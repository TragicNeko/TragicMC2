package tragicneko.tragicmc.blocks.tileentity;

import net.minecraft.tileentity.TileEntity;
import tragicneko.tragicmc.worldgen.structure.Structure;
import tragicneko.tragicmc.worldgen.structure.StructureRandom;

public class TileEntityStructureSeed extends TileEntity {

	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote && this.worldObj.getTotalWorldTime() % 20L == 0L) this.growStructure();
	}

	public void growStructure()
	{
		int meta = this.getBlockMetadata();

		Structure structure = Structure.structureList[meta];
		if (structure == null) return;

		if (structure instanceof StructureRandom) ((StructureRandom) structure).utilityInt = this.worldObj.rand.nextInt(20);
		structure.generate(this.worldObj, this.worldObj.rand, this.xCoord, this.yCoord, this.zCoord); 
		//doesn't need a special case to generate from this anymore since this bypasses the checks that the structure worldgen does
	}

}
