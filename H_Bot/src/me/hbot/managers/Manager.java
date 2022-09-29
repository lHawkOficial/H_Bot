package me.hbot.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import me.hbot.Core;
import me.hbot.objects.CommandBot;

@Getter
public class Manager {

	public static Manager get() {return Core.getInstance().getManager();}
	private List<CommandBot> commands = new ArrayList<>();
	
	public CommandBot getCommand(String cmd) {
		Iterator<CommandBot> it = commands.iterator();
		while(it.hasNext()) {
			CommandBot cb = it.next();
			if (cb.getCommand().equalsIgnoreCase(cmd)) return cb;
		}
		return null;
	}
	
}
