package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayMarionette extends Doomsday implements IExtendedDoomsday {

	public DoomsdayMarionette(int id) {
		super(id, EnumDoomType.OVERFLOW);
		this.waitTime = 3;
		this.maxIterations = 100;
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(4.0D, 4.0D, 4.0D));

		if (list.isEmpty())
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entity close enough..."));
		}
		else
		{
			EntityLivingBase entity = null;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityLivingBase)
				{
					if (entity != null)
					{
						if (player.getDistanceToEntity(entity) > player.getDistanceToEntity(list.get(i))) entity = (EntityLivingBase) list.get(i);
					}
					if (entity == null) entity = (EntityLivingBase) list.get(i);
				}
			}
			
			effect.utilityEntity = entity;

			if (effect.utilityEntity != null && effect.utilityEntity instanceof EntityLivingBase)
			{
				if (TragicNewConfig.allowSubmission) ((EntityLivingBase) effect.utilityEntity).addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 200, 10));

				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Marionette!"));

				if (crucMoment)
				{
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
				}
			}
			else
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No living entities close enough..."));
			}
		}
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		if (effect.utilityEntity != null && !effect.utilityEntity.isDead && effect.utilityEntity instanceof EntityLivingBase)
		{
			Vec3 vec = WorldHelper.getVecFromEntity(player, 5.0D);

			if (vec != null)
			{
				((EntityLivingBase) effect.utilityEntity).setPositionAndUpdate(vec.xCoord, vec.yCoord, vec.zCoord);
				if (rand.nextInt(8) == 0) effect.utilityEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 1.0F);
			}
		}
		else
		{
			List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(4.0D, 4.0D, 4.0D));
			EntityLivingBase entity = null;
			
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityLivingBase)
				{
					if (entity != null)
					{
						if (player.getDistanceToEntity(entity) > player.getDistanceToEntity(list.get(i))) entity = (EntityLivingBase) list.get(i);
					}
					if (entity == null) entity = (EntityLivingBase) list.get(i);
				}
			}
			
			effect.utilityEntity = entity;
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}

}
