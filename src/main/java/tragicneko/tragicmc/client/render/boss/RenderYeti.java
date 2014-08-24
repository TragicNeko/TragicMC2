package tragicneko.tragicmc.client.render.boss;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelYeti;

public class RenderYeti extends RenderBoss {
	
	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Yeti_lowRes.png");

	public RenderYeti() {
		super(new ModelYeti(), 0.485F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return texture;
	}

}
