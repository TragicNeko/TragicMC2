package tragicneko.tragicmc.client.render.alpha;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelOverlordCore;
import tragicneko.tragicmc.client.render.boss.RenderBoss;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;

public class RenderOverlordCore extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/OverlordCore_lowRes.png");
	private static final ResourceLocation damagedTexture = new ResourceLocation("tragicmc:textures/mobs/OverlordCoreDamaged_lowRes.png");

	public RenderOverlordCore() {
		super(new ModelOverlordCore(), 0.756F, 2.25F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{		
		GL11.glScalef(this.scale, this.scale, this.scale);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(0F, 0.5F, 0F);
	}

	@Override
	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.bindEntityTexture(par1EntityLivingBase);
		EntityOverlordCore core = (EntityOverlordCore) par1EntityLivingBase;
		
		if (!core.isInvisible() && core.getVulnerableTicks() == 0)
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
		EntityOverlordCore core = (EntityOverlordCore) entity;
		
		if (core.getVulnerableTicks() == 0) return -1;
		
		if (core.isInvisible())
		{
			return -1;
		}
		else if (pass == 0)
		{
			this.setRenderPassModel(this.mainModel);
			GL11.glEnable(GL11.GL_NORMALIZE);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			return 1;
		}
		else if (pass == 1)
		{
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}

		return -1;

	} 

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityOverlordCore) entity);
	}

	public ResourceLocation getEntityTexture(EntityOverlordCore core)
	{
		return core.getVulnerableTicks() > 0 ? damagedTexture : texture;
	}

}
