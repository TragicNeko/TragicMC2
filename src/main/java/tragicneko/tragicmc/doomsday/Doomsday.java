package tragicneko.tragicmc.doomsday;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;

public abstract class Doomsday {

	protected static final Random rand = new Random();

	public static final Doomsday[] doomsdayList = new Doomsday[64];

	public static final Doomsday Decay = (new DoomsdayDecay(1));
	public static final Doomsday HuntersInstinct = (new DoomsdayHuntersInstinct(2));
	public static final Doomsday Toxicity = (new DoomsdayToxicity(3));
	public static final Doomsday Berserker = (new DoomsdayBerserker(4));
	public static final Doomsday PiercingLight = (new DoomsdayPiercingLight(5));
	public static final Doomsday NatureDrain = (new DoomsdayNatureDrain(6));
	public static final Doomsday PoisonBreak = (new DoomsdayPoisonBreak(7));
	public static final Doomsday Snipe = (new DoomsdaySnipe(8));
	public static final Doomsday RapidFire = (new DoomsdayRapidFire(9));
	public static final Doomsday Pulse = (new DoomsdayPulse(10));
	public static final Doomsday LightShove = (new DoomsdayLightShove(11));
	public static final Doomsday Fear = (new DoomsdayFear(12));
	public static final Doomsday Harmonizer = (new DoomsdayHarmonizer(13));
	public static final Doomsday Ravage = (new DoomsdayRavage(14));
	public static final Doomsday Torment = (new DoomsdayTorment(15));
	public static final Doomsday BeastlyImpulses = (new DoomsdayBeastlyImpulses(16));
	public static final Doomsday SuicidalTendencies = (new DoomsdaySuicidalTendencies(17));
	public static final Doomsday ReaperLaugh = (new DoomsdayReaperLaugh(18));
	public static final Doomsday RealityAlter = (new DoomsdayRealityAlter(19));
	public static final Doomsday SkullCrusher = (new DoomsdaySkullCrusher(20));
	public static final Doomsday MinerSkills = (new DoomsdayMinerSkills(21));
	public static final Doomsday Freeze = (new DoomsdayFreeze(22));
	public static final Doomsday MoonlightSonata = (new DoomsdayMoonlightSonata(23));
	public static final Doomsday FlightOfTheValkyries = (new DoomsdayFlightOfTheValkyries(24));
	public static final Doomsday Titanfall = (new DoomsdayTitanfall(25));
	public static final Doomsday Bloodlust = (new DoomsdayBloodlust(26));
	public static final Doomsday Permafrost = (new DoomsdayPermafrost(27));
	public static final Doomsday Purge = (new DoomsdayPurge(28));
	public static final Doomsday LightningRush = (new DoomsdayLightningRush(29));
	public static final Doomsday Marionette = (new DoomsdayMarionette(30));
	public static final Doomsday Mindcrack = (new DoomsdayMindcrack(31));
	public static final Doomsday GrowthSpurt = (new DoomsdayGrowthSpurt(32));
	public static final Doomsday Blizzard = (new DoomsdayBlizzard(33));
	public static final Doomsday Asphyxiate = (new DoomsdayAsphyxiate(34));
	public static final Doomsday FireRain = (new DoomsdayFireRain(35));
	public static final Doomsday DragonsRoar = (new DoomsdayDragonsRoar(36));
	public static final Doomsday Firestorm = (new DoomsdayFirestorm(37));
	public static final Doomsday Shotgun = (new DoomsdayShotgun(38));
	public static final Doomsday GuardiansCall = (new DoomsdayGuardian(39));
	public static final Doomsday Harden = (new DoomsdayHarden(40));
	public static final Doomsday Sharpen = (new DoomsdaySharpen(41));

	public static final String[] doomsdayNames = new String[] {"null", "decay", "huntersInstinct", "toxicity", "berserker", "piercingLight", "natureDrain", "poisonBreak",
		"snipe", "rapidFire", "pulse", "lightShove", "fear", "harmonizer", "ravage", "torment", "beastlyImpulses", "suicidalTendencies", "reaperLaugh", "realityAlter",
		"skullCrusher", "minerSkills", "freeze", "moonlightSonata", "flightOfTheValkyries", "titanfall", "bloodlust", "permafrost", "purge", "lightningCrush", "marionette",
		"mindcrack", "growthSpurt", "blizzard", "asphyxiate", "fireRain", "dragonsRoar", "firestorm", "shotgun", "guardiansCall", "harden", "sharpen"
	};

	public static final Map<String, Integer> stringToIDMapping = new HashMap();

	public final int doomID;
	public final EnumDoomType doomsdayType;
	public final int requiredDoom;
	public final int cooldown;

	public int waitTime;
	public int maxIterations;

	public Doomsday(int id)
	{
		this(id, EnumDoomType.INFLUENCE);
	}

	public Doomsday(int id, EnumDoomType doomType)
	{
		this.doomsdayType = doomType;
		this.doomID = id;
		this.cooldown = TragicConfig.doomsdayCooldowns[id];
		this.requiredDoom = TragicConfig.doomsdayCosts[id];
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
	public int getScaledDoomRequirement(EnumDifficulty dif)
	{
		int reqDoom = this.requiredDoom;

		if (dif == EnumDifficulty.PEACEFUL)
		{
			return 1;
		}

		if (dif == EnumDifficulty.EASY)
		{
			return reqDoom / 2;
		}

		if (dif == EnumDifficulty.HARD)
		{
			return reqDoom;
		}

		return reqDoom * 2 / 3;
	}
	
	public int getScaledDoomRequirement(World world)
	{
		return getScaledDoomRequirement(world.difficultySetting);
	}
	
	public int getScaledDoomRequirement(PropertyDoom doom)
	{
		return getScaledDoomRequirement(doom.getPlayer().worldObj);
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
		else if (!TragicConfig.allowDoom)
		{
			doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You have Doom disabled... this shouldn't have been called, report this"));
			return;
		}
		else if (TragicConfig.allowStun && doom.getPlayer().isPotionActive(TragicPotion.Stun) || TragicConfig.allowHarmony &&
				doom.getPlayer().isPotionActive(TragicPotion.Harmony) || TragicConfig.allowFear && doom.getPlayer().isPotionActive(TragicPotion.Fear))
		{
			doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You can't use a Doomsday with that effect active..."));
			return;
		}
		else if (!TragicConfig.doomsdayAllow[this.doomID])
		{
			doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You have that particular Doomsday disabled, enable in config."));
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
		int backlash = this.getScaledBacklash(TragicConfig.backlashChance, player, this.doomsdayType);

		if (rand.nextInt(100) <= backlash && TragicConfig.allowBacklash)
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

	public abstract void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment);

	/**
	 * This is the main method each doomsday will override to do it's unique abilities
	 * @param effect
	 * @param doom
	 * @param player
	 * @param crucMoment
	 * @param griefCheck
	 */
	public abstract void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment);

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
	
	public void applyDoomCost(PropertyDoom doom)
	{
		doom.increaseDoom(-this.getScaledDoomRequirement(doom));
	}
	
	public void applyCooldown(PropertyDoom doom, int iterations)
	{
		doom.increaseCooldown(this.getScaledCooldown(doom.getPlayer().worldObj.difficultySetting) * iterations);
	}
	
	public void applyCooldown(PropertyDoom doom, int iterations, int inheritence)
	{
		this.applyCooldown(doom, iterations);
		doom.increaseCooldown(inheritence);
	}
	
	/**
	 * Backlash chance scales based on difficulty, with higher difficulty having a higher chance to backlash, crisis and overflow doomsdays that reduce the
	 * chance for backlash based on their respective amounts should be done individually
	 * @param backlashChance
	 * @param player
	 * @return
	 */
	protected int getScaledBacklash(int backlashChance, EntityPlayer player, EnumDoomType doomType) {
		EnumDifficulty dif = player.worldObj.difficultySetting;
		int x = 1;

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
			return backlashChance * 4 * x;
		}

		if (dif == EnumDifficulty.EASY)
		{
			return backlashChance * 2 * x;
		}

		if (dif == EnumDifficulty.HARD)
		{
			return backlashChance / 2 * x;
		}

		return backlashChance * x;
	}


	/**
	 * Returns name of the current doomsday
	 * @return
	 */
	public String getUnlocalizedName()
	{
		int a = this.doomID;

		String s = null;

		if (a < Doomsday.doomsdayNames.length && a > 0)
		{
			s = Doomsday.doomsdayNames[a];
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
	private int getOverflowAmount(PropertyDoom doom) 
	{
		int currentDoom = doom.getCurrentDoom();
		int reqDoom = this.getScaledDoomRequirement(doom);

		return currentDoom - reqDoom;
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
	public static EnumDoomType getDoomsdayTypeFromId(int id)
	{
		return doomsdayList[id].doomsdayType;
	}

	/**
	 * This returns the scaled cooldown, which should only be changed by difficulty and crisis/overflow doomsday types
	 * @param cooldown2
	 * @return
	 */
	public int getScaledCooldown(EnumDifficulty dif) {
		if (dif == EnumDifficulty.HARD)
		{
			return this.cooldown;
		}

		if (dif == EnumDifficulty.NORMAL)
		{
			return this.cooldown * 2 / 3;
		}

		if (dif == EnumDifficulty.EASY)
		{
			return this.cooldown / 2;
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
		return doom.getCurrentDoom() >= this.getScaledDoomRequirement(doom) * iterations;
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

	public int getOverflow(PropertyDoom doom)
	{
		return doom.getMaxDoom() - doom.getCurrentDoom();
	}

	public float getCrisis(EntityPlayer player)
	{
		return player.getHealth() / player.getMaxHealth();
	}
	
	/**
	 * Extended Doomsdays return the instant one they combine with, Instant ones return the combination that they form
	 * @return
	 */
	public Doomsday getCombination() {
		return null;
	}

	public enum EnumDoomType
	{
		INFLUENCE,
		OVERFLOW(EnumChatFormatting.GREEN),
		CRISIS(EnumChatFormatting.RED),
		WORLDSHAPER(EnumChatFormatting.DARK_PURPLE),
		COMBINATION(EnumChatFormatting.YELLOW);
		
		private final EnumChatFormatting format;
		
		private EnumDoomType()
		{
			this(EnumChatFormatting.AQUA);
		}
		
		private EnumDoomType(EnumChatFormatting format)
		{
			this.format = format;
		}
		
		public EnumChatFormatting getFormat() { return this.format; }
	}
	
	public interface IExtendedDoomsday {} //Marker interface

	static
	{		
		for (int i = 0; i < doomsdayNames.length; i++)
		{
			stringToIDMapping.put(doomsdayNames[i], i);
		}
	}
}
