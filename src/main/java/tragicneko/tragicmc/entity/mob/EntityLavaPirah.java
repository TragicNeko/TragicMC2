package tragicneko.tragicmc.entity.mob;

import java.util.UUID;

import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLavaPirah extends EntityPirah {

	public EntityLavaPirah(World par1World) {
		super(par1World);
		this.isImmuneToFire = true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.45);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.isInsideOfMaterial(Material.water))
		{
			this.attackEntityFrom(DamageSource.drown, 1.0F);
		}
	}
	
	@Override
	protected Material getMaterial()
	{
		return Material.lava;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return this.posY > 5.0D && this.posY < 75.0D && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && this.isInsideOfMaterial(Material.lava);
	}
}
