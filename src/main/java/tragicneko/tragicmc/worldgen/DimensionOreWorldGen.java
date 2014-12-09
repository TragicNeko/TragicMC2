package tragicneko.tragicmc.worldgen;

import static tragicneko.tragicmc.TragicBlocks.DarkStone;
import static tragicneko.tragicmc.TragicBlocks.TragicOres;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class DimensionOreWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider instanceof TragicWorldProvider)
		{
			for (int i = 0; i < 8; i++) //Mercury
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(32) + 16;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 0, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < 8; i++) //Mercury, upper gen
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(154) + 102;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 0, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}

			for (int i = 0; i < 4; i++) //Ruby
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(30) + 5;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 2, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}

			for (int i = 0; i < 4; i++) //Sapphire
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(150) + 116;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 3, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			

			for (int i = 0; i < 8; i++) //Tungsten
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(32) + 16;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);

				new WorldGenMinable(TragicOres, 1, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < 8; i++) //Tungsten, upper gen
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(154) + 102;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);

				new WorldGenMinable(TragicOres, 1, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < 6; i++) //Diamond
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(24) + 2;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 5, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < 6; i++) //Emerald
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(124) + 132;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 6, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < 6; i++) //Gold
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(48) + 16;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 7, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < 6; i++) //Gold, upper gen
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(156) + 100;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 7, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < 10; i++) //Iron
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(96) + 4;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 8, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < 10; i++) //Iron, upper gen
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(184) + 72;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 8, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < 6; i++) //Lapis
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(48) + 32;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 4, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < 6; i++) //Lapis, upper gen
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(156) + 100;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 4, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			
			for (int i = 0; i < 18; i++) //Coal
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(251) + 5;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 9, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}	
			
			for (int i = 0; i < 18; i++) //XP
			{
				int Xcoord = (chunkX * 16) + random.nextInt(16);
				int Ycoord = random.nextInt(251) + 5;
				int Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicOres, 10, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
		}

	}

}
