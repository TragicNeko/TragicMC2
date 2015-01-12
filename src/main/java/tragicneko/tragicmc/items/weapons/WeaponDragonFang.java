package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponDragonFang extends EpicWeapon {

	//private final Lore[] uniqueLores = new Lore[] {new Lore("Here be dragons.", EnumRarity.epic), new Lore("Is that a Centaur?", EnumRarity.uncommon), new Lore("Pegasus!", EnumRarity.rare),
	//		new Lore("I need some Nymphs...", EnumRarity.uncommon), new Lore("Is that Nessie?", EnumRarity.uncommon), new Lore("Sasquatch!"), new Lore("I found Big Foot!", EnumRarity.rare),
	//		new Lore("It's obviously a Jackalope.", EnumRarity.rare), new Lore("Someone call the CIA or MIB", EnumRarity.epic), new Lore("UFO!", EnumRarity.rare),
	//		new Lore("It's a Ghost!", EnumRarity.uncommon), new Lore("There's a cold spot here..."), new Lore("I'm having an old friend for dinner!", EnumRarity.rare),
	//		new Lore("Can't remember what they said...", EnumRarity.epic), new Lore("Area 51", EnumRarity.rare), new Lore("I've been abducted by aliens!", EnumRarity.uncommon)};

	public WeaponDragonFang(Doomsday dday) {
		super(dday);
		this.doomsday2 = Doomsday.FireRain;
		//this.lores = uniqueLores;
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach, TragicEnchantments.RuneBreak, Enchantment.fireAspect};
		this.rareLevels = new int[] {5, 3, 3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach, TragicEnchantments.RuneBreak, Enchantment.fireAspect, Enchantment.knockback, Enchantment.looting};
		this.epicLevels = new int[] {10, 3, 5, 3, 3, 3};
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote || itemRand.nextInt(4) != 0) return super.onLeftClickEntity(stack, player, entity);

		PropertyDoom doom = PropertyDoom.get(player);

		if (doom != null && canUseAbility(doom, TragicConfig.nonDoomsdayAbilityCosts[9]) && entity instanceof EntityLivingBase && getStackCooldown(stack) == 0 && TragicConfig.nonDoomsdayAbilities[9])
		{
			entity.setFire(8 + itemRand.nextInt(5));
			if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.nonDoomsdayAbilityCosts[9]);
			setStackCooldown(stack, 5);
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par2World.isRemote) return par1ItemStack;

		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
		if (doom == null) return par1ItemStack;

		Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
		if (vec == null) return par1ItemStack;

		double d4 = vec.xCoord - par3EntityPlayer.posX;
		double d5 = vec.yCoord - (par3EntityPlayer.posY + par3EntityPlayer.height / 2.0F);
		double d6 = vec.zCoord - par3EntityPlayer.posZ;

		if (canUseAbility(doom,  TragicConfig.nonDoomsdayAbilityCosts[10]) && getStackCooldown(par1ItemStack) == 0 && TragicConfig.nonDoomsdayAbilities[10])
		{
			EntityLargeFireball rocket = new EntityLargeFireball(par3EntityPlayer.worldObj, par3EntityPlayer, d4 + itemRand.nextDouble() - itemRand.nextDouble(), d5,
					d6 + itemRand.nextDouble() - itemRand.nextDouble());
			rocket.posX += d4 * 0.115D;
			rocket.posY = par3EntityPlayer.posY + 0.6D;
			rocket.posZ += d6 * 0.115D;
			par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

			if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(- TragicConfig.nonDoomsdayAbilityCosts[10]);
			setStackCooldown(par1ItemStack, 5);
		}


		return par1ItemStack;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(stack, world, entity, par4, par5);
		if (entity.isBurning() && TragicConfig.nonDoomsdayAbilities[11])
		{
			entity.extinguish();
			if (entity instanceof EntityPlayerMP)
			{
				EntityPlayerMP mp = (EntityPlayerMP) entity;
				PropertyDoom doom = PropertyDoom.get(mp);
				
				if (doom != null && !mp.capabilities.isCreativeMode) doom.increaseDoom(TragicConfig.nonDoomsdayAbilityCosts[11]);
			}
		}
	}

}
