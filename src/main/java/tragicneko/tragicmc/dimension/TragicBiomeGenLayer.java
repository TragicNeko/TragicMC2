package tragicneko.tragicmc.dimension;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import tragicneko.tragicmc.TragicBiomes;
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
		biomeList = new ArrayList[8];

		ArrayList<BiomeEntry> decayingBiomes = new ArrayList<BiomeEntry>();
		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingWasteland, 20));
		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingHills, 15));
		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingMountains, 10));
		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingValley, 5));

		ArrayList<BiomeEntry> paintedBiomes = new ArrayList<BiomeEntry>();
		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedForest, 30));
		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedHills, 20));
		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedPlains, 10));
		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedClearing, 5));

		ArrayList<BiomeEntry> ashenBiomes = new ArrayList<BiomeEntry>();
		ashenBiomes.add(new BiomeEntry(TragicBiomes.AshenBadlands, 20));
		ashenBiomes.add(new BiomeEntry(TragicBiomes.AshenHills, 10));
		ashenBiomes.add(new BiomeEntry(TragicBiomes.AshenMountains, 5));

		ArrayList<BiomeEntry> starlitBiomes = new ArrayList<BiomeEntry>();
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitPrarie, 25));
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitPlateaus, 15));
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitLowlands, 10));
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitCliffs, 5));

		ArrayList<BiomeEntry> taintedBiomes = new ArrayList<BiomeEntry>();
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedLowlands, 15));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedSpikes, 25));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedIsles, 5));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedRises, 15));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedScarlands, 25));

		ArrayList<BiomeEntry> hallowedBiomes = new ArrayList<BiomeEntry>();
		hallowedBiomes.add(new BiomeEntry(TragicBiomes.HallowedHills, 15));
		hallowedBiomes.add(new BiomeEntry(TragicBiomes.HallowedForest, 25));
		hallowedBiomes.add(new BiomeEntry(TragicBiomes.HallowedPrarie, 20));
		hallowedBiomes.add(new BiomeEntry(TragicBiomes.HallowedCliffs, 5));

		ArrayList<BiomeEntry> scorchedBiomes = new ArrayList<BiomeEntry>();
		scorchedBiomes.add(new BiomeEntry(TragicBiomes.ScorchedWastelands, 10));
		scorchedBiomes.add(new BiomeEntry(TragicBiomes.ScorchedValley, 10));
		scorchedBiomes.add(new BiomeEntry(TragicBiomes.ScorchedScarlands, 10));
		
		ArrayList<BiomeEntry> corrodedBiomes = new ArrayList<BiomeEntry>();
		corrodedBiomes.add(new BiomeEntry(TragicBiomes.CorrodedSteppe, 25));
		corrodedBiomes.add(new BiomeEntry(TragicBiomes.CorrodedHeights, 15));
		corrodedBiomes.add(new BiomeEntry(TragicBiomes.CorrodedVeld, 10));
		corrodedBiomes.add(new BiomeEntry(TragicBiomes.CorrodedRunoff, 10));
		corrodedBiomes.add(new BiomeEntry(TragicBiomes.CorrodedFallout, 5));

		biomeList[0] = decayingBiomes;
		biomeList[1] = paintedBiomes;
		biomeList[2] = ashenBiomes;
		biomeList[3] = starlitBiomes;
		biomeList[4] = taintedBiomes;
		biomeList[5] = hallowedBiomes;
		biomeList[6] = scorchedBiomes;
		biomeList[7] = corrodedBiomes;
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
					dest[dx + dz * width] = ((BiomeEntry)WeightedRandom.getItem(biomes, this.nextInt(WeightedRandom.getTotalWeight(biomes)))).biome.biomeID;
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
