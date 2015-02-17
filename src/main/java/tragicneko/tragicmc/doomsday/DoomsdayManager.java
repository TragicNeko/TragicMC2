package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerDisconnectionFromClientEvent;

public class DoomsdayManager {

	public static Logger logger = LogManager.getLogger(TragicMC.MODID + "/Doomsday Manager");
	private static Map<UUID, ArrayList<DoomsdayEffect>> playerMap = new HashMap();

	private static Set<Doomsday> combinations = Sets.newConcurrentHashSet();

	static
	{
		combinations.add(Doomsday.Purge);
		combinations.add(Doomsday.Titanfall);
		combinations.add(Doomsday.Marionette);
		combinations.add(Doomsday.NatureDrain);
		combinations.add(Doomsday.Permafrost);
		combinations.add(Doomsday.FireRain);
		combinations.add(Doomsday.RapidFire);
	}

	public synchronized static void registerDoomsdayEffect(UUID playerID, DoomsdayEffect effect)
	{
		if (playerMap.containsKey(playerID))
		{
			try
			{
				ArrayList<DoomsdayEffect> list = playerMap.get(playerID);

				for (int i = 0; i < list.size(); i++)
				{
					DoomsdayEffect effect2 = list.get(i);
					if (effect2.dday == effect.dday) 
					{
						logger.warn("User with UUID of " + playerID + " attempted to register a new Doomsday effect for a Doomsday they already have active, ignoring registration.");
						return;
					}
				}
				list.add(effect);
			}
			catch (ConcurrentModificationException e)
			{
				logger.error("Error caught while attempting to register a new Doomsday effect.", e);
				return;
			}
		}
		else
		{
			try
			{
				ArrayList<DoomsdayEffect> list = new ArrayList();
				list.add(effect);
				playerMap.put(playerID, list);
			}
			catch (ConcurrentModificationException e)
			{
				logger.error("Error caught while attempting to register a new Doomsday effect.", e);
				return;
			}
		}
	}

	public synchronized static void clearRegistry()
	{
		try
		{
			playerMap.clear();
		}
		catch (Exception e)
		{
			logger.error("Error caught while clearing the playerMap", e);
		}
	}

	public synchronized static void clearPlayerFromRegistry(UUID playerID, String reason)
	{
		try
		{
			if (playerMap.containsKey(playerID))
			{
				playerMap.remove(playerID);
				if (reason != null)
				{
					logger.info("Registry removed registration for player with UUID of " + playerID + ", reason: " + reason);
				}
			}
			else
			{
				logger.error("Attempted to remove a player that was not registered in the map, there is a problem somewhere.");
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
			Iterator<UUID> ite = set.iterator();
			String reason = null;

			while (ite.hasNext())
			{
				ArrayList<DoomsdayEffect> list = playerMap.get(ite.next());
				DoomsdayEffect effect = null;
				DoomsdayEffect temp = null;
				reason = null;

				boolean flag = false;

				if (list.size() == 0) reason = "No Doomsday effects registered.";

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

					if (TragicConfig.allowCombinationDoomsday)
					{
						if (flag && temp != null && temp.dday.getCombination() == effect.dday)
						{
							if (temp.isActive && !temp.isInstant)
							{
								if (!effect.player.capabilities.isCreativeMode && !effect.isCommandActivated) temp.dday.applyDoomCost(temp.doom);
								TragicMC.logInfo("Instant Dday used for the Combination should've applied doom cost on use");
							}
							
							list.clear();
							
							try
							{
								list.add(new DoomsdayEffect(effect.dday.getCombination().doomID, effect.doom, effect.isCommandActivated).inheritCooldown(temp, effect));
							}
							catch (NullPointerException e)
							{
								e.printStackTrace();
								logger.error("Combination doomsdays had a null Combination, report this!");
								break;
							}
							break;
						}

						if (combinations.contains(effect.dday))
						{
							flag = true;
							temp = effect;
						}
					}

					effect.onDoomsdayUpdate();

					if (!effect.isActive && effect.isInstant || effect.dday instanceof IExtendedDoomsday && effect.timeBetweenUpdates == effect.dday.waitTime)
					{
						if (!effect.player.capabilities.isCreativeMode && !effect.isCommandActivated && effect.sneakTicks == 0) effect.dday.applyDoomCost(effect.doom);
					}

					if (!effect.isActive)
					{
						if (!effect.player.capabilities.isCreativeMode && !effect.isCommandActivated && !effect.player.isPotionActive(TragicPotion.Convergence))
						{
							if (effect.dday.doomsdayType == EnumDoomType.COMBINATION)
							{
								effect.dday.applyCooldown(effect.doom, effect.iterations, effect.inheritedCooldown);
							}
							else
							{
								effect.dday.applyCooldown(effect.doom, effect.iterations);
							}
						}
						list.remove(effect);
						reason = "No more Doomsday effects.";
					}
				}

				if (list.isEmpty()) clearPlayerFromRegistry(effect.player.getUniqueID(), reason);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerDisconnect(ServerDisconnectionFromClientEvent event)
	{
		if (event.handler instanceof NetHandlerPlayServer && !playerMap.isEmpty())
		{
			NetHandlerPlayServer net = (NetHandlerPlayServer) event.handler;
			if (net.playerEntity == null) return;

			if (playerMap.containsKey(net.playerEntity.getUniqueID()))
			{
				clearPlayerFromRegistry(net.playerEntity.getUniqueID(), "Disconnected from server.");
			}
		} 
	}
}
