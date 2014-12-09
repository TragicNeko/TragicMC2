package tragicneko.tragicmc.worldgen.biome;

import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.entity.mob.EntitySirv;

public class BiomeGenDecayingWasteland extends TragicBiome {

	public BiomeGenDecayingWasteland(int par1) {
		super(par1);
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 50, 3, 5));
		if (TragicNewConfig.allowSirv) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySirv.class, TragicNewConfig.sirvSC, 4, 6));
		this.enableRain = false;
		this.enableSnow = false;
		this.temperature = 0.6F;
		this.rainfall = 0F;
		this.heightVariation = 0.015F; 
		this.rootHeight = 0.025F;
		this.fillerBlock = TragicBlocks.DarkStone;
		this.topBlock = TragicBlocks.DeadDirt;
		this.theBiomeDecorator.mushroomsPerChunk = 16;
	}
	
	@Override
	public int getBushesFromBiomeType()
	{
		return this == TragicBiomes.DecayingWasteland ? 8 : 4;
	}

}
