package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.mob.EntityFusea;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.worldgen.WorldGenCustomVine;

public class BiomeGenCorrodedSteppe extends TragicBiome {

	public BiomeGenCorrodedSteppe(int par1, int par2) {
		super(par1, par2);
		this.enableSnow = false;
		this.enableRain = false;
		this.temperature = 0.2F;
		this.rainfall = 0.0F;
		this.fillerBlock = TragicBlocks.DarkCobblestone;
		this.topBlock = TragicBlocks.DarkCobblestone;
		if (TragicConfig.allowJarra) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJarra.class, TragicConfig.jarraSC, 0, 1));
		if (TragicConfig.allowTox) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTox.class, TragicConfig.toxSC, 0, 1));
		if (TragicConfig.allowFusea) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityFusea.class, TragicConfig.fuseaSC, 2, 4)); 
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = 16;
	}
	
	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);
		
		int k = x + rand.nextInt(16) + 8;
		int l = z + rand.nextInt(16) + 8;
		int i1 = world.getTopSolidOrLiquidBlock(k, l) + rand.nextInt(8) - rand.nextInt(8);
		
		WorldGenCustomVine worldgenvines = new WorldGenCustomVine(TragicBlocks.WickedVine);
		int mew = this == TragicBiomes.CorrodedSteppe || this == TragicBiomes.CorrodedHeights ? 4 : (this == TragicBiomes.CorrodedVeld ? 22 :10);

		for (l = 0; l < mew; ++l)
		{
			i1 = x + rand.nextInt(16) + 8;
			int j1 = z + rand.nextInt(16) + 8;
			worldgenvines.generate(world, rand, i1, 128, j1);
		}
	}

}
