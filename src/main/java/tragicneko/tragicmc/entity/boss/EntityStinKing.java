
package tragicneko.tragicmc.entity.boss;

import java.util.List;

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
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.entity.projectile.EntityDarkMortor;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EntityStinKing extends EntityGreaterStin implements TragicMiniBoss {

	public EntityStinKing(World par1World) {
		super(par1World);
		this.setSize(1.7835F, 5.15F);
		this.stepHeight = 1.5F;
		this.superiorForm = null;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.226);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(20.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(2.0);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(21, Integer.valueOf(0));
	}

	public int getFiringTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	protected void setFiringTicks(int i)
	{
		this.dataWatcher.updateObject(21, i);
	}

	protected void decrementFiringTicks()
	{
		int pow = this.getFiringTicks();
		this.setFiringTicks(--pow);
	}

	public boolean isFiring()
	{
		return this.getFiringTicks() > 0;
	}

	public void onLivingUpdate()
	{		
		//this.motionX = this.motionY = this.motionZ = 0.0D;
		//this.rotationYaw = this.rotationPitch = this.rotationYawHead = 0.0F;
		
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			this.setSize(1.7835F, 5.15F);
		}
		else
		{
			if (this.isFiring())
			{
				this.decrementFiringTicks();
				if (this.isCharging()) this.setChargeTicks(0);
				if (this.isGalloping()) this.setGallopTicks(0);
				if (this.getAttackTarget() == null) this.setFiringTicks(0);
			}

			if (this.getAttackTarget() != null && this.ticksExisted % 10 == 0 && rand.nextInt(256) == 0 && TragicNewConfig.allowFear)
			{
				this.getAttackTarget().addPotionEffect(new PotionEffect(TragicPotions.Fear.id, 60 + rand.nextInt(160), rand.nextInt(4)));
			}

			if (this.getAttackTarget() != null && !this.isCharging() && !this.isFiring() && this.getDistanceToEntity(this.getAttackTarget()) >= 6.0F &&
					rand.nextInt(12) == 0 && this.ticksExisted % 10 == 0)
			{
				this.setFiringTicks(80);
			}

			if (this.isFiring() && this.getFiringTicks() % 10 == 0 && this.getAttackTarget() != null)
			{
				this.doMortorFire();
			}
		}
	}

	private void doMortorFire() {

		double d0 = this.getAttackTarget().posX - this.posX + rand.nextInt(5) - rand.nextInt(5);
		double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = this.getAttackTarget().posZ - this.posZ + rand.nextInt(5) - rand.nextInt(5);
		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.975F;

		EntityDarkMortor mortor = new EntityDarkMortor(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
		mortor.posY = this.posY + this.height + 0.5D;
		mortor.posX += d0 * 0.04335D;
		mortor.posZ += d2 * 0.04335D;
		mortor.motionY += 0.66D * f1;
		this.worldObj.spawnEntityInWorld(mortor);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 20;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.worldObj.isRemote || rand.nextInt(32) == 0) return false;
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public Class getLesserForm() {
		return EntityGreaterStin.class;
	}
}
