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
	
	public final Block block;
	public final int meta;
	public final int depth;
	public final int depthVar;
	public final double radius;
	public final double variation;
	
	public PitWorldGen(Block block, int meta, int depth, int depthVar, double radius, double variation)
	{
		this.block = block;
		this.meta = meta;
		this.depth = depth;
		this.depthVar = depthVar;
		this.radius = radius;
		this.variation = variation;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) - random.nextInt(10);
		int depth = Ycoord - this.depth - random.nextInt(this.depthVar + 1);

		double size;
		int[] coords;
		ArrayList<int[]> list;
		ArrayList<int[]> cands = new ArrayList<int[]>();

		size = this.variation * random.nextDouble() + this.radius;

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

		for (int[] coords2 : cands)
		{
			if (coords2[1] > depth + 1) world.setBlockToAir(coords2[0], coords2[1], coords2[2]);
			else world.setBlock(coords2[0], coords2[1], coords2[2], this.block, meta, 2);
		}
	}
}
