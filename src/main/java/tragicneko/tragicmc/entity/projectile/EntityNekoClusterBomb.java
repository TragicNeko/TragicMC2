package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNekoClusterBomb extends EntityThrowable {

	private int groundTicks;
	private int airTicks;

	public EntityNekoClusterBomb(World world) {
		super(world);
	}

	public EntityNekoClusterBomb(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityNekoClusterBomb(World world, double par2, double par4, double par6) {
		super(world, par2, par4, par6);
	}

	@Override
	protected float getGravityVelocity() {
		return inGround ? 0.0F : 0.03F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (mop.entityHit != null && !inGround) 
		{			
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 1.0F);
		}
		else 
		{
			this.inGround = true;
		}
	}

	public void onUpdate()
	{
		super.onUpdate();

		if (this.isInWater())
		{
			for (int l = 0; l < 5; ++l) {
				worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			}
			this.worldObj.playSoundAtEntity(this, "random.fizz", 0.4F, 0.4F);
			this.setDead();
		}

		if (this.inGround)
		{
			groundTicks++;
		}
		else
		{
			airTicks++;
		}

		if (this.airTicks >= 40)
		{
			this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);

			for (int l = 0; l < 15; l++)
			{
				double d0 = MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posX; 
				double d1 = MathHelper.getRandomIntegerInRange(rand, 0, 3) + this.posY;
				double d2 = MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posZ; 

				if (this.getThrower() != null && !this.worldObj.isRemote)
				{
					EntityNekoMiniBomb bomb = new EntityNekoMiniBomb(this.worldObj, this.getThrower());

					bomb.setPosition(d0, d1, d2);
					this.worldObj.spawnEntityInWorld(bomb);
				}
			}

			boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

			if (flag)
			{
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() + 2.0F, flag);
			}

			this.setDead();
		}

		if (this.airTicks > 40 && !this.worldObj.isRemote)
		{
			this.setDead();
		}
		
		if (this.inGround && groundTicks >= 40)
		{			
			this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);

			for (int l = 0; l < 15; l++)
			{
				double d0 = MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posX; 
				double d1 = MathHelper.getRandomIntegerInRange(rand, 0, 3) + this.posY;
				double d2 = MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posZ; 

				if (this.getThrower() != null && !this.worldObj.isRemote)
				{
					EntityNekoMiniBomb bomb = new EntityNekoMiniBomb(this.worldObj, this.getThrower());

					bomb.setPosition(d0, d1, d2);
					this.worldObj.spawnEntityInWorld(bomb);
				}
			}

			boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

			if (flag && !this.worldObj.isRemote)
			{
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() + 2.0F, flag);
			}

			this.setDead();
		}

		if (this.groundTicks > 40 && !this.worldObj.isRemote)
		{
			this.setDead();
		}

	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
	}
}

