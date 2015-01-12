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
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.dimension.TragicWorldProvider;

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
		if (world.isRemote || !world.getWorldInfo().isMapFeaturesEnabled() || random.nextInt(200) >= TragicNewConfig.structureOverallRarity) return;

		int Xcoord;
		int Ycoord;
		int Zcoord;
		
		boolean flag = TragicNewConfig.allowNonBossStructureGen;
		boolean flag2 = TragicNewConfig.allowBossStructureGen;

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

			if (flag && random.nextInt(200) <= TragicNewConfig.desertTowerRarity)
			{
				new Structure(1).generate(world, random, Xcoord, Ycoord, Zcoord);
				break;
			}
			else if (flag2 && random.nextInt(200) <= TragicNewConfig.apisTempleRarity)
			{
				new Structure(0).generate(world, random, Xcoord, Ycoord, Zcoord);
				break;
			}
			else if (flag2 && random.nextInt(200) <= TragicNewConfig.celestialTempleRarity)
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

			if (flag && random.nextInt(200) <= TragicNewConfig.desertTowerRarity)
			{
				new Structure(1).generate(world, random, Xcoord, Ycoord, Zcoord);
				break;
			}
			else if (flag2 && random.nextInt(200) <= TragicNewConfig.kitsuneDenRarity)
			{
				new Structure(4).generate(world, random, Xcoord, Ycoord, Zcoord);
				break;
			}
			else if (flag2 && random.nextInt(200) <= TragicNewConfig.deathCircleRarity)
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

				if (flag2 && random.nextInt(400) <= TragicNewConfig.deathCircleRarity * 2)
				{
					new Structure(2).generate(world, random, Xcoord, Ycoord, Zcoord);
					break;
				}
				else if (flag && random.nextInt(300) <= TragicNewConfig.obsidianCavernRarity * 1.5)
				{
					new Structure(3).generate(world, random, Xcoord, Ycoord, Zcoord);
					break;
				}
				else if (flag2 && random.nextInt(300) <= TragicNewConfig.kitsuneDenRarity * 1.5)
				{
					Ycoord = random.nextInt(60) + 5;

					if (!this.areCoordinatesValidForStructureStart(world, Xcoord, Ycoord, Zcoord))
					{
						break;
					}

					new Structure(4).generate(world, random, Xcoord, Ycoord, Zcoord);
					break;
				}
				else if(flag2 && random.nextInt(400) <= TragicNewConfig.celestialTempleRarity * 2)
				{
					Ycoord = random.nextInt(64) + 90;

					if (!this.areCoordinatesValidForAirStructureStart(world, Xcoord, Ycoord, Zcoord))
					{
						break;
					}

					new Structure(5).generate(world, random, Xcoord, Ycoord, Zcoord);
					break;
				}
				else if (flag2 && random.nextInt(400) <= TragicNewConfig.timeAltarRarity * 2)
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
