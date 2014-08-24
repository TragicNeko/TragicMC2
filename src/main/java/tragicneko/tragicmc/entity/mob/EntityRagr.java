package tragicneko.tragicmc.entity.mob;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
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
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBow;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.boss.TragicMiniBoss;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

import com.google.common.collect.Sets;

public class EntityRagr extends TragicMob {

	private int angerTicks;

	private Set crushableBlocks = Sets.newHashSet(new Block[] {Blocks.yellow_flower, Blocks.red_flower, Blocks.red_mushroom, Blocks.brown_mushroom, Blocks.tallgrass,
			Blocks.leaves});

	public EntityRagr(World par1World) {
		super(par1World);
		this.setSize(0.935F, 2.87F);
		this.stepHeight = 1.5F;
		this.experienceValue = 12;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(8, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(4, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityTameable.class, 0, true));
		this.canCorrupt = true;
		this.isCorruptible = true;
		this.isChangeable = false;
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Beast;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(65.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.38);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	public void onLivingUpdate()
	{
		if (this.entityToAttack != null)
		{
			this.angerTicks++;

			if (this.corruptionTicks > 0)
			{
				this.angerTicks++;
			}
		}
		else
		{
			if (angerTicks > 0)
			{
				this.angerTicks--;
			}
		}

		if (this.onGround && this.rand.nextInt(40) == 0 && this.getAttackTarget() != null)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / (double)f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
			this.motionZ = d1 / (double)f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
			this.motionY = 0.7;
		}

		if (this.angerTicks >= 600)
		{
			if (this.angerTicks % 50 == 0 && this.onGround && this.entityToAttack != null)
			{
				double d0 = entityToAttack.posX - this.posX;
				double d1 = entityToAttack.posZ - this.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX = d0 / (double)f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
				this.motionZ = d1 / (double)f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
				this.motionY = rand.nextDouble() + 0.4 + rand.nextDouble();
			}

			if (this.angerTicks % 20 == 0)
			{
				Entity entity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 10.0);

				if (entity == null)
				{
					angerTicks = 0;
				}
			}

			if (angerTicks >= 800)
			{
				angerTicks = 0;
			}
		}

		if (this.getAttackTarget() == null)
		{
			EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

			Entity result = null;

			if (entityplayer != null && this.canEntityBeSeen(entityplayer))
			{
				result = entityplayer;
			}

			if (result == null)
			{
				List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(16.0, 16.0, 16.0));

				for (int i = 0; i < list.size(); i++)
				{
					Entity entity = list.get(i);

					if (this.canEntityBeSeen(entity) && entity != this)
					{

						if (!(entity instanceof EntityWither) && !(entity instanceof EntityDragon) && !(entity instanceof TragicBoss))
						{
							if (entity instanceof TragicMob && entity != this)
							{
								if (entity instanceof TragicMiniBoss && this.superiorForm != null && entity == this.superiorForm)
								{

								}
								else
								{
									result = entity;
									break;
								}
							}
							else if (entity instanceof EntityCreature)
							{
								result = entity;
								break;
							}
							else if (entity instanceof EntityPlayer)
							{
								break;
							}
						}
					}
				}

				if (result != null)
				{
					this.setAttackTarget((EntityLivingBase) result);
				}
			}
			else
			{
				this.setAttackTarget((EntityLivingBase) result);
			}
		}

		super.onLivingUpdate();
	}

	protected void fall(float par1) 
	{
		boolean flag = this.getMobGriefing();

		if (par1 >= 2.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 2.0F, false);
		}
		else if (par1 >= 4.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 2.0F + 1.0F, flag);
		}
		else if (par1 >= 8.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 3.0F + 2.0F, flag);
		}
		
		if (!flag)
		{
			return;
		}

		int x = (int) this.posX - 1;
		int y = (int) (this.posY - 1);
		int z = (int) this.posZ - 1;

		if (par1 >= 4.0F)
		{
			x--;
			z--;

			for (int x1 = 0; x1 < 5; x1++)
			{
				for (int z1 = 0; z1 < 5; z1++)
				{
					Block block = this.worldObj.getBlock(x + x1, y, z + z1);
					boolean flag2 = false;
					
					if (x1 != 0 && z1 != 0)
					{
						flag2 = true;
					}
					
					if (x1 != 4 && z1 != 4)
					{
						flag2 = true;
					}
					
					if (flag2)
					{
						if (this.crushableBlocks.contains(block))
						{
							this.worldObj.setBlockToAir(x + x1, y, z + z1);
						}
						else if (block == Blocks.grass)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.dirt);
						}
						else if (block == Blocks.stone)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.cobblestone);
						}
						else if (block == Blocks.stonebrick)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.stonebrick, 2, 2);
						}
						else if (block == Blocks.cobblestone)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.gravel);
						}
					}
				}
			}
		}
		else if (par1 >= 3.0F)
		{
			for (int x1 = 0; x1 < 3; x1++)
			{
				for (int z1 = 0; z1 < 3; z1++)
				{
					Block block = this.worldObj.getBlock(x + x1, y, z + z1);
					boolean flag2 = false;
					
					if (x1 != 0 && z1 != 0)
					{
						flag2 = true;
					}
					
					if (x1 != 2 && z1 != 2)
					{
						flag2 = true;
					}
					
					if (flag2)
					{
						if (this.crushableBlocks.contains(block))
						{
							this.worldObj.setBlockToAir(x + x1, y, z + z1);
						}
						else if (block == Blocks.grass)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.dirt);
						}
						else if (block == Blocks.stone)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.cobblestone);
						}
						else if (block == Blocks.stonebrick)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.stonebrick, 2, 2);
						}
						else if (block == Blocks.cobblestone)
						{
							this.worldObj.setBlock(x + x1, y, z + z1, Blocks.gravel);
						}
					}
				}
			}
		}
		else if (par1 >= 2.0F)
		{
			Block block = this.worldObj.getBlock(x, y, z);

			if (this.crushableBlocks.contains(block))
			{
				this.worldObj.setBlockToAir(x, y, z);
			}
			else if (block == Blocks.grass)
			{
				this.worldObj.setBlock(x, y, z, Blocks.dirt);
			}
			else if (block == Blocks.stone)
			{
				this.worldObj.setBlock(x, y, z, Blocks.cobblestone);
			}
			else if (block == Blocks.stonebrick)
			{
				this.worldObj.setBlock(x, y, z, Blocks.stonebrick, 2, 2);
			}
			else if (block == Blocks.cobblestone)
			{
				this.worldObj.setBlock(x, y, z, Blocks.gravel);
			}
		}
	}

	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	public void setInWeb() {}

	public int getTotalArmorValue()
	{
		if (this.isBurning())
		{
			return 0;
		}

		if (TragicNewConfig.allowCorruption && this.isPotionActive(TragicPotions.Corruption))
		{
			return 20;
		}
		return 10;
	}

	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.boundingBox.minY);

		if (i <= 63 && !(this.worldObj.provider instanceof TragicWorldProvider))
		{
			return false;
		}
		else if (i > 63 || this.worldObj.provider instanceof TragicWorldProvider)
		{
			int j = MathHelper.floor_double(this.posX);
			int k = MathHelper.floor_double(this.posZ);
			int l = this.worldObj.getBlockLightValue(j, i, k);
			byte b0 = 4;
			Calendar calendar = this.worldObj.getCurrentDate();

			if ((calendar.get(2) + 1 != 10 || calendar.get(5) < 20) && (calendar.get(2) + 1 != 11 || calendar.get(5) > 3))
			{
				if (this.rand.nextBoolean())
				{
					return false;
				}
			}
			else
			{
				b0 = 7;
			}

			return l > this.rand.nextInt(b0) ? false : super.getCanSpawnHere();
		}
		else
		{
			return false;
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				if (!(player.getCurrentEquippedItem().getItem() instanceof ItemBow))
				{
					player.getCurrentEquippedItem().damageItem(rand.nextInt(5) + 1, player);
				}
			}
			else
			{
				player.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
			}
		}

		if (par1DamageSource.isProjectile())
		{
			par2 /= 2;
		}

		Boolean result = super.attackEntityFrom(par1DamageSource, par2);

		return result;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity instanceof EntityLivingBase && rand.nextInt(4) == 0)
			{
				switch(rand.nextInt(3))
				{
				case 0:
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200)));
					break;
				case 1:
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200)));
					break;
				case 2:
					if (TragicNewConfig.allowSubmission)
					{
						((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, rand.nextInt(200)));
					}
					break;
				}
			}

			par1Entity.motionX *= 1.8000000059604645D;
			par1Entity.motionZ *= 1.8D;
			par1Entity.motionY += 0.6D;

		}
		return super.attackEntityAsMob(par1Entity);
	}

}
