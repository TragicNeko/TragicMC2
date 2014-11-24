package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponGravitySpike extends TragicWeapon {
	
	private Lore[] uniqueLores = new Lore[] {new Lore("e=mc2"), new Lore("I like 3.14.", EnumRarity.uncommon), new Lore("The next Einstein.", EnumRarity.uncommon),
		new Lore("Time for a demonstration!"), new Lore("Reaching escape velocity.", EnumRarity.rare), new Lore("Science rules!", EnumRarity.epic),
		new Lore("In spaaaaace!", EnumRarity.epic)};

	public WeaponGravitySpike(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.knockback};
		this.rareLevels = new int[] {3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.knockback, TragicEnchantments.Distract};
		this.epicLevels = new int[] {5, 3, 1};
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && itemRand.nextInt(8) == 0  && TragicNewConfig.allowNonDoomsdayAbilities)
		{
			PropertyDoom doom = PropertyDoom.get(player);

			if (canUseAbility(doom, 7) && getStackCooldown(stack) == 0)
			{
				double d0 = 16.0D;
				double d1 = entity.posX - player.posX;
				double d2 = entity.posZ - player.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				double d3 = 5.0D;

				entity.motionX = d1 / (double)f2 * d3 * 0.800000011920929D + entity.motionX * 0.60000000298023224D;
				entity.motionZ = d2 / (double)f2 * d3 * 0.800000011920929D + entity.motionZ * 0.60000000298023224D;
				entity.motionY += 1.45;
				
				if (!player.capabilities.isCreativeMode) doom.increaseDoom(-7);
				setStackCooldown(stack, 5);
			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	} 
}
