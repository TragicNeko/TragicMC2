package tragicneko.tragicmc.main;

import java.awt.Color;

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
import tragicneko.tragicmc.entity.EntityStatue;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
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
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityDeathReaperClone;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityJanna;
import tragicneko.tragicmc.entity.mob.EntityLavaPirah;
import tragicneko.tragicmc.entity.mob.EntityMinotaur;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityPox;
import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.entity.mob.EntityStarCryse;
import tragicneko.tragicmc.entity.mob.EntityStarVox;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityStinBaby;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.entity.projectile.EntityBanana;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.entity.projectile.EntitySpore;
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
		int id = 1;
		int listid = 1;
		Color color1 = new Color(0x00, 0x00, 0x00);
		Color color2 = new Color(0x00, 0x00, 0x00);

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
			color1 = new Color(0xDA, 0x36, 0x00);
			color2 = new Color(0xFF, 0x96, 0x1D);
			TragicEntityList.addMapping(EntityJabba.class, "TragicMC.Jabba", id++, color1.getRGB(), color2.getRGB());
		}

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
			color1 = new Color(0x77, 0x32, 0x9B);
			color2 = new Color(0xC4, 0x57, 0xFD);
			TragicEntityList.addMapping(EntityJarra.class, "TragicMC.Jarra", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowJanna)
		{
			EntityRegistry.registerModEntity(EntityJanna.class, "Janna", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0x7F, 0xF4, 0xFF);
			color2 = new Color(0x21, 0xC9, 0xA7);
			TragicEntityList.addMapping(EntityJanna.class, "TragicMC.Janna", id++, color1.getRGB(), color2.getRGB());
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
			color1 = new Color(0x12, 0x12, 0x12);
			color2 = new Color(0x12, 0x12, 0x12);
			TragicEntityList.addMapping(EntityPlague.class, "TragicMC.Plague", id++, color1.getRGB(), color2.getRGB());
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
			color1 = new Color(0x77, 0x77, 0x77);
			color2 = new Color(0xaa, 0xaa, 0xaa);
			TragicEntityList.addMapping(EntityGragul.class, "TragicMC.Gragul", id++, color1.getRGB(), color2.getRGB());
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
			color1 = new Color(0xDE, 0x3C, 0x31);
			color2 = new Color(0x74, 0x74, 0x74);
			TragicEntityList.addMapping(EntityKragul.class, "TragicMC.Kragul", id++, color1.getRGB(), color2.getRGB());
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
			color1 = new Color(0x68, 0x3C, 0x1F);
			color2 = new Color(0x35, 0x1F, 0x10);
			TragicEntityList.addMapping(EntityMinotaur.class, "TragicMC.Minotaur", id++, color1.getRGB(), color2.getRGB());
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
			color1 = new Color(0x22, 0x22, 0x22);
			color2 = new Color(0x33, 0x33, 0x33);
			TragicEntityList.addMapping(EntityInkling.class, "TragicMC.Inkling", id++, color1.getRGB(), color2.getRGB());
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
			color1 = new Color(0x94, 0xC3, 0xD9);
			color2 = new Color(0x40, 0x67, 0x79);
			TragicEntityList.addMapping(EntityRagr.class, "TragicMC.Ragr", id++, color1.getRGB(), color2.getRGB());
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
			color1 = new Color(0xFD, 0x92, 0x29);
			color2 = new Color(0x33, 0x33, 0x33);
			TragicEntityList.addMapping(EntityPumpkinhead.class, "TragicMC.Pumpkinhead", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowTragicNeko)
		{
			EntityRegistry.registerModEntity(EntityTragicNeko.class, "TragicNeko", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0x37, 0x35, 0x35);
			color2 = new Color(0x85, 0x3B, 0x3B);
			TragicEntityList.addMapping(EntityTragicNeko.class, "TragicMC.TragicNeko", id++, color1.getRGB(), color2.getRGB());
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
			color1 = new Color(0xDA, 0xCF, 0x18);
			color2 = new Color(0x15, 0xA9, 0x15);
			TragicEntityList.addMapping(EntityTox.class, "TragicMC.Tox", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowMagmox)
		{
			EntityRegistry.registerModEntity(EntityMagmox.class, "Magmox", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityMagmox.class, TragicNewConfig.magmoxSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.hell);
			color1 = new Color(0xC2, 0x00, 0x00);
			color2 = new Color(0x55, 0x00, 0x00);
			TragicEntityList.addMapping(EntityMagmox.class, "TragicMC.Magmox", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowPox)
		{
			EntityRegistry.registerModEntity(EntityPox.class, "Pox", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0x18, 0xDA, 0xA1);
			color2 = new Color(0x15, 0xA4, 0xA9);
			TragicEntityList.addMapping(EntityPox.class, "TragicMC.Pox", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowCryse)
		{
			EntityRegistry.registerModEntity(EntityCryse.class, "Cryse", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityCryse.class, TragicNewConfig.cryseSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.icePlains,
					BiomeGenBase.iceMountains,
					BiomeGenBase.coldTaiga,
					BiomeGenBase.coldTaigaHills
					);
			color1 = new Color(0xCE, 0xE3, 0xE3);
			color2 = new Color(0xFF, 0xFF, 0xFF);
			TragicEntityList.addMapping(EntityCryse.class, "TragicMC.Cryse", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowMegaCryse)
		{
			EntityRegistry.registerModEntity(EntityMegaCryse.class, "MegaCryse", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityMegaCryse.class, TragicNewConfig.megaCryseSC, 0, 1, EnumCreatureType.monster, BiomeGenBase.icePlains,
					BiomeGenBase.iceMountains,
					BiomeGenBase.coldTaiga,
					BiomeGenBase.coldTaigaHills
					);
			color1 = new Color(0xCE, 0xCE, 0xE3);
			color2 = new Color(0xBD, 0xBA, 0xC8);
			TragicEntityList.addMapping(EntityMegaCryse.class, "TragicMC.MegaCryse", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowStarCryse)
		{
			EntityRegistry.registerModEntity(EntityStarCryse.class, "StarCryse", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0x9A, 0x9A, 0x9A);
			color2 = new Color(0xBB, 0xBB, 0xBB);
			TragicEntityList.addMapping(EntityStarCryse.class, "TragicMC.StarCryse", id++, color1.getRGB(), color2.getRGB());
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
			color1 = new Color(0x00, 0x00, 0x00);
			color2 = new Color(0x56, 0x56, 0x56);
			TragicEntityList.addMapping(EntityNorVox.class, "TragicMC.NorVox", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowStarVox)
		{
			EntityRegistry.registerModEntity(EntityStarVox.class, "StarVox", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0xAB, 0xAB, 0xAB);
			color2 = new Color(0xDD, 0xDD, 0xDD);
			TragicEntityList.addMapping(EntityStarVox.class, "TragicMC.StarVox", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowPirah)
		{
			EntityRegistry.registerModEntity(EntityPirah.class, "Pirah", listid++, TragicMC.instance, 80, 1, true);
			EntityRegistry.addSpawn(EntityPirah.class, TragicNewConfig.pirahSC, 2, 6, EnumCreatureType.waterCreature, BiomeGenBase.deepOcean,
					BiomeGenBase.ocean,
					BiomeGenBase.river
					);
			color1 = new Color(0x99, 0xF2, 0xFF);
			color2 = new Color(0xFF, 0x99, 0x99);
			TragicEntityList.addMapping(EntityPirah.class, "TragicMC.Pirah", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowLavaPirah)
		{
			EntityRegistry.registerModEntity(EntityLavaPirah.class, "LavaPirah", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0xF7, 0x77, 0x5D);
			color2 = new Color(0xDD, 0x47, 0x3F);
			TragicEntityList.addMapping(EntityLavaPirah.class, "TragicMC.LavaPirah", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowStin)
		{
			EntityRegistry.registerModEntity(EntityStin.class, "Stin", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0x67, 0x67, 0x67);
			color2 = new Color(0x45, 0x45, 0x45);
			TragicEntityList.addMapping(EntityStin.class, "TragicMC.Stin", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowStinBaby)
		{
			EntityRegistry.registerModEntity(EntityStinBaby.class, "StinBaby", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0x33, 0x33, 0x33);
			color2 = new Color(0x12, 0x12, 0x12);
			TragicEntityList.addMapping(EntityStinBaby.class, "TragicMC.StinBaby", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowGreaterStin)
		{
			EntityRegistry.registerModEntity(EntityGreaterStin.class, "GreaterStin", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0x45, 0x45, 0x45);
			color2 = new Color(0x38, 0x38, 0x38);
			TragicEntityList.addMapping(EntityGreaterStin.class, "TragicMC.GreaterStin", id++, color1.getRGB(), color2.getRGB());
		} 

		if (TragicNewConfig.allowStinKing)
		{
			EntityRegistry.registerModEntity(EntityStinKing.class, "StinKing", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0x75, 0x45, 0x45);
			color2 = new Color(0x48, 0x38, 0x38);
			TragicEntityList.addMapping(EntityStinKing.class, "TragicMC.StinKing", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowStinQueen)
		{
			EntityRegistry.registerModEntity(EntityStinQueen.class, "StinQueen", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0x23, 0x23, 0x23);
			color2 = new Color(0x76, 0x76, 0x76);
			TragicEntityList.addMapping(EntityStinQueen.class, "TragicMC.StinQueen", id++, color1.getRGB(), color2.getRGB());
		}

		if (TragicNewConfig.allowWisp)
		{
			EntityRegistry.registerModEntity(EntityWisp.class, "Wisp", listid++, TragicMC.instance, 80, 1, true);
			color1 = new Color(0x98, 0x98, 0x98);
			color2 = new Color(0xAB, 0xAB, 0xAB);
			TragicEntityList.addMapping(EntityWisp.class, "TragicMC.Wisp", id++, color1.getRGB(), color2.getRGB());
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
			color1 = new Color(0xCD, 0xCD, 0xCD);
			color2 = new Color(0xA9, 0xAF, 0xB7);
			TragicEntityList.addMapping(EntityAbomination.class, "TragicMC.Abomination", id++, color1.getRGB(), color2.getRGB());
		}

		//Iron Golem
		color1 = new Color(0xDB, 0xCD, 0xC1);
		color2 = new Color(0x8B, 0x72, 0x60);
		TragicEntityList.addMapping(EntityIronGolem.class, "TragicMC.IronGolem", id++, color1.getRGB(), color2.getRGB());

		//Added snow golem to ice biomes
		EntityRegistry.addSpawn(EntitySnowman.class, 40, 0, 2, EnumCreatureType.creature, BiomeGenBase.icePlains, BiomeGenBase.iceMountains,
				BiomeGenBase.frozenOcean,
				BiomeGenBase.frozenRiver,
				BiomeGenBase.coldBeach,
				BiomeGenBase.coldTaiga,
				BiomeGenBase.coldTaigaHills
				);

		//Wither
		color1 = new Color(0x1C, 0x1C, 0x1C);
		color2 = new Color(0x25, 0x25, 0x25);
		TragicEntityList.addMapping(EntityWither.class, "TragicMC.Wither", id++, color1.getRGB(), color2.getRGB(), EnumEggType.BOSS);

		//Ender Dragon
		color1 = new Color(0x1A, 0x1A, 0x1A);
		color2 = new Color(0xCC, 0x00, 0xFA);
		TragicEntityList.addMapping(EntityDragon.class, "TragicMC.EnderDragon", id++, color1.getRGB(), color2.getRGB(), EnumEggType.BOSS);

		if (TragicNewConfig.allowBosses)
		{
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
				color1 = new Color(0xED, 0xAC, 0x4F);
				color2 = new Color(0xED, 0x85, 0x4F);
				TragicEntityList.addMapping(EntityApis.class, "TragicMC.Apis", id++, color1.getRGB(), color2.getRGB(), EnumEggType.BOSS);
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
				color1 = new Color(0xCF, 0xCC, 0xB4);
				color2 = new Color(0x55, 0x31, 0x31);
				TragicEntityList.addMapping(EntityDeathReaper.class, "TragicMC.DeathReaper", id++, color1.getRGB(), color2.getRGB(), EnumEggType.BOSS);

				//Death Reaper Clone
				EntityRegistry.registerModEntity(EntityDeathReaperClone.class, "DeathReaperClone", listid++, TragicMC.instance, 80, 1, true);
			}

			if (TragicNewConfig.allowKitsune)
			{
				EntityRegistry.registerModEntity(EntityKitsune.class, "Kitsune", listid++, TragicMC.instance, 80, 1, true);
				if (TragicNewConfig.allowBossNaturalSpawns)
				{
					EntityRegistry.addSpawn(EntityKitsune.class, TragicNewConfig.kitsuneSC, 0, 0, EnumCreatureType.monster, BiomeGenBase.hell);
				}

				color1 = new Color(0xFF, 0x00, 0x00);
				color2 = new Color(0xFF, 0xD0, 0x87);
				TragicEntityList.addMapping(EntityKitsune.class, "TragicMC.Kitsune", id++, color1.getRGB(), color2.getRGB(), EnumEggType.BOSS);
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

				color1 = new Color(0x4A, 0x00, 0xBA);
				color2 = new Color(0x00, 0x00, 0x00);
				TragicEntityList.addMapping(EntityPolaris.class, "TragicMC.Polaris", id++, color1.getRGB(), color2.getRGB(), EnumEggType.BOSS);
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

				color1 = new Color(0xDA, 0xDA, 0xDA);
				color2 = new Color(0xB9, 0xBF, 0xC7);
				TragicEntityList.addMapping(EntityYeti.class, "TragicMC.Yeti", id++, color1.getRGB(), color2.getRGB(), EnumEggType.BOSS);
			}

			if (TragicNewConfig.allowTimeController)
			{
				EntityRegistry.registerModEntity(EntityTimeController.class, "TimeController", listid++, TragicMC.instance, 80, 1, true);

				color1 = new Color(0x94, 0xFF, 0xA3);
				color2 = new Color(0xEA, 0x92, 0xE9);
				TragicEntityList.addMapping(EntityTimeController.class, "TragicMC.TimeController", id++, color1.getRGB(), color2.getRGB(), EnumEggType.BOSS);
			}
		}

		EntityRegistry.registerModEntity(EntityThrowingRock.class, "ThrowingRock", listid++, TragicMC.instance, 80, 10, true);
		EntityRegistry.registerModEntity(EntityPumpkinbomb.class, "Pumpkinbomb", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntityLargePumpkinbomb.class, "LargePumpkinbomb", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntityPoisonBarb.class, "PoisonBarb", listid++, TragicMC.instance, 80, 5, true);

		EntityRegistry.registerModEntity(EntityNekoRocket.class, "NekoRocket", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntityNekoStickyBomb.class, "NekoStickyBomb", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntityNekoClusterBomb.class, "NekoClusterBomb", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntityNekoMiniBomb.class, "NekoMiniBomb", listid++, TragicMC.instance, 80, 5, true);

		EntityRegistry.registerModEntity(EntitySolarBomb.class, "SolarBomb", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntitySpiritCast.class, "SpiritCast", listid++, TragicMC.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntitySpore.class, "Spore", listid++, TragicMC.instance, 80, 5, true);
		EntityRegistry.registerModEntity(EntityBanana.class, "Banana", listid++, TragicMC.instance, 80, 10, true);

		EntityRegistry.registerModEntity(EntityTimeBomb.class, "TimeBomb", listid++, TragicMC.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityTimeDisruption.class, "TimeDisruption", listid++, TragicMC.instance, 80, 3, true);

		EntityRegistry.registerModEntity(EntityLargeRock.class, "LargeRock", listid++, TragicMC.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityIcicle.class, "Icicle", listid++, TragicMC.instance, 80, 3, true);

		EntityRegistry.registerModEntity(EntityStatue.class, "Statue", listid++, TragicMC.instance, 80, 3, false);

	}
}
