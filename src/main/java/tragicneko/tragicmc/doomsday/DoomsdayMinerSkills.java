package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

import com.google.common.collect.Sets;

public class DoomsdayMinerSkills extends Doomsday {

	private static final Set minableBlocks = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.dirt, Blocks.cobblestone, Blocks.stone, Blocks.mycelium, Blocks.gravel, Blocks.sand,
			Blocks.sandstone, Blocks.clay, TragicBlocks.DarkStone, TragicBlocks.DeadDirt});
	
	private ArrayList<int[]> list = new ArrayList();
	private ArrayList<int[]> list2 = new ArrayList();
	private ArrayList<int[]> list3 = new ArrayList();
	private ArrayList<int[]> list4 = new ArrayList();

	public DoomsdayMinerSkills(int id) {
		super(id, EnumDoomType.WORLDSHAPER);
	}
	
	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		list = WorldHelper.getBlocksInSphericalRange(player.worldObj, 6.0D, player.posX, player.posY, player.posZ);
		list2.clear();
		list3.clear();
		list4.clear();
		
		double d0 = crucMoment ? 1.5D : 1.0D;
		
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Miner Skills!"));
		player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, (int) (600 * d0), 3));

		if (crucMoment)
		{
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, (int) (400 * d0), 1));
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}

	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player,	boolean crucMoment) {
		
		int limit = crucMoment ? 100 : 60;
		Block block;
		int[] coords;
		double range = crucMoment ? 6.0D : 4.0D;
		
		for (int i = 0; i < list.size(); i++)
		{
			if (rand.nextInt(48) != 0) continue;
			
			coords = list.get(i);
			block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
			
			if (list2.isEmpty() && minableBlocks.contains(block))
			{
				list2 = WorldHelper.getBlocksInSphericalRange(player.worldObj, range, coords[0], coords[1], coords[2]);
			}
			else if (list3.isEmpty() && minableBlocks.contains(block))
			{
				list3 = WorldHelper.getBlocksInSphericalRange(player.worldObj, range, coords[0], coords[1], coords[2]);
			}
			else if (list4.isEmpty() && minableBlocks.contains(block))
			{
				list4 = WorldHelper.getBlocksInSphericalRange(player.worldObj, range, coords[0], coords[1], coords[2]);
			}
			else
			{
				if (!list2.isEmpty() && !list3.isEmpty() && !list4.isEmpty()) break;
			}
		}
		
		for (int i = 0; i < list2.size(); i++)
		{
			if (rand.nextInt(8) != 0) continue;
			coords = list2.get(i);
			block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
			
			if (minableBlocks.contains(block))
			{
				player.worldObj.func_147480_a(coords[0], coords[1], coords[2], true);
			}
		}
		
		for (int i = 0; i < list3.size(); i++)
		{
			if (rand.nextInt(8) != 0) continue;
			coords = list3.get(i);
			block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
			
			if (minableBlocks.contains(block))
			{
				player.worldObj.func_147480_a(coords[0], coords[1], coords[2], true);
			}
		}
		
		for (int i = 0; i < list4.size(); i++)
		{
			if (rand.nextInt(8) != 0) continue;
			coords = list4.get(i);
			block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
			
			if (minableBlocks.contains(block))
			{
				player.worldObj.func_147480_a(coords[0], coords[1], coords[2], true);
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, (int) 300, 2));
	}

}
