package tragicneko.tragicmc;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.util.EnumHelper;
import tragicneko.tragicmc.entity.EntityDarkCrystal;
import tragicneko.tragicmc.entity.EntityDimensionalAnomaly;
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.entity.EntityKurayami;
import tragicneko.tragicmc.entity.EntityLock;
import tragicneko.tragicmc.entity.EntityNuke;
import tragicneko.tragicmc.entity.EntityStatue;
import tragicneko.tragicmc.entity.EntityTimeDisruption;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCocoon;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.miniboss.EntityAegar;
import tragicneko.tragicmc.entity.miniboss.EntityGreaterStin;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.miniboss.EntityMegaCryse;
import tragicneko.tragicmc.entity.miniboss.EntityStinKing;
import tragicneko.tragicmc.entity.miniboss.EntityStinQueen;
import tragicneko.tragicmc.entity.miniboss.EntityVolatileFusea;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityArchangel;
import tragicneko.tragicmc.entity.mob.EntityAvris;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityErkel;
import tragicneko.tragicmc.entity.mob.EntityFusea;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityHarvester;
import tragicneko.tragicmc.entity.mob.EntityHunter;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityIre;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityLockbot;
import tragicneko.tragicmc.entity.mob.EntityMinotaur;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.entity.mob.EntityParasmite;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityPsygote;
import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.entity.mob.EntityRanmas;
import tragicneko.tragicmc.entity.mob.EntitySeeker;
import tragicneko.tragicmc.entity.mob.EntitySirv;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.entity.projectile.EntityBanana;
import tragicneko.tragicmc.entity.projectile.EntityCrystalMortor;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.entity.projectile.EntityDarkLightning;
import tragicneko.tragicmc.entity.projectile.EntityDarkMortor;
import tragicneko.tragicmc.entity.projectile.EntityGuardianShield;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntityIreEnergy;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.entity.projectile.EntityOverlordMortor;
import tragicneko.tragicmc.entity.projectile.EntityPitchBlack;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.entity.projectile.EntitySpore;
import tragicneko.tragicmc.entity.projectile.EntityStarShard;
import tragicneko.tragicmc.entity.projectile.EntityThrowingRock;
import tragicneko.tragicmc.entity.projectile.EntityTimeBomb;
import tragicneko.tragicmc.entity.projectile.EntityWebBomb;
import tragicneko.tragicmc.util.TragicEntityList;
import tragicneko.tragicmc.util.TragicEntityList.EnumEggType;
import cpw.mods.fml.common.registry.EntityRegistry;

public class TragicEntities {

	public static final EnumCreatureAttribute Natural = EnumHelper.addCreatureAttribute("NATURAL");
	public static final EnumCreatureAttribute Beast = EnumHelper.addCreatureAttribute("BEAST");
	public static final EnumCreatureAttribute Synapse = EnumHelper.addCreatureAttribute("SYNAPSE");

	public static void load()
	{
		int id = 0;
		int listid = 0;

		if (TragicConfig.allowJabba)
		{
			EntityRegistry.registerModEntity(EntityJabba.class, "Jabba", listid++, TragicMC.getInstance(), 80, 1, true);

			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityJabba.class, TragicConfig.jabbaSC, 0, 2, EnumCreatureType.monster, BiomeGenBase.hell,
						BiomeGenBase.desert,
						BiomeGenBase.desertHills,
						BiomeGenBase.mesa,
						BiomeGenBase.mesaPlateau,
						BiomeGenBase.mesaPlateau_F
						);
			}
			TragicEntityList.addMapping(EntityJabba.class, "TragicMC.Jabba", id++, 0xDA3600, 0xFF961D);
		}

		if (TragicConfig.allowPlague)
		{
			EntityRegistry.registerModEntity(EntityPlague.class, "Plague", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityPlague.class, TragicConfig.plagueSC, 2, 3, EnumCreatureType.monster, BiomeGenBase.beach,
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
			}
			TragicEntityList.addMapping(EntityPlague.class, "TragicMC.Plague", id++, 0x121212, 0x121212);
		}

		if (TragicConfig.allowGragul)
		{
			EntityRegistry.registerModEntity(EntityGragul.class, "Gragul", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityGragul.class, TragicConfig.gragulSC, 0, 2, EnumCreatureType.monster, BiomeGenBase.desertHills,
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
			}
			TragicEntityList.addMapping(EntityGragul.class, "TragicMC.Gragul", id++, 0x777777, 0xAAAAAA);
		}

		if (TragicConfig.allowMinotaur)
		{
			EntityRegistry.registerModEntity(EntityMinotaur.class, "Minotaur", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityMinotaur.class, TragicConfig.minotaurSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.plains,
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
			TragicEntityList.addMapping(EntityMinotaur.class, "TragicMC.Minotaur", id++, 0x683C1F, 0x351F10);
			DungeonHooks.addDungeonMob("Minotaur", 50);
		}

		if (TragicConfig.allowInkling)
		{
			EntityRegistry.registerModEntity(EntityInkling.class, "Inkling", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityInkling.class, TragicConfig.inklingSC, 2, 5, EnumCreatureType.monster, BiomeGenBase.beach,
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
						BiomeGenBase.stoneBeach,
						BiomeGenBase.swampland,
						BiomeGenBase.taiga,
						BiomeGenBase.taigaHills
						);
			}
			TragicEntityList.addMapping(EntityInkling.class, "TragicMC.Inkling", id++, 0x222222, 0x333333);
		}

		if (TragicConfig.allowRagr)
		{
			EntityRegistry.registerModEntity(EntityRagr.class, "Ragr", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityRagr.class, TragicConfig.ragrSC, 0, 1, EnumCreatureType.monster,
						BiomeGenBase.taiga,
						BiomeGenBase.taigaHills,
						BiomeGenBase.coldTaiga,
						BiomeGenBase.coldTaigaHills,
						BiomeGenBase.icePlains,
						BiomeGenBase.iceMountains
						);
			}
			TragicEntityList.addMapping(EntityRagr.class, "TragicMC.Ragr", id++, 0x94C3D9, 0x406779);
			DungeonHooks.addDungeonMob("Ragr", 25);
		}

		if (TragicConfig.allowPumpkinhead)
		{
			EntityRegistry.registerModEntity(EntityPumpkinhead.class, "Pumpkinhead", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityPumpkinhead.class, TragicConfig.pumpkinheadSC, 2, 5, EnumCreatureType.monster,
						BiomeGenBase.birchForest,
						BiomeGenBase.birchForestHills,
						BiomeGenBase.forest,
						BiomeGenBase.forestHills,
						BiomeGenBase.megaTaiga,
						BiomeGenBase.megaTaigaHills,
						BiomeGenBase.mushroomIsland,
						BiomeGenBase.mushroomIslandShore,
						BiomeGenBase.plains,
						BiomeGenBase.roofedForest,
						BiomeGenBase.savanna,
						BiomeGenBase.savannaPlateau,
						BiomeGenBase.taiga,
						BiomeGenBase.taigaHills
						);
			}
			TragicEntityList.addMapping(EntityPumpkinhead.class, "TragicMC.Pumpkinhead", id++, 0xFD9229, 0x333333);
		}

		if (TragicConfig.allowTragicNeko)
		{
			EntityRegistry.registerModEntity(EntityTragicNeko.class, "TragicNeko", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityTragicNeko.class, "TragicMC.TragicNeko", id++, 0x373535, 0x853B3B);
		}

		if (TragicConfig.allowTox)
		{
			EntityRegistry.registerModEntity(EntityTox.class, "Tox", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityTox.class, TragicConfig.toxSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.roofedForest,
						BiomeGenBase.forest,
						BiomeGenBase.forestHills,
						BiomeGenBase.birchForest,
						BiomeGenBase.birchForestHills,
						BiomeGenBase.jungle,
						BiomeGenBase.jungleHills
						);
			}
			TragicEntityList.addMapping(EntityTox.class, "TragicMC.Tox", id++, 0xDACF18, 0x15A915);
		}

		if (TragicConfig.allowCryse)
		{
			EntityRegistry.registerModEntity(EntityCryse.class, "Cryse", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityCryse.class, TragicConfig.cryseSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.icePlains,
						BiomeGenBase.iceMountains,
						BiomeGenBase.coldTaiga,
						BiomeGenBase.coldTaigaHills
						);
			}
			TragicEntityList.addMapping(EntityCryse.class, "TragicMC.Cryse", id++, 0xCEE3E3, 0xFFFFFF);
		}

		if (TragicConfig.allowNorVox)
		{
			EntityRegistry.registerModEntity(EntityNorVox.class, "NorVox", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityNorVox.class, TragicConfig.norVoxSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.birchForest,
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
			}
			TragicEntityList.addMapping(EntityNorVox.class, "TragicMC.NorVox", id++, 0x000000, 0x565656);
		}

		if (TragicConfig.allowPirah)
		{
			EntityRegistry.registerModEntity(EntityPirah.class, "Pirah", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityPirah.class, TragicConfig.pirahSC, 2, 6, EnumCreatureType.waterCreature, BiomeGenBase.deepOcean,
						BiomeGenBase.ocean,
						BiomeGenBase.river
						);
			}
			TragicEntityList.addMapping(EntityPirah.class, "TragicMC.Pirah", id++, 0x69A2FF, 0xFF6666);
		}

		if (TragicConfig.allowStin)
		{
			EntityRegistry.registerModEntity(EntityStin.class, "Stin", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityStin.class, "TragicMC.Stin", id++, 0x676767, 0x454545);
		}

		if (TragicConfig.allowWisp)
		{
			EntityRegistry.registerModEntity(EntityWisp.class, "Wisp", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityWisp.class, TragicConfig.wispSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.roofedForest,
						BiomeGenBase.forest,
						BiomeGenBase.forestHills,
						BiomeGenBase.birchForest,
						BiomeGenBase.birchForestHills,
						BiomeGenBase.jungle,
						BiomeGenBase.jungleHills,
						BiomeGenBase.desert,
						BiomeGenBase.desertHills,
						BiomeGenBase.mesa
						);
			}
			TragicEntityList.addMapping(EntityWisp.class, "TragicMC.Wisp", id++, 0xFF2323, 0xCB6B4B);
		}

		if (TragicConfig.allowAbomination)
		{
			EntityRegistry.registerModEntity(EntityAbomination.class, "Abomination", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityAbomination.class, TragicConfig.abominationSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.icePlains,
						BiomeGenBase.iceMountains,
						BiomeGenBase.frozenOcean,
						BiomeGenBase.frozenRiver,
						BiomeGenBase.coldBeach,
						BiomeGenBase.coldTaiga,
						BiomeGenBase.coldTaigaHills
						);
			}
			TragicEntityList.addMapping(EntityAbomination.class, "TragicMC.Abomination", id++, 0xCDCDCD, 0xA9AFB7);
		}

		if (TragicConfig.allowErkel)
		{
			EntityRegistry.registerModEntity(EntityErkel.class, "Erkel", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityTox.class, TragicConfig.toxSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.roofedForest,
						BiomeGenBase.forest,
						BiomeGenBase.forestHills,
						BiomeGenBase.birchForest,
						BiomeGenBase.birchForestHills,
						BiomeGenBase.jungle,
						BiomeGenBase.jungleHills
						);
			}
			TragicEntityList.addMapping(EntityErkel.class, "TragicMC.Erkel", id++, 0x43BD43, 0x30663D);
		}

		if (TragicConfig.allowSirv)
		{
			EntityRegistry.registerModEntity(EntitySirv.class, "Sirv", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntitySirv.class, "TragicMC.Sirv", id++, 0xADADAD, 0xBDBDBD);
		}

		if (TragicConfig.allowPsygote)
		{
			EntityRegistry.registerModEntity(EntityPsygote.class, "Psygote", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityPsygote.class, "TragicMC.Psygote", id++, 0x8965A4, 0x000000);
		}

		if (TragicConfig.allowLockbot)
		{
			EntityRegistry.registerModEntity(EntityLockbot.class, "Lockbot", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityLockbot.class, "TragicMC.Lockbot", id++, 0x121212, 0x60D6D7);
		}

		if (TragicConfig.allowNanoSwarm)
		{
			EntityRegistry.registerModEntity(EntityNanoSwarm.class, "NanoSwarm", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityNanoSwarm.class, "TragicMC.NanoSwarm", id++, 0xFFFFFF, 0xAAAAAA);
		}

		if (TragicConfig.allowHunter)
		{
			EntityRegistry.registerModEntity(EntityHunter.class, "Hunter", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityHunter.class, "TragicMC.Hunter", id++, 0x60D6D7, 0x888888);
		}

		if (TragicConfig.allowHarvester)
		{
			EntityRegistry.registerModEntity(EntityHarvester.class, "Harvester", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityHarvester.class, "TragicMC.Harvester", id++, 0x555555, 0x53BBBC);
		}

		if (TragicConfig.allowOverlord)
		{
			EntityRegistry.registerModEntity(EntitySeeker.class, "Seeker", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntitySeeker.class, "TragicMC.Seeker", id++, 0x53BBBC, 0x464646);
		}

		if (TragicConfig.allowArchangel)
		{
			EntityRegistry.registerModEntity(EntityArchangel.class, "Archangel", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityArchangel.class, "TragicMC.Archangel", id++, 0xFFFCA0, 0xFFFCA0);
		}

		if (TragicConfig.allowIre)
		{
			EntityRegistry.registerModEntity(EntityIre.class, "Ire", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityIre.class, "TragicMC.Ire", id++, 0xFFFFC3, 0xFFFFC3);
		}

		if (TragicConfig.allowFusea)
		{
			EntityRegistry.registerModEntity(EntityFusea.class, "Fusea", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityFusea.class, "TragicMC.Fusea", id++, 0xE4B1E0, 0xA0E39D);
		}

		if (TragicConfig.allowRanmas)
		{
			EntityRegistry.registerModEntity(EntityRanmas.class, "Ranmas", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityRanmas.class, "TragicMC.Ranmas", id++, 0xDCDCDC, 0xCCCCCC);
		}

		if (TragicConfig.allowParasmite)
		{
			EntityRegistry.registerModEntity(EntityParasmite.class, "Parasmite", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityParasmite.class, "TragicMC.Parasmite", id++, 0xAF00A6, 0x581354);
		}
		
		if (TragicConfig.allowAvris)
		{
			EntityRegistry.registerModEntity(EntityAvris.class, "Avris", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityAvris.class, "TragicMC.Avris", id++, 0x000000, 0x000000);
		}

		//Giant Zombie
		TragicEntityList.addMapping(EntityGiantZombie.class, "TragicMC.GiantZombie", id++, 0x43BD98, 0x53DCBC);

		//Iron Golem
		TragicEntityList.addMapping(EntityIronGolem.class, "TragicMC.IronGolem", id++, 0xDBCDC1, 0x8B7260, EnumEggType.PET);

		//Added snow golem to ice biomes
		if (TragicConfig.allowSnowGolem)
		{
			EntityRegistry.addSpawn(EntitySnowman.class, TragicConfig.snowGolemSC, 0, 2, EnumCreatureType.creature, BiomeGenBase.icePlains, BiomeGenBase.iceMountains,
					BiomeGenBase.frozenOcean,
					BiomeGenBase.frozenRiver,
					BiomeGenBase.coldBeach,
					BiomeGenBase.coldTaiga,
					BiomeGenBase.coldTaigaHills
					);
		}
		TragicEntityList.addMapping(EntitySnowman.class, "TragicMC.SnowGolem", id++, 0xFFFDF1, 0xABA290, EnumEggType.PET);

		//Kurayami
		EntityRegistry.registerModEntity(EntityKurayami.class, "Kurayami", listid++, TragicMC.getInstance(), 80, 1, true);
		TragicEntityList.addMapping(EntityKurayami.class, "TragicMC.Kurayami", id++, 0x2222AA, 0x8888FF, EnumEggType.PET);

		//Mini-Bosses
		if (TragicConfig.allowJarra)
		{
			EntityRegistry.registerModEntity(EntityJarra.class, "Jarra", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityJarra.class, TragicConfig.jarraSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.hell,
						BiomeGenBase.desert,
						BiomeGenBase.desertHills,
						BiomeGenBase.mesa,
						BiomeGenBase.mesaPlateau,
						BiomeGenBase.mesaPlateau_F
						);
			}
			TragicEntityList.addMapping(EntityJarra.class, "TragicMC.Jarra", id++, 0x77329B, 0xC457FD, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.allowKragul)
		{
			EntityRegistry.registerModEntity(EntityKragul.class, "Kragul", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityKragul.class, TragicConfig.kragulSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.desertHills,
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
			}
			TragicEntityList.addMapping(EntityKragul.class, "TragicMC.Kragul", id++, 0xDE3C31, 0x747474, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.allowMagmox)
		{
			EntityRegistry.registerModEntity(EntityMagmox.class, "Magmox", listid++, TragicMC.getInstance(), 80, 1, true);
			EntityRegistry.addSpawn(EntityMagmox.class, TragicConfig.magmoxSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.hell);
			TragicEntityList.addMapping(EntityMagmox.class, "TragicMC.Magmox", id++, 0xC20000, 0x550000, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.allowMegaCryse)
		{
			EntityRegistry.registerModEntity(EntityMegaCryse.class, "MegaCryse", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowNonDimensionMobSpawns)
			{
				EntityRegistry.addSpawn(EntityMegaCryse.class, TragicConfig.megaCryseSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.icePlains,
						BiomeGenBase.iceMountains,
						BiomeGenBase.coldTaiga,
						BiomeGenBase.coldTaigaHills
						);
			}
			TragicEntityList.addMapping(EntityMegaCryse.class, "TragicMC.MegaCryse", id++, 0xDADADA, 0xB9BFC7, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.allowGreaterStin)
		{
			EntityRegistry.registerModEntity(EntityGreaterStin.class, "GreaterStin", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityGreaterStin.class, "TragicMC.GreaterStin", id++, 0x454545, 0x383838, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.allowStinKing)
		{
			EntityRegistry.registerModEntity(EntityStinKing.class, "StinKing", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityStinKing.class, "TragicMC.StinKing", id++, 0x754545, 0x483838, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.allowStinQueen)
		{
			EntityRegistry.registerModEntity(EntityStinQueen.class, "StinQueen", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityStinQueen.class, "TragicMC.StinQueen", id++, 0x232323, 0x767676, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.allowVoxStellarum)
		{
			EntityRegistry.registerModEntity(EntityVoxStellarum.class, "VoxStellarum", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityVoxStellarum.class, "TragicMC.VoxStellarum", id++, 0xFDC169, 0xFD3C69, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.allowAegar)
		{
			EntityRegistry.registerModEntity(EntityAegar.class, "Aegar", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityAegar.class, "TragicMC.Aegar", id++, 0x45C0CB, 0xCEFBFF, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.allowVolatileFusea)
		{
			EntityRegistry.registerModEntity(EntityVolatileFusea.class, "VolatileFusea", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityVolatileFusea.class, "TragicMC.VolatileFusea", id++, 0xE7E69B, 0xB3ADE3, EnumEggType.MINIBOSS);
		}

		//Bosses

		//Ender Dragon
		TragicEntityList.addMapping(EntityDragon.class, "TragicMC.EnderDragon", id++, 0x1A1A1A, 0xCC00FA, EnumEggType.BOSS);

		//Wither
		TragicEntityList.addMapping(EntityWither.class, "TragicMC.Wither", id++, 0x1C1C1C, 0x252525, EnumEggType.BOSS);

		if (TragicConfig.allowApis) //TODO finish Boss sounds
		{
			EntityRegistry.registerModEntity(EntityApis.class, "Apis", listid++, TragicMC.getInstance(), 80, 1, true);

			if (TragicConfig.allowBossOverworldSpawns)
			{
				EntityRegistry.addSpawn(EntityApis.class, TragicConfig.apisSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.plains,
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
			TragicEntityList.addMapping(EntityApis.class, "TragicMC.Apis", id++, 0xFFFF82, 0xFFCD82, EnumEggType.BOSS);
		}

		if (TragicConfig.allowDeathReaper)
		{
			EntityRegistry.registerModEntity(EntityDeathReaper.class, "DeathReaper", listid++, TragicMC.getInstance(), 80, 1, true);

			if (TragicConfig.allowBossOverworldSpawns)
			{
				EntityRegistry.addSpawn(EntityDeathReaper.class, TragicConfig.deathReaperSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.forest,
						BiomeGenBase.forestHills,
						BiomeGenBase.birchForest,
						BiomeGenBase.birchForestHills
						);
			}
			TragicEntityList.addMapping(EntityDeathReaper.class, "TragicMC.DeathReaper", id++, 0xCFCCB4, 0x553131, EnumEggType.BOSS);
		}

		if (TragicConfig.allowKitsune)
		{
			EntityRegistry.registerModEntity(EntityKitsune.class, "Kitsune", listid++, TragicMC.getInstance(), 80, 1, true);
			if (TragicConfig.allowBossOverworldSpawns)
			{
				EntityRegistry.addSpawn(EntityKitsune.class, TragicConfig.kitsuneSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.hell);
			}
			TragicEntityList.addMapping(EntityKitsune.class, "TragicMC.Kitsune", id++, 0xFF0000, 0xFFD087, EnumEggType.BOSS);
		}

		if (TragicConfig.allowPolaris)
		{
			EntityRegistry.registerModEntity(EntityPolaris.class, "Polaris", listid++, TragicMC.getInstance(), 80, 1, true);

			if (TragicConfig.allowBossOverworldSpawns)
			{
				EntityRegistry.addSpawn(EntityPolaris.class, TragicConfig.polarisSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.birchForest,
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
			}
			TragicEntityList.addMapping(EntityPolaris.class, "TragicMC.Polaris", id++, 0x4A00BA, 0x000000, EnumEggType.BOSS);
		}

		if (TragicConfig.allowYeti)
		{
			EntityRegistry.registerModEntity(EntityYeti.class, "Yeti", listid++, TragicMC.getInstance(), 80, 1, true);

			if (TragicConfig.allowBossOverworldSpawns)
			{
				EntityRegistry.addSpawn(EntityYeti.class, TragicConfig.yetiSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.icePlains,
						BiomeGenBase.iceMountains,
						BiomeGenBase.frozenOcean,
						BiomeGenBase.frozenRiver,
						BiomeGenBase.coldBeach,
						BiomeGenBase.coldTaiga,
						BiomeGenBase.coldTaigaHills
						);
			}
			TragicEntityList.addMapping(EntityYeti.class, "TragicMC.Yeti", id++, 0xDADADA, 0xB9BFC7, EnumEggType.BOSS);
		}

		if (TragicConfig.allowTimeController)
		{
			EntityRegistry.registerModEntity(EntityTimeController.class, "TimeController", listid++, TragicMC.getInstance(), 80, 1, true);

			if (TragicConfig.allowBossOverworldSpawns)
			{
				EntityRegistry.addSpawn(EntityTimeController.class, TragicConfig.timeControllerSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.birchForest,
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
			}
			TragicEntityList.addMapping(EntityTimeController.class, "TragicMC.TimeController", id++, 0x94FFA3, 0xEA92E9, EnumEggType.BOSS);
		}

		if (TragicConfig.allowEnyvil)
		{
			EntityRegistry.registerModEntity(EntityEnyvil.class, "Enyvil", listid++, TragicMC.getInstance(), 80, 1, true);

			if (TragicConfig.allowBossOverworldSpawns)
			{
				EntityRegistry.addSpawn(EntityEnyvil.class, TragicConfig.enyvilSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.birchForest,
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
			}
			TragicEntityList.addMapping(EntityEnyvil.class, "TragicMC.Enyvil", id++, 0x5D1543, 0xFF6FFF, EnumEggType.BOSS);
		}

		if (TragicConfig.allowClaymation)
		{
			EntityRegistry.registerModEntity(EntityClaymation.class, "Claymation", listid++, TragicMC.getInstance(), 80, 1, true);

			if (TragicConfig.allowBossOverworldSpawns)
			{
				EntityRegistry.addSpawn(EntityClaymation.class, TragicConfig.claymationSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.desert,
						BiomeGenBase.desertHills,
						BiomeGenBase.mesa,
						BiomeGenBase.mesaPlateau,
						BiomeGenBase.mesaPlateau_F
						);
			}
			TragicEntityList.addMapping(EntityClaymation.class, "TragicMC.Claymation", id++, 0xFF8100, 0xFFB800, EnumEggType.BOSS);
		}

		//Alphas

		if (TragicConfig.allowOverlord)
		{
			EntityRegistry.registerModEntity(EntityOverlordCocoon.class, "OverlordCocoon", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityOverlordCocoon.class, "TragicMC.OverlordCocoon", id++, 0x335548, 0x787878, EnumEggType.ALPHA);

			EntityRegistry.registerModEntity(EntityOverlordCombat.class, "OverlordCombat", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityOverlordCombat.class, "TragicMC.OverlordCombat", id++, 0x64A28A, 0x555555, EnumEggType.ALPHA);

			EntityRegistry.registerModEntity(EntityOverlordCore.class, "OverlordCore", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityOverlordCore.class, "TragicMC.OverlordCore", id++, 0x92F9D1, 0x212121, EnumEggType.ALPHA);
		}

		EntityRegistry.registerModEntity(EntityThrowingRock.class, "ThrowingRock", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityPumpkinbomb.class, "Pumpkinbomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityLargePumpkinbomb.class, "LargePumpkinbomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityPoisonBarb.class, "PoisonBarb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityNekoRocket.class, "NekoRocket", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityNekoStickyBomb.class, "NekoStickyBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityNekoClusterBomb.class, "NekoClusterBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityNekoMiniBomb.class, "NekoMiniBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntitySolarBomb.class, "SolarBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntitySpiritCast.class, "SpiritCast", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntitySpore.class, "Spore", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityBanana.class, "Banana", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityTimeBomb.class, "TimeBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityTimeDisruption.class, "TimeDisruption", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityLargeRock.class, "LargeRock", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityIcicle.class, "Icicle", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityStatue.class, "Statue", listid++, TragicMC.getInstance(), 80, 3, false);
		EntityRegistry.registerModEntity(EntityStarShard.class, "StarShard", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDarkLightning.class, "DarkLightning", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityPitchBlack.class, "PitchBlack", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDarkEnergy.class, "DarkEnergy", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDarkCrystal.class, "DarkCrystal", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDarkMortor.class, "DarkMortor", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityWebBomb.class, "WebBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityCrystalMortor.class, "CrystalMortor", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityGuardianShield.class, "GuardianShield", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityOverlordMortor.class, "OverlordMortor", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDimensionalAnomaly.class, "DimensionalAnomaly", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityLock.class, "Lock", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDirectedLightning.class, "DirectedLightning", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityIreEnergy.class, "IreEnergy", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityNuke.class, "Nuke", listid++, TragicMC.getInstance(), 80, 3, true);
	}
}
