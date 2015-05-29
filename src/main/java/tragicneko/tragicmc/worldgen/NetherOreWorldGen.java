package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import cpw.mods.fml.common.IWorldGenerator;

public class NetherOreWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		//TragicMC.logTime();
		if (world.provider.dimensionId == -1)
		{
			for (int i = 0; i < TragicConfig.rubyOreRarity; i++)
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(30) + 15;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicBlocks.RubyOre, 0, 3, Blocks.netherrack).generate(world, random, Xcoord, Ycoord, Zcoord);
			}

			for (int i = 0; i < TragicConfig.sapphireOreRarity; i++)
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(20) + 95;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicBlocks.SapphireOre, 0, 3, Blocks.netherrack).generate(world, random, Xcoord, Ycoord, Zcoord);
			}

			for (int i = 0; i < TragicConfig.drudgeGenRarity && TragicConfig.allowQuicksandGen; i++)
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(60) + 30;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);

				new WorldGenMinable(TragicBlocks.Quicksand, 2, 10, Blocks.netherrack).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
		}
		//TragicMC.logDuration("Custom Nether OreGen");
	}

}
