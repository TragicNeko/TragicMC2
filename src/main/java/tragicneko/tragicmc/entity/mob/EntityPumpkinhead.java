package tragicneko.tragicmc.entity.mob;

import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.main.TragicEntities;

public class EntityPumpkinhead extends TragicMob {

	private int angerTicks;
	private BlockPumpkin homePumpkin;
	private double hasteValue = 2.0D;

	public EntityPumpkinhead(World par1World) {
		super(par1World);
		this.setSize(0.6F, 2.2F);
		this.experienceValue = 6;
		this.getNavigator().setAvoidSun(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIFleeSun(this, 1.2D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityCreature.class, 32.0F));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.55D));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true)); /*
		this.canCorrupt = false;
		this.isCorruptible = false;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(75.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.24);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
	}
	
	public void onLivingUpdate()
	{
		AttributeModifier mod = new AttributeModifier(UUID.fromString("2042ddcd-b29a-474f-acda-00ec1a2b4a2e"), "pumpkinheadHaste", this.hasteValue, 0);
		
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(mod);
		
		if (this.isBurning())
		{
			this.attackEntityFrom(DamageSource.onFire, 3.0F);
		}
		else if (this.ticksExisted % 120 == 0 && this.getHealth() < this.getMaxHealth())
		{
			this.heal(1.0F);
		}

		if (this.getAttackTarget() != null)
		{
			this.angerTicks++;
		}
		else
		{
			this.angerTicks = 0;
		}

		if (angerTicks % 300 == 0 && this.getAttackTarget() != null && this.isHomePumpkinStillThere())
		{
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(mod);
			this.hasteValue += 2.0D;
		}

		if (!(this.worldObj.getBlock(this.getHomePosition().posX, this.getHomePosition().posY, this.getHomePosition().posZ) instanceof BlockPumpkin) &&
				this.homePumpkin != null)
		{
			this.homeDestroyed();
		}

		if (this.homePumpkin != null)
		{
			double d0 = this.getHomePosition().posX - this.posX;
			double d1 = this.height * 2 / 3;
			double d2 = this.getHomePosition().posX - this.posX;

			for (int i = 0; i < 4; i++)
			{
				this.worldObj.spawnParticle("townaura", this.posX, d1, this.posZ, d0, 0.0, d2);
			}
		}

		if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
		{
			float f = this.getBrightness(1.0F);

			if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), 
					MathHelper.floor_double(this.posY),
					MathHelper.floor_double(this.posZ)))
			{
				this.setFire(8);
			}
		}

		if (this.getHealth() <= this.getMaxHealth() / 4 && this.isHomePumpkinStillThere() && rand.nextInt(32) == 0 && !this.worldObj.isRemote && !this.isBurning())
		{
			for (int x = 0; x < 10; x++)
			{
				EntityPumpkinbomb bomb = new EntityPumpkinbomb(this.worldObj, this);

				bomb.setPosition(this.posX + MathHelper.getRandomIntegerInRange(rand, -4, 4), this.posY + 2.5, this.posZ + MathHelper.getRandomIntegerInRange(rand, -4, 4));
				this.worldObj.spawnEntityInWorld(bomb);
			}
		}

		super.onLivingUpdate();
	}

	public int getTotalArmorValue()
	{
		if (this.isHomePumpkinStillThere())
		{
			return 15;
		}
		return 5;
	}

	/**
	 * Returns if there is a pumpkin nearby to set as a home, also sets it as it's home so this can be called without the intention of returning a boolean value
	 * @return
	 */
	public boolean isPumpkinNearby()
	{
		int x = MathHelper.floor_double(posX) - 5;
		int y = MathHelper.floor_double(posY) - 5;
		int z = MathHelper.floor_double(posZ) - 5;

		for (int y1 = 0; y1 < 11; y1++)
		{
			for (int z1 = 0; z1 < 11; z1++)
			{
				for (int x1 = 0; x1 < 11; x1++)
				{
					Block block = worldObj.getBlock(x + x1, y, z);
					if (block instanceof BlockPumpkin)
					{
						this.setHomeArea(x + x1, y, z, 10);
						this.setHomePumpkin(x + x1, y, z);
						return true;
					}
				}
				z++;
			}
			z = MathHelper.floor_double(this.posZ) - 5;
			y++;
		}
		return false;
	}

	public void setHomePumpkin(int par1, int par2, int par3)
	{
		if (this.worldObj.getBlock(par1, par2, par3) instanceof BlockPumpkin)
		{
			this.homePumpkin = (BlockPumpkin) this.worldObj.getBlock(par1, par2, par3);
		}
	}

	public BlockPumpkin getHomePumpkin()
	{
		return this.homePumpkin;
	}

	public boolean isHomePumpkinStillThere()
	{
		return getHomePumpkin() != null;
	}

	public void homeDestroyed()
	{
		this.detachHome();
		this.homePumpkin = null;
	}

	public boolean getCanSpawnHere()
	{
		return super.getCanSpawnHere() && this.isPumpkinNearby();
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase)
		{
			double d0 = 4.0;
			d0 *= par2;

			if (d0 > 32.0)
			{
				d0 = 32.0;
			}

			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(d0, d0, d0));

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityPumpkinhead)
				{
					((EntityPumpkinhead)list.get(i)).setAttackTarget((EntityLivingBase) par1DamageSource.getEntity());
				}
			}
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		if (!this.isPumpkinNearby())
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
						
						if (!(block instanceof BlockPumpkin) && rand.nextInt(10) == 0 && 
								this.worldObj.isSideSolid(x + x1, y - 1, z, ForgeDirection.UP))
						{
							if (this.worldObj.isAirBlock(x + x1, y, z) || block instanceof BlockTallGrass ||
									block instanceof BlockFlower || block instanceof BlockSnow || block instanceof BlockLeaves)
							{
								this.worldObj.setBlock(x + x1, y, z, Blocks.pumpkin);
								this.setHomeArea(x + x1, y, z, 10);
								this.setHomePumpkin(x + x1, y, z);
								return super.onSpawnWithEgg(data);
							}
						}
						else if (block instanceof BlockPumpkin)
						{
							this.setHomeArea(x + x1, y, z, 10);
							this.setHomePumpkin(x + x1, y, z);
							return super.onSpawnWithEgg(data);
						}
					}
					z++;
				}
				z = MathHelper.floor_double(this.posZ) - 3;
				y++;
			}
		}
		
		return super.onSpawnWithEgg(data);
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

}
