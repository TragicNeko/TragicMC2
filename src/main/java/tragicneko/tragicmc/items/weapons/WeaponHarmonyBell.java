package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotions;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponHarmonyBell extends TragicWeapon {

	//private final Lore[] uniqueLores = new Lore[] {new Lore("Hell's Bells!", EnumRarity.rare), new Lore("Ding dong.", EnumRarity.uncommon), new Lore("For Whom the Bell Tolls...", EnumRarity.epic),
	//	new Lore("Fahoo Fores, Dahoo Dores!", EnumRarity.rare), new Lore("Ding ding, dinner's ready!"), new Lore("Ring-a-ding ding", EnumRarity.uncommon), new Lore("Jingle Bells!")};
	
	public WeaponHarmonyBell(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
		//this.lores = uniqueLores;
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.knockback};
		this.rareLevels = new int[] {3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, Enchantment.knockback, TragicEnchantments.Absolve};
		this.epicLevels = new int[] {5, 3, 1};
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		PropertyDoom doom = PropertyDoom.get(player);

		if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && TragicNewConfig.allowHarmony && canUseAbility(doom, 3) && getStackCooldown(stack) == 0 && TragicNewConfig.nonDoomsdayAbilities[17])
		{
			if (doom != null && doom.getCurrentDoom() >= 3)
			{
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotions.Harmony.id, 60, 0));
				if (!player.capabilities.isCreativeMode) doom.increaseDoom(-3);
				setStackCooldown(stack, 5);
			}
		}

		return true;
	}

	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(stack, world, entity, par4, par5);
		
		if (entity instanceof EntityLivingBase)
		{
			if (par5 && entity.ticksExisted % 120 == 0 && ((EntityLivingBase) entity).getHealth() < ((EntityLivingBase) entity).getMaxHealth() && TragicNewConfig.nonDoomsdayAbilities[18])
			{
				if (entity instanceof EntityPlayer)
				{
					PropertyDoom doom = PropertyDoom.get((EntityPlayer) entity);

					if (canUseAbility(doom, 0))
					{
						if (!((EntityPlayer) entity).capabilities.isCreativeMode) doom.increaseDoom(-1);
						((EntityLivingBase) entity).heal(1.0F);
					}
				}
			}
		}
	}
}
