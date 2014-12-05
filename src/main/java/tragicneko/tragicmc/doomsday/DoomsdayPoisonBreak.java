package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPoisonBreak extends Doomsday {
	
	public DoomsdayPoisonBreak(int id) {
		super(id);
	}
	
	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		Vec3 vec = TragicWeapon.getVecFromPlayer(player);
		
		if (vec == null)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Doomsday needs to be aimed..."));
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Poison Break!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
		}
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{
		Vec3 vec = TragicWeapon.getVecFromPlayer(player);
		if (vec == null) return;
		
		for (int i = 0; i < 4; i ++)
		{
			double d0 = player.posX - vec.xCoord; 
			double d1 = player.posY - vec.yCoord + 0.65;
			double d2 = player.posZ - vec.zCoord;

			EntityPoisonBarb fireball = new EntityPoisonBarb(player.worldObj, player, d0, d1, d2);
			fireball.setPosition(player.posX + (d0 * 0.115), player.posY + 0.6D, player.posZ + (d2 * 0.115));
			player.worldObj.spawnEntityInWorld(fireball);
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == TragicItems.MercuryDagger)
		{
			player.destroyCurrentEquippedItem();
			player.playSound("random.break", rand.nextFloat(), rand.nextFloat());
		}
	}

}
