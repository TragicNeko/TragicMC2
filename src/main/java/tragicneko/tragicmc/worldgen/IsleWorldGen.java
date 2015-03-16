package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class IsleWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int Xcoord = (chunkX * 16) + random.nextInt(16) - random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16) - random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) + 1;
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);
		if (biome != TragicBiomes.TaintedIsles || random.nextInt(6) == 0) return;

		ArrayList<int[]> list;

		int relays = 3 + random.nextInt(2);
		Block block;
		double regression = 0.88977745D;
		double cutoff = 0.48943755D;
		double size;
		ArrayList<int[]> cands = new ArrayList<int[]>();
		int yMax;

		for (int buzza = 0; buzza < relays; buzza++)
		{
			size = random.nextDouble() * 3.5D + 1.5D;
			Xcoord += random.nextInt(8) - random.nextInt(8);
			Zcoord += random.nextInt(8) - random.nextInt(8);
			Ycoord += 38 + random.nextInt(48) - random.nextInt(16);
			yMax = Ycoord;

			for (int y1 = 0; y1 > -32; y1--)
			{
				if (size < cutoff) break;
				size *= regression;

				if (random.nextInt(12) == 0)
				{
					Xcoord += random.nextInt(2) - random.nextInt(2);
					Zcoord += random.nextInt(2) - random.nextInt(2);
				}

				list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + y1, Zcoord);

				for (int[] coords2 : list)
				{
					block = world.getBlock(coords2[0], coords2[1], coords2[2]);
					if (StructureWorldGen.validBlocks.contains(block) && !cands.contains(coords2))
					{
						if (yMax < coords2[1]) yMax = coords2[1];
						cands.add(coords2);
					}
				}
			}

			int rand = random.nextInt(3) + 1;

			for (int[] coords2 : cands)
			{
				if (coords2[1] >= yMax)
				{
					world.setBlock(coords2[0], coords2[1], coords2[2], TragicBlocks.ErodedStone, 0, 2);
				}
				else if (coords2[1] >= yMax - rand - random.nextInt(2))
				{
					world.setBlock(coords2[0], coords2[1], coords2[2], TragicBlocks.DeadDirt, 0, 2);
				}
				else
				{
					world.setBlock(coords2[0], coords2[1], coords2[2], TragicBlocks.DarkStone, 0, 2);
				}
			}
		}
		//seed: -4219481829119480244 x: 4200 z: 4600
	}

}
