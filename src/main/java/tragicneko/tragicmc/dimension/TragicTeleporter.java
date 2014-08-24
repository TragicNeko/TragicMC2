package tragicneko.tragicmc.dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicBlocks;

public class TragicTeleporter extends Teleporter {

	protected final WorldServer worldServerInstance;
	/** A private Random() function in Teleporter */
	protected final Random random;
	/**
	 * A list of valid keys for the destinationCoordainteCache. These are based on the X & Z of the players initial
	 * location.
	 */
	protected final List destinationCoordinateKeys = new ArrayList();

	public TragicTeleporter(WorldServer par1WorldServer)
	{
		super(par1WorldServer);
		this.worldServerInstance = par1WorldServer;
		this.random = new Random(par1WorldServer.getSeed());
	}

	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	{
		if (this.worldServerInstance.provider.dimensionId != 0)
		{
			int i = worldServerInstance.getSpawnPoint().posX;
			int k = worldServerInstance.getSpawnPoint().posZ;
			int j = worldServerInstance.getTopSolidOrLiquidBlock(i, k);
			byte b0 = 1;
			byte b1 = 0;

			boolean lavaFlag = worldServerInstance.getBlock(i, j, k).getMaterial() == Material.lava || worldServerInstance.getBlock(i, j + 1, k).getMaterial() == Material.lava;

			if (!TragicWorldProvider.spawnBlocks.contains(worldServerInstance.getTopBlock(i, k)))
			{
				for (int l = -2; l <= 2; ++l)
				{
					for (int i1 = -2; i1 <= 2; ++i1)
					{
						for (int j1 = -1; j1 < 3; ++j1)
						{
							int k1 = i + i1 * b0 + l * b1;
							int l1 = j + j1;
							int i2 = k + i1 * b1 - l * b0;
							boolean flag = j1 < 0;
							this.worldServerInstance.setBlock(k1, l1, i2, flag ? TragicBlocks.DeadDirt : Blocks.air);
						}
					}
				}

				if (lavaFlag)
				{
					TragicMC.logger.info("Top block for spawn was lava, generating dead dirt walls around the player");
					for (int x = -1; x < 2; x++)
					{
						this.worldServerInstance.setBlock(i + x, j, k - 3, TragicBlocks.DeadDirt);
						this.worldServerInstance.setBlock(i + x, j, k + 3, TragicBlocks.DeadDirt);
						this.worldServerInstance.setBlock(i + 3, j, k + x, TragicBlocks.DeadDirt);
						this.worldServerInstance.setBlock(i - 3, j, k + x, TragicBlocks.DeadDirt);
					}
				}
			}

			par1Entity.setLocationAndAngles((double)i, (double)j + 1, (double)k, par1Entity.rotationYaw, 0.0F);
			par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
		}
		else
		{
			int i = worldServerInstance.getSpawnPoint().posX;
			int k = worldServerInstance.getSpawnPoint().posZ;
			int j = worldServerInstance.getTopSolidOrLiquidBlock(i, k);

			if (par1Entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) par1Entity;
				ChunkCoordinates cc = player.getBedLocation(this.worldServerInstance.provider.dimensionId);

				if (cc != null)
				{
					par1Entity.setLocationAndAngles((double)cc.posX, (double)cc.posY + 1.5D, (double)cc.posZ, par1Entity.rotationYaw, 0.0F);
				}
				else
				{
					par1Entity.setLocationAndAngles((double)i, (double)j, (double)k, par1Entity.rotationYaw, 0.0F);
				}
				
				par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
			}
			else
			{				
				par1Entity.setLocationAndAngles((double)i, (double)j, (double)k, par1Entity.rotationYaw, 0.0F);
				par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
			}
		}

	}

}
