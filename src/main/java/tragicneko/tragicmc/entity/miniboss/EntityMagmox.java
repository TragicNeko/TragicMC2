package tragicneko.tragicmc.entity.miniboss;

import static tragicneko.tragicmc.main.TragicNewConfig.magmoxStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.items.weapons.ItemScythe;
import tragicneko.tragicmc.main.TragicEntities;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityMagmox extends EntityTox implements TragicMiniBoss {

	public EntityMagmox(World par1World) {
		super(par1World);
		this.setSize(0.625F * 1.625F, 1.965F * 1.625F);
		this.experienceValue = 12;
		this.isImmuneToFire = true;
	}
	
	public boolean isMobVariant()
	{
		return false;
	}

	@Override
	protected void setToxType(int i)
	{
		this.dataWatcher.updateObject(17, 1);
	}

	@SideOnly(Side.CLIENT)
	public boolean canRenderOnFire()
	{
		return false;
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(magmoxStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(magmoxStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(magmoxStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(magmoxStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(magmoxStats[4]);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			this.setSize(0.625F * 1.625F, 1.965F * 1.625F);
			
			if (this.getWiggleTime() > 0 || this.isFiring() || this.getAttackTime() > 0)
			{
				this.worldObj.spawnParticle("flame",
						this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
						this.posY + this.rand.nextDouble() * (double)this.height,
						this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
						(this.rand.nextDouble() - 0.6D) * 0.1D,
						this.rand.nextDouble() * 0.1D,
						(this.rand.nextDouble() - 0.6D) * 0.1D);
			}
		}
		else
		{
			if (this.getFiringTicks() >= 20 && this.ticksExisted % 20 == 0 && this.getAttackTarget() != null && this.canEntityBeSeen(this.getAttackTarget()) &&
					this.getDistanceToEntity(this.getAttackTarget()) >= 2.0F)
			{
				double d0 = this.getAttackTarget().posX - this.posX;
				double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
				double d2 = this.getAttackTarget().posZ - this.posZ;

				float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

				EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
				fireball.posY = this.posY + (this.height * 2 / 3);
				this.worldObj.spawnEntityInWorld(fireball);
			}
		}
	}

	@Override
	protected void shootProjectiles()
	{
		double d0 = this.getAttackTarget().posX - this.posX;
		double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = this.getAttackTarget().posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.75F;

		for (int i = 0; i < 3; i++)
		{
			EntitySmallFireball fireball = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			fireball.posY = this.posY + (this.height * 2 / 3);
			this.worldObj.spawnEntityInWorld(fireball);
		}
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);

		if (result && par1Entity instanceof EntityLivingBase && rand.nextInt(4) == 0)
		{
			par1Entity.setFire(8 + rand.nextInt(8));
		}

		return result;
	}

	public int getTotalArmorValue()
	{
		return this.isFiring() ? MathHelper.floor_double(magmoxStats[5] * 0.4) : (int) magmoxStats[5];
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public Class getLesserForm() {
		return EntityTox.class;
	}
}
