package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EntityPoisonBarb extends EntityProjectile {

	public EntityPoisonBarb(World world) {
		super(world);
		this.setSize(0.125F, 0.125F);
	}

	public EntityPoisonBarb(World par1World,
			EntityLivingBase par2EntityLivingBase, double par3, double par5,
			double par7) {
		super(par1World, par2EntityLivingBase, par3, par5, par7);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (this.worldObj.isRemote)
		{
			for (int l = 0; l < 4; ++l) 
			{
				worldObj.spawnParticle("witchMagic", posX + (rand.nextDouble() - rand.nextDouble() * 0.25D), posY + (rand.nextDouble() - rand.nextDouble() * 0.25D),
						posZ + (rand.nextDouble() - rand.nextDouble() * 0.25D), 0.0D, 0.0D, 0.0D);
			}
		}
		else
		{
			if (mop.entityHit != null) 
			{			
				mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 1.0F);

				if (mop.entityHit instanceof EntityLivingBase)
				{
					((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 200 + rand.nextInt(200), rand.nextInt(3)));

					if (rand.nextInt(16) == 0 && TragicNewConfig.allowStun)
					{
						((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 20 + rand.nextInt(40), rand.nextInt(2)));
					}
				}
			}
			
			if (mop != null) this.setDead();
		}		
	}
}
