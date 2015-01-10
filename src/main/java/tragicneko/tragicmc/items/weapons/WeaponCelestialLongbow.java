package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.LoreHelper;
import tragicneko.tragicmc.util.WorldHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeaponCelestialLongbow extends ItemBow {

	private static final String[] bowPullIconName = new String[] {"pulling", "pulling1", "pulling2"};

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public final Doomsday doomsday = Doomsday.Snipe;

	public WeaponCelestialLongbow()
	{
		this.setMaxDamage(1348);
		this.setFull3D();
		this.setCreativeTab(TragicMC.Survival);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) 
	{
		if (usingItem == null) return itemIcon;

		int ticksInUse = stack.getMaxItemUseDuration() - useRemaining;

		if (ticksInUse > 48)
		{
			return iconArray[2];
		}
		else if (ticksInUse > 24) 
		{
			return iconArray[1];
		}
		else if (ticksInUse > 6) 
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

	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (TragicNewConfig.allowRandomWeaponLore && LoreHelper.getRarityFromStack(stack) > 0)
		{
			String lore = LoreHelper.getDescFromStack(stack);
			EnumChatFormatting loreFormat = LoreHelper.getFormatForRarity(LoreHelper.getRarityFromStack(stack));

			if (lore != null)
			{
				String[] subs = LoreHelper.splitDesc(lore);
				if (subs != null) for (String sub : subs) par2List.add(loreFormat + sub);
			}
		}

		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			EnumChatFormatting format = doomsday.getDoomsdayType().getFormat();
			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());
			par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(par2EntityPlayer.worldObj));
		}
	}

	public int getItemEnchantability() 
	{
		return 20;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
		
		if (!par3EntityPlayer.isSneaking() || !TragicWeapon.canUseAbility(doom, TragicNewConfig.nonDoomsdayAbilityCosts[5]))
		{
			ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
			MinecraftForge.EVENT_BUS.post(event);
			if (event.isCanceled()) return event.result;

			if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Items.arrow))
			{
				par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
			}
		}
		else
		{
			if (TragicWeapon.getStackCooldown(par1ItemStack) == 0 && TragicNewConfig.nonDoomsdayAbilities[8])
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
					if (!par2World.isRemote) par3EntityPlayer.addChatMessage(new ChatComponentText("Out of range to teleport to!"));
					return par1ItemStack;
				}

				if (mop.typeOfHit == MovingObjectType.BLOCK && par3EntityPlayer instanceof EntityPlayerMP && ((EntityPlayerMP) par3EntityPlayer).playerNetServerHandler.func_147362_b().isChannelOpen())
				{
					if (par3EntityPlayer.isRiding()) par3EntityPlayer.mountEntity((Entity)null);

					double d4 = WorldHelper.getXPositionFromSide(mop.sideHit, mop.hitVec.xCoord);
					double d5 = WorldHelper.getYPositionFromSide(mop.sideHit, mop.hitVec.yCoord);
					double d6 = WorldHelper.getZPositionFromSide(mop.sideHit, mop.hitVec.zCoord);

					par3EntityPlayer.setPositionAndUpdate(d4, d5, d6);
					par3EntityPlayer.fallDistance = 0.0F;
					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicNewConfig.nonDoomsdayAbilityCosts[8]);
					TragicWeapon.setStackCooldown(par1ItemStack, 5);
				}
			}

		}
		return par1ItemStack;
	}

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
			float f = (float)j / 40.0F;
			f = (f * f + f * 2.0F) / 3.0F;
			f *= 0.75F;	

			if ((double)f < 0.2D) return;
			if (f > 1.0F) f = 1.0F;

			EntityArrow arrow = new EntityArrow(par2World, par3EntityPlayer, f);
			arrow.setDamage(arrow.getDamage() + 3.0);
			arrow.motionX *= 1.1;
			arrow.motionZ *= 1.1;
			if (f >= 1.0F) arrow.setIsCritical(true);

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
			if (k > 0) arrow.setDamage(arrow.getDamage() + (double)k * 0.5D + 0.5D);

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
