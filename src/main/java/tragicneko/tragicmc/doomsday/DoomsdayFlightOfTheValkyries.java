package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayFlightOfTheValkyries extends Doomsday implements IThreadedDoomsday {

	public DoomsdayFlightOfTheValkyries(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.ULTIMATE);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) {

		DoomThread thread = new DoomThread(this, doom, crucMoment, griefCheck);

		if (!thread.isAlive() && !thread.isInterrupted())
		{
			thread.start();
		}

		if (!player.capabilities.isCreativeMode) this.applyDoomAndCooldown(doom);
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_BLUE + "You have used Flight of the Valkyries!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			if (TragicNewConfig.allowInvulnerability) player.addPotionEffect(new PotionEffect(TragicPotions.Invulnerability.id, 100, 0));
		}
	}

	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) {
		DoomThread thread = new DoomThread(this, doom, crucMoment, griefCheck, true);

		if (!thread.isAlive() && !thread.isInterrupted())
		{
			thread.start();
		}

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_BLUE + "You have used Flight of the Valkyries!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			if (TragicNewConfig.allowInvulnerability) player.addPotionEffect(new PotionEffect(TragicPotions.Invulnerability.id, 100, 0));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player, boolean griefCheck) {

	}

	@Override
	public void useDoomsdayFromThread(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) {
		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(32.0D, 32.0D, 32.0D));
		EntityLivingBase entity;
		
		if (list.isEmpty()) return;

		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				entity = (EntityLivingBase) list.get(i);
				
				if (entity.isDead) continue;
				
				double d0 = entity.posX - player.posX;
				double d1 = entity.posY - player.posY;
				double d2 = entity.posZ - player.posZ;
				Vec3 v = Vec3.createVectorHelper(d0, d1, d2);
				double d3 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2); //radius
				double d4 = 6.0F / d3;
				double dx = v.xCoord * Math.cos(d4) + v.xCoord * Math.sin(d4);
				double dz = v.zCoord * Math.cos(d4) - v.xCoord * Math.sin(d4);
				Vec3 v2 = Vec3.createVectorHelper(dx, d1, dz).normalize();
				entity.motionX = v2.zCoord * rand.nextDouble() * MathHelper.getRandomDoubleInRange(rand, -1.0, 1.0);
				entity.motionY = 0.65 - rand.nextFloat();
				entity.motionZ = v2.xCoord * rand.nextDouble() * MathHelper.getRandomDoubleInRange(rand, -1.0, 1.0);

				if (rand.nextInt(48) == 0 || crucMoment && rand.nextInt(16) == 0)
				{
					if (rand.nextInt(4) == 0) player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, entity.posX, entity.posY, entity.posZ));
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 1.0F + (rand.nextFloat() * 4.0F));
					if (player.getHealth() <= player.getMaxHealth()|| crucMoment) player.heal(1.0F);
				}
			}
		}
		
		if (rand.nextInt(16) == 0) player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, player.posX - rand.nextInt(32) + rand.nextInt(32), player.posY, player.posZ - rand.nextInt(32) + rand.nextInt(32)));
	}

	@Override
	public void useDoomsdayFromThreadThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) {
		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(32.0D, 32.0D, 32.0D));
		EntityLivingBase entity;
		
		if (list.isEmpty()) return;

		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof EntityMob)
			{
				entity = (EntityLivingBase) list.get(i);
				
				if (entity.isDead) continue;
				
				double d0 = entity.posX - player.posX;
				double d1 = entity.posY - player.posY;
				double d2 = entity.posZ - player.posZ;
				Vec3 v = Vec3.createVectorHelper(d0, d1, d2);
				double d3 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2); //radius
				double d4 = 6.0F / d3;
				double dx = v.xCoord * Math.cos(d4) + v.xCoord * Math.sin(d4);
				double dz = v.zCoord * Math.cos(d4) - v.xCoord * Math.sin(d4);
				Vec3 v2 = Vec3.createVectorHelper(dx, d1, dz).normalize();
				entity.motionX = v2.zCoord * rand.nextDouble() * MathHelper.getRandomDoubleInRange(rand, -1.0, 1.0);
				entity.motionY = 0.65 - rand.nextFloat();
				entity.motionZ = v2.xCoord * rand.nextDouble() * MathHelper.getRandomDoubleInRange(rand, -1.0, 1.0);

				if (rand.nextInt(48) == 0 || crucMoment && rand.nextInt(16) == 0)
				{
					if (rand.nextInt(4) == 0) player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, entity.posX, entity.posY, entity.posZ));
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 1.0F + (rand.nextFloat() * 4.0F));
					if (player.getHealth() <= player.getMaxHealth() || crucMoment) player.heal(1.0F);
				}
			}
		}
	}

}
