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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponBeastlyClaws extends TragicWeapon {

	private final Lore[] uniqueLores = new Lore[] {new Lore("C-c-c-combo Breaker!", EnumRarity.epic), new Lore("Epic.", EnumRarity.uncommon), new Lore("Going Beastmode!", EnumRarity.rare),
			new Lore("Slice 'n' Dice!", EnumRarity.uncommon), new Lore("One-Two Punch", EnumRarity.uncommon), new Lore("Sucker Punch"), new Lore("Falcon Punch!", EnumRarity.rare),
			new Lore("Just sharpening my claws!", EnumRarity.rare), new Lore("Knockout!"), new Lore("TKO!", EnumRarity.rare), new Lore("Punchout!", EnumRarity.uncommon)};

	private static final AttributeModifier mod = new AttributeModifier(UUID.fromString("b4d7a819-5dd1-4383-b1ac-66ef8ea9e40f"), "beastlyClawsComboModifier", 0, 0);

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
		player.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(mod);
		return super.onDroppedByPlayer(item, player);
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		PropertyDoom doom = PropertyDoom.get(player);

		if (canUseAbility(doom, 0))
		{
			if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && stack.hasTagCompound())
			{
				if (!stack.stackTagCompound.hasKey("comboCooldown")) stack.stackTagCompound.setInteger("comboCooldown", 0);
				if (!stack.stackTagCompound.hasKey("comboDamage")) stack.stackTagCompound.setDouble("comboDamage", 0.0);
				if (!stack.stackTagCompound.hasKey("comboCount")) stack.stackTagCompound.setInteger("comboCount", 0);

				int cooldown = stack.stackTagCompound.getInteger("comboCooldown");
				double damage = stack.stackTagCompound.getDouble("comboDamage");
				int combo = stack.stackTagCompound.getInteger("comboCount");

				if (cooldown > 0)
				{
					if (combo < 5)
					{
						stack.stackTagCompound.setInteger("comboCount", combo + 1);
						stack.stackTagCompound.setDouble("comboDamage", damage + 2.0);
					}

					if (cooldown > 5 && cooldown < 20) stack.stackTagCompound.setInteger("comboCooldown", cooldown + 5);
					if (!player.capabilities.isCreativeMode) doom.increaseDoom(-1);

				}
				else
				{
					stack.stackTagCompound.setInteger("comboCount", 0);
					stack.stackTagCompound.setInteger("comboCooldown", 20);
				}
			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	} 

	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(stack, world, entity, par4, par5);

		if (world.isRemote) return;

		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		if (!stack.stackTagCompound.hasKey("comboCooldown")) stack.stackTagCompound.setInteger("comboCooldown", 0);
		if (!stack.stackTagCompound.hasKey("comboDamage")) stack.stackTagCompound.setDouble("comboDamage", 0.0);
		if (!stack.stackTagCompound.hasKey("comboCount")) stack.stackTagCompound.setInteger("comboCount", 0);

		int cooldown = stack.stackTagCompound.getInteger("comboCooldown");
		double damage = stack.stackTagCompound.getDouble("comboDamage");
		int combo = stack.stackTagCompound.getInteger("comboCount");

		if (cooldown > 0) stack.stackTagCompound.setInteger("comboCooldown", cooldown - 1);

		if (cooldown == 0 && !world.isRemote)
		{
			stack.stackTagCompound.setDouble("comboDamage", 0.0);
			stack.stackTagCompound.setInteger("comboCount", 0);
		}

		AttributeModifier mod2 = new AttributeModifier(mod.getID(), "beastlyClawsComboModifier", damage, 0);

		if (entity instanceof EntityPlayer)
		{
			((EntityPlayer) entity).getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(mod2);
			if (par5) ((EntityPlayer) entity).getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(mod2);
		}
	}

}
