package tragicneko.tragicmc.entity.mob;

import java.util.Calendar;
import java.util.UUID;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.main.TragicItems;

public class EntityTragicNeko extends TragicMob {

	private int projCooldown;
	private boolean isAboutToFire;
	private int firingTicks;

	public EntityTragicNeko(World par1World) {
		super(par1World);
		this.setSize(0.4F, 1.955F);
		this.experienceValue = 16;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.55D));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.canCorrupt = false;
		this.isCorruptible = false;
		this.isChangeable = false;
		this.isAboutToFire = false;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.33);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0);
	}

	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	public void setInWeb() {}

	public int getTotalArmorValue()
	{
		return 0;
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (!this.worldObj.isRemote)
		{
			if (this.projCooldown > 0)
			{
				projCooldown--;
			}

			if (this.firingTicks > 0)
			{
				this.firingTicks--;
			}
		}

		if (this.getAttackTarget() != null && rand.nextInt(8) == 0 && !this.worldObj.isRemote && !this.isAboutToFire)
		{
			if (this.projCooldown <= 0)
			{
				this.isAboutToFire = true;
			}
		}

		if (!this.worldObj.isRemote && this.isAboutToFire && this.getAttackTarget() != null)
		{
			this.isAboutToFire = false;
			
			this.firingTicks = 60;
			
			this.doProjectileAttack(this.getAttackTarget(), this.getDistanceToEntity(this.getAttackTarget()));
			this.projCooldown = 51 + rand.nextInt(20);
		}
		
		UUID modUUID = UUID.fromString("ef7bc471-3df8-4d0d-8aa6-8f52ae0a6045");
		AttributeModifier mod = new AttributeModifier(modUUID, "tragicNekoSpeedDebuff", -0.50, 0);
		
		if (this.firingTicks > 0)
		{
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
		}
	}

	public void onDeathUpdate()
	{
		super.onDeathUpdate();
		
		if (this.worldObj.isRemote)
		{
			return;
		}

		if (this.deathTime % 10 == 0)
		{
			EntityNekoStickyBomb bomb = new EntityNekoStickyBomb(this.worldObj, this);

			bomb.posY = this.posY + 0.1;
			bomb.posX = this.posX + rand.nextDouble() * 1.000035;
			bomb.posZ = this.posZ + rand.nextDouble() * 1.000035;
			bomb.motionY = rand.nextDouble() * 0.35;
			bomb.motionZ = (rand.nextDouble() - 0.55) * 0.55;
			bomb.motionX = (rand.nextDouble() - 0.55) * 0.55;

			this.worldObj.spawnEntityInWorld(bomb);
		}
		else if (this.deathTime == 20)
		{
			boolean flag = false;
			int x = 1;

			if (this.rand.nextInt(10) == 0)
			{
				flag = true;
			}

			if (!flag)
			{
				x = rand.nextInt(3) + 2;
			}

			for (int i = 0; i < x; i++)
			{
				EntityNekoClusterBomb bomb = new EntityNekoClusterBomb(this.worldObj, this);
				bomb.posY = this.posY + 0.2;
				bomb.posX = this.posX + rand.nextDouble() * 1.000035;
				bomb.posZ = this.posZ + rand.nextDouble() * 1.000035;
				bomb.motionY = 0.6;
				bomb.motionZ = (rand.nextDouble() - 0.55) * 0.15;
				bomb.motionX = (rand.nextDouble() - 0.55) * 0.15;

				this.worldObj.spawnEntityInWorld(bomb);
			}
		}
	}

	private void doProjectileAttack(EntityLivingBase entity, float distance) 
	{
		Entity theProjectile = null;

		double d0 = entity.posX - this.posX;
		double d1 = entity.boundingBox.minY + (double)(this.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = entity.posZ - this.posZ;

		switch (rand.nextInt(5))
		{
		case 0:
			theProjectile = new EntityNekoRocket(this.worldObj, this, d0, d1, d2);
			break;
		case 1:
			theProjectile = new EntityNekoStickyBomb(this.worldObj, d0, d1, d2);
			break;
		case 2:
			theProjectile = new EntityNekoMiniBomb(this.worldObj, d0, d1, d2);
			break;
		case 3:
			theProjectile = new EntityNekoRocket(this.worldObj, this, d0, d1, d2);
			break;
		case 4:
			theProjectile = new EntityNekoStickyBomb(this.worldObj, d0, d1, d2);
			break;
		}

		if (theProjectile != null)
		{
			theProjectile.posY = this.posY + (this.height * 2 / 3);
			this.worldObj.spawnEntityInWorld(theProjectile);
		}

	}

	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.boundingBox.minY);

		if (i <= 63)
		{
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

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (par1DamageSource.isExplosion())
		{
			return false;
		}

		Boolean result = super.attackEntityFrom(par1DamageSource, par2);

		return result;
	}

}
