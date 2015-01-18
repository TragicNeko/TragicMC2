package tragicneko.tragicmc.entity.boss;

import tragicneko.tragicmc.TragicEntities;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.world.World;

public class EntityOverlordCocoon extends TragicBoss {

	public EntityOverlordCocoon(World par1World) {
		super(par1World);
	}
	
	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Synapse;
	}

}
