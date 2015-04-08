package tragicneko.tragicmc.worldgen;

import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.worldgen.schematic.SchematicApisTemple;
import tragicneko.tragicmc.worldgen.structure.Structure;
import tragicneko.tragicmc.worldgen.structure.StructureApisTemple;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.IWorldGenerator;

public class StructureWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.isRemote || !world.getWorldInfo().isMapFeaturesEnabled() || random.nextInt(200) >= TragicConfig.structureOverallRarity) return;

		int x = chunkX * 16 + random.nextInt(16);
		int y = chunkZ * 16 + random.nextInt(16);
		int z = random.nextInt(256);

		for (Structure s : Structure.structureList)
		{
			if (s != null && s.isValidDimension(world.provider.dimensionId) && s.areCoordsValidForGeneration(world, x, y, z, random, s.getWidth(), s.getHeight()))
			{
				s.generate(world, random, x, y, z);
				break;
			}
		}
	}
}
