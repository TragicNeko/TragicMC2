package tragicneko.tragicmc.client.render.alpha;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelOverlordCombat;
import tragicneko.tragicmc.client.render.boss.RenderBoss;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;

public class RenderOverlordCombat extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/OverlordCombat.png");

	public RenderOverlordCombat() {
		super(new ModelOverlordCombat(), 0.756F, 2.25F);
	}
	
	@Override
	protected void rotateCorpse(EntityLivingBase entity, float par1, float par2, float par3)
	{
		
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{		
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	@Override
	protected void renderModel(EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		EntityOverlordCombat core = (EntityOverlordCombat) entity;
		
		if (core.deathTime > 0)
        {/*
            float f6 = (float)core.deathTime / 300.0F;
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glAlphaFunc(GL11.GL_GREATER, f6);
            this.bindTexture(enderDragonExplodingTextures);
            GL11.glTranslated(entity.getRNG().nextGaussian() * 0.3, entity.getRNG().nextGaussian() * 0.3, entity.getRNG().nextGaussian() * 0.3);
            this.mainModel.render(core, par2, par3, par4, par5, par6, par7);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glDepthFunc(GL11.GL_EQUAL); */
        }
		
		this.bindEntityTexture(core);
		
		if (!core.isInvisible())
		{
			this.mainModel.render(core, par2, par3, par4, par5, par6, par7);
		}
		else if (!core.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
		{
			float trans = 1.0F;

			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, trans);
			GL11.glDepthMask(false);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.03921569F);
			if (core.deathTime > 0) GL11.glTranslated(entity.getRNG().nextGaussian() * 0.2, entity.getRNG().nextGaussian() * 0.2, entity.getRNG().nextGaussian() * 0.2);
			this.mainModel.render(core, par2, par3, par4, par5, par6, par7);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
			GL11.glDepthMask(true);
			GL11.glPopMatrix();
		}
		else
		{
			this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, core);
		}
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entity, int pass, float partialTick)
	{
		return -1;

	} 

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityOverlordCombat) entity);
	}

	public ResourceLocation getEntityTexture(EntityOverlordCombat core)
	{
		return texture;
	}

}
