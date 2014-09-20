package tragicneko.tragicmc.entity.boss;

import static tragicneko.tragicmc.entity.mob.EntityRagr.crushableBlocks;
import static tragicneko.tragicmc.events.NewAmuletEvents.badPotions;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBow;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityClaymation extends TragicBoss {

	private double[][] formValues = new double[][] {{150.0D, 0.22D, 12.0D, 32.0D, 1.0D}, {42.0D, 0.45D, 8.0D, 32.0D, 0.5D}, {160.0D, 0.42D, 8.0D, 48.0D, 1.0D},
			{100.0D, 0.22D, 20.0D, 24.0D, 1.0D}, {150.0D, 0.46D, 4.0D, 64.0D, 0.2D}, {50.0D, 0.32D, 5.5D, 32.0D, 0.0D}, {65.0D, 0.38D, 7.0D, 32.0D, 1.0D},
			{220.0D, 0.35D, 16.0D, 32.0D, 1.0D}, {50.0D, 0.42D, 8.0D, 64.0D, 1.0D}, {100.0D, 0.25D, 12.0D, 16.0D, 0.0D}};
	private float[][] formSizes = new float[][] {{0.625F, 2.375F}, {0.7F, 2.5F}, {1.385F, 3.3F}, {1.7835F, 5.15F}, {1.775F, 2.725F}, {0.4F, 0.5F}, {0.935F, 2.87F}, {0.7F, 2.1F},
			{0.615F, 1.695F}, {1.4F, 2.9F}};

	public EntityClaymation(World par1World) {
		super(par1World);
		this.setSize(0.625F, 2.375F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.isImmuneToFire = true;
		this.stepHeight = 1.5F;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.22);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Float.valueOf(0.0F)); //Claymation's actual health
		this.dataWatcher.addObject(17, Integer.valueOf((int) 0)); //Current Entity form
		this.dataWatcher.addObject(18, Integer.valueOf((int) 0)); //Current time in form
		this.dataWatcher.addObject(19, Integer.valueOf((int) 0)); //Utility integer for use by forms
		this.dataWatcher.addObject(20, Integer.valueOf((int) 0)); //Another utility integer for use by forms
	}

	private void updateHealth(float f)
	{
		this.dataWatcher.updateObject(16, f);
	}

	public float getActualHealth()
	{
		return this.dataWatcher.getWatchableObjectFloat(16);
	}

	private void setEntityForm(int i)
	{
		this.dataWatcher.updateObject(17, i);
		this.dataWatcher.updateObject(18, 0); //resets the ticks in a form to 0

		if (i > formValues.length)
		{
			TragicMC.logError("Claymation AI set to an invalid amount, this is not a good thing."); 
			return;
		}

		this.setFormAttributes(i);
		this.setFormSize(i);
		this.resetUtilityIntegers();
	}

	/**
	 * 0 is the normal form, 1 is the Minotaur, 2 is the Apis, 3 is the Stin King, 4 is Nor-Vox, 5 is Jabba, 6 is Ragr, 7 is Skultar, 8 is Kitsunakuma, 9 is Iron Golem
	 * @return
	 */
	public int getEntityForm()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	public int getFormTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	private void incrementFormTicks()
	{
		int pow = this.dataWatcher.getWatchableObjectInt(18);
		this.dataWatcher.updateObject(18, ++pow);
	}

	private void resetUtilityIntegers()
	{
		this.dataWatcher.updateObject(19, 0);
		this.dataWatcher.updateObject(20, 0);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.getAttackTarget() != null)
		{
			this.incrementFormTicks();
		}
		else
		{
			this.setEntityForm(0);
			this.setHealth(this.getMaxHealth());
		}

		if (this.getFormTicks() >= 250 && this.getEntityForm() == 0)
		{
			this.updateHealth(this.getHealth());
			this.setEntityForm(rand.nextInt(10) + 1);
		}
		else if (this.getFormTicks() >= 1200 && this.getEntityForm() != 0)
		{
			this.setEntityForm(rand.nextInt(10) + 1);
		}

		if (this.getAttackTarget() != null) this.updateAsForm();
	}

	private void updateAsForm()
	{
		TragicMC.logInfo("Claymation current form is " + this.getEntityForm());
		TragicMC.logInfo("Current form ticks is " + this.getFormTicks());
		TragicMC.logInfo("Claymation actual health is " + this.getActualHealth());
		TragicMC.logInfo("Claymation form health is " + this.getHealth());

		switch(this.getEntityForm())
		{
		default:
		case 0:
			this.updateAsClaymation();
			break;
		case 1:
			this.updateAsMinotaur();
			break;
		case 2:
			this.updateAsApis();
			break;
		case 3:
			this.updateAsStinKing();
			break;
		case 4:
			this.updateAsNorVox();
			break;
		case 5:
			this.updateAsJabba();
			break;
		case 6:
			this.updateAsRagr();
			break;
		case 7:
			this.updateAsSkultar();
			break;
		case 8:
			this.updateAsKitsunakuma();
			break;
		case 9:
			this.updateAsIronGolem();
			break;
		}
	}

	private void updateAsClaymation()
	{
		if (this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && this.getDistanceToEntity(this.getAttackTarget()) < 8.0F 
				&& this.onGround && rand.nextInt(48) == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / (double)f2 * 2.5D * 0.100000011920929D + this.motionX * 0.20000000298023224D;
			this.motionZ = d1 / (double)f2 * 2.5D * 0.100000011920929D + this.motionZ * 0.20000000298023224D;
			this.motionY = 0.25;
		}

		this.reflectPotionEffects();
	}

	private void reflectPotionEffects() {

		PotionEffect[] effects = new PotionEffect[16];
		PotionEffect temp;
		int a = 0;

		for (int i = 0; i < Potion.potionTypes.length; i++)
		{
			if (Potion.potionTypes[i] != null)
			{
				Potion potion = Potion.potionTypes[i];

				if (this.isPotionActive(potion) && badPotions.contains(potion) && a < effects.length)
				{
					temp = this.getActivePotionEffect(potion);
					effects[a++] = new PotionEffect(potion.id, temp.getDuration() * 2, temp.getAmplifier() * 2);
					this.removePotionEffect(i);
				}
			}
		}

		EntityLivingBase entity = this.getAttackTarget();

		for (int z = 0; z < effects.length; z++)
		{
			if (effects[z] != null)
			{
				entity.addPotionEffect(effects[z]);
			}
		}

	}

	private void updateAsMinotaur()
	{
		if (this.worldObj.isDaytime() && this.worldObj.getLightBrightness((int)this.posX, (int)this.posY, (int)this.posZ) > 0.4F)
		{
			if (this.ticksExisted % 120 == 0 && !this.worldObj.isRemote && this.getHealth() < this.getMaxHealth())
			{
				this.heal(1.0F);
			}
		}

		if (this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && this.getDistanceToEntity(this.getAttackTarget()) < 8.0F 
				&& this.onGround && rand.nextInt(60) == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / (double)f2 * 2.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
			this.motionZ = d1 / (double)f2 * 2.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
			this.motionY = 0.05;
		}
	}

	private void updateAsApis()
	{
		if (this.worldObj.isDaytime() && this.worldObj.getLightBrightness((int)this.posX, (int)this.posY, (int)this.posZ) > 0.4F)
		{
			if (this.ticksExisted % 60 == 0 && this.getHealth() < this.getMaxHealth())
			{
				this.heal(3.0F);
			}
		}

		if (this.getHealth() <= this.getMaxHealth() / 2 && this.rand.nextInt(64) == 0 && this.onGround && this.recentlyHit <= 0)
		{
			if (this.worldObj.isAirBlock((int)this.posX, (int)this.posY, (int)this.posZ) || this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ) instanceof BlockTallGrass)
			{
				this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ, Blocks.fire);
			}
		}

		if (this.getDistanceToEntity(this.getAttackTarget()) > 3.0F && this.getDistanceToEntity(this.getAttackTarget()) < 7.0F 
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

		if (this.getDistanceToEntity(this.getAttackTarget()) > 7.0F && this.onGround && rand.nextInt(40) == 0)
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

		if (this.getDistanceToEntity(this.getAttackTarget()) > 12.0F && rand.nextInt(36) == 0)
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

	private void updateAsStinKing()
	{
		if (rand.nextInt(256) == 0 && TragicNewConfig.allowFear)
		{
			this.getAttackTarget().addPotionEffect(new PotionEffect(TragicPotions.Fear.id, 120 + rand.nextInt(320), rand.nextInt(4)));
		}
	}

	private void updateAsNorVox()
	{
		if (this.ticksExisted % 240 == 0 && this.getHealth() < this.getMaxHealth())
		{
			this.heal(6.0F);
		}

		if (TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun)) this.setFiringTicks(0);

		AttributeModifier mod = new AttributeModifier(UUID.fromString("e20a064f-7022-4c64-9902-181d3ac9eb17"), "norVoxSpeedDebuff", -0.50, 0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);

		if (this.isFiring())
		{
			this.decrementFiringTicks();
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);

			if (this.getFiringTicks() % 20 == 0)
			{
				this.shootNorVoxProjectiles();
			}
		}
		else if (this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && rand.nextInt(36) == 0)
		{
			this.setFiringTicks(120);
		}
	}

	private void updateAsJabba()
	{
		this.incrementFiringTicks();

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

				if (doom != null)
				{
					doom.increaseDoom(-(this.rand.nextInt(3) * d));
				}
			}
		}

		if (this.ticksExisted % 4 == 0 && this.getHealth() <= this.getMaxHealth() / 2)
		{
			AttributeModifier lowHealthDamageBoost = new AttributeModifier(UUID.fromString("8c159dc4-aacf-461f-b3e9-66dc9fbf6e99"), "jabbaLowHealthDamageBoost", 2.5, 0);
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(lowHealthDamageBoost);
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(lowHealthDamageBoost);
		}

		if (this.getFiringTicks() >= 600)
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
					this.shootJabbaProjectiles(entity);
				}
				else
				{
					this.setFiringTicks(this.getFiringTicks() - 50);
				}
			}
		}
	}

	private void updateAsRagr()
	{
		this.incrementFiringTicks();

		if (this.onGround && this.rand.nextInt(40) == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / (double)f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
			this.motionZ = d1 / (double)f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
			this.motionY = 0.7;
		}

		if (this.getFiringTicks() >= 600)
		{
			if (this.getFiringTicks() % 50 == 0 && this.onGround)
			{
				double d0 = entityToAttack.posX - this.posX;
				double d1 = entityToAttack.posZ - this.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX = d0 / (double)f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
				this.motionZ = d1 / (double)f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
				this.motionY = rand.nextDouble() + 0.4 + rand.nextDouble();
			}

			if (this.getFiringTicks() % 20 == 0)
			{
				Entity entity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 10.0);

				if (entity == null)
				{
					this.setFiringTicks(0);
				}
			}

			if (this.getFiringTicks() >= 800)
			{
				this.setFiringTicks(0);
			}
		}
	}

	private void updateAsSkultar()
	{
		this.incrementFiringTicks(); //this will be used for the time since last hit for the Skultar

		if (this.getFiringTicks() >= 200) this.setDemeanor(5);
		if (rand.nextInt(1048) == 0) this.setDemeanor(0);

		int z = this.getHealth() <= this.getMaxHealth() / 2 ? 2 : 1;

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

			if (rand.nextInt(128) == 0 && TragicNewConfig.allowInhibit)
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

		if (this.ticksExisted % 60 == 0 && this.getHealth() < this.getMaxHealth())
		{
			this.heal(6.0F);
		}

		if (this.getDistanceToEntity(this.getAttackTarget()) < 6.0F && this.onGround && rand.nextInt(32) == 0)
		{
			if (this.isAggressive())
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

		if (this.getDistanceToEntity(this.getAttackTarget()) > 6.0F && this.onGround && rand.nextInt(32) == 0 && this.isAggressive())
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = -d0 / (double)f2 * 3.5D * 0.200000011920929D + this.motionX * 0.30000000298023224D;
			this.motionZ = -d1 / (double)f2 * 3.5D * 0.200000011920929D + this.motionZ * 0.30000000298023224D;
			this.motionY = 0.15;
		}

		int x = 1;

		if (!this.isAggressive())
		{
			x = 2;
		}

		if (this.getHealth() <= this.getMaxHealth() / 2)
		{
			x *= 2;
		}

		if (this.getDistanceToEntity(this.getAttackTarget()) > 2.0F && rand.nextInt(36 / x) == 0 && this.canEntityBeSeen(this.getAttackTarget()))
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

	private void updateAsKitsunakuma()
	{
		AttributeModifier mod = new AttributeModifier(UUID.fromString("c6334c3a-6cf4-4755-8fe5-d1b713c1f375"), "kitsuneSpeedDebuff", -0.5, 0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);

		if (this.isFiring())
		{
			this.decrementFiringTicks();
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
		}

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

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) < 4.0F && this.onGround && rand.nextInt(32) == 0 && !this.isFiring())
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / (double)f2 * 2.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
			this.motionZ = d1 / (double)f2 * 2.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
			this.motionY = 0.1;
		}

		if (this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && rand.nextInt(36) == 0 && !this.isFiring() && this.canEntityBeSeen(this.getAttackTarget()))
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

			EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			fireball.posY = this.posY + (this.height * 2 / 3);
			this.worldObj.spawnEntityInWorld(fireball);
		}

		if (this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && this.canEntityBeSeen(this.getAttackTarget()) && this.isFiring() && this.getFiringTicks() % 5 == 0)
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

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 20.0F && rand.nextInt(36) == 0 && !this.isFiring())
		{
			this.teleportToEntity(this.getAttackTarget());
		}
	}

	private void updateAsIronGolem() //this isn't really needed but whatever, better than having this completely empty
	{
		if (this.isFiring()) this.decrementFiringTicks(); 

		if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0)
		{
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset);
			int k = MathHelper.floor_double(this.posZ);
			Block block = this.worldObj.getBlock(i, j, k);

			if (block.getMaterial() != Material.air)
			{
				this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(block) + "_" + this.worldObj.getBlockMetadata(i, j, k), this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.boundingBox.minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, 4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D);
			}
		}
	}

	private void shootNorVoxProjectiles()
	{
		double d0 = this.getAttackTarget().posX - this.posX;
		double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = this.getAttackTarget().posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;
		float f2 = this.rotationYaw;

		for (int i = 0; i < 2 + rand.nextInt(2); i++)
		{
			EntityWitherSkull fireball = new EntityWitherSkull(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			fireball.posY = this.posY + (this.height * 2 / 3);
			this.worldObj.spawnEntityInWorld(fireball);
		}
	}

	private void shootJabbaProjectiles(Entity entity) 
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
			this.setFiringTicks(this.getFiringTicks() - 50);
		}
	}

	public boolean isFiring()
	{
		return this.dataWatcher.getWatchableObjectInt(19) > 0;
	}

	public int getFiringTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	private void setFiringTicks(int i)
	{
		this.dataWatcher.updateObject(19, i);
	}

	private void decrementFiringTicks()
	{
		int pow = this.dataWatcher.getWatchableObjectInt(19);
		this.dataWatcher.updateObject(19, --pow);
	}

	private void incrementFiringTicks()
	{
		int pow = this.dataWatcher.getWatchableObjectInt(19);
		this.dataWatcher.updateObject(19, ++pow);
	}

	public boolean isAggressive()
	{
		return this.dataWatcher.getWatchableObjectInt(20) >= 0;
	}

	private void setDemeanor(int i)
	{
		MathHelper.clamp_int(i, -10, 10);
		this.dataWatcher.updateObject(20, i);
	}

	private void incrementDemeanor()
	{
		int pow = this.dataWatcher.getWatchableObjectInt(20);
		this.dataWatcher.updateObject(20, ++pow);
	}

	private void decrementDemeanor()
	{
		int pow = this.dataWatcher.getWatchableObjectInt(20);
		this.dataWatcher.updateObject(20, --pow);
	}

	private void setFormAttributes(int i)
	{		
		double health = formValues[i][0];
		double speed = formValues[i][1];
		double attack = formValues[i][2];
		double follow = formValues[i][3];
		double knockback = formValues[i][4];

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(health);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attack);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(follow);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(knockback);

		if (i == 0)
		{
			this.setHealth(this.getActualHealth());
		}
		else
		{
			this.setHealth(this.getMaxHealth());
		}
	}

	private void setFormSize(int i)
	{
		this.setSize(formSizes[i][0], formSizes[i][1]);
	}

	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		if (this.worldObj.isRemote) return false;

		if (this.getEntityForm() == 0 && source.isProjectile() && source.getSourceOfDamage() != null &&
				!(source.getSourceOfDamage() instanceof EntitySnowball) && !(source.getSourceOfDamage() instanceof EntityIcicle))
		{
			return false;
		}

		if (this.getEntityForm() != 0)
		{
			switch(this.getEntityForm())
			{
			default:
			case 0:
				break;
			case 1:
				damage = this.getMinotaurAttackResponse(source, damage);
				break;
			case 2:
				damage = this.getApisAttackResponse(source, damage);
				break;
			case 3:
				damage = this.getStinKingAttackResponse(source, damage);
				break;
			case 4:
				damage = this.getNorVoxAttackResponse(source, damage);
				break;
			case 5:
				damage = this.getJabbaAttackResponse(source, damage);
				break;
			case 6:
				damage = this.getRagrAttackResponse(source, damage);
				break;
			case 7:
				damage = this.getSkultarAttackResponse(source, damage);
				break;
			case 8:
				damage = this.getKitsunakumaAttackResponse(source, damage);
				break;
			case 9:
				damage = this.getIronGolemAttackResponse(source, damage);
				break;
			}

			if (this.getHealth() - damage <= 0.0F)
			{
				this.setEntityForm(0);
				return true;
			}
		}

		return super.attackEntityFrom(source, damage);
	}

	private float getMinotaurAttackResponse(DamageSource source, float damage)
	{
		if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) source.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				if (!(player.getCurrentEquippedItem().getItem() instanceof ItemBow))
				{
					player.getCurrentEquippedItem().damageItem(rand.nextInt(3) + 1, player);
				}
			}
			else
			{
				player.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
			}
		}

		if (!source.isProjectile() && !source.isExplosion())
		{
			damage *= 0.65F;
		}
		return damage;
	}

	private float getApisAttackResponse(DamageSource source, float damage)
	{
		if (source.isExplosion())
		{
			return 0.0F;
		}

		if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) source.getEntity();

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

		if (!source.isProjectile())
		{
			damage *= 0.425F;
		}

		return damage;
	}

	private float getStinKingAttackResponse(DamageSource source, float damage)
	{
		if (source.getEntity() != null && source.getEntity() instanceof EntityLivingBase && !source.isProjectile() && rand.nextInt(8) == 0)
		{
			this.doStinTeleport((EntityLivingBase) source.getEntity());
		}

		return damage;
	}

	private float getNorVoxAttackResponse(DamageSource source, float damage)
	{
		if (source.isExplosion())
		{
			return 0.0F;
		}

		if (this.isFiring() && source.getEntity() != null)
		{
			this.setFiringTicks(0);
			if (TragicNewConfig.allowStun) this.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 60 + rand.nextInt(40)));
		}

		if (source.isProjectile())
		{
			damage /= 4;
		}

		return damage;
	}

	private float getJabbaAttackResponse(DamageSource source, float damage)
	{
		if (this.getHealth() <= this.getMaxHealth() / 2)
		{
			damage /= 2;
		}

		if (this.rand.nextInt(8) == 0 && this.worldObj.difficultySetting == EnumDifficulty.HARD)
		{
			if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer && !source.isProjectile() && !source.isMagicDamage() && !source.isFireDamage())
			{
				EntityPlayer player = (EntityPlayer) source.getEntity();

				if (player.getCurrentEquippedItem() != null && !player.capabilities.isCreativeMode)
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
					this.setFiringTicks(this.getFiringTicks() + 50);
				}
			}
		}

		if (source == DamageSource.drown)
		{
			this.setFiringTicks(this.getFiringTicks() + 50);
		}

		return damage;
	}

	private float getRagrAttackResponse(DamageSource source, float damage)
	{
		if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) source.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				if (!(player.getCurrentEquippedItem().getItem() instanceof ItemBow))
				{
					player.getCurrentEquippedItem().damageItem(rand.nextInt(5) + 1, player);
				}
			}
			else
			{
				player.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
			}
		}

		if (source.isProjectile())
		{
			damage /= 2;
		}

		return damage;
	}

	private float getSkultarAttackResponse(DamageSource source, float damage)
	{
		this.trackHitType(source.getDamageType());
		this.setFiringTicks(0);
		return damage;
	}

	private float getKitsunakumaAttackResponse(DamageSource source, float damage)
	{
		if (!source.getDamageType().equals("fireball"))
		{
			return 0.0F;
		}
		else if (source.getEntity() != null && source.getEntity() instanceof EntityLivingBase)
		{
			this.teleportToEntity(source.getEntity());
			this.hurtResistantTime = 100;
			return this.isFiring() ? 20 : 10;				
		}
		else
		{
			return 0.0F;
		}
	}

	private float getIronGolemAttackResponse(DamageSource source, float damage)
	{
		return damage;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;

		boolean flag = super.attackEntityAsMob(par1Entity);

		if (!flag) return flag;

		if (this.getEntityForm() != 0)
		{
			switch(this.getEntityForm())
			{
			default:
			case 0:
				break;
			case 1:
				this.attackEntityAsMinotaur(par1Entity);
				break;
			case 2:
				this.attackEntityAsApis(par1Entity);
				break;
			case 3:
				this.attackEntityAsStinKing(par1Entity);
				break;
			case 4:
				this.attackEntityAsNorVox(par1Entity);
				break;
			case 5:
				this.attackEntityAsJabba(par1Entity);
				break;
			case 6:
				this.attackEntityAsRagr(par1Entity);
				break;
			case 7:
				this.attackEntityAsSkultar(par1Entity);
				break;
			case 8:
				this.attackEntityAsKitsunakuma(par1Entity);
				break;
			case 9:
				this.attackEntityAsIronGolem(par1Entity);
				break;
			}
		}
		else
		{
			if (rand.nextInt(4) == 0) par1Entity.setFire(8 + rand.nextInt(4));
		}
		return flag;
	}

	private void attackEntityAsMinotaur(Entity par1Entity)
	{
		if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0)
		{
			switch(rand.nextInt(3))
			{
			default:
			case 0:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200)));
				break;
			case 1:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200)));
				break;
			case 2:
				if (TragicNewConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, rand.nextInt(200)));
				break;
			}
		}

		par1Entity.motionX *= 1.2000000059604645D;
		par1Entity.motionZ *= 1.2D;
		par1Entity.motionY += 0.3D;
	}

	private void attackEntityAsApis(Entity par1Entity)
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
			boolean flag = WorldHelper.getMobGriefing(worldObj);
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

	private void attackEntityAsStinKing(Entity par1Entity)
	{
		if (rand.nextInt(8) == 0 && par1Entity instanceof EntityLivingBase)
		{
			if (TragicNewConfig.allowDisorientation) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 300, 0));
			if (TragicNewConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 300, 2 + rand.nextInt(3)));

			if (rand.nextInt(16) == 0) this.doStinTeleport((EntityLivingBase) par1Entity);
		}
	}

	private void attackEntityAsNorVox(Entity par1Entity)
	{
		if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0)
		{
			if (TragicNewConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, rand.nextInt(120) + 60));
		}
	}

	private void attackEntityAsJabba(Entity par1Entity)
	{
		byte d;

		if (this.worldObj.difficultySetting == EnumDifficulty.EASY)
		{
			d = 1;
		}
		else if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
		{
			d = 2;
		}
		else
		{
			d = 3;
		}

		if (this.rand.nextInt(MathHelper.ceiling_double_int(9 / d)) == 0)
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

				if (player.getCurrentEquippedItem() != null && !player.capabilities.isCreativeMode)
				{
					player.dropOneItem(true);
				}
			}
		}

		this.setFiringTicks(this.getFiringTicks() + 20);
	}

	private void attackEntityAsRagr(Entity par1Entity)
	{
		if (par1Entity instanceof EntityLivingBase && rand.nextInt(4) == 0)
		{
			switch(rand.nextInt(3))
			{
			case 0:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200)));
				break;
			case 1:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200)));
				break;
			case 2:
				if (TragicNewConfig.allowSubmission)
				{
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, rand.nextInt(200)));
				}
				break;
			}
		}

		par1Entity.motionX *= 1.8000000059604645D;
		par1Entity.motionZ *= 1.8D;
		par1Entity.motionY += 0.6D;
	}

	private void attackEntityAsSkultar(Entity par1Entity)
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
	}

	private void attackEntityAsKitsunakuma(Entity par1Entity)
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

	private void attackEntityAsIronGolem(Entity par1Entity)
	{
		this.setFiringTicks(10);
		par1Entity.motionY += 0.4000000059604645D;
		this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
	}

	@Override
	public int getTotalArmorValue()
	{
		return this.getEntityForm() == 0 ? 18 : 12;
	}

	@Override
	protected void fall(float par1) 
	{
		if (this.getEntityForm() == 6) this.fallAsRagr(par1);
	}

	@Override
	public void setInWeb() {}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	private void doStinTeleport(EntityLivingBase entity)
	{
		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;

		for (int y1 = 0; y1 < 24; y1++)
		{
			for (int z1 = -8; z1 < 9; z1++)
			{
				for (int x1 = -8; x1 < 9; x1++)
				{
					if (World.doesBlockHaveSolidTopSurface(this.worldObj, (int)this.posX + x1, (int)this.posY + y1 - 1, (int)this.posZ + z1) && rand.nextBoolean())
					{
						if (entity instanceof EntityPlayerMP)
						{
							EntityPlayerMP mp = (EntityPlayerMP) entity;

							if (mp.capabilities.isCreativeMode) return;

							if (mp.playerNetServerHandler.func_147362_b().isChannelOpen() && this.worldObj == mp.worldObj)
							{
								if (mp.isRiding()) mp.mountEntity(null);
								AxisAlignedBB bb = mp.boundingBox.copy();
								bb.offset(x + x1, y + y1, z + z1);

								if (this.worldObj.checkNoEntityCollision(bb) && this.worldObj.getCollidingBoundingBoxes(mp, bb).isEmpty() &&
										!this.worldObj.isAnyLiquid(bb))
								{
									mp.playerNetServerHandler.setPlayerLocation(x + x1, y + y1, z + z1, mp.rotationYaw, mp.rotationPitch);
									short short1 = 128;

									for (int l = 0; l < short1; ++l)
									{
										double d6 = (double)l / ((double)short1 - 1.0D);
										float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
										float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
										float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
										double d7 = x + ((x + x1) - x) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
										double d8 = y + ((y + y1) - y) * d6 + this.rand.nextDouble() * (double)this.height;
										double d9 = z + ((z + z1) - z) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
										this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
									}
									mp.addPotionEffect(new PotionEffect(Potion.blindness.id, 200, 0));
									mp.fallDistance = 0.0F;
									this.worldObj.playSoundAtEntity(mp, "mob.endermen.portal", 0.4F, 0.4F);
									return;
								}
							}
						}
						else
						{
							entity.setPosition(x + x1, y + y1, z + z1);

							if (this.worldObj.checkNoEntityCollision(entity.boundingBox) &&
									this.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox).isEmpty() &&
									!this.worldObj.isAnyLiquid(entity.boundingBox))
							{
								short short1 = 128;

								for (int l = 0; l < short1; ++l)
								{
									double d6 = (double)l / ((double)short1 - 1.0D);
									float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
									float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
									float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
									double d7 = x + ((x + x1) - x) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
									double d8 = y + ((y + y1) - y) * d6 + this.rand.nextDouble() * (double)this.height;
									double d9 = z + ((z + z1) - z) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
									this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
								}

								this.worldObj.playSoundAtEntity(entity, "mob.endermen.portal", 0.4F, 0.4F);
								entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 200, 0));
								return;
							}
							else
							{
								entity.setPosition(x, y, z);
							}
						}
					}
				}
			}
		}
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

	private void fallAsRagr(float par1)
	{
		boolean flag = WorldHelper.getMobGriefing(worldObj);

		if (par1 >= 2.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 2.0F, false);
		}
		else if (par1 >= 4.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 2.0F + 1.0F, flag);
		}
		else if (par1 >= 8.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 3.0F + 2.0F, flag);
		}

		if (!flag)
		{
			return;
		}

		int x = (int) this.posX - 1;
		int y = (int) (this.posY - 1);
		int z = (int) this.posZ - 1;

		if (par1 >= 4.0F)
		{
			x--;
			z--;

			for (int x1 = 0; x1 < 5; x1++)
			{
				for (int z1 = 0; z1 < 5; z1++)
				{
					Block block = this.worldObj.getBlock(x + x1, y, z + z1);
					boolean flag2 = false;

					if (x1 != 0 && z1 != 0)
					{
						flag2 = true;
					}

					if (x1 != 4 && z1 != 4)
					{
						flag2 = true;
					}

					if (flag2)
					{
						if (crushableBlocks.contains(block))
						{
							this.worldObj.setBlockToAir(x + x1, y, z + z1);
						}
						else if (block == Blocks.grass)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.dirt);
						}
						else if (block == Blocks.stone)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.cobblestone);
						}
						else if (block == Blocks.stonebrick)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.stonebrick, 2, 2);
						}
						else if (block == Blocks.cobblestone)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.gravel);
						}
					}
				}
			}
		}
		else if (par1 >= 3.0F)
		{
			for (int x1 = 0; x1 < 3; x1++)
			{
				for (int z1 = 0; z1 < 3; z1++)
				{
					Block block = this.worldObj.getBlock(x + x1, y, z + z1);
					boolean flag2 = false;

					if (x1 != 0 && z1 != 0)
					{
						flag2 = true;
					}

					if (x1 != 2 && z1 != 2)
					{
						flag2 = true;
					}

					if (flag2)
					{
						if (crushableBlocks.contains(block))
						{
							this.worldObj.setBlockToAir(x + x1, y, z + z1);
						}
						else if (block == Blocks.grass)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.dirt);
						}
						else if (block == Blocks.stone)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.cobblestone);
						}
						else if (block == Blocks.stonebrick)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.stonebrick, 2, 2);
						}
						else if (block == Blocks.cobblestone)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.gravel);
						}
					}
				}
			}
		}
		else if (par1 >= 2.0F)
		{
			Block block = this.worldObj.getBlock(x, y, z);

			if (crushableBlocks.contains(block))
			{
				this.worldObj.setBlockToAir(x, y, z);
			}
			else if (block == Blocks.grass)
			{
				this.worldObj.setBlock(x, y, z, Blocks.dirt);
			}
			else if (block == Blocks.stone)
			{
				this.worldObj.setBlock(x, y, z, Blocks.cobblestone);
			}
			else if (block == Blocks.stonebrick)
			{
				this.worldObj.setBlock(x, y, z, Blocks.stonebrick, 2, 2);
			}
			else if (block == Blocks.cobblestone)
			{
				this.worldObj.setBlock(x, y, z, Blocks.gravel);
			}
		}
	}

	private void trackHitType(String damageType) 
	{
		String hitType = null;

		if (damageType.equals("arrow") || damageType.equals("fireball"))
		{
			hitType = "projectile";
		}

		if (damageType.equals("indirectMagic") || damageType.equals("player") || damageType.equals("generic"))
		{
			hitType = "normal";
		}

		if (hitType == null)
		{
			return;
		}
		else if (hitType == "projectile")
		{
			this.incrementDemeanor();
		}
		else
		{
			this.decrementDemeanor();
		}
	}
}
