package tragicneko.tragicmc.items.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.doomsday.Doomsday;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorTungsten extends TragicArmor {
	/*
	protected Lore[] uniqueLores = new Lore[] {new Lore("Hot stuff."), new Lore("Feels lukewarm"), new Lore("Pretty hot"), new Lore("Warm"), new Lore("Warmer"),
			new Lore("It's a bit stuffy out"), new Lore("Just warming up!", EnumRarity.uncommon), new Lore("Things are heating up!", EnumRarity.rare),
			new Lore("Burn, baby, burn!", EnumRarity.rare), new Lore("I'm pretty heated right now.", EnumRarity.uncommon), new Lore("In heat."),
			new Lore("I'm Hot-Blooded!", EnumRarity.rare), new Lore("Too Hot to Handle!", EnumRarity.epic), new Lore("Caution: Contents may be hot", EnumRarity.epic),
			new Lore("First-degree burn"), new Lore("Second-degree burn", EnumRarity.rare), new Lore("Third-degree burn!", EnumRarity.epic),
			new Lore("I'm on fire!", EnumRarity.uncommon), new Lore("Do I look hot in this?"), new Lore("I look pretty hot in this!", EnumRarity.uncommon),
			new Lore("Need some water for that burn?", EnumRarity.rare), new Lore("You got burned!", EnumRarity.uncommon), new Lore("Burning' for You!", EnumRarity.rare),
			new Lore("Bridges Burning", EnumRarity.uncommon), new Lore("Slow burn", EnumRarity.rare), new Lore("Spontaneous Combustion", EnumRarity.uncommon),
			new Lore("We can't wait to burn it to the ground!", EnumRarity.epic), new Lore("Burn it Down!", EnumRarity.uncommon), new Lore("Scream, Aim, Fire!", EnumRarity.epic),
			new Lore("Shepherd of Fire!", EnumRarity.epic), new Lore("Light my Fire!", EnumRarity.uncommon), new Lore("I Don't Want to Set the World on Fire", EnumRarity.epic),
			new Lore("I fell down in a burning ring of fire", EnumRarity.uncommon), new Lore("Through the Fire and Flames", EnumRarity.uncommon),
			new Lore("Original Fire", EnumRarity.uncommon), new Lore("Caution: Contents may explode under pressure!", EnumRarity.epic)}; */

	public ArmorTungsten(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
		//this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking, TragicEnchantments.Ignition}, {Enchantment.unbreaking}};
		this.uncommonLevels = new int[][] {{1}, {1, 1}, {1}};
		this.rareEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking, TragicEnchantments.Ignition}, {Enchantment.unbreaking}};
		this.rareLevels = new int[][] {{3}, {3, 3}, {3}};
		this.epicEnchants = new Enchantment[][] {{Enchantment.unbreaking, Enchantment.fireProtection}, {Enchantment.unbreaking, TragicEnchantments.Ignition, Enchantment.fireProtection},
				{Enchantment.unbreaking, Enchantment.fireProtection}};
		this.epicLevels = new int[][] {{5, 3}, {5, 5, 3}, {5, 3}};
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		switch (armorType)
		{
		case 0:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:TungstenHelm_lowRes");
			break;
		case 1:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:TungstenPlate_lowRes");
			break;
		case 2:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:TungstenLegs_lowRes");
			break;
		case 3:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:TungstenBoots_lowRes");
			break;
		} 
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (slot == 2)
		{
			return "tragicmc:textures/armor/Tungsten2_lowRes.png";
		}
		return "tragicmc:textures/armor/Tungsten1_lowRes.png";
	}
}


