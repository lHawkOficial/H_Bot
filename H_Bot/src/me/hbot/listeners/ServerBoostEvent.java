package me.hbot.listeners;

import org.bukkit.event.Cancellable;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Member;

@Getter
public class ServerBoostEvent extends Event implements Cancellable  {

	private static final HandlerList HANDLERS_LIST = new HandlerList();
	
	private Member member;
	
	public ServerBoostEvent(Member member) {
		this.member = member;
	}
	
	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public void setCancelled(boolean arg0) {
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return HANDLERS_LIST;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS_LIST;
	}

	
	
}
