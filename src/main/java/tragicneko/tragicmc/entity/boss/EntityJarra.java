package tragicneko.tragicmc.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.main.TragicEntities;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityJarra extends EntityJabba implements TragicMiniBoss {

	public EntityJarra(World par1World) {
		super(par1World);
		this.setSize(0.475F * 1.585F, 0.625F * 1.585F);
		this.stepHeight = 1.0F;
		this.experienceValue = 20;
		this.isImmuneToFire = false;
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
		return this.isBurning();
	}
	
	@Override
	protected void setJabbaType(int i)
	{
		this.dataWatcher.updateObject(17, 1);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.36);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.5);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
	}

	public void onLivingUpdate()
	{
		if (!this.worldObj.isRemote && this.isPotionActive(Potion.poison.id))
		{
			this.removePotionEffect(Potion.poison.id);
		}

		super.onLivingUpdate();
		
		if (this.worldObj.isRemote)
		{
			this.setSize(0.475F * 1.585F, 0.625F * 1.585F);
		}
	}

	@Override
	protected void doParticleEffects() {
		if (this.getAngerTicks() == 0) return;
		
		for (int l = 0; l < 3; ++l)
		{
			this.worldObj.spawnParticle("witchMagic",
					this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
					this.posY + this.rand.nextDouble() * (double)this.height,
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
					(this.rand.nextDouble() - 0.6D) * 0.1D,
					this.rand.nextDouble() * 0.1D,
					(this.rand.nextDouble() - 0.6D) * 0.1D);
		}
	}

	@Override
	protected void spawnProjectiles() 
	{
		EntityLivingBase entity = this.getAttackTarget();
		double d0 = entity.posX - this.posX;
		double d1 = entity.boundingBox.minY + (double)(entity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = entity.posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(entity)) * 0.5F;

		for (int i = 0; i < 5; ++i)
		{
			EntityPoisonBarb poisonbarb = new EntityPoisonBarb(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			poisonbarb.posX = this.posX + d0 * 0.115D;
			poisonbarb.posY = this.posY + 0.435D;
			poisonbarb.posZ = this.posZ + d0 * 0.115D;
			this.worldObj.spawnEntityInWorld(poisonbarb);

			this.setAngerTicks(this.getAngerTicks() - 50);
		}
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public Class getLesserForm() {
		return EntityJabba.class;
	}
}
