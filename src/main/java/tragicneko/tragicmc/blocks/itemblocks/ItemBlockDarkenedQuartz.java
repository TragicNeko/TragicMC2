package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;

public class ItemBlockDarkenedQuartz extends TragicItemBlock {

	public ItemBlockDarkenedQuartz(Block block) {
		super(block, new String[] {"smooth", "chiseled", "pillared"});
		this.setUnlocalizedName("tragicmc.darkenedQuartz");
	}

}
