package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import tragicneko.tragicmc.worldgen.structure.Structure;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class SteamVentWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!(world.provider instanceof TragicWorldProvider) || world.isRemote) return;

		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);

		if (!(biome instanceof BiomeGenScorchedWasteland)) return;

		int mew = 5;
		if (biome == TragicBiomes.ScorchedScarlands) mew = 10;
		Block block;

		for (int i = 0; i < mew; i++)
		{
			Xcoord = (chunkX * 16) + random.nextInt(16);
			Zcoord = (chunkZ * 16) + random.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

			block = world.getBlock(Xcoord, Ycoord, Zcoord);
			if (Structure.validBlocks.contains(block) && block.isReplaceable(world, Xcoord, Ycoord, Zcoord) && random.nextInt(4) == 0)
			{
				world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.SteamVent);
				TragicMC.logInfo("Steam vent placed at coords " + Xcoord + ", " + Ycoord + ", " + Zcoord);
			}
		}

	}

}
