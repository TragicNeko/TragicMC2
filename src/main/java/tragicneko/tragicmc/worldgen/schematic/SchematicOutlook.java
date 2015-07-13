package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;

public class SchematicOutlook extends Schematic {

	public SchematicOutlook() {
		super(64, 4, 4);
	}

	@Override
	public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {
		
		for (int y1 = 0; y1 + y < 230; y1++)
		{
			world.setBlock(x, y + y1, z, TragicBlocks.ErodedStone, 1, 2);
			world.setBlock(x + 1, y + y1, z, Blocks.ladder, 0, 2);
		}
		return true;
	}

}
