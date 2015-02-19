package tragicneko.tragicmc.items.armor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.doomsday.Doomsday;

public class ArmorHunter extends TragicArmor {
	
	/*
	protected Lore[] lores = new Lore[] {new Lore("Move swiftly.", EnumRarity.uncommon), new Lore("Make Haste."), new Lore("Feel the wind on your face!", EnumRarity.uncommon),
			new Lore("Fast as the wind!"), new Lore("Too fast, too furious", EnumRarity.uncommon), new Lore("Windswept.", EnumRarity.uncommon),
			new Lore("Rock You Like a Hurricane!", EnumRarity.rare), new Lore("Watch out for windburn!"), new Lore("Like a tornado!"),
			new Lore("Like the howling wind.", EnumRarity.uncommon), new Lore("Eye of the hurricane", EnumRarity.epic), new Lore("In the eye of the storm", EnumRarity.rare),
			new Lore("Like a Tsunami"), new Lore("All the force of the great typhoon!", EnumRarity.epic), new Lore("Swift as the coursing river!", EnumRarity.rare),
			new Lore("Used Gust!", EnumRarity.uncommon), new Lore("Used Whirlwind!"), new Lore("Used Sky Attack! Critical Hit!", EnumRarity.rare),
			new Lore("Used Fly! It's super effective!", EnumRarity.epic), new Lore("I'm like a bird"), new Lore("Here comes the Rooster!", EnumRarity.uncommon),
			new Lore("Won't you find me, Free Bird!", EnumRarity.rare), new Lore("He crawls like a worm from a bird!", EnumRarity.rare),
			new Lore("Fly like the wind, Bullseye!", EnumRarity.rare), new Lore("I can go the distance!", EnumRarity.uncommon)}; */

	//protected Enchantment[][] uncommonEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking}, {Enchantment.unbreaking}};
	//protected int[][] uncommonLevels = new int[][] {{1}, {1}, {1}};

	//protected Enchantment[][] rareEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking, TragicEnchantments.Agility}, {Enchantment.unbreaking}};
	//protected int[][] rareLevels = new int[][] {{2}, {2, 1}, {2}};

	//protected Enchantment[][] epicEnchants = new Enchantment[][] {{Enchantment.unbreaking, Enchantment.projectileProtection}, {Enchantment.unbreaking, TragicEnchantments.Agility,
	//	Enchantment.protection}, {Enchantment.unbreaking, Enchantment.featherFalling}};
	//protected int[][] epicLevels = new int[][] {{3, 1}, {3, 1, 1}, {3, 1}};

	public ArmorHunter(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == TragicItems.HuntersLegs)
		{
			return "tragicmc:textures/armor/Hunter2_lowRes.png";
		}
		return "tragicmc:textures/armor/Hunter1_lowRes.png";
	}
}
