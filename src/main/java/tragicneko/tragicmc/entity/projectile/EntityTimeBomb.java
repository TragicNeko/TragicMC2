package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityTimeController;

public class EntityTimeBomb extends EntityProjectile {
	
	public EntityTimeBomb(World par1World)
	{
		super(par1World);
	}

	public EntityTimeBomb(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
		super(par1World, par2EntityLivingBase, par3, par5, par7);
		this.setSize(1.0F, 1.0F);
	}

	protected float getMotionFactor()
	{
		return 0.766736F;
	}

	@Override
	protected void onImpact(MovingObjectPosition var1) {
		if(var1 != null)
		{
			if (var1.typeOfHit == MovingObjectType.BLOCK)
			{
				int x = var1.blockX;
				int y = var1.blockY;
				int z = var1.blockZ;
				this.setDead();

				EntityTimeDisruption ent = new EntityTimeDisruption(this.worldObj);
				ent.posX = x;
				ent.posY = y;
				ent.posZ = z;
				this.worldObj.spawnEntityInWorld(ent);
			}
			else
			{
				if (var1.entityHit instanceof EntityTimeController || !(var1.entityHit instanceof EntityLivingBase)) return;
				
				var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 1.0F);
				double x = var1.entityHit.posX;
				double y = var1.entityHit.posY;
				double z = var1.entityHit.posZ;
				this.setDead();

				EntityTimeDisruption ent = new EntityTimeDisruption(this.worldObj);
				ent.posX = x;
				ent.posY = y;
				ent.posZ = z;
				this.worldObj.spawnEntityInWorld(ent);
			}
		}
	}

}
