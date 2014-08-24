package tragicneko.tragicmc.entity.boss;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
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
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.projectile.EntityTimeBomb;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.DamageHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityTimeController extends TragicBoss {

	private int timeShift;
	private float damageStored;
	private boolean shiftFlag;

	private int playerCollisionTicks;
	private int timeSinceCollision;

	private static UUID modUUID = UUID.fromString("c6334c3a-6cf4-4755-8fe5-d1b713c1f375");
	private static AttributeModifier mod = new AttributeModifier(modUUID, "timeControllerSpeedDebuff", -0.105, 0);

	private static UUID modUUID2 = UUID.fromString("e759d09b-f687-470a-89ec-18c9f5bed01b");
	private static AttributeModifier mod2 = new AttributeModifier(modUUID2, "timeControllerSpeedBuff", 0.175, 0);

	private static UUID modUUID3 = UUID.fromString("4dc6a9b8-e755-4f67-a6ff-f3ba08161d24");
	private static AttributeModifier mod3 = new AttributeModifier(modUUID2, "timeControllerKnockbackBuff", 1.0, 0);

	public EntityTimeController(World par1World) {
		super(par1World);
		this.setSize(1.245F, 3.55F);
		this.stepHeight = 2.0F;
		this.experienceValue = 240;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 64.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.shiftFlag = false;
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, 0);
	}

	@SideOnly(Side.CLIENT)
	public int getCurrentTimeMode()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		if (this.dataWatcher.getWatchableObjectInt(16) == 1)
		{
			return 0;
		}

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

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(350.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.346);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.5);
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);

		if (!this.worldObj.isRemote) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, 3), rand.nextFloat() - rand.nextFloat());
	}

	public void onLivingUpdate()
	{
		for (int i = 0; i < 100; i++)
		{
			if (this.isPotionActive(i))
			{
				this.removePotionEffect(i);
			}
		}

		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		this.timeShift--;
		timeSinceCollision++;

		if (timeSinceCollision > 80)
		{
			playerCollisionTicks = 0;
		}

		if (this.timeShift >= 400 && rand.nextInt(128) == 0 && this.getAttackTarget() != null) //this shifts the time mode to a different one if time shift has ran out
		{
			switch (this.dataWatcher.getWatchableObjectInt(16))
			{
			case 0:
				this.dataWatcher.updateObject(16, rand.nextInt(2) + 1);
				break;
			case 1:
				if (this.getAttackTarget() != null && this.getAttackTarget() instanceof EntityPlayer)
				{
					this.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) this.getAttackTarget()), this.damageStored);
				}
				else if (this.getAttackTarget() != null)
				{
					this.attackEntityFrom(DamageSource.causeMobDamage(this.getAttackTarget()), this.damageStored);
				}
				this.damageStored = 0.0F;
			case 2:
			default:
				this.dataWatcher.updateObject(16, 0);
				break;
			}

			this.timeShift = 0;
		}

		IAttributeInstance att = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
		att.removeModifier(mod);
		att.removeModifier(mod2);

		IAttributeInstance att2 = this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance);
		att2.removeModifier(mod3);

		switch(this.dataWatcher.getWatchableObjectInt(16)) //This controls the overall ai based on time mode
		{
		case 2: //future
			att.applyModifier(mod2);

			if (this.getAttackTarget() != null)
			{
				this.worldObj.setWorldTime(this.worldObj.getWorldTime() + 20);
			}

			if (this.ticksExisted % 60 == 0 && this.getHealth() < this.getMaxHealth() && !this.worldObj.isRemote)
			{
				this.heal(3.0F);
			}

			if (this.onGround && rand.nextInt(128) == 0)
			{
				this.teleportRandomly();
			}
			else if (this.onGround && this.getAttackTarget() != null)
			{
				this.teleportToEntity(this.getAttackTarget());
			}
			break;
		case 1: //past
			att.applyModifier(mod);
			att2.applyModifier(mod3);

			if (this.getAttackTarget() != null)
			{
				this.worldObj.setWorldTime(this.worldObj.getWorldTime() - 20);
			}

			if (this.ticksExisted % 120 == 0 && this.getHealth() < this.getMaxHealth() && !this.worldObj.isRemote)
			{
				this.heal(1.0F);
			}

			if (this.getAttackTarget() != null && this.ticksExisted % 20 == 0 && rand.nextInt(16) == 0)
			{
				int wut = rand.nextInt(5) + 3;

				for (int l = 0; l < wut; l++)
				{
					double d0 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posX) - this.posX; 
					double d1 = (MathHelper.getRandomIntegerInRange(rand, -3, 1) + this.posY) - this.posY;
					double d2 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posZ) - this.posZ; 

					EntityTimeBomb fireball = new EntityTimeBomb(this.worldObj, this, d0, d1, d2);
					fireball.setPosition(this.posX + (d0 * 0.1), this.posY + 2, this.posZ + (d2 * 0.1));
					this.worldObj.spawnEntityInWorld(fireball);
				}

				if (rand.nextInt(16) == 0)
				{
					this.getAttackTarget().addPotionEffect(new PotionEffect(Potion.confusion.id, 120 + rand.nextInt(200)));
				}

				if (rand.nextInt(4) == 0 && TragicNewConfig.allowDisorientation)
				{
					this.getAttackTarget().addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 120 + rand.nextInt(200)));
				}
			}
			break;
		case 0: //present
			this.worldObj.setWorldTime(this.worldObj.getWorldTime() + 100 - rand.nextInt(200));

			if (this.ticksExisted % 120 == 0 && this.getHealth() < this.getMaxHealth() && !this.worldObj.isRemote)
			{
				this.heal(6.0F);
			}

			if (this.ticksExisted % 5 == 0 && rand.nextBoolean())
			{
				this.pullEntities();
			}

		default:
			break;
		}

	}

	public void pullEntities()
	{
		double d0 = 12.0D;
		List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(d0, d0, d0));
		Iterator iterator = list.iterator();

		while (iterator.hasNext())
		{
			EntityLivingBase entity = (EntityLivingBase) iterator.next();

			if (entity.canEntityBeSeen(this) && !entity.equals(this))
			{					
				if (entity instanceof EntityPlayer)
				{
					if (!((EntityPlayer)entity).capabilities.isCreativeMode)
					{
						double d1 = entity.posX - this.posX;
						double d2 = entity.posZ - this.posZ;
						float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
						double d3 = 0.5D;

						entity.motionX = -d1 / (double)f2 * d3 * 0.100000011920929D + entity.motionX * 0.30000000298023224D;
						entity.motionZ = -d2 / (double)f2 * d3 * 0.100000011920929D + entity.motionZ * 0.30000000298023224D;
						entity.moveEntity(entity.motionX, entity.motionY, entity.motionZ);
					}
				}
				else
				{
					double d1 = entity.posX - this.posX;
					double d2 = entity.posZ - this.posZ;
					float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
					double d3 = 0.5D;

					entity.motionX = -d1 / (double)f2 * d3 * 0.100000011920929D + entity.motionX * 0.30000000298023224D;
					entity.motionZ = -d2 / (double)f2 * d3 * 0.100000011920929D + entity.motionZ * 0.30000000298023224D;
					entity.moveEntity(entity.motionX, entity.motionY, entity.motionZ);
				}
			}
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (!this.worldObj.isRemote)
		{			
			switch (this.dataWatcher.getWatchableObjectInt(16)) //this controls the reaction to being attacked based on time mode
			{
			case 2:
				if (par1DamageSource.isExplosion() || par1DamageSource.isProjectile())
				{
					return false;
				}

				if (par1DamageSource.isMagicDamage())
				{
					par2 *= 1.325;
				}
				break;
			case 1:
				if (rand.nextInt(4) == 0 && par1DamageSource.getEntity() != null)
				{
					Entity entity = par1DamageSource.getEntity();

					entity.attackEntityFrom(par1DamageSource, par2 * 0.735F);
				}
				this.damageStored += par2;
				par2 = 0;
			case 0:
			default:
				return super.attackEntityFrom(par1DamageSource, par2);
			}
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();

		if (super.attackEntityAsMob(par1Entity))
		{
			switch (this.dataWatcher.getWatchableObjectInt(16))
			{
			case 2:
				return par1Entity.attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this), f * 0.55F * rand.nextFloat());
			case 1:
				return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), f * 1.55F);
			case 0:
			default:
				return super.attackEntityAsMob(par1Entity);
			}
		}

		return super.attackEntityAsMob(par1Entity);
	}

	public int getTotalArmorValue()
	{
		switch(this.dataWatcher.getWatchableObjectInt(16)) //this controls the armor based on time mode
		{
		case 2:
			return 22;
		default:
			return 16;
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
				this.worldObj.spawnParticle("happyVillager", d7, d8, d9, (double)f, (double)f1, (double)f2);
			}
			this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
			this.playSound("mob.endermen.portal", 1.0F, 1.0F);
			return true;
		}
	}

	public void onCollideWithPlayer(EntityPlayer player)
	{
		timeSinceCollision = 0;

		if (player.ticksExisted % 20 == 0 && !player.capabilities.isCreativeMode)
		{
			if (playerCollisionTicks < 20)
			{
				this.playerCollisionTicks++;
			}
		}
		if (playerCollisionTicks > 0 && !player.capabilities.isCreativeMode)
		{
			if (player.ticksExisted % playerCollisionTicks == 0)
			{
				player.attackEntityFrom(DamageSource.outOfWorld, 0.5F);
			}
		}
	}

}
