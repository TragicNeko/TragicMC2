package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.worldgen.biome.BiomeGenHallowedHills;
import cpw.mods.fml.common.IWorldGenerator;

public class StringLightWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!(world.provider instanceof TragicWorldProvider) || world.isRemote) return;

		int Xcoord = (chunkX * 16);
		int Zcoord = (chunkZ * 16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) + random.nextInt(8) + 4;
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);

		if (!(biome instanceof BiomeGenHallowedHills)) return;

		Block block;
		ArrayList<int[]> cands = new ArrayList<int[]>();
		int[] cand;
		int l = random.nextInt(16);

		for (int i = 0; i < 20 + l; i++)
		{
			Xcoord += random.nextInt(2) - random.nextInt(2);
			Zcoord += random.nextInt(2) - random.nextInt(2);
			Ycoord += random.nextInt(2);
			cand = new int[] {Xcoord, Ycoord, Zcoord};
			if (cands.contains(cand)) continue;
			block = world.getBlock(Xcoord, Ycoord, Zcoord);

			if (StructureWorldGen.validBlocks.contains(block) || block.canBeReplacedByLeaves(world, Xcoord, Ycoord, Zcoord) || block.isAir(world, Xcoord, Ycoord, Zcoord)) cands.add(cand);
		}

		for (int[] coords : cands)
		{
			world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.StringLight);
		}
		
		Xcoord = (chunkX * 16) + random.nextInt(16);
		Zcoord = (chunkZ * 16) + random.nextInt(16);
		Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) + random.nextInt(8) + 4;
		
		for (int i = 0; i < 6; i++)
		{
			Xcoord += random.nextInt(4) - random.nextInt(4);
			Zcoord += random.nextInt(4) - random.nextInt(4);
			Ycoord += random.nextInt(8);
			block = world.getBlock(Xcoord, Ycoord, Zcoord);

			if (StructureWorldGen.validBlocks.contains(block) || block.canBeReplacedByLeaves(world, Xcoord, Ycoord, Zcoord) || block.isAir(world, Xcoord, Ycoord, Zcoord))
			{
				world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.FragileLight);
				break;
			}
		}
		
		Xcoord = (chunkX * 16) + random.nextInt(16);
		Zcoord = (chunkZ * 16) + random.nextInt(16);
		Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
		
		for (int i = 0; i < 4; i++)
		{
			Xcoord += random.nextInt(4) - random.nextInt(4);
			Zcoord += random.nextInt(4) - random.nextInt(4);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
			block = world.getBlock(Xcoord, Ycoord, Zcoord);

			if (StructureWorldGen.validBlocks.contains(block) || block.canBeReplacedByLeaves(world, Xcoord, Ycoord, Zcoord) || block.isAir(world, Xcoord, Ycoord, Zcoord))
			{
				if (world.doesBlockHaveSolidTopSurface(world, Xcoord, Ycoord - 1, Zcoord) && !block.getMaterial().isLiquid()) world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.Light);
			}
		}
	}

}
