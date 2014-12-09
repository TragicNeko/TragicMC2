package tragicneko.tragicmc.items.food;

import net.minecraft.item.ItemFood;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotions;

public class ItemGooeyFruit extends ItemFood {

	public ItemGooeyFruit(int p_i45340_1_, boolean p_i45340_2_) {
		super(p_i45340_1_, p_i45340_2_);
		if (TragicNewConfig.allowResurrection)
		{
			this.setPotionEffect(TragicPotions.Resurrection.id, MathHelper.getRandomIntegerInRange(this.itemRand, 30, 90), itemRand.nextInt(4), 1.0F);
		}
		this.setAlwaysEdible();
	}

}
