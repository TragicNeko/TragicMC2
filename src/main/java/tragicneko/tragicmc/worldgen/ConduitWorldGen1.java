package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;

public class ConduitWorldGen1 extends WorldGenerator {

	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		if (!world.isAirBlock(x, y, z))
		{
			return false;
		}
		else if (world.getBlock(x, y + 1, z) != TragicBlocks.CircuitBlock)
		{
			return false;
		}
		else
		{
			world.setBlock(x, y, z, TragicBlocks.Conduit, 0, 2);

			for (int l = 0; l < 1200; ++l)
			{
				int i1 = x + rand.nextInt(4) - rand.nextInt(4);
				int j1 = y - rand.nextInt(32);
				int k1 = z + rand.nextInt(4) - rand.nextInt(4);

				if (world.getBlock(i1, j1, k1).getMaterial() == Material.air)
				{
					byte b = 0;
					ArrayList<int[]> list = WorldHelper.getBlocksAdjacent(new int[] {i1, j1, k1});
					for (int[] coords : list)
					{
						if (world.getBlock(coords[0], coords[1], coords[2]) == TragicBlocks.Conduit) b++;
					}

					if (b == 1) world.setBlock(i1, j1, k1, TragicBlocks.Conduit, 0, 2);
				}
			}

			return true;
		}
	}
}
