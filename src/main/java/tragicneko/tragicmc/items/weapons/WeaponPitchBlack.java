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

		if (doom == null || !TragicNewConfig.allowNonDoomsdayAbilities) return par1ItemStack;

		float f = 1.0F;
		float f1 = par3EntityPlayer.prevRotationPitch + (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch) * f;
		float f2 = par3EntityPlayer.prevRotationYaw + (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw) * f;
		double d0 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double)f;
		double d1 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double)f + (double)(par3EntityPlayer.worldObj.isRemote ? par3EntityPlayer.getEyeHeight() - par3EntityPlayer.getDefaultEyeHeight() : par3EntityPlayer.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
		double d2 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 50.0D;

		if (par3EntityPlayer instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP)par3EntityPlayer).theItemInWorldManager.getBlockReachDistance() + 46.0;
		}
		Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);

		MovingObjectPosition mop = par3EntityPlayer.worldObj.func_147447_a(vec3, vec31, true, false, true);

		if (mop == null)
		{
			return par1ItemStack;
		}

		if (cooldown == 0 && !par2World.isRemote)
		{
			if (doom.getCurrentDoom() >= 5)
			{
				double d4 = mop.hitVec.xCoord - par3EntityPlayer.posX;
				double d5 = mop.hitVec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
				double d6 = mop.hitVec.zCoord - par3EntityPlayer.posZ;

				EntityPitchBlack rocket = new EntityPitchBlack(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
				rocket.posX = par3EntityPlayer.posX + d4 * 0.15D;
				rocket.posY = par3EntityPlayer.posY + 0.6D;
				rocket.posZ = par3EntityPlayer.posZ + d6 * 0.15D;
				rocket.setStack(par1ItemStack);
				par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

				if (!par3EntityPlayer.capabilities.isCreativeMode)
				{
					doom.increaseDoom(-5);
				}
				
				par1ItemStack.stackSize--;
				this.cooldown = 5;

				return par1ItemStack;
			}
		}

		return par1ItemStack;
	}
}
