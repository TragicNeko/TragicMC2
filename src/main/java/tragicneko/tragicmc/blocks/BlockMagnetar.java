package tragicneko.tragicmc.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tragicneko.tragicmc.blocks.tileentity.TileEntityMagnetar;
import tragicneko.tragicmc.main.TragicTabs;

public class BlockMagnetar extends BlockContainer {

	private EntityPlayer thePlayer;

	public BlockMagnetar() {
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
		return new TileEntityMagnetar();
	}

}
