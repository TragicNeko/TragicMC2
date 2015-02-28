package tragicneko.tragicmc.items.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday;

public class ArmorLight extends TragicArmor {

	public ArmorLight(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return slot == 2 ? "tragicmc:textures/armor/Light2.png" : "tragicmc:textures/armor/Light1.png";
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, int slot)
	{
		return super.getArmorModel(entity, stack, slot);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		if (!world.isRemote)
		{
			Boolean flag1 = false;
			Boolean flag2 = false;
			Boolean flag3 = false;
			Boolean flag4 = false;

			for (int a = 1; a < 5; a++)
			{				
				if (player.getEquipmentInSlot(a) != null)
				{
					Item armor = player.getEquipmentInSlot(a).getItem();

					if (armor == TragicItems.LightHelm) flag1 = true;
					if (armor == TragicItems.LightPlate) flag2 = true;
					if (armor == TragicItems.LightLegs) flag3 = true;
					if (armor == TragicItems.LightBoots) flag4 = true;
				}
			}

			if (flag1 && flag2 && flag3 && flag4)
			{
				if (player.isPotionActive(Potion.blindness.id)) player.removePotionEffect(Potion.blindness.id);		
				if (player.isBurning()) player.extinguish();
				
				if (player.ticksExisted % 60 == 0)
				{
					player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 600));
					if (TragicConfig.allowClarity) player.addPotionEffect(new PotionEffect(TragicPotion.Clarity.id, 600));
				}
			}
		}
	}
}
