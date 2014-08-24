package tragicneko.tragicmc.entity.boss;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.EntityDropHelper;

public class EntityApis extends TragicBoss {

	public EntityApis(World par1World) {
		super(par1World);
		this.setSize(1.385F, 3.3F);
		this.stepHeight = 2.0F;
		this.experienceValue = 240;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 48.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 48.0F));
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(160.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.42);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	public void onDeath(DamageSource par1)
	{
		if (this.worldObj.isRemote || !this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) return;

		if (!this.worldObj.isRemote) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, 0), 0.4F);

		int x = 2;

		if (par1.getEntity() != null && par1.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				ItemStack weapon = player.inventory.getCurrentItem();
				x += EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, weapon);
			}
		}

		int y = TragicNewConfig.commonDropRate;
		int z = TragicNewConfig.rareDropRate;
		EntityItem item;
		TragicMC.logger.info("Common drop rate: " + y + ", Rare drop rate: " + z);

		for (int i = 0; i < x * 2 + z; i++)
		{
			if (this.rand.nextBoolean())
			{
				item = new EntityItem(this.worldObj, this.posX, this.posY + 0.35F, this.posZ, EntityDropHelper.getRareDropFromEntity(this.getClass()));
				this.worldObj.spawnEntityInWorld(item);
			}
		}

		for (int i = 0; i < x + y; i++)
		{
			if (this.rand.nextBoolean())
			{
				item = new EntityItem(this.worldObj, this.posX, this.posY + 0.35F, this.posZ, EntityDropHelper.getCommonDropFromEntity(this.getClass()));
				this.worldObj.spawnEntityInWorld(item);
			}
		}

		super.onDeath(par1);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isDaytime() && this.worldObj.getLightBrightness((int)this.posX, (int)this.posY, (int)this.posZ) > 0.4F)
		{
			if (this.ticksExisted % 60 == 0 && !this.worldObj.isRemote && this.getHealth() < this.getMaxHealth())
			{
				this.heal(3.0F);
			}
		}

		if (this.getHealth() <= this.getMaxHealth() / 2 && this.rand.nextInt(64) == 0 && this.onGround && this.recentlyHit <= 0 && !this.worldObj.isRemote)
		{
			if (this.worldObj.isAirBlock((int)this.posX, (int)this.posY, (int)this.posZ) || this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ) instanceof BlockTallGrass)
			{
				this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ, Blocks.fire);
			}
		}

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 3.0F && this.getDistanceToEntity(this.getAttackTarget()) < 7.0F 
				&& this.onGround && rand.nextInt(32) == 0 && this.getHealth() <= this.getMaxHealth() / 2)
		{
			if (rand.nextInt(3) == 0)
			{
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)rand.nextDouble() + 0.5F, false);
			}

			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / (double)f2 * 3.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
			this.motionZ = d1 / (double)f2 * 3.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
			this.motionY = 0.1;
		}

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 7.0F && this.onGround && rand.nextInt(40) == 0)
		{
			if (rand.nextInt(3) == 0)
			{
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)rand.nextDouble() + 1.0F, false);
			}

			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / (double)f2 * 3.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
			this.motionZ = d1 / (double)f2 * 3.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
			this.motionY = 0.45;
		}

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 12.0F && rand.nextInt(36) == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

			switch(rand.nextInt(4))
			{
			case 0:

				EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
				fireball.posY = this.posY + this.height;
				this.worldObj.spawnEntityInWorld(fireball);

				break;
			case 1:
				EntitySolarBomb solarBomb = new EntitySolarBomb(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
				solarBomb.posY = this.posY + this.height;
				this.worldObj.spawnEntityInWorld(solarBomb);
				break;
			case 2:
				EntitySolarBomb solarBomb2 = new EntitySolarBomb(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
				solarBomb2.posY = this.posY + this.height;
				this.worldObj.spawnEntityInWorld(solarBomb2);
				break;
			case 3:
				if (this.getHealth() <= this.getMaxHealth() / 2)
				{
					for (int i = 0; i < 3; ++i)
					{
						EntitySmallFireball fireball2 = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
						fireball2.posY = this.posY + this.height;
						this.worldObj.spawnEntityInWorld(fireball2);
					}
				}
				break;
			}
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (par1DamageSource.isExplosion())
		{
			return false;
		}

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				if (!(player.getCurrentEquippedItem().getItem() instanceof ItemBow))
				{
					player.getCurrentEquippedItem().damageItem(rand.nextInt(3) + 1, player);
				}
			}
			else
			{
				if (this.getHealth() <= this.getMaxHealth() / 2)
				{
					player.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F);
					player.setFire(rand.nextInt(8) + 4);
				}
				else
				{
					player.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
				}
			}
		}

		if (!par1DamageSource.isProjectile())
		{
			par2 *= 0.425F;
		}

		Boolean result = super.attackEntityFrom(par1DamageSource, par2);

		return result;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0)
			{
				switch(rand.nextInt(5))
				{
				case 0:
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200) + 160));
					break;
				case 1:
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200) + 160));
					break;
				default:
					if (TragicNewConfig.allowSubmission)
					{
						((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, rand.nextInt(200) + 160, rand.nextInt(2)));
					}
					break;
				}
			}

			if (this.getHealth() <= this.getMaxHealth() / 2)
			{
				par1Entity.setFire(8 + rand.nextInt(12));
			}

			if (this.rand.nextInt(3) == 0 && this.getDistanceToEntity(par1Entity) <= 4.0F)
			{
				boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
				int meow = 3;

				if (!(par1Entity instanceof EntityPlayer))
				{
					meow = 7;
				}
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)rand.nextInt(meow) + 3.0F, flag);
			}	

			par1Entity.motionX *= 2.2000000059604645D;
			par1Entity.motionZ *= 2.2D;
			par1Entity.motionY += 0.6D;

		}
		return super.attackEntityAsMob(par1Entity);
	}

	public int getTotalArmorValue()
	{
		if (this.getHealth() <= this.getMaxHealth() / 4)
		{
			return 20;
		}

		if (this.getHealth() <= this.getMaxHealth() / 2)
		{
			return 15;
		}

		return 10;
	}

}
