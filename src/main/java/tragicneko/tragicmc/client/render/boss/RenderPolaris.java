package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelPolaris;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class RenderPolaris extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Polaris_lowRes.png");

	private boolean isInCombat;

	public RenderPolaris() {
		super(new ModelPolaris(), 0.335F);
	}

	protected int shouldRenderPass(TragicBoss boss, int par2, float par3)
	{
		if (boss.isInvisible())
		{
			GL11.glDepthMask(false);
		}
		else
		{
			GL11.glDepthMask(true);
		}
		
		if (boss.worldObj.getWorldInfo().getWorldTime() >= 13500 && !boss.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
		{
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
				GL11.glColor4f(0.1F, 0.65F, 0.1F, 0.35F);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				GL11.glTranslatef(0.0F, -0.001F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				return 1;
			}

			if (par2 == 2)
			{
				float f1 = (float)boss.ticksExisted + 72 + par3;
				this.bindTexture(texture);
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				float f2 = MathHelper.sin(f1 * 0.02F) * 3.0F;
				float f3 = f1 * 0.01F;
				GL11.glTranslatef(f3, f2, 0.0F);
				this.setRenderPassModel(this.mainModel);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glColor4f(0.55F, 0.1F, 0.55F, 0.35F);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				GL11.glTranslatef(0.0F, 0.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				return 2;
			}
		}

		GL11.glMatrixMode(GL11.GL_TEXTURE);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);

		return -1;
	}

	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return this.shouldRenderPass((TragicBoss)par1EntityLivingBase, par2, par3);
	}

	protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return -1;
	}	

	protected ResourceLocation getEntityTexture(Entity entity)
	{	
		return texture;
	}
}
