package tragicneko.tragicmc.main;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;

public class TragicPotions {

	public static Potion Corruption, Disorientation, Stun, Fear, Cripple, Malnourish, Submission, Inhibit;
	public static Potion Flight, AquaSuperiority, Immunity, Resurrection, Harmony, Invulnerability, Clarity;

	public static void load()
	{
		if (TragicNewConfig.allowCorruption) Corruption = (new TragicPotion(TragicNewConfig.idCorruption, true, 1015154).setPotionName("potion.corruption"));
		if (TragicNewConfig.allowDisorientation) Disorientation = (new TragicPotion(TragicNewConfig.idDisorientation, true, 0).setPotionName("potion.disorientation"));
		if (TragicNewConfig.allowStun) Stun = (new TragicPotion(TragicNewConfig.idStun, true, 1133333).setPotionName("potion.stun").func_111184_a(SharedMonsterAttributes.movementSpeed, "d35fe2b0-2aca-4d5e-b3e3-3fe041dbaf15", -3.0D, 2));
		if (TragicNewConfig.allowFear) Fear = (new TragicPotion(TragicNewConfig.idFear, true, 184891564).setPotionName("potion.fear"));
		if (TragicNewConfig.allowCripple) Cripple = (new TragicPotion(TragicNewConfig.idCripple, true, 9846846).setPotionName("potion.cripple").func_111184_a(SharedMonsterAttributes.maxHealth, "04df3109-9bca-4517-8855-8064742028e4", -2.0D, 0));
		if (TragicNewConfig.allowMalnourish) Malnourish = (new TragicPotion(TragicNewConfig.idMalnourish, true, 498194).setPotionName("potion.malnourish"));
		if (TragicNewConfig.allowSubmission) Submission = (new TragicPotion(TragicNewConfig.idSubmission, true, 1168135).setPotionName("potion.submission"));
		if (TragicNewConfig.allowInhibit) Inhibit = (new TragicPotion(TragicNewConfig.idInhibit, true, 164861891).setPotionName("potion.inhibit"));
		
		if (TragicNewConfig.allowFlight) Flight = (new TragicPotion(TragicNewConfig.idFlight, false, 10000).setPotionName("potion.flight"));
		if (TragicNewConfig.allowAquaSuperiority) AquaSuperiority = (new TragicPotion(TragicNewConfig.idAquaSuperiority, true, 1015154).setPotionName("potion.aquaSuperiority"));
		if (TragicNewConfig.allowImmunity) Immunity = (new TragicPotion(TragicNewConfig.idImmunity, false, 687611).setPotionName("potion.immunity"));
		if (TragicNewConfig.allowResurrection) Resurrection = (new TragicPotion(TragicNewConfig.idResurrection, false, 97516464).setPotionName("potion.resurrection"));
		if (TragicNewConfig.allowHarmony) Harmony = (new TragicPotion(TragicNewConfig.idHarmony, false, 2685972).setPotionName("potion.harmony").func_111184_a(SharedMonsterAttributes.followRange, "ac2b9b0e-2eb2-443f-8ca2-a045f36f9e73", -256.0, 2));
		if (TragicNewConfig.allowInvulnerability) Invulnerability = (new TragicPotion(TragicNewConfig.idInvulnerability, false, 118494).setPotionName("potion.invulnerability").func_111184_a(SharedMonsterAttributes.movementSpeed, "43d74ab6-d058-40e4-9761-70b0b80b8743", -0.2D, 2));
		if (TragicNewConfig.allowClarity) Clarity = (new TragicPotion(TragicNewConfig.idClarity, true, 252976).setPotionName("potion.clarity"));
	}
}
