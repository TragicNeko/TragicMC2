package tragicneko.tragicmc.entity.projectile;

import tragicneko.tragicmc.main.TragicItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrowingRock extends EntityThrowable {
	
	public boolean isLavaRock;

	public EntityThrowingRock(World world) {
		super(world);
		this.isLavaRock = false;
	}

	public EntityThrowingRock(World world, EntityLivingBase entity, boolean flag) {
		super(world, entity);
		this.isLavaRock = flag;
	}

	public EntityThrowingRock(World world, double par2, double par4, double par6, boolean flag) {
		super(world, par2, par4, par6);
		this.isLavaRock = flag;
	}

	@Override
	protected float getGravityVelocity() {
		return 0.65F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (!inGround) {
			for (int l = 0; l < 4; ++l) {
				worldObj.spawnParticle("crit", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			}
		}

		if (mop.entityHit != null && !inGround) 
		{
			float f = 1.0F;
			
			if (this.isLavaRock)
			{
				f = 3.0F;
				mop.entityHit.setFire(rand.nextInt(6) + 2);
			}
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), f);
		}
		
		this.setDead();
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		
	}
}

