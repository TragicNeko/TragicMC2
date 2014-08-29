package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityMegaCryse;
import tragicneko.tragicmc.entity.boss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityStarVox;

public class EntityStarShard extends EntityProjectile {
	
	public EntityStarShard(World par1World)
	{
		super(par1World);
	}

	public EntityStarShard(World par1World, EntityLivingBase entity, double par2, double par4, double par6) {
		super(par1World, entity, par2, par4, par6);
	}
	
	protected float getMotionFactor()
	{
		return 0.925F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (mop.entityHit != null && !inGround) 
		{			
			if (!(mop.entityHit instanceof EntityLivingBase)) return;
			if (mop.entityHit instanceof EntityVoxStellarum || mop.entityHit instanceof EntityStarVox || mop.entityHit instanceof EntityCryse || mop.entityHit instanceof EntityMegaCryse) return;
			
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 2.0F);			
			this.setDead();
		}
	}
	
	@Override
	protected String getParticleString()
	{
		return "crit";
	}

}
