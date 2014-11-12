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
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicTabs;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeaponCelestialLongbow extends ItemBow {

	private static final String[] bowPullIconName = new String[] {"pulling", "pulling1", "pulling2"};

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public final Doomsday doomsday = Doomsday.Snipe;

	private int cooldown;

	private final Lore[] lores = new Lore[] {new Lore("Shooting stars!", EnumRarity.epic), new Lore("Like Meteor showers~"), new Lore("Beautiful Starlights", EnumRarity.rare),
			new Lore("So Beautiful.", EnumRarity.rare), new Lore("Meteor Smash!", EnumRarity.epic), new Lore("Make a wish!", EnumRarity.uncommon),
			new Lore("Time for Armageddon", EnumRarity.epic), new Lore("Guardian of the Galaxy", EnumRarity.rare), new Lore("Time for the Star Festival!", EnumRarity.epic),
			new Lore("Ooh a free Starman!", EnumRarity.uncommon), new Lore("The Final Starman!?", EnumRarity.epic), new Lore("Good morning Starshine, the Earth says, Hello!", EnumRarity.rare)};

	private Enchantment[] uncommonEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.power};
	private int[] uncommonLevels = new int[] {3, 1};

	private Enchantment[] rareEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.power, Enchantment.looting};
	private int[] rareLevels = new int[] {5, 3, 3};

	private Enchantment[] epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.power, Enchantment.looting, TragicEnchantments.Multiply, Enchantment.infinity, TragicEnchantments.Luminescence};
	private int[] epicLevels = new int[] {10, 5, 5, 1, 1, 1};

	public WeaponCelestialLongbow()
	{
		this.setMaxDamage(1348);
		this.setFull3D();
		this.setCreativeTab(TragicTabs.Survival);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) 
	{
		if (usingItem == null) 
		{
			return itemIcon;
		}

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

	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity") ? getRarityFromInt(stack.stackTagCompound.getByte("tragicLoreRarity")) : EnumRarity.common;
	}

	protected EnumRarity getRarityFromInt(int i) {
		return i == 1 ? EnumRarity.uncommon : (i == 2 ? EnumRarity.rare : (i == 3 ? EnumRarity.epic : EnumRarity.common));
	}

	protected Lore getRandomLore()
	{
		return lores[itemRand.nextInt(lores.length)];
	}

	protected int getRarityFromEnum(Lore lore)
	{
		return lore.rarity == EnumRarity.common ? 0 : (lore.rarity == EnumRarity.uncommon ? 1 : (lore.rarity == EnumRarity.rare ? 2 : 3));
	}

	protected EnumChatFormatting getFormatFromRarity(int rarity)
	{
		return rarity == 0 ? EnumChatFormatting.GRAY : (rarity == 1 ? EnumChatFormatting.YELLOW : (rarity == 2 ? EnumChatFormatting.DARK_GREEN : EnumChatFormatting.RED));
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
		return 20;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par3EntityPlayer.isSneaking())
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
			PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
			if (doom != null && doom.getCurrentDoom() >= 5 && cooldown == 0)
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
					
					double d4 = mop.hitVec.xCoord;
					double d5 = mop.hitVec.yCoord;
					double d6 = mop.hitVec.zCoord;
					
					switch(mop.sideHit)
					{
					case 0:
						d5 -= 2.2D;
						break;
					case 1:
						break;
					case 2:
						d6 -= 1.0D;
						break;
					case 3:
						d6 += 1.0D;
						break;
					case 4:
						d4 -= 1.0D;
						break;
					case 5:
						d4 += 1.0D;
						break;
					}

					par3EntityPlayer.setPositionAndUpdate(d4, d5, d6);
					par3EntityPlayer.fallDistance = 0.0F;
					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-5);
					this.cooldown = 5;
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

		boolean flag = par3EntityPlayer.capabilities.isCreativeMode;
		if (!flag && !par3EntityPlayer.inventory.hasItem(Items.arrow)) return;

		float f = (float)j / 40.0F;
        f = (f * f + f * 2.0F) / 3.0F;
		f *= 0.75F;	
		
		TragicMC.logInfo("Pull strength was " + f);
		
		if ((double)f < 0.2D) return;
		if (f > 1.0F) f = 1.0F;

		EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f);

		entityarrow.setDamage(entityarrow.getDamage() + 3.0);
		entityarrow.motionX *= 1.1;
		entityarrow.motionZ *= 1.1;

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

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (!world.isRemote && cooldown > 0)
		{
			this.cooldown--;
		}

		if (!TragicNewConfig.allowRandomWeaponLore || world.isRemote || !(entity instanceof EntityPlayer)) return; 
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		Lore lore = getRandomLore();
		if (!stack.stackTagCompound.hasKey("tragicLore")) stack.stackTagCompound.setString("tragicLore", lore.lore);
		if (!stack.stackTagCompound.hasKey("tragicLoreRarity")) stack.stackTagCompound.setByte("tragicLoreRarity", Byte.valueOf((byte)getRarityFromEnum(lore)));

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
		}
	}

}
