package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.items.ItemAmulet;
import tragicneko.tragicmc.items.ItemAmulet.AmuletModifier;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.network.MessageAmulet;
import tragicneko.tragicmc.properties.PropertyAmulets;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.AmuletHelper;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.util.WorldHelper;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AmuletEvents {

	public static Set<Potion> badPotions = Sets.newHashSet(new Potion[] {Potion.blindness, Potion.confusion, Potion.digSlowdown, Potion.harm, Potion.hunger, Potion.moveSlowdown,
			Potion.poison, Potion.weakness, Potion.wither, TragicPotion.Corruption, TragicPotion.Cripple, TragicPotion.Disorientation, TragicPotion.Fear,
			TragicPotion.Inhibit, TragicPotion.Malnourish, TragicPotion.Stun, TragicPotion.Submission, TragicPotion.Hacked, TragicPotion.LeadFoot});

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) 
		{
			PropertyAmulets amu = PropertyAmulets.get((EntityPlayer) event.entity);

			if (amu == null)
			{
				PropertyAmulets.register((EntityPlayer) event.entity);
			}
			else
			{
				amu.loadNBTData(new NBTTagCompound());
			}

			if (event.entity instanceof EntityPlayerMP && amu != null)
			{
				TragicMC.net.sendTo(new MessageAmulet((EntityPlayerMP) event.entity), (EntityPlayerMP) event.entity);
			}
		}
	}

	@SubscribeEvent
	public void onLivingDeathEvent(PlayerEvent.Clone event) 
	{
		if (!event.entity.worldObj.isRemote && TragicConfig.allowAmulets) {
			if (PropertyAmulets.get(event.original) != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				PropertyAmulets.get(event.original).saveNBTData(tag);
				PropertyAmulets.get(event.entityPlayer).loadNBTData(tag);
			}
		}
	}

	@SubscribeEvent
	public void addAttributesToPlayer(EntityConstructing event)
	{
		if (!(event.entity instanceof EntityPlayer)) return;

		EntityPlayer player = (EntityPlayer) event.entity;
		BaseAttributeMap map = player.getAttributeMap();

		map.registerAttribute(AmuletModifier.jumpHeight);
		map.registerAttribute(AmuletModifier.reach);
		map.registerAttribute(AmuletModifier.resistance);
		map.registerAttribute(AmuletModifier.luck);
	}

	@SubscribeEvent
	public void onPlayerJump(LivingJumpEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			IAttributeInstance ins = event.entityLiving.getEntityAttribute(AmuletModifier.jumpHeight);
			double d0 = ins == null ? 0.0 : (ins.getAttributeValue() - 1.4D) * 0.25D;
			double d1 = event.entityLiving.motionY;
			event.entityLiving.motionY = d0 + d1;
			//TragicMC.logInfo("Entity motion is " + d1 + " amount after modifier is " + (d0 + d1));
			//if (!event.entityLiving.worldObj.isRemote) TragicMC.logInfo("Jump modifier check. Amount was " + d0);
		}
	}

	@SubscribeEvent
	public void onPlayerHurt(LivingHurtEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer && !event.source.isDamageAbsolute() && !event.source.isUnblockable())
		{
			IAttributeInstance ins = event.entityLiving.getEntityAttribute(AmuletModifier.resistance);
			double d0 = ins == null ? 0.0 : ins.getAttributeValue();
			event.ammount -= d0;
			//TragicMC.logInfo("Resistance modifier applied to player. Amount was " + d0);
		}
	}

	@SubscribeEvent
	public void onAmuletTick(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entityLiving;
			PropertyAmulets amu = PropertyAmulets.get(mp);

			IAttributeInstance ins = mp.getEntityAttribute(AmuletModifier.luck);
			double d0 = ins == null ? 0.0 : ins.getAttributeValue();
			if (rand.nextDouble() * 2.5D < d0 && mp.ticksExisted % 20 == 0) mp.addExperience(1);
			//TragicMC.logInfo("Luck modifier applied to player. Amount was " + d0);

			if (amu == null) return;
			TragicMC.net.sendTo(new MessageAmulet((EntityPlayer)event.entityLiving), (EntityPlayerMP)event.entityLiving);

			BaseAttributeMap map = mp.getAttributeMap();
			Multimap mm;

			IAttributeInstance ia;
			for (Object o : map.getAllAttributes())
			{
				if (o instanceof IAttributeInstance)
				{
					ia = (IAttributeInstance) o;
					for (int i = 0; i < AmuletHelper.uuids.length; i++)
					{
						ia.removeModifier(new AttributeModifier(AmuletHelper.uuids[i], ia.getAttribute().getAttributeUnlocalizedName(), 0, 0));
					}
				}
			}

			for (int meow = 0; meow < 3; meow++)
			{
				ItemStack stack = amu.getActiveAmuletItemStack(meow);
				if (stack != null)
				{
					mm = stack.getAttributeModifiers();
					map.removeAttributeModifiers(mm);
					map.applyAttributeModifiers(mm);
				}
			}

			if (mp.ticksExisted % 2 != 0) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];

			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			int same = AmuletHelper.getSameAmulets(amulets[0], amulets[1], amulets[2]);
			int id = 0;

			if (same == 0)
			{
				for (i = 0; i < 3; i++)
				{
					id = amulets[i] != null ? amulets[i].getAmuletID() : 0;
					if (amulets[i] != null) doAmuletEffect(id, amu, amulets[i], mp, mp.worldObj, i, levels[i]);
				}
			}
			else if (same == 12)
			{
				id = amulets[0] != null ? amulets[0].getAmuletID() : 0;
				if (amulets[0] != null) doAmuletEffect(id, amu, amulets[0], mp, mp.worldObj, i, AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[1]));

				id = amulets[2] != null ? amulets[2].getAmuletID() : 0;
				if (amulets[2] != null) doAmuletEffect(id, amu, amulets[2], mp, mp.worldObj, i, levels[2]);
			}
			else if (same == 13)
			{
				id = amulets[0] != null ? amulets[0].getAmuletID() : 0;
				if (amulets[0] != null) doAmuletEffect(id, amu, amulets[0], mp, mp.worldObj, i, AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[2]));

				id = amulets[1] != null ? amulets[1].getAmuletID() : 0;
				if (amulets[1] != null) doAmuletEffect(id, amu, amulets[1], mp, mp.worldObj, i, levels[1]);
			}
			else if (same == 23)
			{
				id = amulets[1] != null ? amulets[1].getAmuletID() : 0;
				if (amulets[1] != null) doAmuletEffect(id, amu, amulets[1], mp, mp.worldObj, i, AmuletHelper.getAmuletWithHighestLevel(levels[1], levels[2]));

				id = amulets[0] != null ? amulets[0].getAmuletID() : 0;
				if (amulets[0] != null) doAmuletEffect(id, amu, amulets[0], mp, mp.worldObj, i, levels[0]);
			}
			else if (same == 123)
			{
				id = amulets[0] != null ? amulets[0].getAmuletID() : 0;
				if (amulets[0] != null) doAmuletEffect(id, amu, amulets[0], mp, mp.worldObj, i, AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[1], levels[2]));
			}
		}
	}

	public static void doAmuletEffect(int id, PropertyAmulets amu, ItemAmulet amulet, EntityPlayer player, World world, int slot, int level)
	{				
		if (id == 0 && TragicConfig.amuKitsune && player.ticksExisted % 60 == 0)
		{
			if (player.isBurning()) player.extinguish();
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 600, 0));
			if (player.isPotionActive(Potion.fireResistance) && rand.nextBoolean() && !world.isRemote) amu.damageStackInSlot(slot, 1);
		}
		else if (id == 1 && TragicConfig.amuPeace && player.ticksExisted % 60 == 0)
		{
			double d0 = 8.0 + (level * 4.0);
			int chance;

			if (level == 2)
			{
				chance = 25;
			}
			else if (level == 3)
			{
				chance = 40;
			}
			else
			{
				chance = 10;
			}

			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityMob && rand.nextInt(100) <= chance)
				{
					if (!((EntityLivingBase) list.get(i)).isPotionActive(TragicPotion.Harmony.id))
					{
						((EntityMob)list.get(i)).addPotionEffect(new PotionEffect(TragicPotion.Harmony.id, 120 + rand.nextInt(320)));
						if (!world.isRemote) amu.damageStackInSlot(slot, 4 - level);
					}
				}
			}
		}
		else if (id == 3 && TragicConfig.amuClaymation && player.ticksExisted % 60 == 0)
		{
			double d0 = 4.0 + (level * 4.0);
			int chance;
			int goodChance;

			if (level == 2)
			{
				chance = 40;
				goodChance = 5;
			}
			else if (level == 3)
			{
				chance = 60;
				goodChance = 15;
			}
			else
			{
				chance = 20;
				goodChance = -1;
			}

			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

			if (rand.nextInt(100) <= chance && list.size() > 0)
			{
				PotionEffect[] effects = new PotionEffect[16];
				PotionEffect temp;
				int a = 0;

				for (int i = 0; i < Potion.potionTypes.length; i++)
				{
					if (list.size() == 0) break;

					if (Potion.potionTypes[i] != null)
					{
						Potion potion = Potion.potionTypes[i];

						if (player.isPotionActive(potion) && badPotions.contains(potion) && a < effects.length)
						{
							temp = player.getActivePotionEffect(potion);
							effects[a++] = new PotionEffect(potion.id, temp.getDuration() * level, temp.getAmplifier() * level);
							player.removePotionEffect(i);
							if (!world.isRemote) amu.damageStackInSlot(slot, 4 - level);
						}
					}
				}

				for (int x = 0; x < list.size(); x++)
				{
					if (list.get(x) instanceof EntityMob)
					{
						EntityMob entity = (EntityMob) list.get(x);

						for (int z = 0; z < effects.length; z++)
						{
							if (effects[z] != null)
							{
								entity.addPotionEffect(effects[z]);
							}
						}
					}
				}

				if (rand.nextInt(100) <= goodChance)
				{
					switch(rand.nextInt(5))
					{
					case 0:
						player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0 + rand.nextInt(3)));
						break;
					case 1:
						player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 0 + rand.nextInt(3)));
						break;
					case 2:
						player.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 0 + rand.nextInt(3)));
						break;
					case 3:
						player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 0 + rand.nextInt(3)));
						break;
					case 4:
						player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 200, 0 + rand.nextInt(3)));
						break;
					}
				}
			}
		}
		else if (id  == 4 && TragicConfig.amuChicken && player.isPlayerFullyAsleep())
		{
			PropertyDoom doom = PropertyDoom.get(player);

			if (level == 1)
			{
				player.heal(player.getMaxHealth() / 4);
				if (doom != null) doom.increaseDoom(doom.getMaxDoom() / 4);
			}
			else if (level == 2)
			{
				player.heal(player.getMaxHealth() / 2);
				if (doom != null) doom.increaseDoom(doom.getMaxDoom() / 2);
			}
			else
			{
				player.heal(player.getMaxHealth() * 3 / 4);
				if (doom != null) doom.increaseDoom(doom.getMaxDoom() * 3 / 4);

				for (int i = 0; i < Potion.potionTypes.length; i++)
				{
					if (Potion.potionTypes[i] != null)
					{
						Potion potion = Potion.potionTypes[i];

						if (player.isPotionActive(potion) && badPotions.contains(potion))
						{
							player.removePotionEffect(i);
						}
					}
				}
			}

			if (!world.isRemote) amu.damageStackInSlot(slot, 4 - level);
		}
		else if (id == 7 && TragicConfig.amuBlacksmith)
		{
			boolean flag = false;

			if (level == 1 && player.ticksExisted % 60 == 0)
			{
				flag = true;
			}
			else if (level == 2 && player.ticksExisted % 40 == 0)
			{
				flag = true;
			}						
			else if (level == 3 && player.ticksExisted % 20 == 0)
			{
				flag = true;
			}

			if (flag)
			{
				ItemStack[] playerInv = player.inventory.mainInventory;

				for (int i = 0; i < 4 * level; i++)
				{
					ItemStack stack = playerInv[rand.nextInt(playerInv.length)];

					if (stack != null && stack.isItemDamaged() && stack.getItem().isRepairable())
					{
						Item item = stack.getItem();

						if (item.getDamage(stack) - level <= 0)
						{
							if (!world.isRemote) item.setDamage(stack, 0);
							break;
						}

						if (item.getDamage(stack) >= item.getMaxDamage(stack) / 8)
						{
							if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem() == stack)
							{
								break;
							}

							if (!world.isRemote)
							{
								item.setDamage(stack, item.getDamage(stack) - level);
								amu.damageStackInSlot(slot, 4 - level);
							}
						}

						break;
					}
				}
			}
		}
		else if (id == 9 && TragicConfig.amuCreeper && player.ticksExisted % 60 == 0)
		{
			player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 600, level));
			if (player.isPotionActive(Potion.digSpeed) && rand.nextBoolean() && !world.isRemote) amu.damageStackInSlot(slot, 4 - level);
		}
		else if (id == 12 && TragicConfig.amuSunken && player.ticksExisted % 60 == 0 && TragicConfig.allowAquaSuperiority && player.isInWater())
		{
			player.addPotionEffect(new PotionEffect(TragicPotion.AquaSuperiority.id, 200, level));
			if (player.isPotionActive(TragicPotion.AquaSuperiority) && rand.nextBoolean() && !world.isRemote) amu.damageStackInSlot(slot, 4 - level);
		}
		else if (id == 13 && TragicConfig.amuTime)
		{
			List<EntityItem> list = world.getEntitiesWithinAABB(EntityItem.class, player.boundingBox.expand(8.0, 8.0, 8.0));
			Iterator ite = list.iterator();
			EntityItem item;

			while (ite.hasNext())
			{
				item = (EntityItem) ite.next();

				double d1 = item.posX - player.posX;
				double d2 = item.posZ - player.posZ;
				double d3 = item.posY - player.posY + player.getEyeHeight();
				float f2 = MathHelper.sqrt_double(d1 * d1 + d2 * d2 + d3 * d3);
				double d4 = 0.65D;

				item.motionX = -d1 / f2 * d4 * 0.300000011920929D + item.motionX * 0.90000000298023224D;
				item.motionZ = -d2 / f2 * d4 * 0.300000011920929D + item.motionZ * 0.90000000298023224D;
				item.motionY = -d3 / f2 * d4 * 0.300000011920929D + item.motionZ * 0.90000000298023224D;
			}
		}
		else if (id == 15 && TragicConfig.amuSnowGolem)
		{
			double d = level * 16.0D + 16.0D;
			List<EntityItem> list = world.getEntitiesWithinAABB(EntityMob.class, player.boundingBox.expand(d, d, d));
			if (list.size() > 0 && player.ticksExisted % 5 == 0) amu.damageStackInSlot(slot, 4 - level);
			Iterator ite = list.iterator();
			EntityMob mob;

			while (ite.hasNext())
			{
				mob = (EntityMob) ite.next();
				mob.targetTasks.addTask(3, new EntityAINearestAttackableTarget(mob, EntityPlayer.class, 0, true));
				mob.setAttackTarget(player);
				mob.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(d + 32.0D);
			}
		}
		else if (id == 16 && TragicConfig.amuIronGolem)
		{
			player.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, level));
			if (player.isPotionActive(Potion.resistance) && rand.nextBoolean() && !world.isRemote) amu.damageStackInSlot(slot, 4 - level);
		}
		else if (id == 19 && TragicConfig.amuSpider)
		{	      
			double d0 = level == 1 ? 0.03 : (level == 2 ? 0.5 : 0.11);
			if (player.isCollidedHorizontally)
			{
				if (player.isSneaking()) player.motionY = 0;
				else player.motionY = d0;
				player.motionX = MathHelper.clamp_double(player.motionX, -d0, d0);
				player.motionZ = MathHelper.clamp_double(player.motionZ, -d0, d0);
				if (player.motionY < -0.15) player.motionY = -0.15;
			}

			if (!world.isRemote)
			{
				List<int[]> list = WorldHelper.getBlocksInSphericalRange(world, 1.0, (int) player.posX, (int) player.posY, (int) player.posZ);
				boolean flag = false;
				for (int i = 0; i < list.size() && !flag; i++)
				{
					int[] coords = list.get(i);
					Block block = world.getBlock(coords[0], coords[1], coords[2]);
					if (block.isOpaqueCube() && coords[1] >= player.posY) flag = true;
				}

				if (player.motionY > 0 && (player.motionX <= d0 && player.motionX >= -d0 || player.motionZ <= d0 && player.motionZ >= -d0) && flag)
				{
					player.fallDistance = 0;
					if (player.ticksExisted % 8 == 0) amu.damageStackInSlot(slot, 4 - level);
					TragicMC.logInfo("Damaged spider amulet.");
				}
			}

			if (level >= 3 && player.ticksExisted % 20 == 0 && TragicConfig.allowImmunity) player.addPotionEffect(new PotionEffect(TragicPotion.Immunity.id, 100, 0));
		}
		else if (id == 22 && TragicConfig.allowHacked)
		{
			if (player.isPotionActive(TragicPotion.Hacked)) player.removePotionEffect(TragicPotion.Hacked.id); 
		}
		else if (id == 25 && player.ticksExisted % 600 == 0 && TragicConfig.amuSupernatural)
		{
			int i = level * 25;
			int j = 100 - i;

			if (rand.nextInt(level + 1) != 0)
			{
				if (rand.nextInt(j) > rand.nextInt(i))
				{
					Potion pot = Potion.potionTypes[0];
					while (pot == null || !badPotions.contains(pot))
					{
						pot = Potion.potionTypes[rand.nextInt(Potion.potionTypes.length)];
					}
					player.addPotionEffect(new PotionEffect(pot.id, 200 + rand.nextInt(100), rand.nextInt(5 - level)));
				}
				else
				{
					Potion pot = Potion.potionTypes[0];
					while (pot == null || badPotions.contains(pot))
					{
						pot = Potion.potionTypes[rand.nextInt(Potion.potionTypes.length)];
					}
					player.addPotionEffect(new PotionEffect(pot.id, 200 + rand.nextInt(200), rand.nextInt(level)));
				}
			}
		}
		else if (id == 26 && TragicConfig.amuUndead)
		{			
			if (world.canBlockSeeTheSky((int) player.posX, (int) player.posY, (int) player.posZ) && world.isDaytime() && player.ticksExisted % 10 == 0)
			{
				player.setFire(8 + rand.nextInt(4));
			}

			if (player.isPotionActive(Potion.weakness))
			{
				PotionEffect effect = player.getActivePotionEffect(Potion.weakness);
				player.removePotionEffect(Potion.weakness.id);
				player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, effect.getDuration(), effect.getAmplifier()));
			}

			if (player.isPotionActive(Potion.poison))
			{
				PotionEffect effect = player.getActivePotionEffect(Potion.poison);
				player.removePotionEffect(Potion.poison.id);
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, effect.getDuration(), effect.getAmplifier()));
			}

			if (TragicConfig.allowCripple && player.isPotionActive(TragicPotion.Cripple))
			{
				PotionEffect effect = player.getActivePotionEffect(TragicPotion.Cripple);
				player.removePotionEffect(TragicPotion.Cripple.id);
				player.addPotionEffect(new PotionEffect(Potion.field_76434_w.id, effect.getDuration(), effect.getAmplifier()));
			}
		}
		else if (id == 29 && TragicConfig.amuEnyvil)
		{
			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(16.0, 16.0, 16.0));

			for (Entity e : list)
			{
				if (e instanceof EntityLivingBase)
				{
					((EntityLivingBase) e).addPotionEffect(new PotionEffect(TragicConfig.allowFear ? TragicPotion.Fear.id : Potion.blindness.id, 120, 0));
				}
			}
		}
		else if (id == 30 && TragicConfig.amuLuck && player.ticksExisted % 300 == 0 && rand.nextInt(level * 2) != 0)
		{
			if (!world.isRemote)
			{
				player.addExperience(rand.nextInt(level * 10));
				amu.damageStackInSlot(slot, 4 - level);
			}
		}
	}

	@SubscribeEvent
	public void onHurtAmuletUse(LivingHurtEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entityLiving;
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			for (i = 0; i < 3 && TragicConfig.amuMartyr; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 5)
				{
					event.ammount /= 2;
					amu.damageStackInSlot(i, MathHelper.floor_float(event.ammount));
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuZombie; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 10)
				{
					if (event.source == DamageSource.starve && event.isCancelable()) event.setCanceled(true);
					amu.damageStackInSlot(i, 4 - levels[i]);
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuKitsune; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 0)
				{
					if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase && !event.source.isProjectile())
					{
						if (rand.nextInt(100) <= 70)
						{
							event.source.getEntity().setFire(rand.nextInt(16) + 8);
							((EntityLivingBase) event.source.getEntity()).addPotionEffect(new PotionEffect(Potion.blindness.id, 160 + rand.nextInt(320)));
							amu.damageStackInSlot(i, 1);
						}

						if (event.source.damageType.equalsIgnoreCase("fireball")) event.ammount *= 1.75F;
					}
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuApis; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 8)
				{
					if (event.source.isExplosion() && event.isCancelable())
					{
						event.setCanceled(true);
						amu.damageStackInSlot(i, 1);
					}
					if (event.source.isProjectile()) event.ammount *= 1.75F;
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuSunken  && !event.source.isDamageAbsolute(); i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 12)
				{
					if (mp.isInWater()) event.ammount *= 1.535F;
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuPiercing && event.source.isMagicDamage(); i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 6)
				{
					event.ammount *= 2.135F;
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuIce && !event.source.isDamageAbsolute(); i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 14)
				{
					if (mp.worldObj.getBiomeGenForCoords((int) mp.posX, (int) mp.posZ).getTempCategory() == TempCategory.COLD)
					{
						if (levels[i] >= 2 && event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase)
						{
							((EntityLivingBase) event.source.getEntity()).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100 * levels[i], levels[i]));
						}
						amu.damageStackInSlot(i, 4 - levels[i]);
						event.ammount *= 0.475F;
						break;
					}
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuWither; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 18)
				{
					if (event.source == DamageSource.wither)
					{
						if (event.isCancelable()) event.setCanceled(true);
						mp.heal(event.ammount);
						break;
					}
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuPolaris && !event.source.isDamageAbsolute(); i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 21)
				{
					if (event.entityLiving.worldObj.isDaytime())
					{
						event.ammount *= 1.45D;
					}
					else
					{
						event.ammount *= (1 / 1.45D);
					}
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuOverlord && !event.source.isDamageAbsolute() && event.entityLiving.worldObj.provider.dimensionId == TragicConfig.synapseID; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 22)
				{
					event.ammount *= 0.65D;
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuEnderDragon && event.source == DamageSource.inWall; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 27)
				{
					List<int[]> list = WorldHelper.getBlocksInSphericalRange(mp.worldObj, 1.0, (int) mp.posX, (int) mp.posY, (int) mp.posZ);
					for (int j = 0; j < list.size(); j++)
					{
						int[] coords = list.get(i);
						Block block = mp.worldObj.getBlock(coords[0], coords[1], coords[2]);
						if (block.isOpaqueCube() && coords[1] >= mp.posY) mp.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
					}
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuFusea; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 28)
				{
					mp.worldObj.createExplosion(mp, mp.posX, mp.posY, mp.posZ, levels[i] * 1.5F, false);
					amu.damageStackInSlot(i, 4 - levels[i]);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onAmuletAttack(LivingAttackEvent event)
	{
		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.source.getEntity();
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			for (i = 0; i < 3 && TragicConfig.amuPiercing; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 6)
				{
					event.entityLiving.attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(mp), event.ammount * 0.135F + 1.0F);
					amu.damageStackInSlot(i, 1);
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuStin; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 20)
				{
					double d = 0.4D * levels[i];
					event.entityLiving.motionX += d;
					event.entityLiving.motionZ += d;
					amu.damageStackInSlot(i, 4 - levels[i]);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onYetiAmuletUse(LivingFallEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entityLiving;
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			float fall = event.distance / 2;

			for (i = 0; i < 3 && TragicConfig.amuYeti; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 2)
				{
					if (fall > levels[i] * 1.5F)
					{
						fall = levels[i] * 1.5F;
					}

					if (fall >= 1.5F)
					{
						mp.worldObj.createExplosion(mp, mp.posX, mp.posY, mp.posZ, fall, false);
						amu.damageStackInSlot(i, 4 - levels[i]);
					}

					if (levels[i] == 3)
					{
						List<Entity> list = event.entityLiving.worldObj.getEntitiesWithinAABBExcludingEntity(mp, mp.boundingBox.expand(6.0D, 6.0D, 6.0D));
						for (i = 0; i < list.size(); i++)
						{
							if (list.get(i) instanceof EntityLivingBase)
							{
								if (TragicConfig.allowSubmission) ((EntityLivingBase) list.get(i)).addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 120 + rand.nextInt(320), rand.nextInt(4)));
								((EntityLivingBase) list.get(i)).addPotionEffect(new PotionEffect(Potion.weakness.id, 120 + rand.nextInt(320), rand.nextInt(4)));
							}
						}
					}
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuIronGolem; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 16 && levels[i] >= 3)
				{
					if (event.isCancelable()) event.setCanceled(true);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onSkeletonAmuletUse(ArrowLooseEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP && TragicConfig.amuSkeleton)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entityLiving;
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 11 && rand.nextInt(16 - (levels[i] * 5)) == 0)
				{
					event.charge += levels[i] * 5;
					amu.damageStackInSlot(i, 4 - levels[i]);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onEndermanAmuletUse(BreakEvent event)
	{
		if (TragicConfig.amuEnderman && event.getPlayer() != null && event.getPlayer().getEquipmentInSlot(0) == null && event.getPlayer() instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.getPlayer();
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 17)
				{
					mp.worldObj.setBlockToAir(event.x, event.y, event.z);
					EntityItem item = new EntityItem(mp.worldObj, event.x, event.y, event.z, new ItemStack(event.block, 1, event.blockMetadata));
					mp.worldObj.spawnEntityInWorld(item);
					if (event.isCancelable()) event.setCanceled(true);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void doomRechargeDeath(LivingDeathEvent event)
	{
		if (event.entityLiving.worldObj.isRemote) return;

		if (event.source.getEntity() instanceof EntityPlayerMP && (event.entityLiving instanceof TragicBoss || event.entityLiving.getMaxHealth() >= 100F) && TragicConfig.allowAmuletKillRecharge)
		{
			EntityPlayerMP player = (EntityPlayerMP) event.source.getEntity();
			PropertyAmulets amu = PropertyAmulets.get(player);

			if (amu == null) return;

			ItemStack[] amulets = new ItemStack[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmuletItemStack(i);
			}

			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getItemDamage() > 0)
				{
					int yo = AmuletHelper.getAmuletLevel(amulets[i]);
					int repair = (yo * 2 + (rand.nextInt(yo + 1) + rand.nextInt(yo + 1)) * 2) * (int) (10 * rand.nextDouble());
					amu.repairStackInSlot(i, MathHelper.clamp_int(repair, 1, amulets[i].getItemDamage()));
				}
			}

			for (i = 0; i < 3 && TragicConfig.amuLuck; i++)
			{
				if (amulets[i] != null && ((ItemAmulet) amulets[i].getItem()).getAmuletID() == 30)
				{
					try
					{
						Method m = EntityLiving.class.getDeclaredMethod("getExperiencePoints", EntityPlayer.class);
						m.setAccessible(true);
						int j = (Integer) m.invoke(event.entityLiving, player);

						while (j > 0)
						{
							int k = EntityXPOrb.getXPSplit(j);
							j -= k;
							player.worldObj.spawnEntityInWorld(new EntityXPOrb(player.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, k));
						}
					}
					catch (Exception e)
					{
						TragicMC.logError("Error caught while reflecting experience value from a mob for the Luck Amulet effect", e);
					}
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onLightningAmuletUse(EntityStruckByLightningEvent event)
	{
		if (event.entity instanceof EntityPlayerMP && TragicConfig.amuLightning)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entity;
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletID() == 23)
				{
					if (event.isCancelable()) event.setCanceled(true);
					mp.getFoodStats().addExhaustion(-40F);
					break;
				}
			}
		}
	}
}
