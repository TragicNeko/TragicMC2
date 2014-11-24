package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicTabs;
import tragicneko.tragicmc.properties.PropertyDoom;

public class TragicWeapon extends ItemSword {

	public final Doomsday doomsday;
	protected Doomsday doomsday2;
	public Item.ToolMaterial material;

	protected Lore[] lores = new Lore[] {new Lore("So boring."), new Lore("Nice."), new Lore("Interesting.", EnumRarity.uncommon), new Lore("That's cool.", EnumRarity.rare),
			new Lore("Lame.", EnumRarity.uncommon), new Lore("Meh."), new Lore("Ha.", EnumRarity.rare), new Lore("Awesome.", EnumRarity.epic), new Lore("I'm oozing with excitement", EnumRarity.epic)};

	protected Enchantment[] uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
	protected int[] uncommonLevels = new int[] {1};

	protected Enchantment[] rareEnchants = new Enchantment[] {Enchantment.unbreaking};
	protected int[] rareLevels = new int[] {2};

	protected Enchantment[] epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.sharpness};
	protected int[] epicLevels = new int[] {3, 1};

	public TragicWeapon(ToolMaterial material, Doomsday dday) {
		super(material);
		this.doomsday = dday;
		this.setCreativeTab(TragicTabs.Survival);
		this.material = material;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity") ? getRarityFromInt(stack.stackTagCompound.getByte("tragicLoreRarity")) : EnumRarity.common;
	}

	protected static EnumRarity getRarityFromInt(int i) {
		return i == 1 ? EnumRarity.uncommon : (i == 2 ? EnumRarity.rare : (i == 3 ? EnumRarity.epic : EnumRarity.common));
	}

	protected Lore getRandomLore()
	{
		return lores[itemRand.nextInt(lores.length)];
	}

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
		}

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

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (!TragicNewConfig.allowRandomWeaponLore || world.isRemote || !(entity instanceof EntityPlayer)) return; 
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		Lore lore = getRandomLore();
		if (!stack.stackTagCompound.hasKey("tragicLore")) stack.stackTagCompound.setString("tragicLore", lore.lore);
		if (!stack.stackTagCompound.hasKey("tragicLoreRarity")) stack.stackTagCompound.setByte("tragicLoreRarity", Byte.valueOf((byte)getRarityFromEnum(lore)));
		if (!stack.stackTagCompound.hasKey("cooldown")) stack.stackTagCompound.setInteger("cooldown", 0);
		
		if (getStackCooldown(stack) > 0) setStackCooldown(stack, getStackCooldown(stack) - 1);

		if (!stack.isItemEnchanted() && stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity"))
		{
			int rarity = stack.stackTagCompound.getByte("tragicLoreRarity");
			if (rarity == 0) return;

			Enchantment[] enchants;
			int[] levels;

			if (rarity == 1)
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
		}
	}

	protected static int getRarityFromEnum(Lore lore)
	{
		return lore.rarity == EnumRarity.common ? 0 : (lore.rarity == EnumRarity.uncommon ? 1 : (lore.rarity == EnumRarity.rare ? 2 : 3));
	}

	protected static EnumChatFormatting getFormatFromRarity(int rarity)
	{
		return rarity == 0 ? EnumChatFormatting.GRAY : (rarity == 1 ? EnumChatFormatting.YELLOW : (rarity == 2 ? EnumChatFormatting.DARK_GREEN : EnumChatFormatting.RED));
	}

	public static class Lore {

		public String lore;
		public EnumRarity rarity;

		public Lore(String lore)
		{
			this(lore, EnumRarity.common);
		}

		public Lore(String lore, EnumRarity rarity)
		{
			this.lore = lore;
			this.rarity = rarity;
		}
	}
	
	public static MovingObjectPosition getMOPFromPlayer(EntityPlayer player, double distance)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = distance;

		if (player instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance() + (d3 - 4.0D);
		}
		Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
		
		return player.worldObj.func_147447_a(vec3, vec31, true, false, true);
	}
	
	public static Vec3 getVecFromPlayer(EntityPlayer player, double distance)
	{
		return getMOPFromPlayer(player, distance).hitVec;
	}
	
	public static Vec3 getVecFromPlayer(EntityPlayer player)
	{
		return getMOPFromPlayer(player, 6.0D).hitVec;
	}
	
	public static boolean canUseAbility(PropertyDoom doom, int rq)
	{
		return doom != null && TragicNewConfig.allowNonDoomsdayAbilities && doom.getCurrentCooldown() == 0 && doom.getCurrentDoom() > rq;
	}
	
	public static void setStackCooldown(ItemStack stack, int i)
	{
		if (!stack.hasTagCompound()) return;
		stack.stackTagCompound.setInteger("cooldown", i);
	}
	
	public static int getStackCooldown(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("cooldown") ? stack.stackTagCompound.getInteger("cooldown") : 0;
	}

	public Doomsday getSecondaryDoomsday()
	{
		return this.doomsday2;
	}
}
