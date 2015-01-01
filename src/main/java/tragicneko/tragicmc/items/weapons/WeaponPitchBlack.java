package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityPitchBlack;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponPitchBlack extends TragicWeapon {

	//private final Lore[] uniqueLores = new Lore[] {new Lore("Black Hole Sun", EnumRarity.rare), new Lore("Black as my soul", EnumRarity.uncommon), new Lore("Pitch Black! ...Wait"),
	//		new Lore("Paint it Black!", EnumRarity.uncommon), new Lore("Black out, blood in your eyes!", EnumRarity.rare), new Lore("Hide in the Shadows.", EnumRarity.uncommon),
	//		new Lore("Perfect Dark Zero."), new Lore("Welcome to the Black Parade~", EnumRarity.epic), new Lore("I was born in the dark.", EnumRarity.epic)};

	public WeaponPitchBlack(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		//this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay};
		this.uncommonLevels = new int[] {1, 1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay, TragicEnchantments.RuneBreak};
		this.rareLevels = new int[] {3, 3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay, TragicEnchantments.RuneBreak, Enchantment.looting};
		this.epicLevels = new int[] {10, 5, 3, 3};
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom == null || !TragicNewConfig.allowNonDoomsdayAbilities || par2World.isRemote) return par1ItemStack;

		Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
		if (vec == null) return par1ItemStack;

		if (canUseAbility(doom, 5) && getStackCooldown(par1ItemStack) == 0 && TragicNewConfig.nonDoomsdayAbilities[24])
		{
			setStackCooldown(par1ItemStack, 5);

			EntityPitchBlack rocket = new EntityPitchBlack(par3EntityPlayer.worldObj, par3EntityPlayer);
			rocket.posY = par3EntityPlayer.posY + 1.6D;
			rocket.setStack(par1ItemStack);
			par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

			if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-5);
			par1ItemStack.stackSize--;

			return par1ItemStack;
		}


		return par1ItemStack;
	}
}
