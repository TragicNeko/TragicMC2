package tragicneko.tragicmc.client.render.boss;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelKragul;
import tragicneko.tragicmc.entity.boss.EntityOverlordCore;

public class RenderOverlordCore extends RenderBoss {
	
	private ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Kragul_lowRes");

	public RenderOverlordCore() {
		super(new ModelKragul(), 0.556F, 14.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityOverlordCore) entity);
	}
	
	public ResourceLocation getEntityTexture(EntityOverlordCore core)
	{
		return texture;
	}

}
