package tragicneko.tragicmc.blocks;

import tragicneko.tragicmc.main.TragicTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;

public class BlockSandstonePressurePlate extends BlockPressurePlate {

	public BlockSandstonePressurePlate() {
		super("sandstone_top", Material.rock, BlockPressurePlate.Sensitivity.players);
		this.setCreativeTab(TragicTabs.Survival);
	}

}
