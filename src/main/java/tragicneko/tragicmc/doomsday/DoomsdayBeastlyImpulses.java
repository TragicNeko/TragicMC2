package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayBeastlyImpulses extends Doomsday {

	public DoomsdayBeastlyImpulses(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.CRISIS);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		int d0 = 12;
		
		d0 /= this.getCrisis(player);

		if (crucMoment)
		{
			d0 *= 2;
		}

		if (TragicNewConfig.allowInvulnerability) player.addPotionEffect(new PotionEffect(TragicPotions.Invulnerability.id, d0, 0));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, d0, 4));
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Beastly Impulses!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}

		if (!player.capabilities.isCreativeMode)
		{
			this.applyDoomAndCooldown(doom);
		}
	}

	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		int d0 = 12;
		
		d0 /= this.getCrisis(player);

		if (crucMoment)
		{
			d0 *= 2;
		}

		if (TragicNewConfig.allowInvulnerability) player.addPotionEffect(new PotionEffect(TragicPotions.Invulnerability.id, d0, 0));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, d0, 4));
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Beastly Impulses!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player,
			boolean griefCheck) {
		
	}

}
