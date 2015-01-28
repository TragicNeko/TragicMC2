package tragicneko.tragicmc.entity.projectile;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.util.WorldHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityGuardianShield extends EntityProjectile {

	public boolean motionFlag;

	private double xOffset;
	private double zOffset;
	
	private float maxHealth = 20.0F;
	private float health = maxHealth;

	public EntityGuardianShield(World world) {
		super(world);
		this.motionFlag = false;
		this.preventEntitySpawning = true;
		this.setSize(2.25F, 2.55F);
	}
	
	public EntityGuardianShield(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
		super(par1World, par2EntityLivingBase, par3, par5, par7);
	}

	@Override
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	public void setCurrentItemOrArmor(int i, ItemStack stack) {

	}

	@Override
	public ItemStack[] getLastActiveItems() {
		return new ItemStack[] {};
	}

	public EntityGuardianShield setShieldMaxHealth(float f)
	{
		this.maxHealth = f;
		this.setHealth(f);
		return this;
	}
	
	public void setHealth(float f)
	{
		this.health = f;
	}

	public void setShieldOffsets(double offx, double offz)
	{
		this.xOffset = offx;
		this.zOffset = offz;
	}

	@Override
	public boolean handleWaterMovement()
	{
		return false;
	}

	@Override
	public void setAir(int i) {}

	@Override
	public void fall(float f) {}

	@Override
	public void updateFallState(double d0, boolean flag) {}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		Entity entity = null;
		if (tag.hasKey("ownerID")) entity = this.worldObj.getEntityByID(tag.getInteger("ownerID"));
		if (entity != null && entity instanceof EntityLivingBase) this.shootingEntity = (EntityLivingBase) entity;
		if (tag.hasKey("motionFlag")) this.motionFlag = tag.getBoolean("motionFlag");
		if (tag.hasKey("offsetX")) this.xOffset = tag.getDouble("offsetX");
		if (tag.hasKey("offsetZ")) this.zOffset = tag.getDouble("offsetZ");
		if (tag.hasKey("health")) this.health = tag.getFloat("health");
		if (tag.hasKey("healthMax")) this.maxHealth = tag.getFloat("maxHealth");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		if (this.shootingEntity != null) tag.setInteger("ownerID", this.shootingEntity.getEntityId());
		tag.setBoolean("motionFlag", this.motionFlag);
		tag.setDouble("offsetX", this.xOffset);
		tag.setDouble("offsetZ", this.zOffset);
		tag.setFloat("health", this.health);
		tag.setFloat("healthMax", this.maxHealth);
	}

	@Override
	public boolean attackEntityFrom(DamageSource src, float dmg)
	{
		if (src == DamageSource.inWall) return false;
		
		if (src.getEntity() != null && src.getEntity() == this.shootingEntity)
		{
			this.motionFlag = true;
			TragicMC.logInfo("Shield attacked by creator");
			Vec3 vec3 = this.shootingEntity.getLookVec();
			
			if (vec3 != null) 	
			{ 	
				this.motionX = vec3.xCoord; 	
				this.motionY = vec3.yCoord; 	
				this.motionZ = vec3.zCoord; 	
				this.accelerationX = this.motionX * 0.1D; 	
				this.accelerationY = this.motionY * 0.1D; 	
				this.accelerationZ = this.motionZ * 0.1D; 	
			}
			
			return super.attackEntityFrom(src, dmg);
		}
		
		if (this.shootingEntity != null && !this.worldObj.isRemote)
		{
			this.health -= dmg;
			if (this.health <= 0F) this.setDead();
			TragicMC.logInfo("Shield damaged, health is " + health);
		}
		
		return super.attackEntityFrom(src, dmg);
	}

	@Override
	public void onUpdate()
	{
		if (!this.motionFlag && this.shootingEntity != null)
		{
			this.motionX = this.shootingEntity.motionX;
			this.motionY = this.shootingEntity.motionY;
			this.motionZ = this.shootingEntity.motionZ;

			this.setPosition(this.shootingEntity.posX + this.xOffset, this.shootingEntity.posY + 0.3, this.shootingEntity.posZ + this.zOffset);
			
			if (!this.worldObj.isRemote && this.shootingEntity.isDead) this.setDead();
		}
		else if (this.motionFlag)
		{
			super.onUpdate();
		}

		if (this.ticksExisted > 600 && !this.worldObj.isRemote)  this.setDead();
		
		if (this.worldObj.isRemote)
		{
			String s = "crit";
			if (Math.abs(this.motionX) > 0 && Math.abs(this.motionY) > 0 && Math.abs(this.motionZ) > 0) s = "smoke";
			
			for (int l = 0; l < 6; ++l)
			{
				this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.855D), this.posY + rand.nextDouble() - rand.nextDouble() + 0.235D,
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.855D), 0.155F * (this.rand.nextFloat() - this.rand.nextFloat()),
						0.155F * (this.rand.nextFloat() - this.rand.nextFloat()), 0.155F * (this.rand.nextFloat() - this.rand.nextFloat()));
			}
		}
	}

	@Override
	protected void onImpact(MovingObjectPosition var1) {
		if (this.worldObj.isRemote || var1 == null) return;
		
		if (this.motionFlag)
		{
			if (var1.entityHit != null)
			{
				if (var1.entityHit instanceof EntityGuardianShield) return;
				var1.entityHit.attackEntityFrom(this.shootingEntity != null ? DamageHelper.causeModMagicDamageToEntity(this.shootingEntity) : DamageSource.magic , this.maxHealth / 10.0F);
			}
			
			this.worldObj.createExplosion(this.shootingEntity != null ? this.shootingEntity : this, this.posX, this.posY, this.posZ, (this.maxHealth / 10.0F) * rand.nextFloat(), WorldHelper.getMobGriefing(this.worldObj));
			this.setDead();
		}
	}
}
