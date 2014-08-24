package tragicneko.tragicmc.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class DamageHelper {
	
	//These may get changed to actually extend EntityDamageSource

	/**
	 * Should be used to inflict suffocation damage on entities
	 * @param entity
	 * @return
	 */
	public static DamageSource causeSuffocationDamageFromMob(EntityLivingBase entity)
	{
		EntityDamageSource source = (EntityDamageSource) new EntityDamageSource("suffocation", entity).setDamageBypassesArmor().setDamageIsAbsolute();
		return source;
	}
	
	/**
	 * Should be used for inflicting magic damage instead of using vanilla magic
	 * @param entity
	 * @return
	 */
	public static DamageSource causeModMagicDamageToEntity(EntityLivingBase entity)
	{
		EntityDamageSource source = (EntityDamageSource) new EntityDamageSource("modMagic", entity).setDamageBypassesArmor().setDamageIsAbsolute().setMagicDamage();
		return source;
	}
	
	/**
	 * Should be used to inflict damage from things that should be armor piercing, such as various Doomsday attacks
	 * @param entity
	 * @return
	 */
	public static DamageSource causeArmorPiercingDamageToEntity(EntityLivingBase entity)
	{
		EntityDamageSource source = (EntityDamageSource) new EntityDamageSource("armorPiercing", entity);
		return source;
	}
}
