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

	private int mobID = 0;
	private int rotationAngle = 0;
	private byte textureID = 0;

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
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
	{
		if (this.worldObj.isRemote || this.isEntityInvulnerable()) return false;
		this.setDead();
		this.setBeenAttacked();
		int id = this.mobID;
		
		for (int i = 0; i < this.textureID; i++)
		{
			id += 15;
		}

		this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, id), (0.25F * rand.nextFloat()) - (0.25F * rand.nextFloat()));
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (!this.worldObj.isRemote) this.dataWatcher.updateObject(2, this.mobID);
		if (!this.worldObj.isRemote) this.dataWatcher.updateObject(3, this.textureID);
		if (!this.worldObj.isRemote) this.dataWatcher.updateObject(4, this.rotationAngle); 
	}

	@SideOnly(Side.CLIENT)
	public int getIDForRender()
	{
		return this.dataWatcher.getWatchableObjectInt(2);
	}

	@SideOnly(Side.CLIENT)
	public int getIDForTexture()
	{
		return this.dataWatcher.getWatchableObjectByte(3);
	}

	@SideOnly(Side.CLIENT)
	public int getRotationAngleForRender()
	{
		return this.dataWatcher.getWatchableObjectInt(4);
	}

	public int getMobID()
	{
		return this.mobID;
	}

	public byte getTextureID()
	{
		return this.textureID;
	}

	public void setMobID(int i)
	{
		this.mobID = i;
	}

	public void setTextureID(byte b0)
	{
		this.textureID = b0;
	}

	public void incrementRotationAngle()
	{
		this.rotationAngle += 45;
		if (this.rotationAngle >= 360) this.rotationAngle = 0;
	}
	
	public void setRotation(int i)
	{
		this.rotationAngle = i % 360;
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(2, Integer.valueOf((int) 0));
		this.dataWatcher.addObject(3, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(4, Integer.valueOf((int) 0));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		if (tag.hasKey("mobID")) this.mobID = tag.getInteger("mobID");
		if (tag.hasKey("rotation")) this.rotationAngle = tag.getInteger("rotation");
		if (tag.hasKey("texture")) this.textureID = tag.getByte("texture");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		tag.setInteger("mobID", this.mobID);
		tag.setInteger("rotation", this.rotationAngle);
		tag.setByte("texture",  this.textureID);
	}

}
