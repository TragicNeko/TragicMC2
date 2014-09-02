package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVoxStellarum extends ModelBase
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
    ModelRenderer LowerJaw;
    ModelRenderer BackJaw;
    ModelRenderer Main2;
    ModelRenderer HeadTop2;
    ModelRenderer HeadTop3;
    ModelRenderer Main3;
    ModelRenderer HeadJaw2;
    ModelRenderer HeadTop4;
    ModelRenderer NoseForehead2;
    ModelRenderer NoseBridge2;
    ModelRenderer NoseForehead3;
    ModelRenderer NoseBridge3;
    ModelRenderer NoseForehead4;
    ModelRenderer NoseBridge4;
    ModelRenderer LeftEyeInner3;
    ModelRenderer RightEyeInner3;
    ModelRenderer LeftEyeOuter3;
    ModelRenderer RightEyeOuter3;
    ModelRenderer LeftEyeUpper3;
    ModelRenderer RightEyeUpper3;
    ModelRenderer RightNostrilUpper3;
    ModelRenderer LeftNostrilUpper3;
    ModelRenderer RightNostrilOuter3;
    ModelRenderer LeftNostrilOuter3;
    ModelRenderer NoseBottom3;
    ModelRenderer BackJaw3;
    ModelRenderer LowerJaw3;
    ModelRenderer Teeth3;
    ModelRenderer Expression3;
    ModelRenderer RightEyeInner2;
    ModelRenderer LeftEyeInner2;
    ModelRenderer RightEyeUpper2;
    ModelRenderer RightEyeLower2;
    ModelRenderer RightEyeOuter2;
    ModelRenderer LeftEyeUpper2;
    ModelRenderer LeftEyeLower2;
    ModelRenderer LeftEyeOuter2;
    ModelRenderer RightNostril2;
    ModelRenderer LeftNostril2;
    ModelRenderer NoseBottom2;
    ModelRenderer LeftNostrilOuter2;
    ModelRenderer RightNostrilOuter2;
    ModelRenderer BackJaw2;
    ModelRenderer LowerJaw2;
    ModelRenderer Teeth2;
    ModelRenderer LeftEyeInner4;
    ModelRenderer RightEyeInner4;
    ModelRenderer LeftEyeOuter4;
    ModelRenderer RightEyeOuter4;
    ModelRenderer LeftEyeLower4;
    ModelRenderer RightEyeLower4;
    ModelRenderer LeftNostrilUpper4;
    ModelRenderer RightNostrilUpper4;
    ModelRenderer RightNostrilOuter4;
    ModelRenderer LeftNostrilOuter4;
    ModelRenderer NoseBottom4;
    ModelRenderer BackJaw4;
    ModelRenderer LowerJaw4;
    ModelRenderer Teeth4;
    ModelRenderer Expression4;
  
  public ModelVoxStellarum()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Main = new ModelRenderer(this, 64, 0);
      Main.addBox(-7F, -4F, -5F, 14, 6, 18);
      Main.setRotationPoint(0F, 10F, -4F);
      Main.setTextureSize(128, 64);
      Main.mirror = true;
      setRotation(Main, 0F, 0F, 0F);
      NoseBridge = new ModelRenderer(this, 50, 0);
      NoseBridge.addBox(-1F, -3F, -6F, 2, 6, 1);
      NoseBridge.setRotationPoint(0F, 10F, -4F);
      NoseBridge.setTextureSize(128, 64);
      NoseBridge.mirror = true;
      setRotation(NoseBridge, 0F, 0F, 0F);
      LeftEyeInner = new ModelRenderer(this, 0, 20);
      LeftEyeInner.addBox(1F, -3F, -7F, 1, 4, 2);
      LeftEyeInner.setRotationPoint(0F, 10F, -4F);
      LeftEyeInner.setTextureSize(128, 64);
      LeftEyeInner.mirror = true;
      setRotation(LeftEyeInner, 0F, 0F, 0F);
      RightEyeInner = new ModelRenderer(this, 0, 19);
      RightEyeInner.addBox(-2F, -3F, -7F, 1, 4, 2);
      RightEyeInner.setRotationPoint(0F, 10F, -4F);
      RightEyeInner.setTextureSize(128, 64);
      RightEyeInner.mirror = true;
      setRotation(RightEyeInner, 0F, 0F, 0F);
      HeadTop = new ModelRenderer(this, 36, 40);
      HeadTop.addBox(-4F, -6F, -5F, 8, 2, 6);
      HeadTop.setRotationPoint(0F, 10F, -4F);
      HeadTop.setTextureSize(128, 64);
      HeadTop.mirror = true;
      setRotation(HeadTop, 0F, 0F, 0F);
      LeftEyeLower = new ModelRenderer(this, 0, 22);
      LeftEyeLower.addBox(2F, 0F, -7F, 4, 1, 2);
      LeftEyeLower.setRotationPoint(0F, 10F, -4F);
      LeftEyeLower.setTextureSize(128, 64);
      LeftEyeLower.mirror = true;
      setRotation(LeftEyeLower, 0F, 0F, 0F);
      LeftEyeUpper = new ModelRenderer(this, 0, 22);
      LeftEyeUpper.addBox(2F, -3F, -7F, 4, 1, 2);
      LeftEyeUpper.setRotationPoint(0F, 10F, -4F);
      LeftEyeUpper.setTextureSize(128, 64);
      LeftEyeUpper.mirror = true;
      setRotation(LeftEyeUpper, 0F, 0F, 0F);
      LeftEyeOuter = new ModelRenderer(this, 0, 21);
      LeftEyeOuter.addBox(5F, -2F, -7F, 1, 2, 2);
      LeftEyeOuter.setRotationPoint(0F, 10F, -4F);
      LeftEyeOuter.setTextureSize(128, 64);
      LeftEyeOuter.mirror = true;
      setRotation(LeftEyeOuter, 0F, 0F, 0F);
      RightEyeLower = new ModelRenderer(this, 0, 22);
      RightEyeLower.addBox(-6F, 0F, -7F, 4, 1, 2);
      RightEyeLower.setRotationPoint(0F, 10F, -4F);
      RightEyeLower.setTextureSize(128, 64);
      RightEyeLower.mirror = true;
      setRotation(RightEyeLower, 0F, 0F, 0F);
      RightEyeUpper = new ModelRenderer(this, 0, 22);
      RightEyeUpper.addBox(-6F, -3F, -7F, 4, 1, 2);
      RightEyeUpper.setRotationPoint(0F, 10F, -4F);
      RightEyeUpper.setTextureSize(128, 64);
      RightEyeUpper.mirror = true;
      setRotation(RightEyeUpper, 0F, 0F, 0F);
      RightEyeOuter = new ModelRenderer(this, 0, 20);
      RightEyeOuter.addBox(-6F, -2F, -7F, 1, 2, 2);
      RightEyeOuter.setRotationPoint(0F, 10F, -4F);
      RightEyeOuter.setTextureSize(128, 64);
      RightEyeOuter.mirror = true;
      setRotation(RightEyeOuter, 0F, 0F, 0F);
      NoseForehead = new ModelRenderer(this, 45, 0);
      NoseForehead.addBox(-3F, -5F, -6F, 6, 2, 1);
      NoseForehead.setRotationPoint(0F, 10F, -4F);
      NoseForehead.setTextureSize(128, 64);
      NoseForehead.mirror = true;
      setRotation(NoseForehead, 0F, 0F, 0F);
      HeadJaw = new ModelRenderer(this, 13, 18);
      HeadJaw.addBox(-4F, 2F, -5F, 8, 3, 18);
      HeadJaw.setRotationPoint(0F, 10F, -4F);
      HeadJaw.setTextureSize(128, 64);
      HeadJaw.mirror = true;
      setRotation(HeadJaw, 0F, 0F, 0F);
      NoseBottom = new ModelRenderer(this, 45, 0);
      NoseBottom.addBox(-3F, 3F, -6F, 6, 1, 1);
      NoseBottom.setRotationPoint(0F, 10F, -4F);
      NoseBottom.setTextureSize(128, 64);
      NoseBottom.mirror = true;
      setRotation(NoseBottom, 0F, 0F, 0F);
      LeftNostrilUpper = new ModelRenderer(this, 48, 0);
      LeftNostrilUpper.addBox(1F, 1F, -6F, 2, 1, 1);
      LeftNostrilUpper.setRotationPoint(0F, 10F, -4F);
      LeftNostrilUpper.setTextureSize(128, 64);
      LeftNostrilUpper.mirror = true;
      setRotation(LeftNostrilUpper, 0F, 0F, 0F);
      RightNostrilUpper = new ModelRenderer(this, 50, 0);
      RightNostrilUpper.addBox(-3F, 1F, -6F, 2, 1, 1);
      RightNostrilUpper.setRotationPoint(0F, 10F, -4F);
      RightNostrilUpper.setTextureSize(128, 64);
      RightNostrilUpper.mirror = true;
      setRotation(RightNostrilUpper, 0F, 0F, 0F);
      LeftNostrilOuter = new ModelRenderer(this, 51, 0);
      LeftNostrilOuter.addBox(2F, 2F, -6F, 1, 1, 1);
      LeftNostrilOuter.setRotationPoint(0F, 10F, -4F);
      LeftNostrilOuter.setTextureSize(128, 64);
      LeftNostrilOuter.mirror = true;
      setRotation(LeftNostrilOuter, 0F, 0F, 0F);
      RightNostrilOuter = new ModelRenderer(this, 50, 0);
      RightNostrilOuter.addBox(-3F, 2F, -6F, 1, 1, 1);
      RightNostrilOuter.setRotationPoint(0F, 10F, -4F);
      RightNostrilOuter.setTextureSize(128, 64);
      RightNostrilOuter.mirror = true;
      setRotation(RightNostrilOuter, 0F, 0F, 0F);
      LowerJaw = new ModelRenderer(this, 42, 24);
      LowerJaw.addBox(-3F, 5F, -5F, 6, 2, 4);
      LowerJaw.setRotationPoint(0F, 11F, -3F);
      LowerJaw.setTextureSize(128, 64);
      LowerJaw.mirror = true;
      setRotation(LowerJaw, 0F, 0F, 0F);
      BackJaw = new ModelRenderer(this, 42, 18);
      BackJaw.addBox(-4F, 5F, -3F, 8, 2, 3);
      BackJaw.setRotationPoint(0F, 10F, -3F);
      BackJaw.setTextureSize(128, 64);
      BackJaw.mirror = true;
      setRotation(BackJaw, 0F, 0F, 0F);
      Main2 = new ModelRenderer(this, 64, 0);
      Main2.addBox(-7F, -4F, -5F, 18, 6, 14);
      Main2.setRotationPoint(-2F, 10F, -2F);
      Main2.setTextureSize(128, 64);
      Main2.mirror = true;
      setRotation(Main2, 0F, 0F, 0F);
      HeadTop2 = new ModelRenderer(this, 36, 40);
      HeadTop2.addBox(-7F, -6F, -2F, 6, 2, 8);
      HeadTop2.setRotationPoint(-2F, 10F, -2F);
      HeadTop2.setTextureSize(128, 64);
      HeadTop2.mirror = true;
      setRotation(HeadTop2, 0F, 0F, 0F);
      HeadTop3 = new ModelRenderer(this, 36, 40);
      HeadTop3.addBox(-4F, -6F, -4F, 6, 2, 8);
      HeadTop3.setRotationPoint(7F, 10F, 0F);
      HeadTop3.setTextureSize(128, 64);
      HeadTop3.mirror = true;
      setRotation(HeadTop3, 0F, 0F, 0F);
      Main3 = new ModelRenderer(this, 0, 0);
      Main3.addBox(-7F, -4F, -3F, 14, 6, 7);
      Main3.setRotationPoint(0F, 10F, 5F);
      Main3.setTextureSize(128, 64);
      Main3.mirror = true;
      setRotation(Main3, 0F, 0F, 0F);
      HeadJaw2 = new ModelRenderer(this, 13, 24);
      HeadJaw2.addBox(-8F, 2F, -3F, 18, 3, 8);
      HeadJaw2.setRotationPoint(-1F, 10F, -1F);
      HeadJaw2.setTextureSize(128, 64);
      HeadJaw2.mirror = true;
      setRotation(HeadJaw2, 0F, 0F, 0F);
      HeadTop4 = new ModelRenderer(this, 36, 40);
      HeadTop4.addBox(-4F, -6F, 3F, 8, 2, 6);
      HeadTop4.setRotationPoint(0F, 10F, 0F);
      HeadTop4.setTextureSize(128, 64);
      HeadTop4.mirror = true;
      setRotation(HeadTop4, 0F, 0F, 0F);
      NoseForehead2 = new ModelRenderer(this, 45, 0);
      NoseForehead2.addBox(-10F, -5F, -3F, 1, 2, 6);
      NoseForehead2.setRotationPoint(0F, 10F, 0F);
      NoseForehead2.setTextureSize(128, 64);
      NoseForehead2.mirror = true;
      setRotation(NoseForehead2, 0F, 0F, 0F);
      NoseBridge2 = new ModelRenderer(this, 50, 0);
      NoseBridge2.addBox(-10F, -3F, -1F, 1, 6, 2);
      NoseBridge2.setRotationPoint(0F, 10F, 0F);
      NoseBridge2.setTextureSize(128, 64);
      NoseBridge2.mirror = true;
      setRotation(NoseBridge2, 0F, 0F, 0F);
      NoseForehead3 = new ModelRenderer(this, 45, 0);
      NoseForehead3.addBox(9F, -5F, 0F, 1, 2, 6);
      NoseForehead3.setRotationPoint(0F, 10F, -3F);
      NoseForehead3.setTextureSize(128, 64);
      NoseForehead3.mirror = true;
      setRotation(NoseForehead3, 0F, 0F, 0F);
      NoseBridge3 = new ModelRenderer(this, 50, 0);
      NoseBridge3.addBox(9F, -3F, 0F, 1, 6, 2);
      NoseBridge3.setRotationPoint(0F, 10F, -1F);
      NoseBridge3.setTextureSize(128, 64);
      NoseBridge3.mirror = true;
      setRotation(NoseBridge3, 0F, 0F, 0F);
      NoseForehead4 = new ModelRenderer(this, 45, 0);
      NoseForehead4.addBox(0F, -5F, 9F, 6, 2, 1);
      NoseForehead4.setRotationPoint(-3F, 10F, 0F);
      NoseForehead4.setTextureSize(128, 64);
      NoseForehead4.mirror = true;
      setRotation(NoseForehead4, 0F, 0F, 0F);
      NoseBridge4 = new ModelRenderer(this, 50, 0);
      NoseBridge4.addBox(-1F, -3F, 9F, 2, 6, 1);
      NoseBridge4.setRotationPoint(0F, 10F, 0F);
      NoseBridge4.setTextureSize(128, 64);
      NoseBridge4.mirror = true;
      setRotation(NoseBridge4, 0F, 0F, 0F);
      LeftEyeInner3 = new ModelRenderer(this, 46, 0);
      LeftEyeInner3.addBox(9F, -2F, -2F, 2, 2, 1);
      LeftEyeInner3.setRotationPoint(0F, 10F, 0F);
      LeftEyeInner3.setTextureSize(128, 64);
      LeftEyeInner3.mirror = true;
      setRotation(LeftEyeInner3, 0F, 0F, 0F);
      RightEyeInner3 = new ModelRenderer(this, 46, 0);
      RightEyeInner3.addBox(9F, -2F, 1F, 2, 2, 1);
      RightEyeInner3.setRotationPoint(0F, 10F, 0F);
      RightEyeInner3.setTextureSize(128, 64);
      RightEyeInner3.mirror = true;
      setRotation(RightEyeInner3, 0F, 0F, 0F);
      LeftEyeOuter3 = new ModelRenderer(this, 46, 0);
      LeftEyeOuter3.addBox(9F, -2F, -6F, 2, 2, 1);
      LeftEyeOuter3.setRotationPoint(0F, 10F, 0F);
      LeftEyeOuter3.setTextureSize(128, 64);
      LeftEyeOuter3.mirror = true;
      setRotation(LeftEyeOuter3, 0F, 0F, 0F);
      RightEyeOuter3 = new ModelRenderer(this, 46, 0);
      RightEyeOuter3.addBox(9F, -2F, 5F, 2, 2, 1);
      RightEyeOuter3.setRotationPoint(0F, 10F, 0F);
      RightEyeOuter3.setTextureSize(128, 64);
      RightEyeOuter3.mirror = true;
      setRotation(RightEyeOuter3, 0F, 0F, 0F);
      LeftEyeUpper3 = new ModelRenderer(this, 46, 0);
      LeftEyeUpper3.addBox(9F, -3F, -5F, 2, 1, 3);
      LeftEyeUpper3.setRotationPoint(0F, 10F, 0F);
      LeftEyeUpper3.setTextureSize(128, 64);
      LeftEyeUpper3.mirror = true;
      setRotation(LeftEyeUpper3, 0F, 0F, 0F);
      RightEyeUpper3 = new ModelRenderer(this, 46, 0);
      RightEyeUpper3.addBox(9F, -3F, 2F, 2, 1, 3);
      RightEyeUpper3.setRotationPoint(0F, 10F, 0F);
      RightEyeUpper3.setTextureSize(128, 64);
      RightEyeUpper3.mirror = true;
      setRotation(RightEyeUpper3, 0F, 0F, 0F);
      RightNostrilUpper3 = new ModelRenderer(this, 46, 0);
      RightNostrilUpper3.addBox(9F, 1F, 1F, 1, 1, 2);
      RightNostrilUpper3.setRotationPoint(0F, 10F, 0F);
      RightNostrilUpper3.setTextureSize(128, 64);
      RightNostrilUpper3.mirror = true;
      setRotation(RightNostrilUpper3, 0F, 0F, 0F);
      LeftNostrilUpper3 = new ModelRenderer(this, 46, 0);
      LeftNostrilUpper3.addBox(9F, 1F, -3F, 1, 1, 2);
      LeftNostrilUpper3.setRotationPoint(0F, 10F, 0F);
      LeftNostrilUpper3.setTextureSize(128, 64);
      LeftNostrilUpper3.mirror = true;
      setRotation(LeftNostrilUpper3, 0F, 0F, 0F);
      RightNostrilOuter3 = new ModelRenderer(this, 46, 0);
      RightNostrilOuter3.addBox(9F, 2F, 2F, 1, 1, 1);
      RightNostrilOuter3.setRotationPoint(0F, 10F, 0F);
      RightNostrilOuter3.setTextureSize(128, 64);
      RightNostrilOuter3.mirror = true;
      setRotation(RightNostrilOuter3, 0F, 0F, 0F);
      LeftNostrilOuter3 = new ModelRenderer(this, 46, 0);
      LeftNostrilOuter3.addBox(9F, 2F, -3F, 1, 1, 1);
      LeftNostrilOuter3.setRotationPoint(0F, 10F, 0F);
      LeftNostrilOuter3.setTextureSize(128, 64);
      LeftNostrilOuter3.mirror = true;
      setRotation(LeftNostrilOuter3, 0F, 0F, 0F);
      NoseBottom3 = new ModelRenderer(this, 45, 0);
      NoseBottom3.addBox(9F, 3F, -3F, 1, 1, 6);
      NoseBottom3.setRotationPoint(0F, 10F, 0F);
      NoseBottom3.setTextureSize(128, 64);
      NoseBottom3.mirror = true;
      setRotation(NoseBottom3, 0F, 0F, 0F);
      BackJaw3 = new ModelRenderer(this, 42, 18);
      BackJaw3.addBox(3F, 5F, -4F, 3, 2, 8);
      BackJaw3.setRotationPoint(0F, 10F, 0F);
      BackJaw3.setTextureSize(128, 64);
      BackJaw3.mirror = true;
      setRotation(BackJaw3, 0F, 0F, 0F);
      LowerJaw3 = new ModelRenderer(this, 42, 24);
      LowerJaw3.addBox(4F, 7F, -3F, 4, 2, 6);
      LowerJaw3.setRotationPoint(0F, 10F, 0F);
      LowerJaw3.setTextureSize(128, 64);
      LowerJaw3.mirror = true;
      setRotation(LowerJaw3, 0F, 0F, 0F);
      Teeth3 = new ModelRenderer(this, 72, 36);
      Teeth3.addBox(6F, 5F, -3F, 1, 2, 6);
      Teeth3.setRotationPoint(0F, 10F, 0F);
      Teeth3.setTextureSize(128, 64);
      Teeth3.mirror = true;
      setRotation(Teeth3, 0F, 0F, 0F);
      Expression3 = new ModelRenderer(this, 46, 21);
      Expression3.addBox(7F, 5F, -2F, 1, 1, 4);
      Expression3.setRotationPoint(0F, 10F, 0F);
      Expression3.setTextureSize(128, 64);
      Expression3.mirror = true;
      setRotation(Expression3, 0F, 0F, 0F);
      RightEyeInner2 = new ModelRenderer(this, 46, 0);
      RightEyeInner2.addBox(-11F, -1F, 1F, 2, 2, 1);
      RightEyeInner2.setRotationPoint(0F, 10F, 0F);
      RightEyeInner2.setTextureSize(128, 64);
      RightEyeInner2.mirror = true;
      setRotation(RightEyeInner2, 0F, 0F, 0F);
      LeftEyeInner2 = new ModelRenderer(this, 46, 0);
      LeftEyeInner2.addBox(-11F, -1F, -2F, 2, 2, 1);
      LeftEyeInner2.setRotationPoint(0F, 10F, 0F);
      LeftEyeInner2.setTextureSize(128, 64);
      LeftEyeInner2.mirror = true;
      setRotation(LeftEyeInner2, 0F, 0F, 0F);
      RightEyeUpper2 = new ModelRenderer(this, 45, 0);
      RightEyeUpper2.addBox(-11F, -2F, 2F, 2, 1, 4);
      RightEyeUpper2.setRotationPoint(0F, 10F, 0F);
      RightEyeUpper2.setTextureSize(128, 64);
      RightEyeUpper2.mirror = true;
      setRotation(RightEyeUpper2, 0F, 0F, 0F);
      RightEyeLower2 = new ModelRenderer(this, 45, 0);
      RightEyeLower2.addBox(-11F, 0F, 2F, 2, 1, 3);
      RightEyeLower2.setRotationPoint(0F, 10F, 0F);
      RightEyeLower2.setTextureSize(128, 64);
      RightEyeLower2.mirror = true;
      setRotation(RightEyeLower2, 0F, 0F, 0F);
      RightEyeOuter2 = new ModelRenderer(this, 45, 0);
      RightEyeOuter2.addBox(-11F, -1F, 4F, 2, 1, 1);
      RightEyeOuter2.setRotationPoint(0F, 10F, 0F);
      RightEyeOuter2.setTextureSize(128, 64);
      RightEyeOuter2.mirror = true;
      setRotation(RightEyeOuter2, 0F, 0F, 0F);
      LeftEyeUpper2 = new ModelRenderer(this, 45, 0);
      LeftEyeUpper2.addBox(-11F, -2F, -6F, 2, 1, 4);
      LeftEyeUpper2.setRotationPoint(0F, 10F, 0F);
      LeftEyeUpper2.setTextureSize(128, 64);
      LeftEyeUpper2.mirror = true;
      setRotation(LeftEyeUpper2, 0F, 0F, 0F);
      LeftEyeLower2 = new ModelRenderer(this, 45, 0);
      LeftEyeLower2.addBox(-11F, 0F, -5F, 2, 1, 3);
      LeftEyeLower2.setRotationPoint(0F, 10F, 0F);
      LeftEyeLower2.setTextureSize(128, 64);
      LeftEyeLower2.mirror = true;
      setRotation(LeftEyeLower2, 0F, 0F, 0F);
      LeftEyeOuter2 = new ModelRenderer(this, 45, 0);
      LeftEyeOuter2.addBox(-11F, -1F, -5F, 2, 1, 1);
      LeftEyeOuter2.setRotationPoint(0F, 10F, 0F);
      LeftEyeOuter2.setTextureSize(128, 64);
      LeftEyeOuter2.mirror = true;
      setRotation(LeftEyeOuter2, 0F, 0F, 0F);
      RightNostril2 = new ModelRenderer(this, 45, 0);
      RightNostril2.addBox(-10F, 1F, 1F, 1, 1, 2);
      RightNostril2.setRotationPoint(0F, 10F, 0F);
      RightNostril2.setTextureSize(128, 64);
      RightNostril2.mirror = true;
      setRotation(RightNostril2, 0F, 0F, 0F);
      LeftNostril2 = new ModelRenderer(this, 45, 0);
      LeftNostril2.addBox(-10F, 1F, -3F, 1, 1, 2);
      LeftNostril2.setRotationPoint(0F, 10F, 0F);
      LeftNostril2.setTextureSize(128, 64);
      LeftNostril2.mirror = true;
      setRotation(LeftNostril2, 0F, 0F, 0F);
      NoseBottom2 = new ModelRenderer(this, 45, 0);
      NoseBottom2.addBox(-10F, 3F, -3F, 1, 1, 6);
      NoseBottom2.setRotationPoint(0F, 10F, 0F);
      NoseBottom2.setTextureSize(128, 64);
      NoseBottom2.mirror = true;
      setRotation(NoseBottom2, 0F, 0F, 0F);
      LeftNostrilOuter2 = new ModelRenderer(this, 45, 0);
      LeftNostrilOuter2.addBox(-10F, 2F, -3F, 1, 1, 1);
      LeftNostrilOuter2.setRotationPoint(0F, 10F, 0F);
      LeftNostrilOuter2.setTextureSize(128, 64);
      LeftNostrilOuter2.mirror = true;
      setRotation(LeftNostrilOuter2, 0F, 0F, 0F);
      RightNostrilOuter2 = new ModelRenderer(this, 45, 0);
      RightNostrilOuter2.addBox(-10F, 2F, 2F, 1, 1, 1);
      RightNostrilOuter2.setRotationPoint(0F, 10F, 0F);
      RightNostrilOuter2.setTextureSize(128, 64);
      RightNostrilOuter2.mirror = true;
      setRotation(RightNostrilOuter2, 0F, 0F, 0F);
      BackJaw2 = new ModelRenderer(this, 42, 18);
      BackJaw2.addBox(-6F, 5F, -4F, 3, 2, 8);
      BackJaw2.setRotationPoint(0F, 10F, 0F);
      BackJaw2.setTextureSize(128, 64);
      BackJaw2.mirror = true;
      setRotation(BackJaw2, 0F, 0F, 0F);
      LowerJaw2 = new ModelRenderer(this, 42, 24);
      LowerJaw2.addBox(-8F, 7F, -3F, 4, 2, 6);
      LowerJaw2.setRotationPoint(0F, 10F, 0F);
      LowerJaw2.setTextureSize(128, 64);
      LowerJaw2.mirror = true;
      setRotation(LowerJaw2, 0F, 0F, 0F);
      Teeth2 = new ModelRenderer(this, 72, 36);
      Teeth2.addBox(-7F, 5F, -3F, 1, 2, 6);
      Teeth2.setRotationPoint(0F, 10F, 0F);
      Teeth2.setTextureSize(128, 64);
      Teeth2.mirror = true;
      setRotation(Teeth2, 0F, 0F, 0F);
      LeftEyeInner4 = new ModelRenderer(this, 45, 0);
      LeftEyeInner4.addBox(-2F, -2F, 9F, 1, 2, 2);
      LeftEyeInner4.setRotationPoint(0F, 10F, 0F);
      LeftEyeInner4.setTextureSize(128, 64);
      LeftEyeInner4.mirror = true;
      setRotation(LeftEyeInner4, 0F, 0F, 0F);
      RightEyeInner4 = new ModelRenderer(this, 45, 0);
      RightEyeInner4.addBox(1F, -2F, 9F, 1, 2, 2);
      RightEyeInner4.setRotationPoint(0F, 10F, 0F);
      RightEyeInner4.setTextureSize(128, 64);
      RightEyeInner4.mirror = true;
      setRotation(RightEyeInner4, 0F, 0F, 0F);
      LeftEyeOuter4 = new ModelRenderer(this, 45, 0);
      LeftEyeOuter4.addBox(-5F, -2F, 9F, 1, 2, 2);
      LeftEyeOuter4.setRotationPoint(0F, 10F, 0F);
      LeftEyeOuter4.setTextureSize(128, 64);
      LeftEyeOuter4.mirror = true;
      setRotation(LeftEyeOuter4, 0F, 0F, 0F);
      RightEyeOuter4 = new ModelRenderer(this, 45, 0);
      RightEyeOuter4.addBox(4F, -2F, 9F, 1, 2, 2);
      RightEyeOuter4.setRotationPoint(0F, 10F, 0F);
      RightEyeOuter4.setTextureSize(128, 64);
      RightEyeOuter4.mirror = true;
      setRotation(RightEyeOuter4, 0F, 0F, 0F);
      LeftEyeLower4 = new ModelRenderer(this, 45, 0);
      LeftEyeLower4.addBox(-4F, 0F, 9F, 2, 1, 2);
      LeftEyeLower4.setRotationPoint(0F, 10F, 0F);
      LeftEyeLower4.setTextureSize(128, 64);
      LeftEyeLower4.mirror = true;
      setRotation(LeftEyeLower4, 0F, 0F, 0F);
      RightEyeLower4 = new ModelRenderer(this, 45, 0);
      RightEyeLower4.addBox(2F, 0F, 9F, 2, 1, 2);
      RightEyeLower4.setRotationPoint(0F, 10F, 0F);
      RightEyeLower4.setTextureSize(128, 64);
      RightEyeLower4.mirror = true;
      setRotation(RightEyeLower4, 0F, 0F, 0F);
      LeftNostrilUpper4 = new ModelRenderer(this, 45, 0);
      LeftNostrilUpper4.addBox(-3F, 1F, 9F, 2, 1, 1);
      LeftNostrilUpper4.setRotationPoint(0F, 10F, 0F);
      LeftNostrilUpper4.setTextureSize(128, 64);
      LeftNostrilUpper4.mirror = true;
      setRotation(LeftNostrilUpper4, 0F, 0F, 0F);
      RightNostrilUpper4 = new ModelRenderer(this, 45, 0);
      RightNostrilUpper4.addBox(1F, 1F, 9F, 2, 1, 1);
      RightNostrilUpper4.setRotationPoint(0F, 10F, 0F);
      RightNostrilUpper4.setTextureSize(128, 64);
      RightNostrilUpper4.mirror = true;
      setRotation(RightNostrilUpper4, 0F, 0F, 0F);
      RightNostrilOuter4 = new ModelRenderer(this, 45, 0);
      RightNostrilOuter4.addBox(2F, 2F, 9F, 1, 1, 1);
      RightNostrilOuter4.setRotationPoint(0F, 10F, 0F);
      RightNostrilOuter4.setTextureSize(128, 64);
      RightNostrilOuter4.mirror = true;
      setRotation(RightNostrilOuter4, 0F, 0F, 0F);
      LeftNostrilOuter4 = new ModelRenderer(this, 45, 0);
      LeftNostrilOuter4.addBox(0F, 2F, 9F, 1, 1, 1);
      LeftNostrilOuter4.setRotationPoint(-3F, 10F, 0F);
      LeftNostrilOuter4.setTextureSize(128, 64);
      LeftNostrilOuter4.mirror = true;
      setRotation(LeftNostrilOuter4, 0F, 0F, 0F);
      NoseBottom4 = new ModelRenderer(this, 45, 0);
      NoseBottom4.addBox(-3F, 3F, 9F, 6, 1, 1);
      NoseBottom4.setRotationPoint(0F, 10F, 0F);
      NoseBottom4.setTextureSize(128, 64);
      NoseBottom4.mirror = true;
      setRotation(NoseBottom4, 0F, 0F, 0F);
      BackJaw4 = new ModelRenderer(this, 42, 18);
      BackJaw4.addBox(-4F, 5F, 3F, 8, 2, 3);
      BackJaw4.setRotationPoint(0F, 10F, 0F);
      BackJaw4.setTextureSize(128, 64);
      BackJaw4.mirror = true;
      setRotation(BackJaw4, 0F, 0F, 0F);
      LowerJaw4 = new ModelRenderer(this, 42, 24);
      LowerJaw4.addBox(-3F, 7F, 4F, 6, 2, 4);
      LowerJaw4.setRotationPoint(0F, 10F, 0F);
      LowerJaw4.setTextureSize(128, 64);
      LowerJaw4.mirror = true;
      setRotation(LowerJaw4, 0F, 0F, 0F);
      Teeth4 = new ModelRenderer(this, 72, 36);
      Teeth4.addBox(-3F, 5F, 6F, 6, 2, 1);
      Teeth4.setRotationPoint(0F, 10F, 0F);
      Teeth4.setTextureSize(128, 64);
      Teeth4.mirror = true;
      setRotation(Teeth4, 0F, 0F, 0F);
      Expression4 = new ModelRenderer(this, 46, 21);
      Expression4.addBox(-2F, 6F, 7F, 4, 1, 1);
      Expression4.setRotationPoint(0F, 10F, 0F);
      Expression4.setTextureSize(128, 64);
      Expression4.mirror = true;
      setRotation(Expression4, 0F, 0F, 0F);
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
    LowerJaw.render(f5);
    BackJaw.render(f5);
    Main2.render(f5);
    HeadTop2.render(f5);
    HeadTop3.render(f5);
    Main3.render(f5);
    HeadJaw2.render(f5);
    HeadTop4.render(f5);
    NoseForehead2.render(f5);
    NoseBridge2.render(f5);
    NoseForehead3.render(f5);
    NoseBridge3.render(f5);
    NoseForehead4.render(f5);
    NoseBridge4.render(f5);
    LeftEyeInner3.render(f5);
    RightEyeInner3.render(f5);
    LeftEyeOuter3.render(f5);
    RightEyeOuter3.render(f5);
    LeftEyeUpper3.render(f5);
    RightEyeUpper3.render(f5);
    RightNostrilUpper3.render(f5);
    LeftNostrilUpper3.render(f5);
    RightNostrilOuter3.render(f5);
    LeftNostrilOuter3.render(f5);
    NoseBottom3.render(f5);
    BackJaw3.render(f5);
    LowerJaw3.render(f5);
    Teeth3.render(f5);
    Expression3.render(f5);
    RightEyeInner2.render(f5);
    LeftEyeInner2.render(f5);
    RightEyeUpper2.render(f5);
    RightEyeLower2.render(f5);
    RightEyeOuter2.render(f5);
    LeftEyeUpper2.render(f5);
    LeftEyeLower2.render(f5);
    LeftEyeOuter2.render(f5);
    RightNostril2.render(f5);
    LeftNostril2.render(f5);
    NoseBottom2.render(f5);
    LeftNostrilOuter2.render(f5);
    RightNostrilOuter2.render(f5);
    BackJaw2.render(f5);
    LowerJaw2.render(f5);
    Teeth2.render(f5);
    LeftEyeInner4.render(f5);
    RightEyeInner4.render(f5);
    LeftEyeOuter4.render(f5);
    RightEyeOuter4.render(f5);
    LeftEyeLower4.render(f5);
    RightEyeLower4.render(f5);
    LeftNostrilUpper4.render(f5);
    RightNostrilUpper4.render(f5);
    RightNostrilOuter4.render(f5);
    LeftNostrilOuter4.render(f5);
    NoseBottom4.render(f5);
    BackJaw4.render(f5);
    LowerJaw4.render(f5);
    Teeth4.render(f5);
    Expression4.render(f5);
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
