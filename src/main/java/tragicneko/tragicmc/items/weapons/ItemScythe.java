package tragicneko.tragicmc.items.weapons;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicTabs;
import tragicneko.tragicmc.doomsday.Doomsday;

import com.google.common.collect.Sets;

public class ItemScythe extends ItemTool {
	
	public Doomsday doomsday;
	
	private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.tallgrass,
			Blocks.brown_mushroom_block, Blocks.cactus, Blocks.red_mushroom_block, Blocks.carrots,
			Blocks.cocoa, Blocks.deadbush, Blocks.double_plant, Blocks.hay_block, Blocks.leaves, Blocks.leaves2,
			Blocks.melon_block, Blocks.melon_stem, Blocks.nether_wart, Blocks.potatoes, Blocks.pumpkin_stem,
			Blocks.red_flower, Blocks.reeds, Blocks.sapling, Blocks.sponge, Blocks.tripwire, Blocks.vine,
			Blocks.waterlily, Blocks.web, Blocks.wheat, Blocks.yellow_flower, TragicBlocks.CarrotBlock, TragicBlocks.PotatoBlock,
			TragicBlocks.AshenTallGrass, TragicBlocks.DriedGrass, TragicBlocks.StarlitTallGrass, TragicBlocks.PaintedTallGrass,
			TragicBlocks.GlowVine, TragicBlocks.DeadBush, TragicBlocks.AshenBush, TragicBlocks.TragicFlower, TragicBlocks.TragicSapling});

	public ItemScythe(ToolMaterial par2Material) {
		super(4.0F, par2Material, blocksEffectiveAgainst);
		this.setHarvestLevel("scythe", 3);
		this.setCreativeTab(TragicTabs.Survival);
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Effective against most plantlife");	
	}
}
