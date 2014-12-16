package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.LoreHelper;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponReaperScythe extends ItemScythe {

	//private final Lore[] lores = new Lore[] {new Lore("Bleed for me.", EnumRarity.epic), new Lore("Bleed out.", EnumRarity.uncommon), new Lore("Let's paint this town red!"),
	//		new Lore("Care for fava beans and a nice chianti?"), new Lore("It's raining blood!", EnumRarity.rare), new Lore("Blood is thicker than water.", EnumRarity.uncommon),
	//		new Lore("Digging deeper just to throw it away!", EnumRarity.epic), new Lore("We all bleed the same.", EnumRarity.uncommon), new Lore("Blood is flowing!", EnumRarity.rare)};

	private Enchantment[] uncommonEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay};
	private int[] uncommonLevels = new int[] {3, 1};

	private Enchantment[] rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay, TragicEnchantments.Vampirism};
	private int[] rareLevels = new int[] {5, 3, 1};

	private Enchantment[] epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay, TragicEnchantments.Vampirism, Enchantment.looting};
	private int[] epicLevels = new int[] {10, 5, 3, 3};

	public WeaponReaperScythe(ToolMaterial par2Material, Doomsday dday) {
		super(par2Material);
		this.doomsday = dday;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (!TragicWeapon.canUseAbility(doom, 5) || TragicWeapon.getStackCooldown(par1ItemStack) > 0 || par2World.isRemote) return par1ItemStack;

		Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer, 30.0);
		if (vec == null) return par1ItemStack;

		if (TragicWeapon.getStackCooldown(par1ItemStack) == 0)
		{
			if (!par3EntityPlayer.isSneaking())
			{
				if (TragicWeapon.canUseAbility(doom, 5) && TragicWeapon.getStackCooldown(par1ItemStack) == 0)
				{
					double d4 = vec.xCoord - par3EntityPlayer.posX;
					double d5 = vec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
					double d6 = vec.zCoord - par3EntityPlayer.posZ;

					EntitySmallFireball rocket = new EntitySmallFireball(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
					rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
					par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-5);
					TragicWeapon.setStackCooldown(par1ItemStack, 5);

					return par1ItemStack;
				}
			}
			else
			{
				if (TragicWeapon.canUseAbility(doom, 15) && TragicWeapon.getStackCooldown(par1ItemStack) == 0)
				{
					double d4 = vec.xCoord - par3EntityPlayer.posX;
					double d5 = vec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
					double d6 = vec.zCoord - par3EntityPlayer.posZ;

					EntityLargeFireball rocket = new EntityLargeFireball(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
					rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
					par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-15);
					TragicWeapon.setStackCooldown(par1ItemStack, 5);

					return par1ItemStack;
				}
			}
		}

		return par1ItemStack;
	}
}
