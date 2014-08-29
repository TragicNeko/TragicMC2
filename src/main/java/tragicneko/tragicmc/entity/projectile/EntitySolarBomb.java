package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityApis;

public class EntitySolarBomb extends EntityProjectile {

	public EntitySolarBomb(World par1World) {
		super(par1World);
		this.setSize(1.0F, 1.0F);
		this.isImmuneToFire = true;
	}

	public EntitySolarBomb(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7)
	{
		super(par1World, par2EntityLivingBase, par3, par5, par7);
	}

	protected float getMotionFactor()
	{
		return 0.90F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (mop.entityHit != null && !inGround && !(mop.entityHit instanceof EntityApis))
		{	
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 5.0F);
			mop.entityHit.setFire(8 + rand.nextInt(16));
		}

		this.setDead();
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		
		if (this.ticksInAir % 10 == 0 && !this.inGround && !this.isDead)
		{
			for (int i = 0; i < 4; i++)
			{
				double d0 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posX) - this.posX; 
				double d1 = (MathHelper.getRandomIntegerInRange(rand, - 4, 4) + this.posY) - this.posY;
				double d2 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posZ) - this.posZ; 

				if (this.shootingEntity != null && !this.worldObj.isRemote)
				{
					EntitySmallFireball fireball = new EntitySmallFireball(this.worldObj, this.shootingEntity, d0, d1, d2);

					fireball.setPosition(this.posX, this.posY, this.posZ);
					this.worldObj.spawnEntityInWorld(fireball);
				}
			}
		}
	}
}
