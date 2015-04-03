package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayFear extends Doomsday {

	public DoomsdayFear(int id) {
		super(id);
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
		double d0 = crucMoment ? 32.0D : 6.0D;
		effect.utilityList = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		if (effect.utilityList.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Afraid of the Dark!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entity close enough..."));
		}
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) 
	{		
		for (int i = 0; i < effect.utilityList.size(); i ++)
		{
			if (effect.utilityList.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) effect.utilityList.get(i);
				if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
				
				entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 300));
				if (TragicConfig.allowFear) entity.addPotionEffect(new PotionEffect(TragicPotion.Fear.id, 300, crucMoment ? 1 + rand.nextInt(3) : 0));
				if (crucMoment && TragicConfig.allowSubmission) entity.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 300, 5));

				if (crucMoment)
				{
					int x = MathHelper.floor_double(entity.posX);
					int y = MathHelper.floor_double(entity.posY);
					int z = MathHelper.floor_double(entity.posZ);

					entity.setPositionAndUpdate(x + rand.nextInt(7) - 3, y + 5 + rand.nextInt(5), z + rand.nextInt(7) - 3);
				}
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.blindness.id, 120));
		if (TragicConfig.allowFear) player.addPotionEffect(new PotionEffect(TragicPotion.Fear.id, 120, 1));
	}

}
