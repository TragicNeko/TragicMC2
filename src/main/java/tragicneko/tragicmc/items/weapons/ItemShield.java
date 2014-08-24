package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicTabs;
import tragicneko.tragicmc.properties.PropertyDoom;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShield extends Item {
	
	public float shieldResistance;
	public Item.ToolMaterial material;
	public float damageVsEntity;
	public Doomsday doomsday;
	
	public ItemShield (Item.ToolMaterial material)
	{
		super();
		this.shieldResistance = material.getDamageVsEntity() * 4.0F;
		this.material = material;
		this.setMaxDamage(material.getMaxUses());
		this.setCreativeTab(TragicTabs.Survival);
		this.damageVsEntity = 1.0F;
	}
	
	public Doomsday getDoomsday()
	{
		return this.doomsday;
	}
	
	public EnumDoomType doomsdayType()
	{
		return this.doomsday.doomsdayType;
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			PropertyDoom doom = PropertyDoom.get(par2EntityPlayer);
			
			EnumChatFormatting format = EnumChatFormatting.DARK_AQUA;
			
			if (doomsday.getDoomsdayType() == EnumDoomType.CRISIS)
			{
				format = EnumChatFormatting.DARK_RED;
			}
			
			if (doomsday.getDoomsdayType() == EnumDoomType.OVERFLOW)
			{
				format = EnumChatFormatting.GREEN;
			}
			
			if (doomsday.getDoomsdayType() == EnumDoomType.WORLDSHAPER)
			{
				format = EnumChatFormatting.DARK_PURPLE;
			}
			
			if (doomsday.getDoomsdayType() == EnumDoomType.ULTIMATE)
			{
				format = EnumChatFormatting.DARK_BLUE;
			}

			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());

			if (doom != null)
			{
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(doom));
			}
		}
	}
	
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 20000;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
	
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    public Item.ToolMaterial func_150913_i()
    {
        return this.material;
    }
    
    public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }

    public String getToolMaterialName()
    {
        return this.material.toString();
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return this.material.func_150995_f() == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
    
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", (double)this.damageVsEntity, 0));
        return multimap;
    }

}
