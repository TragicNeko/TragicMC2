package tragicneko.tragicmc.entity.mob;

import java.util.Calendar;
import java.util.UUID;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.entity.boss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.projectile.EntityStarShard;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;

public class EntityNorVox extends TragicMob {

	protected AttributeModifier mod = new AttributeModifier(UUID.fromString("e20a064f-7022-4c64-9902-181d3ac9eb17"), "norVoxSpeedDebuff", -0.50, 0);

	public EntityNorVox(World par1World) {
		super(par1World);
		this.stepHeight = 2.0F;
		this.experienceValue = 10;
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected boolean canCorrupt()
	{
		return this.getNorVoxType() == 1;
	}

	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return this.getNorVoxType() == 0 ? super.getBrightnessForRender(par1) : 15728880;
	}

	public float getBrightness(float par1)
	{
		return this.getNorVoxType() == 0 ? super.getBrightness(par1) : 1.0F;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
		this.dataWatcher.addObject(17, Integer.valueOf(0));
		this.dataWatcher.addObject(18, Integer.valueOf(0));
		this.dataWatcher.addObject(19, Integer.valueOf(0));
		this.dataWatcher.addObject(20, Integer.valueOf(0));
	}

	@Override
	public boolean handleWaterMovement()
	{
		return false;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	public int getFiringTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	protected void setFiringTicks(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	protected void decrementFiringTicks()
	{
		int pow = this.getFiringTicks();
		this.setFiringTicks(--pow);
	}

	public int getTextureID()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	protected void setTextureID(int i)
	{
		this.dataWatcher.updateObject(18, i);
	}

	public int getNorVoxType()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	protected void setNorVoxType(int i)
	{
		this.dataWatcher.updateObject(17, i);

		if (i == 0)
		{
			this.setSize(1.135F, 1.575F);
		}
		else
		{
			this.setSize(1.135F / 1.455F, 1.575F / 1.455F);
		}
	}

	public boolean isFiring()
	{
		return this.getFiringTicks() > 0;
	}

	public int getAttackTime()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	protected void setAttackTime(int i)
	{
		this.dataWatcher.updateObject(19, i);
	}

	protected void decrementAttackTime()
	{
		int pow = this.getAttackTime();
		this.setAttackTime(--pow);
	}

	public int getNodTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	protected void setNodTicks(int i)
	{
		this.dataWatcher.updateObject(20, i);
	}

	protected void decrementNodTicks()
	{
		int pow = this.getNodTicks();
		this.setNodTicks(--pow);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		boolean flag = this.getNorVoxType() == 0;
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(flag ? 50.0 : 40.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.39);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.25);
	}

	public void onLivingUpdate()
	{
		if (!this.worldObj.isRemote && this.isPotionActive(Potion.wither.id)) this.removePotionEffect(Potion.wither.id);
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			if (this.getNorVoxType() == 0)
			{
				this.setSize(1.135F, 1.575F);
			}
			else
			{
				this.setSize(1.135F / 1.455F, 1.575F / 1.455F);
			}
		}
		else
		{
			if (this.superiorForm == null && this.getNorVoxType() == 1) this.superiorForm = new EntityVoxStellarum(this.worldObj);
			if (this.isFiring()) this.decrementFiringTicks();
			if (this.getAttackTime() > 0) this.decrementAttackTime();
			if (this.getNodTicks() > 0) this.decrementNodTicks();
			if (this.getAttackTarget() == null) this.setFiringTicks(0);
			if (TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun))
			{
				if (this.isFiring()) this.setFiringTicks(0);
				if (this.getAttackTime() == 0) this.setAttackTime(10);
			}

			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
			if (this.getFiringTicks() >= 60) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);

			if (this.ticksExisted % 20 == 0 && this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && rand.nextInt(8) == 0 && !this.isFiring())
			{
				boolean flag = !TragicNewConfig.allowStun ? true : (this.isPotionActive(TragicPotions.Stun.id) ? false : true);
				if (flag) this.setFiringTicks(120);
			}

			if (this.getFiringTicks() >= 60 && this.ticksExisted % 20 == 0 && this.getAttackTarget() != null) this.shootProjectiles();

			if (this.ticksExisted % 120 == 0 && this.getHealth() < this.getMaxHealth()) this.heal(6.0F);

			if (!this.isFiring() && this.getAttackTarget() == null && this.ticksExisted % 60 == 0 && rand.nextInt(4) == 0) this.setNodTicks(20);
		}

		if (!this.onGround && this.motionY < 0.0D) this.motionY *= 0.68D;
	}

	protected void shootProjectiles()
	{
		if (this.getNorVoxType() == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posY - this.posY;
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;
			float f2 = this.rotationYaw;

			for (int i = 0; i < 2 + rand.nextInt(2); i++)
			{
				EntityWitherSkull skull = new EntityWitherSkull(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
				skull.posX = this.posX + 0.115D * d0;
				skull.posY = this.posY + (this.height * 2 / 3);
				skull.posZ = this.posZ + 0.115D * d2;
				this.worldObj.spawnEntityInWorld(skull);
			}
		}
		else
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posY - this.posY;
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.925F;
			float f2 = this.rotationYaw;

			for (int i = 0; i < 2; i++)
			{
				EntityStarShard shard = new EntityStarShard(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
				shard.posX = this.posX + 0.115D * d0;
				shard.posY = this.posY + (this.height * 2 / 3);
				shard.posZ = this.posZ + 0.115D + d2;
				this.worldObj.spawnEntityInWorld(shard);
			}
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.worldObj.isRemote || par1DamageSource.isExplosion() || par1DamageSource == DamageSource.wither) return false;

		if (par1DamageSource.isProjectile()) par2 /= 4;

		boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (result)
		{
			this.setAttackTime(10);
			if (this.getNodTicks() > 0) this.setNodTicks(0);
			if (this.isFiring() && par1DamageSource.getEntity() != null && rand.nextInt(8) == 0 && this.getFiringTicks() >= 40)
			{
				this.setFiringTicks(0);
				if (TragicNewConfig.allowStun) this.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 60 + rand.nextInt(40)));
			}
		}

		return result;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean flag = super.attackEntityAsMob(par1Entity);

		if (flag && par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0)
		{
			if (TragicNewConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, rand.nextInt(120) + 60, rand.nextInt(2)));
		}
		return flag;
	}

	public int getTotalArmorValue()
	{
		return this.getNorVoxType() == 0 ? 8 : 16;
	}

	protected void fall(float par1) {}
	public void setInWeb() {}

	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.boundingBox.minY);

		if (i >= 63)
		{
			int x = MathHelper.floor_double(this.posX);
			int z = MathHelper.floor_double(this.posZ);

			BiomeGenBase spawnBiome = this.worldObj.getBiomeGenForCoords(x, z);

			if (spawnBiome == BiomeGenBase.jungle || spawnBiome == BiomeGenBase.jungleHills)
			{
				return this.worldObj.getBlockLightValue(x, i, z) > this.rand.nextInt(7) ? false : super.getCanSpawnHere();
			}
			return false;
		}
		else
		{		
			int j = MathHelper.floor_double(this.posX);
			int k = MathHelper.floor_double(this.posZ);
			int l = this.worldObj.getBlockLightValue(j, i, k);
			byte b0 = 4;
			Calendar calendar = this.worldObj.getCurrentDate();

			if ((calendar.get(2) + 1 != 10 || calendar.get(5) < 20) && (calendar.get(2) + 1 != 11 || calendar.get(5) > 3))
			{
				if (this.rand.nextBoolean())
				{
					return false;
				}
			}
			else
			{
				b0 = 7;
			}

			return l > this.rand.nextInt(b0) ? false : super.getCanSpawnHere();
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("norVoxType")) this.setNorVoxType(tag.getInteger("norVoxType"));
		if (tag.hasKey("firingTicks")) this.setFiringTicks(tag.getInteger("firingTicks"));
		if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
		if (tag.hasKey("textureID")) this.setTextureID(tag.getInteger("textureID"));
		if (tag.hasKey("nodTicks")) this.setNodTicks(tag.getInteger("nodTicks"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("norVoxType", this.getNorVoxType());
		tag.setInteger("firingTicks", this.getFiringTicks());
		tag.setInteger("attackTime", this.getAttackTime());
		tag.setInteger("textureID", this.getTextureID());
		tag.setInteger("nodTicks", this.getNodTicks());
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		if (!this.worldObj.isRemote)
		{
			BiomeGenBase biome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
			this.setNorVoxType(biome instanceof BiomeGenStarlitPrarie ? 1 : 0);
			this.setTextureID(rand.nextInt(8));
		}
		return super.onSpawnWithEgg(data);
	}

	@Override
	protected boolean isChangeAllowed() {
		return TragicNewConfig.allowVoxStellarum;
	}
}
