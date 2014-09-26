package tragicneko.tragicmc.entity.mob;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.entity.boss.EntityMagmox;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityPox extends EntityTox {

	public EntityPox(World par1World) {
		super(par1World);
		this.setSize(0.5F * 0.75F, 1.9F * 0.75F); /*
		this.canCorrupt = false;
		this.isCorruptible = false;
		this.isChangeable = false; */
		this.superiorForm = null;
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.05);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
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
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);

		if (result && par1Entity instanceof EntityLivingBase && rand.nextInt(4) == 0)
		{
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 300 * (1 + rand.nextInt(4)), 1 + rand.nextInt(3)));
		}

		return result;
	}
	
	public int getTotalArmorValue()
	{
		return this.isFiring ? 0 : 6;
	}
}
