package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayFirestorm extends Doomsday implements IExtendedDoomsday {

	public DoomsdayFirestorm(int id) {
		super(id, EnumDoomType.COMBINATION);
		this.waitTime = 10;
		this.maxIterations = 120;
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Dragon's Roar!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
		
		if (TragicNewConfig.allowFlight)
		{
			player.addPotionEffect(new PotionEffect(TragicPotion.Flight.id, 6000, 0));
		}
		else
		{
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 600, 3));
		}

		if (crucMoment)
		{
			List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(12.0D, 12.0D, 12.0D));
			EntityLivingBase entity;

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityLivingBase)
				{
					entity = (EntityLivingBase) list.get(i);
					if (TragicNewConfig.allowStun && crucMoment)
					{
						entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 120, 1));
					}
					
					entity.addPotionEffect(new PotionEffect(Potion.weakness.id, 300, 15));
				}
			}
		}
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
		player.worldObj.setRainStrength(1.0F);
		player.worldObj.setThunderStrength(1.0F);
		
		double d1 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posX) - player.posX; 
		double d2 = (MathHelper.getRandomIntegerInRange(rand, 4, 10) + player.posY) - player.posY;
		double d3 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posZ) - player.posZ;
		
		for (int l = 0; l < 12; l++)
		{
			EntityLargeFireball fireball = new EntityLargeFireball(player.worldObj, player, -d1, -d2, -d3);
			fireball.setPosition(player.posX + rand.nextInt(16) - rand.nextInt(16), player.worldObj.getTopSolidOrLiquidBlock((int) player.posX, (int) player.posZ) + 24 + rand.nextInt(16),
			player.posZ + rand.nextInt(16) - rand.nextInt(16));
			player.worldObj.spawnEntityInWorld(fireball);
		}
		
		for (int l = 0; l < 24; l++)
		{
			EntityLargeFireball fireball = new EntityLargeFireball(player.worldObj, player, -d1, -d2, -d3);
			fireball.setPosition(player.posX + rand.nextInt(8) - rand.nextInt(8), player.worldObj.getTopSolidOrLiquidBlock((int) player.posX, (int) player.posZ) + 24 + rand.nextInt(16),
			player.posZ + rand.nextInt(8) - rand.nextInt(8));
			player.worldObj.spawnEntityInWorld(fireball);
		}
		
		if (rand.nextInt(4) == 0)
		{
			double d4 = d1 + rand.nextInt(32) - rand.nextInt(32);
			double d5 = d3 + rand.nextInt(32) - rand.nextInt(32);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, d4, player.worldObj.getTopSolidOrLiquidBlock((int) d4, (int) d5), d5));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		
	}

}
