package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.structure.Structure;
import cpw.mods.fml.common.IWorldGenerator;

public class StructureWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.isRemote || !world.getWorldInfo().isMapFeaturesEnabled() || random.nextInt(200) <= TragicConfig.structureOverallRarity) return;
		if (!TragicConfig.allowDimension && world.provider.dimensionId == TragicConfig.dimensionID ||
				!TragicConfig.allowDimension && world.provider.dimensionId == TragicConfig.synapseID) return;

		int x = chunkX * 16 + random.nextInt(16);
		int y = random.nextInt(118) + random.nextInt(118) + 10;
		int z = chunkZ * 16 + random.nextInt(16);

		for (Structure s : Structure.structureList)
		{
			if (s != null && s.isValidDimension(world.provider.dimensionId) && s.areCoordsValidForGeneration(world, x, y, z, random, s.getHeight()))
			{
				TragicMC.logInfo("Attempting to generate " + s.getLocalizedName() + "...");
				if (s.generate(world, random, x, y, z))
				{
					TragicMC.logInfo(s.getLocalizedName() + " succesfully generated at " + x + ", " + y + ", " + z);
					break;
				}
			}
		}
	}
}
