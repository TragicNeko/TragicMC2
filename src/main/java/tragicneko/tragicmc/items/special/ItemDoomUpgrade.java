package tragicneko.tragicmc.items.special;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDoomUpgrade extends Item {
	
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.epic;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Increases your Max Doom limit");
		par2List.add("If at max limit, then refills Doom partially");
		par2List.add("Does not affect cooldown");
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par2World.isRemote) return par1ItemStack;
		
		if (TragicNewConfig.shouldDoomLimitIncrease)
		{
			PropertyDoom property = PropertyDoom.get(par3EntityPlayer);

			if (property.getMaxDoom() + 100 <= TragicNewConfig.maxDoomAmount)
			{
				property.increaseConsumptionLevel(100);

				if (TragicNewConfig.allowConsumeRefill)
				{
					if (TragicNewConfig.consumeRefillAmount >= 100)
					{
						property.fillDoom();
					}
					else
					{
						int total = property.getMaxDoom();
						float percent = (TragicNewConfig.consumeRefillAmount / 100);

						if (property.getCurrentDoom() + (total * percent) < property.getMaxDoom())
						{
							property.increaseDoom((int) (total * percent));
						}
						else
						{
							property.fillDoom();
						}
					}
				}

				if (!par3EntityPlayer.capabilities.isCreativeMode)
				{
					par1ItemStack.stackSize--;
				}
				
				par3EntityPlayer.addChatMessage(new ChatComponentText("Doom max limit increased!"));
			}
			else if (property.getCurrentDoom() < property.getMaxDoom())
			{
				if (TragicNewConfig.allowConsumeRefill)
				{
					if (TragicNewConfig.consumeRefillAmount >= 100)
					{
						property.fillDoom();
					}
					else
					{
						int total = property.getMaxDoom();
						float percent = (TragicNewConfig.consumeRefillAmount / 100);

						if (property.getCurrentDoom() + (total*percent) < property.getMaxDoom())
						{
							property.increaseDoom((int) (total * percent));
						}
						else
						{
							property.fillDoom();
						}
					}
				}

				if (!par3EntityPlayer.capabilities.isCreativeMode)
				{
					par1ItemStack.stackSize--;
				}
				
				par3EntityPlayer.addChatMessage(new ChatComponentText("Doom was refilled!"));
			}
		}
		return par1ItemStack;
	}
}
