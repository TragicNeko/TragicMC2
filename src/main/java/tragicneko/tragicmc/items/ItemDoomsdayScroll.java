package tragicneko.tragicmc.items;

import java.awt.Color;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicTabs;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDoomsdayScroll extends Item {

	public ItemDoomsdayScroll()
	{
		this.setCreativeTab(TragicTabs.Survival);
		this.setMaxDamage(0);
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setUnlocalizedName("tragicmc.doomsdayScroll");
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.epic;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		Doomsday dday = Doomsday.getDoomsdayFromId(par1ItemStack.getItemDamage() + 1);

		par2List.add(EnumChatFormatting.WHITE + "This is a one-time use, regardless of if");
		par2List.add(EnumChatFormatting.WHITE + "the effect is successful or not.");

		if (TragicNewConfig.allowDoomsdays && dday != null)
		{
			par2List.add(""); //extra space in between
			EnumChatFormatting format = dday.getDoomsdayType().getFormat();
			par2List.add(format + dday.getLocalizedType() + ": " + dday.getLocalizedName());
			par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + dday.getScaledDoomRequirement(par2EntityPlayer.worldObj));
		}
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		if (par1ItemStack.getItemDamage() < Doomsday.doomsdayNames.length)
		{
			Doomsday dday = Doomsday.getDoomsdayFromId(par1ItemStack.getItemDamage() + 1);
			Color color = new Color(0x00, 0x00, 0x00);
			if (dday != null)
			{
				switch(dday.getDoomsdayType())
				{
				case COMBINATION:
					color = new Color(0xFF, 0xDB, 0x76);
					//color = light purple
					break;
				case CRISIS:
					color = new Color(0xB7, 0x00, 0x00);
					//color = red
					break;
				case INFLUENCE:
					//color = aqua or light blue
					color = new Color(0x43, 0xBD, 0xBD);
					break;
				case OVERFLOW:
					//color = green
					color = new Color(0x00, 0xFF, 0x00);
					break;
				case WORLDSHAPER:
					color = new Color(0x9C, 0x23, 0xAC);
					//color = dark purple
					break;
				default:
					break;
				}

				return color.getRGB();
			}
		}
		return -1;
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		String s = ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		if (stack.getItemDamage() >= Doomsday.doomsdayNames.length - 1 || Doomsday.getDoomsdayFromId(stack.getItemDamage() + 1) == null) return s;
		return Doomsday.getDoomsdayFromId(stack.getItemDamage() + 1).getLocalizedName() + " " + s;
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) 
	{
		if (world.isRemote || !TragicNewConfig.allowDoomsdays) return stack;

		PropertyDoom doom = PropertyDoom.get(player);
		if (doom != null)
		{
			Doomsday doomsday = Doomsday.getDoomsdayFromId(stack.getItemDamage() + 1);
			if (doomsday != null) doomsday.activateDoomsday(doom);
			stack.stackSize--;
		}
		return stack;
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < Doomsday.doomsdayNames.length - 1; i++)
		{
			if (Doomsday.doomsdayNames[i] != null) list.add(new ItemStack(item, 1, i));
		}
	}
}
