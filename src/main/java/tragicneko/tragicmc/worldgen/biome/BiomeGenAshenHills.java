package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.worldgen.WorldGenAshenShrubs;
import tragicneko.tragicmc.worldgen.WorldGenAshenTree;

public class BiomeGenAshenHills extends TragicBiome {

	public BiomeGenAshenHills(int par1, int par2) {
		super(par1, par2);
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 50, 3, 5));
		if (TragicConfig.allowInkling) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityInkling.class, TragicConfig.inklingSC, 0, 2));
		if (TragicConfig.allowGragul) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGragul.class, TragicConfig.gragulSC, 0, 2));
		if (TragicConfig.allowKragul) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityKragul.class, TragicConfig.kragulSC, 0, 1));
		if (TragicConfig.allowNorVox) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityNorVox.class, TragicConfig.norVoxSC, 0, 2));
		this.enableRain = false;
		this.enableSnow = false;
		this.temperature = 1.6F;
		this.rainfall = 0F;
		this.heightVariation = 0.105F;
		this.rootHeight = 0.385F;
		this.fillerBlock = TragicBlocks.DarkStone;
		this.topBlock = TragicBlocks.AshenGrass;
		this.theBiomeDecorator.treesPerChunk = 4;
		this.theBiomeDecorator.grassPerChunk = 2;
	}
	
	@Override
	public int getBushesFromBiomeType()
	{
		return 12;
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
	{
		if (rand.nextInt(16) == 0)
		{
			return new WorldGenAshenShrubs();
		}		
		return new WorldGenAshenTree(false);
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
	{
		return new WorldGenTallGrass(rand.nextInt(16) == 0 ? TragicBlocks.DriedGrass : TragicBlocks.AshenTallGrass, 0);
	}

}
