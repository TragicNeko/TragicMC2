package tragicneko.tragicmc.worldgen.biome;

import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;

public class BiomeGenSynapse extends TragicBiome {

	public BiomeGenSynapse(int par1) {
		super(par1);
		this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityNanoSwarm.class, 5, 0, 1));
		this.fillerBlock = TragicBlocks.CircuitBlock;
		this.topBlock = TragicBlocks.CircuitBlock;
		this.theBiomeDecorator = new BiomeDecoratorSynapse();
	}
}
