package tragicneko.tragicmc.items.special;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.dimension.TragicTeleporter;
import tragicneko.tragicmc.main.TragicNewConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDimensionalKey extends Item {

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.epic;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add(EnumChatFormatting.DARK_RED + "Teleports you to the Tragic Dimension!");
		par2List.add("Hold down right-click for a couple");
		par2List.add("seconds then let go to use.");
		par2List.add("The dimension is WIP");
	}

	public EnumAction getItemInUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}

	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		return par1ItemStack;
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}

	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if (par5)
		{
			if (par2World.isRemote && par3Entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) par3Entity;

				if (player.getItemInUseCount() > 0 && !((EntityPlayer)par3Entity).capabilities.isCreativeMode)
				{
					player.setInPortal();
				}
			}
		}

	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));

		return par1ItemStack;
	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

		if (TragicNewConfig.allowDimension)
		{
			if (par3EntityPlayer instanceof EntityPlayerMP)
			{
				if (par4 >= 30 || par3EntityPlayer.capabilities.isCreativeMode)
				{
					int dim = par2World.provider.dimensionId;
					int id = TragicNewConfig.dimensionID;

					ServerConfigurationManager manager = MinecraftServer.getServer().getConfigurationManager();

					if (dim != id)
					{
						manager.transferPlayerToDimension((EntityPlayerMP) par3EntityPlayer, TragicNewConfig.dimensionID, new TragicTeleporter(MinecraftServer.getServer().worldServerForDimension(id)));
					}
					else
					{
						manager.transferPlayerToDimension((EntityPlayerMP) par3EntityPlayer, 0, new TragicTeleporter(MinecraftServer.getServer().worldServerForDimension(0)));
					}
				}
				
				if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.damageItem(1, par3EntityPlayer); 
			}
		}
		else
		{
			par3EntityPlayer.addChatMessage(new ChatComponentText("Tragic Dimension is disabled, enable in config."));
		}
	}
}
