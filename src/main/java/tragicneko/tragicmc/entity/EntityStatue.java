package tragicneko.tragicmc.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityStatue extends Entity {

	public EntityStatue(World world) {
		super(world);
		this.preventEntitySpawning = true;
		this.setSize(0.425F, 0.865F);
		this.isImmuneToFire = true;
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
	public boolean attackEntityFrom(DamageSource source, float par2)
	{
		if (this.worldObj.isRemote || this.isEntityInvulnerable() || source.isExplosion()) return false;
		
		this.setDead();
		this.setBeenAttacked();
		
		if (!this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) return true;
		
		int id = this.getMobID();
		
		for (int i = 0; i < this.getTextureID(); i++)
		{
			id += 18;
		}		

		this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, id), 0.4F);
		return true;
	}
	
	public int getMobID()
	{
		return this.dataWatcher.getWatchableObjectInt(2);
	}
	
	public void setMobID(int i)
	{
		this.dataWatcher.updateObject(2, i);
	}
	
	public int getTextureID()
	{
		return this.dataWatcher.getWatchableObjectInt(3);
	}
	
	public void setTextureID(int i)
	{
		this.dataWatcher.updateObject(3, i);
	}

	public void incrementRotationAngle()
	{
		float pow = this.dataWatcher.getWatchableObjectFloat(4) + 45.0F;
		this.setRotation(pow);
	}
	
	public void setRotation(float f)
	{
		this.dataWatcher.updateObject(4, f % 360.0F);
	}
	
	public float getRotation()
	{
		return this.dataWatcher.getWatchableObjectFloat(4);
	}
	
	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		
		if (!this.worldObj.isRemote && this.getRotation() > 360.0F) this.setRotation(this.getRotation() - 360.0F);
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(2, Integer.valueOf((int) 0));
		this.dataWatcher.addObject(3, Integer.valueOf((int)0));
		this.dataWatcher.addObject(4, Float.valueOf((float) 0));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		if (tag.hasKey("mobID")) this.setMobID(tag.getInteger("mobID"));
		if (tag.hasKey("rotation")) this.setRotation(tag.getFloat("rotation"));
		if (tag.hasKey("textureID")) this.setTextureID(tag.getInteger("textureID"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		tag.setInteger("mobID", this.getMobID());
		tag.setFloat("rotation", this.getRotation());
		tag.setInteger("textureID",  this.getTextureID());
	}

}
