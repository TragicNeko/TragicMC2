package tragicneko.tragicmc.entity.mob;

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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EntityMinotaur extends TragicMob {

	public EntityMinotaur(World par1World) {
		super(par1World);
		this.setSize(0.725F, 2.575F);
		this.stepHeight = 1.0F;
		this.experienceValue = 8;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.65D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
	}

	public int getChargeTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	private void setChargeTicks(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	private void decrementChargeTicks()
	{
		int pow = this.getChargeTicks();
		this.setChargeTicks(--pow);
	}

	public boolean isCharging()
	{
		return this.getChargeTicks() > 0;
	}

	@Override
	public boolean canCorrupt()
	{
		return false;
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Beast;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(42.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.35);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.5);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.isCharging())
		{
			this.decrementChargeTicks();
			this.setSprinting(true);
		}
		else
		{
			this.setSprinting(false);
		}

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && this.getDistanceToEntity(this.getAttackTarget()) <= 8.0F 
				&& this.onGround && rand.nextInt(16) == 0 && this.onGround && !this.isCharging() && this.canEntityBeSeen(this.getAttackTarget()))
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			double d2 = this.getAttackTarget().posY - this.posY;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
			this.motionX = d0 / (double)f2 * 2.5D * 0.700000011920929D + this.motionX * 0.40000000298023224D;
			this.motionZ = d1 / (double)f2 * 2.5D * 0.700000011920929D + this.motionZ * 0.40000000298023224D;
			this.motionY = d1 / (double)f2 * 1.1D * 0.200000011920929D + this.motionZ * 0.20000000298023224D;
			this.setChargeTicks(20);
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.worldObj.isRemote) return false;

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer && !par1DamageSource.isProjectile() && !par1DamageSource.isMagicDamage())
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				if (!(player.getCurrentEquippedItem().getItem() instanceof ItemBow))
				{
					player.getCurrentEquippedItem().damageItem(rand.nextInt(3) + 1, player);
				}
			}
			else
			{
				player.attackEntityFrom(DamageSource.causeMobDamage(this), 0.5F);
			}
		}

		if (!par1DamageSource.isProjectile() && !par1DamageSource.isExplosion() && !par1DamageSource.isMagicDamage()) par2 *= 0.65F;
		if (this.isCharging()) par2 *= 0.45F;

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);
		
		if (result)
		{
			if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0)
			{
				switch(rand.nextInt(3))
				{
				default:
				case 0:
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200)));
					break;
				case 1:
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200)));
					break;
				case 2:
					if (TragicNewConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, rand.nextInt(200)));
					break;
				}
			}

			if (this.isCharging())
			{
				par1Entity.motionX *= 1.2000000059604645D;
				par1Entity.motionZ *= 1.2D;
				par1Entity.motionY += 0.3D;
			}
		}
		
		return result;
	}

	public int getTotalArmorValue()
	{
		return 6;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("chargeTicks")) this.setChargeTicks(this.getChargeTicks());
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("chargeTicks", this.getChargeTicks());
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

}
