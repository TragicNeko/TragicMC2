package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTimeController extends ModelBase
{
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6M;
	ModelRenderer Shape7L;
	ModelRenderer Shape8M;
	ModelRenderer Shape9L;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;
	ModelRenderer Shape16;
	ModelRenderer Shape17;

	public ModelTimeController()
	{
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-3F, -3F, -3F, 6, 6, 6);
		Shape1.setRotationPoint(0F, -17F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0.7807508F, 0.7807508F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(-14F, -12F, -15F, 8, 8, 8);
		Shape2.setRotationPoint(0F, -17F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, -0.7435722F, 0.7807508F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(-15F, -20F, 4F, 6, 6, 6);
		Shape3.setRotationPoint(0F, -17F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0.7807508F, 0.5576792F);
		Shape4 = new ModelRenderer(this, 0, 0);
		Shape4.addBox(-3F, -3F, -3F, 6, 6, 6);
		Shape4.setRotationPoint(-11F, -17F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0.7807508F, 0.5576792F);
		Shape5 = new ModelRenderer(this, 0, 0);
		Shape5.addBox(-3F, -3F, -3F, 6, 6, 6);
		Shape5.setRotationPoint(11F, -17F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0.7807508F, 0.5576792F);
		Shape6M = new ModelRenderer(this, 0, 0);
		Shape6M.addBox(-2F, -5F, -2F, 4, 10, 4);
		Shape6M.setRotationPoint(-11F, -8F, 0F);
		Shape6M.setTextureSize(64, 32);
		Shape6M.mirror = true;
		setRotation(Shape6M, -1.33843F, 0.5205006F, 0F);
		Shape7L = new ModelRenderer(this, 0, 0);
		Shape7L.addBox(-2F, -7F, -2F, 4, 16, 4);
		Shape7L.setRotationPoint(-4F, 4F, 0F);
		Shape7L.setTextureSize(64, 32);
		Shape7L.mirror = true;
		setRotation(Shape7L, 0F, 0.5205006F, -2.379431F);
		Shape8M = new ModelRenderer(this, 0, 0);
		Shape8M.addBox(-2F, -5F, -2F, 4, 10, 4);
		Shape8M.setRotationPoint(11F, -8F, 0F);
		Shape8M.setTextureSize(64, 32);
		Shape8M.mirror = true;
		setRotation(Shape8M, -1.449966F, 0.5205006F, 0F);
		Shape9L = new ModelRenderer(this, 0, 0);
		Shape9L.addBox(-2F, -8F, -2F, 4, 16, 4);
		Shape9L.setRotationPoint(4F, -8F, 25F);
		Shape9L.setTextureSize(64, 32);
		Shape9L.mirror = true;
		setRotation(Shape9L, -0.7807508F, 0.5205006F, 0.9666439F);
		Shape10 = new ModelRenderer(this, 0, 0);
		Shape10.addBox(-3F, -3F, -3F, 6, 6, 6);
		Shape10.setRotationPoint(11F, 0F, 0F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0.7807508F, 0.7807508F);
		Shape11 = new ModelRenderer(this, 0, 0);
		Shape11.addBox(-3F, -3F, -3F, 6, 6, 6);
		Shape11.setRotationPoint(-11F, 0F, 6F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0.7807508F, 0.7807508F);
		Shape12 = new ModelRenderer(this, 0, 0);
		Shape12.addBox(-3F, -3F, -3F, 6, 6, 6);
		Shape12.setRotationPoint(4F, 8F, 11F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0.7807508F, 0.7807508F);
		Shape13 = new ModelRenderer(this, 0, 0);
		Shape13.addBox(-3F, -3F, -3F, 6, 6, 6);
		Shape13.setRotationPoint(-4F, 17F, 0F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, -0.8179294F, 0.7807508F);
		Shape14 = new ModelRenderer(this, 0, 0);
		Shape14.addBox(0F, -30F, 0F, 6, 6, 6);
		Shape14.setRotationPoint(0F, -17F, 0F);
		Shape14.setTextureSize(64, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0.7807508F, 0.5576792F);
		Shape15 = new ModelRenderer(this, 0, 0);
		Shape15.addBox(0F, 9F, -23F, 6, 6, 6);
		Shape15.setRotationPoint(0F, -17F, 0F);
		Shape15.setTextureSize(64, 32);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0.7807508F, 0.5576792F);
		Shape16 = new ModelRenderer(this, 0, 0);
		Shape16.addBox(-30F, 0F, 0F, 6, 6, 6);
		Shape16.setRotationPoint(0F, -17F, 0F);
		Shape16.setTextureSize(64, 32);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0.7807508F, 0.5576792F);
		Shape17 = new ModelRenderer(this, 0, 0);
		Shape17.addBox(31F, 0F, 0F, 6, 6, 6);
		Shape17.setRotationPoint(0F, -17F, 0F);
		Shape17.setTextureSize(64, 32);
		Shape17.mirror = true;
		setRotation(Shape17, 0F, 0.7807508F, 0.5576792F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6M.render(f5);
		Shape7L.render(f5);
		Shape8M.render(f5);
		Shape9L.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
		Shape16.render(f5);
		Shape17.render(f5);
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
