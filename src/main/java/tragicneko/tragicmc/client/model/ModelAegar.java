package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import tragicneko.tragicmc.entity.boss.EntityAegar;

public class ModelAegar extends ModelBase
{
	private ModelRenderer head;
	private ModelRenderer body;

	private ModelRenderer bar;
	private ModelRenderer bar2;
	private ModelRenderer bar3;
	private ModelRenderer bar4;
	private ModelRenderer bar5;
	private ModelRenderer bar6;
	private ModelRenderer bar7;

	private ModelRenderer rightShoulder;
	private ModelRenderer rightJoint;
	private ModelRenderer leftShoulder;
	private ModelRenderer leftJoint;

	public ModelAegar()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 8, 0);
		head.addBox(-2F, -4F, -2F, 4, 4, 4);
		head.setRotationPoint(0F, -6F, 0F);

		ModelRenderer headTop = new ModelRenderer(this, 26, 0);
		headTop.addBox(-1F, -5F, -1F, 2, 1, 2);
		head.addChild(headTop);

		body = new ModelRenderer(this, 0, 14);
		body.addBox(-3F, -3F, -3F, 6, 6, 6);
		body.setRotationPoint(0F, -2F, 0F);

		ModelRenderer torso = new ModelRenderer(this, 24, 8);
		torso.addBox(-1.5F, 4F, -1.5F, 3, 4, 3);
		body.addChild(torso);

		ModelRenderer torsoLower = new ModelRenderer(this, 26, 16);
		torsoLower.addBox(-1F, 8F, -1F, 2, 3, 2);
		body.addChild(torsoLower);

		bar = new ModelRenderer(this, 0, 0);
		bar.addBox(0F, -3F, -7F, 1, 5, 1);
		bar.setRotationPoint(0F, -2F, 0F);

		bar2 = new ModelRenderer(this, 0, 0);
		bar2.addBox(-4F, -1F, -5F, 1, 5, 1);
		bar2.setRotationPoint(0F, -2F, 0F);

		bar3 = new ModelRenderer(this, 0, 0);
		bar3.addBox(3F, -3F, -5F, 1, 4, 1);
		bar3.setRotationPoint(0F, -2F, 0F);

		bar4 = new ModelRenderer(this, 0, 0);
		bar4.addBox(-6F, -2F, 0F, 1, 4, 1);
		bar4.setRotationPoint(0F, -3F, 0F);

		bar5 = new ModelRenderer(this, 0, 0);
		bar5.addBox(5F, -2F, 0F, 1, 5, 1);
		bar5.setRotationPoint(0F, -2F, 0F);

		bar6 = new ModelRenderer(this, 0, 0);
		bar6.addBox(-2F, -2F, 5F, 1, 4, 1);
		bar6.setRotationPoint(0F, -2F, 0F);

		bar7 = new ModelRenderer(this, 0, 0);
		bar7.addBox(4F, -4F, 6F, 1, 4, 1);
		bar7.setRotationPoint(0F, -2F, 0F);

		rightShoulder = new ModelRenderer(this, 36, 0);
		rightShoulder.addBox(-3F, -2F, -2F, 4, 6, 4);
		rightShoulder.setRotationPoint(-8F, -4F, 0F);

		rightJoint = new ModelRenderer(this, 54, 0);
		rightJoint.addBox(-2.5F, 5F, -1F, 2, 2, 2);
		rightShoulder.addChild(rightJoint);

		ModelRenderer rightForearm = new ModelRenderer(this, 24, 22);
		rightForearm.addBox(-4F, 12F, -2F, 5, 4, 4);
		rightJoint.addChild(rightForearm);
		
		ModelRenderer rightPalm = new ModelRenderer(this, 42, 12);
		rightPalm.addBox(-3F, 8F, -1F, 3, 4, 2);
		rightJoint.addChild(rightPalm);

		ModelRenderer claw = new ModelRenderer(this, 54, 8);
		claw.addBox(1F, 15F, -1F, 1, 5, 2);
		rightJoint.addChild(claw);
		
		ModelRenderer claw2 = new ModelRenderer(this, 54, 8);
		claw2.addBox(-2F, 14F, -3F, 1, 7, 2);
		rightJoint.addChild(claw2);
		
		ModelRenderer claw3 = new ModelRenderer(this, 54, 8);
		claw3.addBox(-5F, 15F, -1F, 1, 5, 2);
		rightJoint.addChild(claw3);

		leftShoulder = new ModelRenderer(this, 36, 0);
		leftShoulder.addBox(-2F, -2F, -2F, 4, 6, 4);
		leftShoulder.setRotationPoint(9F, -4F, 0F);
		
		leftJoint = new ModelRenderer(this, 54, 0);
		leftJoint.addBox(-0.5F, 5F, -1F, 2, 2, 2);
		leftShoulder.addChild(leftJoint);
		
		ModelRenderer leftForearm = new ModelRenderer(this, 42, 12);
		leftForearm.addBox(-1F, 8F, -1F, 3, 4, 2);
		leftJoint.addChild(leftForearm);
		
		ModelRenderer leftCannon = new ModelRenderer(this, 42, 20);
		leftCannon.addBox(-2F, 12F, -2F, 5, 6, 4);
		leftJoint.addChild(leftCannon);
		
		ModelRenderer rail = new ModelRenderer(this, 0, 7);
		rail.addBox(-1F, 18F, 0F, 1, 5, 1);
		leftJoint.addChild(rail);
		
		ModelRenderer rail2 = new ModelRenderer(this, 0, 7);
		rail2.addBox(1F, 18F, 0F, 1, 5, 1);
		leftJoint.addChild(rail2);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		head.render(f5);
		body.render(f5);
		rightShoulder.render(f5);
		leftShoulder.render(f5);

		if (!(entity instanceof EntityAegar)) return;

		EntityAegar aegar = (EntityAegar) entity;

		if (aegar.getHypermode())
		{
			bar.render(f5);
			bar2.render(f5);
			bar3.render(f5);
			bar4.render(f5);
			bar5.render(f5);
			bar6.render(f5);
			bar7.render(f5);
		}
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
