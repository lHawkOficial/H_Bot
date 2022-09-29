package me.hbot.objects;

import lombok.Getter;

import me.hbot.managers.Manager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

@Getter
public class CommandBot {

	private RunnableBot runnable;
	private String command;
	
	public CommandBot(String command, RunnableBot runnable) {
		this.command = command;
		this.runnable = runnable;
		Manager.get().getCommands().add(this);
	}
	
	public void run(Member member, TextChannel channel, String[] args, Message message) {
		String msg = new String();
		Boolean arg = false;
		if (args.length != 1) {
			for (int i = 1; i < args.length; i++) {
				msg += " " + args[i];
			}
		} else arg = true;
		msg = msg.replaceFirst(" ", new String());
		args = msg.split(" ");
		runnable.setArgs(arg ? new String[0] : args);
		runnable.setChannel(channel);
		runnable.setMember(member);
		runnable.setMessage(message);
		runnable.run();
	}
	
}
