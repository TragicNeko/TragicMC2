package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponSplinter extends EpicWeapon {
	
	//private final Lore[] uniqueLores = new Lore[] {new Lore("A Dime a Dozen", EnumRarity.epic), new Lore("Piece of cake!", EnumRarity.uncommon), new Lore("Beating around the bush!", EnumRarity.rare),
	//		new Lore("Don't cry over spilled milk!", EnumRarity.uncommon), new Lore("Sorry to burst your bubble!", EnumRarity.uncommon), new Lore("Beggars can't be choosers!"),
	//		new Lore("Don't count your chickens before they hatch!", EnumRarity.rare), new Lore("Sorry to rain on your parade!", EnumRarity.uncommon),
	//		new Lore("On cloud nine!", EnumRarity.rare), new Lore("Read 'em and weep!", EnumRarity.epic), new Lore("Curiosity killed the cat!", EnumRarity.rare),
	//		new Lore("Easy as pie!", EnumRarity.uncommon), new Lore("That was delicious!"), new Lore("Jack of all trades, master of none...", EnumRarity.rare),
	//		new Lore("Just roll with the punches!", EnumRarity.rare), new Lore("It's not rocket science!", EnumRarity.rare), new Lore("Fool's gold!", EnumRarity.uncommon)};

	public WeaponSplinter(Doomsday dday) {
		super(dday);
		//this.lores = uniqueLores;
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach, TragicEnchantments.RuneBreak};
		this.rareLevels = new int[] {5, 3, 3};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach, TragicEnchantments.RuneBreak, Enchantment.knockback};
		this.epicLevels = new int[] {10, 5, 5, 3};
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom == null || !TragicNewConfig.allowNonDoomsdayAbilities || par2World.isRemote) return par1ItemStack;
		
		Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
		if (vec == null) return par1ItemStack;

		if (canUseAbility(doom, TragicNewConfig.nonDoomsdayAbilityCosts[28]) && getStackCooldown(par1ItemStack) == 0 && TragicNewConfig.nonDoomsdayAbilities[28])
		{
			List<Entity> list = par2World.getEntitiesWithinAABBExcludingEntity(par3EntityPlayer, par3EntityPlayer.boundingBox.expand(12.0D, 12.0D, 12.0D).offset(vec.xCoord, vec.yCoord, vec.zCoord));
			EntityLivingBase entity;
			
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityLivingBase)
				{
					entity = (EntityLivingBase) list.get(i);
					
					entity.motionX = (itemRand.nextDouble() - itemRand.nextDouble()) * 2.455;
					entity.motionY = 0.45D;
					entity.motionZ = (itemRand.nextDouble() - itemRand.nextDouble()) * 2.455;
				}
			}
			
			if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicNewConfig.nonDoomsdayAbilityCosts[28]);
			setStackCooldown(par1ItemStack, 5);
		}
		
		return par1ItemStack;
	}

}
