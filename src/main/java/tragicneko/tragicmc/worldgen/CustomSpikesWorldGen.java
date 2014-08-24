package tragicneko.tragicmc.worldgen;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import scala.collection.mutable.Set;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.main.TragicBiomes;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import cpw.mods.fml.common.IWorldGenerator;

public class CustomSpikesWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		if (!(world.provider instanceof TragicWorldProvider) || random.nextInt(100) >= TragicNewConfig.largeSpikeRarity) return;

		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);

		HashSet set = (HashSet) TragicBiomes.decayingBiomes;
		set.add(TragicBiomes.TaintedSpikes);

		if (!set.contains(biome) || !WorldHelper.validBlocksForDimension.contains(world.getBlock(Xcoord, Ycoord - 1, Zcoord))) return;

		if (biome instanceof BiomeGenDecayingWasteland)
		{
			if (random.nextInt(4) != 0) return;
		}
		else
		{
			if (!TragicNewConfig.allowLargeSpikeGen) return;
		}

		int relays = biome instanceof BiomeGenDecayingWasteland ? 4 : 8;
		Block spike = biome instanceof BiomeGenDecayingWasteland ? TragicBlocks.BoneBlock : TragicBlocks.DarkStone;
		int meta = biome instanceof BiomeGenDecayingWasteland ? random.nextInt(2) : 14;
		Map<Integer, int[]> map;
		int[] coords;
		Block block;		
		double regression = 0.95977745D;
		double cutoff = 0.36943755D;
		double size;
		int spikeType;

		for (int buzza = 0; buzza < relays; buzza++)
		{
			size = random.nextDouble() + 1.5D;
			Xcoord += random.nextInt(8) - random.nextInt(8);
			Zcoord += random.nextInt(8) - random.nextInt(8);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

			if (WorldHelper.validBlocksForDimension.contains(world.getBlock(Xcoord, Ycoord - 1, Zcoord))) //this ensures that the randomly selected spot to start a spike is valid
			{
				if (TragicBiomes.decayingBiomes.contains(biome)) //faster regression and higher cutoff means the spikes generate shorter and "fatter"
				{
					size = 0.35D * random.nextDouble() + 1.0D;
					regression = 0.89477735D;
					cutoff = 0.441114525D;
				}
				else if (world.getWorldInfo().getTerrainType() == WorldType.AMPLIFIED) //if amplified and not decaying biome, these can be almost 130 blocks tall
				{
					size *= 1.25D;
					regression = 0.9883242287D;
				}

				spikeType = biome instanceof BiomeGenDecayingWasteland? random.nextInt(2) : random.nextInt(6);

				boolean flag = false;
				boolean flag2 = false;

				for (int y1 = 0; y1 < 128; y1++)
				{
					if (random.nextBoolean() && biome == TragicBiomes.TaintedSpikes)
					{
						size *= regression; //reduce the radius of the spike randomly

						if (random.nextInt(3) == 0 && size >= 0.4888233D) //randomly apply offset to the spike, this sometimes gives it a cool spiral effect
						{
							Xcoord += random.nextInt(2) - random.nextInt(2);
							Zcoord += random.nextInt(2) - random.nextInt(2);
						}

						if (spikeType == 1 && !flag && y1 >= 35 && y1 <= 70 && random.nextBoolean() && size <= 0.774446314D) //Type 1 has chance to thicken at a random spot once
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
					else if (spikeType == 3 && !flag2 && random.nextBoolean() && y1 >= 35 && size <= 1.41115648D && size >= 0.76663601D) //Type 3 has a chance to create a smaller spike near the top
					{
						generateChildSpike(world, random, size * 1.32977745D, Xcoord + random.nextInt(5) - random.nextInt(5), Ycoord + y1, Zcoord + random.nextInt(5) - random.nextInt(5), spike, meta);
						flag2 = true;
					}
					else if (spikeType == 4 && random.nextBoolean() && y1 >= 35 && size >= 0.76663601D) //Type 4 creates a lot of smaller spikes going up the spike
					{
						generateChildSpike(world, random, size * 1.12977745D, Xcoord + random.nextInt(5) - random.nextInt(5), Ycoord + y1, Zcoord + random.nextInt(5) - random.nextInt(5), spike, meta);
					}
					else if (spikeType == 5 && random.nextBoolean()) //Type 5 creates huge spikes at the base, and smaller ones near the top, making for a huge web of spikes (usually)
					{
						if (y1 <= 24)
						{
							generateChildSpike(world, random, size * 1.12977745D, Xcoord + random.nextInt(6) - random.nextInt(6), Ycoord + y1, Zcoord + random.nextInt(6) - random.nextInt(6), spike, meta);
						}
						else if (size >= 0.76663601D)
						{
							generateChildSpike(world, random, size * 1.13977745D, Xcoord + random.nextInt(5) - random.nextInt(5), Ycoord + y1, Zcoord + random.nextInt(5) - random.nextInt(5), spike, meta);
						}
					}
					
					if (biome instanceof BiomeGenDecayingWasteland) size *= regression; //makes sure it's done every y-level if in decaying biomes

					if (size < cutoff || Ycoord + y1 > 256) break;

					map = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1, Zcoord);

					for (int j = 0; j < map.size(); j++)
					{
						coords = map.get(j);
						block = world.getBlock(coords[0], coords[1], coords[2]);

						if (StructureWorldGen.validBlocks.contains(block))
						{
							world.setBlock(coords[0], coords[1], coords[2], spike, meta, 2);
						}
					}
				}
			}
		}

	}

	public static void generateChildSpike(World world, Random rand, double size, double Xcoord, double Ycoord, double Zcoord, Block spike, int meta)
	{
		Map<Integer, int[]> map;
		int[] coords;
		Block block;

		for (int y1 = 0; y1 < 128; y1++)
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

			if (size < 0.36443755D || Ycoord + y1 > 256) break;

			map = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1, Zcoord);

			for (int j = 0; j < map.size(); j++)
			{
				coords = map.get(j);
				block = world.getBlock(coords[0], coords[1], coords[2]);

				if (StructureWorldGen.validBlocks.contains(block))
				{
					world.setBlock(coords[0], coords[1], coords[2], spike, meta, 2);
				}
			}
		}
	}
}
