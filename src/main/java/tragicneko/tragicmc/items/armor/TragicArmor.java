package tragicneko.tragicmc.items.armor;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicTabs;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TragicArmor extends ItemArmor {
	
	public final Doomsday doomsday;
	protected int cooldown;
	protected long tick;
	
	protected Lore[] lores = new Lore[] {new Lore("Lore1", EnumRarity.rare), new Lore("Lore2"), new Lore("Lore3", EnumRarity.epic), new Lore("Lore4", EnumRarity.rare),
			new Lore("Lore5", EnumRarity.uncommon), new Lore("Lore6"), new Lore("Lore7", EnumRarity.rare), new Lore("Lore8", EnumRarity.epic), new Lore("Lore9", EnumRarity.epic),
			new Lore("Lore10"), new Lore("Lore11", EnumRarity.rare), new Lore("Lore12", EnumRarity.epic)};

	protected Enchantment[][] uncommonEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking}, {Enchantment.unbreaking}};
	protected int[][] uncommonLevels = new int[][] {{1}, {1}, {1}};

	protected Enchantment[][] rareEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking}, {Enchantment.unbreaking}};
	protected int[][] rareLevels = new int[][] {{2}, {2}, {2}};

	protected Enchantment[][] epicEnchants = new Enchantment[][] {{Enchantment.unbreaking, Enchantment.respiration}, {Enchantment.unbreaking, Enchantment.protection},
			{Enchantment.unbreaking, Enchantment.featherFalling}};
	protected int[][] epicLevels = new int[][] {{3, 1}, {3, 1}, {3, 1}};

	public TragicArmor(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, 0, armorType);
		this.doomsday = dday;
		this.setCreativeTab(TragicTabs.Survival);
	}
	
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
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("tragicmc:NoTexture");
	}
	
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
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
		}
		
		if (TragicNewConfig.allowDoomsdays)
		{
			PropertyDoom doom = PropertyDoom.get(par2EntityPlayer);

			par2List.add(EnumChatFormatting.DARK_AQUA + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());

			if (doom != null)
			{
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(doom));
			}
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
		if (!TragicNewConfig.allowRandomWeaponLore || world.isRemote) return; 
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
		}
	}
	
	
	protected int getRarityFromEnum(Lore lore)
	{
		return lore.rarity == EnumRarity.common ? 0 : (lore.rarity == EnumRarity.uncommon ? 1 : (lore.rarity == EnumRarity.rare ? 2 : 3));
	}

	protected EnumChatFormatting getFormatFromRarity(int rarity)
	{
		return rarity == 0 ? EnumChatFormatting.GRAY : (rarity == 1 ? EnumChatFormatting.YELLOW : (rarity == 2 ? EnumChatFormatting.DARK_GREEN : EnumChatFormatting.RED));
	}

}
