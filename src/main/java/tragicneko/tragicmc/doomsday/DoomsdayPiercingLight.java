package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPiercingLight extends Doomsday {

	public DoomsdayPiercingLight(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.CRISIS);
	}
	
	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		int posX = (int) player.posX;
		int posY = (int) player.posY;
		int posZ = (int) player.posZ;
		
		float crisis = this.getCrisis(player);
		
		if (player.worldObj.getLightBrightness(posX, posY, posZ) <= 0.75F)
		{
			Double d0 = 16.0;

			if (crisis > 0.25F)
			{
				d0 = 12.0;
			}

			if (crisis > 0.5F)
			{
				d0 = 8.0;
			}

			if (crisis > 0.75F)
			{
				d0 = 4.0;
			}

			List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
			for (int x = 0; x < list.size(); x++)
			{
				if (list.get(x) instanceof EntityLivingBase)
				{
					EntityLivingBase entity = (EntityLivingBase) list.get(x);

					Float f = entity.worldObj.getLightBrightness((int)entity.posX, (int)entity.posY, (int)entity.posZ);

					if (f < 0.6F)
					{
						if (f <= 0.0F)
						{
							f = 0.1F;
						}

						entity.setFire(10 + rand.nextInt(16));

						if (crucMoment)
						{
							entity.attackEntityFrom(DamageSource.magic, (40 / MathHelper.ceiling_float_int((f * 10)) + crisis));
						}
						else
						{
							entity.attackEntityFrom(DamageSource.magic, (20 / MathHelper.ceiling_float_int((f * 10)) + crisis));
						}
					}
				}
			}

			if (list.size() > 0)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You have used Piercing Light!"));

				if (crucMoment)
				{
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
				}

				if (!player.capabilities.isCreativeMode)
				{
					this.applyDoomAndCooldown(doom);
				}
			}
			else
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entities in range..."));
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "It's not dark enough to use that..."));
		}
	}
	
	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		int posX = (int) player.posX;
		int posY = (int) player.posY;
		int posZ = (int) player.posZ;
		
		float crisis = this.getCrisis(player);
		
		if (player.worldObj.getLightBrightness(posX, posY, posZ) <= 0.75F)
		{
			Double d0 = 16.0;

			if (crisis > 0.25F)
			{
				d0 = 12.0;
			}

			if (crisis > 0.5F)
			{
				d0 = 8.0;
			}

			if (crisis > 0.75F)
			{
				d0 = 4.0;
			}

			List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
			for (int x = 0; x < list.size(); x++)
			{
				if (list.get(x) instanceof EntityLivingBase)
				{
					EntityLivingBase entity = (EntityLivingBase) list.get(x);

					Float f = entity.worldObj.getLightBrightness((int)entity.posX, (int)entity.posY, (int)entity.posZ);

					if (f < 0.6F)
					{
						if (f <= 0.0F)
						{
							f = 0.1F;
						}

						entity.setFire(10 + rand.nextInt(16));

						if (crucMoment)
						{
							entity.attackEntityFrom(DamageSource.magic, (40 / MathHelper.ceiling_float_int((f * 10)) + crisis));
						}
						else
						{
							entity.attackEntityFrom(DamageSource.magic, (20 / MathHelper.ceiling_float_int((f * 10)) + crisis));
						}
					}
				}
			}

			if (list.size() > 0)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You have used Piercing Light!"));

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
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "It's not dark enough to use that..."));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player,
			boolean griefCheck) {
		
	}

}
