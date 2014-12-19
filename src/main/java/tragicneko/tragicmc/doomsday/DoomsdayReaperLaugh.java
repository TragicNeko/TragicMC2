package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotions;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
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
		if (TragicNewConfig.allowImmunity) player.addPotionEffect(new PotionEffect(TragicPotions.Immunity.id, 300, 0));
		
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Reaper Laugh!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom,	EntityPlayer player, boolean crucMoment) 
	{		
		for (int l = 0; l < 5; l++)
		{
			double d0 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posX) - player.posX; 
			double d1 = (MathHelper.getRandomIntegerInRange(rand, -2, 2) + player.posY) - player.posY;
			double d2 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posZ) - player.posZ; 

			EntityWitherSkull fireball = new EntityWitherSkull(player.worldObj, player, d0, d1, d2);
			fireball.setPosition(player.posX + (d0 * 0.115), player.posY + 0.6D, player.posZ + (d2 * 0.115));
			player.worldObj.spawnEntityInWorld(fireball);
		}	
	}
	
	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}
}
