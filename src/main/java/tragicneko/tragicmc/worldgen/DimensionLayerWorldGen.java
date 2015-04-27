package tragicneko.tragicmc.worldgen;

import static tragicneko.tragicmc.TragicBlocks.DarkStone;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class DimensionLayerWorldGen implements IWorldGenerator {
	
	private static final int[] modifiers = new int[] {-88, -61, -47, -30, -20, 0, 20, 37, 75, 95};

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		int paloma = WorldHelper.getWorldDependency(world);
		int x = chunkX * 16;
		int z = chunkZ * 16;
		
		int j;
		int k;
		int meta;
		int y;
		int p;

		label1: for (int m = 0; m < modifiers.length; m++)
		{
			meta = (m % 5) + 1;
			p = modifiers[m];
			
			for (int r = -1; r < 2; r++)
			{
				y = paloma + p + r;
				if (y < 10) break;
				if (y > 200) break label1;
				
				for (int pew = 0; pew < 48; pew++)
				{
					j = random.nextInt(16);
					k = random.nextInt(16);
					if (world.getBlock(x + j, y, z + k) == DarkStone) world.setBlock(x + j, y, z + k, DarkStone, meta, 2);
				}
			}
		}

		for (int i = 0; i < 4; i++)
		{
			int Xcoord = x + random.nextInt(16);
			int Ycoord = random.nextInt(205) + 10;
			int Zcoord = z + random.nextInt(16);
			if (world.getBlockMetadata(Xcoord, Ycoord, Zcoord) == 0) new WorldGenMinable(TragicBlocks.DeadDirt, 2, 8, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
	}
}
