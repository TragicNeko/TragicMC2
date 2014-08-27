package tragicneko.tragicmc.items.special;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemNekoWand extends Item {

	private EntityCreature targettingEntity;
	private EntityCreature[] targettingGroup;

	private int cooldown = 0;

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add(EnumChatFormatting.DARK_RED + "Use your neko mind control to make mobs fight!");
		par2List.add("Left click to select a mob or group to fight");
		par2List.add("Left click again to set the selected mobs target");
		par2List.add("Sneaking allows you to select a group of mobs");
		par2List.add("Right click to reset all selected mobs");
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (this.cooldown > 0) return true;

		if (player.isSneaking())
		{
			if (this.targettingGroup == null)
			{
				double d0 = 12.0D;
				List<EntityCreature> list = player.worldObj.getEntitiesWithinAABB(EntityCreature.class, player.boundingBox.expand(d0, d0, d0));
				this.targettingGroup = new EntityCreature[list.size()];

				for (int i = 0; i < list.size(); i++)
				{
					this.targettingGroup[i] = list.get(i);
				}

				player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD + "A group of targetted entities has been selected."));
				cooldown = 10;
				return true;

			}
			else
			{
				if (entity instanceof EntityLivingBase)
				{
					EntityCreature ent;

					for (int i = 0; i < this.targettingGroup.length; i++)
					{
						ent = this.targettingGroup[i];

						if (!ent.equals(entity)) //makes sure that an entity doesn't target itself
						{
							ent.setTarget(null);
							ent.setAttackTarget(null);
							ent.setTarget(entity);
							ent.setAttackTarget((EntityLivingBase) entity);
						}
					}

					player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD + "The group of targetted entities has been set to attack " + entity.getCommandSenderName() +"!"));
					cooldown = 10;
					this.targettingGroup = null;
					this.targettingEntity = null;
					return true;
				}
			}
		}
		else
		{
			if (this.targettingEntity == null)
			{
				if (entity instanceof EntityCreature)
				{
					this.targettingEntity = (EntityCreature) entity;
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD + "Please choose a target for " + this.targettingEntity.getCommandSenderName() + "."));
					cooldown = 10;
					return true;
				}
			}
			else
			{
				if (entity instanceof EntityLivingBase && !this.targettingEntity.equals(entity)) //makes sure that an entity doesn't target itself
				{
					this.targettingEntity.setTarget(null);
					this.targettingEntity.setAttackTarget(null);
					this.targettingEntity.setTarget(entity);
					this.targettingEntity.setAttackTarget((EntityLivingBase) entity);
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD + "" + this.targettingEntity.getCommandSenderName() + " has been set to attack " + entity.getCommandSenderName() +"!"));
					cooldown = 10;
					this.targettingEntity = null;
					this.targettingGroup = null;
					return true;
				}
			}
		}
		return true;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (this.targettingGroup != null)
		{
			this.targettingGroup = null;
			if (par2World.isRemote) par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD + "You have reset the group of targetting entities."));
		}

		if (this.targettingEntity != null)
		{
			this.targettingEntity = null;
			if (par2World.isRemote) par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD + "You have reset the targetting entity."));
		}
		return par1ItemStack;
	}

	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
	{
		if (this.cooldown > 0)
		{
			cooldown--;
		}
	}

}
