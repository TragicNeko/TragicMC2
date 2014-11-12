package tragicneko.tragicmc.entity.miniboss;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.EntityPart;
import tragicneko.tragicmc.entity.boss.IMultiPart;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EntityAegar extends TragicMob implements TragicMiniBoss, IMultiPart {

	public EntityPart[] aegarParts;

	public EntityPart aegarBody;
	public EntityAegarCrystal aegarCrystal;
	public EntityPart aegarClaw;
	public EntityPart aegarCannon;
	public EntityPart aegarHead;

	private AttributeModifier mod = new AttributeModifier(UUID.fromString("8771eb81-724e-4d6b-91a4-d7e2cd17f82c"), "aegarSpeedBuff", 0.156, 0);

	public EntityAegar(World par1World) {
		super(par1World);
		this.aegarParts = new EntityPart[] {aegarBody = new EntityPart(this, "body", 1.0F, 1.0F), aegarCrystal = new EntityAegarCrystal(this),
				aegarClaw = new EntityPart(this, "claw", 1.5F, 1.5F), aegarCannon = new EntityPart(this, "cannon", 1.5F, 1.5F),
				aegarHead = new EntityPart(this, "head", 1.0F, 1.0F)};
		this.setSize(1.385F * 1.545F, 2.325F * 1.545F);
		this.stepHeight = 2.0F;
		this.experienceValue = 100;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
		this.isImmuneToFire = true;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.185);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(2.5);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
		this.dataWatcher.addObject(17, Integer.valueOf(0));
		this.dataWatcher.addObject(18, Integer.valueOf(0)); 
		this.dataWatcher.addObject(19, Integer.valueOf(0));
		this.dataWatcher.addObject(20, Integer.valueOf(0));
		this.dataWatcher.addObject(21, Integer.valueOf(0));
		this.dataWatcher.addObject(22, Integer.valueOf(0));
	}

	public boolean getHypermode()
	{
		return this.dataWatcher.getWatchableObjectInt(16) == 1;
	}

	private void setHypermode(boolean flag)
	{
		this.dataWatcher.updateObject(16, flag ? 1 : 0);
	}

	public int getStunTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	private void setStunTicks(int i)
	{
		this.dataWatcher.updateObject(17, i);
	}

	private void decrementStunTicks()
	{
		this.setStunTicks(this.getStunTicks() - 1);
	}

	public int getShockwaveTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	private void setShockwaveTicks(int i)
	{
		this.dataWatcher.updateObject(18, i);
	}

	private void decrementShockwaveTicks()
	{
		this.setShockwaveTicks(this.getShockwaveTicks() - 1);
	}

	public int getAttackTime()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	private void setAttackTime(int i)
	{
		this.dataWatcher.updateObject(19, i);
	}

	private void decrementAttackTime()
	{
		this.setAttackTime(this.getAttackTime() - 1);
	}

	public int getHurtTime()
	{
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	private void setHurtTime(int i)
	{
		this.dataWatcher.updateObject(20, i);
	}

	private void decrementHurtTime()
	{
		this.setHurtTime(this.getHurtTime() - 1);
	}

	public int getLaserTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	private void setLaserTicks(int i)
	{
		this.dataWatcher.updateObject(21, i);
	}

	private void decrementLaserTicks()
	{
		this.setLaserTicks(this.getLaserTicks() - 1);
	}

	public int getMortorTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(22);
	}

	private void setMortorTicks(int i)
	{
		this.dataWatcher.updateObject(22, i);
	}

	private void decrementMortorTicks()
	{
		this.setMortorTicks(this.getMortorTicks() - 1);
	}

	private boolean canUseAbility()
	{
		return this.getStunTicks() == 0 && this.getShockwaveTicks() == 0 && this.getLaserTicks() == 0 && this.getMortorTicks() == 0;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getStunTicks() > 0)
		{
			this.motionX = this.motionZ = 0.0D;
			this.motionY = -0.1D;
			if (this.getShockwaveTicks() > 0) this.setShockwaveTicks(0);
			if (this.getHurtTime() > 0) this.setHurtTime(0);
			if (this.getAttackTime() > 0) this.setAttackTime(0);
			if (this.getLaserTicks() > 0) this.setLaserTicks(0);
			if (this.getMortorTicks() > 0) this.setMortorTicks(0);
		}

		super.onLivingUpdate();

		float f1 = this.rotationYaw * (float)Math.PI / 180.0F;
		float f2 = MathHelper.sin(f1);
		float f3 = MathHelper.cos(f1);
		double d = 1.225D;

		this.aegarHead.width = 0.35F;
		this.aegarHead.height = 0.75F;
		this.aegarCrystal.height = 0.65F;
		this.aegarCrystal.width = 0.45F;
		this.aegarBody.height = this.aegarBody.width = 0.45F;
		this.aegarCannon.width = this.aegarClaw.width = 0.55F;
		this.aegarClaw.height = this.aegarCannon.height = 1.25F;

		this.aegarHead.setLocationAndAngles(this.posX + (f2 * 0.025D), this.posY + 3.25D, this.posZ + (f3 * 0.025D), 0.0F, 0.0F);
		this.aegarCrystal.setLocationAndAngles(this.posX + (f2 * 0.025D), this.posY + 2.55D, this.posZ + (f3 * 0.025D), 0.0F, 0.0F);
		this.aegarBody.setLocationAndAngles(this.posX + (f2 * 0.025D), this.posY + 1.25D, this.posZ + (f3 * 0.025D), 0.0F, 0.0F);
		this.aegarCannon.setLocationAndAngles(this.posX + (f3 * 0.875D), this.posY + 1.2D, this.posZ + (f2 * 0.875D), 0.0F, 0.0F);
		this.aegarClaw.setLocationAndAngles(this.posX - (f3 * 0.875D), this.posY + 1.2D, this.posZ - (f2 * 0.875D), 0.0F, 0.0F);

		for (int i = 0; i < this.aegarParts.length; i++)
		{
			if (aegarParts[i].isBurning()) aegarParts[i].extinguish();
			aegarParts[i].onUpdate();
		}

		if (this.worldObj.isRemote)
		{
			String s = this.getHypermode() ? "reddust" : "happyVillager";

			this.aegarCrystal.worldObj.spawnParticle(s, this.aegarCrystal.posX + rand.nextDouble() - rand.nextDouble(), this.aegarCrystal.posY  + rand.nextDouble() * this.aegarCrystal.height,
					this.aegarCrystal.posZ + rand.nextDouble() - rand.nextDouble(),
					0.0, 0.0, 0.0);

			if (rand.nextBoolean())
				this.worldObj.spawnParticle(s, this.posX + (rand.nextDouble() - rand.nextDouble()) * this.width, this.posY + rand.nextDouble() * this.height + 0.15, this.posZ + (rand.nextDouble() - rand.nextDouble()) * this.width,
						0.0, 0.0, 0.0);

			for (int i = 0; i < 4; i++)
			{
				this.worldObj.spawnParticle("mobSpellAmbient", this.posX + (rand.nextDouble() - rand.nextDouble()) * 0.55, this.posY + 0.25 + rand.nextDouble(), this.posZ + (rand.nextDouble() - rand.nextDouble()) * 0.55,
						0.0, 0.0, 0.0);
			}

			//Do particle effect when charging up to use shockwave or laser
			return;
		}

		if (this.getStunTicks() > 0) this.decrementStunTicks();
		if (this.getShockwaveTicks() > 0) this.decrementShockwaveTicks();
		if (this.getHurtTime() > 0) this.decrementHurtTime();
		if (this.getAttackTime() > 0) this.decrementAttackTime();
		if (this.getLaserTicks() > 0) this.decrementLaserTicks();
		if (this.getMortorTicks() > 0) this.decrementMortorTicks();

		if (this.aegarCrystal.getHealth() <= 0 && !this.getHypermode()) this.onCrystalDestruction();

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
		if (this.getHypermode()) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);

		if (this.ticksExisted % 40 == 0) TragicMC.logInfo("Aegar health is at " + this.getHealth());
	}

	private void onCrystalDestruction() {
		this.setHypermode(true);
		super.attackEntityFrom(DamageSource.magic, 75.0F);
		this.setStunTicks(120);

		if (this.getShockwaveTicks() > 0) this.setShockwaveTicks(0);
		if (this.getHurtTime() > 0) this.setHurtTime(0);
		if (this.getAttackTime() > 0) this.setAttackTime(0);
		if (this.getLaserTicks() > 0) this.setLaserTicks(0);
		if (this.getMortorTicks() > 0) this.setMortorTicks(0);
	}

	public int getTotalArmorValue()
	{
		return 24;
	}

	@Override
	protected void fall(float par1) {}

	@Override
	public void addPotionEffect(PotionEffect effect) {}

	@Override
	public void setFire(int i) {}

	@Override
	public void onStruckByLightning(EntityLightningBolt bolt) {}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public World getWorld() {
		return this.worldObj;
	}

	public boolean canRenderOnFire()
	{
		return false;
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
	public Entity[] getParts()
	{
		return this.aegarParts;
	}

	@Override
	public boolean attackEntityFromPart(EntityPart entity, DamageSource source, float damage) {
		if (this.worldObj.isRemote || source.isFireDamage() || source == DamageSource.drown) return false;
		TragicMC.logInfo("Aegar part hit was " + entity.partName);
		if (this.getHurtTime() == 0) this.setHurtTime(10);

		if (source.canHarmInCreative()) return super.attackEntityFrom(source, damage);

		if (this.aegarCrystal.getHealth() <= 0)
		{
			damage = damage / 4.0F + 1.0F;
		}
		else
		{
			if (entity == this.aegarCrystal)
			{
				damage /= 2.0F;
			}
			else
			{
				damage = damage / 3.0F + 1.0F;
			}
		}

		TragicMC.logInfo("Damage done to Aegar was " + damage);
		return super.attackEntityFrom(source, damage);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote || this.getAttackTime() != 0) return false;
		boolean result = super.attackEntityAsMob(par1Entity);
		if (result)
		{
			this.setAttackTime(10);
			par1Entity.motionX *= 2.35D;
			par1Entity.motionZ *= 2.35D;
			if (par1Entity instanceof EntityPlayer && TragicNewConfig.allowHacked && rand.nextInt(6) == 0) ((EntityPlayer) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Hacked.id, 60, 0));
		}
		return result;
	}

	private void attackEntitiesInList(List list)
	{
		for (int i = 0; i < list.size(); ++i)
		{
			Entity entity = (Entity)list.get(i);

			if (entity instanceof EntityLivingBase)
			{
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), 12.0F);

				entity.motionX *= 3.225D;
				entity.motionZ *= 3.225D;
				entity.motionY += 0.225D;
			}
		}
	}

	@Override
	public EntityPart getDefaultPart() {
		return this.aegarBody;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("hypermode")) this.setHypermode(tag.getBoolean("hypermode"));
		if (tag.hasKey("stunTicks")) this.setStunTicks(tag.getInteger("stunTicks"));
		if (tag.hasKey("shockwaveTicks")) this.setShockwaveTicks(tag.getInteger("shockwaveTicks"));
		if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
		if (tag.hasKey("hurtTime")) this.setHurtTime(tag.getInteger("hurtTime"));
		if (tag.hasKey("laserTicks")) this.setLaserTicks(tag.getInteger("laserTicks"));
		if (tag.hasKey("mortorTicks")) this.setMortorTicks(tag.getInteger("mortorTicks"));
		if (tag.hasKey("crystalHealth")) this.aegarCrystal.setHealth(tag.getFloat("crystalHealth"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setBoolean("hypermode", this.getHypermode());
		tag.setInteger("stunTicks", this.getStunTicks());
		tag.setInteger("shockwaveTicks", this.getShockwaveTicks());
		tag.setInteger("attackTime", this.getAttackTime());
		tag.setInteger("hurtTime", this.getHurtTime());
		tag.setInteger("laserTicks", this.getLaserTicks());
		tag.setInteger("mortorTicks", this.getMortorTicks());
		tag.setFloat("crystalHealth", this.aegarCrystal.getHealth());
	}
}
