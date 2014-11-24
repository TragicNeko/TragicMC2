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

		if (!result && doom != null && getStackCooldown(itemstack) == 0)
		{
			if (entity instanceof EntityLivingBase && itemRand.nextInt(16) == 0 && canUseAbility(doom, 10))
			{
				int x = itemRand.nextInt(6) + 10;

				if (entity.worldObj.getBlockLightValue(MathHelper.ceiling_double_int(entity.posX), MathHelper.ceiling_double_int(entity.posY), MathHelper.ceiling_double_int(entity.posZ)) <= 6)
				{
					entity.setFire(x);
					if (!player.capabilities.isCreativeMode) doom.increaseDoom(-10);
					setStackCooldown(itemstack, 5);
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
		if (par2World.isRemote) return par1ItemStack;

		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (canUseAbility(doom, 15) && getStackCooldown(par1ItemStack) == 0)
		{
			Vec3 vec = getVecFromPlayer(par3EntityPlayer, 30.0);
			if (vec == null) return par1ItemStack;

			double d4 = vec.xCoord - par3EntityPlayer.posX;
			double d5 = vec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
			double d6 = vec.zCoord - par3EntityPlayer.posZ;

			EntitySolarBomb rocket = new EntitySolarBomb(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
			rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
			par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

			if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-15);
			setStackCooldown(par1ItemStack, 5);
		}
		return par1ItemStack;
	}

}
