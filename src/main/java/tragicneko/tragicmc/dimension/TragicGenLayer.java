package tragicneko.tragicmc.dimension;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class TragicGenLayer {
	
	private static final int biomeSize = 6;

	public GenLayer[] createWorld(long seed, WorldType worldType)
	{
		GenLayer biomes = new TragicBiomeGenLayer(seed);
		biomes = GenLayerZoom.magnify(seed, biomes, TragicGenLayer.biomeSize);
		GenLayerVoronoiZoom voronoiZoom = new GenLayerVoronoiZoom(seed, biomes);
		
		biomes.initWorldGenSeed(seed);
		voronoiZoom.initWorldGenSeed(seed);

		return new GenLayer[] {biomes, voronoiZoom};
	}
}
