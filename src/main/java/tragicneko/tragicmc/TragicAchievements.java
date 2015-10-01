package tragicneko.tragicmc;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class TragicAchievements {

	//main achievement line
	public static Achievement thanks = new Achievement("tragicmc.achievement.thanks", "tragicmc.thanks", 0, 0, TragicItems.NekoNekoWand, (Achievement) null).initIndependentStat().setSpecial().registerStat();
	public static Achievement mercury = new Achievement("tragicmc.achievement.mercury", "tragicmc.mercury", 2, 1, TragicItems.RedMercury, thanks).registerStat();
	public static Achievement obsidianOrb = new Achievement("tragicmc.achievement.obsidianOrb", "tragicmc.obsidianOrb", 4, 1, TragicItems.CryingObsidianOrb, mercury).registerStat();
	public static Achievement dimensionalKey = new Achievement("tragicmc.achievement.dimensionalKey", "tragicmc.dimensionalKey", 4, -1, TragicItems.DimensionalKey, obsidianOrb).setSpecial().registerStat();
	public static Achievement collision = new Achievement("tragicmc.achievement.collision", "tragicmc.collision", 5, -2, TragicBlocks.StringLight, dimensionalKey).registerStat();
	public static Achievement aeris = new Achievement("tragicmc.achievement.aeris", "tragicmc.aeris", 7, -2, TragicBlocks.Aeris, collision).registerStat();
	public static Achievement synapseLink = new Achievement("tragicmc.achievement.synapseLink", "tragicmc.synapseLink", 7, 0, TragicItems.SynapseLink, aeris).setSpecial().registerStat();
	public static Achievement synapse = new Achievement("tragicmc.achievement.synapse", "tragicmc.synapse", 5, 0, TragicBlocks.SynapseCore, synapseLink).registerStat();
	public static Achievement overlord = new Achievement("tragicmc.achievement.overlord", "tragicmc.overlord", 3, 0, TragicItems.NanoBots, synapse).registerStat();
	public static Achievement overlord2 = new Achievement("tragicmc.achievement.overlord2", "tragicmc.overlord2", 1, 0, TragicItems.SynapseCrystal, overlord).registerStat();
	public static Achievement overlord3 = new Achievement("tragicmc.achievement.overlord3", "tragicmc.overlord3", -1, 0, TragicItems.CorruptedEye, overlord2).registerStat();
	public static Achievement overlord4 = new Achievement("tragicmc.achievement.overlord4", "tragicmc.overlord4", -3, 0, TragicItems.Sentinel, overlord3).registerStat().setSpecial();
	
	//first expansion achievements
	public static Achievement wildsRelic = new Achievement("tragicmc.achievement.wildsRelic", "tragicmc.wildsRelic", 0, 0, Items.apple, (Achievement) null);
	public static Achievement wilds = new Achievement("tragicmc.achievement.wilds", "tragicmc.wilds", 0, 0, Items.apple, (Achievement) null);
	public static Achievement spiritCatcher = new Achievement("tragicmc.achievement.spiritCatcher", "tragicmc.spiritCatcher", 0, 0, Items.apple, (Achievement) null);
	public static Achievement nerveCenter = new Achievement("tragicmc.achievement.nerveCenter", "tragicmc.nerveCenter", 0, 0, Items.apple, (Achievement) null);
	public static Achievement admin = new Achievement("tragicmc.achievement.admin", "tragicmc.admin", 0, 0, Items.apple, (Achievement) null);
	public static Achievement admin2 = new Achievement("tragicmc.achievement.admin2", "tragicmc.admin2", 0, 0, Items.apple, (Achievement) null);
	public static Achievement admin3 = new Achievement("tragicmc.achievement.admin3", "tragicmc.admin3", 0, 0, Items.apple, (Achievement) null);
	public static Achievement admin4 = new Achievement("tragicmc.achievement.admin4", "tragicmc.admin4", 0, 0, Items.apple, (Achievement) null);
	
	//amulet achievements
	public static Achievement tungsten = new Achievement("tragicmc.achievement.tungsten", "tragicmc.tungsten", 0, 0, TragicItems.Tungsten, mercury).registerStat();
	public static Achievement amulet = new Achievement("tragicmc.achievement.amulet", "tragicmc.amulet", 0, 0, TragicItems.BlacksmithAmulet, tungsten).registerStat();
	public static Achievement amuletEquip = new Achievement("tragicmc.achievement.amuletEquip", "tragicmc.amuletEquip", 0, 0, TragicItems.CreeperAmulet, amulet).registerStat();
	public static Achievement amuletRelease = new Achievement("tragicmc.achievement.amuletRelease", "tragicmc.amuletRelease", 0, 0, TragicItems.AmuletRelease, amulet).registerStat();
	public static Achievement amuletMax = new Achievement("tragicmc.achievement.amuletMax", "tragicmc.amuletMax", 0, 0, TragicItems.ApisAmulet, amulet).registerStat().setSpecial();
	public static Achievement amuletSpecial = new Achievement("tragicmc.achievement.amuletSpecial", "tragicmc.amuletSpecial", 0, 0, TragicItems.KitsuneAmulet, amulet).registerStat();
	
	//doom and doomsday achievements
	public static Achievement doom = new Achievement("tragicmc.achievement.doom", "tragicmc.doom", 2, -3, TragicItems.GuiltyThorn, thanks).registerStat();
	public static Achievement doomsday = new Achievement("tragicmc.achievement.doomsday", "tragicmc.doomsday", 4, -5, TragicItems.Titan, doom).registerStat();
	public static Achievement doomsdayCombo = new Achievement("tragicmc.achievement.doomsdayCombo", "tragicmc.doomsdayCombo", 0, 0, TragicItems.CelestialAegis, doomsday).registerStat().setSpecial();
	public static Achievement doomCooldown = new Achievement("tragicmc.achievement.doomCooldown", "tragicmc.doomCooldown", 0, 0, TragicItems.CooldownDefuse, doom).registerStat();
	public static Achievement doomConsume = new Achievement("tragicmc.achievement.doomConsume", "tragicmc.doomConsume", 0, 0, TragicItems.DoomConsume, doom).registerStat();
	public static Achievement doomCritical = new Achievement("tragicmc.achievement.doomCritical", "tragicmc.doomCritical", 0, 0, TragicItems.BeastlyClaws, doom).registerStat();
	
	//weapon/killing achievements
	public static Achievement weapon = new Achievement("tragicmc.achievement.weapon", "tragicmc.weapon", -3, -1, TragicItems.MercuryDagger, mercury).registerStat();
	public static Achievement kill = new Achievement("tragicmc.achievement.kill", "tragicmc.kill", -1, -2, TragicItems.Thorns, weapon).registerStat();
	public static Achievement killBoss = new Achievement("tragicmc.achievement.killBoss", "tragicmc.killBoss", -3, -3, TragicItems.Thardus, kill).registerStat();
	public static Achievement killAlpha = new Achievement("tragimc.achievement.killAlpha", "tragicmc.killAlpha", -4, -5, TragicItems.Sentinel, killBoss).setSpecial().registerStat();
	
	//armor and basic enchantments achievements
	public static Achievement enchant = new Achievement("tragicmc.achievement.enchant", "tragicmc.enchant", 0, -4, TragicItems.Splinter, kill).registerStat();
	public static Achievement reach = new Achievement("tragicmc.achivement.reach", "tragicmc.reach", -5, -2, TragicItems.Butcher, kill).registerStat();
	public static Achievement enchantArmor = new Achievement("tragicmc.achievement.enchantArmor", "tragicmc.enchantArmor", -1, -6, TragicItems.DarkHelm, kill).registerStat();
	public static Achievement fullSuit = new Achievement("tragicmc.achievement.fullSuit", "tragicmc.fullSuit", 0, 0, TragicItems.MercuryPlate, kill).registerStat();
	public static Achievement haxEngage = new Achievement("tragicmc.achievement.haxEngage", "tragicmc.haxEngage", 0, 0, TragicItems.OverlordHelm, fullSuit).registerStat().setSpecial();
	
	//jack and more enchantment achievements
	public static Achievement jack = new Achievement("tragicmc.achievement.jack", "tragicmc.jack", 0, 0, TragicItems.Jack, mercury).registerStat();
	public static Achievement luminescence = new Achievement("tragicmc.achievement.luminescence", "tragicmc.luminescence", 0, 0, TragicItems.CelestialJack, jack).registerStat();
	public static Achievement combustion = new Achievement("tragicmc.achievement.combustion", "tragicmc.combustion", 0, 0, TragicItems.TungstenJack, jack).registerStat();
	public static Achievement veteran = new Achievement("tragicmc.achievement.veteran", "tragicmc.veteran", 0, 0, Items.diamond_pickaxe, jack).registerStat();
	
	//talisman achievements
	public static Achievement talisman = new Achievement("tragicmc.achievement.talisman", "tragicmc.talisman", 0, 0, TragicItems.Talisman, thanks).registerStat();
	public static Achievement talismanSpecial = new Achievement("tragicmc.achievement.talismanSpecial", "tragicmc.talismanSpecial", 0, 0, TragicItems.MoonlightTalisman, talisman).registerStat();
	public static Achievement talismanFull = new Achievement("tragicmc.achievement.talismanFull", "tragicmc.talismanFull", 0, 0, TragicItems.SynthesisTalisman, talismanSpecial).registerStat().setSpecial();
	
	//random achievements
	public static Achievement goldenSushi = new Achievement("tragicmc.achievement.goldenSushi", "tragicmc.goldenSushi", 0, 0, TragicItems.GoldenSushi, null).registerStat();
	public static Achievement flight = new Achievement("tragicmc.achievement.flight", "tragicmc.flight", 0, 0, TragicItems.WingsOfLiberation, null).registerStat();
	public static Achievement disorientation = new Achievement("tragicmc.achievement.disorientation", "tragicmc.disorientation", 0, 0, TragicItems.Ash, null).registerStat();
	public static Achievement mobStatue = new Achievement("tragicmc.achievement.mobStatue", "tragicmc.mobStatue", 0, 0, TragicItems.MobStatue, null).registerStat();
	public static Achievement challengeScroll = new Achievement("tragicmc.achievement.challengeScroll", "tragicmc.challengeScroll", 0, 0, TragicItems.ChallengeScroll, null).registerStat();
	public static Achievement doomsdayScroll = new Achievement("tragicmc.achievement.doomsdayScroll", "tragicmc.doomsdayScroll", 0, 0, TragicItems.DoomsdayScroll, null).registerStat();
	public static Achievement mineXP = new Achievement("tragicmc.achievement.mineXP", "tragicmc.mineXP", 0, 0, Items.experience_bottle, null).registerStat();
	public static Achievement loot = new Achievement("tragicmc.achievement.loot", "tragicmc.loot", 0, 0, TragicItems.TungstenBoots, null).registerStat();
	public static Achievement loot2 = new Achievement("tragicmc.achievement.loot2", "tragicmc.loot2", 0, 0, TragicItems.LightHelm, loot).registerStat();
	public static Achievement systemCrash = new Achievement("tragicmc.achievement.systemCrash", "tragicmc.systemCrash", 0, 0, TragicItems.NanoBots, null).registerStat();
	public static Achievement divinity = new Achievement("tragicmc.achievement.divinity", "tragicmc.divinity", 0, 0, TragicItems.ArchangelFeather, null).registerStat();
	
	//mob achievements
	public static Achievement tragicNeko = new Achievement("tragicmc.achievement.tragicNeko", "tragicmc.tragicNeko", 0, 0, Blocks.tnt, null).registerStat().setSpecial();
	public static Achievement pumpkinhead = new Achievement("tragicmc.achievement.pumpkinhead", "tragicmc.pumpkinhead", 0, 0, Blocks.lit_pumpkin, null).registerStat();
	public static Achievement norVox = new Achievement("tragicmc.achievement.norVox", "tragicmc.norVox", 0, 0, TragicBlocks.DarkStone, null).registerStat();
	public static Achievement psygote = new Achievement("tragicmc.achievement.psygote", "tragicmc.psygote", 0, 0, TragicItems.DarkParticles, null).registerStat().setSpecial();
	public static Achievement sirv = new Achievement("tragicmc.achievement.sirv", "tragicmc.sirv", 0, 0, Items.clay_ball, null).registerStat();
	public static Achievement ragr = new Achievement("tragicmc.achievement.ragr", "tragicmc.ragr", 0, 0, Blocks.fire, null).registerStat();
	public static Achievement kurayami = new Achievement("tragicmc.achievement.kurayami", "tragicmc.kurayami", 0, 0, TragicItems.KitsuneAmulet, null).registerStat().setSpecial();
	public static Achievement cantTouchThis = new Achievement("tragicmc.achievement.cantTouchThis", "tragicmc.cantTouchThis", 0, 0, TragicItems.AwakeningStone, null).registerStat().setSpecial();
	public static Achievement rewind = new Achievement("tragicmc.achievement.rewind", "tragicmc.rewind", 0, 0, TragicItems.LunarPowder, null).registerStat();
	public static Achievement aegar = new Achievement("tragicmc.achievement.aegar", "tragicmc.aegar", 0, 0, TragicItems.SynapseCrystal, null).registerStat();
	public static Achievement claymation2in1 = new Achievement("tragicmc.achievement.claymation2in1", "tragicmc.claymation2in1", 0, 0, TragicItems.ClaymationAmulet, null).registerStat().setSpecial();
	public static Achievement skultarImmune = new Achievement("tragicmc.achievement.skultarImmune", "tragicmc.skultarImmune", 0, 0, TragicItems.SkullHelmet, null).registerStat();
	public static Achievement minotaurLightning = new Achievement("tragicmc.achievement.minotaurLightning", "tragicmc.minotaurLightning", 0, 0, TragicItems.Horn, null).registerStat().setSpecial();
	public static Achievement seeker = new Achievement("tragicmc.achievement.seeker", "tragicmc.seeker", 0, 0, TragicItems.BlindingLight, null).registerStat();
	public static Achievement harvester = new Achievement("tragicmc.achievement.harvester", "tragicmc.harvester", 0, 0, Blocks.iron_block, null).registerStat();
	public static Achievement lockdown = new Achievement("tragicmc.achievement.lockbot", "tragicmc.lockbot", 0, 0, Blocks.anvil, null).registerStat();
	public static Achievement fusea = new Achievement("tragicmc.achievement.fusea", "tragicmc.fusea", 0, 0, TragicItems.UnstableIsotope, null).registerStat();
	public static Achievement ire = new Achievement("tragicmc.achievement.ire", "tragicmc.ire", 0, 0, TragicItems.IreNetParticleCannon, null).registerStat();
	
	//boss achievements
	public static Achievement apis = new Achievement("tragicmc.achievement.apis", "tragicmc.apis", 0, 0, TragicItems.PureLight, null).registerStat();
	public static Achievement skultar = new Achievement("tragicmc.achievement.skultar", "tragicmc.skultar", 0, 0, TragicItems.DeathlyHallow, null).registerStat();
	public static Achievement kitsunakuma = new Achievement("tragicmc.achievement.kitsunakuma", "tragicmc.kitsunakuma", 0, 0, TragicItems.KitsuneTail, null).registerStat();
	public static Achievement polaris = new Achievement("tragicmc.achievement.polaris", "tragicmc.polaris", 0, 0, TragicItems.StarPieces, null).registerStat();
	public static Achievement empariah = new Achievement("tragicmc.achievement.empariah", "tragicmc.empariah", 0, 0, TragicItems.EmpariahClaw, null).registerStat();
	public static Achievement timeController = new Achievement("tragicmc.achievement.timeController", "tragicmc.timeController", 0, 0, TragicItems.TimeEssence, null).registerStat();
	public static Achievement enyvil = new Achievement("tragicmc.achievement.enyvil", "tragicmc.enyvil", 0, 0, TragicItems.PureDarkness, null).registerStat();
	public static Achievement claymation = new Achievement("tragicmc.achievement.claymation", "tragicmc.claymation", 0, 0, TragicItems.LivingClay, null).registerStat();
	
	public static void load() {		
		AchievementPage page = new AchievementPage(TragicMC.MODID, thanks, kill, mercury, obsidianOrb, dimensionalKey, collision, aeris, synapseLink, synapse, overlord, overlord2, overlord3, overlord4,
				weapon, kill, killBoss, killAlpha, enchant, reach, enchantArmor, fullSuit, haxEngage, tungsten, amulet, amuletEquip, amuletRelease, amuletMax, amuletSpecial, jack, luminescence, combustion, veteran,
				doom, doomsday, doomsdayCombo, doomCooldown, doomConsume, doomCritical, talisman, talismanSpecial, talismanFull, goldenSushi, flight, disorientation, mobStatue, challengeScroll, doomsdayScroll,
				mineXP, loot, loot2, systemCrash, divinity, tragicNeko, pumpkinhead, norVox, psygote, sirv, ragr, kurayami, cantTouchThis, rewind, aegar, claymation2in1, skultarImmune, minotaurLightning,
				seeker, harvester, lockdown, fusea, ire, apis, skultar, kitsunakuma, polaris, empariah, timeController, enyvil, claymation);
		AchievementPage.registerAchievementPage(page);
	}
}
