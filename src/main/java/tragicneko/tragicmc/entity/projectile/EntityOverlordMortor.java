package tragicneko.tragicmc.entity.projectile;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.EntityDimensionalAnomaly;
import tragicneko.tragicmc.entity.miniboss.EntityAegar;

public class EntityOverlordMortor extends EntityProjectile {

	protected EntityLivingBase target = null;
	protected int ticksWithTarget;

	public EntityOverlordMortor(World par1World) {
		super(par1World);
		this.setSize(1.0F, 1.0F);
	}

	public EntityOverlordMortor(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7)
	{
		super(par1World, par2EntityLivingBase, par3, par5, par7);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (mop == null || this.worldObj.isRemote) return;

		if (mop.entityHit != null) 
		{			
			if (mop.entityHit == this.shootingEntity && this.shootingEntity != null) return;
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 10.0F);

			boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
			this.worldObj.createExplosion(this.shootingEntity != null ? this.shootingEntity : this, this.posX, this.posY, this.posZ, rand.nextFloat() * 3.0F + 2.0F, flag);
		}
		else if (this.worldObj.getEntitiesWithinAABB(EntityDimensionalAnomaly.class, this.boundingBox.expand(64.0, 64.0, 64.0D)).size() < 8)
		{
			EntityDimensionalAnomaly anomoly = new EntityDimensionalAnomaly(this.worldObj);
			anomoly.setPosition(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
			this.worldObj.spawnEntityInWorld(anomoly);
		}

		this.setDead();
	}

	@Override
	public void onUpdate()
	{
		if (this.target != null && (this.target.getHealth() == 0 || this.target.isDead))
		{
			this.target = null;
		}

		if (this.target != null && this.ticksInAir > 5)
		{
			this.ticksWithTarget++;
		}

		if (this.ticksWithTarget > 60 && !this.worldObj.isRemote)
		{
			if (rand.nextInt(4) == 0 && this.worldObj.getEntitiesWithinAABB(EntityDimensionalAnomaly.class, this.boundingBox.expand(64.0, 64.0, 64.0D)).size() < 8)
			{
				EntityDimensionalAnomaly anomoly = new EntityDimensionalAnomaly(this.worldObj);
				anomoly.setPosition(this.posX, this.posY, this.posZ);
				this.worldObj.spawnEntityInWorld(anomoly);
			}
			
			this.setDead();
		}

		super.onUpdate();

		if (this.target == null && this.ticksInAir > 5)
		{
			List<Entity> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(16.0, 16.0, 16.0));

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) != this.shootingEntity && list.get(i) instanceof EntityLivingBase && this.shootingEntity != null && this.shootingEntity.canEntityBeSeen(list.get(i)) && !(list.get(i) instanceof EntityAegar))
				{
					this.target = (EntityLivingBase) list.get(i);
					break;
				}
			}
		}

		if (this.target != null && this.ticksInAir % 5 == 0 && this.ticksInAir > 5)
		{
			this.motionX = (target.posX - this.posX) * 0.05D;
			this.motionY = (target.posY - this.posY) * 0.05D;
			this.motionZ = (target.posZ - this.posZ) * 0.05D;

			float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			this.posX -= this.motionX / f2 * 0.0000000074505806D;
			this.posY -= this.motionY / f2 * 0.00000000074505806D;
			this.posZ -= this.motionZ / f2 * 0.0000000074505806D; 
		}
	}

	@Override
	protected String getParticleString()
	{
		return "crit";
	}
}
