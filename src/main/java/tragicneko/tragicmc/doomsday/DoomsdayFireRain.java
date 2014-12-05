package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayFireRain extends Doomsday implements IExtendedDoomsday {

	public DoomsdayFireRain(int id) {
		super(id, EnumDoomType.WORLDSHAPER);
		this.waitTime = 10;
		this.maxIterations = 120;
	}

	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Fire Rain!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
		
		player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 500));
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double d1 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posX) - player.posX; 
		double d2 = (MathHelper.getRandomIntegerInRange(rand, 4, 10) + player.posY) - player.posY;
		double d3 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posZ) - player.posZ;
		
		for (int l = 0; l < 16; l++)
		{
			EntityLargeFireball fireball = new EntityLargeFireball(player.worldObj, player, -d1, -d2, -d3);
			fireball.setPosition(player.posX + rand.nextInt(16) - rand.nextInt(16), player.worldObj.getTopSolidOrLiquidBlock((int) player.posX, (int) player.posZ) + 24 + rand.nextInt(16),
			player.posZ + rand.nextInt(16) - rand.nextInt(16));
			player.worldObj.spawnEntityInWorld(fireball);
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
	}

}
