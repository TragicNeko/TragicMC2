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

public class DoomsdayTorment extends Doomsday {

	public DoomsdayTorment(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		double d0 = 6.0;

		if (crucMoment)
		{
			d0 = 12.0;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);

				int f = 200;

				if (crucMoment)
				{
					f = 2000;
				}

				entity.addPotionEffect(new PotionEffect(Potion.poison.id, f, 2 + rand.nextInt(2)));
				if (TragicNewConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, f, 2 + rand.nextInt(2)));
				if (TragicNewConfig.allowSubmission) entity.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, f, 2 + rand.nextInt(2)));
			}
		}

		if (list.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Torment!"));

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
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		double d0 = 6.0;

		if (crucMoment)
		{
			d0 = 12.0;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);

				int f = 200;

				if (crucMoment)
				{
					f = 2000;
				}

				entity.addPotionEffect(new PotionEffect(Potion.poison.id, f, 2 + rand.nextInt(2)));
				if (TragicNewConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, f, 2 + rand.nextInt(2)));
				if (TragicNewConfig.allowSubmission) entity.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, f, 2 + rand.nextInt(2)));
			}
		}

		if (list.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Torment!"));

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
