package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponSplinter extends EpicWeapon {

	public WeaponSplinter(Doomsday dday) {
		super(dday);
		this.lores = new Lore[] {new Lore("SplinterLore1"), new Lore("SplinterLore2"), new Lore("SplinterLore3")};
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.RuneBreak};
		this.rareLevels = new int[] {3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.RuneBreak};
		this.epicLevels = new int[] {5, 3};
	}
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote || itemRand.nextInt(4) != 0 || TragicNewConfig.allowNonDoomsdayAbilities) return super.onLeftClickEntity(stack, player, entity);

		PropertyDoom doom = PropertyDoom.get(player);

		if (doom != null && doom.getCurrentDoom() >= 3 && entity instanceof EntityLivingBase)
		{
			entity.motionX = itemRand.nextDouble() - itemRand.nextDouble();
			entity.motionY = 0.45D;
			entity.motionZ = itemRand.nextDouble() - itemRand.nextDouble();

			if (!player.capabilities.isCreativeMode)
			{
				doom.increaseDoom(-3);
			}

			this.cooldown = 20;
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom == null || !TragicNewConfig.allowNonDoomsdayAbilities) return par1ItemStack;
		
		MovingObjectPosition mop = getMOPFromPlayer(par3EntityPlayer);

		if (mop == null) return par1ItemStack;

		if (!par2World.isRemote && doom.getCurrentDoom() >= 10)
		{
			List<Entity> list = par2World.getEntitiesWithinAABBExcludingEntity(par3EntityPlayer, par3EntityPlayer.boundingBox.expand(12.0D, 12.0D, 12.0D));
			EntityLivingBase entity;
			
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityLivingBase)
				{
					entity = (EntityLivingBase) list.get(i);
					
					entity.motionX = itemRand.nextDouble() - itemRand.nextDouble();
					entity.motionY = 0.45D;
					entity.motionZ = itemRand.nextDouble() - itemRand.nextDouble();
				}
			}
			
			if (!par3EntityPlayer.capabilities.isCreativeMode)
			{
				doom.increaseDoom(-10);
			}
			
			cooldown = 40;
		}
		
		return par1ItemStack;
	}

}
