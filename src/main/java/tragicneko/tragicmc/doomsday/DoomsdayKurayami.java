package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayKurayami extends Doomsday {

	public DoomsdayKurayami(int id) {
		super(id, EnumDoomType.OVERFLOW);
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		//TODO add Kurayami mob so that I can actually implement the Kurayami Doomsday
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		
	}

}
