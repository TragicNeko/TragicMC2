package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeaponHuntersBow extends ItemBow {

	private static final String[] bowPullIconName = new String[] {"pulling", "pulling1", "pulling2"};

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public final Doomsday doomsday = Doomsday.RapidFire;

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

		if (ticksInUse > 22) 
		{
			return iconArray[2];
		}
		else if (ticksInUse > 12) 
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

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
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

			if (doomsday.getDoomsdayType() == EnumDoomType.ULTIMATE)
			{
				format = EnumChatFormatting.DARK_BLUE;
			}

			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());

			if (doom != null)
			{
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(doom));
			}
		}
	}

	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) 
	{
		par1ItemStack.addEnchantment(Enchantment.punch, 1);
		par1ItemStack.addEnchantment(Enchantment.flame, 1);
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

			if ((double)f < 0.1D)
			{
				return;
			}

			if (f > 1.0F)
			{
				f = 1.0F;
			}

			EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);

			entityarrow.motionX *= 1.3;
			entityarrow.motionZ *= 1.3;
			entityarrow.motionY *= 1.1;

			if (f == 1.0F)
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
}
