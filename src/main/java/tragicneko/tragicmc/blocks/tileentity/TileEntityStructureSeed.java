package tragicneko.tragicmc.blocks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.structure.Structure;
import tragicneko.tragicmc.worldgen.structure.StructureCorruptedSpire;

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
		if (structure instanceof StructureCorruptedSpire && this.worldObj.provider.dimensionId != TragicConfig.dimensionID)
		{
			EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord, this.yCoord, this.zCoord, 16.0);
			if (player != null) player.addChatMessage(new ChatComponentText(structure.structureName + " must be generated in the Collision!"));
		}
		if (structure.generateStructureWithVariant(this.worldObj.rand.nextInt(structure.getVariantSize()), this.worldObj, this.worldObj.rand, this.xCoord, this.yCoord, this.zCoord))
		{
			EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord, this.yCoord, this.zCoord, 16.0);
			if (player != null) player.addChatMessage(new ChatComponentText(structure.structureName + " was generated successfully!"));
		}
		else
		{
			TragicMC.logError("Something went wrong while generating a " + structure.structureName + " with a structure seed");
		}
	}

}
