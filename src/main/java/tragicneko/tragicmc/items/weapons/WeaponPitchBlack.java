package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;

public class WeaponPitchBlack extends TragicWeapon {

	public WeaponPitchBlack(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
	}

	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (TragicNewConfig.allowVampirism)
		{
			par1ItemStack.addEnchantment(TragicEnchantments.Vampirism, 1);
		}
		
		if (TragicNewConfig.allowDecay)
		{
			par1ItemStack.addEnchantment(TragicEnchantments.Decay, 5);
		}
	}
}
