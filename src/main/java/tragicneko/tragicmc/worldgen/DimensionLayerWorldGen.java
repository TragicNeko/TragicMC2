package tragicneko.tragicmc.worldgen;

import static tragicneko.tragicmc.main.TragicBlocks.DarkStone;

import java.util.Random;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class DimensionLayerWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!(world.provider instanceof TragicWorldProvider) || world.isRemote) return;

		int paloma = WorldHelper.getWorldDependency(world); //due to the seed, every layer will generate the same in every chunk in the same world, different seeds alter the layer generation
		paloma += MathHelper.getRandomIntegerInRange(random, -3, 3); //small variation for each chunk

		random = new Random(world.getSeed());
		Random random2 = TragicMC.rand; //Will replace the X and Z randoms with this one
		if (paloma < 0)
		{
			paloma *= -1;
		}

		int Xcoord = 0;
		int Ycoord = 0;
		int Zcoord = 0;

		for (int i = 0; i < 10; i++) //First layer
		{
			if (paloma - 61 < 5 || paloma - 61 > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + (paloma - 61);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 1, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 10; i++) //Second layer
		{
			if (paloma - 47 < 5 || paloma - 47 > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + (paloma - 47);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 2, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 10; i++) //Third layer
		{
			if (paloma < 5 || paloma > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + paloma;
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 3, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 10; i++) //Fourth layer
		{
			if (paloma - 91 < 5 || paloma - 91 > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + (paloma - 91);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 4, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 10; i++) //Fifth layer
		{
			if (paloma - 103 < 5 || paloma - 103 > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + (paloma - 103);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 5, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 10; i++) //Sixth layer
		{
			if (paloma + 37 < 5 || paloma + 37 > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + (paloma + 37);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 6, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 10; i++) //Seventh layer
		{
			if (paloma + 75 < 5 || paloma + 75 > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + (paloma + 75);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 1, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 10; i++) //Eighth layer
		{
			if (paloma - 30 < 5 || paloma - 30 > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + (paloma - 30);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 2, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 10; i++) //Ninth layer
		{
			if (paloma - 20 < 5 || paloma - 20 > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + (paloma - 20);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 3, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 10; i++) //Tenth layer
		{
			if (paloma + 20 < 5 || paloma + 20 > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + (paloma + 20);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 4, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 10; i++) //Eleventh layer
		{
			if (paloma + 95 < 5 || paloma + 95 > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + (paloma + 95);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 5, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 10; i++) //Twelfth layer
		{
			if (paloma + 123 < 5 || paloma + 123 > 256)
			{
				break;
			}

			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(4) + (paloma + 123);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(DarkStone, 6, 12, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}

		for (int i = 0; i < 12; i++) //Mixed dirt generation, generates to replace dark stone and normal dead dirt
		{
			Xcoord = (chunkX * 16) + random2.nextInt(16);
			Ycoord = random2.nextInt(256);
			Zcoord = (chunkZ * 16) + random2.nextInt(16);
			new WorldGenMinable(TragicBlocks.DeadDirt, 2, 12, DarkStone).generate(world, random2, Xcoord, Ycoord, Zcoord);
		}
	}
}
