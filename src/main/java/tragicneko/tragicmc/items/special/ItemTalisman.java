package tragicneko.tragicmc.items.special;

import net.minecraft.item.Item;
import tragicneko.tragicmc.main.TragicTabs;

public class ItemTalisman extends Item {

	public ItemTalisman()
	{
		this.setCreativeTab(TragicTabs.Survival);
		this.setMaxDamage(1000);
		this.setMaxStackSize(1);
	}
}
