package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayFreeze extends Doomsday {
	
	public DoomsdayFreeze(int id) {
		super(id);
	}
	
	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		effect.utilityList = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(8.0, 8.0, 8.0));
		
		if (effect.utilityList.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Freeze!"));
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
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
		for (int i = 0; i < effect.utilityList.size(); i++)
		{
			if (effect.utilityList.get(i) instanceof EntityLivingBase && rand.nextInt(4) == 0)
			{
				EntityLivingBase entity = (EntityLivingBase) effect.utilityList.get(i);
				entity.motionX = 0;
				entity.motionY = 0;
				entity.motionZ = 0;
				entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, rand.nextInt(120) + 120));
				if (crucMoment && TragicNewConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, rand.nextInt(240) + 120, rand.nextInt(6)));
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		
	}

}
