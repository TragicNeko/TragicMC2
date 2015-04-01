package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntityAeris;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAeris extends BlockTragicFlower implements ITileEntityProvider
{
	@SideOnly(Side.CLIENT)
	private IIcon corruptingTexture, corruptedTexture;
	
	public BlockAeris()
	{
		super();
		this.setBlockName("tragicmc.aeris");
		this.setCreativeTab(TragicMC.Survival);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta == 1) return this.corruptingTexture;
		if (meta == 2) return this.corruptedTexture;
		return this.blockIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("tragicmc:PureAeris");
		this.corruptedTexture = par1IconRegister.registerIcon("tragicmc:FullyCorruptedAeris");
		this.corruptingTexture = par1IconRegister.registerIcon("tragicmc:PartiallyCorruptedAeris");
	}
	
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (int i = 0; i < 3; i++)
		par3.add(new ItemStack(par1, 1, i));
	}
	
	@Override
	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityAeris();
	}
	
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
        p_149749_1_.removeTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    }

    public boolean onBlockEventReceived(World p_149696_1_, int p_149696_2_, int p_149696_3_, int p_149696_4_, int p_149696_5_, int p_149696_6_)
    {
        super.onBlockEventReceived(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_5_, p_149696_6_);
        TileEntity tileentity = p_149696_1_.getTileEntity(p_149696_2_, p_149696_3_, p_149696_4_);
        return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
    }

	@Override
	public int damageDropped(int par1)
	{
		return par1;
	}
}
