package tragicneko.tragicmc.events;

import java.util.UUID;

import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.mob.TragicMob;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class DynamicHealthScaling {

	private static UUID normHealthDebuffUUID = UUID.fromString("967abeb0-255d-4ac9-bfa7-9636b25b8ca0");
	private static AttributeModifier normalHealthDebuff = new AttributeModifier(normHealthDebuffUUID, "normalHealthDebuff", -20.0, 0);
	
	private static UUID normHealthBuffUUID = UUID.fromString("3abc10da-144e-4f04-a5dd-f9d7e5feeafe");
	private static AttributeModifier normalHealthBuff = new AttributeModifier(normHealthBuffUUID, "normalHealthBuff", 20.0, 0);
	
	private static UUID bossHealthDebuffUUID = UUID.fromString("b1c91594-c901-41aa-bbd1-e8767a846a80");
	private static AttributeModifier bossHealthDebuff = new AttributeModifier(bossHealthDebuffUUID, "bossHealthDebuff", -50.0, 0);
	
	private static UUID bossHealthBuffUUID = UUID.fromString("909ba6e3-8763-4720-8fb4-c36db00da69b");
	private static AttributeModifier bossHealthBuff = new AttributeModifier(bossHealthBuffUUID, "bossHealthBuff", 50.0, 0);

	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.entity.worldObj.difficultySetting == EnumDifficulty.EASY)
		{
			if (event.entity instanceof TragicMob)
			{
				IAttributeInstance att = ((EntityLivingBase) event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth);

				if (att.getBaseValue() > 40.0)
				{
					att.removeModifier(normalHealthDebuff);
					att.applyModifier(normalHealthDebuff);
				}
			}
			
			if (event.entity instanceof TragicBoss)
			{
				event.entity.setDead();
			}
		}
		
		if (event.entity.worldObj.difficultySetting == EnumDifficulty.HARD)
		{
			if (event.entity instanceof TragicMob)
			{
				IAttributeInstance att = ((EntityLivingBase) event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth);

				if (att.getBaseValue() > 50.0)
				{
					att.removeModifier(normalHealthBuff);
					att.applyModifier(normalHealthBuff);
					((EntityLivingBase) event.entity).heal(((EntityLivingBase) event.entity).getMaxHealth());
				}
			}
			
			if (event.entity instanceof TragicBoss)
			{
				IAttributeInstance att = ((EntityLivingBase) event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth);

				if (att.getBaseValue() > 80.0)
				{
					att.removeModifier(bossHealthBuff);
					att.applyModifier(bossHealthBuff);
					((EntityLivingBase) event.entity).heal(((EntityLivingBase) event.entity).getMaxHealth());
				}
			}
		}
	}
}
