package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntityStructureSeed;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStructureSeed extends BlockContainer {

	private String[] structureNames = new String[]{"ApisTemple", "Tower", "DeathCircle", "ObsidianCavernRandom", "KitsuneDen", "CelestialTemple",
			"TimeAltar", "AegarTower"};

	public BlockStructureSeed() {
		super(Material.gourd);
		this.setResistance(100.0F);
		this.setHardness(10.0F);
		this.setCreativeTab(TragicMC.Creative);
		this.setBlockName("tragicmc.structureSeed");
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("tragicmc:StructureSeed");
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityStructureSeed();
	}

	@Override
	public int damageDropped(int par1)
	{
		return par1;
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (int i = 0; i < this.structureNames.length; i++)
		{
			par3.add(new ItemStack(par1, 1, i));
		}
	}
}
