package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTragicNeko extends ModelBase
{
	ModelRenderer Head;
	ModelRenderer LeftEar;
	ModelRenderer RightEar;
	ModelRenderer Body;
	ModelRenderer Tail;
	ModelRenderer TailTip;
	ModelRenderer RightArm;
	ModelRenderer LeftArm;
	ModelRenderer RightLeg;
	ModelRenderer LeftLeg;
	ModelRenderer RocketLauncherShaft;
	ModelRenderer RocketLauncherBody;

	public ModelTragicNeko()
	{
		textureWidth = 64;
		textureHeight = 64;

		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-3F, -6F, -4F, 6, 6, 6);
		Head.setRotationPoint(0F, 0F, 0F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		LeftEar = new ModelRenderer(this, 9, 38);
		LeftEar.addBox(0F, -5F, -4F, 3, 3, 1);
		LeftEar.setRotationPoint(0F, -3F, 0F);
		LeftEar.setTextureSize(64, 32);
		LeftEar.mirror = true;
		setRotation(LeftEar, -0.3346075F, 0F, 0.4461433F);
		RightEar = new ModelRenderer(this, 0, 38);
		RightEar.addBox(-3F, -5F, -4F, 3, 3, 1);
		RightEar.setRotationPoint(0F, -3F, 0F);
		RightEar.setTextureSize(64, 32);
		RightEar.mirror = true;
		setRotation(RightEar, -0.3346075F, 0F, -0.4461433F);
		Body = new ModelRenderer(this, 16, 16);
		Body.addBox(-3F, 0F, -2F, 6, 12, 3);
		Body.setRotationPoint(0F, 0F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Tail = new ModelRenderer(this, 40, 0);
		Tail.addBox(0F, -8F, 0F, 2, 8, 2);
		Tail.setRotationPoint(0F, 12F, 1F);
		Tail.setTextureSize(64, 32);
		Tail.mirror = true;
		setRotation(Tail, -0.7063936F, 0F, 0F);
		TailTip = new ModelRenderer(this, 40, 0);
		TailTip.addBox(0F, -15F, 2.5F, 2, 8, 2);
		TailTip.setRotationPoint(0F, 12F, 1F);
		TailTip.setTextureSize(64, 32);
		TailTip.mirror = true;
		setRotation(TailTip, -0.3717861F, 0F, 0F);
		RightArm = new ModelRenderer(this, 40, 16);
		RightArm.addBox(-2F, -2F, -2F, 2, 10, 2);
		RightArm.setRotationPoint(-3F, 2F, 0F);
		RightArm.setTextureSize(64, 32);
		RightArm.mirror = true;
		setRotation(RightArm, -0.2974289F, 0F, 0.4461433F);
		LeftArm = new ModelRenderer(this, 40, 16);
		LeftArm.addBox(-1F, -1F, -2F, 2, 10, 2);
		LeftArm.setRotationPoint(3F, 2F, 0F);
		LeftArm.setTextureSize(64, 32);
		LeftArm.mirror = true;
		setRotation(LeftArm, -0.4833219F, 0F, 0.4461433F);
		RightLeg = new ModelRenderer(this, 0, 16);
		RightLeg.addBox(-1.5F, 0F, -2F, 2, 12, 2);
		RightLeg.setRotationPoint(-1F, 12F, 0F);
		RightLeg.setTextureSize(64, 32);
		RightLeg.mirror = true;
		setRotation(RightLeg, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 0, 16);
		LeftLeg.addBox(-1.5F, 0F, -2F, 2, 12, 2);
		LeftLeg.setRotationPoint(2F, 12F, 0F);
		LeftLeg.setTextureSize(64, 32);
		LeftLeg.mirror = true;
		setRotation(LeftLeg, 0F, 0F, 0F);
		RocketLauncherShaft = new ModelRenderer(this, 24, 30);
		RocketLauncherShaft.addBox(-2F, 0F, -9F, 5, 4, 15);
		RocketLauncherShaft.setRotationPoint(-5F, 9F, -4F);
		RocketLauncherShaft.setTextureSize(64, 64);
		RocketLauncherShaft.mirror = true;
		setRotation(RocketLauncherShaft, 0F, 0F, 0F);
		RocketLauncherBody = new ModelRenderer(this, 0, 44);
		RocketLauncherBody.addBox(-3F, -1F, -2F, 7, 6, 7);
		RocketLauncherBody.setRotationPoint(-5F, 9F, -4F);
		RocketLauncherBody.setTextureSize(64, 64);
		RocketLauncherBody.mirror = true;
		setRotation(RocketLauncherBody, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Head.render(f5);
		LeftEar.render(f5);
		RightEar.render(f5);
		Body.render(f5);
		Tail.render(f5);
		TailTip.render(f5);
		RightArm.render(f5);
		LeftArm.render(f5);
		RightLeg.render(f5);
		LeftLeg.render(f5);
		RocketLauncherShaft.render(f5);
		RocketLauncherBody.render(f5);
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
