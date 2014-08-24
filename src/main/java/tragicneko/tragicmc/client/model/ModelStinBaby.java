package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelStinBaby extends ModelBase
{
	ModelRenderer Body;
	ModelRenderer BodyThickness;
	ModelRenderer FrontRightLeg;
	ModelRenderer FrontLeftLeg;
	ModelRenderer BackLeftLeg;
	ModelRenderer BackRightLeg;

	public ModelStinBaby()
	{
		textureWidth = 128;
		textureHeight = 128;

		Body = new ModelRenderer(this, 22, 0);
		Body.addBox(-2F, 0F, 0F, 4, 8, 13);
		Body.setRotationPoint(0F, 14F, -2F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		BodyThickness = new ModelRenderer(this, 0, 30);
		BodyThickness.addBox(-4F, 2F, 0F, 8, 6, 13);
		BodyThickness.setRotationPoint(0F, 13F, 0F);
		BodyThickness.setTextureSize(64, 32);
		BodyThickness.mirror = true;
		setRotation(BodyThickness, 0F, 0F, 0F);
		FrontRightLeg = new ModelRenderer(this, 64, 0);
		FrontRightLeg.addBox(-1F, 0F, -1F, 2, 7, 3);
		FrontRightLeg.setRotationPoint(-4F, 17F, 2F);
		FrontRightLeg.setTextureSize(64, 32);
		FrontRightLeg.mirror = true;
		setRotation(FrontRightLeg, -0.2230717F, 0F, 0.0371786F);
		FrontLeftLeg = new ModelRenderer(this, 64, 20);
		FrontLeftLeg.addBox(-1F, 0F, -1F, 2, 7, 3);
		FrontLeftLeg.setRotationPoint(4F, 17F, 2F);
		FrontLeftLeg.setTextureSize(64, 32);
		FrontLeftLeg.mirror = true;
		setRotation(FrontLeftLeg, -0.2230717F, 0F, -0.0371786F);
		BackLeftLeg = new ModelRenderer(this, 64, 20);
		BackLeftLeg.addBox(-1F, 0F, -1F, 2, 7, 3);
		BackLeftLeg.setRotationPoint(4F, 17F, 6F);
		BackLeftLeg.setTextureSize(64, 32);
		BackLeftLeg.mirror = true;
		setRotation(BackLeftLeg, 0.1487144F, 0F, -0.1487144F);
		BackRightLeg = new ModelRenderer(this, 64, 0);
		BackRightLeg.addBox(-1F, 0F, -1F, 2, 7, 3);
		BackRightLeg.setRotationPoint(-4F, 17F, 6F);
		BackRightLeg.setTextureSize(64, 32);
		BackRightLeg.mirror = true;
		setRotation(BackRightLeg, 0.1487144F, 0F, 0.1487144F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		BodyThickness.render(f5);
		FrontRightLeg.render(f5);
		FrontLeftLeg.render(f5);
		BackLeftLeg.render(f5);
		BackRightLeg.render(f5);
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
