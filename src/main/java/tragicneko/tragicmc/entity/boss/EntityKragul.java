package tragicneko.tragicmc.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityKragul extends TragicMiniBoss {
	
	private int waitTime;

	public EntityKragul(World par1World) {
		super(par1World);
		this.setSize(0.3F, 1.6F);
		this.stepHeight = 1.0F;
		this.experienceValue = 16;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.isImmuneToFire = true;
		this.waitTime = 60;
		//this.lesserForm = new EntityGragul(par1World);
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEAD;
	}

	public boolean canRenderOnFire()
	{
		return false;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.38);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		for (int k = 0; k < 1; ++k)
		{
			this.worldObj.spawnParticle("townaura",
					this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D,
					this.posY + this.rand.nextDouble() * (double)this.height + 0.6D,
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D,
					this.rand.nextDouble(),
					this.rand.nextDouble() - 0.6D,
					this.rand.nextDouble());
		}

		for (int l = 0; l < 2; ++l)
		{
			this.worldObj.spawnParticle("reddust",
					this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
					this.posY + this.rand.nextDouble() * (double)this.height + 0.6D,
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
					(this.rand.nextDouble() - 0.6D) * 0.1D,
					this.rand.nextDouble() * 0.1D,
					(this.rand.nextDouble() - 0.6D) * 0.1D);
		}
		
		if (this.worldObj.isRemote) return; 
		
		if (this.waitTime > 0)
		{
			waitTime--;
		}

		if (this.getAttackTarget() != null && this.ticksExisted % 120 == 0)
		{
			if (TragicNewConfig.allowInhibit && this.canEntityBeSeen(this.getAttackTarget()))
			{
				this.getAttackTarget().addPotionEffect(new PotionEffect(TragicPotions.Inhibit.id, 600));
			}
		}

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 4.0F && this.getDistanceToEntity(this.getAttackTarget()) <= 16.0F && rand.nextInt(36) == 0
				&& this.canEntityBeSeen(this.getAttackTarget()) && this.waitTime == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

			EntitySpiritCast fireball = new EntitySpiritCast(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			fireball.posY = this.posY + this.height;
			this.worldObj.spawnEntityInWorld(fireball);
			this.waitTime = 40;
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		EnumDifficulty dif = this.worldObj.difficultySetting;

		if (par1DamageSource.isProjectile() || par1DamageSource.isFireDamage())
		{
			return false;
		}

		if (dif == EnumDifficulty.EASY)
		{
			if (par2 < 3.0F)
			{
				return false;
			}
			par2 = 3.0F;
		}

		if (dif == EnumDifficulty.NORMAL)
		{
			if (par2 < 2.0F)
			{
				return false;
			}
			par2 = 2.0F;
		}

		if (dif == EnumDifficulty.HARD)
		{
			if (par2 < 1.0F)
			{
				return false;
			}
			par2 = 1.0F;
		}

		if (super.attackEntityFrom(par1DamageSource, par2))
		{
			this.hurtResistantTime = 100;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		
		if (!(par1Entity instanceof EntityLivingBase))
		{
			return false;
		}
		
		boolean result = par1Entity.attackEntityFrom(DamageHelper.causeSuffocationDamageFromMob(this), ((EntityLivingBase) par1Entity).getMaxHealth() / 5);

		if (result)
		{
			par1Entity.attackEntityFrom(DamageHelper.causeSuffocationDamageFromMob(this), ((EntityLivingBase) par1Entity).getMaxHealth() / 5);

			if (par1Entity.worldObj.difficultySetting == EnumDifficulty.HARD)
			{
				if (rand.nextInt(2) == 0)
				{
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 300));
				}
				else if (rand.nextInt(4) == 0)
				{
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 300));
				}
				else if (rand.nextInt(16) == 0 && TragicNewConfig.allowStun)
				{
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 20 + rand.nextInt(40)));
				}

			}
		}

		return result;
	}

}
