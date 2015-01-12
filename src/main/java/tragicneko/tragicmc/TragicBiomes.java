package tragicneko.tragicmc;

import java.util.Set;

import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.worldgen.biome.BiomeGenAshenHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;
import tragicneko.tragicmc.worldgen.biome.BiomeGenSynapse;
import tragicneko.tragicmc.worldgen.biome.BiomeGenTaintedSpikes;
import tragicneko.tragicmc.worldgen.biome.TragicBiome;

import com.google.common.collect.Sets;

public class TragicBiomes {

	public static TragicBiome DecayingHills;
	public static TragicBiome DecayingValley;
	public static TragicBiome DecayingWasteland;
	public static TragicBiome DecayingMountains;

	public static TragicBiome PaintedForest;
	public static TragicBiome PaintedPlains;
	public static TragicBiome PaintedHills;
	public static TragicBiome PaintedClearing;
	
	public static TragicBiome AshenMountains;
	public static TragicBiome AshenHills;
	public static TragicBiome AshenBadlands;
	
	public static TragicBiome StarlitPrarie;
	public static TragicBiome StarlitPlateaus;
	public static TragicBiome StarlitCliffs;
	public static TragicBiome StarlitLowlands;
	
	public static TragicBiome TaintedSpikes;
	public static TragicBiome TaintedLowlands;
	public static TragicBiome TaintedRises;
	public static TragicBiome TaintedScarlands;
	public static TragicBiome TaintedIsles;
	
	public static TragicBiome Synapse;
	
	public static Set decayingBiomes;
	public static Set paintedBiomes;
	public static Set ashenBiomes;
	public static Set starlitBiomes;
	public static Set taintedBiomes;
	
	public static Set magmaBiomes;
	public static Set frozenBiomes; //will be based off of the ice plains biome, frozen spikes will take from the ice spikes biome, but using my spike generator
	public static Set jungleBiomes; //will be based off of the vanilla jungle biome, but altered some, mostly aesthetic-wise and worldgen wise to reduce lag
	public static Set desertBiomes; //will be based off of the vanilla desert, etc.
	public static Set darkForestBiomes; //will be based off of the vanilla roofed forest biome, etc.
	public static Set crystalBiomes; //will be a biome made of really tough, hard to mine blocks

	public static void load()
	{
		DecayingHills = ((TragicBiome) (new BiomeGenDecayingWasteland(TragicConfig.idDecayingHills).setBiomeName("tragicmc.decayingHills").setDisableRain())).setHeightVariation(0.35F).setRootHeight(0.2F);
		DecayingValley = ((TragicBiome) (new BiomeGenDecayingWasteland(TragicConfig.idDecayingValley).setBiomeName("tragicmc.decayingValley").setDisableRain())).setHeightVariation(0.05F).setRootHeight(-0.35F);
		DecayingWasteland = ((TragicBiome) (new BiomeGenDecayingWasteland(TragicConfig.idDecayingWasteland).setBiomeName("tragicmc.decayingWasteland").setDisableRain())).setHeightVariation(0.2F).setRootHeight(0.15F);
		DecayingMountains = ((TragicBiome) (new BiomeGenDecayingWasteland(TragicConfig.idDecayingMountains).setBiomeName("tragicmc.decayingMountains").setDisableRain())).setHeightVariation(0.75F).setRootHeight(0.35F).setMushroomsPerChunk(8);
		
		PaintedForest = ((TragicBiome) (new BiomeGenPaintedForest(TragicConfig.idPaintedForest).setBiomeName("tragicmc.paintedForest"))).setHeightVariation(0.15F).setRootHeight(0.15F).setMushroomsPerChunk(8);
		PaintedPlains = ((TragicBiome) (new BiomeGenPaintedForest(TragicConfig.idPaintedPlains).setBiomeName("tragicmc.paintedPlains"))).setHeightVariation(0.05F).setRootHeight(0.1F).setTreesPerChunk(2).setGrassPerChunk(8);
		PaintedHills = ((TragicBiome) (new BiomeGenPaintedForest(TragicConfig.idPaintedHills).setBiomeName("tragicmc.paintedHills"))).setHeightVariation(0.35F).setRootHeight(0.2F);
		PaintedClearing = ((TragicBiome) (new BiomeGenPaintedForest(TragicConfig.idPaintedClearing).setBiomeName("tragicmc.paintedClearing"))).setHeightVariation(0.0F).setRootHeight(0.05F).setTreesPerChunk(-999);
		
		AshenMountains = ((TragicBiome) (new BiomeGenAshenHills(TragicConfig.idAshenMountains).setBiomeName("tragicmc.ashenMountains"))).setHeightVariation(0.85F).setRootHeight(0.45F);
		AshenHills = ((TragicBiome) (new BiomeGenAshenHills(TragicConfig.idAshenHills).setBiomeName("tragicmc.ashenHills"))).setHeightVariation(0.45F).setRootHeight(0.25F);
		AshenBadlands = ((TragicBiome) (new BiomeGenAshenHills(TragicConfig.idAshenBadlands).setBiomeName("tragicmc.ashenBadlands"))).setHeightVariation(0.05F).setRootHeight(0.35F).setGrassPerChunk(4).setTreesPerChunk(1);
		
		StarlitPrarie = ((TragicBiome) (new BiomeGenStarlitPrarie(TragicConfig.idStarlitPrarie).setBiomeName("tragicmc.starlitPrarie"))).setHeightVariation(0.05F).setRootHeight(0.65F);
		StarlitPlateaus = ((TragicBiome) (new BiomeGenStarlitPrarie(TragicConfig.idStarlitPlateaus).setBiomeName("tragicmc.starlitPlateaus"))).setHeightVariation(0.02F).setRootHeight(1.6F);
		StarlitCliffs = ((TragicBiome) (new BiomeGenStarlitPrarie(TragicConfig.idStarlitCliffs).setBiomeName("tragicmc.starlitCliffs"))).setHeightVariation(1.55F).setRootHeight(0.95F);
		StarlitLowlands = ((TragicBiome) (new BiomeGenStarlitPrarie(TragicConfig.idStarlitLowlands).setBiomeName("tragicmc.starlitLowlands"))).setHeightVariation(0.2F).setRootHeight(0.45F);
		
		TaintedSpikes = ((TragicBiome) (new BiomeGenTaintedSpikes(TragicConfig.idTaintedSpikes).setBiomeName("tragicmc.taintedSpikes"))).setHeightVariation(0.45F).setRootHeight(0.15F);
		TaintedLowlands = ((TragicBiome) (new BiomeGenTaintedSpikes(TragicConfig.idTaintedLowlands).setBiomeName("tragicmc.taintedLowlands"))).setHeightVariation(0.15F).setRootHeight(-0.35F).setMushroomsPerChunk(12);
		TaintedRises = ((TragicBiome) (new BiomeGenTaintedSpikes(TragicConfig.idTaintedRises).setBiomeName("tragicmc.taintedRises"))).setHeightVariation(1.85F).setRootHeight(0.45F);
		TaintedScarlands = ((TragicBiome) (new BiomeGenTaintedSpikes(TragicConfig.idTaintedScarlands).setBiomeName("tragicmc.taintedScarlands"))).setHeightVariation(0.05F).setRootHeight(0.65F);
		TaintedIsles = ((TragicBiome) (new BiomeGenTaintedSpikes(TragicConfig.idTaintedIsles).setBiomeName("tragicmc.taintedIsles"))).setHeightVariation(0.25F).setRootHeight(-0.65F);
		
		Synapse = (TragicBiome) (new BiomeGenSynapse(TragicConfig.idSynapse).setBiomeName("tragicmc.synapse").setDisableRain());
		
		createSets();
	}
	
	public static void createSets()
	{
		decayingBiomes = Sets.newHashSet(new BiomeGenBase[] {DecayingWasteland, DecayingHills, DecayingMountains, DecayingValley});
		paintedBiomes = Sets.newHashSet(new BiomeGenBase[] {PaintedForest, PaintedPlains, PaintedHills, PaintedClearing});
		ashenBiomes = Sets.newHashSet(new BiomeGenBase[] {AshenMountains, AshenHills, AshenBadlands});
		starlitBiomes = Sets.newHashSet(new BiomeGenBase[] {StarlitPrarie, StarlitPlateaus, StarlitCliffs, StarlitLowlands});
		taintedBiomes = Sets.newHashSet(new BiomeGenBase[] {TaintedSpikes, TaintedLowlands, TaintedRises, TaintedScarlands, TaintedIsles});
	}
}
