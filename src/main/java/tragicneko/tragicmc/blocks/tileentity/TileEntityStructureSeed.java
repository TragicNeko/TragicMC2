package tragicneko.tragicmc.blocks.tileentity;

import tragicneko.tragicmc.worldgen.structure.Structure;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStructureSeed extends TileEntity {

	@Override
	public void updateEntity()
	{
		if (this.worldObj.getTotalWorldTime() % 20L == 0L) this.growStructure();
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

		if (meta == 7)
		{
			id = 3;
			variant2 = this.worldObj.rand.nextInt(20);
		}
		
		if (meta == 8)
		{
			id = 4;
		}
		
		if (meta == 9)
		{
			id = 5;
		}
		
		if (meta == 10)
		{
			id = 6;
		}
		
		if (meta == 11) return;

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
