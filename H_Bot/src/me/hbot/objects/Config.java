package me.hbot.objects;

import org.bukkit.configuration.ConfigurationSection;

import lombok.Getter;
import me.hbot.Core;

@Getter
public class Config {
	
	public String activity, 
	token,
	guildID;
	
	public Config() {
		ConfigurationSection section = Core.getInstance().getConfig().getConfigurationSection("Config");
		activity = section.getString("activity");
		token = section.getString("token");
		guildID = section.getString("guildID");
	}
	
	public static Config get() {
		return Core.getInstance().getConfigbot();
	}

}
