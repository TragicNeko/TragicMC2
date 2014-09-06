package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayRavage extends Doomsday {
	
	private List<Entity> list = new ArrayList();

	public DoomsdayRavage(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.WORLDSHAPER);
	}
	
	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
		double d0 = crucMoment ? 24.0D : 12.0D;
		list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
		
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Ravage!"));
		
		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}
	
	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{
		float f = crucMoment ? 5.0F : 2.5F;
		int meow = crucMoment ? 4 : 0;

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);
				player.worldObj.createExplosion(player, entity.posX, entity.posY, entity.posZ, rand.nextFloat() * f, this.getMobGriefing(player));
			}
		}

		for (int x = 0; x < rand.nextInt(8) + 4 + meow; x++)
		{
			player.worldObj.createExplosion(player, player.posX + (rand.nextInt(9) - 4), player.posY + (rand.nextInt(3) - 1), player.posZ + (rand.nextInt(9) - 4), rand.nextFloat() * f, this.getMobGriefing(player));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.worldObj.createExplosion(null, player.posX, player.posY, player.posZ, rand.nextFloat() * 1.5F, this.getMobGriefing(player));
	}

}
