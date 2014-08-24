package tragicneko.tragicmc.blocks.tileentity;

import java.util.Random;

import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.storage.WorldInfo;

public class TileEntityTimeDisruptor extends TileEntity {

	public void updateEntity()
	{
		if (this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord))
		{
			WorldInfo info = MinecraftServer.getServer().worldServers[0].getWorldInfo();
			Random rand = this.worldObj.rand;
			info.setWorldTime(rand.nextInt(6000) + rand.nextInt(6000) + rand.nextInt(6000) + rand.nextInt(6000));
		}
	}
}
