package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
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
			if (random.nextInt(100) >= TragicNewConfig.voidPitRarity || random.nextInt(4) == 0) return;

			int Xcoord = (chunkX * 16) + random.nextInt(16);
			int Ycoord = random.nextInt(35) + 60;
			int Zcoord = (chunkZ * 16) + random.nextInt(16);

			if (!TragicBiomes.decayingBiomes.contains(world.getBiomeGenForCoords(Xcoord, Zcoord))) return;

			double size;
			int[] coords;
			ArrayList<int[]> list;

			size = 3.0D * random.nextDouble() + 3.0D;

			for (int pow = 0; pow + Ycoord >= 0 && pow + Ycoord <= 256; --pow)
			{
				if (size >= 5.5D)
				{
					list = WorldHelper.getBlocksInCircularRange(world, size * 0.31773D, Xcoord, Ycoord + pow, Zcoord); //makes sure the middle of the pit is clear

					for (int mapping = 0; mapping < list.size(); mapping++)
					{
						coords = list.get(mapping);
						if (random.nextInt(2) != 0) world.setBlock(coords[0], coords[1], coords[2], Blocks.air);
					} 
				}

				list = WorldHelper.getBlocksInCircularRange(world, size * 0.64773D, Xcoord, Ycoord + pow, Zcoord); //gives the pit more of a gradual feel

				for (int mapping = 0; mapping < list.size(); mapping++)
				{
					coords = list.get(mapping);
					if (random.nextInt(2) != 0) world.setBlock(coords[0], coords[1], coords[2], Blocks.air);
				} 

				list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + pow, Zcoord); //outer part that has the most scattered blocks

				for (int mapping = 0; mapping < list.size(); mapping++)
				{
					coords = list.get(mapping);
					if (random.nextInt(2) != 0) world.setBlock(coords[0], coords[1], coords[2], Blocks.air);
				}
			}
		}
	}

}
