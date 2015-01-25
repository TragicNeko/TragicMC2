package tragicneko.tragicmc.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday;

public class ArmorOverlord extends TragicArmor {

	public ArmorOverlord(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (slot == 2)
		{
			return "tragicmc:textures/armor/Overlord2_lowRes.png";
		}
		return "tragicmc:textures/armor/Overlord1_lowRes.png";
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.onArmorTick(world, player, itemStack);

		if (!world.isRemote && TragicConfig.allowCripple && player.isPotionActive(TragicPotion.Cripple)) player.removePotionEffect(TragicPotion.Cripple.id);
		if (!world.isRemote && player.isPotionActive(Potion.weakness)) player.removePotionEffect(Potion.weakness.id);
	}
}
