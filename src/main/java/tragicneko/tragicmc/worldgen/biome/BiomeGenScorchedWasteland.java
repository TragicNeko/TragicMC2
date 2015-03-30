package tragicneko.tragicmc.worldgen.biome;

import tragicneko.tragicmc.TragicBlocks;

public class BiomeGenScorchedWasteland extends TragicBiome {

	public BiomeGenScorchedWasteland(int par1) {
		super(par1);
		this.enableSnow = false;
		this.enableRain = false;
		this.temperature = 2.0F;
		this.rainfall = 0.0F;
		this.heightVariation = 0.015F; 
		this.rootHeight = 0.025F;
		this.fillerBlock = TragicBlocks.ScorchedRock;
		this.topBlock = TragicBlocks.MoltenRock;
	}

}
