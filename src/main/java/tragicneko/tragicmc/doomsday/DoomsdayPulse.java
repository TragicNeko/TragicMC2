package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPulse extends Doomsday implements IExtendedDoomsday{

	private int phase = 0;
	private List<Entity> list = new ArrayList();

	public DoomsdayPulse(int id) {
		super(id, EnumDoomType.OVERFLOW);
		this.waitTime = 10;
		this.maxIterations = 20;
	}

	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		if (this.phase != 0) this.phase = 0;

		double d0 = crucMoment ? 16.0 : 8.0;
		list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0 / 2, d0));

		if (list.isEmpty())
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entities in range..."));
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Pulse!"));
			
			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
		}
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{		
		double d0 = crucMoment ? 16.0 : 8.0;
		list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0 / 2, d0));
		
		for (int j = 0; j < list.size(); j++)
		{
			if (list.get(j) instanceof EntityLivingBase && player.canEntityBeSeen(list.get(j)))
			{
				if (phase % 2 == 0)
				{
					list.get(j).motionY = 1.0;
					if (TragicNewConfig.allowSubmission) ((EntityLivingBase) list.get(j)).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 120, rand.nextInt(2)));
				}
				else
				{
					list.get(j).motionY = -1.0;
				}
			}
		}
		phase++;
	}
	
	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.motionY += 1.4D;
	}

}
