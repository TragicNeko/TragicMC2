package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponFrozenLightning extends TragicWeapon {

	private Lore[] uniqueLores = new Lore[] {new Lore("Was that lightning?"), new Lore("Sounds like a storm is coming.", EnumRarity.uncommon), new Lore("I feel shocked.", EnumRarity.rare),
			new Lore("Ouch, you zapped me!"), new Lore("Time for a lightning round!", EnumRarity.rare), new Lore("Lightning crashes", EnumRarity.rare), new Lore("Used Thundershock!", EnumRarity.uncommon),
			new Lore("Static shock!", EnumRarity.epic), new Lore("Used Volt Tackle! Critical hit!", EnumRarity.rare), new Lore("Used Thunder! Super effective!", EnumRarity.epic)};

	public WeaponFrozenLightning(ToolMaterial material, Doomsday dday) {
		super(material, dday);
		this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.RuneBreak};
		this.rareLevels = new int[] {3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.RuneBreak};
		this.epicLevels = new int[] {5, 3};
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && itemRand.nextInt(4) == 0 && cooldown == 0 && TragicNewConfig.allowNonDoomsdayAbilities)
		{
			PropertyDoom doom = PropertyDoom.get(player);

			if (doom != null && cooldown == 0 && !player.worldObj.isRemote && doom.getCurrentDoom() >= 3)
			{
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 1));

				if (!player.capabilities.isCreativeMode)
				{
					doom.increaseDoom(-3);
				}

				cooldown = 100;
			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom != null && cooldown == 0 && !par2World.isRemote)
		{
			float f = 1.0F;
			float f1 = par3EntityPlayer.prevRotationPitch + (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch) * f;
			float f2 = par3EntityPlayer.prevRotationYaw + (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw) * f;
			double d0 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double)f;
			double d1 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double)f + (double)(par3EntityPlayer.worldObj.isRemote ? par3EntityPlayer.getEyeHeight() - par3EntityPlayer.getDefaultEyeHeight() : par3EntityPlayer.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
			double d2 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double)f;
			Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
			float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
			float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
			float f5 = -MathHelper.cos(-f1 * 0.017453292F);
			float f6 = MathHelper.sin(-f1 * 0.017453292F);
			float f7 = f4 * f5;
			float f8 = f3 * f5;
			double d3 = 50.0D;

			if (par3EntityPlayer instanceof EntityPlayerMP)
			{
				d3 = ((EntityPlayerMP)par3EntityPlayer).theItemInWorldManager.getBlockReachDistance() + 46.0;
			}

			Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);

			MovingObjectPosition mop = par3EntityPlayer.worldObj.func_147447_a(vec3, vec31, true, false, true);

			if (mop == null)
			{
				return par1ItemStack;
			}

			double d4 = mop.hitVec.xCoord;
			double d5 = mop.hitVec.yCoord;
			double d6 = mop.hitVec.zCoord;

			if (par3EntityPlayer.isSneaking())
			{
				if (doom.getCurrentDoom() >= 30)
				{
					for (int i = 0; i < 3; i++)
					{
						par3EntityPlayer.worldObj.addWeatherEffect(new EntityLightningBolt(par3EntityPlayer.worldObj, d4 + itemRand.nextDouble() - itemRand.nextDouble(), d5,
								d6 + itemRand.nextDouble() - itemRand.nextDouble()));
					}

					par3EntityPlayer.worldObj.createExplosion(par3EntityPlayer, d4, d5, d6, itemRand.nextFloat() * 2.0F, WorldHelper.getMobGriefing(par2World));

					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						doom.increaseDoom(-30);
					}

					this.cooldown = 40;
				}
			}
			else
			{
				if (doom.getCurrentDoom() >= 5)
				{
					for (int i = 0; i < 5; i++)
					{
						d4 = mop.hitVec.xCoord - par3EntityPlayer.posX;
						d5 = mop.hitVec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
						d6 = mop.hitVec.zCoord - par3EntityPlayer.posZ;

						EntityIcicle rocket = new EntityIcicle(par3EntityPlayer.worldObj, par3EntityPlayer, d4 + itemRand.nextDouble() - itemRand.nextDouble(), d5,
								d6 + itemRand.nextDouble() - itemRand.nextDouble());
						rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
						par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);
					}
					
					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						doom.increaseDoom(-5);
					}

					this.cooldown = 5;

					return par1ItemStack;
				}
			}
		}
		return par1ItemStack;
	}

}
