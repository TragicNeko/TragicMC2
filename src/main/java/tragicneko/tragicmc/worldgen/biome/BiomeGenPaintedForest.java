package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.entity.mob.EntityJanna;
import tragicneko.tragicmc.entity.mob.EntityPox;
import tragicneko.tragicmc.main.TragicBiomes;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.worldgen.WorldGenGlowvine;
import tragicneko.tragicmc.worldgen.WorldGenLargePaintedTree;
import tragicneko.tragicmc.worldgen.WorldGenPaintedShrubs;
import tragicneko.tragicmc.worldgen.WorldGenPaintedTree;

public class BiomeGenPaintedForest extends TragicBiome {

	public BiomeGenPaintedForest(int par1) {
		super(par1);
		if (TragicNewConfig.allowPox) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPox.class, TragicNewConfig.poxSC, 0, 1));
		if (TragicNewConfig.allowJanna) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJanna.class, TragicNewConfig.jannaSC, 0, 2));
		this.fillerBlock = TragicBlocks.DeadDirt;
		this.topBlock = TragicBlocks.BrushedGrass;
		this.temperature = 1.2F;
		this.rainfall = 1.5F;
		this.theBiomeDecorator.treesPerChunk = 24;
		this.theBiomeDecorator.mushroomsPerChunk = 4;
		this.theBiomeDecorator.grassPerChunk = 4;
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
	public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
	{
		super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
		
		int k = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
		int l = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
		int i1 = p_76728_1_.getTopSolidOrLiquidBlock(k, l) + p_76728_2_.nextInt(24) - p_76728_2_.nextInt(24);
		
		WorldGenGlowvine worldgenvines = new WorldGenGlowvine();

		for (l = 0; l < 30; ++l)
		{
			i1 = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
			short short1 = 128;
			int j1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
			worldgenvines.generate(p_76728_1_, p_76728_2_, i1, short1, j1);
		}
	}

}
