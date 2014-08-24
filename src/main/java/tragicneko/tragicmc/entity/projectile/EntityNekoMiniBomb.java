package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNekoMiniBomb extends EntityThrowable {

	private int groundTicks;
	private int airTicks;

	public EntityNekoMiniBomb(World world) {
		super(world);
	}

	public EntityNekoMiniBomb(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityNekoMiniBomb(World world, double par2, double par4, double par6) {
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
		
		this.inGround = true;
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

		if (this.airTicks >= 20)
		{
			this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);

			boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

			if (flag && !this.worldObj.isRemote)
			{
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() + 2.0F, flag);
			}

			this.setDead();
		}

		if (this.airTicks > 20 && !this.worldObj.isRemote)
		{
			this.setDead();
		}

		if (this.inGround && groundTicks >= 20)
		{			
			this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);

			boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

			if (flag && !this.worldObj.isRemote)
			{
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() + 2.0F, flag);
			}

			this.setDead();
		}

		if (this.groundTicks > 20 && !this.worldObj.isRemote)
		{
			this.setDead();
		}

	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
	}
}

