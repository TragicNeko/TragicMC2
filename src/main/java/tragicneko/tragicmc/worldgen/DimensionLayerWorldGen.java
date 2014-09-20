package tragicneko.tragicmc.worldgen;

import static tragicneko.tragicmc.main.TragicBlocks.DarkStone;

import java.util.Map;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class DimensionLayerWorldGen implements IWorldGenerator {

	int[] modifiers = new int[] {-61, -47, 0, 37, 75, -30, -20, 20, 95, -88};

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!(world.provider instanceof TragicWorldProvider) || world.isRemote) return;

		int paloma = WorldHelper.getWorldDependency(world);
		paloma = Math.abs(paloma);

		int Xcoord = (chunkX * 16);
		int Zcoord = (chunkZ * 16);

		for (int r = -1; r < 2; r++)
		{
			for (int p = 0; p < modifiers.length; p++)
			{
				for (int j = 0; j < 17; j++)
				{
					for (int k = 0; k < 17; k++)
					{
						if (world.getBlock(Xcoord + j, paloma + modifiers[p] + r, Zcoord + k) == DarkStone && random.nextInt(4) == 0)
						{
							world.setBlock(Xcoord + j, paloma + modifiers[p] + r, Zcoord + k, DarkStone, (p % 5) + 1, 2);
						}
					}
				}
			}
		}

		for (int i = 0; i < 6; i++)
		{
			Xcoord = (chunkX * 16) + random.nextInt(16);
			int Ycoord = random.nextInt(256);
			Zcoord = (chunkZ * 16) + random.nextInt(16);
			new WorldGenMinable(TragicBlocks.DeadDirt, 2, 6, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
	}
}
