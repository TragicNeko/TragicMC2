package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayTitanfall extends Doomsday implements IExtendedDoomsday {

	private int tick = 0;

	public DoomsdayTitanfall(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.OVERFLOW);
		this.waitTime = 10;
		this.maxIterations = 100;
	}

	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Titanfall!"));

		if (TragicNewConfig.allowInvulnerability) player.addPotionEffect(new PotionEffect(TragicPotions.Invulnerability.id, 400, 0));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 5.0f * rand.nextFloat(), false);
		}

		if (tick > 0) tick = 0;
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		double x = player.posX;
		double y = player.posY;
		double z = player.posZ;

		if (tick > 6) tick = 0;
		tick++;

		float f = crucMoment ? 5.0F : 2.0F;
		double base = 4.0D;

		if (rand.nextBoolean())
		{
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x + base + tick, y, z));
			player.worldObj.createExplosion(player, x + base + tick, y, z, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x, y, z + base + tick));
			player.worldObj.createExplosion(player, x, y, z + base + tick, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x - base - tick, y, z));
			player.worldObj.createExplosion(player, x - base - tick, y, z, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x, y, z - base - tick));
			player.worldObj.createExplosion(player, x, y, z - base - tick, f * rand.nextFloat(), false);
		}
		else
		{
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x + base + tick, y, z + base + tick));
			player.worldObj.createExplosion(player, x + base + tick, y, z + base + tick, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x - base - tick, y, z + base + tick));
			player.worldObj.createExplosion(player, x - base - tick, y, z + base + tick, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x + base + tick, y, z - base - tick));
			player.worldObj.createExplosion(player, x + base + tick, y, z - base - tick, f * rand.nextFloat(), false);
			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, x - base - tick, y, z - base - tick));
			player.worldObj.createExplosion(player, x - base - tick, y, z - base - tick, f * rand.nextFloat(), false);
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
