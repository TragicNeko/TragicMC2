package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.archangelStats;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityArchangel extends TragicMob {

	public int courseChangeCooldown;
	public double waypointX;
	public double waypointY;
	public double waypointZ;

	public int hoverBuffer = 120;

	public EntityArchangel(World par1World) {
		super(par1World);
		this.setSize(1.625F, 2.725F);
		this.isImmuneToFire = true;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, false, EntityIre.selec));
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(archangelStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(archangelStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(archangelStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(archangelStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(archangelStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return (int) archangelStats[5];
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	@Override
	protected boolean canCorrupt()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return 15728880;
	}

	@Override
	public float getBrightness(float par1)
	{
		return 1.0F;
	}

	@Override
	public boolean canRenderOnFire()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0)); //hover/laser ticks
		this.dataWatcher.addObject(17, Integer.valueOf(0)); //target id
	}

	private void setHoverTicks(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	public int getHoverTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	private void setTargetId(int i)
	{
		this.dataWatcher.updateObject(17, i);
	}

	public int getTargetId()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.worldObj.isRemote) return false;

		boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (result)
		{
			this.courseChangeCooldown = -14;
			if (this.getHoverTicks() > 0)
			{
				this.setHoverTicks(0);
				this.hoverBuffer = 120;
			}
		}

		return result;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) par1Entity.setFire(4 + rand.nextInt(4));
		return super.attackEntityAsMob(par1Entity);
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getHoverTicks() > 0) this.motionX = this.motionY = this.motionZ = 0;		

		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			Entity entity = this.worldObj.getEntityByID(this.getTargetId());
			if (entity != null && this.getHoverTicks() > 10)
			{
				double d0 = entity.posX - this.posX;
				double d1 = entity.posY - this.posY;
				double d2 = entity.posZ - this.posZ;

				for (int l = 0; l < 4; l++)
				{
					double d3 = 0.23D * l + (rand.nextDouble() * 0.25D);
					this.worldObj.spawnParticle("crit", this.posX + d0 * d3, this.posY + d1 * d3 + 0.75D, this.posZ + d2 * d3, 0.0, 0.0, 0.0);
					if (this.getHoverTicks() <= 120) this.worldObj.spawnParticle("flame", this.posX + d0 * d3, this.posY + d1 * d3 + 0.75D, this.posZ + d2 * d3, 0.0, 0.0, 0.0);
				}

				if (this.getHoverTicks() <= 120)
				{
					for (int i = 0; i < 4; i++)
					{
						this.worldObj.spawnParticle("flame", this.posX + rand.nextDouble() - rand.nextDouble(),
								this.posY + 0.25 + rand.nextDouble() * 0.5, this.posZ + rand.nextDouble() - rand.nextDouble(), 0.0, 0.0, 0.0);
					}
				}
			}

			if (this.ticksExisted % 5 == 0 && rand.nextBoolean())
			{
				for (int l = 0; l < 2; ++l)
				{
					this.worldObj.spawnParticle("crit",
							this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
							this.posY + this.rand.nextDouble() * this.height,
							this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
							(this.rand.nextDouble() - 0.6D) * 0.1D,
							this.rand.nextDouble() * 0.1D,
							(this.rand.nextDouble() - 0.6D) * 0.1D);
				}
			}

			return;
		}

		if (this.hoverBuffer > 0) this.hoverBuffer--;

		if (this.getHoverTicks() <= 0)
		{
			double d0 = this.waypointX - this.posX;
			double d1 = this.waypointY - this.posY;
			double d2 = this.waypointZ - this.posZ;
			double d3 = d0 * d0 + d1 * d1 + d2 * d2;

			if (d3 < 1.0D || d3 > 3600.0D)
			{
				this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
				this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
				this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
			}

			if (this.waypointY - this.worldObj.getTopSolidOrLiquidBlock((int) this.posX, (int) this.posZ) >= 30) this.waypointY -= 30;

			if (this.courseChangeCooldown-- <= 0)
			{
				this.courseChangeCooldown += this.rand.nextInt(5) + 2;
				d3 = (double)MathHelper.sqrt_double(d3);

				if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
				{
					this.motionX += d0 / d3 * 0.3D;
					this.motionY += d1 / d3 * 0.3D;
					this.motionZ += d2 / d3 * 0.3D;
				}
				else
				{
					this.waypointX = this.posX;
					this.waypointY = this.posY;
					this.waypointZ = this.posZ;
				}
			}

			double d4 = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();

			if (this.getAttackTarget() != null && this.getAttackTarget().getDistanceSqToEntity(this) < d4 * d4 && this.getHoverTicks() == 0)
			{
				double d5 = this.getAttackTarget().posX - this.posX;
				double d6 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
				double d7 = this.getAttackTarget().posZ - this.posZ;
				this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0F / (float)Math.PI;

				if (this.canEntityBeSeen(this.getAttackTarget()) && this.hoverBuffer == 0) this.setHoverTicks(250);
			}
			else
			{
				this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
			}

			if (this.getAttackTarget() == null && this.ticksExisted % 20 == 0)
			{
				this.setAttackTarget((EntityLivingBase) this.worldObj.findNearestEntityWithinAABB(EntityLivingBase.class, this.boundingBox.expand(d4, d4, d4), this));
			}
		}
		else
		{	
			this.setHoverTicks(this.getHoverTicks() - 1);
			this.hoverBuffer = 80 + (int) (120 * (this.getHealth() / this.getMaxHealth()));

			if (this.getHoverTicks() > 10 && this.getHoverTicks() <= 120 && this.getAttackTarget() != null && this.canEntityBeSeen(this.getAttackTarget()))
			{
				this.getAttackTarget().attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(this), (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
			}

			if (this.getAttackTarget() == null || this.getAttackTarget().isDead || !this.canEntityBeSeen(this.getAttackTarget())) this.setHoverTicks(0);
		}

		if (this.getAttackTarget() != null && this.getAttackTarget().isDead) this.setAttackTarget(null);

		if (this.getAttackTarget() != null)
		{
			this.setTargetId(this.getAttackTarget().getEntityId());
		}
		else
		{
			this.setTargetId(0);
		}

		int x = (int) (this.posX + rand.nextInt(2) - rand.nextInt(2));
		int y = (int) (this.posY + rand.nextInt(2) - rand.nextInt(2)) + ((int) this.height * 2 / 3);
		int z = (int) (this.posZ + rand.nextInt(2) - rand.nextInt(2));
		if (EntityOverlordCore.replaceableBlocks.contains(worldObj.getBlock(x, y, z))) this.worldObj.setBlock(x, y, z, TragicBlocks.Luminescence);

		if (this.ticksExisted % 20 == 0)
		{
			TragicMC.logInfo("Waypoint coords are " + waypointX + ", " + waypointY + ", " + waypointZ);
			TragicMC.logInfo("Course change: " + this.courseChangeCooldown);
			TragicMC.logInfo("Hover ticks: " + this.getHoverTicks());
			TragicMC.logInfo("Motion is " + this.motionX + ", " + this.motionY + ", " + this.motionZ);
		}
	}

	private boolean isCourseTraversable(double p_70790_1_, double p_70790_3_, double p_70790_5_, double p_70790_7_)
	{
		double d4 = (this.waypointX - this.posX) / p_70790_7_;
		double d5 = (this.waypointY - this.posY) / p_70790_7_;
		double d6 = (this.waypointZ - this.posZ) / p_70790_7_;
		AxisAlignedBB axisalignedbb = this.boundingBox.copy();

		for (int i = 1; (double)i < p_70790_7_; ++i)
		{
			axisalignedbb.offset(d4, d5, d6);

			if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty())
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public void fall(float par1) {}

	@Override
	public void updateFallState(double par1, boolean par2) {}

	@Override
	public void moveEntityWithHeading(float strafe, float forward)
	{
		if (this.isInWater())
		{
			this.moveFlying(strafe, forward, 0.02F);
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.800000011920929D;
			this.motionY *= 0.800000011920929D;
			this.motionZ *= 0.800000011920929D;
		}
		else if (this.handleLavaMovement())
		{
			this.moveFlying(strafe, forward, 0.02F);
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.5D;
			this.motionY *= 0.5D;
			this.motionZ *= 0.5D;
		}
		else
		{
			float f2 = 0.91F;

			if (this.onGround)
			{
				f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
			}

			float f3 = 0.16277136F / (f2 * f2 * f2);
			this.moveFlying(strafe, forward, this.onGround ? 0.1F * f3 : 0.02F);
			f2 = 0.91F;

			if (this.onGround)
			{
				f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
			}

			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= (double)f2;
			this.motionY *= (double)f2;
			this.motionZ *= (double)f2;
		}

		this.prevLimbSwingAmount = this.limbSwingAmount;
		double d1 = this.posX - this.prevPosX;
		double d0 = this.posZ - this.prevPosZ;
		float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

		if (f4 > 1.0F)
		{
			f4 = 1.0F;
		}

		this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
		this.limbSwing += this.limbSwingAmount;
	}

	@Override
	public boolean isOnLadder()
	{
		return false;
	}
}
