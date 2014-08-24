package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockFox extends TragicItemBlock {

	private static String[] subNames = new String[] {"normal", "chiseled", "beveled", "sculpted", "foxtail", "molten"};
	
	public ItemBlockFox(Block p_i45328_1_) {
		super(p_i45328_1_, subNames);
		this.setUnlocalizedName("tragicmc.smoothNetherrack");
	}
}
