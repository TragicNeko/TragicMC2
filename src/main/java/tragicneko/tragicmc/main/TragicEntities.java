package tragicneko.tragicmc.main;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.util.EnumHelper;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityDarkCrystal;
import tragicneko.tragicmc.entity.EntityStatue;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityGreaterStin;
import tragicneko.tragicmc.entity.boss.EntityJarra;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityKragul;
import tragicneko.tragicmc.entity.boss.EntityMagmox;
import tragicneko.tragicmc.entity.boss.EntityMegaCryse;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityStinKing;
import tragicneko.tragicmc.entity.boss.EntityStinQueen;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityDeathReaperClone;
import tragicneko.tragicmc.entity.mob.EntityErkel;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityMinotaur;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.entity.mob.EntitySirv;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.entity.projectile.EntityBanana;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.entity.projectile.EntityDarkLightning;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.entity.projectile.EntityPitchBlack;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.entity.projectile.EntitySpore;
import tragicneko.tragicmc.entity.projectile.EntityStarShard;
import tragicneko.tragicmc.entity.projectile.EntityThrowingRock;
import tragicneko.tragicmc.entity.projectile.EntityTimeBomb;
import tragicneko.tragicmc.entity.projectile.EntityTimeDisruption;
import tragicneko.tragicmc.main.TragicEntityList.EnumEggType;
import cpw.mods.fml.common.registry.EntityRegistry;

public class TragicEntities {

	public static final EnumCreatureAttribute Natural = EnumHelper.addCreatureAttribute("NATURAL");
	public static final EnumCreatureAttribute Beast = EnumHelper.addCreatureAttribute("BEAST");

	public static void load()
	{
		int id = 0;
		int listid = 0;
		int color = 0x000000;
		int color2 = 0x000000;

		if (TragicNewConfig.allowJabba)
		{
			EntityRegistry.registerModEntity(EntityJabba.class, "Jabba", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityJabba.class, TragicNewConfig.jabbaSC, 0, 2, EnumCreatureType.monster, BiomeGenBase.hell,
					BiomeGenBase.desert,
					BiomeGenBase.desertHills,
					BiomeGenBase.mesa,
					BiomeGenBase.mesaPlateau,
					BiomeGenBase.mesaPlateau_F
					);
			TragicEntityList.addMapping(EntityJabba.class, "TragicMC.Jabba", id++, 0xDA3600, 0xFF961D);
		}

		if (TragicNewConfig.allowPlague)
		{
			EntityRegistry.registerModEntity(EntityPlague.class, "Plague", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityPlague.class, TragicNewConfig.plagueSC, 2, 3, EnumCreatureType.monster, BiomeGenBase.beach,
					BiomeGenBase.birchForest,
					BiomeGenBase.birchForestHills,
					BiomeGenBase.coldBeach,
					BiomeGenBase.coldTaiga,
					BiomeGenBase.coldTaigaHills,
					BiomeGenBase.deepOcean,
					BiomeGenBase.desert,
					BiomeGenBase.desertHills,
					BiomeGenBase.extremeHills,
					BiomeGenBase.extremeHillsEdge,
					BiomeGenBase.extremeHillsPlus,
					BiomeGenBase.forest,
					BiomeGenBase.forestHills,
					BiomeGenBase.frozenOcean,
					BiomeGenBase.frozenRiver,
					BiomeGenBase.hell,
					BiomeGenBase.iceMountains,
					BiomeGenBase.icePlains,
					BiomeGenBase.jungle,
					BiomeGenBase.jungleEdge,
					BiomeGenBase.jungleHills,
					BiomeGenBase.megaTaiga,
					BiomeGenBase.megaTaigaHills,
					BiomeGenBase.mesa,
					BiomeGenBase.mesaPlateau,
					BiomeGenBase.mesaPlateau_F,
					BiomeGenBase.mushroomIsland,
					BiomeGenBase.mushroomIslandShore,
					BiomeGenBase.ocean,
					BiomeGenBase.plains,
					BiomeGenBase.river,
					BiomeGenBase.roofedForest,
					BiomeGenBase.savanna,
					BiomeGenBase.savannaPlateau,
					BiomeGenBase.sky,
					BiomeGenBase.stoneBeach,
					BiomeGenBase.swampland,
					BiomeGenBase.taiga,
					BiomeGenBase.taigaHills
					);
			TragicEntityList.addMapping(EntityPlague.class, "TragicMC.Plague", id++, 0x121212, 0x121212);
		}

		if (TragicNewConfig.allowGragul)
		{
			EntityRegistry.registerModEntity(EntityGragul.class, "Gragul", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityGragul.class, TragicNewConfig.gragulSC, 0, 2, EnumCreatureType.monster, BiomeGenBase.desertHills,
					BiomeGenBase.mesaPlateau,
					BiomeGenBase.mesaPlateau_F,
					BiomeGenBase.extremeHills,
					BiomeGenBase.extremeHillsPlus,
					BiomeGenBase.megaTaiga,
					BiomeGenBase.megaTaigaHills,
					BiomeGenBase.roofedForest,
					BiomeGenBase.swampland,
					BiomeGenBase.mushroomIsland,
					BiomeGenBase.mushroomIslandShore
					);
			TragicEntityList.addMapping(EntityGragul.class, "TragicMC.Gragul", id++, 0x777777, 0xAAAAAA);
		}

		if (TragicNewConfig.allowMinotaur)
		{
			EntityRegistry.registerModEntity(EntityMinotaur.class, "Minotaur", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityMinotaur.class, TragicNewConfig.minotaurSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.plains,
					BiomeGenBase.savanna,
					BiomeGenBase.savannaPlateau,
					BiomeGenBase.forest,
					BiomeGenBase.forestHills,
					BiomeGenBase.birchForest,
					BiomeGenBase.birchForestHills,
					BiomeGenBase.mesa,
					BiomeGenBase.mesaPlateau,
					BiomeGenBase.mesaPlateau_F,
					BiomeGenBase.extremeHills,
					BiomeGenBase.extremeHillsEdge,
					BiomeGenBase.extremeHillsPlus
					);
			TragicEntityList.addMapping(EntityMinotaur.class, "TragicMC.Minotaur", id++, 0x683C1F, 0x351F10);
			DungeonHooks.addDungeonMob("Minotaur", 50);
		}

		if (TragicNewConfig.allowInkling)
		{
			EntityRegistry.registerModEntity(EntityInkling.class, "Inkling", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityInkling.class, TragicNewConfig.inklingSC, 2, 5, EnumCreatureType.monster, BiomeGenBase.beach,
					BiomeGenBase.birchForest,
					BiomeGenBase.birchForestHills,
					BiomeGenBase.coldBeach,
					BiomeGenBase.coldTaiga,
					BiomeGenBase.coldTaigaHills,
					BiomeGenBase.deepOcean,
					BiomeGenBase.desert,
					BiomeGenBase.desertHills,
					BiomeGenBase.extremeHills,
					BiomeGenBase.extremeHillsEdge,
					BiomeGenBase.extremeHillsPlus,
					BiomeGenBase.forest,
					BiomeGenBase.forestHills,
					BiomeGenBase.frozenOcean,
					BiomeGenBase.frozenRiver,
					BiomeGenBase.hell,
					BiomeGenBase.iceMountains,
					BiomeGenBase.icePlains,
					BiomeGenBase.jungle,
					BiomeGenBase.jungleEdge,
					BiomeGenBase.jungleHills,
					BiomeGenBase.megaTaiga,
					BiomeGenBase.megaTaigaHills,
					BiomeGenBase.mesa,
					BiomeGenBase.mesaPlateau,
					BiomeGenBase.mesaPlateau_F,
					BiomeGenBase.mushroomIsland,
					BiomeGenBase.mushroomIslandShore,
					BiomeGenBase.ocean,
					BiomeGenBase.plains,
					BiomeGenBase.river,
					BiomeGenBase.roofedForest,
					BiomeGenBase.savanna,
					BiomeGenBase.savannaPlateau,
					BiomeGenBase.sky,
					BiomeGenBase.stoneBeach,
					BiomeGenBase.swampland,
					BiomeGenBase.taiga,
					BiomeGenBase.taigaHills
					);
			TragicEntityList.addMapping(EntityInkling.class, "TragicMC.Inkling", id++, 0x222222, 0x333333);
		}

		if (TragicNewConfig.allowRagr)
		{
			EntityRegistry.registerModEntity(EntityRagr.class, "Ragr", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityRagr.class, TragicNewConfig.ragrSC, 0, 1, EnumCreatureType.monster, 
					BiomeGenBase.taiga,
					BiomeGenBase.taigaHills,
					BiomeGenBase.coldTaiga,
					BiomeGenBase.coldTaigaHills,
					BiomeGenBase.icePlains,
					BiomeGenBase.iceMountains
					);
			TragicEntityList.addMapping(EntityRagr.class, "TragicMC.Ragr", id++, 0x94C3D9, 0x406779);
			DungeonHooks.addDungeonMob("Ragr", 25);
		}

		if (TragicNewConfig.allowPumpkinhead)
		{
			EntityRegistry.registerModEntity(EntityPumpkinhead.class, "Pumpkinhead", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityPumpkinhead.class, TragicNewConfig.pumpkinheadSC, 2, 5, EnumCreatureType.monster, BiomeGenBase.beach,
					BiomeGenBase.birchForest,
					BiomeGenBase.birchForestHills,
					BiomeGenBase.coldBeach,
					BiomeGenBase.coldTaiga,
					BiomeGenBase.coldTaigaHills,
					BiomeGenBase.deepOcean,
					BiomeGenBase.desert,
					BiomeGenBase.desertHills,
					BiomeGenBase.extremeHills,
					BiomeGenBase.extremeHillsEdge,
					BiomeGenBase.extremeHillsPlus,
					BiomeGenBase.forest,
					BiomeGenBase.forestHills,
					BiomeGenBase.frozenOcean,
					BiomeGenBase.frozenRiver,
					BiomeGenBase.hell,
					BiomeGenBase.iceMountains,
					BiomeGenBase.icePlains,
					BiomeGenBase.jungle,
					BiomeGenBase.jungleEdge,
					BiomeGenBase.jungleHills,
					BiomeGenBase.megaTaiga,
					BiomeGenBase.megaTaigaHills,
					BiomeGenBase.mesa,
					BiomeGenBase.mesaPlateau,
					BiomeGenBase.mesaPlateau_F,
					BiomeGenBase.mushroomIsland,
					BiomeGenBase.mushroomIslandShore,
					BiomeGenBase.ocean,
					BiomeGenBase.plains,
					BiomeGenBase.river,
					BiomeGenBase.roofedForest,
					BiomeGenBase.savanna,
					BiomeGenBase.savannaPlateau,
					BiomeGenBase.sky,
					BiomeGenBase.stoneBeach,
					BiomeGenBase.swampland,
					BiomeGenBase.taiga,
					BiomeGenBase.taigaHills
					);
			TragicEntityList.addMapping(EntityPumpkinhead.class, "TragicMC.Pumpkinhead", id++, 0xFD9229, 0x333333);
		}

		if (TragicNewConfig.allowTragicNeko)
		{
			EntityRegistry.registerModEntity(EntityTragicNeko.class, "TragicNeko", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntityTragicNeko.class, "TragicMC.TragicNeko", id++, 0x373535, 0x853B3B);
		}

		if (TragicNewConfig.allowTox)
		{
			EntityRegistry.registerModEntity(EntityTox.class, "Tox", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityTox.class, TragicNewConfig.toxSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.roofedForest,
					BiomeGenBase.forest,
					BiomeGenBase.forestHills,
					BiomeGenBase.birchForest,
					BiomeGenBase.birchForestHills,
					BiomeGenBase.jungle,
					BiomeGenBase.jungleHills
					);
			TragicEntityList.addMapping(EntityTox.class, "TragicMC.Tox", id++, 0xDACF18, 0x15A915);
		}

		if (TragicNewConfig.allowCryse)
		{
			EntityRegistry.registerModEntity(EntityCryse.class, "Cryse", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityCryse.class, TragicNewConfig.cryseSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.icePlains,
					BiomeGenBase.iceMountains,
					BiomeGenBase.coldTaiga,
					BiomeGenBase.coldTaigaHills
					);
			TragicEntityList.addMapping(EntityCryse.class, "TragicMC.Cryse", id++, 0xCEE3E3, 0xFFFFFF);
		}

		if (TragicNewConfig.allowNorVox)
		{
			EntityRegistry.registerModEntity(EntityNorVox.class, "NorVox", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityNorVox.class, TragicNewConfig.norVoxSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.birchForest,
					BiomeGenBase.birchForestHills,
					BiomeGenBase.deepOcean,
					BiomeGenBase.extremeHills,
					BiomeGenBase.extremeHillsEdge,
					BiomeGenBase.extremeHillsPlus,
					BiomeGenBase.forest,
					BiomeGenBase.forestHills,
					BiomeGenBase.jungle,
					BiomeGenBase.jungleEdge,
					BiomeGenBase.jungleHills,
					BiomeGenBase.megaTaiga,
					BiomeGenBase.megaTaigaHills,
					BiomeGenBase.mesa,
					BiomeGenBase.mesaPlateau,
					BiomeGenBase.mesaPlateau_F,
					BiomeGenBase.mushroomIsland,
					BiomeGenBase.mushroomIslandShore,
					BiomeGenBase.ocean,
					BiomeGenBase.plains,
					BiomeGenBase.river,
					BiomeGenBase.roofedForest,
					BiomeGenBase.savanna,
					BiomeGenBase.savannaPlateau,
					BiomeGenBase.stoneBeach,
					BiomeGenBase.swampland,
					BiomeGenBase.taiga,
					BiomeGenBase.taigaHills
					);
			TragicEntityList.addMapping(EntityNorVox.class, "TragicMC.NorVox", id++, 0x000000, 0x565656);
		}

		if (TragicNewConfig.allowPirah)
		{
			EntityRegistry.registerModEntity(EntityPirah.class, "Pirah", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityPirah.class, TragicNewConfig.pirahSC, 2, 6, EnumCreatureType.waterCreature, BiomeGenBase.deepOcean,
					BiomeGenBase.ocean,
					BiomeGenBase.river
					);
			TragicEntityList.addMapping(EntityPirah.class, "TragicMC.Pirah", id++, 0x69A2FF, 0xFF6666);
		}
		
		if (TragicNewConfig.allowStin)
		{
			EntityRegistry.registerModEntity(EntityStin.class, "Stin", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntityStin.class, "TragicMC.Stin", id++, 0x676767, 0x454545);
		}

		if (TragicNewConfig.allowWisp)
		{
			EntityRegistry.registerModEntity(EntityWisp.class, "Wisp", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntityWisp.class, "TragicMC.Wisp", id++, 0xFF2323, 0xCB6B4B);
		}

		if (TragicNewConfig.allowAbomination)
		{
			EntityRegistry.registerModEntity(EntityAbomination.class, "Abomination", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityAbomination.class, TragicNewConfig.abominationSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.icePlains,
					BiomeGenBase.iceMountains,
					BiomeGenBase.frozenOcean,
					BiomeGenBase.frozenRiver,
					BiomeGenBase.coldBeach,
					BiomeGenBase.coldTaiga,
					BiomeGenBase.coldTaigaHills
					);
			TragicEntityList.addMapping(EntityAbomination.class, "TragicMC.Abomination", id++, 0xCDCDCD, 0xA9AFB7);
		}
		
		if (TragicNewConfig.allowErkel)
		{
			EntityRegistry.registerModEntity(EntityErkel.class, "Erkel", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntityErkel.class, "TragicMC.Erkel", id++, 0x4D935D, 0x30663D);
		}
		
		if (TragicNewConfig.allowSirv)
		{
			EntityRegistry.registerModEntity(EntitySirv.class, "Sirv", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntitySirv.class, "TragicMC.Sirv", id++, 0xADADAD, 0xBDBDBD);
		}

		//Iron Golem
		TragicEntityList.addMapping(EntityIronGolem.class, "TragicMC.IronGolem", id++, 0xDBCDC1, 0x8B7260);

		//Added snow golem to ice biomes
		EntityRegistry.addSpawn(EntitySnowman.class, 40, 0, 2, EnumCreatureType.creature, BiomeGenBase.icePlains, BiomeGenBase.iceMountains,
				BiomeGenBase.frozenOcean,
				BiomeGenBase.frozenRiver,
				BiomeGenBase.coldBeach,
				BiomeGenBase.coldTaiga,
				BiomeGenBase.coldTaigaHills
				);
		TragicEntityList.addMapping(EntitySnowman.class, "TragicMC.SnowGolem", id++, 0xFFFDF1, 0xABA290);
		
		//Mini-Bosses
		if (TragicNewConfig.allowJarra)
		{
			EntityRegistry.registerModEntity(EntityJarra.class, "Jarra", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityJarra.class, TragicNewConfig.jarraSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.hell,
					BiomeGenBase.desert,
					BiomeGenBase.desertHills,
					BiomeGenBase.mesa,
					BiomeGenBase.mesaPlateau,
					BiomeGenBase.mesaPlateau_F
					);
			TragicEntityList.addMapping(EntityJarra.class, "TragicMC.Jarra", id++, 0x77329B, 0xC457FD, EnumEggType.MINIBOSS);
		}
		
		if (TragicNewConfig.allowKragul)
		{
			EntityRegistry.registerModEntity(EntityKragul.class, "Kragul", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityKragul.class, TragicNewConfig.kragulSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.desertHills,
					BiomeGenBase.mesaPlateau,
					BiomeGenBase.mesaPlateau_F,
					BiomeGenBase.extremeHills,
					BiomeGenBase.extremeHillsPlus,
					BiomeGenBase.megaTaiga,
					BiomeGenBase.megaTaigaHills,
					BiomeGenBase.roofedForest,
					BiomeGenBase.swampland,
					BiomeGenBase.mushroomIsland,
					BiomeGenBase.mushroomIslandShore
					);
			TragicEntityList.addMapping(EntityKragul.class, "TragicMC.Kragul", id++, 0xDE3C31, 0x747474, EnumEggType.MINIBOSS);
		}
		
		if (TragicNewConfig.allowMagmox)
		{
			EntityRegistry.registerModEntity(EntityMagmox.class, "Magmox", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityMagmox.class, TragicNewConfig.magmoxSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.hell);
			TragicEntityList.addMapping(EntityMagmox.class, "TragicMC.Magmox", id++, 0xC20000, 0x550000, EnumEggType.MINIBOSS);
		}
		
		if (TragicNewConfig.allowMegaCryse)
		{
			EntityRegistry.registerModEntity(EntityMegaCryse.class, "MegaCryse", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityMegaCryse.class, TragicNewConfig.megaCryseSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.icePlains,
					BiomeGenBase.iceMountains,
					BiomeGenBase.coldTaiga,
					BiomeGenBase.coldTaigaHills
					);
			TragicEntityList.addMapping(EntityMegaCryse.class, "TragicMC.MegaCryse", id++, 0xDADADA, 0xB9BFC7, EnumEggType.MINIBOSS);
		}
		
		if (TragicNewConfig.allowGreaterStin)
		{
			EntityRegistry.registerModEntity(EntityGreaterStin.class, "GreaterStin", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntityGreaterStin.class, "TragicMC.GreaterStin", id++, 0x454545, 0x383838, EnumEggType.MINIBOSS);
		} 

		if (TragicNewConfig.allowStinKing)
		{
			EntityRegistry.registerModEntity(EntityStinKing.class, "StinKing", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntityStinKing.class, "TragicMC.StinKing", id++, 0x754545, 0x483838, EnumEggType.MINIBOSS);
		}

		if (TragicNewConfig.allowStinQueen)
		{
			EntityRegistry.registerModEntity(EntityStinQueen.class, "StinQueen", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntityStinQueen.class, "TragicMC.StinQueen", id++, 0x232323, 0x767676, EnumEggType.MINIBOSS);
		}
		
		if (TragicNewConfig.allowVoxStellarum)
		{
			EntityRegistry.registerModEntity(EntityVoxStellarum.class, "VoxStellarum", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntityVoxStellarum.class, "TragicMC.VoxStellarum", id++, 0xFDC169, 0xFD3C69, EnumEggType.MINIBOSS);
		}
		
		//Bosses

		//Wither
		TragicEntityList.addMapping(EntityWither.class, "TragicMC.Wither", id++, 0x1C1C1C, 0x252525, EnumEggType.BOSS);

		//Ender Dragon
		TragicEntityList.addMapping(EntityDragon.class, "TragicMC.EnderDragon", id++, 0x1A1A1A, 0xCC00FA, EnumEggType.BOSS);

		if (TragicNewConfig.allowApis)
		{
			EntityRegistry.registerModEntity(EntityApis.class, "Apis", listid++, TragicMC.instance, 80, 1, true);

			if (TragicNewConfig.allowBossNaturalSpawns)
			{
				EntityRegistry.addSpawn(EntityApis.class, TragicNewConfig.apisSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.plains,
						BiomeGenBase.savanna,
						BiomeGenBase.savannaPlateau,
						BiomeGenBase.forest,
						BiomeGenBase.forestHills,
						BiomeGenBase.birchForest,
						BiomeGenBase.birchForestHills,
						BiomeGenBase.mesa,
						BiomeGenBase.mesaPlateau,
						BiomeGenBase.mesaPlateau_F,
						BiomeGenBase.extremeHills,
						BiomeGenBase.extremeHillsEdge,
						BiomeGenBase.extremeHillsPlus
						);
			}
			TragicEntityList.addMapping(EntityApis.class, "TragicMC.Apis", id++, 0xEDAC4F, 0xED854F, EnumEggType.BOSS);
		} 

		if (TragicNewConfig.allowDeathReaper)
		{
			EntityRegistry.registerModEntity(EntityDeathReaper.class, "DeathReaper", listid++, TragicMC.instance, 80, 1, true);

			if (TragicNewConfig.allowBossNaturalSpawns)
			{
				EntityRegistry.addSpawn(EntityDeathReaper.class, TragicNewConfig.deathReaperSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.forest,
						BiomeGenBase.forestHills,
						BiomeGenBase.birchForest,
						BiomeGenBase.birchForestHills
						);
			}
			TragicEntityList.addMapping(EntityDeathReaper.class, "TragicMC.DeathReaper", id++, 0xCFCCB4, 0x553131, EnumEggType.BOSS);
			EntityRegistry.registerModEntity(EntityDeathReaperClone.class, "DeathReaperClone", listid++, TragicMC.instance, 80, 1, true);
		}

		if (TragicNewConfig.allowKitsune)
		{
			EntityRegistry.registerModEntity(EntityKitsune.class, "Kitsune", listid++, TragicMC.instance, 80, 1, true);
			if (TragicNewConfig.allowBossNaturalSpawns)
			{
				EntityRegistry.addSpawn(EntityKitsune.class, TragicNewConfig.kitsuneSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.hell);
			}
			TragicEntityList.addMapping(EntityKitsune.class, "TragicMC.Kitsune", id++, 0xFF0000, 0xFFD087, EnumEggType.BOSS);
		}

		if (TragicNewConfig.allowPolaris)
		{
			EntityRegistry.registerModEntity(EntityPolaris.class, "Polaris", listid++, TragicMC.instance, 80, 1, true);

			if (TragicNewConfig.allowBossNaturalSpawns)
			{
				EntityRegistry.addSpawn(EntityPolaris.class, TragicNewConfig.polarisSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.desert,
						BiomeGenBase.desertHills
						);
			}
			TragicEntityList.addMapping(EntityPolaris.class, "TragicMC.Polaris", id++, 0x4A00BA, 0x000000, EnumEggType.BOSS);
		}

		if (TragicNewConfig.allowYeti)
		{
			EntityRegistry.registerModEntity(EntityYeti.class, "Yeti", listid++, TragicMC.instance, 80, 1, true);

			if (TragicNewConfig.allowBossNaturalSpawns)
			{
				EntityRegistry.addSpawn(EntityYeti.class, TragicNewConfig.yetiSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.icePlains,
						BiomeGenBase.iceMountains
						);
			}
			TragicEntityList.addMapping(EntityYeti.class, "TragicMC.Yeti", id++, 0xDADADA, 0xB9BFC7, EnumEggType.BOSS);
		}

		if (TragicNewConfig.allowTimeController)
		{
			EntityRegistry.registerModEntity(EntityTimeController.class, "TimeController", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntityTimeController.class, "TragicMC.TimeController", id++, 0x94FFA3, 0xEA92E9, EnumEggType.BOSS);
		}
		
		if (TragicNewConfig.allowEnyvil)
		{
			EntityRegistry.registerModEntity(EntityEnyvil.class, "Enyvil", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntityEnyvil.class, "TragicMC.Enyvil", id++, 0x5D1543, 0xFF6FFF, EnumEggType.BOSS);
		}
		
		if (TragicNewConfig.allowClaymation)
		{
			EntityRegistry.registerModEntity(EntityClaymation.class, "Claymation", listid++, TragicMC.instance, 80, 1, true);
			TragicEntityList.addMapping(EntityClaymation.class, "TragicMC.Claymation", id++, 0xFF9500, 0xFFCA02, EnumEggType.BOSS);
		}

		EntityRegistry.registerModEntity(EntityThrowingRock.class, "ThrowingRock", listid++, TragicMC.instance, 80, 10, true);
		EntityRegistry.registerModEntity(EntityPumpkinbomb.class, "Pumpkinbomb", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntityLargePumpkinbomb.class, "LargePumpkinbomb", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntityPoisonBarb.class, "PoisonBarb", listid++, TragicMC.instance, 80, 5, true);

		EntityRegistry.registerModEntity(EntityNekoRocket.class, "NekoRocket", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntityNekoStickyBomb.class, "NekoStickyBomb", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntityNekoClusterBomb.class, "NekoClusterBomb", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntityNekoMiniBomb.class, "NekoMiniBomb", listid++, TragicMC.instance, 80, 5, true);

		EntityRegistry.registerModEntity(EntitySolarBomb.class, "SolarBomb", listid++, TragicMC.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntitySpiritCast.class, "SpiritCast", listid++, TragicMC.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntitySpore.class, "Spore", listid++, TragicMC.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBanana.class, "Banana", listid++, TragicMC.instance, 80, 5, true);

		EntityRegistry.registerModEntity(EntityTimeBomb.class, "TimeBomb", listid++, TragicMC.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityTimeDisruption.class, "TimeDisruption", listid++, TragicMC.instance, 80, 3, true);

		EntityRegistry.registerModEntity(EntityLargeRock.class, "LargeRock", listid++, TragicMC.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityIcicle.class, "Icicle", listid++, TragicMC.instance, 80, 3, true);

		EntityRegistry.registerModEntity(EntityStatue.class, "Statue", listid++, TragicMC.instance, 80, 3, false);

		EntityRegistry.registerModEntity(EntityStarShard.class, "StarShard", listid++, TragicMC.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityDarkLightning.class, "DarkLightning", listid++, TragicMC.instance, 80, 3, true);
		
		EntityRegistry.registerModEntity(EntityPitchBlack.class, "PitchBlack", listid++, TragicMC.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityDarkEnergy.class, "DarkEnergy", listid++, TragicMC.instance, 80, 3, true);
		
		EntityRegistry.registerModEntity(EntityDarkCrystal.class, "DarkCrystal", listid++, TragicMC.instance, 80, 3, true);
	}
}
