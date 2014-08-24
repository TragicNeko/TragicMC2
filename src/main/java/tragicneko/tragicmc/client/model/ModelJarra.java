package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelJarra extends ModelBase
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
    ModelRenderer MiddleSpike1;
    ModelRenderer MiddleSpike2;
    ModelRenderer MiddleSpike3;
    ModelRenderer MiddleSpike4;
    ModelRenderer HeadMiddleSpike1;
    ModelRenderer HeadMiddleSpike2;
    ModelRenderer HeadMiddleSpike3;
    ModelRenderer TailMiddleSpike1;
    ModelRenderer TailMiddleSpike2;
    ModelRenderer TailMiddleSpike3;
  
  public ModelJarra()
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
      MiddleSpike1 = new ModelRenderer(this, 8, 48);
      MiddleSpike1.addBox(-2F, -9F, 0F, 1, 5, 1);
      MiddleSpike1.setRotationPoint(0F, 18F, 9F);
      MiddleSpike1.setTextureSize(64, 64);
      MiddleSpike1.mirror = true;
      setRotation(MiddleSpike1, 0F, 0F, 0F);
      MiddleSpike2 = new ModelRenderer(this, 2, 49);
      MiddleSpike2.addBox(2F, -7F, -3F, 1, 3, 1);
      MiddleSpike2.setRotationPoint(0F, 18F, 9F);
      MiddleSpike2.setTextureSize(64, 64);
      MiddleSpike2.mirror = true;
      setRotation(MiddleSpike2, 0F, 0F, 0F);
      MiddleSpike3 = new ModelRenderer(this, 8, 48);
      MiddleSpike3.addBox(-7F, -1F, -5F, 3, 1, 1);
      MiddleSpike3.setRotationPoint(0F, 18F, 9F);
      MiddleSpike3.setTextureSize(64, 64);
      MiddleSpike3.mirror = true;
      setRotation(MiddleSpike3, 0F, 0F, 0F);
      MiddleSpike4 = new ModelRenderer(this, 0, 48);
      MiddleSpike4.addBox(4F, 0F, 0F, 2, 1, 1);
      MiddleSpike4.setRotationPoint(0F, 18F, 9F);
      MiddleSpike4.setTextureSize(64, 64);
      MiddleSpike4.mirror = true;
      setRotation(MiddleSpike4, 0F, 0F, 0F);
      HeadMiddleSpike1 = new ModelRenderer(this, 11, 50);
      HeadMiddleSpike1.addBox(-4F, 0F, 0F, 1, 1, 1);
      HeadMiddleSpike1.setRotationPoint(0F, 18F, 0F);
      HeadMiddleSpike1.setTextureSize(64, 64);
      HeadMiddleSpike1.mirror = true;
      setRotation(HeadMiddleSpike1, 0F, 0F, 0F);
      HeadMiddleSpike2 = new ModelRenderer(this, 8, 52);
      HeadMiddleSpike2.addBox(3F, -2F, 0F, 2, 1, 1);
      HeadMiddleSpike2.setRotationPoint(0F, 18F, 0F);
      HeadMiddleSpike2.setTextureSize(64, 64);
      HeadMiddleSpike2.mirror = true;
      setRotation(HeadMiddleSpike2, 0F, 0F, 0F);
      HeadMiddleSpike3 = new ModelRenderer(this, 4, 50);
      HeadMiddleSpike3.addBox(-1F, -5F, 0F, 1, 2, 1);
      HeadMiddleSpike3.setRotationPoint(0F, 18F, 0F);
      HeadMiddleSpike3.setTextureSize(64, 64);
      HeadMiddleSpike3.mirror = true;
      setRotation(HeadMiddleSpike3, 0F, 0F, 0F);
      TailMiddleSpike1 = new ModelRenderer(this, 0, 48);
      TailMiddleSpike1.addBox(-5F, 0F, 0F, 2, 1, 1);
      TailMiddleSpike1.setRotationPoint(0F, 18F, 14F);
      TailMiddleSpike1.setTextureSize(64, 64);
      TailMiddleSpike1.mirror = true;
      setRotation(TailMiddleSpike1, 0F, 0F, 0F);
      TailMiddleSpike2 = new ModelRenderer(this, 10, 48);
      TailMiddleSpike2.addBox(3F, -1F, -1F, 1, 1, 1);
      TailMiddleSpike2.setRotationPoint(0F, 18F, 14F);
      TailMiddleSpike2.setTextureSize(64, 64);
      TailMiddleSpike2.mirror = true;
      setRotation(TailMiddleSpike2, 0F, 0F, 0F);
      TailMiddleSpike3 = new ModelRenderer(this, 0, 48);
      TailMiddleSpike3.addBox(0F, -5F, 0F, 1, 2, 1);
      TailMiddleSpike3.setRotationPoint(0F, 18F, 14F);
      TailMiddleSpike3.setTextureSize(64, 64);
      TailMiddleSpike3.mirror = true;
      setRotation(TailMiddleSpike3, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
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
    MiddleSpike1.render(f5);
    MiddleSpike2.render(f5);
    MiddleSpike3.render(f5);
    MiddleSpike4.render(f5);
    HeadMiddleSpike1.render(f5);
    HeadMiddleSpike2.render(f5);
    HeadMiddleSpike3.render(f5);
    TailMiddleSpike1.render(f5);
    TailMiddleSpike2.render(f5);
    TailMiddleSpike3.render(f5);
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
		
		this.MiddleSpike1.rotateAngleZ = MathHelper.cos(par1 * 0.3F) * par2;
		this.MiddleSpike1.rotateAngleY = MathHelper.cos(par1 * 0.2F) + par2;
		
		this.MiddleSpike2.rotateAngleZ = MathHelper.cos(par1 * 0.3F) * par2;
		this.MiddleSpike2.rotateAngleY = MathHelper.cos(par1 * 0.2F) + par2;
		
		this.MiddleSpike3.rotateAngleZ = MathHelper.cos(par1 * 0.3F) * par2;
		this.MiddleSpike3.rotateAngleY = MathHelper.cos(par1 * 0.2F) + par2;
		
		this.MiddleSpike4.rotateAngleZ = MathHelper.cos(par1 * 0.3F) * par2;
		this.MiddleSpike4.rotateAngleY = MathHelper.cos(par1 * 0.2F) + par2;
		
		this.HeadMiddle.rotateAngleZ = MathHelper.cos(par1 * 0.3F + (float)Math.PI + 0.5F) * par2;
		this.HeadMiddle.rotateAngleY = MathHelper.cos(par1 * 0.2F + (float)Math.PI) + par2;
		
		this.HeadMiddleSpike1.rotateAngleZ = MathHelper.cos(par1 * 0.3F + (float)Math.PI + 0.5F) * par2;
		this.HeadMiddleSpike1.rotateAngleY = MathHelper.cos(par1 * 0.2F + (float)Math.PI) + par2;
		
		this.HeadMiddleSpike2.rotateAngleZ = MathHelper.cos(par1 * 0.3F + (float)Math.PI + 0.5F) * par2;
		this.HeadMiddleSpike2.rotateAngleY = MathHelper.cos(par1 * 0.2F + (float)Math.PI) + par2;
		
		this.HeadMiddleSpike3.rotateAngleZ = MathHelper.cos(par1 * 0.3F + (float)Math.PI + 0.5F) * par2;
		this.HeadMiddleSpike3.rotateAngleY = MathHelper.cos(par1 * 0.2F + (float)Math.PI) + par2;
		
		this.TailMiddle.rotateAngleZ = MathHelper.cos(par1 * 0.3F - (float)Math.PI - 0.5F) * par2;
		this.TailMiddle.rotateAngleY = MathHelper.cos(par1 * 0.2F - (float)Math.PI) + par2;
		
		this.TailMiddleSpike1.rotateAngleZ = MathHelper.cos(par1 * 0.3F - (float)Math.PI - 0.5F) * par2;
		this.TailMiddleSpike1.rotateAngleY = MathHelper.cos(par1 * 0.2F - (float)Math.PI) + par2;
		
		this.TailMiddleSpike2.rotateAngleZ = MathHelper.cos(par1 * 0.3F - (float)Math.PI - 0.5F) * par2;
		this.TailMiddleSpike2.rotateAngleY = MathHelper.cos(par1 * 0.2F - (float)Math.PI) + par2;
		
		this.TailMiddleSpike3.rotateAngleZ = MathHelper.cos(par1 * 0.3F - (float)Math.PI - 0.5F) * par2;
		this.TailMiddleSpike3.rotateAngleY = MathHelper.cos(par1 * 0.2F - (float)Math.PI) + par2;
		
		this.Head.rotateAngleZ = MathHelper.cos(par1 * 0.3F - 0.1F) * par2;
		this.TailHead.rotateAngleZ = MathHelper.cos(par1 * 0.3F + 0.1F) * par2;
	}

}
