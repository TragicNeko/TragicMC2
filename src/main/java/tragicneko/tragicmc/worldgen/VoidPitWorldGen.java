package tragicneko.tragicmc.worldgen;

import java.util.Map;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.main.TragicBiomes;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class VoidPitWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider instanceof TragicWorldProvider)
		{
			if (random.nextInt(100) >= TragicNewConfig.voidPitRarity) return;
			
			int Xcoord = (chunkX * 16) + random.nextInt(16);
			int Ycoord = random.nextInt(35) + 60;
			int Zcoord = (chunkZ * 16) + random.nextInt(16);

			if (!TragicBiomes.decayingBiomes.contains(world.getBiomeGenForCoords(Xcoord, Zcoord))) return;

			double size;
			int[] coords;
			Map<Integer, int[]> map;

			size = 3.0D * random.nextDouble() + 4.0D;

			for (int pow = 0; pow + Ycoord >= 0 && pow + Ycoord <= 256; --pow)
			{
				map = WorldHelper.getBlocksInCircularRange(world, size * 0.31773D, Xcoord, Ycoord + pow, Zcoord); //makes sure the middle of the pit is clear

				for (int mapping = 0; mapping < map.size(); mapping++)
				{
					coords = map.get(mapping);

					if (coords[1] <= 16)
					{
						if (random.nextInt(32) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
					else if (coords[1] <= 32)
					{
						if (random.nextInt(40) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
					else if (coords[1] <= 48)
					{
						if (random.nextInt(48) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
					else
					{
						if (random.nextInt(64) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
				} 

				map = WorldHelper.getBlocksInCircularRange(world, size * 0.64773D, Xcoord, Ycoord + pow, Zcoord); //gives the pit more of a gradual feel

				for (int mapping = 0; mapping < map.size(); mapping++)
				{
					coords = map.get(mapping);

					if (coords[1] <= 16)
					{
						if (random.nextInt(12) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
					else if (coords[1] <= 32)
					{
						if (random.nextInt(16) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
					else if (coords[1] <= 48)
					{
						if (random.nextInt(24) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
					else
					{
						if (random.nextInt(48) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
				} 

				map = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + pow, Zcoord); //outer part that has the most scattered blocks

				for (int mapping = 0; mapping < map.size(); mapping++)
				{
					coords = map.get(mapping);

					if (coords[1] <= 16)
					{
						if (random.nextInt(2) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
					else if (coords[1] <= 32)
					{
						if (random.nextInt(4) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
					else if (coords[1] <= 48)
					{
						if (random.nextInt(6) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
					else
					{
						if (random.nextInt(16) != 0) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
				}
			}
		}
	}

}
