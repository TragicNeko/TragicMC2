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
		ArrayList<int[]> cands;
		Block block = world.getBlock(x, y, z);
		if (block != CircuitBlock) return false;
		int m = world.getBlockMetadata(x, y, z);
		if (m != 0) return false;

		int[] cand = new int[] {x, y, z};

		int meta = 1;
		boolean flag = false;

		for (int k = 0; k < 32 + rand.nextInt(this.blockSize * this.blockSize); k++)
		{
			block = world.getBlock(cand[0], cand[1], cand[2]);
			m = world.getBlockMetadata(cand[0], cand[1], cand[2]);
			if (block != CircuitBlock) break;

			if (m == 0) world.setBlock(cand[0], cand[1], cand[2], CircuitBlock, meta, 2);
			cands = WorldHelper.getBlocksAdjacent(cand);

			if (rand.nextInt(48) == 0 && meta < 4 && !flag)
			{
				meta++;
				if (meta == 4) flag = true;
			}
			if (meta > 1 && rand.nextInt(4) == 0 && flag) meta--;


			for (int[] cand2 : cands)
			{
				block = world.getBlock(cand2[0], cand2[1], cand2[2]);
				m = world.getBlockMetadata(cand2[0], cand2[1], cand2[2]);

				if (block == CircuitBlock && m == 0 && rand.nextBoolean()) world.setBlock(cand2[0], cand2[1], cand2[2], CircuitBlock, meta, 2);
			}

			cand = cands.get(rand.nextInt(cands.size()));
		}

		return true;
	}
}