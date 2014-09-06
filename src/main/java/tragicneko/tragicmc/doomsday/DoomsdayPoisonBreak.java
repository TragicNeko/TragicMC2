package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPoisonBreak extends Doomsday {
	
	private MovingObjectPosition mop;
	
	public DoomsdayPoisonBreak(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom);
	}
	
	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		mop = this.getMOPFromPlayer(player);
		
		if (mop == null)
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
		if (mop == null) return;
		
		for (int i = 0; i < 4; i ++)
		{
			double d0 = player.posX - mop.blockX; 
			double d1 = player.posY - mop.blockY;
			double d2 = player.posZ - mop.blockZ;

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
