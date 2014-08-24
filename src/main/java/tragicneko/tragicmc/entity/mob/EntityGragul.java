package tragicneko.tragicmc.entity.mob;

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
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityKragul;
import tragicneko.tragicmc.entity.boss.TragicMiniBoss;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityGragul extends TragicMob {

	public EntityGragul(World par1World) {
		super(par1World);
		this.setSize(0.2F, 0.6F);
		this.stepHeight = 1.0F;
		this.experienceValue = 12;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.canCorrupt = false;
		this.isCorruptible = true;
		this.isChangeable = true;
		this.isImmuneToFire = true;
		this.superiorForm = new EntityKragul(par1World);
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.35);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.getAttackTarget() != null && this.ticksExisted % 120 == 0)
		{
			if (this.getAttackTarget() instanceof EntityPlayer && TragicNewConfig.allowInhibit)
			{
				((EntityPlayer) this.getAttackTarget()).addPotionEffect(new PotionEffect(TragicPotions.Inhibit.id, 300));
			}
		}

		for (int k = 0; k < 1; ++k)
		{
			this.worldObj.spawnParticle("townaura",
					this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D,
					this.posY + this.rand.nextDouble() * (double)this.height + 0.4D,
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D,
					this.rand.nextDouble(),
					this.rand.nextDouble() - 0.6D,
					this.rand.nextDouble());
		}

		for (int l = 0; l < 1; ++l)
		{
			this.worldObj.spawnParticle("reddust",
					this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
					this.posY + this.rand.nextDouble() * (double)this.height + 0.4D,
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.5D,
					(this.rand.nextDouble() - 0.6D) * 0.1D,
					this.rand.nextDouble() * 0.1D,
					(this.rand.nextDouble() - 0.6D) * 0.1D);
		}
	}

	public void onChange(World world, TragicMob entity, TragicMiniBoss boss, double par1, double par2, double par3) {

		if (!TragicNewConfig.allowKragul)
		{
			return;
		}

		boss.copyLocationAndAnglesFrom(this);
		boss.onSpawnWithEgg((IEntityLivingData)null);
		world.removeEntity(this);
		world.spawnEntityInWorld(boss);
		boss.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 2));
		boss.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 2));
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		EnumDifficulty dif = this.worldObj.difficultySetting;

		if (par1DamageSource.isProjectile() || par1DamageSource.isFireDamage())
		{
			return false;
		}

		if (dif == EnumDifficulty.EASY)
		{
			if (par2 < 2.5F)
			{
				return false;
			}
			par2 = 2.5F;

		}

		if (dif == EnumDifficulty.NORMAL)
		{
			if (par2 < 1.5F)
			{
				return false;
			}
			par2 = 1.5F;
		}

		if (dif == EnumDifficulty.HARD)
		{
			if (par2 < 1.0F)
			{
				return false;
			}
			par2 = 1.0F;
		}

		if (super.attackEntityFrom(par1DamageSource, par2))
		{
			this.hurtResistantTime = 60;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (!(par1Entity instanceof EntityLivingBase))
		{
			return false;
		}
		
		boolean result = par1Entity.attackEntityFrom(DamageHelper.causeSuffocationDamageFromMob(this), ((EntityLivingBase) par1Entity).getMaxHealth() / 10);

		if (result)
		{
			par1Entity.attackEntityFrom(DamageHelper.causeSuffocationDamageFromMob(this), ((EntityLivingBase) par1Entity).getMaxHealth() / 10);

			if (par1Entity.worldObj.difficultySetting == EnumDifficulty.HARD)
			{
				if (rand.nextInt(4) == 0)
				{
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 300));
				}

				if (rand.nextInt(8) == 0)
				{
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 300));
				}

			}
		}

		return result;
	}
}
