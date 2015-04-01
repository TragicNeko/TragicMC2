package tragicneko.tragicmc.blocks.itemblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockAeris extends TragicItemBlock {

	public ItemBlockAeris(Block block) {
		super(block, new String[] {"pureAeris", "partiallyCorruptedAeris", "corruptedAeris"}, "aeris");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public IIcon getIconFromDamage(int i)
    {
        return this.field_150939_a.getIcon(0, i);
    }

}
