package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponGuiltyThorn extends TragicWeapon {

	public WeaponGuiltyThorn(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		PropertyDoom doom = PropertyDoom.get(player);
		
		if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && itemRand.nextInt(4) == 0 && cooldown == 0 && doom != null && doom.getCurrentDoom() >= 10
				&& TragicNewConfig.allowNonDoomsdayAbilities)
		{
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 60, itemRand.nextInt(4)));
			
			if (TragicNewConfig.allowStun && itemRand.nextInt(16) == 0)
			{
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 40, 0));
			}
			
			if (player.capabilities.isCreativeMode)
			{
				doom.increaseDoom(-10);
			}
			
			cooldown = 100;
		}
		return super.onLeftClickEntity(stack, player, entity);
	} 
}
