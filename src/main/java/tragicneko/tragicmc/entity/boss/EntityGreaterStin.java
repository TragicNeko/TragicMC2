package tragicneko.tragicmc.entity.boss;

import java.util.List;

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
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EntityGreaterStin extends TragicMob implements TragicMiniBoss {

	public EntityGreaterStin(World par1World) {
		super(par1World);
		this.setSize(1.15F, 3.45F);
		this.experienceValue = 15;
		this.getNavigator().setCanSwim(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 16.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 16.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
		this.superiorForm = this.rand.nextBoolean() && TragicNewConfig.allowStinKing ? new EntityStinKing(this.worldObj) : (TragicNewConfig.allowStinQueen ? new EntityStinQueen(this.worldObj) : null);
		this.stepHeight = 1.5F;
	}
	
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.276);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(14.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	public int getTotalArmorValue()
	{
		return 12;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		boolean flag = super.attackEntityFrom(par1DamageSource, par2);
		
		if (flag)
		{
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(8.0, 8.0, 8.0));
			TragicMob mob;

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof TragicMob)
				{
					mob = (TragicMob) list.get(i);

					if (mob instanceof EntityStin)
					{
						if (mob.getAttackTarget() != this.getAttackTarget()) mob.setTarget(this.getAttackTarget());
					}
				}
			}
		}

		if (flag && par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase && !this.worldObj.isRemote && !par1DamageSource.isProjectile() &&
				rand.nextInt(16) == 0)
		{
			EntityLivingBase entity = (EntityLivingBase) par1DamageSource.getEntity();
			double x = entity.posX;
			double y = entity.posY;
			double z = entity.posZ;

			for (int y1 = 0; y1 < 24; y1++)
			{
				for (int z1 = -8; z1 < 9; z1++)
				{
					for (int x1 = -8; x1 < 9; x1++)
					{
						if (World.doesBlockHaveSolidTopSurface(this.worldObj, (int)this.posX + x1, (int)this.posY + y1 - 1, (int)this.posZ + z1) && rand.nextBoolean())
						{
							if (entity instanceof EntityPlayerMP)
							{
								EntityPlayerMP mp = (EntityPlayerMP) entity;

								if (mp.capabilities.isCreativeMode) return flag;

								if (mp.playerNetServerHandler.func_147362_b().isChannelOpen() && this.worldObj == mp.worldObj)
								{
									if (mp.isRiding()) mp.mountEntity(null);
									AxisAlignedBB bb = mp.boundingBox.copy();
									bb.offset(x + x1, y + y1, z + z1);

									if (this.worldObj.checkNoEntityCollision(bb) && this.worldObj.getCollidingBoundingBoxes(mp, bb).isEmpty() &&
											!this.worldObj.isAnyLiquid(bb))
									{
										mp.playerNetServerHandler.setPlayerLocation(x + x1, y + y1, z + z1, mp.rotationYaw, mp.rotationPitch);
										short short1 = 128;

										for (int l = 0; l < short1; ++l)
										{
											double d6 = (double)l / ((double)short1 - 1.0D);
											float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
											float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
											float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
											double d7 = x + ((x + x1) - x) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
											double d8 = y + ((y + y1) - y) * d6 + this.rand.nextDouble() * (double)this.height;
											double d9 = z + ((z + z1) - z) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
											this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
										}
										mp.addPotionEffect(new PotionEffect(Potion.blindness.id, 200, 0));
										mp.fallDistance = 0.0F;
										this.worldObj.playSoundAtEntity(mp, "mob.endermen.portal", 0.4F, 0.4F);
										return flag;
									}
								}
							}
							else
							{
								entity.setPosition(x + x1, y + y1, z + z1);

								if (this.worldObj.checkNoEntityCollision(entity.boundingBox) &&
										this.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox).isEmpty() &&
										!this.worldObj.isAnyLiquid(entity.boundingBox))
								{
									short short1 = 128;

									for (int l = 0; l < short1; ++l)
									{
										double d6 = (double)l / ((double)short1 - 1.0D);
										float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
										float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
										float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
										double d7 = x + ((x + x1) - x) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
										double d8 = y + ((y + y1) - y) * d6 + this.rand.nextDouble() * (double)this.height;
										double d9 = z + ((z + z1) - z) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
										this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
									}

									this.worldObj.playSoundAtEntity(entity, "mob.endermen.portal", 0.4F, 0.4F);
									entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 200, 0));
									return flag;
								}
								else
								{
									entity.setPosition(x, y, z);
								}
							}
						}
					}
				}
			}
		}

		return flag;
	}
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean flag = super.attackEntityAsMob(par1Entity);
		
		if (flag && rand.nextBoolean() && par1Entity instanceof EntityLivingBase)
		{
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 300, 0));
			if (TragicNewConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 300, 1 + rand.nextInt(3)));
		}
		
		if (flag)
		{
			par1Entity.motionY += 1.222543D;
			par1Entity.motionX *= 1.45D;
			par1Entity.motionZ *= 1.45D;
		}
		
		return flag;
	}

	@Override
	protected boolean isChangeAllowed() {
		return this.superiorForm instanceof EntityStinKing ? TragicNewConfig.allowStinKing : TragicNewConfig.allowStinQueen;
	}

	@Override
	public Class getLesserForm() {
		return EntityStin.class;
	}

}
