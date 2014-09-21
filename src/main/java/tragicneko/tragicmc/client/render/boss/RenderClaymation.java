package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelApis;
import tragicneko.tragicmc.client.model.ModelClaymation;
import tragicneko.tragicmc.client.model.ModelCustomGolem;
import tragicneko.tragicmc.client.model.ModelDeathReaper;
import tragicneko.tragicmc.client.model.ModelJabba;
import tragicneko.tragicmc.client.model.ModelKitsune;
import tragicneko.tragicmc.client.model.ModelMinotaur;
import tragicneko.tragicmc.client.model.ModelNorVox;
import tragicneko.tragicmc.client.model.ModelRagr;
import tragicneko.tragicmc.client.model.ModelStinKing;
import tragicneko.tragicmc.entity.boss.EntityClaymation;

public class RenderClaymation extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Claymation_lowRes.png");

	public RenderClaymation() {
		super(new ModelClaymation(), 0.556F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		this.mainModel = this.getModelFromClaymationForm((EntityClaymation) entity);
		this.scale = this.getScaleFromClaymationForm((EntityClaymation) entity);
		GL11.glScalef(scale, scale, scale);
	}

	private float getScaleFromClaymationForm(EntityClaymation clay) {
		switch(clay.getEntityForm())
		{
		case 3:
			return 1.625F;
		case 0:
		case 4:
			return 1.445F;
		default:
			return 1.0F;
		}
	}

	private ModelBase getModelFromClaymationForm(EntityClaymation clay) {
		switch(clay.getEntityForm())
		{
		case 1:
			return new ModelMinotaur();
		case 2:
			return new ModelApis();
		case 3:
			return new ModelStinKing();
		case 4:
			return new ModelNorVox();
		case 5:
			return new ModelJabba();
		case 6:
			return new ModelRagr();
		case 7:
			return new ModelDeathReaper();
		case 8:
			return new ModelKitsune();
		case 9:
			return new ModelCustomGolem();
		default:
			return new ModelClaymation();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}

}
