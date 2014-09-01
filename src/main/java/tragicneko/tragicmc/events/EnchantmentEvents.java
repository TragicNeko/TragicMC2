package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.ArrayList;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentEvents {

	@SubscribeEvent
	public void onCombustion(HarvestDropsEvent event)
	{
		if (event.harvester != null && !event.isSilkTouching && TragicNewConfig.allowCombustion)
		{			
			if (event.harvester.getCurrentEquippedItem() != null)
			{
				ItemStack tool = event.harvester.getCurrentEquippedItem();

				if (EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Combustion.effectId, tool) > 0)
				{
					ArrayList<ItemStack> list = event.drops;

					for (int i = 0; i < list.size(); i++)
					{
						ItemStack itemstack = (ItemStack) list.get(i);

						if (itemstack.toString().contains("tile.mercuryOreBlock"))
						{
							event.drops.remove(i);

							int z = EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, tool);
							event.drops.add(new ItemStack(TragicItems.RedMercury, rand.nextInt(z + 1) + 1));

							for (int j = 0; j < 30; j++)
							{
								event.harvester.worldObj.spawnParticle("flame", event.harvester.posX + rand.nextInt(4) * MathHelper.getRandomIntegerInRange(rand, -1, 1),
										event.harvester.eyeHeight / 2, event.harvester.posZ + rand.nextInt(4) * MathHelper.getRandomIntegerInRange(rand, -1, 1),
										rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
							}
						}
						if (itemstack.toString().contains("tile.tungstenOreBlock"))
						{
							event.drops.remove(i);

							int z = EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, tool);
							event.drops.add(new ItemStack(TragicItems.Tungsten, rand.nextInt(z + 1) + 1));

							for (int j = 0; j < 30; j++)
							{
								event.harvester.worldObj.spawnParticle("flame", event.harvester.posX + rand.nextInt(4) * MathHelper.getRandomIntegerInRange(rand, -1, 1),
										event.harvester.eyeHeight / 2, event.harvester.posZ + rand.nextInt(4) * MathHelper.getRandomIntegerInRange(rand, -1, 1),
										rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
							}
						}
						if (itemstack.toString().contains("tile.oreIron"))
						{
							event.drops.remove(i);

							int z = EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, tool);
							event.drops.add(new ItemStack(Items.iron_ingot, rand.nextInt(z + 1) + 1));

							for (int j = 0; j < 30; j++)
							{
								event.harvester.worldObj.spawnParticle("flame", event.harvester.posX + rand.nextInt(4) * MathHelper.getRandomIntegerInRange(rand, -1, 1),
										event.harvester.eyeHeight / 2, event.harvester.posZ + rand.nextInt(4) * MathHelper.getRandomIntegerInRange(rand, -1, 1),
										rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
							}
						}
						if (itemstack.toString().contains("tile.oreGold"))
						{
							event.drops.remove(i);

							int z = EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, tool);
							event.drops.add(new ItemStack(Items.gold_ingot, rand.nextInt(z + 1) + 1));

							for (int j = 0; j < 30; j++)
							{
								event.harvester.worldObj.spawnParticle("flame", event.harvester.posX + rand.nextInt(4) * MathHelper.getRandomIntegerInRange(rand, -1, 1),
										event.harvester.eyeHeight / 2, event.harvester.posZ + rand.nextInt(4) * MathHelper.getRandomIntegerInRange(rand, -1, 1),
										rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
							}
						}
					}
				}
			}
		}
	}

	/*
	@SubscribeEvent
	public void luck(LivingEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer lucky = (EntityPlayer) event.entityLiving;

			for (int i = 0; i < 4; i++)
			{
				if (lucky.getCurrentArmor(i) != null)
				{					
					ItemStack stack = lucky.getCurrentArmor(i);
					if (EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Luck.effectId, stack) > 0)
					{
						if (rand.nextInt(1000) == 42)
						{
							lucky.addExperience(rand.nextInt(11) + 10);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void luckyXpPickup(PlayerPickupXpEvent event)
	{
		EntityPlayer lucky = event.entityPlayer;

		for (int i = 0; i < 4; i++)
		{
			if (lucky.getCurrentArmor(i) != null)
			{					
				ItemStack stack = lucky.getCurrentArmor(i);
				if (EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Luck.effectId, stack) > 0)
				{
					if (rand.nextInt(10) == 4)
					{
						lucky.addExperience(rand.nextInt(5));
					}
				}
			}
		}

	}


	@SubscribeEvent
	public void rangeIncrease(PlayerEvent event)
	{
		if (event.entityPlayer.inventory.getCurrentItem() != null)
		{
			ItemStack weapon = event.entityPlayer.inventory.getCurrentItem();

			int v = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Range.effectId, weapon);

			if (v > 0)
			{
				if (v > 2)
				{
					v = 2;
				}

				PlayerControllerMP controller = Minecraft.getMinecraft().playerController;
				EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

				if (Minecraft.getMinecraft().objectMouseOver != null)
				{
					if (Minecraft.getMinecraft().objectMouseOver.typeOfHit.ordinal() == 1)
					{
						if (event.entityPlayer.getDistanceSqToEntity(Minecraft.getMinecraft().objectMouseOver.entityHit) < 4.5 + v)
						{
							controller.attackEntity(player, Minecraft.getMinecraft().objectMouseOver.entityHit);
							controller.updateController();
						}
					}
				} 

			}
		}
	} */

	@SubscribeEvent
	public void multiplyArrow(ArrowLooseEvent event)
	{
		if (TragicNewConfig.allowMultiply)
		{
			EntityPlayer player = event.entityPlayer;
			World world = player.worldObj;

			float f = (float)event.charge / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if ((double)f < 0.5D)
			{
				return;
			}

			if (f > 1.0F)
			{
				f = 1.0F;
			}

			if (EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Multiply.effectId, event.bow) > 0)
			{
				EntityArrow entityarrow = new EntityArrow(world, player, f * 1.5F);
				entityarrow.motionX *= 0.95;
				entityarrow.motionZ *= 0.95;
				EntityArrow entityarrow2 = new EntityArrow(world, player, f * 1.5F);
				entityarrow2.motionX *= 1.3;
				entityarrow2.motionZ *= 1.3;

				if (f == 1.0F)
				{
					entityarrow.setIsCritical(true);
					entityarrow2.setIsCritical(true);
				}

				int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, event.bow);

				if (k > 0)
				{
					entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D);
					entityarrow2.setDamage(entityarrow.getDamage() + (double)k * 0.5D);
				}

				int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, event.bow);

				if (l > 0)
				{
					entityarrow.setKnockbackStrength(0);
					entityarrow2.setKnockbackStrength(0);
				}

				if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, event.bow) > 0)
				{
					entityarrow.setFire(100);
					entityarrow2.setFire(100);
				}

				entityarrow.canBePickedUp = 2;
				entityarrow2.canBePickedUp = 2;

				if (!world.isRemote)
				{
					world.spawnEntityInWorld(entityarrow);
					world.spawnEntityInWorld(entityarrow2);
				}
			}
		}
	}

	@SubscribeEvent
	public void withAgility(LivingHurtEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer && TragicNewConfig.allowAgility && !event.source.isMagicDamage())
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (!event.source.canHarmInCreative())
			{				
				for (int x = 1; x < 5; x++)
				{
					if (player.getEquipmentInSlot(x) != null)
					{
						ItemStack armor = player.getEquipmentInSlot(x);

						if (EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Agility.effectId, armor) > 0)
						{
							int l = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Agility.effectId, armor);

							if (rand.nextInt(400) <= 10 * l)
							{
								if (event.isCancelable())
								{
									event.setCanceled(true);
								}
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onExtraWeaponEnchantUse(LivingHurtEvent event)
	{
		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer && TragicNewConfig.allowWeaponEnchants)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				float f = event.ammount / 10;

				if (TragicNewConfig.allowVampirism)
				{
					int vamp = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Vampirism.effectId, player.getCurrentEquippedItem());

					if (vamp > 0 && rand.nextInt(10 - (vamp * 2)) == 0 && rand.nextInt(4) == 0)
					{
						player.heal(f * vamp);

						if (TragicNewConfig.allowCripple)
						{
							event.entityLiving.addPotionEffect(new PotionEffect(TragicPotions.Cripple.id, 60 * vamp, vamp * 2));
						}

						if (rand.nextInt(3) == 0)
						{
							player.addExhaustion(f);
						}
					}
				}

				if (TragicNewConfig.allowLeech)
				{
					int leech = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Leech.effectId, player.getCurrentEquippedItem());

					if (leech > 0 && rand.nextInt(10 - (leech * 2)) == 0 && rand.nextInt(2) == 0)
					{
						int food = player.getFoodStats().getFoodLevel();

						if (food + leech <= 20)
						{
							player.getFoodStats().setFoodLevel(food + leech);
						}

						if (event.entityLiving instanceof EntityPlayer)
						{
							((EntityPlayer) event.entityLiving).addExhaustion(f);
						}

						if (rand.nextInt(3) == 0)
						{
							PropertyDoom property = PropertyDoom.get(player);
							property.increaseDoom(-leech);
						}
					}
				}

				if (TragicNewConfig.allowConsume)
				{
					int consume = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Consume.effectId, player.getCurrentEquippedItem());

					if (consume > 0 && rand.nextInt(10 - (consume * 2)) == 0 && rand.nextInt(3) == 0)
					{
						PropertyDoom property1 = PropertyDoom.get(player);

						if (property1 != null && TragicNewConfig.allowDoom)
						{
							property1.increaseDoom(consume);
							if (event.entityLiving instanceof EntityPlayer)
							{
								PropertyDoom property2 = PropertyDoom.get((EntityPlayer) event.entityLiving);

								property2.increaseDoom(-consume);
							}

							if (rand.nextInt(3) == 0)
							{
								player.attackEntityFrom(DamageSource.generic, consume);
							}
						}
					}
				}

				if (TragicNewConfig.allowRuneBreak)
				{
					int runeBreak = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.RuneBreak.effectId, player.getCurrentEquippedItem());

					if (runeBreak > 0 && event.source.isMagicDamage())
					{
						for (int i = 0; i < runeBreak; i++)
						{
							event.ammount *= 1.125F;
						}
					}
				}
			}
		}
	}
}
