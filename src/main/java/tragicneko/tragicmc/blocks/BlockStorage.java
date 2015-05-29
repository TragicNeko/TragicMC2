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

public class BlockStorage extends Block {

	private String[] oreNames = new String[]{"Ruby", "Sapphire", "Tungsten", "Mercury", "Quicksilver"};

	private IIcon[] iconArray = new IIcon[oreNames.length];

	public BlockStorage() {
		super(Material.iron);
		this.setBlockName("tragicmc.storageBlock");
		this.setCreativeTab(TragicMC.Survival);
		this.setHarvestLevel("pickaxe", 1);
		this.setHardness(6.0F);
		this.setResistance(10.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta >= this.iconArray.length)
		{
			meta = this.iconArray.length - 1;
		}
		return this.iconArray[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		for (int i = 0; i < this.oreNames.length; i++)
		{
			this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.oreNames[i] + "Block");
		}
	}

	@Override
	public int damageDropped(int par1)
	{
		return par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (int i = 0; i < this.oreNames.length; i++)
		{
			par3.add(new ItemStack(par1, 1, i));
		}
	}

}
