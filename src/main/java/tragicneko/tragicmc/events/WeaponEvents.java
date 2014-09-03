package tragicneko.tragicmc.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WeaponEvents {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void whileHoldingSpecialWeapon(LivingHurtEvent event)
	{
		if (!TragicNewConfig.allowNonDoomsdayAbilities) return;
		
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			PropertyDoom doom = PropertyDoom.get(player);

			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof TragicWeapon && doom != null)
			{
				Item weapon = player.getCurrentEquippedItem().getItem();

				if (weapon == TragicItems.BlindingLight && event.source.isProjectile())
				{
					if (event.isCancelable() && doom.getCurrentDoom() >= 5)
					{
						if (!player.capabilities.isCreativeMode) doom.increaseDoom(-5);
						event.setCanceled(true);
					}
				}
				
				if (weapon == TragicItems.EnigmaShield)
				{
					if (event.source.isUnblockable() && !event.source.isMagicDamage() && !event.source.canHarmInCreative() && event.isCancelable() && doom.getCurrentDoom() >= 8)
					{
						if (!player.capabilities.isCreativeMode) doom.increaseDoom(-8);
						event.setCanceled(true);
					}
				}
				
				if (weapon == TragicItems.TragicSentinel && doom.getCurrentDoom() > 0)
				{
					if ((event.source.isMagicDamage() || event.source.isFireDamage() || event.source.isExplosion() || event.source.isProjectile()) && event.isCancelable()) event.setCanceled(true);
				}
				
				if (weapon == TragicItems.CelestialAegis && doom.getCurrentDoom() > 0)
				{
					event.ammount *= 0.825F;
				}
			}
		}
	}

	@SubscribeEvent
	public void carrotAndPotatoBlockCreation(BonemealEvent event)
	{
		if (event.block == Blocks.carrots)
		{
			int meta = event.world.getBlockMetadata(event.x, event.y, event.z);

			if (meta >= 7)
			{
				if (event.world.rand.nextInt(4) == 0)
				{
					event.world.setBlock(event.x, event.y, event.z, TragicBlocks.CarrotBlock);
					event.world.playSoundAtEntity(event.entityPlayer, "random.pop", 0.4F, 1.0F);
				}
				event.setResult(Result.ALLOW);
			}
		}

		if (event.block == Blocks.potatoes)
		{
			int meta = event.world.getBlockMetadata(event.x, event.y, event.z);

			if (meta >= 7)
			{
				if (event.world.rand.nextInt(4) == 0)
				{
					event.world.setBlock(event.x, event.y, event.z, TragicBlocks.PotatoBlock);
					event.world.playSoundAtEntity(event.entityPlayer, "random.pop", 0.4F, 1.0F);
				}
				event.setResult(Result.ALLOW);
			}
		}
	}

	@SubscribeEvent
	public void glueAnvilUse(AnvilUpdateEvent event)
	{
		if (event.left == null || event.right == null)
		{
			return;
		}

		boolean itemFlag = false;
		boolean glueFlag = false;
		boolean superGlueFlag = false;

		if (event.left.getItem() instanceof ItemTool || event.left.getItem() instanceof ItemSword || event.left.getItem() instanceof ItemBow)
		{
			if (event.left.isItemDamaged() && event.left.getItem().isRepairable())
			{
				itemFlag = true;
			}
		}

		if (event.right.getItem() == TragicItems.Glue)
		{
			glueFlag = true;
		}

		if (event.right.getItem() == TragicItems.SuperGlue)
		{
			superGlueFlag = true;
		}

		if (glueFlag && itemFlag)
		{
			ItemStack stack = event.left.copy();
			int extra = 0;

			if (event.left.isItemEnchanted())
			{
				return;
			}

			if (event.left.getItemDamage() <= 150)
			{
				stack.setItemDamage(0);
			}
			else
			{
				stack.setItemDamage(stack.getItemDamage() - 150);
			}

			if (event.left.getItemDamage() > 0)
			{				
				if (event.left.getItemDamage() >= 50)
				{
					extra += 1;

					if (event.left.getItemDamage() >= 75)
					{
						extra += 1;

						if (event.left.getItemDamage() >= 100)
						{
							extra += 1;

							if (event.left.getItemDamage() >= 150)
							{
								extra += 1;
							}
						}
					}
				}
			}

			event.output = stack;
			event.cost = 1 + extra;
		}

		if (itemFlag && superGlueFlag)
		{
			ItemStack stack = event.left.copy();
			int extra = 0;

			if (event.left.isItemEnchanted())
			{
				extra += 5;
			}

			if (event.left.getItemDamage() <= 1500)
			{
				stack.setItemDamage(0);
			}
			else
			{
				stack.setItemDamage(stack.getItemDamage() - 1500);
			}

			if (event.left.getItemDamage() > 0)
			{				
				if (event.left.getItemDamage() >= 250)
				{
					extra += 3;

					if (event.left.getItemDamage() >= 500)
					{
						extra += 3;

						if (event.left.getItemDamage() >= 750)
						{
							extra += 3;

							if (event.left.getItemDamage() >= 1000)
							{
								extra += 3;

								if (event.left.getItemDamage() >= 1500)
								{
									extra += 3;
								}
							}
						}
					}
				}
			}

			event.output = stack;

			event.cost = 5 + extra;
		}
	}
}
