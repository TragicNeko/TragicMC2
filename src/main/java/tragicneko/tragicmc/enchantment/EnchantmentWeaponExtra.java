package tragicneko.tragicmc.enchantment;

import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class EnchantmentWeaponExtra extends Enchantment {

	private String[] enchantNames = {"vampirism", "leech", "consume", "distract"};
	private static String enchantName;
	private int damageType;

	private final Random rand = TragicMC.rand;

	public EnchantmentWeaponExtra(int par1, int par2, int par3) {
		super(par1, par2, EnumEnchantmentType.weapon);
		this.setName("weaponExtra." + enchantNames[par3]);
		this.damageType = par3;
	}

	public int getMinEnchantability(int par1)
	{
		return 25 + (par1 * 5);
	}

	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 50;
	}

	public int getMaxLevel()
	{
		return 3;
	}

	public boolean canApplyTogether(Enchantment par1Enchantment)
	{
		Boolean flag = true;

		if (par1Enchantment instanceof EnchantmentWeaponExtra)
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
		if (par1 > 5)
		{
			par1 = 5;
		}
		
		if (rand.nextInt(12 - (par1 * 2)) == 0 && this.damageType == 3)
		{
			if (rand.nextInt(3) == 0)
			{
				switch(rand.nextInt(10))
				{
				case 0:
					par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.blindness.id, 120 * par1, par1));
				case 1:
					par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 120 * par1, par1));
					break;
				case 2:
					par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.blindness.id, 120 * par1, par1));
					break;
				case 3:
					if (TragicNewConfig.allowDisorientation)
					{
						par2EntityLivingBase.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 120 * par1, par1));
					}
					break;
				case 4:
					par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 120 * par1, par1));
				case 5:
					if (TragicNewConfig.allowDisorientation)
					{
						par2EntityLivingBase.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 120 * par1, par1));
					}
					break;
				case 6:
					par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.blindness.id, 120 * par1, par1));
				case 7:
					if (TragicNewConfig.allowDisorientation)
					{
						par2EntityLivingBase.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 120 * par1, par1));
					}
				case 8:
					par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 120 * par1, par1));
					break;
				case 9:
					if (TragicNewConfig.allowStun)
					{
						par2EntityLivingBase.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 120 * par1, par1));
					}
					break;
				}
			}

		}
		return 0F;
	}

}
