package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponReaperScythe extends ItemScythe {

	//private final Lore[] lores = new Lore[] {new Lore("Bleed for me.", EnumRarity.epic), new Lore("Bleed out.", EnumRarity.uncommon), new Lore("Let's paint this town red!"),
	//		new Lore("Care for fava beans and a nice chianti?"), new Lore("It's raining blood!", EnumRarity.rare), new Lore("Blood is thicker than water.", EnumRarity.uncommon),
	//		new Lore("Digging deeper just to throw it away!", EnumRarity.epic), new Lore("We all bleed the same.", EnumRarity.uncommon), new Lore("Blood is flowing!", EnumRarity.rare)};

	private Enchantment[] uncommonEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay};
	private int[] uncommonLevels = new int[] {3, 1};

	private Enchantment[] rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay, TragicEnchantments.Vampirism};
	private int[] rareLevels = new int[] {5, 3, 1};

	private Enchantment[] epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Decay, TragicEnchantments.Vampirism, Enchantment.looting};
	private int[] epicLevels = new int[] {10, 5, 3, 3};

	public WeaponReaperScythe(ToolMaterial par2Material, Doomsday dday) {
		super(par2Material);
		this.doomsday = dday;
	}
	/*
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity") ? TragicWeapon.getRarityFromInt(stack.stackTagCompound.getByte("tragicLoreRarity")) : EnumRarity.common;
	}

	protected Lore getRandomLore()
	{
		return lores[itemRand.nextInt(lores.length)];
	} */

	public Doomsday getDoomsday()
	{
		return this.doomsday;
	}

	public EnumDoomType doomsdayType()
	{
		return this.doomsday.doomsdayType;
	}

	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		/*
		if (TragicNewConfig.allowRandomWeaponLore)
		{
			String lore = null;
			EnumChatFormatting loreFormat = EnumChatFormatting.WHITE;
			if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLore")) lore = stack.stackTagCompound.getString("tragicLore");
			if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity")) loreFormat = getFormatFromRarity(stack.stackTagCompound.getByte("tragicLoreRarity"));
			if (lore != null)
			{
				par2List.add(loreFormat + lore);
			}
		} */

		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			PropertyDoom doom = PropertyDoom.get(par2EntityPlayer);

			EnumChatFormatting format = EnumChatFormatting.DARK_AQUA;

			switch(doomsday.getDoomsdayType())
			{
			case COMBINATION:
				format = EnumChatFormatting.YELLOW;
				break;
			case CRISIS:
				format = EnumChatFormatting.RED;
				break;
			case OVERFLOW:
				format = EnumChatFormatting.GREEN;
				break;
			case WORLDSHAPER:
				format = EnumChatFormatting.DARK_PURPLE;
				break;
			case INFLUENCE:
			default:
				break;
			}

			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());

			if (doom != null)
			{
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(doom));
			}
		}
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (!TragicWeapon.canUseAbility(doom, 5) || TragicWeapon.getStackCooldown(par1ItemStack) > 0 || par2World.isRemote) return par1ItemStack;

		Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer, 30.0);
		if (vec == null) return par1ItemStack;


		if (TragicWeapon.getStackCooldown(par1ItemStack) == 0) //TODO change onUpdate in Scythe to use the new cooldown system
		{
			if (!par3EntityPlayer.isSneaking())
			{
				if (TragicWeapon.canUseAbility(doom, 5) && TragicWeapon.getStackCooldown(par1ItemStack) == 0)
				{
					double d4 = vec.xCoord - par3EntityPlayer.posX;
					double d5 = vec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
					double d6 = vec.zCoord - par3EntityPlayer.posZ;

					EntitySmallFireball rocket = new EntitySmallFireball(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
					rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
					par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-5);
					TragicWeapon.setStackCooldown(par1ItemStack, 5);

					return par1ItemStack;
				}
			}
			else
			{
				if (TragicWeapon.canUseAbility(doom, 15) && TragicWeapon.getStackCooldown(par1ItemStack) == 0)
				{
					double d4 = vec.xCoord - par3EntityPlayer.posX;
					double d5 = vec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
					double d6 = vec.zCoord - par3EntityPlayer.posZ;

					EntityLargeFireball rocket = new EntityLargeFireball(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
					rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
					par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-15);
					TragicWeapon.setStackCooldown(par1ItemStack, 5);

					return par1ItemStack;
				}
			}
		}

		return par1ItemStack;
	}
	/*
	protected int getRarityFromEnum(Lore lore)
	{
		return lore.rarity == EnumRarity.common ? 0 : (lore.rarity == EnumRarity.uncommon ? 1 : (lore.rarity == EnumRarity.rare ? 2 : 3));
	}

	protected EnumChatFormatting getFormatFromRarity(int rarity)
	{
		return rarity == 0 ? EnumChatFormatting.GRAY : (rarity == 1 ? EnumChatFormatting.YELLOW : (rarity == 2 ? EnumChatFormatting.DARK_GREEN : EnumChatFormatting.RED));
	} */

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		/*
		if (!TragicNewConfig.allowRandomWeaponLore || world.isRemote || !(entity instanceof EntityPlayer)) return; 
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		Lore lore = getRandomLore();
		if (!stack.stackTagCompound.hasKey("tragicLore")) stack.stackTagCompound.setString("tragicLore", lore.lore);
		if (!stack.stackTagCompound.hasKey("tragicLoreRarity")) stack.stackTagCompound.setByte("tragicLoreRarity", Byte.valueOf((byte)getRarityFromEnum(lore)));
		if (!stack.stackTagCompound.hasKey("cooldown")) stack.stackTagCompound.setInteger("cooldown", 0);

		if (TragicWeapon.getStackCooldown(stack) > 0) TragicWeapon.setStackCooldown(stack, TragicWeapon.getStackCooldown(stack) - 1);

		if (!stack.isItemEnchanted() && stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity"))
		{
			int rarity = stack.stackTagCompound.getByte("tragicLoreRarity");

			Enchantment[] enchants;
			int[] levels;

			if (rarity == 0)
			{
				enchants = new Enchantment[] {Enchantment.unbreaking};
				levels = new int[] {1};
			}
			else if (rarity == 1)
			{
				enchants = this.uncommonEnchants;
				levels = this.uncommonLevels;
			}
			else if (rarity == 2)
			{
				enchants = this.rareEnchants;
				levels = this.rareLevels;
			}
			else
			{
				enchants = this.epicEnchants;
				levels = this.epicLevels;
			}

			for (int i = 0; i < enchants.length; i++)
			{
				if (enchants[i] != null) stack.addEnchantment(enchants[i], levels[i]);
			}
		} */
	}

}
