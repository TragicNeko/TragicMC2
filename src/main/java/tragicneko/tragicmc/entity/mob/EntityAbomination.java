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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
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
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.55D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
	}
	
	@Override
	public boolean canCorrupt()
	{
		return false;
	}

	@Override
	public void onKillEntity(EntityLivingBase entity)
	{
		super.onKillEntity(entity);
		
		int ticks = (int) entity.getMaxHealth();
		this.setCelebrationTicks(ticks + rand.nextInt(10));
		
		List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(12.0, 12.0, 12.0));
		EntityAbomination mob;

		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof EntityAbomination)
			{
				mob = (EntityAbomination) list.get(i);
				if (mob.getAttackTarget() == entity) mob.setCelebrationTicks(ticks + rand.nextInt(10) - rand.nextInt(10));
				
			}
		}
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
		this.dataWatcher.addObject(17, Integer.valueOf(0));
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
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.53);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (this.worldObj.isRemote) return;

		if (this.getAttackTime() > 0) this.decrementAttackTime();
		if (this.isCelebrating())
		{
			this.decrementCelebrationTicks();
			if (this.getAttackTime() == 0 && this.getCelebrationTicks() >= 10) this.setAttackTime(10);
		}
		if (this.isCelebrating() && this.getCelebrationTicks() % 20 == 0) this.jump();
	}

	public void moveEntity(double d0, double d1, double d2)
	{
		if (this.isCelebrating())
		{
			super.moveEntity(0, d1, 0);
		}
		else
		{
			super.moveEntity(d0, d1, d2);
		}
	}

	public boolean isCelebrating()
	{
		return this.dataWatcher.getWatchableObjectInt(17) > 0;
	}

	public int getCelebrationTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	private void setCelebrationTicks(int i)
	{
		this.dataWatcher.updateObject(17, i);
	}

	private void decrementCelebrationTicks()
	{
		int pow = this.dataWatcher.getWatchableObjectInt(17);
		this.dataWatcher.updateObject(17, --pow);
	}

	public int getAttackTime()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	private void decrementAttackTime()
	{
		int pow = this.dataWatcher.getWatchableObjectInt(16);
		this.dataWatcher.updateObject(16, --pow);
	}

	private void setAttackTime(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.worldObj.isRemote) return false;

		if (this.isCelebrating()) this.setCelebrationTicks(0);

		if (par1DamageSource.isFireDamage())
		{
			par2 = (par2 * 2.275F) - 1.0F;
		}

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem() != null && !(player.getCurrentEquippedItem().getItem() instanceof ItemSword))
			{
				par2 *= 0.725F;
			}
		}

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase && rand.nextBoolean())
		{
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(12.0, 12.0, 12.0));
			EntityAbomination mob;

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityAbomination)
				{
					mob = (EntityAbomination) list.get(i);
					if (mob.getAttackTarget() == null) mob.setTarget(this.getAttackTarget());
				}
			}
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote || this.isCelebrating()) return false;

		boolean result = super.attackEntityAsMob(par1Entity);

		if (result)
		{
			par1Entity.motionY += 0.34D;
			par1Entity.motionX *= 1.11D;
			par1Entity.motionZ *= 1.11D;
			this.setAttackTime(10);
		}

		return result;
	}

	public int getTotalArmorValue()
	{
		return 4;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
		if (tag.hasKey("celebrationTime")) this.setCelebrationTicks(tag.getInteger("celebrationTime"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("attackTime", this.getAttackTime());
		tag.setInteger("celebrationTime", this.getCelebrationTicks());
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

}
