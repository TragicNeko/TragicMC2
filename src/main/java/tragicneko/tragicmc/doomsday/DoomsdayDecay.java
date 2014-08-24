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

public class DoomsdayDecay extends Doomsday {

	public DoomsdayDecay(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom);
	}
	
	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(8.0, 8.0, 8.0));
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof EntityLivingBase && rand.nextInt(4) == 0)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);

				entity.addPotionEffect(new PotionEffect(Potion.wither.id, rand.nextInt(120) + 120));
				entity.addPotionEffect(new PotionEffect(Potion.hunger.id, rand.nextInt(240) + 160, rand.nextInt(2)));
				if (crucMoment && TragicNewConfig.allowCripple) entity.addPotionEffect(new PotionEffect(TragicPotions.Cripple.id, rand.nextInt(240) + 120, rand.nextInt(6)));
			}
		}

		if (list.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Decay!"));
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
		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(8.0, 8.0, 8.0));
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof EntityLivingBase && rand.nextInt(4) == 0)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);

				entity.addPotionEffect(new PotionEffect(Potion.wither.id, rand.nextInt(120) + 120, rand.nextInt(2)));
				entity.addPotionEffect(new PotionEffect(Potion.hunger.id, rand.nextInt(240) + 160, rand.nextInt(2)));
				if (crucMoment && TragicNewConfig.allowCripple) entity.addPotionEffect(new PotionEffect(TragicPotions.Cripple.id, rand.nextInt(240) + 120, rand.nextInt(6)));
			}
		}

		if (list.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Decay!"));
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
