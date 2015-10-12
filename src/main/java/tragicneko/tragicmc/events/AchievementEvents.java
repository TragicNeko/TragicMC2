package tragicneko.tragicmc.events;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AchievementEvents {

	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event) 
	{
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entityLiving;
			mp.triggerAchievement(TragicAchievements.thanks);

			InventoryPlayer inv = mp.inventory;

			if (inv.hasItem(TragicItems.CryingObsidianOrb) || inv.hasItem(TragicItems.DyingObsidianOrb) || inv.hasItem(TragicItems.CryingObsidianOrb))
			{
				mp.triggerAchievement(TragicAchievements.obsidianOrb);
			}

			if (inv.hasItem(TragicItems.DimensionalKey))
			{
				mp.triggerAchievement(TragicAchievements.dimensionalKey);
			}
			
			if (inv.hasItem(TragicItems.SynapseLink))
			{
				mp.triggerAchievement(TragicAchievements.synapseLink);
			}

			for (ItemStack stack : inv.mainInventory)
			{
				if (stack != null && stack.getItem() instanceof TragicWeapon)
				{
					mp.triggerAchievement(TragicAchievements.weapon);
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BreakEvent event)
	{
		if (event.getPlayer() instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.getPlayer();

			if (event.block == TragicBlocks.MercuryOre)
			{
				mp.triggerAchievement(TragicAchievements.mercury);
			}

			if (event.block == TragicBlocks.Aeris && event.blockMetadata >= 2)
			{
				mp.triggerAchievement(TragicAchievements.aeris);
			}
		}
	}
	
	@SubscribeEvent
	public void onAttack(LivingHurtEvent event)
	{
		if (event.source.getEntity() instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.source.getEntity();
			if (TragicConfig.allowReach && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Reach.effectId, mp.getCurrentEquippedItem()) > 0 && mp.getDistanceToEntity(event.entityLiving) > 4.0F)
			{
				mp.triggerAchievement(TragicAchievements.reach);
			}
			
			if (TragicConfig.allowAbsolve && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Absolve.effectId, mp.getCurrentEquippedItem()) > 0 && TragicConfig.allowCorruption && event.entityLiving.isPotionActive(TragicPotion.Corruption))
			{
				mp.triggerAchievement(TragicAchievements.enchant);
			}
			
			if (TragicConfig.allowSlay && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Slay.effectId, mp.getCurrentEquippedItem()) > 0 && event.entityLiving.getCreatureAttribute() == TragicEntities.Beast)
			{
				mp.triggerAchievement(TragicAchievements.enchant);
			}
			
			if (TragicConfig.allowDecay && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Decay.effectId, mp.getCurrentEquippedItem()) > 0 && event.entityLiving.getCreatureAttribute() == TragicEntities.Natural)
			{
				mp.triggerAchievement(TragicAchievements.enchant);
			}
		}
	}
}
