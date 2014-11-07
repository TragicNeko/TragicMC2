package tragicneko.tragicmc.entity.boss;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class EntityAegarCrystal extends EntityPart {
	
	private float health;

	public EntityAegarCrystal(IMultiPart main, String name, float width, float height) {
		super(main, name, width, height);
		this.health = 25.0F;
	}
	
	public float getHealth()
	{
		return this.health;
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(4, Float.valueOf(0));
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		this.health = tag.getFloat("crystalHealth");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setFloat("crystalHealth", this.health);
		
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		if (!this.worldObj.isRemote && this.health > 0) this.health -= damage / 4.0F + 1.0F;
		return super.attackEntityFrom(source, damage);
	}
}
