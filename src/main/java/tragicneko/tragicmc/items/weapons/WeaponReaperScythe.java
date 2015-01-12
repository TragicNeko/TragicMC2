package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.properties.PropertyDoom;
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

	@Override
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
				if (TragicWeapon.canUseAbility(doom, TragicConfig.nonDoomsdayAbilityCosts[25]) && TragicConfig.nonDoomsdayAbilities[25])
				{
					EntityPumpkinbomb bomb = new EntityPumpkinbomb(par2World, par3EntityPlayer);
					bomb.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
					par3EntityPlayer.worldObj.spawnEntityInWorld(bomb);

					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.nonDoomsdayAbilityCosts[25]);
					TragicWeapon.setStackCooldown(par1ItemStack, 5);

					return par1ItemStack;
				}
			}
			else
			{
				if (TragicWeapon.canUseAbility(doom, TragicConfig.nonDoomsdayAbilityCosts[26]) && TragicConfig.nonDoomsdayAbilities[26])
				{
					EntityLargePumpkinbomb bomb = new EntityLargePumpkinbomb(par2World, par3EntityPlayer);
					bomb.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
					par3EntityPlayer.worldObj.spawnEntityInWorld(bomb);

					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.nonDoomsdayAbilityCosts[26]);
					TragicWeapon.setStackCooldown(par1ItemStack, 5);

					return par1ItemStack;
				}
			}
		}

		return par1ItemStack;
	}
}
