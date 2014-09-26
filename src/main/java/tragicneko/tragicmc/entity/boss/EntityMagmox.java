package tragicneko.tragicmc.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.items.weapons.ItemScythe;
import tragicneko.tragicmc.main.TragicEntities;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityMagmox extends TragicMob implements TragicMiniBoss {
	
	private int firingTicks;
	private boolean isFiring;

	public EntityMagmox(World par1World) {
		super(par1World);
		this.setSize(0.67F, 2.7F);
		this.stepHeight = 1.0F;
		this.experienceValue = 12;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 0.05D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 0.05D, 32.0F));
		this.tasks.addTask(5, new EntityAIWander(this, 0.7D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.isImmuneToFire = true;
	}

	public boolean isAIEnabled()
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean canRenderOnFire()
	{
		return false;
	}
	
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(75.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.05);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}
	
	public void onLivingUpdate()
	{
		if (this.isPotionActive(Potion.poison)) this.removePotionEffect(Potion.poison.id); 
		
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
		
		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 2.0F && rand.nextInt(48) == 0 && this.firingTicks == 0)
		{
			this.firingTicks = 160;
		}
		
		if (this.firingTicks > 0 && this.firingTicks % 20 == 0 && this.getAttackTarget() != null && this.canEntityBeSeen(this.getAttackTarget()))
		{			
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

			EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
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

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);

		if (result && par1Entity instanceof EntityLivingBase && rand.nextInt(16) == 0)
		{
			par1Entity.setFire(16);
		}

		return result;
	}
	
	public int getTotalArmorValue()
	{
		return this.isFiring ? 5 : 20;
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}
	
	@Override
	public Class getLesserForm() {
		return EntityTox.class;
	}
}
