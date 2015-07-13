package tragicneko.tragicmc.properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import tragicneko.tragicmc.TragicPotion;

public class PropertyMisc implements IExtendedEntityProperties {

	public static final String propertyName = "TragicMC.Misc";
	private final EntityLivingBase theEntity;

	/**
	 * For the Bleed potion effect
	 */
	public int bleedOutTime;

	/**
	 * For recovering from being Corrupted
	 */
	public int recoveryTime;

	public PropertyMisc(EntityLivingBase ent)
	{
		this.theEntity = ent;
	}

	public static final void register(EntityLivingBase ent)
	{
		ent.registerExtendedProperties(PropertyMisc.propertyName, new PropertyMisc(ent));
	}

	public static final PropertyMisc get(EntityLivingBase ent)
	{
		return (PropertyMisc) ent.getExtendedProperties(PropertyMisc.propertyName);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("bleedOut", this.bleedOutTime);
		tag.setInteger("recoveryTime", this.recoveryTime);
		compound.setTag(PropertyMisc.propertyName, tag);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound tag = (NBTTagCompound)compound.getTag(PropertyMisc.propertyName);

		if (tag != null)
		{
			this.bleedOutTime = tag.getInteger("bleedOut");
			this.recoveryTime = tag.getInteger("recoveryTime");
		}
	}

	@Override
	public void init(Entity entity, World world) {
		if (entity instanceof EntityLivingBase)
		{
			PropertyMisc misc =	PropertyMisc.get((EntityLivingBase) entity);
			if (misc != null) misc.loadNBTData(new NBTTagCompound());
		}
	}

	public void onUpdate()
	{
		/*
		if (TragicPotion.Bleed != null && this.theEntity.isPotionActive(TragicPotion.Bleed))
		{
			if (this.bleedOutTime < 1200) this.bleedOutTime++; 
		}
		else this.bleedOutTime = 0; */
	}
}
