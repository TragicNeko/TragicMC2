package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdaySharpen extends Doomsday {

	public DoomsdaySharpen(int id) {
		super(id);
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, crucMoment ? 40 : 20, 10));
		
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Sharpen!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		
	}

}
