package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityRanmas;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;

public class BiomeGenCrystal extends TragicBiome {

	public CustomSpikesWorldGen crystalGen;

	public BiomeGenCrystal(int par1) {
		super(par1, 0);
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityRanmas.class, TragicConfig.ranmasSC, 0, 1));
		this.enableRain = false;
		this.enableSnow = false;
		this.rainfall = 0F;
		this.temperature = 1F;
		this.heightVariation = 1.25F;
		this.rootHeight = 1.04F;
		this.theBiomeDecorator.flowersPerChunk = 0;
		this.theBiomeDecorator.treesPerChunk = 0;
		this.fillerBlock = TragicBlocks.Crystal;
		this.topBlock = TragicBlocks.Crystal;
		this.crystalGen = new CustomSpikesWorldGen(8, TragicBlocks.Crystal, 0, 0.89477735D, 0.441114525D, 1.0D, 0.35D, false, false);
	}

	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);
		this.crystalGen.generate(rand, x / 16, z / 16, world, null, null);
	}
}
