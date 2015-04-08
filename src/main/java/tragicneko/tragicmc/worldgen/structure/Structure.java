package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.blocks.BlockStructureSeed;
import tragicneko.tragicmc.worldgen.schematic.Schematic;

import com.google.common.collect.Sets;

public class Structure extends WorldGenerator {

	public final Schematic schematic;
	public final int structureId;
	protected final int height;
	protected final int width;
	
	public static Structure[] structureList = new Structure[16];
	public static Structure apisTemple = new StructureApisTemple(0);
	//public static Structure towerStructure = new StructureTower(1);
	
	public static final Set validBlocks = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.tallgrass, Blocks.yellow_flower, Blocks.red_flower, Blocks.double_plant,
			Blocks.snow_layer, Blocks.snow, Blocks.stone, Blocks.sand, Blocks.air, Blocks.netherrack, TragicBlocks.Quicksand, Blocks.ice, Blocks.water, Blocks.lava,
			Blocks.dirt, Blocks.gravel, Blocks.clay, Blocks.hardened_clay, TragicBlocks.DarkStone, TragicBlocks.DarkSand, TragicBlocks.DeadDirt, Blocks.leaves,
			Blocks.packed_ice, TragicBlocks.AshenGrass, TragicBlocks.BrushedGrass, TragicBlocks.AshenLeaves, TragicBlocks.AshenTallGrass, TragicBlocks.DeadBush,
			TragicBlocks.AshenBush, TragicBlocks.BleachedLeaves, TragicBlocks.PaintedLeaves, TragicBlocks.GlowVine, TragicBlocks.DriedGrass, TragicBlocks.PaintedTallGrass,
			TragicBlocks.StarlitGrass, TragicBlocks.StarlitTallGrass, TragicBlocks.StarCrystal, TragicBlocks.ErodedStone, TragicBlocks.DarkCobblestone,
			TragicBlocks.HallowedGrass, TragicBlocks.HallowedLeaves, TragicBlocks.HallowedLeafTrim, TragicBlocks.MoltenRock, TragicBlocks.ScorchedRock});

	public Structure(Schematic sch, int id)
	{
		this.schematic = sch;
		structureList[id] = this;
		this.structureId = id;
		this.height = sch.height;
		this.width = sch.width;
	}
	
	/**
	 * Override to set a variant amount for a particular structure
	 * @return
	 */
	public int getVariantSize()
	{
		return 1;
	}
	
	/**
	 * Whether or not this particular structure should only generate on a solid surface
	 * @return
	 */
	public boolean isSurfaceStructure()
	{
		return false;
	}
	
	/**
	 * Check if the structure is in the correct dimension
	 * @param dim
	 * @return
	 */
	public boolean isValidDimension(int dim)
	{
		return true;
	}
	
	/**
	 * Check if the starting coords for the structure are valid, may check a larger area as well
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param rand
	 * @return
	 */
	public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand, int radius, int height)
	{
		if (this.isSurfaceStructure() && World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)) return false;
		
		for (int y1 = 0; y1 < radius; y1++)
		{
			if (!(world.getBlock(x, y + y1 + 1, z) instanceof BlockAir) && !(world.getBlock(x, y + y1 + 1, z) instanceof BlockStructureSeed))
			{
				return false;
			}
		}

		for (int y1 = 0; y1 < height; y1++)
		{
			if (!validBlocks.contains(world.getBlock(x, y - y1, z)) && !(world.getBlock(x, y - y1, z) instanceof BlockStructureSeed))
			{
				return false;
			}

			if (!validBlocks.contains(world.getBlock(x - y1, y, z)) && !(world.getBlock(x - y1, y, z) instanceof BlockStructureSeed))
			{
				return false;
			}

			if (!validBlocks.contains(world.getBlock(x + y1, y, z)) && !(world.getBlock(x + y1, y, z) instanceof BlockStructureSeed))
			{
				return false;
			}

			if (!validBlocks.contains(world.getBlock(x, y, z + y1)) && !(world.getBlock(x, y, z + y1) instanceof BlockStructureSeed))
			{
				return false;
			}

			if (!validBlocks.contains(world.getBlock(x, y , z - y1)) && !(world.getBlock(x, y, z - y1) instanceof BlockStructureSeed))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Specific checks for particular structures, this should be where to check things like if the structure is allowed in the config or if a valid variant
	 * id is set, etc.
	 * @param rand
	 * @param variantID
	 * @return
	 */
	public boolean canGenerate()
	{
		return true;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
		return generateStructureWithVariant(rand.nextInt(this.getVariantSize()), world, rand, x, y, z);
	}


	public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z)
	{
		if (world.isRemote || !this.canGenerate()) return false;
		//this.schematic.generateStructure(variant, world, rand, x, y, z);
		return true;
		/*

		switch(world.rand.nextInt(16))
		{
		case 0: //Apis Temple Structure
			if (!this.grownBySeed)
			{
				if (world.getBiomeGenForCoords(x, z) instanceof BiomeGenPlains || world.getBiomeGenForCoords(x, z) instanceof BiomeGenSavanna)
				{
					new SchematicApisTemple(variant, world, rand, x, y, z);
					TragicMC.logInfo("Apis Temple successfully generated at coords: " + x + ", " + y + ", " + z);
					return true;
				}
				else
				{
					return false;
				} 
			}
			else
			{
				new SchematicApisTemple(variant, world, rand, x, y, z);
				return true;
			}
		case 1: //Tower Structure
			if (!this.grownBySeed)
			{
				variant = -1;

				if (world.getBiomeGenForCoords(x, z) instanceof BiomeGenDesert || world.getBiomeGenForCoords(x, z) instanceof BiomeGenBeach)
				{
					variant = 0;
				}

				if (world.getBiomeGenForCoords(x, z) instanceof BiomeGenMesa)
				{
					variant = 1;
				}

				if (world.getBiomeGenForCoords(x, z) instanceof BiomeGenPlains || world.getBiomeGenForCoords(x, z) instanceof BiomeGenForest)
				{
					variant = 2;
				}

				if (world.getBiomeGenForCoords(x, z) instanceof BiomeGenHell)
				{
					variant = 3;
				}

				if (world.getBiomeGenForCoords(x, z) == BiomeGenBase.coldTaiga || world.getBiomeGenForCoords(x, z) == BiomeGenBase.coldTaigaHills ||
						world.getBiomeGenForCoords(x, z) instanceof BiomeGenSnow)
				{
					variant = 4;
				}

				if (variant >= 0)
				{
					new SchematicDesertTower(variant, world, rand, x, y, z);
					TragicMC.logInfo("Tower Structure successfully generated at coords: " + x + ", " + y + ", " + z);
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				new SchematicDesertTower(variant, world, rand, x, y, z);
				return true;
			}
		case 2: //Death Circle structure
			if (!this.grownBySeed)
			{
				if (world.getBiomeGenForCoords(x, z) instanceof BiomeGenHell || world.getBiomeGenForCoords(x, z) instanceof BiomeGenDecayingWasteland)
				{
					new SchematicDeathCircle(variant, world, rand, x, y, z);
					TragicMC.logInfo("Death Circle successfully generated at coords: " + x + ", " + y + ", " + z);
					return true;
				}
				else
				{
					return false;
				} 
			}
			else
			{
				new SchematicDeathCircle(variant, world, rand, x, y, z);
				return true;
			}
		case 3: //Obsidian Cavern structure
			if (!this.grownBySeed)
			{
				if (!(world.provider instanceof TragicWorldProvider))
				{
					return false;
				}

				new SchematicObsidianCavern(variant, world, rand, x, y, z);
				TragicMC.logInfo("Obsidian Cavern successfully generated at coords: " + x + ", " + y + ", " + z);
				return true;
			}
			else
			{
				new SchematicObsidianCavern(variant, 0, world, rand, x, y, z);
				return true;
			}
		case 4: //Kitsune Den structure
			if (!this.grownBySeed)
			{
				if (world.getBiomeGenForCoords(x, z) instanceof BiomeGenHell || world.provider instanceof TragicWorldProvider)
				{
					new SchematicKitsuneDen(variant, world, rand, x, y, z);
					TragicMC.logInfo("Kitsune Den successfully generated at coords: " + x + ", " + y + ", " + z);
					return true;
				}
				else
				{
					return false;
				} 
			}
			else
			{
				new SchematicKitsuneDen(variant, world, rand, x, y, z);
				return true;
			}
		case 5: //Celestial Temple
			if (!this.grownBySeed)
			{
				if (world.provider instanceof TragicWorldProvider)
				{
					new SchematicCelestialTemple(variant, world, rand, x, y, z);
					TragicMC.logInfo("Celestial Temple successfully generated at coords: " + x + ", " + y + ", " + z);
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				new SchematicCelestialTemple(variant, world, rand, x, y, z);
				return true;
			}
		case 6: //Time Altar
			if (!this.grownBySeed)
			{
				if (world.provider instanceof TragicWorldProvider)
				{
					new SchematicTimeAltar(variant, world, rand, x, y, z);
					TragicMC.logInfo("Time Altar successfully generated at coords: " + x + ", " + y + ", " + z);
					return true;
				}
				else
				{
					return false;
				} 
			}
			else
			{
				new SchematicTimeAltar(variant, world, rand, x, y, z);
				return true;
			}
		case 7: //Yeti Pit
		default:
			return false;
		} */

	}
}
