package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRagr extends ModelBase
{
	ModelRenderer Head;
	ModelRenderer Nose;
	ModelRenderer Forehead;
	ModelRenderer Body;
	ModelRenderer Chest;
	ModelRenderer RightLeg;
	ModelRenderer LeftLeg;
	ModelRenderer RightShoulder;
	ModelRenderer RightUpperArm;
	ModelRenderer RightForearm;
	ModelRenderer RightFist;
	ModelRenderer LeftShoulder;
	ModelRenderer LeftUpperArm;
	ModelRenderer LeftForearm;
	ModelRenderer LeftFist;

	public ModelRagr()
	{
		textureWidth = 128;
		textureHeight = 64;

		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-6F, -9F, -4F, 12, 14, 8);
		Head.setRotationPoint(0F, -12F, 0F);
		Head.setTextureSize(128, 64);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		Nose = new ModelRenderer(this, 0, 32);
		Nose.addBox(-2F, -2F, -7F, 4, 8, 3);
		Nose.setRotationPoint(0F, -12F, 0F);
		Nose.setTextureSize(128, 64);
		Nose.mirror = true;
		setRotation(Nose, 0F, 0F, 0F);
		Forehead = new ModelRenderer(this, 0, 24);
		Forehead.addBox(-6F, -9F, -6F, 12, 4, 2);
		Forehead.setRotationPoint(0F, -12F, 0F);
		Forehead.setTextureSize(128, 64);
		Forehead.mirror = true;
		setRotation(Forehead, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 60, 0);
		Body.addBox(-4F, -7F, -1F, 8, 14, 4);
		Body.setRotationPoint(0F, 0F, 0F);
		Body.setTextureSize(128, 64);
		Body.mirror = true;
		setRotation(Body, 0.1115358F, 0F, 0F);
		Chest = new ModelRenderer(this, 86, 0);
		Chest.addBox(-6F, -7F, -2F, 12, 5, 5);
		Chest.setRotationPoint(0F, 0F, 0F);
		Chest.setTextureSize(128, 64);
		setRotation(Chest, 0F, 0F, 0F);
		RightLeg = new ModelRenderer(this, 16, 32);
		RightLeg.addBox(-2F, 0F, -1F, 3, 6, 3);
		RightLeg.setRotationPoint(-3F, 7F, 0F);
		RightLeg.setTextureSize(128, 64);
		setRotation(RightLeg, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 16, 32);
		LeftLeg.addBox(-1F, 0F, -1F, 3, 6, 3);
		LeftLeg.setRotationPoint(3F, 7F, 0F);
		LeftLeg.setTextureSize(128, 64);
		LeftLeg.mirror = true;
		setRotation(LeftLeg, 0F, 0F, 0F);
		RightShoulder = new ModelRenderer(this, 32, 22);
		RightShoulder.addBox(-7F, -3F, -3F, 8, 8, 8);
		RightShoulder.setRotationPoint(-7F, -7F, 0F);
		RightShoulder.setTextureSize(128, 64);
		RightShoulder.mirror = true;
		setRotation(RightShoulder, 0F, 0F, 0F);
		RightUpperArm = new ModelRenderer(this, 42, 0);
		RightUpperArm.addBox(-5F, 5F, -1F, 4, 8, 4);
		RightUpperArm.setRotationPoint(-7F, -7F, 0F);
		RightUpperArm.setTextureSize(128, 64);
		RightUpperArm.mirror = true;
		setRotation(RightUpperArm, 0F, 0F, 0F);
		RightForearm = new ModelRenderer(this, 34, 42);
		RightForearm.addBox(-6F, 13F, -2F, 6, 12, 6);
		RightForearm.setRotationPoint(-7F, -7F, 0F);
		RightForearm.setTextureSize(128, 64);
		RightForearm.mirror = true;
		setRotation(RightForearm, 0F, 0F, 0F);
		RightFist = new ModelRenderer(this, 0, 46);
		RightFist.addBox(-7F, 25F, -3F, 8, 6, 8);
		RightFist.setRotationPoint(-7F, -7F, 0F);
		RightFist.setTextureSize(128, 64);
		RightFist.mirror = true;
		setRotation(RightFist, 0F, 0F, 0F);
		LeftShoulder = new ModelRenderer(this, 32, 22);
		LeftShoulder.addBox(-1F, -3F, -3F, 8, 8, 8);
		LeftShoulder.setRotationPoint(7F, -7F, 0F);
		LeftShoulder.setTextureSize(128, 64);
		LeftShoulder.mirror = true;
		setRotation(LeftShoulder, 0F, 0F, 0F);
		LeftUpperArm = new ModelRenderer(this, 42, 0);
		LeftUpperArm.addBox(1F, 5F, -1F, 4, 8, 4);
		LeftUpperArm.setRotationPoint(7F, -7F, 0F);
		LeftUpperArm.setTextureSize(128, 64);
		LeftUpperArm.mirror = true;
		setRotation(LeftUpperArm, 0F, 0F, 0F);
		LeftForearm = new ModelRenderer(this, 34, 42);
		LeftForearm.addBox(0F, 13F, -2F, 6, 12, 6);
		LeftForearm.setRotationPoint(7F, -7F, 0F);
		LeftForearm.setTextureSize(128, 64);
		LeftForearm.mirror = true;
		setRotation(LeftForearm, 0F, 0F, 0F);
		LeftFist = new ModelRenderer(this, 0, 46);
		LeftFist.addBox(-1F, 25F, -3F, 8, 6, 8);
		LeftFist.setRotationPoint(7F, -7F, 0F);
		LeftFist.setTextureSize(128, 64);
		LeftFist.mirror = true;
		setRotation(LeftFist, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Head.render(f5);
		Nose.render(f5);
		Forehead.render(f5);
		Body.render(f5);
		Chest.render(f5);
		RightLeg.render(f5);
		LeftLeg.render(f5);
		RightShoulder.render(f5);
		RightUpperArm.render(f5);
		RightForearm.render(f5);
		RightFist.render(f5);
		LeftShoulder.render(f5);
		LeftUpperArm.render(f5);
		LeftForearm.render(f5);
		LeftFist.render(f5);
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
