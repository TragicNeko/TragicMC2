package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.doomsday.Doomsday;

public class WeaponGravitySpike extends TragicWeapon {
	
	//private Lore[] uniqueLores = new Lore[] {new Lore("e=mc2"), new Lore("I like 3.14.", EnumRarity.uncommon), new Lore("The next Einstein.", EnumRarity.uncommon),
	//	new Lore("Time for a demonstration!"), new Lore("Reaching escape velocity.", EnumRarity.rare), new Lore("Science rules!", EnumRarity.epic),
	//	new Lore("In spaaaaace!", EnumRarity.epic)};

	public WeaponGravitySpike(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		//this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.knockback};
		this.rareLevels = new int[] {3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.knockback, TragicEnchantments.Distract};
		this.epicLevels = new int[] {5, 3, 1};
	}
}
