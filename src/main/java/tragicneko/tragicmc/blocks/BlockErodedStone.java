package tragicneko.tragicmc.blocks;

import static net.minecraftforge.common.util.ForgeDirection.UP;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockErodedStone extends Block {
	
	private String[] oreNames = new String[] {"Smooth", "Carved"};
	private IIcon[] iconArray = new IIcon[oreNames.length];

	public BlockErodedStone() {
		super(Material.rock);
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setCreativeTab(TragicTabs.Survival);
		this.setStepSound(soundTypeStone);
		this.setBlockName("tragicmc.erodedStone");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta >= this.iconArray.length)
		{
			meta = this.iconArray.length - 1;
		}
		return this.iconArray[meta];
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		for (int i = 0; i < this.oreNames.length; i++)
		{
			this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.oreNames[i] + "ErodedStone_lowRes");
		}
	}

	public int damageDropped(int par1)
	{
		return par1;
	}

	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (int i = 0; i < this.oreNames.length; i++)
		{
			par3.add(new ItemStack(par1, 1, i));
		}
	}
}
