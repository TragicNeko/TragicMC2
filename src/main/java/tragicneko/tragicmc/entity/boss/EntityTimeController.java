package tragicneko.tragicmc.entity.boss;

import static tragicneko.tragicmc.TragicNewConfig.timeControllerStats;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Sets;

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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotions;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.projectile.EntityTimeBomb;
import tragicneko.tragicmc.util.DamageHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityTimeController extends TragicBoss {

	private static AttributeModifier mod = new AttributeModifier(UUID.fromString("c6334c3a-6cf4-4755-8fe5-d1b713c1f375"), "timeControllerSpeedBuff", TragicNewConfig.modifierAmts[2], 0);
	private Set replaceableBlocks = Sets.newHashSet(new Block[] {Blocks.air, TragicBlocks.Luminescence});

	private HashMap<Integer, double[]> tracker = new HashMap();
	private float storedDamage;
	private int ticksSinceFlux;

	public EntityTimeController(World par1World) {
		super(par1World);
		this.setSize(1.245F, 3.05F);
		this.stepHeight = 2.0F;
		this.experienceValue = 240;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 16.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.isImmuneToFire = true;
		this.tracker = new HashMap();
		this.storedDamage = 0.0F;
	}

	private void updateTracker()
	{
		try
		{
			Entity entity;
			Iterator<Integer> iterator = tracker.keySet().iterator();
			int i = 0;

			while (iterator.hasNext())
			{
				entity = this.worldObj.getEntityByID(iterator.next());
				if (entity != null)
				{
					tracker.put(i++, new double[]{entity.posX, entity.posY, entity.posZ, entity.rotationPitch, entity.rotationYaw});
				}
				else
				{
					tracker.remove(i++);
				}
			}
		}
		catch (Exception e)
		{
			TragicMC.logError("There was an error updating the Time Controller's tracker.", e);
			return;
		}
	}

	private boolean addToTracker(Entity entity)
	{
		if (tracker.containsKey(entity.getEntityId()) || entity.isDead) return false;
		try
		{
			tracker.put(entity.getEntityId(), new double[] {entity.posX, entity.posY, entity.posZ, entity.rotationPitch, entity.rotationYaw});
		}
		catch (Exception e)
		{
			TragicMC.logError("There was an error adding an entity to the Time Controller's tracker.", e);
			return false;
		}
		return true;
	}

	private void addEntitiesToTracker(List<Entity> list)
	{
		Iterator<Entity> iterator = list.iterator();
		while (iterator.hasNext())
		{
			addToTracker(iterator.next());
		}
	}

	private void doQuantumLeap() {
		if (tracker.isEmpty()) return;

		Entity entity;
		Iterator<Integer> iterator = tracker.keySet().iterator();
		int id;
		double[] values;

		while (iterator.hasNext())
		{
			id = iterator.next();
			entity = this.worldObj.getEntityByID(id);
			if (entity != null && !(entity instanceof EntityTimeController))
			{
				values = tracker.get(id);
				entity.setLocationAndAngles(values[0], values[1], values[2], (float) values[3], (float) values[4]);
				entity.playSound("mob.endermen.portal", 0.4F, 1.0F);
				if (entity instanceof EntityLivingBase && rand.nextBoolean()) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 120));
			}
		}

		tracker = new HashMap();
		this.worldObj.setWorldTime(this.worldObj.getWorldTime() - 250);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0)); //leap ticks
		this.dataWatcher.addObject(17, Integer.valueOf(0)); //flux ticks
		this.dataWatcher.addObject(18, Integer.valueOf(0)); //purge ticks
		this.dataWatcher.addObject(19, Integer.valueOf(0)); //spaz ticks
	}

	public int getLeapTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	private void setLeapTicks(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	private void decrementLeapTicks()
	{
		int pow = this.getLeapTicks();
		this.setLeapTicks(--pow);
	}

	public int getFluxTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	private void setFluxTicks(int i)
	{
		this.dataWatcher.updateObject(17, i);
	}

	private void decrementFluxTicks()
	{
		int pow = this.getFluxTicks();
		this.setFluxTicks(--pow);
	}

	public int getPurgeTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	private void setPurgeTicks(int i)
	{
		this.dataWatcher.updateObject(18, i);
	}

	private void decrementPurgeTicks()
	{
		int pow = this.getPurgeTicks();
		this.setPurgeTicks(--pow);
	}

	public int getSpazTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	private void setSpazTicks(int i)
	{
		this.dataWatcher.updateObject(19, i);
	}

	private void decrementSpazTicks()
	{
		int pow = this.getSpazTicks();
		this.setSpazTicks(--pow);
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

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(timeControllerStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(timeControllerStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(timeControllerStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(timeControllerStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(timeControllerStats[4]);
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		if (!this.worldObj.isRemote && TragicNewConfig.allowMobStatueDrops && rand.nextInt(100) <= TragicNewConfig.mobStatueDropChance && this.getAllowLoot()) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, 3), 0.4F);
	}

	public void onLivingUpdate()
	{
		this.fallDistance = 0.0F;

		if (this.getLeapTicks() > 0 || this.getFluxTicks() > 0 || this.getSpazTicks() > 0) this.motionX = this.motionY = this.motionZ = 0.0D;
		for (int i = 0; i < Potion.potionTypes.length; i++)
		{
			if (Potion.potionTypes[i] != null && this.isPotionActive(i)) this.removePotionEffect(i);
		}

		super.onLivingUpdate();

		if (!this.onGround && this.motionY < 0.0D) this.motionY *= 0.68D;

		if (this.getLeapTicks() == 140) this.addEntitiesToTracker(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D)));
		if (this.getLeapTicks() == 1) this.doQuantumLeap();

		if (this.worldObj.isRemote)
		{
			if (this.getFluxTicks() > 10)
			{
				for (int i = 0; i < 32; i++)
				{
					double d7 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);
					double d8 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);
					double d9 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);

					double d0 = d7 + this.posX;
					double d1 = d8 + this.posY + this.height / 2.0D;
					double d2 = d9 + this.posZ;

					float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
					double d3 = 0.5D;

					double d4 = d0 / (double)f2 * d3 * 0.100000011920929D + d7 * 0.10000000298023224D;
					double d5 = d1 / (double)f2 * d3 * 0.100000011920929D + d8 * 0.10000000298023224D;
					double d6 = d2 / (double)f2 * d3 * 0.100000011920929D + d9 * 0.10000000298023224D;

					this.worldObj.spawnParticle("portal", d0, d1, d2, d4 * 15.5, d5 * 15.5, d6 * 15.5);
				}

				if (rand.nextInt(64) == 0)
				{
					for (int l = 0; l < 6; ++l)
					{
						this.worldObj.spawnParticle("instantSpell", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.155D), this.posY + 0.115D + rand.nextDouble(),
								this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.155D), 0.155F * this.rand.nextFloat(), 0.155F * this.rand.nextFloat(), 0.155F * this.rand.nextFloat());
					}
				}
				this.pullEntities();
			}
			else if (this.getFluxTicks() == 5)
			{
				for (int i = 0; i < 32; i++)
				{
					this.worldObj.spawnParticle("portal", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 2.255D), this.posY + this.height / 2.0D + rand.nextDouble(),
							this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 2.255D), rand.nextFloat(), 0.155F * this.rand.nextFloat(), rand.nextFloat());
				}
			}

			if (this.getLeapTicks() > 0)
			{
				for (int i = 0; i < 4; i++)
				{
					this.worldObj.spawnParticle("portal", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 2.255D), this.posY + this.height / 2.0D + rand.nextDouble(),
							this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 2.255D), rand.nextFloat(), 0.155F * this.rand.nextFloat(), rand.nextFloat());
				}
			}

			if (rand.nextInt(4) == 0 && this.getLeapTicks() == 0 && this.getFluxTicks() == 0)
			{
				this.worldObj.spawnParticle("happyVillager", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 2.255D), this.posY + this.height / 2.0D + rand.nextDouble(),
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 2.255D), rand.nextFloat(), 0.155F * this.rand.nextFloat(), rand.nextFloat());
			}
			return;
		}

		ticksSinceFlux++;
		if (this.ticksExisted % 5 == 0 && this.getHealth() < this.getMaxHealth() && this.getFluxTicks() == 0) this.heal(1.0F);

		if (this.getLeapTicks() > 0)
		{
			this.decrementLeapTicks();
			if (TragicNewConfig.allowLeadFoot) this.weighDownEntities();
		}
		
		if (this.getFluxTicks() > 0)
		{
			if (this.getLeapTicks() > 0) this.setLeapTicks(0);
			this.decrementFluxTicks();
			this.pullEntities();
			this.worldObj.setWorldTime(rand.nextInt(48) * 500);
		}
		
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
		if (this.getPurgeTicks() > 0) 
		{
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
			this.decrementPurgeTicks();
		}
		
		if (this.getSpazTicks() > 0) this.decrementSpazTicks();
		if (this.getFluxTicks() == 2) this.damageNearbyEntities();

		if (rand.nextInt(1028) == 0 && this.getLeapTicks() == 0 && this.getFluxTicks() == 0 && this.getPurgeTicks() == 0 && this.getSpazTicks() == 0) this.setLeapTicks(150);
		if (rand.nextInt(128) == 0 && ticksSinceFlux > 600 && this.getFluxTicks() == 0 && this.getLeapTicks() == 0 && this.getPurgeTicks() == 0 && this.getSpazTicks() == 0 && this.getAttackTarget() != null && this.isEntityInRange(this.getAttackTarget(), 6.0F, 12.0F))
		{
			this.storedDamage = 0.0F;
			this.setFluxTicks(200 + rand.nextInt(120));
		}
		if (rand.nextInt(64) == 0 && this.getPurgeTicks() == 0 && this.getLeapTicks() == 0 && this.getFluxTicks() == 0 && this.getSpazTicks() == 0 && this.getAttackTarget() != null && this.isEntityInRange(this.getAttackTarget(), 4.0F, 16.0F))
		{
			this.setPurgeTicks(100 + rand.nextInt(80));
		}

		if (this.getPurgeTicks() > 0 && this.ticksExisted % 20 == 0) this.damageNearbyEntities();

		if (this.getPurgeTicks() > 0)
		{
			this.setSprinting(true);
		}
		else
		{
			this.setSprinting(false);
		}

		if (this.getSpazTicks() > 0 && this.ticksExisted % 5 == 0) this.spazOut();

		int x = (int) (this.posX + rand.nextInt(2) - rand.nextInt(2));
		int y = (int) (this.posY + rand.nextInt(2) - rand.nextInt(2));
		int z = (int) (this.posZ + rand.nextInt(2) - rand.nextInt(2));
		if (replaceableBlocks.contains(worldObj.getBlock(x, y, z))) this.worldObj.setBlock(x, y, z, TragicBlocks.Luminescence);
	}

	private void weighDownEntities() {
		double d0 = 16.0D;
		List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(d0, d0, d0));
		Iterator iterator = list.iterator();

		while (iterator.hasNext())
		{
			EntityLivingBase entity = (EntityLivingBase) iterator.next();

			if (entity.canEntityBeSeen(this) && entity != this)
			{				
				entity.addPotionEffect(new PotionEffect(TragicPotions.LeadFoot.id, 60));
			}
		}
		
	}

	private void createTimeBombs() {		
		for (int i = 0; i < 2; i++)
		{
			int y1 = MathHelper.getRandomIntegerInRange(this.worldObj.rand, 6, 10);
			int x1 = MathHelper.getRandomIntegerInRange(this.worldObj.rand, -14, 14);
			int z1 = MathHelper.getRandomIntegerInRange(this.worldObj.rand, -14, 14);
			double x = this.posX + x1;
			double y = this.posY + y1;
			double z = this.posZ + z1;

			EntityTimeBomb bomb = new EntityTimeBomb(this.worldObj, this, 0.0, -0.5, 0.0);
			bomb.setPosition(x, y, z);
			bomb.motionX = bomb.motionZ = 0.0D;
			bomb.motionY = -0.1D;

			if (this.worldObj.checkNoEntityCollision(bomb.boundingBox) &&
					this.worldObj.getCollidingBoundingBoxes(bomb, bomb.boundingBox).isEmpty() &&
					!this.worldObj.isAnyLiquid(bomb.boundingBox))
			{
				this.worldObj.spawnEntityInWorld(bomb);
			}
		}

	}

	private void spazOut() {

		double d0 = 16.0D;
		List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(d0, d0, d0));
		Iterator iterator = list.iterator();

		while (iterator.hasNext())
		{
			EntityLivingBase entity = (EntityLivingBase) iterator.next();

			if (entity.canEntityBeSeen(this) && entity != this && rand.nextBoolean())
			{	
				this.teleportEnemyAway(entity, true);
			}
		}

		this.createTimeBombs();
		this.teleportRandomly();
	}

	private void damageNearbyEntities() {
		boolean flag2 = this.getPurgeTicks() > 0;
		double d0 = flag2 ? 2.0D : 16.0D;
		List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(d0, d0, d0));
		Iterator iterator = list.iterator();

		while (iterator.hasNext())
		{
			EntityLivingBase entity = (EntityLivingBase) iterator.next();

			if (entity.canEntityBeSeen(this) && entity != this)
			{				
				if (!flag2)
				{
					boolean flag = !(entity instanceof EntityPlayer) || entity instanceof EntityPlayer && !((EntityPlayer) entity).capabilities.isCreativeMode;
					if (flag) entity.attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this), this.storedDamage / list.size());
				}
				else
				{
					entity.attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this), 1.0F);
				}
			}
		}

		if (!flag2)
		{
			this.storedDamage = 0.0F;
			this.ticksSinceFlux = 0;
		}
	}

	public void pullEntities()
	{
		double d0 = 16.0D;
		List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(d0, d0, d0));
		Iterator iterator = list.iterator();

		while (iterator.hasNext())
		{
			EntityLivingBase entity = (EntityLivingBase) iterator.next();

			if (entity.canEntityBeSeen(this) && entity != this)
			{					
				if (entity instanceof EntityPlayer)
				{
					if (!((EntityPlayer)entity).capabilities.isCreativeMode && this.ticksExisted % 4 == 0)
					{
						double d1 = entity.posX - this.posX;
						double d2 = entity.posZ - this.posZ;
						double d3 = entity.posY - this.posY;
						float f2 = MathHelper.sqrt_double(d1 * d1 + d2 * d2 + d3 * d3);
						double d4 = 0.25D;

						entity.motionX = -d1 / (double)f2 * d4 * 0.200000011920929D + entity.motionX * 0.30000000298023224D;
						entity.motionZ = -d2 / (double)f2 * d4 * 0.200000011920929D + entity.motionZ * 0.30000000298023224D;
						entity.motionY = -d3 / (double)f2 * d4 * 0.100000011920929D + entity.motionZ * 0.30000000298023224D;
						entity.moveEntity(entity.motionX, entity.motionY, entity.motionZ);
					}
				}
				else
				{
					double d1 = entity.posX - this.posX;
					double d2 = entity.posZ - this.posZ;
					double d3 = entity.posY - this.posY;
					float f2 = MathHelper.sqrt_double(d1 * d1 + d2 * d2 + d3 * d3);
					double d4 = 0.5D;

					entity.motionX = -d1 / (double)f2 * d4 * 0.200000011920929D + entity.motionX * 0.30000000298023224D;
					entity.motionZ = -d2 / (double)f2 * d4 * 0.200000011920929D + entity.motionZ * 0.30000000298023224D;
					entity.motionY = -d3 / (double)f2 * d4 * 0.100000011920929D + entity.motionZ * 0.30000000298023224D;
				}
			}
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		if (this.getPurgeTicks() > 0 && par1DamageSource.isProjectile()) return false;

		if (this.getFluxTicks() > 0)
		{
			this.storedDamage += par2;
			return super.attackEntityFrom(par1DamageSource, 0.0F);
		}

		boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (result && this.getFluxTicks() == 0 && this.getLeapTicks() == 0 && this.getPurgeTicks() == 0 && rand.nextInt(8) == 0 && this.getHealth() <= this.getMaxHealth() / 2 && this.getSpazTicks() == 0)
		{
			this.setSpazTicks(120);
		}
		return result;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote || this.getFluxTicks() > 0 || this.getLeapTicks() > 0 || this.getSpazTicks() > 0) return false;

		float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
		boolean result = par1Entity.attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this), (f - 2.0F) * rand.nextFloat() + 2.0F);
		if (result && this.getPurgeTicks() > 0)
		{
			par1Entity.motionX *= 4.875F;
			par1Entity.motionZ *= 4.875F;
		}
		return result;
	}

	public int getTotalArmorValue()
	{
		return (int) timeControllerStats[5];
	}

	public void collideWithEntity(Entity entity)
	{
		super.collideWithEntity(entity);

		if (this.worldObj.isRemote) return;
		if (this.ticksExisted % 10 == 0 && entity instanceof EntityLivingBase && this.getFluxTicks() > 0) entity.attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this), 1.0F);
	}

	protected boolean teleportRandomly()
	{
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 16.0D;
		double d1 = this.posY + (double)(this.rand.nextInt(48) - 24);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 16.0D;
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

		if (this.worldObj.blockExists(i, j, k))
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

	protected boolean teleportEnemyAway(EntityLivingBase entity, boolean flag)
	{
		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;

		for (int y1 = 0; y1 < 18; y1++)
		{
			for (int z1 = -6; z1 < 7; z1++)
			{
				for (int x1 = -6; x1 < 7; x1++)
				{
					if (World.doesBlockHaveSolidTopSurface(this.worldObj, (int)this.posX + x1, (int)this.posY + y1 - 1, (int)this.posZ + z1) && rand.nextBoolean())
					{
						if (entity instanceof EntityPlayerMP)
						{
							EntityPlayerMP mp = (EntityPlayerMP) entity;

							if (mp.capabilities.isCreativeMode) return flag;

							if (mp.playerNetServerHandler.func_147362_b().isChannelOpen() && this.worldObj == mp.worldObj)
							{
								if (mp.isRiding()) mp.mountEntity(null);
								AxisAlignedBB bb = mp.boundingBox.copy();
								bb.offset(x + x1, y + y1, z + z1);

								if (this.worldObj.checkNoEntityCollision(bb) && this.worldObj.getCollidingBoundingBoxes(mp, bb).isEmpty() &&
										!this.worldObj.isAnyLiquid(bb))
								{
									mp.playerNetServerHandler.setPlayerLocation(x + x1, y + y1, z + z1, mp.rotationYaw, mp.rotationPitch);
									short short1 = 128;

									for (int l = 0; l < short1; ++l)
									{
										double d6 = (double)l / ((double)short1 - 1.0D);
										float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
										float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
										float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
										double d7 = x + ((x + x1) - x) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
										double d8 = y + ((y + y1) - y) * d6 + this.rand.nextDouble() * (double)this.height;
										double d9 = z + ((z + z1) - z) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
										this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
									}
									mp.fallDistance = 0.0F;
									this.worldObj.playSoundAtEntity(mp, "mob.endermen.portal", 0.4F, 0.4F);
									return flag;
								}
							}
						}
						else
						{
							entity.setPosition(x + x1, y + y1, z + z1);

							if (this.worldObj.checkNoEntityCollision(entity.boundingBox) &&
									this.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox).isEmpty() &&
									!this.worldObj.isAnyLiquid(entity.boundingBox))
							{
								short short1 = 128;

								for (int l = 0; l < short1; ++l)
								{
									double d6 = (double)l / ((double)short1 - 1.0D);
									float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
									float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
									float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
									double d7 = x + ((x + x1) - x) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
									double d8 = y + ((y + y1) - y) * d6 + this.rand.nextDouble() * (double)this.height;
									double d9 = z + ((z + z1) - z) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
									this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
								}

								this.worldObj.playSoundAtEntity(entity, "mob.endermen.portal", 0.4F, 0.4F);
								return flag;
							}
							else
							{
								entity.setPosition(x, y, z);
							}
						}
					}
				}
			}
		}

		return flag;
	}

	@Override
	protected void fall(float par1) {}

	@Override
	public void addPotionEffect(PotionEffect effect) {}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("leapTicks")) this.setLeapTicks(tag.getInteger("leapTicks"));
		if (tag.hasKey("fluxTicks")) this.setFluxTicks(tag.getInteger("fluxTicks"));
		if (tag.hasKey("storedDamage")) this.storedDamage = tag.getFloat("storedDamage");
		if (tag.hasKey("fluxBuffer")) this.ticksSinceFlux = tag.getInteger("fluxBuffer");
		if (tag.hasKey("purgeTicks")) this.setPurgeTicks(tag.getInteger("purgeTicks"));
		if (tag.hasKey("spazTicks")) this.setSpazTicks(tag.getInteger("spazTicks"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("leapTicks", this.getLeapTicks());
		tag.setInteger("fluxTicks", this.getFluxTicks());
		tag.setFloat("storedDamage", this.storedDamage);
		tag.setInteger("fluxBuffer", this.ticksSinceFlux);
		tag.setInteger("purgeTicks", this.getPurgeTicks());
		tag.setInteger("spazTicks", this.getSpazTicks());
	}
}
