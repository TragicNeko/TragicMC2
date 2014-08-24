package tragicneko.tragicmc.worldgen.schematic;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.StructureWorldGen;

public class SchematicTimeAltar extends Schematic {

	private static Block quartz = Blocks.quartz_block;
	private static Block crystal = TragicBlocks.StarCrystal;
	private static Block summon = TragicBlocks.SummonBlock;
	private static Block chest = Blocks.chest;

	public SchematicTimeAltar(int variant, World world, Random rand, int x, int y, int z) {
		super(variant, world, rand, x, y, z);
	}

	@Override
	public void generateStructure(int variant, World world, Random rand, int x, int y, int z) {
		switch(variant)
		{
		case 0:
			generateWithoutVariation(world, rand, x, y, z);
			break;
		default:
			TragicMC.logger.info("There was a problem generating a Time Altar");
			break;
		}
	}

	@Override
	public void generateWithoutVariation(World world, Random rand, int x, int y, int z) {

		for (int y1 = -1; y1 < 10; y1++)
		{
			for (int x1 = -7; x1 < 8; x1++)
			{
				for (int z1 = -7; z1 < 8; z1++)
				{
					world.setBlockToAir(x + x1, y + y1, z + z1);
				}
			}
		}

		Map<Integer, int[]> map;
		int[] coords;
		Block block;
		boolean flag;

		map = WorldHelper.getBlocksInSphericalRange(world, 8.0D, x, y, z);

		for (int i = 0; i < map.size(); i++) //creates a giant hemisphere of quartz with star crystal inside of it sparingly
		{
			coords = map.get(i);

			if (coords[1] < y + 1)
			{
				block = world.getBlock(coords[0], coords[1], coords[2]);
				flag = rand.nextInt(32) != 0;

				if (StructureWorldGen.validBlocks.contains(block))
				{
					if (rand.nextInt(8) != 0)
					{
						world.setBlock(coords[0], coords[1], coords[2], quartz, 0, 2);
					}
					else
					{
						world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
				}
			}
		}

		map = WorldHelper.getBlocksInCircularRange(world, 4.446D, x, y, z);

		for (int i = 0; i < map.size(); i++) //creates a smaller hemisphere of star crystal to provide lighting
		{
			coords = map.get(i);

			if (coords[1] < y + 1)
			{
				block = world.getBlock(coords[0], coords[1], coords[2]);

				if (block == quartz)
				{
					if (rand.nextInt(4) == 0)
					{
						world.setBlock(coords[0], coords[1], coords[2], crystal, rand.nextInt(6), 2);
					}
				}
			}
		}

		//Creates a structure in the middle that you can use to create another time controller, it comes with a summon block on top though
		world.setBlock(x, y + 1, z, quartz);
		world.setBlock(x, y + 2, z, quartz);
		world.setBlock(x, y + 2, z + 1, quartz);
		world.setBlock(x, y + 2, z - 1, quartz);
		world.setBlock(x + 1, y + 2, z, quartz);
		world.setBlock(x - 1, y + 2, z, quartz);

		world.setBlock(x, y + 3, z, summon, 7, 2);

		world.setBlock(x, y, z, chest, 0, 2);
		this.applyChestContents(world, rand, x, y, z);

		world.setBlock(x + 1, y, z, quartz); //blocks to ensure the chest is concealed
		world.setBlock(x - 1, y, z, quartz);
		world.setBlock(x, y, z + 1, quartz);
		world.setBlock(x, y, z - 1, quartz);
	}

	@Override
	public void generateVariant(World world, Random rand, int x, int y, int z) {

	}

	@Override
	public void applyChestContents(World world, Random rand, int x, int y, int z) {
		TileEntityChest tileentity = (TileEntityChest)world.getTileEntity(x, y, z);

		if (tileentity != null)
		{
			WeightedRandomChestContent.generateChestContents(rand, TragicItems.AwesomeChestHook.getItems(rand), tileentity, TragicItems.AwesomeChestHook.getCount(rand));
		}
		else
		{
			TragicMC.logger.warn("Chest generation failed for some reason.");
		}

	}

}
