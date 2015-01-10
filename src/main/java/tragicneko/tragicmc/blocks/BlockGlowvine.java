package tragicneko.tragicmc.blocks;

import net.minecraft.block.BlockVine;
import net.minecraft.world.IBlockAccess;
import tragicneko.tragicmc.TragicMC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGlowvine extends BlockVine {

	public BlockGlowvine()
    {
        super();
        this.setCreativeTab(TragicMC.Survival);
        this.setLightLevel(1.0F);
        this.setLightOpacity(0);
        this.setTickRandomly(true);
    }
	
	@SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return -1;
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_)
    {
        return -1;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        return -1;
    }
}
