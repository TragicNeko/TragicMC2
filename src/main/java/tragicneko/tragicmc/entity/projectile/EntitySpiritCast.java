package tragicneko.tragicmc.entity.projectile;

import java.util.List;

import tragicneko.tragicmc.util.DamageHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySpiritCast extends EntityProjectile {

	public EntitySpiritCast(World par1World) {
		super(par1World);
		this.setSize(0.465F, 0.465F);
	}

	public EntitySpiritCast(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7)
	{
		super(par1World, par2EntityLivingBase, par3, par5, par7);
	}

	protected float getMotionFactor()
	{
		return 0.812F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (mop.entityHit != null && this.shootingEntity != null) 
		{			
			if (mop.entityHit instanceof EntityLivingBase)
			{
				mop.entityHit.attackEntityFrom(DamageHelper.causeSuffocationDamageFromMob(this.shootingEntity), 2.5F);
			}
		}
	}

	public void onUpdate()
	{
		super.onUpdate();

		if (this.ticksExisted >= 60)
		{
			this.setDead();
		}
	}
	
	@Override
	protected String getParticleString()
	{
		return "crit";
	}

}
