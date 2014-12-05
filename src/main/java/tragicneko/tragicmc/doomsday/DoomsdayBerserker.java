package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayBerserker extends Doomsday {

	public DoomsdayBerserker(int id) {
		super(id, EnumDoomType.OVERFLOW);
	}
	
	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Berserker!"));
		
		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}
	
	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{
		short overflow = this.getOverflow(doom);
		int a = MathHelper.ceiling_double_int(overflow / 10);

		MathHelper.clamp_int(a, 1, 10);
		if (crucMoment) a *= 2;

		player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 60 * a, a));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 60 * a, a));

		if (crucMoment) a /= 4;

		player.addPotionEffect(new PotionEffect(Potion.confusion.id, 30 * a));
		if (TragicNewConfig.allowSubmission) player.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 60 * a, a));
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		short overflow = this.getOverflow(doom);
		int a = MathHelper.ceiling_double_int(overflow / 10);

		MathHelper.clamp_int(a, 1, 10);

		player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 30 * a, 0));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 30 * a, 0));

		player.addPotionEffect(new PotionEffect(Potion.confusion.id, 120 * a, a));
		if (TragicNewConfig.allowSubmission) player.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 120 * a, a));
	}
}
