package tragicneko.tragicmc.worldgen;

import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.worldgen.structure.Structure;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.IWorldGenerator;

public class StructureWorldGen implements IWorldGenerator {

	public static final Set validBlocks = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.tallgrass, Blocks.yellow_flower, Blocks.red_flower, Blocks.double_plant,
			Blocks.snow_layer, Blocks.snow, Blocks.stone, Blocks.sand, Blocks.air, Blocks.netherrack, TragicBlocks.Quicksand, Blocks.ice, Blocks.water, Blocks.lava,
			Blocks.dirt, Blocks.gravel, Blocks.clay, Blocks.hardened_clay, TragicBlocks.DarkStone, TragicBlocks.DarkSand, TragicBlocks.DeadDirt, Blocks.leaves,
			Blocks.packed_ice, TragicBlocks.AshenGrass, TragicBlocks.BrushedGrass, TragicBlocks.AshenLeaves, TragicBlocks.AshenTallGrass, TragicBlocks.DeadBush,
			TragicBlocks.AshenBush, TragicBlocks.BleachedLeaves, TragicBlocks.PaintedLeaves, TragicBlocks.GlowVine, TragicBlocks.DriedGrass, TragicBlocks.PaintedTallGrass,
			TragicBlocks.StarlitGrass, TragicBlocks.StarlitTallGrass, TragicBlocks.StarCrystal, TragicBlocks.ErodedStone});

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.isRemote || !world.getWorldInfo().isMapFeaturesEnabled() || random.nextInt(200) >= TragicConfig.structureOverallRarity) return;

		int Xcoord;
		int Ycoord;
		int Zcoord;
		
		boolean flag = TragicConfig.allowNonBossStructureGen;
		boolean flag2 = TragicConfig.allowBossStructureGen;
		/* TODO rewrite how Structures are generated, so that this mess looks cleaner and less complex
		Structure[] structureList = new Structure[16];
		for (Structure s : structureList)
		{ //canGenerate will check if config option allows the structure, if proper biome and does rarity check at this point
		//biomes will be another method within the structure class for each one to override, it'll be a set of biomes to check
		//rarity will also be an integer value that is returned, though set by each one's config option
		//allowed dimensions will be returned from the method but will not have a variable to save the allowed dimensions
			if (s.isValidDimension(world.provider.dimensionId) && s.areCoordsValidForStructure(world, Xcoord, Ycoord, Zcoord) && s.canGenerate(world))
			{
				s.generate(world, random, Xcoord, Ycoord, Zcoord);
			}
		} */

		switch(world.provider.dimensionId)
		{
		case 0:
			Xcoord = (chunkX * 16) + 8;
			Zcoord = (chunkZ * 16) + 8;
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

			if (!this.areCoordinatesValidForStructureStart(world, Xcoord, Ycoord, Zcoord))
			{
				break;
			}

			if (flag && random.nextInt(200) <= TragicConfig.desertTowerRarity)
			{
				new Structure(1).generate(world, random, Xcoord, Ycoord, Zcoord);
				break;
			}
			else if (flag2 && random.nextInt(200) <= TragicConfig.apisTempleRarity)
			{
				new Structure(0).generate(world, random, Xcoord, Ycoord, Zcoord);
				break;
			}
			else if (flag2 && random.nextInt(200) <= TragicConfig.celestialTempleRarity)
			{
				Ycoord = random.nextInt(64) + 90;

				if (!this.areCoordinatesValidForStructureStart(world, Xcoord, Ycoord, Zcoord))
				{
					break;
				}
				new Structure(5).generate(world, random, Xcoord, Ycoord, Zcoord);
			}

			break;
		case -1:

			Xcoord = (chunkX * 16) + random.nextInt(16);
			Zcoord = (chunkZ * 16) + random.nextInt(16);
			Ycoord = random.nextInt(80) + 30;

			if (!this.areCoordinatesValidForStructureStart(world, Xcoord, Ycoord, Zcoord))
			{
				break;
			}

			if (flag && random.nextInt(200) <= TragicConfig.desertTowerRarity)
			{
				new Structure(1).generate(world, random, Xcoord, Ycoord, Zcoord);
				break;
			}
			else if (flag2 && random.nextInt(200) <= TragicConfig.kitsuneDenRarity)
			{
				new Structure(4).generate(world, random, Xcoord, Ycoord, Zcoord);
				break;
			}
			else if (flag2 && random.nextInt(200) <= TragicConfig.deathCircleRarity)
			{
				new Structure(2).generate(world, random, Xcoord, Ycoord, Zcoord);
				break;
			}
			break;
		default:
			if (world.provider instanceof TragicWorldProvider)
			{
				Xcoord = (chunkX * 16) + 8;
				Zcoord = (chunkZ * 16) + 8;
				Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

				if (!this.areCoordinatesValidForStructureStart(world, Xcoord, Ycoord, Zcoord))
				{
					break;
				}

				if (flag2 && random.nextInt(400) <= TragicConfig.deathCircleRarity * 2)
				{
					new Structure(2).generate(world, random, Xcoord, Ycoord, Zcoord);
					break;
				}
				else if (flag && random.nextInt(300) <= TragicConfig.obsidianCavernRarity * 1.5)
				{
					new Structure(3).generate(world, random, Xcoord, Ycoord, Zcoord);
					break;
				}
				else if (flag2 && random.nextInt(300) <= TragicConfig.kitsuneDenRarity * 1.5)
				{
					Ycoord = random.nextInt(60) + 5;

					if (!this.areCoordinatesValidForStructureStart(world, Xcoord, Ycoord, Zcoord))
					{
						break;
					}

					new Structure(4).generate(world, random, Xcoord, Ycoord, Zcoord);
					break;
				}
				else if(flag2 && random.nextInt(400) <= TragicConfig.celestialTempleRarity * 2)
				{
					Ycoord = random.nextInt(64) + 90;

					if (!this.areCoordinatesValidForAirStructureStart(world, Xcoord, Ycoord, Zcoord))
					{
						break;
					}

					new Structure(5).generate(world, random, Xcoord, Ycoord, Zcoord);
					break;
				}
				else if (flag2 && random.nextInt(400) <= TragicConfig.timeAltarRarity * 2)
				{
					new Structure(6).generate(world, random, Xcoord, Ycoord, Zcoord);
					break;
				}
			}
			break;
		}
	}

	public boolean areCoordinatesValidForStructureStart(World world, int x, int y, int z)
	{
		if (StructureWorldGen.validBlocks.contains(world.getBlock(x, y, z)) && !(world.getBlock(x, y, z) instanceof BlockLeaves) && !(world.getBlock(x, y, z) == Blocks.air))
		{
			return true;
		}
		return false;
	}

	public boolean areCoordinatesValidForAirStructureStart(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y, z).getMaterial() == Material.air)
		{
			return true;
		}
		return false;
	}

}
