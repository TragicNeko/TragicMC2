package tragicneko.tragicmc.doomsday;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public abstract class Doomsday {

	protected final Random rand = TragicMC.rand;
	
	public static final Doomsday[] doomsdayList = new Doomsday[36];

	public static final Doomsday Decay = (new DoomsdayDecay(1, 20, 40));
	public static final Doomsday HuntersInstinct = (new DoomsdayHuntersInstinct(2, 25, 60));
	public static final Doomsday Toxicity = (new DoomsdayToxicity(3, 10, 40));
	public static final Doomsday Berserker = (new DoomsdayBerserker(4, 20, 50));
	public static final Doomsday PiercingLight = (new DoomsdayPiercingLight(5, 30, 60));
	public static final Doomsday NatureDrain = (new DoomsdayNatureDrain(6, 45, 65));
	public static final Doomsday PoisonBreak = (new DoomsdayPoisonBreak(7, 15, 30));
	public static final Doomsday Snipe = (new DoomsdaySnipe(8, 55, 80));
	public static final Doomsday RapidFire = (new DoomsdayRapidFire(9, 3, 8));
	public static final Doomsday Pulse = (new DoomsdayPulse(10, 6, 10));
	public static final Doomsday LightShove = (new DoomsdayLightShove(11, 1, 5));
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
	public static final Doomsday MoonlightSonata = (new DoomsdayMoonlightSonata(23, 40, 1));
	
	/*
	public static final Doomsday Titanfall = (new Doomsday(24, 20, 80, EnumDoomType.OVERFLOW)); //Creates waves of lightning, The Titan
	public static final Doomsday Bloodlust = (new Doomsday(25, 45, 90, EnumDoomType.OVERFLOW)); //Gives you an insane amount of buffs/debuffs, The Butcher
	public static final Doomsday Permafrost = (new Doomsday(26, 25, 100, EnumDoomType.WORLDSHAPER)); //Freezes any liquid nearby and creates snow, also kills crops/grass
	//damages any entities nearby that are immune to fire, The Thardus
	public static final Doomsday MindTorture = (new Doomsday(27, 55, 130, EnumDoomType.CRISIS)); //Completely stops the motion of all nearby entities (sets their motion in all directions to 0 for
	//the entirety of it's effect and prevents them from being able to attack, they also take way more damage (submission), they then get attacked from random sides by a projectile
	//every few seconds, The Paranoia
	public static final Doomsday Asphyxiate = (new Doomsday(28, 65, 110, EnumDoomType.OVERFLOW)); //Allows you control over the closest entity to you (like the Gravity Gun), I'll try to do this
	// by getting a ray trace from the player every couple ticks and setting the entity's position to that spot, The Splinter
	public static final Doomsday FireRain = (new Doomsday(29, 40, 95, EnumDoomType.CRISIS)); //Spawns waves of fireballs above the player that rain down around them, The Dragon Fang
	public static final Doomsday DragonRoar = (new Doomsday(30, 10, 50)); //allows you flight and also gives invulnerability for a short period of time, also stuns nearby
	//enemies (if any are nearby) for a few seconds, Draconic Armor
	Set up another Doomsday to use this
	//spins enemies all around you in a huge spiral, while shooting multiple
	//random projectiles at them, every time you do damage, you heal yourself and you also gain a huge amount of positive potion effects, can also only be used at night and,
	//when the player can see the moon in the sky, Celestial Aegis
	public static final Doomsday Isolation = (new Doomsday(31, 25, 125, EnumDoomType.WORLDSHAPER)); //knocks away all enemies near you and inflicts a huge amount of damage
	//to them, also throws a bunch of blocks near you up into the air and out away from you, causes major terrain damage, Tragic Armor
	public static final Doomsday JudgmentDay = (new Doomsday(32, 160, 200, EnumDoomType.ULTIMATE)); //teleports all nearby entities into the air above you and damages them
	//multiple times with magic damage, for every hit you heal yourself, they also get struck by lightning randomly and will take huge amounts of damage the more health 
	//that they have, potentionally doing hundreds of points of damage to boss mobs, Tragic Hellraiser
	public static final Doomsday ParadigmShift = (new Doomsday(33, 55, 100, EnumDoomType.ULTIMATE)); //Activates a random doomsday effect to it's highest ability (maximum
	//crisis/overflow amounts as well as maximum amount of bursts/waves if it has them)
	public static final Doomsday Harden = (new Doomsday(34, 35, 30)); //gives you resistance 10 for a short amount of time or until hit, along with slowness 1, Diamond Armor
	public static final Doomsday DiamondCut = (new Doomsday(35, 35, 30)); //gives you an attack buff that lasts for one hit or a few seconds, whichever occurs first, Diamond Sword
	*/

	public static final String[] doomsdayNames = new String[] {"null", "decay", "huntersInstinct", "toxicity", "berserker", "piercingLight", "natureDrain", "poisonBreak",
		"snipe", "rapidFire", "pulse", "lightShove", "fear", "harmonizer", "ravage", "torment", "beastlyImpulses", "suicidalTendencies", "reaperLaugh", "realityAlter",
		"skullCrusher", "minerSkills", "freeze", "moonlightSonata" //, "titanfall", "bloodlust", "permafrost", "mindCrush", "asphyxiate", "fireRain", "dragonRoar", "isolation",
		//"judgmentDay", "paradigmShift", "harden", "diamondCut"
		};
	
	public static final Map<String, Integer> stringToIDMapping = new HashMap();

	public final byte doomID;
	public final EnumDoomType doomsdayType;
	public final short requiredDoom;
	public final short cooldown;

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
		doomsdayList[id] = this;
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
			return (short) (reqDoom / 2);
		}

		if (dif == EnumDifficulty.EASY)
		{
			return (short) ((reqDoom * 2) / 3);
		}

		if (dif == EnumDifficulty.HARD)
		{
			return (short) (reqDoom);
		}

		return reqDoom;
	}

	/**
	 * Method to activate the doomsday, does a check for the required doom and cooldown check, then passes off to the doDoomsday() method
	 * @param doom
	 */
	public void activateDoomsday(PropertyDoom doom)
	{
		if (doom != null && this.doesCurrentDoomMeetRequirement(doom) && doom.getCurrentCooldown() == 0 && TragicNewConfig.allowDoom && !doom.getPlayer().isDead)
		{
			if (TragicNewConfig.allowStun && doom.getPlayer().isPotionActive(TragicPotions.Stun) || TragicNewConfig.allowHarmony &&
					doom.getPlayer().isPotionActive(TragicPotions.Harmony))
			{
				doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You can't use a Doomsday with that effect active..."));
			}
			else
			{
				this.doDoomsday(doom, doom.getPlayer(), doom.getPlayer().posX, doom.getPlayer().posY, doom.getPlayer().posZ);
			}
		}
		else
		{
			if (doom.getCurrentCooldown() != 0)
			{
				doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You still have cooldown."));
			}
			else if (!this.doesCurrentDoomMeetRequirement(doom))
			{
				doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Not enough doom for that Doomsday..."));
			}
			else if (doom.getPlayer().isDead)
			{
				doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You are dead and cannot use Doomsdays."));
			}
			else if (!TragicNewConfig.allowDoom)
			{
				doom.getPlayer().addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You have Doom disabled... this shouldn't have been called, report this"));
			}
		}
	}
	
	/**
	 * Does pre-requisite checks that most doomsdays need like crucial moment chance and mob griefing checks, then hands off to the main method for them to be actually done
	 * @param doom
	 * @param player
	 * @param posX
	 * @param posY
	 * @param posZ
	 */
	public void doDoomsday(PropertyDoom doom, EntityPlayer player, double posX, double posY, double posZ)
	{
		short backlash = (short) this.getScaledBacklash(TragicNewConfig.backlashChance, player, this.doomsdayType);
		
		boolean crucMoment = false;
		
		if (TragicNewConfig.allowCrucialMoments)
		{
			if (rand.nextInt(100) <= TragicNewConfig.crucialMomentChance)
			{
				crucMoment = true;
			}
		}
		
		boolean griefCheck = this.getMobGriefing(player.worldObj);
		
		if (rand.nextInt(100) >= TragicNewConfig.backlashChance)
		{
			this.useDoomsday(doom, player, crucMoment, griefCheck);
		}
		else
		{
			if (!player.capabilities.isCreativeMode)
			{
				this.doBacklashEffect(doom, player, griefCheck);
			}
		}
	}
	
	/**
	 * This is the main method each doomsday will override to do it's unique abilities
	 * @param doom
	 * @param player
	 * @param crucMoment
	 * @param griefCheck
	 */
	public abstract void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck);
	
	/**
	 * This method will be the same as the main method, leaving out any cooldown application or doom requirement checks
	 */
	public abstract void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck);
	
	/**
	 * This applies the backlash effect, will be overriden by each Doomsday to have their own unique effects
	 * @param doom
	 * @param player
	 * @param griefCheck
	 */
	public abstract void doBacklashEffect(PropertyDoom doom, EntityPlayer player, boolean griefCheck);
	
	/**
	 * If the effect backlashed, this method is called, does the backlash effect then applies cooldown, removes half of normal doom cost as well
	 * @param doom
	 * @param player
	 * @param posX
	 * @param posY
	 * @param posZ
	 */
	@Deprecated
	protected void applyBacklashEffect(PropertyDoom doom, EntityPlayer player, double posX, double posY, double posZ)
	{
		switch(this.doomID)
		{
		case 1:
			player.addPotionEffect(new PotionEffect(Potion.hunger.id, rand.nextInt(240) + 60, rand.nextInt(2)));

			if (rand.nextInt(3) == 0)
			{
				player.addPotionEffect(new PotionEffect(Potion.wither.id, rand.nextInt(120) + 60, rand.nextInt(2)));
			}
			break;
		case 3:
			player.addPotionEffect(new PotionEffect(Potion.poison.id, rand.nextInt(120) + 60, rand.nextInt(2)));

			if (rand.nextInt(3) == 0 && TragicNewConfig.allowStun)
			{
				player.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, rand.nextInt(60) + 40));
			}
			break;
		case 4:
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 60, 1));
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 60, 1));
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 240));

			if (TragicNewConfig.allowSubmission)
			{
				player.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 240, 4));
			}
			break;
		case 5:
			player.setFire(rand.nextInt(8) + 4);
			break;
		case 6:
			for (int x = 0; x < rand.nextInt(8); x++)
			{
				player.addExhaustion(5.0F);
			}
			break;
		case 7:
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == TragicItems.MercuryDagger)
			{
				player.destroyCurrentEquippedItem();
				player.renderBrokenItemStack(player.getCurrentEquippedItem());
			}
			break;
		case 8:
			if (player.inventory.hasItem(Items.arrow))
			{
				player.inventory.consumeInventoryItem(Items.arrow);
			}
			break;
		case 9:
			for (int j = 0; j < 16; j++)
			{
				if (player.inventory.hasItem(Items.arrow))
				{
					player.inventory.consumeInventoryItem(Items.arrow);
				}
			}

			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == TragicItems.HuntersBow)
			{
				player.getCurrentEquippedItem().damageItem(1, player);
			}
			break;
		case 10:
			player.motionY += 0.8;
			break;
		case 11:
			break;
		case 12:
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 300));
			if (TragicNewConfig.allowDisorientation)
			{
				player.addPotionEffect(new PotionEffect(TragicPotions.Disorientation.id, 300));
			}
			break;
		case 13:
			break;
		case 14:
			player.worldObj.createExplosion(null, player.posX, player.posY, player.posZ, rand.nextFloat() * 2.0F, player.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
			break;
		case 15:
			player.addPotionEffect(new PotionEffect(Potion.poison.id, 200, 4));

			if (TragicNewConfig.allowStun)
			{
				player.addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 100, 1));
			}

			if (TragicNewConfig.allowSubmission)
			{
				player.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 200, 2));
			}
			break;
		case 16:
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 4));
			break;
		case 17:
			for (int i = 0; i < 3; i++)
			{
				player.worldObj.spawnEntityInWorld(new EntityNekoClusterBomb(player.worldObj));
			}
			break;
		case 18:
			for (int i = 0; i < 3 + rand.nextInt(3); i++)
			{
				EntitySkeleton skelly = new EntitySkeleton(player.worldObj);
				skelly.onSpawnWithEgg((IEntityLivingData)null);
				skelly.setSkeletonType(1);
				skelly.setPosition(player.posX + rand.nextInt(6), player.posY, player.posZ + rand.nextInt(6));

				if (player.worldObj.checkNoEntityCollision(skelly.boundingBox))
				{
					player.worldObj.spawnEntityInWorld(skelly);
					skelly.setAttackTarget(player);
				}
			}
			break;
		case 19:
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 600));
			break;
		case 20:
			player.addPotionEffect(new PotionEffect(Potion.wither.id, 250, 2));
			break;
		}

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "The Doomsday backlashed..."));
		doom.increaseDoom(-(this.getScaledDoomRequirement(doom) / 3));
		doom.setCooldown(this.getScaledCooldown(player.worldObj.difficultySetting) / 2);
	}

	/**
	 * Applies scaled doom and cooldown
	 * @param doom
	 */
	public void applyDoomAndCooldown(PropertyDoom doom)
	{
		doom.decreaseDoomAmountAndApplyCooldown(this.getScaledDoomRequirement(doom), this.getScaledCooldown(doom.getPlayer().worldObj.difficultySetting));
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
		
		case ULTIMATE:
			x = 4;
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
		case ULTIMATE:
			s = "ultimate";
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
		return cooldown;
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

	public enum EnumDoomType
	{
		INFLUENCE,
		OVERFLOW,
		CRISIS,
		WORLDSHAPER,
		ULTIMATE;
	}
	
	static
	{		
		for (int i = 0; i < doomsdayNames.length; i++)
		{
			stringToIDMapping.put(doomsdayNames[i], i);
		}
	}

}
