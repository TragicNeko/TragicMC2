package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
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
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.items.special.ItemAmulet;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.network.MessageAmulet;
import tragicneko.tragicmc.properties.PropertyAmulets;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.AmuletHelper;
import tragicneko.tragicmc.util.DamageHelper;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class NewAmuletEvents {

	private static Set badPotions = Sets.newHashSet(new Potion[] {Potion.blindness, Potion.confusion, Potion.digSlowdown, Potion.harm, Potion.hunger, Potion.moveSlowdown,
			Potion.poison, Potion.weakness, Potion.wither, TragicPotions.Corruption, TragicPotions.Cripple, TragicPotions.Disorientation, TragicPotions.Fear,
			TragicPotions.Inhibit, TragicPotions.Malnourish, TragicPotions.Stun, TragicPotions.Submission});

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
				TragicMC.net.sendTo(new MessageAmulet((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
			}
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) 
		{
			PropertyAmulets.loadProxyData((EntityPlayer) event.entity);
		}
	}

	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) 
	{
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			PropertyAmulets.saveProxyData((EntityPlayer) event.entity);
		}
	}

	@SubscribeEvent
	public void onAmuletTick(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP && event.entityLiving.ticksExisted % 2 == 0)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entityLiving;
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			TragicMC.net.sendTo(new MessageAmulet((EntityPlayer)event.entityLiving), (EntityPlayerMP)event.entityLiving);

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = amulets[i] == null ? 0 : amulets[i].getAmuletLevel();
			}

			int same = AmuletHelper.getSameAmulets(amulets[0], amulets[1], amulets[2]);
			String name = null;

			if (same == 0)
			{
				for (i = 0; i < 3; i++)
				{
					name = amulets[i] != null ? amulets[i].getAmuletName() : null;

					if (name != null) doAmuletEffect(name, amu, amulets[i], mp, mp.worldObj, i, levels[i]);
				}
			}
			else if (same == 12)
			{
				name = amulets[0] != null ? amulets[0].getAmuletName() : null;
				if (name != null) doAmuletEffect(name, amu, amulets[0], mp, mp.worldObj, i, AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[1]));
				
				name = amulets[2] != null ? amulets[2].getAmuletName() : null;
				if (name != null) doAmuletEffect(name, amu, amulets[2], mp, mp.worldObj, i, levels[2]);
			}
			else if (same == 13)
			{
				name = amulets[0] != null ? amulets[0].getAmuletName() : null;
				if (name != null) doAmuletEffect(name, amu, amulets[0], mp, mp.worldObj, i, AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[2]));
				
				name = amulets[1] != null ? amulets[1].getAmuletName() : null;
				if (name != null) doAmuletEffect(name, amu, amulets[1], mp, mp.worldObj, i, levels[1]);
			}
			else if (same == 23)
			{
				name = amulets[1] != null ? amulets[1].getAmuletName() : null;
				if (name != null) doAmuletEffect(name, amu, amulets[1], mp, mp.worldObj, i, AmuletHelper.getAmuletWithHighestLevel(levels[1], levels[2]));
				
				name = amulets[0] != null ? amulets[0].getAmuletName() : null;
				if (name != null) doAmuletEffect(name, amu, amulets[0], mp, mp.worldObj, i, levels[0]);
			}
			else if (same == 123)
			{
				name = amulets[0] != null ? amulets[0].getAmuletName() : null;
				if (name != null) doAmuletEffect(name, amu, amulets[0], mp, mp.worldObj, i, AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[1], levels[2]));
			}
		}
	}

	public static void doAmuletEffect(String s, PropertyAmulets amu, ItemAmulet amulet, EntityPlayerMP mp, World world, int slot, int level)
	{		
		if (s.equals("Kitsune") && TragicNewConfig.amuKitsune && mp.ticksExisted % 60 == 0)
		{
			mp.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 600, 0));
			if (mp.isPotionActive(Potion.fireResistance) && rand.nextBoolean()) amu.damageStackInSlot(slot, 1);
		}
		else if (s.equals("Peace") && TragicNewConfig.amuPeace && mp.ticksExisted % 60 == 0)
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

			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(mp, mp.boundingBox.expand(d0, d0, d0));

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityMob && rand.nextInt(100) <= chance)
				{
					if (!((EntityLivingBase) list.get(i)).isPotionActive(TragicPotions.Harmony.id))
					{
						((EntityMob)list.get(i)).addPotionEffect(new PotionEffect(TragicPotions.Harmony.id, 120 + rand.nextInt(320)));
						amu.damageStackInSlot(slot, 4 - level);
					}
				}
			}
		}
		else if (s.equals("Claymation") && TragicNewConfig.amuClaymation && mp.ticksExisted % 60 == 0)
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

			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(mp, mp.boundingBox.expand(d0, d0, d0));

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

						if (mp.isPotionActive(potion) && badPotions.contains(potion) && a < effects.length)
						{
							temp = mp.getActivePotionEffect(potion);
							effects[a++] = new PotionEffect(potion.id, temp.getDuration() * level, temp.getAmplifier() * level);
							mp.removePotionEffect(i);
							amu.damageStackInSlot(slot, 4 - level);
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
						mp.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0 + rand.nextInt(3)));
						break;
					case 1:
						mp.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 0 + rand.nextInt(3)));
						break;
					case 2:
						mp.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 0 + rand.nextInt(3)));
						break;
					case 3:
						mp.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 0 + rand.nextInt(3)));
						break;
					case 4:
						mp.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 200, 0 + rand.nextInt(3)));
						break;
					}
				}
			}
		}
		else if (s.equals("Chicken") && TragicNewConfig.amuChicken && mp.isPlayerFullyAsleep())
		{
			PropertyDoom doom = PropertyDoom.get(mp);
			
			if (level == 1)
			{
				mp.heal(mp.getMaxHealth() / 4);
				if (doom != null) doom.increaseDoom(doom.getMaxDoom() / 4);
			}
			else if (level == 2)
			{
				mp.heal(mp.getMaxHealth() / 2);
				if (doom != null) doom.increaseDoom(doom.getMaxDoom() / 2);
			}
			else
			{
				mp.heal(mp.getMaxHealth() * 3 / 4);
				if (doom != null) doom.increaseDoom(doom.getMaxDoom() * 3 / 4);

				for (int i = 0; i < Potion.potionTypes.length; i++)
				{
					if (Potion.potionTypes[i] != null)
					{
						Potion potion = Potion.potionTypes[i];

						if (mp.isPotionActive(potion) && badPotions.contains(potion))
						{
							mp.removePotionEffect(i);
						}
					}
				}
			}
			
			amu.damageStackInSlot(slot, 4 - level);
		}
		else if (s.equals("Blacksmith") && TragicNewConfig.amuBlacksmith)
		{
			boolean flag = false;

			if (level == 1 && mp.ticksExisted % 60 == 0)
			{
				flag = true;
			}
			else if (level == 2 && mp.ticksExisted % 40 == 0)
			{
				flag = true;
			}						
			else if (level == 3 && mp.ticksExisted % 20 == 0)
			{
				flag = true;
			}

			if (flag)
			{
				ItemStack[] playerInv = mp.inventory.mainInventory;
				
				for (int i = 0; i < 4 * level; i++)
				{
					ItemStack stack = playerInv[rand.nextInt(playerInv.length)];

					if (stack != null && stack.isItemDamaged() && stack.getItem().isRepairable())
					{
						Item item = stack.getItem();

						if (item.getDamage(stack) - level <= 0)
						{
							item.setDamage(stack, 0);
							break;
						}

						if (item.getDamage(stack) >= item.getMaxDamage(stack) / 8)
						{
							if (mp.getCurrentEquippedItem() != null && mp.getCurrentEquippedItem() == stack)
							{
								break;
							}

							item.setDamage(stack, item.getDamage(stack) - level);
							amu.damageStackInSlot(slot, 4 - level);
						}

						break;
					}
				}
			}
		}
		else if (s.equals("Creeper") && TragicNewConfig.amuCreeper && mp.ticksExisted % 60 == 0)
		{
			mp.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 600, level));
			if (mp.isPotionActive(Potion.digSpeed) && rand.nextBoolean()) amu.damageStackInSlot(slot, 4 - level);
		}
		else if (s.equals("Sunken") && TragicNewConfig.amuSunken && mp.ticksExisted % 60 == 0 && TragicNewConfig.allowAquaSuperiority && mp.isInWater())
		{
			mp.addPotionEffect(new PotionEffect(TragicPotions.AquaSuperiority.id, 200, level));
			if (mp.isPotionActive(TragicPotions.AquaSuperiority) && rand.nextBoolean()) amu.damageStackInSlot(slot, 4 - level);
		}
		else if (s.equals("Time") && TragicNewConfig.amuTime)
		{
			List<EntityItem> list = world.getEntitiesWithinAABB(EntityItem.class, mp.boundingBox.expand(8.0, 8.0, 8.0));
			Iterator ite = list.iterator();
			EntityItem item;

			while (ite.hasNext())
			{
				item = (EntityItem) ite.next();
				
				double d0 = 8.0D;
				double d1 = (mp.posX + rand.nextDouble() - rand.nextDouble() - item.posX) / d0;
	            double d2 = (mp.posY + (double)mp.getEyeHeight() - item.posY) / d0;
	            double d3 = (mp.posZ + rand.nextDouble() - rand.nextDouble() - item.posZ) / d0;
	            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
	            double d5 = 1.0D - d4;

	            if (d5 > 0.0D)
	            {
	                d5 *= d5;
	                item.motionX += d1 / d4 * d5 * 0.05D;
	                item.motionY += d2 / d4 * d5 * 0.05D;
	                item.motionZ += d3 / d4 * d5 * 0.05D;
	                
	                if (rand.nextBoolean() && item.onGround) item.motionY += rand.nextDouble() * 0.28D;
	                
	                item.moveEntity(item.motionX, item.motionY, item.motionZ);
	            }
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
				levels[i] = amulets[i] == null ? 0 : amulets[i].getAmuletLevel();
			}
			
			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletName().equals("Martyr") && TragicNewConfig.amuMartyr)
				{
					event.ammount /= 2;
					amu.damageStackInSlot(i, MathHelper.floor_float(event.ammount));
					break;
				}
			}
			
			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletName().equals("Zombie") && TragicNewConfig.amuZombie)
				{
					if (event.source == DamageSource.starve && event.isCancelable()) event.setCanceled(true);
					amu.damageStackInSlot(i, 4 - levels[i]);
					break;
				}
			}
			
			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletName().equals("Kitsune") && TragicNewConfig.amuKitsune)
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
			
			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletName().equals("Apis") && TragicNewConfig.amuApis)
				{
					if (event.source.isExplosion() && event.isCancelable()) event.setCanceled(true);
					if (event.source.isProjectile()) event.ammount *= 1.75F;
					break;
				}
			}
			
			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletName().equals("Sunken") && TragicNewConfig.amuSunken)
				{
					if (mp.isInWater()) event.ammount *= 1.535F;
					break;
				}
			}
			
			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletName().equals("Piercing") && TragicNewConfig.amuPiercing && event.source.isMagicDamage())
				{
					event.ammount *= 2.135F;
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
				if (amulets[i] != null && amulets[i].getAmuletName().equals("Piercing") && TragicNewConfig.amuPiercing)
				{
					event.entityLiving.attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(mp), event.ammount * 0.135F + 1.0F);
					break;
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onYetiAmuletUse(LivingFallEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP && TragicNewConfig.amuYeti)
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
				levels[i] = amulets[i] == null ? 0 : amulets[i].getAmuletLevel();
			}
			
			float fall = event.distance / 2;
			
			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletName().equals("Yeti"))
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
								if (TragicNewConfig.allowSubmission) ((EntityLivingBase) list.get(i)).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 120 + rand.nextInt(320), rand.nextInt(4)));
								((EntityLivingBase) list.get(i)).addPotionEffect(new PotionEffect(Potion.weakness.id, 120 + rand.nextInt(320), rand.nextInt(4)));
							}
						}
					}
					break;
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onSkeletonAmuletUse(ArrowLooseEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP && TragicNewConfig.amuSkeleton)
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
				levels[i] = amulets[i] == null ? 0 : amulets[i].getAmuletLevel();
			}
			
			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getAmuletName().equals("Skeleton") && rand.nextInt(16 - (levels[i] * 5)) == 0)
				{
					event.charge += levels[i] * 5;
					break;
				}
			}
		}
	}
}
