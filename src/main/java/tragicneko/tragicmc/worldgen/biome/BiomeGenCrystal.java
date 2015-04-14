package tragicneko.tragicmc.worldgen.biome;

public class BiomeGenCrystal extends TragicBiome {

	public BiomeGenCrystal(int par1) {
		super(par1);
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.enableRain = false;
		this.enableSnow = false;
		this.rainfall = 0F;
		this.temperature = 1F;
		this.theBiomeDecorator.flowersPerChunk = 0;
		this.theBiomeDecorator.treesPerChunk = 0;
	}

}
