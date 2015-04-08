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

public class CorrodedSurfaceWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!(world.provider instanceof TragicWorldProvider) || world.isRemote) return;
		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
		
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);
		if (!(biome instanceof BiomeGenCorrodedSteppe)) return;
		
		Block block;		
		ArrayList<int[]> cands;
		int[] cand = new int[] {Xcoord, Ycoord, Zcoord};
		
		for (int i = 0; i < 12; i++)
		{
			Xcoord += random.nextInt(4) - random.nextInt(4);
			Zcoord += random.nextInt(4) - random.nextInt(4);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
			block = world.getBlock(Xcoord, Ycoord, Zcoord);

			if (StructureWorldGen.validBlocks.contains(block) || block.canBeReplacedByLeaves(world, Xcoord, Ycoord, Zcoord) || block.isAir(world, Xcoord, Ycoord, Zcoord))
			{
				if (world.doesBlockHaveSolidTopSurface(world, Xcoord, Ycoord - 1, Zcoord) && !block.getMaterial().isLiquid()) world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.RadiatedGas);
			}
		}

		boolean flag = false;

		for (int k = 0; k < 16 + random.nextInt(8); k++)
		{
			block = world.getBlock(cand[0], cand[1], cand[2]);
			if (block != TragicBlocks.DarkStone) break;

			world.setBlock(cand[0], cand[1], cand[2], TragicBlocks.Quicksand, 3, 2);
			cands = WorldHelper.getBlocksAdjacent(cand);

			for (int[] cand2 : cands)
			{
				block = world.getBlock(cand2[0], cand2[1], cand2[2]);
				if (block == TragicBlocks.DarkStone && random.nextBoolean()) world.setBlock(cand2[0], cand2[1], cand2[2], TragicBlocks.Quicksand, 3, 2);
			}

			cand = cands.get(random.nextInt(cands.size()));
		}
		
		
	}

}
