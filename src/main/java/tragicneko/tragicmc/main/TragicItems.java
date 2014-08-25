package tragicneko.tragicmc.main;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.ItemGeneric;
import tragicneko.tragicmc.items.ItemProjectile;
import tragicneko.tragicmc.items.ItemPumpkinbomb;
import tragicneko.tragicmc.items.ItemRock;
import tragicneko.tragicmc.items.armor.ArmorDark;
import tragicneko.tragicmc.items.armor.ArmorHunter;
import tragicneko.tragicmc.items.armor.ArmorLight;
import tragicneko.tragicmc.items.armor.ArmorMercury;
import tragicneko.tragicmc.items.armor.ArmorSkull;
import tragicneko.tragicmc.items.armor.ArmorTungsten;
import tragicneko.tragicmc.items.food.ItemBanana;
import tragicneko.tragicmc.items.food.ItemBananaSplit;
import tragicneko.tragicmc.items.food.ItemBoneMarrow;
import tragicneko.tragicmc.items.food.ItemEnchantedSushi;
import tragicneko.tragicmc.items.food.ItemExoticFruit;
import tragicneko.tragicmc.items.food.ItemGooeyFruit;
import tragicneko.tragicmc.items.food.ItemIceCream;
import tragicneko.tragicmc.items.food.ItemNastyFruit;
import tragicneko.tragicmc.items.food.ItemRice;
import tragicneko.tragicmc.items.food.ItemSushi;
import tragicneko.tragicmc.items.special.ItemAmulet;
import tragicneko.tragicmc.items.special.ItemAmuletRelease;
import tragicneko.tragicmc.items.special.ItemBleedingObsidianOrb;
import tragicneko.tragicmc.items.special.ItemCooldownDefuse;
import tragicneko.tragicmc.items.special.ItemCryingObsidianOrb;
import tragicneko.tragicmc.items.special.ItemDimensionalKey;
import tragicneko.tragicmc.items.special.ItemDoomUpgrade;
import tragicneko.tragicmc.items.special.ItemDyingObsidianOrb;
import tragicneko.tragicmc.items.special.ItemGenerator;
import tragicneko.tragicmc.items.special.ItemLifeWater;
import tragicneko.tragicmc.items.special.ItemMobEgg;
import tragicneko.tragicmc.items.special.ItemNekoWand;
import tragicneko.tragicmc.items.special.ItemStatue;
import tragicneko.tragicmc.items.weapons.ItemEverlastingLight;
import tragicneko.tragicmc.items.weapons.ItemJack;
import tragicneko.tragicmc.items.weapons.ItemScythe;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.items.weapons.WeaponBeastlyClaws;
import tragicneko.tragicmc.items.weapons.WeaponBlindingLight;
import tragicneko.tragicmc.items.weapons.WeaponBowOfJustice;
import tragicneko.tragicmc.items.weapons.WeaponEnigmaShield;
import tragicneko.tragicmc.items.weapons.WeaponGravitySpike;
import tragicneko.tragicmc.items.weapons.WeaponGuiltyThorn;
import tragicneko.tragicmc.items.weapons.WeaponHarmonyBell;
import tragicneko.tragicmc.items.weapons.WeaponHuntersBow;
import tragicneko.tragicmc.items.weapons.WeaponMourningStar;
import tragicneko.tragicmc.items.weapons.WeaponNekoLauncher;
import tragicneko.tragicmc.items.weapons.WeaponPitchBlack;
import tragicneko.tragicmc.items.weapons.WeaponReaperScythe;
import tragicneko.tragicmc.items.weapons.WeaponSwordOfJustice;
import tragicneko.tragicmc.items.weapons.WeaponWitheringAxe;
import cpw.mods.fml.common.registry.GameRegistry;

public class TragicItems {

	//Tool Materials
	private static final ToolMaterial toolScythe = EnumHelper.addToolMaterial("SCYTHE", 1, 110, 12.0F, 0.0F, 6);
	private static final ToolMaterial toolJack = EnumHelper.addToolMaterial("JACK", 3, 1650, 12.0F, 0.0F, 5);
	private static final ToolMaterial toolGravity = EnumHelper.addToolMaterial("GRAVITY", 1, 220, 0.5F, 0.5F, 10);
	private static final ToolMaterial toolHunter = EnumHelper.addToolMaterial("HUNTER", 1, 160, 1F, 1F, 4);
	private static final ToolMaterial toolMercury = EnumHelper.addToolMaterial("MERCURY", 2, 170, 1.5F, 1.5F, 12);
	private static final ToolMaterial toolTungsten = EnumHelper.addToolMaterial("TUNGSTEN", 3, 770, 2.5F, 2.5F, 8);
	private static final ToolMaterial toolClaws = EnumHelper.addToolMaterial("CLAWS", 2, 350, 4.0F, 4.0F, 2);
	private static final ToolMaterial toolThorn = EnumHelper.addToolMaterial("THORN", 1, 330, 6.0F, 6.0F, 4);
	private static final ToolMaterial toolWithering = EnumHelper.addToolMaterial("WITHERING", 3, 550, 12.0F, 6.0F, 16);
	private static final ToolMaterial toolDarkness = EnumHelper.addToolMaterial("DARKNESS", 3, 350, 8.0F, 8.0F, 26);
	private static final ToolMaterial toolLight = EnumHelper.addToolMaterial("LIGHT", 3, 333, 8.0F, 8.0F, 14);
	private static final ToolMaterial toolMourning = EnumHelper.addToolMaterial("MOURNING", 3, 440, 9.0F, 9.0F, 14);
	private static final ToolMaterial toolReaper = EnumHelper.addToolMaterial("REAPER", 3, 800, 30.0F, 12.0F, 10);
	private static final ToolMaterial toolEnigma = EnumHelper.addToolMaterial("ENIGMA", 3, 444, 4.0F, 4.0F, 4);
	private static final ToolMaterial toolCelestial = EnumHelper.addToolMaterial("CELESTIAL", 3, 860, 12.0F, 12.0F, 24);
	private static final ToolMaterial toolDragon = EnumHelper.addToolMaterial("DRAGON", 3, 1000, 14.0F, 14.0F, 18);
	private static final ToolMaterial toolEpic = EnumHelper.addToolMaterial("EPIC", 3, 750, 16.0F, 16.0F, 16);
	private static final ToolMaterial toolTragic = EnumHelper.addToolMaterial("TRAGIC", 3, 2100, 21.0F, 21.0F, 28);
	private static final ToolMaterial toolJustice = EnumHelper.addToolMaterial("JUSTICE", 100, 1000, Float.MAX_VALUE, Float.MAX_VALUE, 100);

	//Armor Materials
	private static final ArmorMaterial armorSkull = EnumHelper.addArmorMaterial("SKULL", 32, new int[] {1, 2, 1, 1}, 16); //5
	private static final ArmorMaterial armorHunter = EnumHelper.addArmorMaterial("HUNTER", 29, new int[] {2, 3, 2, 2}, 8); //9
	private static final ArmorMaterial armorMercury = EnumHelper.addArmorMaterial("MERCURY", 24, new int[] {2, 5, 4, 2}, 22); //13
	private static final ArmorMaterial armorTungsten = EnumHelper.addArmorMaterial("TUNGSTEN", 22, new int[] {3, 6, 4, 2}, 10); //15
	private static final ArmorMaterial armorDark = EnumHelper.addArmorMaterial("DARK", 18, new int[] {3, 7, 5, 3}, 6); //18
	private static final ArmorMaterial armorLight = EnumHelper.addArmorMaterial("LIGHT", 26, new int[] {5, 7, 6, 4}, 18); //22
	private static final ArmorMaterial armorCelestial = EnumHelper.addArmorMaterial("CELESTIAL", 29, new int[] {5, 7, 7, 4}, 24); //23
	private static final ArmorMaterial armorDraconic = EnumHelper.addArmorMaterial("DRACONIC", 25, new int[] {5, 7, 7, 4}, 16); //23
	private static final ArmorMaterial armorTragic = EnumHelper.addArmorMaterial("TRAGIC", 35, new int[] {5, 8, 7, 4}, 26); //24

	//Item variables
	public static Item RedMercury, Quicksilver, QuicksilverIngot;
	public static Item MercuryDagger, MercuryHelm, MercuryPlate, MercuryLegs, MercuryBoots;
	public static Item Tungsten, Jack, TungstenHelm, TungstenPlate, TungstenLegs, TungstenBoots;
	
	public static Item Scythe, SkullHelmet, SkullPlate, SkullLegs, SkullBoots;
	public static Item HuntersBow, HuntersCap, HuntersTunic, HuntersLegs, HuntersBoots;

	public static Item DarkIngot, DarkHelm, DarkPlate, DarkLegs, DarkBoots, PitchBlack;
	public static Item LightIngot, LightHelm, LightPlate, LightLegs, LightBoots, BlindingLight, EverlastingLight;

	//Can be found in random boss structures
	public static Item DraconicHelm;
	public static Item DraconicPlate;
	public static Item DraconicLegs;
	public static Item DraconicBoots;

	//Uncraftable weapon/armor set, found only in the Tragic Dimension
	public static Item TragicHelm, TragicPlate, TragicLegs, TragicBoots, TragicHellraiser, TragicSentinel;

	//Can be found in random boss structures, can also be crafted from drops from the Time Controller
	public static Item CelestialAegis, CelestialLongbow;
	public static Item GravitySpike, HarmonyBell, MourningStar, BeastlyClaws, GuiltyThorn, NekoLauncher, ReaperScythe, EnigmaShield, WitheringAxe;

	//The "Epic" Weapons, uncraftable super rare weapons in the Tragic Dimension
	public static Item TheSplinter, TheTitan, TheButcher, Thardus, Paranoia;
	//The Splinter will shared traits from the Ender sword and jack
	//The Titan will utilize electrical attacks
	//The Butcher will have aoe like armor piercing damage
	//The Thardus will have ice and wind style attacks
	//The Paranoia will have "dark" and weird abilities

	//Weather/Time items
	public static Item RainDance; //While in inventory keeps it raining, requires Lapis, Wisp Particles and Redstone
	public static Item SunnyDay; //While in inventory stops it from raining, requires Gold, Wisp Particles and Redstone
	public static Item Thunderstorm; //While in inventory makes it always thunderstorm, requires Emerald, Shifting Sands and Redstone
	public static Item TimeManipulator; //When right-clicking with it, speeds up time, shift-right clicking reverses time, requires Time Essence, Shifting Sands, Clock and Diamonds
	public static Item Moonlight; //Heals the player while it is night time
	public static Item Synthesis; //Heals the player while it is daytime

	public static Item Ruby, Sapphire, RubyCharm, SapphireCharm, DiamondCharm, EmeraldCharm, AwakeningStone, ObsidianOrb, CryingObsidianOrb, BleedingObsidianOrb, DyingObsidianOrb;

	//Boss drops
	public static Item LightParticles; //Apis
	public static Item YetiClaw; //Yeti
	public static Item ReaperSkull; //Death Reaper
	public static Item TimeEssence; //Time Controller
	public static Item PureDarkness; //Nyx and Erebus
	public static Item KitsuneTail; //Kitsune
	public static Item LivingClay; //Claymation
	public static Item StarPieces; //Polaris
	public static Item PhoenixFeather; //Phoenix
	public static Item DragonScale; //Lava Dragon

	//Doom items
	public static Item DoomConsume, CooldownDefuse, AmuletRelease;

	//Amulets
	public static Item KitsuneAmulet;
	public static Item YetiAmulet, YetiAmulet2, YetiAmulet3;
	public static Item PeaceAmulet, PeaceAmulet2, PeaceAmulet3;
	public static Item ClaymationAmulet, ClaymationAmulet2, ClaymationAmulet3;
	public static Item ChickenAmulet, ChickenAmulet2, ChickenAmulet3;
	public static Item MartyrAmulet;
	public static Item HornetAmulet;
	public static Item BlacksmithAmulet, BlacksmithAmulet2, BlacksmithAmulet3;
	public static Item ApisAmulet;
	public static Item CreeperAmulet, CreeperAmulet2, CreeperAmulet3;
	public static Item ZombieAmulet, ZombieAmulet2, ZombieAmulet3;
	public static Item SkeletonAmulet, SkeletonAmulet2, SkeletonAmulet3;
	public static Item SunkenAmulet;
	public static Item TimeAmulet;

	//Normal items
	public static Item Ectoplasm;
	public static Item Ash;
	public static Item LifeWater;
	public static Item ToughLeather;
	public static Item WovenSilk;
	public static Item CrushedIce;
	public static Item DarkParticles;
	public static Item Spore;
	public static Item Honey;
	public static Item Sap;
	public static Item Rock;
	public static Item Thorns;
	public static Item BoneMarrow;
	public static Item Horn;
	public static Item Glue;
	public static Item SuperGlue;
	public static Item LunarPowder;
	public static Item CelestialDiamond;

	public static Item IceOrb, GravityOrb, FireOrb, LightningOrb, AquaOrb;
	
	public static Item WispParticles;
	public static Item StinHorn;
	public static Item IcyFur;

	//Projectile items
	public static Item Pumpkinbomb;
	public static Item NekoStickyBomb;
	public static Item NekoClusterBomb;
	public static Item NekoMiniBomb;
	public static Item NekoRocket;
	public static Item FlameBoomerang;
	public static Item SolarBomb;
	public static Item PoisonBarb;
	public static Item SpiritCast;

	//Food items
	public static Item IceCream;
	public static Item ExoticFruit;
	public static Item GooeyFruit;
	public static Item NastyFruit;
	public static Item Rice;
	public static Item Sushi;
	public static Item Banana;
	public static Item BananaSplit;
	public static Item GoldenSushi;

	public static Item DimensionalKey;

	public static Item BowOfJustice;
	public static Item SwordOfJustice;

	public static Item VoidPitGenerator;
	public static Item SphereGenerator;
	public static Item SphereEraser;
	public static Item LiquidRemover;
	public static Item TreeGenerator;
	public static Item SpikeGenerator;
	public static Item StarCrystalGenerator;
	public static Item LightningSummoner;
	public static Item ExplosionGenerator;
	
	public static Item NekoNekoWand; //allows you to set a mob to target another one, for creative mode fun only

	public static Item SpawnEgg;
	public static Item MobStatue;

	public static ChestGenHooks BossStructureHook; //Boss hook that has a lot of good and rare items
	public static ChestGenHooks NetherStructureHook; //Boss hook that has a lot of good and rare items, slightly improved chance of rare items over the normal boss hook
	public static ChestGenHooks LameChestHook; //Hook for having a lot of common, boring items
	public static ChestGenHooks AwesomeChestHook; //Hook for having a lot of high rarity items, only should be used for the very difficult boss chests

	public static void load()
	{
		//Ore Registrations
		RedMercury = (new ItemGeneric().setUnlocalizedName("tragicmc.redMercury").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:RedMercury" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(RedMercury, "redMercury");

		Quicksilver = (new ItemGeneric().setUnlocalizedName("tragicmc.quicksilver").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Quicksilver" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Quicksilver, "quicksilver");

		QuicksilverIngot = (new ItemGeneric().setUnlocalizedName("tragicmc.quicksilverIngot").setMaxStackSize(16).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:QuicksilverIngot" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(QuicksilverIngot, "quicksilverIngot");

		Tungsten = (new ItemGeneric().setUnlocalizedName("tragicmc.tungsten").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Tungsten" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Tungsten, "tungsten");

		Ruby = (new ItemGeneric().setUnlocalizedName("tragicmc.ruby").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Ruby" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Ruby, "ruby");

		Sapphire = (new ItemGeneric().setUnlocalizedName("tragicmc.sapphire").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Sapphire" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Sapphire, "sapphire");

		//Armor Registrations
		SkullHelmet = (new ArmorSkull(armorSkull, 0, Doomsday.Decay).setUnlocalizedName("tragicmc.skullHelmet"));
		GameRegistry.registerItem(SkullHelmet, "skullHelmet");

		SkullPlate = (new ArmorSkull(armorSkull, 1, Doomsday.Decay).setUnlocalizedName("tragicmc.skullPlate"));
		GameRegistry.registerItem(SkullPlate, "skullPlate");

		SkullLegs = (new ArmorSkull(armorSkull, 2, Doomsday.Decay).setUnlocalizedName("tragicmc.skullLegs"));
		GameRegistry.registerItem(SkullLegs, "skullLegs");

		SkullBoots = (new ArmorSkull(armorSkull, 3, Doomsday.Decay).setUnlocalizedName("tragicmc.skullBoots"));
		GameRegistry.registerItem(SkullBoots, "skullBoots");


		HuntersCap = (new ArmorHunter(armorHunter, 0, Doomsday.HuntersInstinct).setUnlocalizedName("tragicmc.huntersCap"));
		GameRegistry.registerItem(HuntersCap, "huntersCap");

		HuntersTunic = (new ArmorHunter(armorHunter, 1, Doomsday.HuntersInstinct).setUnlocalizedName("tragicmc.huntersTunic"));
		GameRegistry.registerItem(HuntersTunic, "huntersTunic");

		HuntersLegs = (new ArmorHunter(armorHunter, 2, Doomsday.HuntersInstinct).setUnlocalizedName("tragicmc.huntersLegs"));
		GameRegistry.registerItem(HuntersLegs, "huntersLegs");

		HuntersBoots = (new ArmorHunter(armorHunter, 3, Doomsday.HuntersInstinct).setUnlocalizedName("tragicmc.huntersBoots"));
		GameRegistry.registerItem(HuntersBoots, "huntersBoots");


		MercuryHelm = (new ArmorMercury(armorMercury, 0, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryHelm"));
		GameRegistry.registerItem(MercuryHelm, "mercuryHelm");

		MercuryPlate = (new ArmorMercury(armorMercury, 1, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryPlate"));
		GameRegistry.registerItem(MercuryPlate, "mercuryPlate");

		MercuryLegs = (new ArmorMercury(armorMercury, 2, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryLegs"));
		GameRegistry.registerItem(MercuryLegs, "mercuryLegs");

		MercuryBoots = (new ArmorMercury(armorMercury, 3, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryBoots"));
		GameRegistry.registerItem(MercuryBoots, "mercuryBoots");


		TungstenHelm = (new ArmorTungsten(armorTungsten, 0, Doomsday.Berserker).setUnlocalizedName("tragicmc.tungstenHelm"));
		GameRegistry.registerItem(TungstenHelm, "tungstenHelm");

		TungstenPlate = (new ArmorTungsten(armorTungsten, 1, Doomsday.Berserker).setUnlocalizedName("tragicmc.tungstenPlate"));
		GameRegistry.registerItem(TungstenPlate, "tungstenPlate");

		TungstenLegs = (new ArmorTungsten(armorTungsten, 2, Doomsday.Berserker).setUnlocalizedName("tragicmc.tungstenLegs"));
		GameRegistry.registerItem(TungstenLegs, "tungstenLegs");

		TungstenBoots = (new ArmorTungsten(armorTungsten, 3, Doomsday.Berserker).setUnlocalizedName("tragicmc.tungstenBoots"));
		GameRegistry.registerItem(TungstenBoots, "tungstenBoots");


		LightHelm = (new ArmorLight(armorLight, 0, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightHelm"));
		GameRegistry.registerItem(LightHelm, "lightHelm");

		LightPlate = (new ArmorLight(armorLight, 1, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightPlate"));
		GameRegistry.registerItem(LightPlate, "lightPlate");

		LightLegs = (new ArmorLight(armorLight, 2, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightLegs"));
		GameRegistry.registerItem(LightLegs, "lightLegs");

		LightBoots = (new ArmorLight(armorLight, 3, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightBoots"));
		GameRegistry.registerItem(LightBoots, "lightBoots");


		DarkHelm = (new ArmorDark(armorDark, 0, Doomsday.Fear).setUnlocalizedName("tragicmc.darkHelm"));
		GameRegistry.registerItem(DarkHelm, "darkHelm");

		DarkPlate = (new ArmorDark(armorDark, 1, Doomsday.Fear).setUnlocalizedName("tragicmc.darkPlate"));
		GameRegistry.registerItem(DarkPlate, "darkPlate");

		DarkLegs = (new ArmorDark(armorDark, 2, Doomsday.Fear).setUnlocalizedName("tragicmc.darkLegs"));
		GameRegistry.registerItem(DarkLegs, "darkLegs");

		DarkBoots = (new ArmorDark(armorDark, 3, Doomsday.Fear).setUnlocalizedName("tragicmc.darkBoots"));
		GameRegistry.registerItem(DarkBoots, "darkBoots");

		//Weapon Registrations
		MercuryDagger = (new TragicWeapon(toolMercury, Doomsday.PoisonBreak).setUnlocalizedName("tragicmc.mercuryDagger").setTextureName("tragicmc:MercuryDagger" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(MercuryDagger, "mercuryDagger");

		HuntersBow = (new WeaponHuntersBow().setUnlocalizedName("tragicmc.huntersBow").setMaxStackSize(1).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:HuntersBow" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(HuntersBow, "huntersBow");

		PitchBlack = (new WeaponPitchBlack(toolDarkness, Doomsday.NatureDrain).setUnlocalizedName("tragicmc.pitchBlack").setTextureName("tragicmc:PitchBlack" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(PitchBlack, "pitchBlack");

		BlindingLight = (new WeaponBlindingLight(toolLight, Doomsday.PiercingLight).setUnlocalizedName("tragicmc.blindingLight").setTextureName("tragicmc:BlindingLight" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(BlindingLight, "blindingLight");

		GravitySpike = (new WeaponGravitySpike(toolGravity, Doomsday.Pulse).setUnlocalizedName("tragicmc.gravitySpike").setTextureName("tragicmc:GravitySpike" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(GravitySpike, "gravitySpike");

		HarmonyBell = (new WeaponHarmonyBell(toolGravity, Doomsday.Harmonizer).setUnlocalizedName("tragicmc.harmonyBell").setTextureName("tragicmc:HarmonyBell" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(HarmonyBell, "harmonyBell");

		MourningStar = (new WeaponMourningStar(toolMourning, Doomsday.Ravage).setUnlocalizedName("tragicmc.mourningStar").setTextureName("tragicmc:MourningStar" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(MourningStar, "mourningStar");

		BeastlyClaws = (new WeaponBeastlyClaws(toolClaws, Doomsday.BeastlyImpulses).setUnlocalizedName("tragicmc.beastlyClaws").setTextureName("tragicmc:BeastlyClaws" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(BeastlyClaws, "beastlyClaws");

		GuiltyThorn = (new WeaponGuiltyThorn(toolThorn, Doomsday.Torment).setUnlocalizedName("tragicmc.guiltyThorn").setTextureName("tragicmc:GuiltyThorn" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(GuiltyThorn, "guiltyThorn");

		NekoLauncher = (new WeaponNekoLauncher(toolGravity, Doomsday.SuicidalTendencies).setUnlocalizedName("tragicmc.nekoLauncher").setTextureName("tragicmc:NekoLauncher" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(NekoLauncher, "nekoLauncher");

		ReaperScythe = (new WeaponReaperScythe(toolReaper, Doomsday.ReaperLaugh).setUnlocalizedName("tragicmc.reaperScythe").setTextureName("tragicmc:ReaperScythe" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(ReaperScythe, "reaperScythe");

		EnigmaShield = (new WeaponEnigmaShield(toolEnigma, Doomsday.RealityAlter).setUnlocalizedName("tragicmc.enigmaShield").setTextureName("tragicmc:EnigmaShield" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(EnigmaShield, "enigmaShield");

		WitheringAxe = (new WeaponWitheringAxe(toolWithering, Doomsday.SkullCrusher).setUnlocalizedName("tragicmc.witheringAxe").setTextureName("tragicmc:WitheringAxe" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(WitheringAxe, "witheringAxe");

		//Tool Registrations
		Scythe = (new ItemScythe(toolScythe).setUnlocalizedName("tragicmc.scythe").setTextureName("tragicmc:Scythe" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Scythe, "scythe");

		EverlastingLight = (new ItemEverlastingLight().setUnlocalizedName("tragicmc.everlastingLight").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:EverlastingLight" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(EverlastingLight, "everlastingLight");

		Jack = (new ItemJack(toolJack, Doomsday.MinerSkills).setUnlocalizedName("tragicmc.jack").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:TungstenJack_lowRes"));
		GameRegistry.registerItem(Jack, "jack");

		//Normal Item Registrations
		Ectoplasm = (new ItemGeneric().setUnlocalizedName("tragicmc.ectoplasm").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Ectoplasm" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Ectoplasm, "ectoplasm");

		Ash = (new ItemGeneric().setUnlocalizedName("tragicmc.ash").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Ash" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Ash, "ash");

		LifeWater = (new ItemLifeWater().setUnlocalizedName("tragicmc.lifeWater").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:LifeWater" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(LifeWater, "lifeWater");

		ToughLeather = (new ItemGeneric().setUnlocalizedName("tragicmc.toughLeather").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:ToughLeather" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(ToughLeather, "toughLeather");

		WovenSilk = (new ItemGeneric().setUnlocalizedName("tragicmc.wovenSilk").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:WovenSilk" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(WovenSilk, "wovenSilk");

		CrushedIce = (new ItemGeneric().setUnlocalizedName("tragicmc.crushedIce").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:CrushedIce" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(CrushedIce, "crushedIce");

		LightParticles = (new ItemGeneric().setUnlocalizedName("tragicmc.lightParticles").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:LightParticles" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(LightParticles, "lightParticles");

		DarkParticles = (new ItemGeneric().setUnlocalizedName("tragicmc.darkParticles").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:DarkParticles" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(DarkParticles, "darkParticles");

		IceOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.iceOrb").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:IceOrb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(IceOrb, "iceOrb");

		GravityOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.gravityOrb").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:GravityOrb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(GravityOrb, "gravityOrb");

		FireOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.fireOrb").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:FireOrb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(FireOrb, "fireOrb");

		LightningOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.lightningOrb").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:LightningOrb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(LightningOrb, "lightningOrb");

		AquaOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.aquaOrb").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:AquaOrb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(AquaOrb, "aquaOrb");

		Spore = (new ItemGeneric().setUnlocalizedName("tragicmc.spore").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Spore" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Spore, "spore");

		Honey = (new ItemGeneric().setUnlocalizedName("tragicmc.honey").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Honey" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Honey, "honey");

		Thorns = (new ItemGeneric().setUnlocalizedName("tragicmc.thorns").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Thorns" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Thorns, "thorns");

		Sap = (new ItemGeneric().setUnlocalizedName("tragicmc.sap").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Sap" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Sap, "sap");

		Horn = (new ItemGeneric().setUnlocalizedName("tragicmc.horn").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Horn" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Horn, "horn");

		BoneMarrow = (new ItemBoneMarrow(2, false).setUnlocalizedName("tragicmc.boneMarrow").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:BoneMarrow" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(BoneMarrow, "boneMarrow");

		Glue = (new ItemGeneric().setUnlocalizedName("tragicmc.glue").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Glue" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Glue, "glue");

		SuperGlue = (new ItemGeneric().setUnlocalizedName("tragicmc.superGlue").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:SuperGlue" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(SuperGlue, "superGlue");

		LightIngot = (new ItemGeneric().setUnlocalizedName("tragicmc.lightIngot").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:LightIngot" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(LightIngot, "lightIngot");

		DarkIngot = (new ItemGeneric().setUnlocalizedName("tragicmc.darkIngot").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:DarkIngot" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(DarkIngot, "darkIngot");

		KitsuneTail = (new ItemGeneric().setUnlocalizedName("tragicmc.kitsuneTail").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:KitsuneTail" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(KitsuneTail, "kitsuneTail");

		ReaperSkull = (new ItemGeneric().setUnlocalizedName("tragicmc.reaperSkull").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:ReaperSkull" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(ReaperSkull, "reaperSkull");

		YetiClaw = (new ItemGeneric().setUnlocalizedName("tragicmc.yetiClaw").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:YetiClaw" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(YetiClaw, "yetiClaw");

		StarPieces = (new ItemGeneric().setUnlocalizedName("tragicmc.starPieces").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:StarPieces" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(StarPieces, "starPieces");

		TimeEssence = (new ItemGeneric().setUnlocalizedName("tragicmc.timeEssence").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:TimeEssence" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(TimeEssence, "timeEssence");

		LunarPowder = (new ItemGeneric().setUnlocalizedName("tragicmc.lunarPowder").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:LunarPowder" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(LunarPowder, "lunarPowder");

		CelestialDiamond = (new ItemGeneric().setUnlocalizedName("tragicmc.celestialDiamond").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:CelestialDiamond" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(CelestialDiamond, "celestialDiamond");
		
		StinHorn = (new ItemGeneric().setUnlocalizedName("tragicmc.stinHorn").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:StinHorn" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(StinHorn, "stinHorn");
		
		WispParticles = (new ItemGeneric().setUnlocalizedName("tragicmc.wispParticles").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:WispParticles" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(WispParticles, "wispParticles");
		
		IcyFur = (new ItemGeneric().setUnlocalizedName("tragicmc.icyFur").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:IcyFur" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(IcyFur, "icyFur");
		
		PureDarkness = (new ItemGeneric().setUnlocalizedName("tragicmc.pureDarkness").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:NoTexture"));
		GameRegistry.registerItem(PureDarkness, "pureDarkness");
		
		LivingClay = (new ItemGeneric().setUnlocalizedName("tragicmc.livingClay").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:NoTexture"));
		GameRegistry.registerItem(LivingClay, "livingClay");

		//Food Registrations
		IceCream = (new ItemIceCream(4, false).setUnlocalizedName("tragicmc.iceCream").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:IceCream" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(IceCream, "iceCream");

		ExoticFruit = (new ItemExoticFruit(9, false).setUnlocalizedName("tragicmc.exoticFruit").setMaxStackSize(16).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:ExoticFruit" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(ExoticFruit, "exoticFruit");

		GooeyFruit = (new ItemGooeyFruit(6, false).setUnlocalizedName("tragicmc.gooeyFruit").setMaxStackSize(8).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:GooeyFruit" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(GooeyFruit, "gooeyFruit");

		NastyFruit = (new ItemNastyFruit(4, false).setUnlocalizedName("tragicmc.nastyFruit").setMaxStackSize(8).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:NastyFruit" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(NastyFruit, "nastyFruit");

		Rice = (new ItemRice(2, false).setUnlocalizedName("tragicmc.rice").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Rice" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Rice, "rice");

		Sushi = (new ItemSushi(4, false).setUnlocalizedName("tragicmc.sushi").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Sushi" + TragicNewConfig.textureRes)); 
		GameRegistry.registerItem(Sushi, "sushi");

		GoldenSushi = (new ItemEnchantedSushi(8, false).setUnlocalizedName("tragicmc.goldenSushi").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:GoldenSushi" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(GoldenSushi, "goldenSushi");

		Banana = (new ItemBanana(1, false).setUnlocalizedName("tragicmc.banana").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:Banana" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(Banana, "banana");

		BananaSplit = (new ItemBananaSplit(8, false).setUnlocalizedName("tragicmc.bananaSplit").setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:BananaSplit" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(BananaSplit, "bananaSplit");

		//Special Item Registrations
		RubyCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.rubyCharm").setMaxStackSize(16).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:RubyCharm" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(RubyCharm, "rubyCharm");

		SapphireCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.sapphireCharm").setMaxStackSize(16).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:SapphireCharm" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(SapphireCharm, "sapphireCharm");

		DiamondCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.diamondCharm").setMaxStackSize(16).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:DiamondCharm" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(DiamondCharm, "diamondCharm");

		EmeraldCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.emeraldCharm").setMaxStackSize(16).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:EmeraldCharm" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(EmeraldCharm, "emeraldCharm");

		AwakeningStone = (new ItemGeneric().setUnlocalizedName("tragicmc.awakeningStone").setMaxStackSize(1).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:AwakeningStone" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(AwakeningStone, "awakeningStone");

		ObsidianOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.obsidianOrb").setMaxStackSize(16).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:ObsidianOrb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(ObsidianOrb, "obsidianOrb");

		CryingObsidianOrb = (new ItemCryingObsidianOrb().setUnlocalizedName("tragicmc.cryingObsidianOrb").setMaxStackSize(8).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:CryingObsidianOrb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(CryingObsidianOrb, "cryingObsidianOrb");

		BleedingObsidianOrb = (new ItemBleedingObsidianOrb().setUnlocalizedName("tragicmc.bleedingObsidianOrb").setMaxStackSize(8).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:BleedingObsidianOrb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(BleedingObsidianOrb, "bleedingObsidianOrb");

		DyingObsidianOrb = (new ItemDyingObsidianOrb().setUnlocalizedName("tragicmc.dyingObsidianOrb").setMaxStackSize(8).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:DyingObsidianOrb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(DyingObsidianOrb, "dyingObsidianOrb");

		DimensionalKey = (new ItemDimensionalKey().setUnlocalizedName("tragicmc.dimensionalKey").setMaxStackSize(1).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:DimensionalKe" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(DimensionalKey, "dimensionalKey");

		//Projectile Item Registrations (For Testing)
		Rock = (new ItemRock().setUnlocalizedName("tragicmc.rock"));
		GameRegistry.registerItem(Rock, "rock");

		Pumpkinbomb = (new ItemPumpkinbomb().setUnlocalizedName("tragicmc.pumpkinbomb"));
		GameRegistry.registerItem(Pumpkinbomb, "pumpkinbomb");

		PoisonBarb = (new ItemProjectile().setUnlocalizedName("tragicmc.poisonBarb").setTextureName("tragicmc:PoisonBarb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(PoisonBarb, "poisonBarb");

		NekoRocket = (new ItemProjectile().setUnlocalizedName("tragicmc.nekoRocket").setTextureName("tragicmc:NekoRocket" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(NekoRocket, "nekoRocket");

		NekoStickyBomb = (new ItemProjectile().setUnlocalizedName("tragicmc.nekoStickyBomb").setTextureName("tragicmc:NekoStickyBomb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(NekoStickyBomb, "nekoStickyBomb");

		NekoClusterBomb = (new ItemProjectile().setUnlocalizedName("tragicmc.nekoClusterBomb").setTextureName("tragicmc:NekoClusterBomb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(NekoClusterBomb, "nekoClusterBomb");

		NekoMiniBomb = (new ItemProjectile().setUnlocalizedName("tragicmc.nekoMiniBomb").setTextureName("tragicmc:NekoMiniBomb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(NekoMiniBomb, "nekoMiniBomb");

		SolarBomb = (new ItemProjectile().setUnlocalizedName("tragicmc.solarBomb").setTextureName("tragicmc:SolarBomb" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(SolarBomb, "solarBomb");

		SpiritCast = (new ItemProjectile().setUnlocalizedName("tragicmc.spiritCast").setTextureName("tragicmc:SpiritCast" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(SpiritCast, "spiritCast");
		
		MobStatue = (new ItemStatue());
		GameRegistry.registerItem(MobStatue, "mobStatue");

		//Doom/Amulet Item Registrations
		DoomConsume = (new ItemDoomUpgrade().setUnlocalizedName("tragicmc.doomConsume").setMaxStackSize(1).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:DoomConsume" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(DoomConsume, "doomConsume");

		CooldownDefuse = (new ItemCooldownDefuse().setUnlocalizedName("tragicmc.cooldownDefuse").setMaxStackSize(16).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:CooldownDefuse" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(CooldownDefuse, "cooldownDefuse");

		AmuletRelease = (new ItemAmuletRelease().setUnlocalizedName("tragicmc.amuletRelease").setMaxStackSize(1).setCreativeTab(TragicTabs.Survival).setTextureName("tragicmc:AmuletRelease" + TragicNewConfig.textureRes));
		GameRegistry.registerItem(AmuletRelease, "amuletRelease");

		//Amulet Registrations		
		Color color1 = new Color(0x00, 0x00, 0x00);
		Color color2 = new Color(0xff, 0xff, 0xff);
		int id = 0;

		color2 = new Color(0xB5, 0x19, 0x19);
		KitsuneAmulet = (new ItemAmulet(id++, 4, "Kitsune", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(KitsuneAmulet, "kitsuneAmulet");

		color1 = new Color(0x9F, 0x5B, 0x86);
		color2 = new Color(0xFF, 0x9A, 0xCA);
		PeaceAmulet = (new ItemAmulet(id, 1, "Peace", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(PeaceAmulet, "peaceAmulet");
		PeaceAmulet2 = (new ItemAmulet(id, 2, "Peace", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(PeaceAmulet2, "peaceAmulet2");
		PeaceAmulet3 = (new ItemAmulet(id++, 3, "Peace", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(PeaceAmulet3, "peaceAmulet3");

		color1 = new Color(0xFF, 0xFF, 0xFF);
		color2 = new Color(0x98, 0xB4, 0xC1);
		YetiAmulet = (new ItemAmulet(id, 1, "Yeti", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(YetiAmulet, "yetiAmulet");
		YetiAmulet2 = (new ItemAmulet(id, 2, "Yeti", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(YetiAmulet2, "yetiAmulet2");
		YetiAmulet3 = (new ItemAmulet(id++, 3, "Yeti", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(YetiAmulet3, "yetiAmulet3");

		color1 = new Color(0xE5, 0xBA, 0x77);
		color2 = new Color(0xE7, 0xD2, 0x6F);
		ClaymationAmulet = (new ItemAmulet(id, 1, "Claymation", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(ClaymationAmulet, "claymationAmulet");
		ClaymationAmulet2 = (new ItemAmulet(id, 2, "Claymation", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(ClaymationAmulet2, "claymationAmulet2");
		ClaymationAmulet3 = (new ItemAmulet(id++, 3, "Claymation", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(ClaymationAmulet3, "claymationAmulet3");

		color1 = new Color(0xDE, 0xDE, 0xDE);
		color2 = new Color(0xFF, 0xEA, 0xA1);
		ChickenAmulet = (new ItemAmulet(id, 1, "Chicken", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(ChickenAmulet, "chickenAmulet");
		ChickenAmulet2 = (new ItemAmulet(id, 2, "Chicken", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(ChickenAmulet2, "chickenAmulet2");
		ChickenAmulet3 = (new ItemAmulet(id++, 3, "Chicken", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(ChickenAmulet3, "chickenAmulet3");

		color1 = new Color(0x00, 0x00, 0x00);
		color2 = new Color(0x45, 0x45, 0x45);
		MartyrAmulet = (new ItemAmulet(id++, 4, "Martyr", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(MartyrAmulet, "martyrAmulet");

		color1 = new Color(0x00, 0x00, 0x00);
		color2 = new Color(0xFF, 0xFA, 0x5E);
		HornetAmulet = (new ItemAmulet(id++, 4, "Hornet", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(HornetAmulet, "hornetAmulet");

		color1 = new Color(0x94, 0x94, 0x94);
		color2 = new Color(0x69, 0x69, 0x69);
		BlacksmithAmulet = (new ItemAmulet(id, 1, "Blacksmith", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(BlacksmithAmulet, "blacksmithAmulet");
		BlacksmithAmulet2 = (new ItemAmulet(id, 2, "Blacksmith", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(BlacksmithAmulet2, "blacksmithAmulet2");
		BlacksmithAmulet3 = (new ItemAmulet(id++, 3, "Blacksmith", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(BlacksmithAmulet3, "blacksmithAmulet3");

		color1 = new Color(0x00, 0x00, 0x00);
		color2 = new Color(0xEF, 0xE2, 0x97);
		ApisAmulet = (new ItemAmulet(id++, 4, "Apis", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(ApisAmulet, "apisAmulet");

		color1 = new Color(0x27, 0xC1, 0x23);
		color2 = new Color(0x43, 0xE1, 0x40);
		CreeperAmulet = (new ItemAmulet(id, 1, "Creeper", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(CreeperAmulet, "creeperAmulet");
		CreeperAmulet2 = (new ItemAmulet(id, 2, "Creeper", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(CreeperAmulet2, "creeperAmulet2");
		CreeperAmulet3 = (new ItemAmulet(id++, 3, "Creeper", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(CreeperAmulet3, "creeperAmulet3");

		color1 = new Color(0x3A, 0x8F, 0x4A);
		color2 = new Color(0x27, 0xC1, 0xC9);
		ZombieAmulet = (new ItemAmulet(id, 1, "Zombie", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(ZombieAmulet, "zombieAmulet");
		ZombieAmulet2 = (new ItemAmulet(id, 2, "Zombie", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(ZombieAmulet2, "zombieAmulet2");
		ZombieAmulet3 = (new ItemAmulet(id++, 3, "Zombie", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(ZombieAmulet3, "zombieAmulet3");

		color1 = new Color(0xA0, 0xA0, 0xA0);
		color2 = new Color(0xC1, 0xC1, 0xC1);
		SkeletonAmulet = (new ItemAmulet(id, 1, "Skeleton", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(SkeletonAmulet, "skeletonAmulet");
		SkeletonAmulet2 = (new ItemAmulet(id, 2, "Skeleton", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(SkeletonAmulet2, "skeletonAmulet2");
		SkeletonAmulet3 = (new ItemAmulet(id++, 3, "Skeleton", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(SkeletonAmulet3, "skeletonAmulet3");


		color1 = new Color(0x00, 0x00, 0x00);
		color2 = new Color(0x46, 0x6D, 0xB3);
		SunkenAmulet = (new ItemAmulet(id++, 4, "Sunken", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(SunkenAmulet, "sunkenAmulet");

		color1 = new Color(0x00, 0x00, 0x00);
		color2 = new Color(0x00, 0x00, 0x00);
		TimeAmulet = (new ItemAmulet(id++, 4, "Time", color1.getRGB(), color2.getRGB()));
		GameRegistry.registerItem(TimeAmulet, "timeAmulet");

		//Armor and Tool materials
		toolScythe.customCraftingMaterial = Items.bone;
		armorSkull.customCraftingMaterial = Items.bone;

		toolMercury.customCraftingMaterial = RedMercury;
		armorMercury.customCraftingMaterial = RedMercury;

		toolHunter.customCraftingMaterial = ToughLeather;
		armorHunter.customCraftingMaterial = ToughLeather;

		toolLight.customCraftingMaterial = LightParticles;
		armorLight.customCraftingMaterial = LightParticles;

		toolDarkness.customCraftingMaterial = DarkParticles;
		armorDark.customCraftingMaterial = DarkParticles;

		//Creative only items
		BowOfJustice = (new WeaponBowOfJustice().setUnlocalizedName("tragicmc.bowOfJustice").setTextureName("bow").setCreativeTab(TragicTabs.Creative));
		GameRegistry.registerItem(BowOfJustice, "bowOfJustice");

		SwordOfJustice = (new WeaponSwordOfJustice(toolJustice).setUnlocalizedName("tragicmc.swordOfJustice").setTextureName("gold_sword").setCreativeTab(TragicTabs.Creative));
		GameRegistry.registerItem(SwordOfJustice, "swordOfJustice");
		
		VoidPitGenerator = (new ItemGenerator().setUnlocalizedName("tragicmc.voidPitGenerator").setTextureName("tragicmc:VoidPitGenerator_lowRes"));
		GameRegistry.registerItem(VoidPitGenerator, "voidPitGenerator");
		
		SpikeGenerator = (new ItemGenerator().setUnlocalizedName("tragicmc.spikeGenerator").setTextureName("tragicmc:SpikeGenerator_lowRes"));
		GameRegistry.registerItem(SpikeGenerator, "spikeGenerator");
		
		StarCrystalGenerator = (new ItemGenerator().setUnlocalizedName("tragicmc.starCrystalGenerator").setTextureName("tragicmc:StarCrystalGenerator_lowRes"));
		GameRegistry.registerItem(StarCrystalGenerator, "starCrystalGenerator");
		
		SphereGenerator = (new ItemGenerator().setUnlocalizedName("tragicmc.sphereGenerator").setTextureName("tragicmc:SphereGenerator_lowRes"));
		GameRegistry.registerItem(SphereGenerator, "sphereGenerator");
		
		SphereEraser = (new ItemGenerator().setUnlocalizedName("tragicmc.sphereEraser").setTextureName("tragicmc:SphereEraser_lowRes"));
		GameRegistry.registerItem(SphereEraser, "sphereEraser");
		
		LiquidRemover = (new ItemGenerator().setUnlocalizedName("tragicmc.liquidRemover").setTextureName("tragicmc:LiquidRemover_lowRes"));
		GameRegistry.registerItem(LiquidRemover, "liquidRemover");
		
		TreeGenerator = (new ItemGenerator().setUnlocalizedName("tragicmc.treeGenerator").setTextureName("tragicmc:TreeGenerator_lowRes"));
		GameRegistry.registerItem(TreeGenerator, "treeGenerator");
		
		LightningSummoner = (new ItemGenerator().setUnlocalizedName("tragicmc.lightningSummoner").setTextureName("tragicmc:LightningSummoner_lowRes"));
		GameRegistry.registerItem(LightningSummoner, "lightningSummoner");
		
		ExplosionGenerator = (new ItemGenerator().setUnlocalizedName("tragicmc.explosionGenerator").setTextureName("tragicmc:ExplosionGenerator_lowRes"));
		GameRegistry.registerItem(ExplosionGenerator, "explosionGenerator");
		
		NekoNekoWand = (new ItemNekoWand().setUnlocalizedName("tragicmc.nekoNekoWand").setTextureName("tragicmc:NekoNekoWand_lowRes").setCreativeTab(TragicTabs.Creative));
		GameRegistry.registerItem(NekoNekoWand, "nekoNekoWand");

		//Chest Gens
		//WeightedRandomChestContent(ItemStack, min gen chance, max gen chance, item weight);

		WeightedRandomChestContent[] bossStructureContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(DoomConsume), 0, 1, TragicNewConfig.doomConsumeRarity),
				new WeightedRandomChestContent(new ItemStack(CooldownDefuse), 1, 3, TragicNewConfig.cooldownDefuseRarity),
				new WeightedRandomChestContent(new ItemStack(Items.diamond, 1), 0, 2, 20),
				new WeightedRandomChestContent(new ItemStack(Blocks.diamond_block, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Items.gold_ingot, 1), 0, 2, 40),
				new WeightedRandomChestContent(new ItemStack(Blocks.gold_block, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Ruby, 1), 0, 2, 25),
				new WeightedRandomChestContent(new ItemStack(Sapphire, 1), 0, 2, 25),
				new WeightedRandomChestContent(new ItemStack(Items.emerald, 1), 0, 2, 30),
				new WeightedRandomChestContent(new ItemStack(Blocks.emerald_block, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Tungsten, 1), 0, 2, 50),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.StorageBlock, 1, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.StorageBlock, 1, 2), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.StorageBlock, 1, 3), 0, 1, 35),
				new WeightedRandomChestContent(new ItemStack(KitsuneAmulet, 1), 0, 1, 5),
				//new WeightedRandomChestContent(new ItemStack(HornetAmulet, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(ObsidianOrb, 1), 0, 2, 3),
				new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 0, 1, 1),
				new WeightedRandomChestContent(new ItemStack(HuntersBow, 1), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(DarkHelm, 1), 0, 1, 7),
				new WeightedRandomChestContent(new ItemStack(DarkPlate, 1), 0, 1, 7),
				new WeightedRandomChestContent(new ItemStack(DarkLegs, 1), 0, 1, 7),
				new WeightedRandomChestContent(new ItemStack(DarkBoots, 1), 0, 1, 7),
				new WeightedRandomChestContent(new ItemStack(LightHelm, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(LightPlate, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(LightLegs, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(LightBoots, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_helmet, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_chestplate, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_pickaxe, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_leggings, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_boots, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_sword, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(ExoticFruit, 1), 1, 4, 25),
				new WeightedRandomChestContent(new ItemStack(GooeyFruit, 1), 0, 2, 5),
				new WeightedRandomChestContent(new ItemStack(Items.cooked_beef, 3), 2, 5, 65),
				new WeightedRandomChestContent(new ItemStack(Items.cooked_porkchop, 3), 2, 5, 65),
				new WeightedRandomChestContent(new ItemStack(Items.saddle, 1), 0, 2, 60),
				new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(ApisAmulet, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(MartyrAmulet, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(BlacksmithAmulet, 1), 0, 1, 3),
				new WeightedRandomChestContent(new ItemStack(CreeperAmulet, 1), 0, 1, 8),
				new WeightedRandomChestContent(new ItemStack(ZombieAmulet, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(SkeletonAmulet, 1), 0, 1, 12),
				new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 0, 1, TragicNewConfig.amuletReleaseRarity),
				new WeightedRandomChestContent(new ItemStack(Items.lead, 1 + TragicMC.rand.nextInt(2)), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Items.saddle), 0, 2, 5)
		};

		WeightedRandomChestContent[] netherStructureContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(DoomConsume), 0, 1, TragicNewConfig.doomConsumeRarity),
				new WeightedRandomChestContent(new ItemStack(CooldownDefuse), 1, 3, TragicNewConfig.cooldownDefuseRarity),
				new WeightedRandomChestContent(new ItemStack(Items.diamond, 1), 0, 2, 25),
				new WeightedRandomChestContent(new ItemStack(Blocks.diamond_block, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Ruby, 1), 0, 2, 18),
				new WeightedRandomChestContent(new ItemStack(Sapphire, 1), 0, 2, 18),
				new WeightedRandomChestContent(new ItemStack(Items.emerald, 1), 0, 2, 20),
				new WeightedRandomChestContent(new ItemStack(Blocks.emerald_block, 1), 0, 1, 8),
				new WeightedRandomChestContent(new ItemStack(Tungsten, 1), 0, 2, 70),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.StorageBlock, 1, 1), 0, 1, 30),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.StorageBlock, 1, 2), 0, 1, 30),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.StorageBlock, 1, 3), 0, 1, 50),
				new WeightedRandomChestContent(new ItemStack(KitsuneAmulet, 1), 0, 1, 15),
				//new WeightedRandomChestContent(new ItemStack(HornetAmulet, 1), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(ObsidianOrb, 1), 0, 2, 10),
				new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1), 0, 1, 35),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(ApisAmulet, 1), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(MartyrAmulet, 1), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(GoldenSushi, 1), 0, 2, 5),
				new WeightedRandomChestContent(new ItemStack(BlacksmithAmulet, 1), 0, 1, 12),
				new WeightedRandomChestContent(new ItemStack(CreeperAmulet, 1), 0, 1, 20),
				new WeightedRandomChestContent(new ItemStack(ZombieAmulet, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(SkeletonAmulet, 1), 0, 1, 25),
				new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 0, 1, TragicNewConfig.amuletReleaseRarity),
				new WeightedRandomChestContent(new ItemStack(Items.lead, 1 + TragicMC.rand.nextInt(2)), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(Items.saddle), 0, 2, 15)
		};

		WeightedRandomChestContent[] lameChestContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(Blocks.cobblestone), 3, 5, 50),
				new WeightedRandomChestContent(new ItemStack(Blocks.gravel), 2, 4, 50),
				new WeightedRandomChestContent(new ItemStack(Items.apple), 0, 3, 25),
				new WeightedRandomChestContent(new ItemStack(TragicItems.Ash), 0, 3, 25),
				new WeightedRandomChestContent(new ItemStack(Items.wheat_seeds), 2, 4, 50),
				new WeightedRandomChestContent(new ItemStack(Items.coal), 0, 3, 25),
				new WeightedRandomChestContent(new ItemStack(Items.rotten_flesh), 2, 5, 100),
				new WeightedRandomChestContent(new ItemStack(Items.string), 2, 4, 75),
				new WeightedRandomChestContent(new ItemStack(Items.bone), 0, 2, 25)
		};

		WeightedRandomChestContent[] awesomeChestContent = new WeightedRandomChestContent[] { //TODO add the uncraftable weapons to this generation with around 20 in weight, then add to the other two boss hooks with very low weight, around 3 maybe
				new WeightedRandomChestContent(new ItemStack(TragicItems.DoomConsume), 0, 1, 45),
				new WeightedRandomChestContent(new ItemStack(TragicItems.CooldownDefuse), 1, 3, 60),
				new WeightedRandomChestContent(new ItemStack(TragicItems.AmuletRelease, 1), 0, 1, 45),
				new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 0, 2, 35),
				new WeightedRandomChestContent(new ItemStack(GoldenSushi, 1), 0, 3, 45),
				new WeightedRandomChestContent(new ItemStack(Items.diamond, 1 + TragicMC.rand.nextInt(3)), 0, 3, 50),
				new WeightedRandomChestContent(new ItemStack(Items.emerald, 1 + TragicMC.rand.nextInt(3)), 0, 3, 50),
				new WeightedRandomChestContent(new ItemStack(Ruby, 1 + TragicMC.rand.nextInt(3)), 0, 3, 50),
				new WeightedRandomChestContent(new ItemStack(Sapphire, 1 + TragicMC.rand.nextInt(3)), 0, 3, 50),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 0, 1, 35),
				new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 0, 1, 45),
				new WeightedRandomChestContent(new ItemStack(Items.saddle), 0, 2, 25)
		};

		BossStructureHook = (new ChestGenHooks("TragicMC.BossStructure", bossStructureContent, 3, 7));
		NetherStructureHook = (new ChestGenHooks("TragicMC.NetherBossStructure", netherStructureContent, 5, 8));
		LameChestHook = (new ChestGenHooks("TragicMC.LameChest", lameChestContent, 3, 6));
		AwesomeChestHook = (new ChestGenHooks("TragicMC.AwesomeChest", awesomeChestContent, 6, 10));


		if (TragicNewConfig.allowCooldownDefuse)
		{
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicNewConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicNewConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicNewConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicNewConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicNewConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 2), 1, 3, TragicNewConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicNewConfig.cooldownDefuseRarity));
		}

		if (TragicNewConfig.shouldDoomLimitIncrease)
		{
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicNewConfig.doomConsumeRarity));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicNewConfig.doomConsumeRarity));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicNewConfig.doomConsumeRarity));
		}

		if (TragicNewConfig.shouldUnlockAmuletSlots)
		{
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicNewConfig.amuletReleaseRarity));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicNewConfig.amuletReleaseRarity));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicNewConfig.amuletReleaseRarity));
		}

		if (TragicNewConfig.allowMobs)
		{
			SpawnEgg = (new ItemMobEgg());
			GameRegistry.registerItem(SpawnEgg, "spawnEgg");
		}

		OreDictionary.registerOre("itemRock", Rock);
		OreDictionary.registerOre("itemLavaRock", new ItemStack(Rock, 1, 1));
		OreDictionary.registerOre("gemRuby", Ruby);
		OreDictionary.registerOre("gemSapphire", Sapphire);
		OreDictionary.registerOre("cropRice", Rice);
		OreDictionary.registerOre("foodSushi", Sushi);
		OreDictionary.registerOre("foodIceCream", IceCream);
		OreDictionary.registerOre("foodBanana", Banana);
		OreDictionary.registerOre("foodBananaSplit", BananaSplit);
		OreDictionary.registerOre("ingotTungsten", Tungsten);
		OreDictionary.registerOre("ingotMercury", QuicksilverIngot);

		//Vanilla entries for my mod
		for (int i = 0; i < 5; i++)
		{
			OreDictionary.registerOre("fish", new ItemStack(Items.fish, 1, i));
		}

		OreDictionary.registerOre("oreCharms", RubyCharm);
		OreDictionary.registerOre("oreCharms", SapphireCharm);
		OreDictionary.registerOre("oreCharms", DiamondCharm);
		OreDictionary.registerOre("oreCharms", EmeraldCharm);
	}

}
