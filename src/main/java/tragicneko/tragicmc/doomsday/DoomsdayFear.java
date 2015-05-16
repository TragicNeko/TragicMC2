package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayFear extends Doomsday {

	public DoomsdayFear(int id) {
		super(id);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) 
	{		
		double d0 = crucMoment ? 32.0D : 6.0D;
		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

		if (list.size() > 0)
		{
			for (int i = 0; i < list.size(); i ++)
			{
				if (list.get(i) instanceof EntityLivingBase)
				{
					EntityLivingBase entity = (EntityLivingBase) list.get(i);
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
		else
		{
			addNoEntityMessage(player);
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.blindness.id, 120));
		if (TragicConfig.allowFear) player.addPotionEffect(new PotionEffect(TragicPotion.Fear.id, 120, 1));
	}

}
