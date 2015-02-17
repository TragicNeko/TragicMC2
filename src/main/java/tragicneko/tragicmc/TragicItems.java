package tragicneko.tragicmc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.commons.lang3.ArrayUtils;

import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.ItemAmulet;
import tragicneko.tragicmc.items.ItemAmuletRelease;
import tragicneko.tragicmc.items.ItemBleedingObsidianOrb;
import tragicneko.tragicmc.items.ItemBloodSacrifice;
import tragicneko.tragicmc.items.ItemChallenge;
import tragicneko.tragicmc.items.ItemCooldownDefuse;
import tragicneko.tragicmc.items.ItemCryingObsidianOrb;
import tragicneko.tragicmc.items.ItemDimensionalKey;
import tragicneko.tragicmc.items.ItemDoomUpgrade;
import tragicneko.tragicmc.items.ItemDoomsdayScroll;
import tragicneko.tragicmc.items.ItemDyingObsidianOrb;
import tragicneko.tragicmc.items.ItemGenerator;
import tragicneko.tragicmc.items.ItemGeneric;
import tragicneko.tragicmc.items.ItemHydration;
import tragicneko.tragicmc.items.ItemLightningRod;
import tragicneko.tragicmc.items.ItemMobEgg;
import tragicneko.tragicmc.items.ItemMoonlight;
import tragicneko.tragicmc.items.ItemNekoWand;
import tragicneko.tragicmc.items.ItemNourishmentSacrifice;
import tragicneko.tragicmc.items.ItemProjectile;
import tragicneko.tragicmc.items.ItemStatue;
import tragicneko.tragicmc.items.ItemSynthesis;
import tragicneko.tragicmc.items.ItemTalisman;
import tragicneko.tragicmc.items.armor.ArmorDark;
import tragicneko.tragicmc.items.armor.ArmorHunter;
import tragicneko.tragicmc.items.armor.ArmorLight;
import tragicneko.tragicmc.items.armor.ArmorMercury;
import tragicneko.tragicmc.items.armor.ArmorOverlord;
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
import tragicneko.tragicmc.items.food.ItemSkyFruit;
import tragicneko.tragicmc.items.food.ItemSushi;
import tragicneko.tragicmc.items.food.ItemTentacle;
import tragicneko.tragicmc.items.weapons.ItemEverlastingLight;
import tragicneko.tragicmc.items.weapons.ItemJack;
import tragicneko.tragicmc.items.weapons.ItemScythe;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.items.weapons.WeaponBeastlyClaws;
import tragicneko.tragicmc.items.weapons.WeaponBlindingLight;
import tragicneko.tragicmc.items.weapons.WeaponBowOfJustice;
import tragicneko.tragicmc.items.weapons.WeaponButcher;
import tragicneko.tragicmc.items.weapons.WeaponCelestialAegis;
import tragicneko.tragicmc.items.weapons.WeaponCelestialLongbow;
import tragicneko.tragicmc.items.weapons.WeaponDragonFang;
import tragicneko.tragicmc.items.weapons.WeaponFrozenLightning;
import tragicneko.tragicmc.items.weapons.WeaponGravitySpike;
import tragicneko.tragicmc.items.weapons.WeaponGuiltyThorn;
import tragicneko.tragicmc.items.weapons.WeaponHarmonyBell;
import tragicneko.tragicmc.items.weapons.WeaponHuntersBow;
import tragicneko.tragicmc.items.weapons.WeaponMourningStar;
import tragicneko.tragicmc.items.weapons.WeaponNekoLauncher;
import tragicneko.tragicmc.items.weapons.WeaponParanoia;
import tragicneko.tragicmc.items.weapons.WeaponPitchBlack;
import tragicneko.tragicmc.items.weapons.WeaponReaperScythe;
import tragicneko.tragicmc.items.weapons.WeaponSentinel;
import tragicneko.tragicmc.items.weapons.WeaponSilentHellraiser;
import tragicneko.tragicmc.items.weapons.WeaponSplinter;
import tragicneko.tragicmc.items.weapons.WeaponSwordOfJustice;
import tragicneko.tragicmc.items.weapons.WeaponThardus;
import tragicneko.tragicmc.items.weapons.WeaponTitan;
import tragicneko.tragicmc.items.weapons.WeaponWitheringAxe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TragicItems {

	@Deprecated
	public static final String textureRes = "_lowRes";

	//Item variables
	public static Item RedMercury, Quicksilver, QuicksilverIngot, Jack;
	public static Item MercuryDagger, MercuryHelm, MercuryPlate, MercuryLegs, MercuryBoots;
	public static Item Tungsten, TungstenJack, TungstenHelm, TungstenPlate, TungstenLegs, TungstenBoots;

	public static Item Scythe, SkullHelmet, SkullPlate, SkullLegs, SkullBoots;
	public static Item HuntersBow, HuntersCap, HuntersTunic, HuntersLegs, HuntersBoots;

	public static Item DarkIngot, DarkHelm, DarkPlate, DarkLegs, DarkBoots, PitchBlack;
	public static Item LightIngot, LightHelm, LightPlate, LightLegs, LightBoots, BlindingLight, EverlastingLight;

	public static Item OverlordHelm, OverlordPlate, OverlordLegs, OverlordBoots;

	public static Item CelestialAegis, CelestialLongbow, CelestialJack, CelestialSteel;
	public static Item GravitySpike, HarmonyBell, MourningStar, BeastlyClaws, GuiltyThorn, NekoLauncher, ReaperScythe, WitheringAxe;

	public static Item Splinter, Titan, Butcher, Thardus, Paranoia, DragonFang, SilentHellraiser;

	public static Item Sentinel, Password;

	public static Item RainDanceTalisman, SunnyDayTalisman, ThunderstormTalisman, TimeManipulatorTalisman;
	public static Item Talisman, MoonlightTalisman, SynthesisTalisman, HydrationTalisman, LightningRodTalisman;

	public static Item Ruby, Sapphire, RubyCharm, SapphireCharm, DiamondCharm, EmeraldCharm, AwakeningStone, ObsidianOrb, CryingObsidianOrb, BleedingObsidianOrb, DyingObsidianOrb;

	public static Item LightParticles; //Apis
	public static Item EmpariahClaw; //Empariah
	public static Item DeathlyHallow; //Skultar
	public static Item TimeEssence; //Time Controller
	public static Item PureDarkness; //Enyvil
	public static Item KitsuneTail; //Kitsunakuma
	public static Item LivingClay; //Claymation
	public static Item StarPieces; //Polaris
	//public static Item PhoenixFeather; //Phoenix
	public static Item OverlordEye; //The Overlord

	//Doom items
	public static Item DoomConsume, CooldownDefuse, AmuletRelease;

	//Amulets
	public static Item KitsuneAmulet;
	public static Item YetiAmulet;
	public static Item PeaceAmulet;
	public static Item ClaymationAmulet;
	public static Item ChickenAmulet;
	public static Item MartyrAmulet;
	public static Item PiercingAmulet;
	public static Item BlacksmithAmulet;
	public static Item ApisAmulet;
	public static Item CreeperAmulet;
	public static Item ZombieAmulet;
	public static Item SkeletonAmulet;
	public static Item SunkenAmulet;
	public static Item TimeAmulet;
	public static Item IceAmulet;
	public static Item SnowGolemAmulet;
	public static Item IronGolemAmulet;
	public static Item EndermanAmulet;
	public static Item WitherAmulet;

	//Normal items
	public static Item Ectoplasm;
	public static Item Ash;
	public static Item EnchantedTears;
	public static Item ToughLeather;
	public static Item WovenSilk;
	public static Item CrushedIce;
	public static Item DarkParticles;
	public static Item Spore;
	public static Item Sap;
	public static Item Thorns;
	public static Item BoneMarrow;
	public static Item Horn;
	public static Item LunarPowder;
	public static Item CelestialDiamond;

	public static Item IceOrb, GravityOrb, FireOrb, LightningOrb, AquaOrb;

	public static Item WispParticles;
	public static Item StinHorn;
	public static Item IcyFur;

	public static Item FrozenLightning;

	public static Item BloodSacrifice;
	public static Item NourishmentSacrifice;

	//Projectile items
	public static Item Projectile;

	//Food items
	public static Item IceCream;
	public static Item ExoticFruit;
	public static Item GooeyFruit;
	public static Item NastyFruit;
	public static Item Rice;
	public static Item Sushi;
	public static Item Tentacle;
	public static Item Banana;
	public static Item BananaSplit;
	public static Item GoldenSushi;
	public static Item SkyFruit;

	public static Item DimensionalKey, DimensionalKeyEnd, DimensionalKeyNether, DimensionalKeySynapse;
	public static Item SynapseLink, SynapseCrystal;

	public static Item BowOfJustice;
	public static Item SwordOfJustice;
	public static Item Generator;
	public static Item NekoNekoWand;

	public static Item SpawnEgg;
	public static Item MobStatue;
	public static Item ChallengeScroll;
	public static Item DoomsdayScroll;

	public static ChestGenHooks BossStructureHook;
	public static ChestGenHooks NetherStructureHook;
	public static ChestGenHooks LameChestHook;
	public static ChestGenHooks AwesomeChestHook;

	//Tool Materials
	private static final ToolMaterial toolBasic = EnumHelper.addToolMaterial("BASIC", 1, 115, 12.0F, 0.0F, 1);
	private static final ToolMaterial toolScythe = EnumHelper.addToolMaterial("SCYTHE", 1, 110, 12.0F, 0.0F, 6);
	private static final ToolMaterial toolJack = EnumHelper.addToolMaterial("JACK", 3, 825, 15.0F, 2.0F, 5);
	private static final ToolMaterial toolGravity = EnumHelper.addToolMaterial("GRAVITY", 1, 220, 0.5F, 0.5F, 10);
	private static final ToolMaterial toolHarmony = EnumHelper.addToolMaterial("HARMONY", 0, 330, 0F, -4F, 5);
	private static final ToolMaterial toolLauncher = EnumHelper.addToolMaterial("LAUNCHER", 0, 330, 0F, 0F, 1);
	private static final ToolMaterial toolHunter = EnumHelper.addToolMaterial("HUNTER", 1, 160, 1F, 1F, 4);
	private static final ToolMaterial toolMercury = EnumHelper.addToolMaterial("MERCURY", 2, 170, 1.5F, 1.5F, 12);
	private static final ToolMaterial toolTungsten = EnumHelper.addToolMaterial("TUNGSTEN", 3, 770, 2.5F, 2.5F, 8);
	private static final ToolMaterial toolClaws = EnumHelper.addToolMaterial("CLAWS", 2, 350, 4.0F, 4.0F, 2);
	private static final ToolMaterial toolThorn = EnumHelper.addToolMaterial("THORN", 1, 330, 6.0F, 6.0F, 4);
	private static final ToolMaterial toolFrozen = EnumHelper.addToolMaterial("FROZEN", 2, 400, 6.0F, 6.0F, 3);
	private static final ToolMaterial toolWithering = EnumHelper.addToolMaterial("WITHERING", 3, 550, 15.0F, 6.0F, 16);
	private static final ToolMaterial toolDarkness = EnumHelper.addToolMaterial("DARKNESS", 3, 350, 8.0F, 8.0F, 26);
	private static final ToolMaterial toolLight = EnumHelper.addToolMaterial("LIGHT", 3, 333, 8.0F, 8.0F, 14);
	private static final ToolMaterial toolMourning = EnumHelper.addToolMaterial("MOURNING", 3, 440, 9.0F, 9.0F, 14);
	private static final ToolMaterial toolReaper = EnumHelper.addToolMaterial("REAPER", 3, 800, 30.0F, 12.0F, 10);
	private static final ToolMaterial toolCelesJack = EnumHelper.addToolMaterial("CELESJACK", 3, 1280, 20.0F, 4.0F, 4);
	private static final ToolMaterial toolCelestial = EnumHelper.addToolMaterial("CELESTIAL", 3, 1080, 14.0F, 14.0F, 24);
	private static final ToolMaterial toolDragon = EnumHelper.addToolMaterial("DRAGON", 3, 860, 14.0F, 14.0F, 18);
	private static final ToolMaterial toolTragic = EnumHelper.addToolMaterial("TRAGIC", 3, 400, 21.0F, 21.0F, 28);
	private static final ToolMaterial toolAwakened = EnumHelper.addToolMaterial("AWAKENED", 3, 1000, 26.0F, 26.0F, 32);
	private static final ToolMaterial toolSentinel = EnumHelper.addToolMaterial("SENTINEL", 4, 9001, 26.0F, 26.0F, 32);
	private static final ToolMaterial toolJustice = EnumHelper.addToolMaterial("JUSTICE", 100, 1000, Float.MAX_VALUE, 999999996.0F, 100);

	//Armor Materials
	private static final ArmorMaterial armorSkull = EnumHelper.addArmorMaterial("SKULL", 32, new int[] {1, 2, 1, 1}, 16); //5
	private static final ArmorMaterial armorHunter = EnumHelper.addArmorMaterial("HUNTER", 29, new int[] {2, 3, 2, 2}, 8); //9
	private static final ArmorMaterial armorMercury = EnumHelper.addArmorMaterial("MERCURY", 24, new int[] {2, 5, 4, 2}, 22); //13
	private static final ArmorMaterial armorTungsten = EnumHelper.addArmorMaterial("TUNGSTEN", 22, new int[] {3, 6, 4, 2}, 10); //15
	private static final ArmorMaterial armorDark = EnumHelper.addArmorMaterial("DARK", 18, new int[] {3, 7, 5, 3}, 6); //18
	private static final ArmorMaterial armorLight = EnumHelper.addArmorMaterial("LIGHT", 26, new int[] {5, 7, 6, 4}, 18); //22
	private static final ArmorMaterial armorOverlord = EnumHelper.addArmorMaterial("OVERLORD", 35, new int[] {5, 8, 7, 4}, 26); //24

	public static void load()
	{
		//Ore Registrations
		RedMercury = (new ItemGeneric().setUnlocalizedName("tragicmc.redMercury").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:RedMercury" + textureRes));
		GameRegistry.registerItem(RedMercury, "redMercury");

		Quicksilver = (new ItemGeneric().setUnlocalizedName("tragicmc.quicksilver").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Quicksilver" + textureRes));
		GameRegistry.registerItem(Quicksilver, "quicksilver");

		QuicksilverIngot = (new ItemGeneric().setUnlocalizedName("tragicmc.quicksilverIngot").setMaxStackSize(16).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:QuicksilverIngot" + textureRes));
		GameRegistry.registerItem(QuicksilverIngot, "quicksilverIngot");

		Tungsten = (new ItemGeneric().setUnlocalizedName("tragicmc.tungsten").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Tungsten" + textureRes));
		GameRegistry.registerItem(Tungsten, "tungsten");

		Ruby = (new ItemGeneric().setUnlocalizedName("tragicmc.ruby").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Ruby" + textureRes));
		GameRegistry.registerItem(Ruby, "ruby");

		Sapphire = (new ItemGeneric().setUnlocalizedName("tragicmc.sapphire").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Sapphire" + textureRes));
		GameRegistry.registerItem(Sapphire, "sapphire");

		//Armor Registrations
		SkullHelmet = (new ArmorSkull(armorSkull, 0, Doomsday.Decay).setUnlocalizedName("tragicmc.skullHelmet").setTextureName("tragicmc:SkullHelmet" + textureRes));
		GameRegistry.registerItem(SkullHelmet, "skullHelmet");

		SkullPlate = (new ArmorSkull(armorSkull, 1, Doomsday.Decay).setUnlocalizedName("tragicmc.skullPlate").setTextureName("tragicmc:SkullPlate" + textureRes));
		GameRegistry.registerItem(SkullPlate, "skullPlate");

		SkullLegs = (new ArmorSkull(armorSkull, 2, Doomsday.Decay).setUnlocalizedName("tragicmc.skullLegs").setTextureName("tragicmc:SkullLegs" + textureRes));
		GameRegistry.registerItem(SkullLegs, "skullLegs");

		SkullBoots = (new ArmorSkull(armorSkull, 3, Doomsday.Decay).setUnlocalizedName("tragicmc.skullBoots").setTextureName("tragicmc:SkullBoots" + textureRes));
		GameRegistry.registerItem(SkullBoots, "skullBoots");


		HuntersCap = (new ArmorHunter(armorHunter, 0, Doomsday.HuntersInstinct).setUnlocalizedName("tragicmc.huntersCap").setTextureName("tragicmc:HuntersCap" + textureRes));
		GameRegistry.registerItem(HuntersCap, "huntersCap");

		HuntersTunic = (new ArmorHunter(armorHunter, 1, Doomsday.HuntersInstinct).setUnlocalizedName("tragicmc.huntersTunic").setTextureName("tragicmc:HuntersTunic" + textureRes));
		GameRegistry.registerItem(HuntersTunic, "huntersTunic");

		HuntersLegs = (new ArmorHunter(armorHunter, 2, Doomsday.HuntersInstinct).setUnlocalizedName("tragicmc.huntersLegs").setTextureName("tragicmc:HuntersLegs" + textureRes));
		GameRegistry.registerItem(HuntersLegs, "huntersLegs");

		HuntersBoots = (new ArmorHunter(armorHunter, 3, Doomsday.HuntersInstinct).setUnlocalizedName("tragicmc.huntersBoots").setTextureName("tragicmc:HuntersBoots" + textureRes));
		GameRegistry.registerItem(HuntersBoots, "huntersBoots");


		MercuryHelm = (new ArmorMercury(armorMercury, 0, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryHelm").setTextureName("tragicmc:MercuryHelm" + textureRes));
		GameRegistry.registerItem(MercuryHelm, "mercuryHelm");

		MercuryPlate = (new ArmorMercury(armorMercury, 1, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryPlate").setTextureName("tragicmc:MercuryPlate" + textureRes));
		GameRegistry.registerItem(MercuryPlate, "mercuryPlate");

		MercuryLegs = (new ArmorMercury(armorMercury, 2, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryLegs").setTextureName("tragicmc:MercuryLegs" + textureRes));
		GameRegistry.registerItem(MercuryLegs, "mercuryLegs");

		MercuryBoots = (new ArmorMercury(armorMercury, 3, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryBoots").setTextureName("tragicmc:MercuryBoots" + textureRes));
		GameRegistry.registerItem(MercuryBoots, "mercuryBoots");


		TungstenHelm = (new ArmorTungsten(armorTungsten, 0, Doomsday.Berserker).setUnlocalizedName("tragicmc.tungstenHelm").setTextureName("tragicmc:TungstenHelm" + textureRes));
		GameRegistry.registerItem(TungstenHelm, "tungstenHelm");

		TungstenPlate = (new ArmorTungsten(armorTungsten, 1, Doomsday.Berserker).setUnlocalizedName("tragicmc.tungstenPlate").setTextureName("tragicmc:TungstenPlate" + textureRes));
		GameRegistry.registerItem(TungstenPlate, "tungstenPlate");

		TungstenLegs = (new ArmorTungsten(armorTungsten, 2, Doomsday.Berserker).setUnlocalizedName("tragicmc.tungstenLegs").setTextureName("tragicmc:TungstenLegs" + textureRes));
		GameRegistry.registerItem(TungstenLegs, "tungstenLegs");

		TungstenBoots = (new ArmorTungsten(armorTungsten, 3, Doomsday.Berserker).setUnlocalizedName("tragicmc.tungstenBoots").setTextureName("tragicmc:TungstenBoots" + textureRes));
		GameRegistry.registerItem(TungstenBoots, "tungstenBoots");


		LightHelm = (new ArmorLight(armorLight, 0, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightHelm").setTextureName("tragicmc:LightHelm" + textureRes));
		GameRegistry.registerItem(LightHelm, "lightHelm");

		LightPlate = (new ArmorLight(armorLight, 1, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightPlate").setTextureName("tragicmc:LightPlate" + textureRes));
		GameRegistry.registerItem(LightPlate, "lightPlate");

		LightLegs = (new ArmorLight(armorLight, 2, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightLegs").setTextureName("tragicmc:LightLegs" + textureRes));
		GameRegistry.registerItem(LightLegs, "lightLegs");

		LightBoots = (new ArmorLight(armorLight, 3, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightBoots").setTextureName("tragicmc:LightBoots" + textureRes));
		GameRegistry.registerItem(LightBoots, "lightBoots");


		DarkHelm = (new ArmorDark(armorDark, 0, Doomsday.Fear).setUnlocalizedName("tragicmc.darkHelm").setTextureName("tragicmc:DarkHelm" + textureRes));
		GameRegistry.registerItem(DarkHelm, "darkHelm");

		DarkPlate = (new ArmorDark(armorDark, 1, Doomsday.Fear).setUnlocalizedName("tragicmc.darkPlate").setTextureName("tragicmc:DarkPlate" + textureRes));
		GameRegistry.registerItem(DarkPlate, "darkPlate");

		DarkLegs = (new ArmorDark(armorDark, 2, Doomsday.Fear).setUnlocalizedName("tragicmc.darkLegs").setTextureName("tragicmc:DarkLegs" + textureRes));
		GameRegistry.registerItem(DarkLegs, "darkLegs");

		DarkBoots = (new ArmorDark(armorDark, 3, Doomsday.Fear).setUnlocalizedName("tragicmc.darkBoots").setTextureName("tragicmc:DarkBoots" + textureRes));
		GameRegistry.registerItem(DarkBoots, "darkBoots");
		
		
		OverlordHelm = (new ArmorOverlord(armorOverlord, 0, Doomsday.Harden).setUnlocalizedName("tragicmc.overlordHelm").setTextureName("tragicmc:OverlordHelm" + textureRes));
		GameRegistry.registerItem(OverlordHelm, "overlordHelm");
		
		OverlordPlate = (new ArmorOverlord(armorOverlord, 1, Doomsday.Harden).setUnlocalizedName("tragicmc.overlordPlate").setTextureName("tragicmc:OverlordPlate" + textureRes));
		GameRegistry.registerItem(OverlordPlate, "overlordPlate");
		
		OverlordLegs = (new ArmorOverlord(armorOverlord, 2, Doomsday.Harden).setUnlocalizedName("tragicmc.overlordLegs").setTextureName("tragicmc:OverlordLegs" + textureRes));
		GameRegistry.registerItem(OverlordLegs, "overlordLegs");
		
		OverlordBoots = (new ArmorOverlord(armorOverlord, 3, Doomsday.Harden).setUnlocalizedName("tragicmc.overlordBoots").setTextureName("tragicmc:OverlordBoots" + textureRes));
		GameRegistry.registerItem(OverlordBoots, "overlordBoots");

		//Weapon Registrations
		MercuryDagger = (new TragicWeapon(toolMercury, Doomsday.PoisonBreak).setUnlocalizedName("tragicmc.mercuryDagger").setTextureName("tragicmc:MercuryDagger" + textureRes));
		GameRegistry.registerItem(MercuryDagger, "mercuryDagger");

		HuntersBow = (new WeaponHuntersBow().setUnlocalizedName("tragicmc.huntersBow").setMaxStackSize(1).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:HuntersBow" + textureRes));
		GameRegistry.registerItem(HuntersBow, "huntersBow");

		PitchBlack = (new WeaponPitchBlack(toolDarkness, Doomsday.NatureDrain).setUnlocalizedName("tragicmc.pitchBlack").setTextureName("tragicmc:PitchBlack" + textureRes));
		GameRegistry.registerItem(PitchBlack, "pitchBlack");

		BlindingLight = (new WeaponBlindingLight(toolLight, Doomsday.PiercingLight).setUnlocalizedName("tragicmc.blindingLight").setTextureName("tragicmc:BlindingLight" + textureRes));
		GameRegistry.registerItem(BlindingLight, "blindingLight");

		GravitySpike = (new WeaponGravitySpike(toolGravity, Doomsday.Pulse).setUnlocalizedName("tragicmc.gravitySpike").setTextureName("tragicmc:GravitySpike" + textureRes));
		GameRegistry.registerItem(GravitySpike, "gravitySpike");

		HarmonyBell = (new WeaponHarmonyBell(toolHarmony, Doomsday.Harmonizer).setUnlocalizedName("tragicmc.harmonyBell").setTextureName("tragicmc:HarmonyBell" + textureRes));
		GameRegistry.registerItem(HarmonyBell, "harmonyBell");

		MourningStar = (new WeaponMourningStar(toolMourning, Doomsday.Ravage).setUnlocalizedName("tragicmc.mourningStar").setTextureName("tragicmc:MourningStar" + textureRes));
		GameRegistry.registerItem(MourningStar, "mourningStar");

		BeastlyClaws = (new WeaponBeastlyClaws(toolClaws, Doomsday.BeastlyImpulses).setUnlocalizedName("tragicmc.beastlyClaws").setTextureName("tragicmc:BeastlyClaws" + textureRes));
		GameRegistry.registerItem(BeastlyClaws, "beastlyClaws");

		GuiltyThorn = (new WeaponGuiltyThorn(toolThorn, Doomsday.Torment).setUnlocalizedName("tragicmc.guiltyThorn").setTextureName("tragicmc:GuiltyThorn" + textureRes));
		GameRegistry.registerItem(GuiltyThorn, "guiltyThorn");

		NekoLauncher = (new WeaponNekoLauncher(toolLauncher, Doomsday.SuicidalTendencies).setUnlocalizedName("tragicmc.nekoLauncher").setTextureName("tragicmc:NekoLauncher" + textureRes));
		GameRegistry.registerItem(NekoLauncher, "nekoLauncher");

		ReaperScythe = (new WeaponReaperScythe(toolReaper, Doomsday.ReaperLaugh).setUnlocalizedName("tragicmc.reaperScythe").setTextureName("tragicmc:ReaperScythe" + textureRes));
		GameRegistry.registerItem(ReaperScythe, "reaperScythe");

		WitheringAxe = (new WeaponWitheringAxe(toolWithering, Doomsday.SkullCrusher).setUnlocalizedName("tragicmc.witheringAxe").setTextureName("tragicmc:WitheringAxe" + textureRes));
		GameRegistry.registerItem(WitheringAxe, "witheringAxe");

		FrozenLightning = (new WeaponFrozenLightning(toolFrozen, Doomsday.Freeze).setUnlocalizedName("tragicmc.frozenLightning").setTextureName("tragicmc:FrozenLightning" + textureRes));
		GameRegistry.registerItem(FrozenLightning, "frozenLightning");

		CelestialAegis = (new WeaponCelestialAegis(toolCelestial, Doomsday.MoonlightSonata).setUnlocalizedName("tragicmc.celestialAegis").setTextureName("tragicmc:CelestialAegis" + textureRes));
		GameRegistry.registerItem(CelestialAegis, "celestialAegis");

		CelestialLongbow = (new WeaponCelestialLongbow().setUnlocalizedName("tragicmc.celestialLongbow").setTextureName("tragicmc:CelestialLongbow" + textureRes));
		GameRegistry.registerItem(CelestialLongbow, "celestialLongbow");
		
		SilentHellraiser = (new WeaponSilentHellraiser(toolTragic, null).setUnlocalizedName("tragicmc.silentHellraiser").setTextureName("tragicmc:TragicHellraiser" + textureRes));
		GameRegistry.registerItem(SilentHellraiser, "silentHellraiser");

		//Epic weapons
		Titan = (new WeaponTitan(Doomsday.Titanfall).setUnlocalizedName("tragicmc.titan").setTextureName("tragicmc:Titan" + textureRes));
		GameRegistry.registerItem(Titan, "titan");

		Splinter = (new WeaponSplinter(Doomsday.Marionette).setUnlocalizedName("tragicmc.splinter").setTextureName("tragicmc:Splinter" + textureRes));
		GameRegistry.registerItem(Splinter, "splinter");

		Butcher = (new WeaponButcher(Doomsday.Bloodlust).setUnlocalizedName("tragicmc.butcher").setTextureName("tragicmc:Butcher" + textureRes));
		GameRegistry.registerItem(Butcher, "butcher");

		Thardus = (new WeaponThardus(Doomsday.Permafrost).setUnlocalizedName("tragicmc.thardus").setTextureName("tragicmc:Thardus" + textureRes));
		GameRegistry.registerItem(Thardus, "thardus");

		Paranoia = (new WeaponParanoia(Doomsday.Asphyxiate).setUnlocalizedName("tragicmc.paranoia").setTextureName("tragicmc:Paranoia" + textureRes));
		GameRegistry.registerItem(Paranoia, "paranoia");

		DragonFang = (new WeaponDragonFang(Doomsday.DragonsRoar).setUnlocalizedName("tragicmc.dragonFang").setTextureName("tragicmc:DragonFang" + textureRes));
		GameRegistry.registerItem(DragonFang, "dragonFang");		
		
		//Big boss weapons
		Sentinel = (new WeaponSentinel(toolSentinel, Doomsday.Sharpen).setUnlocalizedName("tragicmc.sentinel").setTextureName("tragicmc:Sentinel" + textureRes));
		GameRegistry.registerItem(Sentinel, "sentinel");

		//Tool Registrations
		Scythe = (new ItemScythe(toolScythe).setUnlocalizedName("tragicmc.scythe").setTextureName("tragicmc:Scythe" + textureRes));
		GameRegistry.registerItem(Scythe, "scythe");

		EverlastingLight = (new ItemEverlastingLight().setUnlocalizedName("tragicmc.everlastingLight").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:EverlastingLight" + textureRes));
		GameRegistry.registerItem(EverlastingLight, "everlastingLight");

		Jack = (new ItemJack(toolBasic, null).setUnlocalizedName("tragicmc.jack").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:BasicJack_lowRes"));
		GameRegistry.registerItem(Jack, "jack");

		TungstenJack = (new ItemJack(toolJack, Doomsday.MinerSkills) {}.setUnlocalizedName("tragicmc.tungstenJack").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:TungstenJack_lowRes"));
		GameRegistry.registerItem(TungstenJack, "tungstenJack");

		CelestialJack = (new ItemJack(toolCelesJack, Doomsday.RealityAlter) {}.setUnlocalizedName("tragicmc.celestialJack").setTextureName("tragicmc:CelestialJack" + textureRes));
		GameRegistry.registerItem(CelestialJack, "celestialJack");

		//Normal Item Registrations
		Ectoplasm = (new ItemGeneric().setUnlocalizedName("tragicmc.ectoplasm").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Ectoplasm" + textureRes));
		GameRegistry.registerItem(Ectoplasm, "ectoplasm");

		Ash = (new ItemGeneric().setUnlocalizedName("tragicmc.ash").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Ash" + textureRes));
		GameRegistry.registerItem(Ash, "ash");

		EnchantedTears = (new ItemGeneric() {
			@SideOnly(Side.CLIENT)
			@Override
			public boolean hasEffect(ItemStack stack, int pass)
			{
				return pass == 0;
			}
		}.setUnlocalizedName("tragicmc.lifeWater").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:LifeWater" + textureRes));
		GameRegistry.registerItem(EnchantedTears, "enchantedTears");

		ToughLeather = (new ItemGeneric().setUnlocalizedName("tragicmc.toughLeather").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:ToughLeather" + textureRes));
		GameRegistry.registerItem(ToughLeather, "toughLeather");

		WovenSilk = (new ItemGeneric().setUnlocalizedName("tragicmc.wovenSilk").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:WovenSilk" + textureRes));
		GameRegistry.registerItem(WovenSilk, "wovenSilk");

		CrushedIce = (new ItemGeneric().setUnlocalizedName("tragicmc.crushedIce").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:CrushedIce" + textureRes));
		GameRegistry.registerItem(CrushedIce, "crushedIce");

		LightParticles = (new ItemGeneric().setUnlocalizedName("tragicmc.lightParticles").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:LightParticles" + textureRes));
		GameRegistry.registerItem(LightParticles, "lightParticles");

		DarkParticles = (new ItemGeneric().setUnlocalizedName("tragicmc.darkParticles").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:DarkParticles" + textureRes));
		GameRegistry.registerItem(DarkParticles, "darkParticles");

		IceOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.iceOrb").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:IceOrb" + textureRes));
		GameRegistry.registerItem(IceOrb, "iceOrb");

		GravityOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.gravityOrb").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:GravityOrb" + textureRes));
		GameRegistry.registerItem(GravityOrb, "gravityOrb");

		FireOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.fireOrb").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:FireOrb" + textureRes));
		GameRegistry.registerItem(FireOrb, "fireOrb");

		LightningOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.lightningOrb").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:LightningOrb" + textureRes));
		GameRegistry.registerItem(LightningOrb, "lightningOrb");

		AquaOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.aquaOrb").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:AquaOrb" + textureRes));
		GameRegistry.registerItem(AquaOrb, "aquaOrb");

		Spore = (new ItemGeneric().setUnlocalizedName("tragicmc.spore").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:ProjectileSpore" + textureRes));
		GameRegistry.registerItem(Spore, "spore");

		Thorns = (new ItemGeneric().setUnlocalizedName("tragicmc.thorns").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Thorns" + textureRes));
		GameRegistry.registerItem(Thorns, "thorns");

		Sap = (new ItemGeneric().setUnlocalizedName("tragicmc.sap").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Sap" + textureRes));
		GameRegistry.registerItem(Sap, "sap");

		Horn = (new ItemGeneric().setUnlocalizedName("tragicmc.horn").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Horn" + textureRes));
		GameRegistry.registerItem(Horn, "horn");

		BoneMarrow = (new ItemBoneMarrow(2, false).setUnlocalizedName("tragicmc.boneMarrow").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:BoneMarrow" + textureRes));
		GameRegistry.registerItem(BoneMarrow, "boneMarrow");

		LightIngot = (new ItemGeneric().setUnlocalizedName("tragicmc.lightIngot").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:LightIngot" + textureRes));
		GameRegistry.registerItem(LightIngot, "lightIngot");

		DarkIngot = (new ItemGeneric().setUnlocalizedName("tragicmc.darkIngot").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:DarkIngot" + textureRes));
		GameRegistry.registerItem(DarkIngot, "darkIngot");

		KitsuneTail = (new ItemGeneric().setUnlocalizedName("tragicmc.kitsuneTail").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:KitsuneTail" + textureRes));
		GameRegistry.registerItem(KitsuneTail, "kitsuneTail");

		DeathlyHallow = (new ItemGeneric().setUnlocalizedName("tragicmc.reaperSkull").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:DeathlyHallow" + textureRes));
		GameRegistry.registerItem(DeathlyHallow, "deathlyHallow");

		EmpariahClaw = (new ItemGeneric().setUnlocalizedName("tragicmc.yetiClaw").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:YetiClaw" + textureRes));
		GameRegistry.registerItem(EmpariahClaw, "yetiClaw");

		StarPieces = (new ItemGeneric().setUnlocalizedName("tragicmc.starPieces").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:StarPieces" + textureRes));
		GameRegistry.registerItem(StarPieces, "starPieces");

		TimeEssence = (new ItemGeneric().setUnlocalizedName("tragicmc.timeEssence").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:TimeEssence" + textureRes));
		GameRegistry.registerItem(TimeEssence, "timeEssence");

		LunarPowder = (new ItemGeneric().setUnlocalizedName("tragicmc.lunarPowder").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:LunarPowder" + textureRes));
		GameRegistry.registerItem(LunarPowder, "lunarPowder");

		CelestialDiamond = (new ItemGeneric().setUnlocalizedName("tragicmc.celestialDiamond").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:CelestialDiamond" + textureRes));
		GameRegistry.registerItem(CelestialDiamond, "celestialDiamond");

		StinHorn = (new ItemGeneric().setUnlocalizedName("tragicmc.stinHorn").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:StinHorn" + textureRes));
		GameRegistry.registerItem(StinHorn, "stinHorn");

		WispParticles = (new ItemGeneric().setUnlocalizedName("tragicmc.wispParticles").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:WispParticles" + textureRes));
		GameRegistry.registerItem(WispParticles, "wispParticles");

		IcyFur = (new ItemGeneric().setUnlocalizedName("tragicmc.icyFur").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:IcyFur" + textureRes));
		GameRegistry.registerItem(IcyFur, "icyFur");

		PureDarkness = (new ItemGeneric().setUnlocalizedName("tragicmc.pureDarkness").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:PureDarkness" + textureRes));
		GameRegistry.registerItem(PureDarkness, "pureDarkness");

		LivingClay = (new ItemGeneric().setUnlocalizedName("tragicmc.livingClay").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:LivingClay" + textureRes));
		GameRegistry.registerItem(LivingClay, "livingClay");

		CelestialSteel = (new ItemGeneric().setUnlocalizedName("tragicmc.celestialSteel").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:CelestialSteel" + textureRes));
		GameRegistry.registerItem(CelestialSteel, "celestialSteel");

		//Food Registrations
		IceCream = (new ItemIceCream(4, false).setUnlocalizedName("tragicmc.iceCream").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:IceCream" + textureRes));
		GameRegistry.registerItem(IceCream, "iceCream");

		ExoticFruit = (new ItemExoticFruit(9, false).setUnlocalizedName("tragicmc.exoticFruit").setMaxStackSize(16).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:ExoticFruit" + textureRes));
		GameRegistry.registerItem(ExoticFruit, "exoticFruit");

		GooeyFruit = (new ItemGooeyFruit(6, false).setUnlocalizedName("tragicmc.gooeyFruit").setMaxStackSize(8).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:GooeyFruit" + textureRes));
		GameRegistry.registerItem(GooeyFruit, "gooeyFruit");

		NastyFruit = (new ItemNastyFruit(4, false).setUnlocalizedName("tragicmc.nastyFruit").setMaxStackSize(8).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:NastyFruit" + textureRes));
		GameRegistry.registerItem(NastyFruit, "nastyFruit");

		Rice = (new ItemRice(2, false).setUnlocalizedName("tragicmc.rice").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Rice" + textureRes));
		GameRegistry.registerItem(Rice, "rice");

		Sushi = (new ItemSushi(4, true).setUnlocalizedName("tragicmc.sushi").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Sushi" + textureRes)); 
		GameRegistry.registerItem(Sushi, "sushi");

		GoldenSushi = (new ItemEnchantedSushi(8, true).setUnlocalizedName("tragicmc.goldenSushi").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:GoldenSushi" + textureRes));
		GameRegistry.registerItem(GoldenSushi, "goldenSushi");

		Banana = (new ItemBanana(1, false).setUnlocalizedName("tragicmc.banana").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:ProjectileBanana" + textureRes));
		GameRegistry.registerItem(Banana, "banana");

		BananaSplit = (new ItemBananaSplit(8, false).setUnlocalizedName("tragicmc.bananaSplit").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:BananaSplit" + textureRes));
		GameRegistry.registerItem(BananaSplit, "bananaSplit");

		SkyFruit = (new ItemSkyFruit(4, false).setUnlocalizedName("tragicmc.skyFruit").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:SkyFruit" + textureRes));
		GameRegistry.registerItem(SkyFruit, "skyFruit");

		Tentacle = (new ItemTentacle(3, true).setUnlocalizedName("tragicmc.tentacle").setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Tentacle" + textureRes));
		GameRegistry.registerItem(Tentacle, "tentacle");

		//Special Item Registrations
		RubyCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.rubyCharm").setMaxStackSize(16).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:RubyCharm" + textureRes));
		GameRegistry.registerItem(RubyCharm, "rubyCharm");

		SapphireCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.sapphireCharm").setMaxStackSize(16).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:SapphireCharm" + textureRes));
		GameRegistry.registerItem(SapphireCharm, "sapphireCharm");

		DiamondCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.diamondCharm").setMaxStackSize(16).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:DiamondCharm" + textureRes));
		GameRegistry.registerItem(DiamondCharm, "diamondCharm");

		EmeraldCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.emeraldCharm").setMaxStackSize(16).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:EmeraldCharm" + textureRes));
		GameRegistry.registerItem(EmeraldCharm, "emeraldCharm");

		AwakeningStone = (new ItemGeneric().setUnlocalizedName("tragicmc.awakeningStone").setMaxStackSize(1).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:AwakeningStone" + textureRes));
		GameRegistry.registerItem(AwakeningStone, "awakeningStone");

		ObsidianOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.obsidianOrb").setMaxStackSize(16).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:ObsidianOrb" + textureRes));
		GameRegistry.registerItem(ObsidianOrb, "obsidianOrb");

		CryingObsidianOrb = (new ItemCryingObsidianOrb().setUnlocalizedName("tragicmc.cryingObsidianOrb").setMaxStackSize(8).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:CryingObsidianOrb" + textureRes));
		GameRegistry.registerItem(CryingObsidianOrb, "cryingObsidianOrb");

		BleedingObsidianOrb = (new ItemBleedingObsidianOrb().setUnlocalizedName("tragicmc.bleedingObsidianOrb").setMaxStackSize(8).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:BleedingObsidianOrb" + textureRes));
		GameRegistry.registerItem(BleedingObsidianOrb, "bleedingObsidianOrb");

		DyingObsidianOrb = (new ItemDyingObsidianOrb().setUnlocalizedName("tragicmc.dyingObsidianOrb").setMaxStackSize(8).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:DyingObsidianOrb" + textureRes));
		GameRegistry.registerItem(DyingObsidianOrb, "dyingObsidianOrb");

		//Weather/Time Talismans
		Talisman = (new ItemGeneric().setUnlocalizedName("tragicmc.talisman").setMaxStackSize(16).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:Talisman" + textureRes));
		GameRegistry.registerItem(Talisman, "talisman");

		RainDanceTalisman = (new ItemTalisman()
		{
			@Override
			public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
			{
				if (world.isRemote || !(entity instanceof EntityPlayer)) return;

				EntityPlayer player = (EntityPlayer) entity;
				if (!world.isRaining()) world.rainingStrength = 1.0F;
			}
		}.setUnlocalizedName("tragicmc.rainDanceTalisman").setTextureName("tragicmc:RainDanceTalisman" + textureRes));
		GameRegistry.registerItem(RainDanceTalisman, "rainDanceTalisman");

		SunnyDayTalisman = (new ItemTalisman()
		{
			@Override
			public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
			{
				if (world.isRemote || !(entity instanceof EntityPlayer)) return;

				EntityPlayer player = (EntityPlayer) entity;

				if (world.isThundering()) world.thunderingStrength = 0.0F;
				if (world.isRaining()) world.rainingStrength = 0.0F;
			}
		}).setUnlocalizedName("tragicmc.sunnyDayTalisman").setTextureName("tragicmc:SunnyDayTalisman" + textureRes);
		GameRegistry.registerItem(SunnyDayTalisman, "sunnyDayTalisman");

		ThunderstormTalisman = (new ItemTalisman()
		{
			@Override
			public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
			{
				if (world.isRemote || !(entity instanceof EntityPlayer)) return;

				EntityPlayer player = (EntityPlayer) entity;
				if (!world.isThundering()) world.thunderingStrength = 1.0F;
			}
		}).setUnlocalizedName("tragicmc.thunderstormTalisman").setTextureName("tragicmc:ThunderstormTalisman" + textureRes);
		GameRegistry.registerItem(ThunderstormTalisman, "thunderstormTalisman");

		TimeManipulatorTalisman = (new ItemTalisman()
		{
			@Override
			public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
			{
				if (world.isRemote || !(entity instanceof EntityPlayer) || !TragicConfig.allowItemTimeAltering) return;

				EntityPlayer player = (EntityPlayer) entity;

				int a = flag ? -5 : 5;
				world.setWorldTime(world.getWorldTime() + a);
			}
		}.setUnlocalizedName("tragicmc.timeManipulatorTalisman").setTextureName("tragicmc:TimeManipulatorTalisman" + textureRes));
		GameRegistry.registerItem(TimeManipulatorTalisman, "timeManipulatorTalisman");

		MoonlightTalisman = (new ItemMoonlight().setUnlocalizedName("tragicmc.moonlightTalisman").setTextureName("tragicmc:MoonlightTalisman" + textureRes));
		GameRegistry.registerItem(MoonlightTalisman, "moonlightTalisman");

		SynthesisTalisman = (new ItemSynthesis().setUnlocalizedName("tragicmc.synthesisTalisman").setTextureName("tragicmc:SynthesisTalisman" + textureRes));
		GameRegistry.registerItem(SynthesisTalisman, "synthesisTalisman");

		HydrationTalisman = (new ItemHydration().setUnlocalizedName("tragicmc.hydrationTalisman").setTextureName("tragicmc:HydrationTalisman" + textureRes));
		GameRegistry.registerItem(HydrationTalisman, "hydrationTalisman");

		LightningRodTalisman = (new ItemLightningRod().setUnlocalizedName("tragicmc.lightningRodTalisman").setTextureName("tragicmc:LightningRodTalisman" + textureRes));
		GameRegistry.registerItem(LightningRodTalisman, "lightningRodTalisman");

		//Amulet Registrations
		int id = 0;

		KitsuneAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(KitsuneAmulet, "kitsuneAmulet");

		PeaceAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(PeaceAmulet, "peaceAmulet");

		YetiAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(YetiAmulet, "yetiAmulet");

		ClaymationAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(ClaymationAmulet, "claymationAmulet");

		ChickenAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(ChickenAmulet, "chickenAmulet");

		MartyrAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(MartyrAmulet, "martyrAmulet");

		PiercingAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(PiercingAmulet, "piercingAmulet");

		BlacksmithAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(BlacksmithAmulet, "blacksmithAmulet");

		ApisAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(ApisAmulet, "apisAmulet");

		CreeperAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(CreeperAmulet, "creeperAmulet");

		ZombieAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(ZombieAmulet, "zombieAmulet");

		SkeletonAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(SkeletonAmulet, "skeletonAmulet");

		SunkenAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(SunkenAmulet, "sunkenAmulet");

		TimeAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(TimeAmulet, "timeAmulet");

		IceAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(IceAmulet, "iceAmulet");

		SnowGolemAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(SnowGolemAmulet, "snowGolemAmulet");

		IronGolemAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(IronGolemAmulet, "ironGolemAmulet");

		EndermanAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(EndermanAmulet, "endermanAmulet");

		WitherAmulet = (new ItemAmulet(id++));
		GameRegistry.registerItem(WitherAmulet, "witherAmulet");

		//Armor and Tool materials
		armorSkull.customCraftingMaterial = Items.bone;
		armorMercury.customCraftingMaterial = RedMercury;
		armorHunter.customCraftingMaterial = ToughLeather;
		armorLight.customCraftingMaterial = LightParticles;
		armorDark.customCraftingMaterial = DarkParticles;

		try
		{
			toolBasic.setRepairItem(new ItemStack(Items.flint));
			toolScythe.setRepairItem(new ItemStack(Items.bone));
			toolMercury.setRepairItem(new ItemStack(RedMercury));
			toolTungsten.setRepairItem(new ItemStack(Tungsten));
			toolLight.setRepairItem(new ItemStack(LightParticles));
			toolDarkness.setRepairItem(new ItemStack(DarkParticles));
			toolCelestial.setRepairItem(new ItemStack(CelestialSteel));
			toolCelesJack.setRepairItem(new ItemStack(CelestialSteel));
		}
		catch (NoSuchMethodError e)
		{
			TragicMC.logWarning("Error caught while setting repair items for materials. Update Forge to at least (1258) to avoid this error in the future.");

			toolBasic.customCraftingMaterial = Items.flint;
			toolScythe.customCraftingMaterial = Items.bone;
			toolMercury.customCraftingMaterial = RedMercury;
			toolTungsten.customCraftingMaterial = Tungsten;
			toolLight.customCraftingMaterial = LightParticles;
			toolDarkness.customCraftingMaterial = DarkParticles;
			toolCelestial.customCraftingMaterial = toolCelesJack.customCraftingMaterial = CelestialSteel;
		}

		//Special item registrations
		MobStatue = (new ItemStatue());
		GameRegistry.registerItem(MobStatue, "mobStatue");

		DoomConsume = (new ItemDoomUpgrade().setUnlocalizedName("tragicmc.doomConsume").setMaxStackSize(1).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:DoomConsume" + textureRes));
		GameRegistry.registerItem(DoomConsume, "doomConsume");

		CooldownDefuse = (new ItemCooldownDefuse().setUnlocalizedName("tragicmc.cooldownDefuse").setMaxStackSize(16).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:CooldownDefuse" + textureRes));
		GameRegistry.registerItem(CooldownDefuse, "cooldownDefuse");

		AmuletRelease = (new ItemAmuletRelease().setUnlocalizedName("tragicmc.amuletRelease").setMaxStackSize(1).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:AmuletRelease" + textureRes));
		GameRegistry.registerItem(AmuletRelease, "amuletRelease");

		BloodSacrifice = (new ItemBloodSacrifice().setUnlocalizedName("tragicmc.bloodSacrifice").setMaxStackSize(1).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:BloodSacrifice" + textureRes));
		GameRegistry.registerItem(BloodSacrifice, "bloodSacrifice");

		NourishmentSacrifice = (new ItemNourishmentSacrifice().setUnlocalizedName("tragicmc.nourishmentSacrifice").setMaxStackSize(1).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:NourishmentSacrifice" + textureRes));
		GameRegistry.registerItem(NourishmentSacrifice, "nourishmentSacrifice");

		DimensionalKey = (new ItemDimensionalKey(2).setUnlocalizedName("tragicmc.dimensionalKey.collision").setTextureName("tragicmc:DimensionalKey" + textureRes));
		GameRegistry.registerItem(DimensionalKey, "dimensionalKey");

		DimensionalKeyEnd = (new ItemDimensionalKey(1).setUnlocalizedName("tragicmc.dimensionalKey.end").setMaxStackSize(1).setCreativeTab(TragicMC.Creative).setTextureName("tragicmc:DimensionalKeyEnd" + textureRes));
		GameRegistry.registerItem(DimensionalKeyEnd, "dimensionalKeyEnd");

		DimensionalKeyNether = (new ItemDimensionalKey(-1).setUnlocalizedName("tragicmc.dimensionalKey.nether").setMaxStackSize(1).setCreativeTab(TragicMC.Creative).setTextureName("tragicmc:DimensionalKeyNether" + textureRes));
		GameRegistry.registerItem(DimensionalKeyNether, "dimensionalKeyNether");

		DimensionalKeySynapse = (new ItemDimensionalKey(3).setUnlocalizedName("tragicmc.dimensionalKey.synapse").setMaxStackSize(1).setCreativeTab(TragicMC.Creative).setTextureName("tragicmc:DimensionalKeySynapse" + textureRes));
		GameRegistry.registerItem(DimensionalKeySynapse, "dimensionalKeySynapse");

		SynapseLink = (new ItemDimensionalKey(3).setUnlocalizedName("tragicmc.synapseLink").setMaxStackSize(1).setMaxDamage(10).setCreativeTab(TragicMC.Creative).setTextureName("tragicmc:SynapseLink" + textureRes));
		GameRegistry.registerItem(SynapseLink, "synapseLink");

		SynapseCrystal = (new ItemGeneric().setUnlocalizedName("tragicmc.synapseCrystal").setMaxStackSize(64).setCreativeTab(TragicMC.Survival).setTextureName("tragicmc:SynapseCrystal" + textureRes));
		GameRegistry.registerItem(SynapseCrystal, "synapseCrystal");

		DoomsdayScroll = (new ItemDoomsdayScroll().setTextureName("tragicmc:DoomsdayScroll_lowRes"));
		GameRegistry.registerItem(DoomsdayScroll, "doomsdayScroll");

		//Creative only items
		BowOfJustice = (new WeaponBowOfJustice().setUnlocalizedName("tragicmc.bowOfJustice").setTextureName("bow"));
		GameRegistry.registerItem(BowOfJustice, "bowOfJustice");

		SwordOfJustice = (new WeaponSwordOfJustice(toolJustice).setUnlocalizedName("tragicmc.swordOfJustice").setTextureName("gold_sword"));
		GameRegistry.registerItem(SwordOfJustice, "swordOfJustice");

		if (TragicConfig.allowGeneratorItems)
		{
			Generator = (new ItemGenerator());
			GameRegistry.registerItem(Generator, "generator");
		}

		NekoNekoWand = (new ItemNekoWand().setUnlocalizedName("tragicmc.nekoNekoWand").setTextureName("tragicmc:NekoNekoWand_lowRes"));
		GameRegistry.registerItem(NekoNekoWand, "nekoNekoWand");

		if (TragicConfig.allowMobs)
		{
			SpawnEgg = (new ItemMobEgg());
			GameRegistry.registerItem(SpawnEgg, "spawnEgg");
		}

		Projectile = (new ItemProjectile());
		GameRegistry.registerItem(Projectile, "projectile");

		//Chest Gens

		WeightedRandomChestContent[] bossStructureContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(RubyCharm), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(SapphireCharm), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(EmeraldCharm), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(DiamondCharm), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(DoomConsume), 0, 1, TragicConfig.doomConsumeRarity),
				new WeightedRandomChestContent(new ItemStack(CooldownDefuse), 1, 3, TragicConfig.cooldownDefuseRarity),
				new WeightedRandomChestContent(new ItemStack(Items.diamond, 1), 0, 2, 20),
				new WeightedRandomChestContent(new ItemStack(Blocks.diamond_block, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Items.gold_ingot, 1), 0, 2, 40),
				new WeightedRandomChestContent(new ItemStack(Blocks.gold_block, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Ruby, 1), 0, 2, 25),
				new WeightedRandomChestContent(new ItemStack(Sapphire, 1), 0, 2, 25),
				new WeightedRandomChestContent(new ItemStack(Items.emerald, 1), 0, 2, 30),
				new WeightedRandomChestContent(new ItemStack(Blocks.emerald_block, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Tungsten, 1), 0, 2, 50),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 2), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 3), 0, 1, 35),
				new WeightedRandomChestContent(new ItemStack(KitsuneAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(PiercingAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
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
				new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(ApisAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(MartyrAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(BlacksmithAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(CreeperAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(ZombieAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(SkeletonAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 0, 1, TragicConfig.amuletReleaseRarity),
				new WeightedRandomChestContent(new ItemStack(Items.lead, 1 + TragicMC.rand.nextInt(2)), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Items.saddle), 0, 2, 5),
				new WeightedRandomChestContent(new ItemStack(IceAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(SnowGolemAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(IronGolemAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(EndermanAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(GuiltyThorn, 1), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(GravitySpike, 1), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(FrozenLightning, 1), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(Titan), 0, 1, 2),
				new WeightedRandomChestContent(new ItemStack(Splinter), 0, 1, 2),
				new WeightedRandomChestContent(new ItemStack(Thardus), 0, 1, 2),
				new WeightedRandomChestContent(new ItemStack(Paranoia), 0, 1, 2),
				new WeightedRandomChestContent(new ItemStack(Butcher), 0, 1, 2),
				new WeightedRandomChestContent(new ItemStack(DragonFang), 0, 1, 2),
				new WeightedRandomChestContent(new ItemStack(Talisman), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(BloodSacrifice), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(NourishmentSacrifice), 0, 1, 5)
		};

		WeightedRandomChestContent[] scrollArray = new WeightedRandomChestContent[Doomsday.doomsdayNames.length];

		for (int i = 0; i < Doomsday.doomsdayNames.length; i++)
		{
			if (Doomsday.doomsdayList[i] != null) scrollArray[i] = new WeightedRandomChestContent(new ItemStack(DoomsdayScroll, 1, i), 0, 1, 3);
		}

		ArrayUtils.addAll(bossStructureContent, scrollArray);

		WeightedRandomChestContent[] netherStructureContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(RubyCharm), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(SapphireCharm), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(EmeraldCharm), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(DiamondCharm), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(DoomConsume), 0, 1, TragicConfig.doomConsumeRarity),
				new WeightedRandomChestContent(new ItemStack(CooldownDefuse), 1, 3, TragicConfig.cooldownDefuseRarity),
				new WeightedRandomChestContent(new ItemStack(Items.diamond, 1), 0, 2, 25),
				new WeightedRandomChestContent(new ItemStack(Blocks.diamond_block, 1), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Ruby, 1), 0, 2, 18),
				new WeightedRandomChestContent(new ItemStack(Sapphire, 1), 0, 2, 18),
				new WeightedRandomChestContent(new ItemStack(Items.emerald, 1), 0, 2, 20),
				new WeightedRandomChestContent(new ItemStack(Blocks.emerald_block, 1), 0, 1, 8),
				new WeightedRandomChestContent(new ItemStack(Tungsten, 1), 0, 2, 70),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 1), 0, 1, 30),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 2), 0, 1, 30),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 3), 0, 1, 50),
				new WeightedRandomChestContent(new ItemStack(KitsuneAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(PiercingAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(ObsidianOrb, 1), 0, 2, 10),
				new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1), 0, 1, 35),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(ApisAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(MartyrAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(GoldenSushi, 1), 0, 2, 5),
				new WeightedRandomChestContent(new ItemStack(BlacksmithAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(CreeperAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(ZombieAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(SkeletonAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 0, 1, TragicConfig.amuletReleaseRarity),
				new WeightedRandomChestContent(new ItemStack(Items.lead, 1 + TragicMC.rand.nextInt(2)), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(Items.saddle), 0, 2, 15),
				new WeightedRandomChestContent(new ItemStack(IceAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(SnowGolemAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(IronGolemAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(EndermanAmulet, 1), 0, 1, TragicConfig.overallAmuletRarity),
				new WeightedRandomChestContent(new ItemStack(Titan), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Splinter), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Thardus), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Paranoia), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Butcher), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(DragonFang), 0, 1, 5),
				new WeightedRandomChestContent(new ItemStack(Talisman), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(BloodSacrifice), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(NourishmentSacrifice), 0, 1, 10)
		};

		scrollArray = new WeightedRandomChestContent[Doomsday.doomsdayNames.length];

		for (int i = 0; i < Doomsday.doomsdayNames.length; i++)
		{
			if (Doomsday.doomsdayList[i] != null) scrollArray[i] = new WeightedRandomChestContent(new ItemStack(DoomsdayScroll, 1, i), 0, 1, 5);
		}

		ArrayUtils.addAll(netherStructureContent, scrollArray);

		WeightedRandomChestContent[] lameChestContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(Blocks.cobblestone), 3, 5, 50),
				new WeightedRandomChestContent(new ItemStack(Blocks.gravel), 2, 4, 50),
				new WeightedRandomChestContent(new ItemStack(Items.apple), 0, 3, 25),
				new WeightedRandomChestContent(new ItemStack(Ash), 0, 3, 25),
				new WeightedRandomChestContent(new ItemStack(Items.wheat_seeds), 2, 4, 50),
				new WeightedRandomChestContent(new ItemStack(Items.coal), 0, 3, 25),
				new WeightedRandomChestContent(new ItemStack(Items.rotten_flesh), 2, 5, 100),
				new WeightedRandomChestContent(new ItemStack(Items.string), 2, 4, 75),
				new WeightedRandomChestContent(new ItemStack(Items.bone), 0, 2, 25),
				new WeightedRandomChestContent(new ItemStack(Spore), 0, 3, 10),
				new WeightedRandomChestContent(new ItemStack(Sap), 0, 3, 10)
		};

		WeightedRandomChestContent[] awesomeChestContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(TragicItems.DoomConsume), 0, 1, TragicConfig.doomConsumeRarity),
				new WeightedRandomChestContent(new ItemStack(TragicItems.CooldownDefuse), 1, 3, TragicConfig.cooldownDefuseRarity),
				new WeightedRandomChestContent(new ItemStack(TragicItems.AmuletRelease, 1), 0, 1, TragicConfig.amuletReleaseRarity),
				new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 0, 2, 35),
				new WeightedRandomChestContent(new ItemStack(GoldenSushi, 1), 0, 3, 45),
				new WeightedRandomChestContent(new ItemStack(Items.diamond, 1 + TragicMC.rand.nextInt(3)), 0, 3, 50),
				new WeightedRandomChestContent(new ItemStack(Items.emerald, 1 + TragicMC.rand.nextInt(3)), 0, 3, 50),
				new WeightedRandomChestContent(new ItemStack(Ruby, 1 + TragicMC.rand.nextInt(3)), 0, 3, 50),
				new WeightedRandomChestContent(new ItemStack(Sapphire, 1 + TragicMC.rand.nextInt(3)), 0, 3, 50),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 0, 1, 35),
				new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 0, 1, 45),
				new WeightedRandomChestContent(new ItemStack(Items.saddle), 0, 2, 25),
				new WeightedRandomChestContent(new ItemStack(RubyCharm), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(SapphireCharm), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(EmeraldCharm), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(DiamondCharm), 0, 1, 15),
				new WeightedRandomChestContent(new ItemStack(Titan), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Splinter), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Thardus), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Paranoia), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Butcher), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(DragonFang), 0, 1, 10),
				new WeightedRandomChestContent(new ItemStack(Talisman), 0, 1, 20),
				new WeightedRandomChestContent(new ItemStack(BloodSacrifice), 0, 1, 20),
				new WeightedRandomChestContent(new ItemStack(NourishmentSacrifice), 0, 1, 20)
		};

		scrollArray = new WeightedRandomChestContent[Doomsday.doomsdayNames.length];

		for (int i = 0; i < Doomsday.doomsdayNames.length; i++)
		{
			if (Doomsday.doomsdayList[i] != null) scrollArray[i] = new WeightedRandomChestContent(new ItemStack(DoomsdayScroll, 1, i), 0, 1, 10);
		}

		ArrayUtils.addAll(awesomeChestContent, scrollArray);

		BossStructureHook = (new ChestGenHooks("TragicMC.BossStructure", bossStructureContent, 3, 7));
		NetherStructureHook = (new ChestGenHooks("TragicMC.NetherBossStructure", netherStructureContent, 5, 8));
		LameChestHook = (new ChestGenHooks("TragicMC.LameChest", lameChestContent, 3, 6));
		AwesomeChestHook = (new ChestGenHooks("TragicMC.AwesomeChest", awesomeChestContent, 6, 10));

		if (TragicConfig.allowCooldownDefuse)
		{
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.cooldownDefuseRarity));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.cooldownDefuseRarity));
		}

		if (TragicConfig.shouldDoomLimitIncrease)
		{
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicConfig.doomConsumeRarity));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicConfig.doomConsumeRarity));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicConfig.doomConsumeRarity));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicConfig.doomConsumeRarity));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicConfig.doomConsumeRarity));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(DoomConsume, 1), 0, 1, TragicConfig.doomConsumeRarity));
		}

		if (TragicConfig.shouldUnlockAmuletSlots)
		{
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 0, 1, TragicConfig.amuletReleaseRarity));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 0, 1, TragicConfig.amuletReleaseRarity));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 0, 1, TragicConfig.amuletReleaseRarity));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 0, 1, TragicConfig.amuletReleaseRarity));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 0, 1, TragicConfig.amuletReleaseRarity));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 0, 1, TragicConfig.amuletReleaseRarity));
		}

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
		for (int i = 0; i < 5; i++) OreDictionary.registerOre("fish", new ItemStack(Items.fish, 1, i));
		OreDictionary.registerOre("fish", Tentacle);

		OreDictionary.registerOre("oreCharms", RubyCharm);
		OreDictionary.registerOre("oreCharms", SapphireCharm);
		OreDictionary.registerOre("oreCharms", DiamondCharm);
		OreDictionary.registerOre("oreCharms", EmeraldCharm);

		OreDictionary.registerOre("celestialSteelDrops", TimeEssence);
		OreDictionary.registerOre("celestialSteelDrops", LivingClay);
		OreDictionary.registerOre("celestialSteelDrops", LightParticles);
		OreDictionary.registerOre("celestialSteelDrops", StarPieces);
		OreDictionary.registerOre("celestialSteelDrops", PureDarkness);
		OreDictionary.registerOre("celestialSteelDrops", EmpariahClaw);
		OreDictionary.registerOre("celestialSteelDrops", KitsuneTail);
		OreDictionary.registerOre("celestialSteelDrops", DeathlyHallow);
	}

	public static void initializeChallengeItem()
	{
		//Challenge item registration
		ChallengeScroll = (new ItemChallenge().setTextureName("tragicmc:ChallengeInProgress_lowRes"));
		GameRegistry.registerItem(ChallengeScroll, "challengeScroll");

		BossStructureHook.addItem(new WeightedRandomChestContent(new ItemStack(ChallengeScroll), 0, 1, 5));
		NetherStructureHook.addItem(new WeightedRandomChestContent(new ItemStack(ChallengeScroll), 0, 1, 15));
		AwesomeChestHook.addItem(new WeightedRandomChestContent(new ItemStack(ChallengeScroll), 0, 1, 25));
	}

}
