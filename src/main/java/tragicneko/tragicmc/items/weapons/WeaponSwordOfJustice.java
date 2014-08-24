package tragicneko.tragicmc.items.weapons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import tragicneko.tragicmc.doomsday.Doomsday;

public class WeaponSwordOfJustice extends ItemSword {

	public WeaponSwordOfJustice(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
	}
	
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.epic;
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack, int passes)
	{
		return true;
	}
}
