package tragicneko.tragicmc.entity.boss;

import static tragicneko.tragicmc.TragicNewConfig.overlordStats;

import java.util.ArrayList;
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
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.miniboss.EntityAegar;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;
import tragicneko.tragicmc.entity.projectile.EntityCrystalMortor;
import tragicneko.tragicmc.util.WorldHelper;

import com.google.common.collect.Sets;

public class EntityOverlord extends TragicBoss {

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
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(overlordStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(overlordStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(overlordStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(overlordStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(overlordStats[4]);
	}
	
	public int getTotalArmorValue()
	{
		return (int) overlordStats[5];
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	private void createCrystalMortors() {
		TragicMC.logInfo("Mortor created.");

		double d0 = this.getAttackTarget().posX - this.posX;
		double d1 = rand.nextInt(4);
		double d2 = this.getAttackTarget().posZ - this.posZ;
		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.975F;

		EntityCrystalMortor mortor = new EntityCrystalMortor(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
		mortor.posY = this.posY + this.height + 0.5D;
		mortor.posX += d0 * 0.04335D;
		mortor.posZ += d2 * 0.04335D;
		mortor.motionY += 0.36D * f1;
		this.worldObj.spawnEntityInWorld(mortor);
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
				this.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
				//this.worldObj.func_147480_a(coords[0], coords[1], coords[2], true);
			}
		}
	}


	private boolean isCourseTraversable(double factor)
	{
		return true;
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

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
	}
}
