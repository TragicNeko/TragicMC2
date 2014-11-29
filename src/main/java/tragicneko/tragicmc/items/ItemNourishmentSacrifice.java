package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNourishmentSacrifice extends Item {

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Sacrifice some Hunger for Doom");
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
			float amount = par3EntityPlayer.getFoodStats().getFoodLevel() - 1;
			amount = (amount / 20.0F) * property.getMaxDoom();

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
				
				par3EntityPlayer.getFoodStats().addStats(-par3EntityPlayer.getFoodStats().getFoodLevel() + 1, 0.0F);
				if (TragicNewConfig.allowMalnourish) par3EntityPlayer.addPotionEffect(new PotionEffect(TragicPotions.Malnourish.id, 600, 0));
				if (TragicNewConfig.allowConvergence) par3EntityPlayer.addPotionEffect(new PotionEffect(TragicPotions.Convergence.id, 300));
				
				if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;
				
				par3EntityPlayer.addChatMessage(new ChatComponentText("Hunger sacrificed!"));
			}
		}

		return par1ItemStack;
	}
}
