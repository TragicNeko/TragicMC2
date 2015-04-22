/*
 * TragicMC 2 - Minecraft Mod that uses Minecraft Forge API
 * Copyright (C) 2014 TragicNeko
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package tragicneko.tragicmc;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tragicneko.tragicmc.client.CommonProxy;
import tragicneko.tragicmc.doomsday.DoomsdayManager;
import tragicneko.tragicmc.events.ServerTickEvents;
import tragicneko.tragicmc.util.LoreHelper;
import tragicneko.tragicmc.worldgen.FlowerWorldGen;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = TragicMC.MODID, name = TragicMC.MODNAME, version = TragicMC.VERSION, acceptedMinecraftVersions = TragicMC.ACCEPTED_VERSION, dependencies="required-after:Forge")
public class TragicMC
{
	public static final String MODNAME = "TragicMC 2";
	public static final String MODID = "TragicMC";
	public static final String VERSION = "2.43.2325 Beta";
	public static final String ACCEPTED_VERSION = "[1.7.10]";

	@Instance(TragicMC.MODID)
	private static TragicMC instance;

	@SidedProxy(clientSide = "tragicneko.tragicmc.client.ClientProxy", serverSide = "tragicneko.tragicmc.client.CommonProxy")
	public static CommonProxy proxy;

	public static SimpleNetworkWrapper net;
	private static final Logger logger = LogManager.getLogger(TragicMC.MODID);

	public static final int idAmuletGui = 1;

	public static final Random rand = new Random();
	private static Configuration config;

	private static long time = 0L;
	public static final boolean DEBUG = false;

	public static CreativeTabs Survival;
	public static CreativeTabs Creative;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logTime();

		config = null;
		config = new Configuration(event.getSuggestedConfigurationFile(), TragicMC.VERSION, true);
		TragicConfig.initialize();
		//MinecraftForge.EVENT_BUS.register(new TragicConfig()); //for the gui stuff, eventually I'll sit down and do this
		FMLCommonHandler.instance().bus().register(new ServerTickEvents());

		logDuration("Configuration");

		if (TragicConfig.allowPotions)
		{
			if (Potion.potionTypes.length >= 64)
			{
				TragicPotion.load();
				MinecraftForge.EVENT_BUS.register(new tragicneko.tragicmc.events.PotionEvents());
			}
			else
			{
				TragicMC.logError("The potionType array was not set to an adequate amount, as a result potion effects are disabled now to prevent any crashes.");
				TragicConfig.disablePotions();
			}
		}

		if (TragicConfig.allowEnchantments) TragicEnchantments.load();
		if (TragicConfig.allowEnchantments) MinecraftForge.EVENT_BUS.register(new tragicneko.tragicmc.events.EnchantmentEvents());

		logDuration("Potions and Enchantments");
		if (!TragicConfig.mobsOnly)
		{
			Survival = (new CreativeTabs("tragicMCSurvival") {
				@Override
				public Item getTabIconItem() {
					return TragicItems.AwakeningStone;
				}
			});
		}

		Creative = (new CreativeTabs("tragicMCCreative") {
			@Override
			public Item getTabIconItem() {
				return TragicItems.NekoNekoWand;
			}
		});

		TragicBlocks.load();
		logDuration("Blocks");
		TragicItems.load();
		//if (!TragicConfig.mobsOnly) LoreHelper.registerLoreJson(event.getModConfigurationDirectory()); TODO setup custom lores, add all of my lores to this so that people can remove them or modify them as they wish
		logDuration("Items");
		if (TragicConfig.allowPotions) TragicPotion.setPotionIcons();
		if (!TragicConfig.mobsOnly) TragicRecipes.load();

		if (TragicConfig.allowAmulets) MinecraftForge.EVENT_BUS.register(new tragicneko.tragicmc.events.AmuletEvents());
		if (!TragicConfig.mobsOnly) 
		{
			MinecraftForge.EVENT_BUS.register(new tragicneko.tragicmc.events.MiscEvents());
			if (TragicConfig.allowChallengeScrolls) MinecraftForge.EVENT_BUS.register(new tragicneko.tragicmc.events.ChallengeItemEvents());
		}

		if (TragicConfig.allowDoom)
		{
			MinecraftForge.EVENT_BUS.register(new tragicneko.tragicmc.events.DoomEvents());
			FMLCommonHandler.instance().bus().register(new tragicneko.tragicmc.events.RespawnDoomEvents());
		}
		logDuration("Events 1");

		if (TragicConfig.allowMobs)
		{
			TragicEntities.load();
			MinecraftForge.EVENT_BUS.register(new tragicneko.tragicmc.events.DynamicHealthScaling());
		}

		logDuration("Entities");

		if (TragicConfig.allowChallengeScrolls && !TragicConfig.mobsOnly) TragicItems.initializeChallengeItem();

		if (!TragicConfig.mobsOnly) MinecraftForge.EVENT_BUS.register(new tragicneko.tragicmc.events.DropEvents());
		logDuration("Events 2");

		if (TragicConfig.allowDimension)
		{
			if (DimensionManager.isDimensionRegistered(TragicConfig.dimensionID))
			{
				int id = DimensionManager.getNextFreeDimId();
				TragicConfig.dimensionID = id;
				TragicConfig.providerID = id;
			}
			
			DimensionManager.registerProviderType(TragicConfig.providerID, tragicneko.tragicmc.dimension.TragicWorldProvider.class, TragicConfig.keepDimensionLoaded);
			DimensionManager.registerDimension(TragicConfig.dimensionID, TragicConfig.providerID);

			if (DimensionManager.isDimensionRegistered(TragicConfig.synapseID))
			{
				int id = DimensionManager.getNextFreeDimId();
				TragicConfig.synapseID = id;
				TragicConfig.synapseProviderID = id;
			}

			DimensionManager.registerProviderType(TragicConfig.synapseProviderID, tragicneko.tragicmc.dimension.SynapseWorldProvider.class, TragicConfig.keepDimensionLoaded);
			DimensionManager.registerDimension(TragicConfig.synapseID, TragicConfig.synapseProviderID);

			TragicBiomes.load();
			MinecraftForge.ORE_GEN_BUS.register(new tragicneko.tragicmc.events.MiscEvents());
		}

		logDuration("Dimension Registrations");

		if (!TragicConfig.mobsOnly) NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
		if (TragicConfig.allowDoomsdays) FMLCommonHandler.instance().bus().register(new DoomsdayManager());
		DoomsdayManager.clearRegistry();

		logDuration("Events 3");

		if (TragicConfig.allowVanillaChanges) MinecraftForge.EVENT_BUS.register(new tragicneko.tragicmc.events.VanillaChangingEvents());
		if (TragicConfig.allowOverworldOreGen) GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.OverworldOreWorldGen(), 1);
		if (TragicConfig.allowNetherOreGen) GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.NetherOreWorldGen(), 2);
		if (!TragicConfig.mobsOnly) GameRegistry.registerWorldGenerator(new FlowerWorldGen(), 3);

		if (TragicConfig.allowDimension)
		{
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.PaintedClearing);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.PaintedForest);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.PaintedHills);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.PaintedPlains);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.AshenBadlands);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.AshenHills);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.AshenMountains);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.DarkForest);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.DarkForestHills);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.DarkMarsh);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.HallowedHills);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.HallowedForest);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.HallowedCliffs);
			FlowerWorldGen.allowedBiomes.add(TragicBiomes.HallowedPrarie);

			//TODO convert these to biome-unique decoration instead of having them called every chunk for every dimension for every biome
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.InvertedSpikeWorldGen(), 3); //For the tainted scarlands
			if (TragicConfig.allowVoidPitGen) GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.VoidPitWorldGen(), 4);
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.PitWorldGen(), 5); //Pits for some of the newer biomes
			if (TragicConfig.allowDarkStoneVariantGen) GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.DimensionLayerWorldGen(), 6);
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.StarCrystalWorldGen(), 7); //for the starlit prarie
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.CustomSpikesWorldGen(), 8); //for the decaying wasteland and tainted spikes
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.RuggedTerrainWorldGen(), 9); //for the ashen badlands and tainted scarlands
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.DarkShieldWorldGen(), 10); //for the ashen badlands and decaying wasteland
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.IsleWorldGen(), 11); //for the tainted isles
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.StringLightWorldGen(), 12); //For Hallowed biomes
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.ScorchedSurfaceWorldGen(), 13); //For the Scorched biomes
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.DimensionOreWorldGen(), 14);
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.CorrodedSurfaceWorldGen(), 15); //Corroded biomes
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.ExplosiveGasWorldGen(), 16); //Corroded biomes
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.FrozenSurfaceWorldGen(), 17); //Frozen biome generation
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.AerisWorldGen(), 24); //Aeris flower gen
		}

		if (TragicConfig.allowStructureGen) GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.StructureWorldGen(), 20);

		logDuration("WorldGen registration");

		net = new SimpleNetworkWrapper(TragicMC.MODID);
		net.registerMessage(tragicneko.tragicmc.network.MessageHandlerDoom.class, tragicneko.tragicmc.network.MessageDoom.class, 0, Side.CLIENT);
		net.registerMessage(tragicneko.tragicmc.network.MessageHandlerAmulet.class, tragicneko.tragicmc.network.MessageAmulet.class, 1, Side.CLIENT);
		net.registerMessage(tragicneko.tragicmc.network.MessageHandlerGui.class, tragicneko.tragicmc.network.MessageGui.class, 2, Side.SERVER);
		net.registerMessage(tragicneko.tragicmc.network.MessageHandlerUseDoomsday.class, tragicneko.tragicmc.network.MessageUseDoomsday.class, 3, Side.SERVER);
		net.registerMessage(tragicneko.tragicmc.network.MessageHandlerFlight.class, tragicneko.tragicmc.network.MessageFlight.class, 4, Side.CLIENT);
		net.registerMessage(tragicneko.tragicmc.network.MessageHandlerAttack.class, tragicneko.tragicmc.network.MessageAttack.class, 5, Side.SERVER);
		net.registerMessage(tragicneko.tragicmc.network.MessageHandlerSpawnParticle.class, tragicneko.tragicmc.network.MessageParticle.class, 6, Side.CLIENT);

		logDuration("Network Handlers");
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		logTime();

		proxy.registerRenders();
		logDuration("Proxy Registrations");
	}

	@EventHandler
	public void onServerLoad(FMLServerStartingEvent event)
	{
		if (TragicConfig.allowDoom) event.registerServerCommand(new tragicneko.tragicmc.commands.DoomCommand());
		if (TragicConfig.allowDoomsdays) event.registerServerCommand(new tragicneko.tragicmc.commands.DoomsdayCoomand());

		if (!event.getServer().isFlightAllowed())
		{
			TragicConfig.allowFlight = false;
			logWarning("Flight potion effect is disabled due to the server not allowing it. Change the option in your server.properties file if you want it enabled.");
		}
	}

	@EventHandler
	public void mappings(FMLMissingMappingsEvent event)
	{
		//If I change modIDs I'll set this up properly
		TragicMC.logInfo("Mapping event received.");
	}

	public static void doPotionReflection()
	{
		try
		{
			Potion[] potionTypes;
			Field f = ReflectionHelper.findField(Potion.class, "potionTypes", "field_76425_a");

			if (f == null) throw new Throwable();

			Field modfield = Field.class.getDeclaredField("modifiers");
			modfield.setAccessible(true);
			modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
			potionTypes = (Potion[])f.get(null);

			if (potionTypes.length <= 64)
			{
				final Potion[] newPotionTypes = new Potion[256];
				System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
				f.set(null, newPotionTypes);
			}
			else
			{
				logWarning("potionTypes[]'s array length was " + Potion.potionTypes.length + ", so it is assumed that it was previously reflected to an adequate amount.");
			}
		}
		catch (Throwable t)
		{
			logError("There was an error during Potion array reflection, this may be due to obfuscation and could have unintended side effects.", t);
		}
	}

	public static void logError(String s)
	{
		logger.error(s);
	}

	public static void logError(String s, Exception e)
	{
		logger.error(s, e);
	}

	public static void logError(String s, Throwable t)
	{
		logger.error(s, t);
	}

	public static void logInfo(String s)
	{
		if (!DEBUG) return;
		logger.info(s);
	}

	public static void logWarning(String s)
	{
		logger.warn(s);
	}

	public static Configuration getConfig()
	{
		return config;
	}

	public static void logTime()
	{
		time = System.currentTimeMillis();
	}

	public static void logDuration(String sectionName)
	{
		if (!DEBUG) return;

		long l = System.currentTimeMillis() - time;
		logInfo("Time to complete section (" + sectionName + ") was " + l + " ms.");
		logTime();
	}

	public static TragicMC getInstance() {
		return instance;
	}
}
