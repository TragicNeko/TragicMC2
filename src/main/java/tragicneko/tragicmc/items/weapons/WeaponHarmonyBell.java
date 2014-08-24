package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponHarmonyBell extends TragicWeapon {

	public WeaponHarmonyBell(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		PropertyDoom doom = PropertyDoom.get(player);

		if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && TragicNewConfig.allowHarmony && TragicNewConfig.allowNonDoomsdayAbilities)
		{
			if (doom != null && doom.getCurrentDoom() >= 3)
			{
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotions.Harmony.id, 60, 0));

				if (!player.capabilities.isCreativeMode)
				{
					doom.increaseDoom(-3);
				}
			}
		}

		return true;
	}

	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		if (entity instanceof EntityLivingBase)
		{
			if (par5 && entity.ticksExisted % 120 == 0 && ((EntityLivingBase) entity).getHealth() < ((EntityLivingBase) entity).getMaxHealth() && TragicNewConfig.allowNonDoomsdayAbilities)
			{
				if (entity instanceof EntityPlayer)
				{
					PropertyDoom doom = PropertyDoom.get((EntityPlayer) entity);

					if (doom.getCurrentDoom() > 0)
					{
						doom.increaseDoom(-1);
						((EntityLivingBase) entity).heal(1.0F);
					}
				}
			}
		}
	}
}
