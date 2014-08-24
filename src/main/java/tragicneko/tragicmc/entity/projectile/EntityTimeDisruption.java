package tragicneko.tragicmc.entity.projectile;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityTimeDisruption extends Entity {

	private int playerCollisionTicks;
	private int reach;

	private int timeToLive;

	public EntityTimeDisruption(World par1World) {
		super(par1World);
		this.setSize(0.4F, 1.0F);
		this.reach = 1;
		this.timeToLive = rand.nextInt(800);
		this.playerCollisionTicks = 0;
	}

	public void onUpdate()
	{
		super.onUpdate();

		if (this.ticksExisted % 10 == 0 && !this.worldObj.isRemote)
		{
			double d0 = 8.0D * reach;
			List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(d0, d0, d0));
			Iterator iterator = list.iterator();

			while (iterator.hasNext())
			{
				EntityLivingBase entity = (EntityLivingBase) iterator.next();

				if (!(entity instanceof TragicBoss))
				{					
					double d1 = entity.posX - this.posX;
					double d2 = entity.posZ - this.posZ;
					float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
					double d3 = 0.5D;

					entity.motionX = -d1 / (double)f2 * d3 * 0.800000011920929D + entity.motionX * 0.60000000298023224D;
					entity.motionZ = -d2 / (double)f2 * d3 * 0.800000011920929D + entity.motionZ * 0.60000000298023224D;
					entity.motionY += 0.15;
				}
			}
		}

		if (this.ticksExisted >= 400 + timeToLive)
		{
			this.setDead();
		}

		if (this.worldObj.isRemote && rand.nextBoolean())
		{
			for (int l = 0; l < 3; ++l)
			{
				double d6 = (double)((float)this.posX + rand.nextFloat());
				double d1 = (double)((float)this.posX + rand.nextFloat());
				d6 = (double)((float)this.posY + rand.nextFloat());
				double d3 = 0.0D;
				double d4 = 0.0D;
				double d5 = 0.0D;
				int i1 = rand.nextInt(2) * 2 - 1;
				int j1 = rand.nextInt(2) * 2 - 1;
				d3 = ((double)rand.nextFloat() - 0.5D) * 0.125D;
				d4 = ((double)rand.nextFloat() - 0.5D) * 0.125D;
				d5 = ((double)rand.nextFloat() - 0.5D) * 0.125D;
				double d2 = (double)this.posZ + 0.5D + 0.25D * (double)j1;
				d5 = (double)(rand.nextFloat() * 1.0F * (float)j1);
				double d0 = (double)this.posX + 0.5D + 0.25D * (double)i1;
				d3 = (double)(rand.nextFloat() * 1.0F * (float)i1);
				this.worldObj.spawnParticle("fireworksSpark", d0, d1, d2, d3, d4, d5);
			}

			for (int l = 0; l < 3; ++l)
			{
				this.worldObj.spawnParticle("portal", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.135D), this.posY + (0.135D * rand.nextDouble()),
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.135D), 0.0F, 0.115F * this.rand.nextFloat(), 0.0F);
			}
			
			if (rand.nextInt(128) == 0)
			{
				for (int l = 0; l < 10; ++l)
				{
					this.worldObj.spawnParticle("instantSpell", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
							this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.155F * this.rand.nextFloat(), 0.155F * this.rand.nextFloat(), 0.155F * this.rand.nextFloat());
				}
			}
		}
	}

	public void onStruckByLightning(EntityLightningBolt entity)
	{
		this.reach = 2;
	}

	public void onCollideWithPlayer(EntityPlayer player)
	{
		if (player.ticksExisted % 20 == 0)
		{
			if (playerCollisionTicks < 20)
			{
				this.playerCollisionTicks++;
			}
		}
		if (playerCollisionTicks > 0)
		{
			if (player.ticksExisted % playerCollisionTicks == 0)
			{
				if (this.worldObj.isRemote)
				{
					for (int l = 0; l < 10; ++l)
					{
						this.worldObj.spawnParticle("instantSpell", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
								this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.155F * this.rand.nextFloat(), 0.155F * this.rand.nextFloat(), 0.155F * this.rand.nextFloat());
					}
				}
				else
				{
					player.attackEntityFrom(DamageSource.magic, 0.5F * reach);
				}
			}
		}
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
