package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayTorment extends Doomsday {

	public DoomsdayTorment(int id) {
		super(id);
	}
	
	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double d0 = crucMoment ? 12.0D : 6.0D;
		effect.utilityList = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
		
		if (effect.utilityList.size() > 0)
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
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) 
	{
		for (int i = 0; i < effect.utilityList.size(); i ++)
		{
			if (effect.utilityList.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) effect.utilityList.get(i);
				int f = crucMoment ? 2000 : 200;
				
				entity.addPotionEffect(new PotionEffect(Potion.wither.id, f, 2 + rand.nextInt(2)));
				if (TragicConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, f, 2 + rand.nextInt(2)));
				if (TragicConfig.allowSubmission) entity.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, f, 2 + rand.nextInt(2)));
			}
		}

		
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.wither.id, 200, 2 + rand.nextInt(2)));
		if (TragicConfig.allowStun) player.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 200, 2 + rand.nextInt(2)));
		if (TragicConfig.allowSubmission) player.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 200, 2 + rand.nextInt(2)));
	}

	public Doomsday getCombination() {
		return Doomsday.Mindcrack;
	}
}
