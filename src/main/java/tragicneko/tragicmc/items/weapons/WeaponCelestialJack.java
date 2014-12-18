package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import tragicneko.tragicmc.doomsday.Doomsday;

public class WeaponCelestialJack extends ItemJack {

	//private final Lore[] lores = new Lore[] {new Lore("Enigmatic.", EnumRarity.uncommon), new Lore("Quite the mystery", EnumRarity.uncommon), new Lore("Elementary, my dear Watson!"),
	//		new Lore("I would've gotten away with it too!"), new Lore("Looks like another mystery solved!", EnumRarity.rare), new Lore("We've got a mystery on our hands!"),
	//		new Lore("It's Old Man Withers from the Amusement Park!", EnumRarity.uncommon), new Lore("To the Mystery Machine!"), new Lore("Scooby Snax?", EnumRarity.rare),
	//		new Lore("Jinkies!", EnumRarity.rare), new Lore("I'm a high functioning Sociopath.", EnumRarity.epic), new Lore("I live by Harry's Code", EnumRarity.uncommon), 
	//		new Lore("Whodunit?"), new Lore("Colonel Mustard with a Knife in the Library!", EnumRarity.epic), new Lore("We just found a Clue!", EnumRarity.rare), 
	//		new Lore("Let's get out our Handy Dandy notebook!", EnumRarity.epic)};

	private Enchantment[] uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
	private int[] uncommonLevels = new int[] {1};

	private Enchantment[] rareEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.knockback};
	private int[] rareLevels = new int[] {3, 1};

	private Enchantment[] epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.knockback, Enchantment.sharpness};
	private int[] epicLevels = new int[] {5, 3, 1};

	public WeaponCelestialJack(ToolMaterial material, Doomsday dday) {
		super(material);
		this.doomsday = dday;
	}

}
