package tragicneko.tragicmc.doomsday;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.common.MinecraftForge;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdaySuicidalTendencies extends Doomsday implements IThreadedDoomsday {

	public DoomsdaySuicidalTendencies(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.WORLDSHAPER);
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player,	boolean crucMoment, boolean griefCheck) 
	{

		DoomThread thread = new DoomThread(this, doom, crucMoment, griefCheck);

		if (!thread.isAlive() && !thread.isInterrupted())
		{
			thread.start();
		}

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Suicidal Tendencies!"));

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

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "You have used Suicidal Tendencies!"));

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

		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 100.0D;

		if (player instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance() + 96.0;
		}
		Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);

		MovingObjectPosition mop = player.worldObj.func_147447_a(vec3, vec31, true, false, true);

		if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK)
		{
			double d4 = mop.hitVec.xCoord - player.posX;
			double d5 = mop.hitVec.yCoord - (player.posY + (double)(player.height / 2.0F));
			double d6 = mop.hitVec.zCoord - player.posZ;

			EntityNekoRocket rocket = new EntityNekoRocket(player.worldObj, player, d4, d5, d6);
			rocket.posY = player.posY + player.getEyeHeight();

			try
			{
				if (!player.worldObj.isRemote)
				{
					player.worldObj.spawnEntityInWorld(rocket);
				}
			}
			catch (Throwable throwable)
			{
				CrashReport report = CrashReport.makeCrashReport(throwable, "Entity Tracking Error");
				CrashReportCategory cat = report.makeCategory("Entity to Track");
				cat.addCrashSection("Entity being Spawned", rocket);
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
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 100.0D;

		if (player instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance() + 96.0;
		}
		Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);

		MovingObjectPosition mop = player.worldObj.func_147447_a(vec3, vec31, true, false, true);

		if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK)
		{
			double d4 = mop.hitVec.xCoord - player.posX;
			double d5 = mop.hitVec.yCoord - (player.posY + (double)(player.height / 2.0F));
			double d6 = mop.hitVec.zCoord - player.posZ;

			EntityNekoRocket rocket = new EntityNekoRocket(player.worldObj, player, d4, d5, d6);
			rocket.posY = player.posY + player.getEyeHeight();

			try
			{
				if (!player.worldObj.isRemote)
				{
					player.worldObj.spawnEntityInWorld(rocket);
				}
			}
			catch (Throwable throwable)
			{
				CrashReport report = CrashReport.makeCrashReport(throwable, "Entity Tracking Error");
				CrashReportCategory cat = report.makeCategory("Entity to Track");
				cat.addCrashSection("Entity being Spawned", rocket);
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
