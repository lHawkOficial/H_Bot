package me.hbot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.hbot.Core;

public class CommandBot implements CommandExecutor {

	public CommandBot() {
		Core.getInstance().getCommand("bot").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String lb, String[] args) {
		if (!s.hasPermission("*")) return false;
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				Core.getInstance().reloadPlugin();
				s.sendMessage(Core.getInstance().getTag() + " §aBot recarregado!");
				return false;
			}
		}
		s.sendMessage(Core.getInstance().getTag() + " §4Use §c/Bot [Reload]");
		return false;
	}
	
}
