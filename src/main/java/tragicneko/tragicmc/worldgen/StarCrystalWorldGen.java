package tragicneko.tragicmc.worldgen;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.main.TragicBiomes;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class StarCrystalWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

		if (!WorldHelper.validBlocksForDimension.contains(world.getBlock(Xcoord, Ycoord - 1, Zcoord))) return;

		if (!TragicBiomes.starlitBiomes.contains(world.getBiomeGenForCoords(Xcoord, Zcoord))) return;

		Map<Integer, int[]> map;
		double size;
		int[] coords;
		Block block;
		int meta;

		for (int i = 0; i < 4; i++)
		{			
			if (random.nextInt(100) <= TragicNewConfig.starCrystalRarity)
			{
				Xcoord += random.nextInt(8) - random.nextInt(8);
				Zcoord += random.nextInt(8) - random.nextInt(8);
				Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

				size = 0.35D * random.nextDouble() + 0.75D;
				meta = random.nextInt(16);

				if (WorldHelper.validBlocksForDimension.contains(world.getBlock(Xcoord, Ycoord - 1, Zcoord)))
				{
					for (int y1 = 0; y1 < 12; y1++)
					{
						size *= 0.91377745D;

						if (size < 0.444443755D || Ycoord + y1 > 256) break;

						map = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1 + (size * 0.5D), Zcoord);

						for (int j = 0; j < map.size(); j++)
						{
							coords = map.get(j);

							if (StructureWorldGen.validBlocks.contains(world.getBlock(coords[0], coords[1], coords[2])))
							{
								world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.StarCrystal, meta, 2);
							}
						}
					}
				}
			}
		}
	}

}
