package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tragicneko.tragicmc.TragicMC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPermafrost extends Block {
	private String[] oreNames = new String[] {"Normal", "Cracked", "Mossy"};
	private IIcon[] iconArray = new IIcon[oreNames.length];

	public BlockPermafrost() {
		super(Material.ground);
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(5.0F);
		this.setHardness(1.5F);
		this.slipperiness = 0.72F;
		this.setStepSound(soundTypeGravel);
		this.setHarvestLevel("shovel", 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta >= this.iconArray.length)
		{
			meta = this.iconArray.length - 1;
		}
		return side == 1 ? this.iconArray[meta] : this.iconArray[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		for (int i = 0; i < this.oreNames.length; i++)
		{
			this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.oreNames[i] + "Permafrost");
		}
	}

	@Override
	public int damageDropped(int par1)
	{
		return par1;
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (int i = 0; i < this.oreNames.length; i++)
		{
			par3.add(new ItemStack(par1, 1, i));
		}
	}
}
