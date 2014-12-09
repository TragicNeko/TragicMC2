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
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayTorment extends Doomsday {
	
	private List<Entity> list = new ArrayList();

	public DoomsdayTorment(int id) {
		super(id);
	}
	
	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double d0 = crucMoment ? 12.0D : 6.0D;
		list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
		
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
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment) 
	{
		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);
				int f = crucMoment ? 2000 : 200;
				
				entity.addPotionEffect(new PotionEffect(Potion.wither.id, f, 2 + rand.nextInt(2)));
				if (TragicNewConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, f, 2 + rand.nextInt(2)));
				if (TragicNewConfig.allowSubmission) entity.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, f, 2 + rand.nextInt(2)));
			}
		}

		
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.wither.id, 200, 2 + rand.nextInt(2)));
		if (TragicNewConfig.allowStun) player.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 200, 2 + rand.nextInt(2)));
		if (TragicNewConfig.allowSubmission) player.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 200, 2 + rand.nextInt(2)));
	}

}
