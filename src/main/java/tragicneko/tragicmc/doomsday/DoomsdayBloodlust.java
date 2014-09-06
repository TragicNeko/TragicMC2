package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayBloodlust extends Doomsday {

	public DoomsdayBloodlust(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom);
	}

	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Bloodlust!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		int a = 2;

		if (crucMoment)
		{
			a *= 2;
		}

		player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 240 * a, a * 2));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 240 * a, a * 2));
		player.addPotionEffect(new PotionEffect(Potion.jump.id, 240 * a, a * 2));
		player.addPotionEffect(new PotionEffect(Potion.resistance.id, 240 * a, a * 2));
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 240 * a, a * 2));

		if (!crucMoment)
		{
			a = 1;
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 160 * a, 0));
			if (TragicNewConfig.allowDisorientation) player.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 160 * a, a));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		
		player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 60, 0));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 60, 0));
		player.addPotionEffect(new PotionEffect(Potion.jump.id, 60, 0));
		player.addPotionEffect(new PotionEffect(Potion.resistance.id, 60, 0));
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 0));
		
		player.addPotionEffect(new PotionEffect(Potion.confusion.id, 640, 0));
		if (TragicNewConfig.allowDisorientation) player.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 640, 1));
	}


}
