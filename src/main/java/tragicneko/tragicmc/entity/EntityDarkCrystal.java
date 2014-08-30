package tragicneko.tragicmc.entity;

import tragicneko.tragicmc.main.TragicItems;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDarkCrystal extends Entity {

	public int rotationTicks;

	public EntityDarkCrystal(World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(0.445F, 0.665F);
		this.preventEntitySpawning = true;
		this.yOffset = this.height / 2.0F;
		this.isImmuneToFire = true;
		this.rotationTicks = rand.nextInt(10000);
	}
	
	protected boolean canTriggerWalking()
    {
        return false;
    }

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		this.rotationTicks++;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float par2)
	{
		if (this.worldObj.isRemote || this.isEntityInvulnerable()) return false;
		if (source.getEntity() != null)
		{
			this.setDead();
			this.setBeenAttacked();
			
			if (!this.worldObj.isRemote)
            {
                this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, 4.0F, true);
            }
			return true;
		}
		return false;
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

}
