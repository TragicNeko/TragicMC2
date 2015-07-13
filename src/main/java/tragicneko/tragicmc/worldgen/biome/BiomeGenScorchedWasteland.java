package tragicneko.tragicmc.worldgen.biome;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.InvertedSpikeWorldGen;
import tragicneko.tragicmc.worldgen.PitWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen2;

public class BiomeGenScorchedWasteland extends TragicBiome {

	public final SurfaceWorldGen2 fireGen;
	public final PitWorldGen pitGen;
	public final InvertedSpikeWorldGen scarGen;

	public static final float[][] heights = new float[][] {{0.15F, 0.05F}, {0.05F, -0.45F}, {0.45F, -0.05F}};

	public BiomeGenScorchedWasteland(int par1, byte par2) {
		super(par1, par2);
		this.spawnableCreatureList.clear();
		if (TragicConfig.allowWisp) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWisp.class, TragicConfig.wispSC, 3, 6));
		if (TragicConfig.allowJabba) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJabba.class, TragicConfig.jabbaSC, 2, 4));
		if (TragicConfig.allowMagmox) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMagmox.class, TragicConfig.magmoxSC, 0, 1));
		if (TragicConfig.allowKitsune) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityKitsune.class, TragicConfig.kitsuneSC, 0, 1));
		this.enableSnow = false;
		this.enableRain = false;
		this.temperature = 2.0F;
		this.rainfall = 0.0F;
		this.heightVariation = heights[variant][0];
		this.rootHeight = heights[variant][1];
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = -999;
		this.fillerBlock = TragicBlocks.ScorchedRock;
		this.topBlock = TragicBlocks.MoltenRock;
		this.fireGen = new SurfaceWorldGen2((byte) (variant == 2 ? 16 : 4), Blocks.fire, (byte) 0, (byte) 8, (byte) 4);
		this.pitGen = new PitWorldGen(Blocks.flowing_lava, (byte) 0, (byte) 12, (byte) 6, 4.0D, 3.0D);
		this.scarGen = new InvertedSpikeWorldGen((byte) 6, 1.5, 2.5, 0.90977745D, 0.48943755D);
	}
	
	@Override
	public int getFlowersFromBiomeType()
	{
		return variant == 2 ? 4 : 0;
	}

	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);

		int Xcoord = (x * 16) + rand.nextInt(16);
		int Zcoord = (z * 16) + rand.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) - 1;

		byte mew = (byte) (variant == 2 ? 8 : 2);
		ArrayList<int[]> cands = new ArrayList<int[]>();
		Block block;
		
		byte i;

		for (i = 0; i < mew; i++)
		{
			Xcoord = (x * 16) + rand.nextInt(16);
			Zcoord = (z * 16) + rand.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) - 1;

			block = world.getBlock(Xcoord, Ycoord, Zcoord);
			if (block == TragicBlocks.MoltenRock && rand.nextInt(4) == 0)
			{
				world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.Geyser);
				world.setBlock(Xcoord, Ycoord - 1, Zcoord, Blocks.lava);
			}
		}

		mew = (byte) (variant == 2 ? 10 : 5);

		for (i = 0; i < mew; i++)
		{
			Xcoord = (x * 16) + rand.nextInt(16);
			Zcoord = (z * 16) + rand.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) - 1;

			block = world.getBlock(Xcoord, Ycoord, Zcoord);
			if (block == TragicBlocks.MoltenRock && rand.nextInt(4) == 0) world.setBlock(Xcoord, Ycoord, Zcoord, TragicBlocks.SteamVent);
		}

		mew = (byte) (variant == 0 ? 8 : 2);

		for (i = 0; i < mew; i++)
		{
			Xcoord = (x * 16) + rand.nextInt(16);
			Zcoord = (z * 16) + rand.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) - 1;

			block = world.getBlock(Xcoord, Ycoord, Zcoord);

			if (block == TragicBlocks.MoltenRock && rand.nextInt(4) == 0)
			{
				cands.clear();
				cands.addAll(WorldHelper.getBlocksInSphericalRange(world, (rand.nextDouble() * 2.25) + 1.5, Xcoord, Ycoord - 1, Zcoord));

				for (int[] coords : cands)
				{
					block = world.getBlock(coords[0], coords[1], coords[2]);
					if (block.isReplaceable(world, coords[0], coords[1], coords[2])) world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.ScorchedRock);
				}
			}
		}
		if (rand.nextInt(8) == 0) this.pitGen.generate(rand, x / 16, z / 16, world, null, null);
		this.fireGen.generate(rand, x / 16, z / 16, world, null, null);
		if (variant == 2 && rand.nextInt(100) > TragicConfig.largeSpikeRarity && rand.nextInt(6) != 0) this.scarGen.generate(rand, x / 16, z / 16, world, null, null);
	}
}
