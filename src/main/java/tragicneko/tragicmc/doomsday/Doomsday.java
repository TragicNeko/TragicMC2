package tragicneko.tragicmc.doomsday;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public abstract class Doomsday {

	protected final Random rand = TragicMC.rand;

	public static final Doomsday[] doomsdayList = new Doomsday[48];

	public static final Doomsday Decay = (new DoomsdayDecay(1, 20, 40));
	public static final Doomsday HuntersInstinct = (new DoomsdayHuntersInstinct(2, 25, 60));
	public static final Doomsday Toxicity = (new DoomsdayToxicity(3, 15, 40));
	public static final Doomsday Berserker = (new DoomsdayBerserker(4, 20, 50));
	public static final Doomsday PiercingLight = (new DoomsdayPiercingLight(5, 30, 60));
	public static final Doomsday NatureDrain = (new DoomsdayNatureDrain(6, 6, 12));
	public static final Doomsday PoisonBreak = (new DoomsdayPoisonBreak(7, 10, 30));
	public static final Doomsday Snipe = (new DoomsdaySnipe(8, 55, 90));
	public static final Doomsday RapidFire = (new DoomsdayRapidFire(9, 3, 8));
	public static final Doomsday Pulse = (new DoomsdayPulse(10, 6, 10));
	public static final Doomsday LightShove = (new DoomsdayLightShove(11, 1, 3));
	public static final Doomsday Fear = (new DoomsdayFear(12, 20, 30));
	public static final Doomsday Harmonizer = (new DoomsdayHarmonizer(13, 30, 40));
	public static final Doomsday Ravage = (new DoomsdayRavage(14, 35, 55));
	public static final Doomsday Torment = (new DoomsdayTorment(15, 20, 45));
	public static final Doomsday BeastlyImpulses = (new DoomsdayBeastlyImpulses(16, 50, 60));
	public static final Doomsday SuicidalTendencies = (new DoomsdaySuicidalTendencies(17, 4, 20));
	public static final Doomsday ReaperLaugh = (new DoomsdayReaperLaugh(18, 3, 16));
	public static final Doomsday RealityAlter = (new DoomsdayRealityAlter(19, 12, 40));
	public static final Doomsday SkullCrusher = (new DoomsdaySkullCrusher(20, 15, 50));
	public static final Doomsday MinerSkills = (new DoomsdayMinerSkills(21, 20, 30));
	public static final Doomsday Freeze = (new DoomsdayFreeze(22, 30, 30));
	public static final Doomsday MoonlightSonata = (new DoomsdayMoonlightSonata(23, 60, 1));
	public static final Doomsday FlightOfTheValkyries = (new DoomsdayFlightOfTheValkyries(24, 10, 10));
	public static final Doomsday Titanfall = (new DoomsdayTitanfall(25, 10, 5));
	public static final Doomsday Bloodlust = (new DoomsdayBloodlust(26, 30, 80));
	public static final Doomsday Permafrost = (new DoomsdayPermafrost(27, 5, 6));
	public static final Doomsday Purge = (new DoomsdayPurge(28, 4, 5));
	public static final Doomsday LightningRush = (new DoomsdayLightningRush(29, 6, 8));
	public static final Doomsday Marionette = (new DoomsdayMarionette(30, 3, 3));
	public static final Doomsday Mindcrack = (new DoomsdayMindcrack(31, 60, 45));
	public static final Doomsday GrowthSpurt = (new DoomsdayGrowthSpurt(32, 60, 50));
	public static final Doomsday Blizzard = (new DoomsdayBlizzard(33, 6, 10));
	public static final Doomsday Asphyxiate = (new DoomsdayAsphyxiate(34, 3, 3));
	public static final Doomsday FireRain = (new DoomsdayFireRain(35, 5, 8));
	public static final Doomsday DragonsRoar = (new DoomsdayDragonsRoar(36, 15, 25));
	public static final Doomsday Firestorm = (new DoomsdayFirestorm(37, 8, 10));
	public static final Doomsday Shotgun = (new DoomsdayShotgun(38, 5, 10));

	/*
	public static final Doomsday Isolation = (new Doomsday(31, 25, 125, EnumDoomType.WORLDSHAPER)); //knocks away all enemies near you and inflicts a huge amount of damage
	//to them, also throws a bunch of blocks near you up into the air and out away from you, causes major terrain damage, Tragic Armor
	public static final Doomsday JudgmentDay = (new Doomsday(32, 160, 200, EnumDoomType.COMBINATION)); //teleports all nearby entities into the air above you and damages them
	//multiple times with magic damage, for every hit you heal yourself, they also get hurt by spirit bursts fired from the ground and will take huge amounts of damage the more health 
	//that they have, potentionally doing hundreds of points of damage to boss mobs, Tragic Hellraiser
	public static final Doomsday ParadigmShift = (new Doomsday(33, 55, 100, EnumDoomType.ULTIMATE)); //Activates a random doomsday
	public static final Doomsday Harden = (new Doomsday(34, 35, 30)); //gives you resistance 10 for a very short amount of time
	public static final Doomsday Sharpen = (new Doomsday(35, 35, 30)); //gives you strength 10 for a very short amount of time
	public static final Doomsday DeathMark = (new Doomsday(44, 6, 4)); //inflicts a mob nearby with submission 10, damage that mob with magic bursts over the duration of it
	 */

	public static final String[] doomsdayNames = new String[] {"null", "decay", "huntersInstinct", "toxicity", "berserker", "piercingLight", "natureDrain", "poisonBreak",
		"snipe", "rapidFire", "pulse", "lightShove", "fear", "harmonizer", "ravage", "torment", "beastlyImpulses", "suicidalTendencies", "reaperLaugh", "realityAlter",
		"skullCrusher", "minerSkills", "freeze", "moonlightSonata", "flightOfTheValkyries", "titanfall", "bloodlust", "permafrost", "purge", "lightningCrush", "marionette",
		"mindcrack", "growthSpurt", "blizzard", "asphyxiate", "fireRain", "dragonsRoar", "firestorm", "shotgun", //"isolation", "judgmentDay", "paradigmShift", "harden", "diamondCut",
		//"deathMark", "lateralus"
	};

	public static final Map<String, Integer> stringToIDMapping = new HashMap();

	public final byte doomID;
	public final EnumDoomType doomsdayType;
	public final short requiredDoom;
	public final short cooldown;

	public int waitTime;
	public int maxIterations;

	public Doomsday(int id, int cd, int reqDoom)
	{
		this(id, cd, reqDoom, EnumDoomType.INFLUENCE);
	}

	public Doomsday(int id, int cd, int reqDoom, EnumDoomType doomType)
	{
		this.doomsdayType = doomType;
		this.doomID = (byte)id;
		this.cooldown = (short) cd;
		this.requiredDoom = (short) reqDoom;
		this.waitTime = 0;
		this.maxIterations = 1;
		doomsdayList[id] = this;
	}

	public int getWaitTime()
	{
		return this.waitTime;
	}

	public int getMaxIterations()
	{
		return this.maxIterations;
	}

	public EnumDoomType getDoomsdayType()
	{
		return this.doomsdayType;
	}

	/**
	 * Returns the scaled doom requirement based on difficulty setting
	 * @param doom
	 * @return
	 */
	public short getScaledDoomRequirement(PropertyDoom doom)
	{
		EnumDifficulty dif = doom.getPlayer().worldObj.difficultySetting;
		Short reqDoom = this.requiredDoom;

		if (dif == EnumDifficulty.PEACEFUL)
		{
			return (short) (1);
		}

		if (dif == EnumDifficulty.EASY)
		{
			return (short) (reqDoom / 2);
		}

		if (dif == EnumDifficulty.HARD)
		{
			return (short) (reqDoom);
		}

		return (short) (reqDoom * 2 / 3);
	}

	/**
	 * Method to activate the doomsday, does a check for the required doom and cooldown check, then passes off to the doDoomsday() method
	 * @param doom
	 */
	public void activateDoomsday(PropertyDoom doom)
	{
		if (doom == null)
		{
			TragicMC.logError("A doomsday was activated with null doom? This error shouldn't be possible and should be reported.");
			return;
		}
		else if (doom.getCurrentCooldown() != 0)
		{
			doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You still have cooldown."));
			return;
		}
		else if (!this.doesCurrentDoomMeetRequirement(doom))
		{
			doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Not enough doom for that Doomsday..."));
			return;
		}
		else if (doom.getPlayer().isDead)
		{
			doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You are dead and cannot use Doomsdays."));
			return;
		}
		else if (!TragicNewConfig.allowDoom)
		{
			doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You have Doom disabled... this shouldn't have been called, report this"));
			return;
		}
		else if (TragicNewConfig.allowStun && doom.getPlayer().isPotionActive(TragicPotions.Stun) || TragicNewConfig.allowHarmony &&
				doom.getPlayer().isPotionActive(TragicPotions.Harmony) || TragicNewConfig.allowFear && doom.getPlayer().isPotionActive(TragicPotions.Fear))
		{
			doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You can't use a Doomsday with that effect active..."));
			return;
		}
		
		this.doDoomsday(doom, doom.getPlayer());
	}

	/**
	 * Does pre-requisite checks that most doomsdays need like crucial moment and backlash chance, then hands off to the main method for them to be actually done
	 * @param doom
	 * @param player
	 * @param posX
	 * @param posY
	 * @param posZ
	 */
	public void doDoomsday(PropertyDoom doom, EntityPlayer player)
	{
		short backlash = (short) this.getScaledBacklash(TragicNewConfig.backlashChance, player, this.doomsdayType);

		if (rand.nextInt(100) <= backlash && TragicNewConfig.allowBacklash)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Doomsday backlashed..."));

			if (!player.capabilities.isCreativeMode)
			{
				doom.increaseCooldown(this.getScaledCooldown(player.worldObj.difficultySetting) / 3);
				doom.increaseDoom(this.getScaledDoomRequirement(doom) / 3);
				this.doBacklashEffect(doom, player);
				return;
			}
		}

		DoomsdayEffect effect = new DoomsdayEffect(this.doomID, doom);
		DoomsdayManager.registerDoomsdayEffect(player.getCommandSenderName(), effect);
	}

	public abstract void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment);

	/**
	 * This is the main method each doomsday will override to do it's unique abilities
	 * @param doom
	 * @param player
	 * @param crucMoment
	 * @param griefCheck
	 */
	public abstract void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment);

	/**
	 * This applies the backlash effect, will be overriden by each Doomsday to have their own unique effects
	 * @param doom
	 * @param player
	 * @param griefCheck
	 */
	public abstract void doBacklashEffect(PropertyDoom doom, EntityPlayer player);


	/**
	 * Applies scaled doom and cooldown
	 * @param doom
	 */
	public void applyDoomAndCooldown(PropertyDoom doom)
	{
		doom.decreaseDoomAmountAndApplyCooldown(this.getScaledDoomRequirement(doom), this.getScaledCooldown(doom.getPlayer().worldObj.difficultySetting));
	}

	public void applyDoomAndCooldown(PropertyDoom doom, int iterations)
	{
		doom.decreaseDoomAmountAndApplyCooldown(this.getScaledDoomRequirement(doom) * iterations, this.getScaledCooldown(doom.getPlayer().worldObj.difficultySetting) * iterations);
	}

	/**
	 * Backlash chance scales based on difficulty, with higher difficulty having a higher chance to backlash, crisis and overflow doomsdays that reduce the
	 * chance for backlash based on their respective amounts should be done individually
	 * @param backlashChance
	 * @param player
	 * @return
	 */
	protected short getScaledBacklash(int backlashChance, EntityPlayer player, EnumDoomType doomType) {
		EnumDifficulty dif = player.worldObj.difficultySetting;
		short x = 1;

		switch(doomType)
		{
		case INFLUENCE:
			x = 1;
			break;
		case OVERFLOW:
		case WORLDSHAPER:
			x = 2;
			break;
		case CRISIS:
			x = 3;
			break;
		case COMBINATION:
			x = -1;
			break;
		}

		if (dif == EnumDifficulty.PEACEFUL)
		{
			return (short) (backlashChance * 4 * x);
		}

		if (dif == EnumDifficulty.EASY)
		{
			return (short) (backlashChance * 2 * x);
		}

		if (dif == EnumDifficulty.HARD)
		{
			return (short) (backlashChance / 2 * x);
		}

		return (short) (backlashChance * x);
	}


	/**
	 * Returns name of the current doomsday
	 * @return
	 */
	public String getUnlocalizedName()
	{
		byte a = this.doomID;

		String s = null;

		if (a < this.doomsdayNames.length && a > 0)
		{
			s = this.doomsdayNames[a];
		}

		return "doomsday." + s + ".name";
	}

	public String getLocalizedName()
	{
		return StatCollector.translateToLocal(this.getUnlocalizedName());
	}

	public String getLocalizedType()
	{
		return StatCollector.translateToLocal(this.getUnlocalizedType());
	}


	/**
	 * Returns the type of the current doomsday
	 * @return
	 */
	public String getUnlocalizedType()
	{
		EnumDoomType type = this.doomsdayType;
		String s = null;

		switch(type)
		{
		case INFLUENCE:
			s = "influence";
			break;
		case OVERFLOW:
			s = "overflow";
			break;
		case CRISIS:
			s = "crisis";
			break;
		case WORLDSHAPER:
			s = "worldShaper";
			break;
		case COMBINATION:
			s = "combination";
			break;
		}

		return "doomsday." + s + ".type";
	}


	/**
	 * Returns the overflow amount for overflow type doomsdays
	 * @param doom
	 * @return
	 */
	private short getOverflowAmount(PropertyDoom doom) 
	{
		int currentDoom = doom.getCurrentDoom();
		short reqDoom = this.getScaledDoomRequirement(doom);

		return (short) (currentDoom - reqDoom);
	}

	/**
	 * Returns the crisis amount (percentage of total health the player has)
	 * @param player
	 * @return
	 */
	private float getCrisisAmount(EntityPlayer player)
	{
		float health = player.getHealth();
		float maxHealth = player.getMaxHealth();

		return health / maxHealth;
	}

	/**
	 * Gets the doomsday type from the specified id
	 * @param id
	 * @return
	 */
	public static EnumDoomType getDoomsdayTypeFromId(byte id)
	{
		return doomsdayList[id].doomsdayType;
	}

	/**
	 * This returns the scaled cooldown, which should only be changed by difficulty and crisis/overflow doomsday types
	 * @param cooldown2
	 * @return
	 */
	private short getScaledCooldown(EnumDifficulty dif) {
		if (dif == EnumDifficulty.HARD)
		{
			return (byte) (this.cooldown);
		}

		if (dif == EnumDifficulty.NORMAL)
		{
			return (byte) (this.cooldown * 2 / 3);
		}

		if (dif == EnumDifficulty.EASY)
		{
			return (byte) (this.cooldown / 2);
		}
		return 0;
	}

	/**
	 * Checks if current doom amount is enough to do the attempted doomsday
	 * @param doom
	 * @return
	 */
	public boolean doesCurrentDoomMeetRequirement(PropertyDoom doom)
	{
		if (doom.getCurrentDoom() >= this.getScaledDoomRequirement(doom))
		{
			return true;
		}
		return false;
	}

	public boolean doesCurrentDoomMeetRequirement(PropertyDoom doom, int iterations)
	{
		if (doom.getCurrentDoom() >= this.getScaledDoomRequirement(doom) * iterations)
		{
			return true;
		}
		return false;
	}

	public int getDoomId()
	{
		return this.doomID;
	}

	/**
	 * Returns the Doomsday with the matching id, currently only used by the /doomsday command, may return null
	 * @param id
	 * @return
	 */
	public static Doomsday getDoomsdayFromId(int id) {
		return doomsdayList[id];
	}

	/**
	 * Returns a Doomsday with matching string name, may return null
	 * @param s
	 * @return
	 */
	public static Doomsday getDoomsdayFromString(String s)
	{
		s.trim();
		return getDoomsdayFromId(stringToIDMapping.get(s));
	}

	public boolean getMobGriefing(World world)
	{
		return world.getGameRules().getGameRuleBooleanValue("mobGriefing");
	}

	public boolean getMobGriefing(EntityPlayer player)
	{
		return getMobGriefing(player.worldObj);
	}

	public short getOverflow(PropertyDoom doom)
	{
		return (short) (doom.getMaxDoom() - doom.getCurrentDoom());
	}

	public float getCrisis(EntityPlayer player)
	{
		return player.getHealth() / player.getMaxHealth();
	}
	
	public MovingObjectPosition getMOPFromPlayer(EntityPlayer player, double distance)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = distance;

		if (player instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance() + (d3 - 4.0D);
		}
		Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);

		return player.worldObj.func_147447_a(vec3, vec31, true, false, true);
	}
	
	public MovingObjectPosition getMOPFromPlayer(EntityPlayer player)
	{
		return getMOPFromPlayer(player, 50.0D);
	}

	public enum EnumDoomType
	{
		INFLUENCE,
		OVERFLOW,
		CRISIS,
		WORLDSHAPER,
		COMBINATION
	}

	static
	{		
		for (int i = 0; i < doomsdayNames.length; i++)
		{
			stringToIDMapping.put(doomsdayNames[i], i);
		}
	}

}
