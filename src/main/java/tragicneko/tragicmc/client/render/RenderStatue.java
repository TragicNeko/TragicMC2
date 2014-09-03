package tragicneko.tragicmc.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import tragicneko.tragicmc.client.model.ModelApis;
import tragicneko.tragicmc.client.model.ModelBlock;
import tragicneko.tragicmc.client.model.ModelDeathReaper;
import tragicneko.tragicmc.client.model.ModelGreaterStin;
import tragicneko.tragicmc.client.model.ModelJarra;
import tragicneko.tragicmc.client.model.ModelKitsune;
import tragicneko.tragicmc.client.model.ModelKragul;
import tragicneko.tragicmc.client.model.ModelMegaCryse;
import tragicneko.tragicmc.client.model.ModelPolaris;
import tragicneko.tragicmc.client.model.ModelStinKing;
import tragicneko.tragicmc.client.model.ModelStinQueen;
import tragicneko.tragicmc.client.model.ModelTimeController;
import tragicneko.tragicmc.client.model.ModelTox;
import tragicneko.tragicmc.client.model.ModelVoxStellarum;
import tragicneko.tragicmc.client.model.ModelYeti;
import tragicneko.tragicmc.entity.EntityStatue;

public class RenderStatue extends Render {
	
	private ModelBase model = new ModelBlock();

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.doRender((EntityStatue)par1Entity, par2, par4, par6, par8, par9);
	}

	public void doRender(EntityStatue statue, double par2, double par3, double par4, float par5, float par6)
	{
		int id = statue.getIDForRender();

		switch(id)
		{
		case 0:
			model = new ModelApis();
			break;
		case 1:
			model = new ModelKitsune();
			break;
		case 2:
			model = new ModelDeathReaper();
			break;
		case 3:
			model = new ModelTimeController();
			break;
		case 4:
			model = new ModelYeti();
			break;
		case 5:
			model = new ModelPolaris();
			break;
		case 6:
			model = new ModelJarra();
			break;
		case 7:
			model = new ModelKragul();
			break;
		case 8:
			model = new ModelTox();
			break;
		case 9:
			model = new ModelMegaCryse();
			break;
		case 10:
			model = new ModelStinKing();
			break;
		case 11:
			model = new ModelStinQueen();
			break;
		case 12:
			model = new ModelGreaterStin();
			break;
		case 13:
			model = new ModelVoxStellarum();
			break;
		}

		boolean flag = model instanceof ModelApis || model instanceof ModelTimeController || model instanceof ModelGreaterStin || model instanceof ModelStinKing || model instanceof ModelStinQueen;
		boolean flag2 = model instanceof ModelKragul;
		
		float f = flag ? 0.505F : (flag2 ? 0.865F : 0.625F);		
		float f1 = flag ? 0.01725F : (flag2 ? 0.0525F : 0.025F);
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glTranslatef((float)par2, (float)par3 + f, (float)par4);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		float f0 = statue.getRotationAngleForRender();
		GL11.glRotatef(f0, 0.0F, 1.0F, 0.0F);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		this.bindTexture(this.getEntityTexture(statue));
		model.render(statue, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f1);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityStatue)entity);
	}

	private ResourceLocation getEntityTexture(EntityStatue entity)
	{
		switch (entity.getIDForTexture())
		{
		case 0:
			String s = "tragicmc:textures/mobs/";
			switch (entity.getIDForRender())
			{
			case 0:
				return new ResourceLocation(s + "ApisCombat2_lowRes.png");
			case 1:
				return new ResourceLocation(s + "Kitsune_lowRes.png");
			case 2:
				return new ResourceLocation(s + "DeathReaper_lowRes.png");
			case 3:
				return new ResourceLocation(s + "TimeController_lowRes.png");
			case 4:
				return new ResourceLocation(s + "Yeti_lowRes.png");
			case 5:
				return new ResourceLocation(s + "Polaris_lowRes.png");
			case 6:
				return new ResourceLocation(s + "Jarra_lowRes.png");
			case 7:
				return new ResourceLocation(s + "Kragul_lowRes.png");
			case 8:
				return new ResourceLocation(s + "Magmox_lowRes.png");
			case 9:
				return new ResourceLocation(s + "Cryse_lowRes.png");
			case 10:
				return new ResourceLocation(s + "StinKing_lowRes.png");
			case 11:
				return new ResourceLocation(s + "StinQueen_lowRes.png");
			case 12:
				return new ResourceLocation(s + "GreaterStin_lowRes.png");
			case 13:
				return new ResourceLocation(s + "StarVox_lowRes.png");
			}
		case 1:
			return new ResourceLocation("textures/blocks/iron_block.png");
		case 2:
			return new ResourceLocation("textures/blocks/gold_block.png");
		case 3:
			return new ResourceLocation("textures/blocks/diamond_block.png");
		case 4:
			return new ResourceLocation("trextures/blocks/stone.png");
		case 5:
			return new ResourceLocation("textures/blocks/log_oak.png");
		case 6:
			return new ResourceLocation("textures/blocks/emerald_block.png");
		case 7:
			return new ResourceLocation("tragicmc:textures/blocks/MercuryBlock_lowRes.png");
		case 8:
			return new ResourceLocation("tragicmc:textures/blocks/TungstenBlock_lowRes.png");
		case 9:
			return new ResourceLocation("tragicmc:textures/blocks/RubyBlock_lowRes.png");
		case 10:
			return new ResourceLocation("tragicmc:textures/blocks/SapphireBlock_lowRes.png");
		case 11:
			return new ResourceLocation("textures/blocks/redstone_block.png");
		case 12:
			return new ResourceLocation("textures/blocks/coal_block.png");
		case 13:
			return new ResourceLocation("textures/blocks/lapis_block.png");
		case 14:
			return new ResourceLocation("textures/blocks/obsidian.png");
		case 15:
			return new ResourceLocation("tragicmc:textures/blocks/DisappearingBlock_lowRes.png");
		}
		return null;
	}

}
