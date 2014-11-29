package tragicneko.tragicmc.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemBloodSacrifice extends Item {

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Sacrifice some Health for Doom");
	}
	
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.uncommon;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote && TragicNewConfig.allowDoom)
		{
			PropertyDoom property = PropertyDoom.get(par3EntityPlayer);
			float amount = par3EntityPlayer.getHealth() - 1.0F;
			amount = (amount / par3EntityPlayer.getMaxHealth()) * property.getMaxDoom();

			if (property.getCurrentDoom() < property.getMaxDoom())
			{
				if (amount + property.getCurrentDoom() >= property.getMaxDoom())
				{
					property.fillDoom();
				}
				else
				{
					property.increaseDoom((int) amount);
				}
				
				par3EntityPlayer.setHealth(1.0F);
				
				if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;

				par3EntityPlayer.addChatMessage(new ChatComponentText("Health sacrificed!"));
				if (TragicNewConfig.allowInhibit) par3EntityPlayer.addPotionEffect(new PotionEffect(TragicPotions.Inhibit.id, 600, 0));
				if (TragicNewConfig.allowConvergence) par3EntityPlayer.addPotionEffect(new PotionEffect(TragicPotions.Convergence.id, 300));
			}
		}

		return par1ItemStack;
	}
}
