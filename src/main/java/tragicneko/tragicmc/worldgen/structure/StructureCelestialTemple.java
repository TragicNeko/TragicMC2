package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.SchematicCelestialTemple;

public class StructureCelestialTemple extends StructureBoss {

	public StructureCelestialTemple(int id, String name) {
		super(new SchematicCelestialTemple(), id, name);
	}

	@Override
	public int getVariantSize()
	{
		return 3;
	}

	@Override
	public boolean isValidDimension(int dim)
	{
		return dim == TragicConfig.dimensionID;
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand)
	{
		if (y < 128 || rand.nextInt(50) != 0) return false; //enforced due to how much open space there is for it to generate normally
		return super.areCoordsValidForGeneration(world, x, y, z, rand) && rand.nextInt(200) <= TragicConfig.celestialTempleRarity;
	}

	@Override
	public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z)
	{
		if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
		return this.schematic.generateStructure(variant, world, rand, x, y, z);
	}
}
