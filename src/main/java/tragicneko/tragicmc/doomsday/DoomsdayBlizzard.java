package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayBlizzard extends Doomsday implements IExtendedDoomsday {

	public DoomsdayBlizzard(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.COMBINATION);
		this.waitTime = 6;
		this.maxIterations = 100;
	}

	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "You have used Blizzard!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		for (int l = 0; l < 30; l++)
		{
			double d0 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posX) - player.posX; 
			double d1 = (MathHelper.getRandomIntegerInRange(rand, -2, 2) + player.posY) - player.posY;
			double d2 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posZ) - player.posZ; 

			EntityIcicle fireball = new EntityIcicle(player.worldObj, player, d0, d1, d2);
			fireball.setPosition(player.posX + (d0 * 0.115), player.posY + 0.6D, player.posZ + (d2 * 0.115));
			player.worldObj.spawnEntityInWorld(fireball);
		}	
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		
	}

}
