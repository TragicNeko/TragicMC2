package tragicneko.tragicmc.entity;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.mob.EntityLockbot;

public class EntityLock extends Entity {

	private EntityLockbot lockOwner;

	private AttributeModifier mod = new AttributeModifier(UUID.fromString("d35fe2b0-2aca-4d5e-b3e3-3fe041dbaf15"), "lockbotDebuff", -3.0D, 2);

	public EntityLock(World world) {
		super(world);
		this.setSize(0.1F, 0.1F);
		this.yOffset = -4F;
	}

	public EntityLock(World world, EntityLockbot owner, EntityLivingBase prisoner)
	{
		super(world);
		this.lockOwner = owner;
		this.mountEntity(prisoner);
	}

	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		if (!this.worldObj.isRemote)
		{
			if (this.ridingEntity instanceof EntityLivingBase) ((EntityLivingBase) this.ridingEntity).getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);

			if (this.ridingEntity instanceof EntityLivingBase && this.lockOwner != null && this.getDistanceToEntity(this.lockOwner) > 8.0F)
			{
				this.ridingEntity.motionX = this.ridingEntity.motionZ = this.ridingEntity.motionY = 0D;
				((EntityLivingBase) this.ridingEntity).getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
				if (TragicConfig.allowLeadFoot) ((EntityLivingBase) this.ridingEntity).addPotionEffect(new PotionEffect(TragicPotion.LeadFoot.id, 10));
				TragicMC.logInfo("Effects should be applied");
			}
			if (this.lockOwner == null || this.lockOwner.isDead)
			{
				this.setDead();
				if (this.ridingEntity instanceof EntityLivingBase) ((EntityLivingBase) this.ridingEntity).getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
			}
		}
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
