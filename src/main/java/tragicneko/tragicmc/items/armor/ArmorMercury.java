package tragicneko.tragicmc.items.armor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.doomsday.Doomsday;

public class ArmorMercury extends TragicArmor {
	/*
	protected Lore[] uniqueLores = new Lore[] {new Lore("Don't touch me!", EnumRarity.uncommon), new Lore("Eww"), new Lore("Get away from me!", EnumRarity.uncommon),
			new Lore("Don't touch it!"), new Lore("Germs!", EnumRarity.uncommon), new Lore("Don't breathe on me!", EnumRarity.uncommon),
			new Lore("Don't touch me with your filthy hands!", EnumRarity.rare), new Lore("Ew, I can feel the germs all over me"), new Lore("I need to be disinfected!"),
			new Lore("Does anyone have disinfectant?", EnumRarity.uncommon), new Lore("Don't touch me you filthy casual!", EnumRarity.epic),
			new Lore("Time for my 4th daily shower!", EnumRarity.rare), new Lore("Eww, it touched me!"), new Lore("I despise personal interaction!", EnumRarity.epic),
			new Lore("Anyone here ever heard of soap?", EnumRarity.rare), new Lore("Eww, what's that smell...", EnumRarity.uncommon), new Lore("Just filth, all around me."),
			new Lore("Ever heard of hygeine?", EnumRarity.rare), new Lore("It's a gift, and a curse", EnumRarity.epic), new Lore("Disease, disease everywhere!"),
			new Lore("Too many things in close proximity!", EnumRarity.uncommon), new Lore("Die you infectious disease!", EnumRarity.rare),
			new Lore("Quick, give me a wipey!", EnumRarity.rare), new Lore("Why is the outside so... dirty...", EnumRarity.rare),
			new Lore("There's dirt everywhere...", EnumRarity.uncommon), new Lore("Eww, there's filth everywhere!")}; */

	public ArmorMercury(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
		//this.lores = uniqueLores;
		this.rareEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking, TragicEnchantments.Toxicity}, {Enchantment.unbreaking}};
		this.rareLevels = new int[][] {{2}, {2, 1}, {2}};
		this.epicEnchants = new Enchantment[][] {{Enchantment.unbreaking, Enchantment.respiration}, {Enchantment.unbreaking, TragicEnchantments.Toxicity,
			TragicEnchantments.Paralysis}, {Enchantment.unbreaking}};
		this.epicLevels = new int[][] {{3, 1}, {3, 3, 1}, {3}};
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (slot == 2)
		{
			return "tragicmc:textures/armor/Mercury2_lowRes.png";
		}
		return "tragicmc:textures/armor/Mercury1_lowRes.png";
	}
}
