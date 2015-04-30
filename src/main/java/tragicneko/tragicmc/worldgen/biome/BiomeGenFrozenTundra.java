package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.worldgen.WorldGenCustomVine;

public class BiomeGenFrozenTundra extends TragicBiome {

	public BiomeGenFrozenTundra(int par1, int par2) {
		super(par1, par2);
		this.enableSnow = true;
		this.enableRain = false;
		this.temperature = 0.1F;
		this.rainfall = 0.1F;
		this.fillerBlock = TragicBlocks.IcedDirt;
		this.topBlock = TragicBlocks.Permafrost;
		if (TragicConfig.allowCryse) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityCryse.class, TragicConfig.cryseSC, 2, 4));
		if (TragicConfig.allowRagr) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityRagr.class, TragicConfig.ragrSC, 0, 1));
		if (TragicConfig.allowSnowGolem) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySnowman.class, TragicConfig.snowGolemSC, 1, 2));
		if (TragicConfig.allowAbomination) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityAbomination.class, TragicConfig.abominationSC, 3, 5));
		if (TragicConfig.allowYeti) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityYeti.class, TragicConfig.yetiSC, 0, 1));
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = 32;
	}
	
	@Override
	public int getFlowersFromBiomeType()
	{
		return this == TragicBiomes.FrozenTundra ? 6 : 2;
	}
	
	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);
		
		int k = x + rand.nextInt(16) + 8;
		int l = z + rand.nextInt(16) + 8;
		int i1 = world.getTopSolidOrLiquidBlock(k, l) + rand.nextInt(24) - rand.nextInt(24);
		
		WorldGenCustomVine worldgenvines = new WorldGenCustomVine(TragicBlocks.Moss);
		int mew = 20;
		if (this != TragicBiomes.FrozenTundra) mew = 40;

		for (l = 0; l < 40; ++l)
		{
			i1 = x + rand.nextInt(16) + 8;
			int j1 = z + rand.nextInt(16) + 8;
			worldgenvines.generate(world, rand, i1, 128, j1);
		} 
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
	{
		return new WorldGenTallGrass(TragicBlocks.Lichen, 0);
	}

}
