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
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EntityPolaris extends TragicBoss {

	public boolean isSpamming;
	private int spamTicks;

	public EntityPolaris(World par1World) {
		super(par1World);
		this.setSize(0.5F, 2.075F);
		this.stepHeight = 2.0F;
		this.experienceValue = 120;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityTameable.class, 0, true));
		this.isSpamming = false;
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(120.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.39);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);

		if (!this.worldObj.isRemote && TragicNewConfig.allowMobStatueDrops && rand.nextInt(100) <= TragicNewConfig.mobStatueDropChance) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, 5), 0.4F);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (!this.worldObj.isRemote)
		{
			if (this.getAttackTarget() != null)
			{
				WorldInfo info = this.worldObj.getWorldInfo();

				if (info.isRaining())
				{
					info.setRaining(false);
					info.setRainTime(0);
				}

				if (info.isThundering())
				{
					info.setThundering(false);
					info.setThunderTime(0);
				}

				info.setWorldTime(18000);
			}

			if (this.ticksExisted % 120 == 0)
			{
				this.heal(6.0F);
			}

			if (this.spamTicks > 0)
			{
				this.spamTicks--;
				this.isSpamming = true;
			}
			else
			{
				this.isSpamming = false;
			}

			UUID modUUID = UUID.fromString("a89f9af3-29f6-4e0f-a674-fee52f8c9b21");
			AttributeModifier mod = new AttributeModifier(modUUID, "polarisSpeedDebuff", -0.3, 0);

			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);

			if (this.isSpamming)
			{
				this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
			}

			if (this.getAttackTarget() != null)
			{
				if (!this.isSpamming)
				{
					if (this.getDistanceToEntity(this.getAttackTarget()) >= 3.0F && this.getDistanceToEntity(this.getAttackTarget()) <= 16.0F)
					{
						if (TragicNewConfig.allowStun && !this.isPotionActive(TragicPotions.Stun)) this.setInvisible(true);
					}
					else
					{
						this.setInvisible(false);
					}
				}
				else
				{
					this.setInvisible(false);

					if (this.spamTicks % 5 == 0)
					{
						double d0 = this.getAttackTarget().posX - this.posX;
						double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
						double d2 = this.getAttackTarget().posZ - this.posZ;

						float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.92F;

						for (int i = 0; i < 5; i++)
						{
							EntitySpiritCast fireball = new EntitySpiritCast(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
							fireball.posY = this.posY + (this.height * 2 / 3);
							this.worldObj.spawnEntityInWorld(fireball);
						}
					}
				}
			}
			else
			{
				this.spamTicks = 0;
				this.isSpamming = false;
				this.setInvisible(false);
			}
		}


		if (this.ticksExisted % 60 == 0 && !this.isSpamming && this.getAttackTarget() != null)
		{			
			if (rand.nextInt(48) == 0)
			{
				if (TragicNewConfig.allowStun && !this.isPotionActive(TragicPotions.Stun)) this.teleportRandomly();
			}
			else if (rand.nextInt(8) == 0)
			{
				if (TragicNewConfig.allowStun && !this.isPotionActive(TragicPotions.Stun)) this.teleportToEntity(this.getAttackTarget());
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (par1DamageSource.getDamageType().equals("arrow"))
		{
			par2 *= 1.15;
		}

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase)
		{
			if (TragicNewConfig.allowStun && !this.isPotionActive(TragicPotions.Stun)) this.teleportRandomly();

			if (par1DamageSource.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

				if (player.experienceTotal > 20)
				{
					player.experienceTotal -= 20;
				}
				else
				{
					player.experienceTotal = 0;
				}
			}

			if (!this.worldObj.isRemote)
			{
				if (this.isSpamming)
				{
					if (par1DamageSource.getEntity() != null)
					{
						if (TragicNewConfig.allowStun) this.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 60));
						this.spamTicks = 0;
						this.isSpamming = false;
					}
				}
				else
				{
					if (this.rand.nextInt(16) == 0 && this.getHealth() <= this.getMaxHealth() / 2)
					{
						this.spamTicks = 80;
						this.isSpamming = true;
					}
				}
			}
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			this.teleportRandomly();
		}

		return super.attackEntityAsMob(par1Entity);

	}

	public int getTotalArmorValue()
	{
		return this.isSpamming ? 25 : 16;
	}

	public void fall(float par1){}

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
			this.worldObj.playSoundEffect(d3, d4, d5, "mob.enderdragon.wings", 1.0F, 1.0F);
			this.playSound("mob.enderdragon.wings", 1.0F, 1.0F);
			return true;
		}
	}
}
