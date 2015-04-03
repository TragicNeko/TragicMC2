package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayLightShove extends Doomsday {

	public DoomsdayLightShove(int id) {
		super(id, EnumDoomType.CRISIS);
	}
	
	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
		float crisis = this.getCrisis(player);
		double d0 = crucMoment ? 6.0D : 3.0D;
		
		effect.utilityList = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
		
		if (effect.utilityList.size() > 0)
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
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) 
	{
		for (int i = 0; i < effect.utilityList.size(); i ++)
		{
			if (effect.utilityList.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) effect.utilityList.get(i);
				if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;

				entity.applyEntityCollision(player);
				if (crucMoment)
				{
					entity.motionX *= 1.8;
					entity.motionZ *= 1.8;
					entity.motionY *= 1.8;
				}
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		
	}

}
