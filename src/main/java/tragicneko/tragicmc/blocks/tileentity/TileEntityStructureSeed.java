package tragicneko.tragicmc.blocks.tileentity;

import java.util.Random;

import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.worldgen.Structure;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStructureSeed extends TileEntity {

	public void updateEntity()
	{
		if (this.worldObj.getTotalWorldTime() % 20L == 0L)
		{
			this.growStructure();
		}
	}

	public void growStructure()
	{
		int id = 0; //This is the schematic id
		int meta = this.getBlockMetadata();
		int variant2 = 0;

		if (meta == 0)
		{
			id = 0;
		}

		if (meta >= 1 && meta < 5 || meta == 6)
		{
			id = 1;
		}

		if (meta == 5)
		{
			id = 2;
		}

		if (meta >= 7 && meta <= 11)
		{
			id = 3;

			switch(meta)
			{
			case 8:
				variant2 = 1;
				break;
			case 9:
				variant2 = 2;
				break;
			case 10:
				variant2 = 5;
				break;
			case 11:
				variant2 = 7;
				break;
			case 7:
			default:
				variant2 = 20;
				break;
			}
		}
		
		if (meta == 12)
		{
			id = 4;
		}
		
		if (meta == 13)
		{
			id = 5;
		}
		
		if (meta == 14)
		{
			id = 6;
		}

		Structure structure = null;

		if (id != 3) //Changes constructor based solely on if it is an obsidian cavern, which has an extra parameter due to it's randomized nature
		{
			structure = new Structure(id, true);
		}
		else
		{
			structure = new Structure(id, true, variant2);
		}

		if (structure.hasVariants())
		{
			int x = 0;

			switch(meta) //This deals with utilizing the proper variant of a structure schematic to match with the structure seed, therefore multiple could use the same variant id
			{
			case 2:
				x = 2;
				break;
			case 3:
				x = 1;
				break;
			case 4:
				x = 3;
				break;
			case 6:
				x = 4;
				break;
			case 7:
				x = 100;
				break;
			case 8:
				x = 1;
				break;
			case 9:
				x = 4;
				break;
			case 10:
				x = 0;
				break;
			case 11:
				x = 3;
				break;
			default:
				x = 0;
				break;
			}

			structure.generateStructureWithVariant(x, this.worldObj, this.worldObj.rand, this.xCoord, this.yCoord, this.zCoord);
			
		}
		else
		{		
			structure.generateStructure(this.worldObj, this.worldObj.rand, this.xCoord, this.yCoord, this.zCoord);
		}				
	}

}
