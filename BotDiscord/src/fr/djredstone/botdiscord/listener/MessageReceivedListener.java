package fr.djredstone.botdiscord.listener;

import java.util.Random;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceivedListener extends ListenerAdapter {
	
	Main main;
	
	Random r = new Random();
	
	public MessageReceivedListener(Main main) {
		this.main = main;
	}
	
	public void onGuildMessageReceived(MessageReceivedEvent event) {
		if(!event.getAuthor().isBot()) {
			if(r.nextBoolean()) {
				if(event.getMessage().getContentRaw().length() > 10) Main.setMoney(event.getAuthor(), Main.getMoney(event.getAuthor()) + 1);
			}
		}
	}

}
