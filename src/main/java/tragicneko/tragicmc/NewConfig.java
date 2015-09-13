package tragicneko.tragicmc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class NewConfig {

	private static final String CAT_MASTER = "Master Configs";
	private static final String CAT_BLANKET = "Blanket Configs";
	private static final String CAT_ACHIEVE = "Achievements";
	private static final String CAT_AMULET = "Amulets";
	private static final String CAT_AMUEFFECT = "Amulet Effects";
	private static final String CAT_DIMENSION = "Dimension";
	private static final String CAT_BIOME = "Dimension Biomes";
	private static final String CAT_DOOM = "Doom";
	private static final String CAT_DOOMSDAYS = "Doomsdays";
	private static final String CAT_WEAPON = "Weapons";
	private static final String CAT_ENCHANT = "Enchantments";
	private static final String CAT_MOBS = "Mobs";
	private static final String CAT_MOBSTATS = "Mob Stats";
	private static final String CAT_POTION = "Potions";
	private static final String CAT_VANILLA = "Vanilla Changes";
	private static final String CAT_WORLDGEN = "WorldGen";
	private static final String CAT_MISC = "Miscellaneous";
	private static final String CAT_MODIFIERS = "Attribute Modifiers";
	private static final String CAT_CREATIVE = "Creative Item Options";
	private static final String CAT_CLIENT = "Client-side Only Options";
	private static final String CAT_STRUCTURE = "Structures";
	private static final String CAT_GRIEF = "Weapon Griefing Options";

	//options meant for internal use, for toggling via master configs
	public static boolean allowNonMobItems = true, allowNonMobBlocks = true, allowNetwork = true, allowRecipes = true, allowSurvivalTab = true;

	public static boolean mobsOnlyMode, hardcoreMode, lightweightMode, barebonesMode;
	public static boolean allowAchievements, allowAmulets, allowDimension, allowDoom, allowEnchantments, allowMobs, allowPotions, allowVanillaChanges, allowWorldGen;

	private static boolean[] amuletConfig = new boolean[16];
	public static boolean allowAmuletLeveling, allowAmuletCrafting, requireAmuletSlotUnlock, allowAmuletBossKillCharge, allowAmuletModifiers, allowAmuletDeathDrops;
	public static int amuletMaxSlots, amuletOverallRarity, amuletReleaseRarity, amuletModChance, amuletModChance2, amuletModChance3;
	private static boolean[] amuletEffects = new boolean[48];
	public static boolean amuPeace, amuYeti, amuClaymation, amuChicken, amuBlacksmith, amuCreeper, amuZombie, amuSkeleton, amuIce, amuSnowGolem, amuIronGolem;
	public static boolean amuSpider, amuStin, amuSupernatural, amuFusea, amuLuck, amuKitsune, amuMartyr, amuPiercing, amuApis, amuSunken, amuEnderman, amuPolaris, amuLightning;
	public static boolean amuConsumption, amuUndead, amuEnderDragon, amuTime, amuWither, amuOverlord, amuEnyvil;

	public static boolean sphereGenUsesFilter, eraserUsesFilter, spikeGenUsesFilter, voidPitUsesFilter;
	public static String[] sphereFilter = new String[64], eraserFilter = new String[64], spikeFilter = new String[64], voidPitFilter = new String[64];
	public static double sphereSize, eraserSize, spikeSize, spikeRegression, spikeCutoff, voidPitSize, explosionBaseSize, explosionSizeVariation;

	private static boolean[] dimensionConfig = new boolean[16];
	public static boolean allowSynapse, allowCollision, allowCollisionRespawn, allowSynapseRespawn, keepCollisionLoaded, keepSynapseLoaded, allowSynapseVariants;
	public static int collisionID, collisionProviderID, synapseID, synapseProviderID, collisionBiomeSize, synapseVariantChance;

	public static int idDecayingHills, idDecayingValley, idDecayingWasteland, idDecayingMountains, idPaintedForest, idPaintedPlains, idPaintedHills, idPaintedClearing;
	public static int idAshenMountains, idAshenHills, idAshenBadlands, idStarlitPrarie, idStarlitPlateaus, idStarlitCliffs, idStarlitLowlands, idTaintedSpikes;
	public static int idTaintedLowlands, idTaintedRises, idTaintedScarlands, idTaintedIsles, idHallowedHills, idHallowedForest, idHallowedPrarie, idHallowedCliffs;
	public static int idScorchedWastelands, idScorchedValley, idScorchedScarlands, idCorrodedSteppe, idCorrodedHeights, idCorrodedVeld, idCorrodedRunoff, idCorrodedFallout;
	public static int idFrozenTundra, idFrozenHills, idFrozenDepths, idCrystal, idDarkForest, idDarkForestHills, idDarkMarsh, idSynapseDead, idSynapseCorrupt;
	public static int idSynapse;
	public static int decayingHillsW, decayingValleyW, decayingWastelandW, decayingMountainsW, paintedForestW, paintedPlainsW, paintedHillsW, paintedClearingW;
	public static int ashenMountainsW, ashenHillsW, ashenBadlandsW, starlitPrarieW, starlitPlateausW, starlitCliffsW, starlitLowlandsW, taintedSpikesW, taintedLowlandsW;
	public static int taintedRisesW, taintedScarlandsW, taintedIslesW, hallowedHillsW, hallowedForestW, hallowedPrarieW, hallowedCliffsW, scorchedWastelandsW, scorchedValleyW;
	public static int scorchedScarlandsW, corrodedSteppeW, corrodedHeightsW, corrodedVeldW, corrodedRunoffW, corrodedFalloutW, frozenTundraW, frozenHillsW, frozenDepthsW;
	public static int crystalW, darkForestW, darkForestHillsW, darkMarshW;

	private static boolean[] doomConfig = new boolean[24];
	public static boolean allowDoomsdays, allowInfluenceDoomsdays, allowOverflowDoomsdays, allowCrisisDoomsdays, allowWorldShaperDoomsdays, allowCombinationDoomsdays;
	public static boolean allowNonDoomsdayAbilities, shouldDoomLimitIncrease, allowConsumeRefill, allowDoomPainRecharge, allowNaturalRecharge, allowCrucialMoments, allowBacklash;
	public static boolean allowCooldown, allowDoomKillRecharge, allowCooldownDefuse;
	public static int maxDoomAmount, doomRechargeRate, doomConsumeRarity, cooldownDefuseRarity, consumeRefillAmount, defuseRefillAmount, backlashChance, crucialMomentChance;
	public static int doomConsumeAmount, maxDoomStart, doomRechargeAmount;

	public static boolean[] doomsdayAllow = new boolean[96];
	public static int[] doomsdayCooldown = new int[96];
	public static int[] doomsdayCost = new int[96];
	public static boolean[] doomAbility = new boolean[48];
	public static int[] doomAbilityCost = new int[48];

	public static boolean[] enchantAllow = new boolean[32];
	public static boolean allowDecay, allowSlay, allowAbsolve, allowVampirism, allowLeech, allowConsume, allowDistract, allowMultiply, allowCombustion, allowRuneBreak;
	public static boolean allowReach, allowUnbreakable, allowRust, allowVeteran, allowDeathTouch, allowIgnition, allowToxicity, allowParalysis, allowElasticity, allowAgility;
	public static boolean allowRuneWalker, allowLuminescence;
	public static int idDecay, idSlay, idAbsolve, idVampirism, idLeech, idConsume, idDistract, idMultiply, idCombustion, idRuneBreak, idReach, idUnbreakable, idRust, idVeteran;
	public static int idDeathTouch, idIgnition, idToxicity, idParalysis, idElasticity, idAgility, idRuneWalker, idLuminescence;

	public static boolean[] mobConfig = new boolean[16];
	public static boolean allowNormalMobs, allowMiniBosses, allowBosses, allowBossOverworldSpawns, allowExtraBossLoot, allowMobTransformation;
	public static boolean allowDynamicHealthScaling, allowNonDimensionMobSpawns, allowGroupBuffs, allowEasyBosses, allowMobSounds;
	public static int commonDropRate, rareDropRate, mobTransformationChance, bossDamageCap, groupBuffChance;
	public static boolean[] mobAllow = new boolean[64];
	public static boolean allowJabba, allowJanna, allowPlague, allowGragul, allowMinotaur, allowInkling, allowRagr, allowPumpkinhead, allowTragicNeko, allowTox, allowPox;
	public static boolean allowCryse, allowStarCryse, allowNorVox, allowStarVox, allowPirah, allowStin, allowStinBaby, allowKindlingSpirit, allowAbomination, allowErkel;
	public static boolean allowSirv, allowPsygote, allowLockbot, allowNanoSwarm, allowSnowGolem, allowHunter, allowHarvester, allowArchangel, allowIre;
	public static boolean allowFusea, allowRanmas, allowParasmite, allowAvris;
	public static boolean[] miniBossAllow = new boolean[32];
	public static boolean allowJarra, allowKragul, allowMagmox, allowMegaCryse, allowVoxStellarum, allowGreaterStin, allowStinKing, allowStinQueen, allowAegar, allowVolatileFusea;
	public static boolean[] bossAllow = new boolean[24];
	public static boolean allowApis, allowSkultar, allowKitsunakuma, allowEmpariah, allowTimeController, allowPolaris, allowEnyvil, allowClaymation, allowOverlord;

	public static int jabbaSC, jannaSC, plagueSC, gragulSC, minotaurSC, inklingSC, ragrSC, pumpkinheadSC, tragicNekoSC, toxSC, poxSC, cryseSC, starCryseSC, norVoxSC, starVoxSC;
	public static int pirahSC, stinSC, kindlingSpiritSC, abominationSC, erkelSC, sirvSC, psygoteSC, lockbotSC, nanoSwarmSC, snowGolemSC, hunterSC;
	public static int harvesterSC, archangelSC, ireSC, fuseaSC, ranmasSC, parasmiteSC, avrisSC, jarraSC, kragulSC, magmoxSC, megaCryseSC, voxStellarumSC, greaterStinSC;
	public static int stinKingSC, stinQueenSC, volatileFuseaSC, apisSC, skultarSC, kitsunakumaSC, empariahSC, timeControllerSC, polarisSC, enyvilSC, claymationSC, overlordSC;

	public static double[] jabbaStats, jannaStats, plagueStats, gragulStats, minotaurStats, inklingStats, ragrStats, pumpkinheadStats, tragicNekoStats, toxStats, poxStats, cryseStats;
	public static double[] starCryseStats, norVoxStats, starVoxStats, goldenPirahStats, pirahStats, stinStats, stinBabyStats, kindlingSpiritStats, abominationStats, erkelStats, sirvStats, psygoteStats;
	public static double[] lockbotStats, nanoSwarmStats, hunterStats, harvesterStats, seekerStats, archangelStats, ireStats, fuseaStats, ranmasStats, parasmiteStats, kurayamiStats, avrisStats;
	public static double[] jarraStats, kragulStats, magmoxStats, megaCryseStats, voxStellarumStats, greaterStinStats, stinKingStats, stinQueenStats, aegarStats, volatileFuseaStats;
	public static double[] apisStats, skultarStats, kitsunakumaStats, empariahStats, timeControllerStats, polarisStats, enyvilStats, claymationStats, overlordCoreStats, overlordCombatStats, overlordCocoonStats;

	public static boolean[] potionAllow = new boolean[32];
	public static boolean allowFlight, allowAquaSuperiority, allowImmunity, allowResurrection, allowHarmony, allowInvulnerability, allowClarity, allowConvergence, allowDivinity;
	public static boolean allowCorruption, allowDisorientation, allowStun, allowFear, allowMalnourish, allowCripple, allowSubmission, allowInhibit, allowLeadFoot, allowHacked, allowBurned;
	public static int idFlight, idAquaSuperiority, idImmunity, idResurrection, idHarmony, idInvulnerability, idClarity, idConvergence, idDivinity;
	public static int idCorruption, idDisorientation, idStun, idFear, idMalnourish, idCripple, idSubmission, idInhibit, idLeadFoot, idHacked, idBurned;

	private static boolean[] vanillaConfig = new boolean[16];
	public static boolean allowVanillaMobBuffs, allowExtraMobEffects, allowAnimalRetribution, allowMobModdedArmor, allowRespawnPunishment, allowExtraExplosiveEffects;
	public static boolean allowMobBlindnessDebuff, allowExtraOverworldFlowers, allowOverworldSilverfishGen, allowNetherOreGen, allowOverworldOreGen, allowDrudgeGen, allowAnimalGolemCorruption;
	public static boolean allowCowMinotaurCreation;
	public static int rubyOreRate, sapphireOreRate, mercuryOreRate, tungstenOreRate, drudgeRate, silverfishRate, rubyOreVeinSize, sapphireOreVeinSize, mercuryOreVeinSize;
	public static int tungstenOreVeinSize, drudgeVeinSize, silverfishVeinSize, aerisRarity;

	private static boolean[] worldGenConfig = new boolean[16];
	public static boolean allowVoidPitGen, allowSpikeGen, allowScatteredSurfaceGen, allowStringLightGen, allowDarkStoneVariantGen, allowStructureGen, allowInvertedSpikeGen;
	public static boolean allowDigitalSeaGen, allowFruitGen, allowIsleGen, allowFlowerGen;
	public static int structureOverallRarity;
	private static boolean[] structureAllow = new boolean[32];
	private static int[] structureRarity = new int[32];

	public static boolean allowRandomWeaponLore, allowChallengeScrolls, allowMobStatueDrops, allowGeneratorItems, allowItemTimeAltering;
	public static boolean allowPvP, allowDefaultLores, allowCorruptionTransfer;
	public static int challengeScrollDropChance, mobStatueDropChance;

	public static boolean allowAnimatedGui, allowArmorModels, allowWeaponModels, allowDivinityColorChange, showDoomGui, showAmuletStatusGui;
	public static int guiTransparency, guiTexture, guiX, guiY;

	public static boolean[] griefConfig = new boolean[12];
	public static double[] modifier = new double[32];

	public static void load()
	{
		Configuration config = TragicMC.getConfig();
		config.load();

		ConfigCategory cat; //The category currently being loaded from the config
		Property prop; //The value currently being parsed
		byte m; //a byte mapping to make it easier for my array hypervisors to work

		cat = config.getCategory(CAT_MASTER);
		cat.setComment("These override all other mod options. If multiple are enabled then the first one that is read is used and the others are disabled.");
		cat.setRequiresMcRestart(true);
		cat.setShowInGui(true);

		prop = cat.get("mobsOnlyMode");
		prop.setDefaultValue(false);
		prop.comment = "Is mobs only mode enabled? This strips the mod down to just the mobs and a few items/blocks related to them.";
		mobsOnlyMode = prop.getBoolean(false);

		prop = cat.get("hardcoreMode");
		prop.setDefaultValue(false);
		prop.comment = "Is hardcode mode enabled? This makes things a bit more difficult than normal.";
		hardcoreMode = prop.getBoolean(false);

		prop = cat.get("lightweightMode");
		prop.setDefaultValue(false);
		prop.comment = "Is lightweight mode enabled? This makes things somewhat easier than normal.";
		lightweightMode = prop.getBoolean(false);
		
		prop = cat.get("barebonesMode");
		prop.setDefaultValue(false);
		prop.comment = "Is barebones mode enabled? This takes things back to a much simpler time, stripping the mod down to just weapons and mobs.";
		barebonesMode = prop.getBoolean(false);

		cat = config.getCategory(CAT_BLANKET);
		cat.setComment("These disable all options beneath them if set to false.");
		cat.setRequiresMcRestart(true);
		cat.setShowInGui(true);

		prop = cat.get("allowAchievements");
		prop.setDefaultValue(true);
		prop.comment = "Are Achievements and Achievement options allowed?";
		allowAchievements = prop.getBoolean(true);

		prop = cat.get("allowAmulets");
		prop.setDefaultValue(true);
		prop.comment = "Are Amulets, Amulet Modifiers, Amulet Guis and Amulet recipes allowed?";
		allowAmulets = prop.getBoolean(true);

		prop = cat.get("allowDimension");
		prop.setDefaultValue(true);
		prop.comment = "Are the mod-exclusive Dimensions, Biomes and WorldGen allowed?";
		allowDimension = prop.getBoolean(true);

		prop = cat.get("allowDoom");
		prop.setDefaultValue(true);
		prop.comment = "Are Doom, Doomsdays and non-Doomsday Weapon/Armor abilities allowed?";
		allowDoom = prop.getBoolean(true);

		prop = cat.get("allowEnchantments");
		prop.setDefaultValue(true);
		prop.comment = "Are mod-exclusive Weapon and Armor Enchantments allowed?";
		allowEnchantments = prop.getBoolean(true);

		prop = cat.get("allowMobs");
		prop.setDefaultValue(true);
		prop.comment = "Are mod-exclusive Mobs and Bosses allowed?";
		allowMobs = prop.getBoolean(true);

		prop = cat.get("allowPotions");
		prop.setDefaultValue(true);
		prop.comment = "Are mod-exclusive Potion Effects allowed and can they be used by the mod's Mobs and various effects?";
		allowPotions = prop.getBoolean(true);

		prop = cat.get("allowVanillaChanges");
		prop.setDefaultValue(true);
		prop.comment = "Are changes to Vanilla like increasing Vanilla mob health and giving extra abilities to Vanilla mobs allowed?";
		allowVanillaChanges = prop.getBoolean(true);

		prop = cat.get("allowWorldGen");
		prop.setDefaultValue(true);
		prop.comment = "Is the mod able to execute any of it's non-Ore WorldGen?";
		allowWorldGen = prop.getBoolean(true);

		cat = config.getCategory(CAT_ACHIEVE);
		cat.setComment("These allow you to toggle specific Achievements, whether they can be received or not.");
		cat.setShowInGui(true);

		//TODO setup achievements in new config

		cat = config.getCategory(CAT_AMULET);
		cat.setComment("These allow you to toggle various aspects of Amulets.");
		cat.setRequiresMcRestart(true);
		cat.setShowInGui(false);

		prop = cat.get("allowAmuletLeveling");
		prop.setDefaultValue(true);
		prop.comment = "Are amulets able to be leveled up by combining them in a crafting recipe?";
		amuletConfig[m = 0] = prop.getBoolean(true);

		prop = cat.get("allowAmuletCrafting");
		prop.setDefaultValue(true);
		prop.comment = "Can amulets be crafted via raw materials?";
		amuletConfig[++m] = prop.getBoolean(true);

		prop = cat.get("requireAmuletSlotUnlock");
		prop.setDefaultValue(true);
		prop.comment = "Do you need to use an Amulet Release to unlock a new Amulet slot?";
		amuletConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowAmuletBossKillCharge");
		prop.setDefaultValue(true);
		prop.comment = "Will your Amulets repair some durability after killing a Boss?";
		amuletConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowAmuletModifiers");
		prop.setDefaultValue(true);
		prop.comment = "Will Amulets have random modifiers that affect their user while equipped?";
		amuletConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowAmuletDeathDrops");
		prop.setDefaultValue(true);
		prop.comment = "Do Amulets drop off of you on death?";
		amuletConfig[++m] = prop.getBoolean(true);

		prop = cat.get("amuletMaxSlots");
		prop.setDefaultValue(3);
		prop.comment = "The maximum amount of slots you can have unlocked.";
		amuletMaxSlots = clamp(prop.getInt(3), 1, 3);

		prop = cat.get("amuletOverallRarity");
		prop.setDefaultValue(5);
		prop.comment = "The chance of you getting an Amulet in a chest, higher value is higher chance.";
		amuletOverallRarity = clamp(prop.getInt(5), 1, 250);

		prop = cat.get("amuletReleaseRarity");
		prop.setDefaultValue(5);
		prop.comment = "The chance of you getting an Amulet Release in a chest, higher value is higher chance.";
		amuletReleaseRarity = clamp(prop.getInt(5), 1, 250);

		prop = cat.get("amuletModChance");
		prop.setDefaultValue(54);
		prop.comment = "Affects the chance of getting at least one Modifier on an Amulet. Lower value is higher chance.";
		amuletModChance = clamp(prop.getInt(54), 1, 100);

		prop = cat.get("amuletModChance2");
		prop.setDefaultValue(79);
		prop.comment = "Affects the chance of getting a second Modifier on an Amulet. Lower value is higher chance.";
		amuletModChance2 = clamp(prop.getInt(79), 1, 100);

		prop = cat.get("amuletModChance3");
		prop.setDefaultValue(89);
		prop.comment = "Affects the chance of getting a third Modifier on an Amulet. Lower value is higher chance.";
		amuletModChance3 = clamp(prop.getInt(89), 1, 100);

		cat = config.getCategory(CAT_AMUEFFECT);
		cat.setComment("Disable or enable certain Amulet Effects.");
		cat.setRequiresMcRestart(true);

		prop = cat.get("peace");
		prop.setDefaultValue(true);
		amuletEffects[m = 0] = prop.getBoolean(true);

		prop = cat.get("yeti");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("claymation");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("chicken");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("blacksmith");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("creeper");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("zombie");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("skeleton");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("ice");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("snowGolem");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("ironGolem");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("spider");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("stin");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("supernatural");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("fusea");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("luck");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("kitsune");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("martyr");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("piercing");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("apis");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("sunken");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("enderman");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("polaris");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("lightning");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("consumption");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("undead");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("enderDragon");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("time");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("wither");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("overlord");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = cat.get("enyvil");
		prop.setDefaultValue(true);
		amuletEffects[++m] = prop.getBoolean(true);

		cat = config.getCategory(CAT_CREATIVE);
		cat.setComment("Change various aspects of the Creative mode only items in the mod.");
		cat.setRequiresMcRestart(true);

		prop = cat.get("sphereGenUsesFilter");
		prop.setDefaultValue(false);
		prop.comment = "Does the Sphere Generator use your custom block filter?";
		sphereGenUsesFilter = prop.getBoolean(false);

		prop = cat.get("sphereFilter");
		prop.setDefaultValues(new String[] {"tnt", "stone", "cobblestone", "tragicmc:darkStone"});
		prop.comment = "The blocks that can be used in the Sphere Generator, must be the Vanilla blockname or must be appended with the mod id for use of modded blocks. This is not meta-sensitive.";
		sphereFilter = prop.getStringList();

		prop = cat.get("sphereSize");
		prop.setDefaultValue(6.5D);
		prop.comment = "The radius of the sphere that the Sphere Generator uses.";
		sphereSize = prop.getDouble(6.5D);

		prop = cat.get("eraserUsesFilter");
		prop.setDefaultValue(false);
		prop.comment = "Does the Sphere Eraser use your custom block filter?";
		eraserUsesFilter = prop.getBoolean(false);

		prop = cat.get("eraserFilter");
		prop.setDefaultValues(new String[] {"air"});
		prop.comment = "The blocks that are ignored by the Sphere Eraser, must be the Vanilla blockname or must be appended with the mod id for use of modded blocks. This is not meta-sensitive.";
		eraserFilter = prop.getStringList();

		prop = cat.get("eraserSize");
		prop.setDefaultValue(6.5D);
		prop.comment = "The radius of the sphere that the Sphere Eraser uses.";
		eraserSize = prop.getDouble(6.5D);

		prop = cat.get("spikeGenUsesFilter");
		prop.setDefaultValue(false);
		prop.comment = "Does the Spike Generator use your custom block filter?";
		spikeGenUsesFilter = prop.getBoolean(false);

		prop = cat.get("spikeFilter");
		prop.setDefaultValues(new String[] {"darkStone"});
		prop.comment = "The blocks that spikes can be generated out of in the Spike Generator, must be Vanilla blockname or must be appended with mod id for use of modded blocks. This is not meta-sensitive.";
		spikeFilter = prop.getStringList();

		prop = cat.get("spikeSize");
		prop.setDefaultValue(2.5D);
		prop.comment = "The starting radius of a spike that the Spike Generator uses.";
		spikeSize = prop.getDouble(2.5D);

		prop = cat.get("spikeRegression");
		prop.setDefaultValue(0.96977745D);
		prop.comment = "The amount the spike will regress as it gets generated higher up into the air, used by the Spike Generator.";
		spikeRegression = prop.getDouble(0.96977745D);

		prop = cat.get("spikeCutoff");
		prop.setDefaultValue(0.36943755D);
		prop.comment = "The value that the spike will stop generating at, used by the Spike Generator.";
		spikeCutoff = prop.getDouble(0.36943755D);

		prop = cat.get("voidPitUsesFiler");
		prop.setDefaultValue(false);
		prop.comment = "Does the Void Pit Generator ignore any blocks when generated?";
		voidPitUsesFilter = prop.getBoolean(false);

		prop = cat.get("voidPitFilter");
		prop.setDefaultValues(new String[] {"air"});
		prop.comment = "The blocks that void pits can delete during generation via the Void Pit Generator, must be Vanilla blockname or must be appended with mod id for use of modded blocks. This is not meta-sensitive.";
		voidPitFilter = prop.getStringList();

		prop = cat.get("voidPitSize");
		prop.setDefaultValue(12.5D);
		prop.comment = "The radius that the Void Pit Generator will use.";
		voidPitSize = prop.getDouble(12.5D);

		prop = cat.get("explosionBaseSize");
		prop.setDefaultValue(3.5F);
		prop.comment = "The base size of the explosions used by the Explosion Generator";
		explosionBaseSize = prop.getDouble(3.5F);

		prop = cat.get("explosionSizeVariation");
		prop.setDefaultValue(5.0F);
		prop.comment = "The variation applied to the base size of explosions created via the Explosion Generator";
		explosionSizeVariation = prop.getDouble(5.0F);

		cat = config.getCategory(CAT_DIMENSION);
		cat.setComment("Change and toggle aspects of the mod-exclusive Dimensions.");
		cat.setRequiresMcRestart(true);

		prop = cat.get("allowSynapse");
		prop.setDefaultValue(true);
		prop.comment = "Is the Synapse Dimension allowed?";
		dimensionConfig[m = 0] = prop.getBoolean(true);

		prop = cat.get("allowCollision");
		prop.setDefaultValue(true);
		prop.comment = "Is the Collision Dimension allowed?";
		dimensionConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowCollisionRespawn");
		prop.setDefaultValue(true);
		prop.comment = "Can you respawn in the Collision or will you be forced back to the Overworld?";
		dimensionConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowSynapseRespawn");
		prop.setDefaultValue(true);
		prop.comment = "Can you respawn in the Synapse or wll you be forced back to the Synapse?";
		dimensionConfig[++m] = prop.getBoolean(true);

		prop = cat.get("keepCollisionLoaded");
		prop.setDefaultValue(true);
		prop.comment = "Will the Collision Dimension remain loaded when no one is there?";
		dimensionConfig[++m] = prop.getBoolean(true);

		prop = cat.get("keepSynapseLoaded");
		prop.setDefaultValue(true);
		prop.comment = "Will the Synapse Dimension remain loaded when no one is there?";
		dimensionConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowSynapseVariants");
		prop.setDefaultValue(true);
		prop.comment = "Can the Synapse generate with mini-Biomes?";
		dimensionConfig[++m] = prop.getBoolean(true);

		cat = config.getCategory(CAT_BIOME);
		cat.setComment("Set biome ids and generation weights, higher weight is greater chance to generate out of that biome group. Setting a weight to 0 effectively removes a biome from the possible biomes to generate.");
		cat.setRequiresMcRestart(true);

		prop = cat.get("decayingHillsID");
		prop.setDefaultValue(findBiomeID(90));
		idDecayingHills = prop.getInt(findBiomeID(90));

		prop = cat.get("decayingValleyID");
		prop.setDefaultValue(findBiomeID(++idDecayingHills));
		idDecayingValley = prop.getInt(findBiomeID(++idDecayingHills));

		prop = cat.get("decayingWastelandID");
		prop.setDefaultValue(findBiomeID(++idDecayingValley));
		idDecayingWasteland = prop.getInt(findBiomeID(++idDecayingValley));

		prop = cat.get("decayingMountainsID");
		prop.setDefaultValue(findBiomeID(idDecayingWasteland));
		idDecayingMountains = prop.getInt(findBiomeID(idDecayingWasteland));

		prop = cat.get("paintedForestID");
		prop.setDefaultValue(findBiomeID(++idDecayingMountains));
		idPaintedForest = prop.getInt(findBiomeID(++idDecayingMountains));

		prop = cat.get("paintedPlainsID");
		prop.setDefaultValue(findBiomeID(++idPaintedForest));
		idPaintedPlains = prop.getInt(findBiomeID(++idPaintedForest));

		prop = cat.get("paintedHillsID");
		prop.setDefaultValue(findBiomeID(++idPaintedPlains));
		idPaintedHills = prop.getInt(findBiomeID(++idPaintedPlains));

		prop = cat.get("paintedClearingID");
		prop.setDefaultValue(findBiomeID(++idPaintedHills));
		idPaintedClearing = prop.getInt(findBiomeID(++idPaintedHills));

		prop = cat.get("ashenMountainsID");
		prop.setDefaultValue(findBiomeID(++idPaintedClearing));
		idAshenMountains = prop.getInt(findBiomeID(++idPaintedClearing));

		prop = cat.get("ashenHillsID");
		prop.setDefaultValue(findBiomeID(++idAshenMountains));
		idAshenHills = prop.getInt(findBiomeID(++idAshenMountains));

		prop = cat.get("ashenBadlandsID");
		prop.setDefaultValue(findBiomeID(++idAshenHills));
		idAshenBadlands = prop.getInt(findBiomeID(++idAshenHills));

		prop = cat.get("starlitPrarieID");
		prop.setDefaultValue(findBiomeID(++idAshenBadlands));
		idStarlitPrarie = prop.getInt(findBiomeID(++idAshenBadlands));

		prop = cat.get("starlitPlateausID");
		prop.setDefaultValue(findBiomeID(++idStarlitPrarie));
		idStarlitPlateaus = prop.getInt(findBiomeID(++idStarlitPrarie));

		prop = cat.get("starlitCliffsID");
		prop.setDefaultValue(findBiomeID(++idStarlitPlateaus));
		idStarlitCliffs = prop.getInt(findBiomeID(++idStarlitPlateaus));

		prop = cat.get("starlitLowlandsID");
		prop.setDefaultValue(findBiomeID(++idStarlitCliffs));
		idStarlitLowlands = prop.getInt(findBiomeID(++idStarlitCliffs));

		prop = cat.get("taintedSpikesID");
		prop.setDefaultValue(findBiomeID(++idStarlitLowlands));
		idTaintedSpikes = prop.getInt(findBiomeID(++idStarlitLowlands));

		prop = cat.get("taintedLowlandsID");
		prop.setDefaultValue(findBiomeID(++idTaintedSpikes));
		idTaintedLowlands = prop.getInt(findBiomeID(++idTaintedSpikes));

		prop = cat.get("taintedRisesID");
		prop.setDefaultValue(findBiomeID(++idTaintedLowlands));
		idTaintedRises = prop.getInt(findBiomeID(++idTaintedLowlands));

		prop = cat.get("taintedScarlandsID");
		prop.setDefaultValue(findBiomeID(++idTaintedRises));
		idTaintedScarlands = prop.getInt(findBiomeID(++idTaintedRises));

		prop = cat.get("taintedIslesID");
		prop.setDefaultValue(findBiomeID(++idTaintedScarlands));
		idTaintedIsles = prop.getInt(findBiomeID(++idTaintedScarlands));

		prop = cat.get("synapseID");
		prop.setDefaultValue(findBiomeID(++idTaintedIsles));
		idSynapse = prop.getInt(findBiomeID(++idTaintedIsles));

		prop = cat.get("hallowedHillsID");
		prop.setDefaultValue(findBiomeID(++idSynapse));
		idHallowedHills = prop.getInt(findBiomeID(++idSynapse));

		prop = cat.get("hallowedForestID");
		prop.setDefaultValue(findBiomeID(++idHallowedHills));
		idHallowedForest = prop.getInt(findBiomeID(++idHallowedHills));

		prop = cat.get("hallowedPrarieID");
		prop.setDefaultValue(findBiomeID(++idHallowedForest));
		idHallowedPrarie = prop.getInt(findBiomeID(++idHallowedForest));

		prop = cat.get("hallowedCliffsID");
		prop.setDefaultValue(findBiomeID(++idHallowedPrarie));
		idHallowedCliffs = prop.getInt(findBiomeID(++idHallowedPrarie));

		prop = cat.get("scorchedWastelandsID");
		prop.setDefaultValue(findBiomeID(++idHallowedCliffs));
		idScorchedWastelands = prop.getInt(findBiomeID(++idHallowedCliffs));

		prop = cat.get("scorchedValleyID");
		prop.setDefaultValue(findBiomeID(++idScorchedWastelands));
		idScorchedValley = prop.getInt(findBiomeID(++idScorchedWastelands));

		prop = cat.get("scorchedScarlandsID");
		prop.setDefaultValue(findBiomeID(++idScorchedValley));
		idScorchedScarlands = prop.getInt(findBiomeID(++idScorchedValley));

		prop = cat.get("corrodedSteppeID");
		prop.setDefaultValue(findBiomeID(++idScorchedScarlands));
		idCorrodedSteppe = prop.getInt(findBiomeID(++idScorchedScarlands));

		prop = cat.get("corrodedHeightsID");
		prop.setDefaultValue(findBiomeID(++idCorrodedSteppe));
		idCorrodedHeights = prop.getInt(findBiomeID(++idCorrodedSteppe));

		prop = cat.get("corrodedVeldID");
		prop.setDefaultValue(findBiomeID(++idCorrodedHeights));
		idCorrodedVeld = prop.getInt(findBiomeID(++idCorrodedHeights));

		prop = cat.get("corrodedRunoffID");
		prop.setDefaultValue(findBiomeID(++idCorrodedVeld));
		idCorrodedRunoff = prop.getInt(findBiomeID(++idCorrodedVeld));

		prop = cat.get("corrodedFalloutID");
		prop.setDefaultValue(findBiomeID(++idCorrodedRunoff));
		idCorrodedFallout = prop.getInt(findBiomeID(++idCorrodedRunoff));

		prop = cat.get("frozenTundraID");
		prop.setDefaultValue(findBiomeID(++idCorrodedFallout));
		idFrozenTundra = prop.getInt(findBiomeID(++idCorrodedFallout));

		prop = cat.get("frozenHillsID");
		prop.setDefaultValue(findBiomeID(++idFrozenTundra));
		idFrozenHills = prop.getInt(findBiomeID(++idFrozenTundra));

		prop = cat.get("frozenDepthsID");
		prop.setDefaultValue(findBiomeID(++idFrozenHills));
		idFrozenDepths = prop.getInt(findBiomeID(++idFrozenHills));

		prop = cat.get("crystalID");
		prop.setDefaultValue(findBiomeID(++idFrozenDepths));
		idCrystal = prop.getInt(findBiomeID(++idFrozenDepths));

		prop = cat.get("darkForestID");
		prop.setDefaultValue(findBiomeID(++idCrystal));
		idDarkForest = prop.getInt(findBiomeID(++idCrystal));

		prop = cat.get("darkForestHillsID");
		prop.setDefaultValue(findBiomeID(++idDarkForest));
		idDarkForestHills = prop.getInt(findBiomeID(++idDarkForest));

		prop = cat.get("darkMarshID");
		prop.setDefaultValue(findBiomeID(++idDarkForestHills));
		idDarkMarsh = prop.getInt(findBiomeID(++idDarkForestHills));

		prop = cat.get("synapseDeadID");
		prop.setDefaultValue(findBiomeID(++idDarkMarsh));
		idSynapseDead = prop.getInt(findBiomeID(++idDarkMarsh));

		prop = cat.get("synapseCorruptID");
		prop.setDefaultValue(findBiomeID(++idSynapseDead));
		idSynapseCorrupt = prop.getInt(findBiomeID(++idSynapseDead));

		prop = cat.get("decayingHillsWeight");
		prop.setDefaultValue(20);
		decayingHillsW = clamp(prop.getInt(20), 0, 250);

		prop = cat.get("decayingValleyWeight");
		prop.setDefaultValue(5);
		decayingValleyW = clamp(prop.getInt(5), 0, 250);

		prop = cat.get("decayingWastelandWeight");
		prop.setDefaultValue(20);
		decayingWastelandW = clamp(prop.getInt(20), 0, 250);

		prop = cat.get("decayingMountainsWeight");
		prop.setDefaultValue(10);
		decayingMountainsW = clamp(prop.getInt(10), 0, 250);

		prop = cat.get("paintedForestWeight");
		prop.setDefaultValue(30);
		paintedForestW = clamp(prop.getInt(30), 0, 250);

		prop = cat.get("paintedPlainsWeight");
		prop.setDefaultValue(10);
		paintedPlainsW = clamp(prop.getInt(10), 0, 250);

		prop = cat.get("paintedHillsWeight");
		prop.setDefaultValue(20);
		paintedHillsW = clamp(prop.getInt(20), 0, 250);

		prop = cat.get("paintedClearingWeight");
		prop.setDefaultValue(5);
		paintedClearingW = clamp(prop.getInt(5), 0, 250);

		prop = cat.get("ashenMountainsWeight");
		prop.setDefaultValue(10);
		ashenMountainsW = clamp(prop.getInt(10), 0, 250);

		prop = cat.get("ashenHillsWeight");
		prop.setDefaultValue(20);
		ashenHillsW = clamp(prop.getInt(20), 0, 250);

		prop = cat.get("ashenBadlandsWeight");
		prop.setDefaultValue(25);
		ashenBadlandsW = clamp(prop.getInt(25), 0, 250);

		prop = cat.get("starlitPrarieWeight");
		prop.setDefaultValue(25);
		starlitPrarieW = clamp(prop.getInt(25), 0, 250);

		prop = cat.get("starlitPlateausWeight");
		prop.setDefaultValue(15);
		starlitPlateausW = clamp(prop.getInt(15), 0, 250);

		prop = cat.get("starlitCliffsWeight");
		prop.setDefaultValue(10);
		starlitCliffsW = clamp(prop.getInt(10), 0, 250);

		prop = cat.get("starlitLowlandsWeight");
		prop.setDefaultValue(25);
		starlitLowlandsW = clamp(prop.getInt(25), 0, 250);

		prop = cat.get("taintedSpikesWeight");
		prop.setDefaultValue(25);
		taintedSpikesW = clamp(prop.getInt(25), 0, 250);

		prop = cat.get("taintedLowlandsWeight");
		prop.setDefaultValue(10);
		taintedLowlandsW = clamp(prop.getInt(10), 0, 250);

		prop = cat.get("taintedRisesWeight");
		prop.setDefaultValue(15);
		taintedRisesW = clamp(prop.getInt(15), 0, 250);

		prop = cat.get("taintedScarlandsWeight");
		prop.setDefaultValue(15);
		taintedScarlandsW = clamp(prop.getInt(15), 0, 250);

		prop = cat.get("taintedIslesWeight");
		prop.setDefaultValue(5);
		taintedIslesW = clamp(prop.getInt(5), 0, 250);

		prop = cat.get("hallowedHillsWeight");
		prop.setDefaultValue(10);
		hallowedHillsW = clamp(prop.getInt(10), 0, 250);

		prop = cat.get("hallowedForestWeight");
		prop.setDefaultValue(25);
		hallowedForestW = clamp(prop.getInt(25), 0, 250);

		prop = cat.get("hallowedPrarieWeight");
		prop.setDefaultValue(20);
		hallowedPrarieW = clamp(prop.getInt(20), 0, 250);

		prop = cat.get("hallowedCliffsWeight");
		prop.setDefaultValue(5);
		hallowedCliffsW = clamp(prop.getInt(5), 0, 250);

		prop = cat.get("scorchedWastelandsWeight");
		prop.setDefaultValue(15);
		scorchedWastelandsW = clamp(prop.getInt(15), 0, 250);

		prop = cat.get("scorchedValleyWeight");
		prop.setDefaultValue(5);
		scorchedValleyW = clamp(prop.getInt(5), 0, 250);

		prop = cat.get("scorchedScarlandsWeight");
		prop.setDefaultValue(10);
		scorchedScarlandsW = clamp(prop.getInt(10), 0, 250);

		prop = cat.get("corrodedSteppeWeight");
		prop.setDefaultValue(25);
		corrodedSteppeW = clamp(prop.getInt(25), 0, 250);

		prop = cat.get("corrodedHeightsWeight");
		prop.setDefaultValue(15);
		corrodedHeightsW = clamp(prop.getInt(15), 0, 250);

		prop = cat.get("corrodedVeldWeight");
		prop.setDefaultValue(10);
		corrodedVeldW = clamp(prop.getInt(10), 0, 250);

		prop = cat.get("corrodedRunoffWeight");
		prop.setDefaultValue(20);
		corrodedRunoffW = clamp(prop.getInt(10), 0, 250);

		prop = cat.get("corrodedFalloutWeight");
		prop.setDefaultValue(5);
		corrodedFalloutW = clamp(prop.getInt(5), 0, 250);

		prop = cat.get("frozenTundraWeight");
		prop.setDefaultValue(25);
		frozenTundraW = clamp(prop.getInt(25), 0, 250);

		prop = cat.get("frozenHillsWeight");
		prop.setDefaultValue(15);
		frozenHillsW = clamp(prop.getInt(15), 0, 250);

		prop = cat.get("frozenDepthsWeight");
		prop.setDefaultValue(5);
		frozenDepthsW = clamp(prop.getInt(5), 0, 250);

		prop = cat.get("crystalWeight");
		prop.setDefaultValue(5);
		crystalW = clamp(prop.getInt(5), 0, 250);

		prop = cat.get("darkForestWeight");
		prop.setDefaultValue(25);
		darkForestW = clamp(prop.getInt(25), 0, 250);

		prop = cat.get("darkForetHillsWeight");
		prop.setDefaultValue(15);
		darkForestHillsW = clamp(prop.getInt(15), 0, 250);

		prop = cat.get("darkMarshWeight");
		prop.setDefaultValue(10);
		darkMarshW = clamp(prop.getInt(10), 0, 250);

		cat = config.getCategory(CAT_DOOM);
		cat.setComment("Modify aspects of Doom.");
		cat.setRequiresMcRestart(true);

		prop = cat.get("allowDoomsdays");
		prop.setDefaultValue(true);
		prop.comment = "Are Doomsdays able to be used?";
		doomConfig[m = 0] = prop.getBoolean(true);

		prop = cat.get("allowInfluenceDoomsdays");
		prop.setDefaultValue(true);
		prop.comment = "Can Influence Doomsdays be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowOverflowDoomsdays");
		prop.setDefaultValue(true);
		prop.comment = "Can Overflow Doomsdays be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowCrisisDoomsdays");
		prop.setDefaultValue(true);
		prop.comment = "Can Crisis Doomsdays be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowWorldShaperDoomsdays");
		prop.setDefaultValue(true);
		prop.comment = "Can World Shaper Doomsdays be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowCombinationDoomsdays");
		prop.setDefaultValue(true);
		prop.comment = "Can Combination Doomsdays be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowNonDoomsdayAbilities");
		prop.setDefaultValue(true);
		prop.comment = "Can non-Doomsday Weapon and Armor abilities be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowDoomIncrease");
		prop.setDefaultValue(true);
		prop.comment = "Can the maximum Doom amount be increased by a Doom Consume?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowConsumeRefill");
		prop.setDefaultValue(true);
		prop.comment = "Should Doom Consumes refill your Doom when used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowDoomPainCharge");
		prop.setDefaultValue(true);
		prop.comment = "Should your Doom increase when you take or inflict damage?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowDoomNaturalCharge");
		prop.setDefaultValue(true);
		prop.comment = "Should your Doom recharge naturally?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowCrucialMoments");
		prop.setDefaultValue(true);
		prop.comment = "Can Doomsdays have a chance to have extra effectiveness?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowBacklash");
		prop.setDefaultValue(true);
		prop.comment = "Can Doomsdays have a chance to fail?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowCooldown");
		prop.setDefaultValue(true);
		prop.comment = "Should Doomsdays inflict Global cooldown after use?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowDoomKillCharge");
		prop.setDefaultValue(true);
		prop.comment = "Should killing enemies recharge your Doom?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowCooldownDefuse");
		prop.setDefaultValue(true);
		prop.comment = "Can Cooldown Defuses be used to remove your Global cooldown?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = cat.get("maxDoomAmount");
		prop.setDefaultValue(500);
		prop.comment = "The highest Doom amount you can have.";
		maxDoomAmount = clampPositive(prop.getInt(500));

		prop = cat.get("doomRechargeRate");
		prop.setDefaultValue(1);
		prop.comment = "The speed at which you naturally recharge Doom. Maxes out at 20, which would be one recharge per tick essentially.";
		doomRechargeRate = clamp(prop.getInt(1), 1, 20);

		prop = cat.get("doomConsumeRarity");
		prop.setDefaultValue(3);
		prop.comment = "The chance of getting a Doom Consume out of a chest, higher value is higher chance.";
		doomConsumeRarity = clamp(prop.getInt(3), 1, 250);

		prop = cat.get("cooldownDefuseRarity");
		prop.setDefaultValue(5);
		prop.comment = "The chance of getting a Cooldown Defuse out of a chest, higher value is higher chance.";
		cooldownDefuseRarity = clamp(prop.getInt(5), 1, 250);

		prop = cat.get("doomConsumeRefillAmount");
		prop.setDefaultValue(50);
		prop.comment = "The percentage of Doom that you'll refill upon use of a Doom Consume.";
		doomConsumeRarity = clamp(prop.getInt(50), 1, 100);

		prop = cat.get("cooldownDefuseRefillAmount");
		prop.setDefaultValue(30);
		prop.comment = "The amount of cooldown that you'll remove upon use of a Cooldown Defuse.";
		doomConsumeRarity = clampPositive(prop.getInt(30));

		prop = cat.get("backlashChance");
		prop.setDefaultValue(5);
		prop.comment = "The chance that Backlash will occur, higher value is higher chance.";
		backlashChance = clamp(prop.getInt(5), 1, 100);

		prop = cat.get("crucialMomentChance");
		prop.setDefaultValue(5);
		prop.comment = "The chance that a Crucial Moment will occur, higher value is higher chance.";
		crucialMomentChance = clamp(prop.getInt(5), 1, 100);

		prop = cat.get("doomConsumeIncreaseAmount");
		prop.setDefaultValue(100);
		prop.comment = "The amount of Max Doom you gain per Doom Consume use.";
		doomConsumeRarity = clampPositive(prop.getInt(100));

		prop = cat.get("maxDoomStartAmount");
		prop.setDefaultValue(100);
		prop.comment = "The default amount of Max Doom that you start with.";
		doomConsumeRarity = clampPositive(prop.getInt(100));

		prop = cat.get("doomRechargeAmount");
		prop.setDefaultValue(1);
		prop.comment = "The amount you recharge per recharge tick, also used by the Doom kill charge.";
		doomRechargeAmount = clampPositive(prop.getInt(1));

		cat = config.getCategory(CAT_DOOMSDAYS);
		cat.setComment("Set whether each Doomsday is allowed, as well as set their cooldown and their Doom cost.");
		cat.setRequiresWorldRestart(false);

		prop = cat.get("decayAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[m = 1] = prop.getBoolean(true);

		prop = cat.get("huntersInstinctAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("toxicityAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("berserkerAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("piercingLightAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("natureDrainAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("poisonBreakAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("snipeAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("rapidFireAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("pulseAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("lightShoveAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("fearAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("harmonizerAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("ravageAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("tormentAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("beastlyImpulsesAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("huntersInstinctAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("suicicalTendenciesAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("reaperLaughAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("realityAlterAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("skullCrusherAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("minerSkillsAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("freezeAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("moonlightSonataAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("flightOfTheValkyriesAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("titanfallAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("bloodlustAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("permafrostAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("purgeAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("lightningRushAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("marionetteAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("mindcrackAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("growthSpurtAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("blizzardAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("asphyxiateAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("fireRainAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("dragonsRoarAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("firestormAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("shotgunAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("guardiansCallAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("hardenAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("sharpenAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("flashAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("septicsAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("kurayamiAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("lifeShareAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("deathMarkAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("paradigmShiftAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("adrenalineAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("escapeAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("giftOfTheGodsAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("gamblerAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("soulstealerAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("parasiteAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("symbiosisAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("timeCollapseAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("magnetizerAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("ambienceAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("dimentiaAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("deleteAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("laserCutterAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("radiantLightAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("dangerZoneAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("supportAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("purifyAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("recallAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("shuffleAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("blinkAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("evacuateAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("medicAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("resurgenceAllow");
		prop.setDefaultValue(true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = cat.get("decayCost");
		prop.setDefaultValue(40);
		doomsdayCost[m = 1] = clampPositive(prop.getInt(40));

		prop = cat.get("huntersInstinctCost");
		prop.setDefaultValue(60);
		doomsdayCost[++m] = clampPositive(prop.getInt(60));

		prop = cat.get("toxicityCost");
		prop.setDefaultValue(40);
		doomsdayCost[++m] = clampPositive(prop.getInt(40));

		prop = cat.get("berserkerCost");
		prop.setDefaultValue(50);
		doomsdayCost[++m] = clampPositive(prop.getInt(50));

		prop = cat.get("piercingLightCost");
		prop.setDefaultValue(60);
		doomsdayCost[++m] = clampPositive(prop.getInt(60));

		prop = cat.get("natureDrainCost");
		prop.setDefaultValue(12);
		doomsdayCost[++m] = clampPositive(prop.getInt(12));

		prop = cat.get("poisonBreakCost");
		prop.setDefaultValue(30);
		doomsdayCost[++m] = clampPositive(prop.getInt(30));

		prop = cat.get("snipeCost");
		prop.setDefaultValue(90);
		doomsdayCost[++m] = clampPositive(prop.getInt(90));

		prop = cat.get("rapidFireCost");
		prop.setDefaultValue(8);
		doomsdayCost[++m] = clampPositive(prop.getInt(8));

		prop = cat.get("pulseCost");
		prop.setDefaultValue(10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("lightShoveCost");
		prop.setDefaultValue(3);
		doomsdayCost[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("fearCost");
		prop.setDefaultValue(30);
		doomsdayCost[++m] = clampPositive(prop.getInt(30));

		prop = cat.get("harmonizerCost");
		prop.setDefaultValue(40);
		doomsdayCost[++m] = clampPositive(prop.getInt(40));

		prop = cat.get("ravageCost");
		prop.setDefaultValue(55);
		doomsdayCost[++m] = clampPositive(prop.getInt(55));

		prop = cat.get("tormentCost");
		prop.setDefaultValue(45);
		doomsdayCost[++m] = clampPositive(prop.getInt(45));

		prop = cat.get("beastlyImpulsesCost");
		prop.setDefaultValue(60);
		doomsdayCost[++m] = clampPositive(prop.getInt(60));

		prop = cat.get("suicidalTendenciesCost");
		prop.setDefaultValue(12);
		doomsdayCost[++m] = clampPositive(prop.getInt(12));

		prop = cat.get("reaperLaughCost");
		prop.setDefaultValue(16);
		doomsdayCost[++m] = clampPositive(prop.getInt(16));

		prop = cat.get("realityAlterCost");
		prop.setDefaultValue(40);
		doomsdayCost[++m] = clampPositive(prop.getInt(40));

		prop = cat.get("skullCrusherCost");
		prop.setDefaultValue(50);
		doomsdayCost[++m] = clampPositive(prop.getInt(50));

		prop = cat.get("minerSkillsCost");
		prop.setDefaultValue(30);
		doomsdayCost[++m] = clampPositive(prop.getInt(30));

		prop = cat.get("freezeCost");
		prop.setDefaultValue(30);
		doomsdayCost[++m] = clampPositive(prop.getInt(30));

		prop = cat.get("moonlightSonataCost");
		prop.setDefaultValue(1);
		doomsdayCost[++m] = clampPositive(prop.getInt(1));

		prop = cat.get("flightOfTheValkyriesCost");
		prop.setDefaultValue(10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("titanfallCost");
		prop.setDefaultValue(5);
		doomsdayCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("bloodlustCost");
		prop.setDefaultValue(80);
		doomsdayCost[++m] = clampPositive(prop.getInt(80));

		prop = cat.get("permafrostCost");
		prop.setDefaultValue(6);
		doomsdayCost[++m] = clampPositive(prop.getInt(6));

		prop = cat.get("purgeCost");
		prop.setDefaultValue(5);
		doomsdayCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("lightningRushCost");
		prop.setDefaultValue(8);
		doomsdayCost[++m] = clampPositive(prop.getInt(8));

		prop = cat.get("marionetteCost");
		prop.setDefaultValue(3);
		doomsdayCost[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("mindcrackCost");
		prop.setDefaultValue(45);
		doomsdayCost[++m] = clampPositive(prop.getInt(45));

		prop = cat.get("growthSpurtCost");
		prop.setDefaultValue(50);
		doomsdayCost[++m] = clampPositive(prop.getInt(50));

		prop = cat.get("blizzardCost");
		prop.setDefaultValue(10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("asphyxiateCost");
		prop.setDefaultValue(3);
		doomsdayCost[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("fireRainCost");
		prop.setDefaultValue(8);
		doomsdayCost[++m] = clampPositive(prop.getInt(8));

		prop = cat.get("dragonsRoarCost");
		prop.setDefaultValue(25);
		doomsdayCost[++m] = clampPositive(prop.getInt(25));

		prop = cat.get("firestormCost");
		prop.setDefaultValue(10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("shotgunCost");
		prop.setDefaultValue(10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("guardiansCallCost");
		prop.setDefaultValue(75);
		doomsdayCost[++m] = clampPositive(prop.getInt(75));

		prop = cat.get("hardenCost");
		prop.setDefaultValue(60);
		doomsdayCost[++m] = clampPositive(prop.getInt(60));

		prop = cat.get("sharpenCost");
		prop.setDefaultValue(75);
		doomsdayCost[++m] = clampPositive(prop.getInt(75));

		prop = cat.get("flashCost");
		prop.setDefaultValue(10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("septicsCost");
		prop.setDefaultValue(8);
		doomsdayCost[++m] = clampPositive(prop.getInt(8));

		prop = cat.get("kurayamiCost");
		prop.setDefaultValue(115);
		doomsdayCost[++m] = clampPositive(prop.getInt(115));

		prop = cat.get("lifeShareCost");
		prop.setDefaultValue(65);
		doomsdayCost[++m] = clampPositive(prop.getInt(65));

		prop = cat.get("deathMarkCost");
		prop.setDefaultValue(12);
		doomsdayCost[++m] = clampPositive(prop.getInt(12));

		prop = cat.get("paradigmShiftCost");
		prop.setDefaultValue(50);
		doomsdayCost[++m] = clampPositive(prop.getInt(50));

		prop = cat.get("adrenalineCost");
		prop.setDefaultValue(33);
		doomsdayCost[++m] = clampPositive(prop.getInt(33));

		prop = cat.get("escapeCost");
		prop.setDefaultValue(12);
		doomsdayCost[++m] = clampPositive(prop.getInt(12));

		prop = cat.get("giftOfTheGodsCost");
		prop.setDefaultValue(115);
		doomsdayCost[++m] = clampPositive(prop.getInt(115));

		prop = cat.get("gamblerCost");
		prop.setDefaultValue(15);
		doomsdayCost[++m] = clampPositive(prop.getInt(15));

		prop = cat.get("soulstealerCost");
		prop.setDefaultValue(12);
		doomsdayCost[++m] = clampPositive(prop.getInt(12));

		prop = cat.get("parasiteCost");
		prop.setDefaultValue(16);
		doomsdayCost[++m] = clampPositive(prop.getInt(16));

		prop = cat.get("symbiosisCost");
		prop.setDefaultValue(18);
		doomsdayCost[++m] = clampPositive(prop.getInt(18));

		prop = cat.get("timeCollapseCost");
		prop.setDefaultValue(3);
		doomsdayCost[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("magnetizerCost");
		prop.setDefaultValue(95);
		doomsdayCost[++m] = clampPositive(prop.getInt(95));

		prop = cat.get("ambienceCost");
		prop.setDefaultValue(1);
		doomsdayCost[++m] = clampPositive(prop.getInt(1));

		prop = cat.get("dimentiaCost");
		prop.setDefaultValue(99);
		doomsdayCost[++m] = clampPositive(prop.getInt(99));

		prop = cat.get("deleteCost");
		prop.setDefaultValue(135);
		doomsdayCost[++m] = clampPositive(prop.getInt(135));

		prop = cat.get("laserCutterCost");
		prop.setDefaultValue(16);
		doomsdayCost[++m] = clampPositive(prop.getInt(16));

		prop = cat.get("radiantLightCost");
		prop.setDefaultValue(62);
		doomsdayCost[++m] = clampPositive(prop.getInt(62));

		prop = cat.get("dangerZoneCost");
		prop.setDefaultValue(22);
		doomsdayCost[++m] = clampPositive(prop.getInt(22));

		prop = cat.get("supportCost");
		prop.setDefaultValue(60);
		doomsdayCost[++m] = clampPositive(prop.getInt(60));

		prop = cat.get("purifyCost");
		prop.setDefaultValue(42);
		doomsdayCost[++m] = clampPositive(prop.getInt(42));

		prop = cat.get("recallCost");
		prop.setDefaultValue(82);
		doomsdayCost[++m] = clampPositive(prop.getInt(82));

		prop = cat.get("shuffleCost");
		prop.setDefaultValue(18);
		doomsdayCost[++m] = clampPositive(prop.getInt(18));

		prop = cat.get("blinkCost");
		prop.setDefaultValue(15);
		doomsdayCost[++m] = clampPositive(prop.getInt(15));

		prop = cat.get("evacuateCost");
		prop.setDefaultValue(56);
		doomsdayCost[++m] = clampPositive(prop.getInt(56));

		prop = cat.get("medicCost");
		prop.setDefaultValue(9);
		doomsdayCost[++m] = clampPositive(prop.getInt(9));

		prop = cat.get("resurgenceCost");
		prop.setDefaultValue(135);
		doomsdayCost[++m] = clampPositive(prop.getInt(135));

		prop = cat.get("decayCooldown");
		prop.setDefaultValue(20);
		doomsdayCooldown[m = 1] = clampPositive(prop.getInt(20));

		prop = cat.get("huntersInstinctCooldown");
		prop.setDefaultValue(25);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(25));

		prop = cat.get("toxicityCooldown");
		prop.setDefaultValue(15);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

		prop = cat.get("berserkerCooldown");
		prop.setDefaultValue(15);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

		prop = cat.get("piercingLightCooldown");
		prop.setDefaultValue(30);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

		prop = cat.get("natureDrainCooldown");
		prop.setDefaultValue(6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = cat.get("poisonBreakCooldown");
		prop.setDefaultValue(10);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("snipeCooldown");
		prop.setDefaultValue(55);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(55));

		prop = cat.get("rapidFireCooldown");
		prop.setDefaultValue(3);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("pulseCooldown");
		prop.setDefaultValue(6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = cat.get("lightShoveCooldown");
		prop.setDefaultValue(1);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(1));

		prop = cat.get("fearCooldown");
		prop.setDefaultValue(20);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

		prop = cat.get("harmonizerCooldown");
		prop.setDefaultValue(30);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

		prop = cat.get("ravageCooldown");
		prop.setDefaultValue(35);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(35));

		prop = cat.get("tormentCooldown");
		prop.setDefaultValue(20);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

		prop = cat.get("beastlyImpulsesCooldown");
		prop.setDefaultValue(50);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(50));

		prop = cat.get("suicidalTendenciesCooldown");
		prop.setDefaultValue(4);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(4));

		prop = cat.get("reaperLaughCooldown");
		prop.setDefaultValue(3);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("realityAlterCooldown");
		prop.setDefaultValue(12);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(12));

		prop = cat.get("skullCrusherCooldown");
		prop.setDefaultValue(15);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

		prop = cat.get("minerSkillsCooldown");
		prop.setDefaultValue(20);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

		prop = cat.get("freezeCooldown");
		prop.setDefaultValue(30);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

		prop = cat.get("moonlightSonataCooldown");
		prop.setDefaultValue(60);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(60));

		prop = cat.get("flightOfTheValkyriesCooldown");
		prop.setDefaultValue(10);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("titanfallCooldown");
		prop.setDefaultValue(10);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("bloodlustCooldown");
		prop.setDefaultValue(30);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

		prop = cat.get("permafrostCooldown");
		prop.setDefaultValue(5);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("purgeCooldown");
		prop.setDefaultValue(4);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(4));

		prop = cat.get("lightningRushCooldown");
		prop.setDefaultValue(6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = cat.get("marionetteCooldown");
		prop.setDefaultValue(3);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("mindcrackCooldown");
		prop.setDefaultValue(60);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(60));

		prop = cat.get("growthSpurtCooldown");
		prop.setDefaultValue(10);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("blizzardCooldown");
		prop.setDefaultValue(6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = cat.get("asphyxiateCooldown");
		prop.setDefaultValue(3);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("fireRainCooldown");
		prop.setDefaultValue(5);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("dragonsRoarCooldown");
		prop.setDefaultValue(15);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

		prop = cat.get("firestormCooldown");
		prop.setDefaultValue(8);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(8));

		prop = cat.get("shotgunCooldown");
		prop.setDefaultValue(5);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("guardiansCallCooldown");
		prop.setDefaultValue(50);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(50));

		prop = cat.get("hardenCooldown");
		prop.setDefaultValue(4);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(4));

		prop = cat.get("sharpenCooldown");
		prop.setDefaultValue(6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = cat.get("flashCooldown");
		prop.setDefaultValue(5);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("septicsCooldown");
		prop.setDefaultValue(6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = cat.get("kurayamiCooldown");
		prop.setDefaultValue(80);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(80));

		prop = cat.get("lifeShareCooldown");
		prop.setDefaultValue(25);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(25));

		prop = cat.get("deathMarkCooldown");
		prop.setDefaultValue(10);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("paradigmShiftCooldown");
		prop.setDefaultValue(0);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(0));

		prop = cat.get("adrenalineCooldown");
		prop.setDefaultValue(21);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(21));

		prop = cat.get("escapeCooldown");
		prop.setDefaultValue(38);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(38));

		prop = cat.get("giftOfTheGodsCooldown");
		prop.setDefaultValue(100);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(100));

		prop = cat.get("gamblerCooldown");
		prop.setDefaultValue(20);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

		prop = cat.get("soulstealerCooldown");
		prop.setDefaultValue(8);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(8));

		prop = cat.get("parasiteCooldown");
		prop.setDefaultValue(12);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(12));

		prop = cat.get("symbiosisCooldown");
		prop.setDefaultValue(14);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(14));

		prop = cat.get("timeCollapseCooldown");
		prop.setDefaultValue(2);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(2));

		prop = cat.get("magnetizerCooldown");
		prop.setDefaultValue(65);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(65));

		prop = cat.get("ambienceCooldown");
		prop.setDefaultValue(1);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(1));

		prop = cat.get("dimentiaCooldown");
		prop.setDefaultValue(77);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(77));

		prop = cat.get("deleteCooldown");
		prop.setDefaultValue(125);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(125));

		prop = cat.get("laserCutterCooldown");
		prop.setDefaultValue(15);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

		prop = cat.get("radiantLightCooldown");
		prop.setDefaultValue(65);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(65));

		prop = cat.get("dangerZoneCooldown");
		prop.setDefaultValue(24);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(24));

		prop = cat.get("supportCooldown");
		prop.setDefaultValue(16);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(16));

		prop = cat.get("purifyCooldown");
		prop.setDefaultValue(42);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(42));

		prop = cat.get("recallCooldown");
		prop.setDefaultValue(22);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(22));

		prop = cat.get("shuffleCooldown");
		prop.setDefaultValue(8);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(8));

		prop = cat.get("blinkCooldown");
		prop.setDefaultValue(1);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(1));

		prop = cat.get("evacuateCooldown");
		prop.setDefaultValue(77);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(77));

		prop = cat.get("medicCooldown");
		prop.setDefaultValue(9);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(9));

		prop = cat.get("resurgenceCooldown");
		prop.setDefaultValue(55);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(55));

		cat = config.getCategory(CAT_WEAPON);
		cat.setComment("Modify non-Doomsday abilities of weapons, setting whether they are allowed and their cost.");
		cat.setRequiresWorldRestart(false);

		prop = cat.get("beastlyClawsComboAllow");
		prop.setDefaultValue(true);
		doomAbility[m = 0] = prop.getBoolean(true);

		prop = cat.get("blindingLightSolarBombAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("blindingLightBurnAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("blindingLightProjectileDeflectAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("butcherCritKnockbackAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("butcherKnockbackResistanceBuffAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("butcherWeaknessDebuffAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("celestialAegisDamageReductionAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("celestialLongbowTeleportAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("dragonFangBurnAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("dragonFangLargeFireballAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("dragonFangExtinguishAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("frozenLightningSlownessDebuffAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("frozenLightningLightningStrikeAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("frozenLightningIcicleAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("gravitySpikeLaunchAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("guiltyThornPoisonStunDebuffAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("harmonyBellHarmonyDebuffAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("harmonyBellHealingAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("mourningStarSelfDestructAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("mourningStarLookExplosionAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("paranoiaFearSubmissionDebuffAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("paranoiaDarkEnergySprayAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("paranoiaSingleDarkEnergyAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("pitchBlackThrowAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("reaperScytheSmallPumpkinbombAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("reaperScytheLargePumpkinbombAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("splinterRandomDirectonHitAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("splinterGroupRandomDirectionHitAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("thardusSlownessDebuffAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("thardusIcicleAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("titanLightningHitAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("titanLightningStrikesAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("titanLightningAbsorbAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("witheringAxeWitherDebuffAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("witherAxeWitherSkullAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("witheringAxeBlueWitherSkullAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("dragonFangFlamethrowerAllow");
		prop.setDefaultValue(true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = cat.get("beastlyClawsComboCost");
		prop.setDefaultValue(0);
		doomAbilityCost[m = 0] = clampPositive(prop.getInt(0));

		prop = cat.get("blindingLightSolarBombCost");
		prop.setDefaultValue(15);
		doomAbilityCost[++m] = clampPositive(prop.getInt(15));

		prop = cat.get("blindingLightBurnCost");
		prop.setDefaultValue(10);
		doomAbilityCost[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("blindingLightProjectileDeflectCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("butcherCritKnockbackCost");
		prop.setDefaultValue(1);
		doomAbilityCost[++m] = clampPositive(prop.getInt(1));

		prop = cat.get("butcherKnockbackResistanceCost");
		prop.setDefaultValue(0);
		doomAbilityCost[++m] = clampPositive(prop.getInt(0));

		prop = cat.get("butcherWeaknessDebuffCost");
		prop.setDefaultValue(0);
		doomAbilityCost[++m] = clampPositive(prop.getInt(0));

		prop = cat.get("celestialAegisDamageReductionCost");
		prop.setDefaultValue(0);
		doomAbilityCost[++m] = clampPositive(prop.getInt(0));

		prop = cat.get("celestialLongbowTeleportCost");
		prop.setDefaultValue(0);
		doomAbilityCost[++m] = clampPositive(prop.getInt(0));

		prop = cat.get("dragonFangBurnCost");
		prop.setDefaultValue(1);
		doomAbilityCost[++m] = clampPositive(prop.getInt(1));

		prop = cat.get("dragonFangLargeFireballCost");
		prop.setDefaultValue(10);
		doomAbilityCost[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("dragonFangExtinguishCost");
		prop.setDefaultValue(1);
		doomAbilityCost[++m] = clampPositive(prop.getInt(1));

		prop = cat.get("frozenLightningSlownessDebuffCost");
		prop.setDefaultValue(3);
		doomAbilityCost[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("frozenLightningLightningStrikeCost");
		prop.setDefaultValue(20);
		doomAbilityCost[++m] = clampPositive(prop.getInt(20));

		prop = cat.get("frozenLightningIcicleCost");
		prop.setDefaultValue(3);
		doomAbilityCost[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("gravitySpikeLaunchCost");
		prop.setDefaultValue(7);
		doomAbilityCost[++m] = clampPositive(prop.getInt(7));

		prop = cat.get("guiltyThornPoisonStunDebuffCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("harmonyBellHarmonyDebuffCost");
		prop.setDefaultValue(3);
		doomAbilityCost[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("harmonyBellHealingCost");
		prop.setDefaultValue(1);
		doomAbilityCost[++m] = clampPositive(prop.getInt(1));

		prop = cat.get("mourningStarSelfDestructCost");
		prop.setDefaultValue(25);
		doomAbilityCost[++m] = clampPositive(prop.getInt(25));

		prop = cat.get("mourningStarLookExplosionCost");
		prop.setDefaultValue(30);
		doomAbilityCost[++m] = clampPositive(prop.getInt(30));

		prop = cat.get("paranoiaFearSubmissionDebuffCost");
		prop.setDefaultValue(10);
		doomAbilityCost[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("paranoiaDarkEnergySprayCost");
		prop.setDefaultValue(15);
		doomAbilityCost[++m] = clampPositive(prop.getInt(15));

		prop = cat.get("paranoiaSingleDarkEnergyCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("pitchBlackThrowCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("reaperScytheSmallPumpkinbombCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("reaperScytheLargePumpkinbombCost");
		prop.setDefaultValue(15);
		doomAbilityCost[++m] = clampPositive(prop.getInt(15));

		prop = cat.get("splinterRandomDirectionHitCost");
		prop.setDefaultValue(3);
		doomAbilityCost[++m] = clampPositive(prop.getInt(3));

		prop = cat.get("splinterGroupRandomDirectionHitCost");
		prop.setDefaultValue(10);
		doomAbilityCost[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("thardusSlownessDebuffCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("thardusIcicleCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("titanLightningHitCost");
		prop.setDefaultValue(10);
		doomAbilityCost[++m] = clampPositive(prop.getInt(10));

		prop = cat.get("titanLightningStrikesCost");
		prop.setDefaultValue(20);
		doomAbilityCost[++m] = clampPositive(prop.getInt(20));

		prop = cat.get("titanLightningAbsorbCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("witheringAxeWitherDebuffCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("witheringAxeWitherSkullCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = cat.get("witheringAxeBlueWitherSkullCost");
		prop.setDefaultValue(15);
		doomAbilityCost[++m] = clampPositive(prop.getInt(15));

		prop = cat.get("dragonFangFlamethrowerCost");
		prop.setDefaultValue(3);
		doomAbilityCost[++m] = clampPositive(prop.getInt(3));

		cat = config.getCategory(CAT_ENCHANT);
		cat.setComment("Disable certain enchantments and set their ids");
		cat.setRequiresMcRestart(true);

		prop = cat.get("decayAllow");
		prop.setDefaultValue(true);
		enchantAllow[m = 0] = prop.getBoolean(true);

		prop = cat.get("slayAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("absolveAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("vampirismAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("leechAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("consumeAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("distractAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("multiplyAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("combustionAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("runeBreakAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("reachAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("unbreakableAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("rustAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("veteranAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("deathTouchAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("ignitionAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("toxicityAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("paralysisAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("elasticityAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("agilityAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("runeWalkerAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("luminescenceAllow");
		prop.setDefaultValue(true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = cat.get("decayID");
		prop.setDefaultValue(findEnchantID(64));
		idDecay = prop.getInt(findEnchantID(64));

		prop = cat.get("slayID");
		prop.setDefaultValue(findEnchantID(idDecay));
		idSlay = prop.getInt(findEnchantID(idDecay));

		prop = cat.get("absolveID");
		prop.setDefaultValue(findEnchantID(idSlay));
		idAbsolve = prop.getInt(findEnchantID(idSlay));

		prop = cat.get("vampirismID");
		prop.setDefaultValue(findEnchantID(idAbsolve));
		idVampirism = prop.getInt(findEnchantID(idAbsolve));

		prop = cat.get("leechID");
		prop.setDefaultValue(findEnchantID(idVampirism));
		idLeech = prop.getInt(findEnchantID(idVampirism));

		prop = cat.get("consumeID");
		prop.setDefaultValue(findEnchantID(idLeech));
		idConsume = prop.getInt(findEnchantID(idLeech));

		prop = cat.get("distractID");
		prop.setDefaultValue(findEnchantID(idConsume));
		idDistract = prop.getInt(findEnchantID(idConsume));

		prop = cat.get("multiplyID");
		prop.setDefaultValue(findEnchantID(idDistract));
		idMultiply = prop.getInt(findEnchantID(idDistract));

		prop = cat.get("combustionID");
		prop.setDefaultValue(findEnchantID(idMultiply));
		idCombustion = prop.getInt(findEnchantID(idMultiply));

		prop = cat.get("runeBreakID");
		prop.setDefaultValue(findEnchantID(idCombustion));
		idRuneBreak = prop.getInt(findEnchantID(idCombustion));

		prop = cat.get("reachID");
		prop.setDefaultValue(findEnchantID(idRuneBreak));
		idReach = prop.getInt(findEnchantID(idRuneBreak));

		prop = cat.get("unbreakableID");
		prop.setDefaultValue(findEnchantID(idReach));
		idUnbreakable = prop.getInt(findEnchantID(idReach));

		prop = cat.get("rustID");
		prop.setDefaultValue(findEnchantID(idUnbreakable));
		idRust = prop.getInt(findEnchantID(idUnbreakable));

		prop = cat.get("veteranID");
		prop.setDefaultValue(findEnchantID(idRust));
		idVeteran = prop.getInt(findEnchantID(idRust));

		prop = cat.get("deathTouchID");
		prop.setDefaultValue(findEnchantID(idVeteran));
		idDeathTouch = prop.getInt(findEnchantID(idVeteran));

		prop = cat.get("ignitionID");
		prop.setDefaultValue(findEnchantID(idDeathTouch));
		idIgnition = prop.getInt(findEnchantID(idDeathTouch));

		prop = cat.get("toxicityID");
		prop.setDefaultValue(findEnchantID(idIgnition));
		idToxicity = prop.getInt(findEnchantID(idIgnition));

		prop = cat.get("paralysisID");
		prop.setDefaultValue(findEnchantID(idToxicity));
		idParalysis = prop.getInt(findEnchantID(idToxicity));

		prop = cat.get("elasticityID");
		prop.setDefaultValue(findEnchantID(idParalysis));
		idElasticity = prop.getInt(findEnchantID(idParalysis));

		prop = cat.get("agilityID");
		prop.setDefaultValue(findEnchantID(idElasticity));
		idAgility = prop.getInt(findEnchantID(idElasticity));

		prop = cat.get("runeWalkerID");
		prop.setDefaultValue(findEnchantID(idAgility));
		idRuneWalker = prop.getInt(findEnchantID(idAgility));

		prop = cat.get("luminescenceID");
		prop.setDefaultValue(findEnchantID(idRuneWalker));
		idLuminescence = prop.getInt(findEnchantID(idRuneWalker));

		cat = config.getCategory(CAT_MOBS);
		cat.setComment("Set aspects of Mobs and Bosses, set which are enabled and their spawn chance.");
		cat.setRequiresMcRestart(true);

		prop = cat.get("allowNormalMobs");
		prop.setDefaultValue(true);
		prop.comment = "Are all of the normal mod-exclusive mobs allowed?";
		mobConfig[m = 0] = prop.getBoolean(true);

		prop = cat.get("allowMiniBosses");
		prop.setDefaultValue(true);
		prop.comment = "Are all of the Mini-Boss variants of some of the mobs allowed?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowBosses");
		prop.setDefaultValue(true);
		prop.comment = "Are all of the Bosses allowed? (Disbling the Overlord disables all of it's forms and disables the Seeker mob)";
		mobConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowBossOverworldSpawns");
		prop.setDefaultValue(true);
		prop.comment = "Are Bosses able to spawn in the Overworld?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowExtraBossLoot");
		prop.setDefaultValue(true);
		prop.comment = "Can Bosses drop a lot of extra normal loot in addition to their specific drops?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowMobTransformation");
		prop.setDefaultValue(true);
		prop.comment = "Can normal mobs transform into their Mini-Boss variants?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowMobDynamicHealthScaling");
		prop.setDefaultValue(true);
		prop.comment = "Do the mod-exclusive mobs have their health buffed or debuffed based on difficulty?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowNonDimensionMobSpawns");
		prop.setDefaultValue(true);
		prop.comment = "Are the mod-exclusive mobs able to spawn naturally outside of the mod's Dimensions?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowMobGroupBuffs");
		prop.setDefaultValue(true);
		prop.comment = "Are the mod-exclusive mobs able to randomly spawn with a potion effect when they spawn naturally in a group?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowEasyBosses");
		prop.setDefaultValue(true);
		prop.comment = "Can Bosses be fought on Easy difficulty?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowCustomMobSounds");
		prop.setDefaultValue(true);
		prop.comment = "Can the mod-exclusive mobs make their custom sounds? (they will still play the Vanilla sounds if disabled)";
		mobConfig[++m] = prop.getBoolean(true);

		prop = cat.get("commonMobDropChance");
		prop.setDefaultValue(25);
		prop.comment = "Affects the chances of getting common mob drops from the looting amount you killed mobs with, only affects mod-exclusive entities.";
		commonDropRate = clamp(prop.getInt(25), 1, 200);

		prop = cat.get("rareMobDropChance");
		prop.setDefaultValue(5);
		prop.comment = "Affects the chances of getting rare mob drops from the looting amount you killed mobs with, only affects mod-exclusive entities.";
		rareDropRate = clamp(prop.getInt(5), 1, 100);

		prop = cat.get("mobTransformationChance");
		prop.setDefaultValue(3);
		mobTransformationChance = clamp(prop.getInt(3), 1, 100);

		prop = cat.get("bossDamageCap");
		prop.setDefaultValue(25);
		bossDamageCap = clampPositive(prop.getInt(25));

		prop = cat.get("groupBuffChance");
		prop.setDefaultValue(25);
		groupBuffChance = clamp(prop.getInt(15), 1, 200);

		cat = config.getCategory(CAT_MOBSTATS);
		cat.setComment("Change mob stats and allowances.");
		cat.setRequiresMcRestart(true);

		prop = cat.get("jabbaAllow");
		prop.setDefaultValue(true);
		mobAllow[m = 0] = prop.getBoolean(true);

		prop = cat.get("jannaAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("plagueAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("gragulAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("minotaurAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("inklingAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("ragrAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("pumpkinheadAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("tragicNekoAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("toxAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("poxAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("cryseAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("starCryseAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("norVoxAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("starVoxAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("pirahAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("stinAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("stinBabyAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("kindlingSpiritAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("abominationAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("erkelAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("sirvAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("psygoteAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("lockbotAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("nanoSwarmAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("snowGolemAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("hunterAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("harvesterAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("archangelAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("ireAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("fuseaAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("ranmasAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("parasmiteAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("avrisAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = cat.get("jarraAllow");
		prop.setDefaultValue(true);
		miniBossAllow[m = 0] = prop.getBoolean(true);

		prop = cat.get("kragulAllow");
		prop.setDefaultValue(true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("magmoxAllow");
		prop.setDefaultValue(true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("megaCryseAllow");
		prop.setDefaultValue(true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("voxStellarumAllow");
		prop.setDefaultValue(true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("greaterStinAllow");
		prop.setDefaultValue(true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("stinKingAllow");
		prop.setDefaultValue(true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("stinQueenAllow");
		prop.setDefaultValue(true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("aegarAllow");
		prop.setDefaultValue(true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("volatileFuseaAllow");
		prop.setDefaultValue(true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("apisAllow");
		prop.setDefaultValue(true);
		bossAllow[m = 0] = prop.getBoolean(true);

		prop = cat.get("skultarAllow");
		prop.setDefaultValue(true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("kitsunakumaAllow");
		prop.setDefaultValue(true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("empariahAllow");
		prop.setDefaultValue(true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("timeControllerAllow");
		prop.setDefaultValue(true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("polarisAllow");
		prop.setDefaultValue(true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("enyvilAllow");
		prop.setDefaultValue(true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("claymationAllow");
		prop.setDefaultValue(true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("overlordAllow");
		prop.setDefaultValue(true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = cat.get("jabbaSpawnChance");
		prop.setDefaultValue(75);
		jabbaSC = prop.getInt(75);

		prop = cat.get("jannaSpawnChance");
		prop.setDefaultValue(50);
		jannaSC = prop.getInt(50);

		prop = cat.get("jarraSpawnChance");
		prop.setDefaultValue(5);
		jarraSC = prop.getInt(5);

		prop = cat.get("plagueSpawnChance");
		prop.setDefaultValue(50);
		plagueSC = prop.getInt(50);

		prop = cat.get("gragulSpawnChance");
		prop.setDefaultValue(25);
		gragulSC = prop.getInt(25);

		prop = cat.get("kragulSpawnChance");
		prop.setDefaultValue(5);
		kragulSC = prop.getInt(5);

		prop = cat.get("minotaurSpawnChance");
		prop.setDefaultValue(75);
		minotaurSC = prop.getInt(75);

		prop = cat.get("inklingSpawnChance");
		prop.setDefaultValue(75);
		inklingSC = prop.getInt(75);

		prop = cat.get("ragrSpawnChance");
		prop.setDefaultValue(25);
		ragrSC = prop.getInt(25);

		prop = cat.get("pumpkinheadSpawnChance");
		prop.setDefaultValue(25);
		pumpkinheadSC = prop.getInt(25);

		prop = cat.get("tragicNekoSpawnChance");
		prop.setDefaultValue(50);
		tragicNekoSC = prop.getInt(50);

		prop = cat.get("toxSpawnChance");
		prop.setDefaultValue(50);
		toxSC = prop.getInt(50);

		prop = cat.get("poxSpawnChance");
		prop.setDefaultValue(50);
		poxSC = prop.getInt(50);

		prop = cat.get("magmoxSpawnChance");
		prop.setDefaultValue(5);
		magmoxSC = prop.getInt(5);

		prop = cat.get("cryseSpawnChance");
		prop.setDefaultValue(75);
		cryseSC = prop.getInt(75);

		prop = cat.get("starCryseSpawnChance");
		prop.setDefaultValue(75);
		starCryseSC = prop.getInt(75);

		prop = cat.get("megaCryseSpawnChance");
		prop.setDefaultValue(5);
		megaCryseSC = prop.getInt(5);

		prop = cat.get("norVoxSpawnChance");
		prop.setDefaultValue(25);
		norVoxSC = prop.getInt(25);

		prop = cat.get("starVoxSpawnChance");
		prop.setDefaultValue(25);
		starVoxSC = prop.getInt(25);

		prop = cat.get("voxStellarumSpawnChance");
		prop.setDefaultValue(5);
		voxStellarumSC = prop.getInt(5);

		prop = cat.get("pirahSpawnChance");
		prop.setDefaultValue(25);
		pirahSC = prop.getInt(25);

		prop = cat.get("stinSpawnChance");
		prop.setDefaultValue(50);
		stinSC = prop.getInt(50);

		prop = cat.get("greaterStinSpawnChance");
		prop.setDefaultValue(5);
		greaterStinSC = prop.getInt(5);

		prop = cat.get("stinKingSpawnChance");
		prop.setDefaultValue(5);
		stinKingSC = prop.getInt(5);

		prop = cat.get("stinQueenSpawnChance");
		prop.setDefaultValue(5);
		stinQueenSC = prop.getInt(5);

		prop = cat.get("kindlingSpiritSpawnChance");
		prop.setDefaultValue(15);
		kindlingSpiritSC = prop.getInt(15);

		prop = cat.get("abominationSpawnChance");
		prop.setDefaultValue(25);
		abominationSC = prop.getInt(25);

		prop = cat.get("erkelSpawnChance");
		prop.setDefaultValue(25);
		erkelSC = prop.getInt(25);

		prop = cat.get("sirvSpawnChance");
		prop.setDefaultValue(50);
		greaterStinSC = prop.getInt(50);

		prop = cat.get("psygoteSpawnChance");
		prop.setDefaultValue(5);
		psygoteSC = prop.getInt(5);

		prop = cat.get("lockbotSpawnChance");
		prop.setDefaultValue(5);
		lockbotSC = prop.getInt(5);

		prop = cat.get("nanoSwarmSpawnChance");
		prop.setDefaultValue(25);
		nanoSwarmSC = prop.getInt(25);

		prop = cat.get("snowGolemSpawnChance");
		prop.setDefaultValue(20);
		snowGolemSC = prop.getInt(20);

		prop = cat.get("hunterSpawnChance");
		prop.setDefaultValue(15);
		hunterSC = prop.getInt(15);

		prop = cat.get("harvesterSpawnChance");
		prop.setDefaultValue(10);
		harvesterSC = prop.getInt(10);

		prop = cat.get("archangelSpawnChance");
		prop.setDefaultValue(5);
		archangelSC = prop.getInt(5);

		prop = cat.get("ireSpawnChance");
		prop.setDefaultValue(45);
		ireSC = prop.getInt(45);

		prop = cat.get("fuseaSpawnChance");
		prop.setDefaultValue(25);
		fuseaSC = prop.getInt(25);

		prop = cat.get("volatileFuseaSpawnChance");
		prop.setDefaultValue(5);
		volatileFuseaSC = prop.getInt(5);

		prop = cat.get("ranmasSpawnChance");
		prop.setDefaultValue(25);
		ranmasSC = prop.getInt(25);

		prop = cat.get("parasmiteSpawnChance");
		prop.setDefaultValue(25);
		parasmiteSC = prop.getInt(25);

		prop = cat.get("apisSpawnChance");
		prop.setDefaultValue(5);
		apisSC = prop.getInt(5);

		prop = cat.get("skultarSpawnChance");
		prop.setDefaultValue(5);
		skultarSC = prop.getInt(5);

		prop = cat.get("kitsunakumaSpawnChance");
		prop.setDefaultValue(5);
		kitsunakumaSC = prop.getInt(5);

		prop = cat.get("empariahSpawnChance");
		prop.setDefaultValue(5);
		empariahSC = prop.getInt(5);

		prop = cat.get("timeControllerSpawnChance");
		prop.setDefaultValue(5);
		timeControllerSC = prop.getInt(5);

		prop = cat.get("polarisSpawnChance");
		prop.setDefaultValue(5);
		polarisSC = prop.getInt(5);

		prop = cat.get("enyvilSpawnChance");
		prop.setDefaultValue(5);
		enyvilSC = prop.getInt(5);

		prop = cat.get("claymationSpawnChance");
		prop.setDefaultValue(5);
		claymationSC = prop.getInt(5);

		prop = cat.get("overlordSpawnChance");
		prop.setDefaultValue(5);
		overlordSC = prop.getInt(5);

		prop = cat.get("jabbaStats");
		prop.setDefaultValues(new double[] {40.0, 0.275, 5.5, 32.0, 0.0, 0});
		jabbaStats = verifyStat(prop.getDoubleList());

		prop = cat.get("jannaStats");
		prop.setDefaultValues(new double[] {20.0, 0.325, 4.5, 32.0, 0.0, 0});
		jannaStats = verifyStat(prop.getDoubleList());

		prop = cat.get("jarraStats");
		prop.setDefaultValues(new double[] {70.0, 0.360, 6.5, 64.0, 0.0, 0});
		jarraStats = verifyStat(prop.getDoubleList());

		prop = cat.get("plagueStats");
		prop.setDefaultValues(new double[] {4.0, 0.235, 1.0, 16.0, 0.0, 0});
		plagueStats = verifyStat(prop.getDoubleList());

		prop = cat.get("gragulStats");
		prop.setDefaultValues(new double[] {5.0, 0.350, 5.0, 32.0, 0.0, 0});
		gragulStats = verifyStat(prop.getDoubleList());

		prop = cat.get("kragulStats");
		prop.setDefaultValues(new double[] {8.0, 0.380, 5.0, 5.0, 32.0, 0});
		kragulStats = verifyStat(prop.getDoubleList());

		prop = cat.get("minotaurStats");
		prop.setDefaultValues(new double[] {32.0, 0.350, 7.0, 32.0, 0.5, 6});
		minotaurStats = verifyStat(prop.getDoubleList());

		prop = cat.get("inklingStats");
		prop.setDefaultValues(new double[] {16.0, 0.230, 1.0, 32.0, 0.0, 0});
		inklingStats = verifyStat(prop.getDoubleList());

		prop = cat.get("ragrStats");
		prop.setDefaultValues(new double[] {65.0, 0.380, 7.0, 32.0, 1.0, 10});
		ragrStats = verifyStat(prop.getDoubleList());

		prop = cat.get("pumpkinheadStats");
		prop.setDefaultValues(new double[] {60.0, 0.275, 6.0, 32.0, 0.0, 18});
		pumpkinheadStats = verifyStat(prop.getDoubleList());

		prop = cat.get("tragicNekoStats");
		prop.setDefaultValues(new double[] {80.0, 0.335, 6.0, 32.0, 0.0, 0});
		tragicNekoStats = verifyStat(prop.getDoubleList());

		prop = cat.get("toxStats");
		prop.setDefaultValues(new double[] {40.0, 0.050, 8.0, 64.0, 1.0, 16});
		toxStats = verifyStat(prop.getDoubleList());

		prop = cat.get("poxStats");
		prop.setDefaultValues(new double[] {30.0, 0.050, 4.0, 64.0, 0.7, 10});
		poxStats = verifyStat(prop.getDoubleList());

		prop = cat.get("magmoxStats");
		prop.setDefaultValues(new double[] {75.0, 0.050, 15.0, 64.0, 1.0, 20});
		magmoxStats = verifyStat(prop.getDoubleList());

		prop = cat.get("cryseStats");
		prop.setDefaultValues(new double[] {35.0, 0.285, 4.0, 48.0, 0.0, 4});
		cryseStats = verifyStat(prop.getDoubleList());

		prop = cat.get("starCryseStats");
		prop.setDefaultValues(new double[] {55.0, 0.315, 4.0, 48.0, 0.0, 4});
		starCryseStats = verifyStat(prop.getDoubleList());

		prop = cat.get("megaCryseStats");
		prop.setDefaultValues(new double[] {50.0, 0.310, 6.0, 48.0, 1.0, 18});
		megaCryseStats = verifyStat(prop.getDoubleList());

		prop = cat.get("norVoxStats");
		prop.setDefaultValues(new double[] {30.0, 0.390, 4.0, 32.0, 0.25, 8});
		norVoxStats = verifyStat(prop.getDoubleList());

		prop = cat.get("starVoxStats");
		prop.setDefaultValues(new double[] {40.0, 0.390, 4.0, 32.0, 0.25, 16});
		starVoxStats = verifyStat(prop.getDoubleList());

		prop = cat.get("voxStellarumStats");
		prop.setDefaultValues(new double[] {150.0, 0.460, 4.0, 64.0, 0.2, 16});
		voxStellarumStats = verifyStat(prop.getDoubleList());

		prop = cat.get("pirahStats");
		prop.setDefaultValues(new double[] {10.0, 0.450, 3.0, 16.0, 0.0, 0});
		pirahStats = verifyStat(prop.getDoubleList());

		prop = cat.get("goldenPirahStats");
		prop.setDefaultValues(new double[] {25.0, 0.450, 7.5, 16.0, 0.0, 0});
		goldenPirahStats = verifyStat(prop.getDoubleList());

		prop = cat.get("stinStats");
		prop.setDefaultValues(new double[] {40.0, 0.246, 10.0, 32.0, 0.5, 6});
		stinStats = verifyStat(prop.getDoubleList());

		prop = cat.get("stinBabyStats");
		prop.setDefaultValues(new double[] {16.0, 0.346, 6.0, 32.0, 0.0, 0});
		stinBabyStats = verifyStat(prop.getDoubleList());

		prop = cat.get("greaterStinStats");
		prop.setDefaultValues(new double[] {80.0, 0.276, 14.0, 24.0, 1.0, 12});
		greaterStinStats = verifyStat(prop.getDoubleList());

		prop = cat.get("stinKingStats");
		prop.setDefaultValues(new double[] {100.0, 0.226, 20.0, 32.0, 2.0, 20});
		stinKingStats = verifyStat(prop.getDoubleList());

		prop = cat.get("stinQueenStats");
		prop.setDefaultValues(new double[] {160.0, 0.186, 12.0, 24, 2.0, 10});
		stinQueenStats = verifyStat(prop.getDoubleList());

		prop = cat.get("kindlingSpiritStats");
		prop.setDefaultValues(new double[] {8.0, 0.476, 1.0, 16.0, 0.0, 0});
		kindlingSpiritStats = verifyStat(prop.getDoubleList());

		prop = cat.get("abominationStats");
		prop.setDefaultValues(new double[] {45.0, 0.276, 7.0, 32.0, 0.5, 4});
		abominationStats = verifyStat(prop.getDoubleList());

		prop = cat.get("erkelStats");
		prop.setDefaultValues(new double[] {16.0, 0.476, 1.0, 16.0, 0.0, 0});
		erkelStats = verifyStat(prop.getDoubleList());

		prop = cat.get("sirvStats");
		prop.setDefaultValues(new double[] {8.0, 0.375, 14.0, 64.0, 0.5, 0});
		sirvStats = verifyStat(prop.getDoubleList());

		prop = cat.get("lockbotStats");
		prop.setDefaultValues(new double[] {22.0, 0.0, 1.0, 8.0, 100.0, 8});
		lockbotStats = verifyStat(prop.getDoubleList());

		prop = cat.get("nanoSwarmStats");
		prop.setDefaultValues(new double[] {6.0, 0.335, 2.0, 64.0, 0.0, 0});
		nanoSwarmStats = verifyStat(prop.getDoubleList());

		prop = cat.get("hunterStats");
		prop.setDefaultValues(new double[] {16.0, 0.236, 4.0, 32.0, 0.0, 0});
		hunterStats = verifyStat(prop.getDoubleList());

		prop = cat.get("harvesterStats");
		prop.setDefaultValues(new double[] {56.0, 0.145, 0.0, 16.0, 100.0, 20});
		harvesterStats = verifyStat(prop.getDoubleList());

		prop = cat.get("seekerStats");
		prop.setDefaultValues(new double[] {30.0, 0.0, 1.0, 48.0, 100.0, 24});
		seekerStats = verifyStat(prop.getDoubleList());

		prop = cat.get("archangelStats");
		prop.setDefaultValues(new double[] {45.0, 0.0, 1.0, 32.0, 0.5, 12});
		archangelStats = verifyStat(prop.getDoubleList());

		prop = cat.get("ireStats");
		prop.setDefaultValues(new double[] {25.0, 0.0, 1.0, 16.0, 100.0, 0});
		ireStats = verifyStat(prop.getDoubleList());

		prop = cat.get("fuseaStats");
		prop.setDefaultValues(new double[] {10.0, 0.0, 0.0, 16.0, 100.0, 0});
		fuseaStats = verifyStat(prop.getDoubleList());

		prop = cat.get("volatileFuseaStats");
		prop.setDefaultValues(new double[] {18.0, 0.0, 0.0, 32.0, 100.0, 0});
		volatileFuseaStats = verifyStat(prop.getDoubleList());

		prop = cat.get("ranmasStats");
		prop.setDefaultValues(new double[] {50.0, 0.0, 1.0, 32.0, 100.0, 24});
		ranmasStats = verifyStat(prop.getDoubleList());

		prop = cat.get("parasmiteStats");
		prop.setDefaultValues(new double[] {10.0, 0.0, 1.0, 16.0, 0.0, 0});
		parasmiteStats = verifyStat(prop.getDoubleList());

		prop = cat.get("kurayamiStats");
		prop.setDefaultValues(new double[] {120.0, 0.420, 12.0, 64.0, 0.4, 10});
		kurayamiStats = verifyStat(prop.getDoubleList());

		prop = cat.get("avrisStats");
		prop.setDefaultValues(new double[] {75.0, 0.312, 2.0, 64.0, 0.6, 16});
		avrisStats = verifyStat(prop.getDoubleList());

		prop = cat.get("aegarStats");
		prop.setDefaultValues(new double[] {150.0, 0.185, 26.0, 32.0, 2.5, 24});
		aegarStats = verifyStat(prop.getDoubleList());

		prop = cat.get("apisStats");
		prop.setDefaultValues(new double[] {160.0, 0.375, 12.0, 32.0, 1.0, 16});
		apisStats = verifyStat(prop.getDoubleList());

		prop = cat.get("skultarStats");
		prop.setDefaultValues(new double[] {220.0, 0.350, 16.0, 32.0, 1.0, 20});
		skultarStats = verifyStat(prop.getDoubleList());

		prop = cat.get("kitsunakumaStats");
		prop.setDefaultValues(new double[] {80.0, 0.420, 6.0, 64.0, 0.0, 0});
		kitsunakumaStats = verifyStat(prop.getDoubleList());

		prop = cat.get("polarisStats");
		prop.setDefaultValues(new double[] {120.0, 0.440, 5.0, 64.0, 0.0, 14});
		polarisStats = verifyStat(prop.getDoubleList());

		prop = cat.get("empariahStats");
		prop.setDefaultValues(new double[] {140.0, 0.326, 16.0, 48.0, 2.0, 22});
		empariahStats = verifyStat(prop.getDoubleList());

		prop = cat.get("timeControllerStats");
		prop.setDefaultValues(new double[] {350.0, 0.366, 6.0, 64.0, 0.5, 18});
		timeControllerStats = verifyStat(prop.getDoubleList());

		prop = cat.get("enyvilStats");
		prop.setDefaultValues(new double[] {450.0, 0.276, 24.0, 48.0, 1.0, 4});
		enyvilStats = verifyStat(prop.getDoubleList());

		prop = cat.get("claymationStats");
		prop.setDefaultValues(new double[] {150.0, 0.320, 12.0, 32.0, 1.0, 18});
		claymationStats = verifyStat(prop.getDoubleList());

		prop = cat.get("overlordCocoonStats");
		prop.setDefaultValues(new double[] {500.0, 0.226, 24.0, 64.0, 4.5, 0});
		overlordCocoonStats = verifyStat(prop.getDoubleList());

		prop = cat.get("overlordCombatStats");
		prop.setDefaultValues(new double[] {500.0, 0.326, 24.0, 64.0, 4.5, 0});
		overlordCombatStats = verifyStat(prop.getDoubleList());

		prop = cat.get("overlordCoreStats");
		prop.setDefaultValues(new double[] {1000.0, 0.326, 24.0, 64.0, 4.5, 0});
		overlordCoreStats = verifyStat(prop.getDoubleList());

		cat = config.getCategory(CAT_POTION);
		cat.setComment("Modify various aspects of Potions.");
		cat.setRequiresMcRestart(true);

		prop = cat.get("flightAllow");
		prop.setDefaultValue(true);
		potionAllow[m = 0] = prop.getBoolean(true);

		prop = cat.get("aquaSuperiorityAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("immunityAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("resurrectionAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("harmonyAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("invulnerabilityAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("clarityAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("convergenceAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("divinityAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("corruptionAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("disorientationAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("stunAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("fearAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("malnourishAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("crippleAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("submissionAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("inhibitAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("leadFootAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("hackedAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("burnedAllow");
		prop.setDefaultValue(true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = cat.get("flightID");
		prop.setDefaultValue(findPotionID(32));
		idFlight = prop.getInt(findPotionID(32));

		prop = cat.get("aquaSuperiorityID");
		prop.setDefaultValue(findPotionID(idFlight));
		idAquaSuperiority = prop.getInt(findPotionID(idFlight));

		prop = cat.get("immunityID");
		prop.setDefaultValue(findPotionID(idAquaSuperiority));
		idImmunity = prop.getInt(findPotionID(idAquaSuperiority));

		prop = cat.get("resurrectionID");
		prop.setDefaultValue(findPotionID(idImmunity));
		idResurrection = prop.getInt(findPotionID(idImmunity));

		prop = cat.get("harmonyID");
		prop.setDefaultValue(findPotionID(idResurrection));
		idHarmony = prop.getInt(findPotionID(idResurrection));

		prop = cat.get("invulnernabilityID");
		prop.setDefaultValue(findPotionID(idHarmony));
		idInvulnerability = prop.getInt(findPotionID(idHarmony));

		prop = cat.get("clarityID");
		prop.setDefaultValue(findPotionID(idInvulnerability));
		idClarity = prop.getInt(findPotionID(idInvulnerability));

		prop = cat.get("convergenceID");
		prop.setDefaultValue(findPotionID(idClarity));
		idConvergence = prop.getInt(findPotionID(idClarity));

		prop = cat.get("divinityID");
		prop.setDefaultValue(findPotionID(idConvergence));
		idDivinity = prop.getInt(findPotionID(idConvergence));

		prop = cat.get("corruptionID");
		prop.setDefaultValue(findPotionID(idDivinity));
		idCorruption = prop.getInt(findPotionID(idDivinity));

		prop = cat.get("disorientationID");
		prop.setDefaultValue(findPotionID(idCorruption));
		idDisorientation = prop.getInt(findPotionID(idCorruption));

		prop = cat.get("stunID");
		prop.setDefaultValue(findPotionID(idDisorientation));
		idStun = prop.getInt(findPotionID(idDisorientation));

		prop = cat.get("fearID");
		prop.setDefaultValue(findPotionID(idStun));
		idFear = prop.getInt(findPotionID(idStun));

		prop = cat.get("malnourishID");
		prop.setDefaultValue(findPotionID(idFear));
		idMalnourish = prop.getInt(findPotionID(idFear));

		prop = cat.get("crippleID");
		prop.setDefaultValue(findPotionID(idMalnourish));
		idCripple = prop.getInt(findPotionID(idMalnourish));

		prop = cat.get("submissionID");
		prop.setDefaultValue(findPotionID(idCripple));
		idSubmission = prop.getInt(findPotionID(idCripple));

		prop = cat.get("inhibitID");
		prop.setDefaultValue(findPotionID(idSubmission));
		idInhibit = prop.getInt(findPotionID(idSubmission));

		prop = cat.get("leadFootID");
		prop.setDefaultValue(findPotionID(idInhibit));
		idLeadFoot = prop.getInt(findPotionID(idInhibit));

		prop = cat.get("hackedID");
		prop.setDefaultValue(findPotionID(idLeadFoot));
		idHacked = prop.getInt(findPotionID(idLeadFoot));

		prop = cat.get("burnedID");
		prop.setDefaultValue(findPotionID(idHacked));
		idBurned = prop.getInt(findPotionID(idHacked));

		cat = config.getCategory(CAT_VANILLA);
		cat.setComment("These toggle the various changes the mod does that explicitly affects Vanilla Minecraft. Ore rate and vein size only affects those ores generated in Vanilla Dimensions.");
		cat.setRequiresMcRestart(true);

		prop = cat.get("allowMobBuffs");
		prop.setDefaultValue(true);
		prop.comment = "Will various Vanilla Mobs gain a Health, Attack Damage, Knockback Resistance or Speed buff?";
		vanillaConfig[m = 0] = prop.getBoolean(true);

		prop = cat.get("allowExtraMobEffects");
		prop.setDefaultValue(true);
		prop.comment = "Will some mobs gain Potion Effect debuffs and other abilities along with their normal attacks?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowAnimalRetribution");
		prop.setDefaultValue(true);
		prop.comment = "Can slaying animals sometimes trigger a Lightning strike?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowMobModdedArmor");
		prop.setDefaultValue(true);
		prop.comment = "Can Vanilla mobs sometimes spawn in with Armor from the mod?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowRespawnPunishment");
		prop.setDefaultValue(true);
		prop.comment = "Will you get inflicted with negative effects upon respawning after a death?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowExtraExplosiveEffects");
		prop.setDefaultValue(true);
		prop.comment = "Do explosions inflict extra negative effects on you when hit?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowMobBlindnessDebuff");
		prop.setDefaultValue(true);
		prop.comment = "Does Blindness reduce the follow range of mobs?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowExtraOverworldFlowers");
		prop.setDefaultValue(true);
		prop.comment = "Can some of the mod-exclusive flowers generate in Vanilla biomes?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowOverworldSilverfishGen");
		prop.setDefaultValue(true);
		prop.comment = "Will Silverfish stone generate in lower y-levels in the Overworld?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowNetherOreGen");
		prop.setDefaultValue(true);
		prop.comment = "Can mod-exclusive ores generate in the Nether?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowOverworldOreGen");
		prop.setDefaultValue(true);
		prop.comment = "Can mod-exclusive ores generate in the Overworld?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowDrudgeGen");
		prop.setDefaultValue(true);
		prop.comment = "Can Drudge generate in the Nether?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowAnimalAndGolemCorruption");
		prop.setDefaultValue(true);
		prop.comment = "Can Animals and Golems become Corrupted?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowCowMinotaurCreation");
		prop.setDefaultValue(true);
		prop.comment = "Will striking a Cow with Lightning turn it into a Minotaur?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = cat.get("rubyOreGenRate");
		prop.setDefaultValue(10);
		rubyOreRate = prop.getInt(10);

		prop = cat.get("rubyOreVeinSize");
		prop.setDefaultValue(3);
		rubyOreVeinSize = prop.getInt(3);

		prop = cat.get("sapphireOreGenRate");
		prop.setDefaultValue(10);
		sapphireOreRate = prop.getInt(10);

		prop = cat.get("sapphireOreVeinSize");
		prop.setDefaultValue(3);
		sapphireOreVeinSize = prop.getInt(3);

		prop = cat.get("mercuryOreGenRate");
		prop.setDefaultValue(20);
		mercuryOreRate = prop.getInt(20);

		prop = cat.get("mercuryOreVeinSize");
		prop.setDefaultValue(4);
		mercuryOreVeinSize = prop.getInt(4);

		prop = cat.get("tungstenOreGenRate");
		prop.setDefaultValue(10);
		tungstenOreRate = prop.getInt(10);

		prop = cat.get("tungstenOreVeinSize");
		prop.setDefaultValue(3);
		tungstenOreVeinSize = prop.getInt(3);

		prop = cat.get("drudgeGenRate");
		prop.setDefaultValue(10);
		drudgeRate = prop.getInt(10);

		prop = cat.get("drudgeVeinSize");
		prop.setDefaultValue(10);
		drudgeVeinSize = prop.getInt(10);

		prop = cat.get("silverfishStoneGenRate");
		prop.setDefaultValue(12);
		silverfishRate = prop.getInt(12);

		prop = cat.get("silverfishStoneVeinSize");
		prop.setDefaultValue(3);
		silverfishVeinSize = prop.getInt(3);

		prop = cat.get("aerisRarity");
		prop.setDefaultValue(5);
		aerisRarity = prop.getInt(5);

		cat = config.getCategory(CAT_WORLDGEN);
		cat.setComment("Change things related to the mod-exclusive Dimensional World Generation.");
		cat.setRequiresMcRestart(true);

		prop = cat.get("allowVoidPits");
		prop.setDefaultValue(true);
		prop.comment = "Should void pits be allowed to generate?";
		worldGenConfig[m = 0] = prop.getBoolean(true);

		prop = cat.get("allowSpikes");
		prop.setDefaultValue(true);
		prop.comment = "Should large spikes, ice spikes, star crystals and crystal spikes be allowed to generate?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowScatteredSurfaceWorldGen");
		prop.setDefaultValue(true);
		prop.comment = "Should scattered surface features like mixed dirt, light orbs and cracked permafrost be allowed to generate?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowStringLights");
		prop.setDefaultValue(true);
		prop.comment = "Should String Lights be allowed to generate?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowDarkStoneVariantGen");
		prop.setDefaultValue(true);
		prop.comment = "Should Dark Stone have layers generate as a colored variant?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowStructureGen");
		prop.setDefaultValue(true);
		prop.comment = "Should mod-exclusive Structures be allowed to generate?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowRoughTerrainGen");
		prop.setDefaultValue(true);
		prop.comment = "Should rough terrain like in the Tainted or Scorched Scarlands be generated?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowDigitalSeaGen");
		prop.setDefaultValue(true);
		prop.comment = "Should Digital Sea generate in the Synapse?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowFruitGen");
		prop.setDefaultValue(true);
		prop.comment = "Should Honeydrop, Deathglow and Sky Fruit generate naturally?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowIsleGen");
		prop.setDefaultValue(true);
		prop.comment = "Should Isles be generated in the Tainted Isles biome?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowFlowerGen");
		prop.setDefaultValue(true);
		prop.comment = "Should flowers generate in the mod's Dimensions?";
		worldGenConfig[++m] = prop.getBoolean(true);

		cat = config.getCategory(CAT_STRUCTURE);
		cat.setComment("Toggle specific structures and change their rarities. Higher number is higher chance to generate.");
		cat.setRequiresWorldRestart(true);

		prop = cat.get("structureOverallRarity");
		prop.setDefaultValue(15);
		structureOverallRarity = clamp(prop.getInt(15), 1, 500);

		prop = cat.get("apisTempleAllow");
		prop.setDefaultValue(true);
		structureAllow[m = 0] = prop.getBoolean(true);

		prop = cat.get("randomTowerAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("deathCircleAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("obsidianCavernAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("kitsunakumaDenAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("celestialTempleAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("timeAltarAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("soulTombAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("corruptedSpireAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("empariahCaveAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("claymationRuinAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("darkHutAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("spiderNestAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("memoryCacheAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("lightSpireAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("hackerNetAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("cubeMazeAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("outlookAllow");
		prop.setDefaultValue(true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = cat.get("apisTempleRarity");
		prop.setDefaultValue(5);
		structureRarity[m = 0] = clamp(prop.getInt(5), 1, 200);

		prop = cat.get("randomTowerRarity");
		prop.setDefaultValue(15);
		structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

		prop = cat.get("deathCircleRarity");
		prop.setDefaultValue(5);
		structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

		prop = cat.get("obsidianCavernRarity");
		prop.setDefaultValue(10);
		structureRarity[++m] = clamp(prop.getInt(10), 1, 200);

		prop = cat.get("kitsunakumaDenRarity");
		prop.setDefaultValue(5);
		structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

		prop = cat.get("celestialTempleRarity");
		prop.setDefaultValue(10);
		structureRarity[++m] = clamp(prop.getInt(10), 1, 200);

		prop = cat.get("timeAltarRarity");
		prop.setDefaultValue(3);
		structureRarity[++m] = clamp(prop.getInt(3), 1, 200);

		prop = cat.get("soulTombRarity");
		prop.setDefaultValue(15);
		structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

		prop = cat.get("corruptedSpireRarity");
		prop.setDefaultValue(0);
		structureRarity[++m] = clamp(prop.getInt(0), 1, 200);

		prop = cat.get("empariahCaveRarity");
		prop.setDefaultValue(5);
		structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

		prop = cat.get("claymationRuinRarity");
		prop.setDefaultValue(5);
		structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

		prop = cat.get("darkHutRarity");
		prop.setDefaultValue(15);
		structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

		prop = cat.get("spiderNestRarity");
		prop.setDefaultValue(15);
		structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

		prop = cat.get("memoryCacheRarity");
		prop.setDefaultValue(3);
		structureRarity[++m] = clamp(prop.getInt(3), 1, 200);

		prop = cat.get("lightSpireRarity");
		prop.setDefaultValue(10);
		structureRarity[++m] = clamp(prop.getInt(10), 1, 200);

		prop = cat.get("hackerNetRarity");
		prop.setDefaultValue(3);
		structureRarity[++m] = clamp(prop.getInt(3), 1, 200);

		prop = cat.get("cubeMazeRarity");
		prop.setDefaultValue(25);
		structureRarity[++m] = clamp(prop.getInt(25), 1, 200);

		prop = cat.get("outlookRarity");
		prop.setDefaultValue(3);
		structureRarity[++m] = clamp(prop.getInt(3), 1, 200);

		cat = config.getCategory(CAT_MISC);
		cat.setComment("Random other options that don't quite fit into other categories.");

		prop = cat.get("allowRandomWeaponLore");
		prop.setDefaultValue(true);
		prop.comment = "Should mod-exclusive weapons and armor come with randomized Lore?";
		allowRandomWeaponLore = prop.getBoolean(true);

		prop = cat.get("allowChallengeScrolls");
		prop.setDefaultValue(true);
		prop.comment = "Are Challenge Scrolls enabled?";
		allowChallengeScrolls = prop.getBoolean(true);

		prop = cat.get("allowMobStatueDrops");
		prop.setDefaultValue(true);
		prop.comment = "Can certain mobs drop statues?";
		allowMobStatueDrops = prop.getBoolean(true);

		prop = cat.get("allowGeneratorItems");
		prop.setDefaultValue(true);
		prop.comment = "Will Creative mode-only generator items be enabled?";
		allowGeneratorItems = prop.getBoolean(true);

		prop = cat.get("allowItemTimeAltering");
		prop.setDefaultValue(true);
		prop.comment = "Certain items and blocks may alter in-game time, should this be allowed?";
		allowItemTimeAltering = prop.getBoolean(true);

		prop = cat.get("allowPvP");
		prop.setDefaultValue(true);
		prop.comment = "Should Doomsdays and Weapons be able to be used against other players?";
		allowPvP = prop.getBoolean(true);

		prop = cat.get("allowDefaultLores");
		prop.setDefaultValue(true);
		prop.comment = "Should the Lores bundled with the mod be used? Perhaps disable them if you wish to only use your own custom Lore.";
		allowDefaultLores = prop.getBoolean(true);

		prop = cat.get("allowCorruptionTransfer");
		prop.setDefaultValue(true);
		prop.comment = "Should Corruption transfer between entities?";
		allowCorruptionTransfer = prop.getBoolean(true);

		prop = cat.get("challengeScrollDropChance");
		prop.setDefaultValue(5);
		prop.comment = "Chance for Challenge Scrolls to drop off of any mob that you kill.";
		challengeScrollDropChance = clamp(prop.getInt(5), 1, 100);

		prop = cat.get("mobStatueDropChance");
		prop.setDefaultValue(100);
		prop.comment = "Chance for a mob to drop it's corresponding statue. Only certain mobs have these.";
		mobStatueDropChance = clamp(prop.getInt(100), 1, 100);

		cat = config.getCategory(CAT_CLIENT);
		cat.setComment("These are client-side only options, they affect nothing on the server-side.");
		cat.setShowInGui(true);

		prop = cat.get("allowAnimatedGui");
		prop.setDefaultValue(true);
		allowAnimatedGui = prop.getBoolean(true);

		prop = cat.get("allowArmorModels");
		prop.setDefaultValue(true);
		allowArmorModels = prop.getBoolean(true);

		prop = cat.get("allowWeaponModels");
		prop.setDefaultValue(true);
		allowWeaponModels = prop.getBoolean(true);

		prop = cat.get("allowDivinityColorChange");
		prop.setDefaultValue(true);
		allowDivinityColorChange = prop.getBoolean(true);
		
		prop = cat.get("showDoomGui");
		prop.setDefaultValue(true);
		showDoomGui = prop.getBoolean(true);
		
		prop = cat.get("showAmuletStatusGui");
		prop.setDefaultValue(true);
		showAmuletStatusGui = prop.getBoolean(true);

		prop = cat.get("guiTransparency");
		prop.setDefaultValue(100);
		guiTransparency = clamp(prop.getInt(100), 1, 100);

		prop = cat.get("guiTextureSkinID");
		prop.setDefaultValue(0);
		guiTexture = clamp(prop.getInt(0), 0, 100);

		prop = cat.get("guiX");
		prop.setDefaultValue(1);
		guiX = clampPositive(prop.getInt(1));

		prop = cat.get("guiY");
		prop.setDefaultValue(1);
		guiY = clampPositive(prop.getInt(1));

		cat = config.getCategory(CAT_GRIEF);
		cat.setComment("Toggle whether specific Weapon abilities or Doomsdays damage the terrain.");

		prop = cat.get("allowNatureDrainDestruction");
		prop.setDefaultValue(true);
		griefConfig[m = 0] = prop.getBoolean(true);

		prop = cat.get("allowRavageDestruction");
		prop.setDefaultValue(true);
		griefConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowFrozenLightningDestruction");
		prop.setDefaultValue(true);
		griefConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowMourningStarDestruction");
		prop.setDefaultValue(true);
		griefConfig[++m] = prop.getBoolean(true);

		prop = cat.get("allowTitanDestruction");
		prop.setDefaultValue(true);
		griefConfig[++m] = prop.getBoolean(true);

		cat = config.getCategory(CAT_MODIFIERS);
		cat.setComment("Set each mob's modifier's actual amount, these can also be used to disable them by setting them to 0 in most cases.");
		//TODO finish setting up modifiers
		//load2();
	}

	private static void load2() //only if necessary, will most likely be
	{
		Configuration config = TragicMC.getConfig();

		ConfigCategory cat;
		Property prop;
		byte m;

		if (config.hasChanged()) config.save();
		postProcessConfigs();
	}

	private static void postProcessConfigs()
	{
		if (mobsOnlyMode)
		{
			TragicMC.logInfo("mobsOnly mode is enabled, overriding other config options.");
			hardcoreMode = false;
			lightweightMode = false;
			barebonesMode = false;
			setupMobsOnly();
		}

		if (hardcoreMode)
		{
			TragicMC.logInfo("hardcoreMode is enabled, overriding other config options.");
			lightweightMode = false;
			barebonesMode = false;
			setupHardcoreMode();
		}

		if (lightweightMode)
		{
			TragicMC.logInfo("lightweightMode is enabled, overriding other config options.");
			barebonesMode = false;
			setupLightweightMode();
		}
		
		if (barebonesMode)
		{
			TragicMC.logInfo("barebonesMode is enabled, overriding other config options.");
			setupBarebonesMode();
		}

		byte b;

		if (!allowAchievements)
		{
			//TODO disable achievements if the blanket config is toggled off
		}

		if (!allowAmulets)
		{
			for (b = 0; b < amuletConfig.length; b++) amuletConfig[b] = false;
			for (b = 0; b < amuletEffects.length; b++) amuletEffects[b] = false;
		}

		if (!allowDimension)
		{
			for (b = 0; b < dimensionConfig.length; b++) dimensionConfig[b] = false;
		}

		if (!allowDoom)
		{
			for (b = 0; b < doomConfig.length; b++) doomConfig[b] = false;
		}

		if (!doomConfig[6]) //non-Doomsday abilities
		{
			for (b = 0; b < doomAbility.length; b++) doomAbility[b] = false;
		}

		if (!allowMobs)
		{
			for (b = 0; b < mobConfig.length; b++) mobConfig[b] = false;
		}

		if (!mobConfig[0]) //mobs
		{
			for (b = 0; b < mobAllow.length; b++) mobAllow[b] = false;
		}

		if (!mobConfig[1]) //mini-bosses
		{
			for (b = 0; b < miniBossAllow.length; b++) miniBossAllow[b] = false;
		}

		if (!mobConfig[2]) //bosses
		{
			for (b = 0; b < bossAllow.length; b++) bossAllow[b] = false;
		}

		if (!allowPotions)
		{
			for (b = 0; b < potionAllow.length; b++) potionAllow[b] = false;
		}

		if (!allowVanillaChanges)
		{
			for (b = 0; b < vanillaConfig.length; b++) vanillaConfig[b] = false;
		}

		if (!allowWorldGen)
		{
			for (b = 0; b < worldGenConfig.length; b++) worldGenConfig[b] = false;
		}

		if (!worldGenConfig[5]) //structures
		{
			for (b = 0; b < structureAllow.length; b++) structureAllow[b] = false;
		}

		initializeRemainingVariables();
	}

	private static void initializeRemainingVariables()
	{
		byte m;
		allowAmuletLeveling = amuletConfig[m = 0];
		allowAmuletCrafting = amuletConfig[++m];
		requireAmuletSlotUnlock = amuletConfig[++m];
		allowAmuletBossKillCharge = amuletConfig[++m];
		allowAmuletModifiers = amuletConfig[++m];
		allowAmuletDeathDrops = amuletConfig[++m];

		amuPeace = amuletEffects[m = 0];
		amuYeti = amuletEffects[++m];
		amuClaymation = amuletEffects[++m];
		amuChicken = amuletEffects[++m];
		amuBlacksmith = amuletEffects[++m];
		amuCreeper = amuletEffects[++m];
		amuZombie = amuletEffects[++m];
		amuSkeleton = amuletEffects[++m];
		amuIce = amuletEffects[++m];
		amuSnowGolem = amuletEffects[++m];
		amuIronGolem = amuletEffects[++m];
		amuSpider = amuletEffects[++m];
		amuStin = amuletEffects[++m];
		amuSupernatural = amuletEffects[++m];
		amuFusea = amuletEffects[++m];
		amuLuck = amuletEffects[++m];
		amuKitsune = amuletEffects[++m];
		amuMartyr = amuletEffects[++m];
		amuPiercing = amuletEffects[++m];
		amuApis = amuletEffects[++m];
		amuSunken = amuletEffects[++m];
		amuEnderman = amuletEffects[++m];
		amuPolaris = amuletEffects[++m];
		amuLightning = amuletEffects[++m];
		amuConsumption = amuletEffects[++m];
		amuUndead = amuletEffects[++m];
		amuEnderDragon = amuletEffects[++m];
		amuTime = amuletEffects[++m];
		amuWither = amuletEffects[++m];
		amuOverlord = amuletEffects[++m];
		amuEnyvil = amuletEffects[++m];

		allowSynapse = dimensionConfig[m = 0];
		allowCollision = dimensionConfig[++m];
		allowCollisionRespawn = dimensionConfig[++m];
		allowSynapseRespawn = dimensionConfig[++m];
		keepCollisionLoaded = dimensionConfig[++m];
		keepSynapseLoaded = dimensionConfig[++m];
		allowSynapseVariants = dimensionConfig[++m];

		allowDoomsdays = doomConfig[m = 0];
		allowInfluenceDoomsdays = doomConfig[++m];
		allowOverflowDoomsdays = doomConfig[++m];
		allowCrisisDoomsdays = doomConfig[++m];
		allowWorldShaperDoomsdays = doomConfig[++m];
		allowCombinationDoomsdays = doomConfig[++m];
		allowNonDoomsdayAbilities = doomConfig[++m];
		shouldDoomLimitIncrease = doomConfig[++m];
		allowConsumeRefill = doomConfig[++m];
		allowDoomPainRecharge = doomConfig[++m];
		allowNaturalRecharge = doomConfig[++m];
		allowCrucialMoments = doomConfig[++m];
		allowBacklash = doomConfig[++m];
		allowCooldown = doomConfig[++m];
		allowDoomKillRecharge = doomConfig[++m];
		allowCooldownDefuse = doomConfig[++m];

		allowNormalMobs = mobConfig[m = 0];
		allowMiniBosses = mobConfig[++m];
		allowBosses = mobConfig[++m];
		allowBossOverworldSpawns = mobConfig[++m];
		allowExtraBossLoot = mobConfig[++m];
		allowMobTransformation = mobConfig[++m];
		allowDynamicHealthScaling = mobConfig[++m];
		allowNonDimensionMobSpawns = mobConfig[++m];
		allowGroupBuffs = mobConfig[++m];
		allowEasyBosses = mobConfig[++m];
		allowMobSounds = mobConfig[++m];

		allowJabba = mobAllow[m = 0];
		allowJanna = mobAllow[++m];
		allowPlague = mobAllow[++m];
		allowGragul = mobAllow[++m];
		allowMinotaur = mobAllow[++m];
		allowInkling = mobAllow[++m];
		allowRagr = mobAllow[++m];
		allowPumpkinhead = mobAllow[++m];
		allowTragicNeko = mobAllow[++m];
		allowTox = mobAllow[++m];
		allowPox = mobAllow[++m];
		allowCryse = mobAllow[++m];
		allowStarCryse = mobAllow[++m];
		allowNorVox = mobAllow[++m];
		allowStarVox = mobAllow[++m];
		allowPirah = mobAllow[++m];
		allowStin = mobAllow[++m];
		allowStinBaby = mobAllow[++m];
		allowKindlingSpirit = mobAllow[++m];
		allowAbomination = mobAllow[++m];
		allowErkel = mobAllow[++m];
		allowSirv = mobAllow[++m];
		allowPsygote = mobAllow[++m];
		allowLockbot = mobAllow[++m];
		allowNanoSwarm = mobAllow[++m];
		allowSnowGolem = mobAllow[++m];
		allowHunter = mobAllow[++m];
		allowHarvester = mobAllow[++m];
		allowArchangel = mobAllow[++m];
		allowIre = mobAllow[++m];
		allowFusea = mobAllow[++m];
		allowRanmas = mobAllow[++m];
		allowAvris = mobAllow[++m];

		allowJarra = miniBossAllow[m = 0];
		allowKragul = miniBossAllow[++m];
		allowMagmox = miniBossAllow[++m];
		allowMegaCryse = miniBossAllow[++m];
		allowVoxStellarum = miniBossAllow[++m];
		allowGreaterStin = miniBossAllow[++m];
		allowStinKing = miniBossAllow[++m];
		allowStinQueen = miniBossAllow[++m];
		allowAegar = miniBossAllow[++m];
		allowVolatileFusea = miniBossAllow[++m];

		allowApis = bossAllow[m = 0];
		allowSkultar = bossAllow[++m];
		allowKitsunakuma = bossAllow[++m];
		allowEmpariah = bossAllow[++m];
		allowTimeController = bossAllow[++m];
		allowPolaris = bossAllow[++m];
		allowEnyvil = bossAllow[++m];
		allowClaymation = bossAllow[++m];
		allowOverlord = bossAllow[++m];

		allowFlight = potionAllow[m = 0];
		allowAquaSuperiority = potionAllow[++m];
		allowImmunity = potionAllow[++m];
		allowResurrection = potionAllow[++m];
		allowHarmony = potionAllow[++m];
		allowInvulnerability = potionAllow[++m];
		allowClarity = potionAllow[++m];
		allowConvergence = potionAllow[++m];
		allowDivinity = potionAllow[++m];
		allowCorruption = potionAllow[++m];
		allowDisorientation = potionAllow[++m];
		allowStun = potionAllow[++m];
		allowFear = potionAllow[++m];
		allowMalnourish = potionAllow[++m];
		allowCripple = potionAllow[++m];
		allowSubmission = potionAllow[++m];
		allowInhibit = potionAllow[++m];
		allowLeadFoot = potionAllow[++m];
		allowHacked = potionAllow[++m];
		allowBurned = potionAllow[++m];

		allowVanillaMobBuffs = vanillaConfig[m = 0];
		allowExtraMobEffects = vanillaConfig[++m];
		allowAnimalRetribution = vanillaConfig[++m];
		allowMobModdedArmor = vanillaConfig[++m];
		allowRespawnPunishment = vanillaConfig[++m];
		allowExtraExplosiveEffects = vanillaConfig[++m];
		allowMobBlindnessDebuff = vanillaConfig[++m];
		allowExtraOverworldFlowers = vanillaConfig[++m];
		allowOverworldSilverfishGen = vanillaConfig[++m];
		allowNetherOreGen = vanillaConfig[++m];
		allowOverworldOreGen = vanillaConfig[++m];
		allowDrudgeGen = vanillaConfig[++m];
		allowAnimalGolemCorruption = vanillaConfig[++m];
		allowCowMinotaurCreation = vanillaConfig[++m];
	}

	private static void setupMobsOnly()
	{
		//blanket configs
		allowAchievements = false;
		allowAmulets = false;
		allowDimension = false;
		allowDoom = false;
		allowEnchantments = false;
		allowPotions = false;
		allowVanillaChanges = false;
		allowWorldGen = false;

		//internal mod options
		allowNonMobItems = false;
		allowNonMobBlocks = false;
		allowNetwork = false;
		allowRecipes = false;
		allowSurvivalTab = false;
	}

	private static void setupHardcoreMode()
	{
		//blanket configs
		allowAchievements = true;
		allowAmulets = true;
		allowDimension = true;
		allowDoom = true;
		allowEnchantments = true;
		allowMobs = true;
		allowPotions = true;
		allowVanillaChanges = true;
		allowWorldGen = true;

		//internal mod options
		allowNonMobItems = true;
		allowNonMobBlocks = true;
		allowNetwork = true;
		allowRecipes = true;
		allowSurvivalTab = true;

		//hardcore-exclusive options
		//disable epic amulets
		amuletConfig[13] = false; //time
		amuletConfig[18] = false; //wither
		amuletConfig[22] = false; //overlord
		amuletConfig[29] = false; //enyvil
		
		//simpified doom setup, disable easy mode things
		maxDoomAmount = 10;
		maxDoomStart = 5;
		doomConfig[0] = true; //doomsdays
		doomConfig[10] = false; //natural recharge
		doomConfig[11] = false; //crucial moments
		doomConfig[9] = false; //doom pain recharge
		doomConfig[8] = false; //consume refill
		doomConfig[15] = false; //cooldown defuse
		doomConsumeAmount = 1;
		doomRechargeAmount = 1;
		for (byte b = 0; b < doomsdayCost.length; b++) doomsdayCost[b] = doomsdayCost[b] > 100 ? 3 : (doomsdayCost[b] > 50 ? 2 : 1); //basically 1 - 50 is simplified to 1, 51 - 100 is simplified to 2, everything higher is 3
		
		//remove crutch potion effects
		potionAllow[2] = false; //immunity
		potionAllow[3] = false; //resurrection
		potionAllow[4] = false; //harmony
		potionAllow[6] = false; //clarity
		
		//remove avris, erkel and wisps
		mobAllow[32] = false; //avris
		mobAllow[18] = false; //wisps
		mobAllow[20] = false; //erkel
		
		//TODO setup mob stat changes for hardcore mode
	}

	private static void setupLightweightMode()
	{
		//remove a lot of the risks, backlash, respawn punishment, simplify doom costs to use 5 to 25 with the max values set to the defaults
		//mob stats are balanced more to fit in with vanilla mob amounts (20 health, 4 - 6 attack damage, etc.)
		//bosses are balanced to fit in more with the vanilla bosses (200 health, 6 attack damage, etc.)
		//less negative effects are enabled, plagues have their natural spawns disabled and mobs cannot transform into mini-bosses
		doomConfig[12] = false; //backlash
		doomConfig[13] = false; //cooldown
		allowRespawnPunishment = false;
		
		//TODO setup mob stat changes for lightweight mode
	}
	
	private static void setupBarebonesMode()
	{
		//removes a lot of the newer stuff this version adds from the original one, doom, amulets, dimensions
		//retains the simplicity of the original by only having mobs, weapons and other items available
		
		//blanket configs
		allowAchievements = true;
		allowAmulets = false;
		allowDimension = false;
		allowDoom = false;
		allowEnchantments = true;
		allowMobs = true;
		allowPotions = false;
		allowVanillaChanges = false;
		allowWorldGen = false;

		//internal mod options
		allowNonMobItems = true;
		allowNonMobBlocks = false;
		allowNetwork = true;
		allowRecipes = true;
		allowSurvivalTab = true;
	}

	public static int clampPositive(int value) {
		return value > 0 ? value : 0;
	}

	public static int clamp(final int value, final int min, final int max) {
		return net.minecraft.util.MathHelper.clamp_int(value, min, max);
	}

	private static double[] verifyStat(final double[] array) {
		return verify(array, 6);
	}

	private static double[] verify(final double[] array, final int amt)
	{
		if (amt != array.length)
		{
			throw new IllegalArgumentException("Invalid array length, required length was " + amt + ", array length was " + array.length);
		}

		for (int i = 0; i < amt; i++)
		{
			if (Double.isNaN(array[i]))
			{
				throw new IllegalArgumentException("Value of " + array[i] + " was invalid.");
			}
		}
		return array;
	}

	private static int findOpenID(final Object[] array, int start, final boolean loop)
	{
		final int l = array.length;

		if (start < l && array[start] == null) return start;
		else if (start >= l || start < 0) start = 0;

		boolean once = !loop;

		for (int i = 0; i < l; i++)
		{
			if (!once && start + i >= l) //only check through the entire array once
			{
				start = i = 0;
				once = true;
			}
			else if (once && start + i >= l) break;

			if (array[start + i] != null) continue;
			return start + i;
		}

		TragicMC.logWarning("No valid values were found for starting id of " + start + ", default to 0, may result in a crash.");
		return 0;
	}

	public static int findBiomeID(int start) {
		return findOpenID(BiomeGenBase.getBiomeGenArray(), start, true);
	}

	public static int findEnchantID(int start) {
		return findOpenID(Enchantment.enchantmentsList, start, true);
	}

	public static int findPotionID(int start) {
		return findOpenID(Potion.potionTypes, start, false);
	}
}
