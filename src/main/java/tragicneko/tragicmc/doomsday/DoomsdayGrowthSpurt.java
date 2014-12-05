package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayGrowthSpurt extends Doomsday {

	private ArrayList<int[]> list = new ArrayList();

	public DoomsdayGrowthSpurt(int id) {
		super(id, EnumDoomType.COMBINATION);
	}

	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double radius = crucMoment ? 12.0D : 7.0D;
		list = WorldHelper.getBlocksInSphericalRange(player.worldObj, radius, player.posX, player.posY, player.posZ);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		double plantCount = 0.0D;
		int[] coords;

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			Block block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);

			if (block == Blocks.gravel)
			{
				player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.farmland);
				plantCount += 0.5;
			}
			else if (block == Blocks.dirt)
			{
				player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.grass);
				plantCount += 0.5;
			}
			else if (block == Blocks.cobblestone)
			{
				player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.mossy_cobblestone);
				plantCount += 0.5;
			}
			else if (block == Blocks.sand)
			{
				player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.dirt);
				plantCount += 0.5;
			}
			else if (block == Blocks.air && rand.nextInt(4) == 0)
			{
				Block block2;
				if (World.doesBlockHaveSolidTopSurface(player.worldObj, coords[0], coords[1] - 1, coords[2]))
				{
					block2 = player.worldObj.getBlock(coords[0], coords[1] - 1, coords[2]);
					if (block2 == Blocks.grass)
					{
						if (rand.nextBoolean())
						{
							if (rand.nextBoolean())
							{
							player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.tallgrass);
							}
							else
							{
								player.worldObj.setBlock(coords[0], coords[1], coords[2], rand.nextBoolean() ? Blocks.red_flower : Blocks.yellow_flower);
							}
						}
						else
						{
							player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.double_plant, 0, 2);
						}
					}
					else if (block2 == Blocks.mycelium)
					{
						player.worldObj.setBlock(coords[0], coords[1], coords[2], rand.nextBoolean() ? Blocks.red_mushroom : Blocks.brown_mushroom);
					}
					else if (block2 instanceof BlockLog || block2 instanceof BlockLeaves)
					{
						player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.vine);
					}
				}
			}
		}

		float f = MathHelper.ceiling_double_int(plantCount);

		if (f > 40.0F)
		{
			f = 40.0F;
		}

		if (f > 0.0F)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "You have used Growth Spurt!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}

			player.heal(f);
			player.playSound("random.breath", rand.nextFloat(), rand.nextFloat());
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Nowhere to grow plants..."));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}

}
