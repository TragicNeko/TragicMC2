package tragicneko.tragicmc.entity.mob;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicItems;

public class EntityInkling extends TragicMob {

	private int summonTicks;
	private int visibleTicks;

	public EntityInkling(World par1World) {
		super(par1World);
		this.setSize(0.3F, 1.4F);
		this.experienceValue = 2;
		this.getNavigator().setBreakDoors(true);
		this.getNavigator().setAvoidSun(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIFleeSun(this, 1.2D));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 0.8D, 32.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));/*
		this.canCorrupt = true;
		this.isCorruptible = true;
		this.isChangeable = false; */
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(24.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.23);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
	}

	public void onLivingUpdate()
	{
		if (!this.worldObj.isRemote)
		{
			if (this.visibleTicks > 0)
			{
				this.visibleTicks--;
			}
			else
			{
				if (this.getAttackTarget() != null && this.visibleTicks == 0)
				{
					this.setInvisible(true);
				}
			}
		}

		if (this.visibleTicks > 0)
		{
			this.setInvisible(false);
		}

		if (this.isInvisible())
		{
			this.setSprinting(true);
		}
		else
		{
			this.setSprinting(false);
		}

		if (!this.worldObj.isRemote)
		{
			if (this.isBurning() && rand.nextInt(16) == 0 && this.worldObj.getBlockLightValue((int)this.posX, (int)this.posY + 1, (int)this.posZ) >= 8)
			{
				this.teleportRandomly();
			}

			if (this.worldObj.getBlockLightValue((int)this.posX, (int)this.posY + 1, (int)this.posZ) >= 8)
			{
				if (!this.isBurning() && this.ticksExisted % 10 == 0)
				{
					this.setFire(4);
				}
				this.setInvisible(false);

				if (this.ticksExisted % 20 == 0)
				{
					int x = MathHelper.floor_double(posX) - 3;
					int y = MathHelper.floor_double(posY) - 3;
					int z = MathHelper.floor_double(posZ) - 3;

					PathEntity path = null;

					for (int y1 = 0; y1 < 6; y1++)
					{
						for (int z1 = 0; z1 < 6; z1++)
						{
							for (int x1 = 0; x1 < 6; x1++)
							{
								if (this.worldObj.getBlockLightValue(x + x1, y, z) <= 6)
								{
									path = this.getNavigator().getPathToXYZ(x + x1, y, z);
									break;
								}
							}
							z++;
						}
						z = MathHelper.floor_double(this.posZ) - 3;
						y++;
					}

					if (path != null)
					{
						this.getNavigator().setPath(path, .6);
						this.setAttackTarget(null);
					}
				}
			}
		}

		if (this.getAttackTarget() != null && this.ticksExisted % 60 == 0)
		{
			if (!this.worldObj.isRemote)
			{
				this.summonTicks++;
				
				if (this.summonTicks >= 5 && this.visibleTicks == 0)
				{
					this.summonTicks = 0;

					if (rand.nextInt(10) == 0)
					{
						//this.attemptToSummonHelp();
					}
				}
			}
		}

		

		if (this.ticksExisted % 20 == 0 && rand.nextInt(8) == 0 && this.getAttackTarget() != null 
				&& this.worldObj.getBlockLightValue((int)this.getAttackTarget().posX, (int)this.getAttackTarget().posY, (int)this.getAttackTarget().posZ) <= 8 &&
				this.getDistanceToEntity(this.getAttackTarget()) > 10.0F)
		{
			this.teleportToEntity(this.getAttackTarget());
		}

		if (this.ticksExisted % 60 == 0 && this.getMobGriefing())
		{
			int x = MathHelper.floor_double(posX) - 3;
			int y = MathHelper.floor_double(posY) - 3;
			int z = MathHelper.floor_double(posZ) - 3;

			for (int y1 = 0; y1 < 7; y1++)
			{
				for (int z1 = 0; z1 < 7; z1++)
				{
					for (int x1 = 0; x1 < 7; x1++)
					{
						Block block = worldObj.getBlock(x + x1, y, z);
						if (block instanceof BlockTorch && this.rand.nextInt(3) == 0)
						{
							worldObj.setBlockToAir(x + x1, y, z);
						}
					}
					z++;
				}
				z = MathHelper.floor_double(this.posZ) - 5;
				y++;
			}
		}

		super.onLivingUpdate();
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase &&
				this.rand.nextInt(4) == 0 && super.attackEntityFrom(par1DamageSource, par2))
		{
			if (!par1DamageSource.isFireDamage() && rand.nextInt(4) == 0 && this.getAttackTarget() != null)
			{
				//this.attemptToSummonHelp();
			}
		}

		if (par1DamageSource.isFireDamage() && rand.nextInt(3) == 0)
		{
			this.teleportRandomly();
		}

		if (this.visibleTicks == 0 && !this.worldObj.isRemote)
		{
			this.visibleTicks = 10 + rand.nextInt(10);
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity) && this.visibleTicks == 0)
		{
			this.visibleTicks = 10;
		}
		return super.attackEntityAsMob(par1Entity);
	}

	public int getTotalArmorValue()
	{
		if (this.isBurning())
		{
			return 0;
		}

		return 4;
	}

	private void attemptToSummonHelp() 
	{
		List<Entity> list = this.worldObj.getEntitiesWithinAABB(EntityInkling.class, boundingBox.expand(32.0, 32.0, 32.0));		

		if (list.size() >= 3)
		{
			return;
		}
		EntityInkling inkling = new EntityInkling(this.worldObj);

		EntityLivingBase entitylivingbase = this.getAttackTarget();

		if (entitylivingbase == null)
		{
			return;
		}

		int x = MathHelper.floor_double(posX) - 3;
		int y = MathHelper.floor_double(posY) - 3;
		int z = MathHelper.floor_double(posZ) - 3;

		for (int y1 = 0; y1 < 7; y1++)
		{
			for (int z1 = 0; z1 < 7; z1++)
			{
				for (int x1 = 0; x1 < 7; x1++)
				{
					if (World.doesBlockHaveSolidTopSurface(this.worldObj, x + x1, y - 1, z) && this.worldObj.getBlockLightValue(x + x1, y, z) <= 8)
					{
						inkling.setPosition((double)x + x1, (double)y, (double)z);

						if (this.worldObj.checkNoEntityCollision(inkling.boundingBox) &&
								this.worldObj.getCollidingBoundingBoxes(inkling, inkling.boundingBox).isEmpty() &&
								!this.worldObj.isAnyLiquid(inkling.boundingBox))
						{
							this.worldObj.spawnEntityInWorld(inkling);

							if (entitylivingbase != null) 
							{
								inkling.setAttackTarget(entitylivingbase);
							}
							break;
						}
					}
				}
				z++;
			}
			z = MathHelper.floor_double(this.posZ) - 3;
			y++;
		}

	}

	protected boolean teleportRandomly()
	{
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 24.0D;
		double d1 = this.posY + (double)(this.rand.nextInt(64) - 32);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 24.0D;
		return this.teleportTo(d0, d1, d2);
	}

	protected boolean teleportToEntity(Entity par1Entity)
	{
		Vec3 vec3 = Vec3.createVectorHelper(this.posX - par1Entity.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - par1Entity.posY + (double)par1Entity.getEyeHeight(), this.posZ - par1Entity.posZ);
		vec3 = vec3.normalize();
		double d0 = 16.0D;
		double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
		double d2 = this.posY + (double)(this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
		double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
		return this.teleportTo(d1, d2, d3);
	}

	protected boolean teleportTo(double par1, double par3, double par5)
	{
		double d3 = this.posX;
		double d4 = this.posY;
		double d5 = this.posZ;
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		boolean flag = false;
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);

		boolean flag2 = false;

		if (this.worldObj.getBlockLightValue(i, j, k) <= 8)
		{
			flag2 = true;
		}

		if (this.worldObj.blockExists(i, j, k) && flag2)
		{
			boolean flag1 = false;

			while (!flag1 && j > 0)
			{
				Block block = this.worldObj.getBlock(i, j - 1, k);

				if (block.getMaterial().blocksMovement())
				{
					flag1 = true;
				}
				else
				{
					--this.posY;
					--j;
				}
			}

			if (flag1)
			{
				this.setPosition(this.posX, this.posY, this.posZ);

				if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
				{
					flag = true;
				}
			}
		}

		if (!flag)
		{
			this.setPosition(d3, d4, d5);
			return false;
		}
		else
		{
			/*
            short short1 = 128;

            for (int l = 0; l < short1; ++l)
            {
                double d6 = (double)l / ((double)short1 - 1.0D);
                float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
                double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                this.worldObj.spawnParticle("smoke", d7, d8, d9, (double)f, (double)f1, (double)f2);
            }
			 */
			this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
			this.playSound("mob.endermen.portal", 1.0F, 1.0F);
			return true;
		}
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

}
