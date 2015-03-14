package tragicneko.tragicmc.worldgen.biome;

import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityPsygote;
import tragicneko.tragicmc.entity.mob.EntityStin;

public class BiomeGenTaintedSpikes extends TragicBiome {

	public BiomeGenTaintedSpikes(int par1) {
		super(par1);
		if (TragicConfig.allowPsygote) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPsygote.class, TragicConfig.psygoteSC, 0, 1));
		if (TragicConfig.allowStin) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityStin.class, TragicConfig.stinSC, 0, 2));
		if (TragicConfig.allowGreaterStin) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityStin.class, TragicConfig.greaterStinSC, 0, 1));
		this.enableSnow = false;
		this.enableRain = false;
		this.temperature = 1.8F;
		this.rainfall = 0.0F;
		this.heightVariation = 0.45F; 
		this.rootHeight = 0.25F;
		this.fillerBlock = TragicBlocks.DarkStone;
		this.topBlock = TragicBlocks.ErodedStone;
	}
}
