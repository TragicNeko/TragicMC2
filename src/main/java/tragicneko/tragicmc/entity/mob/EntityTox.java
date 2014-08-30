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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityMagmox;
import tragicneko.tragicmc.entity.boss.TragicMiniBoss;
import tragicneko.tragicmc.entity.projectile.EntitySpore;
import tragicneko.tragicmc.items.weapons.ItemScythe;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicNewConfig;

public class EntityTox extends TragicMob {
	
	private int firingTicks;
	protected boolean isFiring;

	public EntityTox(World par1World) {
		super(par1World);
		this.setSize(0.5F, 1.9F);
		this.stepHeight = 1.0F;
		this.experienceValue = 12;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 0.05D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 0.05D, 16.0F));
		this.tasks.addTask(5, new EntityAIWander(this, 0.7D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.canCorrupt = true;
		this.isCorruptible = true;
		this.isChangeable = true;
		this.superiorForm = new EntityMagmox(par1World);
	}
	
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.05);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}
	
	public void onLivingUpdate()
	{
		if (this.isPotionActive(Potion.poison.id))
		{
			this.removePotionEffect(Potion.poison.id);
		}
		
		super.onLivingUpdate();
		
		if (this.worldObj.isRemote) return;
		
		if (this.firingTicks > 0)
		{
			this.firingTicks--;
		}
		
		if (this.firingTicks > 0)
		{
			this.isFiring = true;
		}
		else
		{
			this.isFiring = false;
		}
		
		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && rand.nextInt(48) == 0 && this.firingTicks == 0)
		{
			this.firingTicks = 40;
		}
		
		if (this.firingTicks > 0 && this.firingTicks % 10 == 0 && this.getAttackTarget() != null && this.canEntityBeSeen(this.getAttackTarget()))
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

			EntitySpore fireball = new EntitySpore(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			fireball.posY = this.posY + (this.height * 2 / 3);
			this.worldObj.spawnEntityInWorld(fireball);
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemScythe)
			{
				par2 *= 1.5;
			}
		}
		
		if (par1DamageSource.isFireDamage())
		{
			par2 *= 2;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);

		if (result && par1Entity instanceof EntityLivingBase && rand.nextInt(16) == 0)
		{
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 600, 1));
		}

		return result;
	}
	
	public int getTotalArmorValue()
	{
		return this.isFiring ? 0 : 10;
		
	}
	
	public void onChange(World world, TragicMob entity, TragicMiniBoss boss, double par1, double par2, double par3) {

		if (!TragicNewConfig.allowMagmox)
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

}
