package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.Schematic;

public class StructureBoss extends Structure {

	public StructureBoss(Schematic sch, int id) {
		super(sch, id);
	}

	@Override
	public boolean canGenerate()
	{
		return super.canGenerate() && TragicConfig.allowBossStructureGen;
	}
}
