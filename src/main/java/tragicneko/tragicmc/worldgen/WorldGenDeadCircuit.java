package tragicneko.tragicmc.worldgen;

import static tragicneko.tragicmc.TragicBlocks.CircuitBlock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

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
		while (world.isAirBlock(x, y, z) && y > 5)
		{
			--y;
		}
		
		if (world.getBlock(x, y, z) != CircuitBlock) return false;

		int l = rand.nextInt(this.blockSize - 2) + 2;
		byte b0 = 1;

		for (int i1 = x - l; i1 <= x + l; ++i1)
		{
			for (int j1 = z - l; j1 <= z + l; ++j1)
			{
				int k1 = i1 - x;
				int l1 = j1 - z;

				if (k1 * k1 + l1 * l1 <= l * l)
				{
					for (int i2 = y - b0; i2 <= y + b0; ++i2)
					{
						Block block = world.getBlock(i1, i2, j1);

						if (block == CircuitBlock)
						{
							world.setBlock(i1, i2, j1, CircuitBlock, rand.nextInt(4) + 1, 2);
						}
					}
				}
			}
		}

		return true;
	}
}