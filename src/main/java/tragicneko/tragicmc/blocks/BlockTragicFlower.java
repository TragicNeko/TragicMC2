package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockTragicFlower extends BlockFlower implements IGrowable{
	
	private String[] plantNames = new String[]{"BlueSpiranthes", "PinkSpiranthes", "RedSpiranthes", "WhiteSpiranthes", "BlueCoral", "RedCoral", "PinkGinger", "RedGinger",
		"Bluebonnet", "VioletSage", "PinkSage", "WhiteSage", "BirdOfParadise", "JuniperBush", "Stapelia", "Thistle"};
	
	private IIcon[] iconArray = new IIcon[plantNames.length];

	public BlockTragicFlower() {
		super(0);
		this.setCreativeTab(TragicMC.Survival);
		this.setBlockName("tragicmc.flower");
		this.setStepSound(soundTypeGrass);
	}
	
	@Override
	protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return p_149854_1_ == Blocks.grass || p_149854_1_ == Blocks.dirt || p_149854_1_ == Blocks.farmland || p_149854_1_ == TragicBlocks.BrushedGrass || p_149854_1_ == TragicBlocks.DeadDirt;
    }
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta >= this.iconArray.length)
		{
			meta = this.iconArray.length - 1;
		}
		return this.iconArray[meta];
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		for (int i = 0; i < this.plantNames.length; i++)
		{
			this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.plantNames[i] + "_lowRes");
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
		for (int i = 0; i < this.plantNames.length; i++)
		{
			par3.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public boolean func_149851_a(World p_149851_1_, int p_149851_2_,
			int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
		return true;
	}

	@Override
	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_,
			int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return true;
	}

	@Override
	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
		if (p_149853_2_.nextInt(4) != 0) return;
		int meta = p_149853_1_.getBlockMetadata(p_149853_3_, p_149853_4_, p_149853_5_);
		
		if (meta >= 14 && p_149853_2_.nextInt(4) != 0) return;
		this.dropBlockAsItem(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, new ItemStack(this, 1, meta));
	}

}
