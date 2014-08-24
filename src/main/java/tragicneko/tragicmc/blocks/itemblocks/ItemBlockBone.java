package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBone extends TragicItemBlock {

	public ItemBlockBone(Block p_i45328_1_) {
		super(p_i45328_1_, new String[] {"normal", "rotten"});
		this.setUnlocalizedName("tragicmc.boneBlock");
	}
}
