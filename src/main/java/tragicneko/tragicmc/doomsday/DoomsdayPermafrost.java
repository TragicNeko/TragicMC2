package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPermafrost extends Doomsday {

	public DoomsdayPermafrost(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.WORLDSHAPER);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Permafrost!"));

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
			if (list.get(i) instanceof EntityMob) ((EntityLivingBase) list.get(i)).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, half));
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

					if (rand.nextInt(8) != 0)
					{
						if (block == Blocks.lava)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.obsidian);
						}
						else if (block == Blocks.water)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.ice);
						}
						else if (block == Blocks.ice)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.packed_ice);
						}
						else if (block instanceof BlockBush || block instanceof BlockDoublePlant || block instanceof BlockLeaves)
						{
							player.worldObj.setBlockToAir(x + x1 - half, y + y1, z + z1 - half);
						}
						else if (block == Blocks.air && World.doesBlockHaveSolidTopSurface(player.worldObj, x + x1 - half, y + y1 - 1, z + z1 - half))
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.snow_layer, rand.nextInt(8), 2);
						}
					}
				}
			}
		}
	}

	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Permafrost!"));

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
			if (list.get(i) instanceof EntityMob) ((EntityLivingBase) list.get(i)).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, half));
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

					if (rand.nextInt(8) != 0)
					{
						if (block == Blocks.lava)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.obsidian);
						}
						else if (block == Blocks.water)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.ice);
						}
						else if (block == Blocks.ice)
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.packed_ice);
						}
						else if (block instanceof BlockBush || block instanceof BlockDoublePlant)
						{
							player.worldObj.setBlockToAir(x + x1 - half, y + y1, z + z1 - half);
						}
						else if (block == Blocks.air && World.doesBlockHaveSolidTopSurface(player.worldObj, x + x1 - half, y + y1 - 1, z + z1 - half))
						{
							player.worldObj.setBlock(x + x1 - half, y + y1, z + z1 - half, Blocks.snow_layer, rand.nextInt(8), 2);
						}
					}
				}
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player, boolean griefCheck) {
		
	}

}
