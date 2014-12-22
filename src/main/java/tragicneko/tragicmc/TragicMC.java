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

import net.minecraft.potion.Potion;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tragicneko.tragicmc.client.CommonProxy;
import tragicneko.tragicmc.commands.DoomCommand;
import tragicneko.tragicmc.commands.DoomsdayCoomand;
import tragicneko.tragicmc.dimension.SynapseWorldProvider;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.doomsday.DoomsdayManager;
import tragicneko.tragicmc.events.ChallengeItemEvents;
import tragicneko.tragicmc.events.DoomEvents;
import tragicneko.tragicmc.events.DropEvents;
import tragicneko.tragicmc.events.DynamicHealthScaling;
import tragicneko.tragicmc.events.EnchantmentEvents;
import tragicneko.tragicmc.events.MiscEvents;
import tragicneko.tragicmc.events.NewAmuletEvents;
import tragicneko.tragicmc.events.PotionEvents;
import tragicneko.tragicmc.events.RespawnDoomEvents;
import tragicneko.tragicmc.events.VanillaChangingEvents;
import tragicneko.tragicmc.network.MessageAmulet;
import tragicneko.tragicmc.network.MessageAttack;
import tragicneko.tragicmc.network.MessageDoom;
import tragicneko.tragicmc.network.MessageFlight;
import tragicneko.tragicmc.network.MessageGui;
import tragicneko.tragicmc.network.MessageHandlerAmulet;
import tragicneko.tragicmc.network.MessageHandlerAttack;
import tragicneko.tragicmc.network.MessageHandlerDoom;
import tragicneko.tragicmc.network.MessageHandlerFlight;
import tragicneko.tragicmc.network.MessageHandlerGui;
import tragicneko.tragicmc.network.MessageHandlerUseDoomsday;
import tragicneko.tragicmc.network.MessageUseDoomsday;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;
import tragicneko.tragicmc.worldgen.DarkShieldWorldGen;
import tragicneko.tragicmc.worldgen.DimensionLayerWorldGen;
import tragicneko.tragicmc.worldgen.DimensionOreWorldGen;
import tragicneko.tragicmc.worldgen.FlowerWorldGen;
import tragicneko.tragicmc.worldgen.InvertedSpikeWorldGen;
import tragicneko.tragicmc.worldgen.IsleWorldGen;
import tragicneko.tragicmc.worldgen.NetherOreWorldGen;
import tragicneko.tragicmc.worldgen.OverworldOreWorldGen;
import tragicneko.tragicmc.worldgen.RuggedTerrainWorldGen;
import tragicneko.tragicmc.worldgen.StarCrystalWorldGen;
import tragicneko.tragicmc.worldgen.StructureWorldGen;
import tragicneko.tragicmc.worldgen.VoidPitWorldGen;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = TragicMC.MODID, name = TragicMC.MODNAME, version = TragicMC.VERSION, acceptedMinecraftVersions = "[1.7.10]")
public class TragicMC
{
	public static final String MODNAME = "TragicMC 2";
	public static final String MODID = "TragicMC";
	public static final String VERSION = "2.40.1770 Beta";

	@Instance(TragicMC.MODID)
	public static TragicMC instance;

	@SidedProxy(clientSide = "tragicneko.tragicmc.client.ClientProxy", serverSide = "tragicneko.tragicmc.client.CommonProxy")
	public static CommonProxy proxy;

	public static SimpleNetworkWrapper net;
	private static final Logger logger = LogManager.getLogger(TragicMC.MODID);

	public static final int idAmuletGui = 1;

	public static final Random rand = new Random();
	private static Configuration config;

	private static long time = 0L;
	public static boolean DEBUG = true;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logTime();

		config = null;
		config = new Configuration(event.getSuggestedConfigurationFile(), TragicMC.VERSION, true);
		TragicNewConfig.initialize();
		MinecraftForge.EVENT_BUS.register(new TragicNewConfig());

		logDuration("Configuration");

		if (TragicNewConfig.allowPotions)
		{
			if (Potion.potionTypes.length >= 64)
			{
				TragicPotions.load();
				MinecraftForge.EVENT_BUS.register(new PotionEvents());
			}
			else
			{
				TragicMC.logError("The potionType array was not set to an adequate amount, as a result potion effects are disabled now to prevent a crash.");
				TragicNewConfig.disablePotions();
			}
		}

		if (TragicNewConfig.allowEnchantments) TragicEnchantments.load();
		if (TragicNewConfig.allowEnchantments) MinecraftForge.EVENT_BUS.register(new EnchantmentEvents());

		logDuration("Potions and Enchantments");

		TragicTabs.load();
		TragicBlocks.load();
		logDuration("Blocks");
		TragicItems.load();
		logDuration("Items");
		if (TragicNewConfig.allowPotions) TragicPotions.setPotionIcons();
		if (!TragicNewConfig.mobsOnly) TragicRecipes.load();

		if (TragicNewConfig.allowAmulets) MinecraftForge.EVENT_BUS.register(new NewAmuletEvents());
		MinecraftForge.EVENT_BUS.register(new MiscEvents());
		MinecraftForge.EVENT_BUS.register(new ChallengeItemEvents());

		if (TragicNewConfig.allowDoom)
		{
			MinecraftForge.EVENT_BUS.register(new DoomEvents());
			FMLCommonHandler.instance().bus().register(new RespawnDoomEvents());
		}
		logDuration("Events 1");

		if (TragicNewConfig.allowMobs)
		{
			TragicEntities.load();
			MinecraftForge.EVENT_BUS.register(new DynamicHealthScaling());
		}

		logDuration("Entities");

		if (TragicNewConfig.allowChallengeScrolls) TragicItems.initializeChallengeItem();

		MinecraftForge.EVENT_BUS.register(new DropEvents());
		logDuration("Events 2");

		proxy.registerRenders();
		
		logDuration("Proxy Registrations");

		net = new SimpleNetworkWrapper(TragicMC.MODID);
		net.registerMessage(MessageHandlerDoom.class, MessageDoom.class, 0, Side.CLIENT);
		net.registerMessage(MessageHandlerAmulet.class, MessageAmulet.class, 1, Side.CLIENT);
		net.registerMessage(MessageHandlerGui.class, MessageGui.class, 2, Side.SERVER);
		net.registerMessage(MessageHandlerUseDoomsday.class, MessageUseDoomsday.class, 3, Side.SERVER);
		net.registerMessage(MessageHandlerFlight.class, MessageFlight.class, 4, Side.CLIENT);
		net.registerMessage(MessageHandlerAttack.class, MessageAttack.class, 5, Side.SERVER);

		logDuration("Network Handlers");

		if (TragicNewConfig.allowDimension)
		{
			if (!DimensionManager.isDimensionRegistered(TragicNewConfig.dimensionID))
			{
				DimensionManager.registerProviderType(TragicNewConfig.providerID, TragicWorldProvider.class, TragicNewConfig.keepDimensionLoaded);
				DimensionManager.registerDimension(TragicNewConfig.dimensionID, TragicNewConfig.providerID);
			}
			else
			{
				int id = DimensionManager.getNextFreeDimId();
				TragicNewConfig.dimensionID = id;
				TragicNewConfig.providerID = id;
				DimensionManager.registerProviderType(TragicNewConfig.providerID, TragicWorldProvider.class, TragicNewConfig.keepDimensionLoaded);
				DimensionManager.registerDimension(TragicNewConfig.dimensionID, TragicNewConfig.providerID);
			}

			if (!DimensionManager.isDimensionRegistered(TragicNewConfig.synapseID))
			{
				DimensionManager.registerProviderType(TragicNewConfig.synapseProviderID, SynapseWorldProvider.class, TragicNewConfig.keepDimensionLoaded);
				DimensionManager.registerDimension(TragicNewConfig.synapseID, TragicNewConfig.synapseProviderID);
			}
			else
			{
				int id = DimensionManager.getNextFreeDimId();
				TragicNewConfig.synapseID = id;
				TragicNewConfig.synapseProviderID = id;
				DimensionManager.registerProviderType(TragicNewConfig.synapseProviderID, SynapseWorldProvider.class, TragicNewConfig.keepDimensionLoaded);
				DimensionManager.registerDimension(TragicNewConfig.synapseID, TragicNewConfig.synapseProviderID);
			}

			TragicBiomes.load();
			MinecraftForge.ORE_GEN_BUS.register(new MiscEvents());
		}

		logDuration("Dimension Registrations");

		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
		if (TragicNewConfig.allowDoomsdays) FMLCommonHandler.instance().bus().register(new DoomsdayManager());
		DoomsdayManager.clearRegistry();

		logDuration("Events 3");
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		logTime();

		if (TragicNewConfig.allowVanillaChanges) MinecraftForge.EVENT_BUS.register(new VanillaChangingEvents());
		if (TragicNewConfig.allowOverworldOreGen) GameRegistry.registerWorldGenerator(new OverworldOreWorldGen(), 1);
		if (TragicNewConfig.allowNetherOreGen) GameRegistry.registerWorldGenerator(new NetherOreWorldGen(), 2);
		GameRegistry.registerWorldGenerator(new FlowerWorldGen(), 3);

		if (TragicNewConfig.allowDimension)
		{
			FlowerWorldGen.allowedBiomes.addAll(TragicBiomes.paintedBiomes);
			FlowerWorldGen.allowedBiomes.addAll(TragicBiomes.ashenBiomes);

			if (TragicNewConfig.allowVoidPitGen) GameRegistry.registerWorldGenerator(new VoidPitWorldGen(), 4);
			if (TragicNewConfig.allowDarkStoneVariantGen) GameRegistry.registerWorldGenerator(new DimensionLayerWorldGen(), 5);
			GameRegistry.registerWorldGenerator(new StarCrystalWorldGen(), 6); //for the starlit prarie
			GameRegistry.registerWorldGenerator(new CustomSpikesWorldGen(), 7); //for the decaying wasteland and tainted spikes
			GameRegistry.registerWorldGenerator(new RuggedTerrainWorldGen(), 8); //for the ashen badlands and tainted scarlands
			GameRegistry.registerWorldGenerator(new DarkShieldWorldGen(), 9); //for the ashen badlands and decaying wasteland
			GameRegistry.registerWorldGenerator(new IsleWorldGen(), 10); //for the tainted isles
			GameRegistry.registerWorldGenerator(new InvertedSpikeWorldGen(), 11); //For the tainted scarlands
			GameRegistry.registerWorldGenerator(new DimensionOreWorldGen(), 19);
		}

		if (TragicNewConfig.allowStructureGen) GameRegistry.registerWorldGenerator(new StructureWorldGen(), 20);

		logDuration("WorldGen registration");
	}

	@EventHandler
	public void onServerLoad(FMLServerStartingEvent event)
	{
		if (TragicNewConfig.allowDoom) event.registerServerCommand(new DoomCommand());
		if (TragicNewConfig.allowDoomsdays) event.registerServerCommand(new DoomsdayCoomand());

		if (!event.getServer().isFlightAllowed())
		{
			TragicNewConfig.allowFlight = false;
			logWarning("Flight potion effect is disabled due to the server not allowing it. Change the option in your server.properties file if you want it enabled.");
		}
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
		time = System.currentTimeMillis();
	}
}
