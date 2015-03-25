package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDoomUpgrade extends Item {

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.epic;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Increases your Max Doom limit");
		par2List.add("If at max limit, then refills Doom partially");
		par2List.add("Does not affect cooldown");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par2World.isRemote || !TragicConfig.allowDoom) return par1ItemStack;

		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
		if (doom == null) return par1ItemStack;

		if (TragicConfig.shouldDoomLimitIncrease && doom.getMaxDoom() + TragicConfig.doomConsumeAmount <= TragicConfig.maxDoomAmount)
		{
			doom.increaseConsumptionLevel();

			if (TragicConfig.allowConsumeRefill)
			{
				if (TragicConfig.consumeRefillAmount >= 100)
				{
					doom.fillDoom();
				}
				else
				{
					double refill = doom.getMaxDoom() * TragicConfig.consumeRefillAmount / 100;

					if (doom.getCurrentDoom() + refill < doom.getMaxDoom())
					{
						doom.increaseDoom((int) refill);
					}
					else
					{
						doom.fillDoom();
					}
				}
			}

			if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;
			par3EntityPlayer.addChatMessage(new ChatComponentText("Doom max limit increased!"));
		}
		else if (doom.getCurrentDoom() < doom.getMaxDoom())
		{
			if (TragicConfig.allowConsumeRefill)
			{
				if (TragicConfig.consumeRefillAmount >= 100)
				{
					doom.fillDoom();
				}
				else
				{
					double refill = doom.getMaxDoom() * TragicConfig.consumeRefillAmount / 100;

					if (doom.getCurrentDoom() + refill < doom.getMaxDoom())
					{
						doom.increaseDoom((int) refill);
					}
					else
					{
						doom.fillDoom();
					}
				}
			}

			if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;
			par3EntityPlayer.addChatMessage(new ChatComponentText("Doom was refilled!"));
		}

		return par1ItemStack;
	}
}
