package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityParasmite;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.worldgen.WorldGenCustomVine;
import tragicneko.tragicmc.worldgen.WorldGenDarkForestTree;

public class BiomeGenDarkForest extends TragicBiome {

	public BiomeGenDarkForest(int par1) {
		super(par1);
		this.spawnableMonsterList.clear();
		if (TragicConfig.allowPlague) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPlague.class, TragicConfig.plagueSC, 1, 4));
		if (TragicConfig.allowInkling) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityInkling.class, TragicConfig.inklingSC, 2, 4));
		if (TragicConfig.allowParasmite) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityParasmite.class, TragicConfig.parasmiteSC, 2, 4));
		if (TragicConfig.allowEnyvil) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityEnyvil.class, TragicConfig.enyvilSC, 0, 1));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 15, 2, 4));
		this.fillerBlock = TragicBlocks.DeadDirt;
		this.topBlock = TragicBlocks.DarkGrass;
		this.temperature = 1.2F;
		this.rainfall = 1.5F;
		this.theBiomeDecorator.treesPerChunk = 24;
		this.theBiomeDecorator.mushroomsPerChunk = 12;
		this.theBiomeDecorator.grassPerChunk = 4;
		this.theBiomeDecorator.bigMushroomsPerChunk = 8;
		this.theBiomeDecorator.sandGen =  new WorldGenSand(TragicBlocks.DarkSand, 7);
		this.theBiomeDecorator.sandPerChunk = 16;
		this.theBiomeDecorator.sandPerChunk2 = 16;
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_)
	{
		return new WorldGenTallGrass(TragicBlocks.DarkTallGrass, 0);
	}
	
	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
	{
		return new WorldGenDarkForestTree();
	}
	
	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);
		
		int k = x + rand.nextInt(16) + 8;
		int l = z + rand.nextInt(16) + 8;
		int i1 = world.getTopSolidOrLiquidBlock(k, l) + rand.nextInt(24) - rand.nextInt(24);
		
		if (rand.nextBoolean() && world.getBlock(k, i1, l).isAir(world, k, i1, l) && World.doesBlockHaveSolidTopSurface(world, k, i1 - 1, l)) world.setBlock(k, i1, l, TragicBlocks.DarkGas);
		
		WorldGenCustomVine worldgenvines = new WorldGenCustomVine(TragicBlocks.DarkVine);

		for (l = 0; l < 12; ++l)
		{
			i1 = x + rand.nextInt(16) + 8;
			int j1 = z + rand.nextInt(16) + 8;
			worldgenvines.generate(world, rand, i1, 128, j1);
		}
	}

}
