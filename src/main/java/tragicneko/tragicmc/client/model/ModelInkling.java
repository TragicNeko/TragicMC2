package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInkling extends ModelBase
{
	ModelRenderer Body;
	ModelRenderer RightArm;
	ModelRenderer LeftArm;
	ModelRenderer RightLeg;
	ModelRenderer LeftLeg;

	public ModelInkling()
	{
		textureWidth = 64;
		textureHeight = 32;

		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(-3F, 0F, -1F, 6, 10, 2);
		Body.setRotationPoint(0F, 2F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		RightArm = new ModelRenderer(this, 16, 16);
		RightArm.addBox(-2F, 0F, -1F, 2, 10, 2);
		RightArm.setRotationPoint(-3F, 2F, 0F);
		RightArm.setTextureSize(64, 32);
		RightArm.mirror = true;
		setRotation(RightArm, 0F, 0F, 0.3346075F);
		LeftArm = new ModelRenderer(this, 16, 16);
		LeftArm.addBox(0F, 0F, -2F, 2, 10, 2);
		LeftArm.setRotationPoint(3F, 2F, 0F);
		LeftArm.setTextureSize(64, 32);
		LeftArm.mirror = true;
		setRotation(LeftArm, 0F, 0F, -0.3346075F);
		RightLeg = new ModelRenderer(this, 0, 16);
		RightLeg.addBox(-1F, 0F, -1F, 2, 12, 2);
		RightLeg.setRotationPoint(-2F, 12F, 0F);
		RightLeg.setTextureSize(64, 32);
		RightLeg.mirror = true;
		setRotation(RightLeg, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 0, 16);
		LeftLeg.addBox(-1F, 0F, -1F, 2, 12, 2);
		LeftLeg.setRotationPoint(2F, 12F, 0F);
		LeftLeg.setTextureSize(64, 32);
		LeftLeg.mirror = true;
		setRotation(LeftLeg, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		RightArm.render(f5);
		LeftArm.render(f5);
		RightLeg.render(f5);
		LeftLeg.render(f5);
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
