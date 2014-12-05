package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayFlightOfTheValkyries extends Doomsday implements IExtendedDoomsday {

	private List<Entity> list = new ArrayList();

	public DoomsdayFlightOfTheValkyries(int id) {
		super(id, EnumDoomType.COMBINATION);
		this.waitTime = 5;
		this.maxIterations = 200;
	}

	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(32.0D, 32.0D, 32.0D));

		if (list.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "You have used Flight of the Valkyries!"));	
			if (TragicNewConfig.allowInvulnerability) player.addPotionEffect(new PotionEffect(TragicPotions.Invulnerability.id, 200, 0));
			doom.fillDoom();
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entities close enough..."));
		}
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		EntityLivingBase entity;
		float damage = crucMoment ? 8.0F : 4.0F;
		float amt = crucMoment ? 1.5F : 0.5F;
		
		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
		
		list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(32.0D, 32.0D, 32.0D));
		
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				entity = (EntityLivingBase) list.get(i);
				
				entity.motionX = rand.nextDouble() - rand.nextDouble();
				entity.motionY = 0.75 - rand.nextDouble();
				entity.motionZ = rand.nextDouble() - rand.nextDouble();
				
				if (rand.nextBoolean())
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 1.0F + (rand.nextFloat() * damage));
					
					if (rand.nextInt(16) == 0)
					{
						player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, entity.posX, entity.posY, entity.posZ));
						player.heal(amt);
					}
				}
			}
		}
	}


	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}
}
