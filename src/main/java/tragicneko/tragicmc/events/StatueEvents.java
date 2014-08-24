package tragicneko.tragicmc.events;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import tragicneko.tragicmc.entity.EntityStatue;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityGreaterStin;
import tragicneko.tragicmc.entity.boss.EntityJarra;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityKragul;
import tragicneko.tragicmc.entity.boss.EntityMagmox;
import tragicneko.tragicmc.entity.boss.EntityMegaCryse;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityStinKing;
import tragicneko.tragicmc.entity.boss.EntityStinQueen;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class StatueEvents {

	@SubscribeEvent
	public void onStatueInteraction(EntityInteractEvent event)
	{
		if (!(event.target instanceof EntityStatue) || event.entityPlayer.worldObj.isRemote) return;

		EntityStatue statue = (EntityStatue) event.target;

		if (event.entityPlayer.getCurrentEquippedItem() != null)
		{
			ItemStack item = event.entityPlayer.getCurrentEquippedItem();

			if (item.getItem() == TragicItems.LifeWater)
			{
				int id = statue.getMobID();
				EntityLivingBase entity = null;

				switch(id)
				{
				case 0:
					if (TragicNewConfig.allowApis) entity = new EntityApis(statue.worldObj);
					break;
				case 1:
					if (TragicNewConfig.allowKitsune) entity = new EntityKitsune(statue.worldObj);
					break;
				case 2:
					if (TragicNewConfig.allowDeathReaper) entity = new EntityDeathReaper(statue.worldObj);
					break;
				case 3:
					if (TragicNewConfig.allowTimeController) entity = new EntityTimeController(statue.worldObj);
					break;
				case 4:
					if (TragicNewConfig.allowYeti) entity = new EntityYeti(statue.worldObj);
					break;
				case 5:
					if (TragicNewConfig.allowPolaris) entity = new EntityPolaris(statue.worldObj);
					break;
				case 6:
					if (TragicNewConfig.allowJarra) entity = new EntityJarra(statue.worldObj);
					break;
				case 7:
					if (TragicNewConfig.allowKragul) entity = new EntityKragul(statue.worldObj);
					break;
				case 8:
					if (TragicNewConfig.allowMagmox) entity = new EntityMagmox(statue.worldObj);
					break;
				case 9:
					if (TragicNewConfig.allowMegaCryse) entity = new EntityMegaCryse(statue.worldObj);
					break;
				case 10:
					if (TragicNewConfig.allowStinKing) entity = new EntityStinKing(statue.worldObj);
					break;
				case 11:
					if (TragicNewConfig.allowStinQueen) entity = new EntityStinQueen(statue.worldObj);
					break;
				case 12:
					if (TragicNewConfig.allowGreaterStin) entity = new EntityGreaterStin(statue.worldObj);
					break;
				}

				if (entity != null && !statue.worldObj.isRemote)
				{
					entity.copyLocationAndAnglesFrom(statue);
					((EntityLiving) entity).onSpawnWithEgg(null);
					statue.worldObj.removeEntity(statue);
					statue.worldObj.spawnEntityInWorld(entity);
					if (!event.entityPlayer.capabilities.isCreativeMode) event.entityPlayer.getCurrentEquippedItem().stackSize--;
				}
			}
			else
			{
				byte b0 = 0;

				if (item.getItem() == Items.iron_ingot)
				{
					b0 = 1;
				}
				else if (item.getItem() == Items.gold_ingot)
				{
					b0 = 2;
				}
				else if (item.getItem() == Items.diamond)
				{
					b0 = 3;
				}
				else if (item.getItem() == Item.getItemFromBlock(Blocks.stone))
				{
					b0 = 4;
				}
				else if (item.getItem() == Item.getItemFromBlock(Blocks.log))
				{
					b0 = 5;
				}
				else if (item.getItem() == Items.emerald)
				{
					b0 = 6;
				}
				else if (item.getItem() == TragicItems.RedMercury)
				{
					b0 = 7;
				}
				else if (item.getItem() == TragicItems.Tungsten)
				{
					b0 = 8;
				}
				else if (item.getItem() == TragicItems.Ruby)
				{
					b0 = 9;
				}
				else if (item.getItem() == TragicItems.Sapphire)
				{
					b0 = 10;
				}
				else if (item.getItem() == Items.redstone)
				{
					b0 = 11;
				}
				else if (item.getItem() == Items.coal)
				{
					b0 = 12;
				}
				else if (item.getItem() == Items.dye && item.getItemDamage() == 4)
				{
					b0 = 13;
				}
				else if (item.getItem() == Item.getItemFromBlock(Blocks.obsidian))
				{
					b0 = 14;
				}
				else if (item.getItem() == Item.getItemFromBlock(TragicBlocks.DisappearingBlock)) //Creative mode only option, this will be utilized to "animate" the statue later
				{
					b0 = 15;
				}
				else if (item.getItem() == Items.blaze_powder || item.getItem() == Items.blaze_rod) //to reset the texture
				{
					b0 = 0;
				}

				if (b0 != statue.getTextureID())
				{
					statue.setTextureID(b0);
					if (!event.entityPlayer.capabilities.isCreativeMode) event.entityPlayer.setCurrentItemOrArmor(0, null);
				}
			}
		}
		else
		{
			statue.incrementRotationAngle();
		}
	}
}
