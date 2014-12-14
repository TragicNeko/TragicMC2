package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponThardus extends EpicWeapon {
	
	//private final Lore[] uniqueLores = new Lore[] {new Lore("Hyper beam!", EnumRarity.epic), new Lore("Zero suit...", EnumRarity.uncommon), new Lore("Grappling Hook!", EnumRarity.rare),
	//		new Lore("Better than a Stun Gun.", EnumRarity.uncommon), new Lore("That's cool.", EnumRarity.uncommon), new Lore("Spazer Beam!"), new Lore("Phazon Beam!", EnumRarity.rare),
	//		new Lore("Super Missle!", EnumRarity.rare), new Lore("Screw Attack!", EnumRarity.epic), new Lore("The last Metroid is in captivity.", EnumRarity.rare),
	//		new Lore("Tasty.", EnumRarity.uncommon), new Lore("Charge Beam!"), new Lore("Plasma Beam!", EnumRarity.rare), new Lore("Heading to Brinstar!", EnumRarity.uncommon),
	//		new Lore("Onwards to Norfair!", EnumRarity.rare), new Lore("Finally, Tourian!", EnumRarity.epic)};

	public WeaponThardus(Doomsday dday) {
		super(dday);
		//this.lores = uniqueLores;
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach, TragicEnchantments.RuneBreak};
		this.rareLevels = new int[] {5, 3, 3};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach, TragicEnchantments.RuneBreak, TragicEnchantments.Rust, TragicEnchantments.Luminescence};
		this.epicLevels = new int[] {10, 3, 5, 3, 1};
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote || itemRand.nextInt(4) != 0 || !TragicNewConfig.allowNonDoomsdayAbilities) return super.onLeftClickEntity(stack, player, entity);

		PropertyDoom doom = PropertyDoom.get(player);

		if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && canUseAbility(doom, 5) && getStackCooldown(stack) == 0)
		{
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1200, 6));
			if (!player.capabilities.isCreativeMode) doom.increaseDoom(-5);
			setStackCooldown(stack, 5);
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom == null || !TragicNewConfig.allowNonDoomsdayAbilities || par2World.isRemote) return par1ItemStack;
		
		Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
		if (vec == null) return par1ItemStack;

		if (canUseAbility(doom, 3))
		{
			double d4 = vec.xCoord - par3EntityPlayer.posX;
			double d5 = vec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
			double d6 = vec.zCoord - par3EntityPlayer.posZ;
			
			for (int i = 0; i < 7; i++)
			{
				EntityIcicle rocket = new EntityIcicle(par3EntityPlayer.worldObj, par3EntityPlayer, d4 + itemRand.nextDouble() - itemRand.nextDouble(), d5,
						d6 + itemRand.nextDouble() - itemRand.nextDouble());
				rocket.posX += d4 * 0.115D;
				rocket.posY = par3EntityPlayer.posY + 0.6D;
				rocket.posZ += d6 * 0.115D;
				par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);
			}
			
			if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-3);
			setStackCooldown(par1ItemStack, 5);
		}
		
		return par1ItemStack;
	}

}
