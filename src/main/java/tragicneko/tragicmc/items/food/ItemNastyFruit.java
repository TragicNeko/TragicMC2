package tragicneko.tragicmc.items.food;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.util.WorldHelper;

public class ItemNastyFruit extends ItemFood {
	

	public ItemNastyFruit(int p_i45340_1_, boolean p_i45340_2_) {
		super(p_i45340_1_, p_i45340_2_);
		if (TragicConfig.allowImmunity) this.setPotionEffect(TragicPotion.Immunity.id, 120, 0, 1.0F);
		this.setAlwaysEdible();
		this.setPotionEffect(PotionHelper.spiderEyeEffect);
	}

	public IIcon getFruitIcon()
	{
		return this.itemIcon;
	}
}
