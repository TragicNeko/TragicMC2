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
	public static Achievement kill = new Achievement("tragicmc.achievement.kill", "tragicmc.kill", -1, -2, TragicItems.Ash, weapon).registerStat();
	public static Achievement killBoss = new Achievement("tragicmc.achievement.killBoss", "tragicmc.killBoss", -3, -3, TragicItems.TimeEssence, kill).registerStat();
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
	
	
	
	public static void load() {		
		AchievementPage page = new AchievementPage(TragicMC.MODID, thanks, kill, mercury, obsidianOrb, dimensionalKey, collision, aeris, synapseLink, synapse, overlord, overlord2, overlord3, overlord4,
				weapon, kill, killBoss, killAlpha, enchant, reach, enchantArmor, tungsten, amulet, amuletEquip, amuletRelease, amuletMax, amuletSpecial, jack, luminescence, combustion, veteran,
				doom, doomsday, doomsdayCombo, doomCooldown, doomConsume, doomCritical);
		AchievementPage.registerAchievementPage(page);
	}
}
