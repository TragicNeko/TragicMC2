package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayHarmonizer extends Doomsday {

	public DoomsdayHarmonizer(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.CRISIS);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) {

		double d0 = 12.0;

		float crisis = this.getCrisis(player);

		crisis /= 1.0F / 20.0F;

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);

				short dur = 300;

				if (crucMoment)
				{
					dur *= 2;
				}

				if (TragicNewConfig.allowDisorientation)
				{
					entity.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, dur));
				}
				else
				{
					entity.addPotionEffect(new PotionEffect(Potion.blindness.id, dur));
				}

				if (TragicNewConfig.allowHarmony) entity.addPotionEffect(new PotionEffect(TragicPotions.Harmony.id, dur));

				if (player.getHealth() < player.getMaxHealth())
				{
					float potato = crisis;

					if (crucMoment) 
					{
						potato *= 2;
					}

					player.heal(potato);
				}
			}
		}

		if (list.size() > 0)
		{

			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Harmonizer!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}

			if (!player.capabilities.isCreativeMode)
			{
				this.applyDoomAndCooldown(doom);
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entity close enough..."));
		}
	}

	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) {
		double d0 = 12.0;

		float crisis = this.getCrisis(player);

		crisis /= 1.0F / 20.0F;

		if (crucMoment)
		{
			d0 *=2;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);

				short dur = 300;

				if (crucMoment)
				{
					dur *= 2;
				}

				if (TragicNewConfig.allowDisorientation)
				{
					entity.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, dur));
				}
				else
				{
					entity.addPotionEffect(new PotionEffect(Potion.blindness.id, dur));
				}

				if (TragicNewConfig.allowHarmony) entity.addPotionEffect(new PotionEffect(TragicPotions.Harmony.id, dur));

				if (player.getHealth() < player.getMaxHealth())
				{
					float potato = crisis;

					if (crucMoment) 
					{
						potato *= 2;
					}

					player.heal(potato);
				}
			}
		}

		if (list.size() > 0)
		{

			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Harmonizer!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entity close enough..."));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player,
			boolean griefCheck) {

	}

}
