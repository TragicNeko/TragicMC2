package tragicneko.tragicmc.items;

import java.util.List;
import java.util.Set;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
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
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.util.AmuletHelper;

import com.google.common.collect.Sets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAmulet extends Item {

	private IIcon[][] iconArray = new IIcon[4][2];

	private int amuletID;
	private String amuletName;

	public static final int COLOR_WHITE = 0xFFFFFF;
	public static final int COLOR_BLACK = 0x000000;

	private static String[] amuletNames = new String[] {"Kitsune", "Peace", "Yeti", "Claymation", "Chicken", "Martyr", "Piercing", "Blacksmith", "Apis", "Creeper", "Zombie",
		"Skeleton", "Sunken", "Time", "Ice", "SnowGolem", "IronGolem", "Enderman", "Wither"};
	private static int[][] amuletColors = new int[][] {{COLOR_BLACK, 0xB51919}, {0x9F5B86, 0xFF9ACA}, {0xFAFAFA, 0x98B4C1}, {0xFF9500, 0xFFCA02}, {0xDEDEDE, 0xFFEAA1},
		{COLOR_BLACK, 0x454545}, {COLOR_BLACK, 0xFFFA5}, {0x949494, 0x696969}, {COLOR_BLACK, 0xEFE297}, {0x27C123, 0x43E140}, {0x3A8F4A, 0x27C1C9}, {0xA0A0A0, 0xC1C1C1},
		{COLOR_BLACK, 0x466DB3}, {COLOR_WHITE, 0xEA92E9}, {0xC4EFFF, 0xA5D0E0}, {0xFFFDF1, 0xABA290}, {0xDBCDC1, 0x8B7260}, {COLOR_BLACK, 0xB547DE}, {COLOR_WHITE, 0x252525}};

	private static Set<Integer> cursedIds = Sets.newHashSet(0, 5, 6, 8, 12, 17);
	private static Set<Integer> epicIds = Sets.newHashSet(13, 18);

	public ItemAmulet(int id)
	{
		this.amuletID = id;
		this.setUnlocalizedName("tragicmc.amulet" + amuletNames[id]);
		this.setCreativeTab(TragicMC.Survival);
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack, int pass)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") && stack.getTagCompound().getInteger("amuletLevel") > 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int pass)
	{
		int i = stack != null && stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") ? stack.getTagCompound().getInteger("amuletLevel") : getDefaultLevels(this.amuletID);
		return this.iconArray[net.minecraft.util.MathHelper.clamp_int(i, 1, 4) - 1][pass > 0 ? 1 : 0];
	}

	@Override
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

	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		int i = stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") ? stack.getTagCompound().getInteger("amuletLevel") : getDefaultLevels(this.amuletID);
		String specialName = StatCollector.translateToLocal(amuletNames[this.amuletID].toLowerCase() + i + ".special");
		par2List.add(EnumChatFormatting.RED + specialName);

		String s = "Amulet Level: ";
		String s1 = getFormatFromLevel(i) + s + (i > 4 ? "Epic" : (i > 3 ? "Cursed" : i));
		if (s1 != null) par2List.add(s1);
	}

	public static EnumChatFormatting getFormatFromLevel(int i)
	{
		return i <= 1 ? EnumChatFormatting.AQUA : (i == 2 ? EnumChatFormatting.BLUE : (i == 3 ? EnumChatFormatting.DARK_BLUE : (i == 4 ? EnumChatFormatting.DARK_RED : EnumChatFormatting.GOLD)));
	}

	@Override
	public int getMaxDamage()
	{
		return 300;
	}

	@Override
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

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister regi)
	{		
		for (int i = 0; i < 4; i++)
		{
			this.iconArray[i][0] = regi.registerIcon("tragicmc:Amulet" + (i + 1));
			this.iconArray[i][1] = regi.registerIcon("tragicmc:Amulet" + (i + 1) + "_overlay");
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
		return cursedIds.contains(i) ? 4 : (epicIds.contains(i) ? 5 : 1);
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
		if (!stack.getTagCompound().hasKey("amuletLevel")) stack.getTagCompound().setInteger("amuletLevel", getDefaultLevels(this.amuletID));
		if (TragicConfig.allowAmuletModifiers && !stack.stackTagCompound.hasKey("AttributeModifiers", 9)) this.applyModifiersToItemStack(stack);
	}
	
	public void applyModifiersToItemStack(ItemStack stack)
	{
		NBTTagList taglist = new NBTTagList();
		NBTTagCompound tag;
		
		IAttribute atr = AmuletHelper.getRandomAttribute();
		AttributeModifier mod = AmuletHelper.getRandomModifier(atr);
		
		if (itemRand.nextInt(100) <= TragicConfig.amuletModifierChance || getDefaultLevels(this.amuletID) == 4)
		{
			stack.getTagCompound().setTag("AttributeModifiers", taglist);
			TragicMC.logInfo("Applied no modifiers");
			return;
		}

		if (mod != null)
		{
			tag = AmuletHelper.writeAttributeModifierToNBT(atr.getAttributeUnlocalizedName(), mod);
			taglist.appendTag(tag);
		}
		
		atr = AmuletHelper.getRandomAttribute();
		mod = AmuletHelper.getRandomModifier(atr);
		
		if (itemRand.nextInt(100) <= TragicConfig.amuletModifierChance2)
		{
			stack.getTagCompound().setTag("AttributeModifiers", taglist);
			TragicMC.logInfo("Applied 1 modifier");
			return;
		}

		if (mod != null)
		{
			tag = AmuletHelper.writeAttributeModifierToNBT(atr.getAttributeUnlocalizedName(), mod);
			taglist.appendTag(tag);
		}
		
		atr = AmuletHelper.getRandomAttribute();
		mod = AmuletHelper.getRandomModifier(atr);
		
		if (itemRand.nextInt(100) <= TragicConfig.amuletModifierChance3)
		{
			stack.getTagCompound().setTag("AttributeModifiers", taglist);
			TragicMC.logInfo("Applied 2 modifiers");
			return;
		}

		if (mod != null)
		{
			tag = AmuletHelper.writeAttributeModifierToNBT(atr.getAttributeUnlocalizedName(), mod);
			taglist.appendTag(tag);
		}
		
		stack.getTagCompound().setTag("AttributeModifiers", taglist);
		TragicMC.logInfo("Applied 3 modifiers");
	}

	public static class AmuletModifier extends SharedMonsterAttributes {
		public static final IAttribute reach = (new RangedAttribute("tragicmc.reach", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Reach").setShouldWatch(true);
		public static final IAttribute jumpHeight = (new RangedAttribute("tragicmc.jumpHeight", 1.4, 0.0, Double.MAX_VALUE)).setDescription("Jump Height").setShouldWatch(true);
		public static final IAttribute resistance = (new RangedAttribute("tragicmc.resistance", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Resistance").setShouldWatch(true);
		public static final IAttribute luck = (new RangedAttribute("tragicmc.luck", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Luck").setShouldWatch(true);
	}
}
