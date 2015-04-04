package tragicneko.tragicmc.worldgen.biome;

import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.mob.EntityTox;

public class BiomeGenCorrodedSteppe extends TragicBiome {

	public BiomeGenCorrodedSteppe(int par1) {
		super(par1);
		this.enableSnow = false;
		this.enableRain = false;
		this.temperature = 0.2F;
		this.rainfall = 0.0F;
		this.fillerBlock = TragicBlocks.DarkCobblestone;
		this.topBlock = TragicBlocks.DarkCobblestone;
		if (TragicConfig.allowJarra) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJarra.class, TragicConfig.jarraSC, 0, 1));
		if (TragicConfig.allowTox) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTox.class, TragicConfig.toxSC, 0, 1));
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = 16;
	}

}
