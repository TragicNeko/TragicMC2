package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayRavage extends Doomsday {

	public DoomsdayRavage(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.WORLDSHAPER);
	}
	
	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		double d0 = 12.0;
		float f = 2.5F;
		int meow = 0;

		if (crucMoment)
		{
			d0 = 24.0;
			f = 5.0F;
			meow = 4;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);
				player.worldObj.createExplosion(player, entity.posX, entity.posY, entity.posZ, rand.nextFloat() * f, griefCheck);
			}
		}

		for (int x = 0; x < rand.nextInt(8) + 4 + meow; x++)
		{
			player.worldObj.createExplosion(player, player.posX + (rand.nextInt(9) - 4), player.posY + (rand.nextInt(3) - 1), player.posZ + (rand.nextInt(9) - 4), rand.nextFloat() * f, griefCheck);
		}
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Ravage!"));

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
		double d0 = 12.0;
		float f = 2.5F;
		int meow = 0;

		if (crucMoment)
		{
			d0 = 24.0;
			f = 5.0F;
			meow = 4;
		}

		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);
				player.worldObj.createExplosion(player, entity.posX, entity.posY, entity.posZ, rand.nextFloat() * f, griefCheck);
			}
		}

		for (int x = 0; x < rand.nextInt(8) + 4 + meow; x++)
		{
			player.worldObj.createExplosion(player, player.posX + (rand.nextInt(9) - 4), player.posY + (rand.nextInt(3) - 1), player.posZ + (rand.nextInt(9) - 4), rand.nextFloat() * f, griefCheck);
		}
		
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Ravage!"));

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
