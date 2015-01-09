package tragicneko.tragicmc.blocks;

import static net.minecraft.init.Blocks.air;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicTabs;
import tragicneko.tragicmc.blocks.tileentity.TileEntityTimeDisruptor;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.TragicBoss;

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

		Block block = null;
		if (world.getBlock(x, y - 1, z) == Blocks.quartz_block && world.getBlock(x, y - 2, z) == Blocks.quartz_block) block = Blocks.quartz_block;
		if (world.getBlock(x, y - 1, z) == TragicBlocks.DarkenedQuartz && world.getBlock(x, y - 2, z) == TragicBlocks.DarkenedQuartz) block = TragicBlocks.DarkenedQuartz;
		
		if (block == null) return;
		boolean flag = world.getBlock(x - 1, y - 1, z) == block && world.getBlock(x + 1, y - 1, z) == block && world.getBlock(x, y - 1, z - 1) == block && world.getBlock(x, y - 1, z + 1) == block;

		if (flag)
		{
			TragicBoss boss = null;
			if (block == Blocks.quartz_block && TragicNewConfig.allowTimeController) boss = new EntityTimeController(world);
			else if (block == TragicBlocks.DarkenedQuartz && TragicNewConfig.allowEnyvil) boss = new EntityEnyvil(world);
			
			if (boss == null) return;
			
			world.setBlock(x, y, z, air, 0, 2);
			world.setBlock(x, y - 1, z, air, 0, 2);
			world.setBlock(x, y - 2, z, air, 0, 2);
			world.setBlock(x - 1, y - 1, z, air, 0, 2);
			world.setBlock(x + 1, y - 1, z, air, 0, 2);
			world.setBlock(x, y - 1, z - 1, air, 0, 2);
			world.setBlock(x, y - 1, z + 1, air, 0, 2);
			
			boss.setLocationAndAngles((double)x + 0.5D, (double)y - 1.95D, (double)z + 0.5D, 0.0F, 0.0F);
			EntityPlayer player = boss.worldObj.getClosestVulnerablePlayerToEntity(boss, 16.0D);
			if (player != null) boss.setTarget(player);
			world.spawnEntityInWorld(boss);

			for (int l = 0; l < 120; ++l) 
				world.spawnParticle("hugeexplosion", (double)x + world.rand.nextDouble(), (double)(y - 2) + world.rand.nextDouble() * 3.9D, (double)z + world.rand.nextDouble(),
						0.0D, 0.0D, 0.0D);

			world.notifyBlockChange(x, y, z, air);
			world.notifyBlockChange(x, y - 1, z, air);
			world.notifyBlockChange(x, y - 2, z, air);
			world.notifyBlockChange(x - 1, y - 1, z, air);
			world.notifyBlockChange(x + 1, y - 1, z, air);
			world.notifyBlockChange(x, y - 1, z - 1, air);
			world.notifyBlockChange(x, y - 1, z + 1, air);

		}
	}
}
