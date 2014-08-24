package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayLightShove extends Doomsday {

	public DoomsdayLightShove(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.CRISIS);
	}
	
	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		float crisis = this.getCrisis(player);
		double d0 = 3.0;

		if (crucMoment)
		{
			d0 *= 2;
		}
		
		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);

				entity.applyEntityCollision(player);
				if (crucMoment)
				{
					entity.motionX *= 1.8;
					entity.motionZ *= 1.8;
					entity.motionY *= 1.8;
				}
			}
		}

		if (list.size() > 0)
		{

			player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You have used Light Shove!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}

			if (!player.capabilities.isCreativeMode)
			{
				this.applyDoomAndCooldown(doom);
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entity close enough..."));
		}
	}
	
	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		float crisis = this.getCrisis(player);
		double d0 = 3.0;

		if (crucMoment)
		{
			d0 *= 2;
		}
		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);

				entity.applyEntityCollision(player);
				
				if (crucMoment)
				{
					entity.motionX *= 1.8;
					entity.motionZ *= 1.8;
					entity.motionY *= 1.8;
				}
			}
		}

		if (list.size() > 0)
		{

			player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You have used Light Shove!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entity close enough..."));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player,
			boolean griefCheck) {
		
	}

}
