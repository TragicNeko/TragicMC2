package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockTragicFlower extends TragicItemBlock {

	private static final String[] subNames = new String[] {"blueSpiranthes", "pinkSpiranthes", "redSpiranthes", "whiteSpiranthes", "blueCoral", "redCoral", "pinkGinger", "redGinger",
		"bluebonnet", "violetSage", "pinkSage", "whiteSage", "birdOfParadise", "juniperBush", "stapelia", "thistle"};

	public ItemBlockTragicFlower(Block block) {
		super(block, subNames);
		this.setUnlocalizedName("tragicmc.flower");
	}
}
