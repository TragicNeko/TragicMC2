package tragicneko.tragicmc.items.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorHunter extends TragicArmor {

	public ArmorHunter(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		switch (armorType)
		{
		case 0:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:HuntersCap_lowRes");
			break;
		case 1:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:HuntersTunic_lowRes");
			break;
		case 2:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:HuntersLegs_lowRes");
			break;
		case 3:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:HuntersBoots_lowRes");
			break;
		}
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == TragicItems.HuntersLegs)
		{
			return "tragicmc:textures/items/Hunter2_lowRes.png";
		}
		return "tragicmc:textures/items/Hunter1_lowRes.png";
	}

	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) 
	{
		Item item = par1ItemStack.getItem();

		if (item == TragicItems.HuntersCap)
		{
			par1ItemStack.addEnchantment(Enchantment.projectileProtection, 1);
		}

		if (item == TragicItems.HuntersTunic || item == TragicItems.HuntersLegs)
		{
			par1ItemStack.addEnchantment(TragicEnchantments.Agility, 1);
		}

		if (item == TragicItems.HuntersBoots)
		{
			par1ItemStack.addEnchantment(Enchantment.featherFalling, 1);
		}
	}
}
