package tragicneko.tragicmc.client.render.boss;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelEnyvil;

public class RenderEnyvil extends RenderBoss {

	public RenderEnyvil() {
		super(new ModelEnyvil(), 0.725F, 2.55F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("tragicmc:textures/mobs/Enyvil_lowRes.png");
	}

}
