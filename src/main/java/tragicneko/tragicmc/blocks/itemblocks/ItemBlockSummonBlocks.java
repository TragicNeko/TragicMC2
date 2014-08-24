package tragicneko.tragicmc.blocks.itemblocks;

import java.awt.Color;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSummonBlocks extends TragicItemBlock {

	private static final String[] subNames = new String[] {"wither", "enderDragon", "apis", "deathReaper", "kitsune", "polaris", "yeti", "timeController"};

	public ItemBlockSummonBlocks(Block p_i45326_1_) {
		super(p_i45326_1_, subNames);
		this.setUnlocalizedName("tragicmc.summonBlock");
	}
}
