package tragicneko.tragicmc;

import tragicneko.tragicmc.properties.PropertyMisc;
import tragicneko.tragicmc.util.DamageHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

public class PotionBleed extends TragicPotion {

	public PotionBleed(int par1, boolean par2, int par3) {
		super(par1, par2, par3);
	}

	@Override
	public void performEffect(EntityLivingBase entity, int amp)
	{
		float bleedOut = 0F;
		PropertyMisc misc = PropertyMisc.get(entity);
		if (misc != null) bleedOut = misc.bleedOutTime / 120F;
		entity.attackEntityFrom(DamageHelper.bleed, 1.0F + bleedOut);
		TragicMC.logInfo("Bleed out time is " + misc.bleedOutTime + ", damage is " + (bleedOut + 1.0F));
	}
	
	@Override
	public boolean isReady(int dur, int amp)
	{
		int k = 40 >> MathHelper.floor_double(amp / 2);
		return k > 0 ? dur % k == 0 : true;
	}
}
