package tragicneko.tragicmc.client.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTitan extends ModelBase {

	public ModelRenderer titan;

	public ModelTitan()
	{
		textureWidth = 96;
		textureHeight = 32;

		titan = new ModelRenderer(this, 0, 0);
		titan.addBox(-2F, -5F, -0.5F, 5, 4, 2);
		titan.setRotationPoint(16F, 4F, -3F);
		
		ModelRenderer titan2 = new ModelRenderer(this, 32, 0);
		titan2.addBox(-1F, -1F, 0F, 3, 8, 1);
		titan.addChild(titan2);
		
		ModelRenderer titanBlade = new ModelRenderer(this, 0, 0);
		titanBlade.addBox(-4F, 7F, -0.5F, 9, 72, 2);
		titan.addChild(titanBlade);
		
		ModelRenderer titanBladeb = new ModelRenderer(this, 32, 0);
		titanBladeb.addBox(-6, 25F, -0.5F, 2, 3, 2);
		titan.addChild(titanBladeb);
		
		ModelRenderer titanBladec = new ModelRenderer(this, 32, 0);
		titanBladec.addBox(5, 25F, -0.5F, 2, 3, 2);
		titan.addChild(titanBladec);
		
		ModelRenderer titanBladed = new ModelRenderer(this, 32, 0);
		titanBladed.addBox(-6, 46F, -0.5F, 2, 9, 2);
		titan.addChild(titanBladed);
		
		ModelRenderer titanBladee = new ModelRenderer(this, 32, 0);
		titanBladee.addBox(5, 46F, -0.5F, 2, 9, 2);
		titan.addChild(titanBladee);
		
		ModelRenderer titanBladef = new ModelRenderer(this, 32, 0);
		titanBladef.addBox(-6, 69F, -0.5F, 2, 8, 2);
		titan.addChild(titanBladef);
		
		ModelRenderer titanBladeg = new ModelRenderer(this, 32, 0);
		titanBladeg.addBox(5, 69F, -0.5F, 2, 8, 2);
		titan.addChild(titanBladeg);
		
		ModelRenderer titanBlade2 = new ModelRenderer(this, 32, 0);
		titanBlade2.addBox(-10F, 9F, -0.5F, 6, 16, 2);
		titan.addChild(titanBlade2);
		
		ModelRenderer titanBlade3 = new ModelRenderer(this, 32, 0);
		titanBlade3.addBox(5F, 9F, -0.5F, 6, 16, 2);
		titan.addChild(titanBlade3);
		
		ModelRenderer titanBlade4 = new ModelRenderer(this, 64, 0);
		titanBlade4.addBox(-13F, 10F, 0F, 3, 12, 1);
		titan.addChild(titanBlade4);
		
		ModelRenderer titanBlade5 = new ModelRenderer(this, 64, 0);
		titanBlade5.addBox(11F, 10F, 0F, 3, 12, 1);
		titan.addChild(titanBlade5);
		
		ModelRenderer titanBlade6 = new ModelRenderer(this, 32, 0);
		titanBlade6.addBox(-12F, 28F, -0.5F, 8, 18, 2);
		titan.addChild(titanBlade6);
		
		ModelRenderer titanBlade7 = new ModelRenderer(this, 32, 0);
		titanBlade7.addBox(5F, 28F, -0.5F, 8, 18, 2);
		titan.addChild(titanBlade7);
		
		ModelRenderer titanBlade8 = new ModelRenderer(this, 64, 0);
		titanBlade8.addBox(-15F, 30F, 0F, 3, 14, 1);
		titan.addChild(titanBlade8);
		
		ModelRenderer titanBlade9 = new ModelRenderer(this, 64, 0);
		titanBlade9.addBox(13F, 30F, 0F, 3, 14, 1);
		titan.addChild(titanBlade9);
		
		ModelRenderer titanBlade10 = new ModelRenderer(this, 32, 0);
		titanBlade10.addBox(-9F, 55F, -0.5F, 5, 14, 2);
		titan.addChild(titanBlade10);
		
		ModelRenderer titanBlade11 = new ModelRenderer(this, 32, 0);
		titanBlade11.addBox(5F, 55F, -0.5F, 5, 14, 2);
		titan.addChild(titanBlade11);
		
		ModelRenderer titanBlade12 = new ModelRenderer(this, 64, 0);
		titanBlade12.addBox(-12F, 57F, 0F, 3, 10, 1);
		titan.addChild(titanBlade12);
		
		ModelRenderer titanBlade13 = new ModelRenderer(this, 64, 0);
		titanBlade13.addBox(10F, 57F, 0F, 3, 10, 1);
		titan.addChild(titanBlade13);
		
		ModelRenderer titanPlate = new ModelRenderer(this, 0, 0);
		titanPlate.addBox(-7F, 10F, -2.5F, 14, 16, 2);
		titan.addChild(titanPlate);
		
		ModelRenderer titanPlateb = new ModelRenderer(this, 64, 0);
		titanPlateb.addBox(-5F, 12F, -3.5F, 10, 12, 1);
		titan.addChild(titanPlateb);
		
		ModelRenderer titanPlate2 = new ModelRenderer(this, 0, 0);
		titanPlate2.addBox(-7F, 10F, 1.5F, 14, 16, 2);
		titan.addChild(titanPlate2);
		
		ModelRenderer titanPlate2b = new ModelRenderer(this, 64, 0);
		titanPlate2b.addBox(-5F, 12F, 3.5F, 10, 12, 1);
		titan.addChild(titanPlate2b);
		
		ModelRenderer titanPlate3 = new ModelRenderer(this, 0, 0);
		titanPlate3.addBox(-7F, 28F, -2.5F, 14, 18, 2);
		titan.addChild(titanPlate3);
		
		ModelRenderer titanPlate3b = new ModelRenderer(this, 64, 0);
		titanPlate3b.addBox(-6F, 30F, -3.5F, 12, 14, 1);
		titan.addChild(titanPlate3b);
		
		ModelRenderer titanPlate4 = new ModelRenderer(this, 0, 0);
		titanPlate4.addBox(-7F, 28F, 1.5F, 14, 18, 2);
		titan.addChild(titanPlate4);
		
		ModelRenderer titanPlate4b = new ModelRenderer(this, 64, 0);
		titanPlate4b.addBox(-6F, 30F, 3.5F, 12, 14, 1);
		titan.addChild(titanPlate4b);
		
		ModelRenderer titanPlate5 = new ModelRenderer(this, 0, 0);
		titanPlate5.addBox(-7F, 55F, -2.5F, 14, 16, 2);
		titan.addChild(titanPlate5);
		
		ModelRenderer titanPlate5b = new ModelRenderer(this, 64, 0);
		titanPlate5b.addBox(-5F, 57F, -3.5F, 10, 12, 1);
		titan.addChild(titanPlate5b);
		
		ModelRenderer titanPlate6 = new ModelRenderer(this, 0, 0);
		titanPlate6.addBox(-7F, 55F, 1.5F, 14, 16, 2);
		titan.addChild(titanPlate6);
		
		ModelRenderer titanPlate6b = new ModelRenderer(this, 64, 0);
		titanPlate6b.addBox(-5F, 57F, 3.5F, 10, 12, 1);
		titan.addChild(titanPlate6b);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		titan.render(f5);
	}
}
