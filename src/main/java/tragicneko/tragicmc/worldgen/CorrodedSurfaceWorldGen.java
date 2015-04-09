package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCorrodedSteppe;
import tragicneko.tragicmc.worldgen.structure.Structure;
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
		boolean flag = biome == TragicBiomes.CorrodedRunoff || biome == TragicBiomes.CorrodedFallout;
		boolean flag2 = biome == TragicBiomes.CorrodedSteppe || biome == TragicBiomes.CorrodedFallout;
		boolean flag3 = biome == TragicBiomes.CorrodedHeights || biome == TragicBiomes.CorrodedVeld;
		
		int pow = flag2 ? 32 : (flag ? 16 : 4);
		
		for (int i = 0; i < pow; i++)
		{
			Xcoord += random.nextInt(4) - random.nextInt(4);
			Zcoord += random.nextInt(4) - random.nextInt(4);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
			block = world.getBlock(Xcoord, Ycoord, Zcoord);

			if (Structure.validBlocks.contains(block) || block.canBeReplacedByLeaves(world, Xcoord, Ycoord, Zcoord) || block.isAir(world, Xcoord, Ycoord, Zcoord))
			{
				if (world.doesBlockHaveSolidTopSurface(world, Xcoord, Ycoord - 1, Zcoord) && !block.getMaterial().isLiquid()) world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.RadiatedGas);
			}
		}
		
		pow = flag3 ? 32 : (flag2 ? 4 : 16);
		
		for (int l = 0; l < pow + random.nextInt(4); l++)
		{
			Xcoord = (chunkX * 16) + random.nextInt(8) - random.nextInt(8);
			Zcoord = (chunkZ * 16) + random.nextInt(8) - random.nextInt(8);
			Ycoord = 40 + random.nextInt(100);
			new WorldGenWickedVine().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		
		pow = flag ? 32 : (flag2 ? 4 : 16);

		for (int k = 0; k < pow + random.nextInt(8); k++)
		{
			world.setBlock(cand[0], cand[1], cand[2], TragicBlocks.Quicksand, 3, 2);
			cands = WorldHelper.getBlocksAdjacent(cand);

			for (int[] cand2 : cands)
			{
				block = world.getBlock(cand2[0], cand2[1], cand2[2]);
				if (block.isReplaceable(world, cand2[0], cand2[1], cand2[2]) && Structure.validBlocks.contains(block) && block.getMaterial() != Material.air && !block.getMaterial().isLiquid() && random.nextBoolean() && World.doesBlockHaveSolidTopSurface(world, cand2[0], cand2[1] - 1, cand2[2])) world.setBlock(cand2[0], cand2[1], cand2[2], TragicBlocks.Quicksand, 3, 2);
			}

			cand = cands.get(random.nextInt(cands.size()));
		}
		
	}

}
