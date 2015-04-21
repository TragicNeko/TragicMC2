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

public class ScorchedSurfaceWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!(world.provider instanceof TragicWorldProvider) || world.isRemote) return;

		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) - 1;
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);

		if (!(biome instanceof BiomeGenScorchedWasteland)) return;

		int mew = biome == TragicBiomes.ScorchedScarlands ? 8 : 2;
		ArrayList<int[]> cands = new ArrayList<int[]>();
		Block block;

		for (int i = 0; i < mew; i++)
		{
			Xcoord = (chunkX * 16) + random.nextInt(16);
			Zcoord = (chunkZ * 16) + random.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) - 1;
			biome = world.getBiomeGenForCoords(Xcoord, Zcoord);
			if (!(biome instanceof BiomeGenScorchedWasteland)) continue;

			block = world.getBlock(Xcoord, Ycoord, Zcoord);
			if (block == TragicBlocks.MoltenRock && random.nextInt(4) == 0)
			{
				world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.Geyser);
				world.setBlock(Xcoord, Ycoord - 1, Zcoord, Blocks.lava);
				TragicMC.logInfo("Geyser generated at coords " + Xcoord + ", " + Ycoord + ", " + Zcoord);
			}
		}
		
		mew = biome == TragicBiomes.ScorchedScarlands ? 10 : 5;

		for (int i = 0; i < mew; i++)
		{
			Xcoord = (chunkX * 16) + random.nextInt(16);
			Zcoord = (chunkZ * 16) + random.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) - 1;
			biome = world.getBiomeGenForCoords(Xcoord, Zcoord);
			if (!(biome instanceof BiomeGenScorchedWasteland)) continue;
			
			block = world.getBlock(Xcoord, Ycoord, Zcoord);
			if (block == TragicBlocks.MoltenRock && random.nextInt(4) == 0)
			{
				world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.SteamVent);
				TragicMC.logInfo("Steam vent placed at coords " + Xcoord + ", " + Ycoord + ", " + Zcoord);
			}
		}
		
		mew = biome == TragicBiomes.ScorchedWastelands ? 8 : 2;
		
		for (int i = 0; i < mew; i++)
		{
			Xcoord = (chunkX * 16) + random.nextInt(16);
			Zcoord = (chunkZ * 16) + random.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) - 1;
			
			block = world.getBlock(Xcoord, Ycoord, Zcoord);
			
			if (block == TragicBlocks.MoltenRock && random.nextInt(4) == 0)
			{
				cands.clear();
				cands.addAll(WorldHelper.getBlocksInSphericalRange(world, (random.nextDouble() * 2.25) + 1.5, Xcoord, Ycoord - 1, Zcoord));
				
				for (int[] coords : cands)
				{
					block = world.getBlock(coords[0], coords[1], coords[2]);
					if (block.isReplaceable(world, coords[0], coords[1], coords[2]))
					{
						world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.ScorchedRock);
					}
				}
				TragicMC.logInfo("Boulder placed at coords: " + Xcoord + ", " + Ycoord + ", " + Zcoord);
			}
		}
	}

}
