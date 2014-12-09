package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeaponHuntersBow extends ItemBow {

	private static final String[] bowPullIconName = new String[] {"pulling", "pulling1", "pulling2"};

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public final Doomsday doomsday = Doomsday.RapidFire;
	
	private final Lore[] lores = new Lore[] {new Lore("Let the hunt begin.", EnumRarity.rare), new Lore("On the hunt."), new Lore("Time to join the Hunting Party!", EnumRarity.rare),
			new Lore("I'm an expert tracker.", EnumRarity.uncommon), new Lore("Catch me if you can.", EnumRarity.rare), new Lore("The Hunter became the Hunted"),
			new Lore("The Hunter became the Prey", EnumRarity.uncommon), new Lore("Conquest!"), new Lore("Night of the Hunter", EnumRarity.uncommon),
			new Lore("The Most Dangerous Game", EnumRarity.epic)};

	private Enchantment[] uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
	private int[] uncommonLevels = new int[] {1, 1};

	private Enchantment[] rareEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.punch, Enchantment.flame};
	private int[] rareLevels = new int[] {2, 3, 1};

	private Enchantment[] epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.punch, Enchantment.flame, Enchantment.power};
	private int[] epicLevels = new int[] {3, 5, 3, 3};

	public WeaponHuntersBow()
	{
		this.setMaxDamage(674);
		this.setFull3D();
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) 
	{
		if (usingItem == null) 
		{
			return itemIcon;
		}

		int ticksInUse = stack.getMaxItemUseDuration() - useRemaining;

		if (ticksInUse > 14) 
		{
			return iconArray[2];
		}
		else if (ticksInUse > 8) 
		{
			return iconArray[1];
		}
		else if (ticksInUse > 2) 
		{
			return iconArray[0];
		}
		else 
		{
			return itemIcon;
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(this.getIconString() + "_standby");
		this.iconArray = new IIcon[bowPullIconName.length];

		for (int i = 0; i < this.iconArray.length; ++i)
		{
			this.iconArray[i] = par1IconRegister.registerIcon(this.getIconString() + "_" + bowPullIconName[i]);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getItemIconForUseDuration(int par1)
	{
		return this.iconArray[par1];
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity") ? TragicWeapon.getRarityFromInt(stack.stackTagCompound.getByte("tragicLoreRarity")) : EnumRarity.common;
	}
	
	protected Lore getRandomLore()
	{
		return lores[itemRand.nextInt(lores.length)];
	}

	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (TragicNewConfig.allowRandomWeaponLore)
		{
			String lore = null;
			EnumChatFormatting loreFormat = EnumChatFormatting.WHITE;
			if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLore")) lore = stack.stackTagCompound.getString("tragicLore");
			if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity")) loreFormat = TragicWeapon.getFormatFromRarity(stack.stackTagCompound.getByte("tragicLoreRarity"));
			if (lore != null)
			{
				par2List.add(loreFormat + lore);
			}
		}
		
		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			PropertyDoom doom = PropertyDoom.get(par2EntityPlayer);

			EnumChatFormatting format = EnumChatFormatting.DARK_AQUA;

			if (doomsday.getDoomsdayType() == EnumDoomType.CRISIS)
			{
				format = EnumChatFormatting.DARK_RED;
			}

			if (doomsday.getDoomsdayType() == EnumDoomType.OVERFLOW)
			{
				format = EnumChatFormatting.GREEN;
			}

			if (doomsday.getDoomsdayType() == EnumDoomType.WORLDSHAPER)
			{
				format = EnumChatFormatting.DARK_PURPLE;
			}

			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());

			if (doom != null)
			{
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(doom));
			}
		}
	}

	public int getItemEnchantability() 
	{
		return 5;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return event.result;
		}

		if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Items.arrow))
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}

		return par1ItemStack;
	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}
		j = event.charge;

		boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

		if (flag || par3EntityPlayer.inventory.hasItem(Items.arrow))
		{
			float f = (float)j / 20.0F;
	        f = (f * f + f * 2.0F) / 3.0F;
			f *= 1.275F;	
			
			if ((double)f < 0.2D) return;
			if (f > 1.0F) f = 1.0F;

			EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f);

			entityarrow.motionX *= 1.3;
			entityarrow.motionZ *= 1.3;
			entityarrow.motionY *= 1.1;

			if (f >= 1.0F)
			{
				entityarrow.setIsCritical(true);
			}

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

			if (k > 0)
			{
				entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
			}

			int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

			if (l > 0)
			{
				entityarrow.setKnockbackStrength(l);
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
			{
				entityarrow.setFire(100);
			}

			par1ItemStack.damageItem(1, par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (flag)
			{
				entityarrow.canBePickedUp = 2;
			}
			else
			{
				par3EntityPlayer.inventory.consumeInventoryItem(Items.arrow);
			}

			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(entityarrow);
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
		if (!stack.stackTagCompound.hasKey("tragicLoreRarity")) stack.stackTagCompound.setByte("tragicLoreRarity", Byte.valueOf((byte)TragicWeapon.getRarityFromEnum(lore)));
		
		if (!stack.isItemEnchanted() && stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity"))
		{
			int rarity = stack.stackTagCompound.getByte("tragicLoreRarity");

			Enchantment[] enchants;
			int[] levels;

			if (rarity == 0) return;
			
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
}
