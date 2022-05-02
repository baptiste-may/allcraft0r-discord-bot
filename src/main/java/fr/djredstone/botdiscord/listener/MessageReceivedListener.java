package fr.djredstone.botdiscord.listener;

import java.sql.SQLException;
import java.util.Random;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.classes.money;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceivedListener extends ListenerAdapter {
	
	Main main;
	
	Random r = new Random();
	
	public MessageReceivedListener(Main main) {
		this.main = main;
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(!event.getAuthor().isBot()) {
			if(r.nextBoolean()) {
				if(event.getMessage().getContentRaw().length() > 10)
					try {
						money.add(event.getAuthor(), 1);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}

}
