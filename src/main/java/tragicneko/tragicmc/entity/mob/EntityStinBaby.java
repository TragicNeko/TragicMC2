package tragicneko.tragicmc.entity.mob;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityStinKing;
import tragicneko.tragicmc.entity.boss.EntityStinQueen;

public class EntityStinBaby extends TragicMob {

	public EntityStinBaby(World par1World) {
		super(par1World);
		this.setSize(0.65F, 0.65F);
		this.experienceValue = 15;
		this.getNavigator().setAvoidSun(true);
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setCanSwim(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 16.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 16.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
		this.canCorrupt = false;
		this.isCorruptible = true;
		this.isChangeable = false;
		this.stepHeight = 0.5F;
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.346);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0);
	}
	
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		if (this.isCorrupted) this.entityAge++;
		
		if (this.entityAge >= 1200 && rand.nextInt(128) == 0 && !this.worldObj.isRemote)
		{
			this.growUp();
		}
	}
	
	public void growUp()
	{
		EntityStin boss = new EntityStin(this.worldObj);
		boss.copyLocationAndAnglesFrom(this);
		boss.onSpawnWithEgg((IEntityLivingData)null);
		this.worldObj.removeEntity(this);
		this.worldObj.spawnEntityInWorld(boss);
		boss.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 2));
		boss.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 2));
	}

	public int getTotalArmorValue()
	{
		return 6;
	}
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		boolean flag = super.attackEntityFrom(par1DamageSource, par2);
		
		if (flag)
		{
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0, 32.0, 32.0));
			TragicMob mob;

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof TragicMob)
				{
					mob = (TragicMob) list.get(i);

					if (mob instanceof EntityStin || mob instanceof EntityStinBaby || mob instanceof EntityStinKing || mob instanceof EntityStinQueen || mob instanceof EntityStinBaby)
					{
						if (mob.getAttackTarget() != this.getAttackTarget()) mob.setTarget(this.getAttackTarget());
					}
				}
			}
		}
		
		return flag;
	}
}
