package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayHuntersInstinct extends Doomsday {

	public DoomsdayHuntersInstinct(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom);
	}
	
	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Hunter's Instinct!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}
	
	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{
		player.addPotionEffect(new PotionEffect(Potion.jump.id, 400, 1));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 400, 2));
		player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 400, 0));

		if (crucMoment)
		{
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 1));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
	}
}
