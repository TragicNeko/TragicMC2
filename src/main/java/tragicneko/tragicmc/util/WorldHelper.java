package tragicneko.tragicmc.util;

import static tragicneko.tragicmc.TragicMC.rand;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicBlocks;

public class WorldHelper {

	public final static Set validBlocksForDimension = Sets.newHashSet(new Block[] {TragicBlocks.AshenGrass, TragicBlocks.BrushedGrass, TragicBlocks.DeadDirt,
			TragicBlocks.DarkStone, TragicBlocks.DarkSand, TragicBlocks.StarlitGrass, TragicBlocks.TragicSapling, TragicBlocks.ErodedStone,
			TragicBlocks.DarkCobblestone});

	public static Random getRandom()
	{
		return rand;
	}

	/**
	 * Returns randomly selected blocks in the set range, with the total attempts passed in, starting at the coordinates passed in
	 * @param world
	 * @param range
	 * @param passes
	 * @return
	 */
	public static Block[] getRandomBlocksInSetRange(World world, int x, int y, int z, int range, int passes)
	{
		return getRandomBlocksInSetRangeWithRandomChance(world, x, y, z, range, passes, 4);
	}

	/**
	 * Returns randomly selected blocks in the set range, this one allows the ability to change the chance it picks any block
	 * @param world
	 * @param range
	 * @param passes
	 * @param chance
	 * @return
	 */
	public static Block[] getRandomBlocksInSetRangeWithRandomChance(World world, int x, int y, int z, int range, int passes, int chance)
	{
		return null;
	}

	public static Block[] getRandomBlocksInSetRangeWithPrejudice(World world, Set set, int range)
	{
		return getRandomBlocksInSetRangeWithPrejudiceWithRandomChance(world, set, range, 4);
	}

	public static Block[] getRandomBlocksInSetRangeWithPrejudiceWithRandomChance(World world, Set set, int range, int chance)
	{
		return null;
	}

	/**
	 * Returns randomly selected entities within the range set for the entity passed in
	 * @param world
	 * @param range
	 * @return
	 */
	public static Entity[] getRandomEntitiesInRange(World world, Entity entity, double range)
	{
		return getRandomEntitiesInRangeWithRandomChance(world, entity, range, 4);
	}

	/**
	 * Returns randomly selected entities within the range set for the entity passed in, with random chance to ignore it
	 * @param world
	 * @param entity
	 * @param range
	 * @param chance
	 * @return
	 */
	public static Entity[] getRandomEntitiesInRangeWithRandomChance(World world, Entity entity, double range, int chance)
	{
		return null;
	}

	/**
	 * Universally useful method to get if mob griefing is enabled, less typing to use this
	 * @param world
	 * @return
	 */
	public static boolean getMobGriefing(World world) {
		return world.getGameRules().getGameRuleBooleanValue("mobGriefing");
	}

	/**
	 * Universal method for getting the current difficulty setting, useful for switches (not much of a time saver but here if it's ever needed)
	 * @param world
	 * @return
	 */
	public static EnumDifficulty getWorldDifficulty(World world)
	{
		return world.difficultySetting;
	}

	/**
	 * Returns world dependency for use in my dimension's layered rock generation
	 * @param world
	 * @return
	 */
	public static int getWorldDependency(World world)
	{
		int i = (MathHelper.ceiling_double_int((((world.getSeed() % 121L) + (world.getSeed() / 1452749627L) )/ 256)) % 256);
		return (Math.abs(i) % 128) + 32;
	}

	/**
	 * The x, y, z coordinates to be passed in should be for the origin of the circle, returns mappings with coordinates of every block in the circle's area
	 * @param world
	 * @param radius
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static Map<Integer, int[]> getBlocksInCircularRange(World world, double radius, double x, double y, double z) 
	{		
		Map<Integer, int[]> map = new HashMap();
		if (y < 0 || y > 256 || radius <= 0) return map;

		int[] coords;
		int mapping = 0;

		for (double x2 = -radius - 0.55D; x2 < radius + 0.55D; x2 += 0.5D)
		{
			for (double z2 = -radius - 0.55D; z2 < radius + 0.55D; z2 += 0.5D)
			{
				coords = new int[] {(int) Math.round(x + x2), (int) Math.round(y), (int) Math.round(z + z2)};

				if (MathHelper.sqrt_double(x2 * x2 + z2 * z2) <= radius)
				{
					if (!map.containsValue(coords))
					{
						map.put(mapping++, coords);
					}
				}
			}
		}

		return map;
	}

	/**
	 * The x, y, z coordinates to be passed in should be the origin of the sphere, returns mappings with coordinates of every block in the sphere's area
	 * @param world
	 * @param radius
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static Map<Integer, int[]> getBlocksInSphericalRange(World world, double radius, double x, double y, double z)
	{		
		Map<Integer, int[]> map = new HashMap();

		if (y <= 0 || y >= 256 || radius <= 0) return map;

		double distance = radius + 1.5D;
		
		int[] coords;
		int mapping = 0;

		for (double y1 = -distance; y1 < distance; y1 += 0.5D)
		{
			for (double x1 = -distance; x1 < distance; x1 += 0.5D)
			{
				for (double z1 = -distance; z1 < distance; z1 += 0.5D)
				{					
					if (y + y1 < 0 || y + y1 >= 256) break;

					if (MathHelper.sqrt_double(x1 * x1 + z1 * z1 + y1 * y1) < radius)
					{
						coords = new int[] {(int) Math.round(x + x1), (int) Math.round(y + y1), (int) Math.round(z + z1)};

						if (!map.containsValue(coords))
						{
							map.put(mapping++, coords);
						}
					}

				}
			}
		}

		return map;
	}
}
