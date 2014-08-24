package tragicneko.tragicmc.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import tragicneko.tragicmc.items.special.ItemAmulet;
import tragicneko.tragicmc.properties.PropertyAmulets;

public class InventoryAmulet implements IInventory {

	private final String invName = "Amulet Inventory";
	private final String tagName = "InvAmulet";

	public static final int invSize = 28;

	ItemStack[] inventory = new ItemStack[invSize];

	@Override
	public int getSizeInventory() 
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return inventory[var1];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) 
	{
		ItemStack stack = getStackInSlot(slot);
		
		if (stack != null) 
		{
			if (stack.stackSize > amount) 
			{
				stack = stack.splitStack(amount);
				markDirty();
			} 
			else 
			{
				setInventorySlotContents(slot, null);
			}
		}

		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) 
	{
		ItemStack stack = getStackInSlot(slot);
		//setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) 
	{
		if (slot > this.getSizeInventory())
		{
			return;
		}
		
		inventory[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) 
		{
			stack.stackSize = getInventoryStackLimit();
		}
		markDirty();

	}

	@Override
	public String getInventoryName() {
		return invName;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return invName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void markDirty() {
		for (int i = 0; i < getSizeInventory(); ++i) 
		{
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
			{
				inventory[i] = null;
			}
		}

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openInventory() {
		NBTTagCompound tag = new NBTTagCompound();
		readFromNBT(tag);
	}

	@Override
	public void closeInventory() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack stack) 
	{
		return stack.getItem() instanceof ItemAmulet;
	}

	public void writeToNBT(NBTTagCompound compound) 
	{
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); ++i) 
		{
			if (getStackInSlot(i) != null) 
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				getStackInSlot(i).writeToNBT(item);
				items.appendTag(item);
			}
		}

		compound.setTag(tagName, items);
	}

	public void readFromNBT(NBTTagCompound compound) 
	{
		if (compound == null) return;
		
		NBTTagList items = compound.getTagList(tagName, compound.getId());
		
		if (items == null) return;
		
		for (int i = 0; i < items.tagCount(); ++i) 
		{
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			if (slot >= 0 && slot < getSizeInventory()) 
			{
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}

}
