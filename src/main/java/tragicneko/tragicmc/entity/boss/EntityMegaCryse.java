package tragicneko.tragicmc.entity.boss;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.main.TragicEntities;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityMegaCryse extends TragicMiniBoss {

	private int shieldsLeft;
	private int ticksSinceBreak;
	
	private int playerCollisionTicks;
	private int timeSinceCollision;

	public EntityMegaCryse(World par1World) {
		super(par1World);
		this.setSize(0.7F, 3.45F);
		this.stepHeight = 1.0F;
		this.experienceValue = 12;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.canCorrupt = true;
		this.isCorruptible = true;
		this.isChangeable = true;
		this.shieldsLeft = 4;
		//this.lesserForm = new EntityCryse(par1World);
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

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.31);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (!this.worldObj.isRemote && this.ticksSinceBreak > 0)
		{
			this.ticksSinceBreak--;
		}

		if (!this.worldObj.isRemote && this.shieldsLeft < 4 && this.ticksSinceBreak == 0)
		{
			this.shieldsLeft = 4;
		}

		if (!this.worldObj.isRemote)
		{
			timeSinceCollision++;

			if (timeSinceCollision > 80)
			{
				playerCollisionTicks = 20;
			}
		}

		UUID modUUID = UUID.fromString("3466b84d-0df6-4d6c-93cf-0fd4bedc77e9");
		AttributeModifier mod = new AttributeModifier(modUUID, "megaCryseNoShieldBuff", 0.25, 0);
		
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);

		if (this.shieldsLeft == 0)
		{
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
		}

	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.shieldsLeft > 0 && !this.worldObj.isRemote)
		{
			if (this.shieldsLeft == 4)
			{
				this.ticksSinceBreak = 100;
			}
			this.shieldsLeft--;
		}

		if (par1DamageSource.isFireDamage())
		{
			par2 *= 2;
		}

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemPickaxe)
			{
				par2 *= 1.5;
			}			
		}	

		if (super.attackEntityFrom(par1DamageSource, par2) && par1DamageSource.getEntity() != null && this.shieldsLeft > 0)
		{
			par1DamageSource.getEntity().attackEntityFrom(DamageSource.causeMobDamage(this), par2 / 4 * this.shieldsLeft);
		}

		if (this.shieldsLeft > 0 && !par1DamageSource.isMagicDamage())
		{
			par2 = 0;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);

		EnumDifficulty dif = this.worldObj.difficultySetting;
		int x = 1;

		if (dif == EnumDifficulty.EASY)
		{
			x = 2;
		}

		if (dif == EnumDifficulty.NORMAL)
		{
			x = 3;
		}

		if (dif == EnumDifficulty.HARD)
		{
			x = 4;
		}

		if (result && par1Entity instanceof EntityLivingBase && rand.nextInt(8 / x) == 0)
		{
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200 + rand.nextInt(320), 1 + rand.nextInt(4)));
		}

		if (result && par1Entity instanceof EntityLivingBase && rand.nextInt(16 / x) == 0)
		{
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 200 + rand.nextInt(320), 1 + rand.nextInt(4)));
		}

		return result;
	}

	public int getTotalArmorValue()
	{
		return 10;
	}
	
	public void onCollideWithPlayer(EntityPlayer player)
	{
		timeSinceCollision = 0;

		if (player.ticksExisted % 20 == 0)
		{
			if (playerCollisionTicks <= 20 && playerCollisionTicks > 0)
			{
				this.playerCollisionTicks--;
			}
		}
		if (playerCollisionTicks > 0  && !player.capabilities.isCreativeMode)
		{
			if (player.ticksExisted % playerCollisionTicks == 0)
			{
				player.attackEntityFrom(DamageSource.outOfWorld, 0.5F);
			}
		}
	}

}
