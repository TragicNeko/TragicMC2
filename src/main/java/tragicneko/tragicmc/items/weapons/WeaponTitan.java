package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponTitan extends EpicWeapon {

	public WeaponTitan(Doomsday dday) {
		super(dday);
		this.lores = new Lore[] {new Lore("TitanLore1"), new Lore("TitanLore2"), new Lore("TitanLore3")};
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.RuneBreak};
		this.rareLevels = new int[] {3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.RuneBreak};
		this.epicLevels = new int[] {5, 3};
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote) return super.onLeftClickEntity(stack, player, entity);
		
		if (itemRand.nextInt(6) != 0) return super.onLeftClickEntity(stack, player, entity);

		PropertyDoom doom = PropertyDoom.get(player);

		if (doom != null && doom.getCurrentDoom() >= 10)
		{
			for (int i = 0; i < 3; i++)
			{
				player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, entity.posX + itemRand.nextDouble() - itemRand.nextDouble(), entity.posY,
						entity.posZ + itemRand.nextDouble() - itemRand.nextDouble()));
			}

			player.worldObj.createExplosion(player, entity.posX, entity.posY, entity.posZ, itemRand.nextFloat() * 3.0F, WorldHelper.getMobGriefing(player.worldObj));

			if (!player.capabilities.isCreativeMode)
			{
				doom.increaseDoom(-10);
			}

			this.cooldown = 40;
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom == null || !TragicNewConfig.allowNonDoomsdayAbilities) return par1ItemStack;

		MovingObjectPosition mop = this.getMOPFromPlayer(par3EntityPlayer);

		if (mop == null)
		{
			return par1ItemStack;
		}

		if (cooldown == 0 && !par2World.isRemote && doom.getCurrentDoom() >= 20)
		{
			double d4 = mop.hitVec.xCoord - par3EntityPlayer.posX;
			double d5 = mop.hitVec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
			double d6 = mop.hitVec.zCoord - par3EntityPlayer.posZ;

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
				
				if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseCooldown(-20);
				cooldown = 40;
			}
		}
		return par1ItemStack;
	}

}
