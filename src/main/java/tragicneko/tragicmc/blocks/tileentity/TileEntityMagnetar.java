package tragicneko.tragicmc.blocks.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;

public class TileEntityMagnetar extends TileEntity {

	public TileEntityMagnetar()
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
			List list = this.worldObj.getEntitiesWithinAABB(EntityItem.class, axisalignedbb);
			Iterator iterator = list.iterator();

			EntityItem item;

			while (iterator.hasNext())
			{
				item = (EntityItem) iterator.next();

				if (this.getDistanceFrom(item.posX, item.posY, item.posZ) >= 2.0 && this.worldObj.rand.nextInt(6) == 0)
				{
					double d1 = item.posX - this.xCoord;
					double d2 = item.posZ - this.zCoord;
					float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
					double d3 = 0.5D;

					item.motionX = -d1 / (double)f2 * d3 * 0.800000011920929D + item.motionX * 0.60000000298023224D;
					item.motionZ = -d2 / (double)f2 * d3 * 0.800000011920929D + item.motionZ * 0.60000000298023224D;
					item.motionY += 0.15;
				}

			}

		}

	}
}
