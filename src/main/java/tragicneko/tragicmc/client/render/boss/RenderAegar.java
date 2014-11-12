package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelAegar;
import tragicneko.tragicmc.entity.miniboss.EntityAegar;

public class RenderAegar extends RenderLiving {
	
	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Aegar_lowRes.png");
	private static final ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/AegarDamaged_lowRes.png");
	
	protected float scale;

	public RenderAegar() {
		super(new ModelAegar(), 0.845F);
		this.scale = 1.745F;
	}
	
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		GL11.glScalef(scale, scale, scale);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityAegar) entity);
	}

	private ResourceLocation getEntityTexture(EntityAegar entity) {
		return entity.getHypermode() ? texture2 : texture;
	}

}
