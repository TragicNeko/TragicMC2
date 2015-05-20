package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
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
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.util.LoreHelper;
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

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) 
	{
		if (usingItem == null) return itemIcon;

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

	@Override
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

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getItemIconForUseDuration(int par1)
	{
		return this.iconArray[par1];
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (TragicConfig.allowRandomWeaponLore && LoreHelper.getRarityFromStack(stack) >= 0)
		{
			String lore = LoreHelper.getDescFromStack(stack);
			EnumChatFormatting loreFormat = LoreHelper.getFormatForRarity(LoreHelper.getRarityFromStack(stack));
			
			if (lore != null)
			{
				String[] subs = LoreHelper.splitDesc(lore);
				if (subs != null) for (String sub : subs) par2List.add(loreFormat + sub);
				par2List.add(""); //extra space
			}
		}

		if (TragicConfig.allowDoomsdays && this.doomsday != null)
		{
			EnumChatFormatting format = doomsday.getDoomsdayType().getFormat();
			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());
			par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(par2EntityPlayer.worldObj));
			par2List.add(EnumChatFormatting.DARK_AQUA + "Cooldown: " + doomsday.getScaledCooldown(par2EntityPlayer.worldObj.difficultySetting));
			par2List.add(""); //extra space
		}
	}

	@Override
	public int getItemEnchantability() 
	{
		return 5;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled()) return event.result;

		if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Items.arrow))
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}

		return par1ItemStack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled()) return;
		j = event.charge;

		boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

		if (flag || par3EntityPlayer.inventory.hasItem(Items.arrow))
		{
			float f = j / 20.0F;
	        f = (f * f + f * 2.0F) / 3.0F;
			f *= 1.275F;	
			
			if (f < 0.2D) return;
			if (f > 1.0F) f = 1.0F;

			EntityArrow arrow = new EntityArrow(par2World, par3EntityPlayer, f);
			arrow.motionX *= 1.3;
			arrow.motionZ *= 1.3;
			arrow.motionY *= 1.1;
			if (f >= 1.0F) arrow.setIsCritical(true);

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
			if (k > 0) arrow.setDamage(arrow.getDamage() + k * 0.5D + 0.5D); 

			k = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);
			if (k > 0) arrow.setKnockbackStrength(k);

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0) arrow.setFire(100);

			par1ItemStack.damageItem(1, par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (flag) arrow.canBePickedUp = 2;
			else par3EntityPlayer.inventory.consumeInventoryItem(Items.arrow);

			if (!par2World.isRemote) par2World.spawnEntityInWorld(arrow);
		}
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{		
		if (world.isRemote || !(entity instanceof EntityPlayer)) return; 
		TragicWeapon.updateAsWeapon(stack, world, entity, numb, flag);
	}
}
