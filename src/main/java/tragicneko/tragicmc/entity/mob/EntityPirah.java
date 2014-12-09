package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicNewConfig.goldenPirahStats;
import static tragicneko.tragicmc.TragicNewConfig.pirahStats;

import java.util.UUID;

import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class EntityPirah extends TragicMob {

	public EntityPirah(World par1World) {
		super(par1World);
		this.tasks.addTask(4, new EntityAIWander(this, 0.7D));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, false, new IEntitySelector() {
			public boolean isEntityApplicable(Entity par1Entity)
			{
				return par1Entity instanceof EntityLivingBase && !(par1Entity instanceof EntityPirah);
			}
		}));
	}
	
	public boolean isMobVariant()
	{
		return this.getPirahType() == 1;
	}

	@Override
	public boolean canCorrupt()
	{
		return false;
	}
	
	public boolean canRenderOnFire()
	{
		return this.getPirahType() == 1 ? false : super.canRenderOnFire();
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Beast;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	public boolean handleWaterMovement()
	{
		if (this.isBurning()) this.extinguish();
		return false;
	}

	public void setAir(int i){}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		boolean flag = this.getTextureID() == 7;
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(flag ? goldenPirahStats[0] : pirahStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(flag ? goldenPirahStats[1] : pirahStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(flag ? goldenPirahStats[2] : pirahStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(flag ? goldenPirahStats[3] : pirahStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(flag ? goldenPirahStats[4] : pirahStats[4]);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
		this.dataWatcher.addObject(17, Integer.valueOf(0));
		this.dataWatcher.addObject(18, Integer.valueOf(0));
	}

	protected void setAirTicks(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	public int getAirTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	protected void incrementAirTicks()
	{
		int pow = this.getAirTicks();
		this.setAirTicks(++pow);
	}

	protected void decrementAirTicks()
	{
		int pow = this.getAirTicks();
		this.setAirTicks(--pow);
	}
	
	protected void setPirahType(int i)
	{
		if (i == 1) this.isImmuneToFire = true;
		this.dataWatcher.updateObject(17, i);
		
		float height = 0.515F;
		float width = 0.325F;
		
		if (this.getTextureID() == 7)
		{
			height *= 1.5F;
			width *= 1.5F;
		}
		
		if (i == 0)
		{
			this.setSize(width, height);
		}
		else
		{
			this.setSize(width * 1.225F, height * 1.225F);
		}
	}

	public int getPirahType()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}
	
	protected void setTextureID(int i)
	{
		this.dataWatcher.updateObject(18, i);
	}

	public int getTextureID()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	protected boolean canTriggerWalking()
	{
		return false;
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (this.worldObj.isRemote)
		{
			if (this.isInsideOfMaterial(getMaterial()))
			{
				//spawn bubble particles
			}
			else
			{
				//spawn splash particles
			}
			
			float w = 0.325F;
			float h = 0.515F;
			
			if (this.getTextureID() == 7)
			{
				w *= 1.5F;
				h *= 1.5F;
				
				//spawn special particles
			}
			
			if (this.getPirahType() == 0)
			{
				this.setSize(w, h);
			}
			else
			{
				this.setSize(w * 1.225F, h * 1.225F);
			}
		}
		else
		{
			if (this.isInsideOfMaterial(getMaterial()))
			{
				if (this.getAttackTarget() != null && !this.getAttackTarget().isInsideOfMaterial(getMaterial())) this.setAttackTarget(null);
				this.fallDistance = 0.0F;
				
				if (this.ticksExisted % 5 == 0)
				{
					this.motionY += ((rand.nextDouble() - rand.nextDouble()) + 0.625D) * 0.725D;
					this.motionX += ((rand.nextDouble() - rand.nextDouble())) * 0.825D;
					this.motionZ += ((rand.nextDouble() - rand.nextDouble())) * 0.825D;
					
					if (this.getAttackTarget() == null)
					{
						if (this.ticksExisted % 10 == 0)
						{
							this.motionX = (rand.nextDouble() - rand.nextDouble()) * 0.625D;
							this.motionY = (rand.nextDouble() - rand.nextDouble()) * 0.435D;
							this.motionZ = (rand.nextDouble() - rand.nextDouble()) * 0.625D;
						}
					}
					else
					{
						double d0 = this.getAttackTarget().posX - this.posX;
						double d1 = this.getAttackTarget().posY - this.posY;
						double d2 = this.getAttackTarget().posZ - this.posZ;
						float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
						this.motionX = d0 / (double)f2 * 0.75D * 0.150000011920929D + this.motionX * 0.20000000298023224D;
						this.motionY = d1 * 0.10000000298023224D;
						this.motionZ = d2 / (double)f2 * 0.75D * 0.150000011920929D + this.motionZ * 0.20000000298023224D;

						if (this.getAttackTarget().isDead) this.setAttackTarget(null);
					}
				}

				if (this.getAirTicks() < 100) this.incrementAirTicks();
			}
			else
			{
				this.setAttackTarget(null);
				if (this.getAirTicks() > 0) this.decrementAirTicks();

				if (this.ticksExisted % 20 == 0 && this.getAirTicks() == 0) this.attackEntityFrom(DamageSource.drown, 1.0F);
				if (this.ticksExisted % 4 == 0 && this.motionY <= 0.0D &&  
						World.doesBlockHaveSolidTopSurface(this.worldObj, (int) this.posX, MathHelper.ceiling_double_int(this.posY) - 1, (int) this.posZ)) this.motionY = 0.35D + rand.nextDouble() * 0.25D;
				this.motionX = (rand.nextDouble() - rand.nextDouble());
				this.motionZ = (rand.nextDouble() - rand.nextDouble());
				
				if (this.getPirahType() == 1 && this.isInsideOfMaterial(Material.water) && this.ticksExisted % 5 == 0) this.attackEntityFrom(DamageSource.drown, 1.0F);
			}
		}
	}

	public void moveEntity(double d0, double d1, double d2)
	{
		if (this.isInsideOfMaterial(getMaterial())) 
		{
			super.moveEntity(d0, d1, d2);
		}
		else
		{
			super.moveEntity(d0 * 0.315D, d1, d2 * 0.315D);
		}
	}
	
	public int getTotalArmorValue()
	{
		return (int) (this.getTextureID() == 7 ? goldenPirahStats[5] : pirahStats[5]);
	}

	protected Material getMaterial() {
		return this.getPirahType() == 0 ? Material.water : Material.lava;
	}

	public boolean getCanSpawnHere()
	{
		return this.posY > 35.0D && this.posY < 65.0D && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && this.isInsideOfMaterial(this.getMaterial());
	}

	protected void fall(float f) {
		if (this.worldObj.isRemote) return;
		if (!this.isInsideOfMaterial(getMaterial()))
		{
			this.motionX = rand.nextDouble() - rand.nextDouble();
			this.motionZ = rand.nextDouble() - rand.nextDouble();
			this.motionY = rand.nextDouble();
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("pirahType")) this.setPirahType(tag.getInteger("pirahType"));
		if (tag.hasKey("airTicks")) this.setAirTicks(tag.getInteger("airTicks"));
		if (tag.hasKey("textureID")) this.setTextureID(tag.getInteger("textureID"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("pirahType", this.getPirahType());
		tag.setInteger("airTicks", this.getAirTicks());
		tag.setInteger("textureID", this.getTextureID());
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		if (!this.worldObj.isRemote)
		{
			boolean flag = this.isInsideOfMaterial(Material.water);
			boolean flag2 = this.isInsideOfMaterial(Material.lava);
			this.setPirahType(flag ? 0 : (flag2 ? 1 : 0));
			int i = rand.nextInt(8);
			if (i == 7) i = rand.nextInt(8); //make the 7 id less common
			this.setTextureID(i);
		}
		return super.onSpawnWithEgg(data);
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

}
