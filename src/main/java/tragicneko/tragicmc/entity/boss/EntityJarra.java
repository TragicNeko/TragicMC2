package tragicneko.tragicmc.entity.boss;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityJanna;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityJarra extends TragicMiniBoss {

	private static final UUID speedBoostUUID = UUID.fromString("7716b9d7-4288-484f-b30c-3442ff620ca4");
	private static final AttributeModifier groupSpeedBoost = new AttributeModifier(speedBoostUUID, "jarraGroupSpeedBoost", 0.25, 0);


	private static final UUID lowHealthKnockbackBuffUUID = UUID.fromString("d8ca100e-e95b-4f86-b824-99730a584ee2");
	private static final AttributeModifier lowHealthKnockbackBuff = new AttributeModifier(lowHealthKnockbackBuffUUID, "jarraLowHealthKnockbackBuff", 1.0, 0);

	private int angerTicks;

	public EntityJarra(World par1World) {
		super(par1World);
		this.setSize(0.6F, 0.75F);
		this.stepHeight = 1.0F;
		this.experienceValue = 20;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		//this.lesserForm = new EntityJabba(par1World);
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return 15728880;
	}

	public float getBrightness(float par1)
	{
		return 1.0F;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.36);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.5);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
	}

	public void onLivingUpdate()
	{
		if (this.isPotionActive(Potion.poison.id))
		{
			this.removePotionEffect(Potion.poison.id);
		}

		super.onLivingUpdate();
		this.doParticleEffects();

		if (!this.worldObj.isRemote)
		{
			if (this.getAttackTarget() != null)
			{
				this.angerTicks++;

				if (this.corruptionTicks > 0)
				{
					this.angerTicks++;
				}
			}
			else
			{
				if (angerTicks > 0)
				{
					this.angerTicks--;
				}
			}

			if (this.isWet() || this.isBurning())
			{
				this.attackEntityFrom(DamageSource.drown, 5.0F);
			}

			if (this.ticksExisted % 20 == 0 && this.rand.nextInt(3) == 0)
			{
				EntityPlayer player = this.worldObj.getClosestVulnerablePlayerToEntity(this, 10.0);

				if (player != null && TragicNewConfig.allowDoom && this.canEntityBeSeen(player))
				{
					PropertyDoom doom = PropertyDoom.get(player);

					byte d = 0;

					if (this.worldObj.difficultySetting == EnumDifficulty.EASY)
					{
						d = 1;
					}
					if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
					{
						d = 2;
					}
					if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
					{
						d = 3;
					}

					if (doom != null && !this.worldObj.isRemote)
					{
						doom.increaseDoom(-(this.rand.nextInt(5) * d));
					}
				}
			}

			if (this.ticksExisted % 5 == 0)
			{
				List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(16.0, 16.0, 16.0));
				this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(groupSpeedBoost);

				if (list.size() > 0)
				{
					Boolean flag = false;
					for (int i = 0; i < list.size(); i++)
					{
						if (list.get(i) instanceof EntityJabba && !flag || list.get(i) instanceof EntityJarra && !flag || list.get(i) instanceof EntityJanna && !flag)
						{						
							flag = true;
							break;
						}
					}

					if (flag)
					{
						this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(groupSpeedBoost);
					}
				}
			}

			if (this.angerTicks >= 600)
			{
				if (this.ticksExisted % 40 == 0)
				{
					List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(16.0D, 16.0D, 16.0D));
					EntityLivingBase entity;

					for (int i = 0; i < list.size(); i++)
					{
						if (list.get(i) instanceof EntityLivingBase)
						{
							entity = (EntityLivingBase) list.get(i);
							if (entity != null)
							{
								if (!(entity instanceof EntityJabba || entity instanceof EntityJarra || entity instanceof EntityJanna)) this.spawnProjectiles(entity);
							}
							else
							{
								angerTicks -= 50;
							}
						}
					}
				}
			}
		}


	}

	protected void doParticleEffects() {
		for (int l = 0; l < 3; ++l)
		{
			this.worldObj.spawnParticle("witchMagic",
					this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
					this.posY + this.rand.nextDouble() * (double)this.height,
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
					(this.rand.nextDouble() - 0.6D) * 0.1D,
					this.rand.nextDouble() * 0.1D,
					(this.rand.nextDouble() - 0.6D) * 0.1D);
		}
	}

	protected void spawnProjectiles(Entity entity) 
	{
		double d0 = entity.posX - this.posX;
		double d1 = entity.boundingBox.minY + (double)(entity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = entity.posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(entity)) * 0.5F;

		for (int i = 0; i < 3; ++i)
		{
			EntityPoisonBarb poisonbarb = new EntityPoisonBarb(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			poisonbarb.posY = this.posY + 0.4D;
			this.worldObj.spawnEntityInWorld(poisonbarb);

			this.angerTicks -= 50;
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.getHealth() <= this.getMaxHealth() / 2)
		{
			par2 /= 2;
		}

		Boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (this.rand.nextInt(8) == 0 && this.worldObj.difficultySetting == EnumDifficulty.HARD && result)
		{
			if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer && !par1DamageSource.isProjectile() && !par1DamageSource.isMagicDamage() && !par1DamageSource.isFireDamage())
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

				if (player.getCurrentEquippedItem() != null)
				{
					player.dropOneItem(true);
				}
				else
				{
					if (this.rand.nextInt(4) == 0)
					{
						player.setFire(4);
					}
				}

				if (player.isAirBorne)
				{
					this.angerTicks += 25;
				}
			}
		}

		if (result || par1DamageSource == DamageSource.drown)
		{
			this.angerTicks += 50;
		}

		return result;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);

		byte d = 0;

		if (this.worldObj.difficultySetting == EnumDifficulty.EASY)
		{
			d = 1;
		}
		if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
		{
			d = 2;
		}
		if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
		{
			d = 3;
		}

		if (this.rand.nextInt(MathHelper.ceiling_double_int(9 / d)) == 0 && result)
		{
			if (par1Entity instanceof EntityLivingBase)
			{
				if (this.rand.nextInt(8) == 0)
				{
					par1Entity.setFire(2 * d);
				}
			}
			if (par1Entity instanceof EntityPlayer && d >= 3)
			{
				EntityPlayer player = (EntityPlayer) par1Entity;

				if (player.getCurrentEquippedItem() != null)
				{
					player.dropOneItem(true);
				}
			}
		}

		if (result)
		{
			this.angerTicks += 20;
		}

		return result;
	}
}
