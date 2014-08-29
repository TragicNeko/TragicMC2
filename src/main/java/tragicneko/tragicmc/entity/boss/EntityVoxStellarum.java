package tragicneko.tragicmc.entity.boss;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.projectile.EntityStarShard;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityVoxStellarum extends TragicMiniBoss {

	protected boolean isFiring;
	protected int firingTicks;

	public EntityVoxStellarum(World par1World) {
		super(par1World);
		this.setSize(1.435F, 2.675F);
		this.stepHeight = 2.0F;
		this.experienceValue = 10;
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 64.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.canCorrupt = false;
		this.isCorruptible = false;
		this.isChangeable = false;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.46);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.2);
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

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.ticksExisted % 60 == 0 && !this.worldObj.isRemote && this.getHealth() < this.getMaxHealth())
		{
			this.heal(3.0F);
		}

		if (this.shouldSpin())
		{
			this.incrementSpinTicks();			
			if (this.getSpinTicks() > 600) this.setSpinTicks(0);
		}

		if (!this.worldObj.isRemote && TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun.id)) this.firingTicks = 0;

		UUID modUUID2 = UUID.fromString("e20a064f-7022-4c64-99A2-181d3ac9eb17");
		double modifier = getSpinTicks() / 300.0D;
		MathHelper.clamp_double(modifier, 0.455D, 0.91D);
		modifier -= 1.365D;
		AttributeModifier mod2 = new AttributeModifier(modUUID2, "voxStellarumSpinning", modifier, 0);

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod2);

		if (this.isSpinning())
		{
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod2);
		}

		if (!this.worldObj.isRemote && this.firingTicks > 0)
		{
			this.firingTicks--;
		}

		UUID modUUID = UUID.fromString("e20a064f-7022-4c64-9902-181d3ac9eb17");
		AttributeModifier mod = new AttributeModifier(modUUID, "norVoxSpeedDebuff", -0.50, 0);

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);

		if (this.firingTicks > 0)
		{
			this.isFiring = true;
			if (!this.isSpinning()) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
		}
		else
		{
			this.isFiring = false;
		}

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && rand.nextInt(32) == 0 && this.firingTicks == 0 && !this.worldObj.isRemote && !this.isSpinning())
		{
			if (TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun)) this.firingTicks = 120;
		}

		if (this.isSpinning() && this.getHealth() <= this.getMaxHealth() / 4 && this.rand.nextInt(32) == 0) this.firingTicks = 360;

		if (!this.worldObj.isRemote && this.firingTicks > 0 && this.firingTicks % 10 == 0 && this.getAttackTarget() != null)
		{
			this.shootProjectiles();
		}

		if (this.ticksExisted % 20 == 0 && !this.worldObj.isRemote)
		{
			TragicMC.logger.info("Spinning ticks: " + this.getSpinTicks());
			TragicMC.logger.info("Attribute modifier: " + modifier);
			TragicMC.logger.info("Rotation: " + this.getSpinRotation());
		}
	}

	private boolean shouldSpin() {
		if (this.worldObj.isRemote) return false;
		if (this.isSpinning()) return true;
		if (TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun.id) || this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 16.0F) return false;
		if (!this.isFiring && rand.nextInt(128) == 0 && this.getAttackTarget() != null) return true; //make if health is below half it can do this, this is removed for testing purposes currently
		return false;
	}

	protected void shootProjectiles()
	{
		double d0 = this.getAttackTarget().posX - this.posX;
		double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = this.getAttackTarget().posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.985F;
		float f2 = this.rotationYaw;

		for (int i = 0; i < 4 + rand.nextInt(2); i++)
		{
			EntityStarShard fireball = new EntityStarShard(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			fireball.posY = this.posY + (this.height * 2 / 3);
			this.worldObj.spawnEntityInWorld(fireball);
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (par1DamageSource.isExplosion())
		{
			return false;
		}

		if (this.isFiring && par1DamageSource.getEntity() != null)
		{
			this.firingTicks = 0;

			if (TragicNewConfig.allowStun) this.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 20 + rand.nextInt(40)));
		}

		if (par1DamageSource.isProjectile())
		{
			par2 /= 4;
		}

		if (this.isSpinning())
		{
			par2 /= 4;
			if (par1DamageSource.isProjectile()) par2 = 0;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean flag = super.attackEntityAsMob(par1Entity);

		if (flag)
		{
			if (par1Entity instanceof EntityLivingBase && this.getSpinTicks() > 300)
			{
				par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(60) + 120));

				par1Entity.motionX *= 1.75D;
				par1Entity.motionZ *= 1.75D;
				par1Entity.motionY += 0.25D;
			}
		}
		return flag;
	}

	public int getTotalArmorValue()
	{
		return 16;
	}

	protected void fall(float par1) {}
	public void setInWeb() {}

	@Override
	public void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf((int) 0));
		this.dataWatcher.addObject(17, Integer.valueOf((int) 0));
	}

	@SideOnly(Side.CLIENT)
	public int getTextureID()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	public void setTextureID(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	public int getSpinTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	public void setSpinTicks(int i)
	{
		this.dataWatcher.updateObject(17, i);
	}

	public void incrementSpinTicks()
	{
		int pow = this.getSpinTicks();
		this.dataWatcher.updateObject(17, ++pow);
	}

	public float getSpinRotation()
	{
		float f0 = 360.0F;
		if (this.getSpinTicks() > 200) f0 = 270.0F;
		if (this.getSpinTicks() > 300) f0 = 180.0F;
		if (this.getSpinTicks() > 400) f0 = 135.0F;
		if (this.getSpinTicks() > 500) f0 = 180.0F;
		if (this.getSpinTicks() > 550) f0 = 270.0F;
		return MathHelper.wrapAngleTo180_float(this.getSpinTicks() % 360.0F);
	}

	public boolean isSpinning()
	{
		return this.getSpinTicks() > 0;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("texture")) this.setTextureID(tag.getInteger("texture"));
		if (tag.hasKey("spinTicks")) this.setSpinTicks(tag.getInteger("spinTicks"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("texture", this.dataWatcher.getWatchableObjectInt(16));
		tag.setInteger("spinTicks", this.dataWatcher.getWatchableObjectInt(17));
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		this.setTextureID(rand.nextInt(8));
		return super.onSpawnWithEgg(data);
	}
}
