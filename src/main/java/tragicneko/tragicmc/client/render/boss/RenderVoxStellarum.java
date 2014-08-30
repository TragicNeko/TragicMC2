package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelNorVox;
import tragicneko.tragicmc.entity.boss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class RenderVoxStellarum extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/StarVox_lowRes.png");
	private static final float scale = 2.25F;

	private static float rgbR = 1.0F;
	private static float rgbG = 1.0F;
	private static float rgbB = 1.0F;

	public RenderVoxStellarum() {
		super(new ModelNorVox(), 0.75F * scale);
	}

	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.renderModel((EntityVoxStellarum) par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
	}

	private void renderModel(EntityVoxStellarum par1EntityLivingBase, float par2, 	float par3, float par4, float par5, float par6, float par7) 
	{
		this.bindEntityTexture(par1EntityLivingBase);

		if (!par1EntityLivingBase.isInvisible() && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
		{       
			GL11.glPushMatrix();

			this.setRGBThroughTextureID(par1EntityLivingBase.getTextureID());

			GL11.glColor4f(rgbR, rgbG, rgbB, 0.75F);
			GL11.glDepthMask(false);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
			if (par1EntityLivingBase.isSpinning())
			{
				float f0 = par1EntityLivingBase.getSpinRotation();
				GL11.glRotatef(f0, 0.0F, 1.0F, 0.0F);
			}
			this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.2F);
			GL11.glPopMatrix();
			GL11.glDepthMask(true);

		}
		else
		{
			this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityLivingBase);
		}

	}

	private void setRGBThroughTextureID(int textureID) {
		switch(textureID)
		{
		default:
		case 0:
			rgbR = 0.75F;
			rgbG = 0.75F;
			rgbB = 0.75F;
			break;
		case 1:
			rgbR = 0.75F;
			rgbG = 0.25F;
			rgbB = 0.25F;
			break;
		case 2:
			rgbR = 0.25F;
			rgbG = 0.75F;
			rgbB = 0.25F;
			break;
		case 3:
			rgbR = 0.25F;
			rgbG = 0.25F;
			rgbB = 0.75F;
			break;
		case 4:
			rgbR = 0.25F;
			rgbG = 0.75F;
			rgbB = 0.75F;
			break;
		case 5:
			rgbR = 0.75F;
			rgbG = 0.75F;
			rgbB = 0.25F;
			break;
		case 6:
			rgbR = 0.75F;
			rgbG = 0.25F;
			rgbB = 0.75F;
			break;
		case 7:
			rgbR = 0.25F;
			rgbG = 0.25F;
			rgbB = 0.25F;
			break;
		}

		rgbR *= 2.55F;
		rgbG *= 2.55F;
		rgbB *= 2.55F;
	}

	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}
	
	protected int shouldRenderPass(EntityVoxStellarum boss, int par2, float par3)
	{
		if (boss.isHealing())
		{
			if (boss.isInvisible())
			{
				GL11.glDepthMask(false);
			}
			else
			{
				GL11.glDepthMask(true);
			}

			if (par2 == 1)
			{
				float f1 = (float)boss.ticksExisted + par3;
				this.bindTexture(texture);
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				float f2 = MathHelper.cos(f1 * 0.02F) * 3.0F;
				float f3 = f1 * 0.01F;
				GL11.glTranslatef(f2, f3, 0.0F);
				this.setRenderPassModel(this.mainModel);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_BLEND);
				float f4 = 0.5F;
				GL11.glColor4f(f4, f4, f4, 1.0F);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				GL11.glTranslatef(0.0F, 0.05F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				return 1;
			}

			if (par2 == 2)
			{
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_BLEND);
			}
		}

		return -1;
	}

	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return this.shouldRenderPass((EntityVoxStellarum)par1EntityLivingBase, par2, par3);
	}

	protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return -1;
	}
	
}
