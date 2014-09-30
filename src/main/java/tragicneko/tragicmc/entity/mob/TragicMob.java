package tragicneko.tragicmc.entity.mob;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityGreaterStin;
import tragicneko.tragicmc.entity.boss.EntityJarra;
import tragicneko.tragicmc.entity.boss.EntityKragul;
import tragicneko.tragicmc.entity.boss.EntityMagmox;
import tragicneko.tragicmc.entity.boss.EntityMegaCryse;
import tragicneko.tragicmc.entity.boss.EntityStinKing;
import tragicneko.tragicmc.entity.boss.EntityStinQueen;
import tragicneko.tragicmc.entity.boss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.boss.TragicMiniBoss;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.EntityDropHelper;

public abstract class TragicMob extends EntityMob
{
	protected TragicMiniBoss superiorForm;

	public TragicMob(World par1World) {
		super(par1World);
	}

	protected boolean canCorrupt()
	{
		return true;
	}

	protected boolean canChange()
	{
		return this.superiorForm != null && TragicNewConfig.allowMobTransformation;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(15, Integer.valueOf(0));
	}

	public int getCorruptionTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(15);
	}

	protected void setCorruptionTicks(int i)
	{
		this.dataWatcher.updateObject(15, i);
	}

	protected void incrementCorruptionTicks()
	{
		int pow = this.getCorruptionTicks();
		this.setCorruptionTicks(++pow);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.getAttackTarget() != null && this.getAttackTarget().isDead) this.setAttackTarget(null); 
		if (this.getAttackTarget() != null)
		{
			if (this.getAttackTarget().getClass() == this.getLesserForm() || this.getAttackTarget() == this.superiorForm) this.setAttackTarget(null);
			if (this.getAttackTarget().getClass() == this.getClass() && !(this instanceof EntityRagr)) this.setAttackTarget(null);
		}

		if (this.getAttackTarget() == null && this.canCorrupt() && TragicNewConfig.allowCorruption && this.isPotionActive(TragicPotions.Corruption.id))
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

					if (this.canEntityBeSeen(entity) && entity != this)
					{
						if (!(entity instanceof EntityWither) && !(entity instanceof EntityDragon) && !(entity instanceof TragicBoss) && entity.getClass() != this.getClass())
						{
							if (entity instanceof TragicMob)
							{
								if (this.superiorForm != null && entity != this.superiorForm && entity.getClass() != this.getLesserForm())
								{
									result = entity;
									break;
								}
								else if (entity.getClass() != this.getLesserForm() && this.superiorForm == null)
								{
									result = entity;
									break;
								}
							}
							else if (entity instanceof EntityAnimal)
							{
								result = entity;
								break;
							}
						}
					}
				}

				this.setAttackTarget((EntityLivingBase) result);
			}
		}

		if (TragicNewConfig.allowCorruption)
		{
			if (this.isPotionActive(TragicPotions.Corruption))
			{
				this.incrementCorruptionTicks();
			}
			else
			{
				this.setCorruptionTicks(0);
			}

			if (this.canChange() && this.getCorruptionTicks() >= 600 && this.rand.nextInt(100) <= TragicNewConfig.mobTransformationChance && this.ticksExisted % 20 == 0 && rand.nextInt(4) == 0)
			{
				this.change();
			}
		}
		else if (this.canChange() && this.ticksExisted >= 6000 && this.ticksExisted % 20 == 0 && this.rand.nextInt(100) <= TragicNewConfig.mobTransformationChance)
		{
			this.change();
		}
	}

	protected void change() 
	{
		if (this.isChangeAllowed())
		{
			TragicMob boss = (TragicMob) this.getSuperiorForm();
			boss.copyDataFrom(this, true);
			boss.copyLocationAndAnglesFrom(this);
			this.worldObj.removeEntity(this);
			this.worldObj.spawnEntityInWorld(boss);
			boss.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 2));
			boss.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 2));
		}
	}

	protected TragicMiniBoss getSuperiorForm() {
		return this.superiorForm;
	}

	/**
	 * This needs to be overriden by each class to specify whether their superior form is allowed via the config, else it isn't even used
	 * @return
	 */
	protected abstract boolean isChangeAllowed();

	public boolean isCorrupted()
	{
		return this.getCorruptionTicks() > 0;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun) || this.worldObj.isRemote) return false; 

		Boolean result = super.attackEntityAsMob(par1Entity);

		if (result && TragicNewConfig.allowCorruption && this.canCorrupt())
		{
			if (par1Entity instanceof TragicMob && ((TragicMob)par1Entity).canCorrupt() && this.rand.nextInt(4) == 0)
			{
				((TragicMob) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Corruption.id, 60, 1));
			}
			else if (par1Entity instanceof EntityAnimal)
			{
				((EntityAnimal)par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Corruption.id, 120, 1));
			}
		} 

		return result;
	}

	public void readEntityFromNBT(NBTTagCompound tag)
	{
		super.readEntityFromNBT(tag);
		if (tag.hasKey("corruptionTicks")) this.setCorruptionTicks(tag.getInteger("corruptionTicks"));
	}

	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("corruptionTicks", this.getCorruptionTicks());
	}

	public boolean getMobGriefing()
	{
		return this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
	}

	public boolean getAllowLoot()
	{
		return this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot");
	}

	public void onDeath(DamageSource par1DamageSource)
	{		
		super.onDeath(par1DamageSource);

		if (!this.worldObj.isRemote && this.getAllowLoot())
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

			if (!TragicNewConfig.allowMobStatueDrops) return;

			int id = 0;

			if (this instanceof EntityJarra)
			{
				id = 6;
			}
			else if (this instanceof EntityKragul)
			{
				id = 7;
			}
			else if (this instanceof EntityMagmox)
			{
				id = 8;
			}
			else if (this instanceof EntityMegaCryse)
			{
				id = 9;
			}
			else if (this instanceof EntityStinKing)
			{
				id = 10;
			}
			else if (this instanceof EntityStinQueen)
			{
				id = 11;
			}
			else if (this instanceof EntityGreaterStin)
			{
				id = 12;
			}
			else if (this instanceof EntityVoxStellarum)
			{
				id = 13;
			}

			if (id != 0 && rand.nextInt(100) <= TragicNewConfig.mobStatueDropChance) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, id), 0.4F);
		}		
	}

	@Override
	public void onKillEntity(EntityLivingBase entity)
	{
		this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, (int) (entity.getMaxHealth() * 10), 2));
		this.addPotionEffect(new PotionEffect(Potion.resistance.id, (int) (entity.getMaxHealth() * 10), 2));
	}

	public Class getLesserForm()
	{
		return null;
	}

	@Override
	public boolean canAttackClass(Class par1Class)
	{
		return super.canAttackClass(par1Class) && this instanceof TragicMiniBoss ? par1Class != this.getLesserForm() : true;
	}
}
