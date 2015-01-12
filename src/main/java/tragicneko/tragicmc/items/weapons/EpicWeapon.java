package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.doomsday.Doomsday;

public class EpicWeapon extends TragicWeapon {

	public EpicWeapon(Doomsday dday) {
		super(TragicItems.toolEpic, dday);
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach};
		this.uncommonLevels = new int[] {3, 3};
	}
}
