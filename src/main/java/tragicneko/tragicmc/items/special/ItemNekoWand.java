package tragicneko.tragicmc.items.special;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemNekoWand extends Item {

	private int cooldown = 0;

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add(EnumChatFormatting.DARK_RED + "Use your neko mind control to make mobs fight!");
		par2List.add("Left click to select a mob or group to fight");
		par2List.add("Left click again to set the selected mobs target");
		par2List.add("Sneaking allows you to select and use a group");
		par2List.add("Right click to reset all selected mobs");
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (this.cooldown > 0) return true;
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		if (!stack.stackTagCompound.hasKey("entityIDs")) stack.stackTagCompound.setIntArray("entityIDs", new int[0]);
		if (!stack.stackTagCompound.hasKey("entityID")) stack.stackTagCompound.setInteger("entityID", 0);

		if (player.isSneaking())
		{
			if (stack.stackTagCompound.getIntArray("entityIDs").length == 0)
			{
				double d0 = 12.0D;
				List<EntityMob> list = player.worldObj.getEntitiesWithinAABB(EntityMob.class, player.boundingBox.expand(d0, d0, d0));
				int[] ids = new int[list.size()];

				for (int i = 0; i < list.size(); i++)
				{
					ids[i] = list.get(i).getEntityId();
				}

				player.addChatMessage(new ChatComponentText("A group of targetted entities within " + d0 + " has been selected."));
				cooldown = 10;
				stack.stackTagCompound.setIntArray("entityIDs", ids);
				return true;

			}
			else
			{
				if (entity instanceof EntityLivingBase)
				{
					EntityMob ent;

					for (int i = 0; i < stack.stackTagCompound.getIntArray("entityIDs").length; i++)
					{
						ent = (EntityMob) entity.worldObj.getEntityByID(stack.stackTagCompound.getIntArray("entityIDs")[i]);

						if (!ent.equals(entity)) //makes sure that an entity doesn't target itself
						{
							ent.getNavigator().clearPathEntity();
							ent.setAttackTarget(null);
							ent.setAttackTarget((EntityLivingBase) entity);
						}
					}

					player.addChatMessage(new ChatComponentText("The group of targetted entities has been set to attack " + entity +"!"));
					cooldown = 10;
					stack.stackTagCompound.setIntArray("entityIDs", new int[0]);
					return true;
				}
			}
		}
		else
		{
			if (stack.stackTagCompound.getInteger("entityID") == 0)
			{
				if (entity instanceof EntityMob)
				{
					stack.stackTagCompound.setInteger("entityID", entity.getEntityId());
					player.addChatMessage(new ChatComponentText("Please choose a target for " + entity + "."));
					cooldown = 10;
					return true;
				}
			}
			else
			{
				EntityMob ent = (EntityMob) entity.worldObj.getEntityByID(stack.stackTagCompound.getInteger("entityID"));
				if (entity instanceof EntityLivingBase && !ent.equals(entity)) //makes sure that an entity doesn't target itself
				{
					ent.getNavigator().clearPathEntity();
					ent.setAttackTarget(null);
					ent.setAttackTarget((EntityLivingBase) entity);
					player.addChatMessage(new ChatComponentText(ent + " has been set to attack " + entity +"!"));
					cooldown = 10;
					stack.stackTagCompound.setInteger("entityID", 0);
					return true;
				}
			}
		}
		return true;
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer par3EntityPlayer)
	{
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		if (!stack.stackTagCompound.hasKey("entityIDs")) stack.stackTagCompound.setIntArray("entityIDs", new int[0]);
		if (!stack.stackTagCompound.hasKey("entityID")) stack.stackTagCompound.setInteger("entityID", 0);
		
		if (stack.stackTagCompound.getIntArray("entityIDs").length > 0)
		{
			stack.stackTagCompound.setIntArray("entityIDs", new int[0]);
			if (world.isRemote) par3EntityPlayer.addChatMessage(new ChatComponentText("You have reset the group of targetting entities."));
		}

		if (stack.stackTagCompound.getInteger("entityID") != 0)
		{
			stack.stackTagCompound.setInteger("entityID", 0);
			if (world.isRemote) par3EntityPlayer.addChatMessage(new ChatComponentText("You have reset the single targetting entity."));
		}
		
		return stack;
	}

	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
	{
		if (this.cooldown > 0)
		{
			cooldown--;
		}
	}

}
