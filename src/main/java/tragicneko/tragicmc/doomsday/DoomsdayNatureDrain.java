package tragicneko.tragicmc.doomsday;

import java.util.List;

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
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayNatureDrain extends Doomsday implements IExtendedDoomsday {

	public DoomsdayNatureDrain(int id) {
		super(id, EnumDoomType.WORLDSHAPER);
		this.waitTime = 10;
		this.maxIterations = 50;
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double radius = crucMoment ? 12.0D : 7.0D;
		List list = WorldHelper.getBlocksInSphericalRange(player.worldObj, radius, player.posX, player.posY, player.posZ);
		
		boolean griefCheck = TragicConfig.griefConfigs[0];
		double plantCount = 0.0D;
		int[] coords;

		for (int i = 0; i < list.size(); i++)
		{
			coords = (int[]) list.get(i);
			Block block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);

			if (block instanceof BlockReed || block instanceof BlockSapling || block instanceof BlockFlower 
					|| block instanceof BlockDoublePlant || block instanceof BlockMushroom || block instanceof BlockCrops
					|| block instanceof BlockStem || block instanceof BlockLeaves || block instanceof BlockTallGrass)
			{
				if (griefCheck)
				{
					if (block instanceof BlockLeaves)
					{
						player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.gravel);
					}
					else
					{
						player.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
					}
				}
				plantCount += 0.2;
			}
			else if (block instanceof BlockGrass)
			{
				if (griefCheck)
				{
					player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.dirt);
				}
				plantCount += 0.1;
			}

			if (block instanceof BlockFarmland)
			{
				if (griefCheck)
				{
					player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.dirt);
				}
				plantCount += 0.15;
			}
		}

		float f = MathHelper.ceiling_double_int(plantCount);

		if (crucMoment)
		{
			f *= 1.5;
		}

		if (f > 40.0F)
		{
			f = 40.0F;
		}

		if (f > 0.0F)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Nature Drain!"));
			
			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
			
			player.heal(f);
			player.playSound("random.breath", rand.nextFloat(), rand.nextFloat());
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No plantlife in range..."));
		}
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{
		double radius = crucMoment ? 12.0D : 7.0D;
		List list = WorldHelper.getBlocksInSphericalRange(player.worldObj, radius, player.posX, player.posY, player.posZ);
		
		boolean griefCheck = TragicConfig.griefConfigs[0];
		double plantCount = 0.0D;
		int[] coords;

		for (int i = 0; i < list.size(); i++)
		{
			coords = (int[]) list.get(i);
			Block block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);

			if (block instanceof BlockReed || block instanceof BlockSapling || block instanceof BlockFlower 
					|| block instanceof BlockDoublePlant || block instanceof BlockMushroom || block instanceof BlockCrops
					|| block instanceof BlockStem || block instanceof BlockLeaves || block instanceof BlockTallGrass)
			{
				if (griefCheck)
				{
					if (block instanceof BlockLeaves)
					{
						player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.gravel);
					}
					else
					{
						player.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
					}
				}
				plantCount += 0.2;
			}
			else if (block instanceof BlockGrass)
			{
				if (griefCheck)
				{
					player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.dirt);
				}
				plantCount += 0.1;
			}

			if (block instanceof BlockFarmland)
			{
				if (griefCheck)
				{
					player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.dirt);
				}
				plantCount += 0.15;
			}
		}

		float f = MathHelper.ceiling_double_int(plantCount);

		if (crucMoment)
		{
			f *= 1.5;
		}

		if (f > 40.0F)
		{
			f = 40.0F;
		}

		if (f > 0.0F)
		{
			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
			
			player.heal(f);
			player.playSound("random.breath", rand.nextFloat(), rand.nextFloat());
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No plantlife in range..."));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		float f = rand.nextFloat() * 10.0F;
		player.addExhaustion(f);
	}

	public Doomsday getCombination() {
		return Doomsday.RealityAlter;
	}
}
