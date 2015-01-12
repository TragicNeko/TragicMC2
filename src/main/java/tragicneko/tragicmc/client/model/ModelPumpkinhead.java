package tragicneko.tragicmc.client.model;

import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelPumpkinhead extends ModelBase
{
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer cape;

	public ModelPumpkinhead()
	{
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-5F, -10F, -5F, 10, 10, 10);
		head.setRotationPoint(0F, 0F, 0F);

		body = new ModelRenderer(this, 18, 22);
		body.addBox(-3F, 0F, -2F, 6, 12, 4);
		body.setRotationPoint(0F, 0F, 0F);
		cape = new ModelRenderer(this, 44, 0);
		cape.addBox(-4F, 0F, 3F, 8, 14, 0);
		cape.rotateAngleX = 0.185931F;
		body.addChild(cape);

		rightArm = new ModelRenderer(this, 40, 16);
		rightArm.addBox(-3F, -1F, -2F, 2, 8, 2);
		rightArm.setRotationPoint(-2F, 2F, 0F);
		rightArm.rotateAngleX = -0.2230717F;

		leftArm = new ModelRenderer(this, 40, 28);
		leftArm.addBox(0F, -1F, -2F, 4, 12, 4);
		leftArm.setRotationPoint(3F, 2F, 0F);
		leftArm.rotateAngleX = -1.301251F;
		leftArm.rotateAngleZ = -0.5576792F;

		rightLeg = new ModelRenderer(this, 0, 22);
		rightLeg.addBox(-2F, 0F, -2F, 2, 12, 2);
		rightLeg.setRotationPoint(-2F, 12F, 0F);

		leftLeg = new ModelRenderer(this, 0, 40);
		leftLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftLeg.setRotationPoint(2F, 12F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		rightArm.render(f5);
		leftArm.render(f5);
		rightLeg.render(f5);
		leftLeg.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		EntityPumpkinhead pump = (EntityPumpkinhead) entity;
		if (pump.getAngerTicks() > 0)
		{
			this.head.rotateAngleY = 0.385F * this.simplifyAngle(f, 2.0F);
		}
		else
		{
			this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
		}

		this.head.rotateAngleX = f5 / (180F / (float)Math.PI);

		this.rightLeg.rotateAngleX = 1.115F * this.simplifyAngle(f, 15.0F) * f1;
		this.rightArm.rotateAngleX = -1.55F * this.simplifyAngle(f, 15.0F) * f1;


	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float par2, float par3, float par4)
	{
		if (Math.abs(entity.motionX) > 0 || Math.abs(entity.motionZ) > 0)
		{
			this.cape.rotateAngleX = 0.485931F + 0.65F * this.simplifyAngle(par2, 5.0F);
		}
		else
		{
			this.cape.rotateAngleX = 0.185931F;
		}
	}

	private float simplifyAngle(float par1, float par2)
	{
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}

}
