package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;

public class WeaponEnigmaShield extends ItemShield {

	public WeaponEnigmaShield(ToolMaterial material, Doomsday dday) {
		super(material);
		this.doomsday = dday;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));		
		return par1ItemStack;
	}

}
