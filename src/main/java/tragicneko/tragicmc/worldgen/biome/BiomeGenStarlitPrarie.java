package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.entity.boss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityStarVox;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.worldgen.WorldGenBleachedTree;

public class BiomeGenStarlitPrarie extends TragicBiome {

	public BiomeGenStarlitPrarie(int par1) {
		super(par1);
		if (TragicNewConfig.allowStarCryse) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityCryse.class, TragicNewConfig.starCryseSC, 0, 2));
		if (TragicNewConfig.allowStarVox) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityStarVox.class, TragicNewConfig.starVoxSC, 0, 1));
		if (TragicNewConfig.allowVoxStellarum) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityVoxStellarum.class, TragicNewConfig.voxStellarumSC, 0, 1));
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
		return new WorldGenBleachedTree(false, rand.nextInt(8) != 0 ? false : true);
	}
}
