package tragicneko.tragicmc.dimension;

import java.util.Random;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import tragicneko.tragicmc.TragicMC;

public class TragicGenLayer {
	
	private static final int biomeSize = 5;

	public GenLayer[] createWorld(long seed, WorldType worldType)
	{
		GenLayer biomes = new TragicBiomeGenLayer(seed);
		biomes = GenLayerZoom.magnify(seed, biomes, this.biomeSize);
		GenLayerVoronoiZoom voronoiZoom = new GenLayerVoronoiZoom(seed, biomes);
		
		biomes.initWorldGenSeed(seed);
		voronoiZoom.initWorldGenSeed(seed);

		return new GenLayer[] {biomes, voronoiZoom};
	}
}
