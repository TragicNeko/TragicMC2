package tragicneko.tragicmc.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicPotion;

public class EntityDimensionalAnomaly extends Entity {

	private int timeToLive;

	public EntityDimensionalAnomaly(World par1World) {
		super(par1World);
		this.setSize(0.475F, 0.825F);
		this.timeToLive = rand.nextInt(100);
		this.preventEntitySpawning = true;
		this.isImmuneToFire = true;
	}

	@Override
	public void applyEntityCollision(Entity entity)
	{
		if (entity.riddenByEntity != this && entity.ridingEntity != this)
		{
			double d0 = entity.posX - this.posX;
			double d1 = entity.posZ - this.posZ;
			double d2 = MathHelper.abs_max(d0, d1);

			if (d2 >= 0.009999999776482582D)
			{
				d2 = MathHelper.sqrt_double(d2);
				d0 /= d2;
				d1 /= d2;
				double d3 = 1.0D / d2;

				if (d3 > 1.0D)
				{
					d3 = 1.0D;
				}

				d0 *= d3;
				d1 *= d3;
				d0 *= 0.05000000074505806D;
				d1 *= 0.05000000074505806D;
				d0 *= 1.0F - this.entityCollisionReduction;
				d1 *= 1.0F - this.entityCollisionReduction;
				this.addVelocity(-d0, 0.0D, -d1);
				entity.addVelocity(d0, 0.0D, d1);
			}

			if (entity instanceof EntityLivingBase && !this.worldObj.isRemote)
			{
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotion.Divinity.id, 200 + rand.nextInt(200), 0)); 
				this.setDead();
			}
		}

		super.applyEntityCollision(entity);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return 15728880;
	}

	@Override
	public float getBrightness(float par1)
	{
		return 1.0F;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.worldObj.isRemote)
		{
			double d7 = rand.nextDouble() - rand.nextDouble();
			double d8 = rand.nextDouble() - rand.nextDouble();
			double d9 = rand.nextDouble() - rand.nextDouble();

			double d0 = d7 + this.posX;
			double d1 = d8 + this.posY + this.height / 2.0D;
			double d2 = d9 + this.posZ;

			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
			double d3 = 0.5D;

			double d4 = d0 / f2 * d3 * 0.200000011920929D + d7 * 0.20000000298023224D;
			double d5 = d1 / f2 * d3 * 0.200000011920929D + d8 * 0.20000000298023224D;
			double d6 = d2 / f2 * d3 * 0.200000011920929D + d9 * 0.20000000298023224D;
			
			for (int i = 0; i < 8; i++)
			{
				this.worldObj.spawnParticle("reddust", d0, d1, d2, rand.nextFloat() * 2.25F, rand.nextFloat() * 2.25F, rand.nextFloat() * 2.25F);
			}

			if (rand.nextInt(16) == 0)
			{				
				for (int i = 0; i < 4; i++)
				{
					this.worldObj.spawnParticle("cloud", d0, d1, d2, d4 * -0.5, d5 * -0.5, d6 * -0.5);
				}
			}
			return;
		}
		else
		{
			if (this.ticksExisted >= 200 + timeToLive) this.setDead();
		}

		if (!this.onGround) this.moveEntity(0.0, -0.035, 0.0);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float par2)
	{
		return false;
	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {

	}

}
