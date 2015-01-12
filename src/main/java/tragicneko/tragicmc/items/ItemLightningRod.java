package tragicneko.tragicmc.items;

import java.util.UUID;

import tragicneko.tragicmc.TragicConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLightningRod extends ItemTalisman {
	
	public static AttributeModifier mod = new AttributeModifier(UUID.fromString("7611c3b7-5bb8-4597-849b-c75788f8cc9b"), "lightningRodAttackBuff", TragicConfig.modifierAmts[20], 0);

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (world.isRemote || !(entity instanceof EntityPlayer)) return;
		
		EntityPlayer player = (EntityPlayer) entity;
		player.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(mod);
		
		if (world.isThundering())
		{
			player.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(mod);
			if (player.ticksExisted % 400 == 0) stack.damageItem(1, player);
		}
	}
}
