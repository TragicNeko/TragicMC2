package tragicneko.tragicmc.items.weapons;

import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeaponSwordOfJustice extends ItemSword {

	public WeaponSwordOfJustice(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setMaxDamage(1);
		this.setCreativeTab(TragicMC.Creative);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2)
    {
		if (entity2 instanceof EntityPlayer && !entity.worldObj.isRemote) entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) entity2), Float.MAX_VALUE);
		if (entity2 instanceof EntityPlayer && !entity.worldObj.isRemote && !((EntityPlayer) entity2).capabilities.isCreativeMode) stack.stackSize--;
		return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.epic;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack, int passes)
	{
		return true;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (world.isRemote) return;
		
		if (!stack.isItemEnchanted())
		{
			if (TragicConfig.allowReach) stack.addEnchantment(TragicEnchantments.Reach, 5);
			stack.addEnchantment(Enchantment.looting, 10);
		}
	}
}
