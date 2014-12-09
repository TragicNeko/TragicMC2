package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.BlockSand;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tragicneko.tragicmc.TragicTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDarkSand extends BlockSand {

	public BlockDarkSand() {
		super();
		this.setCreativeTab(TragicTabs.Survival);
		this.setStepSound(soundTypeSand);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item));
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.blockIcon;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister regi)
	{
		this.blockIcon = regi.registerIcon("tragicmc:DarkSand_lowRes");
	}

}
