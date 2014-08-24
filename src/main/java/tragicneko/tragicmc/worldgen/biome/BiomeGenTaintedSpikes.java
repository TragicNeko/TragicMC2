package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.worldgen.WorldGenBleachedTree;

public class BiomeGenTaintedSpikes extends TragicBiome {

	public BiomeGenTaintedSpikes(int par1) {
		super(par1);
		this.enableSnow = false;
		this.enableRain = false;
		this.temperature = 1.8F;
		this.rainfall = 0.0F;
		this.heightVariation = 0.45F; 
		this.rootHeight = 0.25F;
		this.fillerBlock = TragicBlocks.DarkStone;
		this.topBlock = TragicBlocks.ErodedStone;
	}
}
