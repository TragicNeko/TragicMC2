package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayParadigmShift extends Doomsday {

	public DoomsdayParadigmShift(int id) {
		super(id, EnumDoomType.CRISIS);
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "You have used Paradigm Shift!"));
		if (crucMoment) player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));

		effect.utilityInt = (int) ((1 / this.getCrisis(player)) * 10);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		TragicMC.logInfo("Integer value is " + effect.utilityInt);
		boolean flag = effect.utilityInt >= 15 || effect.isCommandActivated;
		if (crucMoment)
		{
			Doomsday dday = this.doomsdayList[rand.nextInt(this.doomsdayList.length)];

			while (dday == null && dday != this)
			{
				dday = this.doomsdayList[rand.nextInt(this.doomsdayList.length)];
			}

			if (!flag)
			{
				dday.activateDoomsday(doom);
			}
			else
			{
				DoomsdayEffect effect2 = new DoomsdayEffect(dday.doomID, doom, flag);
				DoomsdayManager.registerDoomsdayEffect(player.getUniqueID(), effect2);
			}
		}

		Doomsday dday = this.doomsdayList[rand.nextInt(this.doomsdayList.length)];

		while (dday == null && dday != this)
		{
			dday = this.doomsdayList[rand.nextInt(this.doomsdayList.length)];
		}

		if (!flag)
		{
			dday.activateDoomsday(doom);
		}
		else
		{
			DoomsdayEffect effect2 = new DoomsdayEffect(dday.doomID, doom, flag);
			DoomsdayManager.registerDoomsdayEffect(player.getUniqueID(), effect2);
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		if (TragicConfig.allowCooldown) doom.increaseCooldown(200);
	}

}
