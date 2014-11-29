package tragicneko.tragicmc.entity.boss;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.miniboss.EntityAegar;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;

import com.google.common.collect.Sets;

public class EntityOverlord extends TragicBoss {

	private double[] target = new double[] {0, 0, 0};
	private int targetChangeCooldown = 0;
	private boolean shouldHover = false;
	private int hoverTicks = 0;
	private int hoverBuffer = 0;

	private static final Set ignoredBlocks = Sets.newHashSet(new Block[] {TragicBlocks.OverlordBarrier, Blocks.air, TragicBlocks.Luminescence});

	public EntityOverlord(World par1World) {
		super(par1World);
		this.setSize(6.0F, 6.0F);
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, false, new IEntitySelector() {
			public boolean isEntityApplicable(Entity par1Entity)
			{
				return par1Entity instanceof EntityLivingBase && !(par1Entity instanceof EntityOverlord) && !(par1Entity instanceof EntityNanoSwarm) && !(par1Entity instanceof EntityAegar);
			}
		}));
		this.target = new double[] {this.posX, this.posY, this.posZ};
		this.shouldHover = false;
	}

	public boolean handleWaterMovement()
	{
		return false;
	}

	public void setAir(int i){}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.326);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(24.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(4.5);
	}

	public void onLivingUpdate()
	{
		this.fallDistance = 0.0F;

		super.onLivingUpdate();		

		//TODO final form parts

		if (this.worldObj.isRemote)
		{
			//particles
			return;
		}

		if (this.targetChangeCooldown > 0) this.targetChangeCooldown--;
		if (this.getMobGriefing() && this.getDistanceToTarget() > 4.0 && rand.nextInt(4) == 0 && this.ticksExisted % 5 == 0) this.destroyBlocks();

		double d0 = this.target[0] - this.posX;
		double d1 = this.target[1] - this.posY;
		double d2 = this.target[2] - this.posZ;

		double d3 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
		if (!this.isCourseTraversable(d3)) this.setNewMotionTarget();
		if (this.getDistanceToTarget() < 16.0 || this.getDistanceToTarget() > 12200.0 || this.target[1] < 5 || this.target[1] > this.worldObj.provider.getHeight() - 10) this.setNewMotionTarget();
		if (rand.nextInt(1048) == 0 && this.hoverBuffer >= 300) this.shouldHover = true;

		if (this.getAttackTarget() == null)
		{
			if (this.ticksExisted % 5 == 0)
			{
				this.motionX = (target[0] - this.posX) * 0.25 / d3;
				this.motionY = (target[1] - this.posY) * 0.25 / d3;
				this.motionZ = (target[2] - this.posZ) * 0.25 / d3;
			}
		}
		else
		{
			this.motionX = (this.getAttackTarget().posX - this.posX) * 0.23 / d3;
			this.motionY = (this.getAttackTarget().posY - this.posY) * 0.23 / d3;
			this.motionZ = (this.getAttackTarget().posZ - this.posZ) * 0.23 / d3;
		}

		if (this.motionY < 0 && this.getAttackTarget() != null && this.getAttackTarget().posY - this.posY >= 0 || this.posY < 5)
		{
			List<int[]> list = WorldHelper.getBlocksInCircularRange(this.worldObj, 2.5, this.posX, this.posY - 1, this.posZ);
			int[] coords;
			Block block;
			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				block = this.worldObj.getBlock(coords[0], coords[1], coords[2]);
				if (block == Blocks.air || block == TragicBlocks.Luminescence) this.worldObj.setBlock(coords[0], coords[1], coords[2], TragicBlocks.OverlordBarrier);
			}
		}

		if (this.shouldHover)
		{
			if (this.hoverTicks % 5 == 0) TragicMC.logInfo("Overlord is hovering");
			this.hoverTicks++;
			this.motionX = this.motionY = this.motionZ = 0.0;
			this.target = new double[] {this.posX, this.posY, this.posZ};
			if (this.hoverTicks > 300)
			{
				this.shouldHover = false;
				this.setNewMotionTarget();
			}
			this.hoverBuffer = 0;
		}
		else
		{
			this.hoverTicks = 0;
			this.hoverBuffer++;
		}

		if (this.ticksExisted % 120 == 0)
		{
			int i = this.getPlayersNearby(64.0, 1, 8);
			this.heal(i * 5.0F);
		}
	}

	private void destroyBlocks() {
		ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(this.worldObj, this.width - 0.725D, this.posX, this.posY + this.height / 2.0D, this.posZ);
		int[] coords;
		Block block;

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			block = this.worldObj.getBlock(coords[0], coords[1], coords[2]);

			if (!this.ignoredBlocks.contains(block))
			{
				this.worldObj.func_147480_a(coords[0], coords[1], coords[2], true);
			}
		}
	}

	private void setNewMotionTarget() {
		if (this.targetChangeCooldown > 0 || this.shouldHover) return;

		TragicMC.logInfo("New target selected.");
		this.target = new double[] {rand.nextInt(48) - rand.nextInt(48) + this.posX,
				rand.nextInt(48) - rand.nextInt(32) + this.posY,
				rand.nextInt(48) - rand.nextInt(48) + this.posZ};

		if (this.posY < 5) this.target[1] += rand.nextInt(32) + 5;
		if (this.posY > this.worldObj.provider.getHeight() - 10) this.target[1] -= rand.nextInt(32) - 5;
		this.targetChangeCooldown = 200;
	}

	private double getDistanceToTarget() {
		double d0 = this.target[0] - this.posX;
		double d1 = this.target[1] - this.posY;
		double d2 = this.target[2] = this.posZ;
		return d0 * d0 + d1 * d1 + d2 * d2;
	}

	private boolean isCourseTraversable(double factor)
	{
		return this.target[1] > 5 && this.target[1] < this.worldObj.provider.getHeight() - 10;
		/*
		double d4 = (this.target[0] - this.posX) / factor;
		double d5 = (this.target[1] - this.posY) / factor;
		double d6 = (this.target[2] - this.posZ) / factor;
		AxisAlignedBB axisalignedbb = this.boundingBox.copy();

		for (int i = 1; (double)i < factor; ++i)
		{
			axisalignedbb.offset(d4, d5, d6);

			if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty())
			{
				return false;
			}
		}

		return true; */
	}

	@Override
	protected void despawnEntity() {}

	@Override
	public void fall(float f) {}
}
