package tragicneko.tragicmc.entity.boss;

import java.util.Iterator;
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
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityDarkCrystal;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.entity.projectile.EntityDarkLightning;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityEnyvil extends TragicBoss implements IMultiPart {

	public EntityPart[] enyvilParts;

	public EntityPart enyvilEye;
	public EntityPart enyvilShell;
	public EntityPart enyvilShell2;
	public EntityPart enyvilShell3;

	public EntityPart enyvilArm;
	public EntityPart enyvilArm2;
	public EntityPart enyvilClaw;
	public EntityPart enyvilClaw2;

	public EntityPart enyvilLeg;
	public EntityPart enyvilLeg2;
	public EntityPart enyvilLeg3;
	public EntityPart enyvilLeg4;
	public EntityPart enyvilLeg5;
	public EntityPart enyvilLeg6;

	public EntityDarkCrystal currentCrystal;

	public EntityEnyvil(World par1World) {
		super(par1World);
		this.enyvilParts = new EntityPart[] {this.enyvilEye = new EntityPart(this, "enyvilEye", 1.0F, 1.0F), this.enyvilShell = new EntityPart(this, "enyvilShell", 2.5F, 1.5F),
				this.enyvilShell2 = new EntityPart(this, "enyvilShell2", 2.5F, 1.0F), this.enyvilArm = new EntityPart(this, "enyvilArm", 1.0F, 1.0F),
				this.enyvilArm2 = new EntityPart(this, "enyvilArm2", 1.0F, 1.0F), this.enyvilClaw = new EntityPart(this, "enyvilClaw", 1.0F, 1.0F),
				this.enyvilClaw2 = new EntityPart(this, "enyvilClaw2", 1.0F, 1.0F), this.enyvilLeg = new EntityPart(this, "enyvilLeg", 1.5F, 1.5F),
				this.enyvilLeg2 = new EntityPart(this, "enyvilLeg2", 1.5F, 1.5F), this.enyvilLeg3 = new EntityPart(this, "enyvilLeg3", 1.5F, 1.5F),
				this.enyvilLeg4 = new EntityPart(this, "enyvilLeg4", 1.5F, 1.5F), this.enyvilLeg5 = new EntityPart(this, "enyvilLeg5", 1.5F, 1.5F),
				this.enyvilLeg6 = new EntityPart(this, "enyvilLeg6", 1.5F, 1.5F), this.enyvilShell3 = new EntityPart(this, "enyvilShell3", 1.5F, 2.5F)
		};
		this.setSize(5.5F, 5.5F);
		this.setHealth(this.getMaxHealth());
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true));
		this.isImmuneToFire = true;
		this.stepHeight = 2.5F;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.getDataWatcher().addObject(16, Integer.valueOf((int) 0));
		this.getDataWatcher().addObject(17, Integer.valueOf((int) 0));
		this.getDataWatcher().addObject(18, Integer.valueOf((int) 0));
	}

	/**
	 * State 0 is nothing active, 1 is using the tractor beam, 2 is using the dark energy waves, 3 is using lightning storm, 4 is without any crystals nearby
	 * @return
	 */
	private void setEntityState(int i)
	{
		this.getDataWatcher().updateObject(16, i);
		this.setStateTicks(0);
	}

	/**
	 * State 0 is nothing active, 1 is using the tractor beam, 2 is using the dark energy waves, 3 is using lightning storm, 4 is without any crystals nearby
	 * @return
	 */
	public int getEntityState()
	{
		return this.getDataWatcher().getWatchableObjectInt(16);
	}

	public int getStateTicks()
	{
		return this.getDataWatcher().getWatchableObjectInt(17);
	}

	private void setStateTicks(int i)
	{
		this.getDataWatcher().updateObject(17, i);
	}
	
	private void incrementStateTicks()
	{
		int pow = this.getStateTicks();
		this.setStateTicks(++pow);
	}

	public int getLaserTicks()
	{
		return this.getDataWatcher().getWatchableObjectInt(18);
	}

	private void setLaserTicks(int i)
	{
		this.getDataWatcher().updateObject(18, i);
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(450.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.24);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(24.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		if (!this.worldObj.isRemote && TragicNewConfig.allowMobStatueDrops && rand.nextInt(100) <= TragicNewConfig.mobStatueDropChance) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, 14), 0.4F);
		
		if (!this.worldObj.isRemote)
		{
			List<EntityDarkCrystal> list = this.worldObj.getEntitiesWithinAABB(EntityDarkCrystal.class, this.boundingBox.expand(128.0D, 128.0D, 128.0D));
			Iterator ite = list.iterator();
			EntityDarkCrystal crystal;
			
			while (ite.hasNext())
			{
				crystal = (EntityDarkCrystal) ite.next();
				crystal.setDead();
			}
		}
	}
	
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		if (!this.worldObj.isRemote) this.createNewCrystals();
		return super.onSpawnWithEgg(data);
	}

	public void onLivingUpdate()
	{		
		this.fallDistance = 0.0F;
		super.onLivingUpdate();	

		if (worldObj.isRemote) return;

		for (int i = 0; i < this.enyvilParts.length; i++)
		{
			if (enyvilParts[i].isBurning()) enyvilParts[i].extinguish();
			enyvilParts[i].onUpdate();
		}

		float f1 = this.rotationYaw * (float)Math.PI / 180.0F;
		float f2 = MathHelper.sin(f1);
		float f3 = MathHelper.cos(f1);
		double d = 1.225D;

		this.enyvilEye.setPosition(this.posX - (f2 * 0.025D), this.posY + 3.5D, this.posZ - (f3 * 0.025D));
		this.enyvilShell.setPosition(this.posX - (f2 * 0.25D), this.posY + 4.5D, this.posZ + (f3 * 0.25D));
		this.enyvilShell2.setPosition(this.posX - (f2 * 0.25D), this.posY + 2.0D, this.posZ + (f3 * 0.25D));
		this.enyvilShell3.setPosition(this.posX - (f2 * 0.95D), this.posY + 2.5D, this.posZ - (f3 * 0.95D));
		this.enyvilArm.setPosition(this.posX + (f3 * 0.725D), this.posY, this.posZ + (f2 * 0.725D));
		this.enyvilArm2.setPosition(this.posX - (f3 * 0.725D), this.posY, this.posZ - (f2 * 0.725D));
		this.enyvilClaw.setPosition(this.posX + (f3 * 0.875D), this.posY, this.posZ + (f2 * 0.875D));
		this.enyvilClaw2.setPosition(this.posX - (f3 * 0.875D), this.posY, this.posZ - (f2 * 0.875D));
		this.enyvilLeg.setPosition(this.posX + (f3 * d), this.posY + 3.0D, this.posZ - (f2 * d));
		this.enyvilLeg2.setPosition(this.posX - (f3 * d), this.posY + 3.0D, this.posZ - (f2 * d));
		this.enyvilLeg3.setPosition(this.posX + (f3 * d), this.posY + 4.5D, this.posZ - (f2 * d));
		this.enyvilLeg4.setPosition(this.posX - (f3 * d), this.posY + 4.5D, this.posZ - (f2 * d));
		this.enyvilLeg5.setPosition(this.posX + (f3 * d), this.posY + 6.0D, this.posZ - (f2 * d));
		this.enyvilLeg6.setPosition(this.posX - (f3 * d), this.posY + 6.0D, this.posZ - (f2 * d));

		this.updateEnyvilAI();
		this.updateDarkCrystal();

		this.useAbilitiesBasedOnState();						

		if (this.getLaserTicks() > 0) this.setLaserTicks(this.getLaserTicks() - 1);
		
		if (this.getAttackTarget() != null && this.getEntityState() == 1)
		{
			double d0 = this.posX - this.getAttackTarget().posX;
			double d1 = this.posZ - this.getAttackTarget().posZ;
			double d2 = this.posY - this.getAttackTarget().posY;
			
			float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2) * (float) Math.PI / 180.0F;
			this.rotationYaw = f4;
		}

	}

	private void updateEnyvilAI() {

		if (this.getStateTicks() >= 250 && rand.nextInt(256) == 0) this.setEntityState(0);
		this.incrementStateTicks();

		AttributeModifier mod = new AttributeModifier(UUID.fromString("6824bb10-3472-4b58-8c9d-18bf73dc709c"), "enyvilSpeedDebuff", -0.5D, 0);
		AttributeModifier mod2 = new AttributeModifier(UUID.fromString("59b1124e-a0fa-47dd-ae99-2ceeeccc4b62"), "enyvilRangeBuff", 32.0D, 0);

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).removeModifier(mod2);

		if (this.getEntityState() == 0)
		{
			if (this.getAttackTarget() != null && rand.nextInt(24) == 0)
			{
				if (this.getDistanceToEntity(this.getAttackTarget()) <= 16.0F)
				{
					if (this.canEntityBeSeen(this.getAttackTarget()) && rand.nextInt(4) == 0)
					{
						this.setEntityState(1);
					}
				}
				else
				{
					if (this.canEntityBeSeen(this.getAttackTarget()) && rand.nextInt(16) == 0)
					{
						this.setEntityState(2);
					}
				}
			}
			else if (rand.nextInt(1048) == 0 && this.getAttackTarget() != null)
			{
				this.setEntityState(3);
			}
			this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(mod2);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
		}
	}

	private void useAbilitiesBasedOnState() {
		if (this.getAttackTarget() != null)
		{
			switch(this.getEntityState())
			{
			case 0:
				if (this.getDistanceToEntity(this.getAttackTarget()) >= 4.0F && this.getDistanceToEntity(this.getAttackTarget()) <= 22.0F &&
				this.canEntityBeSeen(this.getAttackTarget()) && rand.nextInt(16) == 0 && this.ticksExisted % 20 == 0)
				{
					double d0 = this.getAttackTarget().posX - this.posX;
					double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
					double d2 = this.getAttackTarget().posZ - this.posZ;

					float f = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.9875F;

					EntityDarkLightning fireball = new EntityDarkLightning(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f, d1, d2 + this.rand.nextGaussian() * (double)f);
					fireball.posX = this.posX + (d0 * 0.115D);
					fireball.posY = this.posY + 3.75D;
					fireball.posZ = this.posZ + (d0 * 0.115D);
					this.worldObj.spawnEntityInWorld(fireball);
				}

				if (rand.nextInt(64) == 0)
				{
					List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(2.0D, 2.0D, 2.0D));
					this.attackEntitiesInList(list);
					EntityLivingBase entity;
					if (!list.isEmpty())
					{
						this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 4.0F + 1.5F, false);
					}
				}
				break;
			case 1:
				this.tractorBeamEntity();
				break;
			case 2:
				if (this.ticksExisted % 15 == 0 && rand.nextInt(3) == 0)
				{
					this.useDarkEnergySpray();
				}
				break;
			case 3:
				if (this.ticksExisted % 20 == 0 && rand.nextBoolean())
				{
					for (int meow = 0; meow < 6 + rand.nextInt(6); meow++)
					{
						double d0 = this.posX + getInteger(16, 4);
						double d1 = this.posZ + getInteger(16, 4);
						double d2 = this.worldObj.getTopSolidOrLiquidBlock((int) d0, (int) d1);
						this.worldObj.createExplosion(this, d0, d2, d1, rand.nextFloat() * 3.0F + 1.5F, WorldHelper.getMobGriefing(this.worldObj));
						this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, d0, d2, d1));
					}
				}
				break;
			}

			if (rand.nextInt(1048) == 0 && this.getLaserTicks() == 0 && this.getEntityState() != 4)
			{
				if (this.currentCrystal != null && !this.currentCrystal.isDead && this.getAttackTarget().canEntityBeSeen(this.currentCrystal))
				{
					this.getAttackTarget().attackEntityFrom(DamageSource.magic, 2.5F * rand.nextFloat() + 1.0F);
					this.setLaserTicks(1000);
				}
			}
		}
		else
		{
			this.setEntityState(0);
		}
	}

	private void useDarkEnergySpray() {
		for (int i = 0; i < rand.nextInt(8) + 10; i++)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.9875F;

			EntityDarkEnergy fireball = new EntityDarkEnergy(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f, d1, d2 + this.rand.nextGaussian() * (double)f);
			fireball.posX = this.posX + (d0 * 0.115D);
			fireball.posY = this.posY + 3.75D;
			fireball.posZ = this.posZ + (d0 * 0.115D);
			this.worldObj.spawnEntityInWorld(fireball);
		}
	}

	public void createNewCrystals() {
		List<EntityDarkCrystal> list = this.worldObj.getEntitiesWithinAABB(EntityDarkCrystal.class, this.boundingBox.expand(64.0D, 64.0D, 64.0D));
		if (list.size() >= 5) return;
		
		for (int i = 0; i < rand.nextInt(3) + 2; i++)
		{
			EntityDarkCrystal crystal = new EntityDarkCrystal(this.worldObj, this);
			crystal.setPosition(this.posX + getInteger(12, 4), this.posY + Math.abs(getInteger(8, 4)), this.posZ + getInteger(12, 4));
			this.worldObj.spawnEntityInWorld(crystal);
		}
	}

	/**
	 * Get a random integer with a range of i (in positive and negative directions) and a minimum value from 0 of min, i.e. range of 4 gets a random value from -4 to 4, then
	 * a min of 2 will ensure it is always -4 to -2 or 2 to 4
	 * @param i
	 * @param min
	 * @return
	 */
	private int getInteger(int i, int min) {
		MathHelper.getRandomIntegerInRange(this.rand, -i, i);
		if (Math.abs(i) <= min)
		{
			if (i < 0) i -= min;
			if (i >= 0) i += min;
		}
		int l = rand.nextBoolean() ? -1 : 1;
		return i * l;
	}

	public void tractorBeamEntity()
	{
		if (this.getAttackTarget() != null)
		{
			EntityLivingBase entity = this.getAttackTarget();
			
			if (entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode) return;
			
			double d0 = entity.posX - this.posX;
			double d1 = entity.posY - this.posY + 2.0D;
			double d2 = entity.posZ - this.posZ;
			float f = MathHelper.sqrt_double(d0 * d0 + d1* d1 + d2 * d2);

			entity.motionX = -d0 / (double)f * d0 * 0.100000011920929D + entity.motionX * 0.20000000298023224D;
			entity.motionY = -d1 / (double)f * d1 * 0.0500000000019408D + entity.motionY * 0.2242009940220809D;
			entity.motionZ = -d2 / (double)f * d2 * 0.100000011920929D + entity.motionZ * 0.20000000298023224D;
			entity.moveEntity(entity.motionX, 0.115D, entity.motionZ);
		}
		else
		{
			this.setEntityState(0);
		}
	}

	public boolean canUseAbilities()
	{
		return this.currentCrystal != null;
	}

	public boolean canBeCollidedWith()
	{
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		return false;
	}

	@Override
	public World getWorld() {
		return this.worldObj;
	}

	@Override
	public boolean attackEntityFromPart(EntityPart entity, DamageSource source, float damage) {

		if (source.isExplosion() || source == DamageSource.drown) return false;
		
		if (this.currentCrystal != null) damage /= 2;

		if (entity == this.enyvilEye)
		{
			if (super.attackEntityFrom(source, damage))
			{
				if (this.getEntityState() == 1 && rand.nextInt(8) == 0) this.setEntityState(0);
				if (this.getEntityState() == 4 && rand.nextBoolean())
				{
					this.createNewCrystals();
				}
			}
		}
		else
		{
			super.attackEntityFrom(source, (damage / 6.0F) + 1.0F);
		}

		return true;
	}

	@Override
	public Entity[] getParts()
	{
		return this.enyvilParts;
	}

	@Override
	public void addPotionEffect(PotionEffect effect) {}

	@Override
	public void setFire(int i) {}

	@Override
	public void fall(float f) {}

	@Override
	public void onStruckByLightning(EntityLightningBolt bolt) {}

	private void attackEntitiesInList(List p_70971_1_)
	{
		for (int i = 0; i < p_70971_1_.size(); ++i)
		{
			Entity entity = (Entity)p_70971_1_.get(i);

			if (entity instanceof EntityLivingBase)
			{
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), 12.0F);
				if (TragicNewConfig.allowFear) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotions.Fear.id, 60 + rand.nextInt(160), rand.nextInt(3))); 
				
				entity.motionX *= 1.225D;
				entity.motionZ *= 1.225D;
				entity.motionY += 0.225D;
			}
		}
	}
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.getDistanceToEntity(par1Entity) >= 6.0D) return false;
		
		par1Entity.motionX *= 1.225D;
		par1Entity.motionZ *= 1.225D;
		par1Entity.motionY += 0.225D;
		boolean result = super.attackEntityAsMob(par1Entity);
		if (result && this.getEntityState() == 1) this.setEntityState(0);
		return result;
	}

	private void updateDarkCrystal()
	{
		if (this.currentCrystal != null)
		{
			if (this.currentCrystal.isDead)
			{
				this.attackEntityFromPart(this.enyvilEye, DamageSource.magic, 25.0F);
				this.currentCrystal = null;
			}
			else
			{
				if (this.getEntityState() == 4) this.setEntityState(0);
				if (this.ticksExisted % 10 == 0 && this.getHealth() < this.getMaxHealth()) this.heal(1.0F);
				this.currentCrystal.motionX = this.motionX;
				this.currentCrystal.motionZ = this.motionZ;

				if (this.getDistanceToEntity(this.currentCrystal) >= 18.0F)
				{
					this.currentCrystal.setPosition(this.posX + getInteger(8, 4), this.posY + Math.abs(getInteger(8, 4)), this.posZ + getInteger(8, 4));
					this.playSound("mob.endermen.portal", 0.4F, 0.4F);
				}
			}
		}
		else
		{
			this.setEntityState(4);
		}

		if (this.rand.nextInt(16) == 0)
		{
			float f = 32.0F;
			List list = this.worldObj.getEntitiesWithinAABB(EntityDarkCrystal.class, this.boundingBox.expand((double)f, (double)f, (double)f));
			EntityDarkCrystal entityendercrystal = null;
			double d0 = Double.MAX_VALUE;
			Iterator iterator = list.iterator();

			while (iterator.hasNext())
			{
				EntityDarkCrystal entityendercrystal1 = (EntityDarkCrystal)iterator.next();
				double d1 = entityendercrystal1.getDistanceSqToEntity(this);

				if (d1 < d0)
				{
					d0 = d1;
					entityendercrystal = entityendercrystal1;
				}
				entityendercrystal1.motionX = this.motionX;
				entityendercrystal1.motionZ = this.motionZ;
			}

			this.currentCrystal = entityendercrystal;

			if (list.isEmpty() && rand.nextInt(24) == 0 && this.getEntityState() == 4)
			{
				this.createNewCrystals();
			}			
		}
	}
}
