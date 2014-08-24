package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelKitsune;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class RenderKitsune extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Kitsune_lowRes.png");

	public RenderKitsune() {
		super(new ModelKitsune(), 0.375F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return texture;
	}

}
