package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponCelestialAegis extends TragicWeapon {
	
	private Doomsday doomsday2 = Doomsday.FlightOfTheValkyries;
	
	private final Lore[] uniqueLores = new Lore[] {new Lore("Such a Beautiful Night Sky.", EnumRarity.epic), new Lore("Nice tune.", EnumRarity.uncommon),
			new Lore("A lovely melody.", EnumRarity.rare), new Lore("Death in 6/4 time.", EnumRarity.rare), new Lore("What a lovely screaming voice you have!", EnumRarity.epic),
			new Lore("Death in D-Minor!", EnumRarity.epic), new Lore("Shrieks of terror make a nice ambience~", EnumRarity.rare),
			new Lore("Let the music take your breath away~", EnumRarity.epic), new Lore("Playing a Sonata to the sounds of your pain~", EnumRarity.epic),
			new Lore("This is my jam!", EnumRarity.uncommon), new Lore("Sounds to die for!", EnumRarity.uncommon), new Lore("Symphony No. 5!", EnumRarity.rare),
			new Lore("The Hall of the Mountain King!", EnumRarity.rare), new Lore("Dance of the Hours!", EnumRarity.rare), new Lore("Carmina Burana!", EnumRarity.rare),
			new Lore("The Benny Hill Theme!... No? Okay.", EnumRarity.uncommon), new Lore("The Blue Danube!", EnumRarity.epic), new Lore("Symphony No. 5", EnumRarity.epic),
			new Lore("Go! Go! Power Rangers!... or not.", EnumRarity.uncommon), new Lore("Heroes in a half-shell, turtle power!... kay bye.", EnumRarity.uncommon),
			new Lore("Flight of the Bumblebee!", EnumRarity.rare), new Lore("Screams at 60 beats per minute!", EnumRarity.rare)};

	public WeaponCelestialAegis(ToolMaterial material, Doomsday dday) {
		super(material, dday);
		this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Absolve};
		this.uncommonLevels = new int[] {3, 1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Absolve, TragicEnchantments.Consume};
		this.rareLevels = new int[] {5, 3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Absolve, TragicEnchantments.Consume, Enchantment.knockback, Enchantment.fireAspect};
		this.epicLevels = new int[] {10, 5, 3, 3, 2};
	}
	
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		super.addInformation(stack, par2EntityPlayer, par2List, par4);
		
		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			PropertyDoom doom = PropertyDoom.get(par2EntityPlayer);
			EnumChatFormatting format = EnumChatFormatting.DARK_BLUE;
			par2List.add(format + doomsday2.getLocalizedType() + ": " + doomsday2.getLocalizedName());

			if (doom != null)
			{
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday2.getScaledDoomRequirement(doom));
			}
		}
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote && TragicNewConfig.allowUltimateDoomsday)
		{
			PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
			if (doom != null) doomsday2.activateDoomsday(doom);
		}
		return par1ItemStack;
	}

}
