package tragicneko.tragicmc.commands;

import java.util.ArrayList;
import java.util.List;

import tragicneko.tragicmc.properties.PropertyDoom;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class DoomCommand extends CommandBase {

	private List aliases;

	public DoomCommand()
	{
		this.aliases = new ArrayList();
		this.aliases.add("doom");
		this.aliases.add("Doom");
	}
	
	@Override
	public String getCommandName() {
		return "doom";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "Usage: /doom <player> <int to apply>";
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	@Override
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

		int amount = Integer.valueOf(var2[1]);
		
		if (amount == 0)
		{
			doom.setCooldown(0);
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Player cooldown was removed."));
			return;
		}
		
		if (doom.getCurrentDoom() == doom.getMaxDoom() && amount > 0)
		{
			doom.setCooldown(0);
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Player cooldown was removed."));
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Player Doom is already at max."));
			return;
		}
		
		if (doom.getCurrentDoom() == 0 && amount < 0)
		{
			doom.setCooldown(0);
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Player cooldown was removed."));
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Player Doom is already at 0."));
			return;
		}
		
		if (doom != null)
		{
			doom.increaseDoom(amount);
			doom.setCooldown(0);
		}

		if (amount + doom.getCurrentDoom() >= doom.getMaxDoom())
		{
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Player Doom is full now."));
		}
		
		if (amount + doom.getCurrentDoom() <= 0)
		{
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Player Doom is empty now."));
		}
	}

	public int getRequiredPermissionLevel()
    {
        return 2;
    }

	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, this.getAllUsernames()) : null;
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
