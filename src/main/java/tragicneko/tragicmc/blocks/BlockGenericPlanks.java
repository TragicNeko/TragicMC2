package tragicneko.tragicmc.blocks;

import tragicneko.tragicmc.main.TragicTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGenericPlanks extends Block {

	public BlockGenericPlanks() {
		super(Material.wood);
		this.setCreativeTab(TragicTabs.Survival);
		this.setResistance(7.0F);
		this.setHardness(0.7F);
		this.setStepSound(soundTypeWood);
		this.setHarvestLevel("axe", 0);
	}

}
