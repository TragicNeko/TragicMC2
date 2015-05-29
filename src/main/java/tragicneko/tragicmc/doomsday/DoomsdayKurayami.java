package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityKurayami;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayKurayami extends Doomsday {

	public DoomsdayKurayami(int id) {
		super(id, EnumDoomType.OVERFLOW);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double d0 = doom.getCurrentDoom() / (doom.getMaxDoom() - this.getScaledDoomRequirement(doom));
		TragicMC.logInfo("Level is " + d0);
		EntityKurayami kurayami = new EntityKurayami(player.worldObj);
		kurayami.setPosition(player.posX, player.posY, player.posZ);
		kurayami.setOwner(player);
		kurayami.setKurayamiLevel(d0);
		player.worldObj.spawnEntityInWorld(kurayami);
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}

}
