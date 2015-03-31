package tragicneko.tragicmc.client.render;

import java.util.Random;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityDirectedLightning;

public class RenderDirectedLightning extends Render {
	
	private static final ResourceLocation enderDragonCrystalBeamTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");

	public void doRender(EntityDirectedLightning bolt, double par1, double par2, double par3, float par4, float par5)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		double[] adouble = new double[8];
		double[] adouble1 = new double[8];
		double d3 = 0.0D;
		double d4 = 0.0D;
		Random random = new Random(bolt.boltVertex);

		for (int i = 7; i >= 0; --i)
		{
			adouble[i] = d3;
			adouble1[i] = d4;
			d3 += (double)(random.nextInt(11) - 5);
			d4 += (double)(random.nextInt(11) - 5);
		}

		for (int k1 = 0; k1 < 4; ++k1)
		{
			Random random1 = new Random(bolt.boltVertex);

			for (int j = 0; j < 3; ++j)
			{
				int k = 7;
				int l = 0;

				if (j > 0)
				{
					k = 7 - j;
				}

				if (j > 0)
				{
					l = k - 2;
				}

				double d5 = adouble[k] - d3;
				double d6 = adouble1[k] - d4;

				for (int i1 = k; i1 >= l; --i1)
				{
					double d7 = d5;
					double d8 = d6;

					if (j == 0)
					{
						d5 += (double)(random1.nextInt(11) - 5);
						d6 += (double)(random1.nextInt(11) - 5);
					}
					else
					{
						d5 += (double)(random1.nextInt(31) - 15);
						d6 += (double)(random1.nextInt(31) - 15);
					}

					tessellator.startDrawing(5);
					float f2 = 0.5F;
					tessellator.setColorRGBA_F(0.9F * f2, 0.9F * f2, 1.0F * f2, 0.3F);
					double d9 = 0.1D + (double)k1 * 0.2D;

					if (j == 0)
					{
						d9 *= (double)i1 * 0.1D + 1.0D;
					}

					double d10 = 0.1D + (double)k1 * 0.2D;

					if (j == 0)
					{
						d10 *= (double)(i1 - 1) * 0.1D + 1.0D;
					}

					for (int j1 = 0; j1 < 5; ++j1)
					{
						double d11 = par1 + 0.5D - d9;
						double d12 = par3 + 0.5D - d9;

						if (j1 == 1 || j1 == 2)
						{
							d11 += d9 * 2.0D;
						}

						if (j1 == 2 || j1 == 3)
						{
							d12 += d9 * 2.0D;
						}

						double d13 = par1 + 0.5D - d10;
						double d14 = par3 + 0.5D - d10;

						if (j1 == 1 || j1 == 2)
						{
							d13 += d10 * 2.0D;
						}

						if (j1 == 2 || j1 == 3)
						{
							d14 += d10 * 2.0D;
						}

						tessellator.addVertex(d13 + d5, par2 + (double)(i1 * 16), d14 + d6);
						tessellator.addVertex(d11 + d7, par2 + (double)((i1 + 1) * 16), d12 + d8);
					}

					tessellator.draw();
				}
			}
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		Entity user = bolt.worldObj.getEntityByID(bolt.getUserID());
		
		if (user != null)
		{
			TragicMC.logInfo("User isn't null, attempting to render effect to them");
			if (user.worldObj.isRemote) return;
			float f2 = (float)user.ticksExisted + par5;
            float f3 = MathHelper.sin(f2 * 0.2F) / 2.0F + 0.5F;
            f3 = (f3 * f3 + f3) * 0.2F;
			float f4 = (float)(bolt.posX - user.posX - (user.prevPosX - user.posX) * (double)(1.0F - par5));
			float f5 = (float)((double)f3 + bolt.posY - 1.0D - user.posY - (user.prevPosY - user.posY) * (double)(1.0F - par5));
			float f6 = (float)(bolt.posZ - user.posZ - (user.prevPosZ - user.posZ) * (double)(1.0F - par5));
			float f7 = MathHelper.sqrt_float(f4 * f4 + f6 * f6);
			float f8 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6);
			GL11.glPushMatrix();
			GL11.glTranslatef((float)par2, (float)par3 + 2.0F, (float)par4);
			GL11.glRotatef((float)(Math.atan2((double)f6, (double)f4)) * 180.0F / (float)Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef((float)(Math.atan2((double)f7, (double)f5)) * 180.0F / (float)Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_CULL_FACE);
			this.bindTexture(enderDragonCrystalBeamTextures);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			float f9 = 0.0F - ((float)user.ticksExisted + par5) * 0.01F;
			float f10 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6) / 32.0F - ((float)user.ticksExisted + par5) * 0.01F;
			tessellator.startDrawing(5);
			byte b0 = 8;

			for (int i = 0; i <= b0; ++i)
			{
				float f11 = MathHelper.sin((float)(i % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F;
				float f12 = MathHelper.cos((float)(i % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F;
				float f13 = (float)(i % b0) * 1.0F / (float)b0;
				tessellator.setColorOpaque_I(0);
				tessellator.addVertexWithUV((double)(f11 * 0.2F), (double)(f12 * 0.2F), 0.0D, (double)f13, (double)f10);
				tessellator.setColorOpaque_I(16777215);
				tessellator.addVertexWithUV((double)f11, (double)f12, (double)f8, (double)f13, (double)f9);
			}

			tessellator.draw();
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glShadeModel(GL11.GL_FLAT);
			RenderHelper.enableStandardItemLighting();
			GL11.glPopMatrix();
		}
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}

	public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	{
		this.doRender((EntityDirectedLightning)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
}
