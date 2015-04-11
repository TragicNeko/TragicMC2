package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import tragicneko.tragicmc.worldgen.structure.Structure;
import cpw.mods.fml.common.IWorldGenerator;

public class GeyserWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!(world.provider instanceof TragicWorldProvider) || world.isRemote) return;

		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);

		if (!(biome instanceof BiomeGenScorchedWasteland)) return;
		
		int mew = 6;
		if (biome == TragicBiomes.ScorchedScarlands) mew = 12;
		ArrayList<int[]> cands = new ArrayList<int[]>();
		Block block;
		
		for (int i = 0; i < mew; i++)
		{
			Xcoord = (chunkX * 16) + random.nextInt(16);
			Zcoord = (chunkZ * 16) + random.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
			biome = world.getBiomeGenForCoords(Xcoord, Zcoord);
			if (!(biome instanceof BiomeGenScorchedWasteland)) continue;
			
			block = world.getBlock(Xcoord, Ycoord, Zcoord);
			
			if (Structure.validBlocks.contains(block) && block.isReplaceable(world, Xcoord, Ycoord, Zcoord) && random.nextInt(4) == 0)
			{
				cands.clear();
				cands.addAll(WorldHelper.getBlocksInSphericalRange(world, 2.75, Xcoord, Ycoord - 2, Zcoord));
				
				for (int[] coords : cands)
				{
					block = world.getBlock(coords[0], coords[1], coords[2]);
					if (block.isReplaceable(world, coords[0], coords[1], coords[2]))
					{
						if (coords[1] <= Ycoord - 2)
						{
							world.setBlock(coords[0], coords[1], coords[2], Blocks.lava);
						}
						else
						{
							world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.MoltenRock);
						}
					}
				}
				
				world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.Geyser);
				//TragicMC.logInfo("Geyser generated at coords " + Xcoord + ", " + (Ycoord + 2) + ", " + Zcoord);
			}
		}
	}

}
