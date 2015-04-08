package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCorrodedSteppe;
import cpw.mods.fml.common.IWorldGenerator;

public class ExplosiveGasWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!(world.provider instanceof TragicWorldProvider) || world.isRemote) return;

		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = random.nextInt(236) + 10;
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);
		ArrayList<int[]> list;

		if (!(biome instanceof BiomeGenCorrodedSteppe)) return;

		list = WorldHelper.getBlocksInSphericalRange(world, 3.75, Xcoord, Ycoord, Zcoord);
		Block block;
		boolean flag = true;

		for (int[] coords : list)
		{
			block = world.getBlock(coords[0], coords[1], coords[2]);
			if (!block.isOpaqueCube())
			{
				flag = false;
				break;
			}
		}

		if (flag)
		{
			list = WorldHelper.getBlocksInSphericalRange(world, 2.75, Xcoord, Ycoord, Zcoord);

			for (int[] coords : list) world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.ExplosiveGas, 0, 2);
		}
	}

}
