package tragicneko.tragicmc.items.food;

import net.minecraft.item.ItemFood;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;

public class ItemNastyFruit extends ItemFood {

	public ItemNastyFruit(int p_i45340_1_, boolean p_i45340_2_) {
		super(p_i45340_1_, p_i45340_2_);
		if (TragicNewConfig.allowImmunity)
		{
			this.setPotionEffect(TragicPotions.Immunity.id, 120, 0, 1.0F);
		}
		this.setAlwaysEdible();
	}

}
