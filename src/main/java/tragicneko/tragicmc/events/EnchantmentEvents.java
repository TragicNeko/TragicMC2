package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.ArrayList;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentEvents {

	private Set replaceableBlocks = Sets.newHashSet(new Block[] {Blocks.air, TragicBlocks.Luminescence});

	@SubscribeEvent
	public void onLuminescence(LivingUpdateEvent event)
	{
		if (!event.entityLiving.worldObj.isRemote && TragicConfig.allowLuminescence)
		{
			boolean flag = false;

			for (int i = 0; i < 5; i++)
			{
				ItemStack stack = event.entityLiving.getEquipmentInSlot(i);
				if (stack != null && stack.getItem() != Items.enchanted_book && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Luminescence.effectId, stack) > 0) flag = true;
			}
			if (!flag) return;

			ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(event.entityLiving.worldObj, 1.25, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ);
			int[] coords;
			Block block;

			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				block = event.entityLiving.worldObj.getBlock(coords[0], coords[1], coords[2]);
				if (replaceableBlocks.contains(block))
				{
					event.entityLiving.worldObj.setBlock(coords[0], coords[1], coords[2], TragicBlocks.Luminescence); 
				}
			}
		}
	}

	@SubscribeEvent
	public void onCombustion(HarvestDropsEvent event)
	{
		if (event.harvester != null && !event.isSilkTouching && TragicConfig.allowCombustion)
		{			
			if (event.harvester.getEquipmentInSlot(0) != null)
			{
				ItemStack tool = event.harvester.getEquipmentInSlot(0);

				if (EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Combustion.effectId, tool) > 0)
				{
					int z = EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, tool);
					ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(event.block, 1, event.blockMetadata));

					if (result != null)
					{
						result.stackSize = rand.nextInt(z + 1) + 1;
						tool.attemptDamageItem(1, rand);
						event.drops.clear();
						event.drops.add(result.copy());
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
		if (TragicConfig.allowMultiply)
		{
			EntityPlayer player = event.entityPlayer;
			World world = player.worldObj;

			float f = event.charge / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if (f < 0.5D)
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
					entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D);
					entityarrow2.setDamage(entityarrow.getDamage() + k * 0.5D);
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
		if (event.entityLiving instanceof EntityPlayer && TragicConfig.allowAgility && !event.source.isMagicDamage())
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
		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer && TragicConfig.allowWeaponEnchants)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				float f = event.ammount / 10;

				if (TragicConfig.allowVampirism)
				{
					int vamp = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Vampirism.effectId, player.getCurrentEquippedItem());

					if (vamp > 0 && rand.nextInt(10 - (vamp * 2)) == 0 && rand.nextInt(4) == 0)
					{
						player.heal(f * vamp);

						if (TragicConfig.allowCripple)
						{
							event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Cripple.id, 60 * vamp, vamp * 2));
						}

						if (rand.nextInt(3) == 0)
						{
							player.addExhaustion(f);
						}
					}
				}

				if (TragicConfig.allowLeech)
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

				if (TragicConfig.allowConsume)
				{
					int consume = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Consume.effectId, player.getCurrentEquippedItem());

					if (consume > 0 && rand.nextInt(10 - (consume * 2)) == 0 && rand.nextInt(3) == 0)
					{
						PropertyDoom property1 = PropertyDoom.get(player);

						if (property1 != null && TragicConfig.allowDoom)
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

				if (TragicConfig.allowRuneBreak)
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

	@SubscribeEvent
	public void onUnbreakableUse(LivingAttackEvent event)
	{
		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			if (player.getCurrentEquippedItem() != null)
			{
				ItemStack stack = player.getCurrentEquippedItem();

				if (stack.getItemDamage() >= stack.getMaxDamage() - 1 && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Unbreakable.effectId, stack) > 0)
				{
					if (event.isCancelable()) event.setCanceled(true);
				}
			}
		}
	}
}
