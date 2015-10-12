package tragicneko.tragicmc.dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.event.terraingen.TerrainGen;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.CircuitWorldGen;
import tragicneko.tragicmc.worldgen.HangingCoralWorldGen;
import tragicneko.tragicmc.worldgen.SynapseVariantGen;

public class SynapseChunkProvider implements IChunkProvider
{
	private Random synapseRNG;
	/** A NoiseGeneratorOctaves used in generating nether terrain */
	private NoiseGeneratorOctaves netherNoiseGen1;
	private NoiseGeneratorOctaves netherNoiseGen2;
	private NoiseGeneratorOctaves netherNoiseGen3;
	/** Determines whether slowsand or gravel can be generated at a location */
	private NoiseGeneratorOctaves slowsandGravelNoiseGen;
	/** Determines whether something other than nettherack can be generated at a location */
	private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
	public NoiseGeneratorOctaves netherNoiseGen6;
	public NoiseGeneratorOctaves netherNoiseGen7;
	/** Is the world that the nether is getting generated. */
	private World worldObj;
	private double[] noiseField;
	/** Holds the noise used to determine whether slowsand can be generated at a location */
	private double[] slowsandNoise = new double[256];
	private double[] gravelNoise = new double[256];
	/** Holds the noise used to determine whether something other than netherrack can be generated at a location */
	private double[] netherrackExclusivityNoise = new double[256];
	private MapGenBase netherCaveGenerator = new MapGenCavesHell();
	double[] noiseData1;
	double[] noiseData2;
	double[] noiseData3;
	double[] noiseData4;
	double[] noiseData5;
	
	private HangingCoralWorldGen coralGen;
	private HangingCoralWorldGen coralGen2;
	private CircuitWorldGen circuitGen;
	private SynapseVariantGen circuitVarGen;

	public SynapseChunkProvider(World p_i2005_1_, long p_i2005_2_)
	{
		this.worldObj = p_i2005_1_;
		this.synapseRNG = new Random(p_i2005_2_);
		this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.synapseRNG, 16);
		this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.synapseRNG, 16);
		this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.synapseRNG, 8);
		this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.synapseRNG, 4);
		this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.synapseRNG, 4);
		this.netherNoiseGen6 = new NoiseGeneratorOctaves(this.synapseRNG, 10);
		this.netherNoiseGen7 = new NoiseGeneratorOctaves(this.synapseRNG, 16);

		NoiseGenerator[] noiseGens = {netherNoiseGen1, netherNoiseGen2, netherNoiseGen3, slowsandGravelNoiseGen, netherrackExculsivityNoiseGen, netherNoiseGen6, netherNoiseGen7};
		noiseGens = TerrainGen.getModdedNoiseGenerators(p_i2005_1_, this.synapseRNG, noiseGens);
		this.netherNoiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
		this.netherNoiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
		this.netherNoiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
		this.slowsandGravelNoiseGen = (NoiseGeneratorOctaves)noiseGens[3];
		this.netherrackExculsivityNoiseGen = (NoiseGeneratorOctaves)noiseGens[4];
		this.netherNoiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
		this.netherNoiseGen7 = (NoiseGeneratorOctaves)noiseGens[6];
		
		this.circuitGen = new CircuitWorldGen();
		this.coralGen = new HangingCoralWorldGen((byte) 4, 32, 1000, TragicBlocks.Conduit, (byte) 0);
		this.coralGen2 = new HangingCoralWorldGen((byte) 3, 12, 800, TragicBlocks.Conduit, (byte) 0);
		this.circuitVarGen = new SynapseVariantGen();
	}

	public void func_147419_a(int p_147419_1_, int p_147419_2_, Block[] p_147419_3_)
	{
		byte b0 = 4;
		byte b1 = 32;
		int k = b0 + 1;
		byte b2 = 17;
		int l = b0 + 1;
		this.noiseField = this.initializeNoiseField(this.noiseField, p_147419_1_ * b0, 0, p_147419_2_ * b0, k, b2, l);

		for (int i1 = 0; i1 < b0; ++i1)
		{
			for (int j1 = 0; j1 < b0; ++j1)
			{
				for (int k1 = 0; k1 < 16; ++k1)
				{
					double d0 = 0.125D;
					double d1 = this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0];
					double d2 = this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0];
					double d3 = this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0];
					double d4 = this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0];
					double d5 = (this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0;
					double d6 = (this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
					double d7 = (this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0;
					double d8 = (this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;

					for (int l1 = 0; l1 < 8; ++l1)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for (int i2 = 0; i2 < 4; ++i2)
						{
							int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
							short short1 = 128;
							double d14 = 0.25D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;

							for (int k2 = 0; k2 < 4; ++k2)
							{
								Block block = null;

								if (k1 * 8 + l1 < b1)
								{
									block = Blocks.air;
								}

								if (d15 > 0.0D)
								{
									block = TragicBlocks.CircuitBlock;
								}

								p_147419_3_[j2] = block;
								j2 += short1;
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	@Deprecated //You should provide meatadata and biome data in the below method
	public void func_147418_b(int p_147418_1_, int p_147418_2_, Block[] p_147418_3_)
	{
		replaceBiomeBlocks(p_147418_1_, p_147418_2_, p_147418_3_, new byte[p_147418_3_.length], null);
	}

	public void replaceBiomeBlocks(int p_147418_1_, int p_147418_2_, Block[] p_147418_3_, byte[] meta, BiomeGenBase[] biomes)
	{
		byte b0 = 64;
		double d0 = 0.03125D;
		this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, p_147418_1_ * 16, p_147418_2_ * 16, 0, 16, 16, 1, d0, d0, 1.0D);
		this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, p_147418_1_ * 16, 109, p_147418_2_ * 16, 16, 1, 16, d0, 1.0D, d0);
		this.netherrackExclusivityNoise = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.netherrackExclusivityNoise, p_147418_1_ * 16, p_147418_2_ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

		for (int k = 0; k < 16; ++k)
		{
			for (int l = 0; l < 16; ++l)
			{
				int i1 = (int)(this.netherrackExclusivityNoise[k + l * 16] / 3.0D + 3.0D + this.synapseRNG.nextDouble() * 0.25D);
				int j1 = -1;
				Block block = TragicBlocks.CircuitBlock;
				Block block1 = TragicBlocks.CircuitBlock;

				for (int k1 = 127; k1 >= 0; --k1)
				{
					int l1 = (l * 16 + k) * 128 + k1;

					if (k1 < 96 - this.synapseRNG.nextInt(4) && k1 > 24 + this.synapseRNG.nextInt(4))
					{
						Block block2 = p_147418_3_[l1];

						if (block2 != null && block2.getMaterial() != Material.air)
						{
							if (block2 == TragicBlocks.CircuitBlock)
							{
								if (j1 == -1)
								{
									if (k1 < b0 && (block == null || block.getMaterial() == Material.air))
									{
										block = Blocks.air;
									}

									j1 = i1;

									if (k1 >= b0 - 1)
									{
										p_147418_3_[l1] = block;
									}
									else
									{
										p_147418_3_[l1] = block1;
									}
								}
								else if (j1 > 0)
								{
									--j1;
									p_147418_3_[l1] = block1;
								}
							}
						}
						else
						{
							j1 = -1;
						}
					}
					else
					{
						p_147418_3_[l1] = this.synapseRNG.nextInt(16) != 0 || !TragicConfig.allowDigitalSeaGen ? Blocks.air : TragicBlocks.DigitalSea;
					}
				}
			}
		}
	}

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	 @Override
	 public Chunk loadChunk(int p_73158_1_, int p_73158_2_)
	{
		return this.provideChunk(p_73158_1_, p_73158_2_);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
	 * specified chunk from the map seed and chunk seed
	 */
	 @Override
	 public Chunk provideChunk(int p_73154_1_, int p_73154_2_)
	{
		 this.synapseRNG.setSeed(p_73154_1_ * 341873128712L + p_73154_2_ * 132897987541L);
		 Block[] ablock = new Block[32768];
		 byte[] meta = new byte[ablock.length];
		 BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16); //Forge Move up to allow for passing to replaceBiomeBlocks
		 this.func_147419_a(p_73154_1_, p_73154_2_, ablock);
				 this.replaceBiomeBlocks(p_73154_1_, p_73154_2_, ablock, meta, abiomegenbase);
				 this.netherCaveGenerator.func_151539_a(this, this.worldObj, p_73154_1_, p_73154_2_, ablock);
				 Chunk chunk = new Chunk(this.worldObj, ablock, meta, p_73154_1_, p_73154_2_);
				 byte[] abyte = chunk.getBiomeArray();

				 for (int k = 0; k < abyte.length; ++k)
				 {
					 abyte[k] = (byte)abiomegenbase[k].biomeID;
				 }

				 chunk.resetRelightChecks();
				 return chunk;
	}

	 /**
	  * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
	  * size.
	  */
	 private double[] initializeNoiseField(double[] p_73164_1_, int p_73164_2_, int p_73164_3_, int p_73164_4_, int p_73164_5_, int p_73164_6_, int p_73164_7_)
	 {
		 if (p_73164_1_ == null)
		 {
			 p_73164_1_ = new double[p_73164_5_ * p_73164_6_ * p_73164_7_];
		 }

		 double d0 = 684.412D;
		 double d1 = 2053.236D;
		 this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 1.0D, 0.0D, 1.0D);
		 this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 100.0D, 0.0D, 100.0D);
		 this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
		 this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);
		 this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);
		 int k1 = 0;
		 int l1 = 0;
		 double[] adouble1 = new double[p_73164_6_];
		 int i2;

		 for (i2 = 0; i2 < p_73164_6_; ++i2)
		 {
			 adouble1[i2] = Math.cos(i2 * Math.PI * 6.0D / p_73164_6_) * 2.0D;
			 double d2 = i2;

			 if (i2 > p_73164_6_ / 2)
			 {
				 d2 = p_73164_6_ - 1 - i2;
			 }

			 if (d2 < 4.0D)
			 {
				 d2 = 4.0D - d2;
				 adouble1[i2] -= d2 * d2 * d2 * 10.0D;
			 }
		 }

		 for (i2 = 0; i2 < p_73164_5_; ++i2)
		 {
			 for (int k2 = 0; k2 < p_73164_7_; ++k2)
			 {
				 double d3 = (this.noiseData4[l1] + 256.0D) / 512.0D;

				 if (d3 > 1.0D)
				 {
					 d3 = 1.0D;
				 }

				 double d4 = 0.0D;
				 double d5 = this.noiseData5[l1] / 8000.0D;

				 if (d5 < 0.0D)
				 {
					 d5 = -d5;
				 }

				 d5 = d5 * 3.0D - 3.0D;

				 if (d5 < 0.0D)
				 {
					 d5 /= 2.0D;

					 if (d5 < -1.0D)
					 {
						 d5 = -1.0D;
					 }

					 d5 /= 1.4D;
					 d5 /= 2.0D;
					 d3 = 0.0D;
				 }
				 else
				 {
					 if (d5 > 1.0D)
					 {
						 d5 = 1.0D;
					 }

					 d5 /= 6.0D;
				 }

				 d3 += 0.5D;
				 d5 = d5 * p_73164_6_ / 16.0D;
				 ++l1;

				 for (int j2 = 0; j2 < p_73164_6_; ++j2)
				 {
					 double d6 = 0.0D;
					 double d7 = adouble1[j2];
					 double d8 = this.noiseData2[k1] / 512.0D;
					 double d9 = this.noiseData3[k1] / 512.0D;
					 double d10 = (this.noiseData1[k1] / 10.0D + 1.0D) / 2.0D;

					 if (d10 < 0.0D)
					 {
						 d6 = d8;
					 }
					 else if (d10 > 1.0D)
					 {
						 d6 = d9;
					 }
					 else
					 {
						 d6 = d8 + (d9 - d8) * d10;
					 }

					 d6 -= d7;
					 double d11;

					 if (j2 > p_73164_6_ - 4)
					 {
						 d11 = (j2 - (p_73164_6_ - 4)) / 3.0F;
						 d6 = d6 * (1.0D - d11) + -10.0D * d11;
					 }

					 if (j2 < d4)
					 {
						 d11 = (d4 - j2) / 4.0D;

						 if (d11 < 0.0D)
						 {
							 d11 = 0.0D;
						 }

						 if (d11 > 1.0D)
						 {
							 d11 = 1.0D;
						 }

						 d6 = d6 * (1.0D - d11) + -10.0D * d11;
					 }

					 p_73164_1_[k1] = d6;
					 ++k1;
				 }
			 }
		 }

		 return p_73164_1_;
	 }

	 /**
	  * Checks to see if a chunk exists at x, y
	  */
	 @Override
	 public boolean chunkExists(int p_73149_1_, int p_73149_2_)
	 {
		 return true;
	 }

	 /**
	  * Populates chunk with ores etc etc
	  */
	 @Override
	 public void populate(IChunkProvider p_73153_1_, int x, int z)
	 {
		 int k = x * 16;
		 int l = z * 16;
		 BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
		 biomegenbase.decorate(this.worldObj, this.worldObj.rand, k, l);
		 int a;
		 int b;
		 int c;
		 
		 this.circuitGen.generate(this.synapseRNG, x, z, this.worldObj);
		 
		 if (TragicConfig.allowSynapseVariants && this.synapseRNG.nextInt(TragicConfig.synapseVariantChance) == 0) this.circuitVarGen.generate(this.synapseRNG, x, z, this.worldObj);

		 for (int i = 0; i < 4; i++)
		 {
			 a = k + this.synapseRNG.nextInt(16) + 8;
			 c = this.synapseRNG.nextInt(100) + 10;
			 b = l + this.synapseRNG.nextInt(16) + 8;
			 this.coralGen.generate(this.worldObj, this.synapseRNG, a, c, b);
		 }

		 for (int i = 0; i < 6; ++i)
		 {
			 a = k + this.synapseRNG.nextInt(16) + 8;
			 c = this.synapseRNG.nextInt(100) + 10;
			 b = l + this.synapseRNG.nextInt(16) + 8;
			 this.coralGen2.generate(this.worldObj, this.synapseRNG, a, c, b);
		 }
	 }

	 /**
	  * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
	  * Return true if all chunks have been saved.
	  */
	 @Override
	 public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
	 {
		 return true;
	 }

	 /**
	  * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
	  * unimplemented.
	  */
	 @Override
	 public void saveExtraData() {}

	 /**
	  * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
	  */
	 @Override
	 public boolean unloadQueuedChunks()
	 {
		 return false;
	 }

	 /**
	  * Returns if the IChunkProvider supports saving.
	  */
	 @Override
	 public boolean canSave()
	 {
		 return true;
	 }

	 /**
	  * Converts the instance data to a readable string.
	  */
	 @Override
	 public String makeString()
	 {
		 return "SynapseRandomLevelSource";
	 }

	 /**
	  * Returns a list of creatures of the specified type that can spawn at the given location.
	  */
	 @Override
	 public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
	 {
		 BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_73155_2_, p_73155_4_);
		 return biomegenbase.getSpawnableList(p_73155_1_);
	 }

	 @Override
	 public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
	 {
		 return null;
	 }

	 @Override
	 public int getLoadedChunkCount()
	 {
		 return 0;
	 }

	 @Override
	 public void recreateStructures(int p_82695_1_, int p_82695_2_)
	 {
	 }
}