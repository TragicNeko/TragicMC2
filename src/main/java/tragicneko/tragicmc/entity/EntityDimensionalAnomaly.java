package tragicneko.tragicmc.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicPotion;

public class EntityDimensionalAnomaly extends Entity {

	public EntityDimensionalAnomaly(World par1World) {
		super(par1World);
		this.setSize(0.675F, 0.825F);
		if (!par1World.isRemote) this.setTimeToLive(150 + rand.nextInt(100));
		this.preventEntitySpawning = true;
		this.isImmuneToFire = true;
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
	public void onEntityUpdate()
	{
		super.onEntityUpdate();

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
			
			if (this.ticksExisted >= this.getTimeToLive()) this.worldObj.spawnParticle("cloud", this.posX, this.posY, this.posZ, 0D, 0D, 0D);
			return;
		}
		
		if (this.ticksExisted >= this.getTimeToLive()) this.setDead();
		if (!this.onGround)
		{
			this.motionY = -0.035D;
			this.moveEntity(0.0, -0.035, 0.0);
		}

		if (TragicConfig.allowDivinity && this.ticksExisted % 5 == 0)
		{
			List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(1.0, 1.0, 1.0));
			for (EntityLivingBase e : list)
			{
				if (e.getCreatureAttribute() != TragicEntities.Synapse || e instanceof EntityPlayer)
				{
					e.addPotionEffect(new PotionEffect(TragicPotion.Divinity.id, 200 + rand.nextInt(200)));
					this.setDead();
				}
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float par2)
	{
		return false;
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(2, Integer.valueOf(0));
	}
	
	public int getTimeToLive()
	{
		return this.dataWatcher.getWatchableObjectInt(2);
	}
	
	public void setTimeToLive(int i)
	{
		this.dataWatcher.updateObject(2, i);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound var1) {
		if (var1.hasKey("timeToLive")) this.setTimeToLive(var1.getInteger("timeToLive"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound var1) {
		var1.setInteger("timeToLive", this.getTimeToLive());
	}
}
