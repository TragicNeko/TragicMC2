package tragicneko.tragicmc.items.food;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.WorldHelper;

public class ItemExoticFruit extends ItemFood {

	public ItemExoticFruit(int p_i45340_1_, boolean p_i45340_2_) {
		super(p_i45340_1_, p_i45340_2_);
		this.setPotionEffect(Potion.regeneration.id, 4, 6, 1.0F);
		this.setAlwaysEdible();
		this.setPotionEffect(PotionHelper.ghastTearEffect);
	}

	public IIcon getFruitIcon()
	{
		return this.itemIcon;
	}
}
