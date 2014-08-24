package tragicneko.tragicmc.doomsday;

import java.util.Set;

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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.properties.PropertyDoom;

import com.google.common.collect.Sets;

public class DoomsdayMinerSkills extends Doomsday {

	private static final Set minableBlocks = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.dirt, Blocks.cobblestone, Blocks.stone, Blocks.mycelium, Blocks.gravel, Blocks.sand,
			Blocks.sandstone, Blocks.clay, TragicBlocks.DarkStone, TragicBlocks.DeadDirt});

	public DoomsdayMinerSkills(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.WORLDSHAPER);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player,	boolean crucMoment, boolean griefCheck) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Miner Skills!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}

		Block block = null;
		int blockX = 0;
		int blockY = 0;
		int blockZ = 0;

		Block block2 = null;
		int block2X = 0;
		int block2Y = 0;
		int block2Z = 0;

		Block block3 = null;
		int block3X = 0;
		int block3Y = 0;
		int block3Z = 0;

		int x = (int) (player.posX - 3);
		int y = (int) (player.posY - 3);
		int z = (int) (player.posZ - 3);

		boolean flag = false;
		boolean flag2 = false;
		boolean flag3 = false;

		for (int y1 = 0; y1 < 7; y1++)
		{
			for (int z1 = 0; z1 < 7; z1++)
			{
				for (int x1 = 0; x1 < 7; x1++)
				{
					if (flag && flag2 && flag3)
					{
						break;
					}

					if (!flag && rand.nextInt(48) == 0)
					{
						block = player.worldObj.getBlock(x + x1, y + y1, z + z1);
						blockX = x + x1;
						blockY = y + y1;
						blockZ = z + z1;

						if (this.minableBlocks.contains(block))
						{
							player.worldObj.setBlockToAir(blockX, blockY, blockZ);
							flag = true;
						}
					}

					if (!flag2 && rand.nextInt(48) == 0)
					{
						block2 = player.worldObj.getBlock(x + x1, y + y1, z + z1);
						block2X = x + x1;
						block2Y = y + y1;
						block2Z = z + z1;

						if (this.minableBlocks.contains(block2))
						{
							player.worldObj.setBlockToAir(block2X, block2Y, block2Z);
							flag2 = true;
						}
					}

					if (!flag3 && rand.nextInt(48) == 0)
					{
						block3 = player.worldObj.getBlock(x + x1, y + y1, z + z1);
						block3X = x + x1;
						block3Y = y + y1;
						block3Z = z + z1;

						if (this.minableBlocks.contains(block3))
						{
							player.worldObj.setBlockToAir(block3X, block3Y, block3Z);
							flag3 = true;
						}
					}
				}
			}
		}

		Block theBlock = null;

		int count = 0;
		int i = 30;

		if (crucMoment)
		{
			i = 100;
		}

		if (flag)
		{
			for (int y1 = 0; y1 < 7; y1++)
			{
				for (int z1 = 0; z1 < 7; z1++)
				{
					for (int x1 = 0; x1 < 7; x1++)
					{
						if (count <= 70 + i)
						{
							if (rand.nextInt(4) == 0)
							{
								theBlock = player.worldObj.getBlock(blockX + x1 - 3, blockY + y1 - 3, blockZ + z1 - 3);

								if (this.minableBlocks.contains(theBlock))
								{
									player.worldObj.setBlockToAir(blockX + x1 - 3, blockY + y1 - 3, blockZ + z1 - 3);
									count++;
								}

							}
						}
					}
				}
			}
		}
		
		count = 0;

		if (flag2)
		{
			for (int y1 = 0; y1 < 7; y1++)
			{
				for (int z1 = 0; z1 < 7; z1++)
				{
					for (int x1 = 0; x1 < 7; x1++)
					{
						if (count <= 70 + i)
						{
							if (rand.nextInt(4) == 0)
							{
								theBlock = player.worldObj.getBlock(block2X + x1 - 3, block2Y + y1 - 3, block2Z + z1 - 3);

								if (this.minableBlocks.contains(theBlock))
								{
									player.worldObj.setBlockToAir(block2X + x1 - 3, block2Y + y1 - 3, block2Z + z1 - 3);
									count++;
								}

							}
						}
					}
				}
			}
		}
		
		count = 0;

		if (flag3)
		{
			for (int y1 = 0; y1 < 7; y1++)
			{
				for (int z1 = 0; z1 < 7; z1++)
				{
					for (int x1 = 0; x1 < 7; x1++)
					{
						if (count <= 70 + i)
						{
							if (rand.nextInt(4) == 0)
							{
								theBlock = player.worldObj.getBlock(block3X + x1 - 3, block3Y + y1 - 3, block3Z + z1 - 3);

								if (this.minableBlocks.contains(theBlock))
								{
									player.worldObj.setBlockToAir(block3X + x1 - 3, block3Y + y1 - 3, block3Z + z1 - 3);
									count++;
								}

							}
						}
					}
				}
			}
		}

		double d0 = 1;

		if (crucMoment)
		{
			d0 = 1.5;
		}

		player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, (int) (600 * d0), 3));

		if (crucMoment)
		{
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, (int) (400 * d0), 1));
		}

		if (!player.capabilities.isCreativeMode)
		{
			this.applyDoomAndCooldown(doom);
		}
	}

	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) {

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Miner Skills!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}

		Block block = null;
		int blockX = 0;
		int blockY = 0;
		int blockZ = 0;

		Block block2 = null;
		int block2X = 0;
		int block2Y = 0;
		int block2Z = 0;

		Block block3 = null;
		int block3X = 0;
		int block3Y = 0;
		int block3Z = 0;

		int x = (int) (player.posX - 3);
		int y = (int) (player.posY - 3);
		int z = (int) (player.posZ - 3);

		boolean flag = false;
		boolean flag2 = false;
		boolean flag3 = false;

		for (int y1 = 0; y1 < 7; y1++)
		{
			for (int z1 = 0; z1 < 7; z1++)
			{
				for (int x1 = 0; x1 < 7; x1++)
				{
					if (flag && flag2 && flag3)
					{
						break;
					}

					if (!flag && rand.nextInt(48) == 0)
					{
						block = player.worldObj.getBlock(x + x1, y + y1, z + z1);
						blockX = x + x1;
						blockY = y + y1;
						blockZ = z + z1;

						if (this.minableBlocks.contains(block))
						{
							player.worldObj.setBlockToAir(blockX, blockY, blockZ);
							flag = true;
						}
					}

					if (!flag2 && rand.nextInt(48) == 0)
					{
						block2 = player.worldObj.getBlock(x + x1, y + y1, z + z1);
						block2X = x + x1;
						block2Y = y + y1;
						block2Z = z + z1;

						if (this.minableBlocks.contains(block2))
						{
							player.worldObj.setBlockToAir(block2X, block2Y, block2Z);
							flag2 = true;
						}
					}

					if (!flag3 && rand.nextInt(48) == 0)
					{
						block3 = player.worldObj.getBlock(x + x1, y + y1, z + z1);
						block3X = x + x1;
						block3Y = y + y1;
						block3Z = z + z1;

						if (this.minableBlocks.contains(block3))
						{
							player.worldObj.setBlockToAir(block3X, block3Y, block3Z);
							flag3 = true;
						}
					}
				}
			}
		}

		Block theBlock = null;

		int count = 0;
		int i = 0;

		if (crucMoment)
		{
			i = 50;
		}

		if (flag)
		{
			for (int y1 = 0; y1 < 7; y1++)
			{
				for (int z1 = 0; z1 < 7; z1++)
				{
					for (int x1 = 0; x1 < 7; x1++)
					{
						if (count <= 70 + i)
						{
							if (rand.nextInt(4) == 0)
							{
								theBlock = player.worldObj.getBlock(blockX + x1 - 3, blockY + y1 - 3, blockZ + z1 - 3);

								if (this.minableBlocks.contains(theBlock))
								{
									player.worldObj.setBlockToAir(blockX + x1 - 3, blockY + y1 - 3, blockZ + z1 - 3);
									count++;
								}
							}
						}
					}
				}
			}
		}
		
		count = 0;

		if (flag2)
		{
			for (int y1 = 0; y1 < 7; y1++)
			{
				for (int z1 = 0; z1 < 7; z1++)
				{
					for (int x1 = 0; x1 < 7; x1++)
					{
						if (count <= 70 + i)
						{
							if (rand.nextInt(4) == 0)
							{
								theBlock = player.worldObj.getBlock(block2X + x1 - 3, block2Y + y1 - 3, block2Z + z1 - 3);

								if (this.minableBlocks.contains(theBlock))
								{
									player.worldObj.setBlockToAir(block2X + x1 - 3, block2Y + y1 - 3, block2Z + z1 - 3);
									count++;
								}

							}
						}
					}
				}
			}
		}
		
		count = 0;

		if (flag3)
		{
			for (int y1 = 0; y1 < 7; y1++)
			{
				for (int z1 = 0; z1 < 7; z1++)
				{
					for (int x1 = 0; x1 < 7; x1++)
					{
						if (count <= 70 + i)
						{
							if (rand.nextInt(4) == 0)
							{
								theBlock = player.worldObj.getBlock(block3X + x1 - 3, block3Y + y1 - 3, block3Z + z1 - 3);

								if (this.minableBlocks.contains(theBlock))
								{
									player.worldObj.setBlockToAir(block3X + x1 - 3, block3Y + y1 - 3, block3Z + z1 - 3);
									count++;
								}

							}
						}
					}
				}
			}
		}

		double d0 = 1;

		if (crucMoment)
		{
			d0 = 1.5;
		}

		player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, (int) (600 * d0), 3));

		if (crucMoment)
		{
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, (int) (400 * d0), 1));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player, boolean griefCheck) {

	}

}
