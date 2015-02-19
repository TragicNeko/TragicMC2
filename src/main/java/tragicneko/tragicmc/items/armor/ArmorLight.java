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
	/*
	protected Lore[] uniqueLores = new Lore[] {new Lore("Believe it!", EnumRarity.rare), new Lore("Don't give up."), new Lore("Overcome", EnumRarity.uncommon),
			new Lore("Be Bright!", EnumRarity.uncommon), new Lore("Live and Let Die", EnumRarity.rare), new Lore("Rise Above This", EnumRarity.rare),
			new Lore("Brighten your day!"), new Lore("Don't Stop Believin'", EnumRarity.rare), new Lore("Shinedown", EnumRarity.epic), new Lore("I'm Mr. Brightside", EnumRarity.epic),
			new Lore("Everything Zen", EnumRarity.rare), new Lore("Pure and innocent.", EnumRarity.epic), new Lore("Rise Against", EnumRarity.uncommon),
			new Lore("Light 'em up!"), new Lore("The Dawn", EnumRarity.uncommon), new Lore("Get Up, Stand Up", EnumRarity.rare),
			new Lore("Dig me out from under what is covering!", EnumRarity.epic), new Lore("Live to Rise", EnumRarity.rare), new Lore("Never Too Late", EnumRarity.uncommon),
			new Lore("Beacon of Hope",EnumRarity.epic), new Lore("Stand up!", EnumRarity.uncommon), new Lore("Everything in its right place", EnumRarity.epic),
			new Lore("Let your light shine down.", EnumRarity.epic), new Lore("Keep faith."), new Lore("Turn around, bright eyes!", EnumRarity.epic), new Lore("Have hope."),
			new Lore("Everything will be alright.", EnumRarity.uncommon), new Lore("It gets better.", EnumRarity.rare), new Lore("Don't ever give up!"),
			new Lore("Always look on the bright side of life!", EnumRarity.epic)}; */
	
	public ArmorLight(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
		//this.lores = uniqueLores;
		//this.uncommonEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking, TragicEnchantments.Ignition}, {Enchantment.unbreaking}};
		//this.uncommonLevels = new int[][] {{3}, {3, 3}, {3}};
		//this.rareEnchants = new Enchantment[][] {{Enchantment.unbreaking, Enchantment.aquaAffinity}, {Enchantment.unbreaking, TragicEnchantments.Ignition, TragicEnchantments.RuneWalker},
		//		{Enchantment.unbreaking, Enchantment.fireProtection}};
		//this.rareLevels = new int[][] {{5, 3}, {5, 5, 3}, {5, 3}};
		//this.epicEnchants = new Enchantment[][] {{Enchantment.unbreaking, Enchantment.aquaAffinity, Enchantment.respiration}, {Enchantment.unbreaking, TragicEnchantments.Ignition,
		//	TragicEnchantments.RuneWalker, TragicEnchantments.Agility}, {Enchantment.unbreaking, TragicEnchantments.RuneWalker, Enchantment.featherFalling}};
		//this.epicLevels = new int[][] {{10, 5, 3}, {10, 5, 5, 3}, {5, 5, 1}};
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (slot == 2)
		{
			return "tragicmc:textures/armor/Light2_lowRes.png";
		}
		return "tragicmc:textures/armor/Light1_lowRes.png";
	}
	
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, int slot)
	{
		return super.getArmorModel(entity, stack, slot);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		if (!world.isRemote && player.ticksExisted % 120 == 0)
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
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 600));
				if (TragicConfig.allowClarity) player.addPotionEffect(new PotionEffect(TragicPotion.Clarity.id, 600));
			}
		}
	}
}
