package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGreaterStin extends ModelBase
{
	ModelRenderer Body;
	ModelRenderer BodyThickness;
	ModelRenderer Neck;
	ModelRenderer NeckThickness;
	ModelRenderer NeckThickness2;
	ModelRenderer FrontRightForeleg;
	ModelRenderer FrontLeftForeleg;
	ModelRenderer MidLeftForeleg;
	ModelRenderer MidRightForeleg;
	ModelRenderer BackRightForeleg;
	ModelRenderer BackLeftForeleg;
	ModelRenderer FrontRightLegtip;
	ModelRenderer FrontLeftLegTip;
	ModelRenderer MidRightLegTip;
	ModelRenderer MidLeftLegTip;
	ModelRenderer BackRightLeftTip;
	ModelRenderer BackLeftLegTip;
	ModelRenderer Tail;
	ModelRenderer TailThickness;
	ModelRenderer TailTip;
	ModelRenderer BackSpike;
	ModelRenderer BackSpike2;
	ModelRenderer BackSpike3;
	ModelRenderer NeckSpike;
	ModelRenderer NeckSpike2;

	public ModelGreaterStin()
	{
		textureWidth = 128;
		textureHeight = 128;

		Body = new ModelRenderer(this, 22, 0);
		Body.addBox(-2F, 0F, 0F, 4, 12, 13);
		Body.setRotationPoint(0F, -9F, -2F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		BodyThickness = new ModelRenderer(this, 0, 30);
		BodyThickness.addBox(-4F, 2F, 0F, 8, 8, 13);
		BodyThickness.setRotationPoint(0F, -9F, 0F);
		BodyThickness.setTextureSize(64, 32);
		BodyThickness.mirror = true;
		setRotation(BodyThickness, 0F, 0F, 0F);
		Neck = new ModelRenderer(this, 0, 54);
		Neck.addBox(-2F, -14F, -6F, 4, 20, 6);
		Neck.setRotationPoint(0F, -9F, 0F);
		Neck.setTextureSize(64, 32);
		Neck.mirror = true;
		setRotation(Neck, 0F, 0F, 0F);
		NeckThickness = new ModelRenderer(this, 0, 84);
		NeckThickness.addBox(-3F, -6F, -5F, 6, 14, 6);
		NeckThickness.setRotationPoint(0F, -8F, 0F);
		NeckThickness.setTextureSize(64, 32);
		NeckThickness.mirror = true;
		setRotation(NeckThickness, 0F, 0F, 0F);
		NeckThickness2 = new ModelRenderer(this, 32, 80);
		NeckThickness2.addBox(-1F, -20F, -4F, 2, 17, 6);
		NeckThickness2.setRotationPoint(0F, -11F, -1F);
		NeckThickness2.setTextureSize(128, 128);
		NeckThickness2.mirror = true;
		setRotation(NeckThickness2, 0F, 0F, 0F);
		FrontRightForeleg = new ModelRenderer(this, 64, 0);
		FrontRightForeleg.addBox(-1F, 0F, -1F, 2, 15, 3);
		FrontRightForeleg.setRotationPoint(-4F, -5F, 0F);
		FrontRightForeleg.setTextureSize(64, 32);
		FrontRightForeleg.mirror = true;
		setRotation(FrontRightForeleg, -0.2230717F, 0F, 0.0371786F);
		FrontLeftForeleg = new ModelRenderer(this, 64, 20);
		FrontLeftForeleg.addBox(-1F, 0F, -1F, 2, 15, 3);
		FrontLeftForeleg.setRotationPoint(4F, -5F, 0F);
		FrontLeftForeleg.setTextureSize(64, 32);
		FrontLeftForeleg.mirror = true;
		setRotation(FrontLeftForeleg, -0.2230717F, 0F, -0.0371786F);
		MidLeftForeleg = new ModelRenderer(this, 64, 20);
		MidLeftForeleg.addBox(-1F, 0F, -1F, 2, 15, 3);
		MidLeftForeleg.setRotationPoint(4F, -5F, 4F);
		MidLeftForeleg.setTextureSize(64, 32);
		MidLeftForeleg.mirror = true;
		setRotation(MidLeftForeleg, -0.0371786F, 0F, -0.1487144F);
		MidRightForeleg = new ModelRenderer(this, 64, 0);
		MidRightForeleg.addBox(-1F, 0F, -1F, 2, 15, 3);
		MidRightForeleg.setRotationPoint(-4F, -5F, 4F);
		MidRightForeleg.setTextureSize(64, 32);
		MidRightForeleg.mirror = true;
		setRotation(MidRightForeleg, -0.0371786F, 0F, 0.1487144F);
		BackRightForeleg = new ModelRenderer(this, 64, 0);
		BackRightForeleg.addBox(-1F, 0F, -1F, 2, 15, 3);
		BackRightForeleg.setRotationPoint(-4F, -5F, 9F);
		BackRightForeleg.setTextureSize(64, 32);
		BackRightForeleg.mirror = true;
		setRotation(BackRightForeleg, 0.0743572F, 0F, 0.1115358F);
		BackLeftForeleg = new ModelRenderer(this, 64, 20);
		BackLeftForeleg.addBox(-1F, 0F, -1F, 2, 15, 3);
		BackLeftForeleg.setRotationPoint(4F, -5F, 9F);
		BackLeftForeleg.setTextureSize(64, 32);
		BackLeftForeleg.mirror = true;
		setRotation(BackLeftForeleg, 0.1115358F, 0F, -0.1115358F);
		FrontRightLegtip = new ModelRenderer(this, 0, 0);
		FrontRightLegtip.addBox(0F, 0F, -1F, 1, 15, 2);
		FrontRightLegtip.setRotationPoint(-5F, 9F, -3F);
		FrontRightLegtip.setTextureSize(64, 32);
		FrontRightLegtip.mirror = true;
		setRotation(FrontRightLegtip, -0.1115358F, 0F, 0.0743572F);
		FrontLeftLegTip = new ModelRenderer(this, 10, 0);
		FrontLeftLegTip.addBox(-1F, 0F, -1F, 1, 15, 2);
		FrontLeftLegTip.setRotationPoint(5F, 9F, -3F);
		FrontLeftLegTip.setTextureSize(64, 32);
		FrontLeftLegTip.mirror = true;
		setRotation(FrontLeftLegTip, -0.1115358F, 0F, -0.0743572F);
		MidRightLegTip = new ModelRenderer(this, 0, 0);
		MidRightLegTip.addBox(0F, 0F, -1F, 1, 15, 2);
		MidRightLegTip.setRotationPoint(-7F, 9F, 4F);
		MidRightLegTip.setTextureSize(64, 32);
		MidRightLegTip.mirror = true;
		setRotation(MidRightLegTip, 0.0743572F, 0F, 0.0371786F);
		MidLeftLegTip = new ModelRenderer(this, 10, 0);
		MidLeftLegTip.addBox(-1F, 0F, -1F, 1, 15, 2);
		MidLeftLegTip.setRotationPoint(7F, 9F, 4F);
		MidLeftLegTip.setTextureSize(64, 32);
		MidLeftLegTip.mirror = true;
		setRotation(MidLeftLegTip, 0.0743572F, 0F, -0.0371786F);
		BackRightLeftTip = new ModelRenderer(this, 0, 0);
		BackRightLeftTip.addBox(0F, 0F, -1F, 1, 15, 2);
		BackRightLeftTip.setRotationPoint(-6F, 9F, 11F);
		BackRightLeftTip.setTextureSize(64, 32);
		BackRightLeftTip.mirror = true;
		setRotation(BackRightLeftTip, 0.1487144F, 0F, 0F);
		BackLeftLegTip = new ModelRenderer(this, 10, 0);
		BackLeftLegTip.addBox(-1F, 0F, -1F, 1, 15, 2);
		BackLeftLegTip.setRotationPoint(6F, 9F, 11F);
		BackLeftLegTip.setTextureSize(64, 32);
		BackLeftLegTip.mirror = true;
		setRotation(BackLeftLegTip, 0.1487144F, 0F, 0F);
		Tail = new ModelRenderer(this, 32, 56);
		Tail.addBox(-3F, 0F, 0F, 6, 4, 4);
		Tail.setRotationPoint(0F, -5F, 13F);
		Tail.setTextureSize(64, 32);
		Tail.mirror = true;
		setRotation(Tail, 0.2230717F, 0F, 0F);
		TailThickness = new ModelRenderer(this, 60, 56);
		TailThickness.addBox(-6F, 4F, -1F, 12, 15, 6);
		TailThickness.setRotationPoint(0F, -5F, 13F);
		TailThickness.setTextureSize(64, 32);
		TailThickness.mirror = true;
		setRotation(TailThickness, 0.2230717F, 0F, 0F);
		TailTip = new ModelRenderer(this, 32, 66);
		TailTip.addBox(-4F, 19F, 0F, 8, 7, 4);
		TailTip.setRotationPoint(0F, -5F, 13F);
		TailTip.setTextureSize(64, 32);
		TailTip.mirror = true;
		setRotation(TailTip, 0.2602503F, 0F, 0F);
		BackSpike = new ModelRenderer(this, 0, 18);
		BackSpike.addBox(-1F, -8F, -1F, 2, 8, 2);
		BackSpike.setRotationPoint(2F, -7F, 6F);
		BackSpike.setTextureSize(128, 128);
		BackSpike.mirror = true;
		setRotation(BackSpike, 0.2230717F, 0F, 0.2230717F);
		BackSpike2 = new ModelRenderer(this, 0, 18);
		BackSpike2.addBox(-1F, -10F, -1F, 2, 10, 2);
		BackSpike2.setRotationPoint(-3F, -7F, 3F);
		BackSpike2.setTextureSize(128, 128);
		BackSpike2.mirror = true;
		setRotation(BackSpike2, 0F, 0F, -0.2230717F);
		BackSpike3 = new ModelRenderer(this, 0, 18);
		BackSpike3.addBox(-1F, -6F, -1F, 2, 6, 2);
		BackSpike3.setRotationPoint(-1F, -7F, 11F);
		BackSpike3.setTextureSize(128, 128);
		BackSpike3.mirror = true;
		setRotation(BackSpike3, -0.1115358F, 0F, -0.3346075F);
		NeckSpike = new ModelRenderer(this, 0, 18);
		NeckSpike.addBox(-1F, -11F, -1F, 2, 12, 2);
		NeckSpike.setRotationPoint(0F, -2F, -5F);
		NeckSpike.setTextureSize(128, 128);
		NeckSpike.mirror = true;
		setRotation(NeckSpike, 0.5205006F, 0F, 0F);
		NeckSpike2 = new ModelRenderer(this, 0, 18);
		NeckSpike2.addBox(-1F, -7F, -1F, 2, 7, 2);
		NeckSpike2.setRotationPoint(0F, -11F, -5F);
		NeckSpike2.setTextureSize(128, 128);
		NeckSpike2.mirror = true;
		setRotation(NeckSpike2, 0.5205006F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		BodyThickness.render(f5);
		Neck.render(f5);
		NeckThickness.render(f5);
		NeckThickness2.render(f5);
		FrontRightForeleg.render(f5);
		FrontLeftForeleg.render(f5);
		MidLeftForeleg.render(f5);
		MidRightForeleg.render(f5);
		BackRightForeleg.render(f5);
		BackLeftForeleg.render(f5);
		FrontRightLegtip.render(f5);
		FrontLeftLegTip.render(f5);
		MidRightLegTip.render(f5);
		MidLeftLegTip.render(f5);
		BackRightLeftTip.render(f5);
		BackLeftLegTip.render(f5);
		Tail.render(f5);
		TailThickness.render(f5);
		TailTip.render(f5);
		BackSpike.render(f5);
		BackSpike2.render(f5);
		BackSpike3.render(f5);
		NeckSpike.render(f5);
		NeckSpike2.render(f5);
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
