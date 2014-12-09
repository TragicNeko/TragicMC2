package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.BlockAir;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.biome.BiomeGenSnow;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBiomes;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockStructureSeed;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.worldgen.schematic.SchematicApisTemple;
import tragicneko.tragicmc.worldgen.schematic.SchematicCelestialTemple;
import tragicneko.tragicmc.worldgen.schematic.SchematicDeathCircle;
import tragicneko.tragicmc.worldgen.schematic.SchematicDesertTower;
import tragicneko.tragicmc.worldgen.schematic.SchematicKitsuneDen;
import tragicneko.tragicmc.worldgen.schematic.SchematicObsidianCavern;
import tragicneko.tragicmc.worldgen.schematic.SchematicTimeAltar;

public class Structure extends WorldGenerator {

	protected final int structureID;
	protected final boolean grownBySeed;
	protected final int variant2;

	public Structure(int id)
	{
		this.structureID = id;
		this.grownBySeed = false;
		this.variant2 = 20;
	}

	public Structure(int id, boolean seedGrown)
	{
		this.structureID = id;
		this.grownBySeed = seedGrown;
		this.variant2 = 20;
	}
	
	public Structure(int id, boolean seedGrown, int variant2)
	{
		this.structureID = id;
		this.grownBySeed = seedGrown;
		this.variant2 = variant2;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
		if (this.hasVariants())
		{
			return generateStructureWithVariant(rand.nextInt(this.getVariantSize()), world, rand, x, y, z);
		}
		else
		{
			return generateStructure(world, rand, x, y, z);
		}
	}

	/**
	 * If the structure spawns a boss (some trap structures may have a boss as part of the trap)
	 * @return
	 */
	public boolean isBossStructure()
	{
		switch(this.structureID)
		{
		case 0:
			return true;
		case 2:
			return true;
		case 3:
			return true;
		case 4:
			return true;
		case 5:
			return true;
		default:
			return false;
		}
	}

	/**
	 * If the structure has variants
	 * @return
	 */
	public boolean hasVariants()
	{
		switch(this.structureID)
		{
		case 1:
			return true;
		case 3:
			return true;
		default:
			return false;
		}
	}

	/**
	 * Gets the total amount of variants the structure has or 1 if there are no variants
	 * @return
	 */
	public int getVariantSize()
	{
		if (this.hasVariants())
		{
			switch(this.structureID)
			{
			case 1:
				return 5;
			case 3:
				return 7;
			default:
				return 1;
			}
		}
		return 1;
	}

	public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z)
	{
		if (world.isRemote)
		{
			return false;
		}

		if (!this.checkCoordinatesForProperSpace(world, x, y, z, rand) && !this.grownBySeed)
		{
			return false;
		}

		switch(this.structureID)
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
				if (world.getBiomeGenForCoords(x, z) instanceof BiomeGenHell || TragicBiomes.decayingBiomes.contains(world.getBiomeGenForCoords(x, z)))
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
				new SchematicObsidianCavern(variant, variant2, world, rand, x, y, z);
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
		}

	}

	private boolean checkCoordinatesForProperSpace(World world, int x, int y, int z, Random rand) 
	{
		for (int y1 = 0; y1 < 6; y1++)
		{
			if (!(world.getBlock(x, y + y1 + 1, z) instanceof BlockAir) && !(world.getBlock(x, y + y1 + 1, z) instanceof BlockStructureSeed))
			{
				return false;
			}
		}

		for (int y1 = 0; y1 < 3; y1++)
		{
			if (!StructureWorldGen.validBlocks.contains(world.getBlock(x, y - y1, z)) && !(world.getBlock(x, y - y1, z) instanceof BlockStructureSeed))
			{
				return false;
			}

			if (!StructureWorldGen.validBlocks.contains(world.getBlock(x - y1, y, z)) && !(world.getBlock(x - y1, y, z) instanceof BlockStructureSeed))
			{
				return false;
			}

			if (!StructureWorldGen.validBlocks.contains(world.getBlock(x + y1, y, z)) && !(world.getBlock(x + y1, y, z) instanceof BlockStructureSeed))
			{
				return false;
			}

			if (!StructureWorldGen.validBlocks.contains(world.getBlock(x, y, z + y1)) && !(world.getBlock(x, y, z + y1) instanceof BlockStructureSeed))
			{
				return false;
			}

			if (!StructureWorldGen.validBlocks.contains(world.getBlock(x, y , z - y1)) && !(world.getBlock(x, y, z - y1) instanceof BlockStructureSeed))
			{
				return false;
			}
		}
		return true;
	}

	public boolean generateStructure(World world, Random rand, int x, int y, int z)
	{
		return generateStructureWithVariant(0, world, rand, x, y, z);
	}



}
