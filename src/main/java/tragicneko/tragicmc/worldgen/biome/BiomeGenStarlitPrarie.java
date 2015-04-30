package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.worldgen.WorldGenBleachedOakTree;

public class BiomeGenStarlitPrarie extends TragicBiome {

	public BiomeGenStarlitPrarie(int par1, int par2) {
		super(par1, par2);
		if (TragicConfig.allowStarCryse) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityCryse.class, TragicConfig.starCryseSC, 0, 2));
		if (TragicConfig.allowStarVox) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityNorVox.class, TragicConfig.starVoxSC, 0, 1));
		if (TragicConfig.allowVoxStellarum) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityVoxStellarum.class, TragicConfig.voxStellarumSC, 0, 1));
		if (TragicConfig.allowPolaris) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPolaris.class, TragicConfig.polarisSC, 0, 0));
		this.enableSnow = false;
		this.temperature = 1.8F;
		this.rainfall = 0.6F;
		this.heightVariation = 0.15F; 
		this.rootHeight = 0.45F;
		this.fillerBlock = TragicBlocks.DeadDirt;
		this.topBlock = TragicBlocks.StarlitGrass;
		this.theBiomeDecorator.treesPerChunk = 2;
		this.theBiomeDecorator.grassPerChunk = 16;
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_)
	{
		return new WorldGenTallGrass(TragicBlocks.StarlitTallGrass, 0);
	}
	
	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
	{
		return new WorldGenBleachedOakTree(false, rand.nextBoolean());
	}
}
