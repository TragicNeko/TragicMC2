package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
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

		ArrayList<int[]> list;
		double size;
		Block block;
		int meta;

		for (int i = 0; i < 4; i++)
		{			
			if (random.nextInt(100) <= TragicConfig.starCrystalRarity)
			{
				Xcoord += random.nextInt(8) - random.nextInt(8);
				Zcoord += random.nextInt(8) - random.nextInt(8);
				Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

				size = 0.35D * random.nextDouble() + 0.75D;
				meta = random.nextInt(16);
				for (int j = 0; j < 5 && meta != 15; j++) meta = random.nextInt(16); //randomizes the crystal if it doesn't choose a white crystal, this is to make it more common

				if (WorldHelper.validBlocksForDimension.contains(world.getBlock(Xcoord, Ycoord - 1, Zcoord)))
				{
					for (int y1 = 0; y1 < 12; y1++)
					{
						size *= 0.91377745D;

						if (size < 0.444443755D || Ycoord + y1 > 256) break;

						list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1 + (size * 0.5D), Zcoord);

						for (int[] coords : list)
						{
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
