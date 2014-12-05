package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPurge extends Doomsday implements IExtendedDoomsday {

	public DoomsdayPurge(int id) {
		super(id, EnumDoomType.OVERFLOW);
		this.waitTime = 3;
		this.maxIterations = 200;
	}

	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Purge!"));
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(2.0D, 2.0D, 2.0D));
		EntityLivingBase entity;
		
		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
		
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				entity = (EntityLivingBase) list.get(i);
				entity.applyEntityCollision(player);
				
				entity.motionX *= 1.8;
				entity.motionZ *= 1.8;
				entity.motionY *= 1.8;
				
				float f = crucMoment ? 3.0F : 1.0F;
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), f);
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		
	}

}
