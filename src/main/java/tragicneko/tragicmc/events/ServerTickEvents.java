package tragicneko.tragicmc.events;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.mob.EntityIre;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public class ServerTickEvents {

	@SubscribeEvent
	public void onServerTick(ServerTickEvent event)
	{
		if (event.phase == Phase.START)
		{
			if (TragicConfig.allowIre)
			{
				EntityIre.ireTick++;
				if (EntityIre.ireTick >= 40)
				{
					EntityIre.ireTick = 0;
					EntityIre.ireNetSize = 0;
				}
				TragicMC.logInfo("Ire net size: " + EntityIre.ireNetSize);
				//TragicMC.logInfo("Ire tick: " + EntityIre.ireTick);
			}
		}
	}
}
