package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.Structure;

public class SchematicCelestialTemple extends Schematic {

	public SchematicCelestialTemple(int variant, World world, Random rand, int x, int y, int z) {
		super(variant, world, rand, x, y, z);
	}

	@Override
	public void generateStructure(int variant, World world, Random rand, int x, int y, int z) {
		switch(variant)
		{
		case 0:
			generateWithoutVariation(world, rand, x, y, z);
			break;
		default:
			TragicMC.logger.info("There was a problem generating a Celestial Temple");
			break;
		}
	}

	@Override
	public void generateWithoutVariation(World world, Random rand, int x, int y, int z) {
		
	}

	@Override
	public void generateVariant(World world, Random rand, int x, int y, int z) {
		
	}

	@Override
	public void applyChestContents(World world, Random rand, int x, int y, int z) {
		
	}

}
