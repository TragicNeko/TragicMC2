package tragicneko.tragicmc.items.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorLight extends TragicArmor {

	public ArmorLight(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		switch (armorType)
		{
		case 0:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:LightHelm_lowRes");
			break;
		case 1:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:LightPlate_lowRes");
			break;
		case 2:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:LightLegs_lowRes");
			break;
		case 3:
			this.itemIcon = par1IconRegister.registerIcon("tragicmc:LightBoots_lowRes");
			break;
		} 
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == TragicItems.LightLegs)
		{
			return "tragicmc:textures/items/Light2_lowRes.png";
		}
		return "tragicmc:textures/items/Light1_lowRes.png";
	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.onArmorTick(world, player, itemStack);

		if (player.isPotionActive(Potion.blindness.id))
		{
			player.removePotionEffect(Potion.blindness.id);
		}

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

					if (armor == TragicItems.LightHelm)
					{
						flag1 = true;
					}
					if (armor == TragicItems.LightPlate)
					{
						flag2 = true;
					}
					if (armor == TragicItems.LightLegs)
					{
						flag3 = true;
					}
					if (armor == TragicItems.LightBoots)
					{
						flag4 = true;
					}

					if (flag1 && flag2 && flag3 && flag4)
					{
						player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 600));
						if (TragicNewConfig.allowClarity)
						{
							player.addPotionEffect(new PotionEffect(TragicPotions.Clarity.id, 600));
						}
					}
				}
			}
		}
	}
}
