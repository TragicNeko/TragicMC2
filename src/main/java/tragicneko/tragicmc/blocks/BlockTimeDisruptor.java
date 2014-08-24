package tragicneko.tragicmc.blocks;

import tragicneko.tragicmc.blocks.tileentity.TileEntityTimeDisruptor;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.main.TragicTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTimeDisruptor extends BlockContainer {

	public BlockTimeDisruptor() {
		super(Material.iron);
		this.setCreativeTab(TragicTabs.Survival);
		this.setBlockTextureName("tragicmc:TimeDisruptor_lowRes");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityTimeDisruptor();
	}

	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);

		if (world.getBlock(x, y - 1, z) == Blocks.quartz_block && world.getBlock(x, y - 2, z) == Blocks.quartz_block)
		{
			boolean flag = world.getBlock(x - 1, y - 1, z) == Blocks.quartz_block && world.getBlock(x + 1, y - 1, z) == Blocks.quartz_block;
			boolean flag1 = world.getBlock(x, y - 1, z - 1) == Blocks.quartz_block && world.getBlock(x, y - 1, z + 1) == Blocks.quartz_block;

			if (flag && flag1)
			{
				world.setBlock(x, y, z, getBlockById(0), 0, 2);
				world.setBlock(x, y - 1, z, getBlockById(0), 0, 2);
				world.setBlock(x, y - 2, z, getBlockById(0), 0, 2);

				if (flag)
				{
					world.setBlock(x - 1, y - 1, z, getBlockById(0), 0, 2);
					world.setBlock(x + 1, y - 1, z, getBlockById(0), 0, 2);
					world.setBlock(x, y - 1, z - 1, getBlockById(0), 0, 2);
					world.setBlock(x, y - 1, z + 1, getBlockById(0), 0, 2);
				}

				EntityTimeController entityirongolem = new EntityTimeController(world);
				entityirongolem.setLocationAndAngles((double)x + 0.5D, (double)y - 1.95D, (double)z + 0.5D, 0.0F, 0.0F);
				
				EntityPlayer player = entityirongolem.worldObj.getClosestVulnerablePlayerToEntity(entityirongolem, 16.0D);
				if (player != null)
				{
					entityirongolem.setTarget(player);
				}
				
				world.spawnEntityInWorld(entityirongolem);

				for (int l = 0; l < 120; ++l)
				{
					world.spawnParticle("hugeexplosion", (double)x + world.rand.nextDouble(), (double)(y - 2) + world.rand.nextDouble() * 3.9D, (double)z + world.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
				}

				world.notifyBlockChange(x, y, z, getBlockById(0));
				world.notifyBlockChange(x, y - 1, z, getBlockById(0));
				world.notifyBlockChange(x, y - 2, z, getBlockById(0));

				if (flag)
				{
					world.notifyBlockChange(x - 1, y - 1, z, getBlockById(0));
					world.notifyBlockChange(x + 1, y - 1, z, getBlockById(0));
					world.notifyBlockChange(x, y - 1, z - 1, getBlockById(0));
					world.notifyBlockChange(x, y - 1, z + 1, getBlockById(0));
				}
			}
		}
	}

}
