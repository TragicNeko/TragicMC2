package tragicneko.tragicmc.blocks.tileentity;

import java.util.Random;

import tragicneko.tragicmc.TragicNewConfig;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.storage.WorldInfo;

public class TileEntityTimeDisruptor extends TileEntity {

	public void updateEntity()
	{
		if (this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord) && TragicNewConfig.allowItemTimeAltering)
		{
			WorldInfo info = this.worldObj.getWorldInfo();
			Random rand = this.worldObj.rand;
			info.setWorldTime(rand.nextInt(6000) + rand.nextInt(6000) + rand.nextInt(6000) + rand.nextInt(6000));
		}
	}
}
