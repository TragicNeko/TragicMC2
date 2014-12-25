package tragicneko.tragicmc;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraftforge.oredict.OreDictionary;
import tragicneko.tragicmc.blocks.BlockBone;
import tragicneko.tragicmc.blocks.BlockCandle;
import tragicneko.tragicmc.blocks.BlockCelledLamp;
import tragicneko.tragicmc.blocks.BlockCircuit;
import tragicneko.tragicmc.blocks.BlockDarkCobble;
import tragicneko.tragicmc.blocks.BlockDarkSand;
import tragicneko.tragicmc.blocks.BlockDarkStone;
import tragicneko.tragicmc.blocks.BlockDarkenedQuartz;
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
import tragicneko.tragicmc.blocks.BlockLuminescence;
import tragicneko.tragicmc.blocks.BlockNetherBrickPressurePlate;
import tragicneko.tragicmc.blocks.BlockObsidianVariant;
import tragicneko.tragicmc.blocks.BlockOverlordBarrier;
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
import tragicneko.tragicmc.blocks.BlockGas;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockCelledLamp;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockDarkStone;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockOres;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockStarCrystal;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockStructureSeeds;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockSummonBlocks;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockTragicFlower;
import tragicneko.tragicmc.blocks.itemblocks.ItemBlockTragicSapling;
import tragicneko.tragicmc.blocks.itemblocks.TragicItemBlock;
import tragicneko.tragicmc.blocks.tileentity.TileEntityStructureSeed;
import tragicneko.tragicmc.blocks.tileentity.TileEntitySummonBlock;
import tragicneko.tragicmc.blocks.tileentity.TileEntityTimeDisruptor;
import tragicneko.tragicmc.worldgen.FlowerWorldGen;
import cpw.mods.fml.common.registry.GameRegistry;

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
	
	public static Block DeadDirt;
	public static Block DarkSand;

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
	
	public static Block Gas;

	public static void load()
	{		
		MercuryOre = (new BlockGenericOre(1, true).setCreativeTab(TragicTabs.Survival).setBlockTextureName("tragicmc:MercuryOre_lowRes").setBlockName("tragicmc.mercuryOre").setHardness(4.0F).setResistance(5.0F));
		GameRegistry.registerBlock(MercuryOre, ItemBlock.class, "mercuryOre");

		TungstenOre = (new BlockGenericOre(2, true).setCreativeTab(TragicTabs.Survival).setBlockTextureName("tragicmc:TungstenOre_lowRes").setBlockName("tragicmc.tungstenOre").setHardness(4.0F).setResistance(5.0F));
		GameRegistry.registerBlock(TungstenOre, ItemBlock.class, "tungstenOre");

		RubyOre = (new BlockGenericOre(3, false).setCreativeTab(TragicTabs.Survival).setBlockTextureName("tragicmc:RubyNetherOre_lowRes").setBlockName("tragicmc.rubyOre").setHardness(6.0F).setResistance(7.0F));
		GameRegistry.registerBlock(RubyOre, ItemBlock.class, "rubyOre");

		SapphireOre = (new BlockGenericOre(3, false).setCreativeTab(TragicTabs.Survival).setBlockTextureName("tragicmc:SapphireNetherOre_lowRes").setBlockName("tragicmc.sapphireOre").setHardness(6.0F).setResistance(7.0F));
		GameRegistry.registerBlock(SapphireOre, ItemBlock.class, "sapphireOre");

		CompactOre = (new BlockStorage());
		GameRegistry.registerBlock(CompactOre, TragicItemBlock.class, "compactOre", new Object[] {new String[] {"ruby", "sapphire", "tungsten", "mercury", "quicksilver"}, "compactOre"});

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
		GameRegistry.registerBlock(Quicksand, TragicItemBlock.class, "quicksand", new Object[] {new String[] {"quicksand", "mud", "netherDrudge"}, "quicksand"});

		DarkStone = (new BlockDarkStone());
		GameRegistry.registerBlock(DarkStone, ItemBlockDarkStone.class, "darkStone");

		DarkCobblestone = (new BlockDarkCobble());
		GameRegistry.registerBlock(DarkCobblestone, TragicItemBlock.class, "darkCobblestone", new Object[] {new String[] {"normal", "hot", "toxic", "ashen"}, "darkCobblestone"});

		LightCobblestone = (new BlockLightCobble());
		GameRegistry.registerBlock(LightCobblestone, TragicItemBlock.class, "lightCobblestone", new Object[] {new String[] {"normal", "frozen", "glowing"}, "lightCobblestone"});
		
		LightStone = (new BlockGeneric(Material.rock, "pickaxe", 0).setBlockTextureName("tragicmc:SmoothLightStone_lowRes").setBlockName("tragicmc.lightStone").setHardness(1.0F).setResistance(1.0F).setStepSound(Block.soundTypeStone));
		GameRegistry.registerBlock(LightStone, ItemBlock.class, "lightStone");

		TragicObsidian = (new BlockObsidianVariant());
		GameRegistry.registerBlock(TragicObsidian, TragicItemBlock.class, "obsidian", new Object[] {new String[] {"crying", "bleeding", "dying"}, "obsidian"});
		
		DeadDirt = (new BlockDeadDirt().setBlockName("tragicmc.deadDirt").setBlockTextureName("tragicmc:DeadDirt_lowRes"));
		GameRegistry.registerBlock(DeadDirt, TragicItemBlock.class, "deadDirt", new Object[] {new String[] {"normal", "rugged", "mixed"}, "deadDirt"});
		
		DarkSand = (new BlockDarkSand().setBlockName("tragicmc.darkSand").setBlockTextureName("tragicmc:DarkSand_lowRes"));
		GameRegistry.registerBlock(DarkSand, ItemBlock.class, "darkSand");
		
		TimeDisruptionCube = (new BlockTimeDisruptor().setBlockName("tragicmc.timeDisruptor").setBlockTextureName("tragicmc:DimensionBlock_lowRes"));
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
		GameRegistry.registerBlock(ErodedStone, TragicItemBlock.class, "erodedStone", new Object[] {new String[] {"smooth", "carved", "scattered"}, "erodedStone"});
		
		DarkenedQuartz = (new BlockDarkenedQuartz());
		GameRegistry.registerBlock(DarkenedQuartz, TragicItemBlock.class, "darkenedQuartz", new Object[] {new String[] {"smooth", "chiseled", "pillared"}, "darkenedQuartz"});
		
		Luminescence = (new BlockLuminescence().setBlockName("tragicmc.luminescence"));
		GameRegistry.registerBlock(Luminescence, ItemBlock.class, "luminescence");
		
		CircuitBlock = (new BlockCircuit().setBlockName("tragicmc.circuit").setStepSound(Block.soundTypeStone));
		GameRegistry.registerBlock(CircuitBlock, TragicItemBlock.class, "circuit", new Object[] {new String[] {"live", "damaged", "veryDamaged", "dead", "aged"}, "circuit"});
		
		CelledBlock = (new BlockGeneric(Material.rock, "pickaxe", 1).setBlockName("tragicmc.celled").setBlockTextureName("tragicmc:CelledBlock_lowRes").setHardness(6.0F).setResistance(35.0F).setStepSound(Block.soundTypeMetal));
		GameRegistry.registerBlock(CelledBlock, ItemBlock.class, "celled");
		
		CelledLamp = (new BlockCelledLamp().setBlockName("tragicmc.celledLamp"));
		GameRegistry.registerBlock(CelledLamp, ItemBlockCelledLamp.class, "celledLamp");
		
		SynapseCore = (new BlockGeneric(Material.iron, "pickaxe", 0).setBlockName("tragicmc.synapseCore").setBlockTextureName("tragicmc:SynapseCore_lowRes").setHardness(20.0F).setResistance(45.0F).setStepSound(Block.soundTypeMetal));
		GameRegistry.registerBlock(SynapseCore, ItemBlock.class, "synapseCore");
		
		OverlordBarrier = (new BlockOverlordBarrier());
		GameRegistry.registerBlock(OverlordBarrier, ItemBlock.class, "overlordBarrier");
		
		Gas = (new BlockGas().setBlockName("tragicmc.gas"));
		GameRegistry.registerBlock(Gas, ItemBlock.class, "gas");
		
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
