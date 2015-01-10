package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayTitanfall extends Doomsday implements IExtendedDoomsday {

	public DoomsdayTitanfall(int id) {
		super(id, EnumDoomType.OVERFLOW);
		this.waitTime = 10;
		this.maxIterations = 100;
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Titanfall!"));

		if (TragicNewConfig.allowInvulnerability) player.addPotionEffect(new PotionEffect(TragicPotion.Invulnerability.id, 400, 0));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 5.0f * rand.nextFloat(), false);
		}
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		double x = player.posX;
		double y = player.posY;
		double z = player.posZ;

		if (effect.utilityInt > 6) effect.utilityInt = 0;
		effect.utilityInt++;

		float f = crucMoment ? 5.0F : 2.0F;
		double base = 4.0D;

		if (rand.nextBoolean())
		{
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x + base + effect.utilityInt, y, z));
			player.worldObj.createExplosion(player, x + base + effect.utilityInt, y, z, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x, y, z + base + effect.utilityInt));
			player.worldObj.createExplosion(player, x, y, z + base + effect.utilityInt, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x - base - effect.utilityInt, y, z));
			player.worldObj.createExplosion(player, x - base - effect.utilityInt, y, z, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x, y, z - base - effect.utilityInt));
			player.worldObj.createExplosion(player, x, y, z - base - effect.utilityInt, f * rand.nextFloat(), false);
		}
		else
		{
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x + base + effect.utilityInt, y, z + base + effect.utilityInt));
			player.worldObj.createExplosion(player, x + base + effect.utilityInt, y, z + base + effect.utilityInt, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x - base - effect.utilityInt, y, z + base + effect.utilityInt));
			player.worldObj.createExplosion(player, x - base - effect.utilityInt, y, z + base + effect.utilityInt, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x + base + effect.utilityInt, y, z - base - effect.utilityInt));
			player.worldObj.createExplosion(player, x + base + effect.utilityInt, y, z - base - effect.utilityInt, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x - base - effect.utilityInt, y, z - base - effect.utilityInt));
			player.worldObj.createExplosion(player, x - base - effect.utilityInt, y, z - base - effect.utilityInt, f * rand.nextFloat(), false);
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		for (int i = 0; i < 4; i++)
		{
			EntityCreeper creeper = new EntityCreeper(player.worldObj);
			creeper.setPosition(player.posX + rand.nextDouble() - rand.nextDouble(), player.posY, player.posZ + rand.nextDouble() - rand.nextDouble());
			player.worldObj.spawnEntityInWorld(creeper);
			creeper.getDataWatcher().updateObject(17, 1);
		}
	}

}
