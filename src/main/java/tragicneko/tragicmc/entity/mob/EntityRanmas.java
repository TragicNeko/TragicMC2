package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.ranmasStats;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;

public class EntityRanmas extends TragicMob {

	private double[] motions = new double[] {0, 0, 0};
	private int chargeTicks = 0;
	private int chargeBuffer = 120;

	public EntityRanmas(World par1World) {
		super(par1World);
		this.setSize(1.355F, 2.425F);
		this.isImmuneToFire = true;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(4, new EntityAIWatchTarget(this, 64.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, false));
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(ranmasStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(ranmasStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(ranmasStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(ranmasStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(ranmasStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return (int) ranmasStats[5];
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
	public boolean canRenderOnFire()
	{
		return false;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.worldObj.isRemote || this.chargeTicks == 0) this.motionX = this.motionY = this.motionZ = 0;
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.chargeBuffer > 0) --this.chargeBuffer;

		if (this.chargeTicks > 0)
		{
			this.chargeTicks--;
			this.chargeBuffer = 40 + (int) (60 * (this.getHealth() / this.getMaxHealth()));

			if (this.isCollidedHorizontally)
			{
				TragicMC.logInfo("Ranmas was collided");
				this.chargeTicks = 0;

				double x = this.motions[0];
				double y = this.motions[1];
				double z = this.motions[2];
				this.motions = new double[] {0, 0, 0};

				float f = MathHelper.sqrt_double(x * x + z * z + y * y) * 5.0F;
				TragicMC.logInfo("Collision value was " + f);
				if (f >= 2.0F)
				{
					if (f > 5.0F) f = 5.0F;
					this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (f / 2) * rand.nextFloat() + (f / 2), this.getMobGriefing());
					this.attackEntityFrom(DamageSource.fall, f);
				}
			}
		}
		else
		{
			if (this.getAttackTarget() != null && this.canEntityBeSeen(this.getAttackTarget()) && this.ticksExisted % 5 == 0 && rand.nextInt(8) == 0 && this.chargeBuffer == 0)
			{
				double d0 = MathHelper.clamp_double(this.getAttackTarget().posX - this.posX, -2.4D, 2.4D);
				double d1 = MathHelper.clamp_double(this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 2.0F - (this.posY + this.height / 2.0F), -2.4D, 2.4D);
				double d2 = MathHelper.clamp_double(this.getAttackTarget().posZ - this.posZ, -2.4D, 2.4D);
				double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);

				this.motions = new double[] {d0, d1, d2};
				this.chargeTicks = 30;
			}
			else if (this.getAttackTarget() == null && this.ticksExisted % 20 == 0)
			{
				double d4 = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
				this.setAttackTarget((EntityLivingBase) this.worldObj.findNearestEntityWithinAABB(EntityLivingBase.class, this.boundingBox.expand(d4, d4, d4), this));
			}
		}
		
		if (this.ticksExisted % 20 == 0) TragicMC.logWarning("Health: " + this.getHealth());
	}

	@Override
	public boolean attackEntityFrom(DamageSource src, float dmg)
	{
		boolean flag = false;
		if (this.worldObj.isRemote) return flag;
		
		if (src.getEntity() != null && src.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) src.getEntity();
			flag = player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem().getItem() == TragicItems.SwordOfJustice || player.getCurrentEquippedItem().getItem() == TragicItems.BowOfJustice);
		}
		
		if (flag) return super.attackEntityFrom(src, dmg);

		return super.attackEntityFrom(src.setDamageBypassesArmor(), Math.min(1.0F, dmg));
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;

		double x = this.motions[0];
		double y = this.motions[1];
		double z = this.motions[2];

		float f = MathHelper.sqrt_double(x * x + z * z + y * y) * 5.0F;
		if (f < 1.0F) f = 1.0F;
		if (f > 20.0F) f = 20.0F;
		boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), f * (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
		TragicMC.logInfo("Damage value was " + f);

		if (flag && this.chargeTicks > 0)
		{
			par1Entity.motionX *= 2.25D;
			par1Entity.motionZ *= 2.25D;
		}
		return flag;
	}

	@Override
	public void fall(float par1) {}

	@Override
	public void updateFallState(double par1, boolean par2) {}

	@Override
	public boolean handleWaterMovement() {
		return false;
	}

	@Override
	public boolean handleLavaMovement() {
		return false;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("chargeTicks")) this.chargeTicks = tag.getInteger("ageTicks");
		if (tag.hasKey("chargeBuffer")) this.chargeBuffer = tag.getInteger("chargeBuffer");
		if (tag.hasKey("chargeX") && tag.hasKey("chargeY") && tag.hasKey("chargeZ")) this.motions = new double[] {tag.getDouble("chargeX"), tag.getDouble("chargeY"), tag.getDouble("chargeZ")};
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("chargeTicks", this.chargeTicks);
		tag.setInteger("chargeBuffer", this.chargeBuffer);
		tag.setDouble("chargeX", this.motions[0]);
		tag.setDouble("chargeY", this.motions[1]);
		tag.setDouble("chargeZ", this.motions[2]);
	}
}
