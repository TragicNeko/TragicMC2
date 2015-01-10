package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.doomsday.Doomsday;

public class WeaponCelestialAegis extends TragicWeapon {

	public WeaponCelestialAegis(ToolMaterial material, Doomsday dday) {
		super(material, dday);
		this.doomsday2 = Doomsday.Purge;
	}
}
