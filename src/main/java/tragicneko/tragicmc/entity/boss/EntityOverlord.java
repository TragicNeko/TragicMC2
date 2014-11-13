package tragicneko.tragicmc.entity.boss;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.miniboss.EntityAegar;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;

public class EntityOverlord extends TragicBoss {
	
	private double[] target = new double[] {0, 0, 0};

	public EntityOverlord(World par1World) {
		super(par1World);
		this.setSize(8.0F, 8.0F);
		this.tasks.addTask(4, new EntityAIWander(this, 0.7D));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, false, new IEntitySelector() {
			public boolean isEntityApplicable(Entity par1Entity)
			{
				return par1Entity instanceof EntityLivingBase && !(par1Entity instanceof EntityOverlord) && !(par1Entity instanceof EntityNanoSwarm) && !(par1Entity instanceof EntityAegar);
			}
		}));
		this.target = new double[] {this.posX, this.posY, this.posZ};
	}

	public boolean isAIEnabled()
	{
		return true;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.326);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(24.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(4.5);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		this.fallDistance = 0.0F;
		
		if (this.worldObj.isRemote)
		{
			return;
		}
		
		if (this.ticksExisted % 5 == 0)
		{
			this.motionY += ((rand.nextDouble() - rand.nextDouble()) + 0.525D) * 0.725D;
			this.motionX += ((rand.nextDouble() - rand.nextDouble())) * 0.425D;
			this.motionZ += ((rand.nextDouble() - rand.nextDouble())) * 0.425D;
			
			if (this.getAttackTarget() == null)
			{
				if (this.ticksExisted % 10 == 0)
				{
					this.motionX = (rand.nextDouble() - rand.nextDouble()) * 0.325D;
					this.motionY = (rand.nextDouble() - rand.nextDouble()) * 0.335D;
					this.motionZ = (rand.nextDouble() - rand.nextDouble()) * 0.325D;
				}
			}
			else
			{
				double d0 = this.getAttackTarget().posX - this.posX;
				double d1 = this.getAttackTarget().posY - this.posY;
				double d2 = this.getAttackTarget().posZ - this.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
				this.motionX = d0 / (double)f2 * 0.75D * 0.150000011920929D + this.motionX * 0.20000000298023224D;
				this.motionY = d1 * 0.10000000298023224D;
				this.motionZ = d2 / (double)f2 * 0.75D * 0.150000011920929D + this.motionZ * 0.20000000298023224D;
			}
		}
	}
}
