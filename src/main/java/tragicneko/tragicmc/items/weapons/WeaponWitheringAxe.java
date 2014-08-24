package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponWitheringAxe extends TragicWeapon {

	public WeaponWitheringAxe(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom != null && cooldown == 0 && !par2World.isRemote && TragicNewConfig.allowNonDoomsdayAbilities)
		{
			if (!par3EntityPlayer.isSneaking())
			{
				if (doom.getCurrentDoom() >= 10)
				{
					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						doom.increaseDoom(-10);
						this.cooldown = 10;
					}

					EntityWitherSkull skull = new EntityWitherSkull(par2World);
					par2World.spawnEntityInWorld(skull);
					return par1ItemStack;
				}
			}
			else
			{
				if (doom.getCurrentDoom() >= 25)
				{
					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						doom.increaseDoom(-25);
						this.cooldown = 20;
					}
					EntityWitherSkull skull = new EntityWitherSkull(par2World);
					skull.setInvulnerable(true);
					par2World.spawnEntityInWorld(skull);
					return par1ItemStack;
				}
			}
		}

		return par1ItemStack;
	}

}
