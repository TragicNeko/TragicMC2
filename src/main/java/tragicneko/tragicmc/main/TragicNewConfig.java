package tragicneko.tragicmc.main;

import static tragicneko.tragicmc.TragicMC.config;
import tragicneko.tragicmc.TragicMC;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class TragicNewConfig {

	public static boolean mobsOnly;
	public static String textureRes;
	private static int resolution;

	private static String catMaster = "Master Configs";
	private static String catBlanket = "Blanket Configs";
	private static String catAchievement = "Achievements";
	private static String catAmulet = "Amulets";
	private static String catDimension = "Dimension";
	private static String catDoom = "Doom";
	private static String catEnchant = "Enchantments";
	private static String catMobs = "Mobs";
	private static String catPotion = "Potions";
	private static String catVanilla = "Vanilla Changes";
	private static String catWorldGen = "WorldGen";
	private static String catMisc = "Miscellaneous";

	private static boolean[] blanketConfigs = new boolean[9];
	public static boolean allowAchievements, allowAmulets, allowDimension, allowDoom, allowEnchantments, allowMobs, allowPotions, allowVanillaChanges, allowWorldGen;

	private static boolean[] blanketAmulet = new boolean[7];
	public static boolean allowNormalAmulets, allowCursedAmulets, allowEpicAmulets, allowAmuletLeveling, allowAmuletCrafting, shouldUnlockAmuletSlots, allowAmuletKillRecharge;
	private static int[] amuletInts = new int[3];
	public static int maxAmuletSlots, overallAmuletRarity, amuletReleaseRarity;
	private static boolean[] normalAmuletConfigs = new boolean[16];
	public static boolean amuPeace, amuYeti, amuClaymation, amuChicken, amuBlacksmith, amuCreeper, amuZombie, amuSkeleton;
	private static boolean[] cursedAmuletConfigs = new boolean[8];
	public static boolean amuKitsune, amuMartyr, amuHornet, amuApis, amuSunken;
	private static boolean[] epicAmuletConfigs = new boolean[4];
	public static boolean amuTime;

	public static boolean keepDimensionLoaded;
	private static int[] dimensionIDs = new int[2];
	public static int dimensionID, providerID;
	private static int[] biomeIDs = new int[48];
	public static int idDecayingHills, idDecayingValley, idDecayingWasteland, idDecayingMountains, idPaintedForest, idPaintedPlains, idPaintedHills, idPaintedClearing;
	public static int idAshenMountains, idAshenHills, idAshenBadlands, idStarlitPrarie, idStarlitPlateaus, idStarlitCliffs, idStarlitLowlands, idTaintedSpikes;
	public static int idTaintedArchipelago, idTaintedRises, idTaintedScarlands, idTaintedIsles;

	private static boolean[] blanketDoom = new boolean[16];
	public static boolean allowDoomsdays, allowInfluenceDoomsday, allowCrisisDoomsday, allowOverflowDoomsday, allowWorldShaperDoomsday, allowUltimateDoomsday, allowNonDoomsdayAbilities;
	public static boolean shouldDoomLimitIncrease, allowConsumeRefill, allowDoomPainRecharge, allowNaturalRecharge, allowCrucialMoments, allowBacklash, allowCooldown;
	public static boolean allowCooldownDefuse, showDoomGui;
	private static int[] doomInts = new int[8];
	public static int maxDoomAmount, doomRechargeRate, doomConsumeRarity, cooldownDefuseRarity, consumeRefillAmount, defuseRefillAmount, backlashChance, crucialMomentChance;

	private static boolean[] blanketEnchant = new boolean[2];
	public static boolean allowWeaponEnchants, allowArmorEnchants;
	private static boolean[] weaponEnchantConfigs = new boolean[12];
	public static boolean allowDecay, allowSlay, allowAbsolve, allowVampirism, allowLeech, allowConsume, allowDistract, allowMultiply, allowCombustion;
	private static boolean[] armorEnchantConfigs = new boolean[12];
	public static boolean allowDeathTouch, allowIgnition, allowToxicity, allowParalysis, allowElasticity, allowAgility;
	private static int[] weaponEnchantIDs = new int[12];
	public static int idDecay, idSlay, idAbsolve, idVampirism, idLeech, idConsume, idDistract, idMultiply, idCombustion;
	private static int[] armorEnchantIDs = new int[12];
	public static int idDeathTouch, idIgnition, idToxicity, idParalysis, idElasticity, idAgility;

	private static boolean[] blanketMob = new boolean[8];
	public static boolean allowNormalMobs, allowMiniBosses, allowBosses, allowBossNaturalSpawns, allowExtraBossLoot, allowVictoryBuffs, allowCorruptionDamage, allowMobTransformation;
	private static boolean[] mobConfigs = new boolean[32];
	public static boolean allowJabba, allowJanna, allowPlague, allowGragul, allowMinotaur, allowInkling, allowRagr, allowPumpkinhead, allowTragicNeko, allowTox, allowPox;
	public static boolean allowCryse, allowStarCryse, allowNorVox, allowStarVox, allowPirah, allowLavaPirah, allowStin, allowStinBaby, allowWisp, allowAbomination;
	private static int[] mobsSC = new int[32];
	public static int jabbaSC, jannaSC, plagueSC, gragulSC, minotaurSC, inklingSC, ragrSC, pumpkinheadSC, tragicNekoSC, toxSC, poxSC, cryseSC, starCryseSC, norVoxSC, starVoxSC;
	public static int pirahSC, lavaPirahSC, stinSC, stinBabySC, wispSC, abominationSC;
	private static boolean[] miniBossConfigs = new boolean[16];
	public static boolean allowJarra, allowKragul, allowMagmox, allowMegaCryse, allowVoxStellarum, allowGreaterStin, allowStinKing, allowStinQueen;
	private static int[] miniBossSC = new int[16];
	public static int jarraSC, kragulSC, magmoxSC, megaCryseSC, voxStellarumSC, greaterStinSC, stinKingSC, stinQueenSC;
	private static boolean[] bossConfigs = new boolean[12];
	public static boolean allowApis, allowDeathReaper, allowKitsune, allowYeti, allowTimeController, allowPolaris;
	private static int[] bossSC = new int[12];
	public static int apisSC, deathReaperSC, kitsuneSC, yetiSC, timeControllerSC, polarisSC;
	private static int[] mobInts = new int[3];
	public static int commonDropRate, rareDropRate, mobTransformationChance;

	private static boolean[] blanketPotion = new boolean[2];
	public static boolean allowPositivePotions, allowNegativePotions;
	private static boolean[] positivePotionConfigs = new boolean[8];
	public static boolean allowFlight, allowAquaSuperiority, allowImmunity, allowResurrection, allowHarmony, allowInvulnerability, allowClarity;
	private static boolean[] negativePotionConfigs = new boolean[8];
	public static boolean allowCorruption, allowDisorientation, allowStun, allowFear, allowMalnourish, allowCripple, allowSubmission, allowInhibit;
	private static int[] positivePotionIDs = new int[8];
	public static int idFlight, idAquaSuperiority, idImmunity, idResurrection, idHarmony, idInvulnerability, idClarity;
	private static int[] negativePotionIDs = new int[8];
	public static int idCorruption, idDisorientation, idStun, idFear, idMalnourish, idCripple, idSubmission, idInhibit;

	private static boolean[] blanketVanillaChanges = new boolean[12];
	public static boolean allowVanillaMobBuffs, allowExtraMobEffects, allowAnimalRetribution, allowMobModdedArmorAndEnchants, allowRespawnPunishment, allowExtraExplosiveEffects;
	public static boolean allowBabySpawns, allowExtraOverworldFlowers, allowOverworldSilverfishGen, allowNetherOreGen, allowOverworldOreGen, allowQuicksandGen;
	private static int[] vanillaInts = new int[7];
	public static int rubyOreRarity, sapphireOreRarity, mercuryOreRarity, tungstenOreRarity, quicksandGenRarity, drudgeGenRarity, silverfishGenRarity;

	private static boolean[] blanketWorldGen = new boolean[6];
	public static boolean allowVoidPitGen, allowLargeSpikeGen, allowDarkStoneVariantGen, allowStructureGen, allowNonBossStructureGen, allowBossStructureGen;
	private static int[] worldGenInts = new int[12];
	public static int voidPitRarity, largeSpikeRarity, starCrystalRarity, structureOverallRarity, apisTempleRarity, desertTowerRarity, deathCircleRarity, obsidianCavernRarity;
	public static int kitsuneDenRarity, celestialTempleRarity, timeAltarRarity, yetiRavineRarity;
	
	private static boolean[] miscConfigs = new boolean[8];
	public static boolean allowRandomWeaponLore;

	/**
	 * Initializes the start of the configuration file, should only be called once when the mod is loading
	 * @param event
	 */
	public static void initialize()
	{
		config.load();

		mobsOnly = config.get(catMaster, "isMobsOnly", false).getBoolean(false);
		resolution = config.get(catMaster, "textureResolution", 16).getInt(16);

		config.addCustomCategoryComment(catMaster, "These change all other options and may even set specific variables, items are never disabled at any point");

		int mapping = 0;
		int i = 0;

		//Blanket options
		blanketConfigs[mapping++] = false; //(config.get(catBlanket, "allowAchievements", true).getBoolean(true)); //these aren't set up yet
		blanketConfigs[mapping++] = (config.get(catBlanket, "allowAmulets", true).getBoolean(true));
		blanketConfigs[mapping++] = (config.get(catBlanket, "allowDimension", true).getBoolean(true));
		blanketConfigs[mapping++] = (config.get(catBlanket, "allowDoom", true).getBoolean(true));
		blanketConfigs[mapping++] = (config.get(catBlanket, "allowEnchantments", true).getBoolean(true));
		blanketConfigs[mapping++] = (config.get(catBlanket, "allowMobs", true).getBoolean(true));
		blanketConfigs[mapping++] = (config.get(catBlanket, "allowPotions", true).getBoolean(true));
		blanketConfigs[mapping++] = (config.get(catBlanket, "allowVanillaChanges", true).getBoolean(true));
		blanketConfigs[mapping++] = (config.get(catBlanket, "allowWorldgen", true).getBoolean(true));

		config.addCustomCategoryComment(catBlanket, "These toggle all options in their respective categories off.");

		//Amulets
		mapping = 0;
		blanketAmulet[mapping++] = (config.get(catAmulet, "allowNormalAmuletEffects", true).getBoolean(true));
		blanketAmulet[mapping++] = (config.get(catAmulet, "allowCursedAmuletEffects", true).getBoolean(true));
		blanketAmulet[mapping++] = (config.get(catAmulet, "allowEpicAmuletEffects", true).getBoolean(true));
		blanketAmulet[mapping++] = (config.get(catAmulet, "allowAmuletLeveling", true).getBoolean(true));
		blanketAmulet[mapping++] = (config.get(catAmulet, "allowCraftingWithRawMaterials", true).getBoolean(true));
		blanketAmulet[mapping++] = (config.get(catAmulet, "requireUnlockAmuletSlots", true).getBoolean(true));
		blanketAmulet[mapping++] = (config.get(catAmulet, "allowToughKillRecharge", true).getBoolean(true));

		amuletInts[0] = MathHelper.clamp_int((config.get(catAmulet, "maxAmuletSlots", 3).getInt(3)), 1, 3);
		amuletInts[1] = MathHelper.clamp_int(config.get(catAmulet, "overallAmuletRarity", 5).getInt(5), 3, 250);
		amuletInts[2] = MathHelper.clamp_int(config.get(catAmulet, "amuletReleaseRarity", 5).getInt(5), 3, 250);

		mapping = 0;
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectPeace", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectYeti", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectClaymation", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectChicken", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectBlacksmith", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectCreeper", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectZombie", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectSkeleton", true).getBoolean(true));

		for (i = 0; i + mapping < normalAmuletConfigs.length; i++)
		{
			normalAmuletConfigs[i + mapping] = false;
		}

		mapping = 0;
		cursedAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectKitsune", true).getBoolean(true));
		cursedAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectMartyr", true).getBoolean(true));
		cursedAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectHornet", true).getBoolean(true));
		cursedAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectApis", true).getBoolean(true));
		cursedAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectSunken", true).getBoolean(true));

		for (i = 0; i + mapping < cursedAmuletConfigs.length; i++)
		{
			cursedAmuletConfigs[i + mapping] = false;
		}

		mapping = 0;
		epicAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectTime", true).getBoolean(true));

		for (i = 0; i + mapping < epicAmuletConfigs.length; i++)
		{
			epicAmuletConfigs[i + mapping] = false;
		}

		config.addCustomCategoryComment(catAmulet, "Disable specific amulet effects, does not remove the Amulets from the game.");

		//Dimension
		keepDimensionLoaded = (config.get(catDimension, "keepDimensionLoaded", false).getBoolean(false));
		dimensionIDs[0] = (config.get(catDimension, "dimensionID", DimensionManager.getNextFreeDimId()).getInt(DimensionManager.getNextFreeDimId()));
		dimensionIDs[1] = (config.get(catDimension, "providerID", dimensionIDs[0]).getInt(dimensionIDs[0]));

		mapping = 0;
		biomeIDs[mapping++] = (config.get(catDimension, "biomeDecayingHillsID", getOpenIDForBiome(90)).getInt(getOpenIDForBiome(90)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeDecayingValleyID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeDecayingWastelandID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeDecayingMountainsID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomePaintedForestID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomePaintedPlainsID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomePaintedHillsID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomePaintedClearingID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeAshenMountainsID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeAshenHillsID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeAshenBadlandsID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeStarlitPrarieID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeStarlitPlateausID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeStarlitCliffsID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeStarlitLowlandsID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeTaintedSpikesID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeTaintedArchipelagoID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeTaintedRisesID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeTaintedScarlandsID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeTaintedIslesID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));

		config.addCustomCategoryComment(catDimension, "Set the various biome IDs in the Dimension, including the Dimension's own ID, also set if the Dimension should stay loaded.");

		mapping = 0;
		blanketDoom[mapping++] = (config.get(catDoom, "allowDoomsdays", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowInfluenceDoomsdays", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowCrisisDoomsdays", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowOverflowDoomsdays", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowWorldShaperDoomsdays", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowUltimateDoomsdays", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowNonDoomsdayAbilities", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "requireDoomConsumeUse", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowDoomConsumeRefill", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowDoomPainRecharge", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowDoomNaturalRecharge", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowCrucialMoments", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowBacklash", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowCooldown", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowCooldownDefuse", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "showDoomGui", true).getBoolean(true));

		mapping = 0;
		doomInts[mapping++] = MathHelper.clamp_int(config.get(catDoom, "maxDoomAmount", 500).getInt(500), 100, 1000);
		doomInts[mapping++] = MathHelper.clamp_int(config.get(catDoom, "doomRechargeRate", 1).getInt(1), 1, 4);
		doomInts[mapping++] = MathHelper.clamp_int(config.get(catDoom, "doomConsumeRarity", 3).getInt(3), 3, 250);
		doomInts[mapping++] = MathHelper.clamp_int(config.get(catDoom, "cooldownDefuseRarity", 5).getInt(5), 3, 250);
		doomInts[mapping++] = MathHelper.clamp_int(config.get(catDoom, "doomConsumeRefillAmount", 50).getInt(50), 1, 100);
		doomInts[mapping++] = MathHelper.clamp_int(config.get(catDoom, "cooldownDefuseRefillAmount", 30).getInt(30), 1, 100);
		doomInts[mapping++] = MathHelper.clamp_int(config.get(catDoom, "backlashChance", 5).getInt(5), 1, 100);
		doomInts[mapping++] = MathHelper.clamp_int(config.get(catDoom, "crucialMomentChance", 5).getInt(5), 1, 100);

		config.addCustomCategoryComment(catDoom, "Set various aspects of Doom and Doomsdays, refill amounts scale to total doom amount");

		blanketEnchant[0] = (config.get(catEnchant, "allowWeaponEnchantments", true).getBoolean(true));
		blanketEnchant[1] = (config.get(catEnchant, "allowArmorEnchantments", true).getBoolean(true));

		mapping = 0;
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "decayID", getOpenIDForEnchant(120)).getInt(getOpenIDForEnchant(120)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "decayAllow", true).getBoolean(true));
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "slayID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "slayAllow", true).getBoolean(true));
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "absolveID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "absolveAllow", true).getBoolean(true));
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "vampirismID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "vampirismAllow", true).getBoolean(true));
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "leechID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "leechAllow", true).getBoolean(true));
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "consumeID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "consumeAllow", true).getBoolean(true));
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "distractID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "distractAllow", true).getBoolean(true));
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "multiplyID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "multiplyAllow", true).getBoolean(true));
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "combustionID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "combustionAllow", true).getBoolean(true));

		int temp = mapping - 1;

		for (i = 0; i + mapping < weaponEnchantConfigs.length; i++)
		{
			weaponEnchantConfigs[i + mapping] = false;
		}

		mapping = 0;
		armorEnchantIDs[mapping] = (config.get(catEnchant, "deathTouchID", getOpenIDForEnchant(weaponEnchantIDs[temp] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[temp] + 1)));
		armorEnchantConfigs[mapping++] = (config.get(catEnchant, "deathTouchAllow", true).getBoolean(true));
		armorEnchantIDs[mapping] = (config.get(catEnchant, "ignitionID", getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)));
		armorEnchantConfigs[mapping++] = (config.get(catEnchant, "ignitionAllow", true).getBoolean(true));
		armorEnchantIDs[mapping] = (config.get(catEnchant, "toxicityID", getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)));
		armorEnchantConfigs[mapping++] = (config.get(catEnchant, "toxicityAllow", true).getBoolean(true));
		armorEnchantIDs[mapping] = (config.get(catEnchant, "paralysisID", getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)));
		armorEnchantConfigs[mapping++] = (config.get(catEnchant, "paralysisAllow", true).getBoolean(true));
		armorEnchantIDs[mapping] = (config.get(catEnchant, "elasticityID", getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)));
		armorEnchantConfigs[mapping++] = (config.get(catEnchant, "elasticityAllow", true).getBoolean(true));
		armorEnchantIDs[mapping] = (config.get(catEnchant, "agilityID", getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)));
		armorEnchantConfigs[mapping++] = (config.get(catEnchant, "agilityAllow", true).getBoolean(true));

		for (i = 0; i + mapping < armorEnchantConfigs.length; i++)
		{
			armorEnchantConfigs[i + mapping] = false;
		}

		config.addCustomCategoryComment(catEnchant, "Set whether specific Enchantments are allowed, also can choose whether an enchantment type is allowed, also set their IDs");

		blanketMob[0] = (config.get(catMobs, "allowNormalMobs", true).getBoolean(true));
		blanketMob[1] = (config.get(catMobs, "allowMiniBosses", true).getBoolean(true));
		blanketMob[2] = (config.get(catMobs, "allowBosses", true).getBoolean(true));
		blanketMob[3] = (config.get(catMobs, "allowNaturalBossSpawns", true).getBoolean(true));
		blanketMob[4] = (config.get(catMobs, "allowExtraBossLoot", true).getBoolean(true));
		blanketMob[5] = (config.get(catMobs, "allowVictoryBuffs", true).getBoolean(true));
		blanketMob[6] = (config.get(catMobs, "allowCorruptionDamage", true).getBoolean(true));
		blanketMob[7] = (config.get(catMobs, "allowMobTransformation", true).getBoolean(true));

		mobInts[0] = MathHelper.clamp_int(config.get(catMobs, "mobCommonDropRate", 24).getInt(24), 1, 200);
		mobInts[1] = MathHelper.clamp_int(config.get(catMobs, "mobRareDropRate", 5).getInt(5), 1, 100);
		mobInts[2] = MathHelper.clamp_int(config.get(catMobs, "mobTransformationChance", 3).getInt(3), 1, 100);

		mapping = 0;
		mobsSC[mapping] = (config.get(catMobs, "jabbaSpawnChance", 75).getInt(75));
		mobConfigs[mapping++] = (config.get(catMobs, "jabbaAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "jannaSpawnChance", 50).getInt(50));
		mobConfigs[mapping++] = (config.get(catMobs, "jannaAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "plagueSpawnChance", 50).getInt(50));
		mobConfigs[mapping++] = (config.get(catMobs, "plagueAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "gragulSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "gragulAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "minotaurSpawnChance", 75).getInt(75));
		mobConfigs[mapping++] = (config.get(catMobs, "minotaurAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "inklingSpawnChance", 75).getInt(75));
		mobConfigs[mapping++] = (config.get(catMobs, "inklingAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "ragrSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "ragrAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "pumpkinheadSpawnChance", 75).getInt(75));
		mobConfigs[mapping++] = (config.get(catMobs, "pumpkinheadAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "tragicNekoSpawnChance", 50).getInt(50));
		mobConfigs[mapping++] = (config.get(catMobs, "tragicNekoAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "toxSpawnChance", 50).getInt(50));
		mobConfigs[mapping++] = (config.get(catMobs, "toxAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "poxSpawnChance", 50).getInt(50));
		mobConfigs[mapping++] = (config.get(catMobs, "poxAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "cryseSpawnChance", 75).getInt(75));
		mobConfigs[mapping++] = (config.get(catMobs, "cryseAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "starCryseSpawnChance", 75).getInt(75));
		mobConfigs[mapping++] = (config.get(catMobs, "starCryseAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "norVoxSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "norVoxAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "starVoxSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "starVoxAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "pirahSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "pirahAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "lavaPirahSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "lavaPirahAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "stinSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "stinAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "stinBabySpawnChance", 50).getInt(50));
		mobConfigs[mapping++] = (config.get(catMobs, "stinBabyAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "greaterStinSpawnChance", 5).getInt(5));
		mobConfigs[mapping++] = (config.get(catMobs, "greaterStinAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "wispSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "wispAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "abominationSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "abominationAllow", true).getBoolean(true));

		for (i = 0; i + mapping < mobConfigs.length; i++) //sets all unused slots in the array to false
		{
			mobConfigs[mapping + i] = false;
		}

		mapping = 0;
		miniBossSC[mapping] = (config.get(catMobs, "jarraSpawnChance", 5).getInt(5));
		miniBossConfigs[mapping++] = (config.get(catMobs, "jarraAllow", true).getBoolean(true));
		miniBossSC[mapping] = (config.get(catMobs, "kragulSpawnChance", 5).getInt(5));
		miniBossConfigs[mapping++] = (config.get(catMobs, "kragulAllow", true).getBoolean(true));
		miniBossSC[mapping] = (config.get(catMobs, "magmoxSpawnChance", 5).getInt(5));
		miniBossConfigs[mapping++] = (config.get(catMobs, "magmoxAllow", true).getBoolean(true));
		miniBossSC[mapping] = (config.get(catMobs, "megaCryseChance", 5).getInt(5));
		miniBossConfigs[mapping++] = (config.get(catMobs, "megaCryseAllow", true).getBoolean(true));
		miniBossSC[mapping] = (config.get(catMobs, "voxStellarumSpawnChance", 5).getInt(5));
		miniBossConfigs[mapping++] = (config.get(catMobs, "voxStellarumAllow", true).getBoolean(true));
		miniBossSC[mapping] = (config.get(catMobs, "stinKingSpawnChance", 5).getInt(5));
		miniBossConfigs[mapping++] = (config.get(catMobs, "stinKingAllow", true).getBoolean(true));
		miniBossSC[mapping] = (config.get(catMobs, "stinQueenSpawnChance", 5).getInt(5));
		miniBossConfigs[mapping++] = (config.get(catMobs, "stinQueenAllow", true).getBoolean(true));

		for (i = 0; i + mapping < miniBossConfigs.length; i++) //sets all unused slots in the array to false
		{
			miniBossConfigs[mapping + i] = false;
		}

		mapping = 0;
		bossSC[mapping] = (config.get(catMobs, "apisSpawnChance", 5).getInt(5));
		bossConfigs[mapping++] = (config.get(catMobs, "apisAllow", true).getBoolean(true));
		bossSC[mapping] = (config.get(catMobs, "deathReaperSpawnChance", 5).getInt(5));
		bossConfigs[mapping++] = (config.get(catMobs, "deathReaperAllow", true).getBoolean(true));
		bossSC[mapping] = (config.get(catMobs, "kitsuneSpawnChance", 5).getInt(5));
		bossConfigs[mapping++] = (config.get(catMobs, "kitsuneAllow", true).getBoolean(true));
		bossSC[mapping] = (config.get(catMobs, "polarisSpawnChance", 5).getInt(5));
		bossConfigs[mapping++] = (config.get(catMobs, "polarisAllow", true).getBoolean(true));
		bossSC[mapping] = (config.get(catMobs, "yetiSpawnChance", 5).getInt(5));
		bossConfigs[mapping++] = (config.get(catMobs, "yetiAllow", true).getBoolean(true));
		bossSC[mapping] = (config.get(catMobs, "timeControllerSpawnChance", 5).getInt(5));
		bossConfigs[mapping++] = (config.get(catMobs, "timeControllerAllow", true).getBoolean(true));

		for (i = 0; i + mapping < bossConfigs.length; i++) //sets all unused slots in the array to false
		{
			bossConfigs[mapping + i] = false;
		}

		for (i = 0; i < mobsSC.length; i++) //clamps all of the spawn chances between 5 and 250
		{
			mobsSC[i] = MathHelper.clamp_int(mobsSC[i], 5, 250);
			if (i < miniBossSC.length) miniBossSC[i] = MathHelper.clamp_int(miniBossSC[i], 5, 250);
			if (i < bossSC.length) bossSC[i] = MathHelper.clamp_int(bossSC[i], 5, 250);
		}

		config.addCustomCategoryComment(catMobs, "Set whether specific Mobs are allowed, or disable certain groups like Mini-Bosses or Bosses, also set their Spawn Chances");

		TragicMC.doPotionReflection();

		blanketPotion[0] = (config.get(catPotion, "allowPositivePotionEffects", true).getBoolean(true));
		blanketPotion[1] = (config.get(catPotion, "allowNegativePotionEffects", true).getBoolean(true));

		mapping = 0;
		positivePotionIDs[mapping] = (config.get(catPotion, "flightID", getOpenIDForPotion(32)).getInt(getOpenIDForPotion(32)));
		positivePotionConfigs[mapping++] = (config.get(catPotion, "flightAllow", true).getBoolean(true));
		positivePotionIDs[mapping] = (config.get(catPotion, "aquaSuperiorityID", getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)));
		positivePotionConfigs[mapping++] = (config.get(catPotion, "aquaSuperiorityAllow", true).getBoolean(true));
		positivePotionIDs[mapping] = (config.get(catPotion, "immunityID", getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)));
		positivePotionConfigs[mapping++] = (config.get(catPotion, "immunityAllow", true).getBoolean(true));
		positivePotionIDs[mapping] = (config.get(catPotion, "resurrectionID", getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)));
		positivePotionConfigs[mapping++] = (config.get(catPotion, "resurrectionAllow", true).getBoolean(true));
		positivePotionIDs[mapping] = (config.get(catPotion, "harmonyID", getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)));
		positivePotionConfigs[mapping++] = (config.get(catPotion, "harmonyAllow", true).getBoolean(true));
		positivePotionIDs[mapping] = (config.get(catPotion, "invulnerabilityID", getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)));
		positivePotionConfigs[mapping++] = (config.get(catPotion, "invulnerabilityAllow", true).getBoolean(true));
		positivePotionIDs[mapping] = (config.get(catPotion, "clarityID", getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)));
		positivePotionConfigs[mapping++] = (config.get(catPotion, "clarityAllow", true).getBoolean(true));

		temp = mapping - 1;

		for (i = 0; i + mapping < positivePotionConfigs.length; i++) //sets all unused slots in the array to false
		{
			positivePotionConfigs[mapping + i] = false;
		}

		mapping = 0;
		negativePotionIDs[mapping] = (config.get(catPotion, "corruptionID", getOpenIDForPotion(positivePotionIDs[temp] + 1)).getInt(getOpenIDForPotion(positivePotionIDs[temp] + 1)));
		negativePotionConfigs[mapping++] = (config.get(catPotion, "corruptionAllow", true).getBoolean(true));
		negativePotionIDs[mapping] = (config.get(catPotion, "disorientationID", getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)));
		negativePotionConfigs[mapping++] = (config.get(catPotion, "disorientationAllow", true).getBoolean(true));
		negativePotionIDs[mapping] = (config.get(catPotion, "stunID", getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)));
		negativePotionConfigs[mapping++] = (config.get(catPotion, "stunAllow", true).getBoolean(true));
		negativePotionIDs[mapping] = (config.get(catPotion, "fearID", getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)));
		negativePotionConfigs[mapping++] = (config.get(catPotion, "fearAllow", true).getBoolean(true));
		negativePotionIDs[mapping] = (config.get(catPotion, "malnourishID", getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)));
		negativePotionConfigs[mapping++] = (config.get(catPotion, "malnourishAllow", true).getBoolean(true));
		negativePotionIDs[mapping] = (config.get(catPotion, "crippleID", getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)));
		negativePotionConfigs[mapping++] = (config.get(catPotion, "crippleAllow", true).getBoolean(true));
		negativePotionIDs[mapping] = (config.get(catPotion, "submissionID", getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)));
		negativePotionConfigs[mapping++] = (config.get(catPotion, "submissionAllow", true).getBoolean(true));
		negativePotionIDs[mapping] = (config.get(catPotion, "inhibitID", getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)));
		negativePotionConfigs[mapping++] = (config.get(catPotion, "inhibitAllow", true).getBoolean(true));

		for (i = 0; i + mapping < negativePotionConfigs.length; i++) //sets all unused slots in the array to false
		{
			negativePotionConfigs[mapping + i] = false;
		}

		config.addCustomCategoryComment(catPotion, "Set whether specific Potion Effects are allowed, or disable all good or all bad effects, also set their IDs");

		mapping = 0;
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowVanillaMobBuffs", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowExtraMobEffects", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowAnimalRetribution", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowMobModdedArmorAndEnchantments", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowRespawnPunishment", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowExtraExplosiveEffects", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowBabySpawns", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowModdedOverworldFlowerGen", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowOverworldSilverfishOregen", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowNetherOreGen", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowOverworldOreGen", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowQuicksandAndDrudgeGen", true).getBoolean(true));

		mapping = 0;
		vanillaInts[mapping++] = MathHelper.clamp_int(config.get(catVanilla, "rubyOreGenRate", 10).getInt(10), 1, 25);
		vanillaInts[mapping++] = MathHelper.clamp_int(config.get(catVanilla, "sapphireOreGenRate", 10).getInt(10), 1, 25);
		vanillaInts[mapping++] = MathHelper.clamp_int(config.get(catVanilla, "mercuryOreGenRate", 20).getInt(20), 1, 25);
		vanillaInts[mapping++] = MathHelper.clamp_int(config.get(catVanilla, "tungstenOreGenRate", 10).getInt(10), 1, 25);
		vanillaInts[mapping++] = MathHelper.clamp_int(config.get(catVanilla, "quicksandGenRate", 5).getInt(20), 1, 25);
		vanillaInts[mapping++] = MathHelper.clamp_int(config.get(catVanilla, "netherDrudgeGenRate", 10).getInt(20), 1, 25);
		vanillaInts[mapping++] = MathHelper.clamp_int(config.get(catVanilla, "silverfishGenRate", 12).getInt(12), 1, 25);

		config.addCustomCategoryComment(catVanilla, "These are all of the things that are changed in Vanilla by this mod, OreGen only affects Overworld and Nether types of the ores");

		mapping = 0;
		blanketWorldGen[mapping++] = (config.get(catWorldGen, "allowVoidPitGen", true).getBoolean(true));
		blanketWorldGen[mapping++] = (config.get(catWorldGen, "allowLargeSpikeGen", true).getBoolean(true));
		blanketWorldGen[mapping++] = (config.get(catWorldGen, "allowDarkStoneVariantGen", true).getBoolean(true));
		blanketWorldGen[mapping++] = (config.get(catWorldGen, "allowStructureGen", true).getBoolean(true));
		blanketWorldGen[mapping++] = (config.get(catWorldGen, "allowNonBossStructureGen", true).getBoolean(true));
		blanketWorldGen[mapping++] = (config.get(catWorldGen, "allowBossStructureGen", true).getBoolean(true));

		mapping = 0;
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "voidPitRarity", 5).getInt(5), 1, 100);
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "largeSpikeRarity", 95).getInt(5), 1, 100);
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "starCrystalRarity", 10).getInt(10), 1, 100);
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "structureOverallRarity", 5).getInt(5), 1, 100);
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "apisTempleRarity", 5).getInt(5), 1, 100);
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "desertTowerRarity", 15).getInt(15), 1, 100);
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "deathCircleRarity", 5).getInt(5), 1, 100);
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "obsidianCavernRarity", 10).getInt(10), 1, 100);
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "kitsuneDenRarity", 5).getInt(5), 1, 100);
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "celestialTempleRarity", 3).getInt(3), 1, 100);
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "timeAltarRarity", 5).getInt(5), 1, 100);
		worldGenInts[mapping++] = MathHelper.clamp_int(config.get(catWorldGen, "yetiRavineRarity", 5).getInt(5), 1, 100);
		
		config.addCustomCategoryComment(catWorldGen, "These toggle specific WorldGen features, meant to help with lag reduction if your CPU cannot handle it during WorldGen, also toggle rarities of structures");

		mapping = 0;
		miscConfigs[mapping++] = (config.get(catMisc, "allowRandomWeaponLore", true).getBoolean(true));
		
		if (config.hasChanged())
		{
			config.save();
		}

		postProcessConfigs();
	}

	/**
	 * This ensures that blanket configs toggle whatever is below them properly, this then calls initializeAllVariables() to ensure that all of the variables are initialized/changed
	 * @param config
	 */
	public static void postProcessConfigs()
	{
		textureRes = "_lowRes";
		//if (resolution < 32) textureRes = "_lowRes";
		//if (resolution >=32 && resolution < 64) textureRes = "_midRes";
		//if (resolution >= 64 && resolution < 128) textureRes = "_highRes"; 
		//if (resolution >= 128 && resolution < 256) textureRes = "_superRes";
		//if (resolution >= 256) textureRes = "_ultraRes";

		int i;

		if (mobsOnly)
		{
			for (i = 0; i < blanketConfigs.length; i++)
			{
				if (i != 5) blanketConfigs[i] = false;
			}
		}

		if (!blanketConfigs[1])
		{
			for (i = 0; i < normalAmuletConfigs.length; i++)
			{
				normalAmuletConfigs[i] = false;
				if (i < cursedAmuletConfigs.length) cursedAmuletConfigs[i] = false;
				if (i < epicAmuletConfigs.length) epicAmuletConfigs[i] = false;
			}
		}
		else
		{
			if (!blanketAmulet[0])
			{
				for (i = 0; i < normalAmuletConfigs.length; i++)
				{

					normalAmuletConfigs[i] = false;
				}
			}

			if (!blanketAmulet[1])
			{
				for (i = 0; i < cursedAmuletConfigs.length; i++)
				{
					cursedAmuletConfigs[i] = false;
				}
			}

			if (!blanketAmulet[2])
			{
				for (i = 0; i < epicAmuletConfigs.length; i++)
				{
					epicAmuletConfigs[i] = false;
				}
			}
		}

		if (!blanketConfigs[3])
		{
			for (i = 0; i < blanketDoom.length; i++)
			{
				blanketDoom[i] = false;
			}
		}
		else
		{
			if (!blanketDoom[0])
			{
				for (i = 1; i < 6; i++)
				{
					blanketDoom[i] = false;
				}
			}
			
			if (!blanketDoom[13])
			{
				blanketDoom[14] = false;
			}
		}

		if (!blanketConfigs[4])
		{
			for (i = 0; i < weaponEnchantConfigs.length; i++)
			{
				weaponEnchantConfigs[i] = false;
				armorEnchantConfigs[i] = false;
				if (i < blanketEnchant.length) blanketEnchant[i] = false;
			}
		}
		else
		{
			if (!blanketEnchant[0])
			{
				for (i = 0; i < weaponEnchantConfigs.length; i++)
				{
					weaponEnchantConfigs[i] = false;
				}
			}

			if (!blanketEnchant[1])
			{
				for (i = 0; i < armorEnchantConfigs.length; i++)
				{
					armorEnchantConfigs[i] = false;
				}
			}
		}

		if (!blanketConfigs[5])
		{
			for (i = 0; i < mobConfigs.length; i++)
			{
				mobConfigs[i] = false;

				if (i < miniBossConfigs.length) miniBossConfigs[i] = false;
				if (i < bossConfigs.length) bossConfigs[i] = false;
				if (i < blanketMob.length) blanketMob[i] = false; 
			}
		}
		else
		{
			if (!blanketMob[0])
			{
				for (i = 0; i < mobConfigs.length; i++)
				{
					mobConfigs[i] = false;
				}
			}

			if (!blanketMob[1])
			{
				for (i = 0; i < miniBossConfigs.length; i++)
				{
					miniBossConfigs[i] = false;
				}
			}

			if (!blanketMob[2])
			{
				for (i = 0; i < bossConfigs.length; i++)
				{
					bossConfigs[i] = false;
				}
			}
		}

		if (!blanketConfigs[6])
		{
			for (i = 0; i < positivePotionConfigs.length; i++)
			{
				positivePotionConfigs[i] = false;
				negativePotionConfigs[i] = false;
				if (i < blanketPotion.length) blanketPotion[i] = false; 
			}
		}
		else
		{
			if (!blanketPotion[0])
			{
				for (i = 0; i < positivePotionConfigs.length; i++)
				{
					positivePotionConfigs[i] = false;
				}
			}

			if (!blanketPotion[1])
			{
				for (i = 0; i < negativePotionConfigs.length; i++)
				{
					negativePotionConfigs[i] = false;
				}
			}
		}

		if (!blanketConfigs[7])
		{
			for (i = 0; i < blanketVanillaChanges.length; i++)
			{
				blanketVanillaChanges[i] = false;
			}
		}

		if (!blanketConfigs[8])
		{
			for (i = 0; i < blanketWorldGen.length; i++)
			{
				blanketWorldGen[i] = false;
			}
		}
		else
		{			
			if (!blanketWorldGen[3])
			{
				blanketWorldGen[4] = false;
				blanketWorldGen[5] = false;
			}
		}

		initializeAllVariables(config);
	}

	/**
	 * This sets all variables after the configurations are processed for blanket configuration handling
	 * @param config
	 */
	public static void initializeAllVariables(Configuration config)
	{
		int mapping = 0;
		allowAchievements = blanketConfigs[mapping++];
		allowAmulets = blanketConfigs[mapping++];
		allowDimension = blanketConfigs[mapping++];
		allowEnchantments = blanketConfigs[mapping++];
		allowMobs = blanketConfigs[mapping++];
		allowPotions = blanketConfigs[mapping++];
		allowVanillaChanges = blanketConfigs[mapping++];
		allowWorldGen = blanketConfigs[mapping++];

		mapping = 0;
		allowNormalAmulets = blanketAmulet[mapping++];
		allowCursedAmulets = blanketAmulet[mapping++];
		allowEpicAmulets = blanketAmulet[mapping++];
		allowAmuletLeveling = blanketAmulet[mapping++];
		allowAmuletCrafting = blanketAmulet[mapping++];
		shouldUnlockAmuletSlots = blanketAmulet[mapping++];
		allowAmuletKillRecharge = blanketAmulet[mapping++];

		maxAmuletSlots = amuletInts[0];
		overallAmuletRarity = amuletInts[1];
		amuletReleaseRarity = amuletInts[2];

		mapping = 0;
		amuPeace = normalAmuletConfigs[mapping++];
		amuYeti = normalAmuletConfigs[mapping++];
		amuClaymation = normalAmuletConfigs[mapping++];
		amuChicken = normalAmuletConfigs[mapping++];
		amuBlacksmith = normalAmuletConfigs[mapping++];
		amuCreeper = normalAmuletConfigs[mapping++];
		amuZombie = normalAmuletConfigs[mapping++];
		amuSkeleton = normalAmuletConfigs[mapping++];

		mapping = 0;
		amuKitsune = cursedAmuletConfigs[mapping++];
		amuMartyr = cursedAmuletConfigs[mapping++];
		amuHornet = cursedAmuletConfigs[mapping++];
		amuApis = cursedAmuletConfigs[mapping++];
		amuSunken = cursedAmuletConfigs[mapping++];

		amuTime = epicAmuletConfigs[0];

		mapping = 0;
		idDecayingHills = biomeIDs[mapping++];
		idDecayingValley = biomeIDs[mapping++];
		idDecayingWasteland = biomeIDs[mapping++];
		idDecayingMountains = biomeIDs[mapping++];
		idPaintedForest = biomeIDs[mapping++];
		idPaintedPlains = biomeIDs[mapping++];
		idPaintedHills = biomeIDs[mapping++];
		idPaintedClearing = biomeIDs[mapping++];
		idAshenMountains = biomeIDs[mapping++];
		idAshenHills = biomeIDs[mapping++];
		idAshenBadlands = biomeIDs[mapping++];
		idStarlitPrarie = biomeIDs[mapping++];
		idStarlitPlateaus = biomeIDs[mapping++];
		idStarlitCliffs = biomeIDs[mapping++];
		idStarlitLowlands = biomeIDs[mapping++];
		idTaintedSpikes = biomeIDs[mapping++];
		idTaintedArchipelago = biomeIDs[mapping++];
		idTaintedRises = biomeIDs[mapping++];
		idTaintedScarlands = biomeIDs[mapping++];
		idTaintedIsles = biomeIDs[mapping++];

		mapping = 0;
		allowDoomsdays = blanketDoom[mapping++];
		allowInfluenceDoomsday = blanketDoom[mapping++];
		allowCrisisDoomsday = blanketDoom[mapping++];
		allowOverflowDoomsday = blanketDoom[mapping++];
		allowWorldShaperDoomsday = blanketDoom[mapping++];
		allowUltimateDoomsday = blanketDoom[mapping++];
		allowNonDoomsdayAbilities = blanketDoom[mapping++];
		shouldDoomLimitIncrease = blanketDoom[mapping++];
		allowConsumeRefill = blanketDoom[mapping++];
		allowDoomPainRecharge = blanketDoom[mapping++];
		allowNaturalRecharge = blanketDoom[mapping++];
		allowCrucialMoments = blanketDoom[mapping++];
		allowBacklash = blanketDoom[mapping++];
		allowCooldown = blanketDoom[mapping++];
		allowCooldownDefuse = blanketDoom[mapping++];
		showDoomGui = blanketDoom[mapping++];

		mapping = 0;
		maxDoomAmount = doomInts[mapping++];
		doomRechargeRate = doomInts[mapping++];
		doomConsumeRarity = doomInts[mapping++];
		cooldownDefuseRarity = doomInts[mapping++];
		consumeRefillAmount = doomInts[mapping++];
		defuseRefillAmount = doomInts[mapping++];
		backlashChance = doomInts[mapping++];
		crucialMomentChance = doomInts[mapping++];

		allowWeaponEnchants = blanketEnchant[0];
		allowArmorEnchants = blanketEnchant[1];

		mapping = 0;
		idDecay = weaponEnchantIDs[mapping];
		allowDecay = weaponEnchantConfigs[mapping++];
		idSlay = weaponEnchantIDs[mapping];
		allowSlay = weaponEnchantConfigs[mapping++];
		idAbsolve= weaponEnchantIDs[mapping];
		allowAbsolve = weaponEnchantConfigs[mapping++];
		idSlay = weaponEnchantIDs[mapping];
		allowSlay = weaponEnchantConfigs[mapping++];
		idVampirism = weaponEnchantIDs[mapping];
		allowVampirism = weaponEnchantConfigs[mapping++];
		idLeech = weaponEnchantIDs[mapping];
		allowLeech = weaponEnchantConfigs[mapping++];
		idConsume = weaponEnchantIDs[mapping];
		allowConsume = weaponEnchantConfigs[mapping++];
		idDistract = weaponEnchantIDs[mapping];
		allowDistract = weaponEnchantConfigs[mapping++];
		idMultiply = weaponEnchantIDs[mapping];
		allowMultiply = weaponEnchantConfigs[mapping++];
		idCombustion = weaponEnchantIDs[mapping];
		allowCombustion = weaponEnchantConfigs[mapping++];

		mapping = 0;
		idDeathTouch = armorEnchantIDs[mapping];
		allowDeathTouch = armorEnchantConfigs[mapping++];
		idIgnition = armorEnchantIDs[mapping];
		allowIgnition = armorEnchantConfigs[mapping++];
		idToxicity = armorEnchantIDs[mapping];
		allowToxicity = armorEnchantConfigs[mapping++];
		idParalysis = armorEnchantIDs[mapping];
		allowParalysis = armorEnchantConfigs[mapping++];
		idElasticity = armorEnchantIDs[mapping];
		allowElasticity = armorEnchantConfigs[mapping++];
		idAgility = armorEnchantIDs[mapping];
		allowAgility = armorEnchantConfigs[mapping++];

		allowNormalMobs = blanketMob[0];
		allowMiniBosses = blanketMob[1];
		allowBosses = blanketMob[2];
		allowBossNaturalSpawns = blanketMob[3];
		allowExtraBossLoot = blanketMob[4];
		allowVictoryBuffs = blanketMob[5];

		commonDropRate = mobInts[0];
		rareDropRate = mobInts[1];
		mobTransformationChance = mobInts[2];

		mapping = 0;
		jabbaSC = mobsSC[mapping];
		allowJabba = mobConfigs[mapping++];
		jannaSC = mobsSC[mapping];
		allowJanna = mobConfigs[mapping++];
		plagueSC = mobsSC[mapping];
		allowPlague = mobConfigs[mapping++];
		gragulSC = mobsSC[mapping];
		allowGragul = mobConfigs[mapping++];
		minotaurSC = mobsSC[mapping];
		allowMinotaur = mobConfigs[mapping++];
		inklingSC = mobsSC[mapping];
		allowInkling = mobConfigs[mapping++];
		ragrSC = mobsSC[mapping];
		allowRagr = mobConfigs[mapping++];
		pumpkinheadSC = mobsSC[mapping];
		allowPumpkinhead = mobConfigs[mapping++];
		tragicNekoSC = mobsSC[mapping];
		allowTragicNeko = mobConfigs[mapping++];
		toxSC = mobsSC[mapping];
		allowTox = mobConfigs[mapping++];
		poxSC = mobsSC[mapping];
		allowPox = mobConfigs[mapping++];
		cryseSC = mobsSC[mapping];
		allowCryse = mobConfigs[mapping++];
		starCryseSC = mobsSC[mapping];
		allowStarCryse = mobConfigs[mapping++];
		norVoxSC = mobsSC[mapping];
		allowNorVox = mobConfigs[mapping++];
		starVoxSC = mobsSC[mapping];
		allowStarVox = mobConfigs[mapping++];
		pirahSC = mobsSC[mapping];
		allowPirah = mobConfigs[mapping++];
		lavaPirahSC = mobsSC[mapping];
		allowLavaPirah = mobConfigs[mapping++];
		stinSC = mobsSC[mapping];
		allowStin = mobConfigs[mapping++];
		stinBabySC = mobsSC[mapping];
		allowStinBaby = mobConfigs[mapping++];
		greaterStinSC = mobsSC[mapping];
		allowGreaterStin = mobConfigs[mapping++];
		wispSC = mobsSC[mapping];
		allowWisp = mobConfigs[mapping++];
		abominationSC = mobsSC[mapping];
		allowAbomination = mobConfigs[mapping++];

		mapping = 0;
		jarraSC = miniBossSC[mapping];
		allowJarra = miniBossConfigs[mapping++];
		kragulSC = miniBossSC[mapping];
		allowKragul = miniBossConfigs[mapping++];
		magmoxSC = miniBossSC[mapping];
		allowMagmox = miniBossConfigs[mapping++];
		megaCryseSC = miniBossSC[mapping];
		allowMegaCryse = miniBossConfigs[mapping++];
		voxStellarumSC = miniBossSC[mapping];
		allowVoxStellarum = miniBossConfigs[mapping++];
		stinKingSC = miniBossSC[mapping];
		allowStinKing = miniBossConfigs[mapping++];
		stinQueenSC = miniBossSC[mapping];
		allowStinQueen = miniBossConfigs[mapping++];

		mapping = 0;
		apisSC = bossSC[mapping];
		allowApis = bossConfigs[mapping++];
		deathReaperSC = bossSC[mapping];
		allowDeathReaper = bossConfigs[mapping++];
		kitsuneSC = bossSC[mapping];
		allowKitsune = bossConfigs[mapping++];
		polarisSC = bossSC[mapping];
		allowPolaris = bossConfigs[mapping++];
		yetiSC = bossSC[mapping];
		allowYeti = bossConfigs[mapping++];
		timeControllerSC = bossSC[mapping];
		allowTimeController = bossConfigs[mapping++];

		allowPositivePotions = blanketPotion[0];
		allowNegativePotions = blanketPotion[1];

		mapping = 0;
		idFlight = positivePotionIDs[mapping];
		allowFlight = positivePotionConfigs[mapping++];
		idAquaSuperiority = positivePotionIDs[mapping];
		allowAquaSuperiority = positivePotionConfigs[mapping++];
		idImmunity = positivePotionIDs[mapping];
		allowImmunity = positivePotionConfigs[mapping++];
		idResurrection = positivePotionIDs[mapping];
		allowResurrection = positivePotionConfigs[mapping++];
		idHarmony = positivePotionIDs[mapping];
		allowHarmony = positivePotionConfigs[mapping++];
		idInvulnerability = positivePotionIDs[mapping];
		allowInvulnerability = positivePotionConfigs[mapping++];
		idClarity = positivePotionIDs[mapping];
		allowClarity = positivePotionConfigs[mapping++];

		mapping = 0;
		idCorruption = negativePotionIDs[mapping];
		allowCorruption = negativePotionConfigs[mapping++];
		idDisorientation = negativePotionIDs[mapping];
		allowDisorientation = negativePotionConfigs[mapping++];
		idStun = negativePotionIDs[mapping];
		allowStun = negativePotionConfigs[mapping++];
		idFear = negativePotionIDs[mapping];
		allowFear = negativePotionConfigs[mapping++];
		idMalnourish = negativePotionIDs[mapping];
		allowMalnourish = negativePotionConfigs[mapping++];
		idCripple = negativePotionIDs[mapping];
		allowCripple = negativePotionConfigs[mapping++];
		idSubmission = negativePotionIDs[mapping];
		allowSubmission = negativePotionConfigs[mapping++];
		idInhibit = negativePotionIDs[mapping];
		allowInhibit = negativePotionConfigs[mapping++];
		
		mapping = 0;
		allowVanillaMobBuffs = blanketVanillaChanges[mapping++];
		allowExtraMobEffects = blanketVanillaChanges[mapping++];
		allowAnimalRetribution = blanketVanillaChanges[mapping++];
		allowMobModdedArmorAndEnchants = blanketVanillaChanges[mapping++];
		allowRespawnPunishment = blanketVanillaChanges[mapping++];
		allowExtraExplosiveEffects = blanketVanillaChanges[mapping++];
		allowBabySpawns = blanketVanillaChanges[mapping++];
		allowExtraOverworldFlowers = blanketVanillaChanges[mapping++];
		allowOverworldSilverfishGen = blanketVanillaChanges[mapping++];
		allowNetherOreGen = blanketVanillaChanges[mapping++];
		allowOverworldOreGen = blanketVanillaChanges[mapping++];
		allowQuicksandGen = blanketVanillaChanges[mapping++];
		
		mapping = 0;
		rubyOreRarity = vanillaInts[mapping++];
		sapphireOreRarity = vanillaInts[mapping++];
		mercuryOreRarity = vanillaInts[mapping++];
		tungstenOreRarity = vanillaInts[mapping++];
		quicksandGenRarity = vanillaInts[mapping++];
		drudgeGenRarity = vanillaInts[mapping++];
		silverfishGenRarity = vanillaInts[mapping++];
		
		mapping = 0;
		allowVoidPitGen = blanketWorldGen[mapping++];
		allowLargeSpikeGen = blanketWorldGen[mapping++];
		allowDarkStoneVariantGen = blanketWorldGen[mapping++];
		allowStructureGen = blanketWorldGen[mapping++];
		allowNonBossStructureGen = blanketWorldGen[mapping++];
		allowBossStructureGen = blanketWorldGen[mapping++];
		
		mapping = 0;
		voidPitRarity = worldGenInts[mapping++];
		largeSpikeRarity = worldGenInts[mapping++];
		starCrystalRarity = worldGenInts[mapping++];
		structureOverallRarity = worldGenInts[mapping++];
		apisTempleRarity = worldGenInts[mapping++];
		desertTowerRarity = worldGenInts[mapping++];
		deathCircleRarity = worldGenInts[mapping++];
		obsidianCavernRarity = worldGenInts[mapping++];
		kitsuneDenRarity = worldGenInts[mapping++];
		celestialTempleRarity = worldGenInts[mapping++];
		timeAltarRarity = worldGenInts[mapping++];
		yetiRavineRarity = worldGenInts[mapping++];
		
		mapping = 0;
		allowRandomWeaponLore = miscConfigs[mapping++];
	}
	
	public static void disablePotions()
	{
		blanketConfigs[6] = false;
		postProcessConfigs();
	}

	private static int getOpenIDForEnchant(int configId)
	{
		if (configId <= Enchantment.enchantmentsList.length && Enchantment.enchantmentsList[configId] != null)
		{
			while (Enchantment.enchantmentsList[configId] != null)
			{
				configId++;

				if (configId > Enchantment.enchantmentsList.length) break;
			}
		}
		return configId;
	}

	private static int getOpenIDForPotion(int configId)
	{		
		if (configId <= Potion.potionTypes.length && Potion.potionTypes[configId] != null)
		{
			while (Potion.potionTypes[configId] != null)
			{
				configId++;
				if (configId > Potion.potionTypes.length) break;
			}
		}
		return configId;
	}

	private static int getOpenIDForBiome(int configId)
	{
		if (configId <= BiomeGenBase.getBiomeGenArray().length && BiomeGenBase.getBiomeGenArray()[configId] != null)
		{
			while (BiomeGenBase.getBiomeGenArray()[configId] != null)
			{
				configId++;
				if (configId > BiomeGenBase.getBiomeGenArray().length) break; 
			}
		}
		return configId;
	}
}
