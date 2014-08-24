package tragicneko.tragicmc.entity.boss;

import java.util.List;
import java.util.UUID;

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
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EntityYeti extends TragicBoss {

	private boolean isAngry;
	private int angerTicks;

	private boolean shouldBeAngry;
	private int projectileTicks;

	private boolean angerFlag = false; //This ensures that the first time it's health dips below 3/4 it becomes angry
	private boolean angerFlag2 = false; //This ensures that the first time it's health goes below half it becomes angry

	public EntityYeti(World par1World) {
		super(par1World);
		this.setSize(0.885F, 2.435F);
		this.stepHeight = 2.0F;
		this.experienceValue = 240;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.55D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(140.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.2967);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(16.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(2.0);
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		
		if (!this.worldObj.isRemote) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, 4), rand.nextFloat() - rand.nextFloat());
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (!this.worldObj.isRemote)
		{
			if (this.shouldBeAngry)
			{
				this.angerTicks = 500;
				this.shouldBeAngry = false;
			}

			if (this.angerTicks > 0)
			{
				this.angerTicks--;
			}

			if (this.angerTicks > 0)
			{
				this.isAngry = true;
			}
			else
			{
				this.isAngry = false;
			}

			if (this.getHealth() <= this.getMaxHealth() / 4)
			{
				this.isAngry = true;
				this.angerTicks = 1000;
			}

			if (this.projectileTicks > 0)
			{
				this.projectileTicks--;
			}

			if (this.getAttackTarget() == null)
			{
				this.angerTicks = 0;
				this.isAngry = false;
				this.angerFlag = false;
				this.angerFlag2 = false;
			}
			else
			{
				UUID modUUID = UUID.fromString("b23cd5f8-df05-4c8d-91f4-b09f33b15049");
				AttributeModifier mod = new AttributeModifier(modUUID, "yetiSpeedDebuff", -0.5, 0);

				this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);

				double d0 = this.getAttackTarget().posX - this.posX;
				double d1 = this.getAttackTarget().posZ - this.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);

				if (this.isAngry)
				{
					if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 3.0F && this.getDistanceToEntity(this.getAttackTarget()) <= 7.0F 
							&& this.onGround && rand.nextInt(24) == 0)
					{
						this.motionX = d0 / (double)f2 * 3.5D * 0.500000011920929D + this.motionX * 0.60000000298023224D;
						this.motionZ = d1 / (double)f2 * 3.5D * 0.500000011920929D + this.motionZ * 0.60000000298023224D;
						this.motionY = 0.25;
						this.projectileTicks = 0;
					}
					else if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) <= 7.0F && this.onGround && rand.nextInt(24) == 0)
					{
						this.motionX = d0 / (double)f2 * 3.5D * 0.300000011920929D + this.motionX * 0.60000000298023224D;
						this.motionZ = d1 / (double)f2 * 3.5D * 0.300000011920929D + this.motionZ * 0.60000000298023224D;
						this.motionY += 0.45;
						this.projectileTicks = 0;
					}

					if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 7.0F && rand.nextInt(128) == 0 && this.projectileTicks == 0)
					{
						d0 = this.getAttackTarget().posX - this.posX;
						d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
						double d2 = this.getAttackTarget().posZ - this.posZ;
						float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

						EntityLargeRock fireball = new EntityLargeRock(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
						fireball.posY = this.posY + this.height;
						fireball.posX += d0 * 0.02635D;
						fireball.posZ += d2 * 0.02635D;
						this.worldObj.spawnEntityInWorld(fireball);
					}
					
					if (rand.nextInt(32) == 0 && this.projectileTicks == 0)
					{
						this.projectileTicks = 100;
					}

					if (this.projectileTicks > 0 && this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) <= 12.0F)
					{
						d0 = this.getAttackTarget().posX - this.posX;
						d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
						double d2 = this.getAttackTarget().posZ - this.posZ;
						float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

						for (int i = 0; i < 2; i++)
						{
							EntityIcicle fireball = new EntityIcicle(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
							fireball.posY = this.posY + (this.height * 2 / 3);
							fireball.posX += d0 * 0.155D;
							fireball.posZ += d2 * 0.155D;
							this.worldObj.spawnEntityInWorld(fireball);
						}
					}

					if (rand.nextInt(24) == 0 && this.onGround)
					{
						this.motionY += 0.556D;
					}
				}
				else
				{
					this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);

					if (this.projectileTicks == 0 && rand.nextInt(4) == 0)
					{
						d0 = this.getAttackTarget().posX - this.posX;
						d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
						double d2 = this.getAttackTarget().posZ - this.posZ;
						float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

						EntityLargeRock fireball = new EntityLargeRock(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
						fireball.posY = this.posY + this.height;
						fireball.posX += d0 * 0.35D;
						fireball.posZ += d2 * 0.35D;
						this.worldObj.spawnEntityInWorld(fireball);
						this.projectileTicks = 60;
					}

					if (this.rand.nextInt(32) == 0 && this.projectileTicks > 0 && this.projectileTicks % 10 == 0)
					{
						if (TragicNewConfig.allowAbomination) this.attemptToSummonHelp();
					}
				}


			}
		}
	}

	private void attemptToSummonHelp() 
	{
		List<Entity> list = this.worldObj.getEntitiesWithinAABB(EntityAbomination.class, boundingBox.expand(32.0, 32.0, 32.0));		

		if (list.size() >= 4 || this.getAttackTarget() == null)
		{
			return;
		}

		EntityAbomination clone = new EntityAbomination(this.worldObj);
		EntityLivingBase entitylivingbase = this.getAttackTarget();

		for (int y1 = -3; y1 < 4; y1++)
		{
			for (int z1 = -3; z1 < 4; z1++)
			{
				for (int x1 = -3; x1 < 4; x1++)
				{
					if (World.doesBlockHaveSolidTopSurface(this.worldObj, (int)this.posX + x1, (int)this.posY + y1 - 1, (int)this.posZ + z1) && rand.nextBoolean())
					{
						clone.setPosition(this.posX + x1, this.posY + y1, this.posZ + z1);

						if (this.worldObj.checkNoEntityCollision(clone.boundingBox) &&
								this.worldObj.getCollidingBoundingBoxes(clone, clone.boundingBox).isEmpty() &&
								!this.worldObj.isAnyLiquid(clone.boundingBox))
						{
							clone.onSpawnWithEgg(null);
							this.worldObj.spawnEntityInWorld(clone);
							if (entitylivingbase != null) clone.setTarget(entitylivingbase);
							break;
						}
					}
				}
			}
		}

	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (result && !this.worldObj.isRemote)
		{
			if (!this.angerFlag && this.getHealth() <= this.getMaxHealth() / 3 * 4)
			{
				this.shouldBeAngry = true;
				this.angerFlag = true;
			}

			if (!this.angerFlag2 && this.getHealth() <= this.getMaxHealth() / 2)
			{
				this.shouldBeAngry = true;
				this.angerFlag2 = true;
			}

			if (this.rand.nextInt(128) == 0)
			{
				this.shouldBeAngry = true;
			}

			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0, 32.0, 32.0));
			TragicMob mob;

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof TragicMob)
				{
					mob = (TragicMob) list.get(i);

					if (mob instanceof EntityAbomination)
					{
						if (mob.getAttackTarget() != this.getAttackTarget()) mob.setTarget(this.getAttackTarget());
					}
				}
			}
		}

		return result;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0 && this.isAngry)
			{
				if (TragicNewConfig.allowSubmission && rand.nextInt(4) == 0)
				{
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, rand.nextInt(200) + 320, rand.nextInt(2) + 1));
				}

				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200) + 320, rand.nextInt(2) + 1));
			}

			par1Entity.motionX *= 1.4000000059604645D;
			par1Entity.motionZ *= 1.4D;
			par1Entity.motionY += 0.75D;
		}

		return super.attackEntityAsMob(par1Entity);
	}

	public int getTotalArmorValue()
	{
		if (this.getHealth() <= this.getMaxHealth() / 4)
		{
			return 20;
		}
		else if (this.getHealth() <= this.getMaxHealth() / 2)
		{
			return 16;
		}
		else if (this.getHealth() <= this.getMaxHealth() / 3 * 4)
		{
			return 12;
		}
		else
		{
			return 10;
		}
	}

	public void fall(float par1){}

}
