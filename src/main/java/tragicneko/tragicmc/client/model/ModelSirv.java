package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import tragicneko.tragicmc.entity.mob.EntitySirv;

public class ModelSirv extends ModelBase
{
	private ModelRenderer head;
	private ModelRenderer chest;
	private ModelRenderer armRight;
	private ModelRenderer armLeft;
	private ModelRenderer legRight;
	private ModelRenderer legLeft;

	public ModelSirv()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 22, 0);
		head.addBox(-1.5F, -3F, -1.5F, 3, 4, 3);
		head.setRotationPoint(0F, -3F, 0F);

		chest = new ModelRenderer(this, 8, 0);
		chest.addBox(-2F, 0F, -1F, 4, 3, 2);
		chest.setRotationPoint(0F, -1F, 0F);
		ModelRenderer stomach = new ModelRenderer(this, 10, 6);
		stomach.addBox(-1F, 3F, 0F, 2, 5, 1);
		chest.addChild(stomach);
		ModelRenderer neck = new ModelRenderer(this, 18, 8);
		neck.addBox(-0.5F, -1F, -0.5F, 1, 1, 1);
		chest.addChild(neck);

		armRight = new ModelRenderer(this, 0, 12);
		armRight.addBox(-1F, 0F, -0.5F, 1, 9, 1);
		armRight.setRotationPoint(-2F, -1F, 0F);
		ModelRenderer armRightFist = new ModelRenderer(this, 6, 14);
		armRightFist.addBox(-1F, 9F, -1F, 1, 8, 2);
		armRight.addChild(armRightFist);

		armLeft = new ModelRenderer(this, 0, 12);
		armLeft.addBox(0F, 0F, -0.5F, 1, 9, 1);
		armLeft.setRotationPoint(2F, -1F, 0F);
		ModelRenderer armLeftFist = new ModelRenderer(this, 6, 14);
		armLeftFist.addBox(0F, 9F, -1F, 1, 8, 2);
		armLeft.addChild(armLeftFist);

		legRight = new ModelRenderer(this, 0, 0);
		legRight.addBox(-0.5F, 0F, -0.5F, 1, 8, 1);
		legRight.setRotationPoint(-1F, 7F, 0F);
		ModelRenderer legRightFoot = new ModelRenderer(this, 0, 0);
		legRightFoot.addBox(-0.5F, 8F, -0.5F, 1, 9, 2);
		legRight.addChild(legRightFoot);

		legLeft = new ModelRenderer(this, 0, 0);
		legLeft.addBox(-0.5F, 0F, -0.5F, 1, 8, 1);
		legLeft.setRotationPoint(1F, 7F, 0F);
		ModelRenderer legLeftFoot = new ModelRenderer(this, 0, 0);
		legLeftFoot.addBox(-0.5F, 8F, -0.5F, 1, 9, 2);
		legLeft.addChild(legLeftFoot);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		chest.render(f5);
		armRight.render(f5);
		armLeft.render(f5);
		legRight.render(f5);
		legLeft.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		head.rotateAngleY = f3 / (180F / (float)Math.PI);
		head.rotateAngleX = f4 / (180F / (float)Math.PI);
		legLeft.rotateAngleX = -0.85F * this.simplifyAngle(f, 10.0F) * f1;
		legRight.rotateAngleX = 0.85F * this.simplifyAngle(f, 10.0F) * f1;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float par2, float par3, float par4)
	{
		EntitySirv sirv = (EntitySirv)entity;
		int i = sirv.getAttackTime();

		if (i > 0)
		{
			armRight.rotateAngleX = -2.0F + 1.5F * this.simplifyAngle(i - par4, 10.0F);
			armLeft.rotateAngleX = -2.0F + 1.5F * this.simplifyAngle(i - par4, 10.0F);
		}
		else
		{
			armRight.rotateAngleX = (-0.2F + 1.5F * this.simplifyAngle(par2, 13.0F)) * par3;
			armLeft.rotateAngleX = (-0.2F - 1.5F * this.simplifyAngle(par2, 13.0F)) * par3;
		}
	}

	private float simplifyAngle(float par1, float par2)
	{
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}
}
