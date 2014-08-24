package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelAbomination extends ModelBase
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

	public ModelAbomination()
	{
		textureWidth = 64;
		textureHeight = 64;
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-5F, -8F, -4F, 10, 8, 6);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-6F, 0F, -2F, 12, 16, 4);
		body.setRotationPoint(0F, 0F, 1F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.2082002F, 0F, 0F);
		rightarm = new ModelRenderer(this, 48, 16);
		rightarm.addBox(-3F, 0F, -2F, 3, 14, 5);
		rightarm.setRotationPoint(-5F, 0F, 0F);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 48, 16);
		leftarm.addBox(-1F, 0F, -2F, 3, 14, 5);
		leftarm.setRotationPoint(6F, 0F, 0F);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 8, 4);
		rightleg.setRotationPoint(-3F, 16F, 4F);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2F, 0F, -2F, 4, 8, 4);
		leftleg.setRotationPoint(3F, 16F, 4F);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
		nose = new ModelRenderer(this, 32, 0);
		nose.addBox(-2F, -6F, -5F, 4, 2, 1);
		nose.setRotationPoint(0F, 0F, 0F);
		nose.setTextureSize(64, 64);
		nose.mirror = true;
		setRotation(nose, 0F, 0F, 0F);
		rightfist = new ModelRenderer(this, 0, 36);
		rightfist.addBox(-3F, 14F, -3F, 5, 5, 6);
		rightfist.setRotationPoint(-6F, 0F, 0F);
		rightfist.setTextureSize(64, 64);
		rightfist.mirror = true;
		setRotation(rightfist, 0F, 0F, 0F);
		leftfist = new ModelRenderer(this, 22, 36);
		leftfist.addBox(-2F, 14F, -3F, 5, 5, 6);
		leftfist.setRotationPoint(6F, 0F, 0F);
		leftfist.setTextureSize(64, 64);
		leftfist.mirror = true;
		setRotation(leftfist, 0F, 0F, 0F);
		rightear = new ModelRenderer(this, 56, 0);
		rightear.addBox(-6F, -6F, -2F, 1, 3, 3);
		rightear.setRotationPoint(0F, 0F, 0F);
		rightear.setTextureSize(64, 64);
		rightear.mirror = true;
		setRotation(rightear, 0F, 0F, 0F);
		leftear = new ModelRenderer(this, 48, 0);
		leftear.addBox(5F, -6F, -2F, 1, 3, 3);
		leftear.setRotationPoint(0F, 0F, 0F);
		leftear.setTextureSize(64, 64);
		leftear.mirror = true;
		setRotation(leftear, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		nose.render(par7);
		rightfist.render(par7);
		leftfist.render(par7);
		rightear.render(par7);
		leftear.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		/*
		this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.4662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.4662F) * 2.0F * par2 * 0.5F;
		this.rightarm.rotateAngleZ = 0.0F;
		this.leftarm.rotateAngleZ = 0.0F;
		this.rightfist.rotateAngleX = MathHelper.cos(par1 * 0.4662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.leftfist.rotateAngleX = MathHelper.cos(par1 * 0.4662F) * 2.0F * par2 * 0.5F;
		this.rightfist.rotateAngleZ = 0.0F;
		this.leftfist.rotateAngleZ = 0.0F;
		this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.4662F) * 1.4F * par2;
		this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.4662F + (float)Math.PI) * 1.4F * par2;
		this.rightleg.rotateAngleZ = 0.0F;
		this.leftleg.rotateAngleZ = 0.0F; */
	}

}
