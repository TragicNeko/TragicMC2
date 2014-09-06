package tragicneko.tragicmc.doomsday;

import java.util.HashMap;
import java.util.Map;
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
	
	private Map<Integer, int[]> map = new HashMap();
	private Map<Integer, int[]> map2 = new HashMap();
	private Map<Integer, int[]> map3 = new HashMap();
	private Map<Integer, int[]> map4 = new HashMap();

	public DoomsdayMinerSkills(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.WORLDSHAPER);
	}
	
	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
		
		map = WorldHelper.getBlocksInSphericalRange(player.worldObj, 6.0D, player.posX, player.posY, player.posZ);
		map2.clear();
		map3.clear();
		map4.clear();
		
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
		
		for (int i = 0; i < map.size(); i++)
		{
			if (rand.nextInt(48) != 0) continue;
			
			coords = map.get(i);
			block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
			
			if (map2.isEmpty() && minableBlocks.contains(block))
			{
				map2 = WorldHelper.getBlocksInSphericalRange(player.worldObj, range, coords[0], coords[1], coords[2]);
			}
			else if (map3.isEmpty() && minableBlocks.contains(block))
			{
				map3 = WorldHelper.getBlocksInSphericalRange(player.worldObj, range, coords[0], coords[1], coords[2]);
			}
			else if (map4.isEmpty() && minableBlocks.contains(block))
			{
				map4 = WorldHelper.getBlocksInSphericalRange(player.worldObj, range, coords[0], coords[1], coords[2]);
			}
			else
			{
				if (!map2.isEmpty() && !map3.isEmpty() && !map4.isEmpty()) break;
			}
		}
		
		for (int i = 0; i < map2.size(); i++)
		{
			if (rand.nextInt(8) != 0) continue;
			coords = map2.get(i);
			block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
			
			if (minableBlocks.contains(block))
			{
				player.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
			}
		}
		
		for (int i = 0; i < map3.size(); i++)
		{
			if (rand.nextInt(8) != 0) continue;
			coords = map3.get(i);
			block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
			
			if (minableBlocks.contains(block))
			{
				player.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
			}
		}
		
		for (int i = 0; i < map4.size(); i++)
		{
			if (rand.nextInt(8) != 0) continue;
			coords = map4.get(i);
			block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
			
			if (minableBlocks.contains(block))
			{
				player.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, (int) 300, 2));
	}

}
