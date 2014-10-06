package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;

public class WorldGenBleachedTree extends WorldGenAbstractTree {

	private boolean isLargeTree;

	public WorldGenBleachedTree(boolean p_i45448_1_, boolean isLargeTree) {
		super(p_i45448_1_);
		this.isLargeTree = isLargeTree;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {

		if (!WorldHelper.validBlocksForDimension.contains(world.getBlock(x, y, z)) || random.nextInt(3) != 0) return false;
		double size = random.nextDouble() * 2.0D + 3.5D;
		if (this.isLargeTree) size = random.nextDouble() * 3.0D + 6.5D;

		ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(world, size * 0.685D, x, y + (size * 0.785D), z);
		int[] coords;
		Block block;

		for (int i = 0; i < list.size(); i++) //creates a dome shaped leaf coverage with less chance for leaves at lower heights to be placed
		{
			coords = list.get(i);
			block = world.getBlock(coords[0], coords[1], coords[2]);

			if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2]) && coords[1] >= (y + (size * 0.855D)))
			{
				world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedLeaves);
			}
		}

		list = WorldHelper.getBlocksInSphericalRange(world, size * 0.515D, x, y + (size * 0.785D) - 1.5D, z);

		for (int i = 0; i < list.size(); i++) //removes extra leaves that are too low
		{
			coords = list.get(i);
			block = world.getBlock(coords[0], coords[1], coords[2]);

			if (block == TragicBlocks.BleachedLeaves)
			{
				world.setBlockToAir(coords[0], coords[1], coords[2]);
			}
		}

		size *= 0.663337D; //To scale down the radius to what the tree trunk needs

		for (int y1 = 0; y1 <= size + (size * 0.497777455D) + 1; y1++)
		{
			list = WorldHelper.getBlocksInCircularRange(world, size * 0.267D, x, y + y1, z);

			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				block = world.getBlock(coords[0], coords[1], coords[2]);
				if (block == TragicBlocks.BleachedLeaves || block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])) world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
			}

			if (y1 <= size * 0.345D || y1 >= size * 0.885D)
			{
				list = WorldHelper.getBlocksInCircularRange(world, size * 0.487D, x, y + y1, z);

				for (int i = 0; i < list.size(); i++)
				{
					coords = list.get(i);
					if (random.nextInt(4) != 0)
					{
						block = world.getBlock(coords[0], coords[1], coords[2]);
						if (block == TragicBlocks.BleachedLeaves || block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])) world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
					}
				}
			}

			if (y1 <= size * 0.145D)
			{
				list = WorldHelper.getBlocksInCircularRange(world, size * 0.638D, x, y + y1, z);

				for (int i = 0; i < list.size(); i++)
				{
					coords = list.get(i);
					block = world.getBlock(coords[0], coords[1], coords[2]);
					if (block == TragicBlocks.BleachedLeaves || block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])) world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
				}
			}

			if (y1 >= size * 0.925D)
			{
				list = WorldHelper.getBlocksInCircularRange(world, size * 0.618D, x, y + y1, z);

				for (int i = 0; i < list.size(); i++)
				{
					coords = list.get(i);

					if (((coords[0] != x + 3 || coords[0] != x - 3) && (coords[2] != z + 3 || coords[2] != z - 3)) && ((coords[0] != x + 2 || coords[0] != x - 2) && (coords[2] != z + 2 || coords[2] != z - 2)))
					{
						if (random.nextInt(4) != 0)
						{
							block = world.getBlock(coords[0], coords[1], coords[2]);
							if (block == TragicBlocks.BleachedLeaves || block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])) world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
						}
					}
				}
			}

			if (y1 >= size * 0.887D) //remove some blocks from the inside near the top to give it more of an open look
			{
				double d0 = size >= 6.333357D ? 0.3334567D : 0.283D;
				
				list = WorldHelper.getBlocksInCircularRange(world, size * 0.283D, x, y + y1, z);

				for (int i = 0; i < list.size(); i++)
				{
					coords = list.get(i);

					block = world.getBlock(coords[0], coords[1], coords[2]);
					if (block == TragicBlocks.BleachedWood && random.nextInt(8) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);

				}
			}

			if (y1 >= size * 0.956687D && size >= 3.64673D) //fill in some empty space near the top after some wood is removed
			{
				list = WorldHelper.getBlocksInCircularRange(world, size * 0.363D, x, y + y1, z);

				for (int i = 0; i < list.size(); i++)
				{
					coords = list.get(i);

					block = world.getBlock(coords[0], coords[1], coords[2]);
					if (block == TragicBlocks.BleachedWood && random.nextInt(4) == 0|| block == Blocks.air) world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedLeaves);

				}
			}
		}

		list = WorldHelper.getBlocksInCircularRange(world, size * 0.728D, x, y - 1, z); //to make the roots appear more widespread

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			block = world.getBlock(coords[0], coords[1], coords[2]);
			if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2]) && random.nextInt(4) == 0) world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
		}

		if (size >= 6.6667D) //for the larger version to give the roots more spread
		{
			list = WorldHelper.getBlocksInCircularRange(world, size * 0.838D, x, y - 2, z);

			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				block = world.getBlock(coords[0], coords[1], coords[2]);
				if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2]) && random.nextInt(4) == 0) world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
			}
		}
		return true;
	}

}
