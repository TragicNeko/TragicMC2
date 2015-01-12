package tragicneko.tragicmc.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.miniboss.EntityAegar;
import tragicneko.tragicmc.entity.miniboss.EntityGreaterStin;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.miniboss.EntityMegaCryse;
import tragicneko.tragicmc.entity.miniboss.EntityStinKing;
import tragicneko.tragicmc.entity.miniboss.EntityStinQueen;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;

public class EntityStatue extends Entity {

	public EntityStatue(World world) {
		super(world);
		this.preventEntitySpawning = true;
		this.setSize(0.425F, 0.865F);
		this.isImmuneToFire = true;
	}
	
	@Override
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
		this.dataWatcher.addObject(2, Integer.valueOf(0));
		this.dataWatcher.addObject(3, Integer.valueOf(0));
		this.dataWatcher.addObject(4, Float.valueOf(0));
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
	
	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		if (player.worldObj.isRemote) return false;
		
		if (player.getCurrentEquippedItem() != null)
		{
			ItemStack item = player.getCurrentEquippedItem();

			if (item.getItem() == TragicItems.LivingClay)
			{
				int id = this.getMobID();
				EntityLivingBase entity = null;

				switch(id)
				{
				case 0:
					if (TragicNewConfig.allowApis) entity = new EntityApis(this.worldObj);
					break;
				case 1:
					if (TragicNewConfig.allowKitsune) entity = new EntityKitsune(this.worldObj);
					break;
				case 2:
					if (TragicNewConfig.allowDeathReaper) entity = new EntityDeathReaper(this.worldObj);
					break;
				case 3:
					if (TragicNewConfig.allowTimeController) entity = new EntityTimeController(this.worldObj);
					break;
				case 4:
					if (TragicNewConfig.allowYeti) entity = new EntityYeti(this.worldObj);
					break;
				case 5:
					if (TragicNewConfig.allowPolaris) entity = new EntityPolaris(this.worldObj);
					break;
				case 6:
					if (TragicNewConfig.allowJarra) entity = new EntityJarra(this.worldObj);
					break;
				case 7:
					if (TragicNewConfig.allowKragul) entity = new EntityKragul(this.worldObj);
					break;
				case 8:
					if (TragicNewConfig.allowMagmox) entity = new EntityMagmox(this.worldObj);
					break;
				case 9:
					if (TragicNewConfig.allowMegaCryse) entity = new EntityMegaCryse(this.worldObj);
					break;
				case 10:
					if (TragicNewConfig.allowStinKing) entity = new EntityStinKing(this.worldObj);
					break;
				case 11:
					if (TragicNewConfig.allowStinQueen) entity = new EntityStinQueen(this.worldObj);
					break;
				case 12:
					if (TragicNewConfig.allowGreaterStin) entity = new EntityGreaterStin(this.worldObj);
					break;
				case 13:
					if (TragicNewConfig.allowVoxStellarum) entity = new EntityVoxStellarum(this.worldObj);
					break;
				case 14:
					if (TragicNewConfig.allowEnyvil) entity = new EntityEnyvil(this.worldObj);
					break;
				case 15:
					if (TragicNewConfig.allowClaymation) entity = new EntityClaymation(this.worldObj);
					break;
				case 16:
					if (TragicNewConfig.allowAegar) entity = new EntityAegar(this.worldObj);
					break;
				case 17: break;
				}

				if (entity != null)
				{
					entity.copyLocationAndAnglesFrom(this);
					((EntityLiving) entity).onSpawnWithEgg(null);
					this.worldObj.removeEntity(this);
					this.worldObj.spawnEntityInWorld(entity);
					if (!player.capabilities.isCreativeMode) player.getCurrentEquippedItem().stackSize--;
				}
			}
			else
			{
				byte b0 = 0;

				if (item.getItem() == Items.iron_ingot)
				{
					b0 = 1;
				}
				else if (item.getItem() == Items.gold_ingot)
				{
					b0 = 2;
				}
				else if (item.getItem() == Items.diamond)
				{
					b0 = 3;
				}
				else if (item.getItem() == Item.getItemFromBlock(Blocks.stone))
				{
					b0 = 4;
				}
				else if (item.getItem() == Item.getItemFromBlock(Blocks.log))
				{
					b0 = 5;
				}
				else if (item.getItem() == Items.emerald)
				{
					b0 = 6;
				}
				else if (item.getItem() == TragicItems.RedMercury)
				{
					b0 = 7;
				}
				else if (item.getItem() == TragicItems.Tungsten)
				{
					b0 = 8;
				}
				else if (item.getItem() == TragicItems.Ruby)
				{
					b0 = 9;
				}
				else if (item.getItem() == TragicItems.Sapphire)
				{
					b0 = 10;
				}
				else if (item.getItem() == Items.redstone)
				{
					b0 = 11;
				}
				else if (item.getItem() == Items.coal)
				{
					b0 = 12;
				}
				else if (item.getItem() == Items.dye && item.getItemDamage() == 4)
				{
					b0 = 13;
				}
				else if (item.getItem() == Item.getItemFromBlock(Blocks.netherrack))
				{
					b0 = 14;
				}
				else if (item.getItem() == TragicItems.CelestialDiamond) //allows the statue to rotate by itself
				{
					b0 = 15;
				}
				else if (item.getItem() == Items.blaze_powder) //to reset the texture
				{
					b0 = 0;
				}

				if (b0 != this.getTextureID())
				{
					this.setTextureID(b0);
					if (!player.capabilities.isCreativeMode) player.setCurrentItemOrArmor(0, null);
				}
			}
		}
		else
		{
			this.incrementRotationAngle();
		}
		
		return false;
	}

}
