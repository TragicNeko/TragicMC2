package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGas extends Block {

	public BlockGas() {
		super(Material.circuits);
		this.setCreativeTab(TragicTabs.Creative);
		this.setLightLevel(0.0F);
		this.setLightOpacity(0);
		this.setResistance(0.0F);
		this.setHardness(0.0F);
		this.canBlockGrass = true;
		this.opaque = false;
		this.setTickRandomly(true);
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
	{
		return false;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean canReplace(World world, int x, int y, int z, int meta, ItemStack stack)
	{
		return true;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		world.setBlockToAir(x, y, z);
	}

	public void onBlockAdded(World world, int x, int y, int z)
	{
		if (!world.isRemote) world.scheduleBlockUpdate(x, y, z, this, 300 + world.rand.nextInt(120));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("tragicmc:Transparency");
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return null;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return -1;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * Returns whether this block is collideable based on the arguments passed in n@param par1 block metaData n@param
	 * par2 whether the player right-clicked while holding a boat
	 */
	public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_)
	{
		return false;
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (!world.isRemote && entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD) entity.attackEntityFrom(DamageSource.wither, 1.0F);
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		for (int i = 0; i < 10; i++)
			world.spawnParticle("smoke", x + rand.nextDouble() - rand.nextDouble(), y + (rand.nextDouble() * 0.725), z + rand.nextDouble() - rand.nextDouble(),
					0.0, 0.0, 0.0);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}
}
