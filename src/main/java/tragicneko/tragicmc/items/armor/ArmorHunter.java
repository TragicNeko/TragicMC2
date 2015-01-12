package tragicneko.tragicmc.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.doomsday.Doomsday;

public class ArmorHunter extends TragicArmor {

	public ArmorHunter(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == TragicItems.HuntersLegs)
		{
			return "tragicmc:textures/armor/Hunter2_lowRes.png";
		}
		return "tragicmc:textures/armor/Hunter1_lowRes.png";
	}
}
