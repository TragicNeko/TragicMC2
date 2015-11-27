package tragicneko.tragicmc.entity.projectile;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNekoRocket extends EntityProjectile {

	public EntityLivingBase target = null;
	private int ticksWithTarget;

	public EntityNekoRocket(World par1World) {
		super(par1World);
		this.setSize(1.0F, 1.0F);
	}

	public EntityNekoRocket(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7)
	{
		super(par1World, par2EntityLivingBase, par3, par5, par7);
	}

	@Override
	public float getMotionFactor()
	{
		return 0.872F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (this.worldObj.isRemote) return;

		if (mop.entityHit != null)
		{
			if (mop.entityHit == this.shootingEntity) return;
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 5.0F);
		}

		boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
		this.worldObj.createExplosion(this.shootingEntity, this.posX, this.posY, this.posZ, rand.nextFloat() + 2.0F, flag);

		this.setDead();
	}

	@Override
	public void onUpdate()
	{
		if (this.target != null && (this.target.getHealth() == 0 || this.target.isDead)) this.target = null;
		if (this.target != null && this.ticksInAir > 2) this.ticksWithTarget++;

		if (this.ticksWithTarget > 60 || this.ticksInAir > 80 || this.target != null && this.getDistanceToEntity(this.target) <= 2.0)
		{
			boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

			if (!this.worldObj.isRemote)
			{
				this.worldObj.createExplosion(this.shootingEntity, this.posX, this.posY, this.posZ, rand.nextFloat() + 2.0F, flag);
				this.setDead();
			}
		}

		super.onUpdate();

		if (this.target == null && this.ticksInAir > 2)
		{
			List<Entity> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(8.0, 8.0, 8.0));

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) != this.shootingEntity && list.get(i) instanceof EntityLivingBase && this.shootingEntity != null && this.shootingEntity.canEntityBeSeen(list.get(i)))
				{
					if (this.target == null || this.getDistanceToEntity(list.get(i)) < this.getDistanceToEntity(this.target)) this.target = (EntityLivingBase) list.get(i);
				}
			}
		}

		if (this.target != null && this.ticksInAir % 5 == 0 && this.ticksInAir > 5)
		{
			this.motionX = (target.posX - this.posX) * this.getMotionFactor();
			this.motionY = (target.posY - this.posY) * this.getMotionFactor();
			this.motionZ = (target.posZ - this.posZ) * this.getMotionFactor();

			float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			this.posX -= this.motionX / f2 * 0.0000000074505806D;
			this.posY -= this.motionY / f2 * 0.00000000074505806D;
			this.posZ -= this.motionZ / f2 * 0.0000000074505806D;
		}

		if (this.isInWater())
		{
			if (this.worldObj.isRemote)
			{
				for (int l = 0; l < 5; ++l) {
					worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
				}
				this.worldObj.playSoundAtEntity(this, "random.fizz", 0.4F, 0.4F);
			}
			else
			{
				this.setDead();
			}
		}
	}

}
