package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.mob.EntityErkel;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;

public class TragicBiome extends BiomeGenBase {

	public TragicBiome(int par1) {
		super(par1, true);
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.fillerBlock = TragicBlocks.DarkStone;
		this.topBlock = TragicBlocks.DeadDirt;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = -999;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.bigMushroomsPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = -999;
		if (TragicConfig.allowPlague) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPlague.class, TragicConfig.plagueSC, 1, 4));
		if (TragicConfig.allowTragicNeko) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTragicNeko.class, TragicConfig.tragicNekoSC, 0, 1));
		if (TragicConfig.allowTimeController) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTimeController.class, TragicConfig.timeControllerSC, 0, 1));
		if (TragicConfig.allowErkel) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityErkel.class, TragicConfig.erkelSC, 0, 3));
		if (TragicConfig.allowWisp) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWisp.class, TragicConfig.wispSC, 1, 3));
	}

	@Override
	public BiomeGenBase setBiomeName(String s)
	{
		this.biomeName = StatCollector.translateToLocal(s);
		return this;
	}

	public TragicBiome setFlowersPerChunk(int i)
	{
		this.theBiomeDecorator.flowersPerChunk = i;
		return this;
	}

	public TragicBiome setGrassPerChunk(int i)
	{
		this.theBiomeDecorator.grassPerChunk = i;
		return this;
	}

	public TragicBiome setMushroomsPerChunk(int i)
	{
		this.theBiomeDecorator.mushroomsPerChunk = i;
		return this;
	}

	public TragicBiome setTreesPerChunk(int i)
	{
		this.theBiomeDecorator.treesPerChunk = i;
		return this;
	}

	public TragicBiome setHeightVariation(float f)
	{
		this.heightVariation = f;
		return this;
	}

	public TragicBiome setRootHeight(float f)
	{
		this.rootHeight = f;
		return this;
	}

	/**
	 * Returns the flowers per chunk based on specific biome, this is for my custom flower world gen that uses my own flowers
	 * @return
	 */
	public int getFlowersFromBiomeType()
	{
		return 0;
	}

	/**
	 * Returns the bushes per chunk based on specific biome, this is for my custom bush worldgen within the flower worldgen
	 * @return
	 */
	public int getBushesFromBiomeType()
	{
		return 0;
	}

	@Override
	public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
	{
		this.genTragicBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
	} 

	public final void genTragicBiomeTerrain(World p_150560_1_, Random p_150560_2_, Block[] p_150560_3_, byte[] p_150560_4_, int p_150560_5_, int p_150560_6_, double p_150560_7_)
	{
		boolean flag = true;
		Block block = this.topBlock;
		byte b0 = (byte)(this.field_150604_aj & 255);
		Block block1 = this.fillerBlock;
		int k = -1;
		int l = (int)(p_150560_7_ / 3.0D + 3.0D + p_150560_2_.nextDouble() * 0.25D);
		int i1 = p_150560_5_ & 15;
		int j1 = p_150560_6_ & 15;
		int k1 = p_150560_3_.length / 256;

		for (int l1 = 255; l1 >= 0; --l1)
		{
			int i2 = (j1 * 16 + i1) * k1 + l1;

			if (l1 <= 0 + p_150560_2_.nextInt(5))
			{
				p_150560_3_[i2] = Blocks.air;
			}
			else
			{
				Block block2 = p_150560_3_[i2];

				if (block2 != null && block2.getMaterial() != Material.air)
				{
					if (block2 == TragicBlocks.DarkStone)
					{
						if (k == -1)
						{
							if (l <= 0)
							{
								block = null;
								b0 = 0;
								block1 = TragicBlocks.DarkStone;
							}
							else if (l1 >= 51 && l1 <= 64)
							{
								block = this.topBlock;
								b0 = (byte)(this.field_150604_aj & 255);
								block1 = this.fillerBlock;
							}

							k = l;

							if (l1 >= 42)
							{
								p_150560_3_[i2] = block;
								p_150560_4_[i2] = b0;
							}
							else if (l1 < 47 - l)
							{
								block = null;
								block1 = TragicBlocks.DarkStone;
								p_150560_3_[i2] = TragicBlocks.DeadDirt;
							}
							else
							{
								p_150560_3_[i2] = block1;
							}
						}
						else if (k > 0)
						{
							--k;
							p_150560_3_[i2] = block1;
						}
					}
				}
				else
				{
					k = -1;
				}
			}
		}
	}
}
