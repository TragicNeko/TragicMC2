package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.lockbotStats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.EntityLock;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityLockbot extends TragicMob {

	private EntityLivingBase prevTarget;

	public EntityLockbot(World par1World) {
		super(par1World);
		this.setSize(0.625F, 0.725F);
		this.stepHeight = 1.0F;
		this.experienceValue = 5;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(lockbotStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(lockbotStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(lockbotStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(lockbotStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(lockbotStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return (int) lockbotStats[5];
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
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.prevTarget == null && this.getAttackTarget() != null || this.getAttackTarget() != null && this.prevTarget != null && this.getAttackTarget() != this.prevTarget)
		{
			this.getAttackTarget().attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(this), (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
			this.prevTarget = this.getAttackTarget();
		}		

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) <= 8.0F && this.getAttackTarget().riddenByEntity == null)
		{
			EntityLock lock = new EntityLock(this.worldObj, this, this.getAttackTarget());
			lock.setPosition(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ);
			this.worldObj.spawnEntityInWorld(lock);
		}

	}

	@Override
	public void fall(float par1) {}

	@Override
	public void updateFallState(double par1, boolean par2) {}

}
