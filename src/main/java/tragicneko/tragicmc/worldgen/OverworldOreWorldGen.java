package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import cpw.mods.fml.common.IWorldGenerator;

public class OverworldOreWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == 0)
		{		
			int Xcoord;
			int Ycoord;
			int Zcoord;
						
			for (int i = 0; i < TragicConfig.mercuryOreRarity; i++)
			{
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Ycoord = random.nextInt(48) + 5;
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicBlocks.MercuryOre, 0, 4, Blocks.stone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < TragicConfig.tungstenOreRarity; i++)
			{
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Ycoord = random.nextInt(24) + 5;
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicBlocks.TungstenOre, 0, 3, Blocks.stone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < TragicConfig.silverfishGenRarity && TragicConfig.allowOverworldSilverfishGen; i++)
			{
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Ycoord = random.nextInt(6);
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(Blocks.monster_egg, 0, 3, Blocks.stone).generate(world, random, Xcoord, Ycoord, Zcoord);
				
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Ycoord = random.nextInt(16) + 5;
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(Blocks.monster_egg, 0, 3, Blocks.stone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			if (random.nextInt(4) != 0) return;
			
			for (int i = 0; i < 16 && TragicConfig.allowDimension; i++)
			{
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
				if (Ycoord < 100) continue;
				if (world.getBlock(Xcoord, Ycoord, Zcoord).isReplaceable(world, Xcoord, Ycoord, Zcoord) && world.getBlock(Xcoord, Ycoord - 1, Zcoord).isOpaqueCube() && random.nextInt(200) <= TragicConfig.aerisRarity)
				{
					world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.Aeris, 0, 2);
					break;
				}
			}
		}

	}

}
