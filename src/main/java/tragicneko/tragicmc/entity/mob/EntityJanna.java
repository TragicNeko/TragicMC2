package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityJanna extends EntityJabba {

	public EntityJanna(World par1World) {
		super(par1World);
		this.canCorrupt = false;
		this.isCorruptible = false;
		this.isChangeable = false;
		this.isImmuneToFire = false;
		this.superiorForm = null;
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.365);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.5);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
	}
	
	public void onLivingUpdate()
	{
		this.angerTicks = 0;
		super.onLivingUpdate();
	}
	
	protected void doParticleEffects() {
		for (int k = 0; k < 3; ++k)
		{
			this.worldObj.spawnParticle("slime",
					this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D,
					this.posY + this.rand.nextDouble() * (double)this.height - 0.55D,
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D,
					this.rand.nextDouble(),
					this.rand.nextDouble() - 0.6D,
					this.rand.nextDouble());
		}
	}
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.getHealth() <= this.getMaxHealth() / 2)
		{
			par2 /= 2;
		}
		
		Boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (this.rand.nextInt(8) == 0 && this.worldObj.difficultySetting == EnumDifficulty.HARD && result)
		{
			if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer && !par1DamageSource.isProjectile() && !par1DamageSource.isMagicDamage() && !par1DamageSource.isFireDamage())
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

				if (player.getCurrentEquippedItem() != null && !player.capabilities.isCreativeMode)
				{
					player.dropOneItem(true);
				}
			}
		}

		return result;
	}
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		byte d = 0;

		if (this.worldObj.difficultySetting == EnumDifficulty.EASY)
		{
			d = 1;
		}
		if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
		{
			d = 2;
		}
		if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
		{
			d = 3;
		}

		if (this.rand.nextInt(MathHelper.ceiling_double_int(9 / d)) == 0 && super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity instanceof EntityLivingBase)
			{
				if (this.rand.nextInt(4) == 0)
				{
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 120 * d, d));
				}
			}
			
			if (par1Entity instanceof EntityPlayer && d >= 3)
			{
				EntityPlayer player = (EntityPlayer) par1Entity;

				if (player.getCurrentEquippedItem() != null && !player.capabilities.isCreativeMode)
				{
					player.dropOneItem(true);
				}
			}
		}

		return super.attackEntityAsMob(par1Entity);
	}
}
