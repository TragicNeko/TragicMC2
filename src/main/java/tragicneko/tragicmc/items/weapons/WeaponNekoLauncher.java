package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponNekoLauncher extends TragicWeapon {

	//private final Lore[] uniqueLores = new Lore[] {new Lore("Some days you just can't get rid of a bomb!", EnumRarity.epic), new Lore("I meant to do that!", EnumRarity.uncommon),
	//		new Lore("That was supposed to happen!"), new Lore("Oops."), new Lore("This is why I can't have nice things!", EnumRarity.rare),
	//		new Lore("It's just a flesh wound.", EnumRarity.rare), new Lore("Meow~", EnumRarity.epic)};

	public WeaponNekoLauncher(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		//this.lores = uniqueLores;
		this.setMaxDamage(250);
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.knockback};
		this.rareLevels = new int[] {5, 3};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.knockback, TragicEnchantments.Distract};
		this.epicLevels = new int[] {10, 5, 3};
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom == null || doom.getCurrentDoom() < 5 || getStackCooldown(par1ItemStack) > 0) return par1ItemStack;

		Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
		if (vec == null) return par1ItemStack;

		double d4 = vec.xCoord - par3EntityPlayer.posX;
		double d5 = vec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
		double d6 = vec.zCoord - par3EntityPlayer.posZ;

		EntityNekoRocket rocket = new EntityNekoRocket(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
		rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
		if (!par2World.isRemote) par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

		par1ItemStack.damageItem(1, par3EntityPlayer);
		if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-5);
		setStackCooldown(par1ItemStack, 5);

		return par1ItemStack;
	}

}
