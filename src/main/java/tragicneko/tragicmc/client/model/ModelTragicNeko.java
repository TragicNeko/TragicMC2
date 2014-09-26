package tragicneko.tragicmc.client.model;

import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelTragicNeko extends ModelBase
{
	private ModelRenderer head;
	private ModelRenderer leftEar;
	private ModelRenderer rightEar;
	private ModelRenderer body;
	private ModelRenderer tail;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer rocketLauncher;

	public ModelTragicNeko()
	{
		textureWidth = 64;
		textureHeight = 64;

		//Head and Ears
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, -6F, -4F, 6, 6, 6);
		head.setRotationPoint(0F, 0F, 0F);
		leftEar = new ModelRenderer(this, 9, 38);
		leftEar.addBox(-1F, -7F, -4F, 3, 3, 1);
		leftEar.rotateAngleX = -0.3346075F;
		leftEar.rotateAngleZ = 0.4461433F;
		head.addChild(leftEar);
		rightEar = new ModelRenderer(this, 0, 38);
		rightEar.addBox(-2.5F, -7.5F, -4F, 3, 3, 1);
		rightEar.rotateAngleX = -0.3346075F;
		rightEar.rotateAngleZ = -0.4461433F;
		head.addChild(rightEar);

		//Body and Tail
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-3F, 0F, -2F, 6, 12, 3);
		body.setRotationPoint(0F, 0F, 0F);

		tail = new ModelRenderer(this, 40, 0);
		tail.addBox(0F, -3F, 5F, 2, 8, 2);
		tail.setRotationPoint(0F, 0F, 3F);
		tail.rotateAngleX = -0.7063936F;
		ModelRenderer tailTip = new ModelRenderer(this, 40, 0);
		tailTip.addBox(0F, -8F, 5.5F, 2, 8, 2);
		tailTip.rotateAngleX = 0.3717861F;
		tail.addChild(tailTip);

		//Right Arm
		rightArm = new ModelRenderer(this, 40, 16);
		rightArm.addBox(-2F, -2F, -2F, 2, 10, 2);
		rightArm.setRotationPoint(-3F, 2F, 0F);
		rightArm.rotateAngleX = -0.2974289F;
		rightArm.rotateAngleZ = 0.4461433F;
		rocketLauncher = new ModelRenderer(this, 24, 30);
		rocketLauncher.addBox(-2F, 6F, -7F, 5, 4, 15);
		rightArm.addChild(rocketLauncher);
		ModelRenderer rocketLauncherBody = new ModelRenderer(this, 0, 44);
		rocketLauncherBody.addBox(-3F, 5F, -2F, 7, 6, 7);
		rocketLauncher.addChild(rocketLauncherBody);

		//Left Arm
		leftArm = new ModelRenderer(this, 40, 16);
		leftArm.addBox(-1F, -1F, -2F, 2, 10, 2);
		leftArm.setRotationPoint(3F, 2F, 0F);
		leftArm.rotateAngleX = -0.4833219F;
		leftArm.rotateAngleZ = 0.4461433F;

		//Right Leg
		rightLeg = new ModelRenderer(this, 0, 16);
		rightLeg.addBox(-1.5F, 0F, -2F, 2, 12, 2);
		rightLeg.setRotationPoint(-1F, 12F, 0F);

		//Left Leg
		leftLeg = new ModelRenderer(this, 0, 16);
		leftLeg.addBox(-1.5F, 0F, -2F, 2, 12, 2);
		leftLeg.setRotationPoint(2F, 12F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		tail.render(f5);
		rightArm.render(f5);
		leftArm.render(f5);
		rightLeg.render(f5);
		leftLeg.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		this.head.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.leftLeg.rotateAngleX = -1.85F * this.simplifyAngle(entity.ticksExisted, 15.0F) * f1;
		this.rightLeg.rotateAngleX = 1.85F * this.simplifyAngle(entity.ticksExisted, 15.0F) * f1;
	}

	public void setLivingAnimations(EntityLivingBase entity, float par2, float par3, float par4)
	{
		EntityTragicNeko neko = (EntityTragicNeko) entity;

		if (neko.deathTime > 0)
		{
			this.rocketLauncher.offsetY = 0.635F;
		}
		else
		{
			this.rocketLauncher.offsetY = 0.0F;
			
			this.tail.offsetY = 0.215F;
			this.tail.offsetZ = -0.205F;
			this.tail.offsetX = -0.0275F + 0.019875F * this.simplifyAngle(entity.ticksExisted, 30.0F);

			this.tail.rotateAngleY = 0.275F * this.simplifyAngle(entity.ticksExisted, 15.0F);
			this.tail.rotateAngleZ =  0.01875F * this.simplifyAngle(entity.ticksExisted, 15.0F);
			this.tail.rotateAngleX = -0.7063936F;

			if (neko.getFlickTime() > 0)
			{
				this.rightEar.rotateAngleZ = -0.115F + 0.01875F * this.simplifyAngle(entity.ticksExisted, 5.0F);
				this.leftEar.rotateAngleZ = 0.115F + -0.01875F * this.simplifyAngle(entity.ticksExisted, 5.0F);
				this.rightEar.offsetY = 0.0175F;
				this.leftEar.offsetX = 0.075F;
				this.rightEar.offsetX = -0.075F;
			}
			else
			{
				this.rightEar.rotateAngleZ = -0.4461433F;
				this.leftEar.rotateAngleZ = 0.4461433F;
				this.rightEar.offsetX = 0.0F;
				this.rightEar.offsetY = 0.0F;
				this.leftEar.offsetX = 0.0F;

				if (neko.getThrowingTicks() > 0)
				{
					this.leftArm.rotateAngleX = -0.45F + -1.65F * this.simplifyAngle(neko.getThrowingTicks(), 30.0F);
					this.leftArm.rotateAngleZ = -0.45F + 0.35F * this.simplifyAngle(neko.getThrowingTicks(), 30.0F);
				}
				else
				{
					leftArm.rotateAngleX = -0.4833219F;
					leftArm.rotateAngleZ = 0.4461433F;

					if (neko.getAttackTime() > 0)
					{
						this.rightArm.rotateAngleX = 0.45F + -1.65F * this.simplifyAngle(neko.getThrowingTicks(), 30.0F);
						this.rightArm.rotateAngleZ = 0.45F + 0.35F * this.simplifyAngle(neko.getThrowingTicks(), 30.0F);
					}
					else
					{
						rightArm.rotateAngleX = -0.4833219F;
						rightArm.rotateAngleZ = 0.4461433F;
					}
				}
			}
		}
	}

	private float simplifyAngle(float par1, float par2)
	{
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}
}
