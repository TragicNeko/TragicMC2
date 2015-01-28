package tragicneko.tragicmc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;

public class TragicConfig {

	public static boolean mobsOnly;
	private static int resolution;

	private static final String catMaster = "Master Configs";
	private static final String catBlanket = "Blanket Configs";
	private static final String catAchievement = "Achievements";
	private static final String catAmulet = "Amulets";
	private static final String catDimension = "Dimension";
	private static final String catDoom = "Doom";
	private static final String catEnchant = "Enchantments";
	private static final String catMobs = "Mobs";
	private static final String catPotion = "Potions";
	private static final String catVanilla = "Vanilla Changes";
	private static final String catWorldGen = "WorldGen";
	private static final String catMisc = "Miscellaneous";
	private static final String catMod = "Attribute Modifiers";

	private static boolean[] blanketConfigs = new boolean[9];
	public static boolean allowAchievements, allowAmulets, allowDimension, allowDoom, allowEnchantments, allowMobs, allowPotions, allowVanillaChanges, allowWorldGen;

	private static boolean[] blanketAmulet = new boolean[10];
	public static boolean allowNormalAmulets, allowCursedAmulets, allowEpicAmulets, allowAmuletLeveling, allowAmuletCrafting, shouldUnlockAmuletSlots, allowAmuletKillRecharge;
	public static boolean showAmuletStatus, allowAmuletModifiers;
	private static int[] amuletInts = new int[8];
	public static int maxAmuletSlots, overallAmuletRarity, amuletReleaseRarity, amuletModifierChance, amuletModifierChance2, amuletModifierChance3;
	private static boolean[] normalAmuletConfigs = new boolean[24];
	public static boolean amuPeace, amuYeti, amuClaymation, amuChicken, amuBlacksmith, amuCreeper, amuZombie, amuSkeleton, amuIce, amuSnowGolem, amuIronGolem;
	private static boolean[] cursedAmuletConfigs = new boolean[12];
	public static boolean amuKitsune, amuMartyr, amuPiercing, amuApis, amuSunken, amuEnderman;
	private static boolean[] epicAmuletConfigs = new boolean[6];
	public static boolean amuTime, amuWither;

	public static boolean keepDimensionLoaded;
	private static int[] dimensionIDs = new int[4];
	public static int dimensionID, providerID, synapseID, synapseProviderID;
	private static int[] biomeIDs = new int[48];
	public static int idDecayingHills, idDecayingValley, idDecayingWasteland, idDecayingMountains, idPaintedForest, idPaintedPlains, idPaintedHills, idPaintedClearing;
	public static int idAshenMountains, idAshenHills, idAshenBadlands, idStarlitPrarie, idStarlitPlateaus, idStarlitCliffs, idStarlitLowlands, idTaintedSpikes;
	public static int idTaintedLowlands, idTaintedRises, idTaintedScarlands, idTaintedIsles;
	public static int idMagmaSprings, idMagmaMountains, idMagmaSinkholes, idMagmaScarlands;
	public static int idFrozenValley, idFrozenForest, idFrozenTundra, idFrozenSwamps;
	public static int idSynapse;

	private static boolean[] blanketDoom = new boolean[17];
	public static boolean allowDoomsdays, allowInfluenceDoomsday, allowCrisisDoomsday, allowOverflowDoomsday, allowWorldShaperDoomsday, allowCombinationDoomsday, allowNonDoomsdayAbilities;
	public static boolean shouldDoomLimitIncrease, allowConsumeRefill, allowDoomPainRecharge, allowNaturalRecharge, allowCrucialMoments, allowBacklash, allowCooldown;
	public static boolean allowCooldownDefuse, showDoomGui;
	private static int[] doomInts = new int[12];
	public static int maxDoomAmount, doomRechargeRate, doomConsumeRarity, cooldownDefuseRarity, consumeRefillAmount, defuseRefillAmount, backlashChance, crucialMomentChance;
	public static int doomConsumeAmount, maxDoomMinimum;
	public static boolean[] doomsdayAllow = new boolean[64];
	public static int[] doomsdayCooldowns = new int[64];
	public static int[] doomsdayCosts = new int[64];
	public static boolean[] nonDoomsdayAbilities = new boolean[64];
	public static int[] nonDoomsdayAbilityCosts = new int[64];

	private static boolean[] blanketEnchant = new boolean[2];
	public static boolean allowWeaponEnchants, allowArmorEnchants;
	private static boolean[] weaponEnchantConfigs = new boolean[14];
	public static boolean allowDecay, allowSlay, allowAbsolve, allowVampirism, allowLeech, allowConsume, allowDistract, allowMultiply, allowCombustion, allowRuneBreak;
	public static boolean allowReach, allowUnbreakable, allowRust;
	private static boolean[] armorEnchantConfigs = new boolean[12];
	public static boolean allowDeathTouch, allowIgnition, allowToxicity, allowParalysis, allowElasticity, allowAgility, allowRuneWalker, allowLuminescence;
	private static int[] weaponEnchantIDs = new int[14];
	public static int idDecay, idSlay, idAbsolve, idVampirism, idLeech, idConsume, idDistract, idMultiply, idCombustion, idRuneBreak, idReach, idUnbreakable, idRust;
	private static int[] armorEnchantIDs = new int[12];
	public static int idDeathTouch, idIgnition, idToxicity, idParalysis, idElasticity, idAgility, idRuneWalker, idLuminescence;

	private static boolean[] blanketMob = new boolean[12];
	public static boolean allowNormalMobs, allowMiniBosses, allowBosses, allowBossOverworldSpawns, allowExtraBossLoot, allowVictoryBuffs, allowCorruptionDamage, allowMobTransformation;
	public static boolean allowDynamicHealthScaling;
	private static boolean[] mobConfigs = new boolean[32];
	public static boolean allowJabba, allowJanna, allowPlague, allowGragul, allowMinotaur, allowInkling, allowRagr, allowPumpkinhead, allowTragicNeko, allowTox, allowPox;
	public static boolean allowCryse, allowStarCryse, allowNorVox, allowStarVox, allowPirah, allowLavaPirah, allowStin, allowStinBaby, allowWisp, allowAbomination, allowErkel;
	public static boolean allowSirv, allowPsygote, allowLockbot, allowNanoSwarm, allowCircuitGolem, allowSnowGolem;
	private static int[] mobsSC = new int[32];
	public static int jabbaSC, jannaSC, plagueSC, gragulSC, minotaurSC, inklingSC, ragrSC, pumpkinheadSC, tragicNekoSC, toxSC, poxSC, cryseSC, starCryseSC, norVoxSC, starVoxSC;
	public static int pirahSC, lavaPirahSC, stinSC, stinBabySC, wispSC, abominationSC, erkelSC, sirvSC, psygoteSC, lockbotSC, nanoSwarmSC, circuitGolemSC, snowGolemSC;
	private static boolean[] miniBossConfigs = new boolean[16];
	public static boolean allowJarra, allowKragul, allowMagmox, allowMegaCryse, allowVoxStellarum, allowGreaterStin, allowStinKing, allowStinQueen, allowLocobot, allowAegar;
	private static int[] miniBossSC = new int[16];
	public static int jarraSC, kragulSC, magmoxSC, megaCryseSC, voxStellarumSC, greaterStinSC, stinKingSC, stinQueenSC, locobotSC, aegarSC;
	private static boolean[] bossConfigs = new boolean[12];
	public static boolean allowApis, allowDeathReaper, allowKitsune, allowYeti, allowTimeController, allowPolaris, allowEnyvil, allowClaymation, allowOverlord;
	private static int[] bossSC = new int[12];
	public static int apisSC, deathReaperSC, kitsuneSC, yetiSC, timeControllerSC, polarisSC, enyvilSC, claymationSC, overlordSC;
	private static int[] mobInts = new int[3];
	public static int commonDropRate, rareDropRate, mobTransformationChance;
	private static double[][] mobStats = new double[32][];
	public static double[] jabbaStats, jannaStats, plagueStats, gragulStats, minotaurStats, inklingStats, ragrStats, pumpkinheadStats, tragicNekoStats, toxStats, poxStats, cryseStats;
	public static double[] starCryseStats, norVoxStats, starVoxStats, goldenPirahStats, pirahStats, stinStats, stinBabyStats, wispStats, abominationStats, erkelStats, sirvStats, psygoteStats;
	public static double[] lockbotStats, nanoSwarmStats, circuitGolemStats;
	private static double[][] miniBossStats = new double[16][];
	public static double[] jarraStats, kragulStats, magmoxStats, megaCryseStats, voxStellarumStats, greaterStinStats, stinKingStats, stinQueenStats, locobotStats, aegarStats;
	private static double[][] bossStats = new double[12][];
	public static double[] apisStats, deathReaperStats, kitsuneStats, yetiStats, timeControllerStats, polarisStats, enyvilStats, claymationStats, overlordCoreStats;

	private static boolean[] blanketPotion = new boolean[2];
	public static boolean allowPositivePotions, allowNegativePotions;
	private static boolean[] positivePotionConfigs = new boolean[12];
	public static boolean allowFlight, allowAquaSuperiority, allowImmunity, allowResurrection, allowHarmony, allowInvulnerability, allowClarity, allowConvergence, allowDivinity;
	private static boolean[] negativePotionConfigs = new boolean[12];
	public static boolean allowCorruption, allowDisorientation, allowStun, allowFear, allowMalnourish, allowCripple, allowSubmission, allowInhibit, allowLeadFoot, allowHacked;
	private static int[] positivePotionIDs = new int[12];
	public static int idFlight, idAquaSuperiority, idImmunity, idResurrection, idHarmony, idInvulnerability, idClarity, idConvergence, idDivinity;
	private static int[] negativePotionIDs = new int[12];
	public static int idCorruption, idDisorientation, idStun, idFear, idMalnourish, idCripple, idSubmission, idInhibit, idLeadFoot, idHacked;

	private static boolean[] blanketVanillaChanges = new boolean[14];
	public static boolean allowVanillaMobBuffs, allowExtraMobEffects, allowAnimalRetribution, allowMobModdedArmorAndEnchants, allowRespawnPunishment, allowExtraExplosiveEffects;
	public static boolean allowMobBlindnessDebuff, allowExtraOverworldFlowers, allowOverworldSilverfishGen, allowNetherOreGen, allowOverworldOreGen, allowQuicksandGen, allowAnimalGolemCorruption;
	private static int[] vanillaInts = new int[7];
	public static int rubyOreRarity, sapphireOreRarity, mercuryOreRarity, tungstenOreRarity, quicksandGenRarity, drudgeGenRarity, silverfishGenRarity;

	private static boolean[] blanketWorldGen = new boolean[6];
	public static boolean allowVoidPitGen, allowLargeSpikeGen, allowDarkStoneVariantGen, allowStructureGen, allowNonBossStructureGen, allowBossStructureGen;
	private static int[] worldGenInts = new int[12];
	public static int voidPitRarity, largeSpikeRarity, starCrystalRarity, structureOverallRarity, apisTempleRarity, desertTowerRarity, deathCircleRarity, obsidianCavernRarity;
	public static int kitsuneDenRarity, celestialTempleRarity, timeAltarRarity, yetiRavineRarity;

	private static boolean[] miscConfigs = new boolean[16];
	public static boolean allowRandomWeaponLore, allowChallengeScrolls, allowMobStatueDrops, allowAnimatedGui, allowGeneratorItems, allowItemTimeAltering, allowWeaponModels;
	private static int[] miscInts = new int[16];
	public static int challengeScrollDropChance, mobStatueDropChance, guiTransparency, guiTexture, guiX, guiY;
	public static double[] modifierAmts = new double[32];
	public static boolean[] griefConfigs = new boolean[8];

	public static void initialize()
	{
		Configuration config = TragicMC.getConfig();
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
		blanketAmulet[mapping++] = (config.get(catAmulet, "showAmuletStatusGui", true).getBoolean(true));
		blanketAmulet[mapping++] = (config.get(catAmulet, "allowAmuletModifiers", true).getBoolean(true));

		mapping = 0;
		amuletInts[mapping++] = MathHelper.clamp_int((config.get(catAmulet, "maxAmuletSlots", 3).getInt(3)), 1, 3);
		amuletInts[mapping++] = MathHelper.clamp_int(config.get(catAmulet, "overallAmuletRarity", 5).getInt(5), 3, 250);
		amuletInts[mapping++] = MathHelper.clamp_int(config.get(catAmulet, "amuletReleaseRarity", 5).getInt(5), 3, 250);
		amuletInts[mapping++] = MathHelper.clamp_int(config.get(catAmulet, "modifierChance", 54).getInt(54), 1, 100);
		amuletInts[mapping++] = MathHelper.clamp_int(config.get(catAmulet, "modifierChance2", 79).getInt(79), 1, 100);
		amuletInts[mapping++] = MathHelper.clamp_int(config.get(catAmulet, "modifierChance3", 89).getInt(89), 1, 100);

		mapping = 0;
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectPeace", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectYeti", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectClaymation", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectChicken", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectBlacksmith", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectCreeper", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectZombie", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectSkeleton", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectIce", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectSnowGolem", true).getBoolean(true));
		normalAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectIronGolem", true).getBoolean(true));

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
		cursedAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectEnderman", true).getBoolean(true));

		for (i = 0; i + mapping < cursedAmuletConfigs.length; i++)
		{
			cursedAmuletConfigs[i + mapping] = false;
		}

		mapping = 0;
		epicAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectTime", true).getBoolean(true));
		epicAmuletConfigs[mapping++] = (config.get(catAmulet, "amuletEffectWither", true).getBoolean(true));

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
		biomeIDs[mapping++] = (config.get(catDimension, "biomeTaintedLowlandsID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeTaintedRisesID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeTaintedScarlandsID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeTaintedIslesID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		biomeIDs[mapping++] = (config.get(catDimension, "biomeSynapseID", getOpenIDForBiome(biomeIDs[mapping - 2] + 1)).getInt(getOpenIDForBiome(biomeIDs[mapping - 2] + 1)));
		
		config.addCustomCategoryComment(catDimension, "Set the various biome IDs in the Dimension, including the Dimension's own ID, also set if the Dimension should stay loaded.");

		mapping = 0;
		blanketDoom[mapping++] = (config.get(catDoom, "allowDoomsdays", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowInfluenceDoomsdays", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowCrisisDoomsdays", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowOverflowDoomsdays", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowWorldShaperDoomsdays", true).getBoolean(true));
		blanketDoom[mapping++] = (config.get(catDoom, "allowCombinationDoomsdays", true).getBoolean(true));
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
		doomInts[mapping++] = clampPositive(config.get(catDoom, "maxDoomAmount", 500).getInt(500));
		doomInts[mapping++] = clampPositive(config.get(catDoom, "doomRechargeRate", 1).getInt(1));
		doomInts[mapping++] = clampPositive(config.get(catDoom, "doomConsumeRarity", 3).getInt(3));
		doomInts[mapping++] = clampPositive(config.get(catDoom, "cooldownDefuseRarity", 5).getInt(5));
		doomInts[mapping++] = clampPositive(config.get(catDoom, "doomConsumeRefillAmount", 50).getInt(50));
		doomInts[mapping++] = clampPositive(config.get(catDoom, "cooldownDefuseRefillAmount", 30).getInt(30));
		doomInts[mapping++] = MathHelper.clamp_int(config.get(catDoom, "backlashChance", 5).getInt(5), 1, 100);
		doomInts[mapping++] = MathHelper.clamp_int(config.get(catDoom, "crucialMomentChance", 5).getInt(5), 1, 100);
		doomInts[mapping++] = clampPositive(config.get(catDoom, "doomConsumeAmount", 100).getInt(100));
		doomInts[mapping++] = clampPositive(config.get(catDoom, "maxDoomMinimumAmount", 100).getInt(100));
		
		mapping = 1;
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayDecayAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayHuntersInstinctAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayToxicityAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayBerserkerAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayPiercingLightAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayNatureDrainAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayPoisonBreakAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdaySnipeAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayRapidFireAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayPulseAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayLightShoveAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayFearAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayHarmonizerAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayRavageAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayTormentAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayBeastlyImpulsesAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdaySuicidalTendenciesAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayReaperLaughAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayRealityAlterAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdaySkullCrusherAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayMinerSkillsAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayFreezeAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayMoonlightSonataAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayFlightOfTheValkyriesAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayTitanfallAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayBloodlustAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayPermafrostAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayPurgeAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayLightningRushAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayMarionetteAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayMindcrackAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayGrowthSpurtAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayBlizzardAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayAsphyxiateAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayFireRainAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayDragonsRoarAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayFirestormAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayShotgunAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayGuardianAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdayHardenAllow", true).getBoolean(true));
		doomsdayAllow[mapping++] = (config.get(catDoom, "doomsdaySharpenAllow", true).getBoolean(true));
		
		mapping = 1;
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayDecayCooldown", 20).getInt(20));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayHuntersInstinctCooldown", 25).getInt(25));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayToxicityCooldown", 15).getInt(15));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayBerserkerCooldown", 15).getInt(15));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayPiercingLightCooldown", 30).getInt(30));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayNatureDrainCooldown", 6).getInt(6));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayPoisonBreakCooldown", 10).getInt(10));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdaySnipeCooldown", 55).getInt(55));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayRapidFireCooldown", 3).getInt(3));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayPulseCooldown", 6).getInt(6));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayLightShoveCooldown", 1).getInt(1));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayFearCooldown", 20).getInt(20));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayHarmonizerCooldown", 30).getInt(30));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayRavageCooldown", 35).getInt(35));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayTormentCooldown", 20).getInt(20));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayBeastlyImpulsesCooldown", 50).getInt(50));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdaySuicidalTendenciesCooldown", 4).getInt(4));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayReaperLaughCooldown", 3).getInt(3));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayRealityAlterCooldown", 12).getInt(12));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdaySkullCrusherCooldown", 15).getInt(15));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayMinerSkillsCooldown", 20).getInt(20));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayFreezeCooldown", 30).getInt(30));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayMoonlightSonataCooldown", 60).getInt(60));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayFlightOfTheValkyriesCooldown", 10).getInt(10));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayTitanfallCooldown", 10).getInt(10));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayBloodlustCooldown", 30).getInt(30));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayPermafrostCooldown", 5).getInt(5));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayPurgeCooldown", 4).getInt(4));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayLightningRushCooldown", 6).getInt(6));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayMarionetteCooldown", 3).getInt(3));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayMindcrackCooldown", 60).getInt(60));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayGrowthSpurtCooldown", 10).getInt(10));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayBlizzardCooldown", 6).getInt(6));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayAsphyxiateCooldown", 3).getInt(3));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayFireRainCooldown", 5).getInt(5));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayDragonsRoarCooldown", 15).getInt(15));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayFirestormCooldown", 8).getInt(8));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayShotgunCooldown", 5).getInt(5));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayGuardianCooldown", 50).getInt(50));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdayHardenCooldown", 4).getInt(4));
		doomsdayCooldowns[mapping++] = clampPositive(config.get(catDoom, "doomsdaySharpenCooldown", 6).getInt(6));
		
		mapping = 1;
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayDecayCost", 40).getInt(40));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayHuntersInstinctCost", 60).getInt(60));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayToxicityCost", 40).getInt(40));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayBerserkerCost", 50).getInt(50));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayPiercingLightCost", 60).getInt(60));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayNatureDrainCost", 12).getInt(12));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayPoisonBreakCost", 30).getInt(30));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdaySnipeCost", 90).getInt(90));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayRapidFireCost", 8).getInt(8));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayPulseCost", 10).getInt(10));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayLightShoveCost", 3).getInt(3));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayFearCost", 30).getInt(30));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayHarmonizerCost", 40).getInt(40));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayRavageCost", 55).getInt(55));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayTormentCost", 45).getInt(45));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayBeastlyImpulsesCost", 60).getInt(60));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdaySuicidalTendenciesCost", 20).getInt(20));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayReaperLaughCost", 16).getInt(16));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayRealityAlterCost", 40).getInt(40));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdaySkullCrusherCost", 50).getInt(50));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayMinerSkillsCost", 30).getInt(30));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayFreezeCost", 30).getInt(30));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayMoonlightSonataCost", 1).getInt(1));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayFlightOfTheValkyriesCost", 10).getInt(10));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayTitanfallCost", 5).getInt(5));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayBloodlustCost", 80).getInt(80));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayPermafrostCost", 6).getInt(6));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayPurgeCost", 5).getInt(5));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayLightningRushCost", 8).getInt(8));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayMarionetteCost", 3).getInt(3));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayMindcrackCost", 45).getInt(45));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayGrowthSpurtCost", 50).getInt(50));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayBlizzardCost", 10).getInt(10));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayAsphyxiateCost", 3).getInt(3));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayFireRainCost", 8).getInt(8));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayDragonsRoarCost", 25).getInt(25));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayFirestormCost", 10).getInt(10));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayShotgunCost", 10).getInt(10));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayGuardianCost", 75).getInt(75));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdayHardenCost", 60).getInt(60));
		doomsdayCosts[mapping++] = clampPositive(config.get(catDoom, "doomsdaySharpenCost", 75).getInt(75));
		
		mapping = 0;
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "beastlyClaws-Combo", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "blindingLight-SolarBombs", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "blindingLight-Burn", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "blindingLight-ProjectileDeflect", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "butcher-CriticalKnockback", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "butcher-KnockbackResistanceBuff", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "butcher-WeaknessDebuff", true).getBoolean(true)); //6
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "celestialAegis-DamageReduction", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "celestialLongbow-Teleport", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "dragonFang-Burn", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "dragonFang-LargeFireball", true).getBoolean(true)); //10
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "dragonFang-Extinguish", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "frozenLightning-SlowdownHit", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "frozenLightning-LightningStrike", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "frozenLightning-Icicles", true).getBoolean(true)); //14
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "gravitySpike-Launch", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "guiltyThorn-PoisonStun", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "harmonyBell-HarmonyHit", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "harmonyBell-Healing", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "mourningStar-SelfDestruct", true).getBoolean(true)); //19
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "mourningStar-SightExplosion", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "paranoia-FearSubmissionHit", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "paranoia-DarkEnergySpray", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "paranoia-SingleDarkEnergy", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "pitchBlack-Throw", true).getBoolean(true)); //24
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "reaperScythe-SmallPumpkinbomb", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "reaperScythe-LargePumpkinbomb", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "splinter-RandomDirectionHit", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "splinter-AOERandomDirectionHit", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "thardus-SlownessHit", true).getBoolean(true)); //29
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "thardus-Icicles", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "titan-LightningStrikeHit", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "titan-LightningChain", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "titan-LightningAbsorb", true).getBoolean(true)); //33
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "witheringAxe-WitherHit", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "witheringAxe-WitherSkull", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "witheringAxe-BlueWitherSkull", true).getBoolean(true));
		nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "dragonFang-Flamethrower", true).getBoolean(true)); //37
		//nonDoomsdayAbilities[mapping++] = (config.get(catDoom, "", true).getBoolean(true));
		
		mapping = 0;
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "beastlyClaws-ComboCost", 0).getInt(0));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "blindingLight-SolarBombsCost", 15).getInt(15));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "blindingLight-BurnCost", 10).getInt(10));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "blindingLight-ProjectilDeflectCost", 5).getInt(5));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "butcher-CriticalKnockbackCost", 1).getInt(1));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "butcher-KnockbackResistanceBuffCost", 0).getInt(0));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "butcher-WeaknessDebuffCost", 0).getInt(0));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "celestialAegis-DamageReductionCost", 0).getInt(0));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "celestialLongbow-TeleportCost", 0).getInt(0));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "dragonFang-BurnCost", 1).getInt(1));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "dragonFang-LargeFireballCost", 10).getInt(10));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "dragonFang-ExtinguishCost", 1).getInt(1));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "frozenLightning-SlowdownHitCost", 3).getInt(3));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "frozenLightning-LightningStrikeCost", 20).getInt(20));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "frozenLightning-IciclesCost", 3).getInt(3));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "gravitySpike-LaunchCost", 7).getInt(7));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "guiltyThorn-PoisonStunCost", 10).getInt(10));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "harmonyBell-HarmonyHitCost", 3).getInt(3));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "harmonyBell-HealingCost", 1).getInt(1));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "mourningStar-SelfDestructCost", 25).getInt(25));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "mourningStar-SightExplosionCost", 30).getInt(30));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "paranoia-FearSubmissionHitCost", 10).getInt(10));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "paranoia-DarkEnergySprayCost", 15).getInt(15));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "paranoia-SingleDarkEnergyCost", 5).getInt(5));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "pitchBlack-ThrowCost", 5).getInt(5));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "reaperScythe-SmallPumpkinbombCost", 5).getInt(5));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "reaperScythe-LargePumpkinbombCost", 15).getInt(15));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "splinter-RandomDirectionHitCost", 3).getInt(3));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "splinter-AOERandomDirectionHitCost", 10).getInt(10));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "thardus-SlownessHitCost", 5).getInt(5));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "thardus-IciclesCost", 5).getInt(5));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "titan-LightningStrikeHitCost", 10).getInt(10));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "titan-LightningChainCost", 20).getInt(20));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "titan-LightningAbsorbCost", 5).getInt(5));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "witheringAxe-WitherHitCost", 5).getInt(5));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "witheringAxe-WitherSkullCost", 5).getInt(5));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "witheringAxe-BlueWitherSkullCost", 15).getInt(15));
		nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "dragonFang-FlamethrowerCost", 3).getInt(3));
		//nonDoomsdayAbilityCosts[mapping++] = clampPositive(config.get(catDoom, "", 0).getInt(0));

		config.addCustomCategoryComment(catDoom, "Set various aspects of Doom and Doomsdays, refill amounts scale to total doom amount. Set each Doomsday and weapon ability's cost and allowances.");

		blanketEnchant[0] = (config.get(catEnchant, "allowWeaponEnchantments", true).getBoolean(true));
		blanketEnchant[1] = (config.get(catEnchant, "allowArmorEnchantments", true).getBoolean(true));

		mapping = 0;
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "decayID", getOpenIDForEnchant(64)).getInt(getOpenIDForEnchant(64)));
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
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "runeBreakID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "runeBreakAllow", true).getBoolean(true));
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "reachID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "reachAllow", true).getBoolean(true));
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "unbreakableID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "unbreakableAllow", true).getBoolean(true));
		weaponEnchantIDs[mapping] = (config.get(catEnchant, "rustID", getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(weaponEnchantIDs[mapping - 1] + 1)));
		weaponEnchantConfigs[mapping++] = (config.get(catEnchant, "rustAllow", true).getBoolean(true));

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
		armorEnchantIDs[mapping] = (config.get(catEnchant, "runeWalkerID", getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)));
		armorEnchantConfigs[mapping++] = (config.get(catEnchant, "runeWalkerAllow", true).getBoolean(true));
		armorEnchantIDs[mapping] = (config.get(catEnchant, "luminescenceID", getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)).getInt(getOpenIDForEnchant(armorEnchantIDs[mapping - 1] + 1)));
		armorEnchantConfigs[mapping++] = (config.get(catEnchant, "luminescenceAllow", true).getBoolean(true));

		for (i = 0; i + mapping < armorEnchantConfigs.length; i++)
		{
			armorEnchantConfigs[i + mapping] = false;
		}

		config.addCustomCategoryComment(catEnchant, "Set whether specific Enchantments are allowed, also can choose whether an enchantment type is allowed, set their IDs as well.");

		blanketMob[0] = (config.get(catMobs, "allowNormalMobs", true).getBoolean(true));
		blanketMob[1] = (config.get(catMobs, "allowMiniBosses", true).getBoolean(true));
		blanketMob[2] = (config.get(catMobs, "allowBosses", true).getBoolean(true));
		blanketMob[3] = (config.get(catMobs, "allowOverworldBossSpawns", false).getBoolean(false));
		blanketMob[4] = (config.get(catMobs, "allowExtraBossLoot", true).getBoolean(true));
		blanketMob[5] = (config.get(catMobs, "allowVictoryBuffs", true).getBoolean(true));
		blanketMob[6] = (config.get(catMobs, "allowCorruptionDamage", true).getBoolean(true));
		blanketMob[7] = (config.get(catMobs, "allowMobTransformation", true).getBoolean(true));
		blanketMob[8] = (config.get(catMobs, "allowMobHealthScaling", true).getBoolean(true));

		mobInts[0] = MathHelper.clamp_int(config.get(catMobs, "overallMobCommonDropChance", 25).getInt(25), 1, 200);
		mobInts[1] = MathHelper.clamp_int(config.get(catMobs, "overallMobRareDropChance", 5).getInt(5), 1, 100);
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
		mobsSC[mapping] = (config.get(catMobs, "pumpkinheadSpawnChance", 25).getInt(25));
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
		mobsSC[mapping] = (config.get(catMobs, "wispSpawnChance", 15).getInt(15));
		mobConfigs[mapping++] = (config.get(catMobs, "wispAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "abominationSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "abominationAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "erkelSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "erkelAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "sirvSpawnChance", 50).getInt(50));
		mobConfigs[mapping++] = (config.get(catMobs, "sirvAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "psygoteSpawnChance", 5).getInt(5));
		mobConfigs[mapping++] = (config.get(catMobs, "psygoteAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "lockbotSpawnChance", 25).getInt(25));
		mobConfigs[mapping++] = (config.get(catMobs, "lockbotAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "nanoSwarmSpawnChance", 5).getInt(5));
		mobConfigs[mapping++] = (config.get(catMobs, "nanoSwarmAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "circuitGolemSpawnChance", 5).getInt(5));
		mobConfigs[mapping++] = (config.get(catMobs, "circuitGolemAllow", true).getBoolean(true));
		mobsSC[mapping] = (config.get(catMobs, "snowGolemSpawnChance",40).getInt(40));
		mobConfigs[mapping++] = (config.get(catMobs, "snowGolemAllow", true).getBoolean(true));

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
		miniBossSC[mapping] = (config.get(catMobs, "aegarSpawnChance", 5).getInt(5));
		miniBossConfigs[mapping++] = (config.get(catMobs, "aegarAllow", true).getBoolean(true));

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
		bossSC[mapping] = (config.get(catMobs, "enyvilSpawnChance", 5).getInt(5));
		bossConfigs[mapping++] = (config.get(catMobs, "enyvilAllow", true).getBoolean(true));
		bossSC[mapping] = (config.get(catMobs, "claymationSpawnChance", 5).getInt(5));
		bossConfigs[mapping++] = (config.get(catMobs, "claymationAllow", true).getBoolean(true));
		bossSC[mapping] = (config.get(catMobs, "overlordSpawnChance", 5).getInt(5));
		bossConfigs[mapping++] = (config.get(catMobs, "overlordAllow", true).getBoolean(true));

		for (i = 0; i + mapping < bossConfigs.length; i++) //sets all unused slots in the array to false
		{
			bossConfigs[mapping + i] = false;
		}

		for (i = 0; i < mobsSC.length; i++) //clamps all of the spawn chances positive
		{
			mobsSC[i] = clampPositive(mobsSC[i]);
			if (i < miniBossSC.length) miniBossSC[i] = clampPositive(miniBossSC[i]);
			if (i < bossSC.length) bossSC[i] = clampPositive(bossSC[i]);
		}
		
		mapping = 0;
		mobStats[mapping++] = (config.get(catMobs, "jabbaStats", new double[] {40.0, 0.275, 5.5, 32.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "jannaStats", new double[] {20.0, 0.325, 4.5, 32.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "plagueStats", new double[] {4.0, 0.235, 1.0, 16.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "gragulStats", new double[] {5.0, 0.350, 5.0, 32.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "minotaurStats", new double[] {32.0, 0.350, 7.0, 32.0, 0.5, 6}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "inklingStats", new double[] {16.0, 0.230, 1.0, 32.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "ragrStats", new double[] {65.0, 0.380, 7.0, 32.0, 1.0, 10}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "pumpkinheadStats", new double[] {60.0, 0.275, 6.0, 32.0, 0.0, 15}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "tragicNekoStats", new double[] {80.0, 0.335, 6.0, 32.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "toxStats", new double[] {40.0, 0.050, 8.0, 64.0, 1.0, 16}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "poxStats", new double[] {30.0, 0.050, 4.0, 64.0, 0.7, 10}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "cryseStats", new double[] {35.0, 0.285, 4.0, 48.0, 0.0, 4}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "starCryseStats", new double[] {55.0, 0.315, 4.0, 48.0, 0.0, 4}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "norVoxStats", new double[] {30.0, 0.390, 4.0, 32.0, 0.25, 8}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "starVoxStats", new double[] {40.0, 0.390, 4.0, 32.0, 0.25, 16}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "goldenPirahStats", new double[] {25.0, 0.450, 7.5, 16.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "pirahStats", new double[] {10.0, 0.450, 3.0, 16.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "stinStats", new double[] {40.0, 0.246, 10.0, 32.0, 0.5, 6}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "stinBabyStats", new double[] {16.0, 0.346, 6.0, 32.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "greaterStinStats", new double[] {80.0, 0.276, 14.0, 24.0, 1.0, 12}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "wispStats", new double[] {8.0, 0.476, 1.0, 16.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "abominationStats", new double[] {45.0, 0.276, 7.0, 32.0, 0.5, 4}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "erkelStats", new double[] {16.0, 0.476, 1.0, 16.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "sirvStats", new double[] {8.0, 0.375, 14.0, 64.0, 0.5, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "psygoteStats", new double[] {64.0, 0.290, 8.0, 32.0, 0.65, 16}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "lockbotStats", new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0}).getDoubleList());
		mobStats[mapping++] = (config.get(catMobs, "nanoSwarmStats", new double[] {6.0, 0.335, 2.0, 64.0, 0.0, 0}).getDoubleList());
		
		mapping = 0;
		miniBossStats[mapping++] = (config.get(catMobs, "jarraStats", new double[] {70.0, 0.360, 6.5, 64.0, 0.0, 0}).getDoubleList());
		miniBossStats[mapping++] = (config.get(catMobs, "kragulStats", new double[] {8.0, 0.380, 5.0, 5.0, 32.0, 0}).getDoubleList());
		miniBossStats[mapping++] = (config.get(catMobs, "magmoxStats", new double[] {75.0, 0.050, 15.0, 64.0, 1.0, 20}).getDoubleList());
		miniBossStats[mapping++] = (config.get(catMobs, "megaCryseStats", new double[] {50.0, 0.310, 6.0, 48.0, 1.0, 10}).getDoubleList());
		miniBossStats[mapping++] = (config.get(catMobs, "voxStellarumStats", new double[] {150.0, 0.460, 4.0, 64.0, 0.2, 16}).getDoubleList());
		miniBossStats[mapping++] = (config.get(catMobs, "stinKingStats", new double[] {100.0, 0.226, 20.0, 32.0, 2.0, 20}).getDoubleList());
		miniBossStats[mapping++] = (config.get(catMobs, "stinQueenStats", new double[] {160.0, 0.186, 12.0, 24, 2.0, 10}).getDoubleList());
		miniBossStats[mapping++] = (config.get(catMobs, "aegarStats", new double[] {150.0, 0.185, 26.0, 32.0, 2.5, 24}).getDoubleList());
		
		mapping = 0;
		bossStats[mapping++] = (config.get(catMobs, "apisStats", new double[] {160.0, 0.325, 8.0, 32.0, 1.0, 15}).getDoubleList());
		bossStats[mapping++] = (config.get(catMobs, "deathReaperStats", new double[] {220.0, 0.350, 16.0, 32.0, 1.0, 20}).getDoubleList());
		bossStats[mapping++] = (config.get(catMobs, "kitsuneStats", new double[] {50.0, 0.420, 6.0, 64.0, 0.0, 0}).getDoubleList());
		bossStats[mapping++] = (config.get(catMobs, "polarisStats", new double[] {120.0, 0.390, 5.0, 64.0, 0.0, 12}).getDoubleList());
		bossStats[mapping++] = (config.get(catMobs, "yetiStats", new double[] {140.0, 0.326, 12.0, 48.0, 2.0, 16}).getDoubleList());
		bossStats[mapping++] = (config.get(catMobs, "timeControllerStats", new double[] {350.0, 0.386, 6.0, 64.0, 0.5, 16}).getDoubleList());
		bossStats[mapping++] = (config.get(catMobs, "enyvilStats", new double[] {450.0, 0.276, 24.0, 48.0, 1.0, 0}).getDoubleList());
		bossStats[mapping++] = (config.get(catMobs, "claymationStats", new double[] {150.0, 0.220, 12.0, 32.0, 1.0, 18}).getDoubleList());
		bossStats[mapping++] = (config.get(catMobs, "overlordCoreStats", new double[] {1000.0, 0.326, 24.0, 64.0, 4.5, 0}).getDoubleList());

		config.addCustomCategoryComment(catMobs, "Set whether specific Mobs are allowed or disable certain groups like Mini-Bosses or Bosses. Stats are: Health, Movement Speed, Attack Damage, Follow Range, Knockback Resistance, Armor Value.");

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
		positivePotionIDs[mapping] = (config.get(catPotion, "convergenceID", getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)));
		positivePotionConfigs[mapping++] = (config.get(catPotion, "convergenceAllow", true).getBoolean(true));
		positivePotionIDs[mapping] = (config.get(catPotion, "divinityID", getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(positivePotionIDs[mapping - 1] + 1)));
		positivePotionConfigs[mapping++] = (config.get(catPotion, "divinityAllow", true).getBoolean(true));
		
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
		negativePotionIDs[mapping] = (config.get(catPotion, "leadFootID", getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)));
		negativePotionConfigs[mapping++] = (config.get(catPotion, "leadFootAllow", true).getBoolean(true));
		negativePotionIDs[mapping] = (config.get(catPotion, "hackedID", getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)).getInt(getOpenIDForPotion(negativePotionIDs[mapping - 1] + 1)));
		negativePotionConfigs[mapping++] = (config.get(catPotion, "hackedAllow", true).getBoolean(true));

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
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowModdedOverworldFlowerGen", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowOverworldSilverfishOregen", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowNetherOreGen", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowOverworldOreGen", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowQuicksandAndDrudgeGen", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowAnimalAndGolemCorruption", true).getBoolean(true));
		blanketVanillaChanges[mapping++] = (config.get(catVanilla, "allowMobBlindnessDebuff", true).getBoolean(true));

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
		miscConfigs[mapping++] = (config.get(catMisc, "allowChallengeScrolls", true).getBoolean(true));
		miscConfigs[mapping++] = (config.get(catMisc, "allowMobStatues", true).getBoolean(true));
		miscConfigs[mapping++] = (config.get(catMisc, "allowAnimatedGui", true).getBoolean(true));
		miscConfigs[mapping++] = (config.get(catMisc, "allowGeneratorItems", true).getBoolean(true));
		miscConfigs[mapping++] = (config.get(catMisc, "allowItemTimeAltering", true).getBoolean(true));
		miscConfigs[mapping++] = (config.get(catMisc, "allowWeaponModels", true).getBoolean(true));

		for (i = 0; i + mapping < miscConfigs.length; i++) miscConfigs[mapping + i] = false;
		
		mapping = 0;
		griefConfigs[mapping++] = config.get(catMisc, "allowNatureDrainDestruction", true).getBoolean(true);
		griefConfigs[mapping++] = config.get(catMisc, "allowRavageDestruction", true).getBoolean(true);
		griefConfigs[mapping++] = config.get(catMisc, "allowFrozenLightningDestruction", true).getBoolean(true);
		griefConfigs[mapping++] = config.get(catMisc, "allowMourningStarDestruction", true).getBoolean(true);
		griefConfigs[mapping++] = config.get(catMisc, "allowTitanDestruction", true).getBoolean(true);

		mapping = 0;
		miscInts[mapping++] = MathHelper.clamp_int(config.get(catMisc, "challengeScrollDropChance", 5).getInt(5), 1, 100);
		miscInts[mapping++] = MathHelper.clamp_int(config.get(catMisc, "mobStatueDropChance", 100).getInt(100), 1, 100);
		miscInts[mapping++] = MathHelper.clamp_int(config.get(catMisc, "guiTransparency", 100).getInt(100), 1, 100);
		miscInts[mapping++] = config.get(catMisc, "guiTextureSkins", 0).getInt(0);
		miscInts[mapping++] = config.get(catMisc, "guiXPosition", 1).getInt(1);
		miscInts[mapping++] = config.get(catMisc, "guiYPosition", 1).getInt(1);

		config.addCustomCategoryComment(catMisc, "Miscellaneous options that don't fit into other categories.");
		
		mapping = 0;
		modifierAmts[mapping++] = config.get(catMod, "claymationUtilitySpeedDebuff", -1.0).getDouble(-1.0);
		modifierAmts[mapping++] = config.get(catMod, "kitsuneSpeedDebuff", -0.5).getDouble(-0.5);
		modifierAmts[mapping++] = config.get(catMod, "timeControllerSpeedBuff", 0.055).getDouble(0.055);
		modifierAmts[mapping++] = config.get(catMod, "yetiSpeedDebuff", -0.5).getDouble(-0.5);
		modifierAmts[mapping++] = config.get(catMod, "aegarSpeedBuff", 0.156).getDouble(0.156);
		modifierAmts[mapping++] = config.get(catMod, "megaCryseNoShieldAttackBuff", 2.0).getDouble(2.0);
		modifierAmts[mapping++] = config.get(catMod, "jabbaLowHealthDamageBoost", 2.5).getDouble(2.5);
		modifierAmts[mapping++] = config.get(catMod, "norVoxSpeedDebuff", -0.5).getDouble(-0.5);
		modifierAmts[mapping++] = config.get(catMod, "psygoteSpeedDebuff", -0.5).getDouble(-0.5);
		modifierAmts[mapping++] = config.get(catMod, "tragicNekoSpeedDebuff", -0.5).getDouble(-0.5); //9
		modifierAmts[mapping++] = config.get(catMod, "dynamicMobHealthBuff", 20.0).getDouble(20.0);
		modifierAmts[mapping++] = config.get(catMod, "dynamicMobHealthDebuff", -20.0).getDouble(-20.0);
		modifierAmts[mapping++] = config.get(catMod, "dynamicBossHealthBuff", 50.0).getDouble(50.0);
		modifierAmts[mapping++] = 0.0; //13, this particular attribute modifier wasn't actually being used so it'll have no value
		modifierAmts[mapping++] = config.get(catMod, "ghastHealthBuff", 30.0).getDouble(30.0);
		modifierAmts[mapping++] = config.get(catMod, "zombieSkeletonCreeperHealthBuff", 10.0).getDouble(10.0);
		modifierAmts[mapping++] = config.get(catMod, "endermanHealthBuff", 20.0).getDouble(20.0);
		modifierAmts[mapping++] = config.get(catMod, "spiderHealthBuff", 8.0).getDouble(8.0);
		modifierAmts[mapping++] = config.get(catMod, "mobBlindnessFollowRangeDebuff", -16.0).getDouble(-16.0); //18
		modifierAmts[mapping++] = config.get(catMod, "hydrationKnockbackResistanceBuff", 1.0).getDouble(1.0);
		modifierAmts[mapping++] = config.get(catMod, "lightningRodAttackBuff", 5.0).getDouble(5.0);
		modifierAmts[mapping++] = config.get(catMod, "moonlightHealthBuff", 10.0).getDouble(10.0);
		modifierAmts[mapping++] = config.get(catMod, "synthesisHealthBuff", 10.0).getDouble(10.0); //22
		modifierAmts[mapping++] = config.get(catMod, "butcherKnockbackResistanceBuff", 1.0).getDouble(1.0);
		
		config.addCustomCategoryComment(catMod, "These are here due to the addition of the mob stats, in case these need to be modified to fit with the input stats. These could also be set to 0 if you want to nullify them.");

		if (config.hasChanged()) config.save();
		postProcessConfigs();
	}

	public static void postProcessConfigs()
	{
		TragicItems.textureRes = "_lowRes";
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
			
			if (!blanketDoom[6])
			{
				for (i = 0; i < nonDoomsdayAbilities.length; i++)
				{
					nonDoomsdayAbilities[i] = false;
				}
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

		initializeAllVariables();
	}

	public static void initializeAllVariables()
	{
		int mapping = 0;
		allowAchievements = blanketConfigs[mapping++];
		allowAmulets = blanketConfigs[mapping++];
		allowDimension = blanketConfigs[mapping++];
		allowDoom = blanketConfigs[mapping++];
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
		showAmuletStatus = blanketAmulet[mapping++];
		allowAmuletModifiers = blanketAmulet[mapping++];

		mapping = 0;
		maxAmuletSlots = amuletInts[mapping++];
		overallAmuletRarity = amuletInts[mapping++];
		amuletReleaseRarity = amuletInts[mapping++];
		amuletModifierChance = amuletInts[mapping++];
		amuletModifierChance2 = amuletInts[mapping++];
		amuletModifierChance3 = amuletInts[mapping++];

		mapping = 0;
		amuPeace = normalAmuletConfigs[mapping++];
		amuYeti = normalAmuletConfigs[mapping++];
		amuClaymation = normalAmuletConfigs[mapping++];
		amuChicken = normalAmuletConfigs[mapping++];
		amuBlacksmith = normalAmuletConfigs[mapping++];
		amuCreeper = normalAmuletConfigs[mapping++];
		amuZombie = normalAmuletConfigs[mapping++];
		amuSkeleton = normalAmuletConfigs[mapping++];
		amuIce = normalAmuletConfigs[mapping++];
		amuSnowGolem = normalAmuletConfigs[mapping++];
		amuIronGolem = normalAmuletConfigs[mapping++];

		mapping = 0;
		amuKitsune = cursedAmuletConfigs[mapping++];
		amuMartyr = cursedAmuletConfigs[mapping++];
		amuPiercing = cursedAmuletConfigs[mapping++];
		amuApis = cursedAmuletConfigs[mapping++];
		amuSunken = cursedAmuletConfigs[mapping++];
		amuEnderman = cursedAmuletConfigs[mapping++];

		amuTime = epicAmuletConfigs[0];
		amuWither = epicAmuletConfigs[1];

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
		idTaintedLowlands = biomeIDs[mapping++];
		idTaintedRises = biomeIDs[mapping++];
		idTaintedScarlands = biomeIDs[mapping++];
		idTaintedIsles = biomeIDs[mapping++];

		mapping = 0;
		allowDoomsdays = blanketDoom[mapping++];
		allowInfluenceDoomsday = blanketDoom[mapping++];
		allowCrisisDoomsday = blanketDoom[mapping++];
		allowOverflowDoomsday = blanketDoom[mapping++];
		allowWorldShaperDoomsday = blanketDoom[mapping++];
		allowCombinationDoomsday = blanketDoom[mapping++];
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
		doomConsumeAmount = doomInts[mapping++];
		maxDoomMinimum = doomInts[mapping++];

		allowWeaponEnchants = blanketEnchant[0];
		allowArmorEnchants = blanketEnchant[1];

		mapping = 0;
		idDecay = weaponEnchantIDs[mapping];
		allowDecay = weaponEnchantConfigs[mapping++];
		idSlay = weaponEnchantIDs[mapping];
		allowSlay = weaponEnchantConfigs[mapping++];
		idAbsolve= weaponEnchantIDs[mapping];
		allowAbsolve = weaponEnchantConfigs[mapping++];
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
		idRuneBreak = weaponEnchantIDs[mapping];
		allowRuneBreak = weaponEnchantConfigs[mapping++];
		idReach = weaponEnchantIDs[mapping];
		allowReach = weaponEnchantConfigs[mapping++];
		idUnbreakable = weaponEnchantIDs[mapping];
		allowUnbreakable = weaponEnchantConfigs[mapping++];
		idRust = weaponEnchantIDs[mapping];
		allowRust = weaponEnchantConfigs[mapping++];

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
		idRuneWalker = armorEnchantIDs[mapping];
		allowRuneWalker = armorEnchantConfigs[mapping++];
		idLuminescence = armorEnchantIDs[mapping];
		allowLuminescence = armorEnchantConfigs[mapping++];

		allowNormalMobs = blanketMob[0];
		allowMiniBosses = blanketMob[1];
		allowBosses = blanketMob[2];
		allowBossOverworldSpawns = blanketMob[3];
		allowExtraBossLoot = blanketMob[4];
		allowVictoryBuffs = blanketMob[5];
		allowCorruptionDamage = blanketMob[6];
		allowMobTransformation = blanketMob[7];
		allowDynamicHealthScaling = blanketMob[8];

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
		erkelSC = mobsSC[mapping];
		allowErkel = mobConfigs[mapping++];
		sirvSC = mobsSC[mapping];
		allowSirv = mobConfigs[mapping++];
		psygoteSC = mobsSC[mapping];
		allowPsygote = mobConfigs[mapping++];
		lockbotSC = mobsSC[mapping];
		allowLockbot = mobConfigs[mapping++];
		nanoSwarmSC = mobsSC[mapping];
		allowNanoSwarm = mobConfigs[mapping++];
		circuitGolemSC = mobsSC[mapping];
		allowCircuitGolem = mobConfigs[mapping++];
		snowGolemSC = mobsSC[mapping];
		allowSnowGolem = mobConfigs[mapping++];

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
		aegarSC = miniBossSC[mapping];
		allowAegar = miniBossConfigs[mapping++];

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
		enyvilSC = bossSC[mapping];
		allowEnyvil = bossConfigs[mapping++];
		claymationSC = bossSC[mapping];
		allowClaymation = bossConfigs[mapping++];
		overlordSC = bossSC[mapping];
		allowOverlord = bossConfigs[mapping++];
		
		mapping = 0;
		jabbaStats = mobStats[mapping++];
		jannaStats = mobStats[mapping++];
		plagueStats = mobStats[mapping++];
		gragulStats = mobStats[mapping++];
		minotaurStats = mobStats[mapping++];
		inklingStats = mobStats[mapping++];
		ragrStats = mobStats[mapping++];
		pumpkinheadStats = mobStats[mapping++];
		tragicNekoStats = mobStats[mapping++];
		toxStats = mobStats[mapping++];
		poxStats = mobStats[mapping++];
		cryseStats = mobStats[mapping++];
		starCryseStats = mobStats[mapping++];
		norVoxStats = mobStats[mapping++];
		starVoxStats = mobStats[mapping++];
		goldenPirahStats = mobStats[mapping++];
		pirahStats = mobStats[mapping++];
		stinStats = mobStats[mapping++];
		stinBabyStats = mobStats[mapping++];
		greaterStinStats = mobStats[mapping++];
		wispStats = mobStats[mapping++];
		abominationStats = mobStats[mapping++];
		erkelStats = mobStats[mapping++];
		sirvStats = mobStats[mapping++];
		psygoteStats = mobStats[mapping++];
		lockbotStats = mobStats[mapping++];
		nanoSwarmStats = mobStats[mapping++];
		
		mapping = 0;
		jarraStats = miniBossStats[mapping++];
		kragulStats = miniBossStats[mapping++];
		magmoxStats = miniBossStats[mapping++];
		megaCryseStats = miniBossStats[mapping++];
		voxStellarumStats = miniBossStats[mapping++];
		stinKingStats = miniBossStats[mapping++];
		stinQueenStats = miniBossStats[mapping++];
		aegarStats = miniBossStats[mapping++];
		
		mapping = 0;
		apisStats = bossStats[mapping++];
		deathReaperStats = bossStats[mapping++];
		kitsuneStats = bossStats[mapping++];
		polarisStats = bossStats[mapping++];
		yetiStats = bossStats[mapping++];
		timeControllerStats = bossStats[mapping++];
		enyvilStats = bossStats[mapping++];
		claymationStats = bossStats[mapping++];
		overlordCoreStats = bossStats[mapping++];

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
		idConvergence = positivePotionIDs[mapping];
		allowConvergence = positivePotionConfigs[mapping++];
		idDivinity = positivePotionIDs[mapping];
		allowDivinity = positivePotionConfigs[mapping++];

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
		idLeadFoot = negativePotionIDs[mapping];
		allowLeadFoot = negativePotionConfigs[mapping++];
		idHacked = negativePotionIDs[mapping];
		allowHacked = negativePotionConfigs[mapping++];

		mapping = 0;
		allowVanillaMobBuffs = blanketVanillaChanges[mapping++];
		allowExtraMobEffects = blanketVanillaChanges[mapping++];
		allowAnimalRetribution = blanketVanillaChanges[mapping++];
		allowMobModdedArmorAndEnchants = blanketVanillaChanges[mapping++];
		allowRespawnPunishment = blanketVanillaChanges[mapping++];
		allowExtraExplosiveEffects = blanketVanillaChanges[mapping++];
		allowExtraOverworldFlowers = blanketVanillaChanges[mapping++];
		allowOverworldSilverfishGen = blanketVanillaChanges[mapping++];
		allowNetherOreGen = blanketVanillaChanges[mapping++];
		allowOverworldOreGen = blanketVanillaChanges[mapping++];
		allowQuicksandGen = blanketVanillaChanges[mapping++];
		allowAnimalGolemCorruption = blanketVanillaChanges[mapping++];
		allowMobBlindnessDebuff = blanketVanillaChanges[mapping++];

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
		allowChallengeScrolls = miscConfigs[mapping++];
		allowMobStatueDrops = miscConfigs[mapping++];
		allowAnimatedGui = miscConfigs[mapping++];
		allowGeneratorItems = miscConfigs[mapping++];
		allowItemTimeAltering = miscConfigs[mapping++];
		allowWeaponModels = miscConfigs[mapping++];

		mapping = 0;
		challengeScrollDropChance = miscInts[mapping++];
		mobStatueDropChance = miscInts[mapping++];
		guiTransparency = miscInts[mapping++];
		guiTexture = miscInts[mapping++];
		guiX = miscInts[mapping++];
		guiY = miscInts[mapping++];
	}

	public static void disablePotions()
	{
		blanketConfigs[6] = false;
		postProcessConfigs();
	}

	private static int getOpenIDForEnchant(int configId)
	{
		if (configId < Enchantment.enchantmentsList.length && Enchantment.enchantmentsList[configId] != null)
		{
			while (Enchantment.enchantmentsList[configId] != null)
			{
				configId++;

				if (configId >= Enchantment.enchantmentsList.length)
				{
					configId -= Enchantment.enchantmentsList.length;
				}
			}
		}
		else if (configId >= Enchantment.enchantmentsList.length)
		{
			configId -= Enchantment.enchantmentsList.length;
			
			while (Enchantment.enchantmentsList[configId] != null)
			{
				configId++;

				if (configId >= Enchantment.enchantmentsList.length)
				{
					configId -= Enchantment.enchantmentsList.length;
				}
			}
		}

		return configId;
	}

	private static int getOpenIDForPotion(int configId)
	{		
		if (configId < Potion.potionTypes.length && Potion.potionTypes[configId] != null)
		{
			while (Potion.potionTypes[configId] != null)
			{
				configId++;
				if (configId >= Potion.potionTypes.length)
				{
					configId -= Potion.potionTypes.length;
				}
			}
		}
		else if (configId >= Potion.potionTypes.length)
		{
			configId -= Potion.potionTypes.length;
			
			while (Potion.potionTypes[configId] != null)
			{
				configId++;

				if (configId >= Potion.potionTypes.length)
				{
					configId -= Potion.potionTypes.length;
				}
			}
		}
		
		return configId;
	}

	private static int getOpenIDForBiome(int configId)
	{
		if (configId < BiomeGenBase.getBiomeGenArray().length && BiomeGenBase.getBiomeGenArray()[configId] != null)
		{
			while (BiomeGenBase.getBiomeGenArray()[configId] != null)
			{
				configId++;
				if (configId >= BiomeGenBase.getBiomeGenArray().length)
				{
					configId -= BiomeGenBase.getBiomeGenArray().length;
				}
			}
		}
		else if (configId >= BiomeGenBase.getBiomeGenArray().length)
		{
			configId -= BiomeGenBase.getBiomeGenArray().length;
			
			while (BiomeGenBase.getBiomeGenArray()[configId] != null)
			{
				configId++;

				if (configId >= BiomeGenBase.getBiomeGenArray().length)
				{
					configId -= BiomeGenBase.getBiomeGenArray().length;
				}
			}
		}
		return configId;
	}
	
	private static int clampPositive(int i) {
		return i < 0 ? 0 : i;
	}
}
