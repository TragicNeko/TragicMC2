package tragicneko.tragicmc.items.weapons;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicTabs;
import tragicneko.tragicmc.properties.PropertyDoom;

import com.google.common.collect.Sets;

public class ItemJack extends ItemTool {
	
	public Doomsday doomsday;
	
	private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone,
			Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore,
			Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail,
			Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail, Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow,
			Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium, Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin,
			Blocks.lit_pumpkin, Blocks.tallgrass, Blocks.brown_mushroom_block, Blocks.cactus, Blocks.red_mushroom_block, Blocks.carrots, Blocks.cocoa, Blocks.deadbush,
			Blocks.double_plant, Blocks.hay_block, Blocks.leaves, Blocks.leaves2, Blocks.melon_block, Blocks.melon_stem, Blocks.nether_wart, Blocks.potatoes, Blocks.pumpkin_stem,
			Blocks.red_flower, Blocks.reeds, Blocks.sapling, Blocks.sponge, Blocks.tripwire, Blocks.vine, Blocks.waterlily, Blocks.web, Blocks.wheat, Blocks.yellow_flower,
			TragicBlocks.CarrotBlock, TragicBlocks.PotatoBlock, TragicBlocks.Wax, TragicBlocks.LightCobblestone, TragicBlocks.DarkCobblestone, TragicBlocks.Quicksand,
			TragicBlocks.DeadDirt, TragicBlocks.DarkSand, TragicBlocks.Pulsar, TragicBlocks.Magnetar, TragicBlocks.MercuryOre, TragicBlocks.TungstenOre, TragicBlocks.RubyOre,
			TragicBlocks.SapphireOre, Blocks.wool, Blocks.beacon, Blocks.obsidian, TragicBlocks.DarkStone, TragicBlocks.TragicOres, TragicBlocks.SmoothNetherrack,
			TragicBlocks.TragicObsidian, TragicBlocks.AshenGrass, TragicBlocks.AshenLeaves, TragicBlocks.AshenPlanks, TragicBlocks.AshenWood, TragicBlocks.BleachedLeaves,
			TragicBlocks.BleachedPlanks, TragicBlocks.BleachedWood, TragicBlocks.BrushedGrass, TragicBlocks.PaintedLeaves, TragicBlocks.PaintedPlanks, TragicBlocks.PaintedWood,
			TragicBlocks.DarkenedQuartz, TragicBlocks.BoneBlock, TragicBlocks.ErodedStone, TragicBlocks.SandstonePressurePlate, TragicBlocks.NetherBrickPressurePlate,
			Blocks.wooden_button, Blocks.stone_button, Blocks.wooden_door, Blocks.wooden_slab, TragicBlocks.SummonBlock});
	
	private final Lore[] lores = new Lore[] {new Lore("Work, work, work."), new Lore("Time for Lunch!", EnumRarity.uncommon), new Lore("Work all day, sleep all night!"),
			new Lore("Off to work we go!", EnumRarity.uncommon), new Lore("Diamonds!", EnumRarity.rare), new Lore("Ooh, Emeralds!", EnumRarity.rare),
			new Lore("Just keep digging, digging, digging"), new Lore("Can you dig it?", EnumRarity.uncommon), new Lore("The best Blacksmith in Whiterun.", EnumRarity.epic),
			new Lore("The finest weapons and armor!"), new Lore("Forged in the fires of Mount Doom!", EnumRarity.epic)};
		
		private Enchantment[] uncommonEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.efficiency};
		private int[] uncommonLevels = new int[] {1, 1};

		private Enchantment[] rareEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.efficiency, Enchantment.fortune};
		private int[] rareLevels = new int[] {3, 3, 1};

		private Enchantment[] epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.efficiency, Enchantment.fortune, TragicEnchantments.Combustion, Enchantment.fireAspect};
		private int[] epicLevels = new int[] {5, 5, 3, 1, 1};

	public ItemJack(ToolMaterial material, Doomsday dday) {
		super(1.0F, material, blocksEffectiveAgainst);
		this.doomsday = dday;
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(TragicTabs.Survival);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity") ? TragicWeapon.getRarityFromInt(stack.stackTagCompound.getByte("tragicLoreRarity")) : EnumRarity.common;
	}
	
	protected Lore getRandomLore()
	{
		return lores[itemRand.nextInt(lores.length)];
	}
	
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{		
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
		}
		
		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			PropertyDoom doom = PropertyDoom.get(par2EntityPlayer);
			
			EnumChatFormatting format = EnumChatFormatting.DARK_AQUA;
			
			switch(doomsday.getDoomsdayType())
			{
			case COMBINATION:
				format = EnumChatFormatting.YELLOW;
				break;
			case CRISIS:
				format = EnumChatFormatting.RED;
				break;
			case OVERFLOW:
				format = EnumChatFormatting.GREEN;
				break;
			case WORLDSHAPER:
				format = EnumChatFormatting.DARK_PURPLE;
				break;
			case INFLUENCE:
			default:
				break;
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
		}
	}
}
