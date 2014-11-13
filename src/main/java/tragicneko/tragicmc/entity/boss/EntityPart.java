package tragicneko.tragicmc.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPart extends Entity {
	
	public final IMultiPart main;
	public final String partName;

	public EntityPart(IMultiPart main, String name, float width, float height) {
		super(main.getWorld());
		this.setSize(width, height);
		this.main = main;
		this.partName = name;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		
	}
	
	public boolean canBeCollidedWith()
    {
        return true;
    }

    public boolean attackEntityFrom(DamageSource source, float damage)
    {
        return this.isEntityInvulnerable() ? false : this.main.attackEntityFromPart(this, source, damage);
    }

    /**
     * Returns true if Entity argument is equal to this Entity
     */
    public boolean isEntityEqual(Entity p_70028_1_)
    {
        return this == p_70028_1_ || this.main == p_70028_1_;
    }

    @Override
	public void onStruckByLightning(EntityLightningBolt bolt) {} //prevents the Enyvil from hurting itself
}
