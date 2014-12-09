package tragicneko.tragicmc.blocks;

import tragicneko.tragicmc.TragicTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;

public class BlockNetherBrickPressurePlate extends BlockPressurePlate {

	public BlockNetherBrickPressurePlate() {
		super("nether_brick", Material.rock, BlockPressurePlate.Sensitivity.mobs);
		this.setCreativeTab(TragicTabs.Survival);
	}

}
