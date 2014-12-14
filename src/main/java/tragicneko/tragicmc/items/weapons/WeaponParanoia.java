package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotions;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponParanoia extends EpicWeapon {
	
	//private final Lore[] uniqueLores = new Lore[] {new Lore("They're all out to get me.", EnumRarity.epic), new Lore("Alone.", EnumRarity.uncommon), new Lore("Feeling paranoid...", EnumRarity.rare),
	//		new Lore("Isolation.", EnumRarity.uncommon), new Lore("Fear of everything", EnumRarity.uncommon), new Lore("So scary."), new Lore("Paranoid Android", EnumRarity.rare),
	//		new Lore("Fear and Loathing...", EnumRarity.rare), new Lore("Darkness consumes me...", EnumRarity.epic), new Lore("Lost in the Darkness of my mind...", EnumRarity.rare),
	//		new Lore("I'm afraid.", EnumRarity.uncommon), new Lore("So lonely."), new Lore("Just 'cause you're paranoid, doesn't mean they're not after you.", EnumRarity.rare),
	//		new Lore("Fragile and alone...", EnumRarity.uncommon), new Lore("I'm so alone...", EnumRarity.rare), new Lore("It's calm", EnumRarity.uncommon)};

	public WeaponParanoia(Doomsday dday) {
		super(dday);
		//this.lores = uniqueLores;
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach, TragicEnchantments.RuneBreak};
		this.rareLevels = new int[] {5, 3, 3};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach, TragicEnchantments.RuneBreak, TragicEnchantments.Leech, Enchantment.looting};
		this.epicLevels = new int[] {10, 3, 5, 3, 3};
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		PropertyDoom doom = PropertyDoom.get(player);

		if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && itemRand.nextInt(4) == 0 && canUseAbility(doom, 10) && getStackCooldown(stack) == 0)
		{
			if (TragicNewConfig.allowFear) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotions.Fear.id, 240, itemRand.nextInt(2)));
			if (TragicNewConfig.allowSubmission && itemRand.nextInt(16) == 0) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 320, itemRand.nextInt(4)));
			
			if (!player.capabilities.isCreativeMode) doom.increaseDoom(-10);
			setStackCooldown(stack, 5);
		}
		return super.onLeftClickEntity(stack, player, entity);
	} 

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom == null || !TragicNewConfig.allowNonDoomsdayAbilities || par2World.isRemote) return par1ItemStack;

			if (par3EntityPlayer.isSneaking())
			{
				if (canUseAbility(doom, 25) && getStackCooldown(par1ItemStack) == 0)
				{
					for (int l = 0; l < 5; l++)
					{
						double d0 = (MathHelper.getRandomIntegerInRange(itemRand, -4, 4) + par3EntityPlayer.posX) - par3EntityPlayer.posX; 
						double d1 = (MathHelper.getRandomIntegerInRange(itemRand, -2, 2) + par3EntityPlayer.posY) - par3EntityPlayer.posY;
						double d2 = (MathHelper.getRandomIntegerInRange(itemRand, -4, 4) + par3EntityPlayer.posZ) - par3EntityPlayer.posZ; 

						EntityDarkEnergy fireball = new EntityDarkEnergy(par3EntityPlayer.worldObj, par3EntityPlayer, d0, d1, d2);
						fireball.setPosition(par3EntityPlayer.posX + (d0 * 0.115), par3EntityPlayer.posY + 0.6D, par3EntityPlayer.posZ + (d2 * 0.115));
						par3EntityPlayer.worldObj.spawnEntityInWorld(fireball);
					}

					if (!par3EntityPlayer.capabilities.isCreativeMode)doom.increaseDoom(-25);
					setStackCooldown(par1ItemStack, 5);
					
					return par1ItemStack;
				}
			}
			else
			{
				if (canUseAbility(doom, 5) && getStackCooldown(par1ItemStack) == 0)
				{
					Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
					if (vec == null) return par1ItemStack;
					
					double d4 = vec.xCoord - par3EntityPlayer.posX;
					double d5 = vec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
					double d6 = vec.zCoord - par3EntityPlayer.posZ;

					EntityDarkEnergy rocket = new EntityDarkEnergy(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
					rocket.posX = par3EntityPlayer.posX + d4 * 0.15D;
					rocket.posY = par3EntityPlayer.posY + 0.6D;
					rocket.posZ = par3EntityPlayer.posZ + d6 * 0.15D;
					par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

					if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-5);
					setStackCooldown(par1ItemStack, 5);

					return par1ItemStack;
				}
			}
		

		return par1ItemStack;
	}

}
