package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGragul extends ModelBase
{
	ModelRenderer Body;
	ModelRenderer Top;
	ModelRenderer RightNub;
	ModelRenderer LeftNub;
	ModelRenderer LeftLeg;
	ModelRenderer RightLeg;
	ModelRenderer Eye;
	ModelRenderer Jaw;

	public ModelGragul()
	{
		textureWidth = 64;
		textureHeight = 32;

		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		Body.setRotationPoint(0F, 13F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 16, 0);
		Top.addBox(-1F, -2.5F, -0.5F, 2, 1, 1);
		Top.setRotationPoint(0F, 13F, 0F);
		Top.setTextureSize(64, 32);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		RightNub = new ModelRenderer(this, 0, 8);
		RightNub.addBox(-2F, 0F, -1F, 1, 1, 1);
		RightNub.setRotationPoint(0F, 13F, 0F);
		RightNub.setTextureSize(64, 32);
		RightNub.mirror = true;
		setRotation(RightNub, 0F, 0F, 0F);
		LeftNub = new ModelRenderer(this, 0, 8);
		LeftNub.addBox(1F, 0F, -1F, 1, 1, 1);
		LeftNub.setRotationPoint(0F, 13F, 0F);
		LeftNub.setTextureSize(64, 32);
		LeftNub.mirror = true;
		setRotation(LeftNub, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 8, 8);
		LeftLeg.addBox(0.5F, 1.5F, -1F, 1, 1, 1);
		LeftLeg.setRotationPoint(0F, 13F, 0F);
		LeftLeg.setTextureSize(64, 32);
		LeftLeg.mirror = true;
		setRotation(LeftLeg, 0F, 0F, 0F);
		RightLeg = new ModelRenderer(this, 8, 8);
		RightLeg.addBox(-1.5F, 1.5F, -1F, 1, 1, 1);
		RightLeg.setRotationPoint(0F, 13F, 0F);
		RightLeg.setTextureSize(64, 32);
		RightLeg.mirror = true;
		setRotation(RightLeg, 0F, 0F, 0F);
		Eye = new ModelRenderer(this, 0, 16);
		Eye.addBox(-1F, -1F, -2F, 2, 2, 1);
		Eye.setRotationPoint(0F, 13F, 0F);
		Eye.setTextureSize(64, 32);
		Eye.mirror = true;
		setRotation(Eye, 0F, 0F, 0F);
		Jaw = new ModelRenderer(this, 0, 12);
		Jaw.addBox(-1.5F, 1F, -2.5F, 3, 1, 1);
		Jaw.setRotationPoint(0F, 13F, 0F);
		Jaw.setTextureSize(64, 32);
		Jaw.mirror = true;
		setRotation(Jaw, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		Top.render(f5);
		RightNub.render(f5);
		LeftNub.render(f5);
		LeftLeg.render(f5);
		RightLeg.render(f5);
		Eye.render(f5);
		Jaw.render(f5);
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
