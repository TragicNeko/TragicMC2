package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTox extends ModelBase
{
	ModelRenderer Bulb;
	ModelRenderer BulbTop;
	ModelRenderer StemBottom;
	ModelRenderer StemMiddle;
	ModelRenderer StemTop;
	ModelRenderer BottomLeaf;
	ModelRenderer TopLeaf;
	ModelRenderer LeftLeaf;
	ModelRenderer RightLeaf;
	ModelRenderer TopMouth;
	ModelRenderer BottomMouth;
	ModelRenderer BasePetals;
	ModelRenderer InnerLeafTL;
	ModelRenderer InnerLeafTR;
	ModelRenderer InnerLeafBL;
	ModelRenderer InnerLeafBR;
	ModelRenderer OuterLeafTL;
	ModelRenderer OuterLeafBL;
	ModelRenderer OuterLeafBR;
	ModelRenderer OuterLeafTR;
	ModelRenderer LegFL;
	ModelRenderer LegFR;
	ModelRenderer LegBR;
	ModelRenderer LegBL;

	public ModelTox()
	{
		textureWidth = 128;
		textureHeight = 64;

		Bulb = new ModelRenderer(this, 0, 0);
		Bulb.addBox(-5F, 0F, -5F, 10, 6, 10);
		Bulb.setRotationPoint(0F, 16F, 0F);
		Bulb.setTextureSize(128, 64);
		Bulb.mirror = true;
		setRotation(Bulb, 0F, 0F, 0F);
		BulbTop = new ModelRenderer(this, 42, 0);
		BulbTop.addBox(-3F, -2F, -3F, 6, 2, 6);
		BulbTop.setRotationPoint(0F, 16F, 0F);
		BulbTop.setTextureSize(128, 64);
		BulbTop.mirror = true;
		setRotation(BulbTop, 0F, 0F, 0F);
		StemBottom = new ModelRenderer(this, 0, 18);
		StemBottom.addBox(-1F, -7F, -1F, 2, 6, 2);
		StemBottom.setRotationPoint(0F, 16F, 0F);
		StemBottom.setTextureSize(128, 64);
		StemBottom.mirror = true;
		setRotation(StemBottom, -0.4089647F, 0F, 0F);
		StemMiddle = new ModelRenderer(this, 0, 18);
		StemMiddle.addBox(-1F, -6F, -1F, 2, 6, 2);
		StemMiddle.setRotationPoint(0F, 10F, 3F);
		StemMiddle.setTextureSize(128, 64);
		StemMiddle.mirror = true;
		setRotation(StemMiddle, 0.2974289F, 0F, 0F);
		StemTop = new ModelRenderer(this, 0, 19);
		StemTop.addBox(-1F, -4F, -1F, 2, 5, 2);
		StemTop.setRotationPoint(0F, 4F, 1F);
		StemTop.setTextureSize(128, 64);
		StemTop.mirror = true;
		setRotation(StemTop, 0.9294653F, 0F, 0F);
		BottomLeaf = new ModelRenderer(this, 54, 40);
		BottomLeaf.addBox(-2F, 1F, -1F, 4, 6, 1);
		BottomLeaf.setRotationPoint(0F, 2F, -1F);
		BottomLeaf.setTextureSize(128, 64);
		BottomLeaf.mirror = true;
		setRotation(BottomLeaf, -0.5205006F, 0F, 0F);
		TopLeaf = new ModelRenderer(this, 54, 22);
		TopLeaf.addBox(-2F, -7F, -1F, 4, 6, 1);
		TopLeaf.setRotationPoint(0F, 2F, -1F);
		TopLeaf.setTextureSize(128, 64);
		TopLeaf.mirror = true;
		setRotation(TopLeaf, 0.4089647F, 0F, 0F);
		LeftLeaf = new ModelRenderer(this, 60, 32);
		LeftLeaf.addBox(2F, -2F, 0F, 6, 4, 1);
		LeftLeaf.setRotationPoint(0F, 2F, -1F);
		LeftLeaf.setTextureSize(128, 64);
		LeftLeaf.mirror = true;
		setRotation(LeftLeaf, 0F, 0.669215F, 0F);
		RightLeaf = new ModelRenderer(this, 44, 32);
		RightLeaf.addBox(-8F, -2F, 0F, 6, 4, 1);
		RightLeaf.setRotationPoint(0F, 2F, -1F);
		RightLeaf.setTextureSize(128, 64);
		RightLeaf.mirror = true;
		setRotation(RightLeaf, 0F, -0.6320364F, 0F);
		TopMouth = new ModelRenderer(this, 70, 0);
		TopMouth.addBox(-1F, -2F, -4F, 2, 2, 5);
		TopMouth.setRotationPoint(0F, 2F, -3F);
		TopMouth.setTextureSize(128, 64);
		TopMouth.mirror = true;
		setRotation(TopMouth, 0F, 0F, 0F);
		BottomMouth = new ModelRenderer(this, 70, 10);
		BottomMouth.addBox(-1F, -1F, -5F, 2, 1, 4);
		BottomMouth.setRotationPoint(0F, 2F, -1F);
		BottomMouth.setTextureSize(128, 64);
		BottomMouth.mirror = true;
		setRotation(BottomMouth, 0.3717861F, 0F, 0F);
		BasePetals = new ModelRenderer(this, 0, 32);
		BasePetals.addBox(-2F, -2F, -1F, 4, 4, 1);
		BasePetals.setRotationPoint(0F, 2F, -1F);
		BasePetals.setTextureSize(128, 64);
		BasePetals.mirror = true;
		setRotation(BasePetals, 0F, 0F, 0F);
		InnerLeafTL = new ModelRenderer(this, 86, 32);
		InnerLeafTL.addBox(0F, -5F, -2F, 2, 4, 1);
		InnerLeafTL.setRotationPoint(0F, 2F, -1F);
		InnerLeafTL.setTextureSize(128, 64);
		InnerLeafTL.mirror = true;
		setRotation(InnerLeafTL, 0.4461433F, 0F, 0.2974289F);
		InnerLeafTR = new ModelRenderer(this, 76, 32);
		InnerLeafTR.addBox(-2F, -5F, -2F, 2, 4, 1);
		InnerLeafTR.setRotationPoint(0F, 2F, -1F);
		InnerLeafTR.setTextureSize(128, 64);
		InnerLeafTR.mirror = true;
		setRotation(InnerLeafTR, 0.4089647F, 0F, -0.2974289F);
		InnerLeafBL = new ModelRenderer(this, 86, 40);
		InnerLeafBL.addBox(0F, 2F, -2F, 2, 4, 1);
		InnerLeafBL.setRotationPoint(0F, 2F, -1F);
		InnerLeafBL.setTextureSize(128, 64);
		InnerLeafBL.mirror = true;
		setRotation(InnerLeafBL, -0.4461433F, 0F, -0.3717861F);
		InnerLeafBR = new ModelRenderer(this, 76, 40);
		InnerLeafBR.addBox(-2F, 2F, -2F, 2, 4, 1);
		InnerLeafBR.setRotationPoint(0F, 2F, -1F);
		InnerLeafBR.setTextureSize(128, 64);
		InnerLeafBR.mirror = true;
		setRotation(InnerLeafBR, -0.4089647F, 0F, 0.4461433F);
		OuterLeafTL = new ModelRenderer(this, 28, 18);
		OuterLeafTL.addBox(-3F, -10F, 0F, 6, 10, 1);
		OuterLeafTL.setRotationPoint(0F, 2F, -1F);
		OuterLeafTL.setTextureSize(128, 64);
		OuterLeafTL.mirror = true;
		setRotation(OuterLeafTL, 0.1115358F, 0F, 0.7807508F);
		OuterLeafBL = new ModelRenderer(this, 28, 32);
		OuterLeafBL.addBox(-3F, 0F, 0F, 6, 10, 1);
		OuterLeafBL.setRotationPoint(0F, 2F, -1F);
		OuterLeafBL.setTextureSize(128, 64);
		OuterLeafBL.mirror = true;
		setRotation(OuterLeafBL, -0.2230717F, 0F, -0.7807508F);
		OuterLeafBR = new ModelRenderer(this, 12, 18);
		OuterLeafBR.addBox(-3F, 0F, 0F, 6, 10, 1);
		OuterLeafBR.setRotationPoint(0F, 2F, -1F);
		OuterLeafBR.setTextureSize(128, 64);
		OuterLeafBR.mirror = true;
		setRotation(OuterLeafBR, -0.2230717F, 0F, 0.7807508F);
		OuterLeafTR = new ModelRenderer(this, 12, 32);
		OuterLeafTR.addBox(-3F, -10F, 0F, 6, 10, 1);
		OuterLeafTR.setRotationPoint(0F, 2F, -1F);
		OuterLeafTR.setTextureSize(128, 64);
		OuterLeafTR.mirror = true;
		setRotation(OuterLeafTR, 0.1115358F, 0F, -0.7807508F);
		LegFL = new ModelRenderer(this, 0, 50);
		LegFL.addBox(-2F, 0F, -2F, 4, 4, 4);
		LegFL.setRotationPoint(5F, 20F, -5F);
		LegFL.setTextureSize(128, 64);
		LegFL.mirror = true;
		setRotation(LegFL, 0F, 0F, 0F);
		LegFR = new ModelRenderer(this, 0, 50);
		LegFR.addBox(-2F, 0F, -2F, 4, 4, 4);
		LegFR.setRotationPoint(-5F, 20F, -5F);
		LegFR.setTextureSize(128, 64);
		LegFR.mirror = true;
		setRotation(LegFR, 0F, 0F, 0F);
		LegBR = new ModelRenderer(this, 0, 50);
		LegBR.addBox(-2F, 0F, -2F, 4, 4, 4);
		LegBR.setRotationPoint(-5F, 20F, 5F);
		LegBR.setTextureSize(128, 64);
		LegBR.mirror = true;
		setRotation(LegBR, 0F, 0F, 0F);
		LegBL = new ModelRenderer(this, 0, 50);
		LegBL.addBox(-2F, 0F, -2F, 4, 4, 4);
		LegBL.setRotationPoint(5F, 20F, 5F);
		LegBL.setTextureSize(128, 64);
		LegBL.mirror = true;
		setRotation(LegBL, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Bulb.render(f5);
		BulbTop.render(f5);
		StemBottom.render(f5);
		StemMiddle.render(f5);
		StemTop.render(f5);
		BottomLeaf.render(f5);
		TopLeaf.render(f5);
		LeftLeaf.render(f5);
		RightLeaf.render(f5);
		TopMouth.render(f5);
		BottomMouth.render(f5);
		BasePetals.render(f5);
		InnerLeafTL.render(f5);
		InnerLeafTR.render(f5);
		InnerLeafBL.render(f5);
		InnerLeafBR.render(f5);
		OuterLeafTL.render(f5);
		OuterLeafBL.render(f5);
		OuterLeafBR.render(f5);
		OuterLeafTR.render(f5);
		LegFL.render(f5);
		LegFR.render(f5);
		LegBR.render(f5);
		LegBL.render(f5);
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
