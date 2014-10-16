package tragicneko.tragicmc.entity.projectile;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityMegaCryse;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;

public class EntityIcicle extends EntityProjectile {

	public EntityIcicle(World world) {
		super(world);
		this.setSize(0.325F, 0.325F);
	}

	public EntityIcicle(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7)
	{
		super(par1World, par2EntityLivingBase, par3, par5, par7);
	}

	protected float getMotionFactor()
	{
		return 0.865F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (this.worldObj.isRemote) return;

		if (mop.entityHit != null) 
		{			
			if (mop.entityHit instanceof EntityIcicle) return;
			
			if (mop.entityHit instanceof EntityLivingBase && !(mop.entityHit instanceof EntityYeti || mop.entityHit instanceof EntityAbomination || mop.entityHit instanceof EntityCryse || mop.entityHit instanceof EntityMegaCryse))
			{
				float f = mop.entityHit instanceof EntityBlaze ? 6.0F : 2.0F;
				mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), f);

				if (rand.nextBoolean()) ((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 0 + rand.nextInt(2)));
			}
		}

		if (mop != null) this.setDead();
	}

	@Override
	protected String getParticleString()
	{
		return "snowshovel";
	}

	public void onUpdate()
	{
		super.onUpdate();

		if (this.ticksExisted >= 60)
		{
			this.setDead();
		}
	}

}
