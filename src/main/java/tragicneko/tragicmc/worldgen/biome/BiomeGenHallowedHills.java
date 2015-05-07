package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.mob.EntityArchangel;
import tragicneko.tragicmc.entity.mob.EntityIre;
import tragicneko.tragicmc.worldgen.StringWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen2;
import tragicneko.tragicmc.worldgen.WorldGenHallowedTree;

public class BiomeGenHallowedHills extends TragicBiome {
	
	public final StringWorldGen stringGen;
	public final SurfaceWorldGen2 lightGen;
	public final SurfaceWorldGen2 fragileGen;
	
	public static final float[][] heights = new float[][] {{0.35F, 0.52F}, {0.12F, 0.36F}, {0.01F, 0.65F}, {1.55F, 0.85F}};

	public BiomeGenHallowedHills(int par1, int par2) {
		super(par1, par2);
		this.enableSnow = false;
		this.temperature = 1.6F;
		this.rainfall = 0.4F;
		this.heightVariation = heights[variant][0]; 
		this.rootHeight = heights[variant][1];
		this.theBiomeDecorator.treesPerChunk = variant == 0 ? 4 : (variant == 1 ? 16 : 2);
		this.theBiomeDecorator.flowersPerChunk = variant == 0 || variant == 3 ? 4 : (variant == 2 ? 16 : 8);
		this.fillerBlock = TragicBlocks.DeadDirt;
		this.topBlock = TragicBlocks.HallowedGrass;
		if (TragicConfig.allowApis) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityApis.class, TragicConfig.apisSC, 0, 1));
		if (TragicConfig.allowArchangel) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityArchangel.class, TragicConfig.archangelSC, 0, 1));
		if (TragicConfig.allowIre) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityIre.class, TragicConfig.ireSC, 2, 4)); 
		this.stringGen = new StringWorldGen(TragicBlocks.StringLight, 0, 12);
		this.lightGen = new SurfaceWorldGen2(6, TragicBlocks.Light, 0, 4, 4);
		this.fragileGen = new SurfaceWorldGen2(2, TragicBlocks.FragileLight, 0, 4, 4);
	}
	
	@Override
	public int getFlowersFromBiomeType()
	{
		return variant == 2 ? 16 : 6;
	}
	
	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
	{
		return new WorldGenHallowedTree(false);
	}
	
	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);
		if (rand.nextBoolean()) this.stringGen.generate(rand, x / 16, z / 16, world, null, null);
		if (rand.nextBoolean()) this.lightGen.generate(rand, x / 16, z / 16, world, null, null);
		if (rand.nextInt(6) == 0) this.fragileGen.generate(rand, x / 16, z / 16, world, null, null);
	}

}
