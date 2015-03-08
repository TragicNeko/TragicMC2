package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.harvesterStats;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicMC;

public class EntityHarvester extends TragicMob {

	private int directionTicks;
	private boolean heightChange = false;

	public EntityHarvester(World par1World) {
		super(par1World);
		this.setSize(0.625F, 0.725F);
		this.stepHeight = 1.0F;
		this.experienceValue = 5;
		this.getNavigator().setAvoidsWater(true);
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(harvesterStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(harvesterStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(harvesterStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(harvesterStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(harvesterStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return (int) harvesterStats[5];
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Synapse;
	}

	@Override
	public void onLivingUpdate()
	{				
		super.onLivingUpdate();
		this.motionY = 0D;
		if (this.worldObj.isRemote) return;

		if (this.isCollidedHorizontally || this.motionX == 0D && this.motionZ == 0D || this.directionTicks == 0)
		{
			if (rand.nextBoolean())
			{
				this.motionX = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * (rand.nextBoolean() ? 1 : -1);
				this.motionZ = 0D;
			}
			else
			{
				this.motionZ = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * (rand.nextBoolean() ? 1 : -1);
				this.motionX = 0D;
			}
			this.directionTicks = 200 + rand.nextInt(120);
			//TragicMC.logInfo("Direction changed.");
		}
		//this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.directionTicks > 0) this.directionTicks--;

		if (this.ticksExisted % 20 == 0)
		{
			double d0 = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(d0, d0, d0));
			boolean flag = false;

			for (Entity e : list)
			{
				if (this.canEntityBeSeen(e) && e instanceof EntityLivingBase)
				{
					if (((EntityLivingBase) e).getCreatureAttribute() == TragicEntities.Synapse)
					{
						((EntityLivingBase) e).addPotionEffect(new PotionEffect(Potion.damageBoost.id, 120, 0));
					}
					else
					{
						((EntityLivingBase) e).addPotionEffect(new PotionEffect(Potion.weakness.id, 120, 0));
						flag = true;
					}
				}
			}

			if (flag && rand.nextInt(32) == 0)
			{
				EntityNanoSwarm swarm = new EntityNanoSwarm(this.worldObj);
				swarm.setPosition(this.posX, this.posY, this.posZ);
				this.worldObj.spawnEntityInWorld(swarm);
			}
		}
	}

	@Override
	public void fall(float par1) {}

	@Override
	public void updateFallState(double par1, boolean par2) {}
}
