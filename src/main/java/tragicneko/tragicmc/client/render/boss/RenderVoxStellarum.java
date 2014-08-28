package tragicneko.tragicmc.client.render.boss;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelNorVox;

public class RenderVoxStellarum extends RenderLiving {
	
	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/StarVox_lowRes.png");
	private static final float scale = 2.25F;

	public RenderVoxStellarum() {
		super(new ModelNorVox(), 0.55F * scale);
	}
	
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
		GL11.glScalef(this.scale, this.scale, this.scale);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}

}
