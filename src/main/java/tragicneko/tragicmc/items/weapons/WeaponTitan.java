package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponTitan extends EpicWeapon {

	public WeaponTitan(Doomsday dday) {
		super(dday);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote || !(entity instanceof EntityLivingBase)) return super.onLeftClickEntity(stack, player, entity);
		
		if (itemRand.nextInt(6) != 0) return super.onLeftClickEntity(stack, player, entity);

		PropertyDoom doom = PropertyDoom.get(player);

		if (canUseAbility(doom, TragicConfig.nonDoomsdayAbilityCosts[31]) && getStackCooldown(stack) == 0 && TragicConfig.nonDoomsdayAbilities[31])
		{
			for (int i = 0; i < 3; i++)
			{
				player.worldObj.spawnEntityInWorld(new EntityDirectedLightning(player.worldObj, entity.posX + itemRand.nextDouble() - itemRand.nextDouble(), entity.posY,
						entity.posZ + itemRand.nextDouble() - itemRand.nextDouble(), player));
			}

			player.worldObj.createExplosion(player, entity.posX, entity.posY, entity.posZ, itemRand.nextFloat() * 3.0F, TragicConfig.griefConfigs[4]);

			if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.nonDoomsdayAbilityCosts[31]);
			setStackCooldown(stack, 5);
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par2World.isRemote) return par1ItemStack;
		
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
		if (doom == null) return par1ItemStack;

		Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer, 50.0);
		if (vec == null) return par1ItemStack;

		if (canUseAbility(doom, TragicConfig.nonDoomsdayAbilityCosts[32]) && getStackCooldown(par1ItemStack) == 0 && TragicConfig.nonDoomsdayAbilities[32])
		{
			double d4 = vec.xCoord - par3EntityPlayer.posX;
			double d5 = vec.yCoord - (par3EntityPlayer.posY + par3EntityPlayer.height / 2.0F);
			double d6 = vec.zCoord - par3EntityPlayer.posZ;

			double d7 = MathHelper.sqrt_double(d4 * d4 + d5 * d5 + d6 * d6);

			if (d7 >= 3.0D)
			{
				par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.95D * d4)),
						par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.95D * d4)), (int) (par3EntityPlayer.posZ + (0.95D * d6))),
						par3EntityPlayer.posZ + (0.95D * d6)));

				if (d7 >= 8.0D)
				{
					par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.75D * d4)),
							par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.75D * d4)), (int) (par3EntityPlayer.posZ + (0.75D * d6))),
							par3EntityPlayer.posZ + (0.75D * d6)));

					if (d7 >= 13.0D)
					{
						par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.6D * d4)),
								par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.6D * d4)), (int) (par3EntityPlayer.posZ + (0.6D * d6))),
								par3EntityPlayer.posZ + (0.6D * d6)));

						if (d7 >= 18.0D)
						{
							par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.4D * d4)),
									par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.4D * d4)), (int) (par3EntityPlayer.posZ + (0.4D * d6))),
									par3EntityPlayer.posZ + (0.4D * d6)));

							if (d7 >= 21.0D)
							{
								par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.2D * d4)),
										par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.2D * d4)), (int) (par3EntityPlayer.posZ + (0.2D * d6))),
										par3EntityPlayer.posZ + (0.2D * d6)));
								
								if (d7 >= 24.0D)
								{
									par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.1D * d4)),
											par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.1D * d4)), (int) (par3EntityPlayer.posZ + (0.1D * d6))),
											par3EntityPlayer.posZ + (0.1D * d6)));
								}
							}
						}
					}
				}
				
				if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.nonDoomsdayAbilityCosts[32]);
				setStackCooldown(par1ItemStack, 5);
			}
		}
		return par1ItemStack;
	}

}
