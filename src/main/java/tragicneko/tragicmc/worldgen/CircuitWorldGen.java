package tragicneko.tragicmc.worldgen;

import java.util.Random;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class CircuitWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		Chunk chk = world.getChunkFromChunkCoords(chunkX, chunkZ);
		for (int y1 = 0; y1 < 128; y1++)
		{
			double m = (2.5D + (Math.cos((y1 + 6) / 20.0D)) * 2.5);
			int meta = MathHelper.clamp_int((int) Math.round(m), 0, 4);

			for (int x1 = 0; x1 < 16; x1++)
			{
				for (int z1 = 0; z1 < 16; z1++)
				{
					if (chk.getBlock(x1, y1, z1) == TragicBlocks.CircuitBlock) chk.setBlockMetadata(x1, y1, z1, meta);
					
					if (y1 > 5 && y1 < 123)
					{
						int yr = y1 + random.nextInt(6) - random.nextInt(6);
						if (chk.getBlock(x1, yr, z1) == TragicBlocks.CircuitBlock) chk.setBlockMetadata(x1, yr, z1, meta);
					}
				}
			}
		}
	}

}
