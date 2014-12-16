package tragicneko.tragicmc.items.weapons;

import tragicneko.tragicmc.doomsday.Doomsday;

public class WeaponTungstenJack extends ItemJack {

	public WeaponTungstenJack(ToolMaterial material, Doomsday dday) {
		super(material);
		this.doomsday = dday;
	}
	
	//TODO on right click, make it cost like 15 Doom to put a torch down on right-click
}
