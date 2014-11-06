package tragicneko.tragicmc.entity.boss;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.mob.TragicMob;

public class EntityAegar extends TragicMob implements TragicMiniBoss {

	public EntityAegar(World par1World) {
		super(par1World);
		this.setSize(1.385F, 3.325F);
		this.stepHeight = 2.0F;
		this.experienceValue = 100;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.isImmuneToFire = true;
	}
	
	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.185);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(2.5);
	}
	
	public int getTotalArmorValue()
	{
		return 20; //if this is angry, move this up to 24, which would be stronger than diamond™
	}
	
	@Override
	protected void fall(float par1) {}
	
	@Override
	public void addPotionEffect(PotionEffect effect) {}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}
}
