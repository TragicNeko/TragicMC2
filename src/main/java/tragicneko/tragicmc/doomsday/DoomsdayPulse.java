package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPulse extends Doomsday implements IExtendedDoomsday{

	public DoomsdayPulse(int id) {
		super(id, EnumDoomType.OVERFLOW);
		this.waitTime = 10;
		this.maxIterations = 20;
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		double d0 = crucMoment ? 16.0 : 8.0;
		List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0 / 2, d0));

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
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{		
		double d0 = crucMoment ? 16.0 : 8.0;
		List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0 / 2, d0));
		
		for (int j = 0; j < list.size(); j++)
		{
			if (list.get(j) instanceof EntityLivingBase && player.canEntityBeSeen((Entity) list.get(j)))
			{
				if (effect.utilityFlag)
				{
					((Entity) list.get(j)).motionY = 1.0;
					if (TragicConfig.allowSubmission) ((EntityLivingBase) list.get(j)).addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 120, rand.nextInt(2)));
				}
				else
				{
					((Entity) list.get(j)).motionY = -1.0;
				}
			}
		}
		
		effect.utilityFlag = !effect.utilityFlag;
	}
	
	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.motionY += 1.4D;
	}

}
