package tragicneko.tragicmc.items.weapons;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.util.LoreHelper;

import com.google.common.collect.Sets;

public class ItemJack extends ItemTool {

	public final Doomsday doomsday;

	private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone,
			Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore,
			Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail,
			Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail, Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow,
			Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium, Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin,
			Blocks.lit_pumpkin, Blocks.tallgrass, Blocks.brown_mushroom_block, Blocks.cactus, Blocks.red_mushroom_block, Blocks.carrots, Blocks.cocoa, Blocks.deadbush,
			Blocks.double_plant, Blocks.hay_block, Blocks.leaves, Blocks.leaves2, Blocks.melon_block, Blocks.melon_stem, Blocks.nether_wart, Blocks.potatoes, Blocks.pumpkin_stem,
			Blocks.red_flower, Blocks.reeds, Blocks.sapling, Blocks.sponge, Blocks.tripwire, Blocks.vine, Blocks.waterlily, Blocks.web, Blocks.wheat, Blocks.yellow_flower,
			TragicBlocks.CarrotBlock, TragicBlocks.PotatoBlock, TragicBlocks.Wax, TragicBlocks.LightCobblestone, TragicBlocks.DarkCobblestone, TragicBlocks.Quicksand,
			TragicBlocks.DeadDirt, TragicBlocks.DarkSand, TragicBlocks.MercuryOre, TragicBlocks.TungstenOre, TragicBlocks.RubyOre,
			TragicBlocks.SapphireOre, Blocks.wool, Blocks.beacon, Blocks.obsidian, TragicBlocks.DarkStone, TragicBlocks.TragicOres, TragicBlocks.SmoothNetherrack,
			TragicBlocks.TragicObsidian, TragicBlocks.AshenGrass, TragicBlocks.AshenLeaves, TragicBlocks.AshenPlanks, TragicBlocks.AshenWood, TragicBlocks.BleachedLeaves,
			TragicBlocks.BleachedPlanks, TragicBlocks.BleachedWood, TragicBlocks.BrushedGrass, TragicBlocks.PaintedLeaves, TragicBlocks.PaintedPlanks, TragicBlocks.PaintedWood,
			TragicBlocks.DarkenedQuartz, TragicBlocks.BoneBlock, TragicBlocks.ErodedStone, TragicBlocks.SandstonePressurePlate, TragicBlocks.NetherBrickPressurePlate,
			Blocks.wooden_button, Blocks.stone_button, Blocks.wooden_door, Blocks.wooden_slab, TragicBlocks.SummonBlock});

	public ItemJack(ToolMaterial material, Doomsday dday) {
		super(1.0F, material, blocksEffectiveAgainst);
		this.doomsday = dday;
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(TragicMC.Survival);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{		
		if (TragicConfig.allowRandomWeaponLore && LoreHelper.getRarityFromStack(stack) >= 0)
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
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{		
		TragicWeapon.updateAsWeapon(stack, world, entity, numb, flag);
	}
	
	@Override
	public float func_150893_a(ItemStack stack, Block block)
	{
		return this.toolMaterial.getEfficiencyOnProperMaterial();
	}
}
