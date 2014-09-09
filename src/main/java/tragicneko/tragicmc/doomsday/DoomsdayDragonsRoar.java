package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayDragonsRoar extends Doomsday {

	public DoomsdayDragonsRoar(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom);
	}

	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Dragon's Roar!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		if (TragicNewConfig.allowFlight)
		{
			player.addPotionEffect(new PotionEffect(TragicPotions.Flight.id, 6000, 0));
		}
		else
		{
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 600, 3));
		}

		if (crucMoment)
		{
			List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(8.0D, 8.0D, 8.0D));
			EntityLivingBase entity;

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityLivingBase)
				{
					entity = (EntityLivingBase) list.get(i);
					if (TragicNewConfig.allowStun && crucMoment)
					{
						entity.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 120, 1));
					}
					
					entity.addPotionEffect(new PotionEffect(Potion.weakness.id, 120, 10));
				}
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}

}
