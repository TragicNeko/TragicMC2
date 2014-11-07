package tragicneko.tragicmc.worldgen.biome;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeDecoratorSynapse extends BiomeDecorator {

	protected void genDecorations(BiomeGenBase p_150513_1_)
    {
        if (this.chunk_X == 0 && this.chunk_Z == 0)
        {
            EntityDragon entitydragon = new EntityDragon(this.currentWorld);
            entitydragon.setLocationAndAngles(0.0D, 128.0D, 0.0D, this.randomGenerator.nextFloat() * 360.0F, 0.0F);
            this.currentWorld.spawnEntityInWorld(entitydragon);
        }
    }
}
