package tragicneko.tragicmc.items.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.client.ClientProxy;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorMercury extends TragicArmor {

	public ArmorMercury(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		switch (armorType)
		{
		case 0:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:MercuryHelm_lowRes");
			break;
		case 1:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:MercuryPlate_lowRes");
			break;
		case 2:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:MercuryLegs_lowRes");
			break;
		case 3:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:MercuryBoots_lowRes");
			break;
		}  
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == TragicItems.MercuryLegs)
		{
			return "tragicmc:textures/items/Mercury2_lowRes.png";
		}
		return "tragicmc:textures/items/Mercury1_lowRes.png";
	}

	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (TragicNewConfig.allowToxicity)
		{
			par1ItemStack.addEnchantment(TragicEnchantments.Toxicity, 1);
		}
	}
}
