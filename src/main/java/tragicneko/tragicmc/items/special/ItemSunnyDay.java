package tragicneko.tragicmc.items.special;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSunnyDay extends ItemTalisman {

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (world.isRemote || !(entity instanceof EntityPlayer)) return;
		
		EntityPlayer player = (EntityPlayer) entity;

		if (world.isThundering())
		{
			world.thunderingStrength = 0.0F;
		}
		
		if (world.isRaining())
		{
			world.rainingStrength = 0.0F;
		}
	}
}
