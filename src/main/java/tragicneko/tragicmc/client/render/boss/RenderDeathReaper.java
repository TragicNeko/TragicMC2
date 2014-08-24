package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelDeathReaper;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class RenderDeathReaper extends RenderBoss {
	
	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/DeathReaper_lowRes.png");

	public RenderDeathReaper() {
		super(new ModelDeathReaper(), 0.375F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return texture;
	}

}
