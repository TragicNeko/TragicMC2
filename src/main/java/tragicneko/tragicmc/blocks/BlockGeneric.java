package tragicneko.tragicmc.blocks;

import tragicneko.tragicmc.main.TragicTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockGeneric extends Block {

	private int renderPass = 0;
	
	public BlockGeneric(Material p_i45394_1_, String s, int level) {
		super(p_i45394_1_);
		this.setCreativeTab(TragicTabs.Survival);
		this.setHarvestLevel(s, level);
	}
	
	@Override
	public int getRenderBlockPass()
	{
		return this.renderPass;
	}
	
	public Block setRenderPass(int i)
	{
		this.renderPass = i;
		return this;
	}
}
