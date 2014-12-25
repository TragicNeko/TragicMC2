package tragicneko.tragicmc.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicTabs;
import tragicneko.tragicmc.properties.PropertyAmulets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAmulet extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon[][] iconArray = new IIcon[4][2];

	private int amuletID;
	private String amuletName;

	public static final int COLOR_WHITE = 0xFFFFFF;
	public static final int COLOR_BLACK = 0x000000;

	public static String[] amuletNames = new String[] {"Kitsune", "Peace", "Yeti", "Claymation", "Chicken", "Martyr", "Piercing", "Blacksmith", "Apis", "Creeper", "Zombie",
		"Skeleton", "Sunken", "Time", "Ice", "SnowGolem", "IronGolem", "Enderman", "Wither"};
	public static int[][] amuletColors = new int[][] {{COLOR_BLACK, 0xB51919}, {0x9F5B86, 0xFF9ACA}, {0xFAFAFA, 0x98B4C1}, {0xFF9500, 0xFFCA02}, {0xDEDEDE, 0xFFEAA1},
		{COLOR_BLACK, 0x454545}, {COLOR_BLACK, 0xFFFA5}, {0x949494, 0x696969}, {COLOR_BLACK, 0xEFE297}, {0x27C123, 0x43E140}, {0x3A8F4A, 0x27C1C9}, {0xA0A0A0, 0xC1C1C1},
		{COLOR_BLACK, 0x466DB3}, {COLOR_WHITE, 0xEA92E9}, {0xC4EFFF, 0xA5D0E0}, {0xFFFDF1, 0xABA290}, {0xDBCDC1, 0x8B7260}, {COLOR_BLACK, 0xB547DE}, {COLOR_WHITE, 0x252525}};

	public ItemAmulet(int id)
	{
		this.amuletID = id;
		this.setUnlocalizedName("tragicmc.amulet" + amuletNames[id]);
		this.setCreativeTab(TragicTabs.Survival);
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack, int pass)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") && stack.getTagCompound().getInteger("amuletLevel") > 3;
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	public IIcon getIcon(ItemStack stack, int pass)
	{
		int i = stack != null && stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") ? stack.getTagCompound().getInteger("amuletLevel") : getDefaultLevels(this.amuletID);
		return this.iconArray[net.minecraft.util.MathHelper.clamp_int(i, 1, 4) - 1][pass > 0 ? 1 : 0];
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
		int i = stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") ? stack.getTagCompound().getInteger("amuletLevel") : getDefaultLevels(this.amuletID);

		switch (i)
		{
		case 1:
			return EnumRarity.common;
		case 2:
			return EnumRarity.uncommon;
		case 3:
			return EnumRarity.rare;
		case 4:
		case 5:
			return EnumRarity.epic;
		}
		return EnumRarity.common;
	}

	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		int i = stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") ? stack.getTagCompound().getInteger("amuletLevel") : getDefaultLevels(this.amuletID);
		String specialName = StatCollector.translateToLocal(amuletNames[this.amuletID].toLowerCase() + i + ".special");
		par2List.add(EnumChatFormatting.RED + specialName);

		String s = "Amulet Level: ";
		String s1 = getFormatFromLevel(i) + s + (i > 4 ? "Epic" : (i > 3 ? "Cursed" : i));
		if (s1 != null) par2List.add(s1);
		
		/*

		AmuletModifier[] mods = getModifiersFromAmulet(par2EntityPlayer.getAttributeMap(), stack);
		par2List.add(mods != null ? "Modifiers" : "No Modifiers");
		String[] names = new String[3];
		
		for (int j = 0; j < 3; j++)
		{
			names[j] = mods != null && mods[j] != null ? mods[j].mod.getName() : null;
		}
		
		for (int k = 0; k < 3; k++)
		{
			if (names[k] != null) par2List.add(names[k]);
		} */
	}

	public static EnumChatFormatting getFormatFromLevel(int i)
	{
		return i <= 1 ? EnumChatFormatting.AQUA : (i == 2 ? EnumChatFormatting.BLUE : (i == 3 ? EnumChatFormatting.DARK_BLUE : (i == 4 ? EnumChatFormatting.DARK_RED : EnumChatFormatting.GOLD)));
	}

	public int getMaxDamage()
	{
		return 300;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int par2)
	{
		if (par2 == 0)
		{
			return amuletColors[this.amuletID][0];
		}
		else
		{
			return amuletColors[this.amuletID][1];
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister regi)
	{		
		for (int i = 0; i < 4; i++)
		{
			this.iconArray[i][0] = regi.registerIcon("tragicmc:Amulet" + (i + 1) + "_lowRes");
			this.iconArray[i][1] = regi.registerIcon("tragicmc:Amulet" + (i + 1) + "_lowRes_overlay");
		}
	}

	public boolean isCursed(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") && stack.getTagCompound().getInteger("amuletLevel") == 4;
	}

	public int getAmuletLevel(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") ? stack.getTagCompound().getInteger("amuletLevel") : 0;
	}

	public String getAmuletName(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") ? amuletNames[amuletID] : "";
	}

	public static int getDefaultLevels(int i)
	{
		return (i == 0 || i == 5 || i == 6 || i == 8 || i == 12 || i == 17) ? 4 : ((i == 18 || i == 13) ? 5 : 1);
	}

	public int getAmuletID()
	{
		return this.amuletID;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{		
		if (world.isRemote || !(entity instanceof EntityPlayer)) return; 

		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		if (!stack.getTagCompound().hasKey("amuletLevel")) stack.getTagCompound().setInteger("amuletLevel", getDefaultLevels(this.amuletID)); /*
		if (!stack.getTagCompound().hasKey("amuletModifiers"))
		{
			NBTTagList taglist = new NBTTagList();
			AmuletModifier[] mod = AmuletModifier.getRandomModifiers();

			for (AmuletModifier m : mod)
			{
				if (m == null) continue;
				NBTTagCompound tag = writeAttributeModifierToNBT(m.mod);
				TragicMC.logInfo("Modifier applied " + m.attribute.getAttributeUnlocalizedName());
				tag.setString("attributeName", m.attribute.getAttributeUnlocalizedName());
				taglist.appendTag(tag);
			}
			stack.getTagCompound().setTag("amuletModifiers", taglist);
			TragicMC.logInfo("Modifiers applied to amulet");
		}
		else
		{
			EntityPlayer player = (EntityPlayer) entity;
			PropertyAmulets amu = PropertyAmulets.get(player);
			if (amu == null || !TragicNewConfig.allowAmulets) return; // || TragicNewConfig.allowAmuletModifiers) return;
			
			AmuletModifier[][] mods = new AmuletModifier[3][3];
			for (int i = 0; i < 3; i++) mods[i] = ItemAmulet.getModifiersFromAmulet(player.getAttributeMap(), amu.getActiveAmuletItemStack(i));
			IAttributeInstance attr;

			for (int i = 0; i < 3; i++)
			{
				if (mods[i] == null) continue;
				
				for (int j = 0; j < 3; j++)
				{
					if (mods[i][j] != null)
					{
						attr = player.getAttributeMap().getAttributeInstanceByName(mods[i][j].mod.getName());
						attr.applyModifier(mods[i][j].mod);
					}
				}
			}
		} */
	}

	public static AmuletModifier[] getModifiersFromAmulet(BaseAttributeMap map, ItemStack stack)
	{
		if (stack == null || !stack.hasTagCompound() || !stack.getTagCompound().hasKey("amuletModifiers")) return null;

		NBTTagList list = stack.getTagCompound().getTagList("amuletModifiers", 9);
		NBTTagCompound tag;
		AmuletModifier[] mod = new AmuletModifier[3];
		IAttribute attr;
		TragicMC.logInfo("Tag count is " + list.tagCount());

		for (int i = 0; i < list.tagCount(); i++)
		{
			tag = list.getCompoundTagAt(i);
			mod[i] = new AmuletModifier(map.getAttributeInstanceByName(tag.getString("attributeName")).getAttribute(), SharedMonsterAttributes.readAttributeModifierFromNBT(tag));
		}

		return mod;
	}

	public static NBTTagCompound writeAttributeModifierToNBT(AttributeModifier modif)
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		nbttagcompound.setString("Name", modif.getName());
		nbttagcompound.setDouble("Amount", modif.getAmount());
		nbttagcompound.setInteger("Operation", modif.getOperation());
		nbttagcompound.setLong("UUIDMost", modif.getID().getMostSignificantBits());
		nbttagcompound.setLong("UUIDLeast", modif.getID().getLeastSignificantBits());
		return nbttagcompound;
	}

	public static class AmuletModifier {
		public final AttributeModifier mod;
		public final IAttribute attribute;

		public static final IAttribute reach = (new RangedAttribute("tragicmc.reach", 0.0, 0.0, 16.0).setDescription("Reach").setShouldWatch(true));
		public static final IAttribute jumpHeight = (new RangedAttribute("tragicmc.jumpHeight", 0.0, 0.0, 16.0)).setDescription("Jump Height").setShouldWatch(true);
		public static final IAttribute resistance = (new RangedAttribute("tragicmc.resistance", 0.0, 0.0, 10.0)).setDescription("Resistance").setShouldWatch(true);
		public static final IAttribute luck = (new RangedAttribute("tragicmc.luck", 0.0, 0.0, 4.0)).setDescription("Luck").setShouldWatch(true);

		private static IAttribute[] attrs = new IAttribute[] {SharedMonsterAttributes.attackDamage, SharedMonsterAttributes.knockbackResistance,
			SharedMonsterAttributes.maxHealth, SharedMonsterAttributes.movementSpeed, reach, jumpHeight, resistance, luck};

		public AmuletModifier(IAttribute attr, AttributeModifier modif)
		{
			this.mod = modif;
			this.attribute = attr;
		}

		/**
		 * Generates a maximum of 3 modifiers, 55% to have no modifiers set, 36% to have 1 set, 8% to have 2 set, 1% to have 3 set, may return with null modifiers
		 * @return
		 */
		public static AmuletModifier[] getRandomModifiers()
		{
			AmuletModifier[] mods = new AmuletModifier[3];
			if (itemRand.nextInt(100) <= 54) return mods;
			mods[0] = generateNewModifier();
			if (itemRand.nextInt(100) <= 79) return mods;
			mods[1] = generateNewModifier();
			if (itemRand.nextInt(100) <= 90) return mods;
			mods[2] = generateNewModifier();
			return mods;
		}

		private static AmuletModifier generateNewModifier() {
			IAttribute attr = getRandomAttribute();
			AttributeModifier modif = getRandomModifier(attr);
			return new AmuletModifier(attr, modif);
		}

		private static IAttribute getRandomAttribute()
		{
			return attrs[itemRand.nextInt(attrs.length)];
		}

		private static AttributeModifier getRandomModifier(IAttribute attr)
		{
			double d0 = 0.0D;

			if (attr == SharedMonsterAttributes.attackDamage)
			{
				d0 = Math.round(itemRand.nextDouble() * 10.0D);

			}
			else if (attr == SharedMonsterAttributes.knockbackResistance)
			{
				d0 = itemRand.nextInt(10) * 0.1;
			}
			else if (attr == SharedMonsterAttributes.maxHealth)
			{
				d0 = Math.round(itemRand.nextDouble() * 20.0D);
			}
			else if (attr == SharedMonsterAttributes.movementSpeed)
			{
				d0 = itemRand.nextInt(50) * 0.5;
			}
			else if (attr == reach)
			{
				d0 = itemRand.nextInt(10) * 0.1;
			}
			else if (attr == jumpHeight)
			{
				d0 = itemRand.nextInt(10);
			}
			else if (attr == resistance)
			{
				d0 = itemRand.nextInt(10);
			}
			else if (attr == luck)
			{
				d0 = itemRand.nextDouble();
			}

			return new AttributeModifier(UUID.randomUUID(), attr.getAttributeUnlocalizedName(), d0, 0);
		}
	}
}
