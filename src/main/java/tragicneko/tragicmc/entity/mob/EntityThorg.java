package tragicneko.tragicmc.entity.mob;

import net.minecraft.world.World;

public class EntityThorg extends TragicMob {

	public EntityThorg(World par1World) {
		super(par1World);
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

}
