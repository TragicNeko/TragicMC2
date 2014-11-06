package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicBiomes;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class IsleWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int x = (chunkX * 16) + random.nextInt(16);
		int z = (chunkZ * 16) + random.nextInt(16);
		int y = world.getTopSolidOrLiquidBlock(x, z) + 6;

		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		if (biome != TragicBiomes.TaintedIsles || random.nextInt(6) == 0) return;

		y += 32 + random.nextInt(48) - random.nextInt(16);

		ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(world, 8.0D, x + 4, y + 3, z + 4);
		int[] coords;

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			if (coords[1] < y - 1.5D)
			{
				world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.DeadDirt, 0, 2);
			}
			else if (coords[1] <= y - 0.5D)
			{
				world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.ErodedStone, 0, 2);
			}
			else
			{
				world.setBlockToAir(coords[0], coords[1], coords[2]);
			}
		}

		int r = random.nextInt(3) + 3;

		for (int potato = 0; potato < r; potato++)
		{
			int yDif = -2 - random.nextInt(4) + random.nextInt(4);
			list = WorldHelper.getBlocksInSphericalRange(world, 4.0D, x + random.nextInt(26) - random.nextInt(26), y + yDif, z + random.nextInt(26) - random.nextInt(26));

			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				if (coords[1] < y + yDif)
				{
					world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.DeadDirt, 0, 2);
				}
				else if (coords[1] <= y + yDif + 1.0D)
				{
					world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.ErodedStone, 0, 2);
				}
			}
		}

	}

}
