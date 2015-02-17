package tragicneko.tragicmc.items.armor;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.util.LoreHelper;
import tragicneko.tragicmc.util.LoreHelper.EnchantEntry;
import tragicneko.tragicmc.util.LoreHelper.Lore;
import tragicneko.tragicmc.util.LoreHelper.LoreEntry;

public class TragicArmor extends ItemArmor {

	public final Doomsday doomsday;
	private IIcon damagedIcon;
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

	protected Enchantment[][] uncommonEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking}, {Enchantment.unbreaking}};
	protected int[][] uncommonLevels = new int[][] {{1}, {1}, {1}};

	protected Enchantment[][] rareEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking, TragicEnchantments.Agility}, {Enchantment.unbreaking}};
	protected int[][] rareLevels = new int[][] {{2}, {2, 1}, {2}};

	protected Enchantment[][] epicEnchants = new Enchantment[][] {{Enchantment.unbreaking, Enchantment.projectileProtection}, {Enchantment.unbreaking, TragicEnchantments.Agility,
		Enchantment.protection}, {Enchantment.unbreaking, Enchantment.featherFalling}};
	protected int[][] epicLevels = new int[][] {{3, 1}, {3, 1, 1}, {3, 1}};

	public TragicArmor(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, 0, armorType);
		this.doomsday = dday;
		this.setCreativeTab(TragicMC.Survival);
	}

	@Override
	public void registerIcons(IIconRegister register)
	{
		super.registerIcons(register);
		this.damagedIcon = register.registerIcon(this.iconString + "2");
	}

	@Override
	public IIcon getIconFromDamage(int damage)
	{
		if (damage >= this.getMaxDamage() * 2 / 3) return this.damagedIcon;
		return this.itemIcon;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (TragicConfig.allowRandomWeaponLore && LoreHelper.getRarityFromStack(stack) > 0)
		{
			String lore = LoreHelper.getDescFromStack(stack);
			EnumChatFormatting loreFormat = LoreHelper.getFormatForRarity(LoreHelper.getRarityFromStack(stack));
			
			if (lore != null)
			{
				String[] subs = LoreHelper.splitDesc(lore);
				if (subs != null) for (String sub : subs) par2List.add(loreFormat + sub);
			}
		}

		if (TragicConfig.allowDoomsdays && this.doomsday != null)
		{
			EnumChatFormatting format = doomsday.getDoomsdayType().getFormat();
			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());
			par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(par2EntityPlayer.worldObj));
			par2List.add(""); //extra space
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) 
	{
		
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{	
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();

		if (!stack.stackTagCompound.hasKey("cooldown")) stack.stackTagCompound.setInteger("cooldown", 0);
		if (TragicWeapon.getStackCooldown(stack) > 0) TragicWeapon.setStackCooldown(stack, TragicWeapon.getStackCooldown(stack) - 1);

		if (!TragicConfig.allowRandomWeaponLore) return;

		LoreEntry entry = LoreHelper.getLoreEntry(stack.getItem().getClass());
		if (entry == null) return;
		Lore lore = entry.getRandomLore();
		if (lore == null) return;

		if (!stack.stackTagCompound.hasKey("tragicLoreRarity")) stack.stackTagCompound.setByte("tragicLoreRarity", Byte.valueOf((byte) lore.getRarity()));
		if (!stack.stackTagCompound.hasKey("tragicLoreDesc")) stack.stackTagCompound.setString("tragicLoreDesc", lore.getDesc() == null ? "" : lore.getDesc());

		int rarity = stack.stackTagCompound.getByte("tragicLoreRarity");
		lore = entry.getLoreOfRarity(rarity);

		if (!stack.isItemEnchanted() && lore != null)
		{
			EnchantEntry[] enchants = entry.getEnchantmentsForRarity(rarity);
			if (enchants == null) return;

			for (EnchantEntry e : enchants)
			{
				if (e != null && e.getEnchantment() != null) stack.addEnchantment(e.getEnchantment(), e.getEnchantLevel());
			}
		}
	}
}
