package tragicneko.tragicmc.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.network.MessageDoom;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class RespawnDoomEvents {

	@SubscribeEvent
	public void onRespawn(PlayerRespawnEvent event)
	{
		PropertyDoom property = PropertyDoom.get(event.player);

		if (property != null && event.player instanceof EntityPlayerMP)
		{
			TragicMC.net.sendTo(new MessageDoom((EntityPlayer) event.player), (EntityPlayerMP)event.player);
		}
		
		if (TragicNewConfig.allowRespawnPunishment && !event.player.capabilities.isCreativeMode)
		{
			if (event.player.worldObj.difficultySetting == EnumDifficulty.HARD)
			{
				event.player.addPotionEffect(new PotionEffect(Potion.weakness.id, 1800, 1));

				if (TragicNewConfig.allowCripple)
				{
					event.player.addPotionEffect(new PotionEffect(TragicPotions.Cripple.id, 1800, 5));
					event.player.setHealth(event.player.getMaxHealth());
				}
			}

			if (event.player.worldObj.difficultySetting == EnumDifficulty.NORMAL)
			{
				event.player.addPotionEffect(new PotionEffect(Potion.weakness.id, 1200));

				if (TragicNewConfig.allowCripple)
				{
					event.player.addPotionEffect(new PotionEffect(TragicPotions.Cripple.id, 1200, 3));
					event.player.setHealth(event.player.getMaxHealth());
				}
			}

			if (event.player.worldObj.difficultySetting == EnumDifficulty.EASY)
			{
				if (TragicNewConfig.allowCripple)
				{
					event.player.addPotionEffect(new PotionEffect(TragicPotions.Cripple.id, 600, 1));
					event.player.setHealth(event.player.getMaxHealth());
				}
			}
		}
	}

}
