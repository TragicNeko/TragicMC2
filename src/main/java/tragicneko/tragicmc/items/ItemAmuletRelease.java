package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyAmulets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAmuletRelease extends Item {
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.epic;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Unlocks an Amulet slot for use");
		par2List.add("Can also be used to restore an");
		par2List.add("Amulet's charge to max.");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote && TragicConfig.shouldUnlockAmuletSlots)
		{
			PropertyAmulets amulets = PropertyAmulets.get(par3EntityPlayer);
			
			if (amulets.getSlotsOpen() < 3)
			{
				amulets.openAmuletSlot();
			}
			else
			{
				return par1ItemStack;
			}
			
			if (!par3EntityPlayer.capabilities.isCreativeMode)
			{
				par1ItemStack.stackSize--;
			}
			
			par3EntityPlayer.addChatMessage(new ChatComponentText("Unlocked a new amulet slot!"));
		}
		return par1ItemStack;
	}
}
