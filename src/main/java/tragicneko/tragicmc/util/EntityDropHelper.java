package tragicneko.tragicmc.util;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.miniboss.EntityAegar;
import tragicneko.tragicmc.entity.miniboss.EntityGreaterStin;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.miniboss.EntityMegaCryse;
import tragicneko.tragicmc.entity.miniboss.EntityStinKing;
import tragicneko.tragicmc.entity.miniboss.EntityStinQueen;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityErkel;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityMinotaur;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityPsygote;
import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.entity.mob.EntitySirv;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicItems;

public class EntityDropHelper {

	private static Map<Class, DropEntry> entityDrops = new HashMap();
	private static EntityDrop[] luxuryDrops = new EntityDrop[] {new EntityDrop(50, Items.diamond), new EntityDrop(35, Items.emerald), new EntityDrop(75, Items.iron_ingot),
		new EntityDrop(60, Items.gold_ingot), new EntityDrop(75, Items.gold_nugget), new EntityDrop(40, TragicItems.Sapphire), new EntityDrop(25, TragicItems.Ruby),
		new EntityDrop(65, TragicItems.Tungsten), new EntityDrop(75, TragicItems.RedMercury), new EntityDrop(10, Blocks.gold_block), new EntityDrop(15, Blocks.iron_block),
		new EntityDrop(10, Blocks.diamond_block), new EntityDrop(5, Blocks.emerald_block), new EntityDrop(5, TragicItems.AmuletRelease), new EntityDrop(5, TragicItems.AwakeningStone),
		new EntityDrop(5, TragicItems.DoomConsume), new EntityDrop(15, TragicItems.CooldownDefuse), new EntityDrop(3, TragicItems.Titan), new EntityDrop(3, TragicItems.Paranoia),
		new EntityDrop(3, TragicItems.Splinter), new EntityDrop(3, TragicItems.Butcher), new EntityDrop(3, TragicItems.Thardus), new EntityDrop(3, TragicItems.DragonFang),
		new EntityDrop(8, new ItemStack(TragicBlocks.CompactOre, 1, 0), new ItemStack(TragicBlocks.CompactOre, 1, 1), new ItemStack(TragicBlocks.CompactOre, 1, 2),
				new ItemStack(TragicBlocks.CompactOre, 1, 3), new ItemStack(TragicBlocks.CompactOre, 1, 4)), new EntityDrop(5, TragicItems.Talisman),
		new EntityDrop(15, getDoomsdayScrollStacks())};

	static
	{
		//Normal Mob Drops
		addToDropList(EntityJabba.class, new EntityDrop[][] {{new EntityDrop(25, Items.magma_cream), new EntityDrop(15, TragicItems.Ash)},
			{new EntityDrop(5, TragicItems.FireOrb), new EntityDrop(5, TragicItems.RedMercury)},
			{new EntityDrop(25, Items.slime_ball), new EntityDrop(10, Items.magma_cream)},
			{new EntityDrop(5, TragicItems.Spore)}}, true);
		
		addToDropList(EntityPlague.class, new EntityDrop[][] {{new EntityDrop(25, TragicItems.Ash), new EntityDrop(5, TragicItems.DarkParticles)},
			{new EntityDrop(5, TragicItems.DarkParticles)}});
		
		addToDropList(EntityGragul.class, new EntityDrop[][] {{new EntityDrop(15, TragicItems.Ash), new EntityDrop(25, Items.clay_ball), new EntityDrop(5, Items.coal)},
			{new EntityDrop(25, Items.coal), new EntityDrop(5, TragicItems.GravityOrb)}});
		
		addToDropList(EntityMinotaur.class, new EntityDrop[][] {{new EntityDrop(25, Items.beef), new EntityDrop(5, Items.leather), new EntityDrop(5, TragicItems.Horn)},
			{new EntityDrop(10, TragicItems.Horn), new EntityDrop(5, TragicItems.ToughLeather)}});
		
		addToDropList(EntityInkling.class, new EntityDrop[][] {{new EntityDrop(25, TragicItems.Ash), new EntityDrop(5, TragicItems.DarkParticles)},
			{new EntityDrop(25, TragicItems.DarkParticles), new EntityDrop(5, Items.coal)}});
		
		addToDropList(EntityRagr.class, new EntityDrop[][] {{new EntityDrop(25, Items.leather), new EntityDrop(10, TragicItems.CrushedIce), new EntityDrop(15, Items.snowball),
				new EntityDrop(5, Items.fish)},
			{new EntityDrop(25, TragicItems.ToughLeather), new EntityDrop(15, TragicItems.IceOrb), new EntityDrop(10, TragicItems.Sushi), new EntityDrop(5, TragicItems.GoldenSushi)}});
		
		addToDropList(EntityPumpkinhead.class, new EntityDrop[][]{{new EntityDrop(25, Items.pumpkin_pie), new EntityDrop(5, Items.pumpkin_seeds), new EntityDrop(10, Blocks.pumpkin)},
			{new EntityDrop(15, Blocks.lit_pumpkin), new EntityDrop(15, TragicBlocks.Candle), new EntityDrop(5, TragicItems.DarkParticles), new EntityDrop(25, Blocks.torch)}});
		
		addToDropList(EntityTragicNeko.class, new EntityDrop[][] {{new EntityDrop(15, Items.iron_ingot), new EntityDrop(25, Items.gunpowder), new EntityDrop(5, Blocks.tnt)},
			{new EntityDrop(15, TragicItems.GoldenSushi), new EntityDrop(5, new ItemStack(Items.golden_apple, 1, 1), new ItemStack(Items.golden_apple, 1, 0)), new EntityDrop(25, Items.diamond)}});
		
		addToDropList(EntityTox.class, new EntityDrop[][] {{new EntityDrop(25, TragicItems.Spore), new EntityDrop(15, Blocks.vine), new EntityDrop(10, TragicItems.Thorns),
				new EntityDrop(5, new ItemStack(Items.wheat_seeds), new ItemStack(Items.pumpkin_seeds), new ItemStack(Items.melon_seeds))},
			{new EntityDrop(15, TragicItems.ExoticFruit), new EntityDrop(5, TragicItems.NastyFruit), new EntityDrop(25, Items.slime_ball)},
			{new EntityDrop(25, TragicItems.Spore), new EntityDrop(25, TragicBlocks.GlowVine), new EntityDrop(5, TragicItems.Thorns),
				new EntityDrop(10, new ItemStack(Items.wheat_seeds), new ItemStack(Items.pumpkin_seeds), new ItemStack(Items.melon_seeds))},
			{new EntityDrop(15, TragicItems.ExoticFruit), new EntityDrop(5, TragicItems.NastyFruit), new EntityDrop(5, TragicItems.GooeyFruit), new EntityDrop(25, Items.slime_ball)}},
				true);
		
		addToDropList(EntityCryse.class, new EntityDrop[][] {{new EntityDrop(25, TragicItems.CrushedIce), new EntityDrop(15, Items.snowball), new EntityDrop(5, TragicItems.IceOrb)},
			{new EntityDrop(5, TragicItems.IceOrb)},
			{new EntityDrop(5, new ItemStack(TragicBlocks.StarCrystal, 15)), new EntityDrop(25, Items.glowstone_dust)},
			{new EntityDrop(5, TragicBlocks.StarCrystal)}},
				true);
		
		addToDropList(EntityPirah.class, new EntityDrop[][] {{new EntityDrop(25, new ItemStack(Items.fish, 1, 0), new ItemStack(Items.fish, 1, 1), new ItemStack(Items.fish, 1, 2),
				new ItemStack(Items.fish, 1, 3)), new EntityDrop(25, Items.dye), new EntityDrop(5, TragicItems.LifeWater), new EntityDrop(15, TragicItems.Tentacle)},
			{new EntityDrop(5, TragicItems.LifeWater)},
			{new EntityDrop(25, new ItemStack(Items.fish, 1, 0), new ItemStack(Items.fish, 1, 1), new ItemStack(Items.fish, 1, 2),
				new ItemStack(Items.fish, 1, 3)), new EntityDrop(25, Items.dye), new EntityDrop(5, TragicItems.FireOrb), new EntityDrop(25, TragicItems.Tentacle)},
			{new EntityDrop(5, TragicItems.FireOrb), new EntityDrop(10, Items.fire_charge)}},
				true);
		
		addToDropList(EntityNorVox.class, new EntityDrop[][] {{new EntityDrop(25, TragicItems.Projectile), new EntityDrop(10, Items.glowstone_dust), new EntityDrop(5, TragicItems.ObsidianOrb),
				new EntityDrop(10, Blocks.stone)},
			{new EntityDrop(15, Items.emerald), new EntityDrop(15, Items.diamond), new EntityDrop(25, TragicItems.Tungsten), new EntityDrop(25, TragicItems.RedMercury), new EntityDrop(5, TragicItems.Ruby),
				new EntityDrop(5, TragicItems.Sapphire), new EntityDrop(15, Items.experience_bottle)},
			{new EntityDrop(25, new ItemStack(TragicBlocks.StarCrystal, 1, 15)), new EntityDrop(10, Items.glowstone_dust), new EntityDrop(5, TragicItems.ObsidianOrb)},
			{new EntityDrop(15, Items.emerald), new EntityDrop(15, Items.diamond), new EntityDrop(25, TragicItems.Tungsten), new EntityDrop(25, TragicItems.RedMercury), new EntityDrop(5, TragicItems.Ruby),
				new EntityDrop(5, TragicItems.Sapphire), new EntityDrop(15, Items.experience_bottle)}},
				true);
		
		addToDropList(EntityStin.class, new EntityDrop[][] {{new EntityDrop(25, TragicItems.Ash), new EntityDrop(5, TragicItems.DarkParticles)},
			{new EntityDrop(25, TragicItems.DarkParticles), new EntityDrop(5, Items.ender_pearl), new EntityDrop(5, Items.ender_eye)},
			{new EntityDrop(25, TragicItems.Ash), new EntityDrop(5, Items.coal)},
			{new EntityDrop(5, Items.ender_pearl)}},
				true);
		
		addToDropList(EntityWisp.class, new EntityDrop[][] {{new EntityDrop(25, Items.glowstone_dust), new EntityDrop(5, TragicItems.WispParticles)},
			{new EntityDrop(5, TragicItems.WispParticles)}});
		
		addToDropList(EntityAbomination.class, new EntityDrop[][] {{new EntityDrop(25, TragicItems.CrushedIce), new EntityDrop(15, Items.fish), new EntityDrop(10, Items.snowball)},
			{new EntityDrop(25, TragicItems.IcyFur), new EntityDrop(5, TragicItems.IceOrb)}});
		
		addToDropList(EntityErkel.class, new EntityDrop[][] {{new EntityDrop(25, Blocks.brown_mushroom), new EntityDrop(15, Blocks.red_mushroom), new EntityDrop(5, TragicItems.Spore)},
			{new EntityDrop(5, Items.mushroom_stew), new EntityDrop(25, TragicItems.Spore), new EntityDrop(5, Items.nether_wart)}});
		
		addToDropList(EntitySirv.class, new EntityDrop[][] {{new EntityDrop(25, Items.clay_ball), new EntityDrop(5, Blocks.clay)},
			{new EntityDrop(25, Blocks.clay), new EntityDrop(5, Blocks.brick_block)}});
		
		addToDropList(EntityPsygote.class, new EntityDrop[][] {{new EntityDrop(5, getDoomsdayScrollStacks()), new EntityDrop(25, TragicItems.DarkParticles), new EntityDrop(15, Items.ender_pearl)},
			{new EntityDrop(15, getDoomsdayScrollStacks()), new EntityDrop(25, Items.ender_eye), new EntityDrop(5, TragicItems.DimensionalKey)}});
		
		
		//Mini-Boss drops
		addToDropList(EntityJarra.class, new EntityDrop[][] {{new EntityDrop(15, TragicItems.Spore), new EntityDrop(25, TragicItems.Thorns), new EntityDrop(5, Items.magma_cream)},
			{new EntityDrop(15, TragicItems.Spore), new EntityDrop(10, TragicItems.Ectoplasm), new EntityDrop(5, TragicItems.GooeyFruit)}});
		
		addToDropList(EntityKragul.class, new EntityDrop[][] {{new EntityDrop(15, Items.redstone), new EntityDrop(25, Items.clay_ball), new EntityDrop(5, Blocks.clay)},
			{new EntityDrop(5, Items.coal), new EntityDrop(15, TragicItems.GravityOrb), new EntityDrop(5, Blocks.redstone_block)}});
		
		addToDropList(EntityMagmox.class, new EntityDrop[][] {{new EntityDrop(15, Items.magma_cream), new EntityDrop(35, Items.blaze_powder), new EntityDrop(5, Items.blaze_rod),
				new EntityDrop(5, new ItemStack(TragicItems.ExoticFruit), new ItemStack(TragicItems.NastyFruit), new ItemStack(TragicItems.GooeyFruit)), new EntityDrop(5, Items.nether_wart)},
			{new EntityDrop(15, new ItemStack(TragicItems.ExoticFruit), new ItemStack(TragicItems.NastyFruit), new ItemStack(TragicItems.GooeyFruit)), new EntityDrop(5, TragicItems.FireOrb)}});
		
		addToDropList(EntityMegaCryse.class, new EntityDrop[][] {{new EntityDrop(25, TragicItems.CrushedIce), new EntityDrop(15, TragicItems.IceOrb), new EntityDrop(5, Blocks.ice)},
			{new EntityDrop(25, TragicItems.IceOrb), new EntityDrop(15, Blocks.ice), new EntityDrop(5, Blocks.packed_ice)}});
		
		addToDropList(EntityGreaterStin.class, new EntityDrop[][] {{new EntityDrop(25, Items.ender_pearl), new EntityDrop(15, TragicItems.DarkParticles), new EntityDrop(5, Blocks.coal_block)},
			{new EntityDrop(25, TragicItems.DarkParticles), new EntityDrop(15, Items.ender_eye), new EntityDrop(5, TragicItems.StinHorn)}});
		
		addToDropList(EntityStinKing.class, new EntityDrop[][] {{new EntityDrop(5, TragicItems.DarkIngot), new EntityDrop(25, TragicItems.DarkParticles), new EntityDrop(5, Blocks.obsidian),
				new EntityDrop(15, Items.ender_pearl)},
			{new EntityDrop(25, TragicItems.StinHorn), new EntityDrop(5, TragicItems.DarkIngot), new EntityDrop(10, Items.ender_eye)}});
		
		addToDropList(EntityStinQueen.class, new EntityDrop[][] {{new EntityDrop(5, TragicItems.DarkIngot), new EntityDrop(25, TragicItems.DarkParticles), new EntityDrop(5, Blocks.web),
				new EntityDrop(15, Items.string)},
			{new EntityDrop(25, Blocks.web), new EntityDrop(5, TragicItems.DarkIngot), new EntityDrop(10, TragicItems.WovenSilk)}});
		
		addToDropList(EntityVoxStellarum.class, new EntityDrop[][] {{new EntityDrop(25, new ItemStack(TragicBlocks.StarCrystal, 1, 15)), new EntityDrop(10, Items.glowstone_dust), new EntityDrop(5, TragicItems.ObsidianOrb)},
			{new EntityDrop(15, Items.emerald), new EntityDrop(15, Items.diamond), new EntityDrop(5, TragicItems.Tungsten), new EntityDrop(5, TragicItems.RedMercury), new EntityDrop(10, TragicItems.Ruby),
				new EntityDrop(10, TragicItems.Sapphire), new EntityDrop(15, Items.experience_bottle), new EntityDrop(5, getLuxuryBlocks(true))}});
		
		addToDropList(EntityAegar.class, new EntityDrop[][] {{new EntityDrop(5, getLuxuryBlocks(true)), new EntityDrop(25, Items.emerald), new EntityDrop(25, Items.diamond),
				new EntityDrop(15, TragicItems.Ruby), new EntityDrop(15, TragicItems.Sapphire), new EntityDrop(5, getOreCharms())},
			{new EntityDrop(10, getLuxuryBlocks(true)), new EntityDrop(5, getOreCharms()), new EntityDrop(25, TragicItems.SynapseCrystal)}});
		
		
		//Boss Drops
		
		/*
		//Boss drops
		entityLootDrops.put(EntityApis.class, new ItemStack[][] {{new ItemStack(TragicItems.LightParticles), new ItemStack(TragicItems.Horn), new ItemStack(TragicItems.LightIngot),
			new ItemStack(TragicItems.LightHelm), new ItemStack(TragicItems.LightPlate), new ItemStack(TragicItems.LightLegs), new ItemStack(TragicItems.LightBoots)},
			{new ItemStack(TragicItems.LightParticles)}});

		entityLootDrops.put(EntityDeathReaper.class, new ItemStack[][] {{new ItemStack(TragicItems.BoneMarrow), new ItemStack(TragicItems.Ash), new ItemStack(Items.bone),
			new ItemStack(TragicItems.DarkParticles), new ItemStack(TragicItems.FireOrb), new ItemStack(Items.blaze_rod)}, {new ItemStack(TragicItems.ReaperSkull)}});

		entityLootDrops.put(EntityKitsune.class, new ItemStack[][] {{new ItemStack(TragicItems.KitsuneTail), new ItemStack(TragicItems.FireOrb), new ItemStack(Items.blaze_powder), 
			new ItemStack(Items.fire_charge)}, {new ItemStack(TragicItems.KitsuneTail)}});

		entityLootDrops.put(EntityPolaris.class, new ItemStack[][] {{new ItemStack(Items.potionitem, 1, 16446), new ItemStack(Items.potionitem, 1, 78), new ItemStack(TragicItems.StarPieces),
			new ItemStack(Items.glowstone_dust), new ItemStack(Items.potionitem, 1, 46), new ItemStack(Items.potionitem, 1, 16478)}, {new ItemStack(TragicItems.StarPieces)}});

		entityLootDrops.put(EntityTimeController.class, new ItemStack[][] {{new ItemStack(TragicItems.TimeEssence), new ItemStack(Items.clock), new ItemStack(Items.diamond),
			new ItemStack(Items.emerald), new ItemStack(TragicItems.CelestialAegis), new ItemStack(TragicItems.CelestialLongbow), new ItemStack(TragicItems.CelestialSteel)}, 
			{new ItemStack(TragicItems.TimeEssence)}});

		entityLootDrops.put(EntityYeti.class, new ItemStack[][] {{new ItemStack(TragicItems.EmpariahClaw), new ItemStack(TragicItems.IceOrb), new ItemStack(TragicItems.CrushedIce),
			new ItemStack(Items.fish), new ItemStack(TragicItems.IcyFur)}, {new ItemStack(TragicItems.EmpariahClaw)}});

		entityLootDrops.put(EntityEnyvil.class, new ItemStack[][] {{new ItemStack(TragicItems.BoneMarrow), new ItemStack(TragicItems.DarkBoots), new ItemStack(TragicItems.DarkPlate),
			new ItemStack(TragicItems.DarkLegs), new ItemStack(TragicItems.DarkHelm), new ItemStack(TragicItems.DarkIngot), new ItemStack(TragicItems.DarkParticles),
			new ItemStack(TragicItems.CelestialSteel), new ItemStack(Items.diamond)}, {new ItemStack(TragicItems.PureDarkness)}});

		entityLootDrops.put(EntityClaymation.class, new ItemStack[][] {{new ItemStack(TragicItems.LivingClay), new ItemStack(TragicItems.Talisman), new ItemStack(TragicItems.Quicksilver),
			new ItemStack(TragicBlocks.Wax), new ItemStack(Blocks.sand), new ItemStack(TragicBlocks.Quicksand), new ItemStack(Items.clay_ball)}, {new ItemStack(TragicItems.LivingClay)}}); */
	}

	public static void addToDropList(DropEntry entry)
	{
		if (entityDrops.containsKey(entry.getEntityClass())) TragicMC.logWarning("Duplicate drop mapping for " + entry.getEntityClass());
		entityDrops.put(entry.getEntityClass(), entry);
	}

	public static void addToDropList(Class clazz, EntityDrop[][] drops)
	{
		if (entityDrops.containsKey(clazz)) TragicMC.logWarning("Duplicate drop mapping for " + clazz);
		entityDrops.put(clazz, new DropEntry(clazz, drops));
	}

	public static void addToDropList(Class clazz, EntityDrop[][] drops, boolean variants)
	{
		if (entityDrops.containsKey(clazz)) TragicMC.logWarning("Duplicate drop mapping for " + clazz);
		entityDrops.put(clazz, new DropEntry(clazz, drops, variants));
	}
	
	private static ItemStack[] getDoomsdayScrollStacks()
	{
		ItemStack[] stack = new ItemStack[Doomsday.doomsdayNames.length];
		for (int i = 0; i < Doomsday.doomsdayNames.length; i++)
		{
			if (Doomsday.doomsdayList[i] != null) stack[i] = new ItemStack(TragicItems.DoomsdayScroll, 1, i);
		}
		return stack;
	}
	
	/**
	 * Get all of the luxury blocks in an ItemStack array for ease, flag toggles whether modded luxury blocks are included
	 */
	private static ItemStack[] getLuxuryBlocks(boolean flag)
	{
		ItemStack[] stack = new ItemStack[13];
		
		stack[0] = new ItemStack(Blocks.diamond_block);
		stack[1] = new ItemStack(Blocks.emerald_block);
		stack[2] = new ItemStack(Blocks.coal_block);
		stack[3] = new ItemStack(Blocks.gold_block);
		stack[4] = new ItemStack(Blocks.iron_block);
		stack[5] = new ItemStack(Blocks.lapis_block);
		stack[6] = new ItemStack(Blocks.redstone_block);
		
		if (flag)
		{
			for (int i = 0; i < 5; i++) stack[7 + i] = new ItemStack(TragicBlocks.CompactOre, 1, i);
		}
		
		return stack;
	}
	
	private static ItemStack[] getOreCharms()
	{
		ItemStack[] stack = new ItemStack[4];
		stack[0] = new ItemStack(TragicItems.RubyCharm);
		stack[1] = new ItemStack(TragicItems.SapphireCharm);
		stack[2] = new ItemStack(TragicItems.DiamondCharm);
		stack[3] = new ItemStack(TragicItems.EmeraldCharm);
		return stack;
	}

	public static ItemStack getRandomDropFromEntity(Class clazz)
	{
		try
		{
			return entityDrops.containsKey(clazz) ? entityDrops.get(clazz).getRandomDropFromEntity(rand.nextBoolean()) : null;
		}
		catch (Exception e)
		{
			TragicMC.logError("There was an error retrieving a mapping for an entity's drops", e);
			return null;
		}
	}
	
	/**
	 * Set flag to true for common, false for rare
	 * @param clazz
	 * @param flag
	 * @return
	 */
	public static ItemStack getDropFromEntity(Class clazz, boolean flag)
	{
		try
		{
			return entityDrops.containsKey(clazz) ? entityDrops.get(clazz).getRandomDropFromEntity(flag).copy() : null;
		}
		catch (Exception e)
		{
			TragicMC.logError("There was an error retrieving a mapping for an entity's drops", e);
			return null;
		}
	}
	
	public static ItemStack getRandomDropFromVariant(Class clazz)
	{
		try
		{
			return entityDrops.containsKey(clazz) ? entityDrops.get(clazz).getRandomDropFromVariant(rand.nextBoolean()).copy() : null;
		}
		catch (Exception e)
		{
			TragicMC.logError("There was an error retrieving a mapping for an entity's drops", e);
			return null;
		}
	}
	
	/**
	 * Set flag to true for common, false for rare
	 * @param clazz
	 * @param flag
	 * @return
	 */
	public static ItemStack getDropFromVariant(Class clazz, boolean flag)
	{
		try
		{
			return entityDrops.containsKey(clazz) ? entityDrops.get(clazz).getRandomDropFromVariant(flag) : null;
		}
		catch (Exception e)
		{
			TragicMC.logError("There was an error retrieving a mapping for an entity's drops", e);
			return null;
		}
	}
	
	public static ItemStack getLuxuryDropForBoss()
	{
		try
		{
			return ((EntityDrop) WeightedRandom.getRandomItem(rand, luxuryDrops)).getStack().copy();
		}
		catch (Exception e)
		{
			TragicMC.logError("There was an error retrieving a mapping for an entity's drops", e);
			return null;
		}
	}

	private static class DropEntry
	{
		private final Class entityClazz;
		private boolean hasVariants = false;

		private EntityDrop[] rareDrops;
		private EntityDrop[] commonDrops;

		private EntityDrop[] variantRare;
		private EntityDrop[] variantCommon;

		public DropEntry(Class clazz, EntityDrop[][] drops)
		{
			this.entityClazz = clazz;
			this.commonDrops = drops[0];
			this.rareDrops = drops[1];
		}

		public DropEntry(Class clazz, EntityDrop[][] drops, boolean variants)
		{
			this(clazz, drops);
			this.hasVariants = true;
			
			if (variants)
			{
				this.variantCommon = drops[2];
				this.variantRare = drops[3];
			}
		}

		public Class getEntityClass()
		{
			return this.entityClazz;
		}

		public boolean hasVariants()
		{
			return this.hasVariants;
		}

		/**
		 * Set flag to true for common drops, false for rare
		 * @param flag
		 * @return
		 */
		public ItemStack getRandomDropFromEntity(boolean flag)
		{
			return flag ? this.getCommonDropFromEntity() : this.getRareDropFromEntity();
		}

		private ItemStack getCommonDropFromEntity()
		{
			try
			{
				return ((EntityDrop) WeightedRandom.getRandomItem(rand, this.commonDrops)).getStack();
			}
			catch (Exception e)
			{
				TragicMC.logError("Error doing an entity's common drops", e);
				return null;
			}
		}

		private ItemStack getRareDropFromEntity()
		{
			try
			{
				return ((EntityDrop) WeightedRandom.getRandomItem(rand, this.rareDrops)).getStack();
			}
			catch (Exception e)
			{
				TragicMC.logError("Error doing an entity's rare drops", e);
				return null;
			}
		}
		
		/**
		 * Set flag to true for common drops, false for rare, this is for variants
		 * @param flag
		 * @return
		 */
		public ItemStack getRandomDropFromVariant(boolean flag)
		{
			return flag ? this.getCommonDropFromVariant() : this.getRareDropFromVariant();
		}

		private ItemStack getCommonDropFromVariant()
		{
			try
			{
				return this.hasVariants ? ((EntityDrop) WeightedRandom.getRandomItem(rand, this.variantCommon)).getStack() : null;
			}
			catch (Exception e)
			{
				TragicMC.logError("Error doing an entity's variant common drops", e);
				return null;
			}
		}

		private ItemStack getRareDropFromVariant()
		{
			try
			{
				return this.hasVariants ? ((EntityDrop) WeightedRandom.getRandomItem(rand, this.variantRare)).getStack() : null;
			}
			catch (Exception e)
			{
				TragicMC.logError("Error doing an entity's variant rare drops", e);
				return null;
			}
		}
	}
	
	public static class EntityDrop extends WeightedRandom.Item
	{
		private final ItemStack[] stack;
		
		public EntityDrop(int weight, Item item)
		{
			super(weight);
			this.stack = new ItemStack[] {new ItemStack(item)};
		}
		
		public EntityDrop(int weight, Block block)
		{
			super(weight);
			this.stack = new ItemStack[] {new ItemStack(block)};
		}
		
		public EntityDrop(int weight, ItemStack... stack)
		{
			super(weight);
			this.stack = stack;
		}
		
		public ItemStack getStack()
		{
			return this.stack[rand.nextInt(this.stack.length)];
		}
	}
}
