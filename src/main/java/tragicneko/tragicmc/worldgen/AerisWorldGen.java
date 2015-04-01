package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import cpw.mods.fml.common.IWorldGenerator;

public class AerisWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId != 0 || world.isRemote || random.nextInt(4) != 0) return;
		int x;
		int z;
		int y;

		for (int i = 0; i < 16; i++)
		{
			x = (chunkX * 16) + random.nextInt(16);
			z = (chunkZ * 16) + random.nextInt(16);
			y = world.getTopSolidOrLiquidBlock(x, z);
			if (y < 100) continue;
			if (world.getBlock(x, y, z).isReplaceable(world, x, y, z) && world.getBlock(x, y - 1, z).isOpaqueCube())
			{
				world.setBlock(x, y, z, TragicBlocks.Aeris, 0, 2);
				TragicMC.logInfo("Aeris flower placed at " + x + ", " + y + ", " + z);
				break;
			}
		}
	}

}
