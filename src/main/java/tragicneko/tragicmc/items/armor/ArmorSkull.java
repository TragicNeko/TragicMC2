package tragicneko.tragicmc.items.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorSkull extends TragicArmor {
	
	protected Lore[] uniqueLores = new Lore[] {new Lore("Stinky", EnumRarity.uncommon), new Lore("Sniff sniff"), new Lore("Something's in the air", EnumRarity.uncommon),
			new Lore("You smell that?"), new Lore("Something smells raunchy...", EnumRarity.uncommon), new Lore("That's some pretty bad breath", EnumRarity.uncommon),
			new Lore("Love stinks", EnumRarity.rare), new Lore("Smells like rotten eggs..."), new Lore("This smell is making me quite nauseous"),
			new Lore("Anyone have an air freshener?", EnumRarity.uncommon), new Lore("Smells Like Teen Spirit", EnumRarity.epic),
			new Lore("Smells like Nirvana", EnumRarity.rare), new Lore("You smell like garbage..."), new Lore("Grouch paradise right here!", EnumRarity.rare),
			new Lore("Let me guess, your job is a garbage man."), new Lore("Well, this stinks.", EnumRarity.uncommon), new Lore("Seems a bit trashy"),
			new Lore("The nose knows!", EnumRarity.uncommon), new Lore("At least it's a minty garbage smell now"), new Lore("Someone needs to be changed!"),
			new Lore("I bet the Boogeyman smells better", EnumRarity.uncommon), new Lore("You smell like you rolled around in garbage.", EnumRarity.rare),
			new Lore("We don't deliver to sewers", EnumRarity.rare), new Lore("Do you live in a sewer?", EnumRarity.uncommon), new Lore("Are you a hobo?"),
			new Lore("I put Oscar the Grouch to shame!", EnumRarity.epic), new Lore("Everyone, to the latrine!"), new Lore("Stinks so good!", EnumRarity.rare)};

	public ArmorSkull(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
		this.lores = uniqueLores;
		this.rareEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking}, {Enchantment.unbreaking}};
		this.rareLevels = new int[][] {{3}, {3}, {3}};
		this.epicEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking, TragicEnchantments.DeathTouch}, {Enchantment.unbreaking}};
		this.epicLevels = new int[][] {{5}, {5, 3}, {5}};
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (slot == 2)
		{
			return "tragicmc:textures/armor/Skull2" + TragicItems.textureRes + ".png";
		}
		return "tragicmc:textures/armor/Skull1" + TragicItems.textureRes + ".png";
	}
}
