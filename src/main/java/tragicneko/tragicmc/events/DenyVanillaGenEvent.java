package tragicneko.tragicmc.events;

import net.minecraftforge.event.terraingen.OreGenEvent;
import tragicneko.tragicmc.dimension.SynapseWorldProvider;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DenyVanillaGenEvent {

	@SubscribeEvent
	public void denyDimensionVanillaGen(OreGenEvent.GenerateMinable event)
	{
		if (event.world.provider instanceof TragicWorldProvider || event.world.provider instanceof SynapseWorldProvider)
		{
			if (event.hasResult())
			{
				event.setResult(Result.DENY);
			}

		}
	}
}
