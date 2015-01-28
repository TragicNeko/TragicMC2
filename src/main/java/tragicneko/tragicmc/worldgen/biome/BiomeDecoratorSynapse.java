package tragicneko.tragicmc.worldgen.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.dimension.SynapseWorldProvider;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;

public class BiomeDecoratorSynapse extends BiomeDecorator {

	@Override
	protected void genDecorations(BiomeGenBase biome)
	{
		if (this.chunk_X == 0 && this.chunk_Z == 0 && this.currentWorld.provider instanceof SynapseWorldProvider)
		{
			EntityOverlordCore overlord = new EntityOverlordCore(this.currentWorld);
			overlord.setLocationAndAngles(0.0D, 64.0D, 0.0D, this.randomGenerator.nextFloat() * 360.0F, 0.0F);
			this.currentWorld.spawnEntityInWorld(overlord);
		}
	}
}
