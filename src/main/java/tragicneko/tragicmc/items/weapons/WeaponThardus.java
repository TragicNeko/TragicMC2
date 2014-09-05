package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponThardus extends EpicWeapon {

	public WeaponThardus(Doomsday dday) {
		super(dday);
		this.lores = new Lore[] {new Lore("ThardusLore1"), new Lore("ThardusLore2"), new Lore("ThardusLore3")};
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking};
		this.uncommonLevels = new int[] {1};
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.RuneBreak};
		this.rareLevels = new int[] {3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.RuneBreak};
		this.epicLevels = new int[] {5, 3};
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote) return super.onLeftClickEntity(stack, player, entity);

		if (itemRand.nextInt(4) != 0) return super.onLeftClickEntity(stack, player, entity);

		PropertyDoom doom = PropertyDoom.get(player);

		if (doom != null && doom.getCurrentDoom() >= 5 && entity instanceof EntityLivingBase)
		{
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1200, 6));

			if (!player.capabilities.isCreativeMode)
			{
				doom.increaseDoom(-5);
			}

			this.cooldown = 40;
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom == null || !TragicNewConfig.allowNonDoomsdayAbilities) return par1ItemStack;
		
		MovingObjectPosition mop = getMOPFromPlayer(par3EntityPlayer);

		if (mop == null) return par1ItemStack;

		if (!par2World.isRemote && doom.getCurrentDoom() >= 3)
		{
			double d4 = mop.hitVec.xCoord - par3EntityPlayer.posX;
			double d5 = mop.hitVec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
			double d6 = mop.hitVec.zCoord - par3EntityPlayer.posZ;
			
			for (int i = 0; i < 7; i++)
			{
				EntityIcicle rocket = new EntityIcicle(par3EntityPlayer.worldObj, par3EntityPlayer, d4 + itemRand.nextDouble() - itemRand.nextDouble(), d5,
						d6 + itemRand.nextDouble() - itemRand.nextDouble());
				rocket.posX += d4 * 0.115D;
				rocket.posY = par3EntityPlayer.posY + 0.6D;
				rocket.posZ += d6 * 0.115D;
				par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);
			}
			
			if (!par3EntityPlayer.capabilities.isCreativeMode)
			{
				doom.increaseDoom(-3);
			}
		}
		
		return par1ItemStack;
	}

}
