package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.mob.EntitySirv;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen;
import tragicneko.tragicmc.worldgen.VoidPitWorldGen;

public class BiomeGenDecayingWasteland extends TragicBiome {
	
	public final VoidPitWorldGen voidPitGen;
	public final SurfaceWorldGen mixedDirtGen;

	public BiomeGenDecayingWasteland(int par1, int par2) {
		super(par1, par2);
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 50, 3, 5));
		if (TragicConfig.allowSirv) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySirv.class, TragicConfig.sirvSC, 4, 6));
		if (TragicConfig.allowDeathReaper) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityDeathReaper.class, TragicConfig.deathReaperSC, 0, 1));
		this.enableRain = false;
		this.enableSnow = false;
		this.temperature = 0.6F;
		this.rainfall = 0F;
		this.heightVariation = 0.015F;
		this.rootHeight = 0.025F;
		this.fillerBlock = TragicBlocks.DarkStone;
		this.topBlock = TragicBlocks.DeadDirt;
		this.theBiomeDecorator.mushroomsPerChunk = 16;
		this.voidPitGen = new VoidPitWorldGen(3.5D, 3.5D);
		this.mixedDirtGen = new SurfaceWorldGen(3.0D, 4.0D, true, 24, TragicBlocks.DeadDirt, 2, TragicBlocks.DeadDirt, true, true);
	}
	
	@Override
	public int getBushesFromBiomeType()
	{
		return variant == 0 ? 8 : 4;
	}

	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);
		new CustomSpikesWorldGen(variant == 3 ? 8 : 2, TragicBlocks.BoneBlock, rand.nextInt(2), 0.89477735D, 0.441114525D, 1.0D, 0.35D, false, false).generate(rand, x / 16, z / 16, world, null, null);
		this.mixedDirtGen.generate(rand, x / 16, z / 16, world, null, null);
		if (TragicConfig.allowVoidPitGen && rand.nextInt(200) >= TragicConfig.voidPitRarity && rand.nextInt(4) != 0) this.voidPitGen.generate(rand, x / 16, z / 16, world, null, null);
	}
}
