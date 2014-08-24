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

public class DoomsdaySkullCrusher extends Doomsday {

	public DoomsdaySkullCrusher(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.CRISIS);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		double d0 = 2.0;
		float crisis = this.getCrisis(player);

		if (crisis <= 0.5F)
		{
			d0 = 4.0;
		}

		if (crisis <= 0.25F)
		{
			d0 = 6.0;
		}

		if (crucMoment)
		{
			d0 *= 2;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
		for (int x = 0; x < list.size(); x++)
		{
			if (list.get(x) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(x);
				entity.applyEntityCollision(player);
				entity.motionX *= 1.25D * d0;
				entity.motionZ *= 1.25D * d0;
				entity.motionY += 0.4D + (d0 * 0.25D);

				entity.addPotionEffect(new PotionEffect(Potion.wither.id, 600, 4));
				if (TragicNewConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 120));
			}
		}
		if (list.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You have used Skull Crusher!"));

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
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entities in range..."));
		}
	}

	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		double d0 = 2.0;
		float crisis = this.getCrisis(player);

		if (crisis <= 0.5F)
		{
			d0 = 4.0;
		}

		if (crisis <= 0.25F)
		{
			d0 = 6.0;
		}

		if (crucMoment)
		{
			d0 *= 2;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
		for (int x = 0; x < list.size(); x++)
		{
			if (list.get(x) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(x);
				entity.applyEntityCollision(player);
				entity.motionX *= 1.25D * d0;
				entity.motionZ *= 1.25D * d0;
				entity.motionY += 0.4D + (d0 * 0.25D);

				entity.addPotionEffect(new PotionEffect(Potion.wither.id, 600, 4));
				if (TragicNewConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 120));
			}
		}
		if (list.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You have used Skull Crusher!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entities in range..."));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player,
			boolean griefCheck) {
		
	}

}
