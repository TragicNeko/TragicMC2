package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockTragicFlower extends ItemBlockWithMetadata {

	private static final String[] subNames = new String[] {"blueSpiranthes", "pinkSpiranthes", "redSpiranthes", "whiteSpiranthes", "blueCoral", "redCoral", "pinkGinger", "redGinger",
		"bluebonnet", "violetSage", "pinkSage", "whiteSage", "birdOfParadise", "juniperBush", "stapelia", "thistle"};

	public ItemBlockTragicFlower(Block block) {
		super(block, block);
		this.setUnlocalizedName("tragicmc.flower");
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int damage = itemstack.getItemDamage();

		if (damage >= subNames.length)
		{
			damage = subNames.length - 1;
		}
		return getUnlocalizedName() + "." + subNames[damage];
	}
}
