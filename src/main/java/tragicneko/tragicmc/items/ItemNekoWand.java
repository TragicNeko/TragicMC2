package tragicneko.tragicmc.items;

import java.util.List;

import tragicneko.tragicmc.TragicMC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemNekoWand extends Item {
	
	public ItemNekoWand()
	{
		super();
		this.setCreativeTab(TragicMC.Creative);
	}

	private int cooldown = 0;

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add(EnumChatFormatting.DARK_RED + "Make mobs fight with neko mind control!");
		par2List.add("L-Click to select a mob to fight");
		par2List.add("L-Click again to set the mob's target");
		par2List.add("Sneak-L-Click allows you to select a group");
		par2List.add("Sneak-L-Click to set a group's target");
		par2List.add("R-Click to reset all selections"); 
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote) return false; 
		if (this.cooldown > 0) return true;
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		if (!stack.stackTagCompound.hasKey("entityIDs")) stack.stackTagCompound.setIntArray("entityIDs", new int[0]);
		if (!stack.stackTagCompound.hasKey("entityID")) stack.stackTagCompound.setInteger("entityID", 0);
		
		EnumChatFormatting reset = EnumChatFormatting.RESET;
		EnumChatFormatting aqua = EnumChatFormatting.AQUA;
		EnumChatFormatting red = EnumChatFormatting.RED;

		if (player.isSneaking())
		{
			if (stack.stackTagCompound.getIntArray("entityIDs").length == 0)
			{
				double d0 = 12.0D;
				List<EntityCreature> list = player.worldObj.getEntitiesWithinAABB(EntityCreature.class, player.boundingBox.expand(d0, d0, d0));
				int[] ids = new int[list.size()];

				for (int i = 0; i < list.size(); i++)
				{
					ids[i] = list.get(i).getEntityId();
				}

				player.addChatMessage(new ChatComponentText("A Group of Entities within " + aqua + d0 + reset + " blocks has been selected."));
				cooldown = 10;
				stack.stackTagCompound.setIntArray("entityIDs", ids);
				return true;
			}
			else
			{
				if (entity instanceof EntityCreature)
				{
					EntityCreature ent;

					for (int i = 0; i < stack.stackTagCompound.getIntArray("entityIDs").length; i++)
					{
						ent = (EntityCreature) entity.worldObj.getEntityByID(stack.stackTagCompound.getIntArray("entityIDs")[i]);

						if (ent != null && !ent.equals(entity))
						{
							ent.getNavigator().clearPathEntity();
							ent.setAttackTarget(null);
							ent.targetTasks.addTask(3, new EntityAINearestAttackableTarget(ent, entity.getClass(), 0, true));
							ent.setAttackTarget((EntityLivingBase) entity);
						}
					}

					player.addChatMessage(new ChatComponentText("The " + aqua + "Group of Entities" + reset + " has been set to attack " + red + entity.getCommandSenderName() + reset + "!"));
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
				if (entity instanceof EntityCreature)
				{
					stack.stackTagCompound.setInteger("entityID", entity.getEntityId());
					player.addChatMessage(new ChatComponentText("Please choose a target for " + aqua + entity.getCommandSenderName() + reset + "."));
					cooldown = 10;
					return true;
				}
			}
			else
			{
				EntityCreature ent = (EntityCreature) entity.worldObj.getEntityByID(stack.stackTagCompound.getInteger("entityID"));
				if (entity instanceof EntityCreature && ent != null && !ent.equals(entity))
				{
					ent.getNavigator().clearPathEntity();
					ent.setAttackTarget(null);
					ent.targetTasks.addTask(0, new EntityAINearestAttackableTarget(ent, entity.getClass(), 0, true));
					ent.setAttackTarget((EntityLivingBase) entity);
					player.addChatMessage(new ChatComponentText(aqua + "" +  ent.getCommandSenderName() + reset + " has been set to attack " + red + entity.getCommandSenderName() + reset + "!"));
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
		
		EnumChatFormatting green = EnumChatFormatting.GREEN;
		
		if (stack.stackTagCompound.getIntArray("entityIDs").length > 0)
		{
			stack.stackTagCompound.setIntArray("entityIDs", new int[0]);
			if (world.isRemote) par3EntityPlayer.addChatMessage(new ChatComponentText(green + "You have reset the selected group of entities!"));
		}
		
		if (stack.stackTagCompound.getInteger("entityID") != 0)
		{
			stack.stackTagCompound.setInteger("entityID", 0);
			if (world.isRemote) par3EntityPlayer.addChatMessage(new ChatComponentText(green + "You have reset the selected single entity!"));
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
