package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;

public class SchematicDeathCircle extends Schematic {

	public SchematicDeathCircle() {
		super(6, 11, 11);
	}

	private static Block fire = Blocks.fire;
	private static Block exCob = TragicBlocks.DarkCobblestone;
	private static Block darkCob = TragicBlocks.DarkCobblestone;
	private static Block summon = TragicBlocks.SummonBlock;
	private static Block chest = Blocks.chest;

	@Override
	public void generateStructure(int variant, World world, Random rand, int x, int y, int z) 
	{
		switch(variant)
		{
		case 0:
			generateWithoutVariation(world, rand, x, y, z);
			break;
		default:
			TragicMC.logError("There was a problem generating a Death Circle");
			break;
		}
		
		this.applyChestContents(world, rand, x, y + 1, z);
	}

	@Override
	public void generateWithoutVariation(World world, Random rand, int x, int y, int z) 
	{
		
		for (int y1 = 0; y1 < 6; y1++)
		{
			for (int x1 = 0; x1 < 11;x1++)
			{
				for (int z1 = 0; z1 < 11; z1++)
				{
					world.setBlockToAir(x + x1 - 5, y + y1, z - 5 + z1);
				}
			}
		} 

		//First layer

		//First row
		world.setBlock(x - 1, y, z + 8, darkCob);
		world.setBlock(x + 1, y, z + 8, darkCob);
		world.setBlock(x + 2, y, z + 8, darkCob);

		//Second row
		world.setBlock(x - 5, y, z + 7, darkCob);
		world.setBlock(x - 4, y, z + 7, darkCob);
		world.setBlock(x - 2, y, z + 7, darkCob);
		world.setBlock(x - 1, y, z + 7, darkCob);
		world.setBlock(x - 1, y, z + 7, darkCob);
		world.setBlock(x, y, z + 7, darkCob);
		world.setBlock(x + 1, y, z + 7, darkCob);
		world.setBlock(x + 2, y, z + 7, darkCob);
		world.setBlock(x + 3, y, z + 7, darkCob);

		//Third row
		world.setBlock(x - 4, y, z + 6, darkCob);
		world.setBlock(x - 3, y, z + 6, darkCob);
		world.setBlock(x - 2, y, z + 6, exCob, 1, 2);
		world.setBlock(x - 1, y, z + 6, darkCob);
		world.setBlock(x, y, z + 6, darkCob);
		world.setBlock(x + 1, y, z + 6, darkCob);
		world.setBlock(x + 2, y, z + 6, darkCob);
		world.setBlock(x + 3, y, z + 6, darkCob);

		//Fourth row
		world.setBlock(x - 5, y, z + 5, darkCob);
		world.setBlock(x - 4, y, z + 5, darkCob);
		world.setBlock(x - 3, y, z + 5, darkCob);
		world.setBlock(x - 2, y, z + 5, darkCob);
		world.setBlock(x - 1, y, z + 5, exCob, 1, 2);
		world.setBlock(x, y, z + 5, darkCob);
		world.setBlock(x + 1, y, z + 5, darkCob);
		world.setBlock(x + 2, y, z + 5, darkCob);

		//Fifth row
		world.setBlock(x - 10, y, z + 4, darkCob);
		world.setBlock(x - 9, y, z + 4, darkCob);
		world.setBlock(x - 6, y, z + 4, darkCob);
		world.setBlock(x - 5, y, z + 4, darkCob);
		world.setBlock(x - 4, y, z + 4, exCob, 1, 2);
		world.setBlock(x - 3, y, z + 4, exCob, 1, 2);
		world.setBlock(x - 2, y, z + 4, darkCob);
		world.setBlock(x - 1, y, z + 4, exCob, 1, 2);
		world.setBlock(x, y, z + 4, exCob, 1, 2);
		world.setBlock(x + 1, y, z + 4, exCob, 1, 2);
		world.setBlock(x + 2, y, z + 4, exCob, 1, 2);
		world.setBlock(x + 3, y, z + 4, darkCob);
		world.setBlock(x + 4, y, z + 4, darkCob);
		world.setBlock(x + 5, y, z + 4, darkCob);
		world.setBlock(x + 6, y, z + 4, darkCob);

		//Sixth row
		world.setBlock(x - 8, y, z + 3, darkCob);
		world.setBlock(x - 7, y, z + 3, darkCob);
		world.setBlock(x - 6, y, z + 3, darkCob);
		world.setBlock(x - 5, y, z + 3, exCob, 1, 2);
		world.setBlock(x - 4, y, z + 3, darkCob);
		world.setBlock(x - 3, y, z + 3, darkCob);
		world.setBlock(x - 2, y, z + 3, exCob, 1, 2);
		world.setBlock(x - 1, y, z + 3, exCob, 1, 2);
		world.setBlock(x, y, z + 3, exCob, 1, 2);
		world.setBlock(x + 1, y, z + 3, exCob, 1, 2);
		world.setBlock(x + 2, y, z + 3, exCob, 1, 2);
		world.setBlock(x + 3, y, z + 3, darkCob);
		world.setBlock(x + 4, y, z + 3, exCob, 1, 2);
		world.setBlock(x + 5, y, z + 3, darkCob);
		world.setBlock(x + 5, y, z + 3, darkCob);

		//Seventh row
		world.setBlock(x - 7, y, z + 2, darkCob);
		world.setBlock(x - 6, y, z + 2, darkCob);
		world.setBlock(x - 5, y, z + 2, darkCob);
		world.setBlock(x - 4, y, z + 2, darkCob);
		world.setBlock(x - 3, y, z + 2, exCob, 1, 2);
		world.setBlock(x - 2, y, z + 2, exCob, 1, 2);
		world.setBlock(x - 1, y, z + 2, exCob, 1, 2);
		world.setBlock(x, y, z + 2, exCob, 1, 2);
		world.setBlock(x + 1, y, z + 2, exCob, 1, 2);
		world.setBlock(x + 2, y, z + 2, exCob, 1, 2);
		world.setBlock(x + 3, y, z + 2, exCob, 1, 2);
		world.setBlock(x + 4, y, z + 2, darkCob);
		world.setBlock(x + 5, y, z + 2, exCob, 1, 2);
		world.setBlock(x + 6, y, z + 2, darkCob);
		world.setBlock(x + 7, y, z + 2, darkCob);
		world.setBlock(x + 8, y, z + 2, darkCob);
		world.setBlock(x + 9, y, z + 2, darkCob);

		//Eighth row
		world.setBlock(x - 7, y, z + 1, darkCob);
		world.setBlock(x - 6, y, z + 1, darkCob);
		world.setBlock(x - 5, y, z + 1, darkCob);
		world.setBlock(x - 4, y, z + 1, exCob, 1, 2);
		world.setBlock(x - 3, y, z + 1, exCob, 1, 2);
		world.setBlock(x - 2, y, z + 1, exCob, 1, 2);
		world.setBlock(x - 1, y, z + 1, exCob, 1, 2);
		world.setBlock(x, y, z + 1, exCob, 1, 2);
		world.setBlock(x + 1, y, z + 1, exCob, 1, 2);
		world.setBlock(x + 2, y, z + 1, exCob, 1, 2);
		world.setBlock(x + 3, y, z + 1, exCob, 1, 2);
		world.setBlock(x + 4, y, z + 1, exCob, 1, 2);
		world.setBlock(x + 5, y, z + 1, exCob, 1, 2);
		world.setBlock(x + 6, y, z + 1, darkCob);
		world.setBlock(x + 7, y, z + 1, darkCob);
		world.setBlock(x + 8, y, z + 1, darkCob);

		//Ninth row
		world.setBlock(x - 7, y, z, darkCob);
		world.setBlock(x - 6, y, z, darkCob);
		world.setBlock(x - 5, y, z, darkCob);
		world.setBlock(x - 4, y, z, exCob, 1, 2);
		world.setBlock(x - 3, y, z, exCob, 1, 2);
		world.setBlock(x - 2, y, z, exCob, 1, 2);
		world.setBlock(x - 1, y, z, exCob, 1, 2);
		world.setBlock(x, y, z, exCob, 1, 2);
		world.setBlock(x + 1, y, z, exCob, 1, 2);
		world.setBlock(x + 2, y, z, exCob, 1, 2);
		world.setBlock(x + 3, y, z, exCob, 1, 2);
		world.setBlock(x + 4, y, z, exCob, 1, 2);
		world.setBlock(x + 5, y, z, darkCob);
		world.setBlock(x + 6, y, z, exCob, 1, 2);
		world.setBlock(x + 7, y, z, darkCob);
		world.setBlock(x + 8, y, z, darkCob);

		//Tenth row
		world.setBlock(x - 8, y, z - 1, darkCob);
		world.setBlock(x - 7, y, z - 1, darkCob);
		world.setBlock(x - 6, y, z - 1, darkCob);
		world.setBlock(x - 5, y, z - 1, exCob, 1, 2);
		world.setBlock(x - 4, y, z - 1, exCob, 1, 2);
		world.setBlock(x - 3, y, z - 1, exCob, 1, 2);
		world.setBlock(x - 2, y, z - 1, exCob, 1, 2);
		world.setBlock(x - 1, y, z - 1, exCob, 1, 2);
		world.setBlock(x, y, z - 1, exCob, 1, 2);
		world.setBlock(x + 1, y, z - 1, exCob, 1, 2);
		world.setBlock(x + 2, y, z - 1, exCob, 1, 2);
		world.setBlock(x + 3, y, z - 1, exCob, 1, 2);
		world.setBlock(x + 4, y, z - 1, exCob, 1, 2);
		world.setBlock(x + 5, y, z - 1, darkCob);
		world.setBlock(x + 6, y, z - 1, darkCob);
		world.setBlock(x + 7, y, z - 1, darkCob);

		//Eleventh row
		world.setBlock(x - 7, y, z - 2, darkCob);
		world.setBlock(x - 6, y, z - 2, darkCob);
		world.setBlock(x - 5, y, z - 2, darkCob);
		world.setBlock(x - 4, y, z - 2, darkCob);
		world.setBlock(x - 3, y, z - 2, exCob, 1, 2);
		world.setBlock(x - 2, y, z - 2, exCob, 1, 2);
		world.setBlock(x - 1, y, z - 2, exCob, 1, 2);
		world.setBlock(x, y, z - 2, exCob, 1, 2);
		world.setBlock(x + 1, y, z - 2, exCob, 1, 2);
		world.setBlock(x + 2, y, z - 2, exCob, 1, 2);
		world.setBlock(x + 3, y, z - 2, exCob, 1, 2);
		world.setBlock(x + 4, y, z - 2, darkCob);
		world.setBlock(x + 5, y, z - 2, darkCob);
		world.setBlock(x + 6, y, z - 2, darkCob);
		world.setBlock(x + 7, y, z - 2, darkCob);

		//Twelfth row
		world.setBlock(x - 6, y, z - 3, darkCob);
		world.setBlock(x - 5, y, z - 3, darkCob);
		world.setBlock(x - 4, y, z - 3, darkCob);
		world.setBlock(x - 3, y, z - 3, darkCob);
		world.setBlock(x - 2, y, z - 3, exCob, 1, 2);
		world.setBlock(x - 1, y, z - 3, exCob, 1, 2);
		world.setBlock(x, y, z - 3, exCob, 1, 2);
		world.setBlock(x + 1, y, z - 3, exCob, 1, 2);
		world.setBlock(x + 2, y, z - 3, exCob, 1, 2);
		world.setBlock(x + 3, y, z - 3, darkCob);
		world.setBlock(x + 4, y, z - 3, darkCob);
		world.setBlock(x + 5, y, z - 3, darkCob);
		world.setBlock(x + 6, y, z - 3, darkCob);
		world.setBlock(x + 7, y, z - 3, darkCob);

		//Thirteenth row
		world.setBlock(x - 6, y, z - 4, darkCob);
		world.setBlock(x - 5, y, z - 4, darkCob);
		world.setBlock(x - 4, y, z - 4, darkCob);
		world.setBlock(x - 3, y, z - 4, exCob, 1, 2);
		world.setBlock(x - 2, y, z - 4, darkCob);
		world.setBlock(x - 1, y, z - 4, exCob, 1, 2);
		world.setBlock(x, y, z - 4, exCob, 1, 2);
		world.setBlock(x + 1, y, z - 4, exCob, 1, 2);
		world.setBlock(x + 2, y, z - 4, darkCob);
		world.setBlock(x + 3, y, z - 4, exCob, 1, 2);
		world.setBlock(x + 4, y, z - 4, exCob, 1, 2);
		world.setBlock(x + 5, y, z - 4, darkCob);
		world.setBlock(x + 6, y, z - 4, darkCob);

		//Fourteenth row
		world.setBlock(x - 7, y, z - 5, darkCob);
		world.setBlock(x - 6, y, z - 5, darkCob);
		world.setBlock(x - 5, y, z - 5, darkCob);
		world.setBlock(x - 4, y, z - 5, darkCob);
		world.setBlock(x - 3, y, z - 5, darkCob);
		world.setBlock(x - 2, y, z - 5, darkCob);
		world.setBlock(x - 1, y, z - 5, exCob, 1, 2);
		world.setBlock(x, y, z - 5, exCob, 1, 2);
		world.setBlock(x + 1, y, z - 5, darkCob);
		world.setBlock(x + 2, y, z - 5, darkCob);
		world.setBlock(x + 3, y, z - 5, darkCob);
		world.setBlock(x + 4, y, z - 5, darkCob);
		world.setBlock(x + 5, y, z - 5, darkCob);
		world.setBlock(x + 6, y, z - 5, darkCob);
		world.setBlock(x + 7, y, z - 5, darkCob);

		//Fifteenth row
		world.setBlock(x - 8, y, z - 6, darkCob);
		world.setBlock(x - 5, y, z - 6, darkCob);
		world.setBlock(x - 4, y, z - 6, darkCob);
		world.setBlock(x - 3, y, z - 6, darkCob);
		world.setBlock(x - 2, y, z - 6, darkCob);
		world.setBlock(x - 1, y, z - 6, exCob, 1, 2);
		world.setBlock(x, y, z - 6, darkCob);
		world.setBlock(x + 1, y, z - 6, exCob, 1, 2);
		world.setBlock(x + 2, y, z - 6, darkCob);
		world.setBlock(x + 3, y, z - 6, darkCob);
		world.setBlock(x + 4, y, z - 6, darkCob);
		world.setBlock(x + 8, y, z - 6, darkCob);

		//Sixteenth row
		world.setBlock(x - 3, y, z - 7, darkCob);
		world.setBlock(x - 2, y, z - 7, darkCob);
		world.setBlock(x - 1, y, z - 7, darkCob);
		world.setBlock(x, y, z - 7, darkCob);
		world.setBlock(x + 1, y, z - 7, darkCob);
		world.setBlock(x + 2, y, z - 7, darkCob);

		//Seventeeth row
		world.setBlock(x - 3, y, z - 8, darkCob);
		world.setBlock(x - 1, y, z - 8, darkCob);
		world.setBlock(x, y, z - 8, darkCob);
		world.setBlock(x + 1, y, z - 8, darkCob);

		//Eighteenth row
		world.setBlock(x - 4, y, z - 9, darkCob);
		world.setBlock(x + 2, y, z - 9, darkCob);		

		//Second layer
		y++;

		world.setBlock(x + 1, y, z + 6, darkCob);

		world.setBlock(x - 1, y, z + 5, exCob, 1, 2);

		world.setBlock(x - 3, y, z + 4, exCob, 1, 2);
		world.setBlock(x + 2, y, z + 4, exCob, 1, 2);

		world.setBlock(x - 1, y, z + 2, exCob, 1, 2);
		world.setBlock(x + 1, y, z + 2, exCob, 1, 2);
		world.setBlock(x + 2, y, z + 2, exCob, 1, 2);
		world.setBlock(x + 5, y, z + 2, exCob, 1, 2);

		world.setBlock(x - 7, y, z + 1, darkCob);
		world.setBlock(x - 2, y, z + 1, exCob, 1, 2);
		world.setBlock(x + 2, y, z + 1, exCob, 1, 2);

		world.setBlock(x - 8, y, z, darkCob);
		world.setBlock(x, y, z, chest, 0, 2);

		world.setBlock(x - 4, y, z - 1, exCob, 1, 2);

		world.setBlock(x - 2, y, z - 2, exCob, 1, 2);
		world.setBlock(x + 2, y, z - 2, exCob, 1, 2);
		world.setBlock(x + 5, y, z - 2, darkCob);

		world.setBlock(x - 5, y, z - 3, darkCob);
		world.setBlock(x + 1, y, z - 3, exCob, 1, 2);
		world.setBlock(x + 5, y, z - 3, darkCob);
		world.setBlock(x + 6, y, z - 3, darkCob);

		world.setBlock(x - 6, y, z - 4, darkCob);

		world.setBlock(x - 1, y, z - 5, exCob, 1, 2);
		world.setBlock(x + 3, y, z - 5, darkCob);

		world.setBlock(x, y, z - 7, darkCob);

		world.setBlock(x + 1, y, z - 8, darkCob);

		//Third layer
		y++;
		
		world.setBlock(x - 1, y, z + 5, exCob, 1, 2);

		world.setBlock(x - 3, y, z + 4, exCob, 1, 2);
		world.setBlock(x + 2, y, z + 4, exCob, 1, 2);

		world.setBlock(x - 1, y, z + 2, fire);
		world.setBlock(x + 1, y, z + 2, exCob, 1, 2);

		world.setBlock(x - 2, y, z + 1, exCob, 1, 2);
		world.setBlock(x + 2, y, z + 1, exCob, 1, 2);

		world.setBlock(x - 8, y, z, darkCob);
		world.setBlock(x, y, z, summon, 3, 2);

		world.setBlock(x - 2, y, z - 2, exCob, 1, 2);
		world.setBlock(x + 2, y, z - 2, exCob, 1, 2);
		world.setBlock(x + 5, y, z - 2, darkCob);

		world.setBlock(x - 5, y, z - 3, darkCob);
		world.setBlock(x + 6, y, z - 3, darkCob);

		world.setBlock(x - 6, y, z - 4, darkCob);

		world.setBlock(x - 1, y, z - 5, exCob, 1, 2);
		world.setBlock(x + 3, y, z - 5, fire);

		world.setBlock(x, y, z - 7, darkCob);

		world.setBlock(x + 1, y, z - 8, darkCob);
		
		//Fourth layer
		y++;

		world.setBlock(x - 3, y, z + 4, exCob, 1, 2);
		world.setBlock(x + 2, y, z + 4, fire);

		world.setBlock(x + 1, y, z + 2, exCob, 1, 2);

		world.setBlock(x - 2, y, z + 1, exCob, 1, 2);
		world.setBlock(x + 2, y, z + 1, exCob, 1, 2);

		world.setBlock(x - 8, y, z, darkCob);

		world.setBlock(x - 2, y, z - 2, exCob, 1, 2);
		world.setBlock(x + 2, y, z - 2, exCob, 1, 2);
		world.setBlock(x + 5, y, z - 2, darkCob);

		world.setBlock(x - 5, y, z - 3, darkCob);

		world.setBlock(x - 1, y, z - 5, fire);

		world.setBlock(x + 1, y, z - 8, darkCob);
		
		//Fifth layer
		y++;
		
		world.setBlock(x - 3, y, z + 4, exCob, 1, 2);

		world.setBlock(x + 2, y, z + 1, exCob, 1, 2);

		world.setBlock(x - 8, y, z, fire);

		world.setBlock(x - 2, y, z - 2, fire);
		world.setBlock(x + 2, y, z - 2, fire);
		world.setBlock(x + 5, y, z - 2, darkCob);

		world.setBlock(x - 5, y, z - 3, darkCob);

		world.setBlock(x + 1, y, z - 8, darkCob);
		
		//Sixth layer
		y++;
		
		world.setBlock(x - 3, y, z + 4, fire);

		world.setBlock(x + 2, y, z + 1, exCob, 1, 2);

		world.setBlock(x + 5, y, z - 2, fire);

		world.setBlock(x - 5, y, z - 3, fire);

		world.setBlock(x + 1, y, z - 8, fire);
		
		//Seventh layer
		y++;
		
		world.setBlock(x + 2, y, z + 1, fire);
	}

	@Override
	public void generateVariant(World world, Random rand, int x, int y, int z) {

	}

	@Override
	public void applyChestContents(World world, Random rand, int x, int y, int z) {
		
		if (world.isRemote)
		{
			return;
		}
		
		TileEntityChest tileentity = (TileEntityChest)world.getTileEntity(x, y, z);

		if (tileentity != null)
		{
			WeightedRandomChestContent.generateChestContents(rand, TragicItems.NetherStructureHook.getItems(rand), tileentity, TragicItems.NetherStructureHook.getCount(rand));
		}
		else
		{
			TragicMC.logWarning("Chest generation failed for some reason.");
		}
	}

	@Override
	public void fillMatrices() {
		// TODO Auto-generated method stub
		
	}

}
