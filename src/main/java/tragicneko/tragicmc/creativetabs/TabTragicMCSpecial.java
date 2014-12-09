package tragicneko.tragicmc.creativetabs;

import tragicneko.tragicmc.TragicItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabTragicMCSpecial extends CreativeTabs {

	public TabTragicMCSpecial(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	public Item getTabIconItem() {
		return TragicItems.NekoNekoWand;
	}

}
