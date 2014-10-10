package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.items.weapons.WeaponReaperScythe;

public class EntityDeathReaperClone extends TragicMob {
	
	private boolean isBomb;
	
	public EntityDeathReaperClone(World par1World) {
		super(par1World);
		this.isBomb = rand.nextBoolean();
		this.setSize(0.7F, 2.1F);
		this.stepHeight = 2.0F;
		this.experienceValue = 0;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityGolem.class, 32.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
		this.isImmuneToFire = true;
	}
	
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEAD;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(220.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.33);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}
	
	public void onLivingUpdate()
	{		
		if (this.isPotionActive(Potion.wither.id)) this.removePotionEffect(Potion.wither.id);
		if (this.isPotionActive(Potion.weakness.id)) this.removePotionEffect(Potion.weakness.id);

		super.onLivingUpdate();
		
		if (this.worldObj.isRemote) return;
		
		if (this.getAttackTarget() != null && this.getAttackTarget() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) this.getAttackTarget();
			
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof WeaponReaperScythe)
			{
				this.setAttackTarget(null);
			}
		}
		
		if (this.ticksExisted % 60 == 0 && !this.worldObj.isRemote && this.getHealth() < this.getMaxHealth()) this.heal(6.0F);
		
		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 4.0F && rand.nextInt(72) == 0 && this.onGround)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

			switch(rand.nextInt(6))
			{
			case 0:
				EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
				fireball.posY = this.posY + this.height;
				this.worldObj.spawnEntityInWorld(fireball);

				break;
			case 1:
				EntityWitherSkull solarBomb = new EntityWitherSkull(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
				solarBomb.posY = this.posY + (this.height * 2 / 3);
				solarBomb.setInvulnerable(true);
				this.worldObj.spawnEntityInWorld(solarBomb);
				break;
			case 2:
				EntityWitherSkull solarBomb2 = new EntityWitherSkull(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
				solarBomb2.posY = this.posY + (this.height * 2 / 3);
				this.worldObj.spawnEntityInWorld(solarBomb2);
				break;
			default:
				for (int i = 0; i < 3; ++i)
				{
					EntitySmallFireball fireball2 = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
					fireball2.posY = this.posY + this.height;
					this.worldObj.spawnEntityInWorld(fireball2);
				}
				break;
			case 4:
				EntityLightningBolt bolt = new EntityLightningBolt(this.worldObj, this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ);
				this.worldObj.spawnEntityInWorld(bolt);
				break;
			}
		}
		
		if (this.ticksExisted >= 200)
		{
			this.setDead();
		}
	}
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote || par1DamageSource.getEntity() != null || par1DamageSource.getEntity() instanceof EntityDeathReaper) return false;
		
		boolean result = super.attackEntityFrom(par1DamageSource, par2 * 10.0F);

		if (this.isBomb && result)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float) (rand.nextDouble() * 2.0F) + 2.0F, this.getMobGriefing());
			this.setDead();
			return result;
		}

		return result;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("isBomb")) this.isBomb = tag.getBoolean("isBomb");
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setBoolean("isBomb", this.isBomb);
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}
}
