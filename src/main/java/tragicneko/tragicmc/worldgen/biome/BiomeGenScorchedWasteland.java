package tragicneko.tragicmc.worldgen.biome;

import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityWisp;

public class BiomeGenScorchedWasteland extends TragicBiome {

	public BiomeGenScorchedWasteland(int par1, int par2) {
		super(par1, par2);
		this.spawnableCreatureList.clear();
		if (TragicConfig.allowWisp) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWisp.class, TragicConfig.wispSC, 3, 6));
		if (TragicConfig.allowJabba) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJabba.class, TragicConfig.jabbaSC, 2, 4));
		if (TragicConfig.allowMagmox) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMagmox.class, TragicConfig.magmoxSC, 0, 1));
		if (TragicConfig.allowKitsune) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityKitsune.class, TragicConfig.kitsuneSC, 0, 1));
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
