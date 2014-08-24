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

	public DoomsdayBerserker(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.OVERFLOW);
	}
	
	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		short overflow = this.getOverflow(doom);
		int a = MathHelper.ceiling_double_int(overflow / 10);

		if (a > 10)
		{
			a = 10;
		}

		if (a < 1)
		{
			a = 1;
		}

		if (crucMoment)
		{
			a *= 2;
		}

		player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 60 * a, a));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 60 * a, a));

		if (crucMoment)
		{
			a /= 4;
		}

		player.addPotionEffect(new PotionEffect(Potion.confusion.id, 30 * a));
		if (TragicNewConfig.allowSubmission) player.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 60 * a, a));
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Berserker!"));

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
		short overflow = this.getOverflow(doom);
		int a = MathHelper.ceiling_double_int(overflow / 10);

		if (a > 10)
		{
			a = 10;
		}

		if (a < 1)
		{
			a = 1;
		}

		if (crucMoment)
		{
			a *= 2;
		}

		player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 60 * a, a));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 60 * a, a));

		if (crucMoment)
		{
			a /= 4;
		}

		player.addPotionEffect(new PotionEffect(Potion.confusion.id, 30 * a));
		if (TragicNewConfig.allowSubmission) player.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 60 * a, a));
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Berserker!"));

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
