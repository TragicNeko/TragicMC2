package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.worldgen.structure.Structure;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class SurfaceWorldGen2 implements IWorldGenerator {

	public final int iterations;
	public final Block block;
	public final int meta;
	public final int width;
	public final int height;

	public SurfaceWorldGen2(int iterations, Block block, int meta, int width, int height)
	{
		this.iterations = iterations;
		this.block = block;
		this.meta = meta;
		this.width = width;
		this.height = height;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

		Block block;		
		ArrayList<int[]> cands;
		int[] cand = new int[] {Xcoord, Ycoord, Zcoord};

		for (int i = 0; i < iterations; i++)
		{
			Xcoord += random.nextInt(width) - random.nextInt(width);
			Zcoord += random.nextInt(width) - random.nextInt(width);
			Ycoord += random.nextInt(height) - random.nextInt(height);

			if (Ycoord < 0 || Ycoord > 256) break;
			block = world.getBlock(Xcoord, Ycoord, Zcoord);

			if (Structure.validBlocks.contains(block) || block.canBeReplacedByLeaves(world, Xcoord, Ycoord, Zcoord) || block.isAir(world, Xcoord, Ycoord, Zcoord))
			{
				if (world.doesBlockHaveSolidTopSurface(world, Xcoord, Ycoord - 1, Zcoord) && !block.getMaterial().isLiquid()) world.setBlock(Xcoord, Ycoord, Zcoord, this.block, meta, 2);
			}
		}
	}

}
