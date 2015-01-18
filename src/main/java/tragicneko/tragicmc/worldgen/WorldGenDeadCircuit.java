package tragicneko.tragicmc.worldgen;

import static tragicneko.tragicmc.TragicBlocks.CircuitBlock;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.util.WorldHelper;

public class WorldGenDeadCircuit extends WorldGenerator
{
	private int blockSize;

	public WorldGenDeadCircuit(int size)
	{
		this.blockSize = size;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(world, this.blockSize - rand.nextInt(this.blockSize), x, y, z);
		Block block;
		
		for (int[] coords : list)
		{
			block = world.getBlock(coords[0], coords[1], coords[2]);
			if (block == CircuitBlock && rand.nextInt(5) == 0) world.setBlock(coords[0], coords[1], coords[2], CircuitBlock, rand.nextInt(4) + 1, 2);
		}

		return true;
	}
}