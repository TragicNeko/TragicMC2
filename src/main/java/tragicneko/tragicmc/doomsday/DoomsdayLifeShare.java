package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayLifeShare extends Doomsday {

	public DoomsdayLifeShare(int id) {
		super(id);
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Life Share!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
		
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
		List<EntityLivingBase> list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, player.boundingBox.expand(16.0, 16.0, 16.0));
		EntityLivingBase e;
		float total = 0F;
		
		if (list.size() > 0)
		{
			for (int i = 0; i < list.size(); i++)
			{
				e = list.get(i);
				total += e.getHealth();
			}
			
			total /= list.size() + 1;
			if (total < 1.0F) total = 1.0F;
			
			for (int i = 0; i < list.size(); i++)
			{
				e = list.get(i);
				
				if (e == player && crucMoment) player.setHealth(total * 2.5F);
				else e.setHealth(total);
			}
		}
			
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		
	}

}
