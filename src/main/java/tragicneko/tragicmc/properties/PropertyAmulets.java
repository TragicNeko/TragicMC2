package tragicneko.tragicmc.properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import tragicneko.tragicmc.inventory.ContainerAmulet;
import tragicneko.tragicmc.inventory.InventoryAmulet;
import tragicneko.tragicmc.inventory.SlotActiveAmulet;
import tragicneko.tragicmc.items.ItemAmulet;

public class PropertyAmulets implements IExtendedEntityProperties {

	public static final String propertyName = "TragicMC.Amulets";
	private final EntityPlayer thePlayer;
	public final InventoryAmulet inventory = new InventoryAmulet();
	private byte slotsOpen;

	public PropertyAmulets(EntityPlayer player)
	{
		this.thePlayer = player;
		this.slotsOpen = 1;
	}

	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(propertyName, new PropertyAmulets(player));
	}

	public static final PropertyAmulets get(EntityPlayer player)
	{
		return (PropertyAmulets) player.getExtendedProperties(propertyName);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound comp = new NBTTagCompound();
		inventory.writeToNBT(comp);
		comp.setByte("slotsOpen", this.slotsOpen);
		compound.setTag(this.propertyName, comp);

	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		if (compound == null) return;
		NBTTagCompound comp = (NBTTagCompound) compound.getTag(this.propertyName);
		if (comp == null) return;
		inventory.readFromNBT(comp);
		this.slotsOpen = comp.getByte("slotsOpen");
	}

	@Override
	public void init(Entity entity, World world) {
		if (entity instanceof EntityPlayer)
		{
			if (PropertyDoom.get((EntityPlayer) entity) == null)
			{
				PropertyDoom.register((EntityPlayer) entity);
			}
		}
	}

	/**
	 * Unlocks another amulet slot for the player to use
	 */
	public boolean openAmuletSlot()
	{
		if (this.slotsOpen < 3)
		{
			this.slotsOpen++;
			return true;
		}

		return false;
	}

	/**
	 * Returns how many slots should be unlocked currently
	 * @return
	 */
	public int getSlotsOpen()
	{
		return this.slotsOpen;
	}

	/**
	 * Returns null if no amulets in slot specified or if the slot that is called in is currently locked (safeguard for cheating)
	 * @param slot
	 * @return
	 */
	public ItemAmulet getActiveAmulet(int slot)
	{
		if (this.inventory.getStackInSlot(slot) != null)
		{
			ContainerAmulet container = new ContainerAmulet(thePlayer, thePlayer.inventory, this.inventory);
			Slot invSlot = (Slot) container.inventorySlots.get(slot);

			if (invSlot instanceof SlotActiveAmulet && !((SlotActiveAmulet) invSlot).isLocked())
			{
				return (ItemAmulet) this.inventory.getStackInSlot(slot).getItem();
			}
		}
		return null;
	}
	
	public ItemStack getActiveAmuletItemStack(int slot)
	{
		if (this.inventory.getStackInSlot(slot) != null)
		{
			ContainerAmulet container = new ContainerAmulet(thePlayer, thePlayer.inventory, this.inventory);
			Slot invSlot = (Slot) container.inventorySlots.get(slot);

			if (invSlot instanceof SlotActiveAmulet && !((SlotActiveAmulet) invSlot).isLocked())
			{
				return this.inventory.getStackInSlot(slot);
			}
		}
		
		return null;
	}
	
	/**
	 * Shortcut method to damage an amulet
	 * @param slot
	 * @param damage
	 */
	public void damageStackInSlot(int slot, int damage)
	{
		if (this.getActiveAmulet(slot) != null)
		{
			ContainerAmulet container = new ContainerAmulet(thePlayer, thePlayer.inventory, this.inventory);
			Slot invSlot = (Slot) container.inventorySlots.get(slot);

			if (invSlot instanceof SlotActiveAmulet && !((SlotActiveAmulet) invSlot).isLocked())
			{
				this.inventory.getStackInSlot(slot).damageItem(damage, thePlayer);
			}
		}
	}
}
