package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPitchBlack extends EntityProjectile {

	private ItemStack stack;

	public EntityPitchBlack(World par1World)
	{
		super(par1World);
	}

	public EntityPitchBlack(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5,
			double par7) {
		super(par1World, par2EntityLivingBase, par3, par5, par7);
	}
	
	protected float getMotionFactor()
	{
		return 0.865F;
	}

	public void setStack(ItemStack stack)
	{
		this.stack = stack.copy();
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (this.worldObj.isRemote)
		{
			for (int l = 0; l < 4; ++l) 
			{
				worldObj.spawnParticle("magicCrit", posX + (rand.nextDouble() - rand.nextDouble() * 0.25D), posY + (rand.nextDouble() - rand.nextDouble() * 0.25D),
						posZ + (rand.nextDouble() - rand.nextDouble() * 0.25D), 0.0D, 0.0D, 0.0D);
			}
		}
		else 
		{
			if (mop.entityHit != null && !mop.entityHit.equals(this.shootingEntity)) 
			{						
				mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 10.0F);

				if (mop.entityHit instanceof EntityLivingBase)
				{
					((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.blindness.id, 600, 1));
				}
			}
			
			if (this.shootingEntity != null && this.stack != null)
			{
				if (mop.entityHit != null) this.setPosition(this.shootingEntity.posX + rand.nextDouble() - rand.nextDouble(), this.shootingEntity.posY + 0.4D, this.shootingEntity.posZ + rand.nextDouble() - rand.nextDouble());
				this.entityDropItem(stack, 0.4F);
			}
			
			if (mop != null) this.setDead();
		}
	}

}
