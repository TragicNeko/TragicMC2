package tragicneko.tragicmc.creativetabs;

import tragicneko.tragicmc.TragicItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabTragicMC extends CreativeTabs {

	public TabTragicMC(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	public Item getTabIconItem() {
		return TragicItems.AwakeningStone;
	}

}
