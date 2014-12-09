package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCooldownDefuse extends Item {
	
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.rare;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Removes some global Doomsday cooldown");
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote && TragicNewConfig.allowCooldownDefuse)
		{
			PropertyDoom property = PropertyDoom.get(par3EntityPlayer);

			if (property.getCurrentCooldown() > 0)
			{
				if (TragicNewConfig.defuseRefillAmount >= property.getCurrentCooldown())
				{
					property.setCooldown(0);
				}
				else
				{
					int cooldown = TragicNewConfig.defuseRefillAmount;
					
					if (cooldown < property.getCurrentCooldown())
					{
						property.setCooldown(property.getCurrentCooldown() - cooldown);
					}
				}

				if (!par3EntityPlayer.capabilities.isCreativeMode)
				{
					par1ItemStack.stackSize--;
				}
				
				par3EntityPlayer.addChatMessage(new ChatComponentText("Cooldown was reduced!"));
			}
		}
		return par1ItemStack;
	}
}
