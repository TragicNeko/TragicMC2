package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMinotaur extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer lefthorn;
    ModelRenderer righthorn;
    ModelRenderer nose;
    ModelRenderer body;
    ModelRenderer abdomen;
    ModelRenderer rightarm;
    ModelRenderer rightarmlower;
    ModelRenderer leftarm;
    ModelRenderer leftarmlower;
    ModelRenderer rightleg;
    ModelRenderer rightthigh;
    ModelRenderer leftleg;
    ModelRenderer leftthigh;
    ModelRenderer righthoof;
    ModelRenderer lefthoof;
  
  public ModelMinotaur()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-3.5F, -8F, -6F, 7, 6, 8);
      head.setRotationPoint(0F, -5F, 0F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      lefthorn = new ModelRenderer(this, 30, 0);
      lefthorn.addBox(3.5F, -2F, 0F, 2, 2, 9);
      lefthorn.setRotationPoint(0F, -11F, 0F);
      lefthorn.setTextureSize(64, 64);
      lefthorn.mirror = true;
      setRotation(lefthorn, 0.2974289F, 0.3866576F, 0F);
      righthorn = new ModelRenderer(this, 30, 0);
      righthorn.addBox(-5.5F, -2F, 0F, 2, 2, 9);
      righthorn.setRotationPoint(0F, -11F, 0F);
      righthorn.setTextureSize(64, 64);
      righthorn.mirror = true;
      setRotation(righthorn, 0.2974289F, -0.3866576F, 0F);
      nose = new ModelRenderer(this, 0, 25);
      nose.addBox(-2F, 0F, -4F, 4, 3, 3);
      nose.setRotationPoint(0F, -10F, -5F);
      nose.setTextureSize(64, 64);
      nose.mirror = true;
      setRotation(nose, 0F, 0F, 0F);
      body = new ModelRenderer(this, 16, 16);
      body.addBox(-6F, 0F, -3F, 12, 9, 10);
      body.setRotationPoint(0F, -7F, -1F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      abdomen = new ModelRenderer(this, 26, 36);
      abdomen.addBox(-4F, 0F, 0F, 8, 6, 4);
      abdomen.setRotationPoint(0F, 2F, 0F);
      abdomen.setTextureSize(64, 64);
      abdomen.mirror = true;
      setRotation(abdomen, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 0, 35);
      rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
      rightarm.setRotationPoint(-7F, -5F, 0F);
      rightarm.setTextureSize(64, 64);
      rightarm.mirror = true;
      setRotation(rightarm, -0.1189716F, 0F, 0.2082002F);
      rightarmlower = new ModelRenderer(this, 0, 52);
      rightarmlower.addBox(-6F, 8F, -2F, 3, 7, 3);
      rightarmlower.setRotationPoint(-7F, -5F, 0F);
      rightarmlower.setTextureSize(64, 64);
      rightarmlower.mirror = true;
      setRotation(rightarmlower, -0.1189716F, 0F, -0.2082002F);
      leftarm = new ModelRenderer(this, 0, 35);
      leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
      leftarm.setRotationPoint(7F, -5F, 0F);
      leftarm.setTextureSize(64, 64);
      leftarm.mirror = true;
      setRotation(leftarm, -0.1189716F, 0F, -0.2082002F);
      leftarmlower = new ModelRenderer(this, 0, 52);
      leftarmlower.addBox(3F, 8F, -2F, 3, 7, 3);
      leftarmlower.setRotationPoint(7F, -5F, 0F);
      leftarmlower.setTextureSize(64, 64);
      leftarmlower.mirror = true;
      setRotation(leftarmlower, -0.1189716F, 0F, 0.2082002F);
      rightleg = new ModelRenderer(this, 0, 16);
      rightleg.addBox(-1F, 8F, -2F, 3, 5, 3);
      rightleg.setRotationPoint(-3F, 6F, 1F);
      rightleg.setTextureSize(64, 64);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      rightthigh = new ModelRenderer(this, 16, 48);
      rightthigh.addBox(-4F, 0F, -3F, 6, 8, 7);
      rightthigh.setRotationPoint(-3F, 6F, 1F);
      rightthigh.setTextureSize(64, 64);
      rightthigh.mirror = true;
      setRotation(rightthigh, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-2F, 8F, -2F, 3, 5, 3);
      leftleg.setRotationPoint(3F, 6F, 1F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      leftthigh = new ModelRenderer(this, 16, 48);
      leftthigh.addBox(-2F, 0F, -3F, 6, 8, 7);
      leftthigh.setRotationPoint(3F, 6F, 1F);
      leftthigh.setTextureSize(64, 64);
      leftthigh.mirror = true;
      setRotation(leftthigh, 0F, 0F, 0F);
      righthoof = new ModelRenderer(this, 42, 47);
      righthoof.addBox(-2.5F, 13F, -2.5F, 5, 5, 5);
      righthoof.setRotationPoint(-3F, 6F, 1F);
      righthoof.setTextureSize(64, 64);
      righthoof.mirror = true;
      setRotation(righthoof, 0F, 0F, 0F);
      lefthoof = new ModelRenderer(this, 42, 47);
      lefthoof.addBox(-2.5F, 13F, -2.5F, 5, 5, 5);
      lefthoof.setRotationPoint(3F, 6F, 1F);
      lefthoof.setTextureSize(64, 64);
      lefthoof.mirror = true;
      setRotation(lefthoof, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    lefthorn.render(f5);
    righthorn.render(f5);
    nose.render(f5);
    body.render(f5);
    abdomen.render(f5);
    rightarm.render(f5);
    rightarmlower.render(f5);
    leftarm.render(f5);
    leftarmlower.render(f5);
    rightleg.render(f5);
    rightthigh.render(f5);
    leftleg.render(f5);
    leftthigh.render(f5);
    righthoof.render(f5);
    lefthoof.render(f5);
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
