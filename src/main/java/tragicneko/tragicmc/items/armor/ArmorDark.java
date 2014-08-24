package tragicneko.tragicmc.items.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorDark extends TragicArmor {
	
	public ArmorDark(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		switch (armorType)
		{
		case 0:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:DarkHelm_lowRes");
			break;
		case 1:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:DarkPlate_lowRes");
			break;
		case 2:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:DarkLegs_lowRes");
			break;
		case 3:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:DarkBoots_lowRes");
			break;
		} 
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == TragicItems.DarkLegs)
		{
			return "tragicmc:textures/items/Dark2_lowRes.png";
		}
		return "tragicmc:textures/items/Dark1_lowRes.png";
	}


	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.onArmorTick(world, player, itemStack);
		
		if (!world.isRemote && tick % 120 == 0)
		{
			PropertyDoom doom = PropertyDoom.get(player);

			Boolean flag0 = false;
			Boolean flag1 = false;
			Boolean flag2 = false;
			Boolean flag3 = false;
			Boolean flag4 = false;
			
			for (int a = 0; a < 5; a++)
			{				
				if (player.getEquipmentInSlot(a) == null)
				{
					if (a == 0)
					{
						flag0 = true;
					}
				}
				else
				{
					Item armor = player.getEquipmentInSlot(a).getItem();

					if (armor == TragicItems.DarkHelm)
					{
						flag1 = true;
					}
					if (armor == TragicItems.DarkPlate)
					{
						flag2 = true;
					}
					if (armor == TragicItems.DarkLegs)
					{
						flag3 = true;
					}
					if (armor == TragicItems.DarkBoots)
					{
						flag4 = true;
					}
					
					if (flag1 && flag2 && flag2 && flag4)
					{
						if (TragicNewConfig.allowImmunity)
						{
							player.addPotionEffect(new PotionEffect(TragicPotions.Immunity.id, 600));
						}
					}
				}
			}
		}
	}
}
