package tragicneko.tragicmc.util;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.items.armor.ArmorDark;
import tragicneko.tragicmc.items.armor.ArmorHunter;
import tragicneko.tragicmc.items.armor.ArmorLight;
import tragicneko.tragicmc.items.armor.ArmorMercury;
import tragicneko.tragicmc.items.armor.ArmorOverlord;
import tragicneko.tragicmc.items.armor.ArmorSkull;
import tragicneko.tragicmc.items.armor.ArmorTungsten;
import tragicneko.tragicmc.items.weapons.WeaponBeastlyClaws;
import tragicneko.tragicmc.items.weapons.WeaponBlindingLight;
import tragicneko.tragicmc.items.weapons.WeaponButcher;
import tragicneko.tragicmc.items.weapons.WeaponCelestialAegis;
import tragicneko.tragicmc.items.weapons.WeaponCelestialLongbow;
import tragicneko.tragicmc.items.weapons.WeaponDragonFang;
import tragicneko.tragicmc.items.weapons.WeaponFrozenLightning;
import tragicneko.tragicmc.items.weapons.WeaponGravitySpike;
import tragicneko.tragicmc.items.weapons.WeaponGuiltyThorn;
import tragicneko.tragicmc.items.weapons.WeaponHarmonyBell;
import tragicneko.tragicmc.items.weapons.WeaponHuntersBow;
import tragicneko.tragicmc.items.weapons.WeaponMourningStar;
import tragicneko.tragicmc.items.weapons.WeaponNekoLauncher;
import tragicneko.tragicmc.items.weapons.WeaponParanoia;
import tragicneko.tragicmc.items.weapons.WeaponPitchBlack;
import tragicneko.tragicmc.items.weapons.WeaponReaperScythe;
import tragicneko.tragicmc.items.weapons.WeaponSentinel;
import tragicneko.tragicmc.items.weapons.WeaponSplinter;
import tragicneko.tragicmc.items.weapons.WeaponThardus;
import tragicneko.tragicmc.items.weapons.WeaponTitan;
import tragicneko.tragicmc.items.weapons.WeaponWitheringAxe;

public class LoreHelper {

	private static Map<Class<? extends Item>, LoreEntry> weaponLores = new HashMap();

	static
	{
		//Armor
		addToLoreMap(ArmorDark.class, new Lore[] {}, new EnchantEntry[][] {{}, {}, {}, {}});
		addToLoreMap(ArmorHunter.class, new Lore[] {}, new EnchantEntry[][] {{}, {}, {}, {}});
		addToLoreMap(ArmorLight.class, new Lore[] {}, new EnchantEntry[][] {{}, {}, {}, {}});
		addToLoreMap(ArmorMercury.class, new Lore[] {}, new EnchantEntry[][] {{}, {}, {}, {}});
		addToLoreMap(ArmorSkull.class, new Lore[] {}, new EnchantEntry[][] {{}, {}, {}, {}});
		addToLoreMap(ArmorTungsten.class, new Lore[] {}, new EnchantEntry[][] {{}, {}, {}, {}});
		addToLoreMap(ArmorOverlord.class, new Lore[] {}, new EnchantEntry[][] {{}, {}, {}, {}}); //programming themed possibly

		//Tools
		addToLoreMap(TragicItems.TungstenJack.getClass(), new Lore[] {new Lore(25, "Work, work, work!", 1), new Lore(15, "Time for lunch!", 1), new Lore(15, "Work all day, sleep all night!", 2),
			new Lore(5, "Off to work we go!", 2), new Lore(10, "Can you dig it?", 1), new Lore(25, "Just keep digging, digging, digging!", 2), new Lore(5, "The finest weapons and armor!", 2),
			new Lore(25, "Diamonds!", 3), new Lore(15, "Ooh, emeralds!", 3), new Lore(5, "Forged in the fires of Mount Doom!", 3), new Lore(5, "The best blacksmith in Whiterun!", 3),
			new Lore(10, "Can you pick up what I'm putting down?", 1)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.efficiency, 1), new EnchantEntry(Enchantment.fortune, 1)}, {new EnchantEntry(Enchantment.efficiency, 3), new EnchantEntry(Enchantment.fortune, 2), new EnchantEntry(Enchantment.fireAspect, 1)},
			{new EnchantEntry(Enchantment.efficiency, 5), new EnchantEntry(Enchantment.fortune, 3), new EnchantEntry(Enchantment.fireAspect, 2), new EnchantEntry(TragicEnchantments.Combustion, 1)}});

		addToLoreMap(TragicItems.CelestialJack.getClass(), new Lore[] {new Lore(25, "Enigmatic.", 0), new Lore(15, "Quite the mystery.", 0), new Lore(10, "It's a mystery to us all.", 0),
			new Lore(25, "To the Mystery Machine!", 1), new Lore(15, "I would've gotten away with it if it wasn't for you meddling kids!", 1), new Lore(10, "Scooby Snax?", 1), new Lore(5, "Another mystery solved!", 1),
			new Lore(25, "We've got a mystery on our hands!", 2), new Lore(15, "It's Old Man Withers from the Amusement Park!", 2), new Lore(10, "Jinkies!", 2), new Lore(5, "Whodunit?", 2),
			new Lore(25, "Let's get out our Handy-Dandy Notebook!", 3), new Lore(15, "Elementary, my dear Watson!", 3), new Lore(5, "Once you eliminate the impossible, whatever remains, no matter how improbable, must be the truth.", 3),
			new Lore(15, "We just found a clue!", 1), new Lore(5, "I live by Harry's code.", 3), new Lore(5, "I'm not a psychopath, I'm a high functioning sociopath, do your research.", 3),
			new Lore(5, "Colonel Mustard in the library with a knife!", 3)},
			new EnchantEntry[][] {{new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.fortune, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.fortune, 3), new EnchantEntry(TragicEnchantments.Combustion, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(Enchantment.fortune, 5), new EnchantEntry(TragicEnchantments.Combustion, 1), new EnchantEntry(TragicEnchantments.Luminescence, 1)}});

		//Normal Weapons
		addToLoreMap(TragicItems.MercuryDagger.getClass(), new Lore[] {new Lore(25, "Boring.", 1), new Lore(15, "Nice.", 1), new Lore(5, "Interesting.", 1), new Lore(15, "Lame", 1), new Lore(25, "Ha.", 2),
			new Lore(15, "Awesome.", 2), new Lore(10, "That's fascinating.", 2), new Lore(5, "That's nice.", 2), new Lore(25, "That's amazing!", 3), new Lore(15, "Fantastic!", 3),
			new Lore(5, "I'm shuddering with excitement!", 3), new Lore(5, "Ama-zuh-zing!", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 2)}, {new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.sharpness, 1)}});

		addToLoreMap(WeaponBeastlyClaws.class, new Lore[] {new Lore(25, "That's beastly.", 1), new Lore(15, "Epic.", 1), new Lore(5, "Knockout!", 1),
			new Lore(10, "Roar!", 1), new Lore(15, "Combo!", 1), new Lore(5, "Let's fight!", 1), new Lore(5, "Sucker punch!", 1),
			new Lore(25, "Just getting started!", 2), new Lore(20, "Just sharpening my claws!", 2), new Lore(15, "One-two punch!", 2),
			new Lore(5, "You're gonna hear me roar!", 2), new Lore(10, "Punchout!", 2), new Lore(5, "Fight!", 2), new Lore(5, "TKO!", 2),
			new Lore(25, "Hit and Run!", 3), new Lore(5, "Falcon Punch!", 3), new Lore(15, "Going Beastmode!", 3), new Lore(2, "C-c-c-combo breaker!", 3),
			new Lore(10, "Limit break!", 3), new Lore(15, "I'll rip you to pieces!", 3), new Lore(20, "Tear you to pieces, rip you apart!", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 2), new EnchantEntry(TragicEnchantments.Slay, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Slay, 2), new EnchantEntry(TragicEnchantments.Consume, 1)}});

		addToLoreMap(WeaponBlindingLight.class, new Lore[] {new Lore(25, "You're shining!", 1), new Lore(15, "Shine on!", 1), new Lore(5, "Aw, you're glowing~", 1),
			new Lore(10, "Shine bright like a diamond.", 1), new Lore(5, "Just needs some spit shine!", 1), new Lore(15, "Shinedown.", 1),
			new Lore(10, "It's bright, like me!", 1), new Lore(5, "So bright.", 2), new Lore(25, "Like a shooting star!", 2), new Lore(15, "Ooh, shiny!", 2),
			new Lore(25, "Shiny, shiny, shiny!", 2), new Lore(5, "Brilliant luster!", 2), new Lore(25, "Heaven let your light shine on!", 3),
			new Lore(15, "How do you get just the right amount of shiny?!", 3), new Lore(5, "Always look on the bright side of life!", 3),
			new Lore(5, "Turn on your love light!", 3), new Lore(5, "Shine on you crazy diamond!", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(TragicEnchantments.Absolve, 1)}, {new EnchantEntry(TragicEnchantments.Absolve, 3), new EnchantEntry(Enchantment.unbreaking, 1)},
			{new EnchantEntry(TragicEnchantments.Absolve, 5), new EnchantEntry(Enchantment.unbreaking, 1), new EnchantEntry(Enchantment.fireAspect, 1)}});

		addToLoreMap(WeaponCelestialAegis.class, new Lore[] {new Lore(25, "This is my jam!", 1), new Lore(15, "Sounds to die for!", 1), new Lore(5, "The Benny Hill theme song!", 1),
			new Lore(5, "First things first, I'm the realist!", 1), new Lore(20, "Nice tune!", 1), new Lore(20, "What a lovely melody!", 1), new Lore(25, "Death in E-Minor!", 2),
			new Lore(15, "Screams in 6/4 time!", 2), new Lore(25, "4/4 at 120 bpm", 2), new Lore(15, "What a lovely death sound you make!", 2), new Lore(10, "Such a lovely scream!", 2),
			new Lore(5, "Shrieks of terror have a nice ambience!", 2), new Lore(5, "Let the music take your breath away~", 2), new Lore(25, "I can show you the world!", 3),
			new Lore(25, "I wish I could be part of your world!", 3), new Lore(15, "Be our guest!", 3), new Lore(15, "Poor unfortunate souls!", 3), new Lore(10, "Go! Go! Power Rangers!", 3),
			new Lore(10, "Heroes in a half-shell, turtle power!", 3), new Lore(5, "Flight of the Bumblebee!", 3), new Lore(5, "The Blue Danube!", 3), new Lore(20, "It's Mambo No. 5!", 3),
			new Lore(15, "Guess who's back, back again!", 3), new Lore(15, "Under the sea!", 3)},
			new EnchantEntry[][] {{new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Absolve, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Absolve, 3), new EnchantEntry(TragicEnchantments.Consume, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Absolve, 5), new EnchantEntry(TragicEnchantments.Consume, 3),
				new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(Enchantment.looting, 3), new EnchantEntry(TragicEnchantments.Luminescence, 1)}});

		addToLoreMap(WeaponCelestialLongbow.class, new Lore[] {new Lore(25, "Like meteor showers!", 1), new Lore(10, "Shooting stars!", 1), new Lore(15, "Beautiful Starlights!", 1),
			new Lore(5, "Make a Wish!", 1), new Lore(25, "So beautiful!", 2), new Lore(10, "Ooh, a free starman!", 2), new Lore(5, "Make a wish!", 2), new Lore(5, "Time for the star festival!", 3),
			new Lore(5, "Meteor Smash!", 3), new Lore(25, "Time for armageddon!", 3), new Lore(5, "Guardian of the Galaxy!", 3), new Lore(15, "The Final Starman!?", 3),
			new Lore(5, "Good Morning Starshine!", 3), new Lore(5, "The Earth says, Hello!", 3), new Lore(5, "Warm the celestial bodies!", 3)},
			new EnchantEntry[][] {{new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.power, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.power, 3), new EnchantEntry(Enchantment.looting, 3)},
			{new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(Enchantment.power, 5), new EnchantEntry(Enchantment.looting, 5),
				new EnchantEntry(TragicEnchantments.Multiply, 1), new EnchantEntry(Enchantment.infinity, 1), new EnchantEntry(TragicEnchantments.Luminescence, 1)}});

		addToLoreMap(WeaponFrozenLightning.class, new Lore[] {new Lore(25, "Was that lightning?", 1), new Lore(15, "Ouch, you zapped me!", 1), new Lore(5, "Used Spark! It's not very effective...", 2),
			new Lore(25, "Lightning crashes...", 2), new Lore(15, "A storm is brewing!", 2), new Lore(5, "You've been... THUNDERSTRUCK!", 3), new Lore(15, "Static shock!", 2),
			new Lore(5, "I feel shocked.", 3),	new Lore(25, "Time for a lightning round!", 3), new Lore(5, "Used Volt Tackle! Critical hit!", 3),
			new Lore(10, "Used Thunder! It's super effective!", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.RuneBreak, 3), new EnchantEntry(TragicEnchantments.Rust, 1),
				new EnchantEntry(TragicEnchantments.Luminescence, 1)}});

		addToLoreMap(WeaponGravitySpike.class, new Lore[] {new Lore(15, "Time for a demonstration!", 1), new Lore(5, "e=mc^2", 1), new Lore(5, "For Science!", 1),
			new Lore(15, "The next Einstein!", 2), new Lore(5, "Isn't that a Rube Goldberg?", 2), new Lore(25, "Science rules!", 2), new Lore(10, "I like 3.14.", 2),
			new Lore(5, "In SPAAAAAAAAAAAACE!", 3), new Lore(25, "Reaching escape velocity!", 3), new Lore(15, "It is a dimension as vast as space and as timeless as infinity...", 3),
			new Lore(5, "There is a fifth dimension, beyond that which is known to man.", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.knockback, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.knockback, 3), new EnchantEntry(TragicEnchantments.Distract, 1)}});

		addToLoreMap(WeaponGuiltyThorn.class, new Lore[] {new Lore(25, "Kill...", 1), new Lore(15, "Your happiness kills me inside.", 1), new Lore(5, "Your pain feeds me.", 1),
			new Lore(10, "Your hatred fuels my soul.", 2), new Lore(25, "I love when you hate me.", 2), new Lore(25, "Die.", 1), new Lore(5, "I'm not crazy, I'm the only one thinking clearly right now.", 2),
			new Lore(25, "Your pain = <3", 3), new Lore(15, "Your screams of agony sound so beautiful!", 3), new Lore(5, "Some call me sadistic. I just like to have fun at other's expense.", 3),
			new Lore(15, "Don't worry, I'll end your misery!", 3), new Lore(5, "You sound better when you're dead!", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Leech, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Leech, 3), new EnchantEntry(Enchantment.sharpness, 3)}});

		addToLoreMap(WeaponHarmonyBell.class, new Lore[] {new Lore(25, "Ring-a-ding-ding!", 1), new Lore(15, "Peace and Quiet.", 1), new Lore(5, "Tranquility.", 1),
			new Lore(15, "Need some R&R?", 2), new Lore(5, "Ding-ding! Dinner is ready!", 2), new Lore(10, "Listen to those glorious chimes!", 3), new Lore(25, "Relax.", 2),
			new Lore(5, "Hell's Bells!", 3), new Lore(25, "Fahoo-Fores, Dahoo-Dores!", 3), new Lore(15, "Ding dong, the witch is dead!", 3), new Lore(5, "For Whom the Bell Tolls.", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Distract, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Distract, 3), new EnchantEntry(TragicEnchantments.Absolve, 1), new EnchantEntry(Enchantment.knockback, 1)}});

		addToLoreMap(WeaponHuntersBow.class, new Lore[] {new Lore(15, "On the hunt.", 1), new Lore(5, "Run as fast as you can!", 1), new Lore(10, "Tracking...", 1),
			new Lore(25, "Let the hunt begin.", 2), new Lore(5, "Time to join the hunting party!", 3), new Lore(5, "Catch me if you can!", 2), new Lore(5, "Conquest!", 2),
			new Lore(25, "Don't worry, I'm an expert.", 3), new Lore(15, "The Hunter becomes the Hunted.", 3), new Lore(5, "The Most Dangerous Game", 3), new Lore(15, "Night of the Hunter!", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 2), new EnchantEntry(Enchantment.punch, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.punch, 2), new EnchantEntry(Enchantment.flame, 1), new EnchantEntry(Enchantment.power, 1)}});

		addToLoreMap(WeaponMourningStar.class, new Lore[] {new Lore(25, "Sleep is for the weak!", 1), new Lore(15, "Dy-no-mite!", 1), new Lore(5, "Kaboom.", 1), new Lore(5, "Nuke!", 3),
			new Lore(25, "For SPARTAAAAAA!", 3), new Lore(15, "Just die already!", 2), new Lore(15, "I'm TNT, I'm dynamite!", 3), new Lore(25, "I have an explosive temper.", 2),
			new Lore(5, "Enemy airstrike inbound!", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.smite, 1)}, {new EnchantEntry(Enchantment.smite, 3), new EnchantEntry(TragicEnchantments.Consume, 1)},
			{new EnchantEntry(Enchantment.smite, 5), new EnchantEntry(TragicEnchantments.Consume, 3), new EnchantEntry(Enchantment.looting, 1)}});

		addToLoreMap(WeaponNekoLauncher.class, new Lore[] {new Lore(25, "Oops", 1), new Lore(15, "I meant to do that.", 1), new Lore(5, "That was supposed to happen!", 1),
			new Lore(25, "Tell me where it hurts!", 2), new Lore(15, "It's just a flesh wound.", 2), new Lore(5, "Does it hurt when I do this?", 2),
			new Lore(25, "This is why I can't have nice things!", 3), new Lore(15, "Some days you just can't get rid of a bomb!", 3), new Lore(5, "Meow~", 3),
			new Lore(5, "Seriously, all of these mess-ups were on purpose!", 2), new Lore(10, "It's all going to plan, honestly!", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.knockback, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(Enchantment.knockback, 3), new EnchantEntry(TragicEnchantments.Distract, 1)}});

		addToLoreMap(WeaponPitchBlack.class, new Lore[] {new Lore(25, "Black as my soul!", 1), new Lore(15, "Hide in the shadows.", 1), new Lore(5, "Darkness is my friend.", 1),
			new Lore(10, "Perfect Dark Zero.", 1), new Lore(25, "Paint it black!", 2), new Lore(15, "Like the black in your eyes.", 2), new Lore(5, "Pitch black!", 2), new Lore(15, "Black Hole Sun!", 3),
			new Lore(15, "Welcome to the Black Parade!", 3), new Lore(5, "Blackout! Blood in your eyes!", 3), new Lore(5, "I was born in the dark. Molded by it. You merely adopted it.", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 1), new EnchantEntry(TragicEnchantments.Decay, 1)}, {new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Decay, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Decay, 5), new EnchantEntry(TragicEnchantments.RuneBreak, 3), new EnchantEntry(Enchantment.looting, 1)}});

		addToLoreMap(WeaponReaperScythe.class, new Lore[] {new Lore(25, "Bleed out.", 1), new Lore(15, "Bleed for me.", 1), new Lore(5, "Blood is flowing now!", 1), new Lore(5, "It's raining blood.", 1),
			new Lore(25, "Blood is thicker than water.", 2), new Lore(15, "No matter how you discriminate we all bleed the same.", 2), new Lore(5, "Crimson red, like the blood moon.", 2),
			new Lore(5, "I ate his liver with some fava beans and a nice Chianti.", 3), new Lore(15, "Digging deeper just to throw it away!", 3), new Lore(25, "Let's paint this town red!", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Decay, 1)}, {new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Decay, 3), new EnchantEntry(TragicEnchantments.Vampirism, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Decay, 5), new EnchantEntry(TragicEnchantments.Vampirism, 3)}});

		addToLoreMap(WeaponWitheringAxe.class, new Lore[] {new Lore(5, "Like Paul Bunyan.", 1), new Lore(15, "Lemme axe you a question!", 1), new Lore(15, "Chop chop!", 1), new Lore(25, "Weapon of choice.", 1),
			new Lore(25, "Plaid is the new black.", 2), new Lore(15, "Tree murderer.", 2), new Lore(10, "Go ahead, axe me how my day went.", 2), new Lore(5, "Your beard looks quite luxurious today.", 2),
			new Lore(25, "He's a lumberjack and he's okay!", 3), new Lore(15, "Chop Suey!?", 3), new Lore(5, "Taking you right to the chop block!", 3), new Lore(5, "Treetho's Choppa", 3)},
			new EnchantEntry[][] {{}, {new EnchantEntry(Enchantment.unbreaking, 1)}, {new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.sharpness, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.sharpness, 3), new EnchantEntry(Enchantment.efficiency, 1)}});

		//Alpha weapons
		addToLoreMap(WeaponSentinel.class, new Lore[] {new Lore(15, "The red pill?", 3), new Lore(25, "White rabbit.", 3), new Lore(5, "The blue pill?", 3), new Lore(20, "How would you know the difference between the dream world and the real world?", 3),
			new Lore(25, "Tumbling down the rabbit hole...", 3), new Lore(5, "Is this the Matrix?", 3), new Lore(5, "You are the one.", 3), new Lore(15, "There is no spoon.", 3), new Lore(25, "Wonder what's next.", 3), new Lore(5, "I'm not the one.", 3),
			new Lore(5, "You may have spent the last few years looking for me, but I have spent my entire life looking for you.", 3), new Lore(15, "I don't like the idea that I'm not in control of my life.", 3), new Lore(15, "What good is a phone call if you're unable to speak?", 3),
			new Lore(5, "Mr. Anderson, you disappoint me.", 3), new Lore(15, "Free your mind.", 3)},
			new EnchantEntry[][] {{}, {}, {}, {new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Absolve, 5), new EnchantEntry(Enchantment.baneOfArthropods, 5), new EnchantEntry(TragicEnchantments.Decay, 5),
				new EnchantEntry(TragicEnchantments.Slay, 5), new EnchantEntry(Enchantment.smite, 5), new EnchantEntry(TragicEnchantments.Reach, 5)}});

		//Epic weapons
		addToLoreMap(WeaponButcher.class, new Lore[] {new Lore(25, "Time to eat?", 0), new Lore(15, "I'm so hungry...", 0), new Lore(10, "I need food.", 0),
			new Lore(25, "My stomach's grumbling...", 1), new Lore(15, "That looks delicious!", 1), new Lore(10, "My stomach won't stop growling!", 1), new Lore(5, "Needs more salt...", 1),
			new Lore(25, "Preheat oven to 450.", 2), new Lore(15, "Bon apetit!", 2), new Lore(5, "I'm having an old friend for dinner!", 2), new Lore(5, "Just add a pinch of salt.", 2),
			new Lore(25, "Add a splash of red wine.", 3), new Lore(15, "Bake for twenty minutes or until golden brown.", 3), new Lore(5, "Mmmm... donuts.", 3), new Lore(5, "OMNOMNOMNOMNOM", 3),
			new Lore(5, "Everything is edible, even me, but that would be cannibalism, children.", 3), new Lore(5, "Add some olive oil and garlic then simmer.", 3)},
			new EnchantEntry[][] {{new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3)}, {new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.Slay, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.Slay, 3), new EnchantEntry(Enchantment.sharpness, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 5), new EnchantEntry(TragicEnchantments.Slay, 5), new EnchantEntry(Enchantment.sharpness, 3)}});

		addToLoreMap(WeaponDragonFang.class, new Lore[] {new Lore(25, "Sasquatch!", 0), new Lore(15, "Is that a nymph?", 0), new Lore(10, "Sleeping with Sirens.", 0), new Lore(5, "It's really happening!", 0), new Lore(5, "Alrighty then. Picture this if you will.", 0),
			new Lore(25, "I swear, there was a triangle of lights in the sky moving around!", 1), new Lore(15, "Was that a jackelope?", 1), new Lore(10, "It's obviously a centaur.", 1), new Lore(5, "There's a cold spot here. Ghost?", 1),
			new Lore(25, "I think I just saw E.T.!", 2), new Lore(15, "Dude, I totally just saw Nessie.", 2), new Lore(10, "Cartman got abducted by aliens last night!", 2), new Lore(10, "Someone call the MIB", 2), new Lore(5, "Can't remember what they said...", 2),
			new Lore(25, "Return the slab or suffer my curse!", 3), new Lore(5, "The man in gauze, the man in gauze. King RAAAAAMSAYYY!", 3), new Lore(15, "Aliens are nice, they apologized, gave me a nice lollipop and sent me on my way.", 3),
			new Lore(5, "It's the chupacabra!", 3), new Lore(5, "A Will-o-the-Wisp started that fire, I know how to cook!", 3), new Lore(5, "Probed by alien. Chance of survival: minimal.", 3)},
			new EnchantEntry[][] {{new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3)}, {new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.Slay, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.Slay, 3), new EnchantEntry(Enchantment.fireAspect, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.Slay, 5), new EnchantEntry(Enchantment.fireAspect, 2)}});

		addToLoreMap(WeaponParanoia.class, new Lore[] {new Lore(25, "So lonely.", 0), new Lore(15, "Fragile and alone...", 0), new Lore(10, "It's calm.", 0), new Lore(5, "I'm so alone...", 0),
			new Lore(25, "If only I had friends...", 1), new Lore(15, "So scary.", 1), new Lore(10, "The outside world is so scary.", 1), new Lore(5, "When I wake up, I'm afraid.", 1),
			new Lore(5, "Alone...", 1), new Lore(25, "Feeling a bit paranoid right now...", 2), new Lore(15, "Sensational fear of everything!", 2), new Lore(10, "Darkness consumes me", 2),
			new Lore(25, "Lost in the dark of my mind...", 3), new Lore(15, "If fear is an animal, I've tamed it.", 3), new Lore(10, "If fear is an animal, it may have just swallowed me whole...", 3),
			new Lore(5, "Just because you're paranoid, doesn't mean they're not after you.", 3), new Lore(15, "They're all out to get me! Somebody help me!", 3), new Lore(5, "Darkness consume me.", 3)},
			new EnchantEntry[][] {{new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3)}, {new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 3), new EnchantEntry(TragicEnchantments.Leech, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 5), new EnchantEntry(TragicEnchantments.Leech, 3), new EnchantEntry(Enchantment.looting, 1)}});

		addToLoreMap(WeaponSplinter.class, new Lore[] {new Lore(25, "Beating around the bush...", 0), new Lore(15, "I see.", 0), new Lore(5, "I don't get it...", 0), new Lore(5, "Fool's Gold!", 0),
			new Lore(25, "Beggars can't be choosers!", 1), new Lore(15, "Sorry to burst your bubble!", 1), new Lore(10, "Easy as pie!", 1), new Lore(5, "Piece of cake!", 1), new Lore(10, "Don't cry over spilled milk!", 1),
			new Lore(25, "Don't count your chickens before they hatch!", 2), new Lore(15, "Jack of all trades!", 2), new Lore(10, "Don't count your chickens before they hatch!", 2), new Lore(5, "On cloud nine!", 2),
			new Lore(15, "Sorry to rain on your parade!", 1), new Lore(25, "A dime a dozen!", 3), new Lore(15, "Curiosity killed the cat!", 1), new Lore(5, "The nail that sticks out the most gets hammered down!", 3),
			new Lore(15, "Read'em and weep!", 3), new Lore(5, "Roll with the punches!", 3), new Lore(10, "It's not rocket science!", 3), new Lore(5, "A blessing in disguise!", 3)},
			new EnchantEntry[][] {{new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3)}, {new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.Consume, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.Consume, 3), new EnchantEntry(Enchantment.knockback, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.Consume, 5), new EnchantEntry(Enchantment.knockback, 3)}});

		addToLoreMap(WeaponThardus.class, new Lore[] {new Lore(25, "Power beam.", 0), new Lore(15, "Charge beam.", 0), new Lore(10, "Morph Ball.", 0), new Lore(5, "Missle.", 0), new Lore(5, "Energy Tank.", 0),
			new Lore(25, "Better than a stun gun!", 1), new Lore(15, "Spazer beam.", 1), new Lore(10, "Grapple beam.", 1), new Lore(15, "Zero suit.", 1), new Lore(5, "Morph Ball bomb acquired!", 1), new Lore(5, "Speed Booster acquired!", 1),
			new Lore(25, "You're a girl?", 2), new Lore(15, "Metroids. Metroids everywhere.", 2), new Lore(5, "Why is there always a Space Pirate?", 2), new Lore(5, "Remember me?", 2), new Lore(5, "Space jump acquired!", 2),
			new Lore(25, "Hyper Beam acquired!", 3), new Lore(15, "Hypermode, activate!", 3), new Lore(10, "Phazon beam acquired!", 3), new Lore(15, "Plasma beam acquired!", 3), new Lore(15, "Wave beam acquired!", 3), new Lore(15, "Screw Attack acquired!", 3),
			new Lore(5, "Super missle acquired!", 3), new Lore(5, "Power Bomb acquired!", 3), new Lore(5, "Speed Booster acquired!", 3), new Lore(15, "New area discovered: Tourian", 3), new Lore(5, "You have arrived on planet, Tallon IV", 3), new Lore(5, "SR388 has been cleared of all Metroid activity.", 3),
			new Lore(5, "The last metroid is in captivity.", 3), new Lore(5, "Power Suit acquired.", 1), new Lore(5, "Varia Suit acquired!", 2), new Lore(5, "Gravity Suit acquired!", 2), new Lore(5, "Phazon Suit acquired!", 2), new Lore(5, "Kraid has been defeated!", 1), new Lore(5, "Ridley has been defeated!", 2),
			new Lore(5, "Mother Brain has been defeated!", 3)},
			new EnchantEntry[][] {{new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3)}, {new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 3), new EnchantEntry(TragicEnchantments.Rust, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 5), new EnchantEntry(TragicEnchantments.Rust, 3), new EnchantEntry(TragicEnchantments.Luminescence, 1)}});

		addToLoreMap(WeaponTitan.class, new Lore[] {new Lore(25, "Filthy mortal.", 0), new Lore(15, "You're such a mortal.", 0), new Lore(10, "Why do you have to be so... mortal?", 0), new Lore(5, "You bore me, mortal.", 0),
			new Lore(25, "So God-like!", 1), new Lore(15, "It's like the Gods have blessed me!", 1), new Lore(10, "Thank the Gods!", 1), new Lore(5, "Praise the Gods for this gift!", 1), new Lore(5, "Thank God!", 1),
			new Lore(5, "A God-like aura permeates the air.", 1), new Lore(25, "Such God-like abilities!", 2), new Lore(15, "The Gods have become my equal!", 2), new Lore(5, "I am a God!", 2), new Lore(10, "Who needs a God when you have me?", 2),
			new Lore(25, "You are an ant to me, mortal.", 3), new Lore(10, "I am no mere mortal!", 2), new Lore(5, "Puny God.", 3), new Lore(15, "Poseidon has nothing on me!", 3), new Lore(15, "Faster than Hermes!", 3), new Lore(10, "The violence of Ares!", 3),
			new Lore(15, "Wiser than Athena!", 3), new Lore(5, "More creative than Hephaestus!", 3), new Lore(15, "More beautiful than Aphrodite!", 3), new Lore(5, "More spectacular than Zeus!", 3)},
			new EnchantEntry[][] {{new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3)}, {new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(Enchantment.looting, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(Enchantment.looting, 3), new EnchantEntry(TragicEnchantments.Consume, 1)},
			{new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 3), new EnchantEntry(Enchantment.looting, 5), new EnchantEntry(TragicEnchantments.Consume, 3)}});
	}

	public static void addToLoreMap(Class<? extends Item> clazz, LoreEntry entry)
	{
		if (weaponLores.containsKey(clazz)) TragicMC.logWarning("Duplicate lore entry for the item " + clazz);
		weaponLores.put(clazz, entry);
	}

	public static void addToLoreMap(Class<? extends Item> clazz, Lore[] lores, EnchantEntry[][] enchants)
	{
		weaponLores.put(clazz, new LoreEntry(Arrays.asList(lores), enchants));
	}

	public static LoreEntry getLoreEntry(Class<? extends Item> clazz)
	{
		return weaponLores.containsKey(clazz) ? weaponLores.get(clazz) : null;
	}

	public static EnumChatFormatting getFormatForRarity(int rarity)
	{
		return rarity == 0 ? EnumChatFormatting.GRAY : (rarity == 1 ? EnumChatFormatting.GOLD : (rarity == 2 ? EnumChatFormatting.DARK_GREEN : EnumChatFormatting.DARK_RED));
	}

	public static int getRarityFromStack(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity") ? stack.stackTagCompound.getByte("tragicLoreRarity") : 0;
	}

	public static String getDescFromStack(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreDesc") ? stack.stackTagCompound.getString("tragicLoreDesc") : null;
	}

	/**
	 * Can split any lengthy string into 3 smaller ones to fit within an item's description, it will only split to a max of 3 lines, then will trim out the rest
	 * @param lore
	 * @return
	 */
	public static String[] splitDesc(String lore)
	{
		String s = lore;
		String s2 = null;
		String s3 = null;

		if (s.length() > 32)
		{
			for (int i = 32; i < s.length(); i++)
			{
				if (s.substring(0, i).endsWith(" "))
				{
					if (s2 == null)
					{
						s2 = s.substring(i).trim();
						s = s.substring(0, i).trim();
						break;
					}

				}
			}
		}

		if (s2 != null && s2.length() > 32)
		{
			for (int i = 32; i < s2.length(); i++)
			{
				if (s2.substring(0, i).endsWith(" "))
				{
					if (s3 == null)
					{
						s3 = s2.substring(i).trim();
						s2 = s2.substring(0, i).trim();
						break;
					}
				}
			}

			if (s3 != null && s3.length() > 42)
			{
				s3 = s3.substring(0, 42).trim();
			}
		}

		if (s2 == null) return new String[] {s};
		if (s3 == null) return new String[] {s, s2};

		return new String[] {s, s2, s3};
	}

	public static class LoreEntry {

		private final ArrayList<Lore> lores = new ArrayList<Lore>();
		private final EnchantEntry[][] enchants;

		public LoreEntry(Collection<Lore> lores, EnchantEntry[][] enchants)
		{
			this.lores.addAll(lores);
			this.enchants = enchants;
		}

		public void addLore(Lore l)
		{
			this.lores.add(l);
		}

		/**
		 * This is used to pick a completely random lore based off of the weapon/armor's set of lores, this is for brand new weapons/armor or weapons/armor received from a mob
		 * @return
		 */
		public Lore getRandomLore()
		{
			try
			{
				int i = rand.nextInt(100);
				int r = i < 10 ? 3 : (i < 35 ? 2 : (i < 80 ? 1 : 0)); //10% chance to be epic, 25% to be rare, 45% to be uncommon, 20% to be common
				List<Lore> alist = new ArrayList();

				for (Lore l : this.lores)
				{
					if (l.getRarity() == r) alist.add(l);
				}

				if (alist.isEmpty()) return this.lores.size() > 0 && r > 0 ? this.lores.get(rand.nextInt(this.lores.size())) : new Lore(1, null, 0);

				return (Lore) WeightedRandom.getRandomItem(rand, alist);
			}
			catch (Exception e)
			{
				TragicMC.logError("Error getting a random Lore for a weapon", e);
				return null;
			}
		}

		/**
		 * Returns a random lore in the given rarity tier, otherwise returns an empty lore
		 * @param rarity
		 * @param id
		 * @return
		 */
		public Lore getLoreOfRarity(int rarity)
		{
			try
			{
				List<Lore> alist = new ArrayList();

				for (Lore l : this.lores)
				{
					if (l.getRarity() == rarity) alist.add(l);
				}

				if (alist.isEmpty()) return new Lore(1, null, rarity);

				return (Lore) WeightedRandom.getRandomItem(rand, alist);
			}
			catch (Exception e)
			{
				TragicMC.logError("Error retrieving a specific Lore for a weapon based on rarity", e);
				return null;
			}
		}

		public EnchantEntry[] getEnchantmentsForRarity(int rarity)
		{
			try
			{
				rarity = MathHelper.clamp_int(rarity, 0, 3);
				return this.enchants[rarity];
			}
			catch (Exception e)
			{
				TragicMC.logError("Error getting enchantments for a weapon", e);
				return null;
			}
		}
	}

	public static class EnchantEntry extends Tuple<Enchantment, Integer>{

		public EnchantEntry(Enchantment ench, int level)
		{
			super(ench, level);
		}

		public Enchantment getEnchantment() { return this.getLeft(); }
		public int getEnchantLevel() { return this.getRight(); }
	}

	public static class Lore extends WeightedRandom.Item {

		private final int rarity;
		private final String desc;

		public Lore(int weight, String desc, int rarity) {
			super(weight);
			this.desc = desc;
			this.rarity = net.minecraft.util.MathHelper.clamp_int(rarity, 0, 3);
		}

		public String getDesc() { return this.desc; }
		public int getRarity() { return this.rarity; }

		public EnumRarity getRarityEnum()
		{
			return this.rarity == 0 ? EnumRarity.common : (this.rarity == 1 ? EnumRarity.uncommon : (this.rarity == 2 ? EnumRarity.rare : EnumRarity.epic));
		}
	}
}
