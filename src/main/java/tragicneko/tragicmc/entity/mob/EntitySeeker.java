package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.seekerStats;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;
import tragicneko.tragicmc.util.DamageHelper;

public class EntitySeeker extends TragicMob {
	
	public int killTicks;
	private int timeSinceTarget;

	public EntitySeeker(World par1World) {
		super(par1World);
		this.setSize(0.625F, 0.725F);
		this.stepHeight = 1.0F;
		this.experienceValue = 5;
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 64.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, false, EntityOverlordCombat.selec));
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(seekerStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(seekerStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(seekerStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(seekerStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(seekerStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return (int) seekerStats[5];
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Synapse;
	}

	@Override
	public void onLivingUpdate()
	{		
		this.motionX = this.motionY = this.motionZ = 0D;
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;
		
		if (this.getAttackTarget() != null)
		{
			this.timeSinceTarget = 0;
			if (this.canEntityBeSeen(this.getAttackTarget()))
			{
				if (this.killTicks < 400) this.killTicks++;
				
				if (this.killTicks >= 300)
				{
					this.getAttackTarget().attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this), Math.max(this.getAttackTarget().getMaxHealth() / 20F, 1.0F));
				}
				
				List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(64.0, 64.0, 64.0));
				for (Entity e : list)
				{
					if (e instanceof EntityLivingBase && ((EntityLivingBase) e).getCreatureAttribute() == TragicEntities.Synapse && ((EntityLiving) e).getAttackTarget() == null)
					{
						((EntityLiving) e).setAttackTarget(this.getAttackTarget());
					}
				}
			}
			else
			{
				if (this.ticksExisted % 5 == 0 && this.killTicks > 0) this.killTicks--;
			}
		}
		else
		{
			if (this.killTicks > 0) this.killTicks--;
			this.timeSinceTarget++;
			if (this.timeSinceTarget >= 200) this.killTicks = 0;
		}
		TragicMC.logInfo("Kill ticks: " + this.killTicks);
		TragicMC.logInfo("Health: " + this.getHealth());
	}

	@Override
	public void fall(float par1) {}

	@Override
	public void updateFallState(double par1, boolean par2) {}

}
