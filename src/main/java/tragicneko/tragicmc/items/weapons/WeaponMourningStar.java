package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponMourningStar extends TragicWeapon {

	//private final Lore[] uniqueLores = new Lore[] {new Lore("I mourn for no man.", EnumRarity.rare), new Lore("Sleep is for the weak", EnumRarity.uncommon), new Lore("Boom!"),
	//		new Lore("Just die already!", EnumRarity.rare), new Lore("For Spartaaaaaaa!", EnumRarity.uncommon), new Lore("I have an explosive temper.", EnumRarity.rare), 
	//		new Lore("Kaboom!"), new Lore("I'm TNT, I'm dynamite!", EnumRarity.uncommon), new Lore("Dy-no-mite!")};

	public WeaponMourningStar(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		//this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.smite};
		this.uncommonLevels = new int[] {3};
		this.rareEnchants = new Enchantment[] {Enchantment.smite, TragicEnchantments.Consume};
		this.rareLevels = new int[] {5, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.smite, TragicEnchantments.Consume, Enchantment.looting, Enchantment.unbreaking};
		this.epicLevels = new int[] {10, 3, 3, 5};
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par2World.isRemote) return par1ItemStack;
		
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom != null)
		{
			if (!par3EntityPlayer.isSneaking())
			{
				if (canUseAbility(doom, 25) && getStackCooldown(par1ItemStack) == 0)
				{
					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-25);
					setStackCooldown(par1ItemStack, 5);

					par2World.createExplosion(par3EntityPlayer, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, 3.0F * itemRand.nextFloat() + 2.0F, TragicNewConfig.griefConfigs[3]);
					return par1ItemStack;
				}
			}
			else
			{
				if (canUseAbility(doom, 30) && getStackCooldown(par1ItemStack) == 0)
				{
					Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer, 30.0);			
					if (vec == null) return par1ItemStack;
					
					double d4 = vec.xCoord;
					double d5 = vec.yCoord;
					double d6 = vec.zCoord;

					par3EntityPlayer.worldObj.createExplosion(par3EntityPlayer, d4, d5, d6, 3.0F * itemRand.nextFloat() + 2.0F, TragicNewConfig.griefConfigs[3]);
					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-30);
					setStackCooldown(par1ItemStack, 5);
					
					return par1ItemStack;
				}
			}
		}
		return par1ItemStack;
	}

}
