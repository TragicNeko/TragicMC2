package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPulse extends Doomsday implements IThreadedDoomsday{

	public DoomsdayPulse(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.OVERFLOW);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		DoomThread thread = new DoomThread(this, doom, crucMoment, griefCheck);

		if (!thread.isAlive() && !thread.isInterrupted())
		{
			thread.start();
		}

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Pulse!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsdayFromThread(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{		
		double d0 = 8.0;

		if (crucMoment)
		{
			d0 += 8.0;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0 / 2, d0));

		if (list.size() == 0)
		{
			return;
		}

		for (int j = 0; j < list.size(); j++)
		{
			if (list.get(j) instanceof EntityLivingBase && player.canEntityBeSeen(list.get(j)))
			{
				list.get(j).motionY = 1.0;
				if (TragicNewConfig.allowSubmission) ((EntityLivingBase) list.get(j)).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 120, rand.nextInt(2)));
			}
		}		
	}

	public void useDoomsdayFromThread2(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		double d0 = 8.0;

		if (crucMoment)
		{
			d0 += 8.0;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0 / 2, d0));

		if (list.size() < 1)
		{
			return;
		}

		if (!player.capabilities.isCreativeMode)
		{
			this.applyDoomAndCooldown(doom);
		}

		for (int j = 0; j < list.size(); j++)
		{
			if (list.get(j) instanceof EntityLivingBase)
			{
				list.get(j).motionY = -1.0;
			}
		}
	}

	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		DoomThread thread = new DoomThread(this, doom, crucMoment, griefCheck, true);

		if (!thread.isAlive() && !thread.isInterrupted())
		{
			thread.start();
		}

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Pulse!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsdayFromThreadThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{		
		double d0 = 8.0;

		if (crucMoment)
		{
			d0 += 8.0;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0 / 2, d0));

		if (list.size() == 0)
		{
			return;
		}

		for (int j = 0; j < list.size(); j++)
		{
			if (list.get(j) instanceof EntityLivingBase && player.canEntityBeSeen(list.get(j)))
			{
				list.get(j).motionY = 1.0;
				if (TragicNewConfig.allowSubmission) ((EntityLivingBase) list.get(j)).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 120, rand.nextInt(2)));
			}
		}		
	}

	public void useDoomsdayFromThreadThroughCommand2(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		double d0 = 8.0;

		if (crucMoment)
		{
			d0 += 8.0;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0 / 2, d0));

		if (list.size() < 1)
		{
			return;
		}

		for (int j = 0; j < list.size(); j++)
		{
			if (list.get(j) instanceof EntityLivingBase)
			{
				list.get(j).motionY = -1.0;
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player,
			boolean griefCheck) {

	}

}
