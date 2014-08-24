package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicTabs;
import tragicneko.tragicmc.properties.PropertyDoom;

public class TragicWeapon extends ItemSword {

	public final Doomsday doomsday;
	public Item.ToolMaterial material;
	
	protected int cooldown;
	
	public TragicWeapon(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_);
		this.doomsday = dday;
		this.setCreativeTab(TragicTabs.Survival);
		this.material = p_i45356_1_;
	}
	
	public Doomsday getDoomsday()
	{
		return this.doomsday;
	}
	
	public EnumDoomType doomsdayType()
	{
		return this.doomsday.doomsdayType;
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
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (!world.isRemote && cooldown > 0)
		{
			this.cooldown--;
		}
	}

}
