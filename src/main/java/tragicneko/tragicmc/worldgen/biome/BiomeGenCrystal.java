package tragicneko.tragicmc.worldgen.biome;

import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityRanmas;

public class BiomeGenCrystal extends TragicBiome {

	public BiomeGenCrystal(int par1) {
		super(par1);
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityRanmas.class, TragicConfig.ranmasSC, 0, 1));
		this.enableRain = false;
		this.enableSnow = false;
		this.rainfall = 0F;
		this.temperature = 1F;
		this.theBiomeDecorator.flowersPerChunk = 0;
		this.theBiomeDecorator.treesPerChunk = 0;
	}

}
