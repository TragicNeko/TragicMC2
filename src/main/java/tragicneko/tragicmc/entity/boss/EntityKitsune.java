package tragicneko.tragicmc.entity.boss;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EntityKitsune extends TragicBoss {

	private boolean isFiring;
	private int firingTicks;

	public EntityKitsune(World par1World) {
		super(par1World);
		this.setSize(0.615F, 1.695F);
		this.experienceValue = 120;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 64.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.isImmuneToFire = true;
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Beast;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.42);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		
		if (!this.worldObj.isRemote && TragicNewConfig.allowMobStatueDrops && rand.nextInt(100) <= TragicNewConfig.mobStatueDropChance) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, 1), 0.4F);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.firingTicks > 0)
		{
			this.firingTicks--;
		}

		UUID modUUID = UUID.fromString("c6334c3a-6cf4-4755-8fe5-d1b713c1f375");
		AttributeModifier mod = new AttributeModifier(modUUID, "kitsuneSpeedDebuff", -0.5, 0);

		if (this.firingTicks > 0)
		{
			this.isFiring = true;

			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
		}
		else
		{
			this.isFiring = false;
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
		}

		if (this.getAttackTarget() != null)
		{
			if (this.canEntityBeSeen(this.getAttackTarget()))
			{
				if (this.rand.nextInt(32) == 0)
				{
					EntityLivingBase entity = this.getAttackTarget();

					if (rand.nextInt(72) == 0)
					{
						entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 300 + rand.nextInt(320), 0));
					}

					if (rand.nextInt(72) == 0 && TragicNewConfig.allowDisorientation)
					{
						entity.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 300 + rand.nextInt(320), rand.nextInt(3)));
					}

					if (rand.nextInt(72) == 0)
					{
						entity.addPotionEffect(new PotionEffect(Potion.confusion.id, 300 + rand.nextInt(320), 0));
					}

					if (this.ticksExisted % 120 == 0 && this.rand.nextInt(16) == 0)
					{
						this.teleportRandomly();
					}
				}
			}
			else
			{
				if (this.rand.nextInt(56) == 0 || this.hurtResistantTime > 0 && this.hurtResistantTime % 20 == 0)
				{
					this.teleportToEntity(this.getAttackTarget());
				}
			}
		}

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) < 5.0F && this.onGround && rand.nextInt(32) == 0 && !this.isFiring)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / (double)f2 * 2.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
			this.motionZ = d1 / (double)f2 * 2.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
			this.motionY = 0.1;
		}

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && rand.nextInt(36) == 0 && this.firingTicks == 0 && this.canEntityBeSeen(this.getAttackTarget()))
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

			EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			fireball.posY = this.posY + (this.height * 2 / 3);
			this.worldObj.spawnEntityInWorld(fireball);
			this.firingTicks = 40;
		}

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && this.hurtResistantTime > 0 && this.hurtResistantTime % 20 == 0 && this.canEntityBeSeen(this.getAttackTarget()))
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

			for (int i = 0; i < 3; i++)
			{
				EntitySmallFireball fireball = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
				fireball.posY = this.posY + (this.height * 2 / 3);
				this.worldObj.spawnEntityInWorld(fireball);
			}
		}
		
		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 20.0F && rand.nextInt(36) == 0 && !this.isFiring)
		{
			this.teleportToEntity(this.getAttackTarget());
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (!par1DamageSource.getDamageType().equals("fireball"))
		{
			return super.attackEntityFrom(par1DamageSource, 0.0F);
		}
		else
		{
			if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase)
			{
				par2 = 10;

				if (this.isFiring)
				{
					par2 = 20;
				}

				this.teleportToEntity(par1DamageSource.getEntity());
			}
			else
			{
				return super.attackEntityFrom(par1DamageSource, 0.0F);
			}
		}

		this.hurtResistantTime = 100;

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity instanceof EntityLivingBase && rand.nextInt(4) == 0)
			{
				switch(rand.nextInt(10))
				{
				case 1:
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200) + 320));
					break;
				case 2:
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, rand.nextInt(200) + 320));
					break;
				case 3:
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, rand.nextInt(200) + 320));
					break;
				case 4:
					if (TragicNewConfig.allowDisorientation)
					{
						((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, rand.nextInt(200) + 320));
					}
					break;
				default:
					if (TragicNewConfig.allowSubmission)
					{
						((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, rand.nextInt(200) + 320, rand.nextInt(2) + 1));
					}
					break;
				}
			}

			if (this.rand.nextInt(4) == 0)
			{
				par1Entity.setFire(4 + rand.nextInt(12));
			}

			par1Entity.motionX *= 1.4000000059604645D;
			par1Entity.motionZ *= 1.4D;
			par1Entity.motionY += 0.5D;
		}

		return super.attackEntityAsMob(par1Entity);
	}

	protected boolean teleportRandomly()
	{
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 24.0D;
		double d1 = this.posY + (double)(this.rand.nextInt(48) - 24);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 24.0D;
		return this.teleportTo(d0, d1, d2);
	}

	protected boolean teleportToEntity(Entity par1Entity)
	{
		Vec3 vec3 = Vec3.createVectorHelper(this.posX - par1Entity.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - par1Entity.posY + (double)par1Entity.getEyeHeight(), this.posZ - par1Entity.posZ);
		vec3 = vec3.normalize();
		double d0 = 16.0D;
		double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
		double d2 = this.posY + (double)(this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
		double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
		return this.teleportTo(d1, d2, d3);
	}

	protected boolean teleportTo(double par1, double par3, double par5)
	{
		double d3 = this.posX;
		double d4 = this.posY;
		double d5 = this.posZ;
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		boolean flag = false;
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);

		boolean flag2 = false;

		if (this.worldObj.getBlockLightValue(i, j, k) <= 4)
		{
			flag2 = true;
		}

		if (this.worldObj.blockExists(i, j, k) && flag2)
		{
			boolean flag1 = false;

			while (!flag1 && j > 0)
			{
				Block block = this.worldObj.getBlock(i, j - 1, k);

				if (block.getMaterial().blocksMovement())
				{
					flag1 = true;
				}
				else
				{
					--this.posY;
					--j;
				}
			}

			if (flag1)
			{
				this.setPosition(this.posX, this.posY, this.posZ);

				if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
				{
					flag = true;
				}
			}
		}

		if (!flag)
		{
			this.setPosition(d3, d4, d5);
			return false;
		}
		else
		{

			short short1 = 128;

			for (int l = 0; l < short1; ++l)
			{
				double d6 = (double)l / ((double)short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
				double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
				double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
				this.worldObj.spawnParticle("flame", d7, d8, d9, (double)f, (double)f1, (double)f2);
			}
			this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
			this.playSound("mob.endermen.portal", 1.0F, 1.0F);
			return true;
		}
	}

}
