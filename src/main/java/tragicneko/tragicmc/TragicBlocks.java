package tragicneko.tragicmc;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraftforge.oredict.OreDictionary;
import tragicneko.tragicmc.blocks.BlockAeris;
import tragicneko.tragicmc.blocks.BlockBone;
import tragicneko.tragicmc.blocks.BlockCandle;
import tragicneko.tragicmc.blocks.BlockCelledLamp;
import tragicneko.tragicmc.blocks.BlockCircuit;
import tragicneko.tragicmc.blocks.BlockDarkCobble;
import tragicneko.tragicmc.blocks.BlockDarkSand;
import tragicneko.tragicmc.blocks.BlockDarkSandstone;
import tragicneko.tragicmc.blocks.BlockDarkStone;
import tragicneko.tragicmc.blocks.BlockDarkenedQuartz;
import tragicneko.tragicmc.blocks.BlockDeadDirt;
import tragicneko.tragicmc.blocks.BlockDigitalSea;
import tragicneko.tragicmc.blocks.BlockDisappearing;
import tragicneko.tragicmc.blocks.BlockErodedStone;
import tragicneko.tragicmc.blocks.BlockFox;
import tragicneko.tragicmc.blocks.BlockFragileLight;
import tragicneko.tragicmc.blocks.BlockGas;
import tragicneko.tragicmc.blocks.BlockGeneric;
import tragicneko.tragicmc.blocks.BlockGenericBush;
import tragicneko.tragicmc.blocks.BlockGenericGrass;
import tragicneko.tragicmc.blocks.BlockGenericLeaves;
import tragicneko.tragicmc.blocks.BlockGenericLog;
import tragicneko.tragicmc.blocks.BlockGenericOre;
import tragicneko.tragicmc.blocks.BlockGenericPlanks;
import tragicneko.tragicmc.blocks.BlockGenericTallGrass;
import tragicneko.tragicmc.blocks.BlockGeyser;
import tragicneko.tragicmc.blocks.BlockGiantCrop;
import tragicneko.tragicmc.blocks.BlockGlowvine;
import tragicneko.tragicmc.blocks.BlockLeafTrim;
import tragicneko.tragicmc.blocks.BlockLight;
import tragicneko.tragicmc.blocks.BlockLightCobble;
import tragicneko.tragicmc.blocks.BlockLuminescence;
import tragicneko.tragicmc.blocks.BlockMoltenRock;
import tragicneko.tragicmc.blocks.BlockObsidianVariant;
import tragicneko.tragicmc.blocks.BlockOverlordBarrier;
import tragicneko.tragicmc.blocks.BlockQuicksand;
import tragicneko.tragicmc.blocks.BlockStarCrystal;
import tragicneko.tragicmc.blocks.BlockSteamVent;
import tragicneko.tragicmc.blocks.BlockStorage;
import tragicneko.tragicmc.blocks.BlockStringLight;
import tragicneko.tragicmc.blocks.BlockStructureSeed;
import tragicneko.tragicmc.blocks.BlockSummon;
import tragicneko.tragicmc.blocks.BlockSynapseCore;
import tragicneko.tragicmc.blocks.BlockTimeDisruptor;
import tragicneko.tragicmc.blocks.BlockTragicFlower;
import tragicneko.tragicmc.blocks.BlockTragicOres;
import tragicneko.tragicmc.blocks.BlockTragicSapling;
import tragicneko.tragicmc.blocks.BlockWickedVine;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockAeris;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockCelledLamp;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockDarkStone;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockOres;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockStarCrystal;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockStructureSeeds;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockSummonBlocks;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockTragicFlower;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockTragicSapling;
import tragicneko.tragicmc.blocks.itemblocks.TragicItemBlock;
import tragicneko.tragicmc.blocks.tileentity.TileEntityAeris;
import tragicneko.tragicmc.blocks.tileentity.TileEntityStructureSeed;
import tragicneko.tragicmc.blocks.tileentity.TileEntitySummonBlock;
import tragicneko.tragicmc.blocks.tileentity.TileEntityTimeDisruptor;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.FlowerWorldGen;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TragicBlocks {

	public static Block MercuryOre;
	public static Block TungstenOre;
	public static Block RubyOre;
	public static Block SapphireOre;

	public static Block CompactOre;

	public static Block Wax;
	public static Block Light;
	public static Block Candle;

	public static Block SummonBlock;

	public static Block StructureSeed;

	public static Block PotatoBlock;
	public static Block CarrotBlock;

	public static Block SandstonePressurePlate;
	public static Block NetherBrickPressurePlate;

	public static Block DisappearingBlock;
	public static Block DisappearingBlockInvis;

	public static Block Quicksand;

	public static Block DarkStone;
	public static Block DarkCobblestone;
	public static Block LightCobblestone;
	public static Block LightStone;
	
	public static Block FrozenNetherrack;

	public static Block DeadDirt;
	public static Block DarkSand;
	public static Block DarkSandstone;

	public static Block WickedVine;
	public static Block GlowVine;
	public static Block Root;

	public static Block TragicObsidian;

	public static Block TimeDisruptionCube;

	public static Block TragicOres;
	public static Block BoneBlock;
	public static Block SmoothNetherrack;

	public static Block BrushedGrass;
	public static Block PaintedWood;
	public static Block PaintedPlanks;
	public static Block PaintedLeaves;
	public static Block PaintedTallGrass;

	public static Block AshenGrass;
	public static Block AshenWood;
	public static Block AshenLeaves;
	public static Block AshenPlanks;

	public static Block BleachedWood;
	public static Block BleachedLeaves;
	public static Block BleachedPlanks;

	public static Block TragicSapling;
	public static Block TragicFlower;

	public static Block AshenBush;
	public static Block DeadBush;

	public static Block DriedGrass;
	public static Block AshenTallGrass;

	public static Block StarlitGrass;
	public static Block StarCrystal;
	public static Block StarlitTallGrass;

	public static Block ErodedStone;
	public static Block DarkenedQuartz;

	public static Block Luminescence;

	public static Block CircuitBlock;
	public static Block CelledBlock;
	public static Block CelledLamp;
	public static Block SynapseCore;
	public static Block OverlordBarrier;

	public static Block WitheringGas, CorruptedGas, ExplosiveGas, RadiatedGas;

	public static Block Conduit;
	public static Block DigitalSea;
	public static Block DigitalSeaPowered;
	
	public static Block Aeris;
	
	public static Block MoltenRock;
	public static Block ScorchedRock;
	public static Block Geyser;
	public static Block GeyserSteaming;
	public static Block SteamVent;
	
	public static Block HallowedGrass;
	public static Block StringLight;
	public static Block FragileLight;
	public static Block FragileLightInvis;
	public static Block HallowedLeaves;
	public static Block HallowedLeafTrim;
	public static Block HallowedWood;
	public static Block HallowedPlanks;

	public static void load()
	{		
		MercuryOre = (new BlockGenericOre(1, true).setBlockTextureName("tragicmc:MercuryOre").setBlockName("tragicmc.mercuryOre").setHardness(4.0F).setResistance(5.0F));
		GameRegistry.registerBlock(MercuryOre, ItemBlock.class, "mercuryOre");

		TungstenOre = (new BlockGenericOre(2, true).setBlockTextureName("tragicmc:TungstenOre").setBlockName("tragicmc.tungstenOre").setHardness(4.0F).setResistance(5.0F));
		GameRegistry.registerBlock(TungstenOre, ItemBlock.class, "tungstenOre");

		RubyOre = (new BlockGenericOre(3, false).setBlockTextureName("tragicmc:RubyNetherOre").setBlockName("tragicmc.rubyOre").setHardness(6.0F).setResistance(7.0F));
		GameRegistry.registerBlock(RubyOre, ItemBlock.class, "rubyOre");

		SapphireOre = (new BlockGenericOre(3, false).setBlockTextureName("tragicmc:SapphireNetherOre").setBlockName("tragicmc.sapphireOre").setHardness(6.0F).setResistance(7.0F));
		GameRegistry.registerBlock(SapphireOre, ItemBlock.class, "sapphireOre");

		CompactOre = (new BlockStorage());
		GameRegistry.registerBlock(CompactOre, TragicItemBlock.class, "compactOre", new Object[] {new String[] {"ruby", "sapphire", "tungsten", "mercury", "quicksilver"}, "compactOre"});

		Wax = (((BlockGeneric) (new BlockGeneric(Material.clay, "spade", 0).setBlockTextureName("tragicmc:Wax").setBlockName("tragicmc.wax").setHardness(1.0F).setResistance(1.0F).setStepSound(Block.soundTypeStone).setLightOpacity(5))).setRenderPass(1));
		GameRegistry.registerBlock(Wax, ItemBlock.class, "wax");

		Light = (new BlockLight().setBlockName("tragicmc.light").setStepSound(Block.soundTypeGlass));
		GameRegistry.registerBlock(Light, ItemBlock.class, "light");

		Candle = (new BlockCandle().setBlockName("tragicmc.candle"));
		GameRegistry.registerBlock(Candle, ItemBlock.class, "candle");

		PotatoBlock = (new BlockGiantCrop().setBlockName("tragicmc.potatoBlock").setBlockTextureName("PotatoBlock"));
		GameRegistry.registerBlock(PotatoBlock, ItemBlock.class, "potatoBlock");

		CarrotBlock = (new BlockGiantCrop().setBlockName("tragicmc.carrotBlock").setBlockTextureName("CarrotBlock"));
		GameRegistry.registerBlock(CarrotBlock, ItemBlock.class, "carrotBlock");

		SandstonePressurePlate = (new BlockPressurePlate("sandstone_top", Material.rock, BlockPressurePlate.Sensitivity.players) {
		}.setBlockName("tragicmc.sandstonePressurePlate").setCreativeTab(TragicMC.Survival));
		GameRegistry.registerBlock(SandstonePressurePlate, ItemBlock.class, "sandstonePressurePlate");

		NetherBrickPressurePlate = (new BlockPressurePlate("nether_brick", Material.rock, BlockPressurePlate.Sensitivity.mobs) {
		}.setBlockName("tragicmc.netherBrickPressurePlate").setCreativeTab(TragicMC.Survival));
		GameRegistry.registerBlock(NetherBrickPressurePlate, ItemBlock.class, "netherBrickPressurePlate");

		SummonBlock = (new BlockSummon());
		GameRegistry.registerBlock(SummonBlock, ItemBlockSummonBlocks.class, "summonBlock");

		GameRegistry.registerTileEntity(TileEntitySummonBlock.class, "summonBlock");

		StructureSeed = (new BlockStructureSeed());
		GameRegistry.registerBlock(StructureSeed, ItemBlockStructureSeeds.class, "structureSeed");

		GameRegistry.registerTileEntity(TileEntityStructureSeed.class, "structureSeed");

		DisappearingBlock = (new BlockDisappearing(false).setBlockName("tragicmc.disappearingBlock"));
		GameRegistry.registerBlock(DisappearingBlock, ItemBlock.class, "disappearingBlock");

		DisappearingBlockInvis = (new BlockDisappearing(true).setBlockName("tragicmc.disappearingBlockInvis"));
		GameRegistry.registerBlock(DisappearingBlockInvis, null, "disappearingBlockInvis");

		Quicksand = (new BlockQuicksand());
		GameRegistry.registerBlock(Quicksand, TragicItemBlock.class, "quicksand", new Object[] {new String[] {"quicksand", "mud", "netherDrudge", "toxicSludge"}, "quicksand"});

		DarkStone = (new BlockDarkStone());
		GameRegistry.registerBlock(DarkStone, ItemBlockDarkStone.class, "darkStone");

		DarkCobblestone = (new BlockDarkCobble());
		GameRegistry.registerBlock(DarkCobblestone, TragicItemBlock.class, "darkCobblestone", new Object[] {new String[] {"normal", "hot", "toxic", "ashen"}, "darkCobblestone"});

		LightCobblestone = (new BlockLightCobble());
		GameRegistry.registerBlock(LightCobblestone, TragicItemBlock.class, "lightCobblestone", new Object[] {new String[] {"normal", "frozen", "glowing"}, "lightCobblestone"});

		LightStone = (new BlockGeneric(Material.rock, "pickaxe", 0).setBlockTextureName("tragicmc:SmoothLightStone").setBlockName("tragicmc.lightStone").setHardness(1.0F).setResistance(1.0F).setStepSound(Block.soundTypeStone));
		GameRegistry.registerBlock(LightStone, ItemBlock.class, "lightStone");

		TragicObsidian = (new BlockObsidianVariant());
		GameRegistry.registerBlock(TragicObsidian, TragicItemBlock.class, "obsidian", new Object[] {new String[] {"crying", "bleeding", "dying"}, "obsidian"});

		DeadDirt = (new BlockDeadDirt().setBlockName("tragicmc.deadDirt").setBlockTextureName("tragicmc:DeadDirt"));
		GameRegistry.registerBlock(DeadDirt, TragicItemBlock.class, "deadDirt", new Object[] {new String[] {"normal", "rugged", "mixed"}, "deadDirt"});

		DarkSand = (new BlockDarkSand().setBlockName("tragicmc.darkSand").setBlockTextureName("tragicmc:DarkSand"));
		GameRegistry.registerBlock(DarkSand, ItemBlock.class, "darkSand");

		DarkSandstone = new BlockDarkSandstone();
		GameRegistry.registerBlock(DarkSandstone, TragicItemBlock.class, "darkSandstone", new Object[] {new String[] {"rough", "smooth", "bricked", "chiseled", "gridded", "carved"}, "darkSandstone"});

		TimeDisruptionCube = (new BlockTimeDisruptor().setBlockName("tragicmc.timeDisruptor").setBlockTextureName("tragicmc:DimensionBlock"));
		GameRegistry.registerBlock(TimeDisruptionCube, ItemBlock.class, "timeDisruptor");

		GameRegistry.registerTileEntity(TileEntityTimeDisruptor.class, "timeDisruptor");

		TragicOres = (new BlockTragicOres());
		GameRegistry.registerBlock(TragicOres, ItemBlockOres.class, "tragicOres");

		BoneBlock = (new BlockBone());
		GameRegistry.registerBlock(BoneBlock, TragicItemBlock.class, "boneBlock", new Object[] {new String[]{"normal", "rotten"}, "boneBlock"});

		SmoothNetherrack = (new BlockFox());
		GameRegistry.registerBlock(SmoothNetherrack, TragicItemBlock.class, "smoothNetherrack", new Object[] {new String[] {"normal", "chiseled", "beveled", "sculpted", "foxtail", "molten"}, "smoothNetherrack"});

		BrushedGrass = (new BlockGenericGrass("Brushed").setBlockName("tragicmc.brushedGrass"));
		GameRegistry.registerBlock(BrushedGrass, ItemBlock.class, "brushedGrass");

		PaintedWood = (new BlockGenericLog("Painted").setBlockName("tragicmc.paintedWood"));
		GameRegistry.registerBlock(PaintedWood, ItemBlock.class, "paintedWood");

		PaintedLeaves = (new BlockGenericLeaves().setBlockName("tragicmc.paintedLeaves").setBlockTextureName("PaintedLeaves"));
		GameRegistry.registerBlock(PaintedLeaves, ItemBlock.class, "paintedLeaves");

		PaintedPlanks = (new BlockGenericPlanks().setBlockName("tragicmc.paintedPlanks").setBlockTextureName("tragicmc:PaintedPlanks"));
		GameRegistry.registerBlock(PaintedPlanks, ItemBlock.class, "paintedPlanks");

		GlowVine = (new BlockGlowvine().setBlockName("tragicmc.glowvine").setBlockTextureName("tragicmc:GlowVine"));
		GameRegistry.registerBlock(GlowVine, ItemBlock.class, "glowvine");

		PaintedTallGrass = (new BlockGenericTallGrass("Painted").setBlockName("tragicmc.paintedTallGrass"));
		GameRegistry.registerBlock(PaintedTallGrass, ItemBlock.class, "paintedTallGrass");

		AshenGrass = (new BlockGenericGrass("Ashen").setBlockName("tragicmc.ashenGrass"));
		GameRegistry.registerBlock(AshenGrass, ItemBlock.class, "ashenGrass");

		AshenWood = (new BlockGenericLog("Ashen").setBlockName("tragicmc.ashenWood"));
		GameRegistry.registerBlock(AshenWood, ItemBlock.class, "ashenWood");

		AshenLeaves = (new BlockGenericLeaves().setBlockName("tragicmc.ashenLeaves").setBlockTextureName("AshenLeaves"));
		GameRegistry.registerBlock(AshenLeaves, ItemBlock.class, "ashenLeaves");

		AshenPlanks = (new BlockGenericPlanks().setBlockName("tragicmc.ashenPlanks").setBlockTextureName("tragicmc:AshenPlanks"));
		GameRegistry.registerBlock(AshenPlanks, ItemBlock.class, "ashenPlanks");

		BleachedWood = (new BlockGenericLog("Bleached").setBlockName("tragicmc.bleachedWood"));
		GameRegistry.registerBlock(BleachedWood, ItemBlock.class, "bleachedWood");

		BleachedLeaves = (new BlockGenericLeaves().setBlockName("tragicmc.bleachedLeaves").setBlockTextureName("BleachedLeaves"));
		GameRegistry.registerBlock(BleachedLeaves, ItemBlock.class, "bleachedLeaves");

		BleachedPlanks = (new BlockGenericPlanks().setBlockName("tragicmc.bleachedPlanks").setBlockTextureName("tragicmc:BleachedPlanks"));
		GameRegistry.registerBlock(BleachedPlanks, ItemBlock.class, "bleachedPlanks");

		TragicSapling = (new BlockTragicSapling());
		GameRegistry.registerBlock(TragicSapling, ItemBlockTragicSapling.class, "tragicSapling");

		TragicFlower = (new BlockTragicFlower());
		GameRegistry.registerBlock(TragicFlower, ItemBlockTragicFlower.class, "tragicFlower");

		AshenBush = (new BlockGenericBush().setBlockName("tragicmc.ashenBush").setBlockTextureName("tragicmc:AshenBush"));
		GameRegistry.registerBlock(AshenBush, ItemBlock.class, "ashenBush");

		DeadBush = (new BlockGenericBush().setBlockName("tragicmc.deadBush").setBlockTextureName("tragicmc:DeadBush"));
		GameRegistry.registerBlock(DeadBush, ItemBlock.class, "deadBush");

		DriedGrass = (new BlockGenericTallGrass("Dried").setBlockName("tragicmc.driedTallGrass"));
		GameRegistry.registerBlock(DriedGrass, ItemBlock.class, "driedTallGrass");

		AshenTallGrass = (new BlockGenericTallGrass("Ashen").setBlockName("tragicmc.ashenTallGrass"));
		GameRegistry.registerBlock(AshenTallGrass, ItemBlock.class, "ashenTallGrass");

		StarlitGrass = (new BlockGenericGrass("Starlit").setBlockName("tragicmc.starlitGrass"));
		GameRegistry.registerBlock(StarlitGrass, ItemBlock.class, "starlitGrass");

		StarCrystal = (new BlockStarCrystal());
		GameRegistry.registerBlock(StarCrystal, ItemBlockStarCrystal.class, "starCrystal");

		StarlitTallGrass = (new BlockGenericTallGrass("Starlit").setBlockName("tragicmc.starlitTallGrass"));
		GameRegistry.registerBlock(StarlitTallGrass, ItemBlock.class, "starlitTallGrass");

		ErodedStone = (new BlockErodedStone());
		GameRegistry.registerBlock(ErodedStone, TragicItemBlock.class, "erodedStone", new Object[] {new String[] {"smooth", "carved", "scattered"}, "erodedStone"});

		DarkenedQuartz = (new BlockDarkenedQuartz());
		GameRegistry.registerBlock(DarkenedQuartz, TragicItemBlock.class, "darkenedQuartz", new Object[] {new String[] {"smooth", "chiseled", "pillared"}, "darkenedQuartz"});

		Luminescence = (new BlockLuminescence().setBlockName("tragicmc.luminescence"));
		GameRegistry.registerBlock(Luminescence, ItemBlock.class, "luminescence");

		CircuitBlock = (new BlockCircuit().setBlockName("tragicmc.circuit").setStepSound(Block.soundTypeStone));
		GameRegistry.registerBlock(CircuitBlock, TragicItemBlock.class, "circuit", new Object[] {new String[] {"live", "damaged", "veryDamaged", "dead", "aged"}, "circuit"});

		CelledBlock = (new BlockGeneric(Material.rock, "pickaxe", 1).setBlockName("tragicmc.celled").setBlockTextureName("tragicmc:CelledBlock").setHardness(6.0F).setResistance(35.0F).setStepSound(Block.soundTypeMetal));
		GameRegistry.registerBlock(CelledBlock, ItemBlock.class, "celled");

		CelledLamp = (new BlockCelledLamp().setBlockName("tragicmc.celledLamp"));
		GameRegistry.registerBlock(CelledLamp, ItemBlockCelledLamp.class, "celledLamp");

		SynapseCore = (new BlockSynapseCore().setBlockName("tragicmc.synapseCore").setBlockTextureName("tragicmc:SynapseCore").setHardness(20.0F).setResistance(45.0F).setStepSound(Block.soundTypeMetal));
		GameRegistry.registerBlock(SynapseCore, ItemBlock.class, "synapseCore");

		OverlordBarrier = (new BlockOverlordBarrier());
		GameRegistry.registerBlock(OverlordBarrier, ItemBlock.class, "overlordBarrier");

		WitheringGas = (new BlockGas().setBlockName("tragicmc.witheringGas"));
		GameRegistry.registerBlock(WitheringGas, ItemBlock.class, "witheringGas");

		CorruptedGas = (new BlockGas() {
			@Override
			public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
			{
				if (!world.isRemote && entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getCreatureAttribute() != TragicEntities.Synapse)
				{
					if (TragicConfig.allowCorruption) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 200, 0));
					entity.attackEntityFrom(DamageHelper.causeSuffocationDamageFromMob((EntityLivingBase) entity), 1.0F);
				}
			}
			
			@Override
			@SideOnly(Side.CLIENT)
			public void randomDisplayTick(World world, int x, int y, int z, Random rand)
			{
				for (int i = 0; i < 10; i++)
				{
					world.spawnParticle("smoke", x + rand.nextDouble() - rand.nextDouble(), y + (rand.nextDouble() * 0.725), z + rand.nextDouble() - rand.nextDouble(),
							0.0F, 0.0F, 0.0F);
					world.spawnParticle("smoke", x + rand.nextDouble() - rand.nextDouble(), y + (rand.nextDouble() * 0.725), z + rand.nextDouble() - rand.nextDouble(),
							0.0F, 0.0F, 0.0F);
					world.spawnParticle("witchMagic", x + rand.nextDouble() - rand.nextDouble(), y + (rand.nextDouble() * 0.725), z + rand.nextDouble() - rand.nextDouble(),
							0.0F, 0.0F, 0.0F);
				}
			}
		}.setBlockName("tragicmc.corruptedGas"));
		GameRegistry.registerBlock(CorruptedGas, ItemBlock.class, "corruptedGas");

		Conduit = (new BlockGeneric(Material.iron, "pickaxe", 0) {

			@Override
			public boolean isOpaqueCube()
			{
				return false;
			}

			@Override
			@SideOnly(Side.CLIENT)
			public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
			{
				Block block = world.getBlock(x, y, z);
				return block == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
			}
		}.setBlockName("tragicmc.conduit").setBlockTextureName("tragicmc:Conduit").setHardness(12.0F).setResistance(100.0F).setStepSound(Block.soundTypeMetal).setLightLevel(1.0F).setLightOpacity(0));
		GameRegistry.registerBlock(Conduit, ItemBlock.class, "conduit");

		DigitalSea = (new BlockDigitalSea(false));
		GameRegistry.registerBlock(DigitalSea, ItemBlock.class, "digitalSea");

		DigitalSeaPowered = (new BlockDigitalSea(true));
		GameRegistry.registerBlock(DigitalSeaPowered, null, "digitalSeaPowered");
		
		FrozenNetherrack = (new BlockGeneric(Material.rock, "pickaxe", 0).setBlockTextureName("tragicmc:FrozenNetherrack").setBlockName("tragicmc.frozenNetherrack").setHardness(1.0F).setResistance(1.0F).setStepSound(Block.soundTypeStone));
		GameRegistry.registerBlock(FrozenNetherrack, ItemBlock.class, "frozenNetherrack");
		
		Aeris = (new BlockAeris());
		GameRegistry.registerBlock(Aeris, ItemBlockAeris.class, "aeris");
		GameRegistry.registerTileEntity(TileEntityAeris.class, "aeris");

		MoltenRock = (new BlockMoltenRock());
		GameRegistry.registerBlock(MoltenRock, ItemBlock.class, "moltenRock");
		
		ScorchedRock = (new BlockGeneric(Material.rock, "pickaxe", 0).setBlockTextureName("tragicmc:MoltenRockBottom").setBlockName("tragicmc.scorchedRock").setHardness(0.8F).setResistance(10.0F));
		GameRegistry.registerBlock(ScorchedRock, ItemBlock.class, "scorchedRock");
		
		Geyser = (new BlockGeyser(false));
		GameRegistry.registerBlock(Geyser, ItemBlock.class, "geyser");
		
		GeyserSteaming = (new BlockGeyser(true));
		GameRegistry.registerBlock(GeyserSteaming, null, "geyserSteaming");
		
		SteamVent = (new BlockSteamVent());
		GameRegistry.registerBlock(SteamVent, ItemBlock.class, "steamVent");
		
		HallowedGrass = (new BlockGenericGrass("Hallowed").setBlockName("tragicmc.hallowedGrass").setLightLevel(0.25F));
		GameRegistry.registerBlock(HallowedGrass, ItemBlock.class, "hallowedGrass");
		
		StringLight = (new BlockStringLight().setBlockName("tragicmc.stringLight"));
		GameRegistry.registerBlock(StringLight, ItemBlock.class, "stringLight");
		
		FragileLight = (new BlockFragileLight(true));
		GameRegistry.registerBlock(FragileLight, ItemBlock.class, "fragileLight");
		
		FragileLightInvis = (new BlockFragileLight(false));
		GameRegistry.registerBlock(FragileLightInvis, null, "fragileLightInvis");
		
		HallowedLeaves = (new BlockGenericLeaves().setBlockName("tragicmc.hallowedLeaves").setBlockTextureName("HallowedLeaves"));
		GameRegistry.registerBlock(HallowedLeaves, ItemBlock.class, "hallowedLeaves");
		
		HallowedLeafTrim = (new BlockLeafTrim("Hallowed").setBlockName("tragicmc.hallowedLeafTrim"));
		GameRegistry.registerBlock(HallowedLeafTrim, ItemBlock.class, "hallowedLeafTrim");
		
		HallowedPlanks = (new BlockGenericPlanks().setBlockName("tragicmc.hallowedPlanks").setBlockTextureName("tragicmc:HallowedPlanks"));
		GameRegistry.registerBlock(HallowedPlanks, ItemBlock.class, "hallowedPlanks");
		
		HallowedWood = (new BlockGenericLog("Hallowed").setBlockName("tragicmc.hallowedWood"));
		GameRegistry.registerBlock(HallowedWood, ItemBlock.class, "hallowedWood");
		
		WickedVine = (new BlockWickedVine().setBlockName("tragicmc.wickedVine"));
		GameRegistry.registerBlock(WickedVine, ItemBlock.class, "wickedVine");
		
		RadiatedGas = new BlockGas() {
			@Override
			public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
			{
				if (!world.isRemote && entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getCreatureAttribute() != TragicEntities.Synapse)
				{
					entity.attackEntityFrom(DamageSource.cactus, 1.0F);
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 200, 0));
				}
			}
			
			@Override
			@SideOnly(Side.CLIENT)
			public void randomDisplayTick(World world, int x, int y, int z, Random rand)
			{
				for (int i = 0; i < 10; i++)
				{
					world.spawnParticle("reddust", x + rand.nextDouble() - rand.nextDouble(), y + (rand.nextDouble() * 0.725), z + rand.nextDouble() - rand.nextDouble(),
							0.4F, 1.0F, 0.4F);
					world.spawnParticle("reddust", x + rand.nextDouble() - rand.nextDouble(), y + (rand.nextDouble() * 0.725), z + rand.nextDouble() - rand.nextDouble(),
							0.1F, 1.0F, 0.1F);
				}
			}
			
			@Override
			public void updateTick(World world, int x, int y, int z, Random rand)
			{
				
			}
		}.setBlockName("tragicmc.radiatedGas");
		GameRegistry.registerBlock(RadiatedGas, ItemBlock.class, "radiatedGas");
		
		ExplosiveGas = new BlockGas() {
			@Override
			public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
			{
				
			}
			
			@Override
			@SideOnly(Side.CLIENT)
			public void randomDisplayTick(World world, int x, int y, int z, Random rand)
			{
				for (int i = 0; i < 10; i++)
				{
					world.spawnParticle("reddust", x + rand.nextDouble() - rand.nextDouble(), y + (rand.nextDouble() * 0.725), z + rand.nextDouble() - rand.nextDouble(),
							0.6F, 0.6F, 0.6F);
					world.spawnParticle("reddust", x + rand.nextDouble() - rand.nextDouble(), y + (rand.nextDouble() * 0.725), z + rand.nextDouble() - rand.nextDouble(),
							0.8F, 0.8F, 0.8F);
				}
			}
			
			@Override
			public void updateTick(World world, int x, int y, int z, Random rand)
			{
				Block[] block = new Block[] {world.getBlock(x, y, z), world.getBlock(x + 1, y, z), world.getBlock(x - 1, y, z),
						world.getBlock(x, y, z + 1), world.getBlock(x, y, z - 1), world.getBlock(x, y + 1, z), world.getBlock(x, y - 1, z)};
				for (Block b : block)
				{
					if (b instanceof BlockFire) world.createExplosion(null, x, y, z, 1.5F + rand.nextFloat(), WorldHelper.getMobGriefing(world));
				}
				world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
			}
			
			public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
		    {
		        if (!world.isRemote)  world.createExplosion(null, x, y, z, 1.5F + world.rand.nextFloat(), WorldHelper.getMobGriefing(world));
		    }
		}.setBlockName("tragicmc.explosiveGas");
		GameRegistry.registerBlock(ExplosiveGas, ItemBlock.class, "explosiveGas");
		
		for (int i = 0; i < 3; i++)
		{
			OreDictionary.registerOre("blockQuicksand", new ItemStack(Quicksand, 1, i));
			OreDictionary.registerOre("cobblestone", new ItemStack(LightCobblestone, 1, i));
		}

		for (int i = 0; i < 4; i++) OreDictionary.registerOre("cobblestone", new ItemStack(DarkCobblestone, 1, i));
		for (int i = 0; i < 8; i++) OreDictionary.registerOre("stone", new ItemStack(DarkStone, 1, i));

		OreDictionary.registerOre("stone", LightStone);
		OreDictionary.registerOre("stone", ErodedStone);

		OreDictionary.registerOre("materialVine", Blocks.vine);
		OreDictionary.registerOre("materialVine", GlowVine);

		OreDictionary.registerOre("oreRuby", RubyOre);
		OreDictionary.registerOre("oreSapphire", SapphireOre);
		OreDictionary.registerOre("oreTungsten", TungstenOre);
		OreDictionary.registerOre("oreMercury", MercuryOre);

		OreDictionary.registerOre("cropGiantPotato", PotatoBlock);
		OreDictionary.registerOre("cropGiantCarrot", CarrotBlock);

		OreDictionary.registerOre("logWood", PaintedWood);
		OreDictionary.registerOre("plankWood", PaintedPlanks);
		OreDictionary.registerOre("logWood", AshenWood);
		OreDictionary.registerOre("plankWood", AshenPlanks);
		OreDictionary.registerOre("logWood", BleachedWood);
		OreDictionary.registerOre("plankWood", BleachedPlanks);
		OreDictionary.registerOre("logWood", HallowedWood);
		OreDictionary.registerOre("plankWood", HallowedPlanks);

		java.util.Set<BiomeGenBase> set = FlowerWorldGen.allowedBiomes;
		BiomeGenBase[] biomes = set.toArray(new BiomeGenBase[set.size()]);
		boolean[] discrim = new boolean[16];

		for (int j = 0; j < 16; j++) discrim[j] = true;

		for (BiomeGenBase b : biomes)
		{
			boolean flag = !(b instanceof BiomeGenJungle);
			boolean flag2 = !(b instanceof BiomeGenTaiga); 
			boolean flag3 = !(b instanceof BiomeGenPlains);
			boolean flag4 = b != BiomeGenBase.roofedForest && b != BiomeGenBase.swampland;

			if (flag)
			{
				discrim[12] = false;
				discrim[4] = false;
				discrim[5] = false;
			}

			if (flag2)
			{
				discrim[13] = false;
			}

			if (flag3)
			{
				discrim[8] = false;
			}

			if (flag4)
			{
				discrim[6] = false;
				discrim[7] = false;
				discrim[15] = false;
			}

			for (int i = 0; i < 16; i++)
			{
				if (discrim[i]) b.addFlower(TragicFlower, i, i == 14 ? 1 : 10);
			}
		}
	}

}
