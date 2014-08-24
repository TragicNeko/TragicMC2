package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockLightCobble extends TragicItemBlock {

	public ItemBlockLightCobble(Block block) {
		super(block, new String[] {"normal", "frozen", "glowing"});
		this.setUnlocalizedName("tragicmc.lightCobblestone");
	}
}
