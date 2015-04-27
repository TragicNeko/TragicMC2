package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCorrodedSteppe;
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import cpw.mods.fml.common.IWorldGenerator;

public class PitWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) - random.nextInt(10);
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);

		if (!(biome instanceof BiomeGenFrozenTundra) && !(biome instanceof BiomeGenScorchedWasteland)
				&& !(biome instanceof BiomeGenCorrodedSteppe)) return;

		int depth = Ycoord - 10 - random.nextInt(10);

		double size;
		int[] coords;
		ArrayList<int[]> list;
		ArrayList<int[]> cands = new ArrayList<int[]>();

		size = 3.0D * random.nextDouble() + 3.0D;

		for (int pow = 0; pow + Ycoord >= depth && pow + Ycoord >= 0 && pow + Ycoord <= 256; --pow)
		{
			if (size >= 5.5D)
			{
				list = WorldHelper.getBlocksInCircularRange(world, size * 0.31773D, Xcoord, Ycoord + pow, Zcoord); //makes sure the middle of the pit is clear

				for (int mapping = 0; mapping < list.size(); mapping++)
				{
					coords = list.get(mapping);
					if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
				} 
			}

			list = WorldHelper.getBlocksInCircularRange(world, size * 0.64773D, Xcoord, Ycoord + pow, Zcoord); //gives the pit more of a gradual feel

			for (int mapping = 0; mapping < list.size(); mapping++)
			{
				coords = list.get(mapping);
				if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
			} 

			list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + pow, Zcoord); //outer part that has the most scattered blocks

			for (int mapping = 0; mapping < list.size(); mapping++)
			{
				coords = list.get(mapping);
				if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
			}

			if (size >= 3.0D && random.nextInt(4) == 0) size *= 0.987425D; //reduces size of the void pit randomly, similarly to spikes, but this is to reduce lag
		}

		Block block = biome instanceof BiomeGenFrozenTundra ? TragicBlocks.IceSpike : (biome instanceof BiomeGenScorchedWasteland ? Blocks.flowing_lava : ( random.nextBoolean() ? TragicBlocks.RadiatedGas : TragicBlocks.Quicksand));

		for (int[] coords2 : cands)
		{
			if (coords2[1] > depth + 1) world.setBlockToAir(coords2[0], coords2[1], coords2[2]);
			else world.setBlock(coords2[0], coords2[1], coords2[2], block, block == TragicBlocks.Quicksand ? 3 : 0, 3);
		}
	}
}
