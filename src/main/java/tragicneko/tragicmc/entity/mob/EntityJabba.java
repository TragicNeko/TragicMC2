package tragicneko.tragicmc.entity.mob;

import java.util.List;
import java.util.UUID;

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
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.EntityJarra;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityJabba extends TragicMob {

	private static final UUID lowHealthDamageBoostUUID = UUID.fromString("8c159dc4-aacf-461f-b3e9-66dc9fbf6e99");
	private static final AttributeModifier lowHealthDamageBoost = new AttributeModifier(lowHealthDamageBoostUUID, "jabbaLowHealthDamageBoost", 2.5, 0);

	public EntityJabba(World par1World) {
		super(par1World);
		this.stepHeight = 1.0F;
		this.experienceValue = 8;
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setCanSwim(false);
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.65D));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected boolean canCorrupt()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
		this.dataWatcher.addObject(17, Integer.valueOf(0));
		this.dataWatcher.addObject(18, Integer.valueOf(0));
	}

	protected void setAngerTicks(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	public int getAngerTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	protected void incrementAngerTicks()
	{
		int pow = this.getAngerTicks();
		this.setAngerTicks(++pow);
	}

	protected void decrementAngerTicks()
	{
		int pow = this.getAngerTicks();
		this.setAngerTicks(--pow);
	}

	public int getJabbaType()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	protected void setJabbaType(int i)
	{
		this.dataWatcher.updateObject(17, i);
		this.isImmuneToFire = i == 0;

		if (i == 0)
		{
			this.setSize(0.475F, 0.625F);
		}
		else
		{
			this.setSize(0.475F * 0.825F, 0.625F * 0.825F);
		}
	}

	public int getAttackTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	public void setAttackTicks(int i)
	{
		this.dataWatcher.updateObject(18, i);
	}

	public void decrementAttackTicks()
	{
		int pow = this.getAttackTicks();
		this.setAttackTicks(--pow);
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
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

	public boolean canRenderOnFire()
	{
		return false;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		boolean flag = this.getJabbaType() == 0;
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(flag ? 50.0 : 40.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(flag ? 0.275 : 0.325);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(flag ? 5.5 : 4.5);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			this.doParticleEffects();

			if (this.getJabbaType() == 0)
			{
				this.setSize(0.475F, 0.625F);
			}
			else
			{
				this.setSize(0.475F * 0.825F, 0.625F * 0.825F);
			}
		}
		else
		{
			if (this.superiorForm == null && this.getJabbaType() == 0) this.superiorForm = new EntityJarra(this.worldObj);

			if (this.getAttackTicks() > 0) this.decrementAttackTicks();

			if (this.getJabbaType() == 0)
			{
				if (this.getAttackTarget() != null)
				{
					this.incrementAngerTicks();
					if (this.isCorrupted()) this.incrementAngerTicks();
				}
				else
				{
					if (this.getAngerTicks() > 0) this.decrementAngerTicks();
				}
			}
			else
			{
				this.setAngerTicks(0);
			}

			if (this.getAngerTicks() >= 400 && this.getAttackTarget() != null)
			{
				if (this.ticksExisted % 100 == 0) this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1.5F * rand.nextFloat(), false);
				if (this.ticksExisted % 40 == 0) this.spawnProjectiles();	
				if (this.getAttackTicks() == 0) this.setAttackTicks(10);
			}

			if (this.isWet()) this.attackEntityFrom(DamageSource.drown, 5.0F);

			if (this.ticksExisted % 20 == 0 && this.rand.nextInt(3) == 0)
			{
				EntityPlayer player = this.worldObj.getClosestVulnerablePlayerToEntity(this, 10.0);

				if (player != null && TragicNewConfig.allowDoom && this.canEntityBeSeen(player))
				{
					PropertyDoom doom = PropertyDoom.get(player);
					int i = this.worldObj.difficultySetting.getDifficultyId();

					if (doom != null) doom.increaseDoom(-((this.rand.nextInt(3) + 1) * i));
				}
			}
			
			if (this.getAttackTarget() == null && this.ticksExisted % 60 == 0 && rand.nextInt(32) == 0)
			{
				this.setAttackTicks(120);
			}

			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(lowHealthDamageBoost);
			if (this.getHealth() <= this.getMaxHealth() / 2) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(lowHealthDamageBoost);
		}
	}

	protected void doParticleEffects() {

		String s1 = this.getJabbaType() == 0 ? "dripLava" : "slime";

		for (int k = 0; k < 3; ++k)
		{
			this.worldObj.spawnParticle(s1,
					this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D,
					this.posY + (rand.nextDouble() * 0.15D),
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D,
					0.0, 0.0, 0.0);
		}


		if (this.getHealth() > this.getMaxHealth() / 2) return;

		String s = this.getJabbaType() == 0 ? "flame" : "slime";

		for (int l = 0; l < 3; ++l)
		{
			this.worldObj.spawnParticle(s,
					this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
					this.posY + this.rand.nextDouble() * (double)this.height,
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
					(this.rand.nextDouble() - 0.6D) * 0.1D,
					this.rand.nextDouble() * 0.1D,
					(this.rand.nextDouble() - 0.6D) * 0.1D);
		}
	}

	protected void spawnProjectiles() 
	{
		EntityLivingBase entity = this.getAttackTarget();
		double d0 = entity.posX - this.posX;
		double d1 = entity.boundingBox.minY + (double)(entity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = entity.posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(entity)) * 0.5F;

		for (int i = 0; i < 5; ++i)
		{
			EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			entitysmallfireball.posY = this.posY + 0.5D;
			this.worldObj.spawnEntityInWorld(entitysmallfireball);
			if (this.getAngerTicks() >= 50) this.setAngerTicks(this.getAngerTicks() - 50);
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.worldObj.isRemote) return false;

		if (this.getHealth() <= this.getMaxHealth() / 2) par2 /= 2;

		Boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (this.rand.nextInt(8) == 0 && this.worldObj.difficultySetting == EnumDifficulty.HARD && result)
		{
			if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer && !par1DamageSource.isProjectile() && !par1DamageSource.isMagicDamage() && !par1DamageSource.isFireDamage())
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

				if (player.getCurrentEquippedItem() != null && !player.capabilities.isCreativeMode && rand.nextBoolean())
				{
					player.dropOneItem(true);
				}
				else
				{
					if (this.rand.nextInt(4) == 0) player.setFire(4 + rand.nextInt(3));
				}

				if (!player.onGround) this.setAngerTicks(this.getAngerTicks() + 25);
			}
		}

		if (result || par1DamageSource == DamageSource.drown)
		{
			this.setAngerTicks(this.getAngerTicks() + 50);
			this.setAttackTicks(5);
		}

		return result;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);

		int i = MathHelper.clamp_int(this.worldObj.difficultySetting.getDifficultyId(), 1, 3);

		if (this.rand.nextInt(MathHelper.ceiling_double_int(9 / i)) == 0 && result)
		{
			if (par1Entity instanceof EntityLivingBase)
			{
				if (this.getJabbaType() == 0)
				{
					if (this.rand.nextInt(8) == 0)
					{
						par1Entity.setFire(2 * i);
					}
				}
				else
				{
					if (this.rand.nextInt(4) == 0)
					{
						((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 120 * i, i));
					}
				}

				if (par1Entity instanceof EntityPlayer && i >= 3 && rand.nextBoolean())
				{
					EntityPlayer player = (EntityPlayer) par1Entity;
					if (player.getCurrentEquippedItem() != null && !player.capabilities.isCreativeMode) player.dropOneItem(true);
				}
			}
		}

		if (result)
		{
			this.setAngerTicks(this.getAngerTicks() + 10);
			this.setAttackTicks(10);
		}

		return result;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("jabbaType")) this.setJabbaType(tag.getInteger("jabbaType"));
		if (tag.hasKey("angerTicks")) this.setAngerTicks(tag.getInteger("angerTicks"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("jabbaType", this.getJabbaType());
		tag.setInteger("angerTicks", this.getAngerTicks());
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
		this.setJabbaType(biome instanceof BiomeGenPaintedForest ? 1 : 0);
		return super.onSpawnWithEgg(data);
	}

	@Override
	protected boolean isChangeAllowed() {
		return TragicNewConfig.allowJarra;
	}

}
