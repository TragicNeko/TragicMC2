package tragicneko.tragicmc.items.food;

import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotions;
import net.minecraft.item.ItemFood;

public class ItemSkyFruit extends ItemFood {

	public ItemSkyFruit(int p_i45340_1_, boolean p_i45340_2_) {
		super(p_i45340_1_, p_i45340_2_);
		if (TragicNewConfig.allowFlight)
		{
			this.setPotionEffect(TragicPotions.Flight.id, 120, 0, 1.0F);
		}
		this.setAlwaysEdible();
	}

}
