package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.blocks.BlockGenericTallGrass;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class RuggedTerrainWorldGen implements IWorldGenerator {

	public final Block block;
	public final int meta;
	public final int iterations;
	public final double radius;
	public final double variation;
	public final boolean replacesAir;
	public final Block toReplace;
	public final int width;

	public RuggedTerrainWorldGen(Block block, int meta, Block toReplace, int iterations, double radius, double var, boolean flag, int width)
	{
		this.block = block;
		this.meta = meta; //1 for dirt, 2 for eroded stone
		this.toReplace = toReplace;
		this.iterations = iterations; //4
		this.radius = radius; //4.0
		this.variation = var; // 2.0
		this.replacesAir = flag; //tainted only
		this.width = width; //8
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int x = (chunkX * 16) + random.nextInt(16);
		int z = (chunkZ * 16) + random.nextInt(16);
		int y = world.getTopSolidOrLiquidBlock(x, z);

		double radius;
		ArrayList<int[]> list;
		int[] coords;
		Block block;

		for (int pow = 0; pow < this.iterations; pow++)
		{
			x += random.nextInt(width) - random.nextInt(width);
			z += random.nextInt(width) - random.nextInt(width);
			radius = (variation * random.nextDouble()) + this.radius;

			for (int y1 = -1; y1 < 2; y1++)
			{
				list = WorldHelper.getBlocksInCircularRange(world, radius, x, y + y1, z);

				for (int i = 0; i < list.size(); i++)
				{
					coords = list.get(i);
					if (random.nextInt(16) != 0) continue;

					block = world.getBlock(coords[0], coords[1], coords[2]);
					if (!world.getBlock(coords[0], coords[1] - 1, coords[2]).isOpaqueCube()) continue;

					if (block == this.toReplace || block.getMaterial() == Material.air && this.replacesAir || block instanceof BlockGenericTallGrass)
					{
						world.setBlock(coords[0], coords[1], coords[2], this.block, meta, 2);
					}
				}
			}

		}
	}

}
