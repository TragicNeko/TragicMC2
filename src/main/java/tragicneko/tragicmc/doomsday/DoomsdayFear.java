package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayFear extends Doomsday {

	public DoomsdayFear(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom);
	}
	
	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		double d0 = 6.0;

		if (crucMoment)
		{
			d0 *= 6;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);
				entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 300));
				if (TragicNewConfig.allowDisorientation) entity.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 300));
				if (TragicNewConfig.allowFear) entity.addPotionEffect(new PotionEffect(TragicPotions.Fear.id, 300, crucMoment ? 1 + rand.nextInt(3) : 0));
				if (crucMoment && TragicNewConfig.allowSubmission) entity.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 300, 5));

				if (crucMoment)
				{
					int x = MathHelper.floor_double(entity.posX);
					int y = MathHelper.floor_double(entity.posY);
					int z = MathHelper.floor_double(entity.posZ);

					entity.setPositionAndUpdate(x + rand.nextInt(7) - 3, y + 5 + rand.nextInt(5), z + rand.nextInt(7) - 3);
				}
			}
		}

		if (list.size() > 0)
		{

			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Afraid of the Dark!"));

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
			d0 *= 6;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);
				entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 300));
				if (TragicNewConfig.allowDisorientation) entity.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 300));
				if (TragicNewConfig.allowFear) entity.addPotionEffect(new PotionEffect(TragicPotions.Fear.id, 300, crucMoment ? 1 + rand.nextInt(3) : 0));
				if (crucMoment && TragicNewConfig.allowSubmission) entity.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 300, 5));

				if (crucMoment)
				{
					int x = MathHelper.floor_double(entity.posX);
					int y = MathHelper.floor_double(entity.posY);
					int z = MathHelper.floor_double(entity.posZ);

					entity.setPositionAndUpdate(x + rand.nextInt(7) - 3, y + 5 + rand.nextInt(5), z + rand.nextInt(7) - 3);
				}
			}
		}

		if (list.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Afraid of the Dark!"));

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
