package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.LoreHelper;
import tragicneko.tragicmc.util.LoreHelper.EnchantEntry;
import tragicneko.tragicmc.util.LoreHelper.Lore;
import tragicneko.tragicmc.util.LoreHelper.LoreEntry;

public class TragicWeapon extends ItemSword {

	protected final Doomsday doomsday;
	protected Doomsday doomsday2;
	public Item.ToolMaterial material;

	//protected Lore[] lores = new Lore[] {new Lore("So boring."), new Lore("Nice."), new Lore("Interesting.", EnumRarity.uncommon), new Lore("That's cool.", EnumRarity.rare),
	//		new Lore("Lame.", EnumRarity.uncommon), new Lore("Meh."), new Lore("Ha.", EnumRarity.rare), new Lore("Awesome.", EnumRarity.epic), new Lore("I'm oozing with excitement", EnumRarity.epic)};

	protected Enchantment[] uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
	protected int[] uncommonLevels = new int[] {1};

	protected Enchantment[] rareEnchants = new Enchantment[] {Enchantment.unbreaking};
	protected int[] rareLevels = new int[] {2};

	protected Enchantment[] epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.sharpness};
	protected int[] epicLevels = new int[] {3, 1};

	public TragicWeapon(ToolMaterial material, Doomsday dday) {
		super(material);
		this.doomsday = dday;
		this.setCreativeTab(TragicMC.Survival);
		this.material = material;
	}
	
	public TragicWeapon(ToolMaterial material, Doomsday dday, Doomsday dday2)
	{
		this(material, dday);
		this.doomsday2 = dday2;
	}

	public Doomsday getDoomsday()
	{
		return this.doomsday;
	}

	public EnumDoomType doomsdayType()
	{
		return this.doomsday.doomsdayType;
	}

	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{		
		if (TragicNewConfig.allowRandomWeaponLore && LoreHelper.getRarityFromStack(stack) > 0)
		{
			String lore = LoreHelper.getDescFromStack(stack);
			EnumChatFormatting loreFormat = LoreHelper.getFormatForRarity(LoreHelper.getRarityFromStack(stack));
			
			if (lore != null)
			{
				String[] subs = LoreHelper.splitDesc(lore);
				if (subs != null) for (String sub : subs) par2List.add(loreFormat + sub);
			}
		}

		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			EnumChatFormatting format = doomsday.getDoomsdayType().getFormat();
			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());
			par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(par2EntityPlayer.worldObj));

			if (this.doomsday2 != null)
			{
				par2List.add(""); //extra space in between
				format = doomsday2.getDoomsdayType().getFormat();
				par2List.add(format + doomsday2.getLocalizedType() + ": " + doomsday2.getLocalizedName());
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday2.getScaledDoomRequirement(par2EntityPlayer.worldObj));
			}
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{		
		if (world.isRemote || !(entity instanceof EntityPlayer)) return; 
		updateAsWeapon(stack, world, entity, numb, flag);
	}

	/**
	 * Allow any item class to update as a weapon and not require extending the main TragicWeapon class, this applies and decrements cooldown and sets up a weapon's lore
	 * @param stack
	 * @param world
	 * @param entity
	 * @param numb
	 * @param flag
	 */
	public static void updateAsWeapon(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();

		if (!stack.stackTagCompound.hasKey("cooldown")) stack.stackTagCompound.setInteger("cooldown", 0);
		if (getStackCooldown(stack) > 0) setStackCooldown(stack, getStackCooldown(stack) - 1);

		if (!TragicNewConfig.allowRandomWeaponLore) return;

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

	public static boolean canUseAbility(PropertyDoom doom, int rq)
	{
		return doom != null && TragicNewConfig.allowNonDoomsdayAbilities && doom.getCurrentCooldown() == 0 && doom.getCurrentDoom() >= rq;
	}

	public static void setStackCooldown(ItemStack stack, int i)
	{
		if (!stack.hasTagCompound()) return;
		stack.stackTagCompound.setInteger("cooldown", i);
	}

	public static int getStackCooldown(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("cooldown") ? stack.stackTagCompound.getInteger("cooldown") : 0;
	}

	public Doomsday getSecondaryDoomsday()
	{
		return this.doomsday2;
	}
}
