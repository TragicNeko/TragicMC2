package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelErkel;
import tragicneko.tragicmc.entity.mob.EntityErkel;

public class RenderErkel extends RenderLiving {
	
	private ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Erkel_lowRes.png");
	private ResourceLocation ashenTexture = new ResourceLocation("tragicmc:textures/mobs/ErkelAshen_lowRes.png");
	private ResourceLocation paintedTexture = new ResourceLocation("tragicmc:textures/mobs/ErkelPainted_lowRes.png");
	private ResourceLocation starlitTexture = new ResourceLocation("tragicmc:textures/mobs/ErkelStarlit_lowRes.png");
	private ResourceLocation decayingTexture = new ResourceLocation("tragicmc:textures/mobs/ErkelDecaying_lowRes.png");

	public RenderErkel() {
		super(new ModelErkel(), 0.625F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityErkel) entity);
	}

	private ResourceLocation getEntityTexture(EntityErkel entity) {
		switch(entity.getTextureId())
		{
		default:
		case 0:
			return texture;
		case 1:
			return ashenTexture;
		case 2:
			return paintedTexture;
		case 3:
			return starlitTexture;
		case 4:
			return decayingTexture;
		}
	}

}
