package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.world.World;

public interface IStructureSchematic {

	/**
	 * Passes off to other methods to actually generate the structure, getting the proper method from the variant
	 */
	public abstract void generateStructure(int variant, World world, Random rand, int x, int y, int z);
	
	/**
	 * Generates the normal structure with no variation
	 */
	public abstract void generateWithoutVariation(World world, Random rand, int x, int y, int z);
	
	/**
	 * Generates the structure with a single variation, if more are needed these must be added and done manually
	 */
	public abstract void generateVariant(World world, Random rand, int x, int y, int z);
	
	/**
	 * Used in structure generation, must be called to apply content to generated chests
	 */
	public abstract void applyChestContents(World world, Random rand, int x, int y, int z);
}
