package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayReaperLaugh extends Doomsday implements IExtendedDoomsday {

	public DoomsdayReaperLaugh(int id) {
		super(id, EnumDoomType.OVERFLOW);
		this.waitTime = 15;
		this.maxIterations = 20;
	}
	
	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{
		if (TragicConfig.allowImmunity) player.addPotionEffect(new PotionEffect(TragicPotion.Immunity.id, 300, 0));
		
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Reaper Laugh!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) 
	{		
		for (int l = 0; l < 3; l++)
		{
			double d0 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posX) - player.posX; 
			double d1 = (MathHelper.getRandomIntegerInRange(rand, -2, 2) + player.posY) - player.posY;
			double d2 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posZ) - player.posZ;

			EntityPumpkinbomb bomb = new EntityPumpkinbomb(player.worldObj, player);
			bomb.setPosition(player.posX + (d0 * 0.115), player.posY + 0.6D, player.posZ + (d2 * 0.115));
			bomb.motionX = d0;
			bomb.motionY = d1;
			bomb.motionZ = d2;
			player.worldObj.spawnEntityInWorld(bomb);
		}	
	}
	
	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}
}
