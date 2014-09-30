package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.EntityVoxStellarum;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityCryse extends TragicMob {

	public EntityCryse(World par1World) {
		super(par1World);
		this.stepHeight = 1.0F;
		this.experienceValue = 6;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 48.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 48.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return 15728880;
	}

	public float getBrightness(float par1)
	{
		return 1.0F;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
		this.dataWatcher.addObject(17, Integer.valueOf(0));
		this.dataWatcher.addObject(18, Integer.valueOf(0));
		this.dataWatcher.addObject(19, Integer.valueOf(0));
		this.dataWatcher.addObject(20, Integer.valueOf(0));
	}

	public int getSpinTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	protected void decrementSpinTicks()
	{
		int pow = this.getSpinTicks();
		this.setSpinTicks(--pow);
	}

	protected void setSpinTicks(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	public boolean isSpinning()
	{
		return this.getSpinTicks() > 0;
	}

	public int getCryseType()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	protected void setCryseType(int i)
	{
		this.dataWatcher.updateObject(17, i);

		if (i == 0)
		{
			this.setSize(0.425F, 1.475F);
		}
		else
		{
			this.setSize(0.525F * 0.625F, 1.475F * 0.625F);
		}
	}

	public int getTextureID()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	protected void setTextureID(int i)
	{
		this.dataWatcher.updateObject(18, i);
	}

	public int getAttackTime()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	protected void setAttackTime(int i)
	{
		this.dataWatcher.updateObject(19, i);
	}

	private void decrementAttackTime()
	{
		int pow = this.getAttackTime();
		this.setAttackTime(--pow);
	}

	public int getFlutterTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	protected void setFlutterTicks(int i)
	{
		this.dataWatcher.updateObject(20, i);
	}

	private void decrementFlutterTicks()
	{
		int pow = this.getFlutterTicks();
		this.setFlutterTicks(--pow);
	}

	public boolean isFluttering()
	{
		return this.getFlutterTicks() > 0;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		boolean flag = this.getCryseType() == 0;
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(flag ? 35.0 : 55.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(flag ? .285 : .315);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48.0);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			if (this.getCryseType() == 0)
			{
				this.setSize(0.425F, 1.475F);
			}
			else
			{
				this.setSize(0.525F * 0.625F, 1.475F * 0.625F);
			}
		}
		else
		{
			if (this.isSpinning()) this.decrementSpinTicks();
			if (this.getAttackTime() > 0) this.decrementAttackTime();
			if (this.isFluttering()) this.decrementFlutterTicks();
			if (this.superiorForm == null && this.getCryseType() == 1) this.superiorForm = new EntityVoxStellarum(this.worldObj);

			if (this.ticksExisted % 120 == 0 && this.getAttackTarget() == null && rand.nextBoolean() && !this.isSpinning() && !this.isFluttering())
			{
				if (rand.nextBoolean())
				{
					this.setSpinTicks(120);
				}
				else
				{
					this.setFlutterTicks(60);
				}
			}
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.worldObj.isRemote) return false;

		if (this.isSpinning()) this.setSpinTicks(0);
		if (this.isFluttering()) this.setFlutterTicks(0);

		if (par1DamageSource.isFireDamage())
		{
			par2 = (par2 * 4.175F) - 1.0F;
		}

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemPickaxe)
			{
				par2 *= 1.275;
			}
		}	

		boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (result) this.setAttackTime(10);

		if (result && par1DamageSource.getEntity() != null && this.rand.nextInt(4) == 0)
		{
			this.worldObj.playSoundAtEntity(this, "mob.enderdragon.hit", 0.4F, 1.0F);
			par1DamageSource.getEntity().attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this), par2 / 4);
		}

		return result;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;

		boolean result = super.attackEntityAsMob(par1Entity);

		EnumDifficulty dif = this.worldObj.difficultySetting;
		int x = 2;

		if (dif == EnumDifficulty.NORMAL)
		{
			x = 3;
		}
		else if (dif == EnumDifficulty.HARD)
		{
			x = 4;
		}

		if (result)
		{
			if (this.isSpinning()) this.dataWatcher.updateObject(16, 0);
			if (this.getFlutterTicks() > 0) this.setFlutterTicks(0);

			if (par1Entity instanceof EntityLivingBase)
			{
				if (rand.nextInt(16 / x) == 0) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100 + rand.nextInt(140), 1 + rand.nextInt(2)));
				if (rand.nextInt(32 / x) == 0) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 100 + rand.nextInt(140), 1 + rand.nextInt(2)));
			}
		}

		return result;
	}

	public int getTotalArmorValue()
	{
		return 4;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("texture")) this.setTextureID(tag.getInteger("texture"));
		if (tag.hasKey("cryseType")) this.setCryseType(tag.getInteger("cryseType"));
		if (tag.hasKey("spinTicks")) this.setSpinTicks(tag.getInteger("spinTicks"));
		if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
		if (tag.hasKey("flutterTicks")) this.setFlutterTicks(tag.getInteger("flutterTicks"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("texture", this.getTextureID());
		tag.setInteger("cryseType", this.getCryseType());
		tag.setInteger("spinTicks", this.getSpinTicks());
		tag.setInteger("attackTime", this.getAttackTime());
		tag.setInteger("flutterTicks", this.getFlutterTicks());
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		if (!this.worldObj.isRemote)
		{
			BiomeGenBase biome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
			this.setCryseType(biome instanceof BiomeGenStarlitPrarie ? 1 : 0);
			this.setTextureID(rand.nextInt(8));
		}
		return super.onSpawnWithEgg(data);
	}

	@Override
	protected boolean isChangeAllowed() {
		return TragicNewConfig.allowMegaCryse;
	}

}
