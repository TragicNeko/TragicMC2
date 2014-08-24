package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.projectile.EntityThrowingRock;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRock extends Item {
	
	private String[] unlocalizedNames = new String[] {"rock", "lavaRock"};
	private String[] textureNames = new String[] {"Rock", "LavaRock"};
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icons;
	
	public ItemRock()
	{
		super();
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(TragicTabs.Creative);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) 
	{
		if (!player.capabilities.isCreativeMode) 
		{
			--stack.stackSize;
		}

		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		if (!world.isRemote) 
		{
			boolean flag = stack.getItemDamage() == 1;
			EntityThrowingRock rock = new EntityThrowingRock(world, player, flag);
			
			if (flag)
			{
				rock.setFire(2000);
			}
			
			world.spawnEntityInWorld(rock);
		}

		return stack;
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 0, 1);
        return this.icons[j];
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 1);
        return super.getUnlocalizedName() + "." + unlocalizedNames[i];
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
        for (int i = 0; i < 2; ++i)
        {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.icons = new IIcon[unlocalizedNames.length];

        for (int i = 0; i < unlocalizedNames.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon("tragicmc:" + textureNames[i] + TragicNewConfig.textureRes);
        }
    }
}
