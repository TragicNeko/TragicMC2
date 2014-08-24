package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponReaperScythe extends ItemScythe {

	private int cooldown;

	public WeaponReaperScythe(ToolMaterial par2Material, Doomsday dday) {
		super(par2Material);
		this.doomsday = dday;
	}

	public Doomsday getDoomsday()
	{
		return this.doomsday;
	}

	public EnumDoomType doomsdayType()
	{
		return this.doomsday.doomsdayType;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			PropertyDoom doom = PropertyDoom.get(par2EntityPlayer);

			EnumChatFormatting format = EnumChatFormatting.DARK_AQUA;

			if (doomsday.getDoomsdayType() == EnumDoomType.CRISIS)
			{
				format = EnumChatFormatting.DARK_RED;
			}

			if (doomsday.getDoomsdayType() == EnumDoomType.OVERFLOW)
			{
				format = EnumChatFormatting.GREEN;
			}

			if (doomsday.getDoomsdayType() == EnumDoomType.WORLDSHAPER)
			{
				format = EnumChatFormatting.DARK_PURPLE;
			}

			if (doomsday.getDoomsdayType() == EnumDoomType.ULTIMATE)
			{
				format = EnumChatFormatting.DARK_BLUE;
			}

			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());

			if (doom != null)
			{
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(doom));
			}
		}
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
			if (!par3EntityPlayer.isSneaking())
			{
				if (doom.getCurrentDoom() >= 5)
				{
					double d4 = mop.hitVec.xCoord - par3EntityPlayer.posX;
					double d5 = mop.hitVec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
					double d6 = mop.hitVec.zCoord - par3EntityPlayer.posZ;

					EntitySmallFireball rocket = new EntitySmallFireball(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
					rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
					par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						doom.increaseDoom(-5);
					}

					this.cooldown = 5;

					return par1ItemStack;
				}
			}
			else
			{
				if (doom.getCurrentDoom() >= 15)
				{
					double d4 = mop.hitVec.xCoord - par3EntityPlayer.posX;
					double d5 = mop.hitVec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
					double d6 = mop.hitVec.zCoord - par3EntityPlayer.posZ;

					EntityLargeFireball rocket = new EntityLargeFireball(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
					rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
					par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						doom.increaseDoom(-15);
					}

					this.cooldown = 5;

					return par1ItemStack;
				}
			}
		}

		return par1ItemStack;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (!world.isRemote && cooldown > 0)
		{
			this.cooldown--;
		}
	}

}
