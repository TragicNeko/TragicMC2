package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.world.World;

public abstract class Schematic implements IStructureSchematic {
	
	public Schematic(int variant, World world, Random rand, int x, int y, int z)
	{
		this.generateStructure(variant, world, rand, x, y, z);
	}

	/**
	 * Main class called upon by the constructor, you need to tell the schematic which method to generate the structure from in here based on variant (if any)
	 */
	public abstract void generateStructure(int variant, World world, Random rand, int x, int y, int z);

	/**
	 * This is the basic structure without variation
	 */
	public abstract void generateWithoutVariation(World world, Random rand, int x, int y, int z);

	/**
	 * This is one variant of the main structure meant as an example method, more can be added and called from the generateStructure method
	 */
	public abstract void generateVariant(World world, Random rand, int x, int y, int z);

	/**
	 * Use this to apply chest contents to generated chests
	 */
	public abstract void applyChestContents(World world, Random rand, int x, int y, int z);
}
