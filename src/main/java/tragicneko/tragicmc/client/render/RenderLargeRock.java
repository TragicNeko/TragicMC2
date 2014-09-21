package tragicneko.tragicmc.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import tragicneko.tragicmc.client.model.ModelLargeRock;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;

public class RenderLargeRock extends Render {

	private ResourceLocation texture = new ResourceLocation("tragicmc:textures/entities/LargeRock_lowRes.png");
	public ModelBase model;
	
	public RenderLargeRock()
	{
		super();
		this.model = new ModelLargeRock();
	}

	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		this.doRender((EntityLargeRock) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}

	public void doRender(EntityLargeRock entity, double par2, double par3, double par4, float par5, float par6) {
		float f = -0.225F;		
		float f1 = 0.025F;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glTranslatef((float)par2, (float)par3 + f, (float)par4);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		float f2 = (float) Math.sin(entity.ticksExisted);
		float f3 = (float) Math.cos(entity.ticksExisted);
		GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(f3, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(2.25F, 2.25F, 2.25F);
		float f0 = entity.ticksExisted;
		GL11.glRotatef(f0, 0.0F, 1.0F, 0.0F);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		this.bindTexture(this.getEntityTexture(entity));
		this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f1);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}

}
