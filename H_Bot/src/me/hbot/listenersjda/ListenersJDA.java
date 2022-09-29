package me.hbot.listenersjda;

import org.bukkit.Bukkit;



import me.hbot.listeners.ServerBoostEvent;
import me.hbot.managers.Manager;
import me.hbot.objects.CommandBot;
import me.hbot.objects.bot.Bot;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ListenersJDA extends ListenerAdapter {

	@Override
	public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent e) {
		if (!e.getGuild().equals(Bot.get().getGuild())) return;
		ServerBoostEvent event = new ServerBoostEvent(e.getMember());
		Bukkit.getPluginManager().callEvent(event);
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if (e.getAuthor().isBot()) return;
		if (!e.isFromGuild()) return;
		if (e.getChannelType() != ChannelType.TEXT) return;
		TextChannel channel = (TextChannel) e.getChannel();
		Member member = e.getMember();
		String[] args = e.getMessage().getContentStripped().split(" ");
		if (args[0].startsWith("/")) {
			String cmd = args[0].replaceFirst("/", new String());
			CommandBot cb = Manager.get().getCommand(cmd);
			if (cb != null) {
				cb.run(member, channel, args, e.getMessage());
			}
		}
	}
	
}
