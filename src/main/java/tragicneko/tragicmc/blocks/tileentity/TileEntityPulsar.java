package tragicneko.tragicmc.blocks.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

public class TileEntityPulsar extends TileEntity {

	public TileEntityPulsar()
	{
	}

	public void updateEntity()
	{
		if (!this.worldObj.isRemote)
		{
			long meow = 60L;

			if (this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord) && this.worldObj.getTotalWorldTime() % meow == 0)
			{
				this.engagePulse();
			}
		}
	}

	private void engagePulse() {

		if (!this.worldObj.isRemote)
		{
			double d0 = 12.0D;

			AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)).expand(d0, d0, d0);
			List list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
			Iterator iterator;
			EntityLivingBase mob;

			iterator = list.iterator();

			while (iterator.hasNext())
			{
				mob = (EntityLivingBase) iterator.next();

				if (mob instanceof EntityMob || mob instanceof EntityAnimal)
				{
					if (this.worldObj.rand.nextInt(6) == 0)
					{
						double d1 = mob.posX - this.xCoord;
						double d2 = mob.posZ - this.zCoord;
						float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
						double d3 = 5.0D;

						mob.motionX = d1 / (double)f2 * d3 * 0.800000011920929D + mob.motionX * 0.60000000298023224D;
						mob.motionZ = d2 / (double)f2 * d3 * 0.800000011920929D + mob.motionZ * 0.60000000298023224D;
						mob.motionY += 0.15;
					}
				}
			}
		}

	}

}
