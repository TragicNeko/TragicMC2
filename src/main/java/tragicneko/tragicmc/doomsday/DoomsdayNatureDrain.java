package tragicneko.tragicmc.doomsday;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayNatureDrain extends Doomsday {

	public DoomsdayNatureDrain(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.WORLDSHAPER);
	}
	
	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		int x = MathHelper.floor_double(player.posX) - 3;
		int y = MathHelper.floor_double(player.posY) - 3;
		int z = MathHelper.floor_double(player.posZ) - 3;

		World world = player.worldObj;

		double plantCount = 0.0D;
		
		for (int y1 = 0; y1 < 7; y1++)
		{
			for (int z1 = 0; z1 < 7; z1++)
			{
				for (int x1 = 0; x1 < 7; x1++)
				{
					Block block = world.getBlock(x + x1, y + y1, z + z1);
					
					if (block instanceof BlockReed || block instanceof BlockSapling || block instanceof BlockFlower 
							|| block instanceof BlockDoublePlant || block instanceof BlockMushroom || block instanceof BlockCrops
							|| block instanceof BlockStem || block instanceof BlockLeaves || block instanceof BlockTallGrass)
					{
						if (griefCheck)
						{
							world.setBlockToAir(x + x1, y + y1, z + z1);
						}
						plantCount += 0.2;
					}

					if (block instanceof BlockGrass)
					{
						if (griefCheck)
						{
							world.setBlock(x + x1, y + y1, z + z1, Blocks.dirt);
						}
						plantCount += 0.1;
					}

					if (block instanceof BlockFarmland)
					{
						if (griefCheck)
						{
							world.setBlock(x + x1, y + y1, z + z1, Blocks.dirt);
						}
						plantCount += 0.15;
					}
				}
			}
		}

		float f = MathHelper.ceiling_double_int(plantCount);

		if (crucMoment)
		{
			f *= 1.5;
		}

		if (f > 40F)
		{
			f = 40F;
		}

		if (f > 0F)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Nature Drain!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}

			for (int j = 0; j < 10; j++)
			{
				if (rand.nextInt(3) == 0)
				{
					player.playSound("random.breath", rand.nextFloat(), rand.nextFloat());
				}
				player.heal(f / 10);
			}

			if (!player.capabilities.isCreativeMode)
			{
				this.applyDoomAndCooldown(doom);
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No plantlife in range..."));
		}
	}
	
	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		int x = MathHelper.floor_double(player.posX) - 3;
		int y = MathHelper.floor_double(player.posY) - 3;
		int z = MathHelper.floor_double(player.posZ) - 3;

		World world = player.worldObj;

		double plantCount = 0;
		for (int y1 = 0; y1 < 7; y1++)
		{
			for (int z1 = 0; z1 < 7; z1++)
			{
				for (int x1 = 0; x1 < 7; x1++)
				{
					Block block = world.getBlock(x + x1, y + y1, z + z1);
					if (block instanceof BlockReed || block instanceof BlockSapling || block instanceof BlockFlower 
							|| block instanceof BlockDoublePlant || block instanceof BlockMushroom || block instanceof BlockCrops
							|| block instanceof BlockStem || block instanceof BlockLeaves || block instanceof BlockTallGrass)
					{
						if (griefCheck)
						{
							world.setBlockToAir(x + x1, y + y1, z + z1);
						}
						plantCount += 0.2;
					}

					if (block instanceof BlockGrass)
					{
						if (griefCheck)
						{
							world.setBlock(x + x1, y + y1, z + z1, Blocks.dirt);
						}
						plantCount += 0.1;
					}

					if (block instanceof BlockFarmland)
					{
						if (griefCheck)
						{
							world.setBlock(x + x1, y + y1, z + z1, Blocks.dirt);
						}
						plantCount += 0.15;
					}
				}
			}
		}

		float f = MathHelper.ceiling_double_int(plantCount);

		if (crucMoment)
		{
			f *= 1.5;
		}

		if (f > 40F)
		{
			f = 40F;
		}

		if (f > 0F)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Nature Drain!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}

			for (int j = 0; j < 10; j++)
			{
				if (rand.nextInt(3) == 0)
				{
					player.playSound("random.breath", rand.nextFloat(), rand.nextFloat());
				}
				player.heal(f / 10);
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No plantlife in range..."));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player,
			boolean griefCheck) {
		
	}

}
