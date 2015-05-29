package tragicneko.tragicmc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import tragicneko.tragicmc.enchantment.EnchantmentAgility;
import tragicneko.tragicmc.enchantment.EnchantmentArmorExtra;
import tragicneko.tragicmc.enchantment.EnchantmentCombustion;
import tragicneko.tragicmc.enchantment.EnchantmentDamageBoost;
import tragicneko.tragicmc.enchantment.EnchantmentLuminescence;
import tragicneko.tragicmc.enchantment.EnchantmentMultiply;
import tragicneko.tragicmc.enchantment.EnchantmentRange;
import tragicneko.tragicmc.enchantment.EnchantmentRuneWalker;
import tragicneko.tragicmc.enchantment.EnchantmentUnbreakable;
import tragicneko.tragicmc.enchantment.EnchantmentVeteran;
import tragicneko.tragicmc.enchantment.EnchantmentWeaponExtra;

public class TragicEnchantments {

	public static Enchantment Decay, Absolve, Slay, Vampirism, Leech, Consume, Distract, Combustion, Multiply, RuneBreak, Reach, Unbreakable, Rust, Veteran;
	public static Enchantment Ignition, Paralysis, Toxicity, Elasticity, DeathTouch, Agility, RuneWalker, Luminescence;

	public static void load()
	{
		if (TragicConfig.allowDecay) Decay = (new EnchantmentDamageBoost(TragicConfig.idDecay, 8, 0));
		if (TragicConfig.allowAbsolve) Absolve = (new EnchantmentDamageBoost(TragicConfig.idAbsolve, 1, 1));
		if (TragicConfig.allowSlay) Slay = (new EnchantmentDamageBoost(TragicConfig.idSlay, 8, 2));
		if (TragicConfig.allowVampirism) Vampirism = (new EnchantmentWeaponExtra(TragicConfig.idVampirism, 1, 0));
		if (TragicConfig.allowLeech) Leech = (new EnchantmentWeaponExtra(TragicConfig.idLeech, 1, 1));
		if (TragicConfig.allowConsume) Consume = (new EnchantmentWeaponExtra(TragicConfig.idConsume, 1, 2));
		if (TragicConfig.allowDistract) Distract = (new EnchantmentWeaponExtra(TragicConfig.idDistract, 8, 3));
		if (TragicConfig.allowCombustion) Combustion = (new EnchantmentCombustion(TragicConfig.idCombustion, 1, EnumEnchantmentType.digger));
		if (TragicConfig.allowMultiply) Multiply = (new EnchantmentMultiply(TragicConfig.idMultiply, 1, EnumEnchantmentType.bow));
		if (TragicConfig.allowRuneBreak) RuneBreak = (new EnchantmentDamageBoost(TragicConfig.idRuneBreak, 4, 3));
		if (TragicConfig.allowReach) Reach = (new EnchantmentRange(TragicConfig.idReach, 6, EnumEnchantmentType.weapon));
		if (TragicConfig.allowUnbreakable) Unbreakable = (new EnchantmentUnbreakable(TragicConfig.idUnbreakable, 1, EnumEnchantmentType.breakable));
		if (TragicConfig.allowRust) Rust = (new EnchantmentWeaponExtra(TragicConfig.idRust, 6, 4));
		if (TragicConfig.allowVeteran) Veteran = (new EnchantmentVeteran(TragicConfig.idVeteran, 1, EnumEnchantmentType.digger));

		if (TragicConfig.allowIgnition)Ignition = (new EnchantmentArmorExtra(TragicConfig.idIgnition, 6, 0));
		if (TragicConfig.allowParalysis)Paralysis = (new EnchantmentArmorExtra(TragicConfig.idParalysis, 1, 1));
		if (TragicConfig.allowToxicity)Toxicity = (new EnchantmentArmorExtra(TragicConfig.idToxicity, 4, 2));
		if (TragicConfig.allowElasticity)Elasticity = (new EnchantmentArmorExtra(TragicConfig.idElasticity, 10, 3));
		if (TragicConfig.allowDeathTouch)DeathTouch = (new EnchantmentArmorExtra(TragicConfig.idDeathTouch, 1, 4));
		if (TragicConfig.allowAgility) Agility = (new EnchantmentAgility(TragicConfig.idAgility, 2, EnumEnchantmentType.armor));
		if (TragicConfig.allowRuneWalker) RuneWalker = (new EnchantmentRuneWalker(TragicConfig.idRuneWalker, 2, EnumEnchantmentType.armor));
		if (TragicConfig.allowLuminescence) Luminescence = (new EnchantmentLuminescence(TragicConfig.idLuminescence, 1, EnumEnchantmentType.all));
	}
}
