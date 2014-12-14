package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponEnigmaShield extends ItemShield {
	
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

	public WeaponEnigmaShield(ToolMaterial material, Doomsday dday) {
		super(material);
		this.doomsday = dday;
	}
	/*
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity") ? TragicWeapon.getRarityFromInt(stack.stackTagCompound.getByte("tragicLoreRarity")) : EnumRarity.common;
	}
	
	protected Lore getRandomLore()
	{
		return lores[itemRand.nextInt(lores.length)];
	} */

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));		
		return par1ItemStack;
	}
	
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		/*
		if (TragicNewConfig.allowRandomWeaponLore)
		{
			String lore = null;
			EnumChatFormatting loreFormat = EnumChatFormatting.WHITE;
			if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLore")) lore = stack.stackTagCompound.getString("tragicLore");
			if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity")) loreFormat = TragicWeapon.getFormatFromRarity(stack.stackTagCompound.getByte("tragicLoreRarity"));
			if (lore != null)
			{
				par2List.add(loreFormat + lore);
			}
		} */
		
		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			PropertyDoom doom = PropertyDoom.get(par2EntityPlayer);

			EnumChatFormatting format = EnumChatFormatting.DARK_AQUA;

			if (doomsday.getDoomsdayType() == EnumDoomType.CRISIS)
			{
				format = EnumChatFormatting.DARK_RED;
			}

			if (doomsday.getDoomsdayType() == EnumDoomType.OVERFLOW)
			{
				format = EnumChatFormatting.GREEN;
			}

			if (doomsday.getDoomsdayType() == EnumDoomType.WORLDSHAPER)
			{
				format = EnumChatFormatting.DARK_PURPLE;
			}
			
			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());

			if (doom != null)
			{
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(doom));
			}
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{		
		/*
		if (!TragicNewConfig.allowRandomWeaponLore || world.isRemote || !(entity instanceof EntityPlayer)) return; 
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		Lore lore = getRandomLore();
		if (!stack.stackTagCompound.hasKey("tragicLore")) stack.stackTagCompound.setString("tragicLore", lore.lore);
		if (!stack.stackTagCompound.hasKey("tragicLoreRarity")) stack.stackTagCompound.setByte("tragicLoreRarity", Byte.valueOf((byte)TragicWeapon.getRarityFromEnum(lore)));
		
		if (!stack.isItemEnchanted() && stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity"))
		{
			int rarity = stack.stackTagCompound.getByte("tragicLoreRarity");

			Enchantment[] enchants;
			int[] levels;
			if (rarity == 0) return;
			
			if (rarity == 1)
			{
				enchants = this.uncommonEnchants;
				levels = this.uncommonLevels;
			}
			else if (rarity == 2)
			{
				enchants = this.rareEnchants;
				levels = this.rareLevels;
			}
			else
			{
				enchants = this.epicEnchants;
				levels = this.epicLevels;
			}

			for (int i = 0; i < enchants.length; i++)
			{
				if (enchants[i] != null) stack.addEnchantment(enchants[i], levels[i]);
			}
		} */
	}

}
