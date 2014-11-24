package tragicneko.tragicmc.client.render;

import static tragicneko.tragicmc.main.TragicItems.Projectile;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityThrowingRock;
import tragicneko.tragicmc.main.TragicItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderProjectile extends Render
{
	private int damage = 0;

	public RenderProjectile() {}
	
	public RenderProjectile(int damage) {
		this.damage = damage;
	}

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		float scale = 0.5F;
		if (par1Entity instanceof EntityThrowingRock && ((EntityThrowingRock)par1Entity).isLavaRock()) this.damage = 1;
		IIcon icon = Projectile.getIconFromDamage(this.damage);

		if (icon != null)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float)par2, (float)par4, (float)par6);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(scale, scale, scale);
			this.bindEntityTexture(par1Entity);
			Tessellator tessellator = Tessellator.instance;
			this.func_77026_a(tessellator, icon);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return TextureMap.locationItemsTexture;
	}

	private void func_77026_a(Tessellator par1Tessellator, IIcon par2Icon)
	{
		float f = par2Icon.getMinU();
		float f1 = par2Icon.getMaxU();
		float f2 = par2Icon.getMinV();
		float f3 = par2Icon.getMaxV();
		float f4 = 1.0F;
		float f5 = 0.5F;
		float f6 = 0.25F;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		par1Tessellator.startDrawingQuads();
		par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
		par1Tessellator.addVertexWithUV((double)(0.0F - f5), (double)(0.0F - f6), 0.0D, (double)f, (double)f3);
		par1Tessellator.addVertexWithUV((double)(f4 - f5), (double)(0.0F - f6), 0.0D, (double)f1, (double)f3);
		par1Tessellator.addVertexWithUV((double)(f4 - f5), (double)(f4 - f6), 0.0D, (double)f1, (double)f2);
		par1Tessellator.addVertexWithUV((double)(0.0F - f5), (double)(f4 - f6), 0.0D, (double)f, (double)f2);
		par1Tessellator.draw();
	}
}