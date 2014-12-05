package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
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

	private List<Entity> list = new ArrayList();

	public DoomsdayHarmonizer(int id) {
		super(id, EnumDoomType.CRISIS);
	}

	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double d0 = 12.0;
		list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		if (list.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You have used Harmonizer!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entities close enough..."));
		}
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		float crisis = this.getCrisis(player);
		crisis /= 1.0F / 20.0F;

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);

				int dur = crucMoment ? 600 : 300;

				if (TragicNewConfig.allowHarmony)
				{
					entity.addPotionEffect(new PotionEffect(TragicPotions.Harmony.id, dur));
				}
				else
				{
					entity.addPotionEffect(new PotionEffect(Potion.blindness.id, dur));
				}

				if (player.getHealth() < player.getMaxHealth())
				{
					float potato = crucMoment ? crisis * 2 : crisis;
					player.heal(potato);
				}
			}
		}
	}


	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		if (TragicNewConfig.allowHarmony)
		{
			player.addPotionEffect(new PotionEffect(TragicPotions.Harmony.id, 240)); 
		}
		else
		{
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 240)); 
		}
	}

}
