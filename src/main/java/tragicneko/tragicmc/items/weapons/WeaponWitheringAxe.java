package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponWitheringAxe extends TragicWeapon {
	
	private final Lore[] uniqueLores = new Lore[] {new Lore("Like Paul Bunyan.", EnumRarity.uncommon), new Lore("Lemme axe you a question.", EnumRarity.uncommon),
		new Lore("Chop chop!", EnumRarity.uncommon), new Lore("Chop Suey?!", EnumRarity.rare), new Lore("Weapon of choice", EnumRarity.uncommon),
		new Lore("Get to the tree choppa!", EnumRarity.epic), new Lore("What a luxurious beard you have!", EnumRarity.rare), new Lore("Plaid is the next fashion trend."),
		new Lore("He's a lumberjack and he's okay!", EnumRarity.epic)};

	public WeaponWitheringAxe(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.efficiency};
		this.rareLevels = new int[] {3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.efficiency, Enchantment.knockback};
		this.epicLevels = new int[] {5, 3, 1};
		this.setHarvestLevel("axe", 3);
	}
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		PropertyDoom doom = PropertyDoom.get(player);
		
		if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && itemRand.nextInt(4) == 0 && canUseAbility(doom, 5) && getStackCooldown(stack) == 0)
		{
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, 60, itemRand.nextInt(4)));
			if (!player.capabilities.isCreativeMode) doom.increaseDoom(-5);
			setStackCooldown(stack, 5);
		}
		return super.onLeftClickEntity(stack, player, entity);
	} 

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom != null && !par2World.isRemote && getStackCooldown(par1ItemStack) == 0)
		{
			if (!par3EntityPlayer.isSneaking())
			{
				if (canUseAbility(doom, 10))
				{
					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-10);
					setStackCooldown(par1ItemStack, 5);
					
					EntityWitherSkull skull = new EntityWitherSkull(par2World);
					par2World.spawnEntityInWorld(skull);
					return par1ItemStack;
				}
			}
			else
			{
				if (canUseAbility(doom, 25))
				{
					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-25);
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

}
