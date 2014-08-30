package tragicneko.tragicmc.items.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorDark extends TragicArmor {
	
	protected Lore[] uniqueLores = new Lore[] {new Lore("It's dark"), new Lore("Dim"), new Lore("Rather dark out!"), new Lore("Hold me"), new Lore("I'm so alone"),
			new Lore("Cold, dark and alone", EnumRarity.uncommon), new Lore("Darkness all around me", EnumRarity.uncommon), new Lore("I Know What You Did Last Summer", EnumRarity.rare),
			new Lore("Scream!", EnumRarity.rare), new Lore("Just another slasher...", EnumRarity.uncommon), 
			new Lore("I was born in the dark", EnumRarity.rare), new Lore("Welcome to my nightmare!", EnumRarity.epic), new Lore("Groovy", EnumRarity.epic),
			new Lore("Come play with us..", EnumRarity.rare), new Lore("One, Two, Freddy's coming for you", EnumRarity.epic),
			new Lore("Oh yes, there will be blood", EnumRarity.uncommon), new Lore("Wanna play?"), new Lore("Broken, Beaten and Scarred", EnumRarity.uncommon),
			new Lore("I want to play a game", EnumRarity.uncommon), new Lore("The Boogeyman is real and you found him.", EnumRarity.epic),
			new Lore("Fright Night", EnumRarity.rare), new Lore("Be afraid, be very afraid.", EnumRarity.uncommon), new Lore("It rubs the lotion on it's skin",EnumRarity.epic),
			new Lore("I see dead people", EnumRarity.uncommon), new Lore("Join us, one of us!", EnumRarity.epic), new Lore("Victims... aren't we all?", EnumRarity.epic),
			new Lore("You will die in 7 days.", EnumRarity.uncommon), new Lore("Three, Four, better lock your door", EnumRarity.epic),
			new Lore("Everything will be okay...", EnumRarity.uncommon), new Lore("Don't worry, there's nothing to be afraid of", EnumRarity.uncommon),
			new Lore("Maybe I'm the Schizophrenic Psycho", EnumRarity.rare), new Lore("We all go a little mad sometimes", EnumRarity.epic)};
	
	public ArmorDark(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
		this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[][] {{Enchantment.unbreaking}, {Enchantment.unbreaking, Enchantment.protection}, {Enchantment.unbreaking}};
		this.uncommonLevels = new int[][] {{3}, {3, 1}, {3}};
		this.rareEnchants = new Enchantment[][] {{Enchantment.unbreaking, TragicEnchantments.DeathTouch}, {Enchantment.unbreaking, Enchantment.protection,
			TragicEnchantments.DeathTouch}, {Enchantment.unbreaking, TragicEnchantments.DeathTouch}};
		this.rareLevels = new int[][] {{5, 3}, {5, 3, 3}, {5, 3}};
		this.epicEnchants = new Enchantment[][] {{Enchantment.unbreaking, TragicEnchantments.DeathTouch, Enchantment.respiration}, {Enchantment.unbreaking, Enchantment.protection,
			TragicEnchantments.DeathTouch, TragicEnchantments.Toxicity}, {Enchantment.unbreaking, TragicEnchantments.DeathTouch, Enchantment.featherFalling}};
		this.epicLevels = new int[][] {{10, 5, 1}, {10, 5, 3, 3}, {10, 5, 1}};
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
		
		if (TragicNewConfig.allowFear && player.isPotionActive(TragicPotions.Fear)) player.removePotionEffect(TragicPotions.Fear.id); 
		
		if (!world.isRemote && tick % 120 == 0)
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
					
					if (flag1 && flag2 && flag2 && flag4 && TragicNewConfig.allowImmunity) player.addPotionEffect(new PotionEffect(TragicPotions.Immunity.id, 600));
				}
			}
		}
	}
}
