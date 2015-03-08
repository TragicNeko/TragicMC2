package tragicneko.tragicmc.entity.projectile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.EntityDimensionalAnomaly;
import tragicneko.tragicmc.entity.miniboss.EntityAegar;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityOverlordMortor extends EntityProjectile {

	protected EntityLivingBase target = null;
	protected int ticksWithTarget;
	public boolean spawnsAnomalies = true;

	public EntityOverlordMortor(World par1World) {
		super(par1World);
		this.setSize(1.0F, 1.0F);
	}

	@Override
	protected float getMotionFactor()
	{
		return 0.75F;
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
		else
		{
			if (this.worldObj.getEntitiesWithinAABB(EntityDimensionalAnomaly.class, this.boundingBox.expand(64.0, 64.0, 64.0D)).size() < 8 && rand.nextBoolean() && this.spawnsAnomalies)
			{
				EntityDimensionalAnomaly anomoly = new EntityDimensionalAnomaly(this.worldObj);
				anomoly.setPosition(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
				this.worldObj.spawnEntityInWorld(anomoly);
			}
			else
			{
				ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(this.worldObj, 2.5, this.posX, this.posY, this.posZ);
				for (int[] coords : list)
				{
					if (World.doesBlockHaveSolidTopSurface(this.worldObj, coords[0], coords[1] - 1, coords[2]) && this.worldObj.getBlock(coords[0], coords[1],coords[2]).isAir(this.worldObj, coords[0], coords[1], coords[2]))
					{
						this.worldObj.setBlock(coords[0], coords[1], coords[2], TragicBlocks.CorruptedGas);
					}
				}
			}
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
			List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(16.0, 16.0, 16.0));

			for (EntityLivingBase e : list)
			{
				if (e != this.shootingEntity && this.shootingEntity != null && this.shootingEntity.canEntityBeSeen(e) && e.getCreatureAttribute() != TragicEntities.Synapse)
				{
					this.target = e;
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
