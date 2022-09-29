package me.hbot.listeners.spigot;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.hbot.Core;
import me.hbot.objects.bot.Bot;

public class PlayersListeners implements Listener {

	public PlayersListeners() {
		Bukkit.getPluginManager().registerEvents(this, Core.getInstance());
	}
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		Bot.get().updateActivity();
	}
	
	@EventHandler
	public void quit(PlayerQuitEvent e) {
		Bot.get().updateActivity();
	}
	
}
