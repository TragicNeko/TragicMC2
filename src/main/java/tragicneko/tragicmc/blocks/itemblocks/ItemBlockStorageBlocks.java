package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockStorageBlocks extends TragicItemBlock {
	
	private static String[] subNames = new String[] {"ruby", "sapphire", "tungsten", "mercury", "quicksilver"};

	public ItemBlockStorageBlocks(Block p_i45326_1_) {
		super(p_i45326_1_, subNames);
		this.setUnlocalizedName("tragicmc.storageBlock");
	}
}
