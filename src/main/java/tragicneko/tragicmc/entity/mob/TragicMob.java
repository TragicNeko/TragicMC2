package tragicneko.tragicmc.entity.mob;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.miniboss.EntityGreaterStin;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.miniboss.EntityMegaCryse;
import tragicneko.tragicmc.entity.miniboss.EntityStinKing;
import tragicneko.tragicmc.entity.miniboss.EntityStinQueen;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.miniboss.TragicMiniBoss;
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
		return this.superiorForm != null && TragicConfig.allowMobTransformation;
	}

	@Override
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

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.getAttackTarget() != null && this.getAttackTarget().isDead) this.setAttackTarget(null);

		if (this.getAttackTarget() == null && this.canCorrupt() && TragicConfig.allowCorruption && this.isPotionActive(TragicPotion.Corruption.id))
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

		if (TragicConfig.allowCorruption)
		{
			if (this.isPotionActive(TragicPotion.Corruption))
			{
				this.incrementCorruptionTicks();
			}
			else
			{
				this.setCorruptionTicks(this.getCorruptionTicks() - 1);
			}

			if (this.canChange() && this.getCorruptionTicks() >= 400 && this.rand.nextInt(200) <= TragicConfig.mobTransformationChance && this.ticksExisted % 20 == 0 && rand.nextInt(4) == 0)
			{
				this.change();
			}
		}
		else if (this.canChange() && this.ticksExisted >= 6000 && this.ticksExisted % 20 == 0 && this.rand.nextInt(100) <= TragicConfig.mobTransformationChance)
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

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote || TragicConfig.allowStun && this.isPotionActive(TragicPotion.Stun)) return false;

		Boolean result = super.attackEntityAsMob(par1Entity);

		if (result && TragicConfig.allowCorruption && this.canCorrupt() && rand.nextInt(4) == 0 && this.isPotionActive(TragicPotion.Corruption))
		{
			if (par1Entity instanceof TragicMob && ((TragicMob)par1Entity).canCorrupt())
			{
				((TragicMob) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 600, 1));
			}
			else if (par1Entity instanceof EntityMob && !(par1Entity instanceof TragicMob))
			{
				((EntityMob)par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 600, 1));
			}
			else if (par1Entity instanceof EntityAnimal)
			{
				((EntityAnimal)par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 400, 1));
			}
			else if (par1Entity instanceof EntityPlayer && !((EntityPlayer) par1Entity).capabilities.isCreativeMode)
			{
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 200,1));
			}
		}

		return result;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		super.readEntityFromNBT(tag);
		if (tag.hasKey("corruptionTicks")) this.setCorruptionTicks(tag.getInteger("corruptionTicks"));
	}

	@Override
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
	@Override
	protected void onDeathUpdate()
	{
		++this.deathTime;

		if (this.deathTime == 20)
		{
			int i;

			if (!this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && this.func_146066_aG() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot"))
			{
				i = this.getExperiencePoints(this.attackingPlayer);

				while (i > 0)
				{
					int j = EntityXPOrb.getXPSplit(i);
					i -= j;
					this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
				}
			}

			this.setDead();

			for (i = 0; i < 20; ++i)
			{
				double d2 = this.rand.nextGaussian() * 0.02D;
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				this.worldObj.spawnParticle("explode", this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d2, d0, d1);
			}
		}
	}

	@Override
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

			int drops = 0;

			for (int i = 0; i < x; i++)
			{
				if (rand.nextInt(100) <= TragicConfig.commonDropRate + (x * 4))
				{
					ItemStack drop = this.isMobVariant() ? EntityDropHelper.getDropFromVariant(this.getClass(), true) : EntityDropHelper.getDropFromEntity(this.getClass(), true);
					if (drop != null) this.entityDropItem(drop, 0.4F);
					drops++;
				}

				if (this.recentlyHit > 0 && rand.nextInt(100) <= TragicConfig.rareDropRate + x)
				{
					ItemStack drop = this.isMobVariant() ? EntityDropHelper.getDropFromVariant(this.getClass(), false) : EntityDropHelper.getDropFromEntity(this.getClass(), false);
					if (drop != null) this.entityDropItem(drop, 0.4F);
					drops++;
				}

				if (drops > x * 2.5) break;
			}

			if (!TragicConfig.allowMobStatueDrops) return;

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

			if (id != 0 && rand.nextInt(100) <= TragicConfig.mobStatueDropChance) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, id), 0.4F);
		}
	}

	public boolean isMobVariant()
	{
		return false;
	}

	@Override
	public void onKillEntity(EntityLivingBase entity)
	{
		super.onKillEntity(entity);
		if (this.worldObj.isRemote) return;
		int i = (int) (entity.getMaxHealth() * 20);
		if (entity instanceof EntityPlayer) i *= 20;
		this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, i, 2));
		this.addPotionEffect(new PotionEffect(Potion.resistance.id, i, 2));
	}

	public Class<? extends TragicMob> getLesserForm()
	{
		return this.getClass();
	}

	@Override
	public boolean canAttackClass(Class par1Class)
	{
		return super.canAttackClass(par1Class) && par1Class != TragicBoss.class && this instanceof TragicMiniBoss ? par1Class != this.getLesserForm() : true;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		if (!TragicConfig.allowGroupBuffs) return super.onSpawnWithEgg(data);
		if (data == null)
		{
			if (rand.nextInt(200) <= TragicConfig.groupBuffChance)
			{
				int id = Potion.damageBoost.id;
				switch(rand.nextInt(12))
				{
				case 0:
				default:
					break;
				case 1:
					id = Potion.moveSpeed.id;
					break;
				case 2:
					id = Potion.invisibility.id;
					break;
				case 3:
					id = this.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD ? Potion.poison.id : Potion.regeneration.id;
					break;
				case 4:
					id = Potion.fireResistance.id;
					break;
				case 5:
					id = Potion.resistance.id;
					break;
				case 6:
					id = Potion.jump.id;
					break;
				case 7:
					id = TragicConfig.allowImmunity ? TragicPotion.Immunity.id : Potion.digSpeed.id;
					break;
				case 8:
					id = TragicConfig.allowClarity ? TragicPotion.Clarity.id : Potion.resistance.id;
					break;
				case 9:
					id = TragicConfig.allowResurrection ? TragicPotion.Resurrection.id : Potion.digSpeed.id;
					break;
				case 10:
					id = TragicConfig.allowAquaSuperiority ? TragicPotion.AquaSuperiority.id : Potion.jump.id;
					break;
				}

				PotionEffect effect = new PotionEffect(id, 99999, rand.nextInt(2));
				this.addPotionEffect(effect);
				return new GroupBuff(effect);
			}
		}
		else if (data instanceof GroupBuff)
		{
			this.addPotionEffect(((GroupBuff) data).getReducedEffect());
			return super.onSpawnWithEgg(data);
		}
		return super.onSpawnWithEgg(data);
	}

	public static class GroupBuff implements IEntityLivingData {
		public final PotionEffect effect;
		public GroupBuff(PotionEffect effect)
		{
			this.effect = effect;
		}

		public PotionEffect getReducedEffect()
		{
			return new PotionEffect(effect.getPotionID(), effect.getDuration() * 3 / 4, effect.getAmplifier() / 2 * 3);
		}
	}
}
