package tragicneko.tragicmc.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentLuck extends Enchantment {

	protected EnchantmentLuck(int par1, int par2,
			EnumEnchantmentType par3EnumEnchantmentType) {
		super(par1, par2, par3EnumEnchantmentType);
		this.setName("luck");
	}
	
	public int getMinEnchantability(int par1)
    {
        return 20;
    }

    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }

    public int getMaxLevel()
    {
        return 1;
    }
    
    public boolean canApplyTogether(Enchantment par1Enchantment)
    {
        return super.canApplyTogether(par1Enchantment);
    }
    
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return false;
    }

}
