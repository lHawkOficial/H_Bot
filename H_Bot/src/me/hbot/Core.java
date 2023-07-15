package me.hbot;



import org.bukkit.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.hbot.commands.CommandBot;
import me.hbot.listeners.spigot.PlayersListeners;
import me.hbot.managers.Manager;
import me.hbot.objects.Config;
import me.hbot.objects.bot.Bot;
import me.hbot.utils.API;

@Getter
public class Core extends JavaPlugin {
	
	@Getter
	private static Core instance;
	private String tag, version = "§dv" + getDescription().getVersion();
	private API api;
	private Manager manager;
	private Bot bot;
	private Config configbot;
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		if(getConfig().getString("Config.token").isEmpty()) return;
		reloadPlugin();
		new PlayersListeners();
		new CommandBot();
		sendConsole(" ", tag + " &aH_Bot iniciado com sucesso! &6[Author lHawk_] " + version, " ");
		
	}
	
	@Override
	public void onDisable() {
		bot.stop();
		sendConsole(" ", tag + " &cH_Bot desligado com sucesso! &6[Author lHawk_] " + version, " ");
	}
	
	public void reloadPlugin() {
		reloadConfig();
		tag = getConfig().getString("Config.tag").replace("&", "§");
		api = new API();
		manager = new Manager();
		configbot = new Config();
		if (bot!=null) bot.stop();
		bot = new Bot();
	}
	
	public void sendConsole(String... msg) {for(String m : msg) Bukkit.getConsoleSender().sendMessage(m.replace("&", "§"));}
	
}
