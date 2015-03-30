package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class BoulderWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!(world.provider instanceof TragicWorldProvider) || world.isRemote) return;

		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);

		if (!(biome instanceof BiomeGenScorchedWasteland)) return;
		
		int mew = 2;
		if (biome == TragicBiomes.ScorchedWastelands) mew = 8;
		ArrayList<int[]> cands = new ArrayList<int[]>();
		Block block;
		
		for (int i = 0; i < mew; i++)
		{
			Xcoord = (chunkX * 16) + random.nextInt(16);
			Zcoord = (chunkZ * 16) + random.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
			
			block = world.getBlock(Xcoord, Ycoord, Zcoord);
			
			if (block.isReplaceable(world, Xcoord, Ycoord, Zcoord) && random.nextInt(4) == 0)
			{
				cands.clear();
				cands.addAll(WorldHelper.getBlocksInSphericalRange(world, (random.nextDouble() * 1.25) + 1.0, Xcoord, Ycoord - 2, Zcoord));
				
				for (int[] coords : cands)
				{
					block = world.getBlock(coords[0], coords[1], coords[2]);
					if (block.isReplaceable(world, coords[0], coords[1], coords[2]))
					{
						world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.ScorchedRock);
					}
				}
			}
		}
	}

}
