package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicTabs;
import tragicneko.tragicmc.util.TragicEntityList;

public class ItemChallenge extends Item {

	private IIcon scrollIcon, completeIcon;
	
	private static final ItemStack[] badRewards = new ItemStack[] {new ItemStack(Items.coal), new ItemStack(Items.flint_and_steel), new ItemStack(Items.string), new ItemStack(Items.stick),
		new ItemStack(Items.bone), new ItemStack(Items.bread), new ItemStack(Items.book), new ItemStack(Items.bowl), new ItemStack(TragicItems.Ash), new ItemStack(TragicItems.Banana),
		new ItemStack(TragicItems.Sushi), new ItemStack(TragicItems.BoneMarrow)
	};
	
	private static final ItemStack[] cheapRewards = new ItemStack[] {new ItemStack(Items.diamond), new ItemStack(Items.emerald), new ItemStack(Items.apple), new ItemStack(Items.iron_ingot),
		new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_nugget), new ItemStack(Items.coal), new ItemStack(Items.dye, 1, 4), new ItemStack(TragicItems.Tungsten),
		new ItemStack(TragicItems.RedMercury), new ItemStack(TragicItems.Quicksilver), new ItemStack(TragicItems.QuicksilverIngot), new ItemStack(Blocks.obsidian),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.Jarra") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.Jarra")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.Magmox") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.Magmox")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.Kragul") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.Kragul")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.MegaCryse") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.MegaCryse")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.StinKing") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.StinKing")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.GreaterStin") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.GreaterStin")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.StinQueen") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.StinQueen")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.VoxStellarum") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.VoxStellarum")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.Aegar") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.Aegar"))
	};

	private static final ItemStack[] rewards = new ItemStack[] {new ItemStack(Items.diamond, itemRand.nextInt(3) + 1), new ItemStack(Items.emerald, itemRand.nextInt(3) + 1),
		new ItemStack(TragicItems.AwakeningStone, 1), new ItemStack(TragicItems.AmuletRelease, 1), new ItemStack(TragicItems.DoomConsume, 1),
		new ItemStack(TragicItems.CooldownDefuse, 1 + itemRand.nextInt(8)), new ItemStack(TragicItems.Ruby, 1 + itemRand.nextInt(3)), new ItemStack(Items.iron_ingot, itemRand.nextInt(3) + 1),
		new ItemStack(TragicItems.Sapphire, itemRand.nextInt(3) + 1), new ItemStack(Items.gold_ingot, itemRand.nextInt(3) + 1), new ItemStack(Items.golden_apple, itemRand.nextInt(3) + 1, itemRand.nextInt(2)),
		new ItemStack(TragicItems.CryingObsidianOrb), new ItemStack(TragicItems.BleedingObsidianOrb), new ItemStack(TragicItems.DyingObsidianOrb), new ItemStack(TragicItems.ObsidianOrb, 1 + itemRand.nextInt(3)),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.Apis") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.Apis")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.Polaris") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.Polaris")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.Yeti") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.Yeti")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.TimeController") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.TimeController")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.Kitsune") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.Kitsune")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.DeathReaper") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.Enyvil")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.Enyvil") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.Kitsune")),
		new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.stringToIDMapping.get("TragicMC.Claymation") == null ? 0 : TragicEntityList.stringToIDMapping.get("TragicMC.Claymation")),
		new ItemStack(TragicItems.ApisAmulet), new ItemStack(TragicItems.CreeperAmulet), new ItemStack(TragicItems.BlacksmithAmulet), new ItemStack(TragicItems.KitsuneAmulet),
		new ItemStack(TragicItems.ZombieAmulet), new ItemStack(TragicItems.SkeletonAmulet), new ItemStack(TragicItems.SunkenAmulet), new ItemStack(TragicItems.PeaceAmulet),
		new ItemStack(TragicItems.ChickenAmulet), new ItemStack(TragicItems.ClaymationAmulet), new ItemStack(TragicItems.YetiAmulet), new ItemStack(TragicItems.MartyrAmulet),
		new ItemStack(TragicItems.GoldenSushi, 1 + itemRand.nextInt(3)), new ItemStack(TragicItems.Titan), new ItemStack(TragicItems.Paranoia), new ItemStack(TragicItems.Butcher),
		new ItemStack(TragicItems.Thardus), new ItemStack(TragicItems.DragonFang), new ItemStack(TragicItems.Splinter), new ItemStack(TragicItems.Talisman), new ItemStack(TragicItems.EndermanAmulet)
	};

	private static String[] subNames = new String[] {"inactive", "inProgress", "complete"};
	
	private static final String progress = "challengeProgress";
	private static final String location = "challengeLocation";

	public ItemChallenge()
	{
		this.setCreativeTab(TragicTabs.Survival);
		this.setMaxDamage(0);
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setUnlocalizedName("tragicmc.challengeItem");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		if (stack.getItemDamage() == 0) return EnumRarity.common;
		if (stack.getItemDamage() == 250) return EnumRarity.epic;
		if (!stack.hasTagCompound() || !stack.stackTagCompound.hasKey("challengeID") || Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID")) == null) return EnumRarity.common;
		switch(Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID")).difficulty)
		{
		case 3:
			return EnumRarity.epic;
		case 2:
			return EnumRarity.rare;
		case 1:
			return EnumRarity.uncommon;
		case 0:
		default:
			return EnumRarity.common;
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int var = itemstack.getItemDamage() == 250 ? 2 : (itemstack.getItemDamage() != 0 ? 1 : 0);
		return getUnlocalizedName() + "." + subNames[var];
	}

	@Override
	public IIcon getIconFromDamage(int damage)
	{
		if (damage == 0) return this.scrollIcon;
		if (damage == 250) return this.completeIcon;
		return this.itemIcon;
	}

	@Override
	public void registerIcons(IIconRegister register)
	{
		super.registerIcons(register);
		this.scrollIcon = register.registerIcon("tragicmc:ChallengeScroll_lowRes");
		this.completeIcon = register.registerIcon("tragicmc:ChallengeComplete_lowRes");
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) 
	{
		if (world.isRemote) return stack;

		if (stack.getItemDamage() == 0)
		{
			player.addChatMessage(new ChatComponentText("Challenge accepted!"));
			Challenge challenge = Challenge.getChallengeFromID(itemRand.nextInt(Challenge.challengeList.length) + 1);
			while (challenge == null)
			{
				challenge = Challenge.getChallengeFromID(itemRand.nextInt(Challenge.challengeList.length) + 1);
			}
			stack.setItemDamage(challenge.challengeID);
			if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setInteger("challengeID", challenge.challengeID);
		}
		else if (stack.getItemDamage() == 250)
		{
			player.addChatMessage(new ChatComponentText("Challenge completed, have a reward!"));
			Challenge challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));
			int extra = itemRand.nextInt((challenge.difficulty + 1) * 2) + 1;
			ItemStack reward = null;
			
			for (int i = 0; i < extra; i++)
			{
				EntityItem item = new EntityItem(world);
				reward = challenge.difficulty > 2 ? rewards[itemRand.nextInt(rewards.length)] : (challenge.difficulty > 0 ? cheapRewards[itemRand.nextInt(cheapRewards.length)] : badRewards[itemRand.nextInt(badRewards.length)]);
				item.setEntityItemStack(reward.copy());
				item.setPosition(player.posX + itemRand.nextDouble() - itemRand.nextDouble(), player.posY + 0.6D, player.posZ  + itemRand.nextDouble() - itemRand.nextDouble());
				world.spawnEntityInWorld(item);
				if (extra > 3 && itemRand.nextInt(3) == 0) break;
			}
			stack.stackSize--;
		}
		else
		{
			if (stack.hasTagCompound()) player.addChatMessage(new ChatComponentText(Challenge.getNameFromID(stack.stackTagCompound.getInteger("challengeID"))+ " is in progress!"));
		}

		return stack;
	}

	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (stack.getItemDamage() == 250)
		{
			par2List.add(EnumChatFormatting.GOLD + "Challenge complete!");
			par2List.add(EnumChatFormatting.WHITE + "What are you doing reading this,");
			par2List.add(EnumChatFormatting.WHITE + "get your reward!");
		}
		else if (stack.getItemDamage() == 0)
		{
			par2List.add(EnumChatFormatting.WHITE + "An inactive Challenge Scroll.");
			par2List.add(EnumChatFormatting.RESET + "Right-Click to start a Challenge!");
		}
		else if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("challengeID"))
		{
			Challenge challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));
			if (challenge == null) return;
			EnumChatFormatting format = challenge.difficulty == 1 ? EnumChatFormatting.AQUA : (challenge.difficulty == 2 ? EnumChatFormatting.BLUE : (challenge.difficulty == 3 ? EnumChatFormatting.GOLD : EnumChatFormatting.WHITE));
			par2List.add("Challenge: " + format + Challenge.getNameFromID(challenge.challengeID));
			String diff = challenge.difficulty == 0 ? "Easy" : (challenge.difficulty == 1 ? "Medium" : (challenge.difficulty == 2 ? "Hard" : "Harsh"));
			par2List.add("Difficulty: " + format + diff);
			String s = challenge.getDesc(challenge.challengeID);
			String s2 = null;
			String s3 = null;

			if (s.length() > 28)
			{
				char space = new Character(' ');
				int index = 0;

				for (int i = 28; i < s.length(); i++)
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

			if (s2 != null && s2.length() > 28)
			{
				char space = new Character(' ');
				int index = 0;

				for (int i = 28; i < s2.length(); i++)
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

				if (s3 != null && s3.length() > 28)
				{
					s3 = s3.substring(0, 28).trim();
				}
			}

			if (s != null) par2List.add(s);
			if (s2 != null) par2List.add(s2);
			if (s3 != null) par2List.add(s3);
			par2List.add("Progress: " + stack.stackTagCompound.getInteger("challengeProgress") + "/ " + challenge.requirement);
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{		
		if (world.isRemote || stack.getItemDamage() == 0 || stack.getItemDamage() == 250) return; 

		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		if (!stack.stackTagCompound.hasKey("challengeID")) stack.stackTagCompound.setInteger("challengeID", stack.getItemDamage());
		
		Challenge challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));

		if (entity instanceof EntityPlayer && challenge != null)
		{
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack[] inv = player.inventory.mainInventory;
			ItemStack invStack;
			int amt = 0;

			if (challenge.isItemChallenge)
			{
				for (int i = 0; i < inv.length; i++)
				{
					if (inv[i] != null && challenge.challengeItem != null)
					{
						invStack = inv[i];
						if (invStack.getItem() == challenge.challengeItem.getItem())
						{
							amt += invStack.stackSize;
						}
					}
				}

				stack.stackTagCompound.setInteger("challengeProgress", amt);
			}
			else
			{
				if (!stack.stackTagCompound.hasKey("challengeProgress")) stack.stackTagCompound.setInteger("challengeProgress", 0);

				if (challenge.isTimed)
				{
					if (player.ticksExisted % 20 == 0)
					{
						int pow = stack.stackTagCompound.getInteger("challengeProgress");

						if (challenge.isMobRush && !player.worldObj.isDaytime() || !challenge.isMobRush)
						{
							stack.stackTagCompound.setInteger("challengeProgress", ++pow);
						}
					}
				}
			}			
		}

		if (stack.stackTagCompound.hasKey("challengeProgress") && challenge != null && stack.stackTagCompound.getInteger("challengeProgress") == 0 && challenge.isLocationBased &&
				stack.stackTagCompound.hasKey("challengeLocation") && stack.stackTagCompound.getBoolean("challengeLocation"))
		{
			if (!challenge.isBlockChallenge && !challenge.isItemChallenge && !challenge.isMobRush && !challenge.isTimed)
			{
				stack.stackTagCompound.setInteger("challengeProgress", 1);
			}
		}

		if (stack.stackTagCompound.hasKey("challengeProgress") && challenge != null && stack.stackTagCompound.getInteger("challengeProgress") >= challenge.requirement)
		{
			if (challenge.isLocationBased)
			{
				if (stack.stackTagCompound.hasKey("challengeLocation") && stack.stackTagCompound.getBoolean("challengeLocation")) stack.setItemDamage(250);
			}
			else
			{
				stack.setItemDamage(250);
			}
		}
	}
}
