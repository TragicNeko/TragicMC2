package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelApis extends ModelBase
{
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer Abdomen;
    ModelRenderer RightShoulder;
    ModelRenderer LeftShoulder;
    ModelRenderer RightArm;
    ModelRenderer LeftArm;
    ModelRenderer RightForearm;
    ModelRenderer LeftForearm;
    ModelRenderer RightLeg;
    ModelRenderer LeftLeg;
    ModelRenderer RightFoot;
    ModelRenderer LeftFoot;
    ModelRenderer RightShin;
    ModelRenderer LeftShin;
    ModelRenderer Snout;
    ModelRenderer LeftHorn;
    ModelRenderer RightHorn;
    ModelRenderer LeftHornTip;
    ModelRenderer RightHornTip;
    ModelRenderer AhnkRight;
    ModelRenderer AhnkLeft;
    ModelRenderer AhnkTop;
    ModelRenderer AhnkCrossRight;
    ModelRenderer AhnkCrossLeft;
  
  public ModelApis()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(-5F, -9F, -6F, 10, 9, 10);
      Head.setRotationPoint(0F, -18F, 0F);
      Head.setTextureSize(128, 128);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 48, 48);
      Body.addBox(-8F, 0F, -2F, 16, 12, 10);
      Body.setRotationPoint(0F, -18F, 0F);
      Body.setTextureSize(128, 128);
      Body.mirror = true;
      setRotation(Body, 0.3346075F, 0F, 0F);
      Abdomen = new ModelRenderer(this, 48, 72);
      Abdomen.addBox(-5F, 0F, -3F, 10, 10, 6);
      Abdomen.setRotationPoint(0F, -8F, 6F);
      Abdomen.setTextureSize(128, 128);
      Abdomen.mirror = true;
      setRotation(Abdomen, 0F, 0F, 0F);
      RightShoulder = new ModelRenderer(this, 72, 0);
      RightShoulder.addBox(-10F, -5F, -4F, 10, 10, 14);
      RightShoulder.setRotationPoint(-8F, -16F, 0F);
      RightShoulder.setTextureSize(128, 128);
      RightShoulder.mirror = true;
      setRotation(RightShoulder, 0F, 0F, 0F);
      LeftShoulder = new ModelRenderer(this, 72, 0);
      LeftShoulder.addBox(0F, -5F, -4F, 10, 10, 14);
      LeftShoulder.setRotationPoint(8F, -16F, 0F);
      LeftShoulder.setTextureSize(128, 128);
      LeftShoulder.mirror = true;
      setRotation(LeftShoulder, 0F, 0F, 0F);
      RightArm = new ModelRenderer(this, 40, 16);
      RightArm.addBox(-6F, -2F, -2F, 6, 18, 8);
      RightArm.setRotationPoint(-8F, -16F, 0F);
      RightArm.setTextureSize(128, 128);
      RightArm.mirror = true;
      setRotation(RightArm, 0.1115358F, 0F, 0F);
      LeftArm = new ModelRenderer(this, 40, 16);
      LeftArm.addBox(0F, -2F, -2F, 6, 18, 8);
      LeftArm.setRotationPoint(8F, -16F, 0F);
      LeftArm.setTextureSize(128, 128);
      LeftArm.mirror = true;
      setRotation(LeftArm, 0.1115358F, 0F, 0F);
      RightForearm = new ModelRenderer(this, 72, 28);
      RightForearm.addBox(-5F, 9F, 10F, 5, 12, 4);
      RightForearm.setRotationPoint(-8F, -16F, 0F);
      RightForearm.setTextureSize(128, 128);
      RightForearm.mirror = true;
      setRotation(RightForearm, -0.7435722F, 0F, 0F);
      LeftForearm = new ModelRenderer(this, 72, 28);
      LeftForearm.addBox(0F, 9F, 10F, 5, 12, 4);
      LeftForearm.setRotationPoint(8F, -16F, 0F);
      LeftForearm.setTextureSize(128, 128);
      LeftForearm.mirror = true;
      setRotation(LeftForearm, -0.7435722F, 0F, 0F);
      RightLeg = new ModelRenderer(this, 0, 32);
      RightLeg.addBox(-3F, 0F, -2F, 6, 12, 6);
      RightLeg.setRotationPoint(-3F, 2F, 5F);
      RightLeg.setTextureSize(128, 128);
      RightLeg.mirror = true;
      setRotation(RightLeg, 0F, 0F, 0.1487144F);
      LeftLeg = new ModelRenderer(this, 0, 32);
      LeftLeg.addBox(-2F, 0F, -2F, 6, 12, 6);
      LeftLeg.setRotationPoint(3F, 2F, 5F);
      LeftLeg.setTextureSize(128, 128);
      LeftLeg.mirror = true;
      setRotation(LeftLeg, 0F, 0F, -0.1487144F);
      RightFoot = new ModelRenderer(this, 0, 72);
      RightFoot.addBox(-5F, 20F, -5F, 5, 2, 8);
      RightFoot.setRotationPoint(-3F, 2F, 5F);
      RightFoot.setTextureSize(128, 128);
      RightFoot.mirror = true;
      setRotation(RightFoot, 0F, 0F, 0F);
      LeftFoot = new ModelRenderer(this, 0, 72);
      LeftFoot.addBox(1F, 20F, -5F, 5, 2, 8);
      LeftFoot.setRotationPoint(3F, 2F, 5F);
      LeftFoot.setTextureSize(128, 128);
      LeftFoot.mirror = true;
      setRotation(LeftFoot, 0F, 0F, 0F);
      RightShin = new ModelRenderer(this, 0, 56);
      RightShin.addBox(-3F, 12F, 0F, 4, 9, 4);
      RightShin.setRotationPoint(-3F, 2F, 5F);
      RightShin.setTextureSize(128, 128);
      RightShin.mirror = true;
      setRotation(RightShin, 0F, 0F, 0.0743572F);
      LeftShin = new ModelRenderer(this, 0, 56);
      LeftShin.addBox(0F, 12F, 0F, 4, 9, 4);
      LeftShin.setRotationPoint(3F, 2F, 5F);
      LeftShin.setTextureSize(128, 128);
      LeftShin.mirror = true;
      setRotation(LeftShin, 0F, 0F, -0.0743572F);
      Snout = new ModelRenderer(this, 0, 22);
      Snout.addBox(-2F, 0F, -11F, 4, 4, 5);
      Snout.setRotationPoint(0F, -22F, 0F);
      Snout.setTextureSize(128, 128);
      Snout.mirror = true;
      setRotation(Snout, 0F, 0F, 0F);
      LeftHorn = new ModelRenderer(this, 0, 100);
      LeftHorn.addBox(4F, -4F, 1F, 3, 3, 12);
      LeftHorn.setRotationPoint(0F, -22F, 0F);
      LeftHorn.setTextureSize(128, 128);
      LeftHorn.mirror = true;
      setRotation(LeftHorn, 1.264073F, 0F, 0.2974289F);
      RightHorn = new ModelRenderer(this, 0, 100);
      RightHorn.addBox(-7F, -4F, 1F, 3, 3, 12);
      RightHorn.setRotationPoint(0F, -22F, 0F);
      RightHorn.setTextureSize(128, 128);
      RightHorn.mirror = true;
      setRotation(RightHorn, 1.264073F, 0F, -0.2974289F);
      LeftHornTip = new ModelRenderer(this, 32, 100);
      LeftHornTip.addBox(10F, -17F, -1F, 2, 9, 2);
      LeftHornTip.setRotationPoint(0F, -22F, 0F);
      LeftHornTip.setTextureSize(128, 128);
      LeftHornTip.mirror = true;
      setRotation(LeftHornTip, -0.2602503F, 0F, -0.2602503F);
      RightHornTip = new ModelRenderer(this, 32, 100);
      RightHornTip.addBox(-13F, -17F, -1F, 2, 9, 2);
      RightHornTip.setRotationPoint(0F, -22F, 0F);
      RightHornTip.setTextureSize(128, 128);
      RightHornTip.mirror = true;
      setRotation(RightHornTip, -0.2602503F, 0F, 0.2602503F);
      AhnkRight = new ModelRenderer(this, 48, 0);
      AhnkRight.addBox(-3F, -9F, -7F, 1, 3, 1);
      AhnkRight.setRotationPoint(0F, -18F, 0F);
      AhnkRight.setTextureSize(128, 128);
      AhnkRight.mirror = true;
      setRotation(AhnkRight, 0F, 0F, 0F);
      AhnkLeft = new ModelRenderer(this, 48, 0);
      AhnkLeft.addBox(2F, -9F, -7F, 1, 3, 1);
      AhnkLeft.setRotationPoint(0F, -18F, 0F);
      AhnkLeft.setTextureSize(128, 128);
      AhnkLeft.mirror = true;
      setRotation(AhnkLeft, 0F, 0F, 0F);
      AhnkTop = new ModelRenderer(this, 48, 0);
      AhnkTop.addBox(-2F, -10F, -7F, 4, 1, 1);
      AhnkTop.setRotationPoint(0F, -18F, 0F);
      AhnkTop.setTextureSize(128, 128);
      AhnkTop.mirror = true;
      setRotation(AhnkTop, 0F, 0F, 0F);
      AhnkCrossRight = new ModelRenderer(this, 48, 0);
      AhnkCrossRight.addBox(-5F, -5F, -7F, 7, 1, 1);
      AhnkCrossRight.setRotationPoint(0F, -18F, 0F);
      AhnkCrossRight.setTextureSize(128, 128);
      AhnkCrossRight.mirror = true;
      setRotation(AhnkCrossRight, 0F, 0F, 0.4461433F);
      AhnkCrossLeft = new ModelRenderer(this, 48, 0);
      AhnkCrossLeft.addBox(-2F, -5F, -7F, 7, 1, 1);
      AhnkCrossLeft.setRotationPoint(0F, -18F, 0F);
      AhnkCrossLeft.setTextureSize(128, 128);
      AhnkCrossLeft.mirror = true;
      setRotation(AhnkCrossLeft, 0F, 0F, -0.4461433F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Head.render(f5);
    Body.render(f5);
    Abdomen.render(f5);
    RightShoulder.render(f5);
    LeftShoulder.render(f5);
    RightArm.render(f5);
    LeftArm.render(f5);
    RightForearm.render(f5);
    LeftForearm.render(f5);
    RightLeg.render(f5);
    LeftLeg.render(f5);
    RightFoot.render(f5);
    LeftFoot.render(f5);
    RightShin.render(f5);
    LeftShin.render(f5);
    Snout.render(f5);
    LeftHorn.render(f5);
    RightHorn.render(f5);
    LeftHornTip.render(f5);
    RightHornTip.render(f5);
    AhnkRight.render(f5);
    AhnkLeft.render(f5);
    AhnkTop.render(f5);
    AhnkCrossRight.render(f5);
    AhnkCrossLeft.render(f5);
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
