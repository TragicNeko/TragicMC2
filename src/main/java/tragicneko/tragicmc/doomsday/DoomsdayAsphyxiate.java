package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotions;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayAsphyxiate extends Doomsday implements IExtendedDoomsday {

	public DoomsdayAsphyxiate(int id) {
		super(id, EnumDoomType.CRISIS);
		this.waitTime = 3;
		this.maxIterations = 30;
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double d0 = 12.0;
		List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		if (list.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You have used Asphyxiate!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entities close enough..."));
		}
		
		float crisis = 1 / this.getCrisis(player);
		
		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);
				entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 2400, 10));
				if (TragicNewConfig.allowSubmission) entity.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 2400, (int) crisis));
				entity.motionX = entity.motionY = entity.motionZ = 0.0;
				entity.setPositionAndUpdate(entity.posX, entity.posY + 7.5, entity.posZ);

				if (rand.nextInt(8) == 0)
				{
					for (int l = 0; l < 3; l++)
					{
						double d1 = rand.nextDouble() + entity.posX - entity.posX; 
						double d2 = rand.nextDouble() * 6.0D + 2.0D + entity.posY - entity.posY;
						double d3 = rand.nextDouble() + entity.posZ - entity.posZ; 

						EntityDarkEnergy fireball = new EntityDarkEnergy(player.worldObj, player, -d1, -d2, -d3);
						fireball.setPosition(entity.posX + (-d1 * 0.115), entity.posY + 6.0 + (-d2 * 0.115), entity.posZ + (-d3 * 0.115));
						player.worldObj.spawnEntityInWorld(fireball);
					}
				}
			}
		}
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double d0 = 12.0;
		List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		float crisis = 1 / this.getCrisis(player);

		if (list.size() > 0)
		{
			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entities close enough..."));
		}

		for (int i = 0; i < list.size(); i ++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);
				entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 2400, 10));
				if (TragicNewConfig.allowSubmission) entity.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 2400, (int) crisis));
				entity.motionX = entity.motionY = entity.motionZ = 0.0;

				if (rand.nextInt(8) == 0)
				{
					for (int l = 0; l < 3; l++)
					{
						double d1 = rand.nextDouble() + entity.posX - entity.posX; 
						double d2 = rand.nextDouble() * 6.0D + 2.0D + entity.posY - entity.posY;
						double d3 = rand.nextDouble() + entity.posZ - entity.posZ; 

						EntityDarkEnergy fireball = new EntityDarkEnergy(player.worldObj, player, -d1, -d2, -d3);
						fireball.setPosition(entity.posX + (-d1 * 0.115), entity.posY + 6.0 + (-d2 * 0.115), entity.posZ + (-d3 * 0.115));
						player.worldObj.spawnEntityInWorld(fireball);
					}
				}
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		for (int l = 0; l < 3; l++)
		{
			double d1 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posX) - player.posX; 
			double d2 = (MathHelper.getRandomIntegerInRange(rand, 2, 8) + player.posY) - player.posY;
			double d3 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posZ) - player.posZ; 

			EntityDarkEnergy fireball = new EntityDarkEnergy(player.worldObj, player, -d1, -d2, -d3);
			fireball.setPosition(player.posX + (-d1 * 0.115), player.posY + (-d2 * 0.115), player.posZ + (-d3 * 0.115));
			player.worldObj.spawnEntityInWorld(fireball);
		}
	}

}
