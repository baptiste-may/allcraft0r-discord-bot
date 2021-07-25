package fr.djredstone.botdiscord.listener;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class messageRecivedListener extends ListenerAdapter {
	
	Main mainClass;
	
	public messageRecivedListener(Main main) {
		mainClass = main;
	}

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		if(!event.getAuthor().isBot()) {
			
			if(mainClass.messageByMinute.get(event.getAuthor()) == null) {
				
				mainClass.messageByMinute.put(event.getAuthor(), 0);
				
			}
			
			mainClass.messageByMinute.put(event.getAuthor(), mainClass.messageByMinute.get(event.getAuthor()) + 1);
			
			if(mainClass.messageByMinute.get(event.getAuthor()) > (Integer) mainClass.getConfig().get("minNbMessageWarn")) {
				
				Main.jda.getTextChannelById("497141089480998912").sendMessage("**" + event.getAuthor().getAsTag() + "** est à plus de " + mainClass.getConfig().get("minNbMessageWarn") + " messages par minutes (actuellement à " + mainClass.messageByMinute.get(event.getAuthor()) + ")").queue();
			}
			
		}
		
	}

}
