package tragicneko.tragicmc.blocks;

import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;

public class BlockGenericBush extends BlockBush {

	public BlockGenericBush()
	{
		super();
		this.setStepSound(soundTypeGrass);
		this.setCreativeTab(TragicTabs.Survival);
	}
	
	@Override
	protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
		return this == TragicBlocks.DeadBush ? (p_149854_1_ == Blocks.grass || p_149854_1_ == Blocks.dirt || p_149854_1_ == Blocks.farmland || p_149854_1_ == TragicBlocks.DeadDirt) : (p_149854_1_ == Blocks.grass || p_149854_1_ == Blocks.dirt || p_149854_1_ == Blocks.farmland || p_149854_1_ == TragicBlocks.AshenGrass);
    }
}
