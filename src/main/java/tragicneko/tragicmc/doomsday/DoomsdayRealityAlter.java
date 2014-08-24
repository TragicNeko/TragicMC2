package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayRealityAlter extends Doomsday {

	public DoomsdayRealityAlter(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.WORLDSHAPER);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player,	boolean crucMoment, boolean griefCheck)
	{
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Reality Alter!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}

		if (player.worldObj.isRemote)
		{
			return;
		}
		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.posY) - 5;
		int z = MathHelper.floor_double(player.posZ);
		Block block;

		int range = 11;
		int half = 5;

		if (crucMoment)
		{
			range = 23;
			half = 11;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(range, range, range));

		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof EntityAnimal && rand.nextInt(8) == 0)
			{
				if (!((EntityLiving) list.get(i)).hasCustomNameTag())
				{
					if (rand.nextFloat() > 0.35F)
					{
						list.get(i).getDataWatcher().updateObject(10, "Dinnerbone");
					}
					else
					{
						list.get(i).getDataWatcher().updateObject(10, "Grumm");
					}
				}
			}
			if (list.get(i) instanceof EntityMob && TragicNewConfig.allowStun) ((EntityLivingBase) list.get(i)).addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 120));
		}

		if (!player.capabilities.isCreativeMode)
		{
			this.applyDoomAndCooldown(doom);
		}

		if (!griefCheck)
		{
			player.addChatMessage(new ChatComponentText("You have griefing off, this doomsday is disabled automatically."));
			return;
		}

		for (int y1 = 0; y1 < range; y1++)
		{
			for (int x1 = 0; x1 < range; x1++)
			{
				for (int z1 = 0; z1 < range; z1++)
				{
					block = player.worldObj.getBlock(x + x1 - half, y + y1, z + z1 - half);

					if (!(block instanceof BlockAir) && rand.nextInt(48) == 0)
					{
						player.worldObj.setBlockToAir(x + x1 - half, y + y1, z + z1 - half);
						block = Blocks.air;
					}

					if (rand.nextInt(8) != 0)
					{
						if (block == Blocks.lava)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.ice);
						}

						if (block == Blocks.water)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.sand);
						}

						if (block == Blocks.stone || block == Blocks.cobblestone)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.gravel);
						}

						if (block == Blocks.gravel)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.dirt);
						}

						if (block == Blocks.netherrack)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.lava);
						}

						if (block == Blocks.ice)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.fire);
						}

						if (block == Blocks.sand)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.water);
						}

						if (block == Blocks.grass)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, TragicBlocks.Quicksand, 1, 2);
						}

						if (block == TragicBlocks.Quicksand && player.worldObj.getBlockMetadata(x + x1, y + y1, z + z1) == 1)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.clay);
						}

						if (block == TragicBlocks.Quicksand && player.worldObj.getBlockMetadata(x + x1, y + y1, z + z1) == 0)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.sand);
						}

						if (block == TragicBlocks.Quicksand && player.worldObj.getBlockMetadata(x + x1, y + y1, z + z1) == 2)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.lava);
						}

						if (block == Blocks.clay)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.stone);
						}

						if (block == Blocks.dirt)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.cobblestone);
						}

						if (block == Blocks.sandstone)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, TragicBlocks.Quicksand, 0, 2);
						}

						if (block == Blocks.leaves)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.web);
						}

						if (block == Blocks.web)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.hay_block);
						}

						if (block instanceof BlockAir && rand.nextInt(256) == 0)
						{
							switch(rand.nextInt(56))
							{
							case 1:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.wheat);
								break;
							case 5:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.glowstone);
								break;
							case 12:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.cactus);
								break;
							case 14:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.deadbush);
								break;
							case 18:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, TragicBlocks.CarrotBlock);
								break;
							case 26:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, TragicBlocks.PotatoBlock);
								break;
							case 32:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, TragicBlocks.Light);
								break;
							case 38:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.red_flower, rand.nextInt(10), 2);
								break;
							case 42:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.yellow_flower);
								break;
							case 46:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.double_plant, rand.nextInt(5), 2);
								break;
							case 52:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.pumpkin);
								break;
							default:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.cake);
								break;
							}

						}
					}
				}
			}
		}
	}

	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Reality Alter!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}

		if (player.worldObj.isRemote)
		{
			return;
		}
		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.posY) - 5;
		int z = MathHelper.floor_double(player.posZ);
		Block block;

		int range = 11;
		int half = 5;

		if (crucMoment)
		{
			range = 23;
			half = 11;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(range, range, range));

		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof EntityAnimal  && rand.nextInt(8) == 0)
			{
				if (!((EntityLiving) list.get(i)).hasCustomNameTag())
				{
					if (rand.nextFloat() > 0.35F)
					{
						list.get(i).getDataWatcher().updateObject(10, "Dinnerbone");
					}
					else
					{
						list.get(i).getDataWatcher().updateObject(10, "Grumm");
					}
				}
			}
			if (list.get(i) instanceof EntityMob && TragicNewConfig.allowStun) ((EntityLivingBase) list.get(i)).addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 120));
		}

		if (!griefCheck)
		{
			player.addChatMessage(new ChatComponentText("You have griefing off, this doomsday is disabled automatically."));
			return;
		}

		for (int y1 = 0; y1 < range; y1++)
		{
			for (int x1 = 0; x1 < range; x1++)
			{
				for (int z1 = 0; z1 < range; z1++)
				{
					block = player.worldObj.getBlock(x + x1 - half, y + y1, z + z1 - half);

					if (!(block instanceof BlockAir) && rand.nextInt(48) == 0)
					{
						player.worldObj.setBlockToAir(x + x1 - half, y + y1, z + z1 - half);
						block = Blocks.air;
					}

					if (rand.nextInt(8) != 0)
					{
						if (block == Blocks.lava)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.ice);
						}

						if (block == Blocks.water)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.sand);
						}

						if (block == Blocks.stone || block == Blocks.cobblestone)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.gravel);
						}

						if (block == Blocks.gravel)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.dirt);
						}

						if (block == Blocks.netherrack)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.lava);
						}

						if (block == Blocks.ice)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.fire);
						}

						if (block == Blocks.sand)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.water);
						}

						if (block == Blocks.grass)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, TragicBlocks.Quicksand, 1, 2);
						}

						if (block == TragicBlocks.Quicksand && player.worldObj.getBlockMetadata(x + x1, y + y1, z + z1) == 1)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.clay);
						}

						if (block == TragicBlocks.Quicksand && player.worldObj.getBlockMetadata(x + x1, y + y1, z + z1) == 0)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.sand);
						}

						if (block == TragicBlocks.Quicksand && player.worldObj.getBlockMetadata(x + x1, y + y1, z + z1) == 2)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.lava);
						}

						if (block == Blocks.clay)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.stone);
						}

						if (block == Blocks.dirt)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.cobblestone);
						}

						if (block == Blocks.sandstone)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, TragicBlocks.Quicksand, 0, 2);
						}

						if (block == Blocks.leaves)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.web);
						}

						if (block == Blocks.web)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.hay_block);
						}

						if (block instanceof BlockAir && rand.nextInt(256) == 0)
						{
							switch(rand.nextInt(56))
							{
							case 1:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.wheat);
								break;
							case 5:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.glowstone);
								break;
							case 12:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.cactus);
								break;
							case 14:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.deadbush);
								break;
							case 18:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, TragicBlocks.CarrotBlock);
								break;
							case 26:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, TragicBlocks.PotatoBlock);
								break;
							case 32:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, TragicBlocks.Light);
								break;
							case 38:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.red_flower, rand.nextInt(10), 2);
								break;
							case 42:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.yellow_flower);
								break;
							case 46:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.double_plant, rand.nextInt(5), 2);
								break;
							case 52:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.pumpkin);
								break;
							default:
								player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.cake);
								break;
							}

						}
					}
				}
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player,
			boolean griefCheck) {

	}

}
