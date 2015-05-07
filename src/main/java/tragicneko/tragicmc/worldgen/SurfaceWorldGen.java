package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class SurfaceWorldGen implements IWorldGenerator {

	public final double radius;
	public final double variation;
	public final boolean usesAltGen;
	public final int iterations;
	public final Block block;
	public final int meta;
	public final Block toReplace;
	public final boolean doesAirCheck;
	public final boolean randPerIteration;

	public SurfaceWorldGen(double radius, double var, boolean flag, int relays, Block block, int meta, Block toReplace, boolean flag2, boolean flag3)
	{
		this.radius = radius;
		this.variation = var;
		this.usesAltGen = flag;
		this.iterations = relays;
		this.block = block;
		this.meta = meta;
		this.toReplace = toReplace;
		this.doesAirCheck = flag2;
		this.randPerIteration = flag3;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int x = (chunkX * 16) + random.nextInt(16);
		int z = (chunkZ * 16) + random.nextInt(16);
		int y = world.getTopSolidOrLiquidBlock(x, z);
		double radius = (this.variation * random.nextDouble()) + this.radius;
		ArrayList<int[]> list;
		int[] coords = new int[] {x, y, z};
		Block block;

		for (int y1 = -1; y1 < 2; y1++)
		{
			if (this.randPerIteration)
			{
				x += random.nextInt(4) - random.nextInt(4);
				z += random.nextInt(4) - random.nextInt(4);
			}

			list = WorldHelper.getBlocksInCircularRange(world, radius, x, y + y1, z);

			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				block = world.getBlock(coords[0], coords[1], coords[2]);

				if (this.doesAirCheck && world.getBlock(coords[0], coords[1] + 1, coords[2]).getMaterial() != Material.air) continue;
				if (block == this.toReplace) world.setBlock(coords[0], coords[1], coords[2], this.block, meta, 2);
			}
		}

		for (int k = 0; k < this.iterations && this.usesAltGen; k++)
		{
			block = world.getBlock(coords[0], coords[1], coords[2]);
			list = WorldHelper.getBlocksAdjacent(coords);

			for (int[] cand2 : list)
			{
				block = world.getBlock(cand2[0], cand2[1], cand2[2]);
				if (this.doesAirCheck && world.getBlock(coords[0], coords[1] + 1, coords[2]).getMaterial() != Material.air) continue;
				if (block == this.toReplace && random.nextBoolean()) world.setBlock(cand2[0], cand2[1], cand2[2], this.block, meta, 2);
			}

			coords = list.get(random.nextInt(list.size()));
		}
	}

}
