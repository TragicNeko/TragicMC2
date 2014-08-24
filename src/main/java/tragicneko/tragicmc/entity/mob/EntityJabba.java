package tragicneko.tragicmc.entity.mob;

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
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityJarra;
import tragicneko.tragicmc.entity.boss.TragicMiniBoss;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityJabba extends TragicMob {

	private static final UUID speedBoostUUID = UUID.fromString("1467eeba-3e4e-47f4-bc5d-db85f2297b40");
	private static final AttributeModifier groupSpeedBoost = new AttributeModifier(speedBoostUUID, "jabbaGroupSpeedBoost", 0.2, 0);


	private static final UUID lowHealthDamageBoostUUID = UUID.fromString("8c159dc4-aacf-461f-b3e9-66dc9fbf6e99");
	private static final AttributeModifier lowHealthDamageBoost = new AttributeModifier(lowHealthDamageBoostUUID, "jabbaLowHealthDamageBoost", 2.5, 0);

	protected int angerTicks;

	public EntityJabba(World par1World) {
		super(par1World);
		this.setSize(0.4F, 0.5F);
		this.stepHeight = 1.0F;
		this.experienceValue = 8;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.canCorrupt = false;
		this.isCorruptible = true;
		this.isChangeable = true;
		this.isImmuneToFire = true;
		this.superiorForm = new EntityJarra(par1World);
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.32);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.5);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
	}

	public void onLivingUpdate()
	{
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

			if (this.isWet())
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
						doom.increaseDoom(-(this.rand.nextInt(3) * d));
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

			if (this.ticksExisted % 4 == 0 && this.getHealth() <= this.getMaxHealth() / 2)
			{
				this.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(lowHealthDamageBoost);
				this.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(lowHealthDamageBoost);
			}

			if (this.angerTicks >= 600)
			{
				if (this.ticksExisted % 100 == 0)
				{
					this.worldObj.createExplosion(this, this.posX, this.boundingBox.minY, this.posZ, 1.5F, false);
				}

				if (this.ticksExisted % 40 == 0)
				{
					Entity entity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 10.0);

					if (entity != null)
					{
						this.spawnProjectiles(entity);
					}
					else
					{
						angerTicks -= 50;
					}
				}
			}
		}


	}

	protected void doParticleEffects() {
		for (int k = 0; k < 3; ++k)
		{
			this.worldObj.spawnParticle("dripLava",
					this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D,
					this.posY + this.rand.nextDouble() * (double)this.height - 0.55D,
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D,
					this.rand.nextDouble(),
					this.rand.nextDouble() - 0.6D,
					this.rand.nextDouble());
		}

		for (int l = 0; l < 3; ++l)
		{
			this.worldObj.spawnParticle("flame",
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

		for (int i = 0; i < 5; ++i)
		{
			EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			entitysmallfireball.posY = this.posY + 0.5D;
			this.worldObj.spawnEntityInWorld(entitysmallfireball);
			this.angerTicks -= 50;
		}
	}

	public void onChange(World world, TragicMob entity, TragicMiniBoss boss, double par1, double par2, double par3) {

		if (!TragicNewConfig.allowJarra)
		{
			return;
		}

		boss.copyLocationAndAnglesFrom(this);
		boss.onSpawnWithEgg((IEntityLivingData)null);
		world.removeEntity(this);
		world.spawnEntityInWorld(boss);
		boss.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 2));
		boss.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 2));

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
