package tragicneko.tragicmc.client.model;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelJabba extends ModelBase
{
	//fields
	ModelRenderer Head;
	ModelRenderer HeadMiddle;
	ModelRenderer Middle;
	ModelRenderer TailMiddle;
	ModelRenderer LeftPincer;
	ModelRenderer RightPincer;
	ModelRenderer TailHead;
	ModelRenderer LeftTailPincer;
	ModelRenderer RightTailPincer;

	public ModelJabba()
	{
		textureWidth = 64;
		textureHeight = 64;

		Head = new ModelRenderer(this, 32, 4);
		Head.addBox(-4F, -4F, -8F, 8, 8, 8);
		Head.setRotationPoint(0F, 18F, -3F);
		Head.setTextureSize(64, 64);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		HeadMiddle = new ModelRenderer(this, 0, 0);
		HeadMiddle.addBox(-3F, -3F, -3F, 6, 6, 6);
		HeadMiddle.setRotationPoint(0F, 18F, 0F);
		HeadMiddle.setTextureSize(64, 64);
		HeadMiddle.mirror = true;
		setRotation(HeadMiddle, 0F, 0F, 0F);
		Middle = new ModelRenderer(this, 0, 14);
		Middle.addBox(-4F, -4F, -6F, 8, 8, 8);
		Middle.setRotationPoint(0F, 18F, 9F);
		Middle.setTextureSize(64, 64);
		Middle.mirror = true;
		setRotation(Middle, 0F, 0F, 0F);
		TailMiddle = new ModelRenderer(this, 0, 0);
		TailMiddle.addBox(-3F, -3F, -3F, 6, 6, 6);
		TailMiddle.setRotationPoint(0F, 18F, 14F);
		TailMiddle.setTextureSize(64, 64);
		TailMiddle.mirror = true;
		setRotation(TailMiddle, 0F, 0F, 0F);
		LeftPincer = new ModelRenderer(this, 0, 32);
		LeftPincer.addBox(1F, -2F, -3F, 4, 4, 4);
		LeftPincer.setRotationPoint(0F, 19F, -11F);
		LeftPincer.setTextureSize(64, 64);
		LeftPincer.mirror = true;
		setRotation(LeftPincer, 0F, 0F, 0F);
		RightPincer = new ModelRenderer(this, 0, 32);
		RightPincer.addBox(-5F, -2F, -3F, 4, 4, 4);
		RightPincer.setRotationPoint(0F, 19F, -11F);
		RightPincer.setTextureSize(64, 64);
		RightPincer.mirror = true;
		setRotation(RightPincer, 0F, 0F, 0F);
		TailHead = new ModelRenderer(this, 32, 4);
		TailHead.addBox(-4F, -4F, 0F, 8, 8, 8);
		TailHead.setRotationPoint(0F, 18F, 17F);
		TailHead.setTextureSize(64, 64);
		TailHead.mirror = true;
		setRotation(TailHead, 0F, 0F, 0F);
		LeftTailPincer = new ModelRenderer(this, 0, 32);
		LeftTailPincer.addBox(1F, -2F, 0F, 4, 4, 4);
		LeftTailPincer.setRotationPoint(0F, 19F, 24F);
		LeftTailPincer.setTextureSize(64, 64);
		LeftTailPincer.mirror = true;
		setRotation(LeftTailPincer, 0F, 0F, 0F);
		RightTailPincer = new ModelRenderer(this, 0, 32);
		RightTailPincer.addBox(-5F, -2F, 0F, 4, 4, 4);
		RightTailPincer.setRotationPoint(0F, 19F, 24F);
		RightTailPincer.setTextureSize(64, 64);
		RightTailPincer.mirror = true;
		setRotation(RightTailPincer, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Head.render(f5);
		HeadMiddle.render(f5);
		Middle.render(f5);
		TailMiddle.render(f5);
		LeftPincer.render(f5);
		RightPincer.render(f5);
		TailHead.render(f5);
		LeftTailPincer.render(f5);
		RightTailPincer.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		
	}

	public void setLivingAnimations(EntityLivingBase entity, float par1, float par2, float par3)
	{		
		if (entity.isWet())
		{
			par2 *= 2;
		}
		
		this.LeftPincer.rotateAngleY = MathHelper.cos(par1 * 0.3F) * par2;
		this.LeftPincer.rotateAngleZ = MathHelper.cos(par1 * 0.3F - 0.1F) * par2;
		
		this.RightPincer.rotateAngleY = MathHelper.cos(par1 * 0.3F + (float)Math.PI) * par2;
		this.RightPincer.rotateAngleZ = MathHelper.cos(par1 * 0.3F - 0.1F) * par2;

		this.LeftTailPincer.rotateAngleY = MathHelper.cos(par1 * 0.3F) * par2;
		this.LeftTailPincer.rotateAngleZ = MathHelper.cos(par1 * 0.3F + 0.1F) * par2;
		
		this.RightTailPincer.rotateAngleY = MathHelper.cos(par1 * 0.3F + (float)Math.PI) * par2;
		this.RightTailPincer.rotateAngleZ = MathHelper.cos(par1 * 0.3F + 0.1F) * par2;

		this.Middle.rotateAngleZ = MathHelper.cos(par1 * 0.3F) * par2;
		this.Middle.rotateAngleY = MathHelper.cos(par1 * 0.2F) + par2;
		
		this.HeadMiddle.rotateAngleZ = MathHelper.cos(par1 * 0.3F + (float)Math.PI + 0.5F) * par2;
		this.HeadMiddle.rotateAngleY = MathHelper.cos(par1 * 0.2F + (float)Math.PI) + par2;
		
		this.TailMiddle.rotateAngleZ = MathHelper.cos(par1 * 0.3F - (float)Math.PI - 0.5F) * par2;
		this.TailMiddle.rotateAngleY = MathHelper.cos(par1 * 0.2F - (float)Math.PI) + par2;
		
		this.Head.rotateAngleZ = MathHelper.cos(par1 * 0.3F - 0.1F) * par2;
		this.TailHead.rotateAngleZ = MathHelper.cos(par1 * 0.3F + 0.1F) * par2;
	}

}
