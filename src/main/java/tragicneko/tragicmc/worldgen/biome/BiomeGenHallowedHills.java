package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.worldgen.WorldGenHallowedTree;

public class BiomeGenHallowedHills extends TragicBiome {

	public BiomeGenHallowedHills(int par1) {
		super(par1);
		this.enableSnow = false;
		this.temperature = 1.6F;
		this.rainfall = 0.4F;
		this.heightVariation = 0.015F; 
		this.rootHeight = 0.025F;
		this.fillerBlock = TragicBlocks.DeadDirt;
		this.topBlock = TragicBlocks.HallowedGrass;
	}
	
	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
	{
		return new WorldGenHallowedTree(false);
	}

}
