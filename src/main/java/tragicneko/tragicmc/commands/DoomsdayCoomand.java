package tragicneko.tragicmc.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayCoomand extends CommandBase {

	private List aliases;

	public DoomsdayCoomand()
	{
		this.aliases = new ArrayList();
		this.aliases.add("doomsday");
		this.aliases.add("Doomsday");
	}

	@Override
	public String getCommandName() {
		return "doomsday";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "Usage: /doomsday <player> <doomsday id>";
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	public void processCommand(ICommandSender var1, String[] var2) {
		if (var2.length != 2)
		{
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + this.getCommandUsage(var1)));
			return;
		}

		EntityPlayerMP mp = getPlayer(var1, var2[0]);

		PropertyDoom doom = PropertyDoom.get(mp);

		if (mp.isDead)
		{
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You are dead and cannot use this command right now."));
			return;
		}

		boolean flag;

		try
		{
			Integer.parseInt(var2[1]);
			flag = true;
		}
		catch (NumberFormatException e)
		{
			flag = false;
		}

		Doomsday doomsday = null;
		int id = 0;
		String s = null;

		if (flag)
		{
			id = Integer.valueOf(var2[1]);

			if (id > Doomsday.doomsdayList.length || id == 0)
			{
				var1.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This id does not correspond to a Doomsday."));
				return;
			}

			doomsday = Doomsday.getDoomsdayFromId(id);
		}
		else
		{
			s = var2[1];

			if (Doomsday.stringToIDMapping.get(s) != null)
			{
				doomsday = Doomsday.getDoomsdayFromId(Doomsday.stringToIDMapping.get(s));
			}
			else
			{
				var1.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This id does not correspond to a Doomsday."));
				return;
			}
		}

		if (doomsday == null)
		{
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "There was an error using that Doomsday."));
			return;
		}

		boolean crucMoment = false;
		if (TragicNewConfig.allowCrucialMoments && doom.getPlayer().worldObj.rand.nextInt(100) <= TragicNewConfig.crucialMomentChance) crucMoment = true;
		doomsday.useDoomsdayThroughCommand(doom, doom.getPlayer(), crucMoment, doomsday.getMobGriefing(doom.getPlayer().worldObj));
	}

	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, this.getAllUsernames()) : (par2ArrayOfStr.length == 2 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, Doomsday.doomsdayNames) :null);
	}

	protected String[] getAllUsernames()
	{
		return MinecraftServer.getServer().getAllUsernames();
	}

	public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2)
	{
		return par2 == 0;
	}

}
