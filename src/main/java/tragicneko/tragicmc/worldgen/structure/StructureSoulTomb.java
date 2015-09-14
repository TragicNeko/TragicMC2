package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.SchematicSoulTomb;

public class StructureSoulTomb extends Structure {

	public StructureSoulTomb(int id, String s) {
		super(new SchematicSoulTomb(), id, s);
	}

	@Override
	public int getVariantSize()
	{
		return SchematicSoulTomb.blocks.length;
	}

	@Override
	public boolean isValidDimension(int dim)
	{
		return dim == TragicConfig.collisionID;
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand)
	{
		if (y >= 62 || rand.nextInt(8) != 0 || world.getTopSolidOrLiquidBlock(x, z) < y) return false;
		return super.areCoordsValidForGeneration(world, x, y, z, rand) && this.getRarity(200);
	}

	@Override
	public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z)
	{
		if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
		return this.schematic.generateStructure(variant, world, rand, x, y, z);
	}

	@Override
	public int getStructureColor()
	{
		return 0x333333;
	}
}
