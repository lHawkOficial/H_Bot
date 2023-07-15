package me.hbot.objects.bot;

import java.util.EnumSet;

import org.bukkit.Bukkit;

import lombok.Getter;
import lombok.Setter;
import me.hbot.Core;
import me.hbot.listenersjda.ListenersJDA;
import me.hbot.objects.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

@Getter
@Setter
public class Bot {
	
	private final String token = Config.get().getToken();
	private JDA jda;
	private JDABuilder builder;
	private Guild guild;
	private boolean initialized = false;
	
	public Bot() {

		builder = JDABuilder.createDefault(token)
				.setAutoReconnect(true)
				.enableCache(EnumSet.allOf(CacheFlag.class))
				.setMemberCachePolicy(MemberCachePolicy.ALL)
				.setChunkingFilter(ChunkingFilter.ALL)
				.setEnabledIntents(EnumSet.allOf(GatewayIntent.class))
				.setStatus(OnlineStatus.DO_NOT_DISTURB);
		
		try {
			
			jda = builder.build();
			jda.awaitReady();
			jda.addEventListener(new ListenersJDA());
			guild = jda.getGuildById(Config.get().getGuildID());
			initialized = true;
			updateActivity();
			
		} catch (Exception e) {
			initialized = false;
			Core.getInstance().sendConsole(Core.getInstance().getTag() + " &cO H_Bot foi desligado, ignorando JDA.");
		}
		
	}
	
	public static Bot get() {
		return Core.getInstance().getBot();
	}
	
	public void updateActivity() {
		if (!initialized) return;
		Bukkit.getScheduler().runTaskAsynchronously(Core.getInstance(), ()-> {
			jda.getPresence().setActivity(Activity.playing(Bukkit.hasWhitelist() ? "[manutenção]" :  Config.get().getActivity().replace("{online}", String.valueOf(Bukkit.getOnlinePlayers().size())).replace("{max}", String.valueOf(Bukkit.getMaxPlayers()))));
		});
	}
	
	public void stop() {
		if (!initialized) return;
		jda.shutdownNow();
	}
	
}
