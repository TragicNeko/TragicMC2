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

	//options meant for internal use, for toggling via master configs
	public static boolean allowNonMobItems, allowNonMobBlocks, allowNetwork, allowRecipes, allowSurvivalTab;

	public static boolean mobsOnly, hardcoreMode, lightweightMode;
	public static boolean allowAchievements, allowAmulets, allowDimension, allowDoom, allowEnchantments, allowMobs, allowPotions, allowVanillaChanges, allowWorldGen;

	private static boolean[] amuletConfig = new boolean[16];
	public static boolean allowAmuletLeveling, allowAmuletCrafting, requireAmuletSlotUnlock, allowAmuletBossKillCharge, showAmuletStatusGui, allowAmuletModifiers, allowAmuletDeathDrops;
	public static int amuletMaxSlots, amuletOverallRarity, amuletReleaseRarity, amuletModChance, amuletModChance2, amuletModChance3;
	private static boolean[] amuletEffects = new boolean[48];
	public static boolean amuPeace, amuYeti, amuClaymation, amuChicken, amuBlacksmith, amuCreeper, amuZombie, amuSkeleton, amuIce, amuSnowGolem, amuIronGolem;
	public static boolean amuSpider, amuStin, amuSupernatural, amuFusea, amuLuck, amuKitsune, amuMartyr, amuPiercing, amuApis, amuSunken, amuEnderman, amuPolaris, amuLightning;
	public static boolean amuConsumption, amuUndead, amuEnderDragon, amuTime, amuWither, amuOverlord, amuEnyvil;

	public static boolean sphereGenUsesFilter, eraserUsesFilter, spikeGenUsesFilter, voidPitUsesFilter;
	public static String[] sphereFilter = new String[64], eraserFilter = new String[64], spikeFilter = new String[64], voidPitFilter = new String[64];
	public static double sphereSize, eraserSize, spikeSize, spikeRegression, spikeCutoff, voidPitSize, explosionBaseSize, explosionSizeVariation;

	private static boolean[] dimensionConfig = new boolean[16];
	public static boolean allowSynapse, allowCollision, allowCollisionRespawn, allowSynapseRespawn, keepCollisionLoaded, keepSynapseLoaded, allowSynapseVariants, allowDigitalSeaGen;
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
	public static boolean allowCooldown, allowDoomKillRecharge, allowCooldownDefuse, showDoomGui;
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

	public static boolean[] mobConfig = new boolean[16]; //TODO move allowCorruptionDamage to miscConfig or potionCofig
	public static boolean allowNormalMobs, allowMiniBosses, allowBosses, allowBossOverworldSpawns, allowExtraBossLoot, allowMobTransformation;
	public static boolean allowDynamicHealthScaling, allowNonDimensionMobSpawns, allowGroupBuffs, allowEasyBosses, allowMobSounds;
	public static boolean[] mobAllow = new boolean[64];
	public static boolean allowJabba, allowJanna, allowPlague, allowGragul, allowMinotaur, allowInkling, allowRagr, allowPumpkinhead, allowTragicNeko, allowTox, allowPox;
	public static boolean allowCryse, allowStarCryse, allowNorVox, allowStarVox, allowPirah, allowLavaPirah, allowStin, allowStinBaby, allowWisp, allowAbomination, allowErkel;
	public static boolean allowSirv, allowPsygote, allowLockbot, allowNanoSwarm, allowSnowGolem, allowHunter, allowHarvester, allowArchangel, allowIre;
	public static boolean allowFusea, allowRanmas, allowParasmite, allowAvris;
	public static boolean[] miniBossAllow = new boolean[32];
	public static boolean allowJarra, allowKragul, allowMagmox, allowMegaCryse, allowVoxStellarum, allowGreaterStin, allowStinKing, allowStinQueen, allowAegar, allowVolatileFusea;
	public static boolean[] bossAllow = new boolean[24];
	public static boolean allowApis, allowDeathReaper, allowKitsune, allowYeti, allowTimeController, allowPolaris, allowEnyvil, allowClaymation, allowOverlord;

	public static int jabbaSC, jannaSC, plagueSC, gragulSC, minotaurSC, inklingSC, ragrSC, pumpkinheadSC, tragicNekoSC, toxSC, poxSC, cryseSC, starCryseSC, norVoxSC, starVoxSC;
	public static int pirahSC, lavaPirahSC, stinSC, stinBabySC, wispSC, abominationSC, erkelSC, sirvSC, psygoteSC, lockbotSC, nanoSwarmSC, snowGolemSC, hunterSC;
	public static int harvesterSC, archangelSC, ireSC, fuseaSC, ranmasSC, parasmiteSC, avrisSC, jarraSC, kragulSC, magmoxSC, megaCryseSC, voxStellarumSC, greaterStinSC;
	public static int stinKingSC, stinQueenSC, locobotSC, aegarSC, volatileFuseaSC, apisSC, deathReaperSC, kitsuneSC, yetiSC, timeControllerSC, polarisSC, enyvilSC, claymationSC, overlordSC;

	public static double[] jabbaStats, jannaStats, plagueStats, gragulStats, minotaurStats, inklingStats, ragrStats, pumpkinheadStats, tragicNekoStats, toxStats, poxStats, cryseStats;
	public static double[] starCryseStats, norVoxStats, starVoxStats, goldenPirahStats, pirahStats, stinStats, stinBabyStats, wispStats, abominationStats, erkelStats, sirvStats, psygoteStats;
	public static double[] lockbotStats, nanoSwarmStats, hunterStats, harvesterStats, seekerStats, archangelStats, ireStats, fuseaStats, ranmasStats, parasmiteStats, kurayamiStats, avrisStats;
	public static double[] jarraStats, kragulStats, magmoxStats, megaCryseStats, voxStellarumStats, greaterStinStats, stinKingStats, stinQueenStats, locobotStats, aegarStats, volatileFuseaStats;
	public static double[] apisStats, deathReaperStats, kitsuneStats, yetiStats, timeControllerStats, polarisStats, enyvilStats, claymationStats, overlordCoreStats, overlordCombatStats, overlordCocoonStats;

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

		prop = cat.get("mobsOnly");
		prop.setDefaultValue(false);
		prop.comment = "Is mobs only mode enabled?";
		mobsOnly = prop.getBoolean(false);

		prop = cat.get("hardcoreMode");
		prop.setDefaultValue(false);
		prop.comment = "Is hardcode mode enabled?";
		hardcoreMode = prop.getBoolean(false);

		prop = cat.get("lightweightMode");
		prop.setDefaultValue(false);
		prop.comment = "Is lightweight mode enabled?";
		lightweightMode = prop.getBoolean(false);

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

		prop = cat.get("allowAmuletStatusGui");
		prop.setDefaultValue(true);
		prop.comment = "Will the Amulet Status Gui be shown?";
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

		prop = cat.get("allowDigitalSeaGeneration");
		prop.setDefaultValue(true);
		prop.comment = "Can the Synapse generate with Digital Sea on the upper and lower sections?";
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

		prop = cat.get("allowDoomGui");
		prop.setDefaultValue(true);
		prop.comment = "Should the Doom Gui be shown?";
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
		doomAbilityCost[m = 0] = prop.getInt(0);

		prop = cat.get("blindingLightSolarBombCost");
		prop.setDefaultValue(15);
		doomAbilityCost[++m] = prop.getInt(15);

		prop = cat.get("blindingLightBurnCost");
		prop.setDefaultValue(10);
		doomAbilityCost[++m] = prop.getInt(10);

		prop = cat.get("blindingLightProjectileDeflectCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = prop.getInt(5);

		prop = cat.get("butcherCritKnockbackCost");
		prop.setDefaultValue(1);
		doomAbilityCost[++m] = prop.getInt(1);

		prop = cat.get("butcherKnockbackResistanceCost");
		prop.setDefaultValue(0);
		doomAbilityCost[++m] = prop.getInt(0);

		prop = cat.get("butcherWeaknessDebuffCost");
		prop.setDefaultValue(0);
		doomAbilityCost[++m] = prop.getInt(0);

		prop = cat.get("celestialAegisDamageReductionCost");
		prop.setDefaultValue(0);
		doomAbilityCost[++m] = prop.getInt(0);

		prop = cat.get("celestialLongbowTeleportCost");
		prop.setDefaultValue(0);
		doomAbilityCost[++m] = prop.getInt(0);

		prop = cat.get("dragonFangBurnCost");
		prop.setDefaultValue(1);
		doomAbilityCost[++m] = prop.getInt(1);

		prop = cat.get("dragonFangLargeFireballCost");
		prop.setDefaultValue(10);
		doomAbilityCost[++m] = prop.getInt(10);

		prop = cat.get("dragonFangExtinguishCost");
		prop.setDefaultValue(1);
		doomAbilityCost[++m] = prop.getInt(1);

		prop = cat.get("frozenLightningSlownessDebuffCost");
		prop.setDefaultValue(3);
		doomAbilityCost[++m] = prop.getInt(3);

		prop = cat.get("frozenLightningLightningStrikeCost");
		prop.setDefaultValue(20);
		doomAbilityCost[++m] = prop.getInt(20);

		prop = cat.get("frozenLightningIcicleCost");
		prop.setDefaultValue(3);
		doomAbilityCost[++m] = prop.getInt(3);

		prop = cat.get("gravitySpikeLaunchCost");
		prop.setDefaultValue(7);
		doomAbilityCost[++m] = prop.getInt(7);

		prop = cat.get("guiltyThornPoisonStunDebuffCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = prop.getInt(5);

		prop = cat.get("harmonyBellHarmonyDebuffCost");
		prop.setDefaultValue(3);
		doomAbilityCost[++m] = prop.getInt(3);

		prop = cat.get("harmonyBellHealingCost");
		prop.setDefaultValue(1);
		doomAbilityCost[++m] = prop.getInt(1);

		prop = cat.get("mourningStarSelfDestructCost");
		prop.setDefaultValue(25);
		doomAbilityCost[++m] = prop.getInt(25);

		prop = cat.get("mourningStarLookExplosionCost");
		prop.setDefaultValue(30);
		doomAbilityCost[++m] = prop.getInt(30);

		prop = cat.get("paranoiaFearSubmissionDebuffCost");
		prop.setDefaultValue(10);
		doomAbilityCost[++m] = prop.getInt(10);

		prop = cat.get("paranoiaDarkEnergySprayCost");
		prop.setDefaultValue(15);
		doomAbilityCost[++m] = prop.getInt(15);

		prop = cat.get("paranoiaSingleDarkEnergyCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = prop.getInt(5);

		prop = cat.get("pitchBlackThrowCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = prop.getInt(5);

		prop = cat.get("reaperScytheSmallPumpkinbombCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = prop.getInt(5);

		prop = cat.get("reaperScytheLargePumpkinbombCost");
		prop.setDefaultValue(15);
		doomAbilityCost[++m] = prop.getInt(15);

		prop = cat.get("splinterRandomDirectionHitCost");
		prop.setDefaultValue(3);
		doomAbilityCost[++m] = prop.getInt(3);

		prop = cat.get("splinterGroupRandomDirectionHitCost");
		prop.setDefaultValue(10);
		doomAbilityCost[++m] = prop.getInt(10);

		prop = cat.get("thardusSlownessDebuffCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = prop.getInt(5);

		prop = cat.get("thardusIcicleCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = prop.getInt(5);

		prop = cat.get("titanLightningHitCost");
		prop.setDefaultValue(10);
		doomAbilityCost[++m] = prop.getInt(10);

		prop = cat.get("titanLightningStrikesCost");
		prop.setDefaultValue(20);
		doomAbilityCost[++m] = prop.getInt(20);

		prop = cat.get("titanLightningAbsorbCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = prop.getInt(5);

		prop = cat.get("witheringAxeWitherDebuffCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = prop.getInt(5);

		prop = cat.get("witheringAxeWitherSkullCost");
		prop.setDefaultValue(5);
		doomAbilityCost[++m] = prop.getInt(5);

		prop = cat.get("witheringAxeBlueWitherSkullCost");
		prop.setDefaultValue(15);
		doomAbilityCost[++m] = prop.getInt(15);

		prop = cat.get("dragonFangFlamethrowerCost");
		prop.setDefaultValue(3);
		doomAbilityCost[++m] = prop.getInt(3);

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
		
		prop = cat.get("lavaPirahAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);
		
		prop = cat.get("stinAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);
		
		prop = cat.get("stinBabyAllow");
		prop.setDefaultValue(true);
		mobAllow[++m] = prop.getBoolean(true);
		
		prop = cat.get("wispAllow");
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
		
		prop = cat.get("plagueSpawnChance");
		prop.setDefaultValue(50);
		plagueSC = prop.getInt(50);
		
		prop = cat.get("gragulSpawnChance");
		prop.setDefaultValue(25);
		gragulSC = prop.getInt(25);
		
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
		toxSC = prop.getInt(50); //TODO finish spawn chances for mobs in new config
		
		prop = cat.get("poxSpawnChance");
		prop.setDefaultValue(50);
		poxSC = prop.getInt(50);

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
		if (mobsOnly)
		{
			TragicMC.logInfo("mobsOnly mode is enabled, overriding other config options.");
			hardcoreMode = false;
			lightweightMode = false;
			setupMobsOnly();
		}

		if (hardcoreMode)
		{
			TragicMC.logInfo("hardcoreMode is enabled, overriding other config options.");
			lightweightMode = false;
			setupHardcoreMode();
		}

		if (lightweightMode)
		{
			TragicMC.logInfo("lightweightMode is enabled, overriding other config options.");
			setupLightweightMode();
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

		initializeRemainingVariables();
	}

	private static void initializeRemainingVariables()
	{
		byte m;
		allowAmuletLeveling = amuletConfig[m = 0];
		allowAmuletCrafting = amuletConfig[++m];
		requireAmuletSlotUnlock = amuletConfig[++m];
		allowAmuletBossKillCharge = amuletConfig[++m];
		showAmuletStatusGui = amuletConfig[++m];
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
		allowDigitalSeaGen = dimensionConfig[++m];

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
		showDoomGui = doomConfig[++m];

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
		
		//TODO put mob allowances from their respective arrays
	}

	private static void setupMobsOnly()
	{

	}

	private static void setupHardcoreMode()
	{

	}

	private static void setupLightweightMode()
	{

	}

	public static int clampPositive(int value) {
		return value > 0 ? value : 0;
	}

	public static int clamp(final int value, final int min, final int max) {
		return value >= min && value <= max ? value : (value < min ? min : max);
	}

	public static int clampExc(final int value, final int min, final int max) {
		return value > min && value < max ? value : (value <= min ? min + 1 : max - 1);
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

	private static int findOpenID(final Object[] array, int start)
	{
		final int l = array.length;

		if (start < l && array[start] == null) return start;
		else if (start >= l || start < 0) start = 0;

		boolean once = false;

		for (int i = 0; i < l; i++)
		{
			if (!once && start + i >= l) //only check through the entire array once
			{
				start = i = 0;
				once = true;
			}
			else if (once && start + i >= l) break;

			if (array[start + i] == null) continue;
			return start + i;
		}

		TragicMC.logWarning("No valid values were found for starting id of " + start + ", default to 0, may result in a crash.");
		return 0;
	}

	public static int findBiomeID(int start) {
		return findOpenID(BiomeGenBase.getBiomeGenArray(), start);
	}

	public static int findEnchantID(int start) {
		return findOpenID(Enchantment.enchantmentsList, start);
	}

	public static int findPotionID(int start) {
		return findOpenID(Potion.potionTypes, start);
	}
}
