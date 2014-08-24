package tragicneko.tragicmc.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tragicneko.tragicmc.blocks.tileentity.TileEntityPulsar;
import tragicneko.tragicmc.main.TragicTabs;

public class BlockPulsar extends BlockContainer {
	
	private EntityPlayer thePlayer;

	public BlockPulsar() {
		super(Material.circuits);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setCreativeTab(TragicTabs.Survival);
		this.setHarvestLevel("pickaxe", 0);
	}
	
	public int getMobilityFlag()
	{
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityPulsar();
	}
}
