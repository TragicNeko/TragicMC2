package tragicneko.tragicmc.entity.mob;

import java.util.List;
import java.util.UUID;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.boss.TragicMiniBoss;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.EntityDropHelper;

public abstract class TragicMob extends EntityMob
{
	/**
	 * If this entity can be corrupted
	 */
	public boolean isCorruptible;

	/**
	 * If this entity can be changed into a superior form
	 */
	protected boolean isChangeable;

	/**
	 * If this entity is currently corrupted (inflicted with corruption effect)
	 */
	public boolean isCorrupted;

	/**
	 * Amount of ticks this entity has been corrupted
	 */
	protected int corruptionTicks;

	/**
	 * This mob's form that it will change into if it is changeable
	 */
	protected TragicMiniBoss superiorForm;

	/**
	 * If the mob can corrupt others
	 */
	public boolean canCorrupt;

	private static UUID victoryHealthUUID = UUID.fromString("971e53b4-421c-4095-bada-ea663c5668bf");
	private static AttributeModifier victoryHealthBuff = new AttributeModifier(victoryHealthUUID, "victoryHealthBuff", 10.0, 0);

	private static UUID victoryAttackUUID = UUID.fromString("289f9662-7c88-40e7-8584-efeede32b3db");
	private static AttributeModifier victoryAttackDamageBuff = new AttributeModifier(victoryAttackUUID, "victoryAttackDamageBuff", 3.0, 0);

	public TragicMob(World par1World) {
		super(par1World);
		this.isCorruptible = true;
		this.canCorrupt = true;
		this.isChangeable = false;
	}

	/**
	 * Returns if the mob is able to be corrupted
	 * @return isCorruptible
	 */
	protected boolean getCorruptible()
	{
		return this.isCorruptible;
	}

	/**
	 * Sets the mob to be corruptible, currently is set in the onLivingUpdate() method automatically
	 * @param flag
	 */
	private void setCorruptible(Boolean flag)
	{
		this.isCorruptible = flag;
	}

	/**
	 * Returns if the current mob is able to be converted into a Mini-Boss
	 * @return isChangeable
	 */
	protected boolean getChangeable()
	{
		return this.isChangeable;
	}

	/**
	 * Sets the mob to be changeable, currently is set in the onLivingUpdate() method automatically.
	 * @param flag
	 */
	protected void setChangeable(Boolean flag)
	{
		this.isChangeable = flag;
	}

	/**
	 * Used when a mob is currently inflicted with the Corruption effect, called each tick while corrupted.
	 */
	public void onCorruption() {}

	/**
	 * Called when a corrupted mob is to be changed into a Mini-Boss. Must be overidden by each changeable mob to actually do the change
	 * @param world
	 * @param entity
	 * @param boss
	 * @param par1
	 * @param par2
	 * @param par3
	 */
	public void onChange(World world, TragicMob entity, TragicMiniBoss boss, double par1, double par2, double par3) {}

	/**
	 * Called when a mob's corruption gets removed, must be called by an outside class to instigate changes to the entity
	 */
	public void onPurify() {}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.getAttackTarget() != null && this.getAttackTarget().isDead) this.setAttackTarget(null); 

		if (this.getAttackTarget() == null && this.isCorrupted && this.canCorrupt && TragicNewConfig.allowCorruption)
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
						if (!(entity instanceof EntityWither) && !(entity instanceof EntityDragon) && !(entity instanceof TragicBoss) && !(entity instanceof TragicMiniBoss) &&
								entity.getClass() != this.getClass())
						{
							if (entity instanceof TragicMob && entity != this && !(entity instanceof EntityPlague))
							{
								if (this.superiorForm != null && entity != this.superiorForm)
								{
									if (!(this instanceof TragicMiniBoss))
									{
										result = entity;
										break;
									}
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

		if (this.corruptionTicks < 0)
		{
			this.corruptionTicks = 0;
		}

		if (this.isCorruptible && TragicNewConfig.allowCorruption)
		{
			if (this.isPotionActive(TragicPotions.Corruption))
			{
				this.isCorrupted = true;
				this.onCorruption();
				this.corruptionTicks++;
			}
			else
			{
				this.isCorrupted = false;

				if (this.corruptionTicks > 0)
				{
					this.corruptionTicks--;
				}
			}

			if (this.corruptionTicks >= 100)
			{
				this.setChangeable(true);
			}
			else
			{
				this.setChangeable(false);
			}

			if (this.isChangeable && corruptionTicks >= 600 && this.rand.nextInt(100) <= TragicNewConfig.mobTransformationChance && this.ticksExisted % 20 == 0 && !this.worldObj.isRemote)
			{
				if (this.superiorForm != null)
				{
					this.change();
				}
			}
		}
		else
		{
			if (this.isChangeable && this.ticksExisted >= 6000 && this.rand.nextInt(128) == 0 && TragicNewConfig.allowMobTransformation && !this.worldObj.isRemote)
			{
				if (this.superiorForm != null)
				{
					this.change();
				}
			}
		}
	}

	/**
	 * Actually calls the onChange() method from the onLivingUpdate() method
	 */
	protected void change() 
	{
		this.onChange(this.worldObj, this, this.getSuperiorForm(), this.posX, this.boundingBox.minY, this.posZ);
	}

	/**
	 * Returns the Mini-Boss this mob should change in to, or null if it doesn't change
	 * @return
	 */
	protected TragicMiniBoss getSuperiorForm() {
		return this.superiorForm;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun) || this.worldObj.isRemote) return false; 

		Boolean result = super.attackEntityAsMob(par1Entity);

		if (result && TragicNewConfig.allowCorruption)
		{
			if (par1Entity instanceof TragicMob && ((TragicMob)par1Entity).isCorruptible && !((TragicMob)par1Entity).isCorrupted && this.rand.nextInt(4) == 0 && this.canCorrupt)
			{
				((TragicMob) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Corruption.id, 60, 1));
			}
			else if (par1Entity instanceof EntityCreature)
			{
				((EntityCreature)par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Corruption.id, 120, 1));
			}
		} 

		return result;
	}

	public boolean canAttackClass(Class par1Class)
	{
		return TragicBoss.class != par1Class;
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("CorruptionTicks", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("CorruptionTicks");
			this.corruptionTicks = b0;
		}
		if (par1NBTTagCompound.hasKey("IsCorrupted"))
		{
			Boolean flag = par1NBTTagCompound.getBoolean("IsCorrupted");
			this.isCorrupted = flag;
		}
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setByte("CorruptionTicks", (byte)this.corruptionTicks);
		par1NBTTagCompound.setBoolean("IsCorrupted", this.isCorrupted);
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	/**
	 * Faster way to get if mobGriefing is enabled
	 * @return
	 */
	public boolean getMobGriefing()
	{
		return this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
	}

	public void onDeath(DamageSource par1DamageSource)
	{		
		super.onDeath(par1DamageSource);
		
		if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot"))
		{
			int x = 1;

			if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

				if (player.getCurrentEquippedItem() != null)
				{
					ItemStack weapon = player.inventory.getCurrentItem();
					x += EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, weapon);
				}
			}

			int y = TragicNewConfig.commonDropRate;
			int z = TragicNewConfig.rareDropRate;
			int drops = 0;

			for (int i = 0; i < x; i++)
			{
				if (rand.nextInt(100) <= y + (x * 4))
				{
					ItemStack drop = EntityDropHelper.getCommonDropFromEntity(this.getClass());
					if (drop != null) this.entityDropItem(drop, 0.4F);
					drops++;
				}

				if (this.recentlyHit > 0 && rand.nextInt(100) <= z + x)
				{
					ItemStack drop = EntityDropHelper.getRareDropFromEntity(this.getClass());
					if (drop != null) this.entityDropItem(drop, 0.4F);
					drops++;
				}

				if (drops > x * 2) break;
			}
		}		
	}
	
	@Override
	public void onKillEntity(EntityLivingBase entity)
	{
		this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, (int) (entity.getMaxHealth() * 10), 2));
		this.addPotionEffect(new PotionEffect(Potion.resistance.id, (int) (entity.getMaxHealth() * 10), 2));
	}
}
