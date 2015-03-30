package tragicneko.tragicmc;

import java.util.Set;

import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.worldgen.biome.BiomeGenAshenHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenHallowedHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
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
	
	public static TragicBiome HallowedHills;
	public static TragicBiome HallowedForest;
	public static TragicBiome HallowedPrarie;
	public static TragicBiome HallowedCliffs;
	
	public static TragicBiome ScorchedWastelands;
	public static TragicBiome ScorchedValley; //deep terrain, will have lots of lava pools
	public static TragicBiome ScorchedScarlands; //will have geysers and steam vents aplenty, also will use inverted spike gen
	
	public static Set<BiomeGenBase> frozenBiomes;
	public static Set<BiomeGenBase> corrodedBiomes;
	public static Set<BiomeGenBase> darkForestBiomes; //will be based off of the vanilla roofed forest biome, etc.
	public static Set<BiomeGenBase> crystalBiomes; //will be a biome made of really tough, hard to mine blocks

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
		
		HallowedHills = ((TragicBiome) (new BiomeGenHallowedHills(TragicConfig.idHallowedHills).setBiomeName("tragicmc.hallowedHills"))).setHeightVariation(0.35F).setRootHeight(0.6F).setFlowersPerChunk(4).setTreesPerChunk(4);
		HallowedForest = ((TragicBiome) (new BiomeGenHallowedHills(TragicConfig.idHallowedForest).setBiomeName("tragicmc.hallowedForest"))).setHeightVariation(0.15F).setRootHeight(0.55F).setFlowersPerChunk(8).setTreesPerChunk(16);
		HallowedPrarie = ((TragicBiome) (new BiomeGenHallowedHills(TragicConfig.idHallowedPrarie).setBiomeName("tragicmc.hallowedPrarie"))).setHeightVariation(0.05F).setRootHeight(0.65F).setFlowersPerChunk(16).setTreesPerChunk(8);
		HallowedCliffs = ((TragicBiome) (new BiomeGenHallowedHills(TragicConfig.idHallowedCliffs).setBiomeName("tragicmc.hallowedCliffs"))).setHeightVariation(1.55F).setRootHeight(0.85F).setFlowersPerChunk(4).setTreesPerChunk(0);
		
		ScorchedWastelands = ((TragicBiome) (new BiomeGenScorchedWasteland(TragicConfig.idScorchedWastelands).setBiomeName("tragicmc.scorchedWastelands"))).setHeightVariation(0.2F).setRootHeight(0.15F);
		ScorchedValley = ((TragicBiome) (new BiomeGenScorchedWasteland(TragicConfig.idScorchedValley).setBiomeName("tragicmc.scorchedValley"))).setHeightVariation(0.05F).setRootHeight(-0.35F);
		ScorchedScarlands = ((TragicBiome) (new BiomeGenScorchedWasteland(TragicConfig.idScorchedScarlands).setBiomeName("tragicmc.scorchedScarlands"))).setHeightVariation(0.05F).setRootHeight(0.65F);
	}
}
