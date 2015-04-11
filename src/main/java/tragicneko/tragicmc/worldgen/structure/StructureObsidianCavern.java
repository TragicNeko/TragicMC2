package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.SchematicObsidianCavern;

public class StructureObsidianCavern extends Structure {

	public StructureObsidianCavern(int id, String name) {
		super(new SchematicObsidianCavern(), id, name);
	}
	
	@Override
	public boolean canGenerate()
	{
		return TragicConfig.allowNonBossStructureGen;
	}
	
	@Override
	public int getVariantSize()
	{
		return 10;
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
	public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand, int height)
	{		
		return super.areCoordsValidForGeneration(world, x, y, z, rand, 8) && rand.nextInt(200) <= TragicConfig.obsidianCavernRarity; //checks smaller radius due to the nature of the obsidian cavern schematic
	}
	
	@Override
	public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z)
	{
		if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;	
		return this.schematic.generateStructure(variant, world, rand, x, y, z);
	}
}
