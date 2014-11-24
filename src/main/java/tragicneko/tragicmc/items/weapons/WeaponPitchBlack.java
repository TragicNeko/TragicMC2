package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityPitchBlack;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponPitchBlack extends TragicWeapon {

	private final Lore[] uniqueLores = new Lore[] {new Lore("Black Hole Sun", EnumRarity.rare), new Lore("Black as my soul", EnumRarity.uncommon), new Lore("Pitch Black! ...Wait"),
			new Lore("Paint it Black!", EnumRarity.uncommon), new Lore("Black out, blood in your eyes!", EnumRarity.rare), new Lore("Hide in the Shadows.", EnumRarity.uncommon),
			new Lore("Perfect Dark Zero."), new Lore("Welcome to the Black Parade~", EnumRarity.epic), new Lore("I was born in the dark.", EnumRarity.epic)};

	public WeaponPitchBlack(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		this.lores = uniqueLores;
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

		Vec3 vec = getVecFromPlayer(par3EntityPlayer);
		if (vec == null) return par1ItemStack;

		if (canUseAbility(doom, 5) && getStackCooldown(par1ItemStack) == 0)
		{
			setStackCooldown(par1ItemStack, 5);
			double d4 = vec.xCoord - par3EntityPlayer.posX;
			double d5 = vec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
			double d6 = vec.zCoord - par3EntityPlayer.posZ;

			EntityPitchBlack rocket = new EntityPitchBlack(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
			rocket.posX = par3EntityPlayer.posX + d4 * 0.15D;
			rocket.posY = par3EntityPlayer.posY + 0.6D;
			rocket.posZ = par3EntityPlayer.posZ + d6 * 0.15D;
			rocket.setStack(par1ItemStack);
			par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

			if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-5);
			par1ItemStack.stackSize--;

			return par1ItemStack;
		}


		return par1ItemStack;
	}
}
