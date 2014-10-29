package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;

public class ItemBlockCircuit extends TragicItemBlock {

	public ItemBlockCircuit(Block p_i45326_1_) {
		super(p_i45326_1_, new String[] {"live", "damaged", "veryDamaged", "dead", "aged"});
		this.setUnlocalizedName("tragicmc.circuit");
	}

}
