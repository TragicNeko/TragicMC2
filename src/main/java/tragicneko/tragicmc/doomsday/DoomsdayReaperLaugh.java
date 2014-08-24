package tragicneko.tragicmc.doomsday;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraftforge.common.MinecraftForge;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayReaperLaugh extends Doomsday implements IThreadedDoomsday {

	public DoomsdayReaperLaugh(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.OVERFLOW);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player,	boolean crucMoment, boolean griefCheck) 
	{
		DoomThread thread = new DoomThread(this, doom, crucMoment, griefCheck);

		if (!thread.isAlive() && !thread.isInterrupted())
		{
			thread.start();
		}

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Reaper Laugh!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsdayThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		DoomThread thread = new DoomThread(this, doom, crucMoment, griefCheck, true);

		if (!thread.isAlive() && !thread.isInterrupted())
		{
			thread.start();
		}

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Reaper Laugh!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player,
			boolean griefCheck) {

	}

	@Override
	public void useDoomsdayFromThread(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		if (!player.capabilities.isCreativeMode)
		{
			this.applyDoomAndCooldown(doom);
		}
		
		if (TragicNewConfig.allowImmunity) player.addPotionEffect(new PotionEffect(TragicPotions.Immunity.id, 600, 0));

		for (int l = 0; l < 5; l++)
		{
			double d0 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posX) - player.posX; 
			double d1 = (MathHelper.getRandomIntegerInRange(rand, -2, 2) + player.posY) - player.posY;
			double d2 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posZ) - player.posZ; 

			EntityWitherSkull fireball = new EntityWitherSkull(player.worldObj, player, d0, d1, d2);

			fireball.setPosition(player.posX + (d0 * 0.1), player.posY + 1.0D, player.posZ + (d2 * 0.1));

			try
			{
				if (!player.worldObj.isRemote)
				{
					player.worldObj.spawnEntityInWorld(fireball);
				}
			}
			catch (Throwable throwable)
			{
				CrashReport report = CrashReport.makeCrashReport(throwable, "Entity Tracking Error");
				CrashReportCategory cat = report.makeCategory("Entity to Track");
				cat.addCrashSection("Entity being Spawned", fireball);
				cat.addCrashSection("Player Using Doomsday", player);
				cat.addCrashSection("Doomsday being Used", this);
				CrashReportCategory cat2 = report.makeCategory("General mod info");
				cat2.addCrashSection("Mod Version", TragicMC.VERSION);
				cat2.addCrashSection("Forge Version", MinecraftForge.getBrandingVersion());

				try
				{
					throw new ReportedException(report);
				}
				catch (ReportedException e)
				{
					TragicMC.logger.error("Silently catching entity tracking error due to concurrent modification", e);
				}
			}
		}	
	}

	@Override
	public void useDoomsdayFromThreadThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck) 
	{
		if (TragicNewConfig.allowImmunity) player.addPotionEffect(new PotionEffect(TragicPotions.Immunity.id, 600, 0));
		
		for (int l = 0; l < 5; l++)
		{
			double d0 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posX) - player.posX; 
			double d1 = (MathHelper.getRandomIntegerInRange(rand, -2, 2) + player.posY) - player.posY;
			double d2 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + player.posZ) - player.posZ; 

			EntityWitherSkull fireball = new EntityWitherSkull(player.worldObj, player, d0, d1, d2);

			fireball.setPosition(player.posX + (d0 * 0.1), player.posY + 1.0D, player.posZ + (d2 * 0.1));

			try
			{
				if (!player.worldObj.isRemote)
				{
					player.worldObj.spawnEntityInWorld(fireball);
				}
			}
			catch (Throwable throwable)
			{
				CrashReport report = CrashReport.makeCrashReport(throwable, "Entity Tracking Error");
				CrashReportCategory cat = report.makeCategory("Entity to Track");
				cat.addCrashSection("Entity being Spawned", fireball);
				cat.addCrashSection("Player Using Doomsday", player);
				cat.addCrashSection("Doomsday being Used", this);
				CrashReportCategory cat2 = report.makeCategory("General mod info");
				cat2.addCrashSection("Mod Version", TragicMC.VERSION);
				cat2.addCrashSection("Forge Version", MinecraftForge.getBrandingVersion());

				try
				{
					throw new ReportedException(report);
				}
				catch (ReportedException e)
				{
					TragicMC.logger.error("Silently catching entity tracking error due to concurrent modification", e);
				}
			}
		}
	}

}
