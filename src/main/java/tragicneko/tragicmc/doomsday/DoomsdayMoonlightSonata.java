package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayMoonlightSonata extends Doomsday {

	public DoomsdayMoonlightSonata(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.ULTIMATE);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) {
		
		long time = player.worldObj.getWorldTime();
		if (time >= 1500 && time <= 1700 && player.worldObj.canBlockSeeTheSky((int) player.posX, (int) player.posY - 1, (int) player.posZ))
		{
			doom.fillDoom();
			if (!player.capabilities.isCreativeMode) this.applyDoomAndCooldown(doom);
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Not the proper time to use that..."));
		}
		
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_BLUE + "You have used Moonlight Sonata!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) {
		
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player, boolean griefCheck) {
		
	}

}
