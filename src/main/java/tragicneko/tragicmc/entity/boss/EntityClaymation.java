package tragicneko.tragicmc.entity.boss;

import tragicneko.tragicmc.TragicMC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityClaymation extends TragicBoss {

	private double[][] formValues = new double[][] {{150.0D, 0.22D, 12.0D, 32.0D, 1.0D}, {42.0D, 0.45D, 8.0D, 32.0D, 0.5D}, {160.0D, 0.42D, 8.0D, 48.0D, 1.0D},
			{100.0D, 0.22D, 20.0D, 24.0D, 1.0D}, {150.0D, 0.46D, 4.0D, 64.0D, 0.2D}, {50.0D, 0.32D, 5.5D, 32.0D, 0.0D}, {65.0D, 0.38D, 7.0D, 32.0D, 1.0D},
			{220.0D, 0.35D, 16.0D, 32.0D, 1.0D}, {50.0D, 0.42D, 8.0D, 64.0D, 1.0D}, {100.0D, 0.25D, 12.0D, 16.0D, 0.0D}};
	private float[][] formSizes = new float[][] {{0.625F, 2.375F}, {0.7F, 2.5F}, {1.385F, 3.3F}, {1.7835F, 5.15F}, {1.775F, 2.725F}, {0.4F, 0.5F}, {0.935F, 2.87F}, {0.7F, 2.1F},
			{0.615F, 1.695F}, {1.4F, 2.9F}};

	public EntityClaymation(World par1World) {
		super(par1World);
		this.setSize(0.625F, 2.375F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.isImmuneToFire = true;
		this.stepHeight = 1.5F;
	}

	public boolean canRenderOnFire()
	{
		return false;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.22);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Float.valueOf(0.0F)); //this keeps track of the Claymation's health, because it will change based on form, this is it's actual health
		this.dataWatcher.addObject(17, Integer.valueOf((int) 0)); //this changes the ai based on what the claymation is turned into, 0 is normal, 1 is Minotaur, etc.
		this.dataWatcher.addObject(18, Integer.valueOf((int) 0)); //this keeps track of the time it can hold it's current form before it reverts to it's normal one
		this.dataWatcher.addObject(19, Integer.valueOf((int) 0)); //this is the first utility integer value, can be set for each ai type for their own use
		this.dataWatcher.addObject(20, Integer.valueOf((int) 0)); //this is the second utility integer value, can be set for each ai type for their own use
	}

	private void updateHealth(float f)
	{
		this.dataWatcher.updateObject(16, f);
	}

	public float getActualHealth()
	{
		return this.dataWatcher.getWatchableObjectFloat(16);
	}

	private void setEntityForm(int i)
	{
		this.dataWatcher.updateObject(17, i);
		this.dataWatcher.updateObject(18, 0); //resets the ticks in a form to 0

		if (i > formValues.length)
		{
			TragicMC.logError("Claymation AI set to an invalid amount, things are not going to work well"); 
			return;
		}
		
		this.setFormAttributes(i);
		this.setFormSize(i);
	}

	/**
	 * 0 is the normal form, 1 is the Minotaur, 2 is the Apis, 3 is the Stin King, 4 is Vox Stellarum, 5 is Jabba, 6 is Ragr, 7 is Skultar, 8 is Kitsunakuma, 9 is Iron Golem
	 * @return
	 */
	public int getEntityForm()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	public int getFormTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	private void incrementFormTicks()
	{
		int pow = this.dataWatcher.getWatchableObjectInt(18);
		this.dataWatcher.setObjectWatched(++pow);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.getAttackTarget() != null) this.incrementFormTicks();

		if (this.getFormTicks() >= 250 && this.getEntityForm() == 0)
		{
			this.updateHealth(this.getHealth());
			this.setEntityForm(rand.nextInt(10) + 1);
		}
		else if (this.getFormTicks() >= 600 && this.getEntityForm() != 0)
		{
			this.setEntityForm(rand.nextInt(10) + 1);
		}

		this.updateEntityAI();
	}

	public void updateEntityAI()
	{
		//update entity ai based on form
	}

	private void setFormAttributes(int i)
	{		
		double health = formValues[i][0];
		double speed = formValues[i][1];
		double attack = formValues[i][2];
		double follow = formValues[i][3];
		double knockback = formValues[i][4];

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(health);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attack);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(follow);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(knockback);

		if (i != 0)
		{
			this.setHealth(this.getActualHealth());
		}
		else
		{
			this.setHealth(this.getMaxHealth());
		}
	}

	private void setFormSize(int i)
	{
		this.setSize(formSizes[i][0], formSizes[i][1]);
	}

	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		if (this.worldObj.isRemote) return false;

		if (this.getHealth() - damage <= 0.0F && this.getEntityForm() != 0)
		{
			this.setEntityForm(0);
			return true;
		}
		return super.attackEntityFrom(source, damage);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;
		return super.attackEntityAsMob(par1Entity);
	}

	@Override
	public int getTotalArmorValue()
	{
		return this.getEntityForm() == 0 ? 16 : 6;
	}
}
