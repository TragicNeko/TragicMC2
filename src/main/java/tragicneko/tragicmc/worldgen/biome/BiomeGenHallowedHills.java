package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.mob.EntityArchangel;
import tragicneko.tragicmc.entity.mob.EntityIre;
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
		if (TragicConfig.allowApis) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityApis.class, TragicConfig.apisSC, 0, 1));
		if (TragicConfig.allowArchangel) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityArchangel.class, TragicConfig.archangelSC, 0, 1));
		if (TragicConfig.allowIre) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityIre.class, TragicConfig.ireSC, 2, 4)); 
	}
	
	@Override
	public int getFlowersFromBiomeType()
	{
		return this == TragicBiomes.HallowedPrarie ? 16 : 6;
	}
	
	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
	{
		return new WorldGenHallowedTree(false);
	}

}
