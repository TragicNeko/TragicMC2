package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponDragonFang extends EpicWeapon {

	public Doomsday doomsday2 = Doomsday.FireRain;

	public WeaponDragonFang(Doomsday dday) {
		super(dday);
		this.lores = new Lore[] {new Lore("DragonFangLore1"), new Lore("DragonFangLore2"), new Lore("DragonFangLore3")};
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.RuneBreak};
		this.rareLevels = new int[] {3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.RuneBreak};
		this.epicLevels = new int[] {5, 3};
	}

	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		super.addInformation(stack, par2EntityPlayer, par2List, par4);
		
		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			PropertyDoom doom = PropertyDoom.get(par2EntityPlayer);
			EnumChatFormatting format = EnumChatFormatting.DARK_PURPLE;
			par2List.add(format + doomsday2.getLocalizedType() + ": " + doomsday2.getLocalizedName());

			if (doom != null)
			{
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday2.getScaledDoomRequirement(doom));
			}
		}
	}
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote || itemRand.nextInt(4) != 0 || !TragicNewConfig.allowNonDoomsdayAbilities) return super.onLeftClickEntity(stack, player, entity);

		PropertyDoom doom = PropertyDoom.get(player);

		if (doom != null && doom.getCurrentDoom() >= 2 && entity instanceof EntityLivingBase)
		{
			entity.setFire(8 + itemRand.nextInt(5));

			if (!player.capabilities.isCreativeMode)
			{
				doom.increaseDoom(-2);
			}

			this.cooldown = 40;
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom == null) return par1ItemStack;

		MovingObjectPosition mop = getMOPFromPlayer(par3EntityPlayer);

		if (mop == null) return par1ItemStack;

		if (!par2World.isRemote)
		{
			double d4 = mop.hitVec.xCoord - par3EntityPlayer.posX;
			double d5 = mop.hitVec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
			double d6 = mop.hitVec.zCoord - par3EntityPlayer.posZ;

			if (par3EntityPlayer.isSneaking())
			{
				if (doom.getCurrentDoom() >= 10 && !TragicNewConfig.allowNonDoomsdayAbilities)
				{
					EntityLargeFireball rocket = new EntityLargeFireball(par3EntityPlayer.worldObj, par3EntityPlayer, d4 + itemRand.nextDouble() - itemRand.nextDouble(), d5,
							d6 + itemRand.nextDouble() - itemRand.nextDouble());
					rocket.posX += d4 * 0.115D;
					rocket.posY = par3EntityPlayer.posY + 0.6D;
					rocket.posZ += d6 * 0.115D;
					par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);
				}

				if (!par3EntityPlayer.capabilities.isCreativeMode)
				{
					doom.increaseDoom(-10);
				}
			}
			else
			{
				if (TragicNewConfig.allowWorldShaperDoomsday)
				{
					doomsday2.activateDoomsday(doom);
				}
			}
		}

		return par1ItemStack;
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(stack, world, entity, par4, par5);
		if (entity.isBurning())
		{
			entity.extinguish();
		}
	}

}
