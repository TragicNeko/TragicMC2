package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class InvertedSpikeWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		if (!(world.provider instanceof TragicWorldProvider) || random.nextInt(100) >= TragicConfig.largeSpikeRarity || random.nextInt(6) == 0) return;

		int Xcoord = (chunkX * 16) + random.nextInt(16) - random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16) - random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);

		if (biome != TragicBiomes.TaintedScarlands) return;

		int relays = 4;
		ArrayList<int[]> list;
		Block block;
		double regression = 0.94977745D;
		double cutoff = 0.46943755D;
		double size;
		int spikeType;

		for (int buzza = 0; buzza < relays; buzza++)
		{
			size = random.nextDouble() * 1.5D + 1.5D;
			Xcoord += random.nextInt(8) - random.nextInt(8);
			Zcoord += random.nextInt(8) - random.nextInt(8);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) + 1;

			if (world.getBlock(Xcoord, Ycoord, Zcoord) == Blocks.air) //this ensures that the randomly selected spot to start a spike is valid
			{
				spikeType = random.nextInt(6);

				boolean flag = false;
				boolean flag2 = false;

				for (int y1 = 0; y1 > -128; y1--)
				{
					size *= regression; //reduce the radius of the spike randomly
					if (Ycoord + y1 < 25) break;
					
					if (random.nextBoolean())
					{
						if (random.nextInt(3) == 0 && size >= 0.4888233D) //randomly apply offset to the spike, this sometimes gives it a cool spiral effect
						{
							Xcoord += random.nextInt(2) - random.nextInt(2);
							Zcoord += random.nextInt(2) - random.nextInt(2);
						}

						if (spikeType == 1 && !flag && y1 <= 35 && y1 <= 70 && random.nextBoolean() && size <= 0.774446314D) //Type 1 has chance to thicken at a random spot once
						{
							size *= 2.86333567D;
							flag = true;
						}
					}
					else if (spikeType == 2 && size >= 0.5625292D) //Type 2 has greater chance of offset, making it look more coral-like
					{
						Xcoord += random.nextInt(2) - random.nextInt(2);
						Zcoord += random.nextInt(2) - random.nextInt(2);
					}
					else if (spikeType == 3 && !flag2 && random.nextBoolean() && y1 <= 35 && size <= 1.41115648D && size >= 0.76663601D) //Type 3 has a chance to create a smaller spike near the top
					{
						generateChildSpike(world, random, size * 1.32977745D, Xcoord + random.nextInt(5) - random.nextInt(5), Ycoord + y1, Zcoord + random.nextInt(5) - random.nextInt(5));
						flag2 = true;
					}
					else if (spikeType == 4 && random.nextBoolean() && y1 <= 35 && size >= 0.76663601D) //Type 4 creates a lot of smaller spikes going up the spike
					{
						generateChildSpike(world, random, size * 1.12977745D, Xcoord + random.nextInt(5) - random.nextInt(5), Ycoord + y1, Zcoord + random.nextInt(5) - random.nextInt(5));
					}
					else if (spikeType == 5 && random.nextBoolean()) //Type 5 creates huge spikes at the base, and smaller ones near the top, making for a huge web of spikes (usually)
					{
						if (y1 >= 24)
						{
							generateChildSpike(world, random, size * 1.12977745D, Xcoord + random.nextInt(6) - random.nextInt(6), Ycoord + y1, Zcoord + random.nextInt(6) - random.nextInt(6));
						}
						else if (size >= 0.76663601D)
						{
							generateChildSpike(world, random, size * 1.13977745D, Xcoord + random.nextInt(5) - random.nextInt(5), Ycoord + y1, Zcoord + random.nextInt(5) - random.nextInt(5));
						}
					}

					if (size < cutoff || Ycoord + y1 <= 0) break;
					
					list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1, Zcoord);

					for (int[] coords : list)
					{
						block = world.getBlock(coords[0], coords[1], coords[2]);
						if (StructureWorldGen.validBlocks.contains(block)) world.setBlockToAir(coords[0], coords[1], coords[2]);
					}
				}


			}
		}

	}

	public static void generateChildSpike(World world, Random rand, double size, double Xcoord, double Ycoord, double Zcoord)
	{
		ArrayList<int[]> list;
		Block block;

		for (int y1 = 0; y1 < 64; y1++)
		{
			if (rand.nextBoolean())
			{
				size *= 0.95977745D;

				if (size >= 0.54288233D)
				{
					Xcoord += rand.nextInt(2) - rand.nextInt(2);
					Zcoord += rand.nextInt(2) - rand.nextInt(2);
				}
			}

			if (size < 0.36443755D || Ycoord - y1 < 0) break;

			list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1, Zcoord);

			for (int[] coords : list)
			{
				block = world.getBlock(coords[0], coords[1], coords[2]);
				if (StructureWorldGen.validBlocks.contains(block)) world.setBlockToAir(coords[0], coords[1], coords[2]);
			}
		}
	}
}
