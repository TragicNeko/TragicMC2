package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayHarden extends Doomsday {

	public DoomsdayHarden(int id) {
		super(id);
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		boolean flag = TragicConfig.allowInvulnerability;
		player.addPotionEffect(new PotionEffect(flag ? TragicPotion.Invulnerability.id : Potion.resistance.id, crucMoment ? 40 : 20, flag ? 0 : 10));
		
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Harden!"));

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
