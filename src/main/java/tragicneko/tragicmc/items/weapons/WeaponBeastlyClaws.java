package tragicneko.tragicmc.items.weapons;

import java.util.UUID;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponBeastlyClaws extends TragicWeapon {

	private double damage;
	private int combo;

	private final Lore[] uniqueLores = new Lore[] {new Lore("C-c-c-combo Breaker!", EnumRarity.epic), new Lore("Epic.", EnumRarity.uncommon), new Lore("Going Beastmode!", EnumRarity.rare),
		new Lore("Slice 'n' Dice!", EnumRarity.uncommon), new Lore("One-Two Punch", EnumRarity.uncommon), new Lore("Sucker Punch"), new Lore("Falcon Punch!", EnumRarity.rare),
		new Lore("Just sharpening my claws!", EnumRarity.rare), new Lore("Knockout!"), new Lore("TKO!", EnumRarity.rare), new Lore("Punchout!", EnumRarity.uncommon)};
	
	public WeaponBeastlyClaws(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Slay};
		this.rareLevels = new int[] {2, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Slay, TragicEnchantments.Consume};
		this.epicLevels = new int[] {3, 2, 2};
	}

	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
	{
		UUID uuidForMod = UUID.fromString("b4d7a819-5dd1-4383-b1ac-66ef8ea9e40f");
		AttributeModifier mod = new AttributeModifier(uuidForMod, "beastlyClawsComboModifier", 0, 0);
		player.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(mod);
		return super.onDroppedByPlayer(item, player);
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		PropertyDoom doom = PropertyDoom.get(player);

		if (doom != null && cooldown > 0 && !player.worldObj.isRemote)
		{
			if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase)
			{
				if (this.cooldown > 0)
				{
					if (doom.getCurrentDoom() >= 3)
					{
						if (combo < 5)
						{
							combo++;
							this.damage += 2.0 * combo;
						}

						if (cooldown < 200)
						{
							cooldown += 10;
						}
						
						if (!player.capabilities.isCreativeMode)
						{
							doom.increaseDoom(-3);
						}
					}

				}
				else
				{
					combo = 0;
					cooldown = 30;
				}
			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	} 

	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(stack, world, entity, par4, par5);

		if (cooldown == 0 && !world.isRemote)
		{
			this.damage = 0.0;
			combo = 0;
		}

		UUID uuidForMod = UUID.fromString("b4d7a819-5dd1-4383-b1ac-66ef8ea9e40f");
		AttributeModifier mod = new AttributeModifier(uuidForMod, "beastlyClawsComboModifier", this.damage, 0);

		if (entity instanceof EntityPlayer)
		{
			((EntityPlayer) entity).getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(mod);

			if (par5)
			{
				((EntityPlayer) entity).getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(mod);
			}
		}

		if (!par5)
		{
			((EntityPlayer) entity).getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(mod);
		}
	}

}
