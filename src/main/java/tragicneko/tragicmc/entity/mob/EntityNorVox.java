package tragicneko.tragicmc.entity.mob;

import java.util.Calendar;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EntityNorVox extends TragicMob {

	protected boolean isFiring;
	protected int firingTicks;

	public EntityNorVox(World par1World) {
		super(par1World);
		this.setSize(1.135F, 1.875F);
		this.stepHeight = 2.0F;
		this.experienceValue = 10;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true)); /*
		this.canCorrupt = false;
		this.isCorruptible = false;
		this.isChangeable = false; */
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.39);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.2);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.ticksExisted % 240 == 0 && !this.worldObj.isRemote && this.getHealth() < this.getMaxHealth())
		{
			this.heal(6.0F);
		}
		
		if (!this.worldObj.isRemote && TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun)) this.firingTicks = 0;

		if (!this.worldObj.isRemote && this.firingTicks > 0)
		{
			this.firingTicks--;
		}
		
		UUID modUUID = UUID.fromString("e20a064f-7022-4c64-9902-181d3ac9eb17");
		AttributeModifier mod = new AttributeModifier(modUUID, "norVoxSpeedDebuff", -0.50, 0);
		
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);

		if (this.firingTicks > 0 && !this.worldObj.isRemote)
		{
			this.isFiring = true;
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
		}
		else
		{
			this.isFiring = false;
		}

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && rand.nextInt(36) == 0 && this.firingTicks == 0 && !this.worldObj.isRemote)
		{
			this.firingTicks = 120;
		}

		if (!this.worldObj.isRemote && this.firingTicks > 0 && this.firingTicks % 20 == 0 && this.getAttackTarget() != null && TragicNewConfig.allowStun && !this.isPotionActive(TragicPotions.Stun))
		{
			this.shootProjectiles();
		}
	}
	
	protected void shootProjectiles()
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

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (par1DamageSource.isExplosion())
		{
			return false;
		}

		if (this.isFiring && par1DamageSource.getEntity() != null)
		{
			this.firingTicks = 0;

			if (TragicNewConfig.allowStun) this.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 60 + rand.nextInt(40)));
		}

		if (par1DamageSource.isProjectile())
		{
			par2 /= 4;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean flag = super.attackEntityAsMob(par1Entity);
		
		if (flag)
		{
			if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0)
			{
				if (TragicNewConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, rand.nextInt(120) + 60));
			}
		}
		return flag;
	}

	public int getTotalArmorValue()
	{
		return 8;
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

			if (spawnBiome == BiomeGenBase.jungle || spawnBiome == BiomeGenBase.jungleHills || spawnBiome == BiomeGenBase.jungleEdge)
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
	protected boolean isChangeAllowed() {
		return false;
	}
}
