package tragicneko.tragicmc.items;

import java.util.List;

import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.entity.projectile.EntityThrowingRock;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class ItemProjectile extends Item {

	private int cooldown;

	public ItemProjectile()
	{
		super();
		this.setCreativeTab(TragicTabs.Creative);
		this.setMaxDamage(0);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) 
	{
		if (cooldown != 0)
		{
			return stack;
		}
		
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(world.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 50.0D;

		if (player instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance() + 46.0;
		}
		Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);

		MovingObjectPosition mop = world.func_147447_a(vec3, vec31, true, false, true);

		if (!world.isRemote && cooldown == 0) 
		{
			if (this == TragicItems.PoisonBarb)
			{
				if (mop == null)
				{
					return stack;
				}

				if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK)
				{
					double d4 = mop.hitVec.xCoord - player.posX;
					double d5 = mop.hitVec.yCoord - (player.posY + (double)(player.height / 2.0F));
					double d6 = mop.hitVec.zCoord - player.posZ;

					EntityPoisonBarb rocket = new EntityPoisonBarb(world, player, d4, d5, d6);
					rocket.posY = player.posY + player.getEyeHeight();
					world.spawnEntityInWorld(rocket);
					cooldown = 10;
				}
			}

			if (this == TragicItems.NekoStickyBomb)
			{
				world.spawnEntityInWorld(new EntityNekoStickyBomb(world, player));
				cooldown = 10;
			}

			if (this == TragicItems.NekoClusterBomb)
			{
				world.spawnEntityInWorld(new EntityNekoClusterBomb(world, player));
				cooldown = 10;
			}

			if (this == TragicItems.NekoMiniBomb)
			{
				world.spawnEntityInWorld(new EntityNekoMiniBomb(world, player));
				cooldown = 10;
			}

			if (this == TragicItems.NekoRocket)
			{
				if (mop == null)
				{
					return stack;
				}

				if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK)
				{
					double d4 = mop.hitVec.xCoord - player.posX;
					double d5 = mop.hitVec.yCoord - (player.posY + (double)(player.height / 2.0F));
					double d6 = mop.hitVec.zCoord - player.posZ;

					EntityNekoRocket rocket = new EntityNekoRocket(world, player, d4, d5, d6);
					rocket.posY = player.posY + player.getEyeHeight();
					world.spawnEntityInWorld(rocket);
					cooldown = 10;
				}
			}
			
			if (this == TragicItems.SolarBomb)
			{
				if (mop == null)
				{
					return stack;
				}

				if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK)
				{
					double d4 = mop.hitVec.xCoord - player.posX;
					double d5 = mop.hitVec.yCoord - (player.posY + (double)(player.height / 2.0F));
					double d6 = mop.hitVec.zCoord - player.posZ;

					EntitySolarBomb rocket = new EntitySolarBomb(world, player, d4, d5, d6);
					rocket.posY = player.posY + player.getEyeHeight();
					world.spawnEntityInWorld(rocket);
					cooldown = 10;
				}
			}
			
			if (this == TragicItems.SpiritCast)
			{
				if (mop == null)
				{
					return stack;
				}

				if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK)
				{
					double d4 = mop.hitVec.xCoord - player.posX;
					double d5 = mop.hitVec.yCoord - (player.posY + (double)(player.height / 2.0F));
					double d6 = mop.hitVec.zCoord - player.posZ;

					EntitySpiritCast rocket = new EntitySpiritCast(world, player, d4, d5, d6);
					rocket.posY = player.posY + player.getEyeHeight();
					world.spawnEntityInWorld(rocket);
					cooldown = 10;
				}
			}

		}

		if (!player.capabilities.isCreativeMode) 
		{
			--stack.stackSize;
		}

		if (cooldown == 0)
		{
			world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}

		return stack;
	}

	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
	{
		if (this.cooldown > 0 && !world.isRemote)
		{
			cooldown--;
		}
	}
}
