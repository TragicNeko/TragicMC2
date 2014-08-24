package tragicneko.tragicmc.items.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorSkull extends TragicArmor {

	public ArmorSkull(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		switch (armorType)
		{
		case 0:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:SkullHelmet_lowRes");
			break;
		case 1:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:SkullPlate_lowRes");
			break;
		case 2:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:SkullLegs_lowRes");
			break;
		case 3:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:SkullBoots_lowRes");
			break;
		}
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == TragicItems.SkullLegs)
		{
			return "tragicmc:textures/items/Skull2" + TragicNewConfig.textureRes + ".png";
		}
		return "tragicmc:textures/items/Skull1" + TragicNewConfig.textureRes + ".png";
	}
}
