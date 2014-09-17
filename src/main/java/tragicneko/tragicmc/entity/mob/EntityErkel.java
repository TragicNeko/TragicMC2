package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.main.TragicBiomes;
import tragicneko.tragicmc.main.TragicEntities;

public class EntityErkel extends TragicMob {

	public EntityErkel(World par1World) {
		super(par1World);
		this.setSize(0.36F, 1.36F);
		this.experienceValue = 5;
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setCanSwim(false);
		this.tasks.addTask(0, new EntityAIPanic(this, 0.65D));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, TragicBoss.class, 12.0F, 0.4D, 0.6D));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.45D));
		this.canCorrupt = false;
		this.isCorruptible = false;
		this.isChangeable = false;
	}
	
	protected void entityInit()
	{
		super.entityInit();
		this.getDataWatcher().addObject(16, Integer.valueOf((int) 0));
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.476);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0);
	}
	
	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		if (source.getEntity() != null && source.getEntity() instanceof EntityLivingBase && !source.isProjectile() && rand.nextBoolean() && !this.worldObj.isRemote)
		{
			((EntityLivingBase) source.getEntity()).addPotionEffect(new PotionEffect(Potion.poison.id, 120, 0));
		}
		
		if (this.worldObj.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				this.worldObj.spawnParticle("witchMagic", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
			}
		}
		return super.attackEntityFrom(source, damage);
	}
	
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			String s = "mobSpellAmbient";
			
			for (int i = 0; i < 2; i++)
			{
				this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
			}
		}
	}
	
	protected boolean isValidLightLevel()
    {
        return true;
    }
	
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
		int i = 0;
		if (TragicBiomes.ashenBiomes.contains(biome))
		{
			i = 1;
		}
		else if (TragicBiomes.paintedBiomes.contains(biome))
		{
			i = 2;
		}
		else if (TragicBiomes.starlitBiomes.contains(biome))
		{
			i = 3;
		}
		
		this.setTextureId(i);
		return super.onSpawnWithEgg(data);
	}
	
	private void setTextureId(int i)
	{
		this.getDataWatcher().updateObject(16, i);
	}
	
	public int getTextureId()
	{
		return this.getDataWatcher().getWatchableObjectInt(16);
	}
}
