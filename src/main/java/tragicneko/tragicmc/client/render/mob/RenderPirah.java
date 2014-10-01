package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelPirah;
import tragicneko.tragicmc.entity.mob.EntityPirah;

public class RenderPirah extends RenderLiving {

	private ResourceLocation[] textures = new ResourceLocation[] {new ResourceLocation("tragicmc:textures/mobs/Pirah_lowRes.png"), new ResourceLocation("tragicmc:textures/mobs/Pirah2_lowRes.png"),
			new ResourceLocation("tragicmc:textures/mobs/Pirah3_lowRes.png"), new ResourceLocation("tragicmc:textures/mobs/Pirah4_lowRes.png"),
			new ResourceLocation("tragicmc:textures/mobs/Pirah5_lowRes.png"), new ResourceLocation("tragicmc:textures/mobs/Pirah6_lowRes.png"),
			new ResourceLocation("tragicmc:textures/mobs/Pirah7_lowRes.png"), new ResourceLocation("tragicmc:textures/mobs/Pirah8_lowRes.png")};
	private ResourceLocation[] textures2 = new ResourceLocation[] {new ResourceLocation("tragicmc:textures/mobs/LavaPirah_lowRes.png"), new ResourceLocation("tragicmc:textures/mobs/LavaPirah2_lowRes.png"),
			new ResourceLocation("tragicmc:textures/mobs/LavaPirah3_lowRes.png"), new ResourceLocation("tragicmc:textures/mobs/LavaPirah4_lowRes.png"),
			new ResourceLocation("tragicmc:textures/mobs/LavaPirah5_lowRes.png"), new ResourceLocation("tragicmc:textures/mobs/LavaPirah6_lowRes.png"),
			new ResourceLocation("tragicmc:textures/mobs/LavaPirah7_lowRes.png"), new ResourceLocation("tragicmc:textures/mobs/LavaPirah8_lowRes.png")};

	public RenderPirah() {
		super(new ModelPirah(), 0.255F);
	}

	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		EntityPirah pirah = (EntityPirah) entity;
		float scale = pirah.getPirahType() == 0 ? 1.0F : 1.225F;
		if (pirah.getTextureID() == 7) scale *= 1.5F;
		GL11.glScalef(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityPirah) entity);
	}

	protected ResourceLocation getEntityTexture(EntityPirah entity)
	{
		int i = entity.getTextureID();
		if (i > textures.length) i = 0; 
		return entity.getPirahType() == 0 ? textures[i] : textures2[i];
	}

}
