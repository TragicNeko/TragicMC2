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

	public static final Map<UUID, NBTTagCompound> extendedEntityData = new HashMap<UUID, NBTTagCompound>();

	public void registerRenders(){}

	@Override
	public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (guiId == TragicMC.idAmuletGui) 
		{
			return new ContainerAmulet(player, player.inventory, PropertyAmulets.get(player).inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (guiId == TragicMC.idAmuletGui)
		{
			return new GuiAmuletInventory(player, player.inventory, PropertyAmulets.get(player).inventory);
		}
		return null;
	}

	/**
	 * Adds an entity's custom data to the map for temporary storage
	 */
	public static void storeEntityData(UUID uuid, NBTTagCompound compound) {
		extendedEntityData.put(uuid, compound);
	}

	/**
	 * Removes the compound from the map and returns the NBT tag stored for name or null if none exists
	 */
	public static NBTTagCompound getEntityData(UUID uuid) {
		return extendedEntityData.remove(uuid);
	}
	
	public EntityPlayer getPlayerFromMessageCtx(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
}
