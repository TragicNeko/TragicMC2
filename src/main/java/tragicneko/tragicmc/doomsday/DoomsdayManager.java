package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicNewConfig;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerDisconnectionFromClientEvent;

public class DoomsdayManager {

	public static Logger logger = LogManager.getLogger(TragicMC.MODID + "/ Doomsday Manager");
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

		this.combinations.put(Doomsday.FireRain, Doomsday.DragonsRoar);
		this.combinationMap.put(Doomsday.FireRain, Doomsday.Firestorm);
		this.combinationMap.put(Doomsday.DragonsRoar, Doomsday.Firestorm);

		this.combinations.put(Doomsday.RapidFire, Doomsday.Snipe);
		this.combinationMap.put(Doomsday.RapidFire, Doomsday.Shotgun);
		this.combinationMap.put(Doomsday.Snipe, Doomsday.Shotgun);
	}

	public static void registerDoomsdayEffect(String playerName, DoomsdayEffect effect)
	{
		if (playerMap.containsKey(playerName))
		{
			try
			{
				ArrayList<DoomsdayEffect> list = playerMap.get(playerName);

				for (int i = 0; i < list.size(); i++)
				{
					DoomsdayEffect effect2 = list.get(i);
					if (effect2.dday == effect.dday) 
					{
						logger.warn(playerName + " attempted to register a new Doomsday effect for a Doomsday they already have active, ignoring registration.");
						return;
					}
				}
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

	public static void clearRegistry()
	{
		try
		{
			playerMap.clear();
			logger.info("Doomsday registry was cleared.");
		}
		catch (Exception e)
		{
			logger.error("Error caught while clearing the playerMap", e);
		}
	}

	public static void clearPlayerFromRegistry(String playerName, String reason)
	{
		try
		{
			if (playerMap.containsKey(playerName))
			{
				playerMap.remove(playerName);
				if (reason != null)
				{
					logger.info("Registry removed registration for " + playerName + ", the reason was: " + reason);
				}
				else
				{
					logger.info("Registry removed registration for " + playerName);
				}
			}
			else
			{
				logger.error("Attempted to remove a player that was not registered in the map, there is a problem.");
			}
		}
		catch (Exception e)
		{
			logger.error("Error caught while attempting to remove a player from the playerMap", e);
		}
	}

	@SubscribeEvent
	public void onTick(ServerTickEvent event)
	{
		if (playerMap.isEmpty()) return;

		if (event.phase == Phase.END)
		{
			Set set = playerMap.keySet();
			Iterator<String> ite = set.iterator();
			String reason = null;

			while (ite.hasNext())
			{
				EntityPlayerMP mp = MinecraftServer.getServer().getConfigurationManager().func_152612_a(ite.next());
				ArrayList<DoomsdayEffect> list = playerMap.get(mp.getCommandSenderName());
				DoomsdayEffect effect;
				boolean flag = false;
				reason = null;

				if (list.size() == 0)
				{
					reason = "No Doomsday effects registered.";
				}

				for (int i = 0; i < list.size(); i++)
				{
					effect = list.get(i);

					if (effect == null)
					{
						logger.error("Player had a null registration somehow! To prevent any problems, the player's registrations were cleared!");
						reason = "Null Doomsday registration.";
						list.clear();
						break;
					}

					if (TragicNewConfig.allowCombinationDoomsday)
					{
						if (flag && combinations.containsValue(effect.dday))
						{
							if (effect.isActive && !effect.isInstant)
							{
								if (!mp.capabilities.isCreativeMode && !effect.isCommandActivated) effect.dday.applyDoomAndCooldown(effect.doom, effect.iterations);
							}
							list.clear();
							list.add(new DoomsdayEffect(combinationMap.get(effect.dday).getDoomId(), effect.doom, effect.isCommandActivated));
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
						if (effect.isInstant)
						{
							if (!mp.capabilities.isCreativeMode && !effect.isCommandActivated) effect.dday.applyDoomAndCooldown(effect.doom);
						}
						else
						{
							if (!mp.capabilities.isCreativeMode && !effect.isCommandActivated) effect.dday.applyDoomAndCooldown(effect.doom, effect.iterations);
						}

						list.remove(effect);
						reason = "No more Doomsday effects.";
					}
				}

				if (list.isEmpty()) clearPlayerFromRegistry(mp.getCommandSenderName(), reason);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerDisconnect(ServerDisconnectionFromClientEvent event)
	{
		if (event.handler instanceof NetHandlerPlayServer)
		{
			NetHandlerPlayServer net = (NetHandlerPlayServer) event.handler;

			if (net.playerEntity != null && playerMap.containsKey(net.playerEntity.getCommandSenderName()))
			{
				clearPlayerFromRegistry(net.playerEntity.getCommandSenderName(), "Disconnected from server.");
			}
		}
	}
}
