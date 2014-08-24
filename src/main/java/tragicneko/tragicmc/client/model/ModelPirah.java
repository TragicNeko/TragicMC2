package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPirah extends ModelBase
{
	//fields
	ModelRenderer Body;
	ModelRenderer Snout;
	ModelRenderer TopFin;
	ModelRenderer SnoutTip;
	ModelRenderer TailBody;
	ModelRenderer TailFin;

	public ModelPirah()
	{
		textureWidth = 64;
		textureHeight = 32;

		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(-1F, -1F, -2F, 2, 4, 4);
		Body.setRotationPoint(0F, 14F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Snout = new ModelRenderer(this, 0, 8);
		Snout.addBox(-0.5F, -1F, -6F, 1, 1, 4);
		Snout.setRotationPoint(0F, 15F, 0F);
		Snout.setTextureSize(64, 32);
		Snout.mirror = true;
		setRotation(Snout, 0F, 0F, 0F);
		TopFin = new ModelRenderer(this, 14, 0);
		TopFin.addBox(-0.5F, -3F, 0F, 1, 1, 5);
		TopFin.setRotationPoint(0F, 15F, 0F);
		TopFin.setTextureSize(64, 32);
		TopFin.mirror = true;
		setRotation(TopFin, 0F, 0F, 0F);
		SnoutTip = new ModelRenderer(this, 14, 0);
		SnoutTip.addBox(-0.5F, 0F, -5F, 1, 1, 1);
		SnoutTip.setRotationPoint(0F, 15F, 0F);
		SnoutTip.setTextureSize(64, 32);
		SnoutTip.mirror = true;
		setRotation(SnoutTip, 0F, 0F, 0F);
		TailBody = new ModelRenderer(this, 14, 8);
		TailBody.addBox(-1F, -1F, 2F, 2, 2, 2);
		TailBody.setRotationPoint(0F, 15F, 0F);
		TailBody.setTextureSize(64, 32);
		TailBody.mirror = true;
		setRotation(TailBody, 0F, 0F, 0F);
		TailFin = new ModelRenderer(this, 24, 0);
		TailFin.addBox(-1F, -1F, 4F, 2, 1, 3);
		TailFin.setRotationPoint(0F, 15F, 0F);
		TailFin.setTextureSize(64, 32);
		TailFin.mirror = true;
		setRotation(TailFin, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		Snout.render(f5);
		TopFin.render(f5);
		SnoutTip.render(f5);
		TailBody.render(f5);
		TailFin.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
