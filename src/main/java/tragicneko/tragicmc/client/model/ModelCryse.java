package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCryse extends ModelBase
{
	//fields
	ModelRenderer Middle;
	ModelRenderer Top;
	ModelRenderer Bottom;
	ModelRenderer Left;
	ModelRenderer Right;
	ModelRenderer TopSemi;
	ModelRenderer BottomSemi;
	ModelRenderer LeftSemi;
	ModelRenderer RightSemi;
	ModelRenderer TLBar;
	ModelRenderer TRBar;
	ModelRenderer BRBar;
	ModelRenderer BLBar;
	ModelRenderer EyeRight;
	ModelRenderer EyeLeft;
	ModelRenderer Mouth;

	public ModelCryse()
	{
		textureWidth = 64;
		textureHeight = 32;

		Middle = new ModelRenderer(this, 0, 0);
		Middle.addBox(-1F, -1F, -1.5F, 2, 2, 3);
		Middle.setRotationPoint(0F, 9F, 0F);
		Middle.setTextureSize(64, 32);
		Middle.mirror = true;
		setRotation(Middle, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 0, 0);
		Top.addBox(-1F, -1F, -1F, 2, 2, 2);
		Top.setRotationPoint(0F, 3F, 0F);
		Top.setTextureSize(64, 32);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		Bottom = new ModelRenderer(this, 0, 0);
		Bottom.addBox(-1F, -1F, -1F, 2, 2, 2);
		Bottom.setRotationPoint(0F, 15F, 0F);
		Bottom.setTextureSize(64, 32);
		Bottom.mirror = true;
		setRotation(Bottom, 0F, 0F, 0F);
		Left = new ModelRenderer(this, 0, 0);
		Left.addBox(-1F, -1F, -1F, 2, 2, 2);
		Left.setRotationPoint(5F, 9F, 0F);
		Left.setTextureSize(64, 32);
		Left.mirror = true;
		setRotation(Left, 0F, 0F, 0F);
		Right = new ModelRenderer(this, 0, 0);
		Right.addBox(-1F, -1F, -1F, 2, 2, 2);
		Right.setRotationPoint(-5F, 9F, 0F);
		Right.setTextureSize(64, 32);
		Right.mirror = true;
		setRotation(Right, 0F, 0F, 0F);
		TopSemi = new ModelRenderer(this, 0, 0);
		TopSemi.addBox(-1F, -5F, -1F, 1, 4, 1);
		TopSemi.setRotationPoint(0F, 9F, 0F);
		TopSemi.setTextureSize(64, 32);
		TopSemi.mirror = true;
		setRotation(TopSemi, 0F, 0F, 0F);
		BottomSemi = new ModelRenderer(this, 0, 0);
		BottomSemi.addBox(0F, 1F, 0F, 1, 4, 1);
		BottomSemi.setRotationPoint(0F, 9F, 0F);
		BottomSemi.setTextureSize(64, 32);
		BottomSemi.mirror = true;
		setRotation(BottomSemi, 0F, 0F, 0F);
		LeftSemi = new ModelRenderer(this, 0, 0);
		LeftSemi.addBox(1F, -1F, 0F, 3, 1, 1);
		LeftSemi.setRotationPoint(0F, 9F, 0F);
		LeftSemi.setTextureSize(64, 32);
		LeftSemi.mirror = true;
		setRotation(LeftSemi, 0F, 0F, 0F);
		RightSemi = new ModelRenderer(this, 0, 0);
		RightSemi.addBox(-4F, 0F, -1F, 3, 1, 1);
		RightSemi.setRotationPoint(0F, 9F, 0F);
		RightSemi.setTextureSize(64, 32);
		RightSemi.mirror = true;
		setRotation(RightSemi, 0F, 0F, 0F);
		TLBar = new ModelRenderer(this, 0, 0);
		TLBar.addBox(-3F, -5F, -1F, 6, 1, 1);
		TLBar.setRotationPoint(0F, 9F, 0F);
		TLBar.setTextureSize(64, 32);
		TLBar.mirror = true;
		setRotation(TLBar, 0F, 0F, 0.7435722F);
		TRBar = new ModelRenderer(this, 0, 0);
		TRBar.addBox(-3F, 4F, 0F, 6, 1, 1);
		TRBar.setRotationPoint(0F, 9F, 0F);
		TRBar.setTextureSize(64, 32);
		TRBar.mirror = true;
		setRotation(TRBar, 0F, 0F, 2.379431F);
		BRBar = new ModelRenderer(this, 0, 0);
		BRBar.addBox(-3F, 4F, -1F, 6, 1, 1);
		BRBar.setRotationPoint(0F, 9F, 0F);
		BRBar.setTextureSize(64, 32);
		BRBar.mirror = true;
		setRotation(BRBar, 0F, 0F, 0.7435722F);
		BLBar = new ModelRenderer(this, 0, 0);
		BLBar.addBox(-3F, -5F, 0F, 6, 1, 1);
		BLBar.setRotationPoint(0F, 9F, 0F);
		BLBar.setTextureSize(64, 32);
		BLBar.mirror = true;
		setRotation(BLBar, 0F, 0F, 2.379431F);
		EyeRight = new ModelRenderer(this, 0, 10);
		EyeRight.addBox(-3F, -2F, -2F, 1, 1, 1);
		EyeRight.setRotationPoint(0F, 9F, 0F);
		EyeRight.setTextureSize(64, 32);
		EyeRight.mirror = true;
		setRotation(EyeRight, 0F, 0F, 0F);
		EyeLeft = new ModelRenderer(this, 0, 10);
		EyeLeft.addBox(2F, -3F, -2F, 1, 1, 1);
		EyeLeft.setRotationPoint(0F, 9F, 0F);
		EyeLeft.setTextureSize(64, 32);
		EyeLeft.mirror = true;
		setRotation(EyeLeft, 0F, 0F, 0F);
		Mouth = new ModelRenderer(this, 0, 10);
		Mouth.addBox(-2F, 2F, -2F, 2, 2, 1);
		Mouth.setRotationPoint(0F, 9F, 0F);
		Mouth.setTextureSize(64, 32);
		Mouth.mirror = true;
		setRotation(Mouth, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Middle.render(f5);
		Top.render(f5);
		Bottom.render(f5);
		Left.render(f5);
		Right.render(f5);
		TopSemi.render(f5);
		BottomSemi.render(f5);
		LeftSemi.render(f5);
		RightSemi.render(f5);
		TLBar.render(f5);
		TRBar.render(f5);
		BRBar.render(f5);
		BLBar.render(f5);
		EyeRight.render(f5);
		EyeLeft.render(f5);
		Mouth.render(f5);
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
