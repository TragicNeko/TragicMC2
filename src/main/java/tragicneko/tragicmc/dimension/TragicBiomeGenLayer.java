package tragicneko.tragicmc.dimension;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;

public class TragicBiomeGenLayer extends GenLayer {
	
	private ArrayList[] biomeList;

	public TragicBiomeGenLayer(long seed, GenLayer genlayer)
	{
		this(seed);
		this.parent = genlayer;
	}

	public TragicBiomeGenLayer(long seed)
	{
		super(seed);
		biomeList = new ArrayList[10];

		ArrayList<BiomeEntry> decayingBiomes = new ArrayList<BiomeEntry>();
		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingWasteland, TragicConfig.decayingWastelandW));
		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingHills, TragicConfig.decayingHillsW));
		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingMountains, TragicConfig.decayingMountainsW));
		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingValley, TragicConfig.decayingValleyW));

		ArrayList<BiomeEntry> paintedBiomes = new ArrayList<BiomeEntry>();
		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedForest, TragicConfig.paintedForestW));
		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedHills, TragicConfig.paintedHillsW));
		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedPlains, TragicConfig.paintedPlainsW));
		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedClearing, TragicConfig.paintedClearingW));

		ArrayList<BiomeEntry> ashenBiomes = new ArrayList<BiomeEntry>();
		ashenBiomes.add(new BiomeEntry(TragicBiomes.AshenBadlands, TragicConfig.ashenBadlandsW));
		ashenBiomes.add(new BiomeEntry(TragicBiomes.AshenHills, TragicConfig.ashenHillsW));
		ashenBiomes.add(new BiomeEntry(TragicBiomes.AshenMountains, TragicConfig.ashenMountainsW));

		ArrayList<BiomeEntry> starlitBiomes = new ArrayList<BiomeEntry>();
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitPrarie, TragicConfig.starlitPrarieW));
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitPlateaus, TragicConfig.starlitPlateausW));
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitLowlands, TragicConfig.starlitLowlandsW));
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitCliffs, TragicConfig.starlitCliffsW));

		ArrayList<BiomeEntry> taintedBiomes = new ArrayList<BiomeEntry>();
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedLowlands, TragicConfig.taintedLowlandsW));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedSpikes, TragicConfig.taintedSpikesW));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedIsles, TragicConfig.taintedIslesW));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedRises, TragicConfig.taintedRisesW));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedScarlands, TragicConfig.taintedScarlandsW));

		ArrayList<BiomeEntry> hallowedBiomes = new ArrayList<BiomeEntry>();
		hallowedBiomes.add(new BiomeEntry(TragicBiomes.HallowedHills, TragicConfig.hallowedHillsW));
		hallowedBiomes.add(new BiomeEntry(TragicBiomes.HallowedForest, TragicConfig.hallowedForestW));
		hallowedBiomes.add(new BiomeEntry(TragicBiomes.HallowedPrarie, TragicConfig.hallowedPrarieW));
		hallowedBiomes.add(new BiomeEntry(TragicBiomes.HallowedCliffs, TragicConfig.hallowedCliffsW));

		ArrayList<BiomeEntry> scorchedBiomes = new ArrayList<BiomeEntry>();
		scorchedBiomes.add(new BiomeEntry(TragicBiomes.ScorchedWastelands, TragicConfig.scorchedWastelandsW));
		scorchedBiomes.add(new BiomeEntry(TragicBiomes.ScorchedValley, TragicConfig.scorchedValleyW));
		scorchedBiomes.add(new BiomeEntry(TragicBiomes.ScorchedScarlands, TragicConfig.scorchedScarlandsW));
		
		ArrayList<BiomeEntry> corrodedBiomes = new ArrayList<BiomeEntry>();
		corrodedBiomes.add(new BiomeEntry(TragicBiomes.CorrodedSteppe, TragicConfig.corrodedSteppeW));
		corrodedBiomes.add(new BiomeEntry(TragicBiomes.CorrodedHeights, TragicConfig.corrodedHeightsW));
		corrodedBiomes.add(new BiomeEntry(TragicBiomes.CorrodedVeld, TragicConfig.corrodedVeldW));
		corrodedBiomes.add(new BiomeEntry(TragicBiomes.CorrodedRunoff, TragicConfig.corrodedRunoffW));
		corrodedBiomes.add(new BiomeEntry(TragicBiomes.CorrodedFallout, TragicConfig.corrodedFalloutW));
		
		ArrayList<BiomeEntry> frozenBiomes = new ArrayList<BiomeEntry>();
		frozenBiomes.add(new BiomeEntry(TragicBiomes.FrozenTundra, TragicConfig.frozenTundraW));
		frozenBiomes.add(new BiomeEntry(TragicBiomes.FrozenHills, TragicConfig.frozenHillsW));
		frozenBiomes.add(new BiomeEntry(TragicBiomes.FrozenDepths, TragicConfig.frozenDepthsW));
		
		ArrayList<BiomeEntry> darkForestBiomes = new ArrayList<BiomeEntry>();
		darkForestBiomes.add(new BiomeEntry(TragicBiomes.DarkForest, TragicConfig.darkForestW));
		darkForestBiomes.add(new BiomeEntry(TragicBiomes.DarkForestHills, TragicConfig.darkForestHillsW));
		darkForestBiomes.add(new BiomeEntry(TragicBiomes.DarkMarsh, TragicConfig.darkMarshW));

		biomeList[0] = decayingBiomes;
		biomeList[1] = paintedBiomes;
		biomeList[2] = ashenBiomes;
		biomeList[3] = starlitBiomes;
		biomeList[4] = taintedBiomes;
		biomeList[5] = hallowedBiomes;
		biomeList[6] = scorchedBiomes;
		biomeList[7] = corrodedBiomes;
		biomeList[8] = frozenBiomes;
		biomeList[9] = darkForestBiomes;
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++)
		{
			for (int dx = 0; dx < width; dx++)
			{
				this.initChunkSeed(dx + x, dz + z);
				try
				{
					List<BiomeEntry> biomes = biomeList[this.nextInt(biomeList.length)];
					dest[dx + dz * width] = this.nextInt(1000) <= TragicConfig.crystalW ? TragicBiomes.Crystal.biomeID : ((BiomeEntry)WeightedRandom.getItem(biomes, this.nextInt(WeightedRandom.getTotalWeight(biomes)))).biome.biomeID;
				}
				catch (Exception e)
				{
					TragicMC.logError("Error adding a biome entry to the gen layer, defaulting biome to Decaying Wasteland", e);
					dest[dx + dz * width] = TragicBiomes.DecayingWasteland.biomeID;
					continue;
				}
			}
		}

		return dest;
	}
}
