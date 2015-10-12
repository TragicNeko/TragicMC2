package tragicneko.tragicmc.items;

import java.util.UUID;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;

public class ItemHydration extends ItemTalisman {

	public static AttributeModifier mod = new AttributeModifier(UUID.fromString("a0de9d5c-2fa2-4042-8261-f68bec735e56"), "hydrationKnockbackResistanceBuff", TragicConfig.modifier[19], 0);

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (world.isRemote || !(entity instanceof EntityPlayer)) return;

		EntityPlayer player = (EntityPlayer) entity;
		player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).removeModifier(mod);

		if (world.isRaining() || player.isInsideOfMaterial(Material.water))
		{
			player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(mod);
			if (player.ticksExisted % 400 == 0) stack.damageItem(1, player);
			if (player instanceof EntityPlayerMP && TragicConfig.allowAchievements) player.triggerAchievement(TragicAchievements.talismanSpecial);
		}
	}
}
