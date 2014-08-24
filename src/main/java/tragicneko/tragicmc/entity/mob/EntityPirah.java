package tragicneko.tragicmc.entity.mob;

import java.util.UUID;

import tragicneko.tragicmc.main.TragicEntities;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
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
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPirah extends TragicMob {

	protected int airTicks;

	private static final IEntitySelector attackEntitySelector = new IEntitySelector()
	{
		public boolean isEntityApplicable(Entity par1Entity)
		{
			return par1Entity instanceof EntityLivingBase && !(par1Entity instanceof EntityPirah);
		}
	};

	public EntityPirah(World par1World) {
		super(par1World);
		this.setSize(0.35F, 0.65F);
		this.tasks.addTask(4, new EntityAIWander(this, 0.7D));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, false, attackEntitySelector));
		this.canCorrupt = false;
		this.isCorruptible = false;
		this.isChangeable = false;
	}
	
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Beast;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	public void setAir(int i){}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.45);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
	}

	public void moveEntityWithHeading(float par1, float par2)
	{
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);

		if (this.recentlyHit > 0)
		{
			int x  = 1;

			if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

				if (player.getCurrentEquippedItem() != null)
				{
					ItemStack weapon = player.getCurrentEquippedItem();
					x += EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, weapon);
				}
			}

			if (rand.nextInt(8) + x >= 4)
			{
				if (!this.isBurning())
				{
					this.entityDropItem(new ItemStack(Items.fish, rand.nextInt(x) + 1, rand.nextInt(3)), rand.nextFloat());
				}
				else
				{
					this.entityDropItem(new ItemStack(Items.cooked_fished, rand.nextInt(x) + 1, rand.nextInt(3)), rand.nextFloat());
				}
			}
		}
	}

	protected boolean canTriggerWalking()
	{
		return false;
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (!this.isInsideOfMaterial(this.getMaterial()))
		{
			this.airTicks++;

			if (this.airTicks < 0)
			{
				this.airTicks = 0;
			}

			if (this.airTicks >= 120 && airTicks % 20 == 0)
			{
				this.attackEntityFrom(DamageSource.drown, 2.0F);
			}

			this.motionX *= 0.10000000298023224D;
			this.motionZ *= 0.10000000298023224D;
			this.motionY = -0.05;
		}
		else
		{
			this.airTicks = 0;

			if (this.ticksExisted % 20 == 0 && !this.worldObj.isRemote)
			{
				this.motionY = 0.01D;
			}

			UUID modUUID = UUID.fromString("4d80f8e8-8601-4c5a-b17a-96239e9e9cbf");
			AttributeModifier mod = new AttributeModifier(modUUID, "pirahSpeedBuff", 0.2, 0);

			if (this.getAttackTarget() != null)
			{
				this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
				this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
			}
			else
			{
				this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);

				if (this.getAttackTarget() instanceof EntityPirah)
				{
					this.setTarget(null);
				}
			}

			if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && this.getDistanceToEntity(this.getAttackTarget()) < 10.0F 
					&& rand.nextInt(48) == 0)
			{
				double d0 = this.getAttackTarget().posX - this.posX;
				double d1 = this.getAttackTarget().posZ - this.posZ;
				double d2 = this.getAttackTarget().posY - this.posY;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX = d0 / (double)f2 * 1.5D * 0.200000011920929D + this.motionX * 0.20000000298023224D;
				this.motionZ = d1 / (double)f2 * 1.5D * 0.200000011920929D + this.motionZ * 0.20000000298023224D;
				this.motionY = d2 * 0.10000000298023224D;
			}

			if (this.ticksExisted % 20 == 0 && rand.nextInt(4) == 0 && !this.worldObj.isRemote)
			{
				this.motionY = 0.01;
				this.motionX = rand.nextDouble() * 0.001;
				this.motionZ = rand.nextDouble() * 0.001;
			}

			if (this.ticksExisted % 40 == 0 && rand.nextInt(8) == 0 && !this.worldObj.isRemote)
			{
				this.motionY = -0.03;
				this.motionX = -rand.nextDouble() * 0.001;
				this.motionZ = -rand.nextDouble() * 0.001;
			}
		}
	}
	
	protected Material getMaterial() {
		return Material.water;
	}

	public void fall(float par1){}

	public boolean getCanSpawnHere()
	{
		return this.posY > 35.0D && this.posY < 65.0D && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty();
	}

}
