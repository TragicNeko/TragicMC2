package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityPsygote;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;
import tragicneko.tragicmc.worldgen.InvertedSpikeWorldGen;
import tragicneko.tragicmc.worldgen.IsleWorldGen;
import tragicneko.tragicmc.worldgen.RuggedTerrainWorldGen;

public class BiomeGenTaintedSpikes extends TragicBiome {

	private final CustomSpikesWorldGen spikeWorldGen;
	private final InvertedSpikeWorldGen scarGen;
	private final RuggedTerrainWorldGen ruggedGen;
	private final IsleWorldGen isleGen;

	public BiomeGenTaintedSpikes(int par1, int par2) {
		super(par1, par2);
		if (TragicConfig.allowPsygote) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPsygote.class, TragicConfig.psygoteSC, 0, 1));
		if (TragicConfig.allowStin) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityStin.class, TragicConfig.stinSC, 0, 2));
		if (TragicConfig.allowGreaterStin) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityStin.class, TragicConfig.greaterStinSC, 0, 1));
		this.enableSnow = false;
		this.enableRain = false;
		this.temperature = 1.8F;
		this.rainfall = 0.0F;
		this.heightVariation = 0.45F; 
		this.rootHeight = 0.25F;
		this.fillerBlock = TragicBlocks.DarkStone;
		this.topBlock = TragicBlocks.ErodedStone;
		this.spikeWorldGen = new CustomSpikesWorldGen(variant == 0 ? 4 : 1, TragicBlocks.DarkStone, 14, 0.92477745D, 0.42943755D, 1.5D, 1.0D);
		this.scarGen = new InvertedSpikeWorldGen(4, 1.5, 2.5, 0.93977745D, 0.48943755D);
		this.ruggedGen = new RuggedTerrainWorldGen(TragicBlocks.ErodedStone, 2, TragicBlocks.ErodedStone, 3, 2.0D, 2.5D, true, 8);
		this.isleGen = new IsleWorldGen();
	}

	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);
		if (this.variant == 4 && rand.nextInt(3) == 0) this.isleGen.generate(rand, x / 16, z / 16, world, null, null);
		if (this.variant < 3 && rand.nextInt(100) >= TragicConfig.largeSpikeRarity && rand.nextBoolean()) this.spikeWorldGen.generate(rand, x / 16, z / 16, world, null, null);
		if (this.variant == 3)
		{
			if (rand.nextInt(100) > TragicConfig.largeSpikeRarity && rand.nextInt(3) != 0) this.scarGen.generate(rand, x / 16, z / 16, world, null, null);
			this.ruggedGen.generate(rand, x / 16, z / 16, world, null, null);
		}
	}
}
