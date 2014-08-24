package tragicneko.tragicmc.entity.projectile;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNekoRocket extends EntityProjectile {

	private EntityLivingBase target = null;
	private int ticksWithTarget;

	public EntityNekoRocket(World par1World) {
		super(par1World);
		this.setSize(1.0F, 1.0F);
	}

	public EntityNekoRocket(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7)
	{
		super(par1World, par2EntityLivingBase, par3, par5, par7);
	}

	protected float getMotionFactor()
	{
		return 0.95F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (mop.entityHit != null && !inGround) 
		{			
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 5.0F);
		}

		boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

		if (!this.worldObj.isRemote)
		{
			this.worldObj.createExplosion(this.shootingEntity, this.posX, this.posY, this.posZ, rand.nextFloat() + 2.0F, flag);
		}

		this.setDead();

	}

	public void onUpdate()
	{
		if (this.target != null && this.target instanceof EntityLivingBase && ((EntityLivingBase) this.target).getHealth() == 0)
		{
			this.target = null;
		}
		
		if (this.target != null && this.ticksInAir > 5 && !this.inGround)
		{
			this.ticksWithTarget++;
		}
		
		if (this.ticksWithTarget > 60)
		{
			boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

			if (!this.worldObj.isRemote)
			{
				this.worldObj.createExplosion(this.shootingEntity, this.posX, this.posY, this.posZ, rand.nextFloat() + 2.0F, flag);
			}

			this.setDead();
		}

		super.onUpdate();

		if (this.target == null && this.ticksInAir > 5)
		{
			List<Entity> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(8.0, 8.0, 8.0));

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) != this.shootingEntity && list.get(i) instanceof EntityLivingBase && this.shootingEntity != null && this.shootingEntity.canEntityBeSeen(list.get(i)))
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
			this.posX -= this.motionX / (double) f2 * 0.0000000074505806D;
			this.posY -= this.motionY / (double) f2 * 0.00000000074505806D;
			this.posZ -= this.motionZ / (double) f2 * 0.0000000074505806D; 
		}
	}

}
