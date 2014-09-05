package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponMourningStar extends TragicWeapon {

	private final Lore[] uniqueLores = new Lore[] {new Lore("I mourn for no man.", EnumRarity.rare), new Lore("Sleep is for the weak", EnumRarity.uncommon), new Lore("Boom!"),
		new Lore("Just die already!", EnumRarity.rare), new Lore("For Spartaaaaaaa!", EnumRarity.uncommon), new Lore("I have an explosive temper.", EnumRarity.rare), 
		new Lore("Kaboom!"), new Lore("I'm TNT, I'm dynamite!", EnumRarity.uncommon), new Lore("Dy-no-mite!")};
	
	public WeaponMourningStar(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.smite};
		this.uncommonLevels = new int[] {3};
		this.rareEnchants = new Enchantment[] {Enchantment.smite, TragicEnchantments.Consume};
		this.rareLevels = new int[] {5, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.smite, TragicEnchantments.Consume, Enchantment.looting, Enchantment.unbreaking};
		this.epicLevels = new int[] {10, 3, 3, 5};
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom != null && cooldown == 0 && !par2World.isRemote)
		{
			if (!par3EntityPlayer.isSneaking())
			{
				if (doom.getCurrentDoom() >= 25)
				{
					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						doom.increaseDoom(-25);	
					}

					this.cooldown = 40;

					par2World.createExplosion(par3EntityPlayer, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, 5.0F * itemRand.nextFloat(), WorldHelper.getMobGriefing(par2World));
					return par1ItemStack;
				}
			}
			else
			{
				if (doom.getCurrentDoom() >= 30)
				{
					MovingObjectPosition mop = getMOPFromPlayer(par3EntityPlayer);

					if (mop == null)
					{
						return par1ItemStack;
					}
					
					if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK)
					{
						double d4 = mop.hitVec.xCoord;
						double d5 = mop.hitVec.yCoord;
						double d6 = mop.hitVec.zCoord;

						par3EntityPlayer.worldObj.createExplosion(par3EntityPlayer, d4, d5, d6, 5.0F * itemRand.nextFloat(), WorldHelper.getMobGriefing(par2World));

						if (!par3EntityPlayer.capabilities.isCreativeMode)
						{
							doom.increaseDoom(-30);
						}

						this.cooldown = 40;
					}

					return par1ItemStack;
				}
			}
		}
		return par1ItemStack;
	}

}
