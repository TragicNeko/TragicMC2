package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import tragicneko.tragicmc.properties.PropertyDoom;

public interface IThreadedDoomsday {
	/**
	 * This should only be utilized by Multi-stage or time sensitive Doomsdays, otherwise it does nothing
	 * @param doom
	 * @param player
	 * @param crucMoment
	 * @param griefCheck
	 */
	public abstract void useDoomsdayFromThread(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck);
	
	/**
	 * Used by multi-stage and time-sensitive Doomsdays, for use through the /Doomsday command
	 * @param doom
	 * @param player
	 * @param crucMoment
	 * @param griefCheck
	 */
	public abstract void useDoomsdayFromThreadThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck);

}
