package fr.djredstone.botdiscord.listener;

import java.util.Random;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class messageReactionAddListener extends ListenerAdapter {
	
	Main main;
	
	Random r = new Random();
	
	public messageReactionAddListener(Main main) {
		this.main = main;
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		if(!event.getAuthor().isBot()) {
			if(r.nextBoolean()) {
				if(event.getMessage().getContentRaw().length() > 10) Main.setMoney(event.getAuthor(), Main.getMoney(event.getAuthor()) + 1);
			}
		}
	}

}
