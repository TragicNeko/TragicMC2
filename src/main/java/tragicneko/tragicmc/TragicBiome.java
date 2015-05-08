package tragicneko.tragicmc;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.mob.EntityErkel;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.worldgen.biome.BiomeGenAshenHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCorrodedSteppe;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCrystal;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDarkForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.biome.BiomeGenHallowedHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;
import tragicneko.tragicmc.worldgen.biome.BiomeGenSynapse;
import tragicneko.tragicmc.worldgen.biome.BiomeGenTaintedSpikes;

public class TragicBiome extends BiomeGenBase {
	
	protected final int variant;
	
	public static BiomeGenBase DecayingHills, DecayingValley, DecayingWasteland, DecayingMountains;
	public static BiomeGenBase PaintedForest, PaintedPlains, PaintedHills, PaintedClearing;
	public static BiomeGenBase AshenMountains, AshenHills, AshenBadlands;
	public static BiomeGenBase StarlitPrarie, StarlitPlateaus, StarlitCliffs, StarlitLowlands;
	public static BiomeGenBase TaintedSpikes, TaintedLowlands, TaintedRises, TaintedScarlands, TaintedIsles;
	public static BiomeGenBase Synapse;
	public static BiomeGenBase HallowedHills, HallowedForest, HallowedPrarie, HallowedCliffs;
	public static BiomeGenBase ScorchedWastelands, ScorchedValley, ScorchedScarlands;
	public static BiomeGenBase CorrodedSteppe, CorrodedHeights, CorrodedVeld, CorrodedRunoff, CorrodedFallout;
	public static BiomeGenBase FrozenTundra,FrozenHills,FrozenDepths;
	public static BiomeGenBase Crystal;
	public static BiomeGenBase DarkForest, DarkForestHills, DarkMarsh;

	public TragicBiome(int par1, int par2) {
		super(par1, true);
		this.variant = par2;
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.fillerBlock = TragicBlocks.DarkStone;
		this.topBlock = TragicBlocks.DeadDirt;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = -999;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.bigMushroomsPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.heightVariation = 0F;
		this.rootHeight = 0F;
		if (TragicConfig.allowPlague) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPlague.class, TragicConfig.plagueSC, 1, 3));
		if (TragicConfig.allowTragicNeko) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTragicNeko.class, TragicConfig.tragicNekoSC, 0, 1));
		if (TragicConfig.allowTimeController) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTimeController.class, TragicConfig.timeControllerSC, 0, 1));
		if (TragicConfig.allowErkel) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityErkel.class, TragicConfig.erkelSC, 0, 2));
		if (TragicConfig.allowWisp) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWisp.class, TragicConfig.wispSC, 0, 2));
	}

	@Override
	public BiomeGenBase setBiomeName(String s)
	{
		this.biomeName = StatCollector.translateToLocal(s);
		return this;
	}

	/**
	 * Returns the flowers per chunk based on specific biome, this is for my custom flower world gen that uses my own flowers
	 * @return
	 */
	public int getFlowersFromBiomeType()
	{
		return 0;
	}

	/**
	 * Returns the bushes per chunk based on specific biome, this is for my custom bush worldgen within the flower worldgen
	 * @return
	 */
	public int getBushesFromBiomeType()
	{
		return 0;
	}

	@Override
	public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
	{
		this.genTragicBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
	} 

	public final void genTragicBiomeTerrain(World world, Random rand, Block[] blockArray, byte[] byteArray, int x, int y, double z)
	{
		boolean flag = true;
		Block block = this.topBlock;
		byte b0 = (byte)(this.field_150604_aj & 255);
		Block block1 = this.fillerBlock;
		int k = -1;
		int l = (int)(z / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int i1 = x & 15;
		int j1 = y & 15;
		int k1 = blockArray.length / 256;

		for (int l1 = 255; l1 >= 0; --l1)
		{
			int i2 = (j1 * 16 + i1) * k1 + l1;

			if (l1 <= 0 + rand.nextInt(5))
			{
				blockArray[i2] = Blocks.air;
			}
			else
			{
				Block block2 = blockArray[i2];

				if (block2 != null && block2.getMaterial() != Material.air)
				{
					if (block2 == TragicBlocks.DarkStone)
					{
						if (k == -1)
						{
							if (l <= 0)
							{
								block = null;
								b0 = 0;
								block1 = TragicBlocks.DarkStone;
							}
							else if (l1 >= 51 && l1 <= 64)
							{
								block = this.topBlock;
								b0 = (byte)(this.field_150604_aj & 255);
								block1 = this.fillerBlock;
							}

							k = l;

							if (l1 >= 37)
							{
								blockArray[i2] = block;
								byteArray[i2] = b0;
							}
							else if (l1 < 42 - l)
							{
								block = null;
								block1 = TragicBlocks.DarkStone;
								blockArray[i2] = TragicBlocks.DeadDirt;
							}
							else
							{
								blockArray[i2] = block1;
							}
						}
						else if (k > 0)
						{
							--k;
							blockArray[i2] = block1;
						}
					}
				}
				else
				{
					k = -1;
				}
			}
		}
	}
	
	public static void load()
	{
		DecayingHills = new BiomeGenDecayingWasteland(TragicConfig.idDecayingHills, 0).setBiomeName("tragicmc.decayingHills");
		DecayingValley = new BiomeGenDecayingWasteland(TragicConfig.idDecayingValley, 1).setBiomeName("tragicmc.decayingValley");
		DecayingWasteland = new BiomeGenDecayingWasteland(TragicConfig.idDecayingWasteland, 2).setBiomeName("tragicmc.decayingWasteland");
		DecayingMountains = new BiomeGenDecayingWasteland(TragicConfig.idDecayingMountains, 3).setBiomeName("tragicmc.decayingMountains");
		
		PaintedForest = new BiomeGenPaintedForest(TragicConfig.idPaintedForest, 0).setBiomeName("tragicmc.paintedForest");
		PaintedPlains = new BiomeGenPaintedForest(TragicConfig.idPaintedPlains, 1).setBiomeName("tragicmc.paintedPlains");
		PaintedHills = new BiomeGenPaintedForest(TragicConfig.idPaintedHills, 2).setBiomeName("tragicmc.paintedHills");
		PaintedClearing = new BiomeGenPaintedForest(TragicConfig.idPaintedClearing, 3).setBiomeName("tragicmc.paintedClearing");
		
		AshenMountains = new BiomeGenAshenHills(TragicConfig.idAshenMountains, 0).setBiomeName("tragicmc.ashenMountains");
		AshenHills = new BiomeGenAshenHills(TragicConfig.idAshenHills, 1).setBiomeName("tragicmc.ashenHills");
		AshenBadlands = new BiomeGenAshenHills(TragicConfig.idAshenBadlands, 2).setBiomeName("tragicmc.ashenBadlands");
		
		StarlitPrarie = new BiomeGenStarlitPrarie(TragicConfig.idStarlitPrarie, 0).setBiomeName("tragicmc.starlitPrarie");
		StarlitPlateaus = new BiomeGenStarlitPrarie(TragicConfig.idStarlitPlateaus, 1).setBiomeName("tragicmc.starlitPlateaus");
		StarlitCliffs = new BiomeGenStarlitPrarie(TragicConfig.idStarlitCliffs, 2).setBiomeName("tragicmc.starlitCliffs");
		StarlitLowlands = new BiomeGenStarlitPrarie(TragicConfig.idStarlitLowlands, 3).setBiomeName("tragicmc.starlitLowlands");
		
		TaintedSpikes = new BiomeGenTaintedSpikes(TragicConfig.idTaintedSpikes, 0).setBiomeName("tragicmc.taintedSpikes");
		TaintedLowlands = new BiomeGenTaintedSpikes(TragicConfig.idTaintedLowlands, 1).setBiomeName("tragicmc.taintedLowlands");
		TaintedRises = new BiomeGenTaintedSpikes(TragicConfig.idTaintedRises, 2).setBiomeName("tragicmc.taintedRises");
		TaintedScarlands = new BiomeGenTaintedSpikes(TragicConfig.idTaintedScarlands, 3).setBiomeName("tragicmc.taintedScarlands");
		TaintedIsles = new BiomeGenTaintedSpikes(TragicConfig.idTaintedIsles, 4).setBiomeName("tragicmc.taintedIsles");
		
		Synapse = new BiomeGenSynapse(TragicConfig.idSynapse).setBiomeName("tragicmc.synapse");
		
		HallowedHills = new BiomeGenHallowedHills(TragicConfig.idHallowedHills, 0).setBiomeName("tragicmc.hallowedHills");
		HallowedForest = new BiomeGenHallowedHills(TragicConfig.idHallowedForest, 1).setBiomeName("tragicmc.hallowedForest");
		HallowedPrarie = new BiomeGenHallowedHills(TragicConfig.idHallowedPrarie, 2).setBiomeName("tragicmc.hallowedPrarie");
		HallowedCliffs = new BiomeGenHallowedHills(TragicConfig.idHallowedCliffs, 3).setBiomeName("tragicmc.hallowedCliffs");
		
		ScorchedWastelands = new BiomeGenScorchedWasteland(TragicConfig.idScorchedWastelands, 0).setBiomeName("tragicmc.scorchedWastelands");
		ScorchedValley = new BiomeGenScorchedWasteland(TragicConfig.idScorchedValley, 1).setBiomeName("tragicmc.scorchedValley");
		ScorchedScarlands = new BiomeGenScorchedWasteland(TragicConfig.idScorchedScarlands, 2).setBiomeName("tragicmc.scorchedScarlands");
		
		CorrodedSteppe = new BiomeGenCorrodedSteppe(TragicConfig.idCorrodedSteppe, 0).setBiomeName("tragicmc.corrodedSteppe");
		CorrodedHeights = new BiomeGenCorrodedSteppe(TragicConfig.idCorrodedHeights, 1).setBiomeName("tragicmc.corrodedHeights");
		CorrodedVeld = new BiomeGenCorrodedSteppe(TragicConfig.idCorrodedVeld, 2).setBiomeName("tragicmc.corrodedVeld");
		CorrodedRunoff = new BiomeGenCorrodedSteppe(TragicConfig.idCorrodedRunoff, 3).setBiomeName("tragicmc.corrodedRunoff");
		CorrodedFallout = new BiomeGenCorrodedSteppe(TragicConfig.idCorrodedFallout, 4).setBiomeName("tragicmc.corrodedFallout");
		
		FrozenTundra = new BiomeGenFrozenTundra(TragicConfig.idFrozenTundra, 0).setBiomeName("tragicmc.frozenTundra");
		FrozenHills = new BiomeGenFrozenTundra(TragicConfig.idFrozenHills, 1).setBiomeName("tragicmc.frozenHills");
		FrozenDepths = new BiomeGenFrozenTundra(TragicConfig.idFrozenDepths, 2).setBiomeName("tragicmc.frozenDepths");
		
		Crystal = new BiomeGenCrystal(TragicConfig.idCrystal).setBiomeName("tragicmc.crystal");
		
		DarkForest = new BiomeGenDarkForest(TragicConfig.idDarkForest, 0).setBiomeName("tragicmc.darkForest");
		DarkForestHills = new BiomeGenDarkForest(TragicConfig.idDarkForestHills, 1).setBiomeName("tragicmc.darkForestHills");
		DarkMarsh = new BiomeGenDarkForest(TragicConfig.idDarkMarsh, 2).setBiomeName("tragicmc.darkMarsh");
	}
}
