package tragicneko.tragicmc.entity.projectile;

import tragicneko.tragicmc.entity.boss.EntityMegaCryse;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityDarkEnergy extends EntityProjectile {

	public EntityDarkEnergy(World par1World)
	{
		super(par1World);
	}

	public EntityDarkEnergy(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
		super(par1World, par2EntityLivingBase, par3, par5, par7);
	}

	protected float getMotionFactor()
	{
		return 0.965F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (mop.entityHit != null && !inGround) 
		{			
			if (!(mop.entityHit instanceof EntityLivingBase)) return;

			if (!this.worldObj.isRemote)
			{
				mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 3.0F);
				((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.blindness.id, 120, 0 + rand.nextInt(2)));
			}
			
			this.setDead();
		}
	}

	@Override
	protected String getParticleString()
	{
		return "smoke";
	}
}
