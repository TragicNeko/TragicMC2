package tragicneko.tragicmc.main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tragicneko.tragicmc.blocks.BlockBone;
import tragicneko.tragicmc.blocks.BlockCandle;
import tragicneko.tragicmc.blocks.BlockDarkCobble;
import tragicneko.tragicmc.blocks.BlockDarkSand;
import tragicneko.tragicmc.blocks.BlockDarkStone;
import tragicneko.tragicmc.blocks.BlockDeadDirt;
import tragicneko.tragicmc.blocks.BlockDisappearing;
import tragicneko.tragicmc.blocks.BlockErodedStone;
import tragicneko.tragicmc.blocks.BlockFox;
import tragicneko.tragicmc.blocks.BlockGeneric;
import tragicneko.tragicmc.blocks.BlockGenericBush;
import tragicneko.tragicmc.blocks.BlockGenericGrass;
import tragicneko.tragicmc.blocks.BlockGenericLeaves;
import tragicneko.tragicmc.blocks.BlockGenericLog;
import tragicneko.tragicmc.blocks.BlockGenericOre;
import tragicneko.tragicmc.blocks.BlockGenericPlanks;
import tragicneko.tragicmc.blocks.BlockGenericTallGrass;
import tragicneko.tragicmc.blocks.BlockGiantCrop;
import tragicneko.tragicmc.blocks.BlockGlowvine;
import tragicneko.tragicmc.blocks.BlockLight;
import tragicneko.tragicmc.blocks.BlockLightCobble;
import tragicneko.tragicmc.blocks.BlockMagnetar;
import tragicneko.tragicmc.blocks.BlockNetherBrickPressurePlate;
import tragicneko.tragicmc.blocks.BlockObsidianVariant;
import tragicneko.tragicmc.blocks.BlockPulsar;
import tragicneko.tragicmc.blocks.BlockDarkenedQuartz;
import tragicneko.tragicmc.blocks.BlockQuicksand;
import tragicneko.tragicmc.blocks.BlockSandstonePressurePlate;
import tragicneko.tragicmc.blocks.BlockStarCrystal;
import tragicneko.tragicmc.blocks.BlockStorage;
import tragicneko.tragicmc.blocks.BlockStructureSeed;
import tragicneko.tragicmc.blocks.BlockSummon;
import tragicneko.tragicmc.blocks.BlockTimeDisruptor;
import tragicneko.tragicmc.blocks.BlockTragicFlower;
import tragicneko.tragicmc.blocks.BlockTragicOres;
import tragicneko.tragicmc.blocks.BlockTragicSapling;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockBone;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockDarkCobble;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockDarkStone;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockDeadDirt;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockErodedStone;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockFox;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockLightCobble;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockObsidianVariants;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockOres;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockDarkenedQuartz;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockQuicksandBlocks;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockStarCrystal;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockStorageBlocks;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockStructureSeeds;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockSummonBlocks;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockTragicFlower;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockTragicSapling;
import tragicneko.tragicmc.blocks.tileentity.TileEntityMagnetar;
import tragicneko.tragicmc.blocks.tileentity.TileEntityPulsar;
import tragicneko.tragicmc.blocks.tileentity.TileEntityStructureSeed;
import tragicneko.tragicmc.blocks.tileentity.TileEntitySummonBlock;
import tragicneko.tragicmc.blocks.tileentity.TileEntityTimeDisruptor;
import cpw.mods.fml.common.registry.GameRegistry;

public class TragicBlocks {

	public static Block MercuryOre;
	public static Block TungstenOre;
	public static Block RubyOre;
	public static Block SapphireOre;

	public static Block StorageBlock;

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
	
	public static Block DeadDirt;
	public static Block DarkSand;

	public static Block WickedVine;
	public static Block GlowVine;
	public static Block Root;

	public static Block TragicObsidian;
	
	public static Block Pulsar;
	public static Block Magnetar;
	
	public static Block WeatherBlock; //Dehydrator, Lightning Rod, Cooler, Heater
	
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

	public static void load()
	{		
		MercuryOre = (new BlockGenericOre(1, true).setCreativeTab(TragicTabs.Survival).setBlockTextureName("tragicmc:MercuryOre_lowRes").setBlockName("tragicmc.mercuryOre").setHardness(4.0F).setResistance(5.0F));
		GameRegistry.registerBlock(MercuryOre, ItemBlock.class, "mercuryOreBlock");

		TungstenOre = (new BlockGenericOre(2, true).setCreativeTab(TragicTabs.Survival).setBlockTextureName("tragicmc:TungstenOre_lowRes").setBlockName("tragicmc.tungstenOre").setHardness(4.0F).setResistance(5.0F));
		GameRegistry.registerBlock(TungstenOre, ItemBlock.class, "tungstenOreBlock");

		RubyOre = (new BlockGenericOre(3, false).setCreativeTab(TragicTabs.Survival).setBlockTextureName("tragicmc:RubyNetherOre_lowRes").setBlockName("tragicmc.rubyOre").setHardness(6.0F).setResistance(7.0F));
		GameRegistry.registerBlock(RubyOre, ItemBlock.class, "rubyOreBlock");

		SapphireOre = (new BlockGenericOre(3, false).setCreativeTab(TragicTabs.Survival).setBlockTextureName("tragicmc:SapphireNetherOre_lowRes").setBlockName("tragicmc.sapphireOre").setHardness(6.0F).setResistance(7.0F));
		GameRegistry.registerBlock(SapphireOre, ItemBlock.class, "sapphireOreBlock");

		StorageBlock = (new BlockStorage());
		GameRegistry.registerBlock(StorageBlock, ItemBlockStorageBlocks.class, "storageBlocks");

		Wax = (((BlockGeneric) (new BlockGeneric(Material.clay, "spade", 0).setBlockTextureName("tragicmc:Wax_lowRes").setBlockName("tragicmc.wax").setHardness(1.0F).setResistance(1.0F).setStepSound(Block.soundTypeStone).setLightOpacity(5))).setRenderPass(1));
		GameRegistry.registerBlock(Wax, ItemBlock.class, "wax");

		Light = (new BlockLight().setCreativeTab(TragicTabs.Survival).setBlockName("tragicmc.light").setStepSound(Block.soundTypeGlass));
		GameRegistry.registerBlock(Light, ItemBlock.class, "light");

		Candle = (new BlockCandle().setCreativeTab(TragicTabs.Survival).setBlockName("tragicmc.candle"));
		GameRegistry.registerBlock(Candle, ItemBlock.class, "candle");

		PotatoBlock = (new BlockGiantCrop().setBlockName("tragicmc.potatoBlock").setBlockTextureName("PotatoBlock"));
		GameRegistry.registerBlock(PotatoBlock, ItemBlock.class, "potatoBlock");

		CarrotBlock = (new BlockGiantCrop().setBlockName("tragicmc.carrotBlock").setBlockTextureName("CarrotBlock"));
		GameRegistry.registerBlock(CarrotBlock, ItemBlock.class, "carrotBlock");

		SandstonePressurePlate = (new BlockSandstonePressurePlate().setBlockName("tragicmc.sandstonePressurePlate"));
		GameRegistry.registerBlock(SandstonePressurePlate, ItemBlock.class, "sandstonePressurePlate");

		NetherBrickPressurePlate = (new BlockNetherBrickPressurePlate().setBlockName("tragicmc.netherBrickPressurePlate"));
		GameRegistry.registerBlock(NetherBrickPressurePlate, ItemBlock.class, "netherBrickPressurePlate");

		SummonBlock = (new BlockSummon());
		GameRegistry.registerBlock(SummonBlock, ItemBlockSummonBlocks.class, "summonBlocks");
		
		GameRegistry.registerTileEntity(TileEntitySummonBlock.class, "summonBlock");

		StructureSeed = (new BlockStructureSeed());
		GameRegistry.registerBlock(StructureSeed, ItemBlockStructureSeeds.class, "structureSeeds");
		
		GameRegistry.registerTileEntity(TileEntityStructureSeed.class, "structureSeed");

		DisappearingBlock = (new BlockDisappearing(false).setBlockName("tragicmc.disappearingBlock"));
		GameRegistry.registerBlock(DisappearingBlock, ItemBlock.class, "disappearingBlock");

		DisappearingBlockInvis = (new BlockDisappearing(true).setBlockName("tragicmc.disappearingBlockInvis"));
		GameRegistry.registerBlock(DisappearingBlockInvis, null, "disappearingBlockInvis");

		Quicksand = (new BlockQuicksand());
		GameRegistry.registerBlock(Quicksand, ItemBlockQuicksandBlocks.class, "quicksandBlocks");

		DarkStone = (new BlockDarkStone());
		GameRegistry.registerBlock(DarkStone, ItemBlockDarkStone.class, "darkStoneBlocks");

		DarkCobblestone = (new BlockDarkCobble());
		GameRegistry.registerBlock(DarkCobblestone, ItemBlockDarkCobble.class, "darkCobbleBlocks");

		LightCobblestone = (new BlockLightCobble());
		GameRegistry.registerBlock(LightCobblestone, ItemBlockLightCobble.class, "lightCobble");

		TragicObsidian = (new BlockObsidianVariant());
		GameRegistry.registerBlock(TragicObsidian, ItemBlockObsidianVariants.class, "obsidianVariants");

		Pulsar = (new BlockPulsar().setBlockName("tragicmc.pulsar").setBlockTextureName("tragicmc:Pulsar_lowRes"));
		GameRegistry.registerBlock(Pulsar, ItemBlock.class, "pulsar");
		
		Magnetar = (new BlockMagnetar().setBlockName("tragicmc.magnetar").setBlockTextureName("tragicmc:Magnetar_lowRes"));
		GameRegistry.registerBlock(Magnetar, ItemBlock.class, "magnetar");

		GameRegistry.registerTileEntity(TileEntityPulsar.class, "pulsar");
		GameRegistry.registerTileEntity(TileEntityMagnetar.class, "magnetar");
		
		DeadDirt = (new BlockDeadDirt().setBlockName("tragicmc.deadDirt").setBlockTextureName("tragicmc:DeadDirt_lowRes"));
		GameRegistry.registerBlock(DeadDirt, ItemBlockDeadDirt.class, "deadDirt");
		
		DarkSand = (new BlockDarkSand().setBlockName("tragicmc.darkSand").setBlockTextureName("tragicmc:DarkSand_lowRes"));
		GameRegistry.registerBlock(DarkSand, ItemBlock.class, "darkSand");
		
		TimeDisruptionCube = (new BlockTimeDisruptor().setBlockName("tragicmc.timeDisruptor").setBlockTextureName("tragicmc:DimensionBlock_lowRes"));
		GameRegistry.registerBlock(TimeDisruptionCube, ItemBlock.class, "timeDisruptor");
		
		GameRegistry.registerTileEntity(TileEntityTimeDisruptor.class, "timeDisruptor");
		
		TragicOres = (new BlockTragicOres());
		GameRegistry.registerBlock(TragicOres, ItemBlockOres.class, "tragicOres");
		
		BoneBlock = (new BlockBone());
		GameRegistry.registerBlock(BoneBlock, ItemBlockBone.class, "boneBlock");
		
		SmoothNetherrack = (new BlockFox());
		GameRegistry.registerBlock(SmoothNetherrack, ItemBlockFox.class, "foxBlock");
		
		BrushedGrass = (new BlockGenericGrass("Brushed").setBlockName("tragicmc.brushedGrass"));
		GameRegistry.registerBlock(BrushedGrass, ItemBlock.class, "brushedGrass");
		
		PaintedWood = (new BlockGenericLog("Painted").setBlockName("tragicmc.paintedWood"));
		GameRegistry.registerBlock(PaintedWood, ItemBlock.class, "paintedWood");
		
		PaintedLeaves = (new BlockGenericLeaves().setBlockName("tragicmc.paintedLeaves").setBlockTextureName("PaintedLeaves_lowRes"));
		GameRegistry.registerBlock(PaintedLeaves, ItemBlock.class, "paintedLeaves");

		PaintedPlanks = (new BlockGenericPlanks().setBlockName("tragicmc.paintedPlanks").setBlockTextureName("tragicmc:PaintedPlanks_lowRes"));
		GameRegistry.registerBlock(PaintedPlanks, ItemBlock.class, "paintedPlanks");
		
		GlowVine = (new BlockGlowvine().setBlockName("tragicmc.glowvine").setBlockTextureName("tragicmc:GlowVine_lowRes"));
		GameRegistry.registerBlock(GlowVine, ItemBlock.class, "glowvine");
		
		PaintedTallGrass = (new BlockGenericTallGrass("Painted").setBlockName("tragicmc.paintedTallGrass"));
		GameRegistry.registerBlock(PaintedTallGrass, ItemBlock.class, "paintedTallGrass");
		
		AshenGrass = (new BlockGenericGrass("Ashen").setBlockName("tragicmc.ashenGrass"));
		GameRegistry.registerBlock(AshenGrass, ItemBlock.class, "ashenGrass");
		
		AshenWood = (new BlockGenericLog("Ashen").setBlockName("tragicmc.ashenWood"));
		GameRegistry.registerBlock(AshenWood, ItemBlock.class, "ashenWood");
		
		AshenLeaves = (new BlockGenericLeaves().setBlockName("tragicmc.ashenLeaves").setBlockTextureName("AshenLeaves"));
		GameRegistry.registerBlock(AshenLeaves, ItemBlock.class, "ashenLeaves");
		
		AshenPlanks = (new BlockGenericPlanks().setBlockName("tragicmc.ashenPlanks").setBlockTextureName("tragicmc:AshenPlanks_lowRes"));
		GameRegistry.registerBlock(AshenPlanks, ItemBlock.class, "ashenPlanks");
		
		BleachedWood = (new BlockGenericLog("Bleached").setBlockName("tragicmc.bleachedWood"));
		GameRegistry.registerBlock(BleachedWood, ItemBlock.class, "bleachedWood");
		
		BleachedLeaves = (new BlockGenericLeaves().setBlockName("tragicmc.bleachedLeaves").setBlockTextureName("BleachedLeaves_lowRes"));
		GameRegistry.registerBlock(BleachedLeaves, ItemBlock.class, "bleachedLeaves");
		
		BleachedPlanks = (new BlockGenericPlanks().setBlockName("tragicmc.bleachedPlanks").setBlockTextureName("tragicmc:BleachedPlanks_lowRes"));
		GameRegistry.registerBlock(BleachedPlanks, ItemBlock.class, "bleachedPlanks");
		
		TragicSapling = (new BlockTragicSapling());
		GameRegistry.registerBlock(TragicSapling, ItemBlockTragicSapling.class, "tragicSapling");
		
		TragicFlower = (new BlockTragicFlower());
		GameRegistry.registerBlock(TragicFlower, ItemBlockTragicFlower.class, "tragicFlower");
		
		AshenBush = (new BlockGenericBush().setBlockName("tragicmc.ashenBush").setBlockTextureName("tragicmc:AshenBush_lowRes"));
		GameRegistry.registerBlock(AshenBush, ItemBlock.class, "ashenBush");
		
		DeadBush = (new BlockGenericBush().setBlockName("tragicmc.deadBush").setBlockTextureName("tragicmc:DeadBush_lowRes"));
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
		GameRegistry.registerBlock(ErodedStone, ItemBlockErodedStone.class, "erodedStone");
		
		DarkenedQuartz = (new BlockDarkenedQuartz());
		GameRegistry.registerBlock(DarkenedQuartz, ItemBlockDarkenedQuartz.class, "darkenedQuartz");
		
		for (int i = 0; i < 3; i++)
		{
			OreDictionary.registerOre("blockQuicksand", new ItemStack(Quicksand, 1, i));
			OreDictionary.registerOre("cobblestone", new ItemStack(DarkCobblestone, 1, i));
			OreDictionary.registerOre("cobblestone", new ItemStack(LightCobblestone, 1, i));
		}
		
		for (int i = 0; i < 8; i++)
		{
			OreDictionary.registerOre("stone", new ItemStack(DarkStone, 1, i));
		}
		
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
		
		OreDictionary.registerOre("treeSapling", new ItemStack(TragicSapling, 1, 0));
	}

}
