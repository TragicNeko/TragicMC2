package tragicneko.tragicmc.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.entity.boss.TragicMiniBoss;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EnchantmentDamageBoost extends Enchantment {

	private String[] enchantNames = {"decay", "absolve", "slay", "runeBreak"};
	private int damageType;

	public EnchantmentDamageBoost(int par1, int par2, int par3) {
		super(par1, par2, EnumEnchantmentType.weapon);
		this.setName("damageBoost." + enchantNames[par3]);
		this.damageType = par3;
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

		if (par1Enchantment instanceof EnchantmentDamage || par1Enchantment instanceof EnchantmentDamageBoost)
		{
			flag = false;
		}

		return flag;
	}

	public boolean canApply(ItemStack par1ItemStack)
	{
		return par1ItemStack.getItem() instanceof ItemAxe ? true : super.canApply(par1ItemStack);
	}

	public float calcModifierLiving(int par1, EntityLivingBase par2EntityLivingBase)
	{
		switch (this.damageType)
		{
		case 0:
			if (par2EntityLivingBase.getCreatureAttribute() == TragicEntities.Natural)
			{
				return (float) (par1 * 1.25);
			}
			break;
		case 1:
			if (par2EntityLivingBase instanceof TragicMob)
			{
				if (((TragicMob)par2EntityLivingBase).isCorrupted || par2EntityLivingBase instanceof TragicMiniBoss)
				{
					if (par2EntityLivingBase.worldObj.rand.nextInt(16 / par1) == 0)
					{
						par2EntityLivingBase.removePotionEffect(TragicPotions.Corruption.id);
					}
					
					return (float) (par1 * 1.25);
				}
			}
			else
			{
				if (TragicNewConfig.allowCorruption && par2EntityLivingBase.isPotionActive(TragicPotions.Corruption))
				{
					if (par2EntityLivingBase.worldObj.rand.nextInt(16 / par1) == 0)
					{
						par2EntityLivingBase.removePotionEffect(TragicPotions.Corruption.id);
					}
					return (float) (par1 * 0.625);
				}
			}
			break;
		case 2:
			if (par2EntityLivingBase.getCreatureAttribute() == TragicEntities.Beast)
			{
				return (float) (par1 * 1.25);
			}
			break;
		case 3:
			break;
		default:
			return 0F;
		}
		return 0F;
	}

}
