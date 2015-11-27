package tragicneko.tragicmc.client;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.client.gui.GuiAmuletInventory;
import tragicneko.tragicmc.inventory.ContainerAmulet;
import tragicneko.tragicmc.properties.PropertyAmulets;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy implements IGuiHandler {
	
	public static final int AMULET_GUI_ID = 0;

	public void registerRenders(){}

	@Override
	public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z)
	{
		if (guiId == AMULET_GUI_ID && PropertyAmulets.get(player) != null)
		{
			return new ContainerAmulet(player, player.inventory, PropertyAmulets.get(player).inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z)
	{
		if (guiId == AMULET_GUI_ID && PropertyAmulets.get(player) != null)
		{
			return new GuiAmuletInventory(player, player.inventory, PropertyAmulets.get(player).inventory);
		}
		return null;
	}

	public EntityPlayer getPlayerFromMessageCtx(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
}
