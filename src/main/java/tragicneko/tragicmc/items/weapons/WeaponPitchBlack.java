package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;

public class WeaponPitchBlack extends TragicWeapon {
	
	private final Lore[] uniqueLores = new Lore[] {new Lore("Black Hole Sun", EnumRarity.rare), new Lore("Black as my soul", EnumRarity.uncommon), new Lore("Pitch Black! ...Wait"),
		new Lore("Paint it Black!", EnumRarity.uncommon), new Lore("Black out, blood in your eyes!", EnumRarity.rare), new Lore("Hide in the Shadows.", EnumRarity.uncommon),
		new Lore("Perfect Dark Zero."), new Lore("Welcome to the Black Parade~", EnumRarity.epic), new Lore("I was born in the dark.", EnumRarity.epic)};

	public WeaponPitchBlack(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay};
		this.uncommonLevels = new int[] {1, 1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay, TragicEnchantments.Vampirism};
		this.rareLevels = new int[] {3, 3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay, TragicEnchantments.Vampirism, Enchantment.looting};
		this.epicLevels = new int[] {10, 5, 3, 3};
	}
}
