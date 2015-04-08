package tragicneko.tragicmc.worldgen.schematic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public abstract class Schematic {

	public ArrayList<BlockPreset[][]> layers;
	public int width;
	public int height;
	
	public int offsetX;
	public int offsetY;
	public int offsetZ;
	//may use these to deal with structures that don't have their origin matching the matrix

	public Schematic(int structureHeight, int w, int h)
	{
		layers = new ArrayList<BlockPreset[][]>();
		for (int i = 0; i < structureHeight; i++)
		{
			layers.add(new BlockPreset[w][h]);
		}
		this.width = w;
		this.height = h;
		this.fillMatrices();
	}
	
	/**
	 * Called in the constructor to fill the matrix, should be overridden by each schematic to fill their particular matrix for generation
	 */
	public abstract void fillMatrices();

	public void invertMatrix(BlockPreset[][] presets)
	{
		for (int k = 0; k < this.height; k++)
		{
			invertRow(presets, k);
		}
	}

	public void invertRow(BlockPreset[][] presets, int row)
	{
		BlockPreset[] temp = presets[row];
		BlockPreset block;
		for (int i = 0; i < this.width; i++)
		{
			block = temp[i];
			presets[row][this.width - i] = block;
		}
	}

	public void rotateMatrix(BlockPreset[][] presets, int rotations)
	{
		for (int k = 0; k < this.height; k++)
		{
			rotateRow(presets, k, rotations);
		}
	}

	public void rotateRow(BlockPreset[][] presets, int row, int rotations)
	{
		BlockPreset[] temp = presets[row];
		BlockPreset block;
		for (int i = 0; i < this.width; i++)
		{
			block = temp[i];
			presets[row][i + rotations >= this.width ? (i + rotations - this.width) : (i + rotations)] = block;
		}
	}

	public void rotateEntireMatrix()
	{
		int h = this.height;
		int w = this.width;
		
		for (int m = 0; m < layers.size(); m++)
		{
			BlockPreset[][] matrix = layers.get(m);
			BlockPreset[][] newMatrix = new BlockPreset[h][w];
			BlockPreset preset;
			
			for (int i = 0; i < h; i++)
			{
				for (int k = 0; k < w; k++)
				{
					newMatrix[k][i] = matrix[i][k];
					preset = newMatrix[k][i];
					if (preset.block instanceof net.minecraft.block.BlockStairs) preset.meta = preset.meta < 4 ? (preset.meta + 1) % 4 : (preset.meta + 1 % 4) + 4;
				}
			}

			this.invertMatrix(newMatrix);
			layers.set(m, newMatrix);
		}
		//invert width and height parameters since these are used to keep track of the matrices assumed width and height
		this.height = w;
		this.width = h;
	}

	/**
	 * Main class called upon by the constructor, you need to tell the schematic which method to generate the structure from in here based on variant (if any)
	 */
	public abstract void generateStructure(int variant, World world, Random rand, int x, int y, int z);

	/**
	 * This is the basic structure without variation
	 */
	public abstract void generateWithoutVariation(World world, Random rand, int x, int y, int z);

	/**
	 * This is one variant of the main structure meant as an example method, more can be added and called from the generateStructure method
	 */
	public abstract void generateVariant(World world, Random rand, int x, int y, int z);

	/**
	 * Use this to apply chest contents to generated chests
	 */
	public abstract void applyChestContents(World world, Random rand, int x, int y, int z);

	public static class BlockPreset {

		public final Block block;
		public int meta;
		public boolean isSpecial; //used to determine if a preset has special randomization applied when generating, like for a chest or luxury block
		public final boolean forceReplace;

		public BlockPreset(Block block, int meta, boolean replace)
		{
			this.block = block;
			this.meta = meta;
			this.forceReplace = replace;
		}
	}
}
