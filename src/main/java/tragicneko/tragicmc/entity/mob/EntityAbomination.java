package tragicneko.tragicmc.entity.mob;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityMegaCryse;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.main.TragicEntities;

public class EntityAbomination extends TragicMob {

	public EntityAbomination(World par1World) {
		super(par1World);
		this.setSize(0.675F, 2.05F);
		this.stepHeight = 1.0F;
		this.experienceValue = 12;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.canCorrupt = false;
		this.isCorruptible = false;
		this.isChangeable = false;
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Beast;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(45.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.276);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.5);
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (par1DamageSource.isFireDamage())
		{
			par2 *= 4;
		}

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemSword)
			{
				par2 *= 1.125;
			}
		}
		
		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase)
		{
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(12.0, 12.0, 12.0));
			TragicMob mob;

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof TragicMob)
				{
					mob = (TragicMob) list.get(i);

					if (mob instanceof EntityAbomination)
					{
						if (mob.getAttackTarget() != this.getAttackTarget()) mob.setTarget(this.getAttackTarget());
					}
				}
			}
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);
		
		if (result)
		{
			par1Entity.motionY += 0.845D;
			par1Entity.motionX *= 1.114D;
			par1Entity.motionZ *= 1.114D;
		}

		return result;
	}

	public int getTotalArmorValue()
	{
		return 2;
	}

}
