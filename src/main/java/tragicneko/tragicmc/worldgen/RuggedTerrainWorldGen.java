package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.blocks.BlockGenericTallGrass;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.main.TragicBiomes;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class RuggedTerrainWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.isRemote) return;

		if (world.provider instanceof TragicWorldProvider)
		{
			int x = (chunkX * 16) + random.nextInt(16);
			int z = (chunkZ * 16) + random.nextInt(16);
			int y = world.getTopSolidOrLiquidBlock(x, z);
			BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

			if (world.getBlock(x, y, z) == TragicBlocks.AshenLeaves || world.getBlock(x, y, z) == TragicBlocks.AshenWood || biome != TragicBiomes.AshenBadlands) return;

			double radius;
			ArrayList<int[]> list;
			int[] coords;
			Block block;

			for (int pow = 0; pow < 4; pow++)
			{
				x += random.nextInt(8) - random.nextInt(8);
				z += random.nextInt(8) - random.nextInt(8);

				radius = (2.0D * random.nextDouble()) + 4.0D;

				for (int y1 = -1; y1 < 2; y1++)
				{
					list = WorldHelper.getBlocksInCircularRange(world, radius, x, y + y1, z);

					for (int i = 0; i < list.size(); i++)
					{
						coords = list.get(i);

						if (random.nextInt(16) == 0)
						{
							block = world.getBlock(coords[0], coords[1], coords[2]);

							if (world.getBlock(coords[0], coords[1] - 1, coords[2]).isOpaqueCube())
							{
								if (block == TragicBlocks.AshenGrass || block == Blocks.air || block instanceof BlockGenericTallGrass)
								{
									world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.DeadDirt, 1, 2);
								}
							}
						}

					}
				}
			}
		}
	}

}
