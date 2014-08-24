package tragicneko.tragicmc.enchantment;

import tragicneko.tragicmc.main.TragicEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentVeteran extends Enchantment {

	public EnchantmentVeteran(int par1, int par2,
			EnumEnchantmentType par3EnumEnchantmentType) {
		super(par1, par2, par3EnumEnchantmentType);
		this.setName("veteran");
	}
	
	public int getMinEnchantability(int par1)
    {
        return 12 + (par1 * 4);
    }

    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }

    public int getMaxLevel()
    {
        return 4;
    }
    
    public boolean canApplyTogether(Enchantment par1Enchantment)
    {
        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != Enchantment.silkTouch.effectId && par1Enchantment.effectId != TragicEnchantments.Combustion.effectId;
    }

}
