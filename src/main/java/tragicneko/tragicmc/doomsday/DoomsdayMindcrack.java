package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayMindcrack extends Doomsday implements IExtendedDoomsday {
	

	public DoomsdayMindcrack(int id) {
		super(id, EnumDoomType.COMBINATION);
		this.waitTime = 3;
		this.maxIterations = 300;
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
		double d0 = 12.0;
		effect.utilityList = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		if (effect.utilityList.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "You have used Mindcrack!"));

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
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double d0 = 12.0;
		effect.utilityList = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
		
		for (int i = 0; i < effect.utilityList.size(); i ++)
		{
			if (effect.utilityList.get(i) instanceof EntityCreature)
			{
				EntityCreature entity = (EntityCreature) effect.utilityList.get(i);
				if (entity.getMaxHealth() >= 40.0F) continue;
				entity.addPotionEffect(new PotionEffect(Potion.wither.id, 2400, 4));
				entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 2400, 10));
				if (TragicNewConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 240));
				entity.tasks.taskEntries.clear();
				entity.targetTasks.taskEntries.clear();
				entity.motionX = 0.0;
				entity.motionY = 0.0;
				entity.motionZ = 0.0;
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		
	}

}
