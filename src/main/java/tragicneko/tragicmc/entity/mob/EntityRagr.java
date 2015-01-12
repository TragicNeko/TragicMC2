package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.ragrStats;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBow;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.util.WorldHelper;

import com.google.common.collect.Sets;

public class EntityRagr extends TragicMob {

	public static Set crushableBlocks = Sets.newHashSet(new Block[] {Blocks.yellow_flower, Blocks.red_flower, Blocks.red_mushroom, Blocks.brown_mushroom, Blocks.tallgrass,
			Blocks.leaves});

	public EntityRagr(World par1World) {
		super(par1World);
		this.setSize(1.335F, 2.675F);
		this.stepHeight = 1.5F;
		this.experienceValue = 12;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityTameable.class, 0, true));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
	}

	public int getAngerTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	private void setAngerTicks(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	private void incrementAngerTicks()
	{
		int pow = this.getAngerTicks();
		this.setAngerTicks(++pow);
	}

	private void decrementAngerTicks()
	{
		int pow = this.getAngerTicks();
		this.setAngerTicks(--pow);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Beast;
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(ragrStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(ragrStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(ragrStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(ragrStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(ragrStats[4]);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (this.worldObj.isRemote) return;

		if (this.getAttackTarget() != null)
		{
			this.incrementAngerTicks();
			if (this.isCorrupted()) this.incrementAngerTicks();

			if (this.onGround && this.rand.nextInt(16) == 0)
			{
				double d0 = this.getAttackTarget().posX - this.posX;
				double d1 = this.getAttackTarget().posZ - this.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX = d0 / f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
				this.motionZ = d1 / f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
				this.motionY = 0.745D;
			}
			else if (this.getAngerTicks() >= 600)
			{
				if (this.getAngerTicks() % 50 == 0 && this.onGround)
				{
					double d0 = this.getAttackTarget().posX - this.posX;
					double d1 = this.getAttackTarget().posZ - this.posZ;
					float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
					this.motionX = d0 / f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
					this.motionZ = d1 / f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
					this.motionY = (rand.nextDouble() * 1.055) + 0.445;
				}

				if (this.getAngerTicks() >= 800) this.setAngerTicks(400);
			}
			else if (this.onGround && rand.nextBoolean())
			{
				double d0 = rand.nextDouble() * 1.45D - rand.nextDouble() * 1.45D;
				double d1 = rand.nextDouble() * 1.45D - rand.nextDouble() * 1.45D;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX = d0 / f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
				this.motionZ = d1 / f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
				this.motionY = 0.545D;
			}
		}
		else
		{
			if (this.getAngerTicks() > 0) this.decrementAngerTicks();

			if (this.onGround && rand.nextBoolean())
			{
				double d0 = rand.nextDouble() * 1.45D - rand.nextDouble() * 1.45D;
				double d1 = rand.nextDouble() * 1.45D - rand.nextDouble() * 1.45D;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX = d0 / f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
				this.motionZ = d1 / f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
				this.motionY = 0.545D;
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
			else
			{
				List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(16.0, 16.0, 16.0));

				for (int i = 0; i < list.size(); i++)
				{
					Entity entity = list.get(i);

					if (this.canEntityBeSeen(entity) && !(entity instanceof EntityWither) && !(entity instanceof EntityDragon) &&
							!(entity instanceof TragicBoss))
					{
						if (entity instanceof TragicMob)
						{
							result = entity;
							break;
						}
						else if (entity instanceof EntityAnimal)
						{
							result = entity;
							break;
						}
					}
				}

				this.setAttackTarget((EntityLivingBase) result);
			}
		}
	}

	@Override
	protected void fall(float par1) 
	{
		if (this.worldObj.isRemote) return;

		boolean flag = this.getMobGriefing();

		if (par1 >= 8.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 3.0F + 2.0F, flag);
		}
		else if (par1 >= 4.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 2.0F + 1.0F, flag);
		}
		else if (par1 >= 2.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 2.0F, flag);
		}

		if (!flag) return;

		int x = (int) this.posX;
		int y = (int) this.posY;
		int z = (int) this.posZ;		
		par1 = MathHelper.clamp_float(par1 / 2.0F, 1.0F, 4.0F);
		ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(worldObj, par1, x, y, z);
		int[] coords;
		Block block;

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			x = coords[0];
			y = coords[1];
			z = coords[2];
			block = this.worldObj.getBlock(x, y, z);

			if (EntityRagr.crushableBlocks.contains(block))
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

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	@Override
	public void setInWeb() {}

	@Override
	public int getTotalArmorValue()
	{
		return this.isBurning() ? 0 : (int) ragrStats[5];
	}

	@Override
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

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				if (!(player.getCurrentEquippedItem().getItem() instanceof ItemBow))
				{
					player.getCurrentEquippedItem().damageItem(rand.nextInt(4) + 1, player);
				}
			}
			else
			{
				player.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
			}
		}

		if (par1DamageSource.isProjectile()) par2 /= 2;

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;

		boolean result = super.attackEntityAsMob(par1Entity);
		if (result)
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
					if (TragicConfig.allowSubmission)
					{
						((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(200)));
					}
					break;
				}
			}

			if (!this.onGround)
			{
				par1Entity.motionX *= 1.8000000059604645D;
				par1Entity.motionZ *= 1.8D;
				par1Entity.motionY += 0.6D;
			}

		}
		return result;
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

}
