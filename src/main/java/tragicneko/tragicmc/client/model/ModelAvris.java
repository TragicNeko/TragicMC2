package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import tragicneko.tragicmc.entity.mob.EntityAvris;

/**
 * ModelAvris - TragicNeko
 * Created using Tabula 4.1.1
 */
public class ModelAvris extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rightShoulder;
    public ModelRenderer leftShoulder;
    public ModelRenderer rightLeg;
    public ModelRenderer leftLeg;
    public ModelRenderer lowerJaw;
    public ModelRenderer rightHorn;
    public ModelRenderer leftHorn;
    public ModelRenderer abdomen;
    public ModelRenderer hips;
    public ModelRenderer bag;
    public ModelRenderer bagTop;
    public ModelRenderer rightArm;
    public ModelRenderer rightForearm;
    public ModelRenderer rightClaw;
    public ModelRenderer rightClaw2;
    public ModelRenderer rightClaw3;
    public ModelRenderer leftArm;
    public ModelRenderer leftForearm;
    public ModelRenderer leftClaw;
    public ModelRenderer leftClaw2;
    public ModelRenderer leftClaw3;
    public ModelRenderer rightShin;
    public ModelRenderer rightTalon;
    public ModelRenderer rightTalon2;
    public ModelRenderer leftShin;
    public ModelRenderer leftTalon;
    public ModelRenderer leftTalon2;

    public ModelAvris() {
        this.textureWidth = 96;
        this.textureHeight = 64;
        this.head = new ModelRenderer(this, 0, 4);
        this.head.setRotationPoint(0.0F, -12.0F, -2.0F);
        this.head.addBox(-4.0F, -6.0F, -4.0F, 8, 4, 7, 0.0F);
        this.rightShoulder = new ModelRenderer(this, 60, 32);
        this.rightShoulder.setRotationPoint(-9.0F, -11.0F, 0.0F);
        this.rightShoulder.addBox(-2.0F, -2.0F, -1.5F, 3, 4, 3, 0.0F);
        this.lowerJaw = new ModelRenderer(this, 0, 16);
        this.lowerJaw.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lowerJaw.addBox(-2.0F, -2.0F, -5.0F, 4, 2, 5, 0.0F);
        this.rightTalon2 = new ModelRenderer(this, 0, 0);
        this.rightTalon2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightTalon2.addBox(0.0F, 19.0F, 2.5F, 1, 3, 1, 0.0F);
        this.hips = new ModelRenderer(this, 54, 20);
        this.hips.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hips.addBox(-4.0F, 13.0F, -2.0F, 8, 3, 5, 0.0F);
        this.leftShoulder = new ModelRenderer(this, 60, 32);
        this.leftShoulder.setRotationPoint(9.0F, -11.0F, 0.0F);
        this.leftShoulder.addBox(-1.0F, -2.0F, -1.5F, 3, 4, 3, 0.0F);
        this.rightTalon = new ModelRenderer(this, 0, 0);
        this.rightTalon.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightTalon.addBox(-1.5F, 19.0F, -1.5F, 3, 3, 1, 0.0F);
        this.leftArm = new ModelRenderer(this, 76, 32);
        this.leftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftArm.addBox(0.0F, 2.0F, -0.5F, 1, 9, 1, 0.0F);
        this.body = new ModelRenderer(this, 36, 0);
        this.body.setRotationPoint(0.0F, -14.0F, 0.0F);
        this.body.addBox(-8.0F, 0.0F, -2.0F, 16, 10, 6, 0.0F);
        this.rightArm = new ModelRenderer(this, 76, 32);
        this.rightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightArm.addBox(-1.0F, 2.0F, -0.5F, 1, 9, 1, 0.0F);
        this.rightClaw = new ModelRenderer(this, 0, 0);
        this.rightClaw.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightClaw.addBox(-0.5F, 22.0F, 0.5F, 1, 2, 1, 0.0F);
        this.leftShin = new ModelRenderer(this, 84, 48);
        this.leftShin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftShin.addBox(-1.0F, 9.0F, -0.5F, 3, 11, 3, 0.0F);
        this.leftTalon2 = new ModelRenderer(this, 0, 0);
        this.leftTalon2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftTalon2.addBox(0.0F, 19.0F, 2.5F, 1, 3, 1, 0.0F);
        this.leftTalon = new ModelRenderer(this, 0, 0);
        this.leftTalon.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftTalon.addBox(-0.5F, 19.0F, -1.5F, 3, 3, 1, 0.0F);
        this.leftClaw2 = new ModelRenderer(this, 0, 0);
        this.leftClaw2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftClaw2.addBox(1.0F, 22.0F, -1.0F, 1, 3, 1, 0.0F);
        this.rightClaw2 = new ModelRenderer(this, 0, 0);
        this.rightClaw2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightClaw2.addBox(-2.0F, 22.0F, -1.0F, 1, 3, 1, 0.0F);
        this.rightHorn = new ModelRenderer(this, 0, 0);
        this.rightHorn.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightHorn.addBox(-5.0F, -7.0F, 0.0F, 1, 4, 1, 0.0F);
        this.leftForearm = new ModelRenderer(this, 82, 32);
        this.leftForearm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftForearm.addBox(-1.0F, 11.0F, -1.5F, 3, 11, 3, 0.0F);
        this.bag = new ModelRenderer(this, 0, 36);
        this.bag.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bag.addBox(-11.0F, -2.0F, 4.0F, 22, 16, 12, 0.0F);
        this.rightShin = new ModelRenderer(this, 84, 48);
        this.rightShin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightShin.addBox(-1.0F, 9.0F, -0.5F, 3, 11, 3, 0.0F);
        this.leftHorn = new ModelRenderer(this, 0, 0);
        this.leftHorn.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftHorn.addBox(4.0F, -7.0F, 0.0F, 1, 4, 1, 0.0F);
        this.leftLeg = new ModelRenderer(this, 76, 48);
        this.leftLeg.setRotationPoint(2.5F, 2.0F, 0.0F);
        this.leftLeg.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1, 0.0F);
        this.rightLeg = new ModelRenderer(this, 76, 48);
        this.rightLeg.setRotationPoint(-3.5F, 2.0F, 0.0F);
        this.rightLeg.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1, 0.0F);
        this.abdomen = new ModelRenderer(this, 36, 18);
        this.abdomen.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.abdomen.addBox(-3.0F, 10.0F, -1.0F, 6, 3, 3, 0.0F);
        this.bagTop = new ModelRenderer(this, 0, 26);
        this.bagTop.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bagTop.addBox(-4.0F, -3.0F, 2.0F, 8, 3, 6, 0.0F);
        this.leftClaw3 = new ModelRenderer(this, 0, 0);
        this.leftClaw3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftClaw3.addBox(-1.0F, 22.0F, -1.5F, 1, 4, 1, 0.0F);
        this.rightForearm = new ModelRenderer(this, 82, 32);
        this.rightForearm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightForearm.addBox(-2.0F, 11.0F, -1.5F, 3, 11, 3, 0.0F);
        this.leftClaw = new ModelRenderer(this, 0, 0);
        this.leftClaw.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftClaw.addBox(-0.5F, 22.0F, 0.5F, 1, 2, 1, 0.0F);
        this.rightClaw3 = new ModelRenderer(this, 0, 0);
        this.rightClaw3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightClaw3.addBox(0.0F, 22.0F, -1.5F, 1, 4, 1, 0.0F);
        this.head.addChild(this.lowerJaw);
        this.rightShin.addChild(this.rightTalon2);
        this.body.addChild(this.hips);
        this.rightShin.addChild(this.rightTalon);
        this.leftShoulder.addChild(this.leftArm);
        this.rightShoulder.addChild(this.rightArm);
        this.rightForearm.addChild(this.rightClaw);
        this.leftLeg.addChild(this.leftShin);
        this.leftShin.addChild(this.leftTalon2);
        this.leftShin.addChild(this.leftTalon);
        this.leftForearm.addChild(this.leftClaw2);
        this.rightForearm.addChild(this.rightClaw2);
        this.head.addChild(this.rightHorn);
        this.leftShoulder.addChild(this.leftForearm);
        this.body.addChild(this.bag);
        this.rightLeg.addChild(this.rightShin);
        this.head.addChild(this.leftHorn);
        this.body.addChild(this.abdomen);
        this.body.addChild(this.bagTop);
        this.leftForearm.addChild(this.leftClaw3);
        this.rightShoulder.addChild(this.rightForearm);
        this.leftForearm.addChild(this.leftClaw);
        this.rightForearm.addChild(this.rightClaw3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.rightShoulder.render(f5);
        this.leftShoulder.render(f5);
        this.body.render(f5);
        this.leftLeg.render(f5);
        this.rightLeg.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
    	EntityAvris avris = (EntityAvris) entity;
    	
    	head.rotateAngleY = f3 / (180F / (float)Math.PI);
		head.rotateAngleX = f4 / (180F / (float)Math.PI);
		if (avris.hurtTime > 0) lowerJaw.rotateAngleX = -0.85F * this.simplifyAngle(avris.hurtTime, 5.0F) * f1;
		
		leftLeg.rotateAngleX = -0.85F * this.simplifyAngle(f, 10.0F) * f1;
		rightLeg.rotateAngleX = 0.85F * this.simplifyAngle(f, 10.0F) * f1;
		
		rightShoulder.rotateAngleX = (-0.2F + 1.5F * this.simplifyAngle(f, 10.0F)) * f1;
		leftShoulder.rotateAngleX = (-0.2F - 1.5F * this.simplifyAngle(f, 10.0F)) * f1;
	}
    
    private float simplifyAngle(float par1, float par2)
	{
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}
}
