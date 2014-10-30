package tragicneko.tragicmc.entity.mob;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class EntityPsygote extends TragicMob {
	
	private AttributeModifier mod = new AttributeModifier(UUID.fromString("1e8bc939-443c-46b6-8158-0d53513a47e6"), "psygoteSpeedDebuff", -0.50, 0);

	public EntityPsygote(World par1World) {
		super(par1World);
		this.stepHeight = 2.0F;
		this.experienceValue = 10;
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 16.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(72.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.19);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.65);
	}

	public void onLivingUpdate()
	{
		if (!this.worldObj.isRemote && this.isPotionActive(Potion.wither.id)) this.removePotionEffect(Potion.wither.id);
		super.onLivingUpdate();
		
		if (!this.onGround && this.motionY < 0.0D) this.motionY *= 0.58D;

		if (this.worldObj.isRemote) return;

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
		//if (this.getFiringTicks() >= 60) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);

		//if (this.ticksExisted % 20 == 0 && this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && rand.nextInt(8) == 0 && !this.isFiring())
		//{
		//	this.setFiringTicks(120);
		//}

		//if (this.getFiringTicks() >= 60 && this.ticksExisted % 20 == 0 && this.getAttackTarget() != null) this.shootProjectiles();

		if (this.ticksExisted % 20 == 0 && this.getHealth() < this.getMaxHealth()) this.heal(3.0F);
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

}
