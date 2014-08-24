package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponMourningStar extends TragicWeapon {

	public WeaponMourningStar(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
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
