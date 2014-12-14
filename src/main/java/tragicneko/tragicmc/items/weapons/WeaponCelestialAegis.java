package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.doomsday.Doomsday;

public class WeaponCelestialAegis extends TragicWeapon {
	
	//private final Lore[] uniqueLores = new Lore[] {new Lore("Such a Beautiful Night Sky.", EnumRarity.epic), new Lore("Nice tune.", EnumRarity.uncommon),
	//		new Lore("A lovely melody.", EnumRarity.rare), new Lore("Death in 6/4 time.", EnumRarity.rare), new Lore("What a lovely screaming voice you have!", EnumRarity.epic),
	//		new Lore("Death in D-Minor!", EnumRarity.epic), new Lore("Shrieks of terror make a nice ambience~", EnumRarity.rare),
	//		new Lore("Let the music take your breath away~", EnumRarity.epic), new Lore("Playing a Sonata to the sounds of your pain~", EnumRarity.epic),
	//		new Lore("This is my jam!", EnumRarity.uncommon), new Lore("Sounds to die for!", EnumRarity.uncommon), new Lore("Symphony No. 5!", EnumRarity.rare),
	//		new Lore("The Hall of the Mountain King!", EnumRarity.rare), new Lore("Dance of the Hours!", EnumRarity.rare), new Lore("Carmina Burana!", EnumRarity.rare),
	//		new Lore("The Benny Hill Theme!... No? Okay.", EnumRarity.uncommon), new Lore("The Blue Danube!", EnumRarity.epic), new Lore("Symphony No. 5", EnumRarity.epic),
	//		new Lore("Go! Go! Power Rangers!... or not.", EnumRarity.uncommon), new Lore("Heroes in a half-shell, turtle power!... kay bye.", EnumRarity.uncommon),
	//		new Lore("Flight of the Bumblebee!", EnumRarity.rare), new Lore("Screams at 60 beats per minute!", EnumRarity.rare)};

	public WeaponCelestialAegis(ToolMaterial material, Doomsday dday) {
		super(material, dday);
		this.doomsday2 = Doomsday.Purge;
		//this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Absolve};
		this.uncommonLevels = new int[] {3, 1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Absolve, TragicEnchantments.Consume};
		this.rareLevels = new int[] {5, 3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Absolve, TragicEnchantments.Consume, TragicEnchantments.Reach, Enchantment.looting, TragicEnchantments.Luminescence};
		this.epicLevels = new int[] {10, 5, 3, 3, 3, 1};
	}
}
