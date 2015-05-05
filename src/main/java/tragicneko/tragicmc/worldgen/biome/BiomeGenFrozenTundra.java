package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;
import tragicneko.tragicmc.worldgen.PitWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen2;
import tragicneko.tragicmc.worldgen.WorldGenCustomVine;

public class BiomeGenFrozenTundra extends TragicBiome {
	
	public final SurfaceWorldGen permafrostGen;
	public final SurfaceWorldGen2 iceSpikeGen;
	public final PitWorldGen pitGen;
	public final WorldGenCustomVine vineGen;

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
		if (TragicConfig.allowAbomination) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityAbomination.class, TragicConfig.abominationSC, 3, 5));
		if (TragicConfig.allowYeti) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityYeti.class, TragicConfig.yetiSC, 0, 1));
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = 32;
		this.permafrostGen = new SurfaceWorldGen(3.0, 2.0, true, 8, TragicBlocks.Permafrost, 1, TragicBlocks.Permafrost, true, true);
		this.iceSpikeGen = new SurfaceWorldGen2(variant == 2 ? 12 : 4, TragicBlocks.IceSpike, 0, 4, 8);
		this.pitGen = new PitWorldGen(TragicBlocks.IceSpike, 0, 12, 6, 4.0D, 3.0D);
		this.vineGen = new WorldGenCustomVine(TragicBlocks.Moss);
	}
	
	@Override
	public int getFlowersFromBiomeType()
	{
		return variant == 0 ? 6 : 2;
	}
	
	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);
		
		int mew = variant > 0 ? 18 : 6;
		int k;
		int l;

		for (int a = 0; a < mew; ++a)
		{
			k = x + rand.nextInt(16) - 8;
			l = z + rand.nextInt(16) - 8;
			this.vineGen.generate(world, rand, k, 128, l);
		}
		
		this.permafrostGen.generate(rand, x / 16, z / 16, world, null, null);
		if (variant != 1) this.iceSpikeGen.generate(rand, x / 16, z / 16, world, null, null);
		new CustomSpikesWorldGen(variant == 2 ? 8 : (variant == 0 ? 2 : 4), Blocks.packed_ice, 0, 0.89477735D, 0.441114525D, 1.0D, 0.35D, false, false).generate(rand, x / 16, z / 16, world, null, null);
		if (rand.nextInt(8) == 0) this.pitGen.generate(rand, x / 16, z / 16, world, null, null);
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
	{
		return new WorldGenTallGrass(TragicBlocks.Lichen, 0);
	}

}
