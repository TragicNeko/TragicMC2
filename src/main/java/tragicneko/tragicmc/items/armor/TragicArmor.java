package tragicneko.tragicmc.items.armor;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicTabs;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class TragicArmor extends ItemArmor {

	public final Doomsday doomsday;
	protected int cooldown;
	protected long tick;

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
		this.setCreativeTab(TragicTabs.Survival);
	}
	/*
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity") ? getRarityFromInt(stack.stackTagCompound.getByte("tragicLoreRarity")) : EnumRarity.common;
	}
	
	protected EnumRarity getRarityFromInt(int i) {
		return i == 1 ? EnumRarity.uncommon : (i == 2 ? EnumRarity.rare : (i == 3 ? EnumRarity.epic : EnumRarity.common));
	}

	protected Lore getRandomLore()
	{
		return lores[itemRand.nextInt(lores.length)];
	} */

	public void registerIcons(IIconRegister register)
	{
		super.registerIcons(register);
		this.damagedIcon = register.registerIcon(this.iconString + "2");
	}

	public IIcon getIconFromDamage(int damage)
	{
		if (damage >= this.getMaxDamage() * 2 / 3) return this.damagedIcon;
		return this.itemIcon;
	}

	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		/*
		if (TragicNewConfig.allowRandomWeaponLore)
		{
			String lore = null;
			EnumChatFormatting loreFormat = EnumChatFormatting.WHITE;
			if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLore")) lore = stack.stackTagCompound.getString("tragicLore");
			if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity")) loreFormat = getFormatFromRarity(stack.stackTagCompound.getByte("tragicLoreRarity"));
			if (lore != null)
			{
				par2List.add(loreFormat + lore);
			}
		} */

		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			EnumChatFormatting format = doomsday.getDoomsdayType().getFormat();
			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());
			par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(par2EntityPlayer.worldObj));
		}
	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) 
	{
		if (!world.isRemote)
		{
			if (cooldown > 0)
			{
				cooldown--;
			}

			tick++;
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{	
		/*
		if (world.isRemote || !TragicNewConfig.allowRandomWeaponLore || !(entity instanceof EntityPlayer)) return; 
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		Lore lore = getRandomLore();
		if (!stack.stackTagCompound.hasKey("tragicLore")) stack.stackTagCompound.setString("tragicLore", lore.lore);
		if (!stack.stackTagCompound.hasKey("tragicLoreRarity")) stack.stackTagCompound.setByte("tragicLoreRarity", Byte.valueOf((byte)getRarityFromEnum(lore)));

		if (!stack.isItemEnchanted() && stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity"))
		{
			int rarity = stack.stackTagCompound.getByte("tragicLoreRarity");
			if (rarity == 0) return;

			Enchantment[] enchants;
			int[] levels;
			int type = this.armorType == 0 ? 0 :(this.armorType == 3 ? 2 : 1); //0 is helmet, 1 is legs/plate, 2 is boots

			if (rarity == 1)
			{
				enchants = this.uncommonEnchants[type];
				levels = this.uncommonLevels[type];
			}
			else if (rarity == 2)
			{
				enchants = this.rareEnchants[type];
				levels = this.rareLevels[type];
			}
			else
			{
				enchants = this.epicEnchants[type];
				levels = this.epicLevels[type];
			}

			for (int i = 0; i < enchants.length; i++)
			{
				if (enchants[i] != null) stack.addEnchantment(enchants[i], levels[i]);
			}
		} */
	}

	/*
	protected int getRarityFromEnum(Lore lore)
	{
		return lore.rarity == EnumRarity.common ? 0 : (lore.rarity == EnumRarity.uncommon ? 1 : (lore.rarity == EnumRarity.rare ? 2 : 3));
	}

	protected EnumChatFormatting getFormatFromRarity(int rarity)
	{
		return rarity == 0 ? EnumChatFormatting.GRAY : (rarity == 1 ? EnumChatFormatting.YELLOW : (rarity == 2 ? EnumChatFormatting.DARK_GREEN : EnumChatFormatting.RED));
	} */

}
