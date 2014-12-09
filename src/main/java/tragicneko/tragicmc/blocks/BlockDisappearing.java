package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDisappearing extends Block
{
	private final boolean isPowered;
	private Block blockToBecome;

	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	@SideOnly(Side.CLIENT)
	private IIcon iconSide;

	@SideOnly(Side.CLIENT)
	private IIcon iconBottom;

	public BlockDisappearing(boolean powered)
	{
		super(Material.sponge);
		this.isPowered = powered;
		this.setHardness(0.5F);
		this.setResistance(5.0F);

		if (!powered)
		{
			this.setCreativeTab(TragicTabs.Creative);
		}
		else
		{
			this.setCreativeTab(null);
		}

		if (powered)
		{
			this.setLightOpacity(0);
		}
	}

	public MovingObjectPosition collisionRayTrace(World world, int par2, int par3, int par4, Vec3 vec, Vec3 vec2)
	{
		if (this.isPowered)
		{
			return null;
		}
		else
		{
			return super.collisionRayTrace(world, par2, par3, par4, vec, vec2);
		}
	}


	public int getMobilityFlag()
	{
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		if (!this.isPowered)
		{
			this.blockIcon = register.registerIcon("tragicmc:DisappearingBlock_lowRes");
		}
		else
		{
			this.blockIcon = register.registerIcon("tragicmc:Transparency");
		}
	}

	public boolean isBlockNormalCube()
	{
		return !this.isPowered;
	}

	public boolean isOpaqueCube()
	{
		return isBlockNormalCube();
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (this.isPowered)
		{
			if (entity instanceof EntityFallingBlock && ((EntityFallingBlock)entity).func_145805_f() != this)
			{
				entity.setDead();
			}
			return;
		}
		else
		{
			super.onEntityCollidedWithBlock(world, x, y, z, entity);
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4)
	{
		if (!this.isPowered)
		{
			return super.getCollisionBoundingBoxFromPool(world, par2, par3, par4);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
	{
		if (!p_149726_1_.isRemote)
		{
			if (this.isPowered && !p_149726_1_.isBlockIndirectlyGettingPowered(p_149726_2_, p_149726_3_, p_149726_4_))
			{
				p_149726_1_.scheduleBlockUpdate(p_149726_2_, p_149726_3_, p_149726_4_, this, 4);
			}
			else if (!this.isPowered && p_149726_1_.isBlockIndirectlyGettingPowered(p_149726_2_, p_149726_3_, p_149726_4_))
			{
				p_149726_1_.setBlock(p_149726_2_, p_149726_3_, p_149726_4_, TragicBlocks.DisappearingBlockInvis, 0, 2);
			}
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor Block
	 */
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		if (!p_149695_1_.isRemote)
		{
			if (this.isPowered && !p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_))
			{
				p_149695_1_.scheduleBlockUpdate(p_149695_2_, p_149695_3_, p_149695_4_, this, 4);
			}
			else if (!this.isPowered && p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_))
			{
				p_149695_1_.setBlock(p_149695_2_, p_149695_3_, p_149695_4_, TragicBlocks.DisappearingBlockInvis, 0, 2);
			}
		}
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
	{
		if (!p_149674_1_.isRemote && this.isPowered && !p_149674_1_.isBlockIndirectlyGettingPowered(p_149674_2_, p_149674_3_, p_149674_4_))
		{
			p_149674_1_.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, TragicBlocks.DisappearingBlock, 0, 2);
		}
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return null;
	}

	/**
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return Item.getItemFromBlock(TragicBlocks.DisappearingBlock);
	}

	protected boolean canSilkHarvest()
	{
		return true;
	}

	/**
	 * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
	 * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
	 */
	protected ItemStack createStackedBlock(int p_149644_1_)
	{
		return new ItemStack(TragicBlocks.DisappearingBlock);
	}

	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        return this.isPowered ? false : super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
    }
}