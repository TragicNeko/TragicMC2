package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayBlizzard extends Doomsday implements IExtendedDoomsday {

	public DoomsdayBlizzard(int id) {
		super(id, EnumDoomType.COMBINATION);
		this.waitTime = 6;
		this.maxIterations = 60;
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		for (int l = 0; l < 30; l++)
		{
			double d1 = rand.nextDouble() - rand.nextDouble(); 
			double d2 = rand.nextDouble()+ rand.nextDouble();
			double d3 = rand.nextDouble() - rand.nextDouble();

			EntityIcicle fireball = new EntityIcicle(player.worldObj, player, d1, d2, d3);
			fireball.setPosition(player.posX + (d1 * 0.115), player.posY + 0.6D, player.posZ + (d3 * 0.115));
			player.worldObj.spawnEntityInWorld(fireball);
		}	
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		
	}
}
