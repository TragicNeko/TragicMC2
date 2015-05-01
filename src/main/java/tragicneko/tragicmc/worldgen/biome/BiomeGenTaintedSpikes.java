package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityPsygote;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;
import tragicneko.tragicmc.worldgen.IsleWorldGen;

public class BiomeGenTaintedSpikes extends TragicBiome {
	
	private CustomSpikesWorldGen spikeWorldGen;

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
		this.spikeWorldGen = new CustomSpikesWorldGen(variant == 0 ? 6 : 2, TragicBlocks.DarkStone, 14, 0.92977745D, 0.38943755D, 1.5D, 1.0D);
	}

	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);
		if (this.variant == 4 && rand.nextInt(3) == 0) new IsleWorldGen().generate(rand, x / 16, z / 16, world, null, null);
		
		if (this.spikeWorldGen != null && this.variant < 3 && rand.nextInt(100) >= TragicConfig.largeSpikeRarity)
		{
			spikeWorldGen.generate(rand, x / 16, z / 16, world, null, null);
		}
	}
}
