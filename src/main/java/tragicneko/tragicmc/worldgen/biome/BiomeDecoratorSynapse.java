package tragicneko.tragicmc.worldgen.biome;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.dimension.SynapseWorldProvider;

public class BiomeDecoratorSynapse extends BiomeDecorator {

	@Override
	protected void genDecorations(BiomeGenBase biome)
	{
		if (this.chunk_X == 0 && this.chunk_Z == 0 && this.currentWorld.provider instanceof SynapseWorldProvider)
		{
			EntityDragon entitydragon = new EntityDragon(this.currentWorld);
			entitydragon.setLocationAndAngles(0.0D, 64.0D, 0.0D, this.randomGenerator.nextFloat() * 360.0F, 0.0F);
			this.currentWorld.spawnEntityInWorld(entitydragon);
		}
	}
}
