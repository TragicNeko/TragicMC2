package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.schematic.SchematicEmpariahCave;

public class StructureEmpariahCave extends Structure {

	public StructureEmpariahCave(int id, String s) {
		super(new SchematicEmpariahCave(), id, s);
	}
	
	@Override
	public boolean canGenerate()
	{
		return TragicConfig.allowBossStructureGen;
	}
	
	@Override
	public boolean isSurfaceStructure()
	{
		return true;
	}

	@Override
	public boolean isValidDimension(int dim)
	{
		return dim == TragicConfig.dimensionID;
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand)
	{		
		if (!(world.getBiomeGenForCoords(x, z) instanceof BiomeGenFrozenTundra) || y < 36) return false;
		return super.areCoordsValidForGeneration(world, x, y, z, rand) && rand.nextInt(200) <= TragicConfig.empariahCaveRarity;
	}
	
	@Override
	public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z)
	{
		if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;	
		return this.schematic.generateStructure(variant, world, rand, x, y, z);
	}

}
