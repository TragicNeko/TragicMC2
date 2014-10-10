package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class RenderDeathReaper extends RenderBoss {
	
	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/DeathReaper_lowRes.png");
	private static final ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/DeathReaper2_lowRes.png");

	public RenderDeathReaper() {
		super(new ModelDeathReaper(), 0.475F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return getEntityTexture((EntityDeathReaper) var1);
	}
	
	protected ResourceLocation getEntityTexture(EntityDeathReaper reap)
	{
		return reap.isBeingAggressive() ? texture2 : texture;
	}

}
