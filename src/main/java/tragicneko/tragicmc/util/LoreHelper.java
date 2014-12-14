package tragicneko.tragicmc.util;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import scala.actors.threadpool.Arrays;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.items.weapons.WeaponBeastlyClaws;
import tragicneko.tragicmc.items.weapons.WeaponBlindingLight;

public class LoreHelper {

	private static Map<Class, LoreEntry> weaponLores = new HashMap();

	static
	{

		//Armor

		//Weapons
		addToLoreMap(WeaponBeastlyClaws.class, new Lore[] {new Lore(25, "That's beastly.", 1), new Lore(15, "Epic.", 1), new Lore(5, "Knockout!", 1),
				new Lore(10, "Roar!", 1), new Lore(15, "Combo!", 1), new Lore(5, "Let's fight!", 1), new Lore(5, "Sucker punch!", 1),
				new Lore(25, "Just getting started!", 2), new Lore(20, "Just sharpening my claws!", 2), new Lore(15, "One-two punch!", 2),
				new Lore(5, "You're gonna hear me roar!", 2), new Lore(10, "Punchout!", 2), new Lore(5, "Fight!", 2), new Lore(5, "TKO!", 2),
				new Lore(25, "Hit and Run!", 3), new Lore(5, "Falcon Punch!", 3), new Lore(15, "Going Beastmode!", 3), new Lore(2, "C-c-c-combo breaker!", 3),
				new Lore(10, "Limit break!", 3), new Lore(15, "I'll rip you to pieces!", 3), new Lore(20, "Tear you to pieces, rip you apart!", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 1)},
				{new EnchantEntry(Enchantment.unbreaking, 2), new EnchantEntry(TragicEnchantments.Slay, 1)},
				{new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Slay, 2), new EnchantEntry(TragicEnchantments.Consume, 1)}});

		addToLoreMap(WeaponBlindingLight.class, new Lore[] {new Lore(25, "You're shining!", 1), new Lore(15, "Shine on!", 1), new Lore(5, "Aw, you're glowing~", 1),
				new Lore(10, "Shine bright like a diamond.", 1), new Lore(5, "Just needs some spit shine!", 1), new Lore(15, "Shinedown.", 1),
				new Lore(10, "It's bright, like me!", 1), new Lore(5, "So bright.", 2), new Lore(25, "Like a shooting star!", 2), new Lore(15, "Ooh, shiny!", 2),
				new Lore(25, "Shiny, shiny, shiny!", 2), new Lore(5, "Brilliant luster!", 2), new Lore(25, "Heaven let your light shine on!", 3),
				new Lore(15, "How do you get just the right amount of shiny?!", 3), new Lore(5, "Always look on the bright side of life!", 3),
				new Lore(5, "Turn on your love light!", 3), new Lore(5, "Shine on you crazy diamond!", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(TragicEnchantments.Absolve, 1)}, {new EnchantEntry(TragicEnchantments.Absolve, 3), new EnchantEntry(Enchantment.unbreaking, 1)},
				{new EnchantEntry(TragicEnchantments.Absolve, 5), new EnchantEntry(Enchantment.unbreaking, 1), new EnchantEntry(Enchantment.fireAspect, 1)}});
		
		//Epic weapons
	}

	public static void addToLoreMap(Class clazz, LoreEntry entry)
	{
		weaponLores.put(clazz, entry);
	}

	public static void addToLoreMap(Class clazz, Lore[] lores, EnchantEntry[][] enchants)
	{
		addToLoreMap(clazz, new LoreEntry(clazz, lores, enchants));
	}

	public static LoreEntry getLoreEntry(Class clazz)
	{
		return weaponLores.containsKey(clazz) ? weaponLores.get(clazz) : null;
	}

	public static EnumChatFormatting getFormatForRarity(int rarity)
	{
		return rarity == 0 ? EnumChatFormatting.GRAY : (rarity == 1 ? EnumChatFormatting.GOLD : (rarity == 2 ? EnumChatFormatting.DARK_GREEN : EnumChatFormatting.DARK_RED));
	}

	public static int getRarityFromStack(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity") ? stack.stackTagCompound.getByte("tragicLoreRarity") : 0;
	}

	public static int getIDFromStack(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreID") ? stack.stackTagCompound.getInteger("tragicLoreID") : 0;
	}

	public static String getDescFromStack(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreDesc") ? stack.stackTagCompound.getString("tragicLoreDesc") : null;
	}
	
	/**
	 * Can split any lengthy string into 3 smaller ones to fit within an item's description, it will only split to a max of 3 lines, then will trim out the rest
	 * @param lore
	 * @return
	 */
	public static String[] splitDesc(String lore)
	{
		String s = lore;
		String s2 = null;
		String s3 = null;

		if (s.length() > 28)
		{
			for (int i = 28; i < s.length(); i++)
			{
				if (s.substring(0, i).endsWith(" "))
				{
					if (s2 == null)
					{
						s2 = s.substring(i).trim();
						s = s.substring(0, i).trim();
						break;
					}

				}
			}
		}

		if (s2 != null && s2.length() > 28)
		{
			for (int i = 28; i < s2.length(); i++)
			{
				if (s2.substring(0, i).endsWith(" "))
				{
					if (s3 == null)
					{
						s3 = s2.substring(i).trim();
						s2 = s2.substring(0, i).trim();
						break;
					}
				}
			}

			if (s3 != null && s3.length() > 28)
			{
				s3 = s3.substring(0, 28).trim();
			}
		}
		
		if (s2 == null) return new String[] {s};
		if (s3 == null) return new String[] {s, s2};
		
		return new String[] {s, s2, s3};
	}

	public static class LoreEntry {

		private final Lore[] lores;
		private final EnchantEntry[][] enchants;

		private final Class weaponClazz;

		public LoreEntry(Class clazz, Lore[] lores, EnchantEntry[][] enchants)
		{
			this.weaponClazz = clazz;
			this.lores = lores;
			this.enchants = enchants;
		}

		public Class getWeaponClass()
		{
			return this.weaponClazz;
		}

		/**
		 * This is used to pick a completely random lore based off of the weapon/armor's set of lores, this is for brand new weapons/armor or weapons/armor received from a mob
		 * @return
		 */
		public Lore getRandomLore()
		{
			try
			{
				int i = rand.nextInt(100);
				int r = i < 10 ? 3 : (i < 35 ? 2 : (i < 80 ? 1 : 0)); //10% chance to be epic, 25% to be rare, 45% to be uncommon, 20% to be common
				List<Lore> alist = new ArrayList();

				for (Lore l : this.lores)
				{
					if (l.getRarity() == r) alist.add(l);
				}
				
				if (alist.isEmpty()) return new Lore(1, null, 0);

				return (Lore) WeightedRandom.getRandomItem(rand, alist);
			}
			catch (Exception e)
			{
				TragicMC.logError("Error getting a random Lore for a weapon", e);
				return null;
			}
		}

		/**
		 * Returns a random lore in the given rarity tier, otherwise returns an empty lore
		 * @param rarity
		 * @param id
		 * @return
		 */
		public Lore getLoreOfRarity(int rarity)
		{
			try
			{
				List<Lore> alist = new ArrayList();

				for (Lore l : this.lores)
				{
					if (l.getRarity() == rarity) alist.add(l);
				}
				
				if (alist.isEmpty()) return new Lore(1, null, 0);

				return (Lore) WeightedRandom.getRandomItem(rand, alist);
			}
			catch (Exception e)
			{
				TragicMC.logError("Error retrieving a specific Lore for a weapon based on rarity", e);
				return null;
			}
		}

		public EnchantEntry[] getEnchantmentsForRarity(int rarity)
		{
			try
			{
				rarity = MathHelper.clamp_int(rarity, 0, 3);
				return this.enchants[rarity];
			}
			catch (Exception e)
			{
				TragicMC.logError("Error getting enchantments for a weapon", e);
				return null;
			}
		}
	}

	public static class EnchantEntry {

		private final Enchantment ench;
		private final int level;

		public EnchantEntry(Enchantment ench, int level)
		{
			this.ench = ench;
			this.level = level;
		}

		public Enchantment getEnchantment() { return this.ench; }
		public int getEnchantLevel() { return this.level; }
	}

	public static class Lore extends WeightedRandom.Item {

		private final int rarity;
		private final String desc;

		public Lore(int weight, String desc, int rarity) {
			super(weight);
			this.desc = desc;
			this.rarity = rarity;
		}

		public String getDesc() { return this.desc; }
		public int getRarity() { return this.rarity; }

		public EnumRarity getRarityEnum()
		{
			return this.rarity == 0 ? EnumRarity.common : (this.rarity == 1 ? EnumRarity.uncommon : (this.rarity == 2 ? EnumRarity.rare : EnumRarity.epic));
		}
	}
}
