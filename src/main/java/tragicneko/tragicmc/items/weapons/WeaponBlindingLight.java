package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotions;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponBlindingLight extends TragicWeapon {

	public WeaponBlindingLight(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
	}

	public boolean onLeftClickEntity(ItemStack itemstack, EntityPlayer player, Entity entity)
	{
		Boolean result = super.onLeftClickEntity(itemstack, player, entity);

		PropertyDoom doom = PropertyDoom.get(player);

		if (!result && doom != null && getStackCooldown(itemstack) == 0 && TragicNewConfig.nonDoomsdayAbilities[2])
		{
			if (entity instanceof EntityLivingBase && itemRand.nextInt(16) == 0 && canUseAbility(doom, 10))
			{
				int x = itemRand.nextInt(6) + 10;

				if (entity.worldObj.getBlockLightValue(MathHelper.ceiling_double_int(entity.posX), MathHelper.ceiling_double_int(entity.posY), MathHelper.ceiling_double_int(entity.posZ)) <= 6)
				{
					entity.setFire(x);
					if (!player.capabilities.isCreativeMode) doom.increaseDoom(-10);
					setStackCooldown(itemstack, 5);
				}
			}

			if (entity instanceof EntityLivingBase && TragicNewConfig.allowCorruption && ((EntityLivingBase) entity).isPotionActive(TragicPotions.Corruption))
			{
				((EntityLivingBase) entity).removePotionEffect(TragicPotions.Corruption.id);
			}
		}

		return result;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par2World.isRemote) return par1ItemStack;

		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (canUseAbility(doom, 15) && getStackCooldown(par1ItemStack) == 0 && TragicNewConfig.nonDoomsdayAbilities[1])
		{
			Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer, 30.0);
			if (vec == null) return par1ItemStack;

			double d4 = vec.xCoord - par3EntityPlayer.posX;
			double d5 = vec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
			double d6 = vec.zCoord - par3EntityPlayer.posZ;

			EntitySolarBomb rocket = new EntitySolarBomb(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
			rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
			par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

			if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-15);
			setStackCooldown(par1ItemStack, 5);
		}
		return par1ItemStack;
	}

}
