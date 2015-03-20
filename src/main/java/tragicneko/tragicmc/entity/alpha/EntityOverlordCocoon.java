package tragicneko.tragicmc.entity.alpha;

import static tragicneko.tragicmc.TragicConfig.overlordCocoonStats;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityOverlordCocoon extends TragicBoss {

	private int phaseTicks; //ticks until phase resets
	private boolean phaseChange = false; //if enough damage has been taken to go to the next phase
	private float phaseDamage; //damage taken during that phase, heal this amount if time runs out
	private ArrayList<Entity> seekers = new ArrayList<Entity>(); //list of currently active seekers, if empty and target is alive then grant Divinity and start timer

	public static final IEntitySelector selec = new IEntitySelector() {
		@Override
		public boolean isEntityApplicable(Entity entity) {
			return entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getCreatureAttribute() != TragicEntities.Synapse;
		}
	};

	public EntityOverlordCocoon(World par1World) {
		super(par1World);
		this.setSize(2.385F, 3.325F);
		this.stepHeight = 2.0F;
		this.experienceValue = 0;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 48.0F));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 48.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, false, selec));
		this.isImmuneToFire = true;
	}

	@Override
	public void setAir(int i){}

	@Override
	protected void despawnEntity() {}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void fall(float f) {}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Synapse;
	}

	@Override
	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		if (!this.worldObj.isRemote)
		{
			EntityOverlordCore core = new EntityOverlordCore(this.worldObj);
			core.setPosition(this.posX, this.posY, this.posZ);
			core.setStartTransform();
			this.worldObj.spawnEntityInWorld(core);
		}
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(overlordCocoonStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(overlordCocoonStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(overlordCocoonStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(overlordCocoonStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(overlordCocoonStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return (int) overlordCocoonStats[5];
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.getAttackTarget() == null)
		{
			//kill all seekers nearby
			//Remove Divinity from all nearby mobs
		}
		else
		{
			if (seekers.isEmpty())
			{
				this.phaseTicks = 200;
				
				if (this.phaseChange)
				{
					//spawn seekers based on phase
					//remove divinity from nearby entities
				}
				else
				{
					if (this.phaseTicks == 200)
					{
						//grant Divinity to nearby entities
					}
					
					if (this.phaseTicks == 0)
					{
						this.heal(this.phaseDamage);
						//remove divinity from all nearby entities
					}
				}
			}
		}
	}

	@Override
	public void addPotionEffect(PotionEffect pe) {}

	/**
	 * Five phases, 0 is full health, 4 is below 1/5 health
	 * @return
	 */
	public int getCurrentPhase()
	{
		float hp = this.getHealth();
		float maxFifth = this.getMaxHealth() / 5;
		return hp <= maxFifth ? 4 : (hp <= maxFifth * 2 ? 3 : (hp <= maxFifth * 3 ? 2 : (hp <= maxFifth * 4 ? 1 : 0)));
	}

	public void spawnSeekers()
	{
		switch(this.getCurrentPhase())
		{
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource src, float dmg)
	{
		//if (this.getTransformationTicks() > 0) return false;

		if (src.getEntity() instanceof EntityLivingBase && !this.worldObj.isRemote)
		{
			EntityLivingBase entity = (EntityLivingBase) src.getEntity();
			boolean flag = TragicConfig.allowDivinity && entity.isPotionActive(TragicPotion.Divinity);

			if (flag || !TragicConfig.allowDivinity && entity.getCreatureAttribute() != TragicEntities.Synapse)// || this.getVulnerableTicks() > 0 && entity.getCreatureAttribute() != TragicEntities.Synapse)
			{
				if (rand.nextBoolean() && this.worldObj.getEntitiesWithinAABB(EntityNanoSwarm.class, this.boundingBox.expand(64.0, 64.0, 64.0D)).size() < 16)
				{
					EntityNanoSwarm swarm = new EntityNanoSwarm(this.worldObj);
					swarm.setPosition(this.posX, this.posY, this.posZ);
					this.worldObj.spawnEntityInWorld(swarm);
				}

				return super.attackEntityFrom(src, dmg);
			}

			if (rand.nextInt(4) == 0 && this.getAttackTarget() != entity && entity.getCreatureAttribute() != TragicEntities.Synapse) this.setAttackTarget(entity);
		}
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		if (this.worldObj.isRemote) return false; // || this.getTransformationTicks() > 0) return false;
		
		boolean flag = super.attackEntityAsMob(entity);
		if (flag)
		{
			//create corrupted gas around itself and inflict high knockback hit on entity
		}
		
		return flag;
	}
}
