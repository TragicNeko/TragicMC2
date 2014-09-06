package tragicneko.tragicmc.items.special;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLightningRod extends ItemTalisman {

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (world.isRemote || !(entity instanceof EntityPlayer)) return;
		
		UUID uuidForMod = UUID.fromString("7611c3b7-5bb8-4597-849b-c75788f8cc9b");
		AttributeModifier mod = new AttributeModifier(uuidForMod, "lightningRodModifier", 5.0, 0);
		EntityPlayer player = (EntityPlayer) entity;
		
		player.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(mod);
		
		if (world.isThundering())
		{
			player.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(mod);
			if (player.ticksExisted % 20 == 0) stack.damageItem(1, player);
		}
	}
}
