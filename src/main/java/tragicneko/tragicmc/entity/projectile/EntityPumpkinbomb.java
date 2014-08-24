package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPumpkinbomb extends EntityThrowable {

	private int groundTicks;
	private int airTicks;

	public EntityPumpkinbomb(World world) {
		super(world);
	}

	public EntityPumpkinbomb(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityPumpkinbomb(World world, double par2, double par4, double par6) {
		super(world, par2, par4, par6);
	}

	@Override
	protected float getGravityVelocity() {
		return inGround ? 0.0F : 0.03F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (!this.inGround)
		{
			for (int l = 0; l < 6; ++l) {
				worldObj.spawnParticle("flame", posX, posY, posZ, (rand.nextGaussian() - 0.4D) * 0.05D, (rand.nextGaussian() - 0.4D) * 0.05D,
						(rand.nextGaussian() - 0.4D) * 0.05D);
			}
		}

		if (mop.entityHit != null && !inGround) 
		{			
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 1.0F);
			mop.entityHit.setFire(rand.nextInt(6) + 2);
		}

		this.motionX = (double)((float)(mop.hitVec.xCoord - this.posX));
		this.motionY = (double)((float)(mop.hitVec.yCoord - this.posY));
		this.motionZ = (double)((float)(mop.hitVec.zCoord - this.posZ)); 
		
		float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		this.posX -= this.motionX / (double) f2 * 0.05000000074505806D;
		this.posY -= this.motionY / (double) f2 * 0.05000000074505806D;
		this.posZ -= this.motionZ / (double) f2 * 0.05000000074505806D;

		this.motionX *= -0.7 + (rand.nextDouble() * (MathHelper.getRandomIntegerInRange(rand, -1, 1)));
		this.motionY *= -0.4 + (rand.nextDouble() * (MathHelper.getRandomIntegerInRange(rand, -1, 1)));
		this.motionZ *= -0.7 + (rand.nextDouble() * (MathHelper.getRandomIntegerInRange(rand, -1, 1)));

		if (motionX <= 0.025 && motionX >= -0.025)
		{
			motionX = 0.0;
		}

		if (motionY <= 0.025 && motionY >= -0.025)
		{
			motionY = 0.0;
		}

		if (motionZ <= 0.025 && motionZ >= -0.025)
		{
			motionZ = 0.0;
		}

		if (motionX == 0.0 && motionY == 0.0 && motionZ == 0.0)
		{
			this.inGround = true;
		}
	}

	public void onUpdate()
	{
		if (this.isInWater())
		{
			for (int l = 0; l < 5; ++l) {
				worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			}
			this.worldObj.playSoundAtEntity(this, "random.fizz", 0.4F, 0.4F);
			this.setDead();
		}

		if (!inGround) 
		{
			this.airTicks++;

			for (int l = 0; l < 4; ++l) {
				worldObj.spawnParticle("flame", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			}

			for (int l = 0; l < 5; ++l) {
				worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			}
		}

		if (this.inGround)
		{
			groundTicks++;
		}

		if (this.airTicks >= 60)
		{
			int random = rand.nextInt(8) + 4;
			this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
			this.worldObj.playSoundAtEntity(this, "mob.blaze.breathe", 0.4F, 0.4F);
			
			for (int l = 0; l < random; l++)
			{
				double d0 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posX) - this.posX; 
				double d1 = (MathHelper.getRandomIntegerInRange(rand, 0, 6) + this.posY) - this.posY;
				double d2 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posZ) - this.posZ; 

				if (this.getThrower() != null && !this.worldObj.isRemote)
				{
					EntitySmallFireball fireball = new EntitySmallFireball(this.worldObj, this.getThrower(), d0, d1, d2);

					fireball.setPosition(this.posX, this.posY, this.posZ);
					this.worldObj.spawnEntityInWorld(fireball);
				}
			}

			this.setDead();
		}

		if (this.airTicks > 60 && !this.worldObj.isRemote)
		{
			this.setDead();
		}
		
		if (this.inGround && groundTicks >= 20)
		{
			int random = rand.nextInt(8) + 4;

			this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
			
			for (int l = 0; l < random; l++)
			{
				double d0 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posX) - this.posX; 
				double d1 = (MathHelper.getRandomIntegerInRange(rand, 0, 6) + this.posY) - this.posY;
				double d2 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posZ) - this.posZ; 

				if (this.getThrower() != null && !this.worldObj.isRemote)
				{
					EntitySmallFireball fireball = new EntitySmallFireball(this.worldObj, this.getThrower(), d0, d1, d2);

					fireball.setPosition(this.posX, this.posY, this.posZ);
					this.worldObj.spawnEntityInWorld(fireball);
				}
			}

			this.setDead();
		}

		if (this.groundTicks > 20 && !this.worldObj.isRemote)
		{
			this.setDead();
		}

		super.onUpdate();

	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		player.setFire(rand.nextInt(4) + 2);
	}
}
