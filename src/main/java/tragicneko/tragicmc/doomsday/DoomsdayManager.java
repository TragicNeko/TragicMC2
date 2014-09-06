package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tragicneko.tragicmc.main.TragicNewConfig;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public class DoomsdayManager {

	public static Logger logger = LogManager.getLogger("TragicMC/ Doomsday Manager");
	private static Map<String, ArrayList<DoomsdayEffect>> playerMap = new HashMap();
	private Map<Doomsday, Doomsday> combinations = new HashMap();
	private Map<Doomsday, Doomsday> combinationMap = new HashMap();

	public DoomsdayManager()
	{
		this.combinations.put(Doomsday.Purge, Doomsday.MoonlightSonata);
		this.combinationMap.put(Doomsday.MoonlightSonata, Doomsday.FlightOfTheValkyries);
		this.combinationMap.put(Doomsday.Purge, Doomsday.FlightOfTheValkyries);

		this.combinations.put(Doomsday.Titanfall, Doomsday.Ravage);
		this.combinationMap.put(Doomsday.Titanfall, Doomsday.LightningRush);
		this.combinationMap.put(Doomsday.Ravage, Doomsday.LightningRush);

		this.combinations.put(Doomsday.Marionette, Doomsday.Torment);
		this.combinationMap.put(Doomsday.Marionette, Doomsday.Mindcrack);
		this.combinationMap.put(Doomsday.Torment, Doomsday.Mindcrack);

		this.combinations.put(Doomsday.NatureDrain, Doomsday.RealityAlter);
		this.combinationMap.put(Doomsday.RealityAlter, Doomsday.GrowthSpurt);
		this.combinationMap.put(Doomsday.NatureDrain, Doomsday.GrowthSpurt);
		
		this.combinations.put(Doomsday.Permafrost, Doomsday.Freeze);
		this.combinationMap.put(Doomsday.Permafrost, Doomsday.Blizzard);
		this.combinationMap.put(Doomsday.Freeze, Doomsday.Blizzard);
	}

	public static void registerDoomsdayEffect(String playerName, DoomsdayEffect effect)
	{
		if (playerMap.containsKey(playerName))
		{
			try
			{
				ArrayList<DoomsdayEffect> list = playerMap.get(playerName);
				list.add(effect);
				logger.info("" + playerName + " was previously registered, adding an additional Doomsday effect: " + effect.dday.getLocalizedName());
			}
			catch (ConcurrentModificationException e)
			{
				logger.error("Error caught while attempting to register a new Doomsday effect.", e);
			}
		}
		else
		{
			try
			{
				ArrayList<DoomsdayEffect> list = new ArrayList();
				list.add(effect);
				playerMap.put(playerName, list);
				logger.info("" + playerName + " registered with a Doomsday effect: " + effect.dday.getLocalizedName());
			}
			catch (ConcurrentModificationException e)
			{
				logger.error("Error caught while attempting to register a new Doomsday effect.", e);
			}
		}
	}

	public static void registerDoomsdayEffect(String playerName, DoomsdayEffect... effect)
	{
		for (int i = 0; i < effect.length; i++)
		{
			registerDoomsdayEffect(playerName, effect[i]);
		}
	}

	public static void clearRegistry()
	{
		playerMap.clear();
		logger.info("Registry was cleared.");
	}

	@SubscribeEvent
	public void onTick(ServerTickEvent event)
	{
		if (playerMap.isEmpty()) return;

		if (event.phase == Phase.END)
		{
			Set set = playerMap.keySet();
			Iterator<String> ite = set.iterator();

			while (ite.hasNext())
			{
				EntityPlayerMP mp = MinecraftServer.getServer().getConfigurationManager().func_152612_a(ite.next());
				ArrayList<DoomsdayEffect> list = playerMap.get(mp.getCommandSenderName());
				DoomsdayEffect effect;
				boolean flag = false;

				for (int i = 0; i < list.size(); i++)
				{
					effect = list.get(i);

					if (effect == null)
					{
						logger.error("Player had a null registration somehow! Error caught and hopefully avoided!");
						break;
					}

					if (TragicNewConfig.allowCombinationDoomsday)
					{
						if (flag && combinations.containsValue(effect.dday))
						{
							if (effect.isActive && !effect.isInstant)
							{
								if (!mp.capabilities.isCreativeMode && !effect.isCommandActivated) effect.dday.applyDoomAndCooldown(effect.doom, effect.iterations);
								logger.info("Combination Doomsday activated, registry applyied Doom cost from extended Doomsday.");
							}
							list.clear();
							list.add(new DoomsdayEffect(combinationMap.get(effect.dday).getDoomId(), effect.doom, effect.isCommandActivated));
							logger.info("Added Combination Doomsday to current effect registrations and cleared all others.");
							break;
						}
						
						if (combinations.containsKey(effect.dday))
						{
							flag = true;
						}
					}

					effect.onDoomsdayUpdate();

					if (!effect.isActive)
					{
						logger.info("Registry cleared a " + effect.dday.getLocalizedName() + " Doomsday effect from " + mp.getCommandSenderName());

						if (effect.isInstant)
						{
							if (!mp.capabilities.isCreativeMode && !effect.isCommandActivated) effect.dday.applyDoomAndCooldown(effect.doom);
						}
						else
						{
							if (!mp.capabilities.isCreativeMode && !effect.isCommandActivated) effect.dday.applyDoomAndCooldown(effect.doom, effect.iterations);
						}

						list.remove(effect);
					}
				}

				if (list.isEmpty()) 
				{
					playerMap.remove(mp.getCommandSenderName());
					logger.info("Removed registration for " + mp.getCommandSenderName() + " as they had no more effects registered.");
				}
			}


		}
	}
}
