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
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.structure.Structure;
import cpw.mods.fml.common.IWorldGenerator;

public class FrozenSurfaceWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!(world.provider instanceof TragicWorldProvider) || world.isRemote) return;
		
		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
		
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);
		if (!(biome instanceof BiomeGenFrozenTundra)) return;
		
		Block block;		
		ArrayList<int[]> cands;
		int[] cand = new int[] {Xcoord, Ycoord, Zcoord};
		
		int pow = biome == TragicBiomes.FrozenDepths ? 16 : 4;
		
		for (int i = 0; i < pow; i++)
		{
			Xcoord += random.nextInt(4) - random.nextInt(4);
			Zcoord += random.nextInt(4) - random.nextInt(4);
			Ycoord += random.nextInt(8) - random.nextInt(8);
			
			if (Ycoord < 0 || Ycoord > 256) break;
			block = world.getBlock(Xcoord, Ycoord, Zcoord);

			if (Structure.validBlocks.contains(block) || block.canBeReplacedByLeaves(world, Xcoord, Ycoord, Zcoord) || block.isAir(world, Xcoord, Ycoord, Zcoord))
			{
				if (world.doesBlockHaveSolidTopSurface(world, Xcoord, Ycoord - 1, Zcoord) && !block.getMaterial().isLiquid()) world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.IceSpike);
			}
		}
		
		pow = 8;
		if (biome == TragicBiomes.FrozenHills || world.getBlock(cand[0], cand[1], cand[2]) != TragicBlocks.Permafrost) return;

		for (int k = 0; k < pow + random.nextInt(8); k++)
		{
			world.setBlock(cand[0], cand[1], cand[2], TragicBlocks.Permafrost, 1, 2);
			cands = WorldHelper.getBlocksAdjacent(cand);

			for (int[] cand2 : cands)
			{
				block = world.getBlock(cand2[0], cand2[1], cand2[2]);
				if (block == TragicBlocks.Permafrost) world.setBlock(cand2[0], cand2[1], cand2[2], TragicBlocks.Permafrost, 1, 2);
			}

			cand = cands.get(random.nextInt(cands.size()));
		}
		
	}
}
