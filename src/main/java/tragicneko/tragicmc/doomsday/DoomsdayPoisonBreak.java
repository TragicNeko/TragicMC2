package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPoisonBreak extends Doomsday {

	public DoomsdayPoisonBreak(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		for (int i = 0; i < 4; i ++)
		{
			double d0 = player.posX - player.prevPosX; 
			double d1 = player.posY - player.prevPosY;
			double d2 = player.posZ - player.prevPosZ;

			EntityPoisonBarb fireball = new EntityPoisonBarb(player.worldObj, player, d0, d1, d2);
			fireball.setPosition(player.posX + (d0 * 0.1), player.posY + 1.0D, player.posZ + (d2 * 0.1));
			player.worldObj.spawnEntityInWorld(fireball);
		}

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Poison Break!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}

		if (!player.capabilities.isCreativeMode)
		{
			this.applyDoomAndCooldown(doom);
		}
	}

	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		for (int i = 0; i < 4; i ++)
		{
			double d0 = player.posX - player.prevPosX; 
			double d1 = player.posY - player.prevPosY;
			double d2 = player.posZ - player.prevPosZ;

			EntityPoisonBarb fireball = new EntityPoisonBarb(player.worldObj, player, d0, d1, d2);
			fireball.setPosition(player.posX + (d0 * 0.1), player.posY + 1.0D, player.posZ + (d2 * 0.1));
			player.worldObj.spawnEntityInWorld(fireball);
		}

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Poison Break!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player,
			boolean griefCheck) {

	}

}
