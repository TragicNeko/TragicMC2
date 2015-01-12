package tragicneko.tragicmc.items.weapons;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponWitheringAxe extends TragicWeapon {

	//private final Lore[] uniqueLores = new Lore[] {new Lore("Like Paul Bunyan.", EnumRarity.uncommon), new Lore("Lemme axe you a question.", EnumRarity.uncommon),
	//	new Lore("Chop chop!", EnumRarity.uncommon), new Lore("Chop Suey?!", EnumRarity.rare), new Lore("Weapon of choice", EnumRarity.uncommon),
	//	new Lore("Get to the tree choppa!", EnumRarity.epic), new Lore("What a luxurious beard you have!", EnumRarity.rare), new Lore("Plaid is the next fashion trend."),
	//	new Lore("He's a lumberjack and he's okay!", EnumRarity.epic)};

	public WeaponWitheringAxe(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		//this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.efficiency};
		this.rareLevels = new int[] {3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.efficiency, Enchantment.knockback};
		this.epicLevels = new int[] {5, 3, 1};
		this.setHarvestLevel("axe", 3);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		PropertyDoom doom = PropertyDoom.get(player);

		if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && itemRand.nextInt(4) == 0 && canUseAbility(doom, TragicConfig.nonDoomsdayAbilityCosts[34]) && getStackCooldown(stack) == 0 && TragicConfig.nonDoomsdayAbilities[34])
		{
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, 60, itemRand.nextInt(4)));
			if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.nonDoomsdayAbilityCosts[34]);
			setStackCooldown(stack, 5);
		}
		return super.onLeftClickEntity(stack, player, entity);
	} 

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom != null && !par2World.isRemote && getStackCooldown(par1ItemStack) == 0)
		{
			if (!par3EntityPlayer.isSneaking())
			{
				if (canUseAbility(doom, TragicConfig.nonDoomsdayAbilityCosts[35]) && TragicConfig.nonDoomsdayAbilities[35])
				{
					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.nonDoomsdayAbilityCosts[35]);
					setStackCooldown(par1ItemStack, 5);

					EntityWitherSkull skull = new EntityWitherSkull(par2World);
					par2World.spawnEntityInWorld(skull);
					return par1ItemStack;
				}
			}
			else
			{
				if (canUseAbility(doom, TragicConfig.nonDoomsdayAbilityCosts[36]) && TragicConfig.nonDoomsdayAbilities[36])
				{
					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.nonDoomsdayAbilityCosts[36]);
					setStackCooldown(par1ItemStack, 5);

					EntityWitherSkull skull = new EntityWitherSkull(par2World);
					skull.setInvulnerable(true);
					par2World.spawnEntityInWorld(skull);
					return par1ItemStack;
				}
			}
		}

		return par1ItemStack;
	}

	@Override
	public float func_150893_a(ItemStack stack, Block block)
	{
		Material material = block.getMaterial();
		return material == Material.wood || material == Material.gourd ? this.material.getEfficiencyOnProperMaterial() : super.func_150893_a(stack, block);
	}
}
