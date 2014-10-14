package tragicneko.tragicmc.worldgen;

import static tragicneko.tragicmc.main.TragicBlocks.DarkStone;
import java.util.Map;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockDarkStone;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class DimensionLayerWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!(world.provider instanceof TragicWorldProvider) || world.isRemote) return;

		int paloma = WorldHelper.getWorldDependency(world);
		int[] modifiers = new int[] {-61, -47, 0, 37, 75, -30, -20, 20, 95, -88};
		int j = random.nextInt(16);
		int k = random.nextInt(16);

		for (int p = 0; p < modifiers.length; p++)
		{
			for (int r = -1; r < 2; r++)
			{
				if (paloma + modifiers[p] + r > 220 || paloma + modifiers[p] + r < 5) break;
				
				for (int pew = 0; pew < 48; pew++)
				{
					j = random.nextInt(16);
					k = random.nextInt(16);
					if (world.getBlock((chunkX * 16) + j, paloma + modifiers[p] + r, (chunkZ * 16) + k) == DarkStone && random.nextInt(4) == 0)
					{
						world.setBlock((chunkX * 16) + j, paloma + modifiers[p] + r, (chunkZ * 16) + k, DarkStone, (p % 5) + 1, 2);
					}
				}
			}
		}

		for (int i = 0; i < 2; i++)
		{
			int Xcoord = (chunkX * 16) + random.nextInt(16);
			int Ycoord = random.nextInt(215) + 5;
			int Zcoord = (chunkZ * 16) + random.nextInt(16);
			new WorldGenMinable(TragicBlocks.DeadDirt, 2, 8, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
	}
}
