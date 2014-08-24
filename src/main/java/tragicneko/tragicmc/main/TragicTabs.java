package tragicneko.tragicmc.main;

import tragicneko.tragicmc.creativetabs.TabTragicMC;
import tragicneko.tragicmc.creativetabs.TabTragicMCSpecial;
import net.minecraft.creativetab.CreativeTabs;

public class TragicTabs {
	
	public static CreativeTabs Survival;
	public static CreativeTabs Creative;
	
	public static void load()
	{
		Survival = (new TabTragicMC(CreativeTabs.getNextID(), "tragicMCMain"));
		Creative = (new TabTragicMCSpecial(CreativeTabs.getNextID(), "tragicMCMobs"));
		
	}

}
