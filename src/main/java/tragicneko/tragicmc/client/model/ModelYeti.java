package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelYeti extends ModelBase
{
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer nose;
	ModelRenderer rightfist;
	ModelRenderer leftfist;
	ModelRenderer rightear;
	ModelRenderer leftear;
	ModelRenderer leftupperear;
	ModelRenderer rightupperear;
	ModelRenderer lefteartip;
	ModelRenderer righteartip;
	ModelRenderer lefteyebrow;
	ModelRenderer righteyebrow;
	ModelRenderer rightwhisker;
	ModelRenderer leftwhisker;
	ModelRenderer upperchest;
	ModelRenderer lowerchest;
	ModelRenderer rightshoulder;
	ModelRenderer leftshoulder;
	ModelRenderer lowerbody;
	ModelRenderer rightfoot;
	ModelRenderer leftfoot;

	public ModelYeti()
	{
		textureWidth = 128;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-5F, -8F, -4F, 10, 8, 6);
		head.setRotationPoint(0F, -5F, 0F);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 70, 0);
		body.addBox(-8F, 0F, -2F, 16, 12, 6);
		body.setRotationPoint(0F, -5F, 1F);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.2082002F, 0F, 0F);
		rightarm = new ModelRenderer(this, 48, 10);
		rightarm.addBox(-3F, 0F, -2F, 4, 16, 5);
		rightarm.setRotationPoint(-9F, -5F, 1F);
		rightarm.setTextureSize(128, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 48, 10);
		leftarm.addBox(0F, 0F, -2F, 4, 16, 5);
		leftarm.setRotationPoint(8F, -5F, 1F);
		leftarm.setTextureSize(128, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 9, 4);
		rightleg.setRotationPoint(-4F, 14F, 5F);
		rightleg.setTextureSize(128, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2F, 0F, -2F, 4, 9, 4);
		leftleg.setRotationPoint(4F, 14F, 5F);
		leftleg.setTextureSize(128, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
		nose = new ModelRenderer(this, 32, 0);
		nose.addBox(-2F, -6F, -5F, 4, 2, 1);
		nose.setRotationPoint(0F, -5F, 0F);
		nose.setTextureSize(128, 64);
		nose.mirror = true;
		setRotation(nose, 0F, 0F, 0F);
		rightfist = new ModelRenderer(this, 0, 36);
		rightfist.addBox(-4F, 16F, -3F, 7, 7, 8);
		rightfist.setRotationPoint(-9F, -5F, 1F);
		rightfist.setTextureSize(128, 64);
		rightfist.mirror = true;
		setRotation(rightfist, 0F, 0F, 0F);
		leftfist = new ModelRenderer(this, 32, 36);
		leftfist.addBox(-2F, 16F, -3F, 7, 7, 8);
		leftfist.setRotationPoint(8F, -5F, 1F);
		leftfist.setTextureSize(128, 64);
		leftfist.mirror = true;
		setRotation(leftfist, 0F, 0F, 0F);
		rightear = new ModelRenderer(this, 48, 0);
		rightear.addBox(-6F, -6F, -2F, 1, 3, 3);
		rightear.setRotationPoint(0F, -5F, 0F);
		rightear.setTextureSize(128, 64);
		rightear.mirror = true;
		setRotation(rightear, 0F, 0F, 0F);
		leftear = new ModelRenderer(this, 48, 0);
		leftear.addBox(5F, -6F, -2F, 1, 3, 3);
		leftear.setRotationPoint(0F, -5F, 0F);
		leftear.setTextureSize(128, 64);
		leftear.mirror = true;
		setRotation(leftear, 0F, 0F, 0F);
		leftupperear = new ModelRenderer(this, 32, 3);
		leftupperear.addBox(0F, 0F, 0F, 1, 2, 2);
		leftupperear.setRotationPoint(-6F, -12F, 0F);
		leftupperear.setTextureSize(128, 64);
		leftupperear.mirror = true;
		setRotation(leftupperear, 0F, 0F, 0F);
		rightupperear = new ModelRenderer(this, 32, 3);
		rightupperear.addBox(0F, 0F, 0F, 1, 2, 2);
		rightupperear.setRotationPoint(5F, -12F, 0F);
		rightupperear.setTextureSize(128, 64);
		rightupperear.mirror = true;
		setRotation(rightupperear, 0F, 0F, 0F);
		lefteartip = new ModelRenderer(this, 0, 0);
		lefteartip.addBox(0F, 0F, 0F, 1, 1, 1);
		lefteartip.setRotationPoint(5F, -13F, 2F);
		lefteartip.setTextureSize(128, 64);
		lefteartip.mirror = true;
		setRotation(lefteartip, 0F, 0F, 0F);
		righteartip = new ModelRenderer(this, 0, 0);
		righteartip.addBox(0F, 0F, 0F, 1, 1, 1);
		righteartip.setRotationPoint(-6F, -13F, 2F);
		righteartip.setTextureSize(128, 64);
		righteartip.mirror = true;
		setRotation(righteartip, 0F, 0F, 0F);
		lefteyebrow = new ModelRenderer(this, 32, 12);
		lefteyebrow.addBox(0F, 0F, 0F, 4, 1, 1);
		lefteyebrow.setRotationPoint(1F, -12F, -5F);
		lefteyebrow.setTextureSize(128, 64);
		lefteyebrow.mirror = true;
		setRotation(lefteyebrow, 0F, 0.0594858F, -0.4461433F);
		righteyebrow = new ModelRenderer(this, 32, 12);
		righteyebrow.addBox(0F, 0F, 0F, 4, 1, 1);
		righteyebrow.setRotationPoint(-2F, -11F, -5F);
		righteyebrow.setTextureSize(128, 64);
		righteyebrow.mirror = true;
		setRotation(righteyebrow, 0F, 0F, -2.706603F);
		rightwhisker = new ModelRenderer(this, 32, 7);
		rightwhisker.addBox(0F, 0F, 0F, 1, 2, 1);
		rightwhisker.setRotationPoint(-3F, -5F, -4F);
		rightwhisker.setTextureSize(128, 64);
		rightwhisker.mirror = true;
		setRotation(rightwhisker, 0F, 0F, 0F);
		leftwhisker = new ModelRenderer(this, 32, 7);
		leftwhisker.addBox(0F, 0F, 0F, 1, 2, 1);
		leftwhisker.setRotationPoint(2F, -5F, -4F);
		leftwhisker.setTextureSize(128, 64);
		leftwhisker.mirror = true;
		setRotation(leftwhisker, 0F, 0F, 0F);
		upperchest = new ModelRenderer(this, 76, 26);
		upperchest.addBox(-7F, 0F, 0F, 14, 6, 1);
		upperchest.setRotationPoint(0F, -2F, -2F);
		upperchest.setTextureSize(128, 64);
		upperchest.mirror = true;
		setRotation(upperchest, 0.2082002F, 0F, 0F);
		lowerchest = new ModelRenderer(this, 76, 34);
		lowerchest.addBox(-5F, 0F, -0.7733333F, 10, 6, 1);
		lowerchest.setRotationPoint(0F, 3F, 0F);
		lowerchest.setTextureSize(128, 64);
		lowerchest.mirror = true;
		setRotation(lowerchest, 0.2082002F, 0F, 0F);
		rightshoulder = new ModelRenderer(this, 66, 46);
		rightshoulder.addBox(-1F, -1F, -3F, 8, 7, 7);
		rightshoulder.setRotationPoint(9F, -5F, 1F);
		rightshoulder.setTextureSize(128, 64);
		rightshoulder.mirror = true;
		setRotation(rightshoulder, 0F, 0F, 0F);
		leftshoulder = new ModelRenderer(this, 66, 46);
		leftshoulder.addBox(-6F, -1F, -3F, 8, 7, 7);
		leftshoulder.setRotationPoint(-9F, -5F, 1F);
		leftshoulder.setTextureSize(128, 64);
		leftshoulder.mirror = true;
		setRotation(leftshoulder, 0F, 0F, 0F);
		lowerbody = new ModelRenderer(this, 73, 13);
		lowerbody.addBox(-6F, 9F, 0F, 12, 8, 5);
		lowerbody.setRotationPoint(0F, -2F, 0F);
		lowerbody.setTextureSize(128, 64);
		lowerbody.mirror = true;
		setRotation(lowerbody, 0.2082002F, 0F, 0F);
		rightfoot = new ModelRenderer(this, 17, 21);
		rightfoot.addBox(-3F, 8F, -6F, 5, 2, 8);
		rightfoot.setRotationPoint(-4F, 14F, 5F);
		rightfoot.setTextureSize(128, 64);
		rightfoot.mirror = true;
		setRotation(rightfoot, 0F, 0F, 0F);
		leftfoot = new ModelRenderer(this, 17, 21);
		leftfoot.addBox(-2F, 8F, -6F, 5, 2, 8);
		leftfoot.setRotationPoint(4F, 14F, 5F);
		leftfoot.setTextureSize(128, 64);
		leftfoot.mirror = true;
		setRotation(leftfoot, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		nose.render(f5);
		rightfist.render(f5);
		leftfist.render(f5);
		rightear.render(f5);
		leftear.render(f5);
		leftupperear.render(f5);
		rightupperear.render(f5);
		lefteartip.render(f5);
		righteartip.render(f5);
		lefteyebrow.render(f5);
		righteyebrow.render(f5);
		rightwhisker.render(f5);
		leftwhisker.render(f5);
		upperchest.render(f5);
		lowerchest.render(f5);
		rightshoulder.render(f5);
		leftshoulder.render(f5);
		lowerbody.render(f5);
		rightfoot.render(f5);
		leftfoot.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
