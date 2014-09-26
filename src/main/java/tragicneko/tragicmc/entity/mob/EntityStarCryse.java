package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityStarCryse extends EntityCryse {

	public EntityStarCryse(World par1World) {
		super(par1World);/*
		this.canCorrupt = false;
		this.isCorruptible = false;
		this.isChangeable = false; */
		this.superiorForm = null;
	}
	
	@Override
	public void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf((int) 0));
	}
	
	@SideOnly(Side.CLIENT)
	public int getTextureID()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}
	
	public void setTextureID(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(55.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.315);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("texture")) this.setTextureID(tag.getInteger("texture"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("texture", this.dataWatcher.getWatchableObjectInt(16));
	}
	
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		this.setTextureID(rand.nextInt(8));
		return super.onSpawnWithEgg(data);
		
	}
}
