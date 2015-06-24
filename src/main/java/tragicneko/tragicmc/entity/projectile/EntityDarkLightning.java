package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;

public class EntityDarkLightning extends EntityProjectile {

	public EntityDarkLightning(World par1World)
	{
		super(par1World);
		this.isImmuneToFire = true;
		this.setSize(0.325F, 0.325F);
	}

	public EntityDarkLightning(World par1World, EntityLivingBase entity, double par2, double par4, double par6) {
		super(par1World, entity, par2, par4, par6);
	}

	@Override
	public float getMotionFactor()
	{
		return 0.766736F;
	}

	@Override
	protected void onImpact(MovingObjectPosition var1) {
		if (this.worldObj.isRemote)
		{
			//spawn particles on impact with something
		}
		else
		{
			if (var1.entityHit != null && !(var1.entityHit instanceof EntityEnyvil))
			{
				if (var1.entityHit instanceof EntityDarkLightning) return;

				var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 5.0F);
				this.worldObj.spawnEntityInWorld(new EntityDirectedLightning(this.worldObj, var1.entityHit.posX, var1.entityHit.posY, var1.entityHit.posZ, this));
				if (var1.entityHit instanceof EntityLivingBase) ((EntityLivingBase) var1.entityHit).addPotionEffect(new PotionEffect(Potion.blindness.id, 120, 1));
			}
			if (var1 != null) this.setDead();
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.ticksInAir % 20 == 0 && this.ticksInAir > 10 && this.ticksInAir <= 120 && !this.isDead && !this.worldObj.isRemote)
		{
			this.worldObj.spawnEntityInWorld(new EntityDirectedLightning(this.worldObj, this.posX, this.worldObj.getTopSolidOrLiquidBlock((int) this.posX, (int) this.posZ), this.posZ, this));
		}

		if (!this.worldObj.isRemote && this.ticksExisted >= 120) this.setDead();
	}

	@Override
	protected String getParticleString()
	{
		return "witchMagic";
	}

}
