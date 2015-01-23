package tragicneko.tragicmc.client.render.boss;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelOverlordCore;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityOverlordCore;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class RenderOverlordCore extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:Meow");

	public RenderOverlordCore() {
		super(new ModelOverlordCore(), 0.756F, 1.75F);
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{		
		GL11.glScalef(this.scale, this.scale, this.scale);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityOverlordCore) entity);
	}

	public ResourceLocation getEntityTexture(EntityOverlordCore core)
	{
		return texture;
	}

}
