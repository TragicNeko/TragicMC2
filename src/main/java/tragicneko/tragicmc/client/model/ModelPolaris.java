package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPolaris extends ModelBase
{
	ModelRenderer head;
	ModelRenderer Headpiece;
	ModelRenderer Eyepiece;
	ModelRenderer Eye;
	ModelRenderer body;
	ModelRenderer Abdomen;
	ModelRenderer Pelvis;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer RightArm;
	ModelRenderer LeftArm;

	public ModelPolaris()
	{
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 16, 40);
		head.addBox(-3F, -6F, -3F, 6, 5, 6);
		head.setRotationPoint(0F, -2F, 0F);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		Headpiece = new ModelRenderer(this, 28, 8);
		Headpiece.addBox(-1F, -7F, -4F, 2, 7, 6);
		Headpiece.setRotationPoint(0F, -2F, 0F);
		Headpiece.setTextureSize(64, 64);
		Headpiece.mirror = true;
		setRotation(Headpiece, 0F, 0F, 0F);
		Eyepiece = new ModelRenderer(this, 28, 0);
		Eyepiece.addBox(-4F, -5F, -4F, 8, 2, 5);
		Eyepiece.setRotationPoint(0F, -2F, 0F);
		Eyepiece.setTextureSize(64, 64);
		Eyepiece.mirror = true;
		setRotation(Eyepiece, 0F, 0F, 0F);
		Eye = new ModelRenderer(this, 56, 0);
		Eye.addBox(-1F, -5F, -5F, 2, 2, 1);
		Eye.setRotationPoint(0F, -2F, 0F);
		Eye.setTextureSize(64, 64);
		Eye.mirror = true;
		setRotation(Eye, 0F, 0F, 0F);
		body = new ModelRenderer(this, 14, 24);
		body.addBox(-4F, 0F, -2F, 8, 6, 5);
		body.setRotationPoint(0F, -3F, 0F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		Abdomen = new ModelRenderer(this, 0, 10);
		Abdomen.addBox(-2F, 3F, -1F, 4, 3, 3);
		Abdomen.setRotationPoint(0F, 0F, 0F);
		Abdomen.setTextureSize(64, 64);
		Abdomen.mirror = true;
		setRotation(Abdomen, 0F, 0F, 0F);
		Pelvis = new ModelRenderer(this, 0, 0);
		Pelvis.addBox(-3F, 0F, -2F, 6, 3, 5);
		Pelvis.setRotationPoint(0F, 6F, 0F);
		Pelvis.setTextureSize(64, 64);
		Pelvis.mirror = true;
		setRotation(Pelvis, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 46, 16);
		rightarm.addBox(-3F, -2F, -2F, 4, 3, 4);
		rightarm.setRotationPoint(-5F, 0F, 0F);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 46, 16);
		leftarm.addBox(-1F, -2F, -2F, 4, 3, 4);
		leftarm.setRotationPoint(5F, 0F, 0F);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 18);
		rightleg.addBox(-2F, 0F, -2F, 3, 14, 3);
		rightleg.setRotationPoint(-2F, 9F, 1F);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 18);
		leftleg.addBox(-1F, 0F, -2F, 3, 14, 3);
		leftleg.setRotationPoint(2F, 9F, 1F);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
		RightArm = new ModelRenderer(this, 0, 42);
		RightArm.addBox(-1F, 1F, -1F, 2, 8, 2);
		RightArm.setRotationPoint(-5F, 0F, 0F);
		RightArm.setTextureSize(64, 64);
		RightArm.mirror = true;
		setRotation(RightArm, -1.710216F, 0F, 0F);
		LeftArm = new ModelRenderer(this, 0, 42);
		LeftArm.addBox(-1F, 1F, -1F, 2, 8, 2);
		LeftArm.setRotationPoint(5F, 0F, 0F);
		LeftArm.setTextureSize(64, 64);
		LeftArm.mirror = true;
		setRotation(LeftArm, -1.710216F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		Headpiece.render(f5);
		Eyepiece.render(f5);
		Eye.render(f5);
		body.render(f5);
		Abdomen.render(f5);
		Pelvis.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		RightArm.render(f5);
		LeftArm.render(f5);
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
