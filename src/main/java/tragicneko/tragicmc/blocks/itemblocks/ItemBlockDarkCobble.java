package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDarkCobble extends TragicItemBlock{

	public ItemBlockDarkCobble(Block block) {
		super(block, new String[] {"normal", "hot", "toxic", "ashen"});
		this.setUnlocalizedName("tragicmc.darkCobblestone");
	}
	
}
