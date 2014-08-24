package tragicneko.tragicmc.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentRuneWalker extends Enchantment {

	public EnchantmentRuneWalker(int par1, int par2,
			EnumEnchantmentType par3EnumEnchantmentType) {
		super(par1, par2, par3EnumEnchantmentType);
		this.setName("runeWalker");
	}

	public int getMinEnchantability(int par1)
    {
        return 15 + (par1 * 3);
    }

    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }

    public int getMaxLevel()
    {
        return 5;
    }
    
    public boolean canApplyTogether(Enchantment par1Enchantment)
    {
    	Boolean flag = true;
    	
    	if (par1Enchantment instanceof EnchantmentProtection)
    	{
    		flag = false;
    	}
        return super.canApplyTogether(par1Enchantment) && flag;
    }
}
