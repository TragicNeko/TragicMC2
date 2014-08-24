package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockObsidianVariants extends TragicItemBlock {

	public ItemBlockObsidianVariants(Block block) {
		super(block, new String[] {"crying", "bleeding", "dying"});
		this.setUnlocalizedName("tragicmc.tragicObsidian");
	}
}
