package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponBlindingLight extends TragicWeapon {
	
	private final Lore[] uniqueLores = new Lore[] {new Lore("Ooh... shiny!", EnumRarity.rare), new Lore("But it's so pretty...", EnumRarity.epic),
		new Lore("Aw, you're glowing~", EnumRarity.uncommon), new Lore("It's bright, like me."), new Lore("Like a shooting star!"),
		new Lore("Always look on the bright side of life!", EnumRarity.uncommon), new Lore("Shine on you Crazy Diamond", EnumRarity.rare),
		new Lore("Heaven let your light shine on!"), new Lore("Turn on your lovelight!", EnumRarity.uncommon)};

	public WeaponBlindingLight(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {TragicEnchantments.Absolve};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {TragicEnchantments.Absolve, Enchantment.unbreaking};
		this.rareLevels = new int[] {3, 1};
		this.epicEnchants = new Enchantment[] {TragicEnchantments.Absolve, Enchantment.unbreaking, Enchantment.fireAspect};
		this.epicLevels = new int[] {5, 3, 2};
	}

	public boolean onLeftClickEntity(ItemStack itemstack, EntityPlayer player, Entity entity)
	{
		Boolean result = super.onLeftClickEntity(itemstack, player, entity);

		PropertyDoom doom = PropertyDoom.get(player);

		if (!result && doom != null)
		{
			if (entity instanceof EntityLivingBase && itemRand.nextInt(16) == 0 && doom.getCurrentDoom() >= 10)
			{
				int x = itemRand.nextInt(6) + 10;

				if (entity.worldObj.getBlockLightValue(MathHelper.ceiling_double_int(entity.posX), MathHelper.ceiling_double_int(entity.posY), MathHelper.ceiling_double_int(entity.posZ)) <= 6)
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), (float)x);
					entity.setFire(x);

					if (!player.capabilities.isCreativeMode)
					{
						doom.increaseDoom(-10);
					}
				}
			}

			if (entity instanceof EntityLivingBase && TragicNewConfig.allowCorruption && ((EntityLivingBase) entity).isPotionActive(TragicPotions.Corruption))
			{
				((EntityLivingBase) entity).removePotionEffect(TragicPotions.Corruption.id);
			}
		}

		return result;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom != null && cooldown == 0 && !par2World.isRemote && TragicNewConfig.allowNonDoomsdayAbilities)
		{
			if (doom.getCurrentDoom() >= 15)
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

				if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK)
				{
					double d4 = mop.hitVec.xCoord - par3EntityPlayer.posX;
					double d5 = mop.hitVec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
					double d6 = mop.hitVec.zCoord - par3EntityPlayer.posZ;

					EntitySolarBomb rocket = new EntitySolarBomb(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
					rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
					par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						doom.increaseDoom(-15);
					}

					this.cooldown = 15;
				}

				return par1ItemStack;

			}
		}
		return par1ItemStack;
	}

}
