package me.hbot.objects;



import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

@Getter
@Setter
public abstract class RunnableBot implements Runnable {

	private String[] args;
	private Member member;
	private TextChannel channel;
	private Message message;

}
