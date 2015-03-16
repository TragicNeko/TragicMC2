package tragicneko.tragicmc.events;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.items.Challenge;
import tragicneko.tragicmc.items.ItemChallenge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ChallengeItemEvents {

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.entityLiving.worldObj.isRemote) return;

		EntityPlayer player;
		if (event.entityLiving instanceof EntityPlayer)
		{
			player = (EntityPlayer) event.entityLiving;
			ItemStack[] inv = player.inventory.mainInventory;
			ItemStack stack;
			Challenge challenge = null;

			for (int i = 0; i < inv.length; i++)
			{
				if (inv[i] != null && inv[i].hasTagCompound() && inv[i].getItem() instanceof ItemChallenge && inv[i].getItemDamage() != 0 && inv[i].getItemDamage() != 250)
				{
					stack = inv[i];
					if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("challengeID")) continue;
					challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));
					if (stack.stackTagCompound.hasKey("challengeProgress") && challenge != null && !challenge.savesProgress) stack.stackTagCompound.setInteger("challengeProgress", 0);
				}
			}
		}
		else if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer)
		{
			player = (EntityPlayer) event.source.getEntity();
			ItemStack[] inv = player.inventory.mainInventory;
			ItemStack stack;
			Challenge challenge = null;

			for (int i = 0; i < inv.length; i++)
			{
				if (inv[i] != null && inv[i].hasTagCompound() && inv[i].getItem() instanceof ItemChallenge && inv[i].getItemDamage() != 0 && inv[i].getItemDamage() != 250)
				{
					stack = inv[i];
					if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("challengeID")) continue;
					challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));
					if (stack.stackTagCompound.hasKey("challengeProgress") && challenge != null && !challenge.isItemChallenge)
					{
						Class cls = challenge.challengeClass;
						if (cls != null && (cls == event.entityLiving.getClass() || event.entityLiving.getClass().getSuperclass() == cls))
						{
							int pow = stack.stackTagCompound.getInteger("challengeProgress");
							stack.stackTagCompound.setInteger("challengeProgress", ++pow);
						}
					}
				}
			}

			if (TragicMC.rand.nextInt(1000) <= TragicConfig.challengeScrollDropChance && event.entityLiving instanceof EntityMob)
			{
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.ChallengeScroll, 1, 0), 0.4F);
			}
		}
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving.worldObj.isRemote || !(event.entityLiving instanceof EntityPlayer) || event.entityLiving.ticksExisted % 10 != 0) return;
		EntityPlayer player = (EntityPlayer) event.entityLiving;
		ItemStack[] inv = player.inventory.mainInventory;
		ItemStack stack;
		Challenge challenge = null;

		for (int i = 0; i < inv.length; i++)
		{
			if (inv[i] != null && inv[i].hasTagCompound() && inv[i].getItem() instanceof ItemChallenge && inv[i].getItemDamage() != 0 && inv[i].getItemDamage() != 250)
			{
				stack = inv[i];
				if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("challengeID")) continue;
				challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));

				if (stack.stackTagCompound.hasKey("challengeProgress") && challenge != null)
				{
					if (challenge.isMobRush)
					{
						List<EntityMob> list = player.worldObj.getEntitiesWithinAABB(EntityMob.class, player.boundingBox.expand(100.0, 100.0, 100.0));

						for (int j = 0; j < list.size(); j++)
						{
							list.get(j).setAttackTarget(player);
						}

						player.worldObj.getChunkFromBlockCoords((int) player.posX, (int) player.posZ).inhabitedTime += 10000L;
					}
					else if (challenge.isTargetChallenge && challenge.challengeClass != null)
					{
						List<Entity> list = player.worldObj.getEntitiesWithinAABB(challenge.challengeClass, player.boundingBox.expand(6.0, 6.0, 6.0));

						for (int j = 0; j < list.size(); j++)
						{
							if (list.get(j).getClass() == challenge.challengeClass)
							{
								stack.stackTagCompound.setInteger("challengeProgress", 1);
								break;
							}
						}
					}
					else if (challenge.isBlockChallenge)
					{
						Block block = player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ);
						if (block == challenge.challengeBlock) stack.stackTagCompound.setInteger("challengeProgress", 1);
					}
					else if (challenge.isLoreChallenge)
					{
						ItemStack loreStack;
						int amt = 0;

						for (int m = 0; m < inv.length; m++)
						{
							if (inv[m] != null && inv[m].hasTagCompound() && inv[m].stackTagCompound.hasKey("tragicLoreRarity"))
							{
								loreStack = inv[m];
								if (loreStack.stackTagCompound.getInteger("tragicLoreRarity") <= challenge.loreRarity) amt++;
							}
						}
						stack.stackTagCompound.setInteger("challengeProgress", amt);
					}
					else if (challenge.isArmorChallenge)
					{
						int amt = 0;
						ItemStack[] armorInv = player.inventory.armorInventory;
						ItemStack[] challengeArmor = challenge.challengeArmor;

						for (int m = 0; m < armorInv.length; m++)
						{
							if (armorInv[m] != null)
							{
								for (int pew = 0; pew < challengeArmor.length; pew++)
								{
									if (challengeArmor[pew] != null && armorInv[m].getItem() == challengeArmor[pew].getItem()) amt++;
								}
							}
						}
						stack.stackTagCompound.setInteger("challengeProgress", amt);
					}

					if (challenge.isLocationBased)
					{
						boolean flag = false;
						if (challenge.challengeBiome != null)
						{
							flag = player.worldObj.getBiomeGenForCoords((int) player.posX, (int) player.posZ) == challenge.challengeBiome;
						}
						else
						{
							flag = MathHelper.sqrt_double(player.posX * player.posX + player.posZ * player.posZ) >= challenge.challengeRange;
						}
						stack.stackTagCompound.setBoolean("challengeLocation", flag);
					}
				}
			}
		}
	}
}
