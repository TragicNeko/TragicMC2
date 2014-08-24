package tragicneko.tragicmc.entity.boss;

import java.util.List;

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
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.mob.EntityDeathReaperClone;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EntityDeathReaper extends TragicBoss {

	private boolean isBeingAggressive;
	private int aiDemeanor;

	private int timeSinceLastHit;
	private int timeSinceLastAttempt;

	public EntityDeathReaper(World par1World) {
		super(par1World);
		this.setSize(0.7F, 2.1F);
		this.stepHeight = 2.0F;
		this.experienceValue = 240;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
		this.isImmuneToFire = true;
		this.timeSinceLastAttempt = 0;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(220.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.35);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(16.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		if (!this.worldObj.isRemote) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, 2), rand.nextFloat() - rand.nextFloat());
	}

	public void onLivingUpdate()
	{
		if (this.isPotionActive(Potion.wither.id))
		{
			this.removePotionEffect(Potion.wither.id);
		}

		if (this.isPotionActive(Potion.weakness.id))
		{
			this.removePotionEffect(Potion.weakness.id);
		}

		super.onLivingUpdate();

		if (!this.worldObj.isRemote)
		{
			this.timeSinceLastHit++;
			this.timeSinceLastAttempt++;

			if (this.aiDemeanor > 10)
			{
				this.aiDemeanor = 10;
			}

			if (this.aiDemeanor < -10)
			{
				this.aiDemeanor = -10;
			}

			if (this.timeSinceLastHit >= 200)
			{
				this.aiDemeanor = 5;
			}

			if (this.aiDemeanor < 10 && this.ticksExisted % 120 == 0)
			{
				this.aiDemeanor++;
			}

			if (this.rand.nextInt(1048) == 0)
			{
				this.aiDemeanor = 0;
			}

			if (this.aiDemeanor >= 0)
			{
				this.isBeingAggressive = true;
			}
			else
			{
				this.isBeingAggressive = false;
			}


			if (this.getAttackTarget() == null)
			{
				this.isBeingAggressive = false;
			}
			else
			{
				int z = 1;

				if (this.getHealth() <= this.getMaxHealth() / 2)
				{
					z = 2;
				}

				if (this.canEntityBeSeen(this.getAttackTarget()) && this.rand.nextInt(48 / z) == 0)
				{
					EntityLivingBase entity = this.getAttackTarget();

					if (rand.nextInt(72) == 0)
					{
						entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 300 + rand.nextInt(320), 0));
					}

					if (rand.nextInt(256) == 0)
					{
						entity.addPotionEffect(new PotionEffect(Potion.wither.id, 300 + rand.nextInt(320), 0));
					}

					if (rand.nextInt(72) == 0 && TragicNewConfig.allowInhibit)
					{
						entity.addPotionEffect(new PotionEffect(TragicPotions.Inhibit.id, 300 + rand.nextInt(320), 0));
					}

					if (rand.nextInt(72) == 0 && TragicNewConfig.allowCripple)
					{
						entity.addPotionEffect(new PotionEffect(TragicPotions.Cripple.id, 300 + rand.nextInt(320), rand.nextInt(3)));
					}

					if (rand.nextInt(72) == 0 && TragicNewConfig.allowDisorientation)
					{
						entity.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 300 + rand.nextInt(320), rand.nextInt(3)));
					}

					if (rand.nextInt(72) == 0 && TragicNewConfig.allowMalnourish)
					{
						entity.addPotionEffect(new PotionEffect(TragicPotions.Malnourish.id, 300 + rand.nextInt(320), rand.nextInt(3)));
					}
				}
			}

			if (this.ticksExisted % 60 == 0 && !this.worldObj.isRemote && this.getHealth() < this.getMaxHealth())
			{
				this.heal(6.0F);
			}

			if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) < 6.0F && this.onGround && rand.nextInt(32) == 0)
			{
				if (this.isBeingAggressive)
				{
					double d0 = this.getAttackTarget().posX - this.posX;
					double d1 = this.getAttackTarget().posZ - this.posZ;
					double d2 = this.getAttackTarget().posY - this.posY;
					float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
					this.motionX = d0 / (double)f2 * 3.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
					this.motionZ = d1 / (double)f2 * 3.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
					this.motionY = d2 / (double)f2 * 3.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
				}
				else
				{
					double d0 = this.getAttackTarget().posX - this.posX;
					double d1 = this.getAttackTarget().posZ - this.posZ;
					float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
					this.motionX = -d0 / (double)f2 * 3.5D * 0.200000011920929D + this.motionX * 0.30000000298023224D;
					this.motionZ = -d1 / (double)f2 * 3.5D * 0.200000011920929D + this.motionZ * 0.30000000298023224D;
					this.motionY = 0.15;
				}
			}

			if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 6.0F && this.onGround && rand.nextInt(32) == 0 && this.isBeingAggressive)
			{
				double d0 = this.getAttackTarget().posX - this.posX;
				double d1 = this.getAttackTarget().posZ - this.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX = -d0 / (double)f2 * 3.5D * 0.200000011920929D + this.motionX * 0.30000000298023224D;
				this.motionZ = -d1 / (double)f2 * 3.5D * 0.200000011920929D + this.motionZ * 0.30000000298023224D;
				this.motionY = 0.15;
			}

			int x = 1;

			if (!this.isBeingAggressive)
			{
				x = 2;
			}

			if (this.getHealth() <= this.getMaxHealth() / 2)
			{
				x *= 2;
			}

			if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 2.0F && rand.nextInt(36 / x) == 0 && this.canEntityBeSeen(this.getAttackTarget()))
			{
				double d0 = this.getAttackTarget().posX - this.posX;
				double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
				double d2 = this.getAttackTarget().posZ - this.posZ;

				float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

				switch(rand.nextInt(10))
				{
				case 0:
					EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
					fireball.posY = this.posY + (this.height * 2 / 3);
					this.worldObj.spawnEntityInWorld(fireball);
					break;
				case 1:
					for (int miff = 0; miff < 3; miff++)
					{
						EntityWitherSkull solarBomb = new EntityWitherSkull(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
						solarBomb.posY = this.posY + (this.height * 2 / 3);
						solarBomb.setInvulnerable(true);
						this.worldObj.spawnEntityInWorld(solarBomb);
					}
					break;
				case 2:
					for (int wubwub = 0; wubwub < 5; wubwub++)
					{
						EntityWitherSkull witherSkull = new EntityWitherSkull(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
						witherSkull.posY = this.posY + (this.height * 2 / 3);
						this.worldObj.spawnEntityInWorld(witherSkull);
					}
					break;
				case 3:
					for (int i = 0; i < 5; ++i)
					{
						EntitySmallFireball fireball2 = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
						fireball2.posY = this.posY + (this.height * 2 / 3);
						this.worldObj.spawnEntityInWorld(fireball2);
					}
					break;
				case 4:
					this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ));
					break;
				default:
					for (int i = 0; i < 5; ++i)
					{
						EntitySmallFireball fireball3 = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
						fireball3.posY = this.posY + (this.height * 2 / 3);
						this.worldObj.spawnEntityInWorld(fireball3);
					}
					break;

				}
			}
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		String s = par1DamageSource.getDamageType();

		if (!this.worldObj.isRemote)
		{
			this.trackHitType(s);
		}

		boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (result && !this.worldObj.isRemote)
		{
			this.timeSinceLastHit = 0;
		}

		if (result && !this.worldObj.isRemote)
		{
			if (this.getHealth() <= this.getMaxHealth() / 2 && rand.nextInt(16) == 0)
			{
				int potato = 1;

				if (this.getHealth() <= this.getMaxHealth() / 4)
				{
					potato = 2;
				}

				for (int x = 0; x < potato; x++)
				{
					this.attemptToSummonClones();
				}
				this.timeSinceLastAttempt = 0;
			}
		}

		return result;
	}

	private void attemptToSummonClones() 
	{
		List<Entity> list = this.worldObj.getEntitiesWithinAABB(EntityDeathReaperClone.class, boundingBox.expand(32.0, 32.0, 32.0));		

		if (list.size() >= 4 || this.getAttackTarget() == null || this.timeSinceLastAttempt <= 60 || this.worldObj.isRemote)
		{
			return;
		}

		EntityDeathReaperClone clone = new EntityDeathReaperClone(this.worldObj);
		EntityLivingBase entitylivingbase = this.getAttackTarget();

		for (int y1 = -4; y1 < 5; y1++)
		{
			for (int z1 = -3; z1 < 4; z1++)
			{
				for (int x1 = -3; x1 < 4; x1++)
				{
					if (World.doesBlockHaveSolidTopSurface(this.worldObj, (int) this.posX + x1, (int) this.posY + y1 - 1, (int) this.posZ + z1) && rand.nextBoolean())
					{
						clone.setPosition(this.posX + x1, this.posY + y1, this.posZ + z1);

						if (this.worldObj.checkNoEntityCollision(clone.boundingBox) &&
								this.worldObj.getCollidingBoundingBoxes(clone, clone.boundingBox).isEmpty() &&
								!this.worldObj.isAnyLiquid(clone.boundingBox))
						{							
							clone.onSpawnWithEgg(null);
							this.worldObj.spawnEntityInWorld(clone);
							if (entitylivingbase != null) clone.setTarget(entitylivingbase);
							return;
						}
					}
				}
			}
		}

	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0)
			{
				switch(rand.nextInt(6))
				{
				case 0:
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200) + 320));
					break;
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
				case 5:
					if (TragicNewConfig.allowFear)
					{
						((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Fear.id, rand.nextInt(200) + 320));
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

			if (this.getHealth() <= this.getMaxHealth() / 2)
			{
				par1Entity.setFire(4 + rand.nextInt(12));
			}

			par1Entity.motionX *= 1.4000000059604645D;
			par1Entity.motionZ *= 1.4D;
			par1Entity.motionY += 0.3D;
		}

		return super.attackEntityAsMob(par1Entity);
	}

	public int getTotalArmorValue()
	{
		if (this.getHealth() <= this.getMaxHealth() / 2)
		{
			return 20;
		}

		return 12;
	}

	public void fall(float par1){}

	private void trackHitType(String damageType) 
	{
		String hitType = null;

		if (damageType.equals("arrow"))
		{
			hitType = "projectile";
		}

		if (damageType.equals("fireball"))
		{
			hitType = "projectile";
		}

		if (damageType.equals("indirectMagic"))
		{
			hitType = "normal";
		}

		if (damageType.equals("player"))
		{
			hitType = "normal";
		}

		if (damageType.equals("generic"))
		{
			hitType = "normal";
		}

		if (hitType == null)
		{
			return;
		}
		else if (hitType == "projectile")
		{
			this.aiDemeanor++;
		}
		else
		{
			this.aiDemeanor--;
		}
	}
}
