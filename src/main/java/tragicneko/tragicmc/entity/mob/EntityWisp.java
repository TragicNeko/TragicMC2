package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.wispStats;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.worldgen.biome.BiomeGenAshenHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCorrodedSteppe;
import tragicneko.tragicmc.worldgen.biome.BiomeGenHallowedHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityWisp extends TragicMob {

	public EntityWisp(World par1World) {
		super(par1World);
		this.setSize(0.36F, 1.36F);
		this.experienceValue = 15;
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setCanSwim(false);
		this.tasks.addTask(0, new EntityAIPanic(this, 1.0D));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 6.0F, 1.0D, 1.6D));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityGolem.class, 6.0F, 1.0D, 1.6D));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.45D));
		this.stepHeight = 1.0F;
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0)); //modifier
		this.dataWatcher.addObject(17, Integer.valueOf(0)); //ticks
	}

	public int getIdleTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	public int getIdleState()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	private void setIdleTicks(int i)
	{
		this.dataWatcher.updateObject(17, i);
	}

	private void setIdleState(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	private void decrementIdleTicks()
	{
		int pow = this.getIdleTicks();
		this.setIdleTicks(--pow);
	}

	@Override
	public boolean canCorrupt()
	{
		return false;
	}

	@Override
	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
	{
		;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float p_70070_1_)
	{
		return 15728880;
	}

	@Override
	public float getBrightness(float p_70013_1_)
	{
		return 1.0F;
	}

	@Override
	protected void fall(float p_70069_1_) {}

	@Override
	public boolean isBurning()
	{
		return false;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(wispStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(wispStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(wispStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(wispStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(wispStats[4]);
	}
	
	@Override
	public int getTotalArmorValue()
	{
		return (int) wispStats[5];
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			String s = "flame";
			BiomeGenBase biome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
			if (TragicConfig.allowDimension && biome instanceof BiomeGenStarlitPrarie) s = "witchMagic";
			if (TragicConfig.allowDimension && biome instanceof BiomeGenAshenHills) s = "smoke";
			if (TragicConfig.allowDimension && biome instanceof BiomeGenPaintedForest) s = "magicCrit";
			if (TragicConfig.allowDimension && biome instanceof BiomeGenHallowedHills) s = "crit";
			if (TragicConfig.allowDimension && biome instanceof BiomeGenCorrodedSteppe) s = "reddust";

			for (int i = 0; i < 2 && this.getIdleTicks() == 0; i++)
			{
				this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
			}

			if (this.getIdleTicks() > 0)
			{
				switch(this.getIdleState())
				{
				default:
					if (this.ticksExisted % 10 == 0)
					{
						for (int i = 0; i < 5; i++)
						{
							this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.155D), this.posY + 0.115D + rand.nextDouble(),
									this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.155D), rand.nextFloat() * 0.114F - rand.nextFloat() * 0.114F,
									0.155F * this.rand.nextFloat(), rand.nextFloat() * 0.114F - rand.nextFloat() * 0.114F);
						}
						
						this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
								this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
					}
					break;
				case 1:
					for (int i = 0; i < 3; i++)
					{
						this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.655D), this.posY + 0.115D + rand.nextDouble(),
								this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.655D), rand.nextFloat() * 0.07F - rand.nextFloat() * 0.07F, 0.255F * this.rand.nextFloat(),
								rand.nextFloat() * 0.07F - rand.nextFloat() * 0.07F);
					}
					break;
				case 2:
					if (this.ticksExisted % 40 == 0)
					{
						for (int i = 0; i < 2; i++)
						{
							this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.455D), this.posY + 0.115D + rand.nextDouble(),
									this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.455D), rand.nextFloat() * 0.114F - rand.nextFloat() * 0.114F, 0.155F * this.rand.nextFloat(),
									rand.nextFloat() * 0.114F - rand.nextFloat() * 0.114F);
						}
					}
					else
					{
						this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
								this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
					}
					break;
				}
			}
		}
		else
		{
			if (this.getIdleTicks() > 0) this.decrementIdleTicks();

			int x = (int) (this.posX + rand.nextInt(2) - rand.nextInt(2));
			int y = (int) (this.posY + rand.nextInt(2) - rand.nextInt(2));
			int z = (int) (this.posZ + rand.nextInt(2) - rand.nextInt(2));
			if (EntityOverlordCore.replaceableBlocks.contains(worldObj.getBlock(x, y, z)))
			{
				this.worldObj.setBlock(x, y, z, TragicBlocks.Luminescence); 
			}

			if (this.ticksExisted % 20 == 0 && rand.nextInt(8) == 0 && this.getAttackTarget() == null && this.getIdleTicks() == 0)
			{
				int i = rand.nextInt(4);
				this.setIdleTicks(i != 1 ? 40 : 60);
				this.setIdleState(i);
			}
		}
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}
}
