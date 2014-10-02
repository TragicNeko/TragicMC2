package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLargeRock extends ModelBase
{
	private ModelRenderer rock;

	public ModelLargeRock()
	{
		textureWidth = 64;
		textureHeight = 32;

		rock = new ModelRenderer(this, 0, 0);
		rock.addBox(-7F, -7F, -7F, 14, 14, 14);
		rock.setRotationPoint(0F, 16F, 0F);
		ModelRenderer rockBottom = new ModelRenderer(this, 0, 0);
		rockBottom.addBox(-9F, -4F, -9F, 18, 8, 18);
		rock.addChild(rockBottom);

		ModelRenderer rockTop = new ModelRenderer(this, 0, 0);
		rockTop.addBox(-5F, -9F, -5F, 10, 18, 10);
		rock.addChild(rockTop);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		rock.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		rock.rotateAngleX = (float) (Math.cos(entity.ticksExisted) * 0.5F);
		rock.rotateAngleY = (float) (Math.sin(entity.ticksExisted) * 0.5F);
	}

}
