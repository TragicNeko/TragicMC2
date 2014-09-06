package tragicneko.tragicmc.items.special;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHydration extends ItemTalisman {

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (world.isRemote || !(entity instanceof EntityPlayer)) return;
		
		UUID uuidForMod = UUID.fromString("a0de9d5c-2fa2-4042-8261-f68bec735e56");
		AttributeModifier mod = new AttributeModifier(uuidForMod, "hydrationModifier", 1.0, 0);
		EntityPlayer player = (EntityPlayer) entity;
		
		player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).removeModifier(mod);
		
		if (world.isRaining())
		{
			player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(mod);
			if (player.ticksExisted % 20 == 0) stack.damageItem(1, player);
		}
	}
}
