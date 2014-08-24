package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponGravitySpike extends TragicWeapon {

	public WeaponGravitySpike(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && itemRand.nextInt(4) == 0 && cooldown == 0 && TragicNewConfig.allowNonDoomsdayAbilities)
		{
			PropertyDoom doom = PropertyDoom.get(player);

			if (doom != null && cooldown == 0 && !player.worldObj.isRemote && doom.getCurrentDoom() >= 7)
			{
				double d0 = 16.0D;
				double d1 = entity.posX - player.posX;
				double d2 = entity.posZ - player.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				double d3 = 5.0D;

				entity.motionX = d1 / (double)f2 * d3 * 0.800000011920929D + entity.motionX * 0.60000000298023224D;
				entity.motionZ = d2 / (double)f2 * d3 * 0.800000011920929D + entity.motionZ * 0.60000000298023224D;
				entity.motionY += 1.45;
				
				if (!player.capabilities.isCreativeMode)
				{
					doom.increaseDoom(-7);
				}

				cooldown = 100;
			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	} 
}
