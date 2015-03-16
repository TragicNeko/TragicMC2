package tragicneko.tragicmc.blocks;

import static net.minecraft.init.Blocks.air;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class BlockSynapseCore extends Block {

	public BlockSynapseCore() {
		super(Material.iron);
		this.setHarvestLevel("pickaxe", 3);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		if (world.provider.dimensionId != TragicConfig.synapseID) return;

		Block block = TragicBlocks.CircuitBlock;
		boolean flag = world.getBlock(x, y - 1, z) == block && world.getBlock(x, y - 2, z) == block &&
				world.getBlock(x - 1, y - 1, z) == block && world.getBlock(x + 1, y - 1, z) == block &&
				world.getBlock(x, y - 1, z - 1) == block && world.getBlock(x, y - 1, z + 1) == block;

		if (flag)
		{
			EntityOverlordCombat boss = new EntityOverlordCombat(world);
			
			world.setBlock(x, y, z, air, 0, 2);
			world.setBlock(x, y - 1, z, air, 0, 2);
			world.setBlock(x, y - 2, z, air, 0, 2);
			world.setBlock(x - 1, y - 1, z, air, 0, 2);
			world.setBlock(x + 1, y - 1, z, air, 0, 2);
			world.setBlock(x, y - 1, z - 1, air, 0, 2);
			world.setBlock(x, y - 1, z + 1, air, 0, 2);
			
			boss.setLocationAndAngles(x + 0.5D, y - 1.95D, z + 0.5D, 0.0F, 0.0F);
			EntityPlayer player = boss.worldObj.getClosestVulnerablePlayerToEntity(boss, 16.0D);
			if (player != null) boss.setTarget(player);
			boss.setTransforming();
			world.spawnEntityInWorld(boss);

			for (int l = 0; l < 120; ++l) 
				world.spawnParticle("hugeexplosion", x + world.rand.nextDouble(), y - 2 + world.rand.nextDouble() * 3.9D, z + world.rand.nextDouble(),
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
