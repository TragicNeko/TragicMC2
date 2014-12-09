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

public class SchematicApisTemple extends Schematic {

	/**
	 * Meta 0 is stoneBrick, 1 is mossy, 2 is cracked, 3 is chiseled
	 */
	private static Block brick = Blocks.stonebrick;

	private static Block obs = Blocks.obsidian;
	private static Block glow = Blocks.glowstone;
	private static Block chest = Blocks.chest;
	private static Block stone = Blocks.stone;
	private static Block summon = TragicBlocks.SummonBlock;

	/**
	 * All of the common blocks that the structure will use, for ease of randomization they are in one array
	 */
	public static Block[] commonBlocks = new Block[] {Blocks.cobblestone, Blocks.gravel, Blocks.dirt, Blocks.mossy_cobblestone, Blocks.stone};

	public SchematicApisTemple(int variant, World world, Random rand, int x, int y, int z)
	{
		super(variant, world, rand, x, y, z);
	}

	public void generateStructure(int variant, World world, Random rand, int x, int y, int z)
	{
		switch(variant)
		{
		case 0:
			generateWithoutVariation(world, rand, x, y, z);
			break;
		default:
			TragicMC.logError("There was a problem generating an Apis Temple");
			break;
		}
	}

	public void generateWithoutVariation(World world, Random rand, int x, int y, int z)
	{
		for (int y1 = 0; y1 < 10; y1++)
		{
			for (int x1 = -8; x1 < 17; x1++)
			{
				for (int z1 = -6; z1 < 15; z1++)
				{
					world.setBlockToAir(x + x1, y + y1, z + z1);
				}
			}
		}
		//First layer

		//Top row

		for (int i = 2; i < 8; i++)
		{
			world.setBlock(x - 7, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		//Second row
		for (int i = 1; i < 9; i++)
		{
			world.setBlock(x - 6, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		//Third row
		for (int i = -1; i < 11; i++)
		{
			world.setBlock(x - 5, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		//Fourth row
		for (int i = -2; i < 12; i++)
		{
			world.setBlock(x - 4, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		//Fifth row
		for (int i = -2; i < 12; i++)
		{
			world.setBlock(x - 3, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		//Sixth row
		for (int i = -3; i < 13; i++)
		{
			world.setBlock(x - 2, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		//Seventh row
		for (int i = -4; i < 14; i++)
		{
			world.setBlock(x - 1, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		world.setBlock(x - 1, y, z, brick, 3, 2);
		world.setBlock(x - 1, y, z + 9, brick, 3, 2);

		//Eighth row
		for (int i = -5; i < 15; i++)
		{
			world.setBlock(x, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		world.setBlock(x, y, z + 4, obs, 0, 2);
		world.setBlock(x, y, z + 5, obs, 0, 2);

		//Ninth row
		for (int i = -5; i < 15; i++)
		{
			world.setBlock(x + 1, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		world.setBlock(x + 1, y, z, commonBlocks[rand.nextInt(5)], 3, 2);
		world.setBlock(x + 1, y, z + 3, obs, 0, 2);
		world.setBlock(x + 1, y, z + 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 1, y, z + 5, brick, rand.nextInt(3), 2);
		world.setBlock(x + 1, y, z + 6, obs, 0, 2);
		world.setBlock(x + 1, y, z + 9, commonBlocks[rand.nextInt(5)], 3, 2);

		//Tenth row
		for (int i = -5; i < 15; i++)
		{
			world.setBlock(x + 2, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		world.setBlock(x + 2, y, z, commonBlocks[rand.nextInt(5)], 3, 2);
		world.setBlock(x + 2, y, z + 2, obs, 0, 2);
		world.setBlock(x + 2, y, z + 3, brick, 3, 2);
		world.setBlock(x + 2, y, z + 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z + 5, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z + 6, brick, 3, 2);
		world.setBlock(x + 2, y, z + 7, obs, 0, 2);
		world.setBlock(x + 2, y, z + 9, commonBlocks[rand.nextInt(5)], 3, 2);

		//Eleventh row
		for (int i = -5; i < 15; i++)
		{
			world.setBlock(x + 3, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		world.setBlock(x + 3, y, z, commonBlocks[rand.nextInt(5)], 3, 2);
		world.setBlock(x + 3, y, z + 2, obs, 0, 2);
		world.setBlock(x + 3, y, z + 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 3, y, z + 4, obs, 0, 2);
		world.setBlock(x + 3, y, z + 5, obs, 0, 2);
		world.setBlock(x + 3, y, z + 6, brick, rand.nextInt(3), 2);
		world.setBlock(x + 3, y, z + 7, obs, 0, 2);
		world.setBlock(x + 3, y, z + 9, commonBlocks[rand.nextInt(5)], 3, 2);

		//Twelfth row - This is the middle row
		for (int i = -5; i < 15; i++)
		{
			world.setBlock(x + 4, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		world.setBlock(x + 4, y, z + 1, obs, 0, 2);
		world.setBlock(x + 4, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 4, y, z + 3, glow, 0, 2);
		world.setBlock(x + 4, y, z + 4, chest, 0, 2);
		world.setBlock(x + 4, y, z + 5, chest, 0, 2);
		world.setBlock(x + 4, y, z + 6, glow, 0, 2);
		world.setBlock(x + 4, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x + 4, y, z + 8, obs, 0, 2);

		//Thirteenth row
		for (int i = -5; i < 15; i++)
		{
			world.setBlock(x + 5, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		world.setBlock(x + 5, y, z, commonBlocks[rand.nextInt(5)], 3, 2);
		world.setBlock(x + 5, y, z + 2, obs, 0, 2);
		world.setBlock(x + 5, y, z + 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 5, y, z + 4, obs, 0, 2);
		world.setBlock(x + 5, y, z + 5, obs, 0, 2);
		world.setBlock(x + 5, y, z + 6, brick, rand.nextInt(3), 2);
		world.setBlock(x + 5, y, z + 7, obs, 0, 2);
		world.setBlock(x + 5, y, z + 9, commonBlocks[rand.nextInt(5)], 3, 2);

		//Fourteenth row
		for (int i = -5; i < 15; i++)
		{
			world.setBlock(x + 6, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		world.setBlock(x + 6, y, z, commonBlocks[rand.nextInt(5)], 3, 2);
		world.setBlock(x + 6, y, z + 2, obs, 0, 2);
		world.setBlock(x + 6, y, z + 3, brick, 3, 2);
		world.setBlock(x + 6, y, z + 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 6, y, z + 5, brick, rand.nextInt(3), 2);
		world.setBlock(x + 6, y, z + 6, brick, 3, 2);
		world.setBlock(x + 6, y, z + 7, obs, 0, 2);
		world.setBlock(x + 6, y, z + 9, commonBlocks[rand.nextInt(5)], 3, 2);

		//Fifteenth row
		for (int i = -5; i < 15; i++)
		{
			world.setBlock(x + 7, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		world.setBlock(x + 7, y, z, commonBlocks[rand.nextInt(5)], 3, 2);
		world.setBlock(x + 7, y, z + 3, obs, 0, 2);
		world.setBlock(x + 7, y, z + 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 7, y, z + 5, brick, rand.nextInt(3), 2);
		world.setBlock(x + 7, y, z + 6, obs, 0, 2);
		world.setBlock(x + 7, y, z + 9, commonBlocks[rand.nextInt(5)], 3, 2);

		//Sixteenth row
		for (int i = -5; i < 15; i++)
		{
			world.setBlock(x + 8, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		world.setBlock(x + 8, y, z + 4, obs, 0, 2);
		world.setBlock(x + 8, y, z + 5, obs, 0, 2);

		//Seventeenth row
		for (int i = -4; i < 14; i++)
		{
			world.setBlock(x + 9, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		world.setBlock(x + 9, y, z, brick, 3, 2);
		world.setBlock(x + 9, y, z + 9, brick, 3, 2);

		//Eighteenth row
		for (int i = -3; i < 13; i++)
		{
			world.setBlock(x + 10, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		//Nineteenth row
		for (int i = -2; i < 12; i++)
		{
			world.setBlock(x + 11, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		//Twentieth row
		for (int i = -2; i < 12; i++)
		{
			world.setBlock(x + 12, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		//Twenty-first row
		for (int i = -1; i < 11; i++)
		{
			world.setBlock(x + 13, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		//Twenty-second row
		for (int i = 1; i < 9; i++)
		{
			world.setBlock(x + 14, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		//Twenty-third row
		for (int i = 2; i < 8; i++)
		{
			world.setBlock(x + 15, y, z + i, commonBlocks[rand.nextInt(5)], 0, 2);
		}

		if (!world.isRemote)
		{
			//First layer chest gen
			this.applyChestContents(world, rand, x + 4, y, z + 4);
			this.applyChestContents(world, rand, x + 4, y, z + 5);
		}
		y++;
		//Second layer
		x -= 1;

		//First row
		world.setBlock(x - 6, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 6, y, z + 7, brick, rand.nextInt(3), 2);

		//Second row
		world.setBlock(x - 5, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 5, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 5, y, z + 3, brick, 3, 2);
		world.setBlock(x - 5, y, z + 6, brick, 3, 2);
		world.setBlock(x - 5, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x - 5, y, z + 8, brick, rand.nextInt(3), 2);

		//Third row
		world.setBlock(x - 4, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 3, brick, 3, 2);
		world.setBlock(x - 4, y, z + 6, brick, 3, 2);
		world.setBlock(x - 4, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 8, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 9, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 10, brick, rand.nextInt(3), 2);

		//Fourth row
		world.setBlock(x - 3, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 3, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 3, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x - 3, y, z + 11, brick, rand.nextInt(3), 2);

		//Fifth row
		world.setBlock(x - 2, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x - 2, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 2, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 2, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x - 2, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x - 2, y, z + 12, brick, rand.nextInt(3), 2);

		//Sixth row
		world.setBlock(x - 1, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 12, brick, rand.nextInt(3), 2);

		//Seventh row
		world.setBlock(x, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z, brick, 3, 2); 
		world.setBlock(x, y, z + 9, brick, 3, 2);
		world.setBlock(x, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 12, brick, rand.nextInt(3), 2);

		//Eighth row
		world.setBlock(x + 1, y, z - 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 1, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 1, y, z + 12, brick, rand.nextInt(3), 2);
		world.setBlock(x + 1, y, z + 13, brick, rand.nextInt(3), 2);

		//Ninth row
		world.setBlock(x + 2, y, z - 5, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z - 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z + 12, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z + 13, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z + 14, brick, rand.nextInt(3), 2);

		//Tenth row
		world.setBlock(x + 3, y, z - 4, brick, 3, 2);
		world.setBlock(x + 3, y, z - 3, brick, 3, 2);
		world.setBlock(x + 3, y, z + 12, brick, 3, 2);
		world.setBlock(x + 3, y, z + 13, brick, 3, 2);

		//Eleventh row

		//Twelfth row - middle row
		world.setBlock(x + 5, y, z + 4, obs, 0, 2);
		world.setBlock(x + 5, y, z + 5, obs, 0, 2);

		//Thirteenth row

		//Fourteenth row
		world.setBlock(x + 7, y, z - 4, brick, 3, 2);
		world.setBlock(x + 7, y, z - 3, brick, 3, 2);
		world.setBlock(x + 7, y, z + 12, brick, 3, 2);
		world.setBlock(x + 7, y, z + 13, brick, 3, 2);

		//Fifteenth row - 9
		world.setBlock(x + 8, y, z - 5, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z - 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z + 12, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z + 13, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z + 14, brick, rand.nextInt(3), 2);

		//Sixteenth row
		world.setBlock(x + 9, y, z - 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 9, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 9, y, z + 12, brick, rand.nextInt(3), 2);
		world.setBlock(x + 9, y, z + 13, brick, rand.nextInt(3), 2);

		//Seventeenth row
		world.setBlock(x + 10, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z, brick, 3, 2);
		world.setBlock(x + 10, y, z + 9, brick, 3, 2);
		world.setBlock(x + 10, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 12, brick, rand.nextInt(3), 2);

		//Eighteenth row - 6
		world.setBlock(x + 11, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 12, brick, rand.nextInt(3), 2);

		//Nineteenth row
		world.setBlock(x + 12, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 12, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 12, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 12, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x + 12, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x + 12, y, z + 12, brick, rand.nextInt(3), 2);

		//Twentieth row - 4
		world.setBlock(x + 13, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 13, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 13, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x + 13, y, z + 11, brick, rand.nextInt(3), 2);

		//Twenty-first row
		world.setBlock(x + 14, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 3, brick, 3, 2);
		world.setBlock(x + 14, y, z + 6, brick, 3, 2);
		world.setBlock(x + 14, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 8, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 9, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 10, brick, rand.nextInt(3), 2);

		//Twenty-second row
		world.setBlock(x + 15, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 15, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 15, y, z + 3, brick, 3, 2);
		world.setBlock(x + 15, y, z + 6, brick, 3, 2);
		world.setBlock(x + 15, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x + 15, y, z + 8, brick, rand.nextInt(3), 2);

		//Twenty-third row
		world.setBlock(x + 16, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 16, y, z + 7, brick, rand.nextInt(3), 2);

		y++;

		//Third layer

		//First row

		//Second row
		world.setBlock(x - 5, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 5, y, z + 3, stone, 0, 2);
		world.setBlock(x - 5, y, z + 6, stone, 0, 2);
		world.setBlock(x - 5, y, z + 7, brick, rand.nextInt(3), 2);

		//Third row
		world.setBlock(x - 4, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 3, stone, 0, 2);
		world.setBlock(x - 4, y, z + 6, stone, 0, 2);
		world.setBlock(x - 4, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 8, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 9, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 10, brick, rand.nextInt(3), 2);

		//Fourth row
		world.setBlock(x - 3, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 3, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 3, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x - 3, y, z + 11, brick, rand.nextInt(3), 2);

		//Fifth row
		world.setBlock(x - 2, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 2, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 2, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x - 2, y, z + 11, brick, rand.nextInt(3), 2);

		//Sixth row
		world.setBlock(x - 1, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 12, brick, rand.nextInt(3), 2);

		//Seventh row
		world.setBlock(x, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z, brick, 3, 2);
		world.setBlock(x, y, z + 9, brick, 3, 2);
		world.setBlock(x, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 12, brick, rand.nextInt(3), 2);

		//Eighth row
		world.setBlock(x + 1, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 1, y, z + 12, brick, rand.nextInt(3), 2);

		//Ninth row
		world.setBlock(x + 2, y, z - 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z + 12, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z + 13, brick, rand.nextInt(3), 2);

		//Tenth row
		world.setBlock(x + 3, y, z - 4, stone, 0, 2);
		world.setBlock(x + 3, y, z - 3, stone, 0, 2);
		world.setBlock(x + 3, y, z + 12, stone, 0, 2);
		world.setBlock(x + 3, y, z + 13, stone, 0, 2);

		//Eleventh row

		//Twelfth row - middle row

		if (!world.isRemote)
		{
			int wubwub = rand.nextInt(100);

			if (wubwub > 50)
			{
				world.setBlock(x + 5, y, z + 4, summon, 2, 2);
				world.setBlockToAir(x + 5, y, z + 5);
			}
			else
			{
				if (wubwub == 0) //1 in 100 chance to create two summon blocks, otherwise it just does one, randomly picking which spot it's in
				{
					world.setBlock(x + 5, y, z + 4, summon, 2, 2);
					world.setBlock(x + 5, y, z + 5, summon, 2, 2);
				}
				else
				{
					world.setBlockToAir(x + 5, y, z + 4);
					world.setBlock(x + 5, y, z + 5, summon, 2, 2);
				}
			}
		}

		//Thirteenth row

		//Fourteenth row
		world.setBlock(x + 7, y, z - 4, stone, 0, 2);
		world.setBlock(x + 7, y, z - 3, stone, 0, 2);
		world.setBlock(x + 7, y, z + 12, stone, 0, 2);
		world.setBlock(x + 7, y, z + 13, stone, 0, 2);

		//Fifteenth row - 9
		world.setBlock(x + 8, y, z - 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z + 12, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z + 13, brick, rand.nextInt(3), 2);

		//Sixteenth row
		world.setBlock(x + 9, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 9, y, z + 12, brick, rand.nextInt(3), 2);

		//Seventeenth row
		world.setBlock(x + 10, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z, brick, 3, 2);
		world.setBlock(x + 10, y, z + 9, brick, 3, 2);
		world.setBlock(x + 10, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 12, brick, rand.nextInt(3), 2);

		//Eighteenth row - 6
		world.setBlock(x + 11, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 12, brick, rand.nextInt(3), 2);

		//Nineteenth row
		world.setBlock(x + 12, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 12, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 12, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x + 12, y, z + 11, brick, rand.nextInt(3), 2);

		//Twentieth row - 4
		world.setBlock(x + 13, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 13, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 13, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x + 13, y, z + 11, brick, rand.nextInt(3), 2);

		//Twenty-first row
		world.setBlock(x + 14, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 3, stone, 0, 2);
		world.setBlock(x + 14, y, z + 6, stone, 0, 2);
		world.setBlock(x + 14, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 8, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 9, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 10, brick, rand.nextInt(3), 2);

		//Twenty-second row
		world.setBlock(x + 15, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 15, y, z + 3, stone, 0, 2);
		world.setBlock(x + 15, y, z + 6, stone, 0, 2);
		world.setBlock(x + 15, y, z + 7, brick, rand.nextInt(3), 2);

		//Twenty-third row

		y++;

		//Fourth layer

		//First row

		//Second row
		world.setBlock(x - 5, y, z + 3, stone, 0, 2);
		world.setBlock(x - 5, y, z + 6, stone, 0, 2);

		//Third row
		world.setBlock(x - 4, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 3, stone, 0, 2);
		world.setBlock(x - 4, y, z + 6, stone, 0, 2);
		world.setBlock(x - 4, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 8, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 9, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 10, brick, rand.nextInt(3), 2);

		//Fourth row
		world.setBlock(x - 3, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 3, y, z + 10, brick, rand.nextInt(3), 2);

		//Fifth row
		world.setBlock(x - 2, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 2, y, z + 10, brick, rand.nextInt(3), 2);

		//Sixth row
		world.setBlock(x - 1, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 12, brick, rand.nextInt(3), 2);

		//Seventh row
		world.setBlock(x, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z, brick, 3, 2);
		world.setBlock(x, y, z + 9, brick, 3, 2);
		world.setBlock(x, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 12, brick, rand.nextInt(3), 2);

		//Eighth row
		world.setBlock(x + 1, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 1, y, z + 12, brick, rand.nextInt(3), 2);

		//Ninth row
		world.setBlock(x + 2, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z + 12, brick, rand.nextInt(3), 2);

		//Tenth row
		world.setBlock(x + 3, y, z - 4, stone, 0, 2);
		world.setBlock(x + 3, y, z - 3, stone, 0, 2);
		world.setBlock(x + 3, y, z + 12, stone, 0, 2);
		world.setBlock(x + 3, y, z + 13, stone, 0, 2);

		//Eleventh row

		//Twelfth row - middle row

		//Thirteenth row

		//Fourteenth row
		world.setBlock(x + 7, y, z - 4, stone, 0, 2);
		world.setBlock(x + 7, y, z - 3, stone, 0, 2);
		world.setBlock(x + 7, y, z + 12, stone, 0, 2);
		world.setBlock(x + 7, y, z + 13, stone, 0, 2);

		//Fifteenth row - 9
		world.setBlock(x + 8, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z + 12, brick, rand.nextInt(3), 2);

		//Sixteenth row
		world.setBlock(x + 9, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 9, y, z + 12, brick, rand.nextInt(3), 2);

		//Seventeenth row
		world.setBlock(x + 10, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z, brick, 3, 2);
		world.setBlock(x + 10, y, z + 9, brick, 3, 2);
		world.setBlock(x + 10, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 12, brick, rand.nextInt(3), 2);

		//Eighteenth row - 6
		world.setBlock(x + 11, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 12, brick, rand.nextInt(3), 2);

		//Nineteenth row
		world.setBlock(x + 12, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 12, y, z + 10, brick, rand.nextInt(3), 2);

		//Twentieth row - 4
		world.setBlock(x + 13, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 13, y, z + 10, brick, rand.nextInt(3), 2);

		//Twenty-first row
		world.setBlock(x + 14, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 3, stone, 0, 2);
		world.setBlock(x + 14, y, z + 6, stone, 0, 2);
		world.setBlock(x + 14, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 8, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 9, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 10, brick, rand.nextInt(3), 2);

		//Twenty-second row
		world.setBlock(x + 15, y, z + 3, stone, 0, 2);
		world.setBlock(x + 15, y, z + 6, stone, 0, 2);

		//Twenty-third row

		y++;

		//Fifth layer

		//First row

		//Second row
		world.setBlock(x - 5, y, z + 3, brick, 3, 2);
		world.setBlock(x - 5, y, z + 4, stone, 0, 2);
		world.setBlock(x - 5, y, z + 5, stone, 0, 2);
		world.setBlock(x - 5, y, z + 6, brick, 3, 2);

		//Third row
		world.setBlock(x - 4, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 3, brick, 3, 2);
		world.setBlock(x - 4, y, z + 4, stone, 0, 2);
		world.setBlock(x - 4, y, z + 5, stone, 0, 2);
		world.setBlock(x - 4, y, z + 6, brick, 3, 2);
		world.setBlock(x - 4, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 8, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 9, brick, rand.nextInt(3), 2);

		//Fourth row
		world.setBlock(x - 3, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 3, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x - 3, y, z + 9, brick, rand.nextInt(3), 2);
		world.setBlock(x - 3, y, z + 10, brick, rand.nextInt(3), 2);

		//Fifth row
		world.setBlock(x - 2, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 2, y, z + 10, brick, rand.nextInt(3), 2);

		//Sixth row
		world.setBlock(x - 1, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 11, brick, rand.nextInt(3), 2);

		//Seventh row
		world.setBlock(x, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z, brick, 3, 2);
		world.setBlock(x, y, z + 9, brick, 3, 2);
		world.setBlock(x, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 12, brick, rand.nextInt(3), 2);

		//Eighth row
		world.setBlock(x + 1, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 1, y, z + 12, brick, rand.nextInt(3), 2);

		//Ninth row
		world.setBlock(x + 2, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z + 12, brick, rand.nextInt(3), 2);

		//Tenth row
		world.setBlock(x + 3, y, z - 4, brick, 3, 2);
		world.setBlock(x + 3, y, z - 3, brick, 3, 2);
		world.setBlock(x + 3, y, z + 12, brick, 3, 2);
		world.setBlock(x + 3, y, z + 13, brick, 3, 2);

		//Eleventh row
		world.setBlock(x + 4, y, z - 4, stone, 0, 2);
		world.setBlock(x + 4, y, z - 3, stone, 0, 2);
		world.setBlock(x + 4, y, z + 12, stone, 0, 2);
		world.setBlock(x + 4, y, z + 13, stone, 0, 2);

		//Twelfth row - middle row
		world.setBlock(x + 5, y, z - 4, stone, 0, 2);
		world.setBlock(x + 5, y, z - 3, stone, 0, 2);
		world.setBlock(x + 5, y, z + 12, stone, 0, 2);
		world.setBlock(x + 5, y, z + 13, stone, 0, 2);

		//Thirteenth row
		world.setBlock(x + 6, y, z - 4, stone, 0, 2);
		world.setBlock(x + 6, y, z - 3, stone, 0, 2);
		world.setBlock(x + 6, y, z + 12, stone, 0, 2);
		world.setBlock(x + 6, y, z + 13, stone, 0, 2);

		//Fourteenth row
		world.setBlock(x + 7, y, z - 4, brick, 3, 2);
		world.setBlock(x + 7, y, z - 3, brick, 3, 2);
		world.setBlock(x + 7, y, z + 12, brick, 3, 2);
		world.setBlock(x + 7, y, z + 13, brick, 3, 2);

		//Fifteenth row - 9
		world.setBlock(x + 8, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z + 12, brick, rand.nextInt(3), 2);

		//Sixteenth row
		world.setBlock(x + 9, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 9, y, z + 12, brick, rand.nextInt(3), 2);

		//Seventeenth row
		world.setBlock(x + 10, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z, brick, 3, 2);
		world.setBlock(x + 10, y, z + 9, brick, 3, 2);
		world.setBlock(x + 10, y, z + 11, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 12, brick, rand.nextInt(3), 2);

		//Eighteenth row - 6
		world.setBlock(x + 11, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 10, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 11, brick, rand.nextInt(3), 2);

		//Nineteenth row
		world.setBlock(x + 12, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 12, y, z + 10, brick, rand.nextInt(3), 2);

		//Twentieth row - 4
		world.setBlock(x + 13, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 13, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 13, y, z + 9, brick, rand.nextInt(3), 2);
		world.setBlock(x + 13, y, z + 10, brick, rand.nextInt(3), 2);

		//Twenty-first row
		world.setBlock(x + 14, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 3, stone, 0, 2);
		world.setBlock(x + 14, y, z + 6, stone, 0, 2);
		world.setBlock(x + 14, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 8, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 9, brick, rand.nextInt(3), 2);

		//Twenty-second row
		world.setBlock(x + 15, y, z + 3, brick, 3, 2);
		world.setBlock(x + 15, y, z + 4, stone, 0, 2);
		world.setBlock(x + 15, y, z + 5, stone, 0, 2);
		world.setBlock(x + 15, y, z + 6, brick, 3, 2);

		//Twenty-third row

		y++;

		//Sixth layer

		//Third row
		world.setBlock(x - 4, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 3, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 4, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 5, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 6, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x - 4, y, z + 8, brick, rand.nextInt(3), 2);

		//Fourth row
		world.setBlock(x - 3, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x - 3, y, z + 9, brick, rand.nextInt(3), 2);

		//Fifth row
		world.setBlock(x - 2, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 2, y, z + 10, brick, rand.nextInt(3), 2);

		//Sixth row
		world.setBlock(x - 1, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x - 1, y, z + 10, brick, rand.nextInt(3), 2);

		//Seventh row
		world.setBlock(x, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z, brick, 3, 2);
		world.setBlock(x, y, z + 9, brick, 3, 2);
		world.setBlock(x, y, z + 11, brick, rand.nextInt(3), 2);

		//Eighth row
		world.setBlock(x + 1, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 1, y, z + 12, brick, rand.nextInt(3), 2);

		//Ninth row
		world.setBlock(x + 2, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z + 12, brick, rand.nextInt(3), 2);

		//Tenth row
		world.setBlock(x + 3, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 3, y, z + 12, brick, rand.nextInt(3), 2);

		//Eleventh row
		world.setBlock(x + 4, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 4, y, z + 12, brick, rand.nextInt(3), 2);

		//Twelfth row - middle row
		world.setBlock(x + 5, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 5, y, z + 12, brick, rand.nextInt(3), 2);

		//Thirteenth row
		world.setBlock(x + 6, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 6, y, z + 12, brick, rand.nextInt(3), 2);

		//Fourteenth row
		world.setBlock(x + 7, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 7, y, z + 12, brick, rand.nextInt(3), 2);

		//Fifteenth row - 9
		world.setBlock(x + 8, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z + 12, brick, rand.nextInt(3), 2);

		//Sixteenth row
		world.setBlock(x + 9, y, z - 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 9, y, z + 12, brick, rand.nextInt(3), 2);

		//Seventeenth row
		world.setBlock(x + 10, y, z - 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z, brick, 3, 2);
		world.setBlock(x + 10, y, z + 9, brick, 3, 2);
		world.setBlock(x + 10, y, z + 11, brick, rand.nextInt(3), 2);

		//Eighteenth row - 6
		world.setBlock(x + 11, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 11, y, z + 10, brick, rand.nextInt(3), 2);

		//Nineteenth row
		world.setBlock(x + 12, y, z - 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 12, y, z + 10, brick, rand.nextInt(3), 2);

		//Twentieth row - 4
		world.setBlock(x + 13, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 13, y, z + 9, brick, rand.nextInt(3), 2);

		//Twenty-first row
		world.setBlock(x + 14, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 5, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 6, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 8, brick, rand.nextInt(3), 2);
		world.setBlock(x + 14, y, z + 9, brick, rand.nextInt(3), 2);

		y++;
		//Seventh layer

		//Seventh row
		world.setBlock(x, y, z, brick, 3, 2); 
		world.setBlock(x, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 3, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 4, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 5, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 6, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 8, brick, rand.nextInt(3), 2);
		world.setBlock(x, y, z + 9, brick, 3, 2);

		//Eighth row
		world.setBlock(x + 1, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 1, y, z + 9, brick, rand.nextInt(3), 2);

		//Ninth row
		world.setBlock(x + 2, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 2, y, z + 9, brick, rand.nextInt(3), 2);

		//Tenth row
		world.setBlock(x + 3, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 3, y, z + 9, brick, rand.nextInt(3), 2);

		//Eleventh row
		world.setBlock(x + 4, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 4, y, z + 9, brick, rand.nextInt(3), 2);

		//Twelfth row - middle row
		world.setBlock(x + 5, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 5, y, z + 9, brick, rand.nextInt(3), 2);

		//Thirteenth row
		world.setBlock(x + 6, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 6, y, z + 9, brick, rand.nextInt(3), 2);

		//Fourteenth row
		world.setBlock(x + 7, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 7, y, z + 9, brick, rand.nextInt(3), 2);

		//Fifteenth row - 9
		world.setBlock(x + 8, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 8, y, z + 9, brick, rand.nextInt(3), 2);

		//Sixteenth row
		world.setBlock(x + 9, y, z, brick, rand.nextInt(3), 2);
		world.setBlock(x + 9, y, z + 9, brick, rand.nextInt(3), 2);

		//Seventeenth row
		world.setBlock(x + 10, y, z, brick, 3, 2); 
		world.setBlock(x + 10, y, z + 1, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 2, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 3, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 4, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 5, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 6, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 7, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 8, brick, rand.nextInt(3), 2);
		world.setBlock(x + 10, y, z + 9, brick, 3, 2);
	}

	public void applyChestContents(World world, Random rand, int x, int y, int z) 
	{
		TileEntityChest tileentity = (TileEntityChest)world.getTileEntity(x, y, z);

		if (tileentity != null)
		{
			WeightedRandomChestContent.generateChestContents(rand, TragicItems.BossStructureHook.getItems(rand), tileentity, TragicItems.BossStructureHook.getCount(rand));
		}
		else
		{
			TragicMC.logWarning("Chest generation failed for some reason.");
		}
	}

	public void generateVariant(World world, Random rand, int x, int y, int z)
	{
	}
}
