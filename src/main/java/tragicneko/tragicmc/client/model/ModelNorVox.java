package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNorVox extends ModelBase
{
  //fields
    ModelRenderer Main;
    ModelRenderer NoseBridge;
    ModelRenderer LeftEyeInner;
    ModelRenderer RightEyeInner;
    ModelRenderer HeadTop;
    ModelRenderer LeftEyeLower;
    ModelRenderer LeftEyeUpper;
    ModelRenderer LeftEyeOuter;
    ModelRenderer RightEyeLower;
    ModelRenderer RightEyeUpper;
    ModelRenderer RightEyeOuter;
    ModelRenderer NoseForehead;
    ModelRenderer HeadJaw;
    ModelRenderer NoseBottom;
    ModelRenderer LeftNostrilUpper;
    ModelRenderer RightNostrilUpper;
    ModelRenderer LeftNostrilOuter;
    ModelRenderer RightNostrilOuter;
    ModelRenderer Teeth;
    ModelRenderer LowerJaw;
    ModelRenderer BackJaw;
  
  public ModelNorVox()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Main = new ModelRenderer(this, 0, 0);
      Main.addBox(-7F, -4F, -5F, 14, 6, 7);
      Main.setRotationPoint(0F, 10F, 0F);
      Main.setTextureSize(64, 32);
      Main.mirror = true;
      setRotation(Main, 0F, 0F, 0F);
      NoseBridge = new ModelRenderer(this, 50, 0);
      NoseBridge.addBox(-1F, -3F, -6F, 2, 6, 1);
      NoseBridge.setRotationPoint(0F, 10F, 0F);
      NoseBridge.setTextureSize(64, 32);
      NoseBridge.mirror = true;
      setRotation(NoseBridge, 0F, 0F, 0F);
      LeftEyeInner = new ModelRenderer(this, 0, 20);
      LeftEyeInner.addBox(1F, -3F, -7F, 1, 4, 2);
      LeftEyeInner.setRotationPoint(0F, 10F, 0F);
      LeftEyeInner.setTextureSize(64, 32);
      LeftEyeInner.mirror = true;
      setRotation(LeftEyeInner, 0F, 0F, 0F);
      RightEyeInner = new ModelRenderer(this, 0, 19);
      RightEyeInner.addBox(-2F, -3F, -7F, 1, 4, 2);
      RightEyeInner.setRotationPoint(0F, 10F, 0F);
      RightEyeInner.setTextureSize(64, 32);
      RightEyeInner.mirror = true;
      setRotation(RightEyeInner, 0F, 0F, 0F);
      HeadTop = new ModelRenderer(this, 37, 8);
      HeadTop.addBox(-4F, -6F, -5F, 8, 2, 6);
      HeadTop.setRotationPoint(0F, 10F, 0F);
      HeadTop.setTextureSize(64, 32);
      HeadTop.mirror = true;
      setRotation(HeadTop, 0F, 0F, 0F);
      LeftEyeLower = new ModelRenderer(this, 0, 22);
      LeftEyeLower.addBox(2F, 0F, -7F, 4, 1, 2);
      LeftEyeLower.setRotationPoint(0F, 10F, 0F);
      LeftEyeLower.setTextureSize(64, 32);
      LeftEyeLower.mirror = true;
      setRotation(LeftEyeLower, 0F, 0F, 0F);
      LeftEyeUpper = new ModelRenderer(this, 0, 22);
      LeftEyeUpper.addBox(2F, -3F, -7F, 4, 1, 2);
      LeftEyeUpper.setRotationPoint(0F, 10F, 0F);
      LeftEyeUpper.setTextureSize(64, 32);
      LeftEyeUpper.mirror = true;
      setRotation(LeftEyeUpper, 0F, 0F, 0F);
      LeftEyeOuter = new ModelRenderer(this, 0, 21);
      LeftEyeOuter.addBox(5F, -2F, -7F, 1, 2, 2);
      LeftEyeOuter.setRotationPoint(0F, 10F, 0F);
      LeftEyeOuter.setTextureSize(64, 32);
      LeftEyeOuter.mirror = true;
      setRotation(LeftEyeOuter, 0F, 0F, 0F);
      RightEyeLower = new ModelRenderer(this, 0, 22);
      RightEyeLower.addBox(-6F, 0F, -7F, 4, 1, 2);
      RightEyeLower.setRotationPoint(0F, 10F, 0F);
      RightEyeLower.setTextureSize(64, 32);
      RightEyeLower.mirror = true;
      setRotation(RightEyeLower, 0F, 0F, 0F);
      RightEyeUpper = new ModelRenderer(this, 0, 22);
      RightEyeUpper.addBox(-6F, -3F, -7F, 4, 1, 2);
      RightEyeUpper.setRotationPoint(0F, 10F, 0F);
      RightEyeUpper.setTextureSize(64, 32);
      RightEyeUpper.mirror = true;
      setRotation(RightEyeUpper, 0F, 0F, 0F);
      RightEyeOuter = new ModelRenderer(this, 0, 20);
      RightEyeOuter.addBox(-6F, -2F, -7F, 1, 2, 2);
      RightEyeOuter.setRotationPoint(0F, 10F, 0F);
      RightEyeOuter.setTextureSize(64, 32);
      RightEyeOuter.mirror = true;
      setRotation(RightEyeOuter, 0F, 0F, 0F);
      NoseForehead = new ModelRenderer(this, 45, 0);
      NoseForehead.addBox(-3F, -5F, -6F, 6, 2, 1);
      NoseForehead.setRotationPoint(0F, 10F, 0F);
      NoseForehead.setTextureSize(64, 32);
      NoseForehead.mirror = true;
      setRotation(NoseForehead, 0F, 0F, 0F);
      HeadJaw = new ModelRenderer(this, 13, 16);
      HeadJaw.addBox(-4F, 2F, -5F, 8, 3, 6);
      HeadJaw.setRotationPoint(0F, 10F, 0F);
      HeadJaw.setTextureSize(64, 32);
      HeadJaw.mirror = true;
      setRotation(HeadJaw, 0F, 0F, 0F);
      NoseBottom = new ModelRenderer(this, 45, 0);
      NoseBottom.addBox(-3F, 3F, -6F, 6, 1, 1);
      NoseBottom.setRotationPoint(0F, 10F, 0F);
      NoseBottom.setTextureSize(64, 32);
      NoseBottom.mirror = true;
      setRotation(NoseBottom, 0F, 0F, 0F);
      LeftNostrilUpper = new ModelRenderer(this, 48, 0);
      LeftNostrilUpper.addBox(1F, 1F, -6F, 2, 1, 1);
      LeftNostrilUpper.setRotationPoint(0F, 10F, 0F);
      LeftNostrilUpper.setTextureSize(64, 32);
      LeftNostrilUpper.mirror = true;
      setRotation(LeftNostrilUpper, 0F, 0F, 0F);
      RightNostrilUpper = new ModelRenderer(this, 50, 0);
      RightNostrilUpper.addBox(-3F, 1F, -6F, 2, 1, 1);
      RightNostrilUpper.setRotationPoint(0F, 10F, 0F);
      RightNostrilUpper.setTextureSize(64, 32);
      RightNostrilUpper.mirror = true;
      setRotation(RightNostrilUpper, 0F, 0F, 0F);
      LeftNostrilOuter = new ModelRenderer(this, 51, 0);
      LeftNostrilOuter.addBox(2F, 2F, -6F, 1, 1, 1);
      LeftNostrilOuter.setRotationPoint(0F, 10F, 0F);
      LeftNostrilOuter.setTextureSize(64, 32);
      LeftNostrilOuter.mirror = true;
      setRotation(LeftNostrilOuter, 0F, 0F, 0F);
      RightNostrilOuter = new ModelRenderer(this, 50, 0);
      RightNostrilOuter.addBox(-3F, 2F, -6F, 1, 1, 1);
      RightNostrilOuter.setRotationPoint(0F, 10F, 0F);
      RightNostrilOuter.setTextureSize(64, 32);
      RightNostrilOuter.mirror = true;
      setRotation(RightNostrilOuter, 0F, 0F, 0F);
      Teeth = new ModelRenderer(this, 23, 28);
      Teeth.addBox(-3F, 5F, -4F, 6, 2, 1);
      Teeth.setRotationPoint(0F, 10F, 0F);
      Teeth.setTextureSize(64, 32);
      Teeth.mirror = true;
      setRotation(Teeth, 0F, 0F, 0F);
      LowerJaw = new ModelRenderer(this, 42, 24);
      LowerJaw.addBox(-3F, 7F, -5F, 6, 2, 4);
      LowerJaw.setRotationPoint(0F, 10F, 0F);
      LowerJaw.setTextureSize(64, 32);
      LowerJaw.mirror = true;
      setRotation(LowerJaw, 0F, 0F, 0F);
      BackJaw = new ModelRenderer(this, 42, 18);
      BackJaw.addBox(-4F, 5F, -3F, 8, 2, 3);
      BackJaw.setRotationPoint(0F, 10F, 0F);
      BackJaw.setTextureSize(64, 32);
      BackJaw.mirror = true;
      setRotation(BackJaw, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Main.render(f5);
    NoseBridge.render(f5);
    LeftEyeInner.render(f5);
    RightEyeInner.render(f5);
    HeadTop.render(f5);
    LeftEyeLower.render(f5);
    LeftEyeUpper.render(f5);
    LeftEyeOuter.render(f5);
    RightEyeLower.render(f5);
    RightEyeUpper.render(f5);
    RightEyeOuter.render(f5);
    NoseForehead.render(f5);
    HeadJaw.render(f5);
    NoseBottom.render(f5);
    LeftNostrilUpper.render(f5);
    RightNostrilUpper.render(f5);
    LeftNostrilOuter.render(f5);
    RightNostrilOuter.render(f5);
    Teeth.render(f5);
    LowerJaw.render(f5);
    BackJaw.render(f5);
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
