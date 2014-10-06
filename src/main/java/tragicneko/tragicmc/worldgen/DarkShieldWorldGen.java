package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.main.TragicBiomes;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import cpw.mods.fml.common.IWorldGenerator;

public class DarkShieldWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.isRemote || !(world.provider instanceof TragicWorldProvider)) return;

		int x = (chunkX * 16) + random.nextInt(16);
		int z = (chunkZ * 16) + random.nextInt(16);
		int y = world.getTopSolidOrLiquidBlock(x, z);
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		double radius;
		ArrayList<int[]> list;
		int[] coords;
		Block block;

		if (biome == TragicBiomes.AshenBadlands)
		{
			radius = (4.0D * random.nextDouble()) + 3.0D;

			for (int y1 = -1; y1 < 2; y1++)
			{
				list = WorldHelper.getBlocksInCircularRange(world, radius, x, y + y1, z);

				for (int i = 0; i < list.size(); i++)
				{
					coords = list.get(i);
					block = world.getBlock(coords[0], coords[1], coords[2]);

					if (block == TragicBlocks.AshenGrass || block == TragicBlocks.DarkStone || block == TragicBlocks.DeadDirt)
					{
						world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.DeadDirt, 1, 2);
					}
				}
			}
		}
		else if (biome instanceof BiomeGenDecayingWasteland && random.nextBoolean())
		{
			radius = (2.0D * random.nextDouble()) + 2.0D;

			for (int y1 = -2; y1 < 5; y1++)
			{
				list = WorldHelper.getBlocksInCircularRange(world, radius, x, y + y1, z);

				for (int i = 0; i < list.size(); i++)
				{
					coords = list.get(i);
					block = world.getBlock(coords[0], coords[1], coords[2]);

					if (block == TragicBlocks.DarkStone || block == TragicBlocks.DeadDirt)
					{
						world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.DeadDirt, 2, 2);
					}
				}
			}
		}
	}

}
