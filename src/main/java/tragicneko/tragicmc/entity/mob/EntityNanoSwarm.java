package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicNewConfig.nanoSwarmStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotion;

public class EntityNanoSwarm extends TragicMob {

	public EntityNanoSwarm(World par1World) {
		super(par1World);
		this.setSize(0.45F, 1.45F);
		this.stepHeight = 1.0F;
		this.experienceValue = 2;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(nanoSwarmStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(nanoSwarmStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(nanoSwarmStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(nanoSwarmStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(nanoSwarmStats[4]);
	}
	
	@Override
	public int getTotalArmorValue()
	{
		return (int) nanoSwarmStats[5];
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}
	
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		if (this.worldObj.isRemote)
		{
			for (int l = 0; l < 2; ++l)
			{
				this.worldObj.spawnParticle("enchantmenttable",
						this.posX + (this.rand.nextDouble() - rand.nextDouble()) * (double)this.width * 1.5D,
						this.posY + this.rand.nextDouble() * (double)this.height + 0.15D,
						this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * (double)this.width * 1.5D,
						(this.rand.nextDouble() - 0.6D) * 0.1D,
						this.rand.nextDouble() * 0.1D,
						(this.rand.nextDouble() - 0.6D) * 0.1D);
			} 
		}
		
		if (this.motionY < 0 && !this.onGround) this.motionY *= 0.65D;
	}
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.worldObj.isRemote) return false;

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase && !par1DamageSource.isProjectile() && !par1DamageSource.isMagicDamage())
		{
			EntityLivingBase entity = (EntityLivingBase) par1DamageSource.getEntity();

			if (entity.getEquipmentInSlot(0) != null)
			{
				if (!(entity.getEquipmentInSlot(0).getItem() instanceof ItemBow) && this.getDistanceToEntity(entity) <= 2.0F)
				{
					entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
					if (TragicNewConfig.allowHacked) entity.addPotionEffect(new PotionEffect(TragicPotion.Hacked.id, 120 + rand.nextInt(180)));
					return super.attackEntityFrom(par1DamageSource, Float.MAX_VALUE);
				}
			}
			else
			{
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
				if (TragicNewConfig.allowHacked) entity.addPotionEffect(new PotionEffect(TragicPotion.Hacked.id, 120 + rand.nextInt(80)));
				return super.attackEntityFrom(par1DamageSource, Float.MAX_VALUE);
			}
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;
		
		boolean result = super.attackEntityAsMob(par1Entity);
		
		if (result && par1Entity instanceof EntityLivingBase)
		{
			this.setDead();
			if (TragicNewConfig.allowHacked) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Hacked.id, 240 + rand.nextInt(160)));
		}
		
		return result;
	}

	public void fall(float par1){}

	public void updateFallState(double par1, boolean par2) {}
}
