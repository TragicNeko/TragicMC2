package tragicneko.tragicmc.doomsday;

import java.util.ConcurrentModificationException;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ReportedException;
import net.minecraftforge.common.MinecraftForge;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayRapidFire extends Doomsday implements IThreadedDoomsday {

	public DoomsdayRapidFire(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.OVERFLOW);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		DoomThread thread = new DoomThread(this, doom, crucMoment, griefCheck);

		if (!thread.isAlive() && !thread.isInterrupted())
		{
			thread.start();
		}

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Rapid Fire!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsdayFromThread(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		float f = 1.0F;
		EntityArrow entityarrow = new EntityArrow(player.worldObj, player, f * 2.0F);
		ItemStack stack = player.getCurrentEquippedItem();
		double damage = 3.0;

		if (crucMoment)
		{
			damage += 3.0;
		}

		entityarrow.setDamage(entityarrow.getDamage() + damage);
		entityarrow.motionX *= 1.3;
		entityarrow.motionZ *= 1.3;
		entityarrow.motionY *= 1.1;

		if (f == 1.0F)
		{
			entityarrow.setIsCritical(true);
		}

		int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

		if (l > 0)
		{
			entityarrow.setKnockbackStrength(l);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
		{
			entityarrow.setFire(200);
		}

		player.worldObj.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		entityarrow.canBePickedUp = 2;

		try
		{
			if (!player.worldObj.isRemote)
			{
				player.worldObj.spawnEntityInWorld(entityarrow);
			}
		}
		catch (Throwable throwable)
		{
			CrashReport report = CrashReport.makeCrashReport(throwable, "Entity Tracking Error");
			CrashReportCategory cat = report.makeCategory("Entity to Track");
			cat.addCrashSection("Entity being Spawned", entityarrow);
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

		if (!player.capabilities.isCreativeMode)
		{
			this.applyDoomAndCooldown(doom);
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

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Rapid Fire!"));

		if (crucMoment)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
		}
	}

	@Override
	public void useDoomsdayFromThreadThroughCommand(PropertyDoom doom, EntityPlayer player, boolean crucMoment, boolean griefCheck)
	{
		float f = 1.0F;
		EntityArrow entityarrow = new EntityArrow(player.worldObj, player, f * 2.0F);
		ItemStack stack = player.getCurrentEquippedItem();
		double damage = 3.0;

		if (crucMoment)
		{
			damage += 3.0;
		}

		entityarrow.setDamage(entityarrow.getDamage() + damage);
		entityarrow.motionX *= 1.3;
		entityarrow.motionZ *= 1.3;
		entityarrow.motionY *= 1.1;

		if (f == 1.0F)
		{
			entityarrow.setIsCritical(true);
		}

		int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

		if (l > 0)
		{
			entityarrow.setKnockbackStrength(l);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
		{
			entityarrow.setFire(200);
		}

		player.worldObj.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		entityarrow.canBePickedUp = 2;

		try
		{
			if (!player.worldObj.isRemote)
			{
				player.worldObj.spawnEntityInWorld(entityarrow);
			}
		}
		catch (Throwable throwable)
		{
			CrashReport report = CrashReport.makeCrashReport(throwable, "Entity Tracking Error");
			CrashReportCategory cat = report.makeCategory("Entity to Track");
			cat.addCrashSection("Entity being Spawned", entityarrow);
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

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player,
			boolean griefCheck) {

	}

}
