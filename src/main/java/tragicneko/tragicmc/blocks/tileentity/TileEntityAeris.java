package tragicneko.tragicmc.blocks.tileentity;

import tragicneko.tragicmc.TragicMC;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAeris extends TileEntity {
	
	private int corruptedTicks;

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (!this.worldObj.isRemote && this.yCoord <= 50 && !this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord, this.zCoord) && this.blockMetadata < 2)
		{
			corruptedTicks++;
			
			if (this.corruptedTicks >= 3000)
			{
				this.corruptedTicks = 0;
				this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, blockMetadata + 1, 2);
				TragicMC.logInfo("Aeris block had it's meta updated to " + this.blockMetadata + 1);
				this.blockMetadata += 1;
			}
		}
	}
}
