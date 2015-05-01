package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.worldgen.WorldGenCustomVine;
import tragicneko.tragicmc.worldgen.WorldGenLargePaintedTree;
import tragicneko.tragicmc.worldgen.WorldGenPaintedShrubs;
import tragicneko.tragicmc.worldgen.WorldGenPaintedTree;

public class BiomeGenPaintedForest extends TragicBiome {

	public BiomeGenPaintedForest(int par1, int par2) {
		super(par1, par2);
		if (TragicConfig.allowPox) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTox.class, TragicConfig.poxSC, 0, 1));
		if (TragicConfig.allowJanna) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJabba.class, TragicConfig.jannaSC, 0, 2));
		this.fillerBlock = TragicBlocks.DeadDirt;
		this.topBlock = TragicBlocks.BrushedGrass;
		this.temperature = 1.2F;
		this.rainfall = 1.5F;
		this.theBiomeDecorator.treesPerChunk = 16;
		this.theBiomeDecorator.mushroomsPerChunk = 4;
		this.theBiomeDecorator.grassPerChunk = 2;
	}

	@Override
	public int getFlowersFromBiomeType()
	{
		return this == TragicBiomes.PaintedClearing ? 16 : 6;
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_)
	{
		return new WorldGenTallGrass(TragicBlocks.PaintedTallGrass, 0);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
	{
		if (this == TragicBiomes.PaintedPlains || this == TragicBiomes.PaintedClearing)
		{
			if (rand.nextInt(4) != 0)
			{
				return new WorldGenPaintedShrubs();
			}
			else
			{
				return new WorldGenPaintedTree(false, rand.nextBoolean());
			}
		}
		else
		{
			if (rand.nextInt(8) != 0)
			{
				return new WorldGenLargePaintedTree(false, rand.nextInt(3) + 4, 10);
			}
			else if (rand.nextInt(8) == 0)
			{
				return new WorldGenPaintedShrubs();
			}
		}

		return new WorldGenPaintedTree(false, rand.nextBoolean());
	}

	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);
		
		WorldGenCustomVine worldgenvines = new WorldGenCustomVine(TragicBlocks.Glowvine);
		int k;
		int l;

		for (int a = 0; a < 40; ++a)
		{
			k = x + rand.nextInt(16) - 8;
			l = z + rand.nextInt(16) - 8;
			worldgenvines.generate(world, rand, k, 128, l);
		}
	}

}
