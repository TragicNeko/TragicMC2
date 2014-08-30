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

	private boolean isFiring;
	private int firingTicks;

	public EntityVoxStellarum(World par1World) {
		super(par1World);
		this.setSize(1.435F, 2.675F);
		this.stepHeight = 2.0F;
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 64.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.canCorrupt = false;
		this.isCorruptible = true;
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
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.2);
	}

	public boolean canRenderOnFire()
	{
		return !this.isHealing();
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

		if (this.ticksExisted % 60 == 0 && !this.worldObj.isRemote && this.getHealth() < this.getMaxHealth() && !this.isHealing())
		{
			this.heal(3.0F);
		}

		if (this.shouldSpin() && !this.worldObj.isRemote)
		{
			this.incrementSpinTicks();			
			if (this.getSpinTicks() > 600) this.setSpinTicks(0);
		}

		if (!this.worldObj.isRemote && TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun.id)) this.firingTicks = 0;

		UUID modUUID2 = UUID.fromString("e20a064f-7022-4c64-99A2-181d3ac9eb17");
		double modifier = getSpinTicks() / 200.0D;
		MathHelper.clamp_double(modifier, 0.455D, 0.91D);
		modifier -= 1.365D;
		AttributeModifier mod2 = new AttributeModifier(modUUID2, "voxStellarumSpinning", modifier, 0);

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod2);

		if (this.isSpinning())
		{
			if (modifier <= 0.5D) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod2);
			this.setSprinting(true);
		}
		else
		{
			this.setSprinting(false);
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

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && rand.nextInt(128) == 0 && !this.isFiring && !this.worldObj.isRemote && !this.isSpinning() && !this.isHealing())
		{
			if (!(TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun))) this.firingTicks = 120;
		}

		if (this.isSpinning() && this.getHealth() <= this.getMaxHealth() / 4 && this.rand.nextInt(128) == 0 && !this.isFiring && !this.isHealing()) this.firingTicks = 360;

		if (!this.worldObj.isRemote && this.firingTicks > 0 && this.firingTicks % 10 == 0 && this.getAttackTarget() != null && !this.isHealing())
		{
			this.shootProjectiles();
		}

		if (this.ticksExisted % 20 == 0 && !this.worldObj.isRemote)
		{
			TragicMC.logger.info("Spinning ticks: " + this.getSpinTicks());
			TragicMC.logger.info("Healing ticks: " + this.getHealTicks());
			TragicMC.logger.info("Firing ticks: " + this.firingTicks);
		}
		
		if (this.getHealth() <= this.getMaxHealth() / 2 && !this.isFiring && !this.isSpinning() && rand.nextInt(128) == 0 && this.getAttackTarget() != null && !this.worldObj.isRemote && !this.isHealing())
		{
			this.incrementHealTicks();
		}
		
		if (this.isHealing() && !this.worldObj.isRemote)
		{
			this.incrementHealTicks();
			
			this.motionY = this.onGround ? 0.0D : -0.05D;
			this.motionX = 0.0D;
			this.motionZ = 0.0D;
			
			if (this.ticksExisted % 4 == 0) this.heal(1.0F);
			
			if (this.getHealth() >= this.getMaxHealth() || this.getHealTicks() >= 400)
			{
				this.setHealTicks(0);
			}
		}
		
		if (this.isSpinning() && this.getAttackTarget() != null)
		{
			EntityLivingBase entity = this.getAttackTarget();
			
			double d0 = this.getDistanceToEntity(entity);
			double d1 = entity.posX - this.posX;
			double d2 = entity.posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			double d3 = 0.5D;
			double d4 = this.getSpinTicks() > 100 && this.getSpinTicks() <= 700 ? 0.22786D : 0.1433467D;

			this.motionX = d1 / (double)f2 * d3 * 0.100000011920929D + entity.motionX * d4;
			this.motionZ = d2 / (double)f2 * d3 * 0.100000011920929D + entity.motionZ * d4;
			this.moveEntity(entity.motionX, 0.4D, entity.motionZ);
		}
	}
	
	public boolean isHealing()
	{
		return this.dataWatcher.getWatchableObjectInt(18) > 0;
	}

	private boolean shouldSpin() {
		if (this.worldObj.isRemote) return false;
		if (this.isSpinning()) return true;
		if (TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun.id) || this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 16.0F) return false;
		if (!this.isFiring && !this.isHealing() && rand.nextInt(128) == 0 && this.getAttackTarget() != null && this.getHealth() <= this.getMaxHealth() / 2) return true;
		return false;
	}

	protected void shootProjectiles()
	{
		double d0 = this.getAttackTarget().posX - this.posX;
		double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = this.getAttackTarget().posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.995F;
		float f2 = this.rotationYaw;
		int blah = this.isSpinning() ? 8 : 4;

		for (int i = 0; i < blah + rand.nextInt(2); i++)
		{
			if (this.isSpinning())
			{
				d0 = rand.nextInt(16) - rand.nextInt(16);
				d1 = rand.nextInt(3) - rand.nextInt(3);
				d2 = rand.nextInt(16) - rand.nextInt(16);
			}
			
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
		if (this.isHealing()) return false;
		
		boolean flag = super.attackEntityAsMob(par1Entity);

		if (flag)
		{
			if (par1Entity instanceof EntityLivingBase && this.getSpinTicks() > 100 && this.getSpinTicks() <= 500)
			{
				par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
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
		this.dataWatcher.addObject(18, Integer.valueOf((int) 0));
	}

	@SideOnly(Side.CLIENT)
	public int getTextureID()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	private void setTextureID(int i)
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

	private void incrementSpinTicks()
	{
		int pow = this.getSpinTicks();
		this.dataWatcher.updateObject(17, ++pow);
	}
	
	private void incrementHealTicks()
	{
		int pow = this.getHealTicks();
		this.dataWatcher.updateObject(18, ++pow);
	}
	
	public int getHealTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}
	
	private void setHealTicks(int i)
	{
		this.dataWatcher.updateObject(18, i);
	}

	public float getSpinRotation()
	{
		int i = 1;
		if (this.getSpinTicks() > 50 && this.getSpinTicks() <= 550) i = 4;
		if (this.getSpinTicks() > 100 && this.getSpinTicks() <= 500) i = 6;
		return MathHelper.wrapAngleTo180_float((this.getSpinTicks() * i) % 360.0F);
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
		if (tag.hasKey("healingTicks")) this.setHealTicks(tag.getInteger("healingTicks"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("texture", this.dataWatcher.getWatchableObjectInt(16));
		tag.setInteger("spinTicks", this.dataWatcher.getWatchableObjectInt(17));
		tag.setInteger("healingTicks", this.dataWatcher.getWatchableObjectInt(18));
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		this.setTextureID(rand.nextInt(8));
		return super.onSpawnWithEgg(data);
	}
}
