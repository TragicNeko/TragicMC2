package tragicneko.tragicmc.items.armor;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicTabs;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TragicArmor extends ItemArmor {
	
	public final Doomsday doomsday;
	protected int cooldown;
	protected long tick;

	public TragicArmor(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, 0, armorType);
		this.doomsday = dday;
		this.setCreativeTab(TragicTabs.Survival);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("tragicmc:NoTexture");
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (TragicNewConfig.allowDoomsdays)
		{
			PropertyDoom doom = PropertyDoom.get(par2EntityPlayer);

			par2List.add(EnumChatFormatting.DARK_AQUA + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());

			if (doom != null)
			{
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(doom));
			}
		}
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		if (!world.isRemote)
		{
			if (cooldown > 0)
			{
				cooldown--;
			}
			
			tick++;
		}
	}

}
