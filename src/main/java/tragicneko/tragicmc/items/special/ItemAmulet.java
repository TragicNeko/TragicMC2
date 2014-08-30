package tragicneko.tragicmc.items.special;

import java.awt.Color;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import tragicneko.tragicmc.main.TragicTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAmulet extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon theIcon;

	private int amuletLevel;
	private int amuletID;
	private String amuletName;
	private String amuletSpecial;

	private boolean isCursed;
	
	private int chainColor;
	private int gemColor;

	public ItemAmulet(int id, int level, String name, int necklaceColor, int gemstoneColor)
	{
		this.amuletID = id;
		this.amuletLevel = level;
		this.amuletName = name;
		this.amuletSpecial = name.toLowerCase() + amuletLevel + ".special";
		this.setUnlocalizedName("tragicmc.amulet" + name);
		this.setCreativeTab(TragicTabs.Survival);
		this.setMaxStackSize(1);
		this.setTextureName("tragicmc:Amulet" + amuletLevel + "_lowRes");
		this.chainColor = necklaceColor;
		this.gemColor = gemstoneColor;
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1, int par2)
	{
		return this.amuletLevel > 3;
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int par2)
	{
		return par2 > 0 ? this.theIcon : super.getIconFromDamageForRenderPass(par1, par2);
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		switch (amuletLevel)
		{
		case 1:
			return EnumRarity.uncommon;
		case 2:
			return EnumRarity.rare;
		case 3:
			return EnumRarity.epic;
		case 4:
			return EnumRarity.epic;
		}
		return EnumRarity.common;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		String specialName = StatCollector.translateToLocal(amuletSpecial);
		par2List.add(EnumChatFormatting.RED + specialName);

		String s = "Amulet Level: ";
		String s1 = null;
		switch (this.amuletLevel)
		{
		case 1:
			s1 = EnumChatFormatting.AQUA + s + "1";
			break;
		case 2:
			s1 = EnumChatFormatting.BLUE + s + "2";
			break;
		case 3:
			s1 = EnumChatFormatting.DARK_BLUE + s + "3";
			break;
		case 4:
			s1 = EnumChatFormatting.DARK_RED + s + "Cursed";
			break;
		}

		par2List.add(s1);
	}

	public int getMaxDamage()
	{
		return 300;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		if (this.amuletLevel == 4)
		{
			if (par2 == 0)
			{
				return 0;
			}
			else
			{
				return this.gemColor;
			}
		}
		else
		{
			if (par2 == 0)
			{
				return this.chainColor;
			}
			else
			{
				return this.gemColor;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		super.registerIcons(par1IconRegister);

		this.theIcon = par1IconRegister.registerIcon("tragicmc:Amulet" + amuletLevel + "_lowRes_overlay");
	}

	public boolean getIsCursed()
	{
		return this.amuletLevel == 4;
	}

	public int getAmuletLevel()
	{
		return this.amuletLevel;
	}

	public String getAmuletName()
	{
		return this.amuletName;
	}

	public int getAmuletID()
	{
		return this.amuletID;
	}
}
