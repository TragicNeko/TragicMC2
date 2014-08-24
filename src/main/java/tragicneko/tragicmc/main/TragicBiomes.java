package tragicneko.tragicmc.main;

import java.util.Set;

import net.minecraft.util.StatCollector;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.worldgen.biome.BiomeGenAshenHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;
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
	
	public static TragicBiome TaintedSpikes; //will be low hilly terrain that will use a custom world gen to create tons of nearly vertical spikes in the biome
	public static TragicBiome TaintedArchipelago; //will be very low, intermittent lava sea and have very little land
	public static TragicBiome TaintedRises; //will be mid-height but have huge tall mesas that form into it (it'll be custom as well)
	public static TragicBiome TaintedScarlands; //will be rugged mid-height terrain with lots of ravines and caves generating in it
	public static TragicBiome TaintedIsles; //will be completely covered in lava, however, islands will generate at very high levels in the sky
	//color scheme for these will be a dark green, with splashes of neon green thrown in, the spike gen will probably use toxic cobblestone
	
	public static Set decayingBiomes;
	public static Set paintedBiomes;
	public static Set ashenBiomes;
	public static Set starlitBiomes;
	public static Set taintedBiomes;

	public static void load()
	{
		DecayingHills = ((TragicBiome) (new BiomeGenDecayingWasteland(TragicNewConfig.idDecayingHills).setBiomeName("tragicmc.decayingHills").setDisableRain())).setHeightVariation(0.35F).setRootHeight(0.2F);
		DecayingValley = ((TragicBiome) (new BiomeGenDecayingWasteland(TragicNewConfig.idDecayingValley).setBiomeName("tragicmc.decayingValley").setDisableRain())).setHeightVariation(0.05F).setRootHeight(-0.35F);
		DecayingWasteland = ((TragicBiome) (new BiomeGenDecayingWasteland(TragicNewConfig.idDecayingWasteland).setBiomeName("tragicmc.decayingWasteland").setDisableRain())).setHeightVariation(0.2F).setRootHeight(0.15F);
		DecayingMountains = ((TragicBiome) (new BiomeGenDecayingWasteland(TragicNewConfig.idDecayingMountains).setBiomeName("tragicmc.decayingMountains").setDisableRain())).setHeightVariation(0.75F).setRootHeight(0.35F).setMushroomsPerChunk(8);
		
		PaintedForest = ((TragicBiome) (new BiomeGenPaintedForest(TragicNewConfig.idPaintedForest).setBiomeName("tragicmc.paintedForest"))).setHeightVariation(0.15F).setRootHeight(0.15F).setMushroomsPerChunk(8);
		PaintedPlains = ((TragicBiome) (new BiomeGenPaintedForest(TragicNewConfig.idPaintedPlains).setBiomeName("tragicmc.paintedPlains"))).setHeightVariation(0.05F).setRootHeight(0.1F).setTreesPerChunk(2).setGrassPerChunk(8);
		PaintedHills = ((TragicBiome) (new BiomeGenPaintedForest(TragicNewConfig.idPaintedHills).setBiomeName("tragicmc.paintedHills"))).setHeightVariation(0.35F).setRootHeight(0.2F);
		PaintedClearing = ((TragicBiome) (new BiomeGenPaintedForest(TragicNewConfig.idPaintedClearing).setBiomeName("tragicmc.paintedClearing"))).setHeightVariation(0.0F).setRootHeight(0.05F).setTreesPerChunk(-999);
		
		AshenMountains = ((TragicBiome) (new BiomeGenAshenHills(TragicNewConfig.idAshenMountains).setBiomeName("tragicmc.ashenMountains"))).setHeightVariation(0.85F).setRootHeight(0.45F);
		AshenHills = ((TragicBiome) (new BiomeGenAshenHills(TragicNewConfig.idAshenHills).setBiomeName("tragicmc.ashenHills"))).setHeightVariation(0.45F).setRootHeight(0.25F);
		AshenBadlands = ((TragicBiome) (new BiomeGenAshenHills(TragicNewConfig.idAshenBadlands).setBiomeName("tragicmc.ashenBadlands"))).setHeightVariation(0.05F).setRootHeight(0.35F).setGrassPerChunk(4).setTreesPerChunk(1);
		
		StarlitPrarie = ((TragicBiome) (new BiomeGenStarlitPrarie(TragicNewConfig.idStarlitPrarie).setBiomeName("tragicmc.starlitPrarie"))).setHeightVariation(0.05F).setRootHeight(0.65F);
		StarlitPlateaus = ((TragicBiome) (new BiomeGenStarlitPrarie(TragicNewConfig.idStarlitPlateaus).setBiomeName("tragicmc.starlitPlateaus"))).setHeightVariation(0.0F).setRootHeight(1.6F);
		StarlitCliffs = ((TragicBiome) (new BiomeGenStarlitPrarie(TragicNewConfig.idStarlitCliffs).setBiomeName("tragicmc.starlitCliffs"))).setHeightVariation(1.55F).setRootHeight(0.95F);
		StarlitLowlands = ((TragicBiome) (new BiomeGenStarlitPrarie(TragicNewConfig.idStarlitLowlands).setBiomeName("tragicmc.starlitLowlands"))).setHeightVariation(0.2F).setRootHeight(0.45F);
		
		TaintedSpikes = ((TragicBiome) (new BiomeGenTaintedSpikes(TragicNewConfig.idTaintedSpikes).setBiomeName("tragicmc.taintedSpikes"))).setHeightVariation(0.45F).setRootHeight(0.15F);
		
		createSets();
	}
	
	public static void createSets()
	{
		decayingBiomes = Sets.newHashSet(new BiomeGenBase[] {DecayingWasteland, DecayingHills, DecayingMountains, DecayingValley});
		paintedBiomes = Sets.newHashSet(new BiomeGenBase[] {PaintedForest, PaintedPlains, PaintedHills, PaintedClearing});
		ashenBiomes = Sets.newHashSet(new BiomeGenBase[] {AshenMountains, AshenHills, AshenBadlands});
		starlitBiomes = Sets.newHashSet(new BiomeGenBase[] {StarlitPrarie, StarlitPlateaus, StarlitCliffs, StarlitLowlands});
		taintedBiomes = Sets.newHashSet(new BiomeGenBase[] {TaintedSpikes});
	}
}
